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

	private JLabel label = null;
	private JTextField field = null;
	private int width=80;
	
	/**
	 * Constructor 
	 */
	public TextBox() {
		super();
		initialize();
	}

	/**
	 * This method refresh label and field dimension 
	 * 
	 */
	private void refreshDimension()
	{
		label.setSize(width,getHeight());
		field.setLocation(width,0);
		field.setSize(getWidth()-width,getHeight());
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private  void initialize() {
		label = new JLabel();
		this.setLayout(null);
		this.setSize(249, 32);
		this.setBackground(new java.awt.Color(166,202,240));
		label.setText(""); //$NON-NLS-1$
		label.setSize(94, 20);
		label.setLocation(4, 0);
		label.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14)); //$NON-NLS-1$
		this.add(label, null);
		this.add(getField(), null);
		refreshDimension();
		this.addComponentListener(new java.awt.event.ComponentAdapter() {   
			public void componentResized(java.awt.event.ComponentEvent e) {
				refreshDimension();
			}
		});
	}
	
	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */    
	private JTextField getField() {
		if (field == null) {
			field = new JTextField();
			field.setSize(120, 20);
			field.setLocation(97, 0);
			field.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 14)); //$NON-NLS-1$
			field.addFocusListener(new java.awt.event.FocusAdapter() {   
				public void focusLost(java.awt.event.FocusEvent e) {    
					if (field.isEditable())
					{
						field.setBackground(Color.WHITE);
					}
				} 
				public void focusGained(java.awt.event.FocusEvent e) {    
					if (field.isEditable())
					{
						field.setBackground(Color.YELLOW);
					}
				}
			});
		}
		return field;
	}
	/**
	 * @return Returns the width label.
	 */
	public int getWidthLabel() {
		return width;
	}
	/**
	 * @param set width label.
	 */
	public void setWidthLabel(int widthLabel) {
		this.width = widthLabel;
		refreshDimension();
	}

	/**
	 * @return Returns the label.
	 */
	public String getLabel() {
		return label.getText();
	}
	/**
	 * @param set text label.
	 */
	public void setLabel(String value) {
		label.setText(value);
	}
	
	/**
	 * @return Returns the text field.
	 */
	public String getText() {
		return field.getText();
	}
	/**
	 * @param set text field
	 */
	public void setText(String value) {
		field.setText(value);
	}

	/**
	 * @return Returns the editable.
	 */
	public boolean getEditable() {
		return field.isEditable();
	}

	/**
	 * @param set editable value
	 */
	public void setEditable(boolean value) {
		field.setEditable(value);
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
