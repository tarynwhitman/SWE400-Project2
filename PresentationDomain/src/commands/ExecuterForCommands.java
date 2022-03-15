package commands;

/**
 * Executer that executes a command passed to it
 * 
 * @author Madeline & co.
 *
 */
public class ExecuterForCommands {

	/**
	 * Constructor that takes in a command and executes it
	 * 
	 * @param c the command to execute
	 * @throws Exception hopefully does not occur
	 */
	public ExecuterForCommands(CommandInterface c) throws Exception {
		c.execute();
	}

}