package pacchetto;

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
/**
 * Main graphical interface
 * @author Ferreri Gabriele 
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private javax.swing.JPanel jContentPane = null;

	private JPanel pannelloComandi = null;
	private JButton openFile = null;
	private JButton salvaFile = null;
	private JButton salvaFileConNome = null;
	private JButton uscita = null;
	private JSplitPane splitAlbero = null;
	private JScrollPane scrollAlbero = null;
	private JTreeXML albero = null;
	private JButton aggiungiNodo = null;  //  @jve:decl-index=0:visual-constraint="461,442"
	private JButton modificaNodo = null;
	private JButton eliminaNodo = null;
	private JList attributi = null;  //  @jve:decl-index=0:visual-constraint="271,213"
	private JPanel pannelloVisualizza = null;
	private JButton aggiungiAttributo = null;
	private JButton modificaAttributo = null;
	private JButton eliminaAttributo = null;
	private JPanel pannelloNodo = null;
	private JPanel pannelloAttributi = null;

	private TextBox nomeNodo = null;
	private TextBox testoNodo = null;
	private JButton about = null;
	/**
	 * @throws java.awt.HeadlessException
	 */
	public MainWindow() {
		super();
		initialize();
		this.setBounds(Utility.centraSuSchermo(this.getBounds()));
	}

	/**
	 * Metodo che svuota tutti i dati del nodo e degli attributi
	 * dalla visualizzazione della maschera
	 */
	public void azzeraDati()
	{
		nomeNodo.setText("");
		testoNodo.setText("");
		attributi.removeAll();
	}
	
	/**
	 * Metodo che aggiorna i dati del nodo e degli attributi
	 * nella visualizzazione delle maschera
	 * @param dmtn Nodo selezionato di cui visualizzare i dati 
	 */
	public void aggiornaDati(DefaultMutableTreeNode dmtn)
	{
		Nodo nodo=(Nodo)dmtn.getUserObject();
		nomeNodo.setText(nodo.getNome());
		testoNodo.setText(nodo.getTesto());
		
		if (nodo.getAttributi()!=null)
		{
			attributi.setListData(nodo.getAttributi().values().toArray());
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
			pannelloComandi.add(getOpenFile(), null);
			pannelloComandi.add(getSalvaFile(), null);
			pannelloComandi.add(getSalvaFileConNome(), null);
			pannelloComandi.add(getUscita(), null);
			pannelloComandi.add(getAbout(), null);
		}
		return pannelloComandi;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getOpenFile() {
		if (openFile == null) {
			openFile = new JButton();
			openFile.setText("Open...");
			openFile.setIcon(new ImageIcon(getClass().getResource("/pacchetto/iconOpenFile.gif")));
			openFile.setToolTipText("Open a File");
			openFile.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12));
			openFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					JFileChooser dlgApri = new JFileChooser();
					
					dlgApri.setFileFilter(new XMLFilter());
					dlgApri.setCurrentDirectory(new File(System.getProperty("user.dir")));
					
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
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getSalvaFile() {
		if (salvaFile == null) {
			salvaFile = new JButton();
			salvaFile.setIcon(new ImageIcon(getClass().getResource("/pacchetto/iconSaveFile.gif")));
			salvaFile.setText("Salva");
			salvaFile.setToolTipText("Salva il file corrente");
			salvaFile.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12));
			salvaFile.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					albero.salvaXML(albero.getNomeFile());
				}
			});
		}
		return salvaFile;
	}
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getSalvaFileConNome() {
		if (salvaFileConNome == null) {
			salvaFileConNome = new JButton();
			salvaFileConNome.setIcon(new ImageIcon(getClass().getResource("/pacchetto/iconSaveFileAs.gif")));
			salvaFileConNome.setText("Salva...");
			salvaFileConNome.setToolTipText("Salva il file corrente con nome");
			salvaFileConNome.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12));
			salvaFileConNome.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					JFileChooser dlgSalva = new JFileChooser();
					boolean ok=false;
					
					dlgSalva.setFileFilter(new XMLFilter());
					dlgSalva.setCurrentDirectory(new File(System.getProperty("user.dir")));
					
					int res=dlgSalva.showOpenDialog(null);
					
					if (res==JFileChooser.APPROVE_OPTION)
					{
						if (dlgSalva.getSelectedFile().exists())
						{
							if (JOptionPane.showConfirmDialog(null,"File già esistente!!! Sovrascrivere?","Sovrascrivi",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
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
							albero.salvaXML(dlgSalva.getSelectedFile().toString());
						}
					}
				}
			});
		}
		return salvaFileConNome;
	}
	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getUscita() {
		if (uscita == null) {
			uscita = new JButton();
			uscita.setIcon(new ImageIcon(getClass().getResource("/pacchetto/iconExit.gif")));
			uscita.setText("Uscita");
			uscita.setToolTipText("Esce dall'applicazione");
			uscita.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12));
			uscita.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (JOptionPane.showConfirmDialog(null,"Confermi uscita applicativo?","Conferma",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
					{
						System.exit(0);	
					}
				}
			});
		}
		return uscita;
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
			albero.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 12));
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
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getAggiungiNodo() {
		if (aggiungiNodo == null) {
			aggiungiNodo = new JButton();
			aggiungiNodo.setText("Aggiungi");
			aggiungiNodo.setIcon(new ImageIcon(getClass().getResource("/pacchetto/iconAddNode.gif")));
			aggiungiNodo.setToolTipText("Aggiunge un nodo figlio al nodo corrente");
			aggiungiNodo.setName("aggiungiNodo");
			aggiungiNodo.setBounds(139, 140, 119, 42);
			aggiungiNodo.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12));
			aggiungiNodo.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					boolean ok=false;
					NodeData dati = new NodeData();
					
					if (albero.getSelectionPath()!=null)
					{
						DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)albero.getLastSelectedPathComponent();
						while (!ok)
						{
							ok=dati.visualizza(NodeData.MODO_ATTRIBUTO,"","");
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
		return aggiungiNodo;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getModificaNodo() {
		if (modificaNodo == null) {
			modificaNodo = new JButton();
			modificaNodo.setIcon(new ImageIcon(getClass().getResource("/pacchetto/iconModifyNode.gif")));
			modificaNodo.setText("Modifica");
			modificaNodo.setToolTipText("Modifica il nodo corrente");
			modificaNodo.setBounds(266, 140, 119, 42);
			modificaNodo.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12));
			modificaNodo.addActionListener(new java.awt.event.ActionListener() { 
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
		return modificaNodo;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getEliminaNodo() {
		if (eliminaNodo == null) {
			eliminaNodo = new JButton();
			eliminaNodo.setText("Elimina");
			eliminaNodo.setIcon(new ImageIcon(getClass().getResource("/pacchetto/iconDeleteNode.gif")));
			eliminaNodo.setToolTipText("Elimina il nodo corrente");
			eliminaNodo.setBounds(393, 140, 119, 42);
			eliminaNodo.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12));
			eliminaNodo.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (albero.getSelectionPath()!=null)
					{
						if (JOptionPane.showConfirmDialog(null,"Confermi eliminazione nodo?","Elimina",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
						{
							albero.eliminaNodo((DefaultMutableTreeNode)albero.getLastSelectedPathComponent());
						}
					}
				}
			});
		}
		return eliminaNodo;
	}
	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */    
	private JList getAttributi() {
		if (attributi == null) {
			attributi = new JList();
			attributi.setPreferredSize(new java.awt.Dimension(300,300));
			attributi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
			attributi.setSize(585, 182);
			attributi.setLocation(10, 37);
			attributi.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14));
		}
		return attributi;
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
			pannelloVisualizza.add(getAggiungiNodo(), null);
			pannelloVisualizza.add(getEliminaNodo(), null);
			pannelloVisualizza.add(getModificaNodo(), null);
			pannelloVisualizza.add(getAggiungiAttributo(), null);
			pannelloVisualizza.add(getModificaAttributo(), null);
			pannelloVisualizza.add(getEliminaAttributo(), null);
			pannelloVisualizza.add(getPannelloNodo(), null);
			pannelloVisualizza.add(getPannelloAttributi(), null);
		}
		return pannelloVisualizza;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getAggiungiAttributo() {
		if (aggiungiAttributo == null) {
			aggiungiAttributo = new JButton();
			aggiungiAttributo.setBounds(139, 447, 119, 42);
			aggiungiAttributo.setText("Aggiungi");
			aggiungiAttributo.setIcon(new ImageIcon(getClass().getResource("/pacchetto/iconAddAttribute.gif")));
			aggiungiAttributo.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12));
			aggiungiAttributo.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					boolean ok=false;
					NodeData dati = new NodeData();
					
					if (albero.getSelectionPath()!=null)
					{
						DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)albero.getLastSelectedPathComponent();
						while (!ok)
						{
							ok=dati.visualizza(NodeData.MODO_ATTRIBUTO,"","");
							if (ok)
							{
								ok=albero.aggiungiAttributo(dmtn,dati.getParametroNome(),dati.getParametroTesto());
								if (!ok)
								{
									JOptionPane.showMessageDialog(null,"Attributo già esistente!!!","Errore",JOptionPane.ERROR_MESSAGE);
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
		return aggiungiAttributo;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getModificaAttributo() {
		if (modificaAttributo == null) {
			modificaAttributo = new JButton();
			modificaAttributo.setBounds(266, 447, 119, 42);
			modificaAttributo.setText("Modifica");
			modificaAttributo.setIcon(new ImageIcon(getClass().getResource("/pacchetto/iconModifyAttribute.gif")));
			modificaAttributo.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12));
			modificaAttributo.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					boolean ok=false;
					NodeData dati = new NodeData();
					
					if (albero.getSelectionPath()!=null)
					{
						DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)albero.getLastSelectedPathComponent();
						Nodo nodo = (Nodo)dmtn.getUserObject();
						if (attributi.getSelectedValue()!=null)
						{
							String[] valori=((String)attributi.getSelectedValue()).split("=");
							
							while (!ok)
							{
								ok=dati.visualizza(NodeData.MODO_ATTRIBUTO ,valori[0],valori[1]);
								if (ok)
								{
									ok=albero.modificaAttributo(dmtn,dati.getParametroNome(),dati.getParametroTesto(),dati.getValoreNomeVecchio());
									if (!ok)
									{
										JOptionPane.showMessageDialog(null,"Attributo già esistente!!!","Errore",JOptionPane.ERROR_MESSAGE);
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
		return modificaAttributo;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getEliminaAttributo() {
		if (eliminaAttributo == null) {
			eliminaAttributo = new JButton();
			eliminaAttributo.setBounds(393, 447, 119, 42);
			eliminaAttributo.setText("Elimina");
			eliminaAttributo.setIcon(new ImageIcon(getClass().getResource("/pacchetto/iconDeleteAttribute.gif")));
			eliminaAttributo.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12));
			eliminaAttributo.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (albero.getSelectionPath()!=null)
					{
						if (attributi.getSelectedValue()!=null)
						{
							if (JOptionPane.showConfirmDialog(null,"Confermi eliminazione attributo?","Elimina",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
							{
							
								String nomeAttributo=((String)attributi.getSelectedValue());
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
		return eliminaAttributo;
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
			pannelloNodo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Nodo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 24), java.awt.Color.black));
			pannelloNodo.add(getNomeNodo(), null);
			pannelloNodo.add(getTestoNodo(), null);
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
			pannelloAttributi.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Attributi", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 24), java.awt.Color.black));
			pannelloAttributi.add(getAttributi(), null);
		}
		return pannelloAttributi;
	}
	/**
	 * This method initializes casellaTesto	
	 * 	
	 * @return pacchetto.CasellaTesto	
	 */    
	private TextBox getNomeNodo() {
		if (nomeNodo == null) {
			nomeNodo = new TextBox();
			nomeNodo.setBounds(10, 42, 582, 22);
			nomeNodo.setLarghezzaEtichetta(100);
			nomeNodo.setEtichetta("Nome Nodo");
			nomeNodo.setEditable(false);
		}
		return nomeNodo;
	}
	/**
	 * This method initializes casellaTesto1	
	 * 	
	 * @return pacchetto.CasellaTesto	
	 */    
	private TextBox getTestoNodo() {
		if (testoNodo == null) {
			testoNodo = new TextBox();
			testoNodo.setBounds(10, 73, 582, 22);
			testoNodo.setLarghezzaEtichetta(100);
			testoNodo.setEtichetta("Testo Nodo");
			testoNodo.setEditable(false);
		}
		return testoNodo;
	}
	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */    
	private JButton getAbout() {
		if (about == null) {
			about = new JButton();
			about.setText("About...");
			about.setIcon(new ImageIcon(getClass().getResource("/pacchetto/iconAbout.gif")));
		}
		return about;
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
		this.setSize(800,600);
		this.setContentPane(getJContentPane());
		this.setTitle("Editor XML 0.1");
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
