package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import commands.ExecuterForCommands;
import commands.GetAllAcidsCmd;

/**
 * 
 * @author Dan Holmgren & Josh Kellogg
 * 
 *	This class sets up the acid panel for the GUI
 */
public class AcidGUI implements guiInterface {
	JPanel acidMainPanel = new JPanel();
	JPanel listOfAcidsPanel = new JPanel();
	JPanel acidControlPanel = new JPanel();

	/**
	 * Constructor for acid GUI panel
	 */
	public AcidGUI() {
		listOfAcidsPanel.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .2), FRAME_SIZE.height));
		acidControlPanel.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .8), FRAME_SIZE.height));
		listOfAcidsPanel.setBackground(new Color(235, 91, 52));
		acidControlPanel.setBackground(new Color(52, 186, 235));
		acidMainPanel.setLayout(new BoxLayout(acidMainPanel, BoxLayout.LINE_AXIS));
		acidMainPanel.add(listOfAcidsPanel);
		acidMainPanel.add(acidControlPanel);

		setuplistOfAcidsPanel();
	}

	/**
	 * Sets up the button to list all acids
	 */
	private void setuplistOfAcidsPanel() {
		listOfAcidsPanel.add(new JLabel("Acids"));
		JButton generateListButton = new JButton("List of Acids");
		generateListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Dan do your thing
				 */
				try {
					GetAllAcidsCmd acidGetter = new GetAllAcidsCmd();
					new ExecuterForCommands(acidGetter);
					acidGetter.getAcids().forEach(n -> System.out.println(n));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		listOfAcidsPanel.add(generateListButton);

	}

}
