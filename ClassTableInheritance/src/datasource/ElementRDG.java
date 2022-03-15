package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Daniel Holmgren
 * @author Joshua Kellogg
 *
 *         ElementRdg uses to access the database
 */
public class ElementRDG {
	private int ID, atomicNumber;
	private double atomicMass;
	private static Connection cn;

	/**
	 * Contructor, assigns instance variables and sets up connection for use in
	 * other methods
	 * 
	 * @param ID
	 * @param atomicNumber
	 * @param atomicMass
	 */
	public ElementRDG(int ID, int atomicNumber, double atomicMass) {
		this.ID = ID;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;

		try {
			DatabaseManager db = DatabaseManager.getSingleton();
			cn = db.getConnection();
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
	}

	/**
	 * finds the appropriate Element by its ID
	 * 
	 * @param ID
	 * @return
	 */
	public static ElementRDG findByID(int ID) {
		ElementRDG data = null;
		try {
			cn = DatabaseManager.getSingleton().getConnection();
			ResultSet rs = cn.createStatement().executeQuery("SELECT * FROM Element WHERE elementID = " + ID);
			if (rs.next()) {
				data = new ElementRDG(rs.getInt("elementID"), rs.getInt("elementAtomicNumber"),
						rs.getDouble("elementAtomicMass"));
			}
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
		return data;
	}

	/**
	 * finds the appropriate Element by its Atomic Number
	 * 
	 * @param ID
	 * @return
	 */
	public static ElementRDG findByAtomicNumber(int atomicNumber) {
		ElementRDG data = null;
		try {
			ResultSet rs = cn.createStatement()
					.executeQuery("SELECT * FROM Element WHERE elementAtomicNumber = " + atomicNumber);
			rs.next();
			data = new ElementRDG(rs.getInt("elementID"), rs.getInt("elementAtomicNumber"),
					rs.getDouble("elementAtomicMass"));
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
		return data;
	}

	/**
	 * finds the appropriate Element by its Atomic Mass
	 * 
	 * @param ID
	 * @return
	 */
	public static ElementRDG findByAtomicMass(double atomicMass) {
		ElementRDG data = null;
		try {
			ResultSet rs = cn.createStatement()
					.executeQuery("SELECT * FROM Element WHERE elementAtomicMass = " + atomicMass);
			rs.next();
			data = new ElementRDG(rs.getInt("elementID"), rs.getInt("elementAtomicNumber"),
					rs.getDouble("elementAtomicMass"));
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
		return data;
	}

	/**
	 * Updates the database with the information currently in the instance variables
	 */
	public void update() {
		PreparedStatement stmt;
		try {
			stmt = cn.prepareStatement(
					"UPDATE Element SET elementAtomicNumber = ?, elementAtomicMass = ? WHERE elementID = ?");
			stmt.setInt(1, atomicNumber);
			stmt.setDouble(2, atomicMass);
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

			stmt = connection.prepareStatement("INSERT INTO Element VALUES (?, ?, ?)");
			stmt.setInt(1, ID);
			stmt.setInt(2, atomicNumber);
			stmt.setDouble(3, atomicMass);

			stmt.executeUpdate();

		} catch (Exception e) {
			DatabaseException.detectError(e);

		}

	}

	/**
	 * Find out how many atoms of an element are in a molecule of a compound
	 * 
	 * @param eID the element ID
	 * @param cID the compound ID
	 * @return the number of atoms
	 */
	public static int findQuantityInCompound(int eID, int cID) {
		Connection c;
		int lol = 0;
		try {
			c = DatabaseManager.getSingleton().getConnection();
			ResultSet rs = c.createStatement().executeQuery(
					"SELECT * FROM CompoundMadeOfElement WHERE elementID = " + eID + " AND compoundID = " + cID);
			if (rs.next()) {
				lol = rs.getInt("elementQuantity");
			}
		} catch (SQLException | DatabaseException e) {
			DatabaseException.detectError(e);
		}
		return lol;
	}

	// Getters and Setters

	public int getID() {
		return ID;
	}

	public int getAtomicNumber() {
		return atomicNumber;
	}

	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	public double getAtomicMass() {
		return atomicMass;
	}

	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}
}
