package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import commands.AddElementCmd;
import commands.ExecuterForCommands;
import commands.FindElementByAtomicMassCmd;
import commands.FindElementByAtomicNumberCmd;
import commands.FindElementByNameCmd;
import commands.FindElementsInRangeCmd;
import commands.FindIDByNameCmd;
import commands.GetAcidIDByNameCmd;
import commands.GetAllAcidsCmd;
import commands.GetAllElementsCmd;
import commands.GetCompoundsByElementCmd;
import commands.GetElementIDByNameCmd;
import commands.ModifyElementAtomicNumberCmd;
import domainObjects.ElementDomainObject;

/**
 * 
 * @author Dan Holmgren & Josh Kellogg
 * This class sets up the element panel of the GUI
 *
 */
public class ElementGUI implements guiInterface {
	JPanel elementMainPanel = new JPanel();
	JPanel listOfElementsPanel = new JPanel();
	JPanel elementControlPanel = new JPanel();

	JComboBox elementNameInput;

	/**
	 * Constructor for the element panel
	 */
	public ElementGUI() {
		listOfElementsPanel
				.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .2), FRAME_SIZE.height));
		elementControlPanel
				.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .8), FRAME_SIZE.height));
		listOfElementsPanel.setBackground(new Color(235, 91, 52));
		elementControlPanel.setBackground(new Color(52, 186, 235));
		elementMainPanel.setLayout(new BoxLayout(elementMainPanel, BoxLayout.LINE_AXIS));
		elementMainPanel.add(listOfElementsPanel);
		elementMainPanel.add(elementControlPanel);

		// elementControlPanel.setLayout(new BoxLayout(elementControlPanel,
		// BoxLayout.PAGE_AXIS));

		setuplistOfElementsPanel();
		setupAddElementPanel();
		setupfindElementsInRangePanel();
		setupfindElementPanel();
		setupgetCompoundByElementPanel();
		setupmodifyElementPanel();
	}

	/**
	 * Sets up the button to list all elements
	 */
	private void setuplistOfElementsPanel() {
		listOfElementsPanel.add(new JLabel("Element"));
		JButton generateListButton = new JButton("List of Elements");
		generateListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * HOOPLA
				 */
				try {
					GetAllElementsCmd elementGetter = new GetAllElementsCmd();
					new ExecuterForCommands(elementGetter);
					elementGetter.getElements().forEach(n -> System.out.println(n));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		listOfElementsPanel.add(generateListButton);
	}

	/**
	 * Sets up the panel to add an element to the database
	 */
	private void setupAddElementPanel() {
		JPanel addElementPanel = new JPanel(new GridLayout(0, 2));
		addElementPanel.setBackground(new Color(52, 186, 235));
		JButton addElementButton = new JButton("Add Element");

		addElementPanel.add(new JLabel("Add Element"));
		addElementPanel.add(addElementButton);
		addElementPanel.add(new JLabel("Atomic Number: "));
		JTextField atomicNumberInput = new JTextField();
		addElementPanel.add(atomicNumberInput);
		addElementPanel.add(new JLabel("Atomic Mass: "));
		JTextField atomicMassInput = new JTextField();
		addElementPanel.add(atomicMassInput);
		addElementPanel.add(new JLabel("Name: "));
		JTextField nameInput = new JTextField();
		addElementPanel.add(nameInput);
		// Amount

		addElementPanel.add(new JLabel("Amount of Moles: "));
		JTextField molesInput = new JTextField();
		addElementPanel.add(molesInput);

		JCheckBox isMetalInput = new JCheckBox("Is Metal?");
		addElementPanel.add(isMetalInput);

		GetAllAcidsCmd gaac = new GetAllAcidsCmd();
		try {
			new ExecuterForCommands(gaac);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		ArrayList<String> names = new ArrayList<String>();
		gaac.getAcids().forEach((n) -> names.add(n.getAcidName()));
		JComboBox metalDissolvedByInput = new JComboBox(names.toArray());
		addElementPanel.add(metalDissolvedByInput);
		// How much mole to dissolve

		addElementPanel.add(new JLabel("Moles to Dissolve: "));
		JTextField molesToDissolveInput = new JTextField();
		addElementPanel.add(molesToDissolveInput);

		addElementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetAcidIDByNameCmd goo = new GetAcidIDByNameCmd(metalDissolvedByInput.getSelectedItem().toString());
				try {
					new ExecuterForCommands(goo);
				} catch (Exception ee) {
					System.out.println("getacididbynamefail: " + metalDissolvedByInput.getSelectedItem().toString());
					ee.printStackTrace();
				}
				try {
					if (isMetalInput.isSelected()) {
						new ExecuterForCommands(new AddElementCmd(547, nameInput.getText(),
								Integer.parseInt(atomicNumberInput.getText()),
								Double.parseDouble(atomicMassInput.getText()), true, goo.getAcidID(),
								Double.parseDouble(molesInput.getText()),
								Double.parseDouble(molesToDissolveInput.getText())));
						System.out.println("Adding metal " + nameInput.getText());
						updateElementList();
					} else {
						new ExecuterForCommands(new AddElementCmd(547, nameInput.getText(),
								Integer.parseInt(atomicNumberInput.getText()),
								Double.parseDouble(atomicMassInput.getText()), false, 0,
								Double.parseDouble(molesInput.getText()), 0));
						System.out.println("Adding element " + nameInput.getText());
						updateElementList();
					}

				} catch (NumberFormatException e1) {
					System.out.println("number probaddelementfail");
					e1.printStackTrace();
				} catch (Exception e1) {
					System.out.println("addelementfail");
					e1.printStackTrace();
				}
//				AddElementCmd(int id, String name, int num, double mass, boolean metal, int a_id, double mol,
				// double molToDissolve)

			}
		});

		elementControlPanel.add(addElementPanel);
	}
	
	/**
	 * Sets up the panel to search the database for
	 * elements with atomic masses within the given range
	 */
	private void setupfindElementsInRangePanel() {
		JPanel findElementsInRangePanel = new JPanel(new GridLayout(0, 2));
		findElementsInRangePanel.setBackground(new Color(52, 186, 0));
		findElementsInRangePanel.add(new JLabel("Find Elements In Range"));
		JButton findElementsInRangeButton = new JButton("Find");
		findElementsInRangePanel.add(findElementsInRangeButton);
		findElementsInRangePanel.add(new JLabel("Lower Bound"));
		JTextField lowerBoundInput = new JTextField();
		findElementsInRangePanel.add(lowerBoundInput);
		findElementsInRangePanel.add(new JLabel("Upper Bound"));
		JTextField upperBoundInput = new JTextField();
		findElementsInRangePanel.add(upperBoundInput);

		findElementsInRangeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindElementsInRangeCmd doo = new FindElementsInRangeCmd(Double.parseDouble(upperBoundInput.getText()),
						Double.parseDouble(lowerBoundInput.getText()));
				try {
					new ExecuterForCommands(doo);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				ArrayList<ElementDomainObject> loo = doo.getElementArrayList();
				if (loo.size() == 0) {
					System.out.println("No elements in range.");
				} else {
					for (ElementDomainObject thingamabob : loo) {
						System.out.println(thingamabob.toString());
					}
				}
			}
		});

		elementControlPanel.add(findElementsInRangePanel);
	}

	/**
	 * Sets up the panel to find an element by atomic number,
	 * atomic mass, or by name
	 */
	private void setupfindElementPanel() {
		JPanel findElementPanel = new JPanel(new GridLayout(0, 2));
		findElementPanel.setBackground(new Color(52, 200, 235));
		JButton findByButton = new JButton("Find");
		findElementPanel.add(new JLabel("Find Element By Something"));
		JComboBox findByType = new JComboBox(new String[] { "Atomic Number", "Atomic Mass", "Name" });
		findElementPanel.add(findByType);
		JTextField findByInput = new JTextField();
		findElementPanel.add(findByInput);
		findElementPanel.add(findByButton);

		findByButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (findByType.getItemAt(findByType.getSelectedIndex()) == "Atomic Number") {
					FindElementByAtomicNumberCmd c1 = new FindElementByAtomicNumberCmd(
							Integer.parseInt(findByInput.getText()));
					try {
						new ExecuterForCommands(c1);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					System.out.println(c1.getEdo().toString());
				} else if (findByType.getItemAt(findByType.getSelectedIndex()) == "Atomic Mass") {
					FindElementByAtomicMassCmd c1 = new FindElementByAtomicMassCmd(
							Double.parseDouble(findByInput.getText()));
					try {
						new ExecuterForCommands(c1);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					System.out.println(c1.getEdo().toString());

				} else if (findByType.getItemAt(findByType.getSelectedIndex()) == "Name") {
					FindElementByNameCmd c1 = new FindElementByNameCmd(findByInput.getText());
					try {
						new ExecuterForCommands(c1);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					System.out.println(c1.getEdo().toString());
				} else {
					System.out.println("Ruh roh");
				}
			}
		});

		elementControlPanel.add(findElementPanel);
	}

	
	/**
	 * Sets up the panel to find all compounds that contain
	 * the selected element
	 */
	private void setupgetCompoundByElementPanel() {
		JPanel getCompoundByElementPanel = new JPanel(new GridLayout(0, 2));
		getCompoundByElementPanel.setBackground(new Color(0, 186, 235));
		getCompoundByElementPanel.add(new JLabel("Get Compounds By Element"));
		JButton getCompoundsButton = new JButton("Find Compounds");
		getCompoundByElementPanel.add(getCompoundsButton);
		getCompoundByElementPanel.add(new JLabel("Element Name"));
		JTextField elementNameInput = new JTextField();
		getCompoundByElementPanel.add(elementNameInput);
		getCompoundsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> compoundNames = new ArrayList<String>();
				GetElementIDByNameCmd nameID = new GetElementIDByNameCmd(elementNameInput.getText());
				try {
					new ExecuterForCommands(nameID);
					GetCompoundsByElementCmd compID = new GetCompoundsByElementCmd(nameID.getElementID());
					new ExecuterForCommands(compID);
					compID.getCompounds().forEach(n -> compoundNames.add(n.getCompoundName()));
					if (compoundNames.size() == 0) {
						System.out.println("No compounds contain this element.");
					} else {
						compoundNames.forEach(n -> System.out.println(n));
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		elementControlPanel.add(getCompoundByElementPanel);
	}

	/**
	 * Sets up the panel to modify an element
	 */
	private void setupmodifyElementPanel() {
		JPanel modifyElementPanel = new JPanel(new GridLayout(0, 2));
		modifyElementPanel.setBackground(new Color(52, 200, 220));
		modifyElementPanel.add(new JLabel("Modify Element"));
		GetAllElementsCmd elementGrabber = new GetAllElementsCmd();
		ArrayList<String> eNames = new ArrayList<String>();
		try {
			new ExecuterForCommands(elementGrabber);
			elementGrabber.getElements().forEach(n -> eNames.add(n.getElementName()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		elementNameInput = new JComboBox(eNames.toArray());
		modifyElementPanel.add(elementNameInput);
		modifyElementPanel.add(new JLabel("New Atomic Number:"));
		JTextField newNumInput = new JTextField();
		modifyElementPanel.add(newNumInput);
		JButton changeNum = new JButton("Change");
		changeNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindIDByNameCmd finder = new FindIDByNameCmd(elementNameInput.getSelectedItem().toString());
				try {
					new ExecuterForCommands(finder);
					ModifyElementAtomicNumberCmd newNum = new ModifyElementAtomicNumberCmd(finder.getID(),
							Integer.parseInt(newNumInput.getText()));
					new ExecuterForCommands(newNum);
					System.out.println("Modifying element " + elementNameInput.getSelectedItem());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		modifyElementPanel.add(changeNum);
		elementControlPanel.add(modifyElementPanel);
	}

	public void updateElementList() {
		elementNameInput.removeAllItems();

		GetAllElementsCmd elementGrabber = new GetAllElementsCmd();
//		ArrayList<String> eNames = new ArrayList<String>();
		try {
			new ExecuterForCommands(elementGrabber);
			elementGrabber.getElements().forEach(n -> elementNameInput.addItem(n.getElementName()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

//		elementNameInput.addItem(eNames.toArray());
	}
}
