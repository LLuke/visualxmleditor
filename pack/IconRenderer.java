package pack;

import java.awt.Component;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;


/**
 * Permits icon personalization based on type
 * @author Ferreri Gabriele
 */
public class IconRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;
	private ImageIcon iconaF;
	private ImageIcon iconaNCA;
	private ImageIcon iconaNSA;
	
	/**
	 * Costruttore della classe
	 * @param iconaFile	Icona che rappresenta il nodo File
	 * @param iconaNodoConAttributi	Icona che rappresenta il nodo Nodo con attributi
	 * @param iconaNodoSenzaAttributi Icona che rappresenta il nodo Nodo senza attributi
	 */
	public IconRenderer(ImageIcon iconaFile,ImageIcon iconaNodoConAttributi,ImageIcon iconaNodoSenzaAttributi) {
		super();
		iconaF=iconaFile;
		iconaNCA=iconaNodoConAttributi;
		iconaNSA=iconaNodoSenzaAttributi;
	}
	
	/**
	 * Funzione che effettua l'overrides del metodo getTreeCellRendererComponent
	 * per personalizzare le icone in base al tipo di nodo
	 * @param tree Oggetto JTree
	 * @param value Oggetto contenente il nodo del JTree corrente
	 * @param sel			
	 * @param expanded
	 * @param leaf
	 * @param row
	 * @param hasFocus
	 * @return Ritorna l'oggetto Render personalizzato
	 */
	public Component getTreeCellRendererComponent(JTree tree,Object value,boolean sel,boolean expanded,boolean leaf,int row,boolean hasFocus)
	{
		super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);
		
		if (value instanceof DefaultMutableTreeNode)
		{
			if (((DefaultMutableTreeNode)value).getUserObject() instanceof Nodo)
			{
				Nodo ele = (Nodo)((DefaultMutableTreeNode)value).getUserObject();
	
				if (ele.getTipo()==Nodo.TIPONODO_FILE)
				{
					this.setIcon(iconaF);
				}
				else if (ele.getTipo()==Nodo.TIPONODO_NODOCONATTRIBUTI)
				{
					this.setIcon(iconaNCA);
				}
				else if (ele.getTipo()==Nodo.TIPONODO_NODOSENZAATTRIBUTI)
				{
					this.setIcon(iconaNSA);
				}
			}
		}
		return this;
	}
}
