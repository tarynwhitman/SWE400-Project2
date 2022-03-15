/**
 * 
 */
package commands;

import java.util.ArrayList;

import mappers.AcidMapper;
import mappers.BaseMapper;
import mappers.CompoundMapper;
import mappers.ElementMapper;
import mappers.MetalMapper;

/**
 * Command to print all the chemicals in the database
 * 
 * @author Ad&Mad
 *
 */
public class PrintAllChemicalsCmd implements CommandInterface {

	/**
	 * Execute the command
	 */
	@Override
	public void execute() throws Exception {
		MetalMapper mm = new MetalMapper();
		AcidMapper am = new AcidMapper();
		BaseMapper bm = new BaseMapper();
		CompoundMapper cm = new CompoundMapper();
		ElementMapper em = new ElementMapper();

		printThingies(mm.getAllMetals());
		printThingies(am.getAllAcids());
		printThingies(bm.getAllBases());
		printThingies(cm.getAllCompounds());
		printThingies(em.getAllElements());
	}

	/**
	 * printa da chems
	 */
	public void printThingies(ArrayList stuff) {
		for (Object o : stuff) {
			System.out.println(o.toString());
		}
	}

}