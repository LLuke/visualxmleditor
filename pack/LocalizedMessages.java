/*
 * Created on 22-dic-2004
 */
package pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;

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
public class LocalizedMessages  implements FilenameFilter {
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
			properties.load(new FileInputStream(Utility.currentPath() + File.separator + languageName + ".properties")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
		String temp=""; //$NON-NLS-1$
		try
		{
			temp = properties.getProperty(key);
		}
		catch (Exception e)
		{
			
		}
		
		return temp;
	}

	/**
	 * 
	 */
	public boolean accept(File dir, String name) {
		boolean ok;
		if (!name.equals("Config.properties")) //$NON-NLS-1$
		{
			ok=name.endsWith(".properties"); //$NON-NLS-1$
		}
		else
		{
			ok=false;
		}
		return ok;
	}

	/**
	 * This method return a list of languages available
	 * @return
	 */
	public static String[] languagesAvailable()
	{
		String[] temp = new File(Utility.currentPath()).list(new LocalizedMessages()); //$NON-NLS-1$

		for (int t=0;t<temp.length;t++)
		{
			temp[t]=temp[t].substring(0,temp[t].lastIndexOf('.'));
		}
		
		return temp;
	}
	
	/**
	 * Refresh form language
	 *
	 */
	public static void refreshLanguage(String formName,Object comp)
	{
		if (comp instanceof TextBox)
		{
			TextBox txt = (TextBox)comp;
			
			String name = txt.getName();
			
			if (name!=null)
			{
				String text = getString(formName + ".TextBox" + name);
				if (text!=null) //$NON-NLS-1$
				{
					txt.setLabel(text);
				}
				
				String textTip = getString(formName + ".TipTextBox" + name); //$NON-NLS-1$
				if (textTip!=null) //$NON-NLS-1$
				{
					txt.setToolTipText(textTip);
				}
			}
		}
		else if (comp instanceof ComboBox)
		{
			ComboBox cbo = (ComboBox)comp;
			
			String name = cbo.getName();
			
			if (name!=null)
			{
				String text = getString(formName + ".ComboBox" + name);
				if (text!=null) //$NON-NLS-1$
				{
					cbo.setLabel(text);
				}
				
				String textTip = getString(formName + ".TipComboBox" + name); //$NON-NLS-1$
				if (textTip!=null) //$NON-NLS-1$
				{
					cbo.setToolTipText(textTip);
				}
			}
		}
		else if (comp instanceof JPanel)
		{
			JPanel pan = (JPanel)comp;

			String name = pan.getName();
			
			if (name!=null)
			{
				String text = getString(formName + ".Title" + name);
				if ((text!=null) && (pan.getBorder()!=null)) 
				{
					((TitledBorder)pan.getBorder()).setTitle(text);
				}
			}

			for (int t=0;t<pan.getComponentCount();t++)
			{
				refreshLanguage(formName,pan.getComponent(t));
			}
			
		}
		else if (comp instanceof JToolBar)
		{
			JToolBar tlb = (JToolBar)comp;
			
			for (int t=0;t<tlb.getComponentCount();t++)
			{
				refreshLanguage(formName,tlb.getComponent(t));
			}
		}
		else if (comp instanceof JSplitPane)
		{
			JSplitPane split = (JSplitPane)comp;
			
			for (int t=0;t<split.getComponentCount();t++)
			{
				refreshLanguage(formName,split.getComponent(t));
			}
		}
		else if (comp instanceof JButton)
		{
			JButton btn = (JButton)comp;
			
			String name = btn.getName();
			
			if (name!=null)
			{
				String text = getString(formName + ".Button" + name);
				if (text!=null) //$NON-NLS-1$
				{
					btn.setText(text);
				}
				
				String textTip = getString(formName + ".TipButton" + name); //$NON-NLS-1$
				if (textTip!=null) //$NON-NLS-1$
				{
					btn.setToolTipText(textTip);
				}
			}
		}
	}
}