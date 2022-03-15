package domainObjects;

import static org.junit.Assert.*;

import mappers.AcidMapper;

/**
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class TestAcidDomainObject {

	/**
	 * Test the constructor and setters
	 */
	
	public void test() {
		AcidMapper b = new AcidMapper();
		AcidDomainObject bdo = new AcidDomainObject(b);
		assertEquals(bdo.getDataMapper(), b);
		
		bdo.setAcidName("Selenaium");
		assertEquals("Selenaium", bdo.getAcidName());
		
		bdo.setAcidID(332);
		assertEquals(332, bdo.getAcidID());
		
		bdo.setAcidMoles(27.4);
		assertEquals(27.4, bdo.getAcidMoles(), 0.01);
		
		bdo.setAcidSolute(23);
		assertEquals(23, bdo.getAcidSolute());
		
		
	}

}
