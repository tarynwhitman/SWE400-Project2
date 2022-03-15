package datasource;

/**
 * Data Transfer Object for a Chemical
 * 
 * @author everyone
 *
 */
public class ChemicalDTO {

	private int chemicalID;
	private String chemicalName;
	private double chemicalMoles;

	/**
	 * Constructor for Chemical DTO
	 * 
	 * @param id    the ID of the chemical
	 * @param name  the name of the chemical
	 * @param moles the number of moles of the chemical in inventory
	 */
	public ChemicalDTO(int id, String name, double moles) {
		chemicalID = id;
		chemicalName = name;
		chemicalMoles = moles;
	}

	public int getChemicalID() {
		return chemicalID;
	}

	public String getChemicalName() {
		return chemicalName;
	}

	public double getChemicalMoles() {
		return chemicalMoles;
	}

}