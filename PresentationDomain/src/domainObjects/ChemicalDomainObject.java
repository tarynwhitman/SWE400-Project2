package domainObjects;

import datasource.DatabaseException;
import mappers.ChemicalMapper;

/**
 * 
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class ChemicalDomainObject {
	private ChemicalMapper dataMapper;
	private int chemicalID;
	private String chemicalName;
	private double chemicalMoles;

	/**
	 * Constructor for ChemicalDomainObject
	 * @param dm the dataMapper that made this chemical domain object
	 */
	public ChemicalDomainObject(ChemicalMapper dm) {
		dataMapper = dm;
		setChemicalID(dataMapper.getID());
		setChemicalName(dataMapper.getName());
		setChemicalMoles(dataMapper.getMoles());
		dm.setCdo(this);
	}

	/**
	 * Setter for chemicalID
	 * 
	 * @param id
	 */
	public void setChemicalID(int id) {
		chemicalID = id;
	}

	/**
	 * Getter for chemicalID
	 * 
	 * @return chemicalID
	 */
	public int getChemicalID() {
		return chemicalID;
	}

	/**
	 * @return the chemicalName
	 */
	public String getChemicalName() {
		return chemicalName;
	}

	/**
	 * @param chemicalName the chemicalName to set
	 */
	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}

	/**
	 * Setter for chemcialMoles
	 * 
	 * @param mole
	 */
	public void setChemicalMoles(double mole) {
		chemicalMoles = mole;
	}

	/**
	 * Getter for chemicalMoles
	 * 
	 * @return chemicalMoles
	 */
	public double getChemicalMoles() {
		return chemicalMoles;
	}

	/**
	 * Return the data mapper that created the domain object
	 * 
	 * @return dataMapper
	 */
	public ChemicalMapper getDataMapper() {
		return dataMapper;
	}

	/**
	 * Call the persist() method in DataMapper to persist the changes made to
	 * chemical
	 * @throws DatabaseException 
	 */
	public void persist() throws DatabaseException {
		dataMapper.persist();
	}
	
	/**
	 * Return a readable string
	 */
	public String toString() {
		return("Chemical: " + chemicalName + ", "+ chemicalMoles + " moles in inventory");
	}
	
}