package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import datasource.AcidDTO;

/**
 * Test class for the AcidDTO class
 * 
 * @author Adam & Madeline
 *
 */
public class TestAcidDTO {

	/**
	 * JUnit to test the AcidDTO constructor and getters
	 */
	@Test
	public static void test() {
		AcidDTO dto = new AcidDTO(1, "Hydrochloric Acid", 5, 5.9);
		assertEquals(1, dto.getAcidID());
		assertEquals("Hydrochloric Acid", dto.getAcidName());
		assertEquals(5, dto.getAcidSolute());
		assertEquals(5.9, dto.getAcidMoles(), 0.01);
	}

	/**
	 * Runs all the tests in this class
	 */
	public static void runAllTheTests() {
		test();
	}

}