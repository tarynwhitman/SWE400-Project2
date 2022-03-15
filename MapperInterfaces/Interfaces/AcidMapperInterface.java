/**
 * 
 */
package Interfaces;

import java.util.ArrayList;

import domainObjects.AcidDomainObject;
import domainObjects.MetalDomainObject;

/**
 * @author Josh B., Ace
 *
 */
public interface AcidMapperInterface {
	
	ArrayList<MetalDomainObject> getChemicalsDissolvedByAcid(int id) throws Exception;

	AcidDomainObject createAcid(int ID, String name, double moles, int solute) throws Exception;

	ArrayList<AcidDomainObject> getAllAcids() throws Exception;

	AcidDomainObject findByName(String acidName) throws Exception;

	void persist();

}