package view;

import static utils.SystemProperties.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.ColourType;
import model.KeySettings;
import services.EventBusService;
import utils.SystemProperties;

import com.google.common.eventbus.Subscribe;

import controller.KeySettingsController;
import events.KeySettingChangedEvent;
import events.ResetBordersEvent;
import events.ResetKeysEvent;
import events.UpdateKeyTextfieldEvent;

public class KeySettingsDialog extends JDialog
{
	private static final long serialVersionUID = 4831416909946712841L;

	private JPanel colourPanel;
	private JPanel keyFieldPanel;
	private JPanel changeKeyPanel;

	private JButton cancelButton, acceptButton, defaultButton;

	private Map<ColourType, JTextField> textFieldMapping;
	private Map<ColourType, JButton> changeButtonMapping;
	private Map<ColourType, JPanel> colourBoxMapping;

	public KeySettingsDialog(KeySettings oldSettings, EventBusService eventBusService)
	{
		eventBusService.register(this);
		
		KeySettingsController controller = new KeySettingsController(this, oldSettings, eventBusService);

		textFieldMapping = new HashMap<ColourType, JTextField>();
		changeButtonMapping = new HashMap<ColourType, JButton>();
		colourBoxMapping = new HashMap<ColourType, JPanel>();

		for (ColourType colour : ColourType.values())
		{
			JPanel boxPanel = new JPanel();

			boxPanel.setBackground(SystemProperties.COLOUR_MAP.get(colour));
			boxPanel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
			boxPanel.setPreferredSize(new Dimension(30, 30));

			colourBoxMapping.put(colour, boxPanel);

			JTextField field = new JTextField(String.valueOf(oldSettings.getColorKey(colour).getKeyCharacter()));
			field.setEditable(false);
			field.setPreferredSize(new Dimension(70, 30));
			field.setHorizontalAlignment(SwingConstants.CENTER);
			field.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			textFieldMapping.put(colour, field);

			JButton changeButton = new JButton(ae+"ndern");
			changeButton.setActionCommand(colour.getColourTypeName());
			changeButton.addActionListener(controller);

			changeButtonMapping.put(colour, changeButton);
		}

		this.colourPanel = new JPanel();
		this.keyFieldPanel = new JPanel();
		this.changeKeyPanel = new JPanel();

		this.cancelButton = new JButton("Abbrechen");
		this.cancelButton.setActionCommand("CANCEL");
		this.cancelButton.addActionListener(controller);

		this.acceptButton = new JButton(Ue+"bernehmen");
		this.acceptButton.setActionCommand("ACCEPT");
		this.acceptButton.addActionListener(controller);

		this.defaultButton = new JButton("Zur"+ue+"cksetzen");
		this.defaultButton.setActionCommand("DEFAULT");
		this.defaultButton.addActionListener(controller);

		this.colourPanel.setLayout(new GridLayout(6, 1, 5, 10));
		for (ColourType colour : ColourType.values())
		{
			this.colourPanel.add(colourBoxMapping.get(colour));
		}

		this.keyFieldPanel.setLayout(new GridLayout(6, 1, 5, 10));
		for (ColourType colour : ColourType.values())
		{
			this.keyFieldPanel.add(textFieldMapping.get(colour));
		}

		this.changeKeyPanel.setLayout(new GridLayout(6, 1, 0, 17));
		for (ColourType colour : ColourType.values())
		{
			this.changeKeyPanel.add(changeButtonMapping.get(colour));
		}

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 5, 0, 5);
		this.add(this.colourPanel, c);

		c.gridx = 1;
		c.gridy = 0;
		this.add(this.keyFieldPanel, c);

		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(15, 15, 15, 15);
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(this.changeKeyPanel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(30, 5, 0, 5);
		this.add(this.acceptButton, c);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(30, 5, 0, 5);
		this.add(this.defaultButton, c);

		c.gridx = 2;
		c.gridy = 1;
		this.add(this.cancelButton, c);
		
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Tastatureinstellungen");
		this.setModal(true);
		this.setSize(370, 370);
		this.setLocation((SystemProperties.SCREEN_WIDTH / 2) - 185, (SystemProperties.SCREEN_HEIGHT / 2) - 185);
	}

	@Subscribe
	public void handleKeySettingChangedEvent(KeySettingChangedEvent event)
	{
		textFieldMapping.get(event.getCurrentEditableColourType()).setText(String.valueOf(event.getNewCharacter()));
		textFieldMapping.get(event.getCurrentEditableColourType()).setBorder(
				BorderFactory.createLineBorder(Color.BLACK));
	}

	@Subscribe
	public void handleResetKeysEvent(ResetKeysEvent event)
	{
		for (ColourType colourType : ColourType.values())
		{
			this.textFieldMapping.get(colourType).setText(
					String.valueOf(event.getKeySettings().getColorKey(colourType).getKeyCharacter()));
		}
	}

	@Subscribe
	public void handleUpdateKeyTextfieldEvent(UpdateKeyTextfieldEvent event)
	{
		JTextField textfield = this.textFieldMapping.get(event.getCurrentEditableColourType());

		if (textfield != null)
		{
			textfield.setBorder(BorderFactory.createLineBorder(Color.RED));
		}
	}

	@Subscribe
	public void handleResetBordersEvent(ResetBordersEvent event)
	{
		for (ColourType colour : textFieldMapping.keySet())
		{
			textFieldMapping.get(colour).setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}

		if (event.isDisposeDialog())
		{
			this.dispose();
			return;
		}

		this.repaint();
	}
}


