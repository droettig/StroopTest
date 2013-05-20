package view;

import static utils.SystemProperties.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.ColourType;
import model.KeySettings;
import model.StroopTestRunner;
import model.Word;
import model.state.InitialState;
import services.EventBusService;
import services.StroopTestFileService;
import services.StroopTestProvider;
import utils.ResultFileFilter;
import utils.SystemProperties;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

import controller.StroopTestRunController;
import events.SaveTestResultsEvent;
import events.StartTestEvent;
import events.StopTestEvent;
import events.SuccessfulWordEvent;
import events.messages.MessageEvent;
import events.state.AnalyzingStateEnterEvent;
import events.state.AnalyzingStateExitEvent;
import events.state.InitialStateEnterEvent;
import events.state.TestFinishedStateEnterEvent;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StroopFrame.java
 *
 * Created on 30.06.2009, 15:55:24
 */
public class MainWindow extends JFrame implements ComponentListener, Observer
{
	private static final long serialVersionUID = 4743114174694533317L;

	private JLabel redLabel;
	private JLabel greenLabel;
	private JLabel blueLabel;
	private JLabel blackLabel;
	private JLabel yellowLabel;
	private JLabel orangeLabel;
	private JLabel letterLabel;

	private JPanel blackBox;
	private JPanel redBox;
	private JPanel blueBox;
	private JPanel greenBox;
	private JPanel yellowBox;
	private JPanel orangeBox;

	private JPanel coloursPanel;

	private KeySettings keySettings;
	private EventBusService eventBusService;
	private StroopTestRunner runner;
	private StroopTestProvider stroopTestProvider;
	private StroopTestFileService stroopTestFileService;

	private StroopMenuBar stroopMenu;

	private KeyListener testRunListener;

	@Inject
	public MainWindow(KeySettings keySettings, EventBusService eventBusService, StroopTestProvider stroopTestProvider,
			StroopMenuBar stroopMenu, StroopTestFileService stroopTestFileService)
	{
		this.keySettings = keySettings;
		this.keySettings.addObserver(this);
		this.eventBusService = eventBusService;
		this.eventBusService.register(this);
		this.stroopTestProvider = stroopTestProvider;
		this.stroopMenu = stroopMenu;
		this.stroopTestFileService = stroopTestFileService;
		this.initComponents();
	}

	private void initComponents()
	{
		this.blackBox = new JPanel();
		this.redBox = new JPanel();
		this.blueBox = new JPanel();
		this.greenBox = new JPanel();
		this.yellowBox = new JPanel();
		this.orangeBox = new JPanel();

		this.letterLabel = new LetterPanel();

		this.coloursPanel = new JPanel();

		this.blackLabel = new JLabel();
		this.redLabel = new JLabel();
		this.greenLabel = new JLabel();
		this.blueLabel = new JLabel();
		this.yellowLabel = new JLabel();
		this.orangeLabel = new JLabel();

		this.coloursPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		this.coloursPanel.add(blackBox, c);

		c.gridx = 1;
		c.gridy = 0;
		this.coloursPanel.add(blackLabel, c);

		c.gridx = 0;
		c.gridy = 1;
		this.coloursPanel.add(redBox, c);

		c.gridx = 1;
		c.gridy = 1;
		this.coloursPanel.add(redLabel, c);

		c.gridx = 0;
		c.gridy = 2;
		this.coloursPanel.add(greenBox, c);

		c.gridx = 1;
		c.gridy = 2;
		this.coloursPanel.add(greenLabel, c);

		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(5, 50, 5, 5);
		this.coloursPanel.add(blueBox, c);

		c.gridx = 3;
		c.gridy = 0;
		c.insets = new Insets(5, 5, 5, 5);
		this.coloursPanel.add(blueLabel, c);

		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(5, 50, 5, 5);
		this.coloursPanel.add(yellowBox, c);

		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 5, 5);
		this.coloursPanel.add(yellowLabel, c);

		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(5, 50, 5, 5);
		this.coloursPanel.add(orangeBox, c);

		c.gridx = 3;
		c.gridy = 2;
		c.insets = new Insets(5, 5, 5, 5);
		this.coloursPanel.add(orangeLabel, c);

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.blackBox.setBackground(SystemProperties.COLOUR_MAP.get(ColourType.BLACK));
		this.blackBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		this.blackBox.setPreferredSize(new Dimension(30, 30));

		this.redBox.setBackground(SystemProperties.COLOUR_MAP.get(ColourType.RED));
		this.redBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		this.redBox.setPreferredSize(new Dimension(30, 30));

		this.greenBox.setBackground(SystemProperties.COLOUR_MAP.get(ColourType.GREEN));
		this.greenBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		this.greenBox.setPreferredSize(new Dimension(30, 30));

		this.blueBox.setBackground(SystemProperties.COLOUR_MAP.get(ColourType.BLUE));
		this.blueBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		this.blueBox.setPreferredSize(new Dimension(30, 30));

		this.yellowBox.setBackground(SystemProperties.COLOUR_MAP.get(ColourType.YELLOW));
		this.yellowBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		this.yellowBox.setPreferredSize(new Dimension(30, 30));

		this.orangeBox.setBackground(SystemProperties.COLOUR_MAP.get(ColourType.ORANGE));
		this.orangeBox.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		this.orangeBox.setPreferredSize(new Dimension(30, 30));

		this.letterLabel.setBackground(SystemProperties.WHITE);
		this.letterLabel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		this.blackLabel.setText("s");
		this.blackLabel.setFont(new Font("Arial", Font.PLAIN, 28));

		this.redLabel.setText("r");
		this.redLabel.setFont(new Font("Arial", Font.PLAIN, 28));

		this.greenLabel.setText("g");
		this.greenLabel.setFont(new Font("Arial", Font.PLAIN, 28));

		this.blueLabel.setText("b");
		this.blueLabel.setFont(new Font("Arial", Font.PLAIN, 28));

		this.yellowLabel.setText("y");
		this.yellowLabel.setFont(new Font("Arial", Font.PLAIN, 28));

		this.orangeLabel.setText("o");
		this.orangeLabel.setFont(new Font("Arial", Font.PLAIN, 28));

		this.setJMenuBar(stroopMenu);

		this.add(this.letterLabel, BorderLayout.CENTER);
		this.add(this.coloursPanel, BorderLayout.SOUTH);
		this.setTitle("Psychosomatik T"+ue+"bingen : Stroop Test");
		this.addComponentListener(this);
		this.setPreferredSize(new Dimension(800, 600));
		this.setLocation((SystemProperties.SCREEN_WIDTH / 2) - 400, (SystemProperties.SCREEN_HEIGHT / 2) - 300);
		this.pack();
	}

	public JLabel getLetterPanel()
	{
		return this.letterLabel;
	}

	
	public void componentHidden(ComponentEvent e)
	{
	}

	
	public void componentMoved(ComponentEvent e)
	{
	}

	
	public void componentResized(ComponentEvent e)
	{
		this.letterLabel.repaint();
	}

	
	public void componentShown(ComponentEvent e)
	{
	}

	
	public void update(Observable obs, Object obj)
	{
		if (obs instanceof KeySettings)
		{
			this.keyboardSettingsChanged();
		}

	}

	private void keyboardSettingsChanged()
	{
		this.blackLabel.setText(String.valueOf(keySettings.getColorKey(ColourType.BLACK).getKeyCharacter()));
		this.redLabel.setText(String.valueOf(keySettings.getColorKey(ColourType.RED).getKeyCharacter()));
		this.greenLabel.setText(String.valueOf(keySettings.getColorKey(ColourType.GREEN).getKeyCharacter()));
		this.blueLabel.setText(String.valueOf(keySettings.getColorKey(ColourType.BLUE).getKeyCharacter()));
		this.yellowLabel.setText(String.valueOf(keySettings.getColorKey(ColourType.YELLOW).getKeyCharacter()));
		this.orangeLabel.setText(String.valueOf(keySettings.getColorKey(ColourType.ORANGE).getKeyCharacter()));
	}

	public void hideLetter()
	{
		((LetterPanel) this.letterLabel).hideWord();
	}

	public void displayLetter(Word currentWord)
	{
		((LetterPanel) this.letterLabel).showWord(currentWord);
	}

	private void startTest()
	{
		this.testRunListener = new StroopTestRunController(eventBusService, keySettings,
				stroopTestProvider.getActiveStroopTest());
		eventBusService.register(testRunListener);

		this.setResizable(false);
		this.stroopMenu.disableMenu();
		this.addKeyListener(testRunListener);
	}

	private void stopTest()
	{
		this.setResizable(true);
		this.stroopMenu.enableMenu();
		this.removeKeyListener(testRunListener);
	}

	@Subscribe
	public void handleMessageEvent(MessageEvent event)
	{
		JOptionPane.showMessageDialog(event.getSource() == null ? this : event.getSource(), event.getMessage(),
				event.getTitle(), event.getType());
	}

	@Subscribe
	public void handleStartTestEvent(StartTestEvent event)
	{
		startTest();

		runner = new StroopTestRunner(event.getTestSuite(), eventBusService);

		runner.startTest();
	}

	@Subscribe
	public void handleStopTestEvent(StopTestEvent event)
	{
		stopTest();
		runner.stopTest();
	}

	@Subscribe
	public void handleTestFinishedEvent(TestFinishedStateEnterEvent event)
	{
		hideLetter();
		stopTest();
	}

	@Subscribe
	public void handleInitialStateEnterEvent(InitialStateEnterEvent event)
	{
		hideLetter();
	}

	@Subscribe
	public void handleAnalyzingStateEnterEvent(AnalyzingStateEnterEvent event)
	{
		displayLetter(event.getCurrentWord());
	}

	@Subscribe
	public void handleAnalyzingStateExitEvent(AnalyzingStateExitEvent event)
	{
		hideLetter();
	}

	@Subscribe
	public void handleSuccessfulWordEventEvent(SuccessfulWordEvent event)
	{
		runner.changeState(new InitialState(eventBusService));
	}

	@Subscribe
	public void handleSaveTestResultsEvent(SaveTestResultsEvent event)
	{
		int choice = JOptionPane.showConfirmDialog(this,
				"Der Test wurde beendet, wollen sie die Ergebnisse abspeichern?", "Test beendet",
				JOptionPane.YES_NO_OPTION);

		if (choice == JOptionPane.YES_OPTION)
		{

			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new ResultFileFilter());

			choice = fc.showSaveDialog(this);

			if (choice == JFileChooser.APPROVE_OPTION)
			{
				stroopTestFileService.saveStroopResult(fc.getSelectedFile(), runner.getTestResult());
			}
		}
	}
}
