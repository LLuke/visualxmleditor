package pacchetto;

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
		else if (Utility.estraiEstensioneFile(f.getName()).equalsIgnoreCase("xml"))
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
		return "File XML";
	}

}
