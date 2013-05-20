package events;

import model.StroopTest;

public class SaveStroopTestEvent implements StroopEvent
{
	private StroopTest stroopTest;
	
	public SaveStroopTestEvent(StroopTest stroopTest)
	{
		this.stroopTest = stroopTest;
	}

	public StroopTest getStroopTest()
	{
		return stroopTest;
	}

}
