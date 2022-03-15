package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import datasource.CompoundDTO;

/**
 * 
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class TestCompoundDTO {

	/**
	 * Tests that the constructor works properly and that the getters work
	 */
	@Test
	public static void testConstructor() {
		CompoundDTO chem = new CompoundDTO(7, "Carodine", 5.8);
		assertEquals(chem.getCompoundID(), 7);
		assertEquals(chem.getCompoundName(), "Carodine");
		assertEquals(chem.getCompoundMoles(), 5.8, 0.01);

	}

	/**
	 * runner used by entire Concrete project to run all tests at once
	 */
	public static void runAllTheTests() {
		testConstructor();
	}

}
