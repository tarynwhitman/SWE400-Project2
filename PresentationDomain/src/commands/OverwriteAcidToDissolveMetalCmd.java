package commands;

import domainObjects.MetalDomainObject;
import mappers.MetalMapper;

/**
 * Command class to overwrite which acid can dissolve a particular metal
 * 
 * @author Madeline and Adam
 *
 */
public class OverwriteAcidToDissolveMetalCmd implements CommandInterface {

	private int metalID; // the metal that can be dissolved
	private int newAcidID; // the acid that can dissolve it

	/**
	 * Constructor
	 * 
	 * @param metal the ID of the metal to modify
	 * @param acid  the ID of the new acid that dissolves it
	 */
	public OverwriteAcidToDissolveMetalCmd(int metal, int acid) {
		metalID = metal;
		newAcidID = acid;
	}

	/**
	 * Execute method to invoke the overwrite acid to dissolve metal command
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception {
		MetalMapper mm = new MetalMapper();
		MetalDomainObject mdo = mm.findByID(metalID);
		mdo.setDissolvedBy(newAcidID);
		mdo.persist();
	}

	/**
	 * @return the metal ID
	 */
	public int getMetalID() {
		return metalID;
	}

	/**
	 * @return the acid ID
	 */
	public int getNewAcidID() {
		return newAcidID;
	}

}