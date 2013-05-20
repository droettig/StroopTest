package controller.menu;

import static utils.SystemProperties.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.StroopTest;
import services.EventBusService;
import services.StroopTestProvider;
import view.StroopTestCreator;

import com.google.inject.Inject;

import events.SaveStroopTestEvent;

public class CreateTestMenuController implements ActionListener
{
	private EventBusService eventBusService;
	private StroopTestProvider stroopTestProvider;

	@Inject
	public CreateTestMenuController(EventBusService eventBusService, StroopTestProvider stroopTestProvider)
	{
		this.eventBusService = eventBusService;
		this.stroopTestProvider = stroopTestProvider;
	}

	
	public void actionPerformed(ActionEvent e)
	{
		StroopTest stroopTest = stroopTestProvider.getActiveStroopTest();
		StroopTestCreator testSuiteCreator;
		
		if (stroopTest != null)
		{
			//FIXME send event + add MainWindow where null
			int choice = JOptionPane.showConfirmDialog(null,
					"Wenn Sie einen neuen Test anlegen wird der Alte gel"+oe+"scht, "
							+ "wollen Sie den alten Test vorher speichern?", "Test speichern?",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

			if (choice == JOptionPane.CANCEL_OPTION)
			{
				return;
			}

			if (choice == JOptionPane.YES_OPTION)
			{
				eventBusService.post(new SaveStroopTestEvent(stroopTest));
			}
			
			testSuiteCreator = new StroopTestCreator(eventBusService, stroopTestProvider, stroopTest);
		}
		else
		{
			testSuiteCreator = new StroopTestCreator(eventBusService, stroopTestProvider);
		}

		testSuiteCreator.showDialog();
	}

}
