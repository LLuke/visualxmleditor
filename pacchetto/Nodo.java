package pacchetto;

import java.util.*;

/**
 * @author Ferreri Gabriele
 *
 * Classe che implementa un Nodo di file XML da inserire in una JTree
 * 
 */
public class Nodo {
	public static int TIPONODO_FILE=0;
	public static int TIPONODO_NODOCONATTRIBUTI=1;
	public static int TIPONODO_NODOSENZAATTRIBUTI=2;

	private int tipoNodo;
	private String nomeNodo;
	private String testoNodo;
	private HashMap attributiNodo;
	
	/**
	 * Costruttore di classe
	 * @param nome Nome del nodo XML
	 * @param testo Testo inserito tra i tag del nodo XML
	 * @param tipo Tipo del nodo 
	 */
	public Nodo(String nome,String testo,HashMap attributi,int tipo) {
		nomeNodo=nome;
		testoNodo=testo;
		attributiNodo=attributi;
		tipoNodo=tipo;
	}
	
	/**
	 * Funzione che ritorna il tipo del nodo
	 * @return Tipo nodo
	 */
	public int getTipo()
	{
		return tipoNodo;
	}
	
	/**
	 * Funzione che imposta il tipo del nodo
	 * @param val Valore del tipo da assegnare al nodo
	 */
	public void setTipo(int val)
	{
		tipoNodo=val;
	}
	
	/**
	 * Funzione che ritorna il nome del nodo
	 * @return Nome del nodo
	 */
	public String getNome()
	{
		return nomeNodo;
	}
	
	/**
	 * Funzione che imposta il nome del nodo
	 * @param val Nome del nodo
	 */
	public void setNome(String val)
	{
		nomeNodo=val;
	}
	
	/**
	 * Funzione che ritorna il testo del nodo 
	 * @return Testo del nodo
	 */
	public String getTesto()
	{
		return testoNodo;
	}
	
	/**
	 * Funzione che imposta il testo del nodo
	 * @param val Testo del nodo
	 */
	public void setTesto(String val)
	{
		testoNodo=val;
	}
	
	/**
	 * Funzione che ritorna la lista degli attributi del nodo 
	 * @return Lista attributi
	 */
	public HashMap getAttributi()
	{
		return attributiNodo;
	}
	
	/**
	 * Funzione che imposta la lista degli attributi del nodo
	 * @param val Testo del nodo
	 */
	public void setAttributi(HashMap val)
	{
		attributiNodo=val;
	}

	/**
	 * Funzione che effettua l'override del metodo toString per
	 * personalizzare la modalità di visualizzazione del nodo
	 * @return Stringa rappresentante l'oggetto
	 */
	public String toString()
	{
		return nomeNodo;
	}
}
