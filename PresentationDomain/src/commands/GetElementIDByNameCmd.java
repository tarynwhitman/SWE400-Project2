package commands;

import domainObjects.ElementDomainObject;
import mappers.ElementMapper;

/**
 * Command to get an element's ID using its name
 * 
 * @authors Adam & Mad
 *
 */
public class GetElementIDByNameCmd implements CommandInterface {

	private String elementName;
	private int elementID;

	/**
	 * Constructor
	 * 
	 * @param elementName the name of the element
	 */
	public GetElementIDByNameCmd(String elementName) {
		this.elementName = elementName;
	}

	/**
	 * Execute method to invoke get element ID by name command :)
	 */
	@Override
	public void execute() throws Exception {
		ElementMapper em = new ElementMapper();
		ElementDomainObject edo = em.findByName(elementName);
		elementID = edo.getElementID();
	}

	/**
	 * Getter for ID
	 * 
	 * @return the element ID
	 */
	public int getElementID() {
		return elementID;
	}

}
