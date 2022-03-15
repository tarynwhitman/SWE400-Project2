package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mappers.MetalMapper;
import datasource.ChemicalRDG;
import datasource.DatabaseException;
import datasource.DatabaseManager;
import datasource.ElementRDG;
import datasource.MetalRDG;

public class TestMetalMapper {

	@Test
	public static void testCreateMetal() {
		MetalMapper mapper = new MetalMapper();
		try {
			DatabaseManager.getSingleton().setTesting();
			mapper.createMetal(40, "element", 20, 50, 30, 5.3, 1.1);
			mapper.persist();

			ChemicalRDG rdg = ChemicalRDG.findByID(40);
			assertEquals("element", rdg.getChemicalName());

			ElementRDG rdg2 = ElementRDG.findByID(40);
			assertEquals(20, rdg2.getAtomicNumber());
			assertEquals(50, rdg2.getAtomicMass(), 0.001);

			MetalRDG rdg3 = MetalRDG.findByID(40);
			assertEquals(30, rdg3.getDissolvedBy());

			DatabaseManager.getSingleton().rollBack();

		} catch (Exception e) {
			e.printStackTrace();
			DatabaseException.detectError(e, "Exception in TestMetalMapper.testCreateMetal()");
		}
	}

	public static void runAllTheTests() {
		testCreateMetal();

	}

}
