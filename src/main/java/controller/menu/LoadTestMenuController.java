package controller.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import services.StroopTestFileService;
import services.StroopTestProvider;
import utils.StroopFileFilter;

import com.google.inject.Inject;

public class LoadTestMenuController implements ActionListener
{
	private StroopTestProvider stroopTestProvider;
	private JFileChooser loadTestFileChooser;
	private StroopTestFileService stroopTestFileService;

	@Inject
	public LoadTestMenuController(StroopTestProvider stroopTestProvider, StroopTestFileService stroopTestFileService)
	{
		this.stroopTestProvider = stroopTestProvider;
		this.stroopTestFileService = stroopTestFileService;
		this.loadTestFileChooser = new JFileChooser();
		loadTestFileChooser.setFileFilter(new StroopFileFilter());
	}

	
	public void actionPerformed(ActionEvent e)
	{
		// FIXME send event + add MainWindow where null
		int choice = loadTestFileChooser.showOpenDialog(null);

		if (choice == JFileChooser.APPROVE_OPTION)
		{
			stroopTestProvider.setActiveStroopTest(stroopTestFileService.loadStroopTest(loadTestFileChooser.getSelectedFile()));
		}
		else
		{
			return;
		}
	}

}
