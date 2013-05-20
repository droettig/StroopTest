package events.messages;

import java.awt.Component;

import events.StroopEvent;

public class MessageEvent implements StroopEvent
{
	private Component source;
	private Object message;
	private String title;
	private int type;

	public MessageEvent(Object message, String title, int type)
	{
		this.message = message;
		this.title = title;
		this.type = type;
	}
	
	public MessageEvent(Component source, Object message, String title, int type)
	{
		this.source = source;
		this.message = message;
		this.title = title;
		this.type = type;
	}

	public Component getSource()
	{
		return source;
	}

	public Object getMessage()
	{
		return message;
	}

	public String getTitle()
	{
		return title;
	}

	public int getType()
	{
		return type;
	}

}
