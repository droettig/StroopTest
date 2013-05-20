package controller;

import static utils.SystemProperties.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.ColourKey;
import model.KeySettings;
import model.StroopTest;
import services.EventBusService;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;

import events.SuccessfulWordEvent;
import events.state.InitialStateEnterEvent;
import events.state.InitialStateExitEvent;

public class StroopTestRunController extends KeyAdapter
{
	private boolean keyboardEnabled;
	
	private StroopTest stroopTest;
	private KeySettings keySettings;
	private EventBusService eventBusService;

	@Inject
	public StroopTestRunController(EventBusService eventBusService, KeySettings keySettings, StroopTest stroopTest)
	{
		this.eventBusService = eventBusService;
		this.keySettings = keySettings;
		this.stroopTest = stroopTest;
	}
	
	
	public void keyTyped(KeyEvent k)
	{
		if (keyboardEnabled)
		{
			System.out.println("Keyboard enabled");
			char keyCharacter = k.getKeyChar();
			ColourKey foundColourKey = keySettings.getColorKey(keyCharacter);

			if (foundColourKey == null)
			{
				System.err.println("Keine g"+ue+"ltige Taste!");
				return;
			}
			else
			{
				if(stroopTest.checkResult(foundColourKey.getColourType()))
				{
					eventBusService.post(new SuccessfulWordEvent());
				}
			}
		}
		else
		{
			System.err.println("Keyboard disabled");
			return;
		}
	}
	
	public void disableKeyboard()
	{
		keyboardEnabled = false;
	}

	public void enableKeyboard()
	{
		keyboardEnabled = true;
	}

	@Subscribe
	public void handleInitialStateEnterEvent(InitialStateEnterEvent event)
	{
		disableKeyboard();
	}
	
	@Subscribe
	public void handleInitialStateExitEvent(InitialStateExitEvent event)
	{
		enableKeyboard();
	}
}
