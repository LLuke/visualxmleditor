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
