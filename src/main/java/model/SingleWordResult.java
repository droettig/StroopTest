package model;

public class SingleWordResult
{
	private Word word;
	private ReactionTime time;

	public SingleWordResult(Word word, ReactionTime time)
	{
		this.word = word;
		this.time = time;
	}

	public Word getWord()
	{
		return word;
	}

	public ReactionTime getTime()
	{
		return time;
	}

	
	public String toString()
	{
		return "[word=" + word + "]";
	}
}
