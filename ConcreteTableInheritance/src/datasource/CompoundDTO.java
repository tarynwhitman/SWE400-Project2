package datasource;

/**
 * 
 * @author Marlee Lackey
 * @author Taryn Whitman
 */
public class CompoundDTO {
	private int compoundID;
	private String compoundName;
	private double compoundMoles;

	/**
	 * Constructor for CompoundDTO
	 * 
	 * @param id    Pass in the ID for the compound
	 * @param name  Pass in the name for the compound
	 * @param moles Pass in the number of moles we have of the compound
	 */
	public CompoundDTO(int id, String name, double moles) {
		compoundID = id;
		compoundName = name;
		compoundMoles = moles;
	}

	/**
	 * Getter for compoundID
	 * 
	 * @return compoundID ID of the compound
	 */
	public int getCompoundID() {
		return compoundID;
	}

	/**
	 * Getter for compoundName
	 * 
	 * @return compoundName name of the compound
	 */
	public String getCompoundName() {
		return compoundName;
	}

	/**
	 * Getter for compoundMoles
	 * 
	 * @return
	 */
	public double getCompoundMoles() {
		return compoundMoles;
	}

}