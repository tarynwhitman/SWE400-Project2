package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import commands.ExecuterForCommands;
import commands.FindIDByNameCmd;
import commands.GenerateLowChemicalsReportCmd;
import commands.GetAllAcidsCmd;
import commands.GetAllBasesCmd;
import commands.GetAllCompoundsCmd;
import commands.GetAllElementsCmd;
import commands.GetAllMetalsCmd;
import commands.ModifyChemicalAmountCmd;
import commands.PrintAllChemicalsCmd;

/**
 * 
 * @author Dan Holmgren & Josh Kellogg
 *
 *	This class sets up the chemical panel in the GUI
 */
public class ChemicalGUI implements guiInterface {
	JPanel chemicalMainPanel = new JPanel();
	JPanel listOfChemicalsPanel = new JPanel();
	JPanel chemicalControlPanel = new JPanel();

	/**
	 * Constructor for the chemical panel
	 */
	public ChemicalGUI() {
		listOfChemicalsPanel
				.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .2), FRAME_SIZE.height));
		chemicalControlPanel
				.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .8), FRAME_SIZE.height));
		listOfChemicalsPanel.setBackground(new Color(235, 91, 52));
		chemicalControlPanel.setBackground(new Color(52, 186, 235));
		chemicalMainPanel.setLayout(new BoxLayout(chemicalMainPanel, BoxLayout.LINE_AXIS));
		chemicalMainPanel.add(listOfChemicalsPanel);
		chemicalMainPanel.add(chemicalControlPanel);

		setuplistOfChemicalsPanel();
		setupmodifyChemicalAmountPanel();
		setupgenerateLowChemicalReportPanel();
	}

	/**
	 * Sets up the button to list all chemicals
	 */
	private void setuplistOfChemicalsPanel() {
		listOfChemicalsPanel.add(new JLabel("Chemical"));
		JButton generateListButton = new JButton("List of Chemicals");
		generateListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * HOOPLA
				 */
				try {
					PrintAllChemicalsCmd chemGetter = new PrintAllChemicalsCmd();
					new ExecuterForCommands(chemGetter);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		listOfChemicalsPanel.add(generateListButton);
	}

	/**
	 * Sets up the panel to modify a chemical
	 */
	private void setupmodifyChemicalAmountPanel() {
		JPanel modifyChemicalAmountPanel = new JPanel(new GridLayout(0, 2));
		modifyChemicalAmountPanel.setBackground(new Color(220, 200, 220));
		modifyChemicalAmountPanel.add(new JLabel("Modify Amount"));
		GetAllAcidsCmd acids = new GetAllAcidsCmd();
		GetAllBasesCmd bases = new GetAllBasesCmd();
		GetAllCompoundsCmd compounds = new GetAllCompoundsCmd();
		GetAllElementsCmd elements = new GetAllElementsCmd();
		GetAllMetalsCmd metals = new GetAllMetalsCmd();
		ArrayList<String> names = new ArrayList<String>();
		try {
			new ExecuterForCommands(acids);
			acids.getAcids().forEach(n -> names.add(n.getAcidName()));
			new ExecuterForCommands(bases);
			bases.getBases().forEach(n -> names.add(n.getBaseName()));
			new ExecuterForCommands(compounds);
			compounds.getCdo().forEach(n -> names.add(n.getCompoundName()));
			new ExecuterForCommands(elements);
			elements.getElements().forEach(n -> names.add(n.getElementName()));
			new ExecuterForCommands(metals);
			metals.getMetals().forEach(n -> names.add(n.getMetalName()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JComboBox chemicalNameInput = new JComboBox(names.toArray());
		modifyChemicalAmountPanel.add(chemicalNameInput);
		modifyChemicalAmountPanel.add(new JLabel("New Amount"));
		JTextField newAmountInput = new JTextField();
		modifyChemicalAmountPanel.add(newAmountInput);
		JButton changeAmount = new JButton("Change");
		changeAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindIDByNameCmd nameFinder = new FindIDByNameCmd(chemicalNameInput.getSelectedItem().toString());
				try {
					new ExecuterForCommands(nameFinder);
					ModifyChemicalAmountCmd newMole = new ModifyChemicalAmountCmd(nameFinder.getID(),
							Double.parseDouble(newAmountInput.getText()));
					new ExecuterForCommands(newMole);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		modifyChemicalAmountPanel.add(changeAmount);
		chemicalControlPanel.add(modifyChemicalAmountPanel);
	}

	/**
	 * Sets up the button to generate a report that lists
	 * which chemicals are low in supply
	 */
	private void setupgenerateLowChemicalReportPanel() {
		JPanel chemReportPanel = new JPanel(new GridLayout(0, 2));
		chemReportPanel.add(new JLabel("Generate Low Chemical Report"));
		JButton generate = new JButton("Generate");
		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerateLowChemicalsReportCmd report;
				try {
					report = new GenerateLowChemicalsReportCmd(new File("ChemicalReport.txt"));
					new ExecuterForCommands(report);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		chemReportPanel.add(generate);
		chemicalControlPanel.add(chemReportPanel);
	}
}
