package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StroopTestFactory
{
	public static StroopTest createPlainColourTest(int wordSize)
	{
		List<Word> words = new ArrayList<Word>(wordSize);

		for (int i = 0; i < wordSize; i++)
		{
			words.add(WordFactory.createRandomPlainColourWord());
		}

		return new StroopTest(words, TestType.TEST_WORD);
	}

	public static StroopTest createOneColourTest(int wordSize)
	{
		List<Word> words = new ArrayList<Word>(wordSize);

		for (int i = 0; i < wordSize; i++)
		{
			words.add(WordFactory.createRandomColourWord());
		}
		return new StroopTest(words, TestType.TEST_WORD);
	}

	/**
	 * 
	 * @param wordSize
	 *            amount of words you want to use for that test
	 * @param type
	 * <br>
	 *            type = 0 means you test for the font colour <br>
	 *            type = 1 means you test for the word itself,
	 * @return a fully working TestSuite Object
	 */
	public static StroopTest createMixedColourTest(int wordSize, TestType type)
	{
		List<Word> words = new ArrayList<Word>(wordSize);

		for (int i = 0; i < wordSize; i++)
		{
			words.add(WordFactory.createRandomMixedColourWord());
		}

		return new StroopTest(words, type, "RANDOM", "");
	}

	public static StroopTest createRandomColourTest(int wordSize, TestType type)
	{
		List<Word> words = new ArrayList<Word>(wordSize);
		Random randomizer = new Random(System.currentTimeMillis());

		for (int i = 0; i < wordSize; i++)
		{
			int next = randomizer.nextInt(2);
			switch (next)
			{
				case 0 :
				{
					words.add(WordFactory.createRandomPlainColourWord());
					break;
				}
				case 1 :
				{
					words.add(WordFactory.createRandomColourWord());
					break;
				}
				case 2 :
				{
					words.add(WordFactory.createRandomMixedColourWord());
					break;
				}
			}
		}
		return new StroopTest(words, type, "RANDOM", "");
	}

	public static StroopTest createNewTest(Word[] words)
	{
		// TODO create empty testsuite for the user, to set it up himself
		return null;
	}
}
