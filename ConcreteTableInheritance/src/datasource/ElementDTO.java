package datasource;

/**
 * @author Daniel Holmgren
 * @author Joshua Kellogg
 * A data transfer object to pass around information about elements
 */
public class ElementDTO {
	private int ID,
				atomicNumber;
	private double atomicMass;
	private String name;
	private double moles;
	
	/**
	 * Assigns instance variables
	 * @param ID
	 * @param atomicNumber
	 * @param atomicMass
	 * @param moles
	 * @param name
	 */
	public ElementDTO(int ID, int atomicNumber, double atomicMass, String name, double moles) {
		this.ID = ID;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		this.name = name;
		this.moles = moles;
	}
	
	// Getters
	public int getID() {
		return ID;
	}
	
	public int getAtomicNumber() {
		return atomicNumber;
	}
	
	public double getAtomicMass() {
		return atomicMass;
	}
	
	public String getName() {
		return name;
	}
	
	public double getMoles() {
		return moles;
	}

}
