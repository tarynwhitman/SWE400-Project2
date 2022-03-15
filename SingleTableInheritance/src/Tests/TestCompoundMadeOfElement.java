package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import datasource.CompoundMadeOfElementDTO;

/**
 * Test class for CompoundMadeOfElement table
 * 
 * @author Madeline & co.
 *
 */
public class TestCompoundMadeOfElement {

	/**
	 * Test that the getters all work
	 */
	@Test
	public static void testGetters() {
		CompoundMadeOfElementDTO idk = new CompoundMadeOfElementDTO(1, 3, 20);
		assertEquals(1, idk.getCompoundID());
		assertEquals(3, idk.getElementID());
		assertEquals(20, idk.getElementQuantity());
	}

	/**
	 * Allows for our tests to be run in TestEverything()
	 */
	@Test
	public static void runAllTheTests() {
		testGetters();
	}

}