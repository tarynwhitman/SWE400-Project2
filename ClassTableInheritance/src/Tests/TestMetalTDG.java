package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import datasource.MetalDTO;
import datasource.MetalTDG;
/**
 * @author Daniel Holmgren
 * @author Joshua Kellogg
 * Test class for the MetalTDG
 *
 */
public class TestMetalTDG {

	/**
	 * Asserts the constructor works
	 */
	@Test
	public static void testConstructor() {
		MetalTDG m = MetalTDG.getSingleton();
		assertNotEquals(m, null);
		MetalTDG d = MetalTDG.getSingleton();
		assertEquals(m, d);
	}
	
	/**
	 * Makes sure the getAllMetals method returns the correct amount of items
	 */
	@Test
	public static void testGetAllMetals() {
		MetalTDG m = MetalTDG.getSingleton();
		ArrayList<MetalDTO> results = new ArrayList<MetalDTO>();
		results = m.getAllMetals();
		assertEquals(results.size(), 3);
	}
	
	/**
	 * Makes sure we can find the metals that are dissolved by a particular acid
	 */
	@Test
	public static void testGetMetalsDissolvedByAcid() {
		MetalTDG m = MetalTDG.getSingleton();
		ArrayList<MetalDTO> results = new ArrayList<MetalDTO>();
		results = m.getMetalsDissolvedByAcid(2);
		assertEquals(results.size(), 2);
		assertEquals(9, results.get(0).getID());
		assertEquals(10, results.get(1).getID());
		System.out.println("tis a miracle!");
	}
	
	/**
	 * To be efficient
	 */
	public static void runAllTheTests() {
		testConstructor();
		testGetAllMetals();
		testGetMetalsDissolvedByAcid();
	}

}
