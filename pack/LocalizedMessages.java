/*
 * Created on 22-dic-2004
 */
package pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;

/**
 * Class to support different property files, containing messages for
 * localization
 * 
 * @author Ignazio
 */
public class LocalizedMessages implements FilenameFilter {
	/** the Properties object used to access property files */
	private static Properties properties = new Properties();
	
	/**
	 * This method return a list of languages available
	 * @return
	 */
	public static String[] languagesAvailable()
	{
		String files[] = new File(new File("").getAbsolutePath()+"/languages/").list(new LocalizedMessages()); //$NON-NLS-1$ //$NON-NLS-2$
		
		for (int t=0;t<files.length;t++)
		{
			files[t]=files[t].replaceFirst(".properties","");  //$NON-NLS-1$//$NON-NLS-2$
		}
		
		return files; 
	}
	
	/**
	 * reinits the singleton object to use a particular language
	 * 
	 * @param languagename
	 *            the nameof the file containing the properties (without the
	 *            predefined extension ".properties")
	 */
	public static void reInit(String languageName) {
		try {
			properties.load(new FileInputStream(Utility.currentPath() + "/languages/" + languageName + ".properties")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Undefined language!!!", e); //$NON-NLS-1$
		}
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		return properties.getProperty(key);
	}

	
	/**
	 * 
	 */
	public boolean accept(File dir, String name) {
		return name.endsWith(".properties"); //$NON-NLS-1$
	}
}