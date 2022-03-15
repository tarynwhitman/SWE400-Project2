/**
 * 
 */
package commands;

import java.util.ArrayList;

import domainObjects.ElementDomainObject;
import mappers.ElementMapper;

/**
 * Command to get all the elements
 * 
 * @author Mad&Ad
 *
 */
public class GetAllElementsCmd implements CommandInterface {

	private ArrayList<ElementDomainObject> elements;

	/**
	 * Execute the get all elements command :)))
	 */
	@Override
	public void execute() throws Exception {
		ElementMapper em = new ElementMapper();
		elements = em.getAllElements();
	}

	/**
	 * @return the elements
	 */
	public ArrayList<ElementDomainObject> getElements() {
		return elements;
	}

}