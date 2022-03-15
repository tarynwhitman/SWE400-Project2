package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import datasource.ElementRDG;

/**
 * @author Daniel Holmgren
 * @author Joshua Kellogg
 * 
 *         Test class for the ElementRDG
 */
public class TestElementRDG {

	// DONT FORGET ROLLBACK

	/**
	 * Tests the constructor
	 */
	@Test
	public static void testConstructor() {
		ElementRDG e = new ElementRDG(1, 10, 12.4, "Test", 8.9);
		assertEquals(e.getID(), 1);
		assertEquals(e.getAtomicMass(), 12.4, 0.01);
		assertEquals(e.getAtomicNumber(), 10);
		assertEquals(e.getName(), "Test");
		assertEquals(e.getMoles(), 8.9, 0.01);
	}

	/**
	 * Makes sure the getters and setters can get and set respectively
	 */
	@Test
	public static void testGettersAndSetters() {
		ElementRDG e = new ElementRDG(1, 2, 3.0, "Tester", 9.2);
		e.setAtomicMass(50.0);
		e.setAtomicNumber(651);
		e.setMoles(2.8);
		e.setName("New Name");
		assertEquals(e.getAtomicMass(), 50.0, 0.01);
		assertEquals(e.getAtomicNumber(), 651);
		assertEquals(e.getID(), 1);
		assertEquals(e.getName(), "New Name");
		assertEquals(e.getMoles(), 2.8, 0.01);
	}

	/**
	 * Tests the findByID method
	 */
	@Test
	public static void testFindByID() {
		ElementRDG e = ElementRDG.findByID(4);
		assertEquals(e.getID(), 4);
		assertEquals(e.getName(), "element1");
		assertEquals(e.getAtomicNumber(), 12);
		assertEquals(e.getAtomicMass(), 50.01, 0.01);
		assertEquals(e.getMoles(), 29.4, 0.01);
	}

	/**
	 * Tests the findByName method
	 */
	@Test
	public static void testFindByName() {
		ElementRDG e = ElementRDG.findByName("element2");
		assertEquals(e.getID(), 5);
		assertEquals(e.getName(), "element2");
		assertEquals(e.getAtomicNumber(), 10);
		assertEquals(e.getAtomicMass(), 20.0, 0.01);
		assertEquals(e.getMoles(), 16.3, 0.01);
	}

	/**
	 * JUnit to test that we can get the type from a name (if it exists)
	 * 
	 * @author Madeline and Adam
	 */
	@Test
	public static void testFindTypeByName() {
		String type = ElementRDG.findTypeByName("element2");
		assertEquals("Element", type);

		String typeFakeNews = ElementRDG.findTypeByName("notReallyAnElement");
		assertNull(typeFakeNews);
	}

	/**
	 * Tests the findByAtomicNumber method
	 */
	@Test
	public static void testFindByAtomicNumber() {
		ElementRDG e = ElementRDG.findByAtomicNumber(11);
		assertEquals(e.getID(), 6);
		assertEquals(e.getName(), "element3");
		assertEquals(e.getAtomicNumber(), 11);
		assertEquals(e.getAtomicMass(), 20.2, 0.01);
		assertEquals(e.getMoles(), 29.5, 0.01);
	}

	/**
	 * Tests the findByAtomicMass method
	 */
	@Test
	public static void testFindByAtomicMass() {
		ElementRDG e = ElementRDG.findByAtomicMass(40.0);
		assertEquals(e.getID(), 7);
		assertEquals(e.getName(), "element4");
		assertEquals(e.getAtomicNumber(), 30);
		assertEquals(e.getAtomicMass(), 40.0, 0.01);
		assertEquals(e.getMoles(), 19.3, 0.01);
	}

	@Test
	public static void testUpdate() {
		// Write after figuring out rollback
	}

	/**
	 * @author Madeline and Adam To be efficient
	 */
	public static void runAllTheTests() {
		testConstructor();
		testGettersAndSetters();
		testFindByID();
		testFindByName();
		testFindTypeByName();
		testFindByAtomicNumber();
		testFindByAtomicMass();
	}

}
