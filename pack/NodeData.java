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

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
/**
 * Extends JDialog and creates a form for data entering on a node or an attribute
 * @author Ferreri Gabriele
 */

public class NodeData extends JDialog {

	private static final long serialVersionUID = 1L;
	private javax.swing.JPanel jContentPane = null;

	private JButton ok = null;
	private JButton annulla = null;
	private int modo;
	private boolean scelto;
	private String valoreNomeVecchio=""; //$NON-NLS-1$
	private String valoreTestoVecchio=""; //$NON-NLS-1$
	
	final static int MODO_NODO=0;
	final static int MODO_ATTRIBUTO=1;
	
	private TextBox nome = null;
	private TextBox testo = null;
	/**
	 * Costruttore di classe
	 * 
	 * 
	 */
	public NodeData() {
		super();
		initialize();
		this.setBounds(Utility.centraSuSchermo(this.getBounds()));
	}
	/**
	 * Visualizza la finestra di dialogo per recuperare i dati

	 * @param modo Modalità di recupero dati (Recupero dati Nodo o Attributo)
	 * @param nomeElemento Nome del nodo o dell'attributo
	 * @param testoElemento Testo del nodo o dell'attributo
	 * @return Vero se è stato premuto Ok, Falso altrimenti
	 */
	public boolean visualizza(int modo,String nomeElemento,String testoElemento)
	{
		valoreNomeVecchio=nomeElemento;
		valoreTestoVecchio=testoElemento;
		
		this.modo=modo;
		this.nome.setText(nomeElemento);
		this.testo.setText(testoElemento);
		
		if (modo==MODO_NODO)
		{
			this.setTitle(LocalizedMessages.getString("NodeData.TitleWindowNode")); //$NON-NLS-1$
			nome.setLabel(LocalizedMessages.getString("NodeData.TextboxNodeName")); //$NON-NLS-1$
			testo.setLabel(LocalizedMessages.getString("NodeData.TextBoxNodeText")); //$NON-NLS-1$
		}
		else
		{
			this.setTitle(LocalizedMessages.getString("NodeData.TitleWindowAttribute")); //$NON-NLS-1$
			nome.setLabel(LocalizedMessages.getString("NodeData.TextboxAttributeName")); //$NON-NLS-1$
			testo.setLabel(LocalizedMessages.getString("NodeData.TextboxAttributeText")); //$NON-NLS-1$
		}

		this.setModal(true);
		this.setVisible(true);
		nome.grabFocus();
		
		return scelto;
	}
	
	/**
	 * Metodo che restituisce il parametro nome recuperato dall'utente
	 * @return nome recuperato
	 */	
	public String getParametroNome()
	{
		return getNome().getText();
	}
	
	/**
	 * Metodo che restituisce il parametro testo recuperato dall'utente
	 * @return testo recuperato
	 */
	public String getParametroTesto()
	{
		return getTesto().getText();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(565, 182);
		this.setContentPane(getJContentPane());
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
			jContentPane.add(getOk(), null);
			jContentPane.add(getAnnulla(), null);
			jContentPane.add(getNome(), null);
			jContentPane.add(getTesto(), null);
		}
		return jContentPane;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getOk() {
		if (ok == null) {
			ok = new JButton();
			ok.setText("Ok"); //$NON-NLS-1$
			ok.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14)); //$NON-NLS-1$
			ok.setIcon(new ImageIcon(getClass().getResource("/icons/iconOk.gif"))); //$NON-NLS-1$
			ok.setLocation(147, 102);
			ok.setSize(130, 44);
			ok.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (getNome().getText().length()>0)
					{
						scelto=true;
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null,LocalizedMessages.getString("NodeData.MessageObbligatoryName"),LocalizedMessages.getString("NodeData.TitleObbligatoryName"),JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
			});
		}
		return ok;
	}
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getAnnulla() {
		if (annulla == null) {
			annulla = new JButton();
			annulla.setText("Cancel"); //$NON-NLS-1$
			annulla.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14)); //$NON-NLS-1$
			annulla.setIcon(new ImageIcon(getClass().getResource("/icons/iconCancel.gif"))); //$NON-NLS-1$
			annulla.setLocation(309, 102);
			annulla.setSize(130, 44);
			annulla.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					scelto=false;
					setVisible(false);
				}
			});
		}
		return annulla;
	}
	/**
	 * This method initializes casellaTesto	
	 * 	
	 * @return pacchetto.CasellaTesto	
	 */    
	private TextBox getNome() {
		if (nome == null) {
			nome = new TextBox();
			nome.setBounds(14, 25, 526, 22);
			nome.setLabel(""); //$NON-NLS-1$
			nome.setWidthLabel(110);
		}
		return nome;
	}
	/**
	 * This method initializes casellaTesto1	
	 * 	
	 * @return pacchetto.CasellaTesto	
	 */    
	private TextBox getTesto() {
		if (testo == null) {
			testo = new TextBox();
			testo.setBounds(14, 58, 526, 22);
			testo.setWidthLabel(110);
		}
		return testo;
	}
	/**
	 * @return Returns the valoreNomeVecchio.
	 */
	public String getValoreNomeVecchio() {
		return valoreNomeVecchio;
	}
	/**
	 * @return Returns the valoreTestoVecchio.
	 */
	public String getValoreTestoVecchio() {
		return valoreTestoVecchio;
	}
  }  //  @jve:decl-index=0:visual-constraint="10,10"
