package mappers;

import java.util.ArrayList;

import domainObjects.ElementDomainObject;
import Interfaces.ElementMapperInterface;
import datasource.ChemicalDTO;
import datasource.ChemicalRDG;
import datasource.ChemicalTDG;
import datasource.DatabaseException;
/**
 * @author Josh B. Mad C.
 * ElementMapper for Concrete Table Inheritance
 */
public class ElementMapper implements ElementMapperInterface {
	private static final int type = 3;
	private int ident;
	private String name;
	private int atomicNumber;
	private double atomicMass;
	private double moles;
	private ElementDomainObject edo;

//	public ElementMapper(int ID, String elementName, int atomicNumber, double atomicMass, double moles) {
//		ident = ID;
//		this.name = elementName;
//		this.atomicNumber = atomicNumber;
//		this.atomicMass = atomicMass;
//		this.moles = moles;
//	}

	/**
	 * Returns an element domain object and sets its mapper to this. Does not change the database.
	 */
	@Override
	public ElementDomainObject createElement(int ID, String elementName, int atomicNumber, double atomicMass,
			double moles) throws Exception {
		ident = ID;
		this.name = elementName;
		this.atomicNumber = atomicNumber;
		this.atomicMass = atomicMass;
		this.moles = moles;
		this.edo = new ElementDomainObject(this);

		return this.edo;
//		return new ElementDomainObject(this);
	}

	/**
	 * returns the element that has the id that is passed in.
	 */
	@Override
	public ElementDomainObject findByID(int id) throws Exception {
		ChemicalRDG rdg = ChemicalRDG.findByIDSingle(id);
		return createElement(rdg.getID(), rdg.getName(), rdg.getAtomicNumber(), rdg.getAtomicMass(), rdg.getMoles());
	}

	/**
	 * returns the element that has the name that is passed in.
	 */
	@Override
	public ElementDomainObject findByName(String name) throws Exception {
		ChemicalRDG rdg = ChemicalRDG.findByName(name);
		return createElement(rdg.getID(), rdg.getName(), rdg.getAtomicNumber(), rdg.getAtomicMass(), rdg.getMoles());
	}

	/**
	 * returns the element that has the atomic number that is passed in.
	 */
	@Override
	public ElementDomainObject findByAtomicNumber(int aNum) throws Exception {
		ChemicalRDG element = ChemicalRDG.findByAtomicNumber(aNum);
		return createElement(element.getID(), element.getName(), element.getAtomicNumber(), element.getAtomicMass(),
				element.getMoles());
	}

	/**
	 * returns the element that has the atomic mass that is passed in.
	 */
	@Override
	public ElementDomainObject findByAtomicMass(double aMass) throws Exception {
		ChemicalRDG element = ChemicalRDG.findByAtomicMass(aMass);
		return createElement(element.getID(), element.getName(), element.getAtomicNumber(), element.getAtomicMass(),
				element.getMoles());
	}

	/**
	 * returns the elements that have atomic masses in the specified range.
	 */
	@Override
	public ArrayList<ElementDomainObject> findElementsInRange(double highRange, double lowRange) throws Exception {
		ChemicalTDG et = ChemicalTDG.getSingleton();
		ArrayList<ChemicalDTO> edto = et.getElementsInRange(lowRange, highRange);
		ArrayList<ElementDomainObject> edo = new ArrayList<ElementDomainObject>();
		for (ChemicalDTO e : edto) {
			edo.add(createElement(e.getChemicalID(), e.getChemicalName(), e.getChemicalAtomicNumber(),
					e.getChemicalAtomicMass(), e.getChemicalMoles()));
		}
		return edo;
	}

	/**
	 * returns all elements in the databse.
	 */
	@Override
	public ArrayList<ElementDomainObject> getAllElements() throws Exception {
		ArrayList<ChemicalDTO> elements = ChemicalTDG.getSingleton().getElementsInRange(0, 999999);
		ArrayList<ElementDomainObject> list = new ArrayList<ElementDomainObject>();
		for (ChemicalDTO e : elements) {
			list.add(createElement(e.getChemicalID(), e.getChemicalName(), e.getChemicalAtomicNumber(),
					e.getChemicalAtomicMass(), e.getChemicalMoles()));
		}
		return list;
	}

	/**
	 * uses an rdg to persist updates of an element to the database.
	 */
	@Override
	public void persist() {
		try {
			ChemicalRDG element = ChemicalRDG.findByIDSingle(ident);
			if (element == null) {
				ChemicalRDG rdg = new ChemicalRDG(ident, type, name, atomicNumber, atomicMass, moles);
				rdg.insert();
			} else {
				element.setAtomicMass(atomicMass);
				element.setAtomicNumber(atomicNumber);
				element.setMoles(moles);
				element.setName(name);
				element.update();
			}
		} catch (Exception e) {
			DatabaseException.detectError(e, "Error spotted in the ElementMapper class, Persist method");
		}
	}

	/**
	 * getter for id.
	 */
	public int getIdent() {
		return ident;
	}

	/**
	 * getter for name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for atomic number.
	 */
	public int getAtomicNumber() {
		return atomicNumber;
	}

	/**
	 * getter for atomic mass.
	 */
	public double getAtomicMass() {
		return atomicMass;
	}

	/**
	 * getter for moles.
	 */
	public double getMoles() {
		return moles;
	}

	/**
	 * getter for this mapper's element.
	 */
	public ElementDomainObject getElement() {
		return edo;
	}

	/**
	 * setter for id.
	 */
	public void setIdent(int ident) {
		this.ident = ident;
	}

	/**
	 * setter for name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * setter for atomic number.
	 */
	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	/**
	 * setter for atomic mass.
	 */
	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}

	/**
	 * setter for moles.
	 */
	public void setMoles(double moles) {
		this.moles = moles;
	}

	/**
	 * setter for element.
	 */
	public void setElement(ElementDomainObject edo) {
		this.edo = edo;
	}
}
