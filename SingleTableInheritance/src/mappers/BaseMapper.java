/**
 * 
 */
package mappers;

import java.util.ArrayList;

import Interfaces.BaseMapperInterface;
import datasource.ChemicalDTO;
import datasource.ChemicalRDG;
import datasource.ChemicalTDG;
import domainObjects.BaseDomainObject;

/**
 * @author Joshua, Ace, Marlee &&&&&&&&&&&&&&&&&& mad :)
 * 
 */
public class BaseMapper implements BaseMapperInterface {

	private static final int type = 1;
	private int baseID;
	private String baseName;
	private int baseSolute;
	private double baseMoles;

	/**
	 * returns all bases in the database as an arraylist of BaseDomainObjects.
	 */
	@Override
	public ArrayList<BaseDomainObject> getAllBases() {
		ArrayList<ChemicalDTO> Adot = ChemicalTDG.getSingleton().getAllBases();
		ArrayList<BaseDomainObject> Doa = new ArrayList<>();
		for (ChemicalDTO b : Adot) {
			baseID = b.getChemicalID();
			baseName = b.getChemicalName();
			baseSolute = b.getChemicalSoluteB();
			baseMoles = b.getChemicalMoles();
			Doa.add(new BaseDomainObject(this));
		}

		return Doa;
	}

	/**
	 * uses an rdg to persist updates of a base to the database.
	 */
	@Override
	public void persist() {
		ChemicalRDG b = new ChemicalRDG(baseID, type, baseName, baseSolute, baseMoles);
		b.update();
	}

	/**
	 * getter for id.
	 */
	public int getBaseID() {
		return baseID;
	}

	/**
	 * getter for name.
	 */
	public String getBaseName() {
		return baseName;
	}

	/**
	 * getter for solute.
	 */
	public int getBaseSolute() {
		return baseSolute;
	}

	/**
	 * getter for moles.
	 */
	public double getBaseMoles() {
		return baseMoles;
	}

}