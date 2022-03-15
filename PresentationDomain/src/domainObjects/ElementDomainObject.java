package domainObjects;

import mappers.CompoundMapper;
import mappers.ElementMapper;
import quantifiedElementPackage.QuantifiedElement;

/**
 * 
 * @author Marlee Lackey
 * @author Taryn Whitman
 *
 */
public class ElementDomainObject {

	/**
	 * Instance variables
	 */
	private ElementMapper dataMapper;
	private int elementID;
	private String elementName;
	private int elementAtomicNumber;
	private double elementAtomicMass;
	private double elementMoles;

	/**
	 * Constructor
	 * 
	 * @param em the data mapper that created this ElementDomainObject
	 * @throws Exception throws an exception if one of the setter's throw an
	 *                   exception
	 */
	public ElementDomainObject(ElementMapper em) throws Exception {
		dataMapper = em;
		setElementID(dataMapper.getIdent());
		setElementName(dataMapper.getName());
		setElementAtomicMass(dataMapper.getAtomicMass());
		setElementAtomicNumber(dataMapper.getAtomicNumber());
		setElementMoles(dataMapper.getMoles());
		em.setElement(this);
	}
	
	/**
	 *  Adds element to the compound
	 * @param compoundID
	 * @param elementQuantity
	 * @throws Exception
	 */
	public void addToCompound(int compoundID, int elementQuantity) throws Exception {
		CompoundMapper cm = new CompoundMapper();
		CompoundDomainObject cdo = cm.findByID(compoundID);
		cdo.getElements().add(new QuantifiedElement(this, elementQuantity));
		cdo.persist();
	}

	/**
	 * Set elementID
	 * 
	 * @param ID
	 */
	public void setElementID(int ID) {
		elementID = ID;
	}

	/**
	 * Set elementName and check if the passed in elementID is allowed (check
	 * business logic)
	 * 
	 * @param name
	 * @throws Exception throws an exception if business logic is not met
	 */
	public void setElementName(String name) throws Exception {
		if (name.contains(" ")) { // if it contains a space, it has 2 words and therefore is illegal
			throw new Exception("Element name should only be one word with no spaces.");
		} else {
			elementName = name;
			if (dataMapper != null ) {
				dataMapper.setName(name);
			}
		}
	}

	/**
	 * Set elementAtomicNumber and check if the passed in elementID is allowed
	 * (check business logic)
	 * 
	 * @param atomicNumber
	 * @throws Exception throws an exception if business logic is not met
	 */
	public void setElementAtomicNumber(int atomicNumber) throws Exception {
		if (atomicNumber > elementAtomicMass) {
			throw new Exception("You can not have an atomic number that is greater than the atomic mass.");
		} else {
			elementAtomicNumber = atomicNumber;
			if (dataMapper != null ) {
				dataMapper.setAtomicNumber(atomicNumber);
			}
		}

	}

	/**
	 * Set elementAtomicMass
	 * 
	 * @param atomicMass
	 */
	public void setElementAtomicMass(double atomicMass) {
		elementAtomicMass = atomicMass;
		if (dataMapper != null ) {
			dataMapper.setAtomicMass(atomicMass);
		}
	}

	/**
	 * Set elementMoles
	 * 
	 * @param elementMoles
	 */
	public void setElementMoles(double elementMoles) {
		this.elementMoles = elementMoles;
		if (dataMapper != null ) {
			dataMapper.setMoles(elementMoles);
		}
	}

	/**
	 * Call the persist() method in DataMapper to persist the changes made to
	 * element
	 */
	public void persist() {
		dataMapper.persist();
	}

	/**
	 * Getter for elementID
	 * 
	 * @return elementID
	 */
	public int getElementID() {
		return elementID;
	}

	/**
	 * Getter for elementName
	 * 
	 * @return elementName
	 */
	public String getElementName() {
		return elementName;
	}

	/**
	 * Getter for elementAtomicMass
	 * 
	 * @return elementAtomicMass
	 */
	public double getElementAtomicMass() {
		return elementAtomicMass;
	}

	/**
	 * Getter for elementAtomicNumber
	 * 
	 * @return elementAtomicNumber
	 */
	public int getElementAtomicNumber() {
		return elementAtomicNumber;
	}

	/**
	 * Getter for elementMoles
	 * 
	 * @return elementMoles
	 */
	public double getElementMoles() {
		return elementMoles;
	}

	/**
	 * Return the data mapper that created the domain object
	 * 
	 * @return dataMapper
	 */
	public ElementMapper getDataMapper() {
		return dataMapper;
	}

	public String toString() {
		return ("Element: " + elementName + ", atomic mass " + elementAtomicMass + ", atomic number "
				+ elementAtomicNumber + ", " + elementMoles + " moles in inventory");
		// Element: [name], atomic mass [mass], atomic number [num], [mol] moles in
		// inventory
	}

}
