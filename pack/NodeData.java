/*
Copyright (c) 2004, Gabriele Ferreri & Ignazio Palmisano 
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer. 
Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution. 
Neither the nameNode of Gabriele Ferreri or Ignazio Palmisano nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission. 
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
	private JButton cancel = null;
	private int mode;
	private boolean choice;
	private String oldNameNode=""; //$NON-NLS-1$
	private String oldTextNode=""; //$NON-NLS-1$
	
	final static int MODE_NODE=0;
	final static int MODE_ATTRIBUTE=1;
	
	private TextBox nameNode = null;
	private TextBox textNode = null;
	/**
	 * Constructor
	 */
	public NodeData() {
		super();
		initialize();
		this.setBounds(Utility.centerToScreen(this.getBounds()));
		LocalizedMessages.refreshLanguage("NodeData",this);
	}
	/**
	 * Show window dialog

	 * @param mode Mode window dialog (Node or Attribute)
	 * @param nameElement Name node or attribute
	 * @param textElement Text node or attribute
	 * @return True if ok else false
	 */
	public boolean visualizza(int mode,String nameElement,String textElement)
	{
		oldNameNode=nameElement;
		oldTextNode=textElement;
		
		this.mode=mode;
		this.nameNode.setText(nameElement);
		this.textNode.setText(textElement);
		
		if (mode==MODE_NODE)
		{
			this.setTitle(LocalizedMessages.getString("NodeData.TitleWindowNode")); //$NON-NLS-1$
			nameNode.setLabel(LocalizedMessages.getString("NodeData.TextBoxNodeName")); //$NON-NLS-1$
			textNode.setLabel(LocalizedMessages.getString("NodeData.TextBoxNodeText")); //$NON-NLS-1$
		}
		else
		{
			this.setTitle(LocalizedMessages.getString("NodeData.TitleWindowAttribute")); //$NON-NLS-1$
			nameNode.setLabel(LocalizedMessages.getString("NodeData.TextBoxAttributeName")); //$NON-NLS-1$
			textNode.setLabel(LocalizedMessages.getString("NodeData.TextBoxAttributeText")); //$NON-NLS-1$
		}

		this.setModal(true);
		this.setVisible(true);
		nameNode.grabFocus();
		
		return choice;
	}
	
	/**
	 * Return Name Node
	 * @return nameNode
	 */	
	public String getParametroNome()
	{
		return getNameNode().getText();
	}
	
	/**
	 * Return Text Node
	 * @return textNode
	 */
	public String getParametroTesto()
	{
		return getTextNode().getText();
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
			jContentPane.setBackground(new java.awt.Color(166,202,240));
			jContentPane.add(getOk(), null);
			jContentPane.add(getCancel(), null);
			jContentPane.add(getNameNode(), null);
			jContentPane.add(getTextNode(), null);
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
			ok.setIcon(new ImageIcon(getClass().getResource("/icons/iconOk.png"))); //$NON-NLS-1$
			ok.setLocation(147, 102);
			ok.setSize(130, 44);
			ok.setName("Ok");
			ok.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (getNameNode().getText().length()>0)
					{
						choice=true;
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
	private JButton getCancel() {
		if (cancel == null) {
			cancel = new JButton();
			cancel.setText("Cancel"); //$NON-NLS-1$
			cancel.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14)); //$NON-NLS-1$
			cancel.setIcon(new ImageIcon(getClass().getResource("/icons/iconCancel.png"))); //$NON-NLS-1$
			cancel.setLocation(309, 102);
			cancel.setSize(130, 44);
			cancel.setName("Cancel");
			cancel.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					choice=false;
					setVisible(false);
				}
			});
		}
		return cancel;
	}
	/**
	 * This method initializes nameNode	
	 * 	
	 * @return TextBox	
	 */    
	private TextBox getNameNode() {
		if (nameNode == null) {
			nameNode = new TextBox();
			nameNode.setBounds(14, 25, 526, 22);
			nameNode.setLabel(""); //$NON-NLS-1$
			nameNode.setWidthLabel(110);
		}
		return nameNode;
	}
	/**
	 * This method initializes textNode	
	 * 	
	 * @return TextBox	
	 */    
	private TextBox getTextNode() {
		if (textNode == null) {
			textNode = new TextBox();
			textNode.setBounds(14, 58, 526, 22);
			textNode.setWidthLabel(110);
		}
		return textNode;
	}
	/**
	 * @return Returns the oldNameNode.
	 */
	public String getOldNameNode() {
		return oldNameNode;
	}
	/**
	 * @return Returns the oldTextNode.
	 */
	public String getoldTextNode() {
		return oldTextNode;
	}
  }  //  @jve:decl-index=0:visual-constraint="10,10"
