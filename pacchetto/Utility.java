package pacchetto;

import java.awt.Rectangle;

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
		return nomeFile.substring(nomeFile.lastIndexOf(".")+1);
	}
}
