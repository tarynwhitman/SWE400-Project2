package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import datasource.ElementDTO;
import datasource.ElementTDG;

/**
 * 
 * @author Daniel Holmgren
 * @author Joshua Kellogg
 * 
 *         Test class for ElementTDG
 */
public class TestElementTDG {

	/**
	 * Asserts that there is only ever one ElementTDG at a given time
	 */
	@Test
	public static void testSingleton() {
		ElementTDG c = ElementTDG.getInstance();
		ElementTDG d = ElementTDG.getInstance();
		assertNotEquals(c, null);
		assertNotEquals(d, null);
		assertEquals(c, d);
	}

	/**
	 * Tests the getElementsInRange method
	 */
	@Test
	public static void testGetElementsInRange() {
		ElementTDG c = ElementTDG.getInstance();
		ArrayList<ElementDTO> result = new ArrayList<ElementDTO>();
		result = c.getElementsInRange(19.0, 21.0);
		// Test to make sure it gets the correct number of dtos
		assertEquals(2, result.size());
		// Test to make sure all dto atomic masses are in range
		for (int i = 0; i < result.size(); i++) {
			if ((result.get(i).getAtomicMass() < 1) || result.get(i).getAtomicMass() > 100) {
				fail("Atomic mass not in range");
			}
		}
		System.out.println("All atomic masses in range");
	}

	/**
	 * Test that you can return all the compounds that a given element is in
	 */
	@Test
	public static void testGetElementsInCompound() {
		ElementTDG e = ElementTDG.getInstance();
		ArrayList<ElementDTO> list = e.getElementsInCompound(2);
		assertEquals(list.size(), 2); // amount of tuples
		assertEquals(list.get(0).getID(), 4);
		assertEquals(list.get(1).getID(), 8);
	}

	/**
	 * In order to be efficient runs all tests at once
	 */
	public static void runAllTheTests() {
		testSingleton();
		testGetElementsInRange();
		testGetElementsInCompound();
	}

}
