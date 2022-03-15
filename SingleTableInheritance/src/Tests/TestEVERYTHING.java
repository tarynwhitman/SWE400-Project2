package Tests;

import org.junit.Test;

import datasource.DatabaseException;

/**
 * Class to run all the tests for Single Table Inheritance
 * 
 * @author Madeline & Adam
 *
 */
public class TestEVERYTHING {

	/**
	 * Runs every test in Single Table Inheritance
	 * @throws DatabaseException 
	 */
	@Test
	public static void testRunAllTheTests() throws DatabaseException {
		TestChemicalDTO.runAllTheTests();
		TestChemicalRDG.runAllTheTests();
		TestChemicalTDG.runAllTheTests();
		TestCompoundMadeOfElement.runAllTheTests();
		TestElementMapper.runAllTheTests();
		TestMetalMapper.runAllTheTests();
	}

}