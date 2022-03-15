package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datasource.BaseRDG;
import datasource.DatabaseManager;

/**
 * @author Josh B and Ace Test class for the Row Data gateway
 */
public class TestBaseRDG {

	/**
	 * Before method to set up the DB Connections project to connect to the correct
	 * database
	 */
	@Before
	public void SelectDatabase() {
		DatabaseManager.setPatternNumber(2);
	}

	@Test
	public static void testBaseRDGConstructor() {
		BaseRDG dot = new BaseRDG(23, "Sodium Hydroxide", 83, 6.5);
		assertEquals(23, dot.getID());
		assertEquals("Sodium Hydroxide", dot.getName());
		assertEquals(83, dot.getSolute());
		assertEquals(6.5, dot.getMoles(), 0.01);
	}

	@Test
	public static void testBaseRDG_findByID() {
		BaseRDG dot = BaseRDG.findByID(10);
		assertEquals("base2", dot.getName());
	}

	@Test
	public static void testBaseRDG_findByName() {
		BaseRDG dot = BaseRDG.findByName("base2");
		assertEquals(10, dot.getID());
	}

	/**
	 * JUnit to test that we can get the type from a name (if it exists)
	 * 
	 * @author Madeline and Adam
	 */
	@Test
	public static void testFindTypeByName() {
		String type = BaseRDG.findTypeByName("base2");
		assertEquals("Base", type);

		String typeFakeNews = BaseRDG.findTypeByName("notReallyABase");
		assertNull(typeFakeNews);
	}

	@Test
	public static void testBaseRDG_Update() {
		BaseRDG dot = BaseRDG.findByID(10);
		assertEquals("base2", dot.getName());
		dot.setName("Sodium Hydroxide");
		dot.update();
		BaseRDG doto = BaseRDG.findByID(10);
		assertEquals("Sodium Hydroxide", doto.getName());
	}

	/**
	 * @author Madeline and Adam
	 */
	public static void runAllTheTests() {
		testBaseRDGConstructor();
		testBaseRDG_findByID();
		testBaseRDG_findByName();
		testFindTypeByName();
		testBaseRDG_Update();
	}

}
