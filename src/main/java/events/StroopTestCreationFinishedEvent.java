package events;

import model.StroopTest;

public class StroopTestCreationFinishedEvent implements StroopEvent
{
	private StroopTest stroopTest;

	public StroopTestCreationFinishedEvent(StroopTest stroopTest)
	{
		this.stroopTest = stroopTest;
	}

	public StroopTest getStroopTest()
	{
		return stroopTest;
	}

}
