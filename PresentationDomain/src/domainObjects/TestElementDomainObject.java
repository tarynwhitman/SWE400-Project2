package domainObjects;

import static org.junit.Assert.*;
import org.junit.Test;

import mappers.ElementMapper;

/**
 * @author Marlee Lackey
 * @author Taryn Whitman
 */
public class TestElementDomainObject {

	/**
	 * Test that the constructor and setters work properly
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testConstructorAndSetters() throws Exception {
		// int ID, String elementName, int atomicNumber, double atomicMass, double moles
		ElementMapper d = new ElementMapper();
		ElementDomainObject e = d.createElement(23, "Carodine", 2, 234.22, 28.2);
		assertEquals(e.getDataMapper(), d);
		assertEquals(d.getElement(), e);
		assertEquals(e.getElementAtomicMass(), 234.22, 0.01);
		assertEquals(e.getElementAtomicNumber(), 2);
		assertEquals(e.getElementID(), 23);
		assertEquals(e.getElementName(), "Carodine");

		// test border cases
		e.setElementAtomicNumber(1111);
		assertNotEquals(1111, e.getElementAtomicNumber()); // atomic number cant be more than atomic mass
		e.setElementName("Carodine Rocks");
		assertNotEquals("Carodine Rocks", e.getElementName()); // name can't be two words
		
	}
	


}
