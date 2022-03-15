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
		ChemicalDTO dto = new ChemicalDTO(1, 2, "Boron", 5, 21.3, 7, 9, 12, 6.0, 4);

		assertEquals(dto.getChemicalID(), 1);
		assertEquals(dto.getChemicalType(), 2);
		assertEquals(dto.getChemicalName(), "Boron");
		assertEquals(dto.getChemicalAtomicNumber(), 5);
		assertEquals(dto.getChemicalAtomicMass(), 21.3, 0);
		assertEquals(dto.getChemicalDissolvedBy(), 7);
		assertEquals(dto.getChemicalSoluteA(), 9);
		assertEquals(dto.getChemicalSoluteB(), 12);
		assertEquals(dto.getChemicalMoles(), 6.0, 0.01);
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
			dto = new ChemicalDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDouble(5),
					rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getDouble(9), rs.getDouble(10));
		} catch (Exception e) {
			DatabaseException.detectError(e, "TestChemicalDTO - Single");
		}

		assertEquals(dto.getChemicalID(), 9);
		assertEquals(dto.getChemicalType(), 2);
		assertEquals(dto.getChemicalName(), "name8");
		assertEquals(dto.getChemicalAtomicNumber(), 51);
		assertEquals(dto.getChemicalAtomicMass(), 21.3, 20.7);
		assertEquals(dto.getChemicalDissolvedBy(), 52);
		assertEquals(dto.getChemicalSoluteA(), 53);
		assertEquals(dto.getChemicalSoluteB(), 0);
		assertEquals(dto.getChemicalMoles(), 9.0, 0.01);
		assertEquals(dto.getChemicalMolesToDissolveMetal(), 4, 0.01);
	}

	/**
	 * Run all the tests for ChemicalDTO
	 */
	public static void runAllTheTests() {
		testConstructorAndGetters();
		testSingleSelect();
	}

}