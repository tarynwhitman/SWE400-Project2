/**
 * 
 */
package commands;

import java.util.ArrayList;

import domainObjects.CompoundDomainObject;
import mappers.CompoundMapper;

/**
 * Command to get all the compounds that contain a specific element
 * 
 * @author Mad&Ad
 *
 */
public class GetCompoundsByElementCmd implements CommandInterface {

	private ArrayList<CompoundDomainObject> compounds;
	private int elementID;

	/**
	 * Constructor!
	 * 
	 * @param eID the ID of the element we're searching for
	 */
	public GetCompoundsByElementCmd(int eID) {
		elementID = eID;
	}

	/**
	 * Execute da command
	 */
	@Override
	public void execute() throws Exception {
		CompoundMapper cm = new CompoundMapper();
		compounds = cm.getCompoundsByElement(elementID);
	}

	/**
	 * @return the compound
	 */
	public ArrayList<CompoundDomainObject> getCompounds() {
		return compounds;
	}

}