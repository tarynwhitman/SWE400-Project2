package commands;

import domainObjects.ChemicalDomainObject;
import mappers.ChemicalMapper;

/**
 * Command to get the ID from a name
 * 
 * @author m&a
 *
 */
public class FindIDByNameCmd implements CommandInterface {

	private String name;
	private int ID;

	/**
	 * THE CONSTRUCTOR
	 * 
	 * @param n da name o da chem
	 */
	public FindIDByNameCmd(String n) {
		name = n;
	}

	/**
	 * Execute da command, yo
	 */
	@Override
	public void execute() throws Exception {
		ChemicalMapper cm = new ChemicalMapper();
		ChemicalDomainObject cdo = cm.findByName(name);
		ID = cdo.getChemicalID();
	}

	/**
	 * @return the id
	 */
	public int getID() {
		return ID;
	}

}