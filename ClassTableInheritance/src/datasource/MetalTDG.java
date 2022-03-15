package datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Daniel Holmgren
 * @author Joshua Kellogg
 *
 *         The metalTDG for accessing the database
 */
public class MetalTDG {
	private static MetalTDG instance;

	/**
	 * Empty constructor for the singleton to use
	 */
	private MetalTDG() {

	}

	/**
	 * If an instance of this class does not yet exist creates one
	 * 
	 * @return the instance of the metalTDG
	 */
	public static MetalTDG getSingleton() {
		if (instance == null) {
			instance = new MetalTDG();
		}
		return instance;
	}

	/**
	 * Gets all the metals
	 * 
	 * @return an ArrayList of Metal DTOs
	 */
	public ArrayList<MetalDTO> getAllMetals() {
		ArrayList<MetalDTO> data = new ArrayList<MetalDTO>();
		ResultSet rs;
		try {
			DatabaseManager db = DatabaseManager.getSingleton();
			Connection cn = db.getConnection();
			rs = cn.createStatement().executeQuery("SELECT * FROM Metal");
			while (rs.next()) {
				data.add(new MetalDTO(rs.getInt("metalID"), rs.getInt("metalDissolvedBy"),
						rs.getDouble("molesOfAcidToDissolve")));
			}
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
		return data;
	}

	/**
	 * Figures out which metals can be dissolved by a particular acid
	 * 
	 * @author Madeline & Taryn & Josh K
	 * @param a_id the ID of the acid
	 * @return a list of the metals that can be dissolved by the acid
	 */
	public ArrayList<MetalDTO> getMetalsDissolvedByAcid(int a_id) {
		ArrayList<MetalDTO> data = new ArrayList<MetalDTO>();
		ResultSet rs;
		try {
			DatabaseManager db = DatabaseManager.getSingleton();
			Connection cn = db.getConnection();
			rs = cn.createStatement().executeQuery("SELECT * FROM Metal WHERE metalDissolvedBy = " + a_id);
			while (rs.next()) {
				data.add(new MetalDTO(rs.getInt(1), rs.getInt(2), rs.getDouble("molesOfAcidToDissolve")));
			}
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
		return data;
	}
}