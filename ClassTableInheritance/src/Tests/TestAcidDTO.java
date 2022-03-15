package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import datasource.AcidDTO;

/**
 * Test class for the AcidDTO class in Class Table Inheritance
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
		AcidDTO dto = new AcidDTO(1, 4);
		assertEquals(1, dto.getAcidID());
		assertEquals(4, dto.getAcidSolute());
	}

	/**
	 * Run the tests in this class
	 */
	@Test
	public static void runAllTheTests() {
		test();
	}
}