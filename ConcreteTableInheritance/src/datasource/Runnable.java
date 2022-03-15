package datasource;

import Tests.TestEVERYTHING;

/**
 * Concrete Table Inheritance Runner
 * 
 * @author Joshua B
 *
 */
public class Runnable {

	public static void main(String[] args) {
		try {
			DatabaseManager.setPatternNumber(2);
			DatabaseManager.getSingleton().openConnection();
			ConcreteTableCreator.dropAllTables();
			ConcreteTableCreator.createTables();
			ConcreteTableCreator.addTestRows();

			TestEVERYTHING.testRunAllTheTests();

			System.out.println("yee yee brethren");

			DatabaseManager.getSingleton().closeConnection();
		} catch (Exception e) {
			DatabaseException.detectError(e, "Runnable - Concrete");
		}
	}

}