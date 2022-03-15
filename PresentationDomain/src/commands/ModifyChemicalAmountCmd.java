package commands;

import domainObjects.ChemicalDomainObject;
import mappers.ChemicalMapper;

/**
 * Command class to modify a chemical's amount in inventory
 * 
 * @author Madeline and Adam
 *
 */
public class ModifyChemicalAmountCmd implements CommandInterface {

	private int chemicalID;
	private double newChemicalAmount;

	/**
	 * Constructor
	 * 
	 * @param id       the ID of the chemical we want to modify
	 * @param newMoles the new number of moles of the chemical in inventory
	 */
	public ModifyChemicalAmountCmd(int id, double newMoles) {
		chemicalID = id;
		newChemicalAmount = newMoles;
	}

	/**
	 * Execute method to invoke the ModifyChemicalAmountCmd
	 * 
	 * @throws Exception
	 */
	@Override
	public void execute() throws Exception {
		ChemicalMapper cm = new ChemicalMapper();
		ChemicalDomainObject cdo = cm.findByID(chemicalID);
		cdo.setChemicalMoles(newChemicalAmount);
		cdo.persist();
	}

	/**
	 * @return the chemicalID
	 */
	public int getChemicalID() {
		return chemicalID;
	}

	/**
	 * @return the newChemicalAmount
	 */
	public double getNewChemicalAmount() {
		return newChemicalAmount;
	}

}