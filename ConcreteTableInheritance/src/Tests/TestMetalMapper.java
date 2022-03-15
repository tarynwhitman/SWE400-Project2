package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import datasource.DatabaseException;
import datasource.DatabaseManager;
import datasource.MetalRDG;
import domainObjects.MetalDomainObject;
import mappers.MetalMapper;

public class TestMetalMapper {
	
	@Test
	public static void testCreateMetal() {
		MetalMapper mapper = new MetalMapper();
		try {
			DatabaseManager.getSingleton().setTesting();
			mapper.createMetal(40, "element", 9, 10, 30, 3.6, 6.8);
			mapper.persist();
			MetalRDG rdg = MetalRDG.findByID(40);
			assertEquals(30, rdg.getDissolvedBy());
			DatabaseManager.getSingleton().rollBack();


		} catch (Exception e) {
			e.printStackTrace();
			DatabaseException.detectError(e, "Exception in TestMetalMapper.testCreateMetal()");
		}
	}
	
	public static void testFindBy() {
		MetalMapper mapper = new MetalMapper();
		try {
			DatabaseManager.getSingleton().setTesting();
			MetalDomainObject metal = mapper.findByID(20);
			assertEquals(metal.getMoles(), 29.4, 0.001);
			DatabaseManager.getSingleton().rollBack();

		} catch (Exception e) {
			e.printStackTrace();
			DatabaseException.detectError(e, "Exception in TestElementMapper.testCreateElement()");
		}
	}
	
	public static void testPersist() throws Exception {
		
		MetalMapper cm = new MetalMapper();
		MetalDomainObject m = cm.findByID(21);
		
		m.setMoles(90);
		
		m.persist();
		
		MetalMapper mc = new MetalMapper();
		MetalDomainObject m2 = mc.findByID(21);
		
		assertEquals(90, m2.getMoles(), 0.001);
		
	}

	public static void runAllTheTests() throws Exception {
		testCreateMetal();
		testFindBy();
		testPersist();
		
	}



}