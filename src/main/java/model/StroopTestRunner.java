package model;

import model.state.AnalyzingState;
import model.state.InitialState;
import model.state.TestFinished;
import model.state.TestState;
import services.EventBusService;
import events.SaveTestResultsEvent;


public class StroopTestRunner implements Runnable
{
	private EventBusService eventBusService;
	private StroopTest test;
	
	public StroopTestRunner(StroopTest testSuite, EventBusService eventBusService)
	{
		this.test = testSuite;
		this.eventBusService = eventBusService;
	}

	public void run()
	{
		test.setCurrentState(new InitialState(eventBusService));
		
		while (!(test.getCurrentState() instanceof TestFinished))
		{
			if (test.getCurrentState() instanceof InitialState)
			{
				if (test.getWordIndex() < test.getWords().size())
				{
					// Giving some time between letters
					try
					{
						Thread.sleep(2000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					
					changeState(new AnalyzingState(test, eventBusService));
				}
				else
				{
					changeState(new TestFinished(eventBusService));
				}
			}
		}

		resetTest();

		eventBusService.post(new SaveTestResultsEvent());
		
		System.out.println("Test beendet");

	}

	public void startTest()
	{
		Thread t = new Thread(this);
		t.start();
	}
	
	public void stopTest()
	{
		changeState(new TestFinished(eventBusService));
	}

	public void resetTest()
	{
		changeState(new InitialState(eventBusService));
		test.resetWordIndex();
	}
	
	
	public void changeState(TestState state)
	{
		test.getCurrentState().exitAction();
		test.setCurrentState(state);
		test.getCurrentState().enterAction();
	}

	public StroopTestResult getTestResult()
	{
		return test.getTestResult();
	}
}
