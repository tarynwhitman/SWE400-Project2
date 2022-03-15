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

import commands.ExecuterForCommands;
import commands.FindIDByNameCmd;
import commands.GetAllAcidsCmd;
import commands.GetAllMetalsCmd;
import commands.OverwriteAcidToDissolveMetalCmd;

/**
 * 
 * @author Dan Holmgren & Josh Kellogg
 *
 *	This class sets up the metal panel in the GUI
 */

public class MetalGUI implements guiInterface {
	JPanel metalMainPanel = new JPanel();
	JPanel listOfMetalsPanel = new JPanel();
	JPanel metalControlPanel = new JPanel();

	/**
	 * Constructor for the metal GUI
	 */
	public MetalGUI() {
		listOfMetalsPanel.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .2), FRAME_SIZE.height));
		metalControlPanel.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .8), FRAME_SIZE.height));
		listOfMetalsPanel.setBackground(new Color(235, 91, 52));
		metalControlPanel.setBackground(new Color(52, 186, 235));
		metalMainPanel.setLayout(new BoxLayout(metalMainPanel, BoxLayout.LINE_AXIS));
		metalMainPanel.add(listOfMetalsPanel);
		metalMainPanel.add(metalControlPanel);

		setuplistOfMetalsPanel();
		setupfillerMetalPanel();
		setupoverWriteAcidToDissolveMetalPanel();
	}

	/**
	 * Sets up the button to list all metals
	 */
	private void setuplistOfMetalsPanel() {
		listOfMetalsPanel.add(new JLabel("Metal"));
		JButton generateListButton = new JButton("List of Metals");
		generateListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * HOOPLA
				 */
				try {
					GetAllMetalsCmd metalGetter = new GetAllMetalsCmd();
					new ExecuterForCommands(metalGetter);
					metalGetter.getMetals().forEach(n -> System.out.println(n.toString()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		listOfMetalsPanel.add(generateListButton);
	}

	private void setupfillerMetalPanel() {
		JPanel fillerMetalPanel = new JPanel(new GridLayout(0, 2));

		metalControlPanel.add(fillerMetalPanel);
	}

	/**
	 * Allows the user to make an acid dissolve a certain metal
	 */
	private void setupoverWriteAcidToDissolveMetalPanel() {
		JPanel overwriteAcidPanel = new JPanel(new GridLayout(0, 2));
		overwriteAcidPanel.setBackground(new Color(235, 183, 52));
		overwriteAcidPanel.add(new JLabel("Overwrite Acid To Dissolve Metal"));
		JButton overwriteButton = new JButton("Overwrite");
		overwriteAcidPanel.add(overwriteButton);
		overwriteAcidPanel.add(new JLabel("Select Metal"));
		ArrayList<String> metalNames = new ArrayList<String>();
		GetAllMetalsCmd metals = new GetAllMetalsCmd();
		try {
			new ExecuterForCommands(metals);
			metals.getMetals().forEach(n -> metalNames.add(n.getMetalName()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JComboBox metalNameBox = new JComboBox(metalNames.toArray());
		overwriteAcidPanel.add(metalNameBox);
		overwriteAcidPanel.add(new JLabel("Select new Acid"));
		ArrayList<String> acidNames = new ArrayList<String>();
		GetAllAcidsCmd acids = new GetAllAcidsCmd();
		try {
			new ExecuterForCommands(acids);
			acids.getAcids().forEach(n -> acidNames.add(n.getAcidName()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JComboBox acidNameBox = new JComboBox(acidNames.toArray());
		overwriteAcidPanel.add(acidNameBox);
		overwriteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindIDByNameCmd metalName = new FindIDByNameCmd(metalNameBox.getSelectedItem().toString());
				FindIDByNameCmd acidName = new FindIDByNameCmd(acidNameBox.getSelectedItem().toString());
				try {
					new ExecuterForCommands(metalName);
					new ExecuterForCommands(acidName);
					new ExecuterForCommands(new OverwriteAcidToDissolveMetalCmd(metalName.getID(), acidName.getID()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		metalControlPanel.add(overwriteAcidPanel);
	}
}