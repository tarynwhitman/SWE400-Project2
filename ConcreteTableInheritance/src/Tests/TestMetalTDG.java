package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Test;

import datasource.MetalDTO;
import datasource.MetalTDG;

/**
 * @author Dan Holmgren
 * @author Josh Kellogg Test class for the metalTDG
 */
public class TestMetalTDG {

	/**
	 * Tests the constructor
	 */
	@Test
	public static void testConstructor() {
		MetalTDG m = MetalTDG.getInstance();
		assertNotEquals(m, null);
	}

	/**
	 * Tests the getAllMetals method
	 */
	@Test
	public static void testGetAllMetals() {
		MetalTDG m = MetalTDG.getInstance();
		ArrayList<MetalDTO> results = m.getAllMetals();
		assertEquals(results.size(), 4);
		for (int i = 0; i < results.size() - 1; i++) {
			assertEquals(results.get(i).getDissolvedBy(), (i + 5));
		}
		assertEquals(7, results.get(3).getDissolvedBy());
	}

	/**
	 * Makes sure we can find the metals that are dissolved by a particular acid
	 */
	@Test
	public static void testGetMetalsDissolvedByAcid() {
		MetalTDG m = MetalTDG.getInstance();
		ArrayList<MetalDTO> results = new ArrayList<MetalDTO>();
		results = m.getMetalsDissolvedByAcid(7);
		assertEquals(results.size(), 2);
		assertEquals(22, results.get(0).getID());
		assertEquals(23, results.get(1).getID());
		System.out.println("tis a miracle!");
	}

	/**
	 * Runs all of the tests in this class
	 */
	public static void runAllTheTests() {
		testConstructor();
		testGetAllMetals();
		testGetMetalsDissolvedByAcid();
	}

}
