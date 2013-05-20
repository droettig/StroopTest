package model;

import static utils.SystemProperties.*;
import java.util.Random;

/**
 * WordFactory creates Words ready to be used, the <br>
 * integer values of the colours are defined as follows: <br>
 * BLACK = 0 <br>
 * RED = 1 <br>
 * GREEN = 2 <br>
 * BLUE = 3 <br>
 * YELLOW = 4 <br>
 * ORANGE = 5; <br>
 * NUMBER_OF_COLOURS hence is 6, but can be extended here
 */
public class WordFactory
{
	public static final int NUMBER_OF_COLOURS = ColourType.values().length;

	private static int[][] COLOUR_COUNTER = new int[NUMBER_OF_COLOURS][NUMBER_OF_COLOURS];

	public void resetColourCounter()
	{
		for (int i = 0; i < NUMBER_OF_COLOURS; i++)
		{
			for (int j = 0; j < NUMBER_OF_COLOURS; j++)
			{
				COLOUR_COUNTER[i][j] = 0;
			}
		}
	}

	public static String getDescriptionOfColour(ColourType colourType)
	{
		switch (colourType)
		{
			case BLACK :
			{
				return "Schwarz";
			}
			case RED :
			{
				return "Rot";
			}
			case GREEN :
			{
				return "Gr"+ue+"n";
			}
			case BLUE :
			{
				return "Blau";
			}
			case YELLOW :
			{
				return "Gelb";
			}
			case ORANGE :
			{
				return "Orange";
			}
			default :
				return "-";
		}
	}

	private static Random randomizer = new Random(System.currentTimeMillis());

	/**
	 * Creates a Word with its meaning defined by wordColour, and font colour
	 * black
	 * 
	 * @param wordColour
	 *            is the actual colour the word describes
	 * @return Word representation of that word
	 */
	public static Word createPlainColourWord(ColourType wordColour)
	{
		Word word = new Word();
		word.setColourOfFont(ColourType.BLACK);
		word.setColourOfWord(wordColour);

		return word;
	}

	/**
	 * Creates a Word with its meaning randomly defined, and font colour black
	 * 
	 * @return Word representation of that word
	 * @throws InterruptedException
	 */
	public static Word createRandomPlainColourWord()
	{
		Word word = new Word();

		int index = randomizer.nextInt(ColourType.values().length);

		word.setColourOfFont(ColourType.BLACK);
		word.setColourOfWord(ColourType.values()[index]);

		return word;
	}

	/**
	 * Creates a Word with its meaning and font colour both defined by colour
	 * 
	 * @param colour
	 *            the meaning and font of the colour
	 * @return Word representation of that word
	 */
	public static Word createOneColourWord(ColourType colour)
	{
		Word word = new Word();

		word.setColourOfFont(colour);
		word.setColourOfWord(colour);

		return word;
	}

	/**
	 * Creates a Word with its meaning and font colour the same but randomly
	 * chosen
	 * 
	 * @return Word representation of that word
	 */
	public static Word createRandomColourWord()
	{
		Word word = new Word();

		int index = randomizer.nextInt(ColourType.values().length);

		word.setColourOfFont(ColourType.values()[index]);
		word.setColourOfWord(ColourType.values()[index]);

		return word;
	}

	/**
	 * Creates a Word with its meaning defined by wordColour and font defined by
	 * fontColour
	 * 
	 * @param colourOfFont
	 *            the actual font colour in which the word is displayed
	 * @param colourOfWord
	 *            the actual meaning of the word
	 * @return Word representation of that word
	 */
	public static Word createMixedColourWord(ColourType colourOfFont, ColourType colourOfWord)
	{
		Word word = new Word();
		word.setColourOfFont(colourOfFont);
		word.setColourOfWord(colourOfWord);

		return word;
	}

	/**
	 * Creates a randomly mixed colour of both font and meaning
	 * 
	 * @return Word representation of that word
	 */
	public static Word createRandomMixedColourWord()
	{
		Word word = new Word();

		int indexFontColour = randomizer.nextInt(ColourType.values().length);
		int indexWordColour;

		word.setColourOfFont(ColourType.values()[indexFontColour]);

		do
		{
			indexWordColour = randomizer.nextInt(ColourType.values().length);
		}
		while (indexFontColour == indexWordColour);

		word.setColourOfWord(ColourType.values()[indexWordColour]);

		return word;
	}
}
