/**
 * 
 */
package commands;

import java.util.ArrayList;

import domainObjects.CompoundDomainObject;
import mappers.CompoundMapper;

/**
 * Command to get all the compounds
 * 
 * @author Mad&Ad
 *
 */
public class GetAllCompoundsCmd implements CommandInterface {

	private ArrayList<CompoundDomainObject> cdo;

	/**
	 * Execute the get all compounds command :D
	 */
	@Override
	public void execute() throws Exception {
		CompoundMapper cm = new CompoundMapper();
		cdo = cm.getAllCompounds();

	}

	/**
	 * @return the cdo
	 */
	public ArrayList<CompoundDomainObject> getCdo() {
		return cdo;
	}

}