package commands;

import java.util.ArrayList;

import domainObjects.AcidDomainObject;
import mappers.AcidMapper;

/**
 * Command to get all the acids in the database
 * 
 * @author Mad&Ad
 *
 */
public class GetAllAcidsCmd implements CommandInterface {

	private ArrayList<AcidDomainObject> acids;

	/**
	 * Execute method to invoke the get all acids command
	 */
	@Override
	public void execute() throws Exception {
		AcidMapper am = new AcidMapper();
		acids = am.getAllAcids();
	}

	/**
	 * @return the list of acids
	 */
	public ArrayList<AcidDomainObject> getAcids() {
		return acids;
	}

}