package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import datasource.BaseRDG;
import datasource.DatabaseException;
import datasource.DatabaseManager;

/**
 * @author Ace and Josh B
 * Test class for the BaseRDG Class 
 */
public class TestBaseRDG {
	
	/**
	 * @before method connecting the the DBconnection to the correct database
	 */
	@Before
	public void runRunnable() {
		DatabaseManager.setPatternNumber(3);
	}
	
	/**
	 * Tests the getter and setter methods in the BaseRDG class
	 */
	@Test
	public static void testBaseRDG() {
		BaseRDG rdg = new BaseRDG(1, 2);
		
		assertEquals(1, rdg.getID());
		assertEquals(2, rdg.getSolute());
		
		rdg.setID(3);
		rdg.setSolute(4);
		
		assertEquals(3, rdg.getID());
		assertEquals(4, rdg.getSolute());
	}
	
	/**
	 * Tests the find by ID method in the BaseRDG class
	 */
	@Test
	public static void testFindByID() {
		BaseRDG rdg = BaseRDG.findByID(12);
		assertEquals(12, rdg.getID());
	}
	
	/**
	 * Tests the update method in the BaseRDG Class
	 */
	@Test
	public static void testUpdate() {
		try {
			DatabaseManager.getSingleton().setTesting();
			
			BaseRDG rdg = new BaseRDG(12, 5);
			rdg.setSolute(7);
			rdg.update();
			
			BaseRDG rdg2 = BaseRDG.findByID(12);
			assertEquals(7, rdg2.getSolute());
			
			DatabaseManager.getSingleton().rollBack();			
		} catch (Exception e) {
			DatabaseException.detectError(e, "Exception in TestBaseRDG.testUpdate");
		}
	}

	/**
	 * Runs all of the tests in the class
	 */
	@Test
	public static void runAllTheTests() {
		testBaseRDG();
		testFindByID();
		testUpdate();
	}
}