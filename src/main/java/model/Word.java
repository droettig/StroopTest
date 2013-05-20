package model;

import java.io.Serializable;

public class Word implements Serializable
{
	private static final long serialVersionUID = -1123098797888022841L;

	private ColourType colourOfWord;
	private ColourType colourOfFont;

	protected Word()
	{
	}

	public ColourType getColourOfFont()
	{
		return this.colourOfFont;
	}

	public ColourType getColourOfWord()
	{
		return this.colourOfWord;
	}

	protected void setColourOfFont(ColourType colourType)
	{
		this.colourOfFont = colourType;
	}

	protected void setColourOfWord(ColourType colourType)
	{
		this.colourOfWord = colourType;
	}

	public boolean colourIsCorrect(ColourType selectedColourType)
	{
		return selectedColourType == colourOfFont;
	}

	public boolean wordIsCorrect(ColourType selectedColourType)
	{
		return selectedColourType == colourOfWord;
	}

	public boolean isInitialized()
	{
		return colourOfFont != null && colourOfWord != null;
	}

	
	public String toString()
	{
		return "Word [word=" + colourOfWord + ", colour=" + colourOfFont + "]";
	}
}
