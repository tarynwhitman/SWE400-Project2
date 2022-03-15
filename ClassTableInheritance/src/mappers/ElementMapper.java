package mappers;

import java.util.ArrayList;

import Interfaces.ElementMapperInterface;
import datasource.ChemicalRDG;
import datasource.DatabaseException;
import datasource.ElementDTO;
import datasource.ElementRDG;
import datasource.ElementTDG;
import domainObjects.ElementDomainObject;

/**
 * 
 * @author Mad&Ad
 *
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
		ChemicalRDG chem = ChemicalRDG.findByID(id);
		return createElement(element.getID(), chem.getChemicalName(), element.getAtomicNumber(),
				element.getAtomicMass(), chem.getChemicalMoles());
	}

	/**
	 * returns the element that has the name that is passed in.
	 */
	@Override
	public ElementDomainObject findByName(String name) throws Exception {
		ChemicalRDG chem = ChemicalRDG.findByName(name);
		ElementRDG element = ElementRDG.findByID(chem.getChemicalID());
		return createElement(element.getID(), chem.getChemicalName(), element.getAtomicNumber(),
				element.getAtomicMass(), chem.getChemicalMoles());
	}

	/**
	 * returns the element that has the atomic number that is passed in.
	 */
	@Override
	public ElementDomainObject findByAtomicNumber(int aNum) throws Exception {
		ElementRDG element = ElementRDG.findByAtomicNumber(aNum);
		ChemicalRDG chem = ChemicalRDG.findByID(element.getID());
		return createElement(element.getID(), chem.getChemicalName(), element.getAtomicNumber(),
				element.getAtomicMass(), chem.getChemicalMoles());
	}

	/**
	 * returns the element that has the atomic mass that is passed in.
	 */
	@Override
	public ElementDomainObject findByAtomicMass(double aMass) throws Exception {
		ElementRDG element = ElementRDG.findByAtomicMass(aMass);
		ChemicalRDG chem = ChemicalRDG.findByID(element.getID());
		return createElement(element.getID(), chem.getChemicalName(), element.getAtomicNumber(),
				element.getAtomicMass(), chem.getChemicalMoles());
	}

	/**
	 * returns the elements that have atomic masses in the specified range.
	 */
	@Override
	public ArrayList<ElementDomainObject> findElementsInRange(double highRange, double lowRange) throws Exception {
		ElementTDG et = ElementTDG.getInstance();
		ArrayList<ElementDTO> edto = et.getElementInRange(lowRange, highRange);
		ArrayList<ElementDomainObject> edo = new ArrayList<ElementDomainObject>();
		for (ElementDTO e : edto) {
			ChemicalRDG chem = ChemicalRDG.findByID(e.getID());
			edo.add(createElement(e.getID(), chem.getChemicalName(), e.getAtomicNumber(), e.getAtomicMass(),
					chem.getChemicalMoles()));
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
			ChemicalRDG chem = ChemicalRDG.findByID(e.getID());
			list.add(createElement(e.getID(), chem.getChemicalName(), e.getAtomicNumber(), e.getAtomicMass(),
					chem.getChemicalMoles()));
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
			ChemicalRDG chem = ChemicalRDG.findByID(ident);
			if (element == null && chem == null) {
				ElementRDG e_rdg = new ElementRDG(ident, atomicNumber, atomicMass);
				ChemicalRDG c_rdg = new ChemicalRDG(ident, name, moles);
				e_rdg.insert();
				c_rdg.insert();
			} else {
				element.setAtomicMass(atomicMass);
				element.setAtomicNumber(atomicNumber);
				element.update();
				chem.setChemicalMoles(moles);
				chem.setName(name);
				chem.update();
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