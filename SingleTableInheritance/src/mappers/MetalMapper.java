package mappers;

import java.util.ArrayList;

import Interfaces.MetalMapperInterface;
import datasource.ChemicalDTO;
import datasource.ChemicalRDG;
import datasource.ChemicalTDG;
import datasource.DatabaseException;
import domainObjects.MetalDomainObject;

public class MetalMapper implements MetalMapperInterface {

	private static final int type = 4;
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
		ChemicalRDG rdg = ChemicalRDG.findByIDSingle(mID);

		ident = mID;
		name = rdg.getName();
		atomicNumber = rdg.getAtomicNumber();
		atomicMass = rdg.getAtomicMass();
		dissolvedBy = rdg.getDissolvedBy();
		moles = rdg.getMoles();
		molesOfAcidToDissolve = rdg.getMolesOfAcidToDissolve();

		return new MetalDomainObject(this);
	}

	/**
	 * returns all of the metals in the database.
	 */
	@Override
	public ArrayList<MetalDomainObject> getAllMetals() throws Exception {
		ArrayList<ChemicalDTO> metals = ChemicalTDG.getSingleton().getAllMetals();
		ArrayList<MetalDomainObject> list = new ArrayList<>();
		for (ChemicalDTO m : metals) {
			list.add(createMetal(m.getChemicalID(), m.getChemicalName(), m.getChemicalAtomicNumber(),
					m.getChemicalAtomicMass(), m.getChemicalDissolvedBy(), m.getChemicalMoles(),
					m.getChemicalMolesToDissolveMetal()));
		}
		return list;
	}

	/**
	 * uses an rdg to persist updates of the metal to the database.
	 */
	@Override
	public void persist() {
		try {
			ChemicalRDG metal = ChemicalRDG.findByIDSingle(ident);
			if (metal == null) {
				ChemicalRDG rdg = new ChemicalRDG(ident, type, name, atomicNumber, atomicMass, dissolvedBy, moles,
						molesOfAcidToDissolve);
				rdg.insert();
				System.out.println("Metal created with ID " + ident);
			} else {
				atomicMass = this.getMetal().getMetalAtomicMass();
				atomicNumber = this.getMetal().getMetalAtomicNumber();
				dissolvedBy = this.getMetal().getDissolvedBy();
				moles = this.getMetal().getMoles();
				molesOfAcidToDissolve = this.getMetal().getMolesOfAcidToDissolve();
				name = this.getMetal().getMetalName();

				metal.setAtomicMass(atomicMass);
				metal.setAtomicNumber(atomicNumber);
				metal.setDissolvedBy(dissolvedBy);
				metal.setMoles(moles);
				metal.setMolesOfAcidToDissolve(molesOfAcidToDissolve);
				metal.setName(name);
				metal.update();
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