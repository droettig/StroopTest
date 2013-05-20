package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import com.google.inject.Singleton;

@Singleton
public class KeySettings extends Observable
{
	public static final ColourKey DEFAULT_BLACK_COLOUR_KEY = new ColourKey(ColourType.BLACK, 's');
	public static final ColourKey DEFAULT_RED_COLOUR_KEY = new ColourKey(ColourType.RED, 'r');
	public static final ColourKey DEFAULT_GREEN_COLOUR_KEY = new ColourKey(ColourType.GREEN, 'g');
	public static final ColourKey DEFAULT_BLUE_COLOUR_KEY = new ColourKey(ColourType.BLUE, 'b');
	public static final ColourKey DEFAULT_YELLOW_COLOUR_KEY = new ColourKey(ColourType.YELLOW, 'y');
	public static final ColourKey DEFAULT_ORANGE_COLOUR_KEY = new ColourKey(ColourType.ORANGE, 'o');
	
	private Map<ColourType, ColourKey> colourKeys;

	public KeySettings()
	{
		super();
		colourKeys = new HashMap<ColourType, ColourKey>();

		colourKeys.put(ColourType.BLACK, DEFAULT_BLACK_COLOUR_KEY);
		colourKeys.put(ColourType.RED, DEFAULT_RED_COLOUR_KEY);
		colourKeys.put(ColourType.GREEN, DEFAULT_GREEN_COLOUR_KEY);
		colourKeys.put(ColourType.BLUE, DEFAULT_BLUE_COLOUR_KEY);
		colourKeys.put(ColourType.YELLOW, DEFAULT_YELLOW_COLOUR_KEY);
		colourKeys.put(ColourType.ORANGE, DEFAULT_ORANGE_COLOUR_KEY);
	}

	public boolean changeKeySetting(ColourType colourType, Character newKey)
	{
		
		if(checkForDuplicateKeyCharacter(newKey))
		{
			return false;
		}
		
		colourKeys.get(colourType).setKeyCharacter(newKey);
		setChanged();
		notifyObservers();
		return true;
	}

	public ColourKey getColorKey(ColourType colourType)
	{
		return colourKeys.get(colourType);
	}

	public ColourKey getColorKey(Character keyCharacter)
	{
		for(ColourType c : colourKeys.keySet())
		{
			ColourKey colourKey = colourKeys.get(c);
			if(colourKey.getKeyCharacter().equals(keyCharacter))
			{
				return colourKey;
			}
		}
		return null;
	}
	
	public Map<ColourType, ColourKey> getColourKeys()
	{
		return colourKeys;
	}

	public void setColourKeys(Map<ColourType, ColourKey> colourKeys)
	{
		this.colourKeys = colourKeys;
		
		setChanged();
		notifyObservers();
	}

	public void revertDefaultSettings()
	{
		colourKeys.put(ColourType.BLACK, DEFAULT_BLACK_COLOUR_KEY);
		colourKeys.put(ColourType.RED, DEFAULT_RED_COLOUR_KEY);
		colourKeys.put(ColourType.GREEN, DEFAULT_GREEN_COLOUR_KEY);
		colourKeys.put(ColourType.BLUE, DEFAULT_BLUE_COLOUR_KEY);
		colourKeys.put(ColourType.YELLOW, DEFAULT_YELLOW_COLOUR_KEY);
		colourKeys.put(ColourType.ORANGE, DEFAULT_ORANGE_COLOUR_KEY);
	}

	private boolean checkForDuplicateKeyCharacter(Character key)
	{
		for(ColourType c :colourKeys.keySet())
		{
			if(colourKeys.get(c).getKeyCharacter().equals(key))
			{
				return true;
			}
		}
		
		return false;
	}

	
	public String toString()
	{
		return "KeySettings [colourKeys=" + colourKeys + "]";
	}
}
