package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import datasource.ElementDTO;

/**
 * @author Daniel Holmgren
 * @author Joshua
 * Test class for elementDTO
 */
public class TestElementDTO {

	/**
	 * Assigns all the variables with the constructor
	 * and gets them and makes sure there equal
	 */
	@Test
	public static void testConstructor() {
		ElementDTO e = new ElementDTO(1, 2, 3.0);
		assertEquals(e.getID(), 1);
		assertEquals(e.getAtomicNumber(), 2);
		assertEquals(e.getAtomicMass(), 3.0, 0.01);
	}

	/**
	 * Runs it all, because efficiency
	 */
	public static void runAllTheTests() {
		testConstructor();
	}

}
