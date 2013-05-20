package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.StroopTest;

import com.google.inject.Inject;

import services.EventBusService;
import services.StroopTestProvider;

import events.StopTestEvent;

public class StopTestMenuController implements ActionListener
{
	private EventBusService eventBusService;
	private StroopTestProvider stroopTestProvider;
	
	@Inject
	public StopTestMenuController(EventBusService eventBusService, StroopTestProvider stroopTestProvider)
	{
		this.eventBusService = eventBusService;
		this.stroopTestProvider = stroopTestProvider;
	}

	
	public void actionPerformed(ActionEvent e)
	{
		StroopTest activeStroopTest = stroopTestProvider.getActiveStroopTest();
		
		if (activeStroopTest != null)
		{
			eventBusService.post(new StopTestEvent(activeStroopTest));
		}
	}
}
