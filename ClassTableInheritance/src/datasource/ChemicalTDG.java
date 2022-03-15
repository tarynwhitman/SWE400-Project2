package datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Table Data Gateway for the Chemical table
 * 
 * @author Madeline and Adam
 *
 */
public class ChemicalTDG {

	/**
	 * Constructor-y stuff for the singleton of ChemicalTDG
	 */
	private static ChemicalTDG Singleton;

	private ChemicalTDG() {
	}

	public static ChemicalTDG getSingleton() {
		if (Singleton == null) {
			Singleton = new ChemicalTDG();
		}
		return Singleton;
	}

	/**
	 * Method to return all the Chemicals in the DB
	 * 
	 * @return an ArrayList of ChemicalDTOs
	 */
	public ArrayList<ChemicalDTO> getAllChemicals() {
		ArrayList<ChemicalDTO> list = new ArrayList<ChemicalDTO>();
		Connection c;

		try {
			c = DatabaseManager.getSingleton().getConnection();
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Chemical");
			while (rs.next()) {
				ChemicalDTO dto = new ChemicalDTO(rs.getInt(1), rs.getString(2), rs.getDouble(3));
				list.add(dto);
			}
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}

		return list;
	}
}