/*
 * Created on 22-dic-2004
 */
package pack;

import java.io.IOException;
import java.util.Properties;

/**
 * Class to support different property files, containing messages for
 * localization
 * 
 * @author Ignazio
 */
public class LocalizedMessages {
	/** the Properties object used to access property files */
	private static Properties properties = new Properties();

	/**
	 * reinits the singleton object to use a particular language
	 * 
	 * @param languagename
	 *            the nameof the file containing the properties (without the
	 *            predefined extension ".properties")
	 */
	public static void reinit(String languagename) {
		try {
			properties.load(LocalizedMessages.class.getResourceAsStream(languagename + ".properties")); //$NON-NLS-1$
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Undefined language!!!", e); //$NON-NLS-1$
		}
	}
/**returns the value for the key in the properties*/
	public static String getString(String key) {
		return properties.getProperty(key);
	}
}