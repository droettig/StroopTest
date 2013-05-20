package view;

import static utils.SystemProperties.ue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.ColourType;
import model.StroopTest;
import model.Word;
import model.WordFactory;
import net.miginfocom.swing.MigLayout;
import services.EventBusService;
import services.StroopTestProvider;
import utils.SystemProperties;

import com.google.common.eventbus.Subscribe;

import controller.StroopTestCreationController;
import events.StroopTestCreationCanceledEvent;
import events.StroopTestCreationFinishedEvent;

public class StroopTestCreatorPanel
{
	private Map<ColourType, Map<ColourType, ColourSpinner>> wordSpinnerMap;
	
	private JPanel tablePanel;
	private JPanel bottomPanel;
	private JPanel wholePanel;

	private JCheckBox chooseColourTest;
	private JCheckBox chooseWordTest;

	private JButton cancel, accept;

	private JLabel creatorLabel;
	private JLabel commentLabel;

	private JTextField creatorField;
	private JTextArea commentArea;
	private StroopTestCreator creator;
	private EventBusService eventBusService;
	private StroopTest stroopTest;

	StroopTestProvider stroopTestProvider;
	
	public StroopTestCreatorPanel(StroopTestCreator creator, StroopTestProvider stroopTestProvider,
			EventBusService eventBusService, StroopTest stroopTest)
	{
		this.wordSpinnerMap = new HashMap<ColourType, Map<ColourType, ColourSpinner>>();
		this.creator = creator;
		this.stroopTestProvider = stroopTestProvider;
		this.eventBusService = eventBusService;
		this.eventBusService.register(this);
		this.stroopTest = stroopTest;
		
		this.initComponents();
	}

	private void initComponents()
	{
		StroopTestCreationController controller = new StroopTestCreationController(stroopTest, eventBusService);
		
		this.tablePanel = new JPanel();
		this.bottomPanel = new JPanel();
		this.wholePanel = new JPanel();

		this.cancel = new JButton("Abbrechen");
		this.cancel.setActionCommand("CANCEL");
		this.cancel.addActionListener(controller);

		this.accept = new JButton("Test erstellen");
		this.accept.setActionCommand("ACCEPT");
		this.accept.addActionListener(controller);

		this.creatorLabel = new JLabel("Ersteller");
		this.commentLabel = new JLabel("Kommentare");

		this.creatorField = new JTextField();
		this.creatorField.setPreferredSize(new Dimension(100, 23));

		this.commentArea = new JTextArea();
		this.commentArea.setLineWrap(true);
		this.commentArea.setBorder(BorderFactory.createEtchedBorder());
		this.commentArea.setPreferredSize(new Dimension(100, 100));

		MigLayout layout = new MigLayout("wrap 7");

		this.tablePanel.setLayout(layout);

		Map<ColourType, Map<ColourType, Integer>> words = new HashMap<ColourType, Map<ColourType, Integer>>();

		for (ColourType colour : ColourType.values())
		{
			Map<ColourType, Integer> colourCounter = new HashMap<ColourType, Integer>();
			for(ColourType c : ColourType.values())
			{
				colourCounter.put(c, 0);
			}
			words.put(colour, colourCounter);
		}

		for (Word w : stroopTest.getWords())
		{
			int amount = words.get(w.getColourOfWord()).get(w.getColourOfFont());
			amount++;
			words.get(w.getColourOfWord()).put(w.getColourOfFont(), amount);
		}
		
		for (ColourType colourType : ColourType.values())
		{
			Map<ColourType, ColourSpinner> spinnerMap = new HashMap<ColourType, ColourSpinner>();
			Map<ColourType, Integer> counterMap = words.get(colourType);
			
			for (ColourType cc : ColourType.values())
			{
				ColourSpinner spinner = new ColourSpinner(colourType, cc);
				spinner.setValue(counterMap.get(cc));
				spinnerMap.put(cc, spinner);
			}

			wordSpinnerMap.put(colourType, spinnerMap);
		}
		
		for (ColourType colourType : ColourType.values())
		{
			JLabel label = new JLabel(WordFactory.getDescriptionOfColour(colourType));
			label.setFont(new Font("Arial", Font.PLAIN, 18));
			
			this.tablePanel.add(label,"span 1 2");

			for (ColourType cc : ColourType.values())
			{
				JPanel panel = new JPanel();
				panel.setBackground(SystemProperties.COLOUR_MAP.get(cc));
				panel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
				panel.setPreferredSize(new Dimension(35, 10));
				this.tablePanel.add(panel);
			}
			for (ColourType cc : ColourType.values())
			{
				this.tablePanel.add(wordSpinnerMap.get(colourType).get(cc).getSpinner());
			}
		}
		
		GridBagConstraints c = new GridBagConstraints();

		this.bottomPanel.setLayout(new GridBagLayout());

		c.gridx = 0;
		c.gridy = 0;
		
		this.chooseColourTest = new JCheckBox("Farbtest");
		this.chooseColourTest.setToolTipText("Die Farbe erkennen und das Wort ignorieren");
		this.chooseColourTest.addActionListener(controller);
		this.chooseColourTest.setActionCommand("COLOUR_TEST");
		this.bottomPanel.add(chooseColourTest, c);

		c.gridx = 1;
		c.gridy = 0;
		this.chooseWordTest = new JCheckBox("Worttest");
		this.chooseWordTest.setToolTipText("Das Wort erkennen und die Farbe ignorieren");
		this.chooseWordTest.addActionListener(controller);
		this.chooseWordTest.setActionCommand("WORD_TEST");
		this.bottomPanel.add(chooseWordTest, c);

		c.gridx = 3;
		c.gridy = 4;
		this.bottomPanel.add(cancel, c);

		c.gridx = 2;
		c.gridy = 4;
		this.bottomPanel.add(accept, c);

		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		this.bottomPanel.add(creatorLabel, c);

		c.gridx = 0;
		c.gridy = 1;
		this.bottomPanel.add(creatorField, c);

		c.gridx = 0;
		c.gridy = 2;
		this.bottomPanel.add(commentLabel, c);

		c.gridx = 0;
		c.gridy = 3;
		this.bottomPanel.add(commentArea, c);

		this.tablePanel.setBorder(BorderFactory.createTitledBorder("Wort"+ue+"bersicht"));
		this.bottomPanel.setBorder(BorderFactory.createTitledBorder("Abschluß"));

		this.wholePanel.setLayout(new GridBagLayout());

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		this.wholePanel.add(tablePanel, c);

		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.wholePanel.add(bottomPanel, c);
		
		controller.init(creator, wordSpinnerMap, chooseColourTest, chooseWordTest, commentArea, creatorField);
	}

	public JPanel getPanel()
	{
		return this.wholePanel;
	}

	public void setChooseColourTest(boolean selected)
	{
		this.chooseColourTest.setSelected(selected);
	}

	public void setChooseWordTest(boolean selected)
	{
		this.chooseWordTest.setSelected(selected);
	}

	public void setComment(String comment)
	{
		this.commentArea.setText(comment);
	}

	public void setCreator(String creator)
	{
		this.creatorField.setText(creator);
	}
	
	@Subscribe
	public void handleStroopTestCreationFinishedEvent(StroopTestCreationFinishedEvent event)
	{
		stroopTestProvider.setActiveStroopTest(event.getStroopTest());
		finishDialog(true);
	}

	@Subscribe
	public void handleStroopTestCreationCanceledEvent(StroopTestCreationCanceledEvent event)
	{
		finishDialog(false);
	}

	private void finishDialog(boolean successfulCreation)
	{
		creator.setTestCreated(successfulCreation);
		creator.dispose();
	}
}