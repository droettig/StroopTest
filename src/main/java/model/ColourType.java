package model;

public enum ColourType
{
	BLACK("black.key"),
	RED("red.key"),
	GREEN("green.key"),
	BLUE("blue.key"),
	YELLOW("yellow.key"),
	ORANGE("orange.key");
	
	private String colourTypeName;
	
	ColourType(String colourTypeName)
	{
		this.colourTypeName = colourTypeName;
	}

	public String getColourTypeName()
	{
		return colourTypeName;
	}
}
