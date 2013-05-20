package events;

import model.StroopTest;

public class StartTestEvent implements StroopEvent
{
	private StroopTest testSuite;

	public StartTestEvent(StroopTest testSuite)
	{
		this.testSuite = testSuite;
	}

	public StroopTest getTestSuite()
	{
		return testSuite;
	}

}
