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
