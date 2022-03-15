package mappers;

import java.util.ArrayList;

import Interfaces.MetalMapperInterface;
import datasource.ChemicalRDG;
import datasource.DatabaseException;
import datasource.ElementRDG;
import datasource.MetalDTO;
import datasource.MetalRDG;
import datasource.MetalTDG;
import domainObjects.MetalDomainObject;

public class MetalMapper implements MetalMapperInterface {

	private int ident;
	private String name;
	private int atomicNumber;
	private double atomicMass;
	private int dissolvedBy;
	private double moles;
	private double molesOfAcidToDissolve;
	private MetalDomainObject mdo;

	/**
	 * Returns a metal domain object and sets its mapper to this. Does not change the database.
	 */
	@Override
	public MetalDomainObject createMetal(int ID, String elementName, int atomicNumber, double atomicMass,
			int dissolvedBy, double moles, double molesOfAcidToDissolve) throws Exception {
		ident = ID;
		this.name = elementName;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		this.dissolvedBy = dissolvedBy;
		this.moles = moles;
		this.molesOfAcidToDissolve = molesOfAcidToDissolve;

		return new MetalDomainObject(this);
	}

	/**
	 * returns the metal that has the id that is passed in.
	 */
	@Override
	public MetalDomainObject findByID(int mID) throws Exception {
		MetalRDG m = MetalRDG.findByID(mID);
		ElementRDG e = ElementRDG.findByID(mID);
		ChemicalRDG c = ChemicalRDG.findByID(mID);

		ident = mID;
		name = c.getChemicalName();
		atomicNumber = e.getAtomicNumber();
		atomicMass = e.getAtomicMass();
		dissolvedBy = m.getDissolvedBy();
		moles = c.getChemicalMoles();
		molesOfAcidToDissolve = m.getMolesOfAcidToDissolve();

		return new MetalDomainObject(this);
	}

	/**
	 * returns all of the metals in the database.
	 */
	@Override
	public ArrayList<MetalDomainObject> getAllMetals() throws Exception {
		ArrayList<MetalDTO> metals = MetalTDG.getSingleton().getAllMetals();
		ArrayList<MetalDomainObject> list = new ArrayList<>();
		for (MetalDTO m : metals) {
			ChemicalRDG c = ChemicalRDG.findByID(m.getID());
			ElementRDG e = ElementRDG.findByID(m.getID());
			list.add(createMetal(m.getID(), c.getChemicalName(), e.getAtomicNumber(), e.getAtomicMass(),
					m.getDissolvedBy(), c.getChemicalMoles(), m.getMolesOfAcidToDissolve()));
		}
		return list;
	}

	/**
	 * uses an rdg to persist updates of the metal to the database.
	 */
	@Override
	public void persist() {
		try {
			MetalRDG m = MetalRDG.findByID(ident);
			ChemicalRDG c = ChemicalRDG.findByID(ident);
			ElementRDG e = ElementRDG.findByID(ident);
			if (m == null && c == null && e == null) {
				c = new ChemicalRDG(ident, name, moles);
				e = new ElementRDG(ident, atomicNumber, atomicMass);
				m = new MetalRDG(ident, dissolvedBy, molesOfAcidToDissolve);
				c.insert();
				e.insert();
				m.insert();
				System.out.println("Metal created with ID " + ident);
			} else {
				atomicMass = mdo.getMetalAtomicMass();
				atomicNumber = mdo.getMetalAtomicNumber();
				dissolvedBy = mdo.getDissolvedBy();
				moles = mdo.getMoles();
				molesOfAcidToDissolve = mdo.getMolesOfAcidToDissolve();
				name = mdo.getMetalName();

				e.setAtomicMass(atomicMass);
				e.setAtomicNumber(atomicNumber);
				m.setDissolvedBy(dissolvedBy);
				c.setChemicalMoles(moles);
				m.setMolesOfAcidToDissolve(molesOfAcidToDissolve);
				c.setName(name);
				c.update();
				e.update();
				m.update();
			}
		} catch (Exception e) {
			DatabaseException.detectError(e, "Error spotted in the MetalMapper class, Persist method");
		}
	}

	/**
	 * setter for id.
	 */
	public void setIdent(int ident) {
		this.ident = ident;
	}

	/**
	 * setter for name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * setter for atomic number.
	 */
	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	/**
	 * setter for atomic mass.
	 */
	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}

	/**
	 * setter for acid id that dissolves this metal.
	 */
	public void setDissolvedBy(int dissolvedBy) {
		this.dissolvedBy = dissolvedBy;
	}

	/**
	 * setter for moles.
	 */
	public void setMoles(double moles) {
		this.moles = moles;
	}

	/**
	 * setter for the moles of acid needed to dissolve this metal.
	 */
	public void setMolesOfAcidToDissolve(double molesOfAcidToDissolve) {
		this.molesOfAcidToDissolve = molesOfAcidToDissolve;
	}

	/**
	 * getter for id.
	 */
	public int getIdent() {
		return ident;
	}

	/**
	 * getter for name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for atomic number.
	 */
	public int getAtomicNumber() {
		return atomicNumber;
	}

	/**
	 * getter for atomic mass.
	 */
	public double getAtomicMass() {
		return atomicMass;
	}

	/**
	 * getter for acid id that dissolves this metal.
	 */
	public int getDissolvedBy() {
		return dissolvedBy;
	}

	/**
	 * getter for moles.
	 */
	public double getMoles() {
		return moles;
	}

	/**
	 * getter for moles of acid required to dissolve this metal.
	 */
	public double getMolesOfAcidToDissolve() {
		return molesOfAcidToDissolve;
	}

	/**
	 * getter for this mapper's domain object.
	 */
	public MetalDomainObject getMetal() {
		return mdo;
	}

	/**
	 * setter for this mapper's domain object.
	 */
	public void setMetal(MetalDomainObject mdo) {
		this.mdo = mdo;
	}

}