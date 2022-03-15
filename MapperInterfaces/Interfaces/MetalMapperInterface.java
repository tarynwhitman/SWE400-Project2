package Interfaces;

import java.util.ArrayList;

import domainObjects.MetalDomainObject;

public interface MetalMapperInterface {
	
	public void persist();
	public MetalDomainObject createMetal(int ID, String elementName, int atomicNumber, double atomicMass, int dissolvedBy, double moles,
			double molesOfAcidToDissolve) throws Exception;
	MetalDomainObject findByID(int mID) throws Exception;
	ArrayList<MetalDomainObject> getAllMetals() throws Exception;

//	public Element findByAtomicNumber(int atomicNumber);
//	public Element findByAtomicMass(int atomicMass);
//	public Element findByID(int elementID);
//	public Element getContainingCompounds(int atomicNumber);



	
}