/*
 * Created on 22-dic-2004
 */
package pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

/**
 * Class to support different property files, containing messages for
 * localization
 * 
 * @author Ignazio
 */
public class LocalizedMessages {

	// TODO test per la lettura delle liste; va fatto il JUnit
	public static void main(String[] args) {
		List l = getLanguageList();
		System.out.println("number of languages:" + l.size());
		for (int i = 0; i < l.size(); i++) {
			reInit((String) l.get(i));
			System.out.println(getString("MainWindow.ButtonOpenFile"));
		}
	}

	/**
	 * map containing File objects representing the available languages; they
	 * are indexed with "languages/ <language_name>.properties"; it is accessed
	 * through the getInputStream method
	 */
	private static Map files = null;
	private static JarFile j = null;

	/**
	 * returns an inputstream for the resource named entryName
	 * 
	 * @param entryName
	 *            the name of the resource to read
	 */
	public static InputStream getInputStream(String entryName) {
		InputStream toReturn = null;
		// ensure the static variables are initialized
		if (files == null) {
			getLanguageList();
		}
		//JOptionPane.showMessageDialog(null,entryName);
		//JOptionPane.showMessageDialog(null,files.toString());
		
		Object o = files.get("pack\\languages\\" + entryName + ".properties");
		
		if (o instanceof File) {
			try {
				toReturn = new FileInputStream((File) o);
			} catch (FileNotFoundException e) {
				toReturn = null;
			}
		}
		if (o instanceof JarEntry) {

			if (j != null) {
				try {
					toReturn = j.getInputStream((JarEntry) o);
				} catch (IOException e) {
					toReturn = null;
				}
			}
		}
		return toReturn;
	}

	/**
	 * returns a list of the available languages; asa side effect, inits the map
	 * of available languages, that can be queried with a language name in this
	 * list
	 * 
	 * @return a list of strings starting with "languages/"
	 */
	public static List getLanguageList() {
		List toReturn = null;
		URL u = LocalizedMessages.class.getResource(".");
		// u is not null if we are on disk
		if (u != null) {

			try {
				File f = new File(new URI(LocalizedMessages.class.getResource(".").toString()));
				// qui si può usare f per navigare l'albero, ex:
				// supponendo che ci sia la directory languages/*.properties
				File[] languageDirectoryFiles = f.listFiles(new LanguagesFilter());
				if (languageDirectoryFiles.length > 0) {
					File[] propertyFiles = languageDirectoryFiles[0].listFiles(new PropertiesFilter());
					toReturn = new ArrayList();
					String semiLocalName = null;
					files = new HashMap();
					for (int i = 0; i < propertyFiles.length; i++) {
						// semilocalname must start with "languages/" (or
						// "languages\\")
						semiLocalName = propertyFiles[i].getAbsolutePath().substring(propertyFiles[i].getAbsolutePath().lastIndexOf("languages"));
						// qui inserisce oggetti di tipo File
						files.put(semiLocalName, propertyFiles[i]);
						toReturn.add(semiLocalName);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// altrimenti bisogna indagare nel jar...
			String url = LocalizedMessages.class.getResource("LocalizedMessages.class").toExternalForm();
			String jarname;
			try {
				//
				jarname = URLDecoder.decode(url.substring("jar:file:/".length(), url.indexOf("!")), "UTF-8");
				//System.out.println(jarname);
				j = new JarFile(jarname);
				Enumeration e = j.entries();
				toReturn = new ArrayList();
				files = new HashMap();
				while (e.hasMoreElements()) {
					Object o = e.nextElement();
					if (o.toString().startsWith(LocalizedMessages.class.getPackage().getName() + "/languages/")) {
						toReturn.add(o.toString());
						// qui inserisce oggetti di tipo JarEntry
						files.put(o.toString(), o);
					}
				}
				// ora l contiene l'elenco delle entry col nome che inizia nel
				// modo prestabilito
				// il path comincia senza / e si riferisce alla radice del jar
				// per ex, il filemanifest.mf si trova come META-INF/MAINFEST.MF
				// (il maiuscolo vale solo per questo file)

			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return toReturn;
	}

	/** the Properties object used to access property files */
	private static Properties properties = new Properties();

	/**
	 * reinits the singleton object to use a particular language
	 * 
	 * @param languagename
	 *            the nameof the file containing the properties (without the
	 *            predefined extension ".properties")
	 */
	public static void reInit(String languageName) {
		try {
			
			properties.load(getInputStream(languageName));
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Undefined language!!!", e); //$NON-NLS-1$
		}
	}

	/**
	 * returns the value for the key in the properties
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		String temp = ""; //$NON-NLS-1$
		try {
			temp = properties.getProperty(key);
		} catch (Exception e) {

		}

		return temp;
	}

	/**
	 * Refresh form language
	 */
	public static void refreshLanguage(String formName, Object comp) {
		if (comp instanceof TextBox) {
			TextBox txt = (TextBox) comp;

			String name = txt.getName();

			if (name != null) {
				String text = getString(formName + ".TextBox" + name);
				if (text != null) //$NON-NLS-1$
				{
					txt.setLabel(text);
				}

				String textTip = getString(formName + ".TipTextBox" + name); //$NON-NLS-1$
				if (textTip != null) //$NON-NLS-1$
				{
					txt.setToolTipText(textTip);
				}
			}
		} else if (comp instanceof ComboBox) {
			ComboBox cbo = (ComboBox) comp;

			String name = cbo.getName();

			if (name != null) {
				String text = getString(formName + ".ComboBox" + name);
				if (text != null) //$NON-NLS-1$
				{
					cbo.setLabel(text);
				}

				String textTip = getString(formName + ".TipComboBox" + name); //$NON-NLS-1$
				if (textTip != null) //$NON-NLS-1$
				{
					cbo.setToolTipText(textTip);
				}
			}
		} else if (comp instanceof JPanel) {
			JPanel pan = (JPanel) comp;

			String name = pan.getName();

			if (name != null) {
				String text = getString(formName + ".Title" + name);
				if ((text != null) && (pan.getBorder() != null)) {
					((TitledBorder) pan.getBorder()).setTitle(text);
				}
			}

			for (int t = 0; t < pan.getComponentCount(); t++) {
				refreshLanguage(formName, pan.getComponent(t));
			}

		} else if (comp instanceof JToolBar) {
			JToolBar tlb = (JToolBar) comp;

			for (int t = 0; t < tlb.getComponentCount(); t++) {
				refreshLanguage(formName, tlb.getComponent(t));
			}
		} else if (comp instanceof JSplitPane) {
			JSplitPane split = (JSplitPane) comp;

			for (int t = 0; t < split.getComponentCount(); t++) {
				refreshLanguage(formName, split.getComponent(t));
			}
		} else if (comp instanceof JButton) {
			JButton btn = (JButton) comp;

			String name = btn.getName();

			if (name != null) {
				String text = getString(formName + ".Button" + name);
				if (text != null) //$NON-NLS-1$
				{
					btn.setText(text);
				}

				String textTip = getString(formName + ".TipButton" + name); //$NON-NLS-1$
				if (textTip != null) //$NON-NLS-1$
				{
					btn.setToolTipText(textTip);
				}
			}
		}
	}
}