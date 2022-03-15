package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import datasource.ElementDTO;
import datasource.ElementTDG;

/**
 * @author Daniel Holmgren
 * @author Joshua Kellogg
 * Test class for the ElementTDG
 *
 */
public class TestElementTDG {

	/**
	 * Runs constructor and makes sure it is not mull
	 */
	@Test
	public static void testConstructor() {
		ElementTDG e = ElementTDG.getInstance();
		assertNotEquals(e, null);
	}
	
	/**
	 * Runs getElementsInRange and asserts the amount of results 
	 * and that the correct information is in each of those things
	 */
	@Test
	public static void testGetElementsInRange() {
		ElementTDG c = ElementTDG.getInstance();
		ArrayList<ElementDTO> result = new ArrayList<ElementDTO>();
		result = c.getElementInRange(0, 100);
		//Test to make sure it gets the correct number of dtos
		assertEquals(4, result.size());
		//Test to make sure all dto atomic masses are in range
		for(int i = 0; i < result.size(); i++) {
			if((result.get(i).getAtomicMass() < 1) || result.get(i).getAtomicMass() > 100) {
				fail("Atomic mass not in range");
			}
		}
	}
	
	/**
	 * Test that you can return all the compounds that a given element is in
	 */
	@Test
	public static void testGetElementsInCompound() {
		ElementTDG e = ElementTDG.getInstance();
		ArrayList<ElementDTO> list = e.getElementsInCompound(14);
		assertEquals(list.size(), 2); // amount of tuples
		assertEquals(list.get(0).getID(), 4); 
		assertEquals(list.get(1).getID(), 5); 


	}
	

	/**
	 * runs all tests in this class for efficient
	 */
	public static void runAllTheTests() {
		testConstructor();
		testGetElementsInRange();
		testGetElementsInCompound();
	}

}
