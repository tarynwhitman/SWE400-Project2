package commands;

import domainObjects.ElementDomainObject;
import mappers.ElementMapper;

/**
 * Command class to add an existing element to an existing compound
 * 
 * @author Madeline and Adam
 *
 */
public class AddElementToCompoundCmd implements CommandInterface {

	private int elementID;
	private int elementQuantity;
	private int compoundID;

	/**
	 * 
	 * Constructor
	 * 
	 * @param elementID       the ID of the element to be added
	 * @param elementQuantity the quantity of the element
	 * @param compoundID      the ID of the compound to add the element to
	 */
	public AddElementToCompoundCmd(int elementID, int elementQuantity, int compoundID) {
		this.elementID = elementID;
		this.elementQuantity = elementQuantity;
		this.compoundID = compoundID;
	}

	/**
	 * Execute method that invokes the add element to a compound command
	 * 
	 * @throws Exception
	 */
	@Override
	public void execute() throws Exception {
		ElementMapper em = new ElementMapper();
		ElementDomainObject edo = em.findByID(elementID);
		edo.addToCompound(compoundID, elementQuantity);
		edo.persist();
	}

	/**
	 * @return the element ID
	 */
	public int getElementID() {
		return elementID;
	}

	/**
	 * @return the element quantity
	 */
	public int getElementQuantity() {
		return elementQuantity;
	}

	/**
	 * @return the compound ID
	 */
	public int getCompoundID() {
		return compoundID;
	}

}
