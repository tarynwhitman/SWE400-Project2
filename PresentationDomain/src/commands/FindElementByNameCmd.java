package commands;

import domainObjects.ElementDomainObject;
import mappers.ElementMapper;

/**
 * Command to find an element by its name
 * 
 * @author Mad & Addddddddddddddddddddddddddddddddddddddddd
 *
 */
public class FindElementByNameCmd implements CommandInterface {

	private String name;
	private ElementDomainObject edo;

	/**
	 * construct it bruh
	 * 
	 * @param n the nameee
	 */
	public FindElementByNameCmd(String n) {
		name = n;
	}

	/**
	 * do the thing
	 */
	@Override
	public void execute() throws Exception {
		ElementMapper em = new ElementMapper();
		edo = em.findByName(name);
	}

	/**
	 * @return the edo
	 */
	public ElementDomainObject getEdo() {
		return edo;
	}

}