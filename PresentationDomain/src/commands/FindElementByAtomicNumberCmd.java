package commands;

import domainObjects.ElementDomainObject;
import mappers.ElementMapper;

/**
 * Command to find an element by its atomic number
 * 
 * @authors mad&ad
 *
 */
public class FindElementByAtomicNumberCmd implements CommandInterface {

	private int atomicNumber;
	private ElementDomainObject edo;

	/**
	 * Constructor
	 * 
	 * @param atomicNum the atomic number of the element we need
	 */
	public FindElementByAtomicNumberCmd(int atomicNum) {
		atomicNumber = atomicNum;
	}

	/**
	 * Execute method to invoke the find element by atomic number command
	 */
	@Override
	public void execute() throws Exception {
		ElementMapper em = new ElementMapper();
		edo = em.findByAtomicNumber(atomicNumber);
	}

	/**
	 * @return the element domain object
	 */
	public ElementDomainObject getEdo() {
		return edo;
	}

}