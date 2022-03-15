package Interfaces;

import java.util.ArrayList;

import datasource.DatabaseException;
import domainObjects.ChemicalDomainObject;

public interface ChemicalMapperInterface {
	
	public ChemicalDomainObject createChemical(int ID, String name, double moles) throws Exception;
	public void persist() throws DatabaseException;
	public ChemicalDomainObject findByID(int chemicalID) throws Exception;
	public ArrayList<ChemicalDomainObject> findLowChemicals() throws Exception;

	public int getID();
	public String getName();
	public double getMoles();
	public ChemicalDomainObject getCdo();
	public void setCdo(ChemicalDomainObject cdo);
	ChemicalDomainObject findByName(String name) throws Exception;

}