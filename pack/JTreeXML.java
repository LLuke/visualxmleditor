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

import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;
import java.util.*;
//import org.jdom.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.*; 

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

/**
 * Extends JTree to work with an XML file
 * @author Ferreri Gabriele
 */
public class JTreeXML extends JTree implements org.w3c.dom.Document {

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
	public void salvaXML(String filename)
	{
		BufferedWriter f=null;

		try
		{
			f = new BufferedWriter(new FileWriter(filename));
			
			//"<?xml version="1.0"?>"
			
			scandisciJTree2XML((DefaultMutableTreeNode)radiceJTree.getFirstChild(),f,0);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		if (f!=null)
		{
			try
			{
				f.close();
			}
			catch (IOException e)
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
	private void scandisciJTree2XML(DefaultMutableTreeNode nodoJTree,BufferedWriter f,int ntab)
	{
		
		if (nodoJTree!=null)
		{
			Nodo nodo=(Nodo)nodoJTree.getUserObject();
			
			try
			{
				f.write(Utility.repeatString("\t",ntab) + "<" + nodo.getNome()); //$NON-NLS-1$ //$NON-NLS-2$
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			Object[] attr=nodo.getAttributi().values().toArray();
			
			if (attr.length>0)
			{
				try
				{
					f.write(" "); //$NON-NLS-1$
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			for (int t=0;t<attr.length;t++)
			{
				String[] temp = attr[t].toString().split("="); //$NON-NLS-1$
				
				try
				{
					f.write(temp[0] + "=\"" + temp[1] + "\""); //$NON-NLS-1$ //$NON-NLS-2$
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			try
			{
				f.write(">" + nodo.getTesto()); //$NON-NLS-1$
				
				if (!nodoJTree.isLeaf())
				{
					 f.write("\r\n"); //$NON-NLS-1$
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			for (int t=0;t<nodoJTree.getChildCount();t++)
			{
				scandisciJTree2XML((DefaultMutableTreeNode)nodoJTree.getChildAt(t),f,ntab+1);
			}
			
			try
			{
				if (!nodoJTree.isLeaf())
				{
					f.write(Utility.repeatString("\t",ntab)); //$NON-NLS-1$
				}
				
				f.write("</" + nodo.getNome() + ">\r\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			catch (IOException e)
			{
				e.printStackTrace();
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

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getImplementation()
	 */
	public DOMImplementation getImplementation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createDocumentFragment()
	 */
	public DocumentFragment createDocumentFragment() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getDoctype()
	 */
	public DocumentType getDoctype() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getDocumentElement()
	 */
	public Element getDocumentElement() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createAttribute(java.lang.String)
	 */
	public Attr createAttribute(String name) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createCDATASection(java.lang.String)
	 */
	public CDATASection createCDATASection(String data) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createComment(java.lang.String)
	 */
	public Comment createComment(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createElement(java.lang.String)
	 */
	public Element createElement(String tagName) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getElementById(java.lang.String)
	 */
	public Element getElementById(String elementId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createEntityReference(java.lang.String)
	 */
	public EntityReference createEntityReference(String name) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#importNode(org.w3c.dom.Node, boolean)
	 */
	public Node importNode(Node importedNode, boolean deep) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getElementsByTagName(java.lang.String)
	 */
	public NodeList getElementsByTagName(String tagname) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createTextNode(java.lang.String)
	 */
	public Text createTextNode(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createAttributeNS(java.lang.String, java.lang.String)
	 */
	public Attr createAttributeNS(String namespaceURI, String qualifiedName) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createElementNS(java.lang.String, java.lang.String)
	 */
	public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#getElementsByTagNameNS(java.lang.String, java.lang.String)
	 */
	public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Document#createProcessingInstruction(java.lang.String, java.lang.String)
	 */
	public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getNodeType()
	 */
	public short getNodeType() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#normalize()
	 */
	public void normalize() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#hasAttributes()
	 */
	public boolean hasAttributes() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#hasChildNodes()
	 */
	public boolean hasChildNodes() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getLocalName()
	 */
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getNamespaceURI()
	 */
	public String getNamespaceURI() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getNodeName()
	 */
	public String getNodeName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getNodeValue()
	 */
	public String getNodeValue() throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getPrefix()
	 */
	public String getPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#setNodeValue(java.lang.String)
	 */
	public void setNodeValue(String nodeValue) throws DOMException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#setPrefix(java.lang.String)
	 */
	public void setPrefix(String prefix) throws DOMException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getOwnerDocument()
	 */
	public Document getOwnerDocument() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getAttributes()
	 */
	public NamedNodeMap getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getFirstChild()
	 */
	public Node getFirstChild() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getLastChild()
	 */
	public Node getLastChild() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getNextSibling()
	 */
	public Node getNextSibling() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getParentNode()
	 */
	public Node getParentNode() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getPreviousSibling()
	 */
	public Node getPreviousSibling() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#cloneNode(boolean)
	 */
	public Node cloneNode(boolean deep) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getChildNodes()
	 */
	public NodeList getChildNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#isSupported(java.lang.String, java.lang.String)
	 */
	public boolean isSupported(String feature, String version) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#appendChild(org.w3c.dom.Node)
	 */
	public Node appendChild(Node newChild) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#removeChild(org.w3c.dom.Node)
	 */
	public Node removeChild(Node oldChild) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#insertBefore(org.w3c.dom.Node, org.w3c.dom.Node)
	 */
	public Node insertBefore(Node newChild, Node refChild) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#replaceChild(org.w3c.dom.Node, org.w3c.dom.Node)
	 */
	public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
		// TODO Auto-generated method stub
		return null;
	}
}
