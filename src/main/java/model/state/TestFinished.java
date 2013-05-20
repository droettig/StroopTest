package model.state;

import services.EventBusService;
import events.state.TestFinishedStateEnterEvent;


public class TestFinished extends TestState
{
	private EventBusService eventBusService;
	
	public TestFinished(EventBusService eventBusService)
	{
		this.eventBusService = eventBusService;
	}

	
	public void enterAction()
	{
		eventBusService.post(new TestFinishedStateEnterEvent());
	}

	
	public void exitAction()
	{
		
	}
}
