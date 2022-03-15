package mappers;

import java.sql.SQLException;
import java.util.ArrayList;

import Interfaces.CompoundMapperInterface;
import datasource.ChemicalDTO;
import datasource.ChemicalRDG;
import datasource.ChemicalTDG;
import datasource.CompoundMadeOfElementDTO;
import datasource.CompoundMadeOfElementRDG;
import datasource.DatabaseException;
import domainObjects.CompoundDomainObject;
import domainObjects.ElementDomainObject;
import quantifiedElementPackage.QuantifiedElement;

/**
 * 
 * @author Josh B, Ace W.
 *
 */
public class CompoundMapper implements CompoundMapperInterface {

	private static final int type = 5;
	private int compoundID;
	private String compoundName;
	private double moles;
	private ArrayList<QuantifiedElement> myElements;
	private CompoundDomainObject cdo;

	/**
	 * returns a compound with the id passed in.
	 */
	@Override
	public CompoundDomainObject findByID(int cID) throws Exception {
		ChemicalRDG rdg = ChemicalRDG.findByIDSingle(cID);

		ArrayList<QuantifiedElement> element_list = new ArrayList<QuantifiedElement>();
		ChemicalTDG tdg = ChemicalTDG.getSingleton();
		ArrayList<CompoundMadeOfElementDTO> list = tdg.getElementsInCompound(cID);

		for (CompoundMadeOfElementDTO e : list) {
			ElementMapper em = new ElementMapper();
			ChemicalRDG el = ChemicalRDG.findByIDSingle(e.getElementID());
			ElementDomainObject edo = em.createElement(el.getID(), el.getName(), el.getAtomicNumber(),
					el.getAtomicMass(), el.getMoles());

			element_list.add(new QuantifiedElement(edo, e.getElementQuantity()));
		}

		compoundID = cID;
		compoundName = rdg.getName();
		moles = rdg.getMoles();
		myElements = element_list;
		cdo = new CompoundDomainObject(this);
		return cdo;
	}

	/**
	 * persists any changes to this compound to the database. updates its elements if 
	 * they were changed as well.
	 */
	@Override
	public void persist() {
		try {
			// persist the compound itself
			ChemicalRDG c = new ChemicalRDG(compoundID, type, compoundName, moles);
			c.update();
			// persist the relationships to its elements
			compareElementsAndPersist();
		} catch (Exception e) {
			DatabaseException.detectError(e, "Error spotted in the CompoundMapper class, persist method");
		}
	}

	/**
	 * persists any changes that were made to the relationship between this compound and 
	 * the elements that make it up.
	 */
	public void compareElementsAndPersist() throws SQLException, DatabaseException {
		// see if a relationship has been deleted from cdo
		for (QuantifiedElement e : myElements) {

			if (!cdo.getElements().contains(e)) {
				System.out.println("Removed element with ID " + e.getElement().getElementID());
				myElements.remove(e);
				CompoundMadeOfElementRDG r = new CompoundMadeOfElementRDG(compoundID, e.getElement().getElementID(),
						e.getQuantityInCompound());
				r.delete();
			}
		}
		// see if a relationship has been inserted into cdo
		for (QuantifiedElement e : cdo.getElements()) {
			if (!myElements.contains(e)) {
				System.out.println("Added element with ID " + e.getElement().getElementID());
				myElements.add(e);
				CompoundMadeOfElementRDG r = new CompoundMadeOfElementRDG(compoundID, e.getElement().getElementID(),
						e.getQuantityInCompound());
				r.insert();
			}
		}
		// see if a relationship has been modified in cdo
		for (QuantifiedElement e : myElements) {
			int currentQ = e.getQuantityInCompound();
			int domainQ = 0;

			for (QuantifiedElement de : cdo.getElements()) {
				if (de.getElement().getElementID() == e.getElement().getElementID()) {
					domainQ = de.getQuantityInCompound();
				}
			}

			if (currentQ != domainQ && domainQ != 0) {
				e.setQuantityInCompound(domainQ);
				CompoundMadeOfElementRDG r = new CompoundMadeOfElementRDG(compoundID, e.getElement().getElementID(),
						e.getQuantityInCompound());
				r.update();
			}
		}

	}

	/**
	 * getter for id.
	 */
	public int getCompoundID() {
		return compoundID;
	}

	/**
	 * getter for name.
	 */
	public String getCompoundName() {
		return compoundName;
	}

	/**
	 * getter for moles.
	 */
	public double getMoles() {
		return moles;
	}

	/**
	 * getter for this mapper's elements.
	 */
	public ArrayList<QuantifiedElement> getMyElements() {
		return myElements;
	}

	/**
	 * setter for id.
	 */
	public void setCompoundID(int compoundID) {
		this.compoundID = compoundID;
	}

	/**
	 * setter for name.
	 */
	public void setCompoundName(String compoundName) {
		this.compoundName = compoundName;
	}

	/**
	 * setter for moles.
	 */
	public void setMoles(double moles) {
		this.moles = moles;
	}

	/**
	 * setter for elements.
	 */
	public void setMyElements(ArrayList<QuantifiedElement> myElements) {
		this.myElements = myElements;
	}

	/**
	 * setter for this mapper's domain object.
	 */
	public void setCdo(CompoundDomainObject compoundDomainObject) {
		cdo = compoundDomainObject;

	}

	/**
	 * returns an arraylist of all compounds in the database.
	 */
	@Override
	public ArrayList<CompoundDomainObject> getAllCompounds() throws Exception {
		ArrayList<ChemicalDTO> cdto = ChemicalTDG.getSingleton().getAllCompounds();
		ArrayList<CompoundDomainObject> cdo = new ArrayList<CompoundDomainObject>();
		for (ChemicalDTO c : cdto) {
			compoundID = c.getChemicalID();
			compoundName = c.getChemicalName();
			moles = c.getChemicalMoles();

			ArrayList<CompoundMadeOfElementDTO> elements = ChemicalTDG.getSingleton().getElementsInCompound(compoundID);
			ArrayList<QuantifiedElement> actualElements = new ArrayList<>();
			for (CompoundMadeOfElementDTO e : elements) {
				ElementMapper em = new ElementMapper();
				ChemicalRDG el = ChemicalRDG.findByIDSingle(e.getElementID());
				ElementDomainObject actualElement = em.createElement(el.getID(), el.getName(), el.getAtomicNumber(),
						el.getAtomicMass(), el.getMoles());
				actualElements.add(new QuantifiedElement(actualElement, e.getElementQuantity()));
			}
			myElements = actualElements;

			cdo.add(new CompoundDomainObject(this));
		}
		return cdo;
	}

	/**
	 * Finds all the compounds that contain a specific element
	 * 
	 * @param elementID the ID of the element to search by
	 * @return the list of compounds
	 * @throws Exception
	 */
	@Override
	public ArrayList<CompoundDomainObject> getCompoundsByElement(int elementID) throws Exception {
		ArrayList<CompoundMadeOfElementDTO> comps = ChemicalTDG.getSingleton().getCompoundsByElement(elementID);
		ArrayList<CompoundDomainObject> list = new ArrayList<>();

		for (CompoundMadeOfElementDTO c : comps) {
			list.add(findByID(c.getCompoundID()));
		}

		return list;
	}

}