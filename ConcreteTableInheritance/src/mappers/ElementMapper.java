package mappers;

import java.util.ArrayList;

import Interfaces.ElementMapperInterface;
import datasource.DatabaseException;
import datasource.ElementDTO;
import datasource.ElementRDG;
import datasource.ElementTDG;
import domainObjects.ElementDomainObject;

/**
 * @author Josh B. , Ace W.
 * ElementMapper for Concrete Table Inheritance
 */
public class ElementMapper implements ElementMapperInterface {

	private int ident;
	private String name;
	private int atomicNumber;
	private double atomicMass;
	private double moles;
	private ElementDomainObject edo;

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

		return new ElementDomainObject(this);
	}

	/**
	 * returns the element that has the id that is passed in.
	 */
	@Override
	public ElementDomainObject findByID(int id) throws Exception {
		ElementRDG element = ElementRDG.findByID(id);
		return createElement(element.getID(), element.getName(), element.getAtomicNumber(), element.getAtomicMass(),
				element.getMoles());
	}

	/**
	 * returns the element that has the name that is passed in.
	 */
	@Override
	public ElementDomainObject findByName(String name) throws Exception {
		ElementRDG element = ElementRDG.findByName(name);
		return createElement(element.getID(), element.getName(), element.getAtomicNumber(), element.getAtomicMass(),
				element.getMoles());
	}

	/**
	 * returns the element that has the atomic number that is passed in.
	 */
	@Override
	public ElementDomainObject findByAtomicNumber(int aNum) throws Exception {
		ElementRDG element = ElementRDG.findByAtomicNumber(aNum);
		return createElement(element.getID(), element.getName(), element.getAtomicNumber(), element.getAtomicMass(),
				element.getMoles());
	}

	/**
	 * returns the element that has the atomic mass that is passed in.
	 */
	@Override
	public ElementDomainObject findByAtomicMass(double aMass) throws Exception {
		ElementRDG element = ElementRDG.findByAtomicMass(aMass);
		return createElement(element.getID(), element.getName(), element.getAtomicNumber(), element.getAtomicMass(),
				element.getMoles());
	}

	/**
	 * returns the elements that have atomic masses in the specified range.
	 */
	@Override
	public ArrayList<ElementDomainObject> findElementsInRange(double highRange, double lowRange) throws Exception {
		ElementTDG et = ElementTDG.getInstance();
		ArrayList<ElementDTO> edto = et.getElementsInRange(lowRange, highRange);
		ArrayList<ElementDomainObject> edo = new ArrayList<ElementDomainObject>();
		for (ElementDTO e : edto) {
			edo.add(createElement(e.getID(), e.getName(), e.getAtomicNumber(), e.getAtomicMass(), e.getMoles()));
		}
		return edo;
	}
	
	/**
	 * returns all elements in the databse.
	 */
	@Override
	public ArrayList<ElementDomainObject> getAllElements() throws Exception {
		ArrayList<ElementDTO> elements = ElementTDG.getInstance().getAllElements();
		ArrayList<ElementDomainObject> list = new ArrayList<>();
		for (ElementDTO e : elements) {
			list.add(createElement(e.getID(), e.getName(), e.getAtomicNumber(), e.getAtomicMass(), e.getMoles()));
		}
		return list;
	}

	/**
	 * uses an rdg to persist updates of an element to the database.
	 */
	@Override
	public void persist() {
		try {
			ElementRDG element = ElementRDG.findByID(ident);
			if (element == null) {
				ElementRDG rdg = new ElementRDG(ident, atomicNumber, atomicMass, name, moles);
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
	public void setElement(ElementDomainObject elementDomainObject) {
		edo = elementDomainObject;
	}

	/**
	 * getter for this mapper's element.
	 */
	public ElementDomainObject getElement() {
		return edo;
	}

}