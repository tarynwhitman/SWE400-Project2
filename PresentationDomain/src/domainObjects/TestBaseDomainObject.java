package domainObjects;

import static org.junit.Assert.*;

import mappers.BaseMapper;

/**
 * @author Marlee Lackey
 * @author Taryn Whitman
 */
public class TestBaseDomainObject {

	/**
	 * Test the constructor and setters
	 */
	public void test() {
		BaseMapper b = new BaseMapper();
		BaseDomainObject bdo = new BaseDomainObject(b);
		assertEquals(bdo.getDataMapper(), b);
		
		bdo.setBaseName("Selenaium");
		assertEquals("Selenaium", bdo.getBaseName());
		
		bdo.setBaseID(342);
		assertEquals(342, bdo.getBaseID());
		
		bdo.setBaseMoles(24.4);
		assertEquals(24.4, bdo.getBaseMoles(), 0.01);
		
		bdo.setBaseSolute(22);
		assertEquals(22, bdo.getBaseSolute());
		
		
	}

}
