package controller.menu;

import static utils.SystemProperties.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.text.html.HTMLEditorKit;

import com.google.inject.Inject;

import services.EventBusService;

import events.messages.PlainMessageEvent;

public class AboutMenuController implements ActionListener
{
	private EventBusService eventBusService;
	
	@Inject
	public AboutMenuController(EventBusService eventBusService)
	{
		this.eventBusService = eventBusService;
	}

	public void actionPerformed(ActionEvent e)
	{
		String message = "<html><center><b>Stroop Test</b>"
				+ "<br><br></br><i>Written by Dirk R"+oe+"ttig, August 2009</i><br></br>"
				+ "<i>Version: 1.0</i></center></html>";

		JTextPane area = new JTextPane();
		area.setEditorKit(new HTMLEditorKit());
		area.setPreferredSize(new Dimension(150, 150));
		area.setText(message);
		area.setEditable(false);

		eventBusService.post(new PlainMessageEvent(area));
	}
}
