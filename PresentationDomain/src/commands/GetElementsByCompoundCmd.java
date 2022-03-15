package commands;

import java.util.ArrayList;

import domainObjects.CompoundDomainObject;
import mappers.CompoundMapper;
import quantifiedElementPackage.QuantifiedElement;

/**
 * command to get the elements in a compoundddddddddddddddd
 * 
 * @author maddddddddddddddd & adddddddddddddddddddd
 *
 */
public class GetElementsByCompoundCmd implements CommandInterface {

	private int compID;
	private ArrayList<QuantifiedElement> elements;

	/**
	 * construct
	 * 
	 * @param id the identificational number
	 */
	public GetElementsByCompoundCmd(int id) {
		compID = id;
	}

	/**
	 * do it
	 */
	@Override
	public void execute() throws Exception {
		CompoundMapper cm = new CompoundMapper();
		CompoundDomainObject cdo = cm.findByID(compID);
		elements = cdo.getElements();
	}

	/**
	 * @return the elements
	 */
	public ArrayList<QuantifiedElement> getElements() {
		return elements;
	}

}