package gui;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * This here be the gui
 * 
 * @author Dan Holmgren & Josh Kellogg
 *
 */
public class MasterGUI implements guiInterface {
	

	JFrame mainFrame = new JFrame("SWE Rocks!");
	JTabbedPane mainPane = new JTabbedPane();
	
	/**
	 * Constructor for the entire GUI,
	 * creates the main window and then adds all panels
	 */
	public MasterGUI() {

		mainFrame.setPreferredSize(FRAME_SIZE);
		mainFrame.pack();

		mainPane.addTab("Element", new ElementGUI().elementMainPanel);
		mainPane.addTab("Metal", new MetalGUI().metalMainPanel);
		mainPane.addTab("Compound", new CompoundGUI().compoundMainPanel);
		mainPane.addTab("Acid", new AcidGUI().acidMainPanel);
		mainPane.addTab("Base", new BaseGUI().baseMainPanel);
		mainPane.addTab("Chemical", new ChemicalGUI().chemicalMainPanel);

		mainFrame.add(mainPane);
		mainFrame.setVisible(true);
	}

	/**
	 * Outline for the basic stuff each tab needs
	 */
//	private void setupPanels(JPanel main, JPanel list, JPanel control) {
//		list.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .2), FRAME_SIZE.height));
//		control.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .8), FRAME_SIZE.height));
//		list.setBackground(new Color(235, 91, 52));
//		control.setBackground(new Color(52, 186, 235));
//		main.setLayout(new BoxLayout(main, BoxLayout.LINE_AXIS));
//		main.add(list);
//		main.add(control);
//	}

	
}
