package Tests;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import datasource.CompoundRDG;
import datasource.DatabaseException;
import datasource.DatabaseManager;
import domainObjects.CompoundDomainObject;
import domainObjects.ElementDomainObject;
import mappers.CompoundMapper;
import mappers.ElementMapper;
import quantifiedElementPackage.QuantifiedElement;

/**
 * @author Joshua, Ace
 *
 */
public class TestCompoundMapper {

	@Test
	public static void testFindBy() {
		CompoundMapper mapper = new CompoundMapper();
		try {
			DatabaseManager.getSingleton().setTesting();
			mapper.findByID(0);
			assertEquals(mapper.getMoles(), 28.3, 0.001);
			DatabaseManager.getSingleton().rollBack();

		} catch (Exception e) {
			e.printStackTrace();
			DatabaseException.detectError(e, "Exception in TestElementMapper.testCreateElement()");
		}
	}

	private static void testPersistRemove() throws Exception {
		CompoundMapper mapper = new CompoundMapper();

		DatabaseManager.getSingleton().setTesting();

		CompoundDomainObject compound = mapper.findByID(0);
		assertEquals(mapper.getMoles(), 28.3, 0.001);

		mapper.setMoles(28.4);
		compound.persist();

		CompoundRDG rdg = CompoundRDG.findByIDConcrete(0);
		assertEquals(28.4, rdg.getCompoundMoles(), 0.001);

		assertEquals(mapper.getMyElements().get(0).getElement().getElementName(), "element1");

		Statement stmt = DatabaseManager.getSingleton().getConnection().createStatement();
		stmt.executeQuery("SELECT * FROM CompoundMadeOfElement");
		ResultSet rs = stmt.getResultSet();

		int size = 0;
		while (rs.next()) {
			size++;
		}
		assertEquals(4, size);

		compound.getElements().remove(0);
		compound.persist();

		stmt = DatabaseManager.getSingleton().getConnection().createStatement();
		stmt.executeQuery("SELECT * FROM CompoundMadeOfElement");
		ResultSet rs2 = stmt.getResultSet();

		size = 0;
		while (rs2.next()) {
			size++;
		}
		assertEquals(3, size);
		DatabaseManager.getSingleton().rollBack();
	}

	public static void testPersistAdd() throws Exception {

		Statement stmt = DatabaseManager.getSingleton().getConnection().createStatement();
		stmt = DatabaseManager.getSingleton().getConnection().createStatement();
		stmt.executeQuery("SELECT * FROM CompoundMadeOfElement");
		ResultSet rs3 = stmt.getResultSet();

		int size = 0;
		while (rs3.next()) {
			size++;
		}
		assertEquals(4, size);

		CompoundMapper mapper = new CompoundMapper();
		CompoundDomainObject compound = mapper.findByID(0);
		DatabaseManager.getSingleton().setTesting();

		ElementMapper em = new ElementMapper();
		em.createElement(30, "testElement", 12, 13, 10.10);
		ElementDomainObject e = new ElementDomainObject(em);
		compound.getElements().add(new QuantifiedElement(e, 2));
		compound.getDataMapper().persist();

		Statement stmt2 = DatabaseManager.getSingleton().getConnection().createStatement();
		stmt2 = DatabaseManager.getSingleton().getConnection().createStatement();
		stmt2.executeQuery("SELECT * FROM CompoundMadeOfElement");
		ResultSet rs = stmt2.getResultSet();

		size = 0;
		while (rs.next()) {
			size++;
		}
		assertEquals(5, size);
		DatabaseManager.getSingleton().rollBack();
	}

	@Test
	public static void testPersist() {
		try {
			testPersistRemove();
			testPersistAdd();

//			
//			for (QuantifiedElement q : mapper.getMyElements()) {
//				System.out.println(q.getElement().getElementName() + "___" + q.getQuantityInCompound());
//			}
//			

//			mapper.createElement(40, "element", 10, 10, 4.7);
//			ElementRDG rdg = ElementRDG.findByID(40);
//			assertEquals("element", rdg.getName());

		} catch (Exception e) {
			e.printStackTrace();
			DatabaseException.detectError(e, "Exception in TestElementMapper.testCreateElement()");
		}
	}

	public static void runAllTheTests() {
		testFindBy();
		testPersist();

	}

}