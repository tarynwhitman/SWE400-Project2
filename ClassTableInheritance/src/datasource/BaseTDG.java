package datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Josh B and Ace
 * Base Table DataGateway for the Base table  
 */
public class BaseTDG {

	/**
	 * @return an array list of all the rows from the Base table 
	 */
	public static ArrayList<BaseDTO> getAllBases() {
		ArrayList<BaseDTO> list = new ArrayList<BaseDTO>();
		Connection c;
		
		try {
			c = DatabaseManager.getSingleton().getConnection();
			Statement stmt = c.createStatement();
			stmt.executeQuery("SELECT * FROM Base");
			ResultSet rs = stmt.getResultSet();

			while(rs.next()) {
				BaseDTO dto = new BaseDTO(rs.getInt(1), rs.getInt(2));
				list.add(dto);
			}
		} catch (Exception e) {
			DatabaseException.detectError(e, "Exception in BaseDTO.getAllBases()");
		}
		
		return list;
	}

}
