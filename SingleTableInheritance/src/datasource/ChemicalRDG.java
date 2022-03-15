package datasource;

import java.sql.*;

/**
 * @author Taryn Whitman
 * @author Marlee Lackey
 */
public class ChemicalRDG {
	private int ID;
	private int type;
	private String name;
	private int atomicNumber;
	private double atomicMass;
	private int dissolvedBy; // is the ID of the Acid it is dissolved by
	private int soluteA;
	private int soluteB;
	private double moles;
	private double molesOfAcidToDissolve;
	static Connection connection;

	/**
	 * Constructor used to make an instance of an object already created in the
	 * database
	 * 
	 * @param ID
	 * @throws DatabaseException
	 */
	public ChemicalRDG(int ID, int type, String name, int atomicNumber, double atomicMass, int dissolvedBy, int soluteA,
			int soluteB, double moles, double molesOfAcidToDissolve) {
		this.ID = ID;
		this.type = type;
		this.name = name;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		this.dissolvedBy = dissolvedBy;
		this.soluteA = soluteA;
		this.soluteB = soluteB;
		this.moles = moles;
		this.molesOfAcidToDissolve = molesOfAcidToDissolve;

		// connection to the DB
		try {
			connection = DatabaseManager.getSingleton().getConnection();
		} catch (DatabaseException e) {
			DatabaseException.detectError(e, "ChemicalRDG - single");
		}
	}

	/**
	 * constructor for acid or base
	 * 
	 * @param ID
	 * @param type
	 * @param name
	 * @param solute
	 * @param moles
	 */
	public ChemicalRDG(int ID, int type, String name, int solute, double moles) {
		this.ID = ID;
		this.type = type;
		this.name = name;
		if (type == 1) {
			this.soluteB = solute;
		} else if (type == 2) {
			this.soluteA = solute;
		}
		this.moles = moles;

		// connection to the DB
		try {
			connection = DatabaseManager.getSingleton().getConnection();
		} catch (DatabaseException e) {
			DatabaseException.detectError(e, "ChemicalRDG - single");
		}
	}

	/**
	 * constructor for metal
	 * 
	 * @param ID
	 * @param type
	 * @param atomicNumber
	 * @param dissolvedBy
	 * @param atomicMass
	 * @param name
	 * @param moles
	 * @param molesOfAcidToDissolve
	 */
	public ChemicalRDG(int ID, int type, String name, int atomicNumber, double atomicMass, int dissolvedBy,
			double moles, double molesOfAcidToDissolve) {
		this.ID = ID;
		this.type = type;
		this.name = name;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		this.dissolvedBy = dissolvedBy;
		this.moles = moles;
		this.molesOfAcidToDissolve = molesOfAcidToDissolve;

		// connection to the DB
		try {
			connection = DatabaseManager.getSingleton().getConnection();
		} catch (DatabaseException e) {
			DatabaseException.detectError(e, "ChemicalRDG - single");
		}
	}

	/**
	 * constructor for element
	 * 
	 * @param ID
	 * @param type
	 * @param name
	 * @param atomicNumber
	 * @param atomicMass
	 * @param moles
	 */
	public ChemicalRDG(int ID, int type, String name, int atomicNumber, double atomicMass, double moles) {
		this.ID = ID;
		this.type = type;
		this.name = name;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		this.moles = moles;

		// connection to the DB
		try {
			connection = DatabaseManager.getSingleton().getConnection();
		} catch (DatabaseException e) {
			DatabaseException.detectError(e, "ChemicalRDG - single");
		}
	}

	/**
	 * constructor for compound
	 * 
	 * @param ID
	 * @param type
	 * @param name
	 * @param moles
	 */
	public ChemicalRDG(int ID, int type, String name, double moles) {
		this.ID = ID;
		this.type = type;
		this.name = name;
		this.moles = moles;

		// connection to the DB
		try {
			connection = DatabaseManager.getSingleton().getConnection();
		} catch (DatabaseException e) {
			DatabaseException.detectError(e, "ChemicalRDG - single");
		}
	}

	/**
	 * Setter for id
	 * 
	 * @param id the new value of ID you want to set
	 */
	public void setID(int id) {
		ID = id;
	}

	/**
	 * Setter for type
	 * 
	 * @param type the new value of type you want to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Setter for name
	 * 
	 * @param name the new value of name you want to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Setter for atomicNumber
	 * 
	 * @param atomicNumber the new value of atomicNumber you want to set
	 */
	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	/**
	 * Setter for atomicMass
	 * 
	 * @param atomicMass the new value of atomicMass you want to set
	 */
	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}

	/**
	 * Setter for dissolvedBY
	 * 
	 * @param dissolvedBy the new value of dissolvedBy you want to set
	 */
	public void setDissolvedBy(int dissolvedBy) {
		this.dissolvedBy = dissolvedBy;
	}

	/**
	 * Setter for soluteA
	 * 
	 * @param soluteA the new value of soluteA you want to set
	 */
	public void setSoluteA(int soluteA) {
		this.soluteA = soluteA;
	}

	/**
	 * Setter for soluteB
	 * 
	 * @param soluteB the new value of soluteB you want to set
	 */
	public void setSoluteB(int soluteB) {
		this.soluteB = soluteB;
	}

	/**
	 * Setter for moles
	 * 
	 * @param newMoles the new number of moles
	 */
	public void setMoles(double newMoles) {
		moles = newMoles;
	}

	public void setMolesOfAcidToDissolve(double molesOfAcidToDissolve) {
		this.molesOfAcidToDissolve = molesOfAcidToDissolve;
	}

	/**
	 * Getter for ID
	 * 
	 * @return ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Getter for type
	 * 
	 * @return type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Getter for name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for atomicNumber
	 * 
	 * @return atomicNumber
	 */
	public int getAtomicNumber() {
		return atomicNumber;
	}

	/**
	 * Getter for atomicMass
	 * 
	 * @return atomicMass
	 */
	public double getAtomicMass() {
		return atomicMass;
	}

	/**
	 * Getter for dissolvedBy
	 * 
	 * @return dissolvedBy
	 */
	public int getDissolvedBy() {
		return dissolvedBy;
	}

	/**
	 * Getter for soluteA
	 * 
	 * @return soluteA
	 */
	public int getSoluteA() {
		return soluteA;
	}

	/**
	 * Getter for soluteB
	 * 
	 * @return soluteB
	 */
	public int getSoluteB() {
		return soluteB;
	}

	/**
	 * Getter for moles
	 * 
	 * @return moles
	 */
	public double getMoles() {
		return moles;
	}

	public double getMolesOfAcidToDissolve() {
		return molesOfAcidToDissolve;
	}

	/**
	 * Find a chemical using the ID
	 * 
	 * @param ID the id of a chemical you are looking for
	 * @return ChemicalRDG
	 * @throws DatabaseException
	 */
	public static ChemicalRDG findByIDSingle(int ID) throws DatabaseException {
		ChemicalRDG chem = null;
		try {
			connection = DatabaseManager.getSingleton().getConnection();
			ResultSet r = connection.createStatement()
					.executeQuery("SELECT * FROM Chemical WHERE Chemical.chemicalID = " + ID);
			if (r.next()) {
				chem = new ChemicalRDG(r.getInt(1), r.getInt(2), r.getString(3), r.getInt(4), r.getDouble(5),
						r.getInt(6), r.getInt(7), r.getInt(8), r.getDouble(9), r.getDouble(10));
			}
		} catch (SQLException e) {
			DatabaseException.detectError(e);
		}

		return chem;
	}

	/**
	 * Find a chemical using the name
	 * 
	 * @param name the name of a chemical you are looking for
	 * @return ChemicalRDG
	 */
	public static ChemicalRDG findByName(String name) {
		ChemicalRDG chem = null;
		try {
			connection = DatabaseManager.getSingleton().getConnection();
			ResultSet r = connection.createStatement()
					.executeQuery("SELECT * FROM Chemical WHERE Chemical.chemicalName = '" + name + "'");
			r.next();
			chem = new ChemicalRDG(r.getInt(1), r.getInt(2), r.getString(3), r.getInt(4), r.getDouble(5), r.getInt(6),
					r.getInt(7), r.getInt(8), r.getDouble(9), r.getDouble(10));
		} catch (Exception e) {
			DatabaseException.detectError(e);
		}

		return chem;

	}

	/**
	 * Find the type of a chemical using the name
	 * 
	 * @param name the name of a chemical you are looking for
	 * @return typeString
	 */
	public static String findTypeByName(String name) {
		ChemicalRDG chem = null;
		try {
			ResultSet r = connection.createStatement()
					.executeQuery("SELECT * FROM Chemical WHERE Chemical.chemicalName = '" + name + "'");
			r.next();
			chem = new ChemicalRDG(r.getInt(1), r.getInt(2), r.getString(3), r.getInt(4), r.getDouble(5), r.getInt(6),
					r.getInt(7), r.getInt(8), r.getDouble(9), r.getDouble(10));
		} catch (SQLException e) {
			DatabaseException.detectError(e);
		}

		String typeString = "";

		switch (chem.getType()) {
		case (0):
			typeString = "Chemical";
			break;
		case (1):
			typeString = "Base";
			break;
		case (2):
			typeString = "Acid";
			break;
		case (3):
			typeString = "Element";
			break;
		case (4):
			typeString = "Metal";
			break;
		case (5):
			typeString = "Compound";
			break;
		default:
			System.out.println("Error: Type not found");
		}

		return typeString;
	}

	/**
	 * Find a chemical using the atomicNumber
	 * 
	 * @param atomicNumber the atomicNumber of a chemical you are looking for
	 * @return ChemicalRDG
	 */
	public static ChemicalRDG findByAtomicNumber(int atomicNumber) {
		ChemicalRDG chem = null;
		try {
			ResultSet r = connection.createStatement()
					.executeQuery("SELECT * FROM Chemical WHERE Chemical.chemicalAtomicNumber = " + atomicNumber);
			r.next();
			chem = new ChemicalRDG(r.getInt(1), r.getInt(2), r.getString(3), r.getInt(4), r.getDouble(5), r.getInt(6),
					r.getInt(7), r.getInt(8), r.getDouble(9), r.getDouble(10));
		} catch (SQLException e) {
			DatabaseException.detectError(e);
		}

		return chem;
	}

	/**
	 * Find a chemical using the atomicMass
	 * 
	 * @param atomicMass the atomicMass of a chemical you are looking for
	 * @return ChemicalRDG
	 */
	public static ChemicalRDG findByAtomicMass(double atomicMass) {
		ChemicalRDG chem = null;
		try {
			ResultSet r = connection.createStatement()
					.executeQuery("SELECT * FROM Chemical WHERE Chemical.chemicalAtomicMass = " + atomicMass);
			r.next();
			chem = new ChemicalRDG(r.getInt(1), r.getInt(2), r.getString(3), r.getInt(4), r.getDouble(5), r.getInt(6),
					r.getInt(7), r.getInt(8), r.getDouble(9), r.getDouble(10));
		} catch (SQLException e) {
			DatabaseException.detectError(e);
		}

		return chem;

	}

	public void insert() {
		PreparedStatement stmt;
		try {
			switch (type) {
			case 1:
				// Base
				stmt = connection
						.prepareStatement("INSERT INTO Chemical VALUES (?, ?, ?, null, null, null, null, ?, ?, null)");
				stmt.setInt(1, ID);
				stmt.setInt(2, type);
				stmt.setString(3, name);
				stmt.setInt(4, soluteB);
				stmt.setDouble(5, moles);

				stmt.executeUpdate();
				break;
			case 2:
				// Acid
				stmt = connection
						.prepareStatement("INSERT INTO Chemical VALUES (?, ?, ?, null, null, null, ?, null, ?, null)");
				stmt.setInt(1, ID);
				stmt.setInt(2, type);
				stmt.setString(3, name);
				stmt.setInt(4, soluteA);
				stmt.setDouble(5, moles);

				stmt.executeUpdate();
				break;
			case 3:
				// Element
				stmt = connection
						.prepareStatement("INSERT INTO Chemical VALUES (?, ?, ?, ?, ?, null, null, null, ?, null)");
				stmt.setInt(1, ID);
				stmt.setInt(2, type);
				stmt.setString(3, name);
				stmt.setInt(4, atomicNumber);
				stmt.setDouble(5, atomicMass);
				stmt.setDouble(6, moles);

				stmt.executeUpdate();
				break;
			case 4:
				// Metal
				stmt = connection.prepareStatement("INSERT INTO Chemical VALUES (?, ?, ?, ?, ?, ?, null, null, ?, ?)");
				stmt.setInt(1, ID);
				stmt.setInt(2, type);
				stmt.setString(3, name);
				stmt.setInt(4, atomicNumber);
				stmt.setDouble(5, atomicMass);
				stmt.setInt(6, dissolvedBy);
				stmt.setDouble(7, moles);
				stmt.setDouble(8, molesOfAcidToDissolve);

				stmt.executeUpdate();
				break;
			case 5:
				// Compound
				stmt = connection.prepareStatement(
						"INSERT INTO Chemical VALUES (?, ?, ?, null, null, null, null, null, ?, null)");
				stmt.setInt(1, ID);
				stmt.setInt(2, type);
				stmt.setString(3, name);
				stmt.setInt(4, atomicNumber);
				stmt.setDouble(5, atomicMass);
				stmt.setInt(6, dissolvedBy);
				stmt.setDouble(7, moles);

				stmt.executeUpdate();
				break;
			}

		} catch (SQLException e) {
			DatabaseException.detectError(e);

		}

	}

	/**
	 * Updates the database with new values to an existing chemical
	 */
	public void update() {
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(
					"UPDATE Chemical SET chemicalName = ?, chemicalType = ?, chemicalAtomicNumber = ?,"
							+ "chemicalAtomicMass = ?, chemicalDissolvedBy = ?, chemicalBaseSolute = ?, chemicalAcidSolute = ?, chemicalMoles = ?, molesOfAcidToDissolve = ? WHERE Chemical.chemicalID = ?");
			stmt.setString(1, name);
			stmt.setInt(2, type);
			stmt.setInt(3, atomicNumber);
			stmt.setDouble(4, atomicMass);
			stmt.setInt(5, dissolvedBy);
			stmt.setInt(6, soluteB);
			stmt.setInt(7, soluteA);
			stmt.setDouble(8, moles);
			stmt.setDouble(9, molesOfAcidToDissolve);
			stmt.setInt(10, ID);

			stmt.executeUpdate();

		} catch (SQLException e) {
			DatabaseException.detectError(e);

		}

	}

}
