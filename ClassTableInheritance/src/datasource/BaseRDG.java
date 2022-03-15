package datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Ace and Josh B.
 *
 */
public class BaseRDG {

	private int id;
	private int solute;
	
	
	public BaseRDG(int id, int solute) {
		this.id = id;
		this.solute = solute;
	}
	
	/**
	 * @param id
	 * @return The row from the Base table that corresponds with the ID submitted as a parameter
	 */
	public static BaseRDG findByID(int id) {
		Connection c;
		BaseRDG result = null;
		try {
			c = DatabaseManager.getSingleton().getConnection();
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM Base WHERE baseID = " + id);
			rs.next();
			result = new BaseRDG(rs.getInt(1), rs.getInt(2));
		} catch (Exception e) {
			DatabaseException.detectError(e, "Exception in BaseRDG.findByID()");
		}
		return result;
	}

	public int getID() {
		return this.id;
	}

	public int getSolute() {
		return this.solute;
	}

	public void setID(int id) {
		this.id = id;
	}

	public void setSolute(int s) {
		this.solute = s;		
	}

	/**
	 * Update method for the Row Data Gateway instance 
	 * using the instance variables as the replacement
	 * information 
	 * 
	 */
	public void update() {
		Connection c;
		try {
			c = DatabaseManager.getSingleton().getConnection();
			PreparedStatement s = c.prepareStatement("UPDATE Base SET baseSolute = ? WHERE baseID = ?");
			s.setInt(1, solute);
			s.setInt(2, id);
			s.execute();
		} catch (Exception e) {
			DatabaseException.detectError(e, "exception in BaseRDG.update()");
		}
		
	}


	

}
