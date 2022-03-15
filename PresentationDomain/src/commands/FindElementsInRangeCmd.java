package commands;

import java.util.ArrayList;

import domainObjects.ElementDomainObject;
import mappers.ElementMapper;

/**
 * Command to find elements in a range of atomic masses
 * 
 * @author Mad&Ad
 *
 */
public class FindElementsInRangeCmd implements CommandInterface {

	private double highRange;
	private double lowRange;
	private ArrayList<ElementDomainObject> edo;

	/**
	 * Constructor
	 * 
	 * @param highRange the upper atomic mass
	 * @param lowRange  the lower atomic mass
	 */
	public FindElementsInRangeCmd(double highRange, double lowRange) {
		this.highRange = highRange;
		this.lowRange = lowRange;
	}

	/**
	 * Execute method to invoke the find elements in range command
	 */
	@Override
	public void execute() throws Exception {
		ElementMapper em = new ElementMapper();
		edo = em.findElementsInRange(highRange, lowRange);
	}

	/**
	 * @return the list of elements in range
	 */
	public ArrayList<ElementDomainObject> getElementArrayList() {
		return edo;
	}

}
