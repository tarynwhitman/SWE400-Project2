package Interfaces;

import java.util.ArrayList;

import domainObjects.BaseDomainObject;

public interface BaseMapperInterface {
	public ArrayList<BaseDomainObject> getAllBases();

	void persist();


}