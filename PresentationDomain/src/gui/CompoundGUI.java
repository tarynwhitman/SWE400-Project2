package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import commands.AddElementToCompoundCmd;
import commands.DeleteElementFromCompoundCmd;
import commands.ExecuterForCommands;
import commands.FindIDByNameCmd;
import commands.GetAllCompoundsCmd;
import commands.GetAllElementsCmd;

/**
 * 
 * @author Dan Holmgren & Josh Kellogg
 *
 * This class sets up the compound tab of the GUI
 */
public class CompoundGUI implements guiInterface {
	JPanel compoundMainPanel = new JPanel();
	JPanel listOfCompoundPanel = new JPanel();
	JPanel compoundControlPanel = new JPanel();
	
	/**
	 * Constructor for the compound panel
	 */
	public CompoundGUI() {
		listOfCompoundPanel
				.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .2), FRAME_SIZE.height));
		compoundControlPanel
				.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .8), FRAME_SIZE.height));
		listOfCompoundPanel.setBackground(new Color(235, 91, 52));
		compoundControlPanel.setBackground(new Color(52, 186, 235));
		compoundMainPanel.setLayout(new BoxLayout(compoundMainPanel, BoxLayout.LINE_AXIS));
		compoundMainPanel.add(listOfCompoundPanel);
		compoundMainPanel.add(compoundControlPanel);

		setuplistOfCompoundPanel();
		setupaddElementToCompoundAmountPanel();
		setupremoveElementFromCompoundAmountPanel();
	}

	/**
	 * Sets up the panel to list all the compounds
	 */
	private void setuplistOfCompoundPanel() {
		listOfCompoundPanel.add(new JLabel("Compound"));
		JButton generateListButton = new JButton("List of Compuounds");
		generateListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * HOOPLA
				 */
				try {
					GetAllCompoundsCmd compoundGetter = new GetAllCompoundsCmd();
					new ExecuterForCommands(compoundGetter);
					compoundGetter.getCdo().forEach(n -> System.out.println(n));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		listOfCompoundPanel.add(generateListButton);
	}

	/**
	 * Sets up the panel to add an element to a compound's
	 * composition
	 */
	private void setupaddElementToCompoundAmountPanel() {
		JPanel addElementToCompoundAmountPanel = new JPanel(new GridLayout(0, 2));
		addElementToCompoundAmountPanel.setBackground(new Color(220, 240, 220));
		addElementToCompoundAmountPanel.add(new JLabel("Add Element"));
		JButton addElement = new JButton("Add");
		addElementToCompoundAmountPanel.add(addElement);
		addElementToCompoundAmountPanel.add(new JLabel("How Much?"));
		JTextField muchInput = new JTextField();
		addElementToCompoundAmountPanel.add(muchInput);
		ArrayList<String> compNames = new ArrayList<String>();
		GetAllCompoundsCmd compGetter = new GetAllCompoundsCmd();
		try {
			new ExecuterForCommands(compGetter);
			compGetter.getCdo().forEach(n -> compNames.add(n.getCompoundName()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JComboBox compoundNameInput = new JComboBox(compNames.toArray());
		addElementToCompoundAmountPanel.add(compoundNameInput);
		// Commmand stuff to get list of elements in the compound
		ArrayList<String> elementNames = new ArrayList<String>();
		GetAllElementsCmd elementGetter = new GetAllElementsCmd();
		try {
			new ExecuterForCommands(elementGetter);
			elementGetter.getElements().forEach(n -> elementNames.add(n.getElementName()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JComboBox elementNameInput = new JComboBox(elementNames.toArray());
		addElementToCompoundAmountPanel.add(elementNameInput);

		addElement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindIDByNameCmd eleFinder = new FindIDByNameCmd(elementNameInput.getSelectedItem().toString());
				FindIDByNameCmd compFinder = new FindIDByNameCmd(compoundNameInput.getSelectedItem().toString());
				try {
					new ExecuterForCommands(eleFinder);
					new ExecuterForCommands(compFinder);
					AddElementToCompoundCmd adder = new AddElementToCompoundCmd(eleFinder.getID(),
							Integer.parseInt(muchInput.getText()), compFinder.getID());
					new ExecuterForCommands(adder);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		compoundControlPanel.add(addElementToCompoundAmountPanel);
	}

	/**
	 * Sets up the panel to remove an element from a compound's
	 * composition
	 */
	private void setupremoveElementFromCompoundAmountPanel() {
		JPanel removeElementFromCompoundAmountPanel = new JPanel(new GridLayout(0, 2));
		removeElementFromCompoundAmountPanel.setBackground(new Color(220, 100, 220));
		removeElementFromCompoundAmountPanel.add(new JLabel("Remove Element"));
		ArrayList<String> compNames = new ArrayList<String>();
		GetAllCompoundsCmd compGetter = new GetAllCompoundsCmd();
		try {
			new ExecuterForCommands(compGetter);
			compGetter.getCdo().forEach(n -> compNames.add(n.getCompoundName()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JComboBox compoundNameInput = new JComboBox(compNames.toArray());
		removeElementFromCompoundAmountPanel.add(compoundNameInput);
		// Command thing for getting list of Elements
		ArrayList<String> elementNames = new ArrayList<String>();
		GetAllElementsCmd elementGetter = new GetAllElementsCmd();
		try {
			new ExecuterForCommands(elementGetter);
			elementGetter.getElements().forEach(n -> elementNames.add(n.getElementName()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JComboBox elementNameInput = new JComboBox(elementNames.toArray());
		removeElementFromCompoundAmountPanel.add(elementNameInput);

		JButton removeElement = new JButton("Remove");
		removeElement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindIDByNameCmd eleName = new FindIDByNameCmd(elementNameInput.getSelectedItem().toString());
				FindIDByNameCmd compName = new FindIDByNameCmd(compoundNameInput.getSelectedItem().toString());
				try {
					new ExecuterForCommands(eleName);
					new ExecuterForCommands(compName);
					DeleteElementFromCompoundCmd remover = new DeleteElementFromCompoundCmd(eleName.getID(),
							compName.getID());
					new ExecuterForCommands(remover);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		removeElementFromCompoundAmountPanel.add(removeElement);
		compoundControlPanel.add(removeElementFromCompoundAmountPanel);
	}
}
