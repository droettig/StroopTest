package model;

public class ReactionTime
{
	private Long seconds;
	private Long milliseconds;

	public ReactionTime(Long seconds, Long milliseconds)
	{
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}

	public Long getSeconds()
	{
		return seconds;
	}

	public Long getMilliseconds()
	{
		return milliseconds;
	}

	
	public String toString()
	{
		return "ReactionTime [seconds=" + seconds + ", milliseconds=" + milliseconds + "]";
	}
	
}
