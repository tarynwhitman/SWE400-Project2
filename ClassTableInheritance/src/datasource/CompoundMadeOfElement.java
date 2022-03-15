package datasource;

/**
 * 
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class CompoundMadeOfElement {

	private int compoundID;
	private int elementID;
	
	/**
	 * Constructor
	 * @param compID ID of compound
	 * @param elemID ID of element
	 */
	public CompoundMadeOfElement(int compID, int elemID) {
		this.compoundID = compID;
		this.elementID = elemID;
	}

	/**
	 * Getter for compoundID
	 * @return compoundID
	 */
	public int getCompoundID() {
		return compoundID;
	}

	/**
	 * Getter for elementID
	 * @return elementID
	 */
	public int getElementID() {
		return elementID;
	}

	
	
}
