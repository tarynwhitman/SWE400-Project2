package datasource;

import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class CompoundTDG {

	private static CompoundTDG Singleton;

	/**
	 * Makes sure there is only one instance of the TDG
	 */
	public static CompoundTDG getSingleton() {
		if (Singleton == null) {
			Singleton = new CompoundTDG();
		}
		return Singleton;
	}

	/**
	 * Return all the compounds that a given element is in
	 * 
	 * @param e_ID -- ID of the element
	 * @return the list of compounds that the passed element is in
	 */
	public ArrayList<CompoundDTO> getCompoundsByElement(int e_ID) {
		ArrayList<CompoundDTO> list = new ArrayList<CompoundDTO>();
		ArrayList<Integer> listOfCompounds = new ArrayList<Integer>();

		ResultSet r, s;

		try {
			Connection connection = DatabaseManager.getSingleton().getConnection();
			r = connection.createStatement().executeQuery(
					"SELECT * FROM CompoundMadeOfElement WHERE CompoundMadeOfElement.elementID = " + e_ID);

			while (r.next()) {
				listOfCompounds.add(r.getInt(1));
			}
			for (int i : listOfCompounds) {
				CompoundDTO dto;
				s = connection.createStatement()
						.executeQuery("SELECT * FROM Compound WHERE Compound.compoundID = " + i);
				s.next();
				dto = new CompoundDTO(s.getInt(1), s.getString(2), s.getDouble(3));
				list.add(dto);
			}

		} catch (DatabaseException | SQLException e) {
			DatabaseException.detectError(e);
		}

		return list;

	}

	public ArrayList<CompoundDTO> getAllCompounds() {
		ArrayList<CompoundDTO> list = new ArrayList<>();

		ResultSet r;

		try {
			Connection connection = DatabaseManager.getSingleton().getConnection();
			r = connection.createStatement().executeQuery("SELECT * FROM Compound");

			while (r.next()) {
				list.add(new CompoundDTO(r.getInt(1), r.getString(2), r.getDouble(3)));
			}

		} catch (DatabaseException | SQLException e) {
			DatabaseException.detectError(e);
		}

		return list;
	}

}
