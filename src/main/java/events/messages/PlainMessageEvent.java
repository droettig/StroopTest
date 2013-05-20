package events.messages;

import java.awt.Component;

import javax.swing.JOptionPane;

public class PlainMessageEvent extends MessageEvent
{
	public PlainMessageEvent(Component source, Object message)
	{
		super(source, message, "Information", JOptionPane.PLAIN_MESSAGE);
	}
	
	public PlainMessageEvent(Object message)
	{
		super(message, "Information", JOptionPane.PLAIN_MESSAGE);
	}
}
