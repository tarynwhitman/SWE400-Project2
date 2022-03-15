package commands;

import domainObjects.ElementDomainObject;
import mappers.ElementMapper;

/**
 * Command class to modify the atomic number of an element
 * 
 * @author Madeline and Adam
 *
 */
public class ModifyElementAtomicNumberCmd implements CommandInterface {

	private int elementID;
	private int newElementAtomicNumber;

	/**
	 * Constructor
	 * 
	 * @param id     the ID of the element we want to modify
	 * @param newNum the new atomic number of the element
	 */
	public ModifyElementAtomicNumberCmd(int id, int newNum) {
		elementID = id;
		newElementAtomicNumber = newNum;
	}

	/**
	 * Execute method to invoke the modify element atomic number command
	 * 
	 * @throws Exception
	 */
	@Override
	public void execute() throws Exception {
		ElementMapper em = new ElementMapper();
		ElementDomainObject edo = em.findByID(elementID);
		edo.setElementAtomicNumber(newElementAtomicNumber);
		edo.persist();
	}

	/**
	 * @return the elementID
	 */
	public int getElementID() {
		return elementID;
	}

	/**
	 * @return the newElementAtomicNumber
	 */
	public int getNewElementAtomicNumber() {
		return newElementAtomicNumber;
	}

}