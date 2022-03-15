package datasource;

/**
 * Data Transfer Object for Single Chemical
 * 
 * @authors Madeline & Adam
 *
 */
public class ChemicalDTO {

	private int chemicalID;
	private int chemicalType;
	private String chemicalName;
	private int chemicalAtomicNumber;
	private double chemicalAtomicMass;
	private int chemicalDissolvedBy;
	private int chemicalSoluteA;
	private int chemicalSoluteB;
	private double chemicalMoles;
	private double chemicalMolesToDissolveMetal;

	/**
	 * Constructor for ChemicalDTO
	 * 
	 * @param ID           the Chemical's ID number
	 * @param type         the type of the Chemical--0 Chemical, 1 Base, 2 Acid, 3
	 *                     Element, 4 Metal, 5 Compound
	 * @param name         the name of the Chemical
	 * @param atomicNumber the atomic number of an Element or Metal
	 * @param atomicMass   the atomic mass of an Element or Metal
	 * @param dissolvedBy  the Acid ID that dissolves a Metal
	 * @param soluteA      the Chemical ID that is the solute of an Acid
	 * @param soluteB      the Chemical ID that is the solute of a Base
	 * @param moles        the number of moles of the chemical that we have in
	 *                     inventory
	 * @param number       of moles to dissolve metals
	 */
	public ChemicalDTO(int ID, int type, String name, int atomicNumber, double atomicMass, int dissolvedBy, int soluteA,
			int soluteB, double moles, double dissolveMoles) {
		chemicalID = ID;
		chemicalType = type;
		chemicalName = name;
		chemicalAtomicNumber = atomicNumber;
		chemicalAtomicMass = atomicMass;
		chemicalDissolvedBy = dissolvedBy;
		chemicalSoluteA = soluteA;
		chemicalSoluteB = soluteB;
		chemicalMoles = moles;
		chemicalMolesToDissolveMetal = dissolveMoles;
	}

	/**
	 * constructor for acid or base
	 * 
	 * @param ID
	 * @param type
	 * @param name
	 * @param solute
	 * @param moles
	 */
	public ChemicalDTO(int ID, int type, String name, int solute, double moles) {
		chemicalID = ID;
		chemicalType = type;
		chemicalName = name;
		if (type == 1) {
			chemicalSoluteB = solute;
		} else if (type == 2) {
			chemicalSoluteA = solute;
		}
		chemicalMoles = moles;
	}

	/**
	 * constructor for metal
	 * 
	 * @param ID
	 * @param type
	 * @param atomicNumber
	 * @param dissolvedBy
	 * @param atomicMass
	 * @param name
	 * @param moles
	 * @param molesOfAcidToDissolve
	 */
	public ChemicalDTO(int ID, int type, String name, int atomicNumber, double atomicMass, int dissolvedBy,
			double moles, double molesOfAcidToDissolve) {
		chemicalID = ID;
		chemicalType = type;
		chemicalName = name;
		chemicalAtomicNumber = atomicNumber;
		chemicalAtomicMass = atomicMass;
		chemicalDissolvedBy = dissolvedBy;
		chemicalMoles = moles;
		chemicalMolesToDissolveMetal = molesOfAcidToDissolve;
	}

	/**
	 * constructor for element
	 * 
	 * @param ID
	 * @param type
	 * @param name
	 * @param atomicNumber
	 * @param atomicMass
	 * @param moles
	 */
	public ChemicalDTO(int ID, int type, String name, int atomicNumber, double atomicMass, double moles) {
		chemicalID = ID;
		chemicalType = type;
		chemicalName = name;
		chemicalAtomicNumber = atomicNumber;
		chemicalAtomicMass = atomicMass;
		chemicalMoles = moles;
	}

	/**
	 * constructor for compound
	 * 
	 * @param ID
	 * @param type
	 * @param name
	 * @param moles
	 */
	public ChemicalDTO(int ID, int type, String name, double moles) {
		chemicalID = ID;
		chemicalType = type;
		chemicalName = name;
		chemicalMoles = moles;
	}

	/**
	 * Getters methods for ChemicalDTO instance variables
	 */

	public int getChemicalID() {
		return chemicalID;
	}

	public int getChemicalType() {
		return chemicalType;
	}

	public String getChemicalName() {
		return chemicalName;
	}

	public int getChemicalAtomicNumber() {
		return chemicalAtomicNumber;
	}

	public double getChemicalAtomicMass() {
		return chemicalAtomicMass;
	}

	public int getChemicalDissolvedBy() {
		return chemicalDissolvedBy;
	}

	public int getChemicalSoluteA() {
		return chemicalSoluteA;
	}

	public int getChemicalSoluteB() {
		return chemicalSoluteB;
	}

	public double getChemicalMoles() {
		return chemicalMoles;
	}

	public double getChemicalMolesToDissolveMetal() {
		return chemicalMolesToDissolveMetal;
	}

}