package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * A Row Data Gateway for the Acid table
 * 
 * @author Madeline & Adam
 *
 */
public class AcidRDG {

	private int acidID;
	private int acidSolute;

	/**
	 * Constructor for AcidRDG
	 * 
	 * @param ID     the ID of the Acid
	 * @param solute the Chemical ID that is a solute of the Acid
	 */
	public AcidRDG(int ID, int solute) {
		acidID = ID;
		acidSolute = solute;
	}

	/**
	 * Method to query the database for an Acid selected by its ID
	 * 
	 * @param ID the ID of the Acid we want to select
	 * @return an AcidRDG for the Acid we select
	 */
	public static AcidRDG findByID(int ID) {
		Connection c;
		AcidRDG result = null;
		try {
			c = DatabaseManager.getSingleton().getConnection();
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Acid WHERE acidID = " + ID);
			rs.next();
			result = new AcidRDG(rs.getInt(1), rs.getInt(2));
		} catch (Exception e) {
			DatabaseException.detectError(e, "AcidRDG.findByID - Class");
		}
		return result;
	}

	/**
	 * Method to persist an AcidRDG to the database
	 */
	public void update() {
		Connection c;
		try {
			c = DatabaseManager.getSingleton().getConnection();
			PreparedStatement s = c.prepareStatement("UPDATE Acid SET acidSolute = ? WHERE acidID = ?");
			s.setInt(1, acidSolute);
			s.setInt(2, acidID);
			s.execute();
		} catch (Exception e) {
			DatabaseException.detectError(e, "AcidRDG.update - Class");
		}
	}

	/**
	 * Getters and Setters for AcidRDG in Class Table
	 */

	public int getAcidID() {
		return acidID;
	}

	public int getAcidSolute() {
		return acidSolute;
	}

	public void setAcidSolute(int acidSolute) {
		this.acidSolute = acidSolute;
	}
}