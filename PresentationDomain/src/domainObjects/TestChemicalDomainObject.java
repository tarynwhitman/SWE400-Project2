package domainObjects;

import static org.junit.Assert.*;

import org.junit.Test;

import mappers.ChemicalMapper;

/**
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class TestChemicalDomainObject {

	/** 
	 * Test that the constructor and setters work properly
	 */
	@Test
	public void test() {
		ChemicalMapper c = new ChemicalMapper();
		ChemicalDomainObject e = new ChemicalDomainObject(c);
		assertEquals(e.getDataMapper(), c);
		e.setChemicalID(1222);
		assertEquals(1222, e.getChemicalID());
		e.setChemicalMoles(23.22);
		assertEquals(23.22, e.getChemicalMoles(), 0.01);

		
	}

}
