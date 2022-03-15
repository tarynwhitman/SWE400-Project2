package datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Table Data Gateway for the Acid table
 * 
 * @author Madeline and Adam
 *
 */
public class AcidTDG {

	/**
	 * Constructor stuff for the singleton of AcidTDG
	 */
	private static AcidTDG Singleton;

	private AcidTDG() {
	}

	public static AcidTDG getSingleton() {
		if (Singleton == null) {
			Singleton = new AcidTDG();
		}
		return Singleton;
	}

	/**
	 * Method to return all the Acids in the DB
	 * 
	 * @return an ArrayList of AcidDTOs
	 */
	public ArrayList<AcidDTO> getAllAcids() {
		ArrayList<AcidDTO> list = new ArrayList<AcidDTO>();
		Connection c;

		try {
			c = DatabaseManager.getSingleton().getConnection();
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Acid");
			while (rs.next()) {
				AcidDTO dto = new AcidDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
				list.add(dto);
			}
		} catch (Exception e) {
			DatabaseException.detectError(e, "AcidTDG - Concrete");
		}

		return list;
	}
}