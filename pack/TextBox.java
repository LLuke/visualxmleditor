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

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.*;

/**
 * Extends JPanel to create a textbox with personalized label
 * @author Ferreri Gabriele
 */
public class TextBox extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel etichetta = null;
	private JTextField campo = null;
	private int larghezzaEtichetta=50;
	
	/**
	 * Costruttore di classe 
	 */
	public TextBox() {
		super();
		initialize();
	}

	/**
	 * Questo metodo aggiorna la dimensione dell'etichetta 
	 * e del campo
	 */
	private void aggiornaDimensione()
	{
		etichetta.setSize(larghezzaEtichetta,getHeight());
		campo.setLocation(larghezzaEtichetta,0);
		campo.setSize(getWidth()-larghezzaEtichetta,getHeight());
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private  void initialize() {
		etichetta = new JLabel();
		this.setLayout(null);
		this.setSize(249, 32);
		etichetta.setText(""); //$NON-NLS-1$
		etichetta.setSize(94, 20);
		etichetta.setLocation(4, 0);
		etichetta.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14)); //$NON-NLS-1$
		this.add(etichetta, null);
		this.add(getCampo(), null);
		this.addComponentListener(new java.awt.event.ComponentAdapter() {   
			public void componentShown(java.awt.event.ComponentEvent e) {    
			} 
			public void componentResized(java.awt.event.ComponentEvent e) {
				aggiornaDimensione();
			}
		});
	}
	
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getCampo() {
		if (campo == null) {
			campo = new JTextField();
			campo.setSize(120, 20);
			campo.setLocation(97, 0);
			campo.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 14)); //$NON-NLS-1$
			campo.setForeground(java.awt.Color.black);
			campo.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if (campo.isEditable())
					{
						campo.setBackground(Color.WHITE);
					}
				} 
				public void focusGained(java.awt.event.FocusEvent e) {    
					if (campo.isEditable())
					{
						campo.setBackground(Color.YELLOW);
					}
				}
			});
		}
		return campo;
	}
	/**
	 * @return Returns the larghezzaEtichetta.
	 */
	public int getLarghezzaEtichetta() {
		return larghezzaEtichetta;
	}
	/**
	 * @param larghezzaEtichetta The larghezzaEtichetta to set.
	 */
	public void setLarghezzaEtichetta(int larghezzaEtichetta) {
		this.larghezzaEtichetta = larghezzaEtichetta;
		aggiornaDimensione();
	}

	/**
	 * @return Returns the etichetta.
	 */
	public String getEtichetta() {
		return etichetta.getText();
	}
	/**
	 * @param larghezzaEtichetta The larghezzaEtichetta to set.
	 */
	public void setEtichetta(String val) {
		etichetta.setText(val);
	}
	
	/**
	 * @return Returns the text.
	 */
	public String getText() {
		return campo.getText();
	}
	/**
	 * @param val The text to set.
	 */
	public void setText(String val) {
		campo.setText(val);
	}

	/**
	 * @return Returns the editable.
	 */
	public boolean getEditable() {
		return campo.isEditable();
	}

	/**
	 * @param val The text to set.
	 */
	public void setEditable(boolean val) {
		campo.setEditable(val);
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
