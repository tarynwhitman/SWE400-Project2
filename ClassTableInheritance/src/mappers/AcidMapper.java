/**
 * 
 */
package mappers;

import java.util.ArrayList;

import Interfaces.AcidMapperInterface;
import datasource.AcidDTO;
import datasource.AcidRDG;
import datasource.AcidTDG;
import datasource.ChemicalRDG;
import datasource.MetalDTO;
import datasource.MetalTDG;
import domainObjects.MetalDomainObject;
import domainObjects.AcidDomainObject;

/**
 * @author Mad&Ad
 *
 */
public class AcidMapper implements AcidMapperInterface {

	private int ident;
	private String name;
	private double moles;
	private int solute;
	private AcidDomainObject acid;

	/**
	 * Returns an Acid domain object and sets its mapper to this. Does not change the database.
	 */
	@Override
	public AcidDomainObject createAcid(int ID, String name, double moles, int solute) throws Exception {
		ident = ID;
		this.name = name;
		this.moles = moles;
		this.solute = solute;

		return new AcidDomainObject(this);
	}

	/**
	 * returns an arraylist of all acids in the database as AcidDomainObjects
	 */
	@Override
	public ArrayList<AcidDomainObject> getAllAcids() throws Exception {
		ArrayList<AcidDTO> Adot = AcidTDG.getSingleton().getAllAcids();
		ArrayList<AcidDomainObject> Doa = new ArrayList<AcidDomainObject>();
		try {
			for (AcidDTO b : Adot) {
				ChemicalRDG chem = ChemicalRDG.findByID(b.getAcidID());
				Doa.add(createAcid(b.getAcidID(), chem.getChemicalName(), chem.getChemicalMoles(), b.getAcidSolute()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Doa;
	}

	/**
	 * returns an arraylist of all metals (as MetalDomainObjects) that can by an acid with
	 * the passed in id.
	 */
	@Override
	public ArrayList<MetalDomainObject> getChemicalsDissolvedByAcid(int id) throws Exception {
		ArrayList<MetalDomainObject> metals = new ArrayList<MetalDomainObject>();

		ArrayList<MetalDTO> metalIDs = MetalTDG.getSingleton().getAllMetals();
		MetalMapper metalMapper = new MetalMapper();

		for (MetalDTO d : metalIDs) {
			if (d.getID() == id) {
				metals.add(metalMapper.findByID(id));
			}
		}

		return metals;
	}

//	@Override
//	public static domainObjects.AcidDomainObject findByName(String acidName) throws Exception {
//		AcidMapper mapper = new AcidMapper();
//		AcidRDG rdg = AcidRDG.findByName(acidName);
//		
//		mapper.ident = rdg.getAcidID();
//		mapper.name = rdg.getAcidName();
//		mapper.moles = rdg.getAcidMoles();
//		mapper.ident = rdg.getAcidSolute();
//
//		return mapper.createAcid(rdg.getAcidID(), rdg.getAcidName(), rdg.getAcidMoles(), rdg.getAcidSolute());
//	}

	/**
	 * returns the acid that has the name that is passed in.
	 */
	@Override
	public AcidDomainObject findByName(String acidName) throws Exception {
		AcidMapper mapper = new AcidMapper();

		ChemicalRDG chem = ChemicalRDG.findByName(acidName);
		AcidRDG rdg = AcidRDG.findByID(chem.getChemicalID());

		mapper.ident = rdg.getAcidID();
		mapper.name = chem.getChemicalName();
		mapper.moles = chem.getChemicalMoles();
		mapper.solute = rdg.getAcidSolute();

		return mapper.createAcid(mapper.ident, mapper.name, mapper.moles, mapper.solute);
	}

	/**
	 * required from interface. not needed yet.
	 */
	@Override
	public void persist() {
		//Auto-generated method stub
		
	}

	/**
	 * getter for id
	 */
	public int getIdent() {
		return ident;
	}

	/**
	 * getter for name
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for moles
	 */
	public double getMoles() {
		return moles;
	}

	/**
	 * getter for solute
	 */
	public int getSolute() {
		return solute;
	}

	/**
	 * getter for acid
	 */
	public AcidDomainObject getAcid() {
		return acid;
	}

}
