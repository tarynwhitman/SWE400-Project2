package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import datasource.ChemicalDTO;
import datasource.ChemicalTDG;

public class TestChemicalTDG {

	@Test
	public static void testSingleton() {
		ChemicalTDG c = ChemicalTDG.getSingleton();
		ChemicalTDG d = ChemicalTDG.getSingleton();
		assertEquals(c, d);
	}

	@Test
	public static void testGetAllChemicals() {
		ChemicalTDG c = ChemicalTDG.getSingleton();
		ArrayList<ChemicalDTO> result = c.getAllChemicals();
		// Test to make sure it gets the correct number of dtos
		assertEquals(15, result.size());
		// Test to make sure all dtos are for bases
		for (int i = 1; i <= result.size(); i++) {
			assertEquals(i, result.get(i - 1).getChemicalID());
		}
	}

	public static void runAllTheTests() {
		testSingleton();
		testGetAllChemicals();
	}

}