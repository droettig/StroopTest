package utils;

import java.io.Serializable;
import java.util.List;

import model.TestType;
import model.Word;

public class TestSuiteConfiguration implements Serializable
{
	private TestType type;
	private List<Word> words;
	private String comment;
	private String creator;
	
	public String getComment()
	{
		return this.comment;
	}

	public String getCreator()
	{
		return this.creator;
	}

	public TestSuiteConfiguration(List<Word> words, TestType type, String creator, String comment)
	{
		this.type = type;
		this.words = words;
		this.creator = creator;
		this.comment = comment;
	}
	
	public TestType getType()
	{
		return this.type;
	}

	public List<Word> getWords()
	{
		return this.words;
	}	
}
