package Tests;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.Test;

import datasource.ChemicalDTO;
import datasource.DatabaseException;
import datasource.DatabaseManager;

/**
 * Test Class for Chemical DTO
 * 
 * @author Madeline and Adam
 *
 */
public class TestChemicalDTO {

	/**
	 * A JUnit to test manual insertion of data into a ChemicalDTO
	 */
	@Test
	public static void testConstructorAndGetters() {
		ChemicalDTO dto = new ChemicalDTO(1, "thingy", 4.5);

		assertEquals(dto.getChemicalID(), 1);
		assertEquals(dto.getChemicalName(), "thingy");
		assertEquals(dto.getChemicalMoles(), 4.5, 0.01);
	}

	/**
	 * A Junit to test that we can select a tuple from the DB and store it in a
	 * ChemicalDTO
	 */
	@Test
	public static void testSingleSelect() {
		Connection cn;
		ChemicalDTO dto = null;

		try {
			cn = DatabaseManager.getSingleton().getConnection();
			ResultSet rs = cn.createStatement().executeQuery("SELECT * FROM Chemical WHERE ChemicalID = 9");
			rs.next();
			dto = new ChemicalDTO(rs.getInt(1), rs.getString(2), rs.getDouble(3));
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}

		assertEquals(dto.getChemicalID(), 9);
		assertEquals(dto.getChemicalName(), "metal1");
		assertEquals(dto.getChemicalMoles(), 12.5, 0.01);
	}

	/**
	 * Run all the tests for ChemicalDTO
	 */
	public static void runAllTheTests() {
		testConstructorAndGetters();
		testSingleSelect();
	}

}