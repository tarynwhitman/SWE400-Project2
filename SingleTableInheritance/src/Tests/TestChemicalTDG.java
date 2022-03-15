package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import datasource.ChemicalDTO;
import datasource.ChemicalTDG;
import datasource.CompoundMadeOfElementDTO;

/**
 * @author Joshua Kellogg
 * @author Daniel Holgren
 *
 */
public class TestChemicalTDG {
	
	/**
	 * Test to assure there is only ever one instance of the object
	 */
	@Test
	public static void testSingleton() {
		ChemicalTDG c = ChemicalTDG.getSingleton();
		ChemicalTDG d = ChemicalTDG.getSingleton();
		assertEquals(c, d);
	}
	
	/**
	 * Test the get all bases method
	 */
	@Test
	public static void testGetAllBases() {
		ChemicalTDG c = ChemicalTDG.getSingleton();
		ArrayList<ChemicalDTO> result = c.getAllBases();
		//Test to make sure it gets the correct number of dtos
		assertEquals(3, result.size());
		//Test to make sure all dtos are for bases
		for(int i = 0; i < result.size(); i++) {
			assertEquals(1, result.get(i).getChemicalType());
		}
	}
	
	/**
	 * Test the get Elements in range method
	 */
	@Test
	public static void testGetElementsInRange() {
		ChemicalTDG c = ChemicalTDG.getSingleton();
		ArrayList<ChemicalDTO> result = c.getElementsInRange(0, 100);
		//Test to make sure it gets the correct number of dtos
		assertEquals(15, result.size());
		//Test to make sure all dto atomic masses are in range
		for(int i = 0; i < result.size(); i++) {
			if((result.get(i).getChemicalAtomicMass() < 1) || result.get(i).getChemicalAtomicMass() > 100) {
				fail("Atomic mass not in range");
			}
		}
		System.out.println("All atomic masses in range");
	}
	
	/**
	 * Test the get compounds by element method
	 */
	@Test
	public static void testGetCompoundsByElement() {
		ChemicalTDG c = ChemicalTDG.getSingleton();
		ArrayList<CompoundMadeOfElementDTO> result = c.getCompoundsByElement(10);
		//Test to make sure it gets the correct number of dtos
		assertEquals(2, result.size());
		//Test to make sure all compounds have the correct elements
		for(int i = 0; i < result.size(); i++) {
			assertEquals(result.get(i).getElementID(), 10);
		}
	}
	
	/**
	 * Test the getElementsInCompound() method
	 */
	@Test
	public static void testGetElementsInCompound() {
		ChemicalTDG c = ChemicalTDG.getSingleton();
		ArrayList<CompoundMadeOfElementDTO> result = c.getElementsInCompound(15);
		//Test to make sure it gets the correct number of dtos
		assertEquals(2, result.size());
		//Test to make sure all compounds have the correct elements
		for(int i = 0; i < result.size(); i++) {
			assertEquals(result.get(i).getElementID(), 15);
		}
	}
	/**
	 * Makes sure we can find the metals that are dissolved by a particular acid
	 */
	@Test
	public static void testGetMetalsDissolvedByAcid() {
		ChemicalTDG c = ChemicalTDG.getSingleton();
		ArrayList<ChemicalDTO> results = new ArrayList<ChemicalDTO>();
		results = c.getChemicalsDissolvedByAcid(8);
		assertEquals(results.size(), 3);
		assertEquals(12, results.get(0).getChemicalID());
		assertEquals(13, results.get(1).getChemicalID());
		assertEquals(14, results.get(2).getChemicalID());
		System.out.println("tis a miracle!");
	}

	/**
	 * A test to allow for efficient testing of the whole system
	 */
	public static void runAllTheTests() {
		testSingleton();
		testGetAllBases();
		testGetElementsInRange();
		testGetCompoundsByElement();
		testGetMetalsDissolvedByAcid();
		testGetElementsInCompound();
	}

}