package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Singleton that handles connections to the database
 * @author Merlin
 * @author Joshua
 * @author Ace
 */
public class DatabaseManager
{
	private static DatabaseManager singleton;
	private HashMap<Long, Connection> connections;
	private static int patternNumber;

	/**
	 * @return the only one
	 * @throws DatabaseException if we are unable to connect to the db
	 */
	public static DatabaseManager getSingleton() throws DatabaseException
	{
		try {
			if (singleton == null)
			{
				singleton = new DatabaseManager();
			}	
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}	
		return singleton;
	}
	
	/**
	 * Method allows for a convenient way to execute a statement
	 * @param sqlString
	 * @throws SQLException
	 */
	public ResultSet executeStatement(String sqlString) {
		try {
			Statement stmt = getConnection().createStatement();
			stmt.execute(sqlString);
			return stmt.getResultSet();

		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
		return null;	

	}

	private boolean testing;

	/**
	 * Private constructor.
	 */
	private DatabaseManager()
	{
		try {
			connections = new HashMap<Long, Connection>();
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
	}

	/**
	 * Calls onenConnectionTo method with appropriate parameters
	 */
	public Connection openConnection()
	{
		try {
			return openConnectionTo("jdbc:mysql://db.cs.ship.edu:3306/swe400_1"+ patternNumber + "?autoReconnect=true",
					"swe400_1", "pwd4swe400_1F20");
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}
		return null;		
	}

	/**
	 * Commits changes to the database. If it is testing, it rolls them back.
	 */
	public void commit()
	{
		try
		{
			if (!testing)
			{
				getConnection().commit();
				getConnection().setSavepoint();
			} else
			{
				getConnection().rollback();
			}
		} catch (Exception e)
		{
			DatabaseException.detectError(e);
		}
	}

	/**
	 * @return the connection to the db
	 */
	public Connection getConnection()
	{
		long id = Thread.currentThread().getId();
		Connection connection = connections.get(id);

		if(connection == null)
		{
			try
			{
				addConnection(id);
				return getConnection();
			}
			catch (Exception e)
			{
				DatabaseException.detectError(e);
			}
		}

		return connection;
	}

	/**
	 * Returns a database connection by id.
	 */
	Connection getConnection(long id)
	{
		return connections.get(id);
	}

	/**
	 * @return all database connections 
	 */
	private HashMap<Long, Connection> getConnections()
	{
		return connections;
	}

	/**
	 * Adds a database connection.
	 */
	void addConnection(long id)
	{
		try
		{
			connections.put(id, openConnection());	
		} catch (Exception e)
		{
			DatabaseException.detectError(e);
		}
	}

	/**
	 * Returns a new database connection.
	 */
	private Connection openConnectionTo(String url, String username, String passwd) 
	{
		try
		{

			return DriverManager.getConnection(url, username, passwd);
																		
		} catch (Exception e)
		{
			DatabaseException.detectError(e);
		}
		return null;
	}

	/**
	 * Roll back the current transaction
	 */
	public void rollBack()
	{
		try
		{
			Statement stmt = getConnection().createStatement();
			stmt.execute("ROLLBACK");
			getConnection().setAutoCommit(true);
																		
		} catch (Exception e)
		{
			DatabaseException.detectError(e);
		}
	}

	/**
	 * When we are testing, use a different db.
	 */
	public void setTesting() 
	{
		try
		{
			startTransaction();
			testing = true;																		
		} catch (Exception e)
		{
			DatabaseException.detectError(e);
		}
	}

	/**
	 * remember a rollback point in case a series of transactions doesn't all
	 * work
	 */
	public void startTransaction()
	{
		try
		{
			getConnection().setAutoCommit(false);
			Statement stmt = getConnection().createStatement();
			stmt.execute("START TRANSACTION");
		} catch (Exception e)
		{
			DatabaseException.detectError(e);
		}
	}

	/**
	 * For testing purposes only
	 */
	public static void reset()
	{
		try
		{
			if (singleton != null)
			{
				singleton.rollBack();

				for(Connection connection: singleton.getConnections().values())
				{
					connection.close();
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		singleton = null;
	}

	/**
	 * Closes the database connection associated with id if there is one open.
	 * @param id The id for which to close a connection.
	 */
	public void closeConnection(long id) 
	{
		try
		{
			Connection connection = getConnection(id);
			if (connection != null && !connection.isClosed())
			{
				connection.close();
			}
		} catch (Exception e)
		{
			DatabaseException.detectError(e);
		}
	}

	/**
	 * Closes the database connection associated with the current thread.
	 */
	public void closeConnection() 
	{
		try
		{
			closeConnection(Thread.currentThread().getId());
		} catch (Exception e)
		{
			DatabaseException.detectError(e);
		}
	}

	/**
	 * Setter for patternNumber
	 */
	public static void setPatternNumber(int num) {
		patternNumber = num;
	}

	/**
	 * Getter for patternNumber
	 */
	public static int getPatternNumber() {
		return patternNumber;
	}

}
