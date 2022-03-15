package datasource;
/**
 * 
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class CompoundDTO {
	private int compoundID;

	/** 
	 * Constructor for CompountDTO
	 * @param id of compound
	 */
	public CompoundDTO(int id) {
		compoundID = id;
	}
	
	/**
	 * Getter for compoundID
	 * @return compoundID
	 */
	public int getCompoundID() {
		return compoundID;
	}
}
