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
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	/**
	 * 
	 */
	public About() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setResizable(false);
		this.setTitle(LocalizedMessages.getString("About.TitleWindowAbout")); //$NON-NLS-1$
		this.setSize(451, 261);
		this.setContentPane(getJContentPane());
		this.setBounds(Utility.centraSuSchermo(this.getBounds()));		
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel1 = new JLabel();
			jLabel = new JLabel();
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(null);
			jLabel.setBounds(5, 16, 436, 62);
			jLabel.setText("Visual XML Editor 0.1"); //$NON-NLS-1$
			jLabel.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 40)); //$NON-NLS-1$
			jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel.setForeground(java.awt.Color.red);
			jLabel.setBackground(java.awt.Color.yellow);
			jContentPane.setBackground(java.awt.Color.yellow);
			jLabel1.setBounds(5, 98, 436, 36);
			jLabel1.setText(LocalizedMessages.getString("About.LabelCreatedBy")); //$NON-NLS-1$
			jLabel1.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 24)); //$NON-NLS-1$
			jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel2.setBounds(5, 137, 436, 42);
			jLabel2.setText("Gabriele Ferreri & Ignazio Palmisano"); //$NON-NLS-1$
			jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			jLabel2.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD | java.awt.Font.ITALIC, 26)); //$NON-NLS-1$
			jContentPane.add(getOk(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
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
			ok.setIcon(new ImageIcon(getClass().getResource("/icons/iconOk.gif"))); //$NON-NLS-1$
			ok.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					setVisible(false);
				}
			});
		}
		return ok;
	}
 }  //  @jve:decl-index=0:visual-constraint="10,10"
