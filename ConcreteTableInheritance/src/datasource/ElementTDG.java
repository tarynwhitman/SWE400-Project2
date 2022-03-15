package datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Dan Holmgren
 * @author Josh Kellogg
 *
 */
public class ElementTDG {

	private static ElementTDG instance;

	private ElementTDG() {

	}

	/**
	 * @author Dan Holmgren
	 * @author Joshua Kellogg
	 * @return an instance of the ElementTDG
	 */
	public static ElementTDG getInstance() {
		if (instance == null) {
			instance = new ElementTDG();
		}
		return instance;
	}

	/**
	 * Returns the elements in the database between the atomic mass lower and upper
	 * 
	 * @param lower
	 * @param upper
	 * @return Arraylist of ElementDTOs each of which corresponds to an Element in
	 *         the database
	 */
	public ArrayList<ElementDTO> getElementsInRange(double lower, double upper) {
		ArrayList<ElementDTO> data = new ArrayList<ElementDTO>();
		ResultSet rs;

		try {
			DatabaseManager db = DatabaseManager.getSingleton();
			Connection cn = db.getConnection();
			rs = cn.createStatement().executeQuery("SELECT * FROM Element WHERE elementAtomicMass > " + lower + " AND elementAtomicMass < " + upper);
			while (rs.next()) {
				data.add(new ElementDTO(rs.getInt("elementOrMetalID"), rs.getInt("elementAtomicNumber"),
						rs.getDouble("elementAtomicMass"), rs.getString("elementName"), rs.getDouble("elementMoles")));
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
				s = connection.createStatement()
						.executeQuery("SELECT * FROM Element WHERE Element.elementOrMetalID = " + i);
				s.next();
				dto = new ElementDTO(s.getInt("elementOrMetalID"), s.getInt("elementAtomicNumber"),
						s.getDouble("elementAtomicMass"), s.getString("elementName"), s.getDouble("elementMoles"));
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
				ElementDTO dto = new ElementDTO(s.getInt("elementOrMetalID"), s.getInt("elementAtomicNumber"),
						s.getDouble("elementAtomicMass"), s.getString("elementName"), s.getDouble("elementMoles"));
				list.add(dto);
			}

		} catch (Exception e) {
			DatabaseException.detectError(e);
		}

		return list;
	}
}
