package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import datasource.AcidRDG;

/**
 * Test class for the AcidRDG class
 * 
 * @author Madeline & Adam
 *
 */
public class TestAcidRDG {

	/**
	 * JUnit to test the AcidRDG constructors, getters, and setters with manual
	 * insertion of data
	 */
	@Test
	public static void testConstructorGettersSetters() {
		AcidRDG rdg = new AcidRDG(1, 27);

		assertEquals(1, rdg.getAcidID());
		assertEquals(27, rdg.getAcidSolute());

		rdg.setAcidSolute(42);

		assertEquals(42, rdg.getAcidSolute());
	}

	/**
	 * JUnit to test that we can find an Acid by ID
	 */
	@Test
	public static void testFindByID() {
		AcidRDG rdg = AcidRDG.findByID(2);
		assertEquals(2, rdg.getAcidID());
		assertEquals(5, rdg.getAcidSolute());
	}

	/**
	 * JUnit to test that we can persist an updated AcidRDG to the database
	 */
	@Test
	public static void testUpdate() {
		AcidRDG rdg = AcidRDG.findByID(3);
		rdg.setAcidSolute(6);

		rdg.update();

		AcidRDG result = AcidRDG.findByID(3);

		assertEquals(3, result.getAcidID());
		assertEquals(6, result.getAcidSolute());
	}

	/**
	 * Run all the tests
	 */
	public static void runAllTheTests() {
		testConstructorGettersSetters();
		testFindByID();
		testUpdate();
	}

}