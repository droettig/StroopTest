package model;

import static utils.SystemProperties.ae;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import model.state.AnalyzingState;
import model.state.TestState;

import org.apache.commons.lang3.time.StopWatch;

public class StroopTest
{
	private TestType testType;
	private List<Word> words;
	private String creator;
	private String comment;

	private Word currentWord;
	private int wordIndex;
	private TestState currentState;
	private List<SingleWordResult> singleWordResults;
	private StopWatch stopwatch;

	private StroopTestResult testResult;

	public StroopTest(List<Word> words, TestType type)
	{
		this(words, type, "Zuf"+ae+"llig erstellter Test", "Erstellt: " + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(Calendar.getInstance().getTime()));
	}

	public StroopTest(List<Word> words, TestType type, String creator, String comment)
	{
		this.creator = creator;
		this.comment = comment;
		this.words = words;
		this.testType = type;
		this.wordIndex = 0;
		this.stopwatch = new StopWatch();
		this.singleWordResults = new ArrayList<SingleWordResult>();
		this.testResult = new StroopTestResult(singleWordResults);
	}

	public TestState getCurrentState()
	{
		return currentState;
	}

	public void addWord(Word newWord)
	{
		words.add(newWord);
	}

	public boolean checkResult(ColourType colourType)
	{
		if (this.currentState instanceof AnalyzingState)
		{
			if (this.testType == TestType.TEST_COLOUR)
			{
				return this.currentWord.colourIsCorrect(colourType);
			}

			else if (this.testType == TestType.TEST_WORD)
			{
				return this.currentWord.wordIsCorrect(colourType);
			}
		}
		return false;
	}

	public Word getCurrentWord()
	{
		return this.currentWord;
	}

	public Word getNextWord()
	{
		if (this.wordIndex < this.words.size())
		{
			return this.words.get(wordIndex++);
		}
		else
		{
			return null;
		}
	}

	public TestType getTestType()
	{
		return this.testType;
	}

	public List<Word> getWords()
	{
		return this.words;
	}

	public void setTestType(TestType testType)
	{
		this.testType = testType;
	}

	public void shuffleWords()
	{
		Collections.shuffle(Arrays.asList(this.words));
	}

	public String getCreator()
	{
		return this.creator;
	}
	
	public String getComment()
	{
		return this.comment;
	}

	public int getWordIndex()
	{
		return wordIndex;
	}

	public void resetWordIndex()
	{
		wordIndex = 0;
	}

	public void setCurrentState(TestState state)
	{
		this.currentState = state;
	}

	public void advanceToNextWord()
	{
		currentWord = getNextWord();
		stopwatch.start();
	}

	public void measureTime()
	{
		stopwatch.stop();
		
		long ms = stopwatch.getTime();

		long seconds = (ms / 1000);
		
		singleWordResults.add(new SingleWordResult(currentWord, new ReactionTime(seconds, ms)));

		stopwatch.reset();
	}
	
	public StroopTestResult getTestResult()
	{
		return testResult;
	}
}