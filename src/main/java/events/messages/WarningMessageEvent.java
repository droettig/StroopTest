package events.messages;

import java.awt.Component;

import javax.swing.JOptionPane;

public class WarningMessageEvent extends MessageEvent
{
	public WarningMessageEvent(Component source, Object message)
	{
		super(source, message, "Warnung", JOptionPane.WARNING_MESSAGE);
	}
	
	public WarningMessageEvent(Object message)
	{
		super(message, "Warnung", JOptionPane.WARNING_MESSAGE);
	}
}
