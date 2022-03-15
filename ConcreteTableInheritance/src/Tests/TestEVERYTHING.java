package Tests;

import org.junit.Test;

/**
 * Class to run all the tests for Concrete Table Inheritance
 * 
 * @author Madeline & Adam
 *
 */
public class TestEVERYTHING {

	/**
	 * Test that runs all tests in Concrete
	 * @throws Exception 
	 */
	@Test
	public static void testRunAllTheTests() throws Exception {
		TestAcidDTO.runAllTheTests();
		TestAcidRDG.runAllTheTests();
		TestAcidTDG.runAllTheTests();

		TestBaseDTO.runAllTheTests();
		TestBaseRDG.runAllTheTests();
		TestBaseTDG.runAllTheTests();

		TestCompoundDTO.runAllTheTests();
		TestCompoundRDG.runAllTheTests();
		TestCompoundTDG.runAllTheTests();

		TestCompoundMadeOfElement.runAllTheTests();

		TestElementDTO.runAllTheTests();
		TestElementRDG.runAllTheTests();
		TestElementTDG.runAllTheTests();

		TestMetalDTO.runAllTheTests();
		TestMetalRDG.runAllTheTests();
		TestMetalTDG.runAllTheTests();
		
		TestElementMapper.runAllTheTests();
		TestMetalMapper.runAllTheTests();
		TestCompoundMapper.runAllTheTests();
		TestChemicalMapper.runAllTheTests();


	}

}