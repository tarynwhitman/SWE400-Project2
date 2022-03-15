package datasource;

import java.sql.Statement;

/**
 * Table Creator for Class Table Inheritance
 * @author Joshua & Madeline
 *
 */
public class ClassTableCreator {
	
	static Statement stmt;
    static String insertData; 
    
    /**
     * Creates tables in the database.
     */
	public static void createTables() {
		
		try {
			stmt = DatabaseManager.getSingleton().getConnection().createStatement();

		    String[] table_statements =
	            {"CREATE TABLE IF NOT EXISTS Chemical (chemicalID int NOT NULL, chemicalName VARCHAR(32) NOT NULL, chemicalMoles double, PRIMARY KEY (chemicalID))",
	             "CREATE TABLE IF NOT EXISTS Element (elementID int NOT NULL, elementAtomicNumber int NOT NULL, elementAtomicMass double NOT NULL, PRIMARY KEY (elementID))",
	             "CREATE TABLE IF NOT EXISTS Compound (compoundID int NOT NULL, PRIMARY KEY (compoundID))",
	             "CREATE TABLE IF NOT EXISTS CompoundMadeOfElement (compoundID int, elementID int, elementQuantity int)",
	             "CREATE TABLE IF NOT EXISTS Base (baseID int NOT NULL, baseSolute int NOT NULL, PRIMARY KEY (baseID))",
	             "CREATE TABLE IF NOT EXISTS Acid (acidID int NOT NULL, acidSolute int NOT NULL, PRIMARY KEY (acidID))",
	             "CREATE TABLE IF NOT EXISTS Metal (metalID int NOT NULL, metalDissolvedBy int NOT NULL, molesOfAcidToDissolve double, PRIMARY KEY (metalID))",
	            };

		    for (int i = 0; i < table_statements.length; i++) {
		      insertData = new String(table_statements[i]);
		      stmt.executeUpdate(insertData);
		      System.out.println("created table " + i);
		    }
	
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
	}

	/**
	 * drops all of the specified tables in the database.
	 */
	public static void dropAllTables() {
		
		try {
		    Statement stmt;
		    String insertData;

		    stmt = DatabaseManager.getSingleton().getConnection().createStatement();

		    String[] table_statements =
		            {"DROP TABLE IF EXISTS Chemical",
		             "DROP TABLE IF EXISTS Element",
		             "DROP TABLE IF EXISTS Compound",
		             "DROP TABLE IF EXISTS CompoundMadeOfElement",
		             "DROP TABLE IF EXISTS Base",
		             "DROP TABLE IF EXISTS Acid",
		             "DROP TABLE IF EXISTS Metal",
		            };

		    for (int i = table_statements.length - 1; i >= 0; i--) {
		      insertData = new String(table_statements[i]);
		      stmt.executeUpdate(insertData);
		    }
	
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}	    
	}
	
	/**
	 * Adds some rows to all tables in the database.
	 */
	public static void addTestRows() {
		
		try {
			stmt = DatabaseManager.getSingleton().getConnection().createStatement();

			String[] table_statements = { "INSERT INTO Acid VALUES (1, 4)",
					"INSERT INTO Acid VALUES (2, 5)", 
					"INSERT INTO Acid VALUES (3, 5)",
					"INSERT INTO Element VALUES (4, 12, 50.01)",
					"INSERT INTO Element VALUES (5, 10, 20.0)",
					"INSERT INTO Element VALUES (6, 17, 20.2)",
					"INSERT INTO Element VALUES (7, 30, 40.0)",
					"INSERT INTO Element VALUES (8, 44, 100.1)",
					"INSERT INTO Metal VALUES (6, 2, 4.8)",
					"INSERT INTO Metal VALUES (7, 2, 8.4)",
					"INSERT INTO Metal VALUES (8, 1, 12.8)",
					"INSERT INTO Base VALUES (12, 5)",
					"INSERT INTO Base VALUES (13, 6)",	
					"INSERT INTO Compound VALUES (14)",
					"INSERT INTO Compound VALUES (15)",
					"INSERT INTO Chemical VALUES (1, \"acid1\", 1.3)",
					"INSERT INTO Chemical VALUES (2, \"acid2\", 1.6)",
					"INSERT INTO Chemical VALUES (3, \"acid3\", 94.3)",
					"INSERT INTO Chemical VALUES (4, \"element1\", 18.5)",
					"INSERT INTO Chemical VALUES (5, \"element2\", 19.2)",
					"INSERT INTO Chemical VALUES (6, \"metal1\", 12.5)",
					"INSERT INTO Chemical VALUES (7, \"metal2\", 92.5)",
					"INSERT INTO Chemical VALUES (8, \"metal3\", 83.5)",
					"INSERT INTO Chemical VALUES (12, \"base1\", 839.4)",
					"INSERT INTO Chemical VALUES (13, \"base2\", 2.7)",
					"INSERT INTO Chemical VALUES (14, \"compound1\", 9.3)",
					"INSERT INTO Chemical VALUES (15, \"compound2\", 8.4)",
					"INSERT INTO CompoundMadeOfElement VALUES (14, 4, 2)",
					"INSERT INTO CompoundMadeOfElement VALUES (14, 5, 4)",
					"INSERT INTO CompoundMadeOfElement VALUES (15, 4, 2)",
					"INSERT INTO CompoundMadeOfElement VALUES (15, 8, 4)"};

			for (int i = 0; i < table_statements.length; i++) {
				insertData = new String(table_statements[i]);
				stmt.executeUpdate(insertData);
			}
	
		} catch (Exception e) {
			DatabaseException.detectError(e, "ClassTableCreator.addTestRows");
		}	
	}
}