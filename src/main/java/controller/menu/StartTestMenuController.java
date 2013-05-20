package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.google.inject.Inject;

import model.StroopTest;

import services.EventBusService;
import services.StroopTestProvider;
import events.StartTestEvent;
import events.messages.WarningMessageEvent;

public class StartTestMenuController implements ActionListener
{
	private EventBusService eventBusService;
	private StroopTestProvider stroopTestProvider;
	
	@Inject
	public StartTestMenuController(EventBusService eventBusService, StroopTestProvider stroopTestProvider)
	{
		this.eventBusService = eventBusService;
		this.stroopTestProvider = stroopTestProvider;
	}

	
	public void actionPerformed(ActionEvent e)
	{
		StroopTest activeStroopTest = stroopTestProvider.getActiveStroopTest();
		
		if (activeStroopTest != null)
		{
			eventBusService.post(new StartTestEvent(activeStroopTest));
		}
		else
		{
			eventBusService.post(new WarningMessageEvent("Es wurde noch kein Test erstellt bzw. geladen!"));
			System.err.println("Noch keine TestSuite vorhanden");
		}
	}
}
