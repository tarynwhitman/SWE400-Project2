package mappers;

import java.util.ArrayList;

import domainObjects.BaseDomainObject;
import Interfaces.BaseMapperInterface;
import datasource.BaseDTO;
import datasource.BaseRDG;
import datasource.BaseTDG;

/**
 * @author Joshua, Ace, Marlee
 * BaseMapper for Concrete Table Inheritance
 */
public class BaseMapper implements BaseMapperInterface {
	
	private int baseID;
	private String baseName;
	private int baseSolute;
	private double baseMoles;

	/**
	 * returns all bases in the database as an arraylist of BaseDomainObjects.
	 */
	@Override
	public ArrayList<BaseDomainObject> getAllBases() {
		ArrayList<BaseDTO> Adot = BaseTDG.getAllBases();
		ArrayList<BaseDomainObject> Doa = new ArrayList<>();
		for(BaseDTO b :Adot) {
			baseID = b.getID();
			baseName = b.getName();
			baseSolute = b.getSolute();
			baseMoles = b.getMoles();
			Doa.add(new BaseDomainObject(this));
		}
		return Doa;
	}

	/**
	 * uses an rdg to persist updates of a base to the database.
	 */
	@Override
	public void persist() {
		BaseRDG b = new BaseRDG(baseID, baseName, baseSolute, baseMoles);
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