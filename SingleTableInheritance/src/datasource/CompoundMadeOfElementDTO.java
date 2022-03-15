package datasource;

/**
 * 
 * @author Dan Holmgren
 *
 */
public class CompoundMadeOfElementDTO {
	private int compoundID,
				elementID,
				elementQuantity;
	/**
	 * Constructor for CompoundMadeOfElement
	 * @param cID The compound ID
	 * @param eID The element ID
	 */
	public CompoundMadeOfElementDTO(int cID, int eID, int eQ) {
		this.compoundID = cID;
		this.elementID = eID;
		this.elementQuantity = eQ;
	}
	
	/**
	 * Getter for compoundID
	 * @return The compoundID
	 */
	public int getCompoundID() {
		return compoundID;
	}
	
	/**
	 * Getter for elementID
	 * @return The elementID
	 */
	public int getElementID() {
		return elementID;
	}

	/**
	 * Getter for elementQuantity
	 * @return The elementQuantity
	 */
	public int getElementQuantity() {
		return elementQuantity;
	}
}
