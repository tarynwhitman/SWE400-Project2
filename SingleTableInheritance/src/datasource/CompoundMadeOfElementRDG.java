package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompoundMadeOfElementRDG {

	private int compoundID;
	private int elementID;
	private int elementQuantity;

	public CompoundMadeOfElementRDG(int c, int e, int q) {
		compoundID = c;
		elementID = e;
		elementQuantity = q;
	}

	/**
	 * Inserts a tuple into the CompoundMadeOfElement table
	 * 
	 * @throws SQLException
	 * @throws DatabaseException
	 */
	public void insert() throws SQLException, DatabaseException {
		Connection connection = DatabaseManager.getSingleton().getConnection();
		PreparedStatement stmt;
		stmt = connection.prepareStatement("INSERT INTO CompoundMadeOfElement VALUES (?, ?, ?)");

		stmt.setInt(1, compoundID);
		stmt.setInt(2, elementID);
		stmt.setInt(3, elementQuantity);

		stmt.execute();
	}

	public void delete() throws SQLException, DatabaseException {
		Connection connection = DatabaseManager.getSingleton().getConnection();
		PreparedStatement stmt;
		stmt = connection.prepareStatement(
				"DELETE FROM CompoundMadeOfElement WHERE compoundID = " + compoundID + " AND elementID = " + elementID);
		stmt.execute();
	}

	public void update() throws SQLException, DatabaseException {
		Connection connection = DatabaseManager.getSingleton().getConnection();
		PreparedStatement stmt;
		stmt = connection.prepareStatement("UPDATE CompoundMadeOfElement SET elementQuantity = " + elementQuantity
				+ " WHERE compoundID = " + compoundID + " AND elementID = " + elementID);
		stmt.execute();
	}

	public int getCompoundID() {
		return compoundID;
	}

	public int getElementID() {
		return elementID;
	}

	public void setCompoundID(int compoundID) {
		this.compoundID = compoundID;
	}

	public void setElementID(int elementID) {
		this.elementID = elementID;
	}

	public int getElementQuantity() {
		return elementQuantity;
	}

	public void setElementQuantity(int elementQuantity) {
		this.elementQuantity = elementQuantity;
	}

}