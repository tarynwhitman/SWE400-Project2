package datasource;

import Tests.TestEVERYTHING;

/**
 * Runner for the Single Table Inheritance project.
 * 
 * @author Joshua B
 * @author Madeline C
 */
public class Runnable {

	/**
	 * main method that sets which database to use and connects to it. Also drops
	 * all tables and recreates them with test rows. Runs all JUnit tests.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			DatabaseManager.setPatternNumber(1);
			DatabaseManager.getSingleton().openConnection();

			SingleTableCreator.dropAllTables();
			SingleTableCreator.createTables();
			SingleTableCreator.addTestRows();

			TestEVERYTHING.testRunAllTheTests();

			System.out.println("ya done diddly did it");

			DatabaseManager.getSingleton().closeConnection();

		} catch (Exception e) {
			DatabaseException.detectError(e, "Runnable - Single");
		}
	}
}