package utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class StroopFileFilter extends FileFilter
{

	
	public boolean accept( File f )
	{
		if (f.isDirectory())
			return true;

		String extension = this.getExtension(f);
		if( extension != null )
		{
			if (extension.equals( "stroop" ))
				return true;
		}
		return false;
	}

	
	public String getDescription()
	{
		return "Stroop Test (*.stroop)";
	}

    private String getExtension(File f)
    {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1)
        {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
