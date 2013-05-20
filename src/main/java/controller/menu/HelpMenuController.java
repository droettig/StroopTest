package controller.menu;

import static utils.SystemProperties.*;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import com.google.inject.Inject;

import services.EventBusService;
import events.messages.ErrorMessageEvent;

public class HelpMenuController implements ActionListener
{
	private EventBusService eventBusService;

	@Inject
	public HelpMenuController(EventBusService eventBusService)
	{
		this.eventBusService = eventBusService;
	}

	
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			Desktop.getDesktop().open(new File("doc/Anleitung_Stroop_Test.pdf"));
		}
		catch (IOException ex)
		{
			eventBusService.post(new ErrorMessageEvent("Fehler beim "+Oe+"ffnen der Datei"));
		}
	}

}
