package events;

import model.StroopTest;

public class StopTestEvent implements StroopEvent
{
	private StroopTest testSuite;

	public StopTestEvent(StroopTest testSuite)
	{
		this.testSuite = testSuite;
	}

	public StroopTest getTestSuite()
	{
		return testSuite;
	}

}
