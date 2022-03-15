package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import datasource.BaseDTO;

/**
 * @author Josh B and Ace Data Transfer Oject test class
 */
public class TestBaseDTO {

	/**
	 * Tests getters for the DTO class
	 */
	@Test
	public static void test() {
		BaseDTO dot = new BaseDTO(23, "Sodium Hydroxide", 83, 0.8);
		assertEquals(23, dot.getID());
		assertEquals("Sodium Hydroxide", dot.getName());
		assertEquals(83, dot.getSolute());
		assertEquals(0.8, dot.getMoles(), 0.01);
	}

	public static void runAllTheTests() {
		test();
	}

}