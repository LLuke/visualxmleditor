package pack;

import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;
import java.util.*;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.*; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

/**
 * Extends JTree to work with an XML file
 * @author Ferreri Gabriele
 */
public class JTreeXML extends JTree {

	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode radiceJTree = new DefaultMutableTreeNode(new Nodo("","",null,Nodo.TIPONODO_FILE)); //$NON-NLS-1$ //$NON-NLS-2$
	private Element radiceXML;
	
	/**
	 * Costruttore di classe
	 */
	public JTreeXML() {
		super();
		ImageIcon iconaFile = new ImageIcon(getClass().getResource("/icons/iconFile.gif")); //$NON-NLS-1$
		ImageIcon iconaNodoConAttributi = new ImageIcon(getClass().getResource("/icons/iconNodeWithAttribute.gif")); //$NON-NLS-1$
		ImageIcon iconaNodoSenzaAttributi = new ImageIcon(getClass().getResource("/icons/iconNodeWithoutAttribute.gif")); //$NON-NLS-1$
		
		IconRenderer render = new IconRenderer(iconaFile,iconaNodoConAttributi,iconaNodoSenzaAttributi);
		
		this.setCellRenderer(render);
		
		this.setModel(new DefaultTreeModel(radiceJTree));
	}
	
	/**
	 * Legge un file XML e lo carica nel JTree
	 * @param nomeFile Nome del file XML da leggere
	 */
	public void caricaXML(String nomeFile)
	{
		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (new File(nomeFile));
			
			radiceXML=doc.getDocumentElement();

			radiceJTree.removeAllChildren();
			this.setModel(new DefaultTreeModel(radiceJTree));
			
			radiceJTree.setUserObject(new Nodo(nomeFile,"",null,Nodo.TIPONODO_FILE)); //$NON-NLS-1$
			
			scandisciXML2JTree(radiceXML,radiceJTree);
			
			this.expandPath(new TreePath(radiceJTree));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * Salva il contenuto della JTree in un file XML
	 * @param nomeFile Nome del file XML su cui scrivere
	 */
	public void salvaXML(String nomeFile)
	{
		File f = new File(nomeFile);

		org.jdom.Element radice = new org.jdom.Element("Radice"); //$NON-NLS-1$
		
		scandisciJTree2XML(radice,(DefaultMutableTreeNode)radiceJTree.getFirstChild(),true);
		
		org.jdom.Document doc = new org.jdom.Document(radice);
		
		BufferedWriter out = null;
		
		try
		{
			out=new BufferedWriter(new FileWriter(nomeFile));
			
			XMLOutputter XMLout=new XMLOutputter();
			XMLout.setIndent(true);
			XMLout.setNewlines(true);
			
			XMLout.output(doc,out);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				out.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo che ritorna il nome del file XML aperto ("" se nessun file è aperto)
	 * @return Nome del file aperto
	 */
	public String getNomeFile()
	{
		return radiceJTree.getUserObject().toString();
	}
	
	/**
	 * Metodo che aggiunge un nodo figlio al nodo specificato
	 * @param nodo Nodo a cui aggiungere il nodo figlio
	 * @param nome Nome del nodo
	 * @param testo Testo del nodo
	 */
	public void aggiungiNodo(DefaultMutableTreeNode nodo,String nome,String testo)
	{
		nodo.add(new DefaultMutableTreeNode(new Nodo(nome,testo,new HashMap(),Nodo.TIPONODO_NODOSENZAATTRIBUTI)));
	}
	
	/**
	 * Metodo che modifica il nodo specificato
	 * @param nodo Nodo da modificare
	 * @param nome Nome del nodo
	 * @param testo Testo del nodo
	 */
	public void modificaNodo(DefaultMutableTreeNode nodo,String nome,String testo)
	{
		((Nodo)nodo.getUserObject()).setNome(nome);
		((Nodo)nodo.getUserObject()).setTesto(testo);
	}

	
	/**
	 * Metodo che elimina un nodo con tutti i nodi figli
	 * @param nodo Nodo da eliminare 
	 */
	public void eliminaNodo(DefaultMutableTreeNode nodo)
	{
		DefaultTreeModel model = (DefaultTreeModel)this.getModel();
		
		model.removeNodeFromParent(nodo);
	}
	
	/**
	 * Metodo che aggiunge un attributo al nodo specificato
	 * @param nodo Nodo a cui aggiungere l'attributo
	 * @param nome Nome dell'attributo
	 * @param testo Testo dell'attributo
	 */
	public boolean aggiungiAttributo(DefaultMutableTreeNode nodo,String nome,String testo)
	{
		boolean ok;
		
		Nodo ele=(Nodo)nodo.getUserObject();
		
		//Controlla prima che non sia già presente
		ok=(ele.getAttributi().get(nome)==null);
		
		if (ok)
		{
			ele.getAttributi().put(nome,nome + "=" + testo); //$NON-NLS-1$
		}
		
		return ok;
	}
	
	/**
	 * Metodo che modifica un attributo al nodo specificato
	 * @param nodo Nodo a cui aggiungere l'attributo
	 * @param nome Nome dell'attributo
	 * @param testo Testo dell'attributo
	 */
	public boolean modificaAttributo(DefaultMutableTreeNode nodo,String nome,String testo,String nomeVecchio)
	{
		boolean ok;
		Nodo ele=(Nodo)nodo.getUserObject();
		
		if (!nome.equals(nomeVecchio))
		{
			//Controlla prima che non sia già presente
			ok=(ele.getAttributi().get(nome)==null);
		}
		else
		{
			ok=true;
		}
			
		if (ok)
		{
			ele.getAttributi().remove(nomeVecchio);
			ele.getAttributi().put(nome,nome + "=" + testo); //$NON-NLS-1$
		}
		
		return ok;
	}

	/**
	 * Metodo che elimina un attributo di un nodo
	 * @param nodo Nodo dell'attributo da eliminare
	 * @param nomeAttributo Attributo da eliminare 
	 */
	public void eliminaAttributo(DefaultMutableTreeNode nodo,String nomeAttributo)
	{
		Nodo ele=(Nodo)nodo.getUserObject();
		
		ele.getAttributi().remove(nomeAttributo);
	}

	/**
	 * Funzione che scandisce ricorsivamente la JTree e ricostruisce il file XML
	 * @param nodoXML Nodo origine del file XML
	 * @param nodoJTree Nodo origine della JTree
	 */
	private void scandisciJTree2XML(org.jdom.Element nodoXML,DefaultMutableTreeNode nodoJTree,boolean primo)
	{
		if (nodoJTree!=null)
		{
			org.jdom.Element ele = null;

			Nodo nodo=(Nodo)nodoJTree.getUserObject();

			if (primo)
			{
				nodoXML.setName(nodo.getNome());
				nodoXML.addContent(nodo.getTesto());
				ele=nodoXML;
			}
			else
			{
				ele = new org.jdom.Element(nodo.getNome());
				ele.addContent(nodo.getTesto());
				nodoXML.addContent(ele);
			}
			
			for (int t=0;t<nodoJTree.getChildCount();t++)
			{
				scandisciJTree2XML(ele,(DefaultMutableTreeNode)nodoJTree.getChildAt(t),false);
			}
			
			Object[] attr=nodo.getAttributi().values().toArray();
			
			for (int t=0;t<attr.length;t++)
			{
				String[] temp = attr[t].toString().split("="); //$NON-NLS-1$
				ele.setAttribute(temp[0],temp[1]);
			}
		}
	}
	
	/**
	 * Funzione che scandisce ricorsivamente il file XML e lo carica nella JTree
	 * @param nodoXML Nodo origine del file XML
	 * @param nodoJTree Nodo origine della JTree	
	 */
	private void scandisciXML2JTree(Node nodoXML,DefaultMutableTreeNode nodoJTree)
	{
		HashMap elenco=new HashMap();
		DefaultMutableTreeNode figlioJTree;
		int inizio=0;
		
		if (nodoXML.getNodeType()==Node.ELEMENT_NODE)
		{
			NodeList figliXML = nodoXML.getChildNodes();

			String nome=nodoXML.getNodeName();

			String testo=""; //$NON-NLS-1$
			
			if (figliXML.item(0)!=null)
			{
				if (figliXML.item(0).getNodeValue()!=null)
				{
					testo=figliXML.item(0).getNodeValue().trim();
					inizio=1;
				}
			}
			
			for (int t=0;t<nodoXML.getAttributes().getLength();t++)
			{
				elenco.put(nodoXML.getAttributes().item(t).getNodeName(),nodoXML.getAttributes().item(t).getNodeName() + "=" + nodoXML.getAttributes().item(t).getNodeValue()); //$NON-NLS-1$
			}
			
			if (nodoXML.getAttributes().getLength()>0)
			{
				figlioJTree = new DefaultMutableTreeNode(new Nodo(nome,testo,elenco,Nodo.TIPONODO_NODOCONATTRIBUTI));				
			}
			else
			{
				figlioJTree = new DefaultMutableTreeNode(new Nodo(nome,testo,elenco,Nodo.TIPONODO_NODOSENZAATTRIBUTI));				
			}
			
			nodoJTree.add(figlioJTree);
			
			if (figliXML!=null)
			{
				for (int t=inizio;t<figliXML.getLength();t++)
				{
					scandisciXML2JTree(figliXML.item(t),figlioJTree);
				}
			}
		}
	}
}
