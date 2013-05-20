package events.messages;

import java.awt.Component;

import javax.swing.JOptionPane;

public class InfoMessageEvent extends MessageEvent
{
	public InfoMessageEvent(Component source, Object message)
	{
		super(source, message, "Hinweis", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public InfoMessageEvent(Object message)
	{
		super(message, "Hinweis", JOptionPane.INFORMATION_MESSAGE);
	}
}
