package domainObjects;

import mappers.BaseMapper;

/**
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class BaseDomainObject {

	private int baseID;
	private String baseName;
	private int baseSolute;
	private double baseMoles;
	private BaseMapper dataMapper;
	
	/**
	 * Constructor for BaseDomainObject
	 * @param bm the dataMapper that made this base domain object
	 */
	public BaseDomainObject(BaseMapper bm) {
		dataMapper = bm;
		setBaseID(dataMapper.getBaseID());
		setBaseName(dataMapper.getBaseName());
		setBaseSolute(dataMapper.getBaseSolute());
		setBaseMoles(dataMapper.getBaseMoles());
	}
	
	/**
	 * Getter for baseID
	 * @return baseID
	 */
	public int getBaseID() {
		return baseID;
	}
	
	/**
	 * Setter for baseID
	 * @param baseID
	 */
	public void setBaseID(int baseID) {
		this.baseID = baseID;
	}
	
	/**
	 * Getter for baseName
	 * @return baseName
	 */
	public String getBaseName() {
		return baseName;
	}
	
	/**
	 * Setter for baseName
	 * @param baseName
	 */
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
	
	/**
	 * Getter for baseSolute
	 * @return baseSolute
	 */
	public int getBaseSolute() {
		return baseSolute;
	}
	
	/**
	 * Setter for baseSolute
	 * @param baseSolute
	 */
	public void setBaseSolute(int baseSolute) {
		this.baseSolute = baseSolute;
	}
	
	/**
	 * Getter for baseMoles
	 * @return baseMoles
	 */
	public double getBaseMoles() {
		return baseMoles;
	}
	
	/**
	 * Setter for baseMoles
	 * @param baseMoles
	 */
	public void setBaseMoles(double baseMoles) {
		this.baseMoles = baseMoles;
	}
	
	/**
	 * Return the data mapper that created the domain object
	 * @return dataMapper
	 */
	public BaseMapper getDataMapper() {
		return dataMapper;
	}
	
	/**
	 * Call the persist() method in DataMapper to persist the changes made to base
	 */
	public void persist() {
		dataMapper.persist();
	}
	
	/**
	 * Return a readable string
	 */
	public String toString() {
		return("Base: " + baseName + ", ID of solute: " + baseSolute + ", "+ baseMoles + " moles in inventory");
	}
	
}
