package model;

import java.util.List;

public class StroopTestResult
{
	private List<SingleWordResult> singleWordResult;

	public StroopTestResult(List<SingleWordResult> singleWordResult)
	{
		this.singleWordResult = singleWordResult;
	}
	
	public String getStringRepresentation()
	{
		StringBuffer sb = new StringBuffer();
		
		for (SingleWordResult result : singleWordResult)
		{
			sb.append(result.getWord());
			sb.append(" "+result.getTime());
			sb.append("\r\n");
		}
		
		return sb.toString();
	}
}
