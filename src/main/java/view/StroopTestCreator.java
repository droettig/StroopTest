package view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;

import model.ColourType;
import model.StroopTest;
import model.TestType;
import model.Word;
import services.EventBusService;
import services.StroopTestProvider;
import utils.SystemProperties;

public class StroopTestCreator extends JDialog
{
	private static final long serialVersionUID = 4021078149259137242L;
	
	private StroopTestCreatorPanel stroopTestDetails;
	private StroopTest stroopTest;

	private boolean testCreated;

	private EventBusService eventBusService;
	private StroopTestProvider stroopTestProvider;
	
	public StroopTestCreator(EventBusService eventBusService, StroopTestProvider stroopTestProvider)
	{
		this(eventBusService, stroopTestProvider, new StroopTest(Arrays.<Word>asList(), TestType.NO_TEST));
	}
	
	public StroopTestCreator(EventBusService eventBusService, StroopTestProvider stroopTestProvider, StroopTest stroopTest)
	{
		this.eventBusService = eventBusService;
		this.eventBusService.register(this);
		this.stroopTestProvider = stroopTestProvider;
		this.testCreated = false;
		this.stroopTest = stroopTest;
		this.initComponents();
	}
	
	private void initComponents()
	{
		this.stroopTestDetails = new StroopTestCreatorPanel(this, stroopTestProvider, eventBusService, stroopTest);
		
		this.add(this.stroopTestDetails.getPanel());
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		this.setTitle("Stroop Test erstellen");
		this.setSize(570, 650);
		this.setLocation((SystemProperties.SCREEN_WIDTH / 2) - 285, (SystemProperties.SCREEN_HEIGHT / 2) - 325);

		if (stroopTest.getTestType() == TestType.TEST_COLOUR)
		{
			this.stroopTestDetails.setChooseColourTest(true);
		}
		else if (stroopTest.getTestType() == TestType.TEST_WORD)
		{
			this.stroopTestDetails.setChooseWordTest(true);
		}

		// Comments and Creator
		this.stroopTestDetails.setComment(stroopTest.getComment());
		this.stroopTestDetails.setCreator(stroopTest.getCreator());
	}
	
	public boolean isTestCreated()
	{
		return this.testCreated;
	}

	public void setTestCreated(boolean testCreated)
	{
		this.testCreated = testCreated;
	}

	public void showDialog()
	{
		this.testCreated = false;
		this.setVisible(true);
	}

}
