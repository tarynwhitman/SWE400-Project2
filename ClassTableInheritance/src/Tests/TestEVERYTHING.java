package Tests;

/**
 * Class to run all the tests for Class Table Inheritance
 * 
 * @author Madeline & Adam
 *
 */
public class TestEVERYTHING {

	/**
	 * Runs all the tests in Class Table Inheritance
	 */
	public static void testRunAllTheTests() {
		TestChemicalDTO.runAllTheTests();
		TestChemicalRDG.runAllTheTests();
		TestChemicalTDG.runAllTheTests();

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

	}

}