package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.StroopTest;
import services.EventBusService;
import services.StroopTestProvider;
import view.StroopTestCreator;

import com.google.inject.Inject;

import events.messages.ErrorMessageEvent;

public class EditTestMenuController implements ActionListener
{
	private EventBusService eventBusService;
	private StroopTestProvider stroopTestProvider;

	@Inject
	public EditTestMenuController(EventBusService eventBusService, StroopTestProvider stroopTestProvider)
	{
		this.eventBusService = eventBusService;
		this.stroopTestProvider = stroopTestProvider;
	}

	
	public void actionPerformed(ActionEvent e)
	{
		StroopTest stroopTest = stroopTestProvider.getActiveStroopTest();
		
		if (stroopTest != null)
		{
			StroopTestCreator testSuiteCreator = new StroopTestCreator(eventBusService, stroopTestProvider, stroopTest);
			testSuiteCreator.showDialog();
		}
		else
		{
			eventBusService.post(new ErrorMessageEvent("Es wurde noch kein Test erstellt bzw. geladen!"));
			System.err.println("Noch keine TestSuite vorhanden");
		}
	}

}
