package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domainObjects.ChemicalDomainObject;
import mappers.ChemicalMapper;

public class TestChemicalMapper {
	
	@Test
	public static void testFindBy() throws Exception {
		ChemicalMapper cm = new ChemicalMapper();
		
		ChemicalDomainObject chm = cm.findByID(3);
		assertEquals(chm.getChemicalMoles(), 39.2, 0.001);
		
		ChemicalDomainObject chm2 = cm.findByID(6);
		assertEquals(chm2.getChemicalMoles(), 29.5, 0.001);
	}	
	
	@Test
	public static void testPersist() throws Exception {
		ChemicalMapper cm = new ChemicalMapper();
		ChemicalDomainObject cdo = cm.findByID(6);
		
		cdo.setChemicalMoles(30);
		
		cdo.persist();
		
		ChemicalMapper mc = new ChemicalMapper();
		ChemicalDomainObject odc = mc.findByID(6);
		
		assertEquals(30, odc.getChemicalMoles(), 0.001);

	}	
	
	@Test
	public static void testLowChemicalReport() {
		//TODO: later
	}
	
	public static void runAllTheTests() throws Exception {
		testFindBy();
		testPersist();
		testLowChemicalReport();
		
	}


}
