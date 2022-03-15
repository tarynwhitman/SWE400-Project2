package commands;

import domainObjects.CompoundDomainObject;
import mappers.CompoundMapper;

/**
 * Command class to remove an existing element from an existing compound
 * 
 * @author Madeline and Adam
 *
 */
public class DeleteElementFromCompoundCmd implements CommandInterface {

	private int elementID;
	private int compoundID;

	/**
	 * 
	 * Constructor
	 * 
	 * @param elementID  the ID of the element to remove
	 * @param compoundID the ID of the compound it's in
	 */
	public DeleteElementFromCompoundCmd(int elementID, int compoundID) {
		this.elementID = elementID;
		this.compoundID = compoundID;
	}

	/**
	 * We deleting elements from compounds out here
	 * 
	 * @throws Exception
	 */
	@Override
	public void execute() throws Exception {
		CompoundMapper cm = new CompoundMapper();
		CompoundDomainObject cdo = cm.findByID(compoundID);
		cdo.removeElement(elementID);
		cdo.persist();
	}

	/**
	 * @return the element ID
	 */
	public int getElementID() {
		return elementID;
	}

	/**
	 * @return the compound ID
	 */
	public int getCompoundID() {
		return compoundID;
	}

}
