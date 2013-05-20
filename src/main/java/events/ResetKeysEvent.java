package events;

import model.KeySettings;

public class ResetKeysEvent implements StroopEvent
{
	private KeySettings keySettings;

	public ResetKeysEvent(KeySettings keySettings)
	{
		this.keySettings = keySettings;
	}

	public KeySettings getKeySettings()
	{
		return keySettings;
	}

}
