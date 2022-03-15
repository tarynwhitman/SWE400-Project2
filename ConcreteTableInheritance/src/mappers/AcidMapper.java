package mappers;

import java.util.ArrayList;

import Interfaces.AcidMapperInterface;
import datasource.AcidDTO;
import datasource.AcidRDG;
import datasource.AcidTDG;
import datasource.MetalDTO;
import datasource.MetalTDG;
import domainObjects.AcidDomainObject;
import domainObjects.MetalDomainObject;

/**
 * @author Josh B. , Ace W.
 * AcidMapper for Concrete Table Inheritance
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
					for(AcidDTO b : Adot) {
						Doa.add(createAcid(b.getAcidID(), b.getAcidName(), b.getAcidMoles(), b.getAcidSolute()));
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
		
		ArrayList<MetalDTO> metalIDs = MetalTDG.getInstance().getAllMetals();
		MetalMapper metalMapper = new MetalMapper();
	
		for (MetalDTO d : metalIDs) {
			if (d.getID() == id) {
				metals.add(metalMapper.findByID(id));
			}
		}
		
		return metals;
	}
	
	/**
	 * returns the acid that has the name that is passed in.
	 */
	@Override
	public AcidDomainObject findByName(String acidName) throws Exception {
		AcidMapper mapper = new AcidMapper();
		AcidRDG rdg = AcidRDG.findByName(acidName);
		
		mapper.ident = rdg.getAcidID();
		mapper.name = rdg.getAcidName();
		mapper.moles = rdg.getAcidMoles();
		mapper.solute = rdg.getAcidSolute();

		return mapper.createAcid(rdg.getAcidID(), rdg.getAcidName(), rdg.getAcidMoles(), rdg.getAcidSolute());
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
