package datasource;

/**
 * @author Ace and Josh B.
 *
 */
public class BaseDTO {
	
	private int ID;
	private int solute;

	/**
	 * @param id
	 * @param solute
	 */
	public BaseDTO(int id, int solute) {
		this.ID = id;
		this.solute = solute;
	}

	public int getID() {
		return this.ID;
	}

	public int getSolute() {
		return this.solute;
	}

}
