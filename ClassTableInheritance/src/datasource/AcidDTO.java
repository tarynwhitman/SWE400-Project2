package datasource;

/**
 * Data Transfer Object for the Acid table in Class Table Inheritance
 * 
 * @author Madeline & Adam
 *
 */
public class AcidDTO {

	private int acidID;
	private int acidSolute;

	/**
	 * Constructor for the Acid Data Transfer Object
	 * 
	 * @param ID     the ID of the acid
	 * @param solute the ID of the acid's solute
	 */
	public AcidDTO(int ID, int solute) {
		acidID = ID;
		acidSolute = solute;
	}

	/**
	 * Getters for AcidDTO in Class Table
	 */

	public int getAcidID() {
		return acidID;
	}

	public int getAcidSolute() {
		return acidSolute;
	}

}