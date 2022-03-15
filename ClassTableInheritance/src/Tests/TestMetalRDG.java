package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import datasource.MetalRDG;

/**
 * @author Daniel Holmgren
 * @author Joshua Kellogg Test class metalTDG
 *
 */
public class TestMetalRDG {

	/**
	 * Makes sure the constructor constructs correctly
	 */
	@Test
	public static void testConstructor() {
		MetalRDG m = new MetalRDG(1, 2, 4.8);
		assertEquals(m.getID(), 1);
		assertEquals(m.getDissolvedBy(), 2);
		assertEquals(m.getMolesOfAcidToDissolve(), 4.8, 0.01);
		m.setDissolvedBy(3);
		assertEquals(m.getDissolvedBy(), 3);
		m.setMolesOfAcidToDissolve(3.7);
		assertEquals(m.getMolesOfAcidToDissolve(), 3.7, 0.01);
	}

	/**
	 * Asserts findByID works
	 */
	@Test
	public static void testFindByID() {
		MetalRDG m = MetalRDG.findByID(10);
		assertEquals(m.getID(), 10);
		assertEquals(m.getDissolvedBy(), 2);
		assertEquals(m.getMolesOfAcidToDissolve(), 8.4, 0.01);
	}

	/**
	 * Updates the database and asserts the changes were made
	 */
	@Test
	public static void testUpdate() {
		MetalRDG m = MetalRDG.findByID(11);
		assertEquals(m.getID(), 11);
		assertEquals(m.getDissolvedBy(), 1);
		m.setDissolvedBy(456);
		m.update();
		MetalRDG d = MetalRDG.findByID(11);
		assertEquals(d.getDissolvedBy(), 456);
	}

	/**
	 * Efficient
	 */
	public static void runAllTheTests() {
		testConstructor();
		testFindByID();
		testUpdate();
	}
}
