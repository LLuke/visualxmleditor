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

import javax.swing.JLabel;
import javax.swing.JButton;

import javax.swing.ImageIcon;
/**
 * About graphical interface
 * @author Ferreri Gabriele 
 */
public class About extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private javax.swing.JPanel jContentPane = null;

	private JButton ok = null;
	private JLabel title = null;
	private JLabel createdBy = null;
	private JLabel Authors = null;  //  @jve:decl-index=0:
	/**
	 * 
	 */
	public About() {
		super();
		initialize();
		this.setBounds(Utility.centerToScreen(this.getBounds()));
		LocalizedMessages.refreshLanguage("About",this);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setModal(true);
		this.setResizable(false);
		this.setTitle("About"); //$NON-NLS-1$
		this.setSize(451, 261);
		this.setContentPane(getJContentPane());
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			Authors = new JLabel();
			createdBy = new JLabel();
			title = new JLabel();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			title.setBounds(5, 16, 436, 62);
			title.setText("Visual XML Editor 0.1"); //$NON-NLS-1$
			title.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 40)); //$NON-NLS-1$
			title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			title.setForeground(java.awt.Color.red);
			title.setBackground(java.awt.Color.yellow);
			title.setName("Title");
			Authors.setName("Authors");
			jContentPane.setBackground(new java.awt.Color(166,202,240));
			createdBy.setBounds(5, 98, 436, 36);
			createdBy.setText("Created by"); //$NON-NLS-1$
			createdBy.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 24)); //$NON-NLS-1$
			createdBy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			createdBy.setName("CreatedBy");
			Authors.setBounds(5, 137, 436, 42);
			Authors.setText("Gabriele Ferreri & Ignazio Palmisano"); //$NON-NLS-1$
			Authors.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			Authors.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD | java.awt.Font.ITALIC, 26)); //$NON-NLS-1$
			jContentPane.add(getOk(), null);
			jContentPane.add(title, null);
			jContentPane.add(createdBy, null);
			jContentPane.add(Authors, null);
		}
		return jContentPane;
	}
	/**
	 * This method initializes ok	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getOk() {
		if (ok == null) {
			ok = new JButton();
			ok.setBounds(148, 187, 138, 47);
			ok.setText("Ok"); //$NON-NLS-1$
			ok.setIcon(new ImageIcon(getClass().getResource("/icons/iconOk.png"))); //$NON-NLS-1$
			ok.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					setVisible(false);
				}
			});
		}
		return ok;
	}
 }  //  @jve:decl-index=0:visual-constraint="10,10"
