package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.KeySettings;
import services.EventBusService;
import view.KeySettingsDialog;

import com.google.inject.Inject;

public class EditKeySettingsMenuController implements ActionListener
{
	private KeySettingsDialog keySettingsDialog;

	@Inject
	public EditKeySettingsMenuController(KeySettings keySettings, EventBusService eventBusService)
	{
		this.keySettingsDialog = new KeySettingsDialog(keySettings, eventBusService);
	}

	
	public void actionPerformed(ActionEvent e)
	{
		keySettingsDialog.setVisible(true);
	}
}
