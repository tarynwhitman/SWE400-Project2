package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import datasource.CompoundRDG;
/**
 * 
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class TestCompoundRDG {

	/**
	 * Tests that the constructor works properly
	 */
	@Test
	public static void testConstructor() {
		CompoundRDG comp = new CompoundRDG(5);
		assertEquals(comp.getCompoundID(), 5);
		
	}
	
	/**
	 * Test that the findBy's work properly
	 */
	@Test 
	public static void testFinders() {
		CompoundRDG comp = CompoundRDG.findByIDClass(14);
		assertEquals(comp.getCompoundID(), 14);
	}
	
	/**
	 * runner used by entire Class project to run all tests at once
	 */
	@Test
	public static void runAllTheTests() {
		testConstructor();
		testFinders();
	}

}
