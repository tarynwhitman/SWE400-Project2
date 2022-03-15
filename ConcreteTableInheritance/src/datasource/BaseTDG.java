package datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author Josh B and Ace The Table Data Gateway Class
 */
public class BaseTDG {

	/**
	 * @return an array of the rows from the Base class
	 */
	public static ArrayList<BaseDTO> getAllBases() {
		ArrayList<BaseDTO> data = new ArrayList<BaseDTO>();
		ResultSet rs;

		try {
			Connection cn = DatabaseManager.getSingleton().getConnection();
			rs = cn.createStatement().executeQuery("SELECT * FROM Base");
			while (rs.next()) {
				data.add(new BaseDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
			}
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}

		return data;
	}

}