/*
 * Created on 27-dic-2004
 */
package pack;

import java.io.File;
import java.io.FilenameFilter;

public class PropertiesFilter implements FilenameFilter {

	public boolean accept(File dir, String name) {
		boolean toReturn = false;
		try {
			toReturn = name.toLowerCase().endsWith(".properties");
		} catch (Exception e) {
			toReturn = false;
		}
		return toReturn;
	}
}	
