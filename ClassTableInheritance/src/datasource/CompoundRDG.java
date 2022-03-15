package datasource;

import java.sql.*;

/**
 * @author Taryn Whitman
 * @author Marlee Lackey
 *
 */
public class CompoundRDG {

	private int compoundID;
	static Connection connection;

	/**
	 * Constructor
	 * 
	 * @param id of compound
	 */
	public CompoundRDG(int id) {
		compoundID = id;

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
	 * Setter for compoundID
	 * 
	 * @param compoundID the ID of the compound you are setting
	 */
	public void setCompoundID(int compoundID) {
		this.compoundID = compoundID;
	}

	/**
	 * Return a CompoundRDG given the ID of a compound
	 * 
	 * @param ID the id of a chemical you are looking for
	 * @return CompoundRDG
	 */
	public static CompoundRDG findByIDClass(int ID) {
		CompoundRDG comp = null;
		try {
			ResultSet r = connection.createStatement()
					.executeQuery("SELECT * FROM Compound WHERE Compound.compoundID = " + ID);
			r.next();
			comp = new CompoundRDG(r.getInt(1));
		} catch (SQLException e) {
			DatabaseException.detectError(e);
		}

		return comp;

	}

}