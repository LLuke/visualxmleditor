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

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Filters XML files
 * @author Ferreri Gabriele
 *  
 */
public class XMLFilter extends FileFilter {

	/**
	 * Costruttore di classe
	 * 
	 */
	public XMLFilter() {
		super();
	}

	/**
	 * Funzione che effettua l'overrides del metodo accept per filtrare i file
	 * @param f File corrente 
	 */
	public boolean accept(File f) {
		if (f.isDirectory())
		{
			return true;
		}
		else if (Utility.estraiEstensioneFile(f.getName()).equalsIgnoreCase("xml")) //$NON-NLS-1$
		{
			return true;
		}
		return false;
	}

	/**
	 * Funzione che ritorna la descrizione del file filtrato
	 * @return Descrizione del file filtrato
	 */
	public String getDescription() {
		return "File XML"; //$NON-NLS-1$
	}

}
