package datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Daniel Holmgren
 * @author Joshua Kellogg
 *
 */
public class ElementTDG {

	private static ElementTDG instance;

	/**
	 * Empty constructor for singleton purposes
	 */
	private ElementTDG() {

	}

	/**
	 * Singleton getter for singleton purposes
	 * 
	 * @return
	 */
	public static ElementTDG getInstance() {
		if (instance == null) {
			instance = new ElementTDG();
		}
		return instance;
	}

	/**
	 * the getElementsInRange method, It gets a list of elementDTOs corresponding to
	 * elements in the database within the lower and upper range
	 * 
	 * @param lower
	 * @param upper
	 * @return
	 */
	public ArrayList<ElementDTO> getElementInRange(double lower, double upper) {
		ArrayList<ElementDTO> data = new ArrayList<ElementDTO>();
		ResultSet rs;
		try {
			DatabaseManager db = DatabaseManager.getSingleton();
			Connection cn = db.getConnection();
			rs = cn.createStatement().executeQuery(
					"SELECT * FROM Element WHERE elementAtomicMass > " + lower + " AND elementAtomicMass < " + upper);
			while (rs.next()) {
				data.add(new ElementDTO(rs.getInt(1), rs.getInt(2), rs.getDouble(3)));
			}

		} catch (Exception e) {
			DatabaseException.detectError(e);
		}

		return data;
	}

	/**
	 * Return all the elements in a given compound
	 * 
	 * @param c_ID -- ID of the compound
	 * @return the list of elements that are in the compound
	 */
	public ArrayList<ElementDTO> getElementsInCompound(int c_ID) {
		ArrayList<ElementDTO> list = new ArrayList<ElementDTO>();
		ArrayList<Integer> listOfCompounds = new ArrayList<Integer>();

		ResultSet r, s;

		try {
			Connection connection = DatabaseManager.getSingleton().getConnection();
			r = connection.createStatement().executeQuery(
					"SELECT * FROM CompoundMadeOfElement WHERE CompoundMadeOfElement.compoundID = " + c_ID);

			while (r.next()) {
				listOfCompounds.add(r.getInt(2));
			}
			for (int i : listOfCompounds) {
				ElementDTO dto;
				s = connection.createStatement().executeQuery("SELECT * FROM Element WHERE Element.elementID = " + i);
				s.next();
				dto = new ElementDTO(s.getInt(1), s.getInt(2), s.getDouble(3));
				list.add(dto);
			}

		} catch (DatabaseException | SQLException e) {
			DatabaseException.detectError(e);
		}

		return list;

	}

	public ArrayList<ElementDTO> getAllElements() {
		ArrayList<ElementDTO> list = new ArrayList<ElementDTO>();
		ResultSet s;

		try {
			Connection connection = DatabaseManager.getSingleton().getConnection();
			s = connection.createStatement().executeQuery("SELECT * FROM Element");

			while (s.next()) {
				ElementDTO dto = new ElementDTO(s.getInt(1), s.getInt(2), s.getDouble(3));
				list.add(dto);
			}

		} catch (Exception e) {
			DatabaseException.detectError(e);
		}

		return list;
	}
}
