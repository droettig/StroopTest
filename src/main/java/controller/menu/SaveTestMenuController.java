package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import model.StroopTest;
import services.EventBusService;
import services.StroopTestFileService;
import services.StroopTestProvider;
import utils.StroopFileFilter;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

import events.SaveStroopTestEvent;
import events.messages.WarningMessageEvent;

public class SaveTestMenuController implements ActionListener
{
	private EventBusService eventBusService;
	private StroopTestProvider stroopTestProvider;
	private StroopTestFileService stroopTestFileWriter;
	private JFileChooser saveTestFileChooser;

	@Inject
	public SaveTestMenuController(EventBusService eventBusService, StroopTestProvider stroopTestProvider, StroopTestFileService stroopTestFileWriter)
	{
		this.eventBusService = eventBusService;
		this.stroopTestProvider = stroopTestProvider;
		this.stroopTestFileWriter = stroopTestFileWriter;
		this.saveTestFileChooser = new JFileChooser();
		saveTestFileChooser.setFileFilter(new StroopFileFilter());
	}

	
	public void actionPerformed(ActionEvent e)
	{
		saveTest(null);
	}

	private void saveTest(StroopTest stroopTest)
	{
		if(stroopTest == null)
		{
			stroopTest = stroopTestProvider.getActiveStroopTest();
		}
		
		File savePath;

		if (stroopTest != null)
		{
			//FIXME send event + add MainWindow where null
			int choice = saveTestFileChooser.showSaveDialog(null);

			if (choice == JFileChooser.APPROVE_OPTION)
			{
				savePath = saveTestFileChooser.getSelectedFile();
			}
			else
			{
				return;
			}

			stroopTestFileWriter.saveStroopTest(stroopTest, savePath);
		}
		else
		{
			eventBusService.post(new WarningMessageEvent("Es wurde noch kein Test erstellt bzw. geladen!"));
			System.err.println("Noch keine TestSuite vorhanden");
		}
	}

	@Subscribe
	public void handleSaveStroopTestEvent(SaveStroopTestEvent event)
	{
		saveTest(event.getStroopTest());
	}
}
