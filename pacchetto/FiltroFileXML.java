package pacchetto;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author Ferreri Gabriele
 *
 * Classe che estende la classe FileFilter per il filtro dei file XML
 *  
 */
public class FiltroFileXML extends FileFilter {

	/**
	 * Costruttore di classe
	 * 
	 */
	public FiltroFileXML() {
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
		else if (Utility.estraiEstensioneFile(f.getName()).toLowerCase().equals("xml"))
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
