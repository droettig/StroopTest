package controller;

import static utils.SystemProperties.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.ColourType;
import model.StroopTest;
import model.TestType;
import model.Word;
import model.WordFactory;
import services.EventBusService;
import view.ColourSpinner;
import view.GUISymbols;
import view.StroopTestCreator;
import events.StroopTestCreationCanceledEvent;
import events.StroopTestCreationFinishedEvent;
import events.messages.InfoMessageEvent;

public class StroopTestCreationController implements ActionListener
{
	private EventBusService eventBusService;
	
	private StroopTest stroopTest;
	private Map<ColourType, Map<ColourType, ColourSpinner>> wordSpinnerMap;

	private JCheckBox chooseColourTest;
	private JCheckBox chooseWordTest;
	private JTextArea commentArea;
	private JTextField creatorField;
	private StroopTestCreator creator;

	public StroopTestCreationController(StroopTest stroopTest, EventBusService eventBusService)
	{
		this.stroopTest = stroopTest;
		this.eventBusService = eventBusService;
	}

	public void init(StroopTestCreator creator, Map<ColourType, Map<ColourType, ColourSpinner>> wordSpinnerMap, JCheckBox chooseColourTest,
			JCheckBox chooseWordTest, JTextArea commentArea, JTextField creatorField)
	{
		this.creator = creator;
		this.wordSpinnerMap = wordSpinnerMap;
		this.chooseColourTest = chooseColourTest;
		this.chooseWordTest = chooseWordTest;
		this.commentArea = commentArea;
		this.creatorField = creatorField;
	}

	
	public void actionPerformed(ActionEvent a)
	{
		if (a.getSource() instanceof JCheckBox)
		{
			JCheckBox tmp = (JCheckBox) a.getSource();

			if (!this.chooseColourTest.isSelected() && !this.chooseWordTest.isSelected())
			{
				tmp.doClick();
				return;
			}

			if (tmp.getActionCommand().equals("COLOUR_TEST"))
			{
				if (tmp.isSelected())
				{
					this.chooseWordTest.setSelected(false);
				}
			}
			else if (tmp.getActionCommand().equals("WORD_TEST"))
			{
				if (tmp.isSelected())
				{
					this.chooseColourTest.setSelected(false);
				}
			}
		}

		else if (a.getSource() instanceof JButton)
		{
			String command = a.getActionCommand();

			if (command.equals("ACCEPT"))
			{
				if (chooseColourTest.isSelected() || chooseWordTest.isSelected())
				{
					List<Word> tmp = new ArrayList<Word>();

					for (ColourType colourTypeOfWord : wordSpinnerMap.keySet())
					{
						Map<ColourType, ColourSpinner> wordMap = wordSpinnerMap.get(colourTypeOfWord);

						for (ColourType colourOfFont : ColourType.values())
						{
							for (int k = 0; k < wordMap.get(colourOfFont).getValue(); k++)
							{
								tmp.add(WordFactory.createMixedColourWord(colourOfFont, colourTypeOfWord));
							}
						}
					}

					if (this.chooseColourTest.isSelected())
					{
						stroopTest = new StroopTest(tmp, TestType.TEST_COLOUR, this.creatorField.getText(),
								this.commentArea.getText());
					}
					else if (this.chooseWordTest.isSelected())
					{
						stroopTest = new StroopTest(tmp, TestType.TEST_WORD, this.creatorField.getText(),
								this.commentArea.getText());
					}

					stroopTest.shuffleWords();
					
					eventBusService.post(new StroopTestCreationFinishedEvent(stroopTest));
				}
				else
				{
					eventBusService.post(new InfoMessageEvent(creator, "Sie m"+ue+"ssen eine Testart ausw"+ae+"hlen."));
				}
			}
			else if (command.equals(GUISymbols.CANCEL_ACTION_COMMAND))
			{
				eventBusService.post(new StroopTestCreationCanceledEvent());
			}
		}
	}
}
