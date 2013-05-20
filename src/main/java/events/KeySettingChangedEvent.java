package events;

import model.ColourType;

public class KeySettingChangedEvent implements StroopEvent
{
	private char newCharacter;
	private ColourType currentEditableColourType;

	public KeySettingChangedEvent(ColourType currentEditableColourType, char newCharacter)
	{
		this.currentEditableColourType = currentEditableColourType;
		this.newCharacter = newCharacter;
	}

	public ColourType getCurrentEditableColourType()
	{
		return currentEditableColourType;
	}

	public char getNewCharacter()
	{
		return newCharacter;
	}
	
	
}
