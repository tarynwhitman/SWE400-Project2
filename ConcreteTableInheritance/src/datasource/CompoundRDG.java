package datasource;

import java.sql.*;

/**
 * 
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class CompoundRDG {

	private int compoundID;
	private String compoundName;
	private double compoundMoles;
	static Connection connection;

	/**
	 * Constructor for CompoundRDG
	 * 
	 * @param id   Passes in the ID for CompoundRDG
	 * @param name Passes in the Name for CompoundRDG
	 */
	public CompoundRDG(int id, String name, double moles) {
		compoundID = id;
		compoundName = name;
		setCompoundMoles(moles);

		try {
			connection = DatabaseManager.getSingleton().getConnection();
		} catch (DatabaseException e) {
			DatabaseException.detectError(e);
		}

	}

	/**
	 * Getter for compoundID
	 * 
	 * @return compoundID
	 */
	public int getCompoundID() {
		return compoundID;
	}

	/**
	 * Getter for compoundName
	 * 
	 * @return compoundName
	 */
	public String getCompoundName() {
		return compoundName;
	}

	/**
	 * Getter for compoundMoles
	 * 
	 * @return the compoundMoles
	 */
	public double getCompoundMoles() {
		return compoundMoles;
	}

	/**
	 * Setter for compoundName
	 * 
	 * @param compoundName name of compound
	 */
	public void setCompoundName(String compoundName) {
		this.compoundName = compoundName;
	}

	/**
	 * Setter for compoundMoles
	 * 
	 * @param compoundMoles the number of moles in inventory
	 */
	public void setCompoundMoles(double compoundMoles) {
		this.compoundMoles = compoundMoles;
	}

	/**
	 * Find a compound using the ID
	 * 
	 * @param ID the id of a compound you are looking for
	 * @return CompoundRDG
	 */
	public static CompoundRDG findByIDConcrete(int ID) {
		CompoundRDG chem = null;
		try {
			connection = DatabaseManager.getSingleton().getConnection();
			ResultSet r = connection.createStatement()
					.executeQuery("SELECT * FROM Compound WHERE Compound.compoundID = " + ID);
			r.next();
			chem = new CompoundRDG(r.getInt(1), r.getString(2), r.getDouble(3));
		} catch (SQLException e) {
			return chem;
		} catch (Exception ex) {
			DatabaseException.detectError(ex);
		}

		return chem;

	}

	/**
	 * Find a chemical using the name
	 * 
	 * @param name the name of a chemical you are looking for
	 * @return ChemicalRDG
	 * @throws DatabaseException
	 */
	public static CompoundRDG findByNameConcrete(String name) throws DatabaseException {
		CompoundRDG chem = null;
		try {
			connection = DatabaseManager.getSingleton().getConnection();
			ResultSet r = connection.createStatement()
					.executeQuery("SELECT * FROM Compound WHERE Compound.compoundName = '" + name + "'");
			if (r.next()) {
				chem = new CompoundRDG(r.getInt(1), r.getString(2), r.getDouble(3));
			}
		} catch (SQLException e) {
			DatabaseException.detectError(e);
		}

		return chem;
	}

	/**
	 * Method to see if a Chemical with a given name is a Compound
	 * 
	 * @author Madeline and Adam
	 * @param name the name of the Chemical
	 * @return Compound if it is a Compound, null if it is not
	 */
	public static String findTypeByName(String name) {
		Connection c;
		try {
			c = DatabaseManager.getSingleton().getConnection();
			ResultSet rs = c.createStatement()
					.executeQuery("SELECT * FROM Compound WHERE Compound.compoundName = '" + name + "'");
			if (rs.next()) {
				return "Compound";
			}
		} catch (SQLException | DatabaseException e) {
			DatabaseException.detectError(e);
		}
		return null;
	}

	/**
	 * Updates the database with new values to an existing chemical
	 */
	public void update() {
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(
					"UPDATE Compound SET compoundName = ?, compoundMoles = ? WHERE Compound.compoundID = ?");
			stmt.setString(1, compoundName);
			stmt.setDouble(2, compoundMoles);
			stmt.setInt(3, compoundID);
			stmt.executeUpdate();

		} catch (SQLException e) {
			DatabaseException.detectError(e);
		}
	}

}
