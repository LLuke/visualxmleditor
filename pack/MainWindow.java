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

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.io.File;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JToolBar;
import java.awt.Toolkit;
/**
 * Main graphical interface
 * @author Ferreri Gabriele 
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private javax.swing.JPanel jContentPane = null;

	private JPanel pannelloComandi = null;
	private JButton openFile = null;
	private JButton saveFile = null;
	private JButton saveFileAs = null;
	private JButton exit = null;
	private JSplitPane splitAlbero = null;
	private JScrollPane scrollAlbero = null;
	private JTreeXML albero = null;
	private JButton addNode = null;  //  @jve:decl-index=0:visual-constraint="461,442"
	private JButton modifyNode = null;
	private JButton deleteNode = null;
	private JList attributes = null;  //  @jve:decl-index=0:visual-constraint="271,213"
	private JPanel pannelloVisualizza = null;
	private JButton addAttribute = null;
	private JButton modifyAttribute = null;
	private JButton deleteAttribute = null;
	private JPanel pannelloNodo = null;
	private JPanel pannelloAttributi = null;

	private TextBox nodeName = null;
	private TextBox nodeText = null;
	private JButton about = null;
	private JButton configure = null;
	private JToolBar comandi = null;
	/**
	 * @throws java.awt.HeadlessException
	 */
	public MainWindow() {
		super();
		initialize();
		this.setBounds(Utility.centraSuSchermo(this.getBounds()));
	}

	/**
	 * Metodo che svuota tutti i dati del nodo e degli attributes
	 * dalla visualizzazione della maschera
	 */
	public void azzeraDati()
	{
		nodeName.setText(""); //$NON-NLS-1$
		nodeText.setText(""); //$NON-NLS-1$
		attributes.removeAll();
	}
	
	/**
	 * Metodo che aggiorna i dati del nodo e degli attributes
	 * nella visualizzazione delle maschera
	 * @param dmtn Nodo selezionato di cui visualizzare i dati 
	 */
	public void aggiornaDati(DefaultMutableTreeNode dmtn)
	{
		Nodo nodo=(Nodo)dmtn.getUserObject();
		nodeName.setText(nodo.getNome());
		nodeText.setText(nodo.getTesto());
		
		if (nodo.getAttributi()!=null)
		{
			attributes.setListData(nodo.getAttributi().values().toArray());
		}
		((DefaultTreeModel)getAlbero().getModel()).nodeStructureChanged(dmtn);
	}
	
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getPannelloComandi() {
		if (pannelloComandi == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			pannelloComandi = new JPanel();
			pannelloComandi.setLayout(flowLayout1);
			flowLayout1.setAlignment(java.awt.FlowLayout.LEFT);
			pannelloComandi.add(getComandi(), null);
		}
		return pannelloComandi;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getOpenFile() {
		if (openFile == null) {
			openFile = new JButton();
			openFile.setText(LocalizedMessages.getString("MainWindow.ButtonOpenFile")); //$NON-NLS-1$
			openFile.setIcon(new ImageIcon(getClass().getResource("/icons/iconOpenFile.gif"))); //$NON-NLS-1$
			openFile.setToolTipText(LocalizedMessages.getString("MainWindow.TipButtonOpenFile")); //$NON-NLS-1$
			openFile.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			openFile.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
			openFile.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
			openFile.setPreferredSize(new java.awt.Dimension(125,44));
			openFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					JFileChooser dlgApri = new JFileChooser();
					
					dlgApri.setFileFilter(new XMLFilter());
					dlgApri.setCurrentDirectory(new File(System.getProperty("user.dir"))); //$NON-NLS-1$
					
					int res=dlgApri.showOpenDialog(null);
						
					if (res==JFileChooser.APPROVE_OPTION)
					{
						azzeraDati();
						albero.caricaXML(dlgApri.getSelectedFile().toString());
					}
				}
			});
		}
		return openFile;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getSaveFile() {
		if (saveFile == null) {
			saveFile = new JButton();
			saveFile.setIcon(new ImageIcon(getClass().getResource("/icons/iconSaveFile.gif"))); //$NON-NLS-1$
			saveFile.setText(LocalizedMessages.getString("MainWindow.ButtonSaveFile")); //$NON-NLS-1$
			saveFile.setToolTipText(LocalizedMessages.getString("MainWindow.TipButtonSaveFile")); //$NON-NLS-1$
			saveFile.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			saveFile.setPreferredSize(new java.awt.Dimension(125,44));
			saveFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					//albero.salvaXML(albero.getNomeFile());
				}
			});
		}
		return saveFile;
	}
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getSaveFileAs() {
		if (saveFileAs == null) {
			saveFileAs = new JButton();
			saveFileAs.setIcon(new ImageIcon(getClass().getResource("/icons/iconSaveFileAs.gif"))); //$NON-NLS-1$
			saveFileAs.setText(LocalizedMessages.getString("MainWindow.ButtonSaveFileAs")); //$NON-NLS-1$
			saveFileAs.setToolTipText(LocalizedMessages.getString("MainWindow.TipButtonSaveFileAs")); //$NON-NLS-1$
			saveFileAs.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			saveFileAs.setPreferredSize(new java.awt.Dimension(125,44));
			saveFileAs.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					JFileChooser dlgSalva = new JFileChooser();
					boolean ok=false;
					
					dlgSalva.setFileFilter(new XMLFilter());
					dlgSalva.setCurrentDirectory(new File(System.getProperty("user.dir"))); //$NON-NLS-1$
					
					int res=dlgSalva.showOpenDialog(null);
					
					if (res==JFileChooser.APPROVE_OPTION)
					{
						if (dlgSalva.getSelectedFile().exists())
						{
							if (JOptionPane.showConfirmDialog(null,LocalizedMessages.getString("MainWindow.MessageFileExistOverwrite"),LocalizedMessages.getString("MainWindow.TitleFileExistOverwrite"),JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) //$NON-NLS-1$ //$NON-NLS-2$
							{
								ok=true;
							}
						}
						else
						{
							ok=true;
						}
						
						if (ok)
						{
							//albero.salvaXML(dlgSalva.getSelectedFile().toString());
						}
					}
				}
			});
		}
		return saveFileAs;
	}
	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getExit() {
		if (exit == null) {
			exit = new JButton();
			exit.setIcon(new ImageIcon(getClass().getResource("/icons/iconExit.gif"))); //$NON-NLS-1$
			exit.setText(LocalizedMessages.getString("MainWindow.ButtonExit")); //$NON-NLS-1$
			exit.setToolTipText(LocalizedMessages.getString("MainWindow.TipButtonExit")); //$NON-NLS-1$
			exit.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			exit.setPreferredSize(new java.awt.Dimension(125,44));
			exit.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (JOptionPane.showConfirmDialog(null,LocalizedMessages.getString("MainWindow.MessageConfirmationExit"),LocalizedMessages.getString("MainWindow.TitleConfirmationExit"),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) //$NON-NLS-1$ //$NON-NLS-2$
					{
						System.exit(0);	
					}
				}
			});
		}
		return exit;
	}
	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */    
	private JSplitPane getSplitAlbero() {
		if (splitAlbero == null) {
			splitAlbero = new JSplitPane();
			splitAlbero.setLeftComponent(getScrollAlbero());
			splitAlbero.setDividerSize(10);
			splitAlbero.setDividerLocation(150);
			splitAlbero.setRightComponent(getPannelloVisualizza());
		}
		return splitAlbero;
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getScrollAlbero() {
		if (scrollAlbero == null) {
			scrollAlbero = new JScrollPane();
			scrollAlbero.setViewportView(getAlbero());
		}
		return scrollAlbero;
	}
	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */    
	private JTreeXML getAlbero() {
		if (albero == null) {
			albero = new JTreeXML();
			albero.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 12)); //$NON-NLS-1$
			albero.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() { 
				public void valueChanged(javax.swing.event.TreeSelectionEvent e) {    
					DefaultMutableTreeNode dmtn=(DefaultMutableTreeNode)albero.getLastSelectedPathComponent();
					
					if (dmtn!=null)
					{
						aggiornaDati(dmtn);
					}
				}
			});
		}
		return albero;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getAddNode() {
		if (addNode == null) {
			addNode = new JButton();
			addNode.setText(LocalizedMessages.getString("MainWindow.ButtonAddNode")); //$NON-NLS-1$
			addNode.setIcon(new ImageIcon(getClass().getResource("/icons/iconAddNode.gif"))); //$NON-NLS-1$
			addNode.setToolTipText(LocalizedMessages.getString("MainWindow.TipButtonAddNode")); //$NON-NLS-1$
			addNode.setName("addNode"); //$NON-NLS-1$
			addNode.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			addNode.setSize(125, 44);
			addNode.setLocation(14, 140);
			addNode.setPreferredSize(new java.awt.Dimension(130,44));
			addNode.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					boolean ok=false;
					NodeData dati = new NodeData();
					
					if (albero.getSelectionPath()!=null)
					{
						DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)albero.getLastSelectedPathComponent();
						while (!ok)
						{
							ok=dati.visualizza(NodeData.MODO_ATTRIBUTO,"",""); //$NON-NLS-1$ //$NON-NLS-2$
							if (ok)
							{
								albero.aggiungiNodo(dmtn,dati.getParametroNome(),dati.getParametroTesto());
							}
							else
							{
								break;
							}
						}
						aggiornaDati(dmtn);
					}
				}
			});
		}
		return addNode;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getModifyNode() {
		if (modifyNode == null) {
			modifyNode = new JButton();
			modifyNode.setIcon(new ImageIcon(getClass().getResource("/icons/iconModifyNode.gif"))); //$NON-NLS-1$
			modifyNode.setText(LocalizedMessages.getString("MainWindow.ButtonModifyNode")); //$NON-NLS-1$
			modifyNode.setToolTipText(LocalizedMessages.getString("MainWindow.TipButtonModifyNode")); //$NON-NLS-1$
			modifyNode.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			modifyNode.setLocation(250, 140);
			modifyNode.setSize(125, 44);
			modifyNode.setPreferredSize(new java.awt.Dimension(130,44));
			modifyNode.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					boolean ok=false;
					NodeData dati = new NodeData();
					
					if (albero.getSelectionPath()!=null)
					{
						DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)albero.getLastSelectedPathComponent();
						Nodo nodo = (Nodo)dmtn.getUserObject();
						
						while (!ok)
						{
							ok=dati.visualizza(NodeData.MODO_ATTRIBUTO ,nodo.getNome(),nodo.getTesto());
							if (ok)
							{
								albero.modificaNodo(dmtn,dati.getParametroNome(),dati.getParametroTesto());
							}
							else
							{
								break;
							}
						}
						aggiornaDati(dmtn);
					}
				}
			});
		}
		return modifyNode;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getDeleteNode() {
		if (deleteNode == null) {
			deleteNode = new JButton();
			deleteNode.setText(LocalizedMessages.getString("MainWindow.ButtonDeleteNode")); //$NON-NLS-1$
			deleteNode.setIcon(new ImageIcon(getClass().getResource("/icons/iconDeleteNode.gif"))); //$NON-NLS-1$
			deleteNode.setToolTipText(LocalizedMessages.getString("MainWindow.TipButtonDeleteNode")); //$NON-NLS-1$
			deleteNode.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			deleteNode.setLocation(486, 140);
			deleteNode.setSize(125, 44);
			deleteNode.setPreferredSize(new java.awt.Dimension(130,44));
			deleteNode.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (albero.getSelectionPath()!=null)
					{
						if (JOptionPane.showConfirmDialog(null,LocalizedMessages.getString("MainWindow.MessageConfirmationDeleteNode"),LocalizedMessages.getString("MainWindow.TitleConfirmationDeleteNode"),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) //$NON-NLS-1$ //$NON-NLS-2$
						{
							albero.eliminaNodo((DefaultMutableTreeNode)albero.getLastSelectedPathComponent());
						}
					}
				}
			});
		}
		return deleteNode;
	}
	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */    
	private JList getAttributes() {
		if (attributes == null) {
			attributes = new JList();
			attributes.setPreferredSize(new java.awt.Dimension(300,300));
			attributes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
			attributes.setSize(585, 182);
			attributes.setLocation(10, 37);
			attributes.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14)); //$NON-NLS-1$
		}
		return attributes;
	}
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getPannelloVisualizza() {
		if (pannelloVisualizza == null) {
			pannelloVisualizza = new JPanel();
			pannelloVisualizza.setLayout(null);
			pannelloVisualizza.add(getAddNode(), null);
			pannelloVisualizza.add(getDeleteNode(), null);
			pannelloVisualizza.add(getModifyNode(), null);
			pannelloVisualizza.add(getAddAttribute(), null);
			pannelloVisualizza.add(getModifyAttribute(), null);
			pannelloVisualizza.add(getDeleteAttribute(), null);
			pannelloVisualizza.add(getPannelloNodo(), null);
			pannelloVisualizza.add(getPannelloAttributi(), null);
		}
		return pannelloVisualizza;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getAddAttribute() {
		if (addAttribute == null) {
			addAttribute = new JButton();
			addAttribute.setText(LocalizedMessages.getString("MainWindow.ButtonAddAttribute")); //$NON-NLS-1$
			addAttribute.setIcon(new ImageIcon(getClass().getResource("/icons/iconAddAttribute.gif"))); //$NON-NLS-1$
			addAttribute.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			addAttribute.setLocation(15, 447);
			addAttribute.setSize(125, 44);
			addAttribute.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					boolean ok=false;
					NodeData dati = new NodeData();
					
					if (albero.getSelectionPath()!=null)
					{
						DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)albero.getLastSelectedPathComponent();
						while (!ok)
						{
							ok=dati.visualizza(NodeData.MODO_ATTRIBUTO,"",""); //$NON-NLS-1$ //$NON-NLS-2$
							if (ok)
							{
								ok=albero.aggiungiAttributo(dmtn,dati.getParametroNome(),dati.getParametroTesto());
								if (!ok)
								{
									JOptionPane.showMessageDialog(null,LocalizedMessages.getString("MainWindow.MessageAttributeExist"),LocalizedMessages.getString("MainWindow.TitleAttributeExist"),JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
								}
							}
							else
							{
								break;
							}
						}
						aggiornaDati(dmtn);
					}
				}
			});
		}
		return addAttribute;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getModifyAttribute() {
		if (modifyAttribute == null) {
			modifyAttribute = new JButton();
			modifyAttribute.setText(LocalizedMessages.getString("MainWindow.ButtonModifyAttribute")); //$NON-NLS-1$
			modifyAttribute.setIcon(new ImageIcon(getClass().getResource("/icons/iconModifyAttribute.gif"))); //$NON-NLS-1$
			modifyAttribute.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			modifyAttribute.setLocation(250, 447);
			modifyAttribute.setSize(125, 44);
			modifyAttribute.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					boolean ok=false;
					NodeData dati = new NodeData();
					
					if (albero.getSelectionPath()!=null)
					{
						DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)albero.getLastSelectedPathComponent();
						Nodo nodo = (Nodo)dmtn.getUserObject();
						if (attributes.getSelectedValue()!=null)
						{
							String[] valori=((String)attributes.getSelectedValue()).split("="); //$NON-NLS-1$
							
							while (!ok)
							{
								ok=dati.visualizza(NodeData.MODO_ATTRIBUTO ,valori[0],valori[1]);
								if (ok)
								{
									ok=albero.modificaAttributo(dmtn,dati.getParametroNome(),dati.getParametroTesto(),dati.getValoreNomeVecchio());
									if (!ok)
									{
										JOptionPane.showMessageDialog(null,LocalizedMessages.getString("MainWindow.MessageAttributeExist"),LocalizedMessages.getString("MainWindow.TitleAttributeExist"),JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
									}
								}
								else
								{
									break;
								}
							}
							aggiornaDati(dmtn);
						}
					}
				}
			});
		}
		return modifyAttribute;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getDeleteAttribute() {
		if (deleteAttribute == null) {
			deleteAttribute = new JButton();
			deleteAttribute.setText(LocalizedMessages.getString("MainWindow.ButtonDeleteAttribute")); //$NON-NLS-1$
			deleteAttribute.setIcon(new ImageIcon(getClass().getResource("/icons/iconDeleteAttribute.gif"))); //$NON-NLS-1$
			deleteAttribute.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			deleteAttribute.setLocation(485, 447);
			deleteAttribute.setSize(125, 44);
			deleteAttribute.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (albero.getSelectionPath()!=null)
					{
						if (attributes.getSelectedValue()!=null)
						{
							if (JOptionPane.showConfirmDialog(null,LocalizedMessages.getString("MainWindow.MessageConfirmationDeleteAttribute"),LocalizedMessages.getString("MainWindow.TitleConfirmationDeleteAttribute"),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) //$NON-NLS-1$ //$NON-NLS-2$
							{
							
								String nomeAttributo=((String)attributes.getSelectedValue());
								nomeAttributo=nomeAttributo.substring(0,nomeAttributo.indexOf('='));
								DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)albero.getLastSelectedPathComponent();
								albero.eliminaAttributo(dmtn,nomeAttributo);
								aggiornaDati(dmtn);
							}
						}
					}
				}
			});
		}
		return deleteAttribute;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getPannelloNodo() {
		if (pannelloNodo == null) {
			pannelloNodo = new JPanel();
			pannelloNodo.setLayout(null);
			pannelloNodo.setBounds(15, 27, 602, 104);
			pannelloNodo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), LocalizedMessages.getString("MainWindow.TitleSectionNode"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 24), java.awt.Color.black)); //$NON-NLS-1$ //$NON-NLS-2$
			pannelloNodo.add(getNodeName(), null);
			pannelloNodo.add(getNodeText(), null);
		}
		return pannelloNodo;
	}
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getPannelloAttributi() {
		if (pannelloAttributi == null) {
			pannelloAttributi = new JPanel();
			pannelloAttributi.setLayout(null);
			pannelloAttributi.setBounds(14, 205, 602, 227);
			pannelloAttributi.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), LocalizedMessages.getString("MainWindow.TitleSectionAttribute"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 24), java.awt.Color.black)); //$NON-NLS-1$ //$NON-NLS-2$
			pannelloAttributi.add(getAttributes(), null);
		}
		return pannelloAttributi;
	}
	/**
	 * This method initializes casellaTesto	
	 * 	
	 * @return pacchetto.CasellaTesto	
	 */    
	private TextBox getNodeName() {
		if (nodeName == null) {
			nodeName = new TextBox();
			nodeName.setBounds(10, 42, 582, 22);
			nodeName.setWidthLabel(100);
			nodeName.setLabel(LocalizedMessages.getString("MainWindow.TextboxNodeName")); //$NON-NLS-1$
			nodeName.setEditable(false);
		}
		return nodeName;
	}
	/**
	 * This method initializes casellaTesto1	
	 * 	
	 * @return pacchetto.CasellaTesto	
	 */    
	private TextBox getNodeText() {
		if (nodeText == null) {
			nodeText = new TextBox();
			nodeText.setBounds(10, 73, 582, 22);
			nodeText.setWidthLabel(100);
			nodeText.setLabel(LocalizedMessages.getString("MainWindow.TextboxNodeText")); //$NON-NLS-1$
			nodeText.setEditable(false);
		}
		return nodeText;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getAbout() {
		if (about == null) {
			about = new JButton();
			about.setText(LocalizedMessages.getString("MainWindow.ButtonAbout")); //$NON-NLS-1$
			about.setIcon(new ImageIcon(getClass().getResource("/icons/iconAbout.gif"))); //$NON-NLS-1$
			about.setPreferredSize(new java.awt.Dimension(125,44));
			about.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					new About().setVisible(true);
				}
			});
		}
		return about;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getConfigure() {
		if (configure == null) {
			configure = new JButton();
			configure.setText(LocalizedMessages.getString("MainWindow.ButtonConfigure")); //$NON-NLS-1$
			configure.setToolTipText(LocalizedMessages.getString("MainWindow.TipButtonConfigure")); //$NON-NLS-1$
			configure.setIcon(new ImageIcon(getClass().getResource("/icons/iconConfig.gif"))); //$NON-NLS-1$
			configure.setHorizontalTextPosition(javax.swing.SwingConstants.TRAILING);
			configure.setPreferredSize(new java.awt.Dimension(125,44));
			configure.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new Configuration().setVisible(true);
					setVisible(false);
					setVisible(true);
				}
			});
		}
		return configure;
	}
	/**
	 * This method initializes comandi	
	 * 	
	 * @return javax.swing.JToolBar	
	 */    
	private JToolBar getComandi() {
		if (comandi == null) {
			comandi = new JToolBar();
			comandi.setFloatable(false);
			comandi.add(getOpenFile());
			comandi.add(getSaveFile());
			comandi.add(getSaveFileAs());
			comandi.add(getConfigure());
			comandi.add(getAbout());
			comandi.add(getExit());
		}
		return comandi;
	}
        	public static void main(String[] args) {
         		new MainWindow().setVisible(true);
         		
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		LocalizedMessages.reInit(Configuration.getCurrentLanguage()); //$NON-NLS-1$
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/iconProgram.gif"))); //$NON-NLS-1$
		this.setSize(800,600);
		this.setContentPane(getJContentPane());
		this.setTitle("Editor XML 0.1"); //$NON-NLS-1$
		this.setBounds(Utility.centraSuSchermo(this.getBounds()));
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.BorderLayout());
			jContentPane.add(getPannelloComandi(), java.awt.BorderLayout.NORTH);
			jContentPane.add(getSplitAlbero(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}
}
