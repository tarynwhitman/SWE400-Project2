package commands;

import domainObjects.ElementDomainObject;
import mappers.ElementMapper;

/**
 * Command to find an element by its atomic mass
 * 
 * @authors mad&ad
 *
 */
public class FindElementByAtomicMassCmd implements CommandInterface {

	private double atomicMass;
	private ElementDomainObject edo;

	/**
	 * Constructorrr
	 * 
	 * @param a_mass the atomic mass of the element we need
	 */
	public FindElementByAtomicMassCmd(double a_mass) {
		atomicMass = a_mass;
	}

	/**
	 * Execute method to invoke the find element by atomic mass command
	 */
	@Override
	public void execute() throws Exception {
		ElementMapper em = new ElementMapper();
		edo = em.findByAtomicMass(atomicMass);
	}

	/**
	 * @return the element domain object
	 */
	public ElementDomainObject getEdo() {
		return edo;
	}

}