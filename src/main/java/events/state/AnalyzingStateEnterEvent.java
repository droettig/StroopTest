package events.state;

import model.Word;
import events.StroopEvent;

public class AnalyzingStateEnterEvent implements StroopEvent
{
	private Word currentWord;

	public AnalyzingStateEnterEvent(Word currentWord)
	{
		this.currentWord = currentWord;
	}

	public Word getCurrentWord()
	{
		return currentWord;
	}
}
