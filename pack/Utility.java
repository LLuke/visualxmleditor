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

import java.awt.Rectangle;
import java.io.File;

/**
 * @author Ferreri Gabriele
 *
 * Classe contenente funzioni di utility
 * 
 */
public class Utility {

	/**
	 * Centra il JFrame passato sullo schermo
	 * 
	 * @param finestra JFrame da centrare
	 */
	static public Rectangle centraSuSchermo(Rectangle dim)
	{
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        return new Rectangle((screenSize.width-dim.width)/2, (screenSize.height-dim.height)/2, dim.width, dim.height);
	}
	
	/**
	 * Estrae l'estensione di un file passato (es. "file1.avi" -> "avi")
	 * @param nomeFile File da cui estrarre l'estensione
	 * @return Estensione del file passato
	 */
	static public String estraiEstensioneFile(String nomeFile)
	{
		return nomeFile.substring(nomeFile.lastIndexOf(".")+1); //$NON-NLS-1$
	}
	
	/**
	 * Return current directory
	 * @return Current directory
	 */
	static public String currentPath()
	{
		String temp=System.getProperty("java.class.path"); //$NON-NLS-1$
		
		File f=new File(temp);
		
		if (f.isDirectory())
		{
			temp=f.getPath();
		}
		else
		{
			temp=f.getPath().substring(0,f.getPath().lastIndexOf(File.separator));
		}
		return temp;
	}
	
	/**
	 * Create String by repeating a character
	 * @param car
	 * @param count
	 * @return
	 */
	static public String repeatString(String text,int count)
	{
		String temp=""; //$NON-NLS-1$
		
		for (int t=0;t<count;t++)
		{
			temp+=text;
		}
		
		return temp;
	}
}

