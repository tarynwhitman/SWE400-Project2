package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import datasource.MetalDTO;

/**
 * @author Daniel Holmgren
 * @author Joshua Kellogg A data transfer object for metal test
 *
 */
public class TestMetalDTO {

	/**
	 * Makes sure the constructor constructs
	 */
	@Test
	public static void testConstructor() {
		MetalDTO m = new MetalDTO(1, 2, 4.5);
		assertEquals(m.getID(), 1);
		assertEquals(m.getDissolvedBy(), 2);
		assertEquals(m.getMolesOfAcidToDissolve(), 4.5, 0.01);
	}

	/**
	 * Runs all the tests at the same time for efficiency's sake
	 */
	public static void runAllTheTests() {
		testConstructor();
	}

}
