package datasource;

/**
 * 
 * @author Dan Holmgren
 * @author Josh Kellogg
 * Data Transfer Object for element
 */
public class ElementDTO {
	private int ID,
				atomicNumber;
	private double atomicMass;
	
	/**
	 * Constructor, assigns instance variables
	 * @param ID
	 * @param atomicNumber
	 * @param atomicMass
	 */
	public ElementDTO(int ID, int atomicNumber, double atomicMass) {
		this.ID = ID;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
	}

	
	//Getters and Setters
	public int getID() {
		return ID;
	}

	public int getAtomicNumber() {
		return atomicNumber;
	}

	public double getAtomicMass() {
		return atomicMass;
	}
}
