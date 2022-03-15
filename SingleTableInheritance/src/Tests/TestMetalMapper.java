package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import datasource.ChemicalRDG;
import datasource.DatabaseException;
import datasource.DatabaseManager;
import mappers.MetalMapper;

public class TestMetalMapper {

	@Test
	public static void testCreateMetal() {
		MetalMapper mapper = new MetalMapper();
		try {
			DatabaseManager.getSingleton().setTesting();
			mapper.createMetal(30, "element", 10, 10, 30, 2.0, 3.0);
			ChemicalRDG rdg = ChemicalRDG.findByIDSingle(30);
			assertEquals(30, rdg.getDissolvedBy());
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