/*
 * Created on 27-dic-2004
 */
package pack;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author Ignazio
 */
public class LanguagesFilter implements FilenameFilter {

	public boolean accept(File dir, String name) {
		boolean toReturn = false;
		try {
			toReturn = name.equals("languages");
		} catch (Exception e) {
			toReturn = false;
		}

		return toReturn;
	}

}
