package Tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import datasource.BaseDTO;
import datasource.BaseTDG;
import datasource.DatabaseManager;

/**
 * @author Ace and Josh B
 * Test Class for the BaseTDG class
 */
public class TestBaseTDG {
	
	/**
	 * @before every test so that the correct Database is selected 
	 */
	@Before
	public void runRunnable() {
		DatabaseManager.setPatternNumber(2);
	}
	
	/**
	 * Tests getting all of the stored bases from the Base Table  
	 */
	@Test
	public static void testGetAllBases() {
		ArrayList<BaseDTO> list = new ArrayList<BaseDTO>();
		
		list = BaseTDG.getAllBases();
		assertEquals(list.get(0).getID(), 12);
		assertEquals(list.get(1).getID(), 13);		
	}
	
	/**
	 * Runner for all of the tests in the class 
	 */
	@Test
	public static void runAllTheTests() {
		testGetAllBases();
	}

}