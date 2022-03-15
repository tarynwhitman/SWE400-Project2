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
import commands.GetAllBasesCmd;

/**
 * 
 * @author Dan Holmgren & Josh Kellogg
 *	This class sets up the base panel for the GUI
 */
public class BaseGUI implements guiInterface {
	JPanel baseMainPanel = new JPanel();
	JPanel listOfBasesPanel = new JPanel();
	JPanel baseControlPanel = new JPanel();

	/**
	 * Constructor for base panel
	 */
	public BaseGUI() {
		listOfBasesPanel.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .2), FRAME_SIZE.height));
		baseControlPanel.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .8), FRAME_SIZE.height));
		listOfBasesPanel.setBackground(new Color(235, 91, 52));
		baseControlPanel.setBackground(new Color(52, 186, 235));
		baseMainPanel.setLayout(new BoxLayout(baseMainPanel, BoxLayout.LINE_AXIS));
		baseMainPanel.add(listOfBasesPanel);
		baseMainPanel.add(baseControlPanel);

		setuplistOfBasesPanel();
	}

	/**
	 * Sets up the button to list all the bases
	 */
	private void setuplistOfBasesPanel() {
		listOfBasesPanel.add(new JLabel("Bases"));
		JButton generateListButton = new JButton("List of Bases");
		generateListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * HOOPLA
				 */
				try {
					GetAllBasesCmd baseGetter = new GetAllBasesCmd();
					new ExecuterForCommands(baseGetter);
					baseGetter.getBases().forEach(n -> System.out.println(n));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		listOfBasesPanel.add(generateListButton);
	}

}
