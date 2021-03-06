package datasource;

/**
 * @author Merlin
 */
public class DatabaseException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String simpleDescription;

	private Exception rootCause;

	/**
	 * 
	 * @param msg the message associated with this exception
	 */
	public DatabaseException(String msg)
	{
		simpleDescription = msg;
	}

	/**
	 * @param msg description of complication
	 * @param e exception being thrown
	 */
	public DatabaseException(String msg, Exception e)
	{
		simpleDescription = msg;
		rootCause = e;
	}

	/**
	 * 
	 * @return the original exception, if any, that occurred
	 */
	public Exception getRootCause()
	{
		return rootCause;
	}

	/**
	 * @return simple Description
	 */
	public String getSimpleDescription()
	{
		return simpleDescription;
	}

	/**
	 * @see java.lang.Throwable#toString()
	 */
	public String toString()
	{
		return simpleDescription + ": " + rootCause;
	}
	
	/**
	 * Prints some information about an Exception.
	 * @author mad
	 * @param e
	 */
	public static void detectError(Exception e)
	{	
		DatabaseException d = new DatabaseException("A problem ensued; please investigate the error in your ways", e);
		System.out.println(d.toString());
		e.printStackTrace();
	}
	
	/**
	 * Prints some information about an Exception as well as a string 
	 * that can be passed by whoever is calling it.
	 * @param e
	 * @param location
	 */
	public static void detectError(Exception e, String location)
	{	
		DatabaseException d = new DatabaseException("A problem ensued; please investigate the error in your ways. It is likely located in " + location, e);
		System.out.println(d.toString());
		e.printStackTrace();
	}

}
