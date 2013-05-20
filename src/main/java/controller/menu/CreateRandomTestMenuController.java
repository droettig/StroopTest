package controller.menu;

import static utils.SystemProperties.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;

import model.StroopTest;
import model.StroopTestFactory;
import model.TestType;
import services.EventBusService;
import services.StroopTestProvider;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import events.SaveStroopTestEvent;
import events.messages.InfoMessageEvent;

@Singleton
public class CreateRandomTestMenuController implements ActionListener
{
	private EventBusService eventBusService;
	private StroopTestProvider stroopTestProvider;

	@Inject
	public CreateRandomTestMenuController(EventBusService eventBusService, StroopTestProvider stroopTestProvider)
	{
		this.eventBusService = eventBusService;
		this.stroopTestProvider = stroopTestProvider;
	}

	private void createRandomTestSuite(final int i)
	{
		StroopTest stroopTest = stroopTestProvider.getActiveStroopTest();

		if (stroopTest != null)
		{
			// FIXME send event + add MainWindow where null
			int choice = JOptionPane.showConfirmDialog(null,
					"Wenn Sie einen neuen Test anlegen wird der Alte gel"+oe+"scht, "
							+ "wollen Sie den alten Test vorher speichern?", "Test speichern?",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

			if (choice == JOptionPane.CANCEL_OPTION)
				return;

			if (choice == JOptionPane.YES_OPTION)
			{
				eventBusService.post(new SaveStroopTestEvent(stroopTest));
			}
		}

		int amount;

		switch (i)
		{
			case 0 :
			{
				amount = new Random().nextInt(120);
				stroopTest = StroopTestFactory.createPlainColourTest(amount);
				break;
			}
			case 1 :
			{
				amount = new Random().nextInt(120);
				stroopTest = StroopTestFactory.createOneColourTest(amount);
				break;
			}
			case 2 :
			{
				amount = new Random().nextInt(250);
				stroopTest = StroopTestFactory.createMixedColourTest(amount, TestType.TEST_WORD);
				break;
			}
			case 3 :
			{
				amount = new Random().nextInt(250);
				stroopTest = StroopTestFactory.createMixedColourTest(amount, TestType.TEST_COLOUR);
				break;
			}
		}

		stroopTestProvider.setActiveStroopTest(stroopTest);
		eventBusService.post(new InfoMessageEvent("Der Test wurde erfolgreich erstellt."));
	}

	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();

		if (command.equals("CREATE_WORD_BLACK"))
		{
			this.createRandomTestSuite(0);
		}

		else if (command.equals("CREATE_WORD_COLOUR"))
		{
			this.createRandomTestSuite(1);
		}

		else if (command.equals("CREATE_WORD_RANDOM"))
		{
			this.createRandomTestSuite(2);
		}

		else if (command.equals("CREATE_COLOUR_RANDOM"))
		{
			this.createRandomTestSuite(3);
		}

	}

}
