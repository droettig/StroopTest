package events;

import model.ColourType;

public class UpdateKeyTextfieldEvent implements StroopEvent
{
	private ColourType currentEditableColourType;

	public UpdateKeyTextfieldEvent(ColourType currentEditableColourType)
	{
		this.currentEditableColourType = currentEditableColourType;
	}

	public ColourType getCurrentEditableColourType()
	{
		return currentEditableColourType;
	}
}
