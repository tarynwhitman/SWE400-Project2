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
	private int elementQuantity;

	/**
	 * Constructor for CompoundMadeOfElement
	 * 
	 * @param compoundID      ID of the compound
	 * @param elementID       ID of the element
	 * @param elementQuantity the number of atoms of the element in one molecule of
	 *                        the compound
	 */
	public CompoundMadeOfElement(int compoundID, int elementID, int elementQuantity) {
		this.compoundID = compoundID;
		this.elementID = elementID;
		this.elementQuantity = elementQuantity;
	}

	/**
	 * Getter for elementID
	 * 
	 * @return elementID
	 */
	public int getElementID() {
		return elementID;
	}

	/**
	 * Getter for compoundID
	 * 
	 * @return compoundID
	 */
	public int getCompoundID() {
		return compoundID;
	}

	/**
	 * Getter for elementQuantity
	 * 
	 * @return elementQuantity
	 */
	public int getElementQuantity() {
		return elementQuantity;
	}
}