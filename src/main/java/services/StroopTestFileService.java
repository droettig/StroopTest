package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import model.StroopTest;
import model.StroopTestResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import utils.TestSuiteConfiguration;

import com.google.inject.Singleton;

@Singleton
public class StroopTestFileService
{
	public boolean saveStroopTest(StroopTest stroopTest, File file)
	{
		file = checkExtension(file, "stroop");
		
		TestSuiteConfiguration testConfig = new TestSuiteConfiguration(stroopTest.getWords(),
				stroopTest.getTestType(), stroopTest.getCreator(), stroopTest.getComment());
		try
		{
			ObjectOutputStream obj_out = new ObjectOutputStream(new FileOutputStream(file));
			obj_out.writeObject(testConfig);
			obj_out.close();
			return true;
		}
		catch (FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		return false;
	}

	public void saveStroopResult(File file, StroopTestResult testResult)
	{
		try
		{
			file = checkExtension(file, "txt");
			
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter out = new OutputStreamWriter(fos, "ISO8859-1");

			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			Calendar cal = Calendar.getInstance();

			out.write(df.format(cal.getTime()) + "\n");

			out.write(testResult.getStringRepresentation());
			
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public StroopTest loadStroopTest(File file)
	{
		try
		{
			ObjectInputStream obj_in = new ObjectInputStream(new FileInputStream(file));

			TestSuiteConfiguration testConfig = (TestSuiteConfiguration) obj_in.readObject();

			obj_in.close();

			return new StroopTest(testConfig.getWords(), testConfig.getType(), testConfig.getCreator(), testConfig.getComment());
		}
		catch (FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		catch (ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}
	
	private File checkExtension(File file, String extension)
	{
		String currentExtension = FilenameUtils.getExtension(file.getName());
		
		if (StringUtils.isEmpty(currentExtension) || !currentExtension.equals(extension))
		{
			file = FileUtils.getFile(file + "." + extension);
		}
		return file;
	}
}
