package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mappers.ElementMapper;
import datasource.DatabaseException;
import datasource.DatabaseManager;
import datasource.ElementRDG;

public class TestElementMapper {
	
	@Test
	public static void testCreateElement() {
		ElementMapper mapper = new ElementMapper();
		try {
			DatabaseManager.getSingleton().setTesting();
			mapper.createElement(40, "element", 10, 10, 4.7);
			mapper.persist();
			ElementRDG rdg = ElementRDG.findByID(40);
			assertEquals("element", rdg.getName());
			DatabaseManager.getSingleton().rollBack();


		} catch (Exception e) {
			e.printStackTrace();
			DatabaseException.detectError(e, "Exception in TestElementMapper.testCreateElement()");
		}
	}

	public static void runAllTheTests() {
		testCreateElement();
		
	}

}