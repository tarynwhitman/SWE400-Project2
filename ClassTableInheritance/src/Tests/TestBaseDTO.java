package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import datasource.BaseDTO;

/**
 * @author Ace and Josh B 
 * Test class for the Data transfer object class
 */
public class TestBaseDTO {
	
	@Test
	public static void testBaseDTO() {
		BaseDTO dto = new BaseDTO(1, 2);
		assertEquals(1, dto.getID());
		assertEquals(2, dto.getSolute());

	}
	
	@Test
	public static void runAllTheTests() {
		testBaseDTO();
	}
	

}
