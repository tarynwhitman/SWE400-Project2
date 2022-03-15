package Interfaces;

import java.util.ArrayList;

import domainObjects.ElementDomainObject;

public interface ElementMapperInterface {

	public void persist();

	public ElementDomainObject createElement(int ID, String elementName, int atomicNumber, double atomicMass,
			double moles) throws Exception;
	ElementDomainObject findByID(int id) throws Exception;

	ElementDomainObject findByName(String name) throws Exception;

	ElementDomainObject findByAtomicNumber(int aNum) throws Exception;

	ElementDomainObject findByAtomicMass(double aMass) throws Exception;

	ArrayList<ElementDomainObject> findElementsInRange(double highRange, double lowRange) throws Exception;

	ArrayList<ElementDomainObject> getAllElements() throws Exception;

}