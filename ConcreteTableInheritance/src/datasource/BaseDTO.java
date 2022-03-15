package datasource;

/**
 * A Data Transfer Object for the Base table
 * 
 * @author Ace Josh B.
 *
 */
public class BaseDTO {
	private int ID;
	private String name;
	private int solute;
	private double moles;

	public BaseDTO(int id, String nme, int slte, double moles) {
		this.ID = id;
		name = nme;
		solute = slte;
		this.moles = moles;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public int getSolute() {
		return solute;
	}

	public double getMoles() {
		return moles;
	}

}