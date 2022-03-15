package datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * 
 * @author Daniel Holmgren
 * @author Joshua Kellogg Table data gateway for accessing the metal table
 */
public class MetalTDG {

	private static MetalTDG instance;

	/**
	 * Empty constructor for singleton
	 */
	private MetalTDG() {

	}

	/**
	 * getInstance method to return the instance of the metalTDG
	 * 
	 * @return metalTDG singleton
	 */
	public static MetalTDG getInstance() {
		if (instance == null) {
			instance = new MetalTDG();
		}
		return instance;
	}

	/**
	 * Will search and return all of the metals in the database
	 * 
	 * @return an ArrayList of metalDTOs each of which corresponds to a metal in the
	 *         database
	 */
	public ArrayList<MetalDTO> getAllMetals() {
		ArrayList<MetalDTO> data = new ArrayList<MetalDTO>();
		ResultSet rs;
		try {
			DatabaseManager db = DatabaseManager.getSingleton();
			Connection cn = db.getConnection();
			rs = cn.createStatement().executeQuery("SELECT * FROM Metal");
			while (rs.next()) {
				data.add(new MetalDTO(rs.getInt("elementOrMetalID"), rs.getInt("metalAtomicNumber"),
						rs.getInt("metalDissolvedBy"), rs.getDouble("metalAtomicMass"), rs.getString("metalName"),
						rs.getDouble("metalMoles"), rs.getDouble("molesOfAcidToDissolve")));
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
				data.add(new MetalDTO(rs.getInt("elementOrMetalID"), rs.getInt("metalAtomicNumber"),
						rs.getInt("metalDissolvedBy"), rs.getDouble("metalAtomicMass"), rs.getString("metalName"),
						rs.getDouble("metalMoles"), rs.getDouble("molesOfAcidToDissolve")));
			}
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
		return data;
	}
}