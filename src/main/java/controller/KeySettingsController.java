package controller;

import static utils.SystemProperties.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.ColourType;
import model.KeySettings;
import services.EventBusService;
import utils.SystemProperties;
import view.KeySettingsDialog;
import events.KeySettingChangedEvent;
import events.ResetBordersEvent;
import events.ResetKeysEvent;
import events.UpdateKeyTextfieldEvent;
import events.messages.ErrorMessageEvent;

public class KeySettingsController implements ActionListener, KeyListener
{
	private KeySettings oldSettings;
	private KeySettings newSettings;
	private KeySettingsDialog keySettingsDialog;

	private EventBusService eventBusService;
	private boolean keyboardEnabled;
	private boolean mouseEnabled;
	
	private ColourType currentEditableColourType;

	public KeySettingsController(KeySettingsDialog keySettingsDialog, KeySettings oldSettings, EventBusService eventBusService)
	{
		this.keyboardEnabled = false;
		this.mouseEnabled = true;
		this.keySettingsDialog = keySettingsDialog;
		this.newSettings = new KeySettings();
		this.newSettings.setColourKeys(oldSettings.getColourKeys());
		this.oldSettings = oldSettings;
		
		this.eventBusService = eventBusService;
	}
	
	
	public void actionPerformed(ActionEvent a)
	{
		if (mouseEnabled)
		{
			eventBusService.post(new UpdateKeyTextfieldEvent(SystemProperties.ACTION_COMMAND_COLOUR_MAP.get(a.getActionCommand())));
			
			
			keyboardEnabled = true;
			mouseEnabled = false;
		}

		if (a.getActionCommand().equals("CANCEL"))
		{
			newSettings.setColourKeys(oldSettings.getColourKeys());

			eventBusService.post(new ResetBordersEvent(true));

			keyboardEnabled = false;
			mouseEnabled = true;
		}

		if (a.getActionCommand().equals("ACCEPT"))
		{
			oldSettings.setColourKeys(newSettings.getColourKeys());

			eventBusService.post(new ResetBordersEvent(true));

			keyboardEnabled = false;
			mouseEnabled = true;
		}

		if (a.getActionCommand().equals("DEFAULT"))
		{
			newSettings.setColourKeys(oldSettings.getColourKeys());

			eventBusService.post(new ResetKeysEvent(oldSettings));

			keyboardEnabled = false;
			mouseEnabled = true;
		}
	}
	
	
	public void keyTyped(KeyEvent k)
	{
		if (this.keyboardEnabled)
		{
			char newChar = k.getKeyChar();
			
			if (newSettings.changeKeySetting(currentEditableColourType, newChar))
			{
				eventBusService.post(new KeySettingChangedEvent(currentEditableColourType, newChar));
				
				keyboardEnabled = false;
				mouseEnabled = true;
			}
			else
			{
				eventBusService.post(new ErrorMessageEvent(keySettingsDialog,
						"Diese Taste ist schon belegt, bitte w"+ae+"hlen Sie eine andere."));				
			}
		}
	}

	
	public void keyPressed(KeyEvent k)
	{
	}

	
	public void keyReleased(KeyEvent k)
	{
	}

}
