package pacchetto;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JButton;
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
	private String valoreNomeVecchio="";
	private String valoreTestoVecchio="";
	
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
			this.setTitle("Nodo");
			nome.setEtichetta("Nome Nodo");
			testo.setEtichetta("Testo Nodo");
		}
		else
		{
			this.setTitle("Attributo");
			nome.setEtichetta("Nome Attibuto");
			testo.setEtichetta("Testo Attributo");
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
		this.setSize(565, 164);
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		});
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
			ok.setBounds(152, 92, 89, 40);
			ok.setText("Ok");
			ok.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14));
			ok.addActionListener(new java.awt.event.ActionListener() { 
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (getNome().getText().length()>0)
					{
						scelto=true;
						setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Nome obbligatorio!!!","Errore",JOptionPane.ERROR_MESSAGE);
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
			annulla.setBounds(314, 92, 89, 38);
			annulla.setText("Annulla");
			annulla.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 14));
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
			nome.setEtichetta("");
			nome.setLarghezzaEtichetta(100);
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
			testo.setLarghezzaEtichetta(100);
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
