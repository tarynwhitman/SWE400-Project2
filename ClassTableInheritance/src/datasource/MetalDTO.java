package datasource;

/**
 * @author Joshua Kellogg
 * @author Daniel Holmgren
 * 
 *         The data transfer object for metal
 */
public class MetalDTO {
	private int ID, dissolvedBy;
	private double molesOfAcidToDissolve;

	/**
	 * Constructor, assigns instance variables
	 * 
	 * @param ID
	 * @param dissolvedBy
	 * @param molesOfAcidToDissolve
	 */
	public MetalDTO(int ID, int dissolvedBy, double molesOfAcidToDissolve) {
		this.ID = ID;
		this.dissolvedBy = dissolvedBy;
		this.molesOfAcidToDissolve = molesOfAcidToDissolve;
	}

	// Getters
	public int getID() {
		return ID;
	}

	public int getDissolvedBy() {
		return dissolvedBy;
	}

	public double getMolesOfAcidToDissolve() {
		return molesOfAcidToDissolve;
	}
}
