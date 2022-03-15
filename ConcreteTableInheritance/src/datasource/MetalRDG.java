package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Daniel Holmgren
 * @author Joshua Kellogg The row data gateway that will allow us to access the
 *         metal table
 */
public class MetalRDG {
	private int ID, atomicNumber, dissolvedBy;
	private double atomicMass;
	private String name;
	private double moles;
	private double molesOfAcidToDissolve;
	private static Connection cn;

	/**
	 * Constructor assigns instance variables and sets up the connection that well
	 * need for the other methods in this class
	 * 
	 * @param ID
	 * @param atomicNumber
	 * @param dissolvedBy
	 * @param atomicMass
	 * @param name
	 * @param moles
	 * @param molesOfAcidToDissolve
	 */
	public MetalRDG(int ID, int atomicNumber, int dissolvedBy, double atomicMass, String name, double moles,
			double molesOfAcidToDissolve) {
		this.ID = ID;
		this.atomicNumber = atomicNumber;
		this.dissolvedBy = dissolvedBy;
		this.atomicMass = atomicMass;
		this.name = name;
		this.moles = moles;
		this.molesOfAcidToDissolve = molesOfAcidToDissolve;

		try {
			DatabaseManager db = DatabaseManager.getSingleton();
			cn = db.getConnection();
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
	}

	/**
	 * @author Dan Holmgren
	 * @author Josh Kellogg
	 * @param ID The ID to search the database for
	 * @return A metalRDG with the correct Metal
	 * @throws DatabaseException 
	 */
	public static MetalRDG findByID(int ID) throws DatabaseException {
		cn = DatabaseManager.getSingleton().getConnection();
		MetalRDG results = null;
		try {
			ResultSet rs = cn.createStatement().executeQuery("SELECT * FROM Metal WHERE elementOrMetalID = " + ID);
			if (rs.next()) {
				results = new MetalRDG(rs.getInt("elementOrMetalID"), rs.getInt("metalAtomicNumber"),
						rs.getInt("metalDissolvedBy"), rs.getDouble("metalAtomicMass"), rs.getString("metalName"),
						rs.getDouble("metalMoles"), rs.getDouble("molesOfAcidToDissolve"));
			} else {
				System.out.println("Metal with this ID does not exist.");
			}
		} catch (SQLException s) {
			return results;
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
		return results;
	}

	/**
	 * Given the name of a metal will return the information from the database about
	 * that metal
	 * 
	 * @param name
	 * @return A metalRDG with information from the database corresponding to the
	 *         given name
	 * @throws DatabaseException
	 */
	public static MetalRDG findByName(String name) throws DatabaseException {
		MetalRDG results = null;
		cn = DatabaseManager.getSingleton().getConnection();
		try {
			ResultSet rs = cn.createStatement().executeQuery("SELECT * FROM Metal WHERE metalName = '" + name + "'");
			if (rs.next()) {
				results = new MetalRDG(rs.getInt("elementOrMetalID"), rs.getInt("metalAtomicNumber"),
						rs.getInt("metalDissolvedBy"), rs.getDouble("metalAtomicMass"), rs.getString("metalName"),
						rs.getDouble("metalMoles"), rs.getDouble("molesOfAcidToDissolve"));
			}
		} catch (SQLException e) {
			DatabaseException.detectError(e);
		}
		return results;
	}

	/**
	 * Method to see if a Chemical with a given name is a Metal
	 * 
	 * @author Madeline and Adam
	 * @param name the name of the Chemical
	 * @return Metal if it is a Metal, null if it is not
	 */
	public static String findTypeByName(String name) {
		Connection c;
		try {
			c = DatabaseManager.getSingleton().getConnection();
			ResultSet rs = c.createStatement()
					.executeQuery("SELECT * FROM Metal WHERE Metal.MetalName = '" + name + "'");
			if (rs.next()) {
				return "Metal";
			}
		} catch (SQLException | DatabaseException e) {
			DatabaseException.detectError(e);
		}
		return null;
	}

	/**
	 * Will update the database with the current values of the instance variables
	 */
	public void update() {
		PreparedStatement stmt;
		try {
			stmt = cn.prepareStatement(
					"UPDATE Metal SET metalName = ?, metalAtomicNumber = ?, metalAtomicMass = ?, metalDissolvedBy = ?, metalMoles = ?, molesOfAcidToDissolve = ? WHERE elementOrMetalID = ?");
			stmt.setString(1, name);
			stmt.setInt(2, atomicNumber);
			stmt.setDouble(3, atomicMass);
			stmt.setDouble(4, dissolvedBy);
			stmt.setDouble(5, moles);
			stmt.setDouble(6, molesOfAcidToDissolve);
			stmt.setInt(7, ID);
			stmt.execute();
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
	}

	public void insert() {

		PreparedStatement stmt;
		try {
			stmt = DatabaseManager.getSingleton().getConnection()
					.prepareStatement("INSERT INTO Metal VALUES (?, ?, ?, ?, ?, ?, ?)");

			stmt.setInt(1, ID);
			stmt.setString(2, name);
			stmt.setInt(3, atomicNumber);
			stmt.setDouble(4, atomicMass);
			stmt.setInt(5, dissolvedBy);
			stmt.setDouble(6, moles);
			stmt.setDouble(7, molesOfAcidToDissolve);

			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			DatabaseException.detectError(e, "exception in ElementRDG.insert()");
		}
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

	public int getDissolvedBy() {
		return dissolvedBy;
	}

	public void setDissolvedBy(int dissolvedBy) {
		this.dissolvedBy = dissolvedBy;
	}

	public double getAtomicMass() {
		return atomicMass;
	}

	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMoles() {
		return moles;
	}

	public void setMoles(double moles) {
		this.moles = moles;
	}

	public double getMolesOfAcidToDissolve() {
		return molesOfAcidToDissolve;
	}

	public void setMolesOfAcidToDissolve(double molesOfAcidToDissolve) {
		this.molesOfAcidToDissolve = molesOfAcidToDissolve;
	}

}