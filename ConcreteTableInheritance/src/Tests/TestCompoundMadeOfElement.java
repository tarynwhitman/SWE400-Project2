package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import datasource.CompoundMadeOfElement;

/**
 * 
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class TestCompoundMadeOfElement {

	/**
	 * Test that the the constructor is made properly and the getters work
	 */
	@Test
	public static void testConstructorAndGetters() {

		CompoundMadeOfElement comp = new CompoundMadeOfElement(1, 2, 5);
		assertEquals(comp.getCompoundID(), 1);
		assertEquals(comp.getElementID(), 2);
		assertEquals(comp.getElementQuantity(), 5);
	}

	/**
	 * runner used by entire Concrete project to run all tests at once
	 */
	public static void runAllTheTests() {
		testConstructorAndGetters();
	}
}
