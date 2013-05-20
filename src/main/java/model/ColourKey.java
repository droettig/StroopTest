package model;

public class ColourKey
{
	private ColourType colourType;
	private Character keyCharacter;

	protected ColourKey(ColourType colourType, Character keyCharacter)
	{
		this.colourType = colourType;
		this.keyCharacter = keyCharacter;
	}

	public ColourType getColourType()
	{
		return colourType;
	}

	public Character getKeyCharacter()
	{
		return keyCharacter;
	}

	public void setKeyCharacter(Character keyCharacter)
	{
		this.keyCharacter = keyCharacter;
	}

}
