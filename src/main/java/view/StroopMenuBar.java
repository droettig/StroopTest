package view;

import static utils.SystemProperties.*;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import utils.ImageArchive;

import com.google.inject.Inject;

import controller.menu.AboutMenuController;
import controller.menu.CreateRandomTestMenuController;
import controller.menu.CreateTestMenuController;
import controller.menu.EditKeySettingsMenuController;
import controller.menu.EditTestMenuController;
import controller.menu.HelpMenuController;
import controller.menu.LoadTestMenuController;
import controller.menu.QuitMenuController;
import controller.menu.SaveTestMenuController;
import controller.menu.StartTestMenuController;
import controller.menu.StopTestMenuController;

public class StroopMenuBar extends JMenuBar
{
	private static final long serialVersionUID = -7646415846316247646L;

	private JMenu fileMenu;
	private JMenu testMenu;
	private JMenu testControlMenu;
	private JMenu editMenu;
	private JMenu helpMenu;

	private JMenuItem loadTestItem;
	private JMenuItem saveTestItem;
	private JMenuItem helpItem;
	private JMenuItem aboutItem;
	private JMenuItem randomWordTestBlackOnlyItem;
	private JMenuItem randomWordTestOneColourItem;
	private JMenuItem randomWordTestRandomColourItem;
	private JMenuItem randomColourTestRandomColourItem;
	private JMenuItem createOwnTestItem;
	private JMenuItem editTestItem;
	private JMenuItem setKeysItem;
	private JMenuItem quitItem;
	private JMenuItem stopTestItem;
	private JMenuItem startTestItem;

	@Inject
	public StroopMenuBar(
			CreateRandomTestMenuController randomTestMenuController,
			HelpMenuController helpMenuController,
			StopTestMenuController stopTestMenuController,
			EditTestMenuController editTestMenuController,
			LoadTestMenuController loadTestMenuController,
			SaveTestMenuController saveTestMenuController,
			CreateTestMenuController createTestMenuController,
			EditKeySettingsMenuController editKeySettingsMenuController,
			StartTestMenuController startTestMenuController,
			AboutMenuController aboutMenuController,
			QuitMenuController quitMenuController)
	{
		fileMenu = new JMenu("Datei");
		fileMenu.setMnemonic(KeyEvent.VK_D);

		testMenu = new JMenu("Test erstellen");
		testMenu.setMnemonic(KeyEvent.VK_T);
		testMenu.setIcon(ImageArchive.NEW);

		testControlMenu = new JMenu("Steuerung");
		testControlMenu.setMnemonic(KeyEvent.VK_S);

		startTestItem = new JMenuItem("Test beginnen");
		startTestItem.setActionCommand("START_TEST");
		startTestItem.setMnemonic(KeyEvent.VK_B);
		startTestItem.setIcon(ImageArchive.RUN_TEST);

		stopTestItem = new JMenuItem("Test stoppen");
		stopTestItem.setActionCommand("STOP_TEST");
		stopTestItem.setMnemonic(KeyEvent.VK_S);
		stopTestItem.setIcon(ImageArchive.STOP_TEST);

		loadTestItem = new JMenuItem("Test laden");
		loadTestItem.setActionCommand("LOAD");
		loadTestItem.setMnemonic(KeyEvent.VK_L);
		loadTestItem.setIcon(ImageArchive.LOAD);

		saveTestItem = new JMenuItem("Test speichern");
		saveTestItem.setActionCommand("SAVE");
		saveTestItem.setMnemonic(KeyEvent.VK_S);
		saveTestItem.setIcon(ImageArchive.SAVE);

		quitItem = new JMenuItem("Beenden");
		quitItem.setActionCommand("QUIT");
		quitItem.setMnemonic(KeyEvent.VK_B);
		quitItem.setIcon(ImageArchive.QUIT);

		editMenu = new JMenu("Bearbeiten");
		editMenu.setMnemonic(KeyEvent.VK_B);

		setKeysItem = new JMenuItem("Tasten belegen");
		setKeysItem.setActionCommand("EDIT_KEYS");
		setKeysItem.setMnemonic(KeyEvent.VK_T);
		setKeysItem.setIcon(ImageArchive.EDIT_KEYS);

		editTestItem = new JMenuItem("Test bearbeiten");
		editTestItem.setActionCommand("EDIT_TEST");
		editTestItem.setMnemonic(KeyEvent.VK_B);
		editTestItem.setIcon(ImageArchive.EDIT_TEST);

		helpMenu = new JMenu("?");
		helpMenu.setMnemonic(KeyEvent.VK_H);

		helpItem = new JMenuItem("Hilfe");
		helpItem.setActionCommand("HELP");
		helpItem.setMnemonic(KeyEvent.VK_I);
		helpItem.setIcon(ImageArchive.HELP);

		aboutItem = new JMenuItem(Ue+"ber");
		aboutItem.setActionCommand("ABOUT");
		aboutItem.setMnemonic(KeyEvent.VK_E);
		aboutItem.setIcon(ImageArchive.INFO);

		randomWordTestBlackOnlyItem = new JMenuItem("Zuf"+ae+"lligen Worttest erstellen (nur schwarze Schrift)");
		randomWordTestBlackOnlyItem.setActionCommand("CREATE_WORD_BLACK");

		randomWordTestOneColourItem = new JMenuItem("Zuf"+ae+"lligen Worttest erstellen (nur einfarbige Schrift)");
		randomWordTestOneColourItem.setActionCommand("CREATE_WORD_COLOUR");

		randomWordTestRandomColourItem = new JMenuItem("Zuf"+ae+"lligen Worttest erstellen (gemischte Farben)");
		randomWordTestRandomColourItem.setActionCommand("CREATE_WORD_RANDOM");

		randomColourTestRandomColourItem = new JMenuItem("Zuf"+ae+"lligen Farbtest erstellen (gemischte Farben)");
		randomColourTestRandomColourItem.setActionCommand("CREATE_COLOUR_RANDOM");

		createOwnTestItem = new JMenuItem("Neuen Test erstellen");
		createOwnTestItem.setActionCommand("CREATE_TEST");

		this.testMenu.add(createOwnTestItem);
		this.testMenu.add(randomWordTestBlackOnlyItem);
		this.testMenu.add(randomWordTestOneColourItem);
		this.testMenu.add(randomWordTestRandomColourItem);
		this.testMenu.add(randomColourTestRandomColourItem);

		this.fileMenu.add(testMenu);
		this.fileMenu.add(loadTestItem);
		this.fileMenu.add(saveTestItem);

		this.testControlMenu.add(startTestItem);
		this.testControlMenu.add(stopTestItem);

		this.fileMenu.addSeparator();

		this.fileMenu.add(quitItem);

		this.add(this.fileMenu);

		this.editMenu.add(setKeysItem);
		this.editMenu.add(editTestItem);

		this.add(fileMenu);

		this.add(testControlMenu);

		this.add(editMenu);

		this.helpMenu.add(helpItem);
		this.helpMenu.add(aboutItem);

		this.add(this.helpMenu);
		
		quitItem.addActionListener(quitMenuController);
		aboutItem.addActionListener(aboutMenuController);

		startTestItem.addActionListener(startTestMenuController);
		setKeysItem.addActionListener(editKeySettingsMenuController);
		createOwnTestItem.addActionListener(createTestMenuController);
		saveTestItem.addActionListener(saveTestMenuController);
		loadTestItem.addActionListener(loadTestMenuController);
		editTestItem.addActionListener(editTestMenuController);
		stopTestItem.addActionListener(stopTestMenuController);
		helpItem.addActionListener(helpMenuController);

		randomColourTestRandomColourItem.addActionListener(randomTestMenuController);
		randomWordTestBlackOnlyItem.addActionListener(randomTestMenuController);
		randomWordTestOneColourItem.addActionListener(randomTestMenuController);
		randomWordTestRandomColourItem.addActionListener(randomTestMenuController);
	}

	public void enableMenu()
	{
		setMenuItemsEnabled(true);
	}

	public void disableMenu()
	{
		setMenuItemsEnabled(false);
	}

	private void setMenuItemsEnabled(boolean enabled)
	{
		this.helpMenu.setEnabled(enabled);
		this.saveTestItem.setEnabled(enabled);
		this.testMenu.setEnabled(enabled);
		this.loadTestItem.setEnabled(enabled);
		this.editMenu.setEnabled(enabled);
	}
}
