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
	private final String titleProgram="Editor XML 0.1";
	
	private javax.swing.JPanel jContentPane = null;
	private JPanel panelCommands = null;
	private JButton openFile = null;
	private JButton saveFile = null;
	private JButton saveFileAs = null;
	private JButton exit = null;
	private JSplitPane splitTree = null;
	private JScrollPane scrollAlbero = null;
	private JTreeXML tree = null;
	private JButton addNode = null;  //  @jve:decl-index=0:visual-constraint="461,442"
	private JButton modifyNode = null;
	private JButton deleteNode = null;
	private JList attributes = null;  //  @jve:decl-index=0:visual-constraint="271,213"
	private JPanel panelView = null;
	private JButton addAttribute = null;
	private JButton modifyAttribute = null;
	private JButton deleteAttribute = null;
	private JPanel panelNode = null;
	private JPanel panelAttribute = null;

	private TextBox nodeName = null;
	private TextBox nodeText = null;
	private JButton about = null;
	private JButton configure = null;
	private JToolBar commands = null;
	private JButton newFile = null;
	
	private boolean modifiedFile=false;
	
	/**
	 * @throws java.awt.HeadlessException
	 */
	public MainWindow() {
		super();
		initialize();
		this.setBounds(Utility.centerToScreen(this.getBounds()));
		LocalizedMessages.reInit(Configuration.getCurrentLanguage());
		LocalizedMessages.refreshLanguage("MainWindow",this);
		tree.newXML();
		tree.refresh();
	}

	/**
	 * Reset Node and Attributes values
	 */
	public void resetData()
	{
		nodeName.setText(""); //$NON-NLS-1$
		nodeText.setText(""); //$NON-NLS-1$
		attributes.removeAll();
	}
	
	/**
	 * Refresh Node Data and Attributes Data
	 * @param dmtn Node Data to show 
	 */
	public void refreshData(DefaultMutableTreeNode dmtn)
	{
		NodeElement node=(NodeElement)dmtn.getUserObject();
		nodeName.setText(node.getName());
		nodeText.setText(node.getText());
		
		if (node.getAttributes()!=null)
		{
			attributes.setListData(node.getAttributes().values().toArray());
		}
		((DefaultTreeModel)getTree().getModel()).nodeStructureChanged(dmtn);
	}
	
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getPanelCommands() {
		if (panelCommands == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			panelCommands = new JPanel();
			panelCommands.setLayout(flowLayout1);
			flowLayout1.setAlignment(java.awt.FlowLayout.LEFT);
			panelCommands.setBackground(new java.awt.Color(166,202,240));
			panelCommands.add(getCommands(), null);
		}
		return panelCommands;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getOpenFile() {
		if (openFile == null) {
			openFile = new JButton();
			openFile.setText("Open..."); //$NON-NLS-1$
			openFile.setIcon(new ImageIcon(getClass().getResource("/icons/iconOpenFile.png"))); //$NON-NLS-1$
			openFile.setToolTipText("Open a File"); //$NON-NLS-1$
			openFile.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			openFile.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
			openFile.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
			openFile.setPreferredSize(new java.awt.Dimension(125,44));
			openFile.setName("OpenFile");
			openFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					JFileChooser dlgApri = new JFileChooser();
					
					dlgApri.setFileFilter(new XMLFilter());
					dlgApri.setCurrentDirectory(new File(System.getProperty("user.dir"))); //$NON-NLS-1$
					
					int res=dlgApri.showOpenDialog(null);
						
					if (res==JFileChooser.APPROVE_OPTION)
					{
						resetData();
						tree.loadXML(dlgApri.getSelectedFile().toString());
						tree.refresh();
						setTitle(titleProgram + " - " + tree.getFileNameXML());
						modifiedFile=false;
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
			saveFile.setIcon(new ImageIcon(getClass().getResource("/icons/iconSaveFile.png"))); //$NON-NLS-1$
			saveFile.setText("Save"); //$NON-NLS-1$
			saveFile.setToolTipText("Save current file"); //$NON-NLS-1$
			saveFile.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			saveFile.setPreferredSize(new java.awt.Dimension(125,44));
			saveFile.setName("SaveFile");
			saveFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					tree.saveXML(tree.getFileNameXML());
					modifiedFile=false;
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
			saveFileAs.setIcon(new ImageIcon(getClass().getResource("/icons/iconSaveFileAs.png"))); //$NON-NLS-1$
			saveFileAs.setText("Save..."); //$NON-NLS-1$
			saveFileAs.setToolTipText("Save current file as..."); //$NON-NLS-1$
			saveFileAs.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			saveFileAs.setPreferredSize(new java.awt.Dimension(125,44));
			saveFileAs.setName("SaveFileAs");
			saveFileAs.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					JFileChooser dlgSalva = new JFileChooser();
					boolean ok=false;
					
					dlgSalva.setFileFilter(new XMLFilter());
					dlgSalva.setCurrentDirectory(new File(System.getProperty("user.dir"))); //$NON-NLS-1$
					
					int res=dlgSalva.showSaveDialog(null);
					
					if (res==JFileChooser.APPROVE_OPTION)
					{
						String filename=dlgSalva.getSelectedFile().toString();
						if (!filename.endsWith(".xml"))
						{
							filename=filename + ".xml";
						}
						
						if (new File(filename).exists())
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
							tree.saveXML(filename);
							tree.refresh();
							modifiedFile=false;
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
			exit.setIcon(new ImageIcon(getClass().getResource("/icons/iconExit.png"))); //$NON-NLS-1$
			exit.setText("Exit"); //$NON-NLS-1$
			exit.setToolTipText("Exit application"); //$NON-NLS-1$
			exit.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			exit.setPreferredSize(new java.awt.Dimension(125,44));
			exit.setName("Exit");
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
	private JSplitPane getSplitTree() {
		if (splitTree == null) {
			splitTree = new JSplitPane();
			splitTree.setLeftComponent(getScrollAlbero());
			splitTree.setDividerSize(10);
			splitTree.setDividerLocation(150);
			splitTree.setRightComponent(getPanelView());
		}
		return splitTree;
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */    
	private JScrollPane getScrollAlbero() {
		if (scrollAlbero == null) {
			scrollAlbero = new JScrollPane();
			scrollAlbero.setViewportView(getTree());
		}
		return scrollAlbero;
	}
	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */    
	private JTreeXML getTree() {
		if (tree == null) {
			tree = new JTreeXML();
			tree.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 12)); //$NON-NLS-1$
			tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() { 
				public void valueChanged(javax.swing.event.TreeSelectionEvent e) {    
					DefaultMutableTreeNode dmtn=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
					
					if (dmtn!=null)
					{
						refreshData(dmtn);
					}
				}
			});
		}
		return tree;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getAddNode() {
		if (addNode == null) {
			addNode = new JButton();
			addNode.setText("Add"); //$NON-NLS-1$
			addNode.setIcon(new ImageIcon(getClass().getResource("/icons/iconAddNode.png"))); //$NON-NLS-1$
			addNode.setToolTipText("Add child node to current node"); //$NON-NLS-1$
			addNode.setName("AddNode"); //$NON-NLS-1$
			addNode.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			addNode.setSize(130, 44);
			addNode.setLocation(15, 140);
			addNode.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					boolean ok=true;
					NodeData dati = new NodeData();
					
					if (tree.getLastSelectedPathComponent()!=null)
					{
						DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
						
						NodeElement node = (NodeElement)dmtn.getUserObject();
						
						if (node.getType()==NodeElement.TYPENODE_FILE)
						{
							if (dmtn.getChildCount()<1)
							{
								ok=false;
							}
							else
							{
								JOptionPane.showMessageDialog(null,LocalizedMessages.getString("MessageMainNodeAllreadyExist"),LocalizedMessages.getString("TitleMainNodeAllreadyExist"),JOptionPane.OK_OPTION);
							}
						}
						
						if ((node.getType()==NodeElement.TYPENODE_NODEWITHATTRIBUTES) || (node.getType()==NodeElement.TYPENODE_WITHOUTATTRIBUTES))
						{
							ok=false;
						}

						while (!ok)
						{
							ok=dati.visualizza(NodeData.MODE_NODE,"",""); //$NON-NLS-1$ //$NON-NLS-2$
							if (ok)
							{
								tree.addNode(dmtn,dati.getParametroNome(),dati.getParametroTesto());
								modifiedFile=true;
							}
							else
							{
								break;
							}
						}
						refreshData(dmtn);
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
			modifyNode.setIcon(new ImageIcon(getClass().getResource("/icons/iconModifyNode.png"))); //$NON-NLS-1$
			modifyNode.setText("Modify"); //$NON-NLS-1$
			modifyNode.setToolTipText("Modify current node"); //$NON-NLS-1$
			modifyNode.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			modifyNode.setLocation(253, 140);
			modifyNode.setSize(130, 44);
			modifyNode.setName("ModifyNode");
			modifyNode.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					boolean ok=false;
					NodeData dati = new NodeData();
					
					if (tree.getSelectionPath()!=null)
					{
						DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
						NodeElement nodo = (NodeElement)dmtn.getUserObject();
						
						while (!ok)
						{
							ok=dati.visualizza(NodeData.MODE_NODE ,nodo.getName(),nodo.getText());
							if (ok)
							{
								tree.modifyNode(dmtn,dati.getParametroNome(),dati.getParametroTesto());
								modifiedFile=true;
							}
							else
							{
								break;
							}
						}
						refreshData(dmtn);
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
			deleteNode.setText("Delete"); //$NON-NLS-1$
			deleteNode.setIcon(new ImageIcon(getClass().getResource("/icons/iconDeleteNode.png"))); //$NON-NLS-1$
			deleteNode.setToolTipText("Delete current node"); //$NON-NLS-1$
			deleteNode.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			deleteNode.setLocation(485, 140);
			deleteNode.setSize(130, 44);
			deleteNode.setName("DeleteNode");
			deleteNode.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (tree.getLastSelectedPathComponent()!=null)
					{
						NodeElement node = (NodeElement)((DefaultMutableTreeNode)tree.getLastSelectedPathComponent()).getUserObject();
						if ((node.getType()==NodeElement.TYPENODE_NODEWITHATTRIBUTES) || (node.getType()==NodeElement.TYPENODE_WITHOUTATTRIBUTES))
						{
							if (JOptionPane.showConfirmDialog(null,LocalizedMessages.getString("MainWindow.MessageConfirmationDeleteNode"),LocalizedMessages.getString("MainWindow.TitleConfirmationDeleteNode"),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) //$NON-NLS-1$ //$NON-NLS-2$
							{
								tree.deleteNode((DefaultMutableTreeNode)tree.getLastSelectedPathComponent());
								modifiedFile=true;
							}
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
	private JPanel getPanelView() {
		if (panelView == null) {
			panelView = new JPanel();
			panelView.setLayout(null);
			panelView.setBackground(new java.awt.Color(166,202,240));
			panelView.add(getAddNode(), null);
			panelView.add(getDeleteNode(), null);
			panelView.add(getModifyNode(), null);
			panelView.add(getAddAttribute(), null);
			panelView.add(getModifyAttribute(), null);
			panelView.add(getDeleteAttribute(), null);
			panelView.add(getPanelNode(), null);
			panelView.add(getPanelAttribute(), null);
		}
		return panelView;
	}
	/**
	 * This method initializes configure	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getAddAttribute() {
		if (addAttribute == null) {
			addAttribute = new JButton();
			addAttribute.setText("Add"); //$NON-NLS-1$
			addAttribute.setIcon(new ImageIcon(getClass().getResource("/icons/iconAddAttribute.png"))); //$NON-NLS-1$
			addAttribute.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			addAttribute.setLocation(15, 447);
			addAttribute.setSize(130, 44);
			addAttribute.setName("AddAttribute");
			addAttribute.setToolTipText("Add attribute to current node");
			addAttribute.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					boolean ok=false;
					NodeData dati = new NodeData();
					
					if (tree.getSelectionPath()!=null)
					{
						DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
						while (!ok)
						{
							ok=dati.visualizza(NodeData.MODE_ATTRIBUTE,"",""); //$NON-NLS-1$ //$NON-NLS-2$
							if (ok)
							{
								ok=tree.addAttribute(dmtn,dati.getParametroNome(),dati.getParametroTesto());
								if (!ok)
								{
									JOptionPane.showMessageDialog(null,LocalizedMessages.getString("MainWindow.MessageAttributeExist"),LocalizedMessages.getString("MainWindow.TitleAttributeExist"),JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
								}
								else
								{
									modifiedFile=true;
								}
							}
							else
							{
								break;
							}
						}
						refreshData(dmtn);
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
			modifyAttribute.setText("Modify"); //$NON-NLS-1$
			modifyAttribute.setIcon(new ImageIcon(getClass().getResource("/icons/iconModifyAttribute.png"))); //$NON-NLS-1$
			modifyAttribute.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			modifyAttribute.setLocation(253, 447);
			modifyAttribute.setSize(130, 44);
			modifyAttribute.setName("ModifyAttribute");
			modifyAttribute.setToolTipText("Modify selected attribute");
			modifyAttribute.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					boolean ok=false;
					NodeData dati = new NodeData();
					
					if (tree.getSelectionPath()!=null)
					{
						DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
						NodeElement nodo = (NodeElement)dmtn.getUserObject();
						if (attributes.getSelectedValue()!=null)
						{
							String[] valori=((String)attributes.getSelectedValue()).split("="); //$NON-NLS-1$
							
							while (!ok)
							{
								ok=dati.visualizza(NodeData.MODE_ATTRIBUTE ,valori[0],valori[1]);
								if (ok)
								{
									ok=tree.modifyAttribute(dmtn,dati.getParametroNome(),dati.getParametroTesto(),dati.getOldNameNode());
									if (!ok)
									{
										JOptionPane.showMessageDialog(null,LocalizedMessages.getString("MainWindow.MessageAttributeExist"),LocalizedMessages.getString("MainWindow.TitleAttributeExist"),JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
									}
									else
									{
										modifiedFile=true;
									}
								}
								else
								{
									break;
								}
							}
							refreshData(dmtn);
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
			deleteAttribute.setText("Delete"); //$NON-NLS-1$
			deleteAttribute.setIcon(new ImageIcon(getClass().getResource("/icons/iconDeleteAttribute.png"))); //$NON-NLS-1$
			deleteAttribute.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12)); //$NON-NLS-1$
			deleteAttribute.setLocation(485, 447);
			deleteAttribute.setSize(130, 44);
			deleteAttribute.setName("DeleteAttribute");
			deleteAttribute.setToolTipText("Delete selected attribute");
			deleteAttribute.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (tree.getSelectionPath()!=null)
					{
						if (attributes.getSelectedValue()!=null)
						{
							if (JOptionPane.showConfirmDialog(null,LocalizedMessages.getString("MainWindow.MessageConfirmationDeleteAttribute"),LocalizedMessages.getString("MainWindow.TitleConfirmationDeleteAttribute"),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION) //$NON-NLS-1$ //$NON-NLS-2$
							{
							
								String nomeAttributo=((String)attributes.getSelectedValue());
								nomeAttributo=nomeAttributo.substring(0,nomeAttributo.indexOf('='));
								DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
								tree.deleteAttribute(dmtn,nomeAttributo);
								modifiedFile=true;
								refreshData(dmtn);
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
	private JPanel getPanelNode() {
		if (panelNode == null) {
			panelNode = new JPanel();
			panelNode.setLayout(null);
			panelNode.setBounds(15, 27, 602, 104);
			panelNode.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Node", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 36), java.awt.Color.black)); //$NON-NLS-1$ //$NON-NLS-2$
			panelNode.setName("PanelNode");
			panelNode.setBackground(new java.awt.Color(166,202,240));
			panelNode.add(getNodeName(), null);
			panelNode.add(getNodeText(), null);
		}
		return panelNode;
	}
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */    
	private JPanel getPanelAttribute() {
		if (panelAttribute == null) {
			panelAttribute = new JPanel();
			panelAttribute.setLayout(null);
			panelAttribute.setBounds(14, 205, 602, 227);
			panelAttribute.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Attribute", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 36), java.awt.Color.black)); //$NON-NLS-1$ //$NON-NLS-2$
			panelAttribute.setName("PanelAttribute");
			panelAttribute.setBackground(new java.awt.Color(166,202,240));
			panelAttribute.add(getAttributes(), null);
		}
		return panelAttribute;
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
			nodeName.setLabel("Node Name"); //$NON-NLS-1$
			nodeName.setEditable(false);
			nodeName.setName("NodeName");
			nodeName.setText("");
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
			nodeText.setLabel("Text Node"); //$NON-NLS-1$
			nodeText.setEditable(false);
			nodeText.setName("NodeText");
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
			about.setText("About..."); //$NON-NLS-1$
			about.setIcon(new ImageIcon(getClass().getResource("/icons/iconAbout.png"))); //$NON-NLS-1$
			about.setPreferredSize(new java.awt.Dimension(125,44));
			about.setName("About");
			about.setToolTipText("Open windows about");
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
			configure.setText("Configure..."); //$NON-NLS-1$
			configure.setToolTipText("Open window configuration"); //$NON-NLS-1$
			configure.setIcon(new ImageIcon(getClass().getResource("/icons/iconConfig.png"))); //$NON-NLS-1$
			configure.setHorizontalTextPosition(javax.swing.SwingConstants.TRAILING);
			configure.setPreferredSize(new java.awt.Dimension(125,44));
			configure.setName("Configure");
			configure.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new Configuration().setVisible(true);
					LocalizedMessages.refreshLanguage("MainWindow",getContentPane());
					repaint();
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
	private JToolBar getCommands() {
		if (commands == null) {
			commands = new JToolBar();
			commands.setFloatable(false);
			commands.setBackground(new java.awt.Color(166,202,240));
			commands.add(getNewFile());
			commands.add(getOpenFile());
			commands.add(getSaveFile());
			commands.add(getSaveFileAs());
			commands.add(getConfigure());
			commands.add(getAbout());
			commands.add(getExit());
		}
		return commands;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getNewFile() {
		if (newFile == null) {
			newFile = new JButton();
			newFile.setName("NewFile");
			newFile.setText("New");
			newFile.setIcon(new ImageIcon(getClass().getResource("/icons/iconNewFile.png")));
			newFile.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12));
			newFile.setActionCommand("New");
			newFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					boolean ok=false;
					
					if (modifiedFile)
					{
						if (JOptionPane.showConfirmDialog(null,LocalizedMessages.getString("MainWindow.MessageFileNotSaved"),LocalizedMessages.getString("MainWindow.TitleFileNotSaved"),JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
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
						tree.newXML();
					}
				}
			});
		}
		return newFile;
	}
 	public static void main(String[] args)
	{
		new MainWindow().setVisible(true);
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/iconProgram.png")));
		this.setSize(800,600);
		this.setContentPane(getJContentPane());
		this.setTitle(titleProgram); //$NON-NLS-1$
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
			jContentPane.add(getPanelCommands(), java.awt.BorderLayout.NORTH);
			jContentPane.add(getSplitTree(), java.awt.BorderLayout.CENTER);
		}
		return jContentPane;
	}
}
