package Tests;

import static org.junit.Assert.*;

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
	 * Test that the constructor and getters work properly
	 */
	@Test
	public static void testConstructorAndGetters() {
		CompoundMadeOfElement comp = new CompoundMadeOfElement(1,2);
		assertEquals(comp.getCompoundID(), 1);
		assertEquals(comp.getElementID(), 2);
	}

	
	/**
	 * runner used by entire Class project to run all tests at once
	 */
	@Test
	public static void runAllTheTests() {
		testConstructorAndGetters();
	}
}
