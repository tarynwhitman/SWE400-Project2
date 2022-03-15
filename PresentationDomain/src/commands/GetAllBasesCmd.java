package commands;

import java.util.ArrayList;

import domainObjects.BaseDomainObject;
import mappers.BaseMapper;

/**
 * Command to get all the bases
 * 
 * @author Mad(&Ad)
 *
 */
public class GetAllBasesCmd implements CommandInterface {

	private ArrayList<BaseDomainObject> bases;

	/**
	 * Execute the get all bases commanddd
	 */
	@Override
	public void execute() throws Exception {
		BaseMapper bm = new BaseMapper();
		bases = bm.getAllBases();
	}

	/**
	 * Getter for the bases list
	 * 
	 * @return da list o bases
	 */
	public ArrayList<BaseDomainObject> getBases() {
		return bases;
	}

}