package commands;

import domainObjects.AcidDomainObject;
import mappers.AcidMapper;

/**
 * Command to get an acid's ID using its name
 * 
 * @authors Adam & Mad
 *
 */
public class GetAcidIDByNameCmd implements CommandInterface {

	private String acidName;
	private int acidID;

	/**
	 * Constructor
	 * 
	 * @param acidName the name of the acid
	 */
	public GetAcidIDByNameCmd(String acidName) {
		this.acidName = acidName;
	}

	/**
	 * Execute method to invoke get acid ID by name command
	 */
	@Override
	public void execute() throws Exception {
		AcidMapper am = new AcidMapper();
		AcidDomainObject ado = am.findByName(acidName);
		acidID = ado.getAcidID();
	}

	/**
	 * @return the acid ID
	 */
	public int getAcidID() {
		return acidID;
	}
}
