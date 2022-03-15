package domainObjects;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import mappers.CompoundMapper;
import mappers.ElementMapper;
import quantifiedElementPackage.QuantifiedElement;

/**
 * @author Marlee Lackey
 * @author Taryn Whitman
 */
public class TestCompoundDomainObject {

	/**
	 * Test the constructor and setters
	 * @throws Exception
	 */
	public void test() throws Exception {
		CompoundMapper c = new CompoundMapper();
		CompoundDomainObject cdo = new CompoundDomainObject(c);
		assertEquals(cdo.getDataMapper(), c);
		
		cdo.setCompoundID(1223);
		assertEquals(1223, cdo.getCompoundID());
		
		cdo.setCompoundName("Neilium monoxide");
		assertEquals("Neilium monoxide", cdo.getCompoundName());
		
		cdo.setCompoundMoles(24.4);
		assertEquals(24.4, cdo.getCompoundMoles(), 0.01);
		
		ArrayList<QuantifiedElement> elements = new ArrayList<>();
		ElementMapper em = new ElementMapper();
		ElementDomainObject edo = em.createElement(12, "Neilium", 13, 23.4, 32.3);
		ElementDomainObject edo2 = em.createElement(14, "Oxygen", 12, 15.9, 23.3);
		elements.add(new QuantifiedElement(edo, 2));
		elements.add(new QuantifiedElement(edo2, 1));
		
		cdo.setCompoundElements(elements);
		assertEquals(cdo.getElements(), elements);
		
		
	}
	
	/**
	 * Test that the business logic works
	 * @throws Exception
	 */
	@Test (expected = Exception.class)
	public void testBusinessLogic() throws Exception {
		// check business logic
		CompoundMapper c = new CompoundMapper();
		CompoundDomainObject cdo = new CompoundDomainObject(c);
		
		ArrayList<QuantifiedElement> elements = new ArrayList<>();
		ElementMapper em = new ElementMapper();
		ElementDomainObject edo = em.createElement(12, "Neilium", 13, 23.4, 32.3);
		ElementDomainObject edo2 = em.createElement(14, "Oxygen", 12, 15.9, 23.3);
		elements.add(new QuantifiedElement(edo, 2));
		elements.add(new QuantifiedElement(edo2, 1));
		
		ElementDomainObject edo3 = em.createElement(14, "Oxygen", 12, 15.9, 23.3);
		elements.add(new QuantifiedElement(edo3, 1));
				
		cdo.setCompoundElements(elements);
	}
	
	

}
