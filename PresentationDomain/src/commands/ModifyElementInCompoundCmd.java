package commands;

import domainObjects.CompoundDomainObject;
import mappers.CompoundMapper;

/**
 * Command class to modify how many atoms of an element are in one molecule of a
 * compound
 * 
 * @author Mad&Ad
 *
 */
public class ModifyElementInCompoundCmd {

	private int elementID; // the element ID
	private int compoundID; // the compound ID
	private int newElementQuantity; // the new number of atoms of element that are in the compound

	/**
	 * Constructor
	 * 
	 * @param element  the ID of the element
	 * @param compound the ID of the compound
	 * @param quantity the new quantity of the element
	 */
	public ModifyElementInCompoundCmd(int element, int compound, int quantity) {
		elementID = element;
		compoundID = compound;
		newElementQuantity = quantity;
	}

	/**
	 * Execute method to invoke the modify element in compound command
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception {
		CompoundMapper cm = new CompoundMapper();
		CompoundDomainObject cdo = cm.findByID(compoundID);
		cdo.modifyElementQuantity(elementID, newElementQuantity);
		cdo.persist();
	}

	/**
	 * @return the ID of the element
	 */
	public int getElementID() {
		return elementID;
	}

	/**
	 * @return the ID of the compound
	 */
	public int getCompoundID() {
		return compoundID;
	}

	/**
	 * @return the new quantity of the element
	 */
	public int getNewElementQuantity() {
		return newElementQuantity;
	}

}