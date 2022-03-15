package mappers;

import java.util.ArrayList;

import Interfaces.ChemicalMapperInterface;
import datasource.AcidDTO;
import datasource.AcidRDG;
import datasource.AcidTDG;
import datasource.BaseDTO;
import datasource.BaseRDG;
import datasource.BaseTDG;
import datasource.CompoundDTO;
import datasource.CompoundRDG;
import datasource.CompoundTDG;
import datasource.DatabaseException;
import datasource.ElementDTO;
import datasource.ElementRDG;
import datasource.ElementTDG;
import datasource.MetalDTO;
import datasource.MetalRDG;
import datasource.MetalTDG;
import domainObjects.ChemicalDomainObject;

/**
 * @author Joshua, Ace
 * ChemicalMapper for Concrete Table Inheritance.
 */
public class ChemicalMapper implements ChemicalMapperInterface {

	private int ID;
	private String name;
	private double moles;
	private ChemicalDomainObject cdo;

	/**
	 * Returns a chemical domain object and sets its mapper to this. Does not change the database.
	 */
	@Override
	public ChemicalDomainObject createChemical(int ID, String name, double moles) throws Exception {
		this.ID = ID;
		this.name = name;
		this.moles = moles;

		return new ChemicalDomainObject(this);
	}

	/**
	 * persists any changes that were made to this chemical to the database.
	 */
	@Override
	public void persist() throws DatabaseException {
		ElementRDG element = ElementRDG.findByID(ID);
		CompoundRDG compound = CompoundRDG.findByIDConcrete(ID);
		MetalRDG metal = MetalRDG.findByID(ID);
		AcidRDG acid = AcidRDG.findByID(ID);
		BaseRDG base = BaseRDG.findByID(ID);

		moles = cdo.getChemicalMoles();
		if (element != null) {
			element.setMoles(moles);
			element.update();
		} else if (compound != null) {
			compound.setCompoundMoles(moles);
			compound.update();
		} else if (metal != null) {
			metal.setMoles(moles);
			metal.update();
		} else if (acid != null) {
			acid.setAcidMoles(moles);
			acid.update();
		} else if (base != null) {
			base.setMoles(moles);
			base.update();
		}
	}

	/**
	 * returns the chemical with the passed in id.
	 */
	@Override
	public ChemicalDomainObject findByID(int chemicalID) throws Exception {
		ElementRDG element = ElementRDG.findByID(chemicalID);
		CompoundRDG compound = CompoundRDG.findByIDConcrete(chemicalID);
		MetalRDG metal = MetalRDG.findByID(chemicalID);
		AcidRDG acid = AcidRDG.findByID(chemicalID);
		BaseRDG base = BaseRDG.findByID(chemicalID);

		if (element != null) {
			cdo = createChemical(element.getID(), element.getName(), element.getMoles());
		} else if (compound != null) {
			cdo = createChemical(compound.getCompoundID(), compound.getCompoundName(), compound.getCompoundMoles());
		} else if (metal != null) {
			cdo = createChemical(metal.getID(), metal.getName(), metal.getMoles());
		} else if (acid != null) {
			cdo = createChemical(acid.getAcidID(), acid.getAcidName(), acid.getAcidMoles());
		} else if (base != null) {
			cdo = createChemical(base.getID(), base.getName(), base.getMoles());
		}

		return cdo;
	}

	/**
	 * Find a chemical from its name
	 * 
	 * @author mad&ad
	 * 
	 * @param name da name
	 * @return da chem
	 * @throws Exception da error
	 */
	@Override
	public ChemicalDomainObject findByName(String name) throws Exception {
		ElementRDG element = ElementRDG.findByName(name);
		CompoundRDG compound = CompoundRDG.findByNameConcrete(name);
		MetalRDG metal = MetalRDG.findByName(name);
		AcidRDG acid = AcidRDG.findByName(name);
		BaseRDG base = BaseRDG.findByName(name);

		if (element != null) {
			cdo = createChemical(element.getID(), element.getName(), element.getMoles());
		} else if (compound != null) {
			cdo = createChemical(compound.getCompoundID(), compound.getCompoundName(), compound.getCompoundMoles());
		} else if (metal != null) {
			cdo = createChemical(metal.getID(), metal.getName(), metal.getMoles());
		} else if (acid != null) {
			cdo = createChemical(acid.getAcidID(), acid.getAcidName(), acid.getAcidMoles());
		} else if (base != null) {
			cdo = createChemical(base.getID(), base.getName(), base.getMoles());
		}

		return cdo;
	}

	/**
	 * returns all chemicals that, if the are elements, have less than 20 moles remaining or dont 
	 * have enough remaining to replenish all compounds that contain it, or if they are acids, dont
	 * have enough to dissolve all metals that can be dissolved by it, or if they are bases, have less
	 * than 40 moles remaining.
	 */
	@Override
	public ArrayList<ChemicalDomainObject> findLowChemicals() throws Exception {
		ArrayList<ChemicalDomainObject> lowChemicals = new ArrayList<ChemicalDomainObject>();

		// elements that have less than 20 moles
		ArrayList<ElementDTO> elements = ElementTDG.getInstance().getAllElements();
		for (ElementDTO e : elements) {
			if (e.getMoles() < 20) {
				lowChemicals.add(createChemical(e.getID(), e.getName(), e.getMoles()));
			}
		}

		// elements that don't have enough to replenish all compounds that contain it
		for (ElementDTO e : elements) {
			ArrayList<CompoundDTO> compounds = CompoundTDG.getSingleton().getCompoundsByElement(e.getID());
			double totalToReplenish = 0;
			for (CompoundDTO c : compounds) {
				ArrayList<ElementDTO> allElementsInCompound = ElementTDG.getInstance()
						.getElementsInCompound(c.getCompoundID());
				int numAtoms = 0; // total number of atoms in the compound
				for (ElementDTO containedElement : allElementsInCompound) {
					numAtoms += ElementRDG.findQuantityInCompound(containedElement.getID(), c.getCompoundID());
				}
				int thisElement = ElementRDG.findQuantityInCompound(e.getID(), c.getCompoundID());

				// calculate percentage that the element makes up of total number of moles
				totalToReplenish += thisElement / numAtoms * c.getCompoundMoles();
			}
			if (e.getMoles() < totalToReplenish) {
				lowChemicals.add(createChemical(e.getID(), e.getName(), e.getMoles()));
			}
		}

		// acids that don't have enough to dissolve all metals that can be dissolved by
		// it
		ArrayList<AcidDTO> acids = AcidTDG.getSingleton().getAllAcids();
		for (AcidDTO a : acids) {
			ArrayList<MetalDTO> metals = MetalTDG.getInstance().getMetalsDissolvedByAcid(a.getAcidID());
			double totalToDissolve = 0;
			for (MetalDTO m : metals) {
				totalToDissolve += m.getMolesOfAcidToDissolve();
			}
			if (a.getAcidMoles() < totalToDissolve) {
				lowChemicals.add(createChemical(a.getAcidID(), a.getAcidName(), a.getAcidMoles()));
			}
		}

		// bases that have less than 40 moles
		ArrayList<BaseDTO> bases = BaseTDG.getAllBases();
		for (BaseDTO b : bases) {
			if (b.getMoles() < 40) {
				lowChemicals.add(createChemical(b.getID(), b.getName(), b.getMoles()));
			}
		}

		return lowChemicals;
	}

	/**
	 * getter for id.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * getter for name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for moles.
	 */
	public double getMoles() {
		return moles;
	}

	/**
	 * getter for this mapper's domain object.
	 */
	public ChemicalDomainObject getCdo() {
		return cdo;
	}

	/**
	 * setter for this mapper's domain object.
	 */
	public void setCdo(ChemicalDomainObject cdo) {
		this.cdo = cdo;
	}

}