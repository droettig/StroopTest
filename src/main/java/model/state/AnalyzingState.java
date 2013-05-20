package model.state;

import model.StroopTest;
import services.EventBusService;
import events.state.AnalyzingStateEnterEvent;
import events.state.AnalyzingStateExitEvent;

public class AnalyzingState extends TestState
{
	private EventBusService eventBusService;
	private StroopTest test;
	
	public AnalyzingState(StroopTest test, EventBusService eventBusService)
	{
		this.test = test;
		this.eventBusService = eventBusService;
	}

	
	public void enterAction()
	{
		test.advanceToNextWord();

		eventBusService.post(new AnalyzingStateEnterEvent(test.getCurrentWord()));
	}

	
	public void exitAction()
	{
		test.measureTime();
		
		eventBusService.post(new AnalyzingStateExitEvent());
	}

}
