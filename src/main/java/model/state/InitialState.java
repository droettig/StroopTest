package model.state;

import services.EventBusService;
import events.state.InitialStateEnterEvent;
import events.state.InitialStateExitEvent;

public class InitialState extends TestState
{
	private EventBusService eventBusService;
	
	public InitialState(EventBusService eventBusService)
	{
		this.eventBusService = eventBusService;
	}
	
	
	public void enterAction()
	{
		eventBusService.post(new InitialStateEnterEvent());
	}

	
	public void exitAction()
	{
		eventBusService.post(new InitialStateExitEvent());
	}

}
