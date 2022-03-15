package gui;

import datasource.ClassTableCreator;
import datasource.ConcreteTableCreator;
import datasource.DatabaseException;
import datasource.DatabaseManager;
import datasource.SingleTableCreator;

public class Runnable {
	public static void main(String[] args) throws DatabaseException {
		testSingle();
	}

	public static void testSingle() throws DatabaseException {
		DatabaseManager.setPatternNumber(1);
		DatabaseManager.getSingleton().openConnection();

		SingleTableCreator.dropAllTables();
		SingleTableCreator.createTables();
		SingleTableCreator.addTestRows();

		System.out.println("Hullabaloo");
		new MasterGUI();
	}

	public static void testConcrete() throws DatabaseException {
		DatabaseManager.setPatternNumber(2);
		DatabaseManager.getSingleton().openConnection();

		ConcreteTableCreator.dropAllTables();
		ConcreteTableCreator.createTables();
		ConcreteTableCreator.addTestRows();

		System.out.println("Hullabaloo");
		new MasterGUI();
	}

	public static void testClass() throws DatabaseException {
		DatabaseManager.setPatternNumber(3);
		DatabaseManager.getSingleton().openConnection();

		ClassTableCreator.dropAllTables();
		ClassTableCreator.createTables();
		ClassTableCreator.addTestRows();

		System.out.println("Hullabaloo");
		new MasterGUI();
	}
}