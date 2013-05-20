package view;

import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modules.StroopModule;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;

public class StroopTestMain
{
	@Inject
	public StroopTestMain(final MainWindow mainWindow)
	{
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				mainWindow.setVisible(true);
			}
		});
	}

	public static void main(String args[])
	{
		System.err.println(new File(".").getAbsolutePath());
		System.err.println(		Thread.currentThread().getContextClassLoader().getResource("."));

		try
		{
			UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
		}
		catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		
		Guice.createInjector(new StroopModule()).getInstance(StroopTestMain.class);
	}
}
