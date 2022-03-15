package commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import domainObjects.ChemicalDomainObject;
import mappers.ChemicalMapper;

/**
 * Command class to generate a report of the chemicals that are running low in
 * inventory
 * 
 * @author Madeline and Adam
 *
 */
public class GenerateLowChemicalsReportCmd implements CommandInterface {

	private File outputFile;
	private FileWriter fw;

	/**
	 * Constructor
	 * 
	 * @param f the file to write the report to
	 * @throws IOException
	 */
	public GenerateLowChemicalsReportCmd(File f) throws IOException {
		outputFile = f;
	}

	/**
	 * Execute method to invoke the generate low chemicals report command
	 * 
	 * @throws Exception
	 */
	public void execute() throws Exception {
		fw = new FileWriter(outputFile);
		ChemicalMapper cm = new ChemicalMapper();
		ArrayList<ChemicalDomainObject> chem_list = cm.findLowChemicals();
		for (ChemicalDomainObject c : chem_list) {
			fw.write(c.toString() + "\n");
		}
		fw.close();
	}

	/**
	 * @return the report
	 */
	public File getOutputFile() {
		return outputFile;
	}

}