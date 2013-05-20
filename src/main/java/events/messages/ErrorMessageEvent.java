package events.messages;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ErrorMessageEvent extends MessageEvent
{
	public ErrorMessageEvent(Component source, Object message)
	{
		super(source, message, "Fehler", JOptionPane.ERROR_MESSAGE);
	}
	
	public ErrorMessageEvent(Object message)
	{
		super(message, "Fehler", JOptionPane.ERROR_MESSAGE);
	}
}
