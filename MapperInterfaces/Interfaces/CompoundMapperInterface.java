package Interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import datasource.DatabaseException;
import domainObjects.CompoundDomainObject;

public interface CompoundMapperInterface {

	public CompoundDomainObject findByID(int cID) throws Exception;

	public void persist();

	ArrayList<CompoundDomainObject> getAllCompounds() throws Exception;

	ArrayList<CompoundDomainObject> getCompoundsByElement(int elementID) throws Exception;

	void compareElementsAndPersist() throws SQLException, DatabaseException;

}