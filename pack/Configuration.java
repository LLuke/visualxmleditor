/*
Copyright (c) 2004, Gabriele Ferreri & Ignazio Palmisano 
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer. 
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution. 
Neither the name of Gabriele Ferreri or Ignazio Palmisano nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission. 
THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package pack;

import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Configuration graphical interface
 * @author Ferreri Gabriele 
 */
public class Configuration extends JDialog {
	private static final long serialVersionUID = 1L;
	private javax.swing.JPanel jContentPane = null;

	private ComboBox language = null;
	private JButton ok = null;
	private JButton cancel = null;
	
	/** the Properties object used to access property files */
	private static Properties properties = new Properties();

	/**
	 * 
	 */
	public Configuration() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setModal(true);
		this.setSize(400,230);
		this.setContentPane(getJContentPane());
		this.setTitle(LocalizedMessages.getString("Configuration.TitleConfiguration")); //$NON-NLS-1$
		this.setBounds(Utility.centraSuSchermo(this.getBounds()));

		List files = LocalizedMessages.getLanguageList();
		
		for (int t=0;t<files.size();t++)
		{
			String temp = files.get(t).toString();
			temp=temp.substring(temp.lastIndexOf('\\')+1,temp.lastIndexOf('.'));
			language.addItem(temp);			
		}
		
		language.setSelectedItem(getCurrentLanguage());

	}
	
	/**
	 * This method load file config in properties
	 *
	 */
	private static void loadConfig()
	{
		try {
			properties.load(new FileInputStream(Utility.currentPath() + File.separator + "Config.properties")); //$NON-NLS-1$
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}
	
	/**
	 * This method save properties in file config
	 *
	 */
	private static void saveConfig()
	{
		try {
			properties.store(new FileOutputStream(Utility.currentPath() + File.separator + "Config.properties"),""); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method return current language
	 * @return current language
	 */
	public static String getCurrentLanguage()
	{
		loadConfig();
		return properties.getProperty("Language"); //$NON-NLS-1$
	}
	
	public static void setCurrentLanguage(String value)
	{
		loadConfig();
		properties.setProperty("Language",value); //$NON-NLS-1$
		saveConfig();
	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLanguage(), null);
			jContentPane.add(getOk(), null);
			jContentPane.add(getCancel(), null);
		}
		return jContentPane;
	}
	/**
	 * This method initializes language	
	 * 	
	 * @return pack.ComboBox	
	 */    
	private ComboBox getLanguage() {
		if (language == null) {
			language = new ComboBox();
			language.setBounds(10, 24, 376, 28);
			language.setLabel(LocalizedMessages.getString("LabelSettingLanguage")); //$NON-NLS-1$
			language.setWidthLabel(120);
		}
		return language;
	}
	/**
	 * This method initializes ok	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getOk() {
		if (ok == null) {
			ok = new JButton();
			ok.setIcon(new ImageIcon(getClass().getResource("/icons/iconOk.gif"))); //$NON-NLS-1$
			ok.setText(LocalizedMessages.getString("Configuration.ButtonOk")); //$NON-NLS-1$
			ok.setLocation(64, 138);
			ok.setSize(121, 51);
			ok.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					setCurrentLanguage(language.getSelectedItem().toString());
					LocalizedMessages.reInit(language.getSelectedItem().toString());
					setVisible(false);
				}
			});
		}
		return ok;
	}
	/**
	 * This method initializes cancel	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getCancel() {
		if (cancel == null) {
			cancel = new JButton();
			cancel.setBounds(210, 138, 121, 51);
			cancel.setIcon(new ImageIcon(getClass().getResource("/icons/iconCancel.gif"))); //$NON-NLS-1$
			cancel.setText(LocalizedMessages.getString("Configuration.ButtonCancel")); //$NON-NLS-1$
			cancel.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					setVisible(false);
				}
			});
		}
		return cancel;
	}
   }
