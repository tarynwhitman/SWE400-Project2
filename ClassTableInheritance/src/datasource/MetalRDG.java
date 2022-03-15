package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Daniel Holmgren
 * @author Joshua Kellogg
 *
 *         The metalRDG class for accessing the database
 */
public class MetalRDG {
	private int ID, dissolvedBy;
	private double molesOfAcidToDissolve;
	private static Connection cn;

	/**
	 * Constructor, assigns variables and sets up the connection for use in the rest
	 * of the class
	 * 
	 * @param ID
	 * @param dissolvedBy
	 * @param molesOfAcidToDissolve
	 */
	public MetalRDG(int ID, int dissolvedBy, double molesOfAcidToDissolve) {
		this.ID = ID;
		this.dissolvedBy = dissolvedBy;
		this.molesOfAcidToDissolve = molesOfAcidToDissolve;

		DatabaseManager db;
		try {
			db = DatabaseManager.getSingleton();
			cn = db.getConnection();
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
	}

	/**
	 * Returns a metalRDG with the given ID
	 * 
	 * @param ID
	 * @return
	 */
	public static MetalRDG findByID(int ID) {
		MetalRDG data = null;
		try {
			ResultSet rs = cn.createStatement().executeQuery("SELECT * FROM Metal WHERE metalID = " + ID);
			if (rs.next()) {
				data = new MetalRDG(rs.getInt("metalID"), rs.getInt("metalDissolvedBy"),
						rs.getDouble("molesOfAcidToDissolve"));
			}
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
		return data;
	}

	/**
	 * Updates the database with the given instance variables
	 */
	public void update() {
		PreparedStatement stmt;
		try {
			stmt = cn.prepareStatement(
					"UPDATE Metal SET metalDissolvedBy = ?, molesOfAcidToDissolve = ? WHERE metalID = ?");
			stmt.setInt(1, dissolvedBy);
			stmt.setDouble(2, molesOfAcidToDissolve);
			stmt.setInt(3, ID);
			stmt.execute();
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
	}

	public void insert() {
		PreparedStatement stmt;
		try {
			Connection connection = DatabaseManager.getSingleton().getConnection();

			stmt = connection.prepareStatement("INSERT INTO Metal VALUES (?, ?, ?)");
			stmt.setInt(1, ID);
			stmt.setInt(2, dissolvedBy);
			stmt.setDouble(3, molesOfAcidToDissolve);

			stmt.executeUpdate();

		} catch (Exception e) {
			DatabaseException.detectError(e);

		}

	}

	// Getters and setters
	public int getID() {
		return ID;
	}

	public int getDissolvedBy() {
		return dissolvedBy;
	}

	public double getMolesOfAcidToDissolve() {
		return molesOfAcidToDissolve;
	}

	public void setDissolvedBy(int dissolvedBy) {
		this.dissolvedBy = dissolvedBy;
	}

	public void setMolesOfAcidToDissolve(double molesOfAcidToDissolve) {
		this.molesOfAcidToDissolve = molesOfAcidToDissolve;
	}

}
