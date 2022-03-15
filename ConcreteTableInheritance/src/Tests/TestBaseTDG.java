package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import datasource.BaseDTO;
import datasource.BaseTDG;
import datasource.DatabaseManager;

/**
 * @author Josh B and Ace Test class for the Table Data Gateway
 */
public class TestBaseTDG {

	/**
	 * Sets up the DC connections class to run using the correct Database
	 */
	@Before
	public void runRunnable() {
		DatabaseManager.setPatternNumber(3);
	}

	/**
	 * Tests getting all of the rows from the Base table
	 */
	@Test
	public static void testGetAllBases() {
		ArrayList<BaseDTO> list = new ArrayList<BaseDTO>();

		list = BaseTDG.getAllBases();
		assertEquals(list.get(0).getID(), 9);
		assertEquals(list.get(1).getID(), 10);
		assertEquals(list.get(2).getID(), 11);
		assertEquals(list.get(3).getID(), 12);
	}

	public static void runAllTheTests() {
		testGetAllBases();
	}

}