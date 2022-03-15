package domainObjects;

import mappers.AcidMapper;

/**
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class AcidDomainObject {

	private int acidID;
	private String acidName;
	private int acidSolute;
	private double acidMoles;
	private AcidMapper dataMapper;
	
	/**
	 * Constructor for AcidDomainObject
	 * @param bm the dataMapper that made this Acid domain object
	 */
	public AcidDomainObject(AcidMapper bm) {
		dataMapper = bm;
		setAcidID(dataMapper.getIdent());
		setAcidName(dataMapper.getName());
		setAcidSolute(dataMapper.getSolute());
		setAcidMoles(dataMapper.getMoles());
	}
	
	/**
	 * Getter for acidID
	 * @return acidID
	 */
	public int getAcidID() {
		return acidID;
	}
	
	/**
	 * Setter for acidID
	 * @param acidID
	 */
	public void setAcidID(int acidID) {
		this.acidID = acidID;
	}
	
	/**
	 * Getter for acidName
	 * @return acidName
	 */
	public String getAcidName() {
		return acidName;
	}
	
	/**
	 * Setter for acidName
	 * @param acidName
	 */
	public void setAcidName(String acidName) {
		this.acidName = acidName;
	}
	
	/**
	 * Getter for acidSolute
	 * @return acidSolute
	 */
	public int getAcidSolute() {
		return acidSolute;
	}
	
	/**
	 * Setter for acidSolute
	 * @param acidSolute
	 */
	public void setAcidSolute(int acidSolute) {
		this.acidSolute = acidSolute;
	}
	
	/**
	 * Getter for acidMoles
	 * @return acidMoles
	 */
	public double getAcidMoles() {
		return acidMoles;
	}
	
	/**
	 * Setter for acidMoles
	 * @param acidMoles
	 */
	public void setAcidMoles(double acidMoles) {
		this.acidMoles = acidMoles;
	}
	
	/**
	 * Return the data mapper that created the domain object
	 * @return dataMapper
	 */
	public AcidMapper getDataMapper() {
		return dataMapper;
	}
	
	/**
	 * Call the persist() method in DataMapper to persist the changes made to acid
	 */
	public void persist() {
		dataMapper.persist();
	}
	
	/**
	 * Return a readable string
	 */
	public String toString() {
		return("Acid: " + acidName + ", ID of solute: " + acidSolute + ", "+ acidMoles + " moles in inventory");
	}
}
