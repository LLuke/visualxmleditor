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
	private DefaultMutableTreeNode folderJTree = new DefaultMutableTreeNode(new NodeElement("Files","",null,NodeElement.TYPENODE_FOLDER));
	private DefaultMutableTreeNode fileXMLJTree = new DefaultMutableTreeNode(new NodeElement("","",null,NodeElement.TYPENODE_FILE));
	private Element rootXML;
	private IconRenderer render;
	
	/**
	 * Constructor
	 */
	public JTreeXML() {
		super();
		ImageIcon iconFolder = new ImageIcon(getClass().getResource("/icons/iconFolder.png"));
		ImageIcon iconFile = new ImageIcon(getClass().getResource("/icons/iconFileXML.png"));
		ImageIcon iconNodeWithAttribute = new ImageIcon(getClass().getResource("/icons/iconNodeWithAttribute.png"));
		ImageIcon iconNodeWithoutAttribute = new ImageIcon(getClass().getResource("/icons/iconNodeWithoutAttribute.png"));
		
		render = new IconRenderer(iconFolder,iconFile,iconNodeWithAttribute,iconNodeWithoutAttribute);
		
		this.setCellRenderer(render);
		
		folderJTree.add(fileXMLJTree);
		
		this.setModel(new DefaultTreeModel(folderJTree));
	}
	
	public void newXML()
	{
		if (!fileXMLJTree.isLeaf())
		{
			DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)fileXMLJTree; 
			if (dmtn!=null)
			{
				deleteNode(dmtn);
			}
		}
		setFileNameXML("noname.xml");
		setFileXMLVersion("<?xml version=\"1.0\"?>");
	}
	
	/**
	 * Load XML file into JTree
	 * @param filename Filename to read
	 */
	public void loadXML(String filename)
	{
		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (new File(filename));
			
			rootXML=doc.getDocumentElement();

			newXML();
			
			setFileNameXML(new File(filename).getName());
			setFileXMLVersion("<?xml version=\"1.0\"?>");
			
			scanXML2JTree(rootXML,fileXMLJTree);
			
			this.expandPath(new TreePath(fileXMLJTree));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * Save from JTree to XML File
	 * @param filename Filename to write
	 */
	public void saveXML(String filename)
	{
		BufferedWriter f=null;

		try
		{
			f = new BufferedWriter(new FileWriter(filename));
			
			f.write(getFileXMLVersion() + "\r\n");
			
			setFileNameXML(filename);
			
			scanJTree2XML((DefaultMutableTreeNode)fileXMLJTree.getFirstChild(),f,0);
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
	 * This method return current filename ("" if not open)
	 * @return Current filename
	 */
	public String getFileNameXML()
	{
		return ((NodeElement)fileXMLJTree.getUserObject()).getName();
	}
	
	/**
	 * This method set current filename
	 * @param val Current filename
	 */
	public void setFileNameXML(String val)
	{
		((NodeElement)fileXMLJTree.getUserObject()).setName(val);
	}
	
	/**
	 * Refresh view
	 *
	 */
	public void refresh()
	{
		this.setModel(new DefaultTreeModel(folderJTree));
	}
	
	/**
	 * This method return version current file ("" if not open)
	 * @return Version file
	 */
	public String getFileXMLVersion()
	{
		return ((NodeElement)fileXMLJTree.getUserObject()).getText();
	}
	
	/**
	 * This method set version current file
	 * @param val Version file
	 */
	public void setFileXMLVersion(String val)
	{
		((NodeElement)fileXMLJTree.getUserObject()).setText(val);
	}
	
	/**
	 * Add child node to node
	 * @param node Node to add child
	 * @param name Node name
	 * @param text Node text 
	 */
	public void addNode(DefaultMutableTreeNode node,String name,String text)
	{
		node.add(new DefaultMutableTreeNode(new NodeElement(name,text,new HashMap(),NodeElement.TYPENODE_WITHOUTATTRIBUTES)));
	}
	
	/**
	 * Modify node
	 * @param node Node to modify
	 * @param name Node name
	 * @param text Node text
	 */
	public void modifyNode(DefaultMutableTreeNode node,String name,String text)
	{
		((NodeElement)node.getUserObject()).setName(name);
		((NodeElement)node.getUserObject()).setText(text);
	}

	
	/**
	 * Delete node
	 * @param node Node to delete 
	 */
	public void deleteNode(DefaultMutableTreeNode node)
	{
		DefaultTreeModel model = (DefaultTreeModel)this.getModel();
		
		model.removeNodeFromParent(node);
	}
	
	/**
	 * Add attribute to Node
	 * @param node Node to add Attribute
	 * @param name Attribute name
	 * @param text Attribute text
	 */
	public boolean addAttribute(DefaultMutableTreeNode node,String name,String text)
	{
		boolean ok;
		
		NodeElement ele=(NodeElement)node.getUserObject();
		
		//Control if exist
		ok=(ele.getAttributes().get(name)==null);
		
		if (ok)
		{
			ele.getAttributes().put(name,name + "=" + text); //$NON-NLS-1$
		}
		
		return ok;
	}
	
	/**
	 * Modify attribute
	 * @param node Node of attribute 
	 * @param name Attribute name
	 * @param text Attribute text
	 */
	public boolean modifyAttribute(DefaultMutableTreeNode node,String name,String text,String oldName)
	{
		boolean ok;
		NodeElement ele=(NodeElement)node.getUserObject();
		
		if (!name.equals(oldName))
		{
			//Control exist
			ok=(ele.getAttributes().get(name)==null);
		}
		else
		{
			ok=true;
		}
			
		if (ok)
		{
			ele.getAttributes().remove(oldName);
			ele.getAttributes().put(name,name + "=" + text); //$NON-NLS-1$
		}
		
		return ok;
	}

	/**
	 * Delete attribute
	 * @param node Node of attribute
	 * @param name Attribute name 
	 */
	public void deleteAttribute(DefaultMutableTreeNode node,String name)
	{
		NodeElement ele=(NodeElement)node.getUserObject();
		
		ele.getAttributes().remove(name);
	}

	/**
	 * Scan JTree to save XML file
	 * @param nodeJTree Main node JTree
	 * @param f File XML to save
	 * @param ntab Using for indent XML File 
	 */
	private void scanJTree2XML(DefaultMutableTreeNode nodeJTree,BufferedWriter f,int ntab)
	{
		
		if (nodeJTree!=null)
		{
			NodeElement node=(NodeElement)nodeJTree.getUserObject();
			
			try
			{
				f.write(Utility.repeatString("\t",ntab) + "<" + node.getName()); //$NON-NLS-1$ //$NON-NLS-2$
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			Object[] attr=node.getAttributes().values().toArray();
			
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
					f.write(temp[0] + "=\"" + temp[1] + "\" "); //$NON-NLS-1$ //$NON-NLS-2$
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

			try
			{
				f.write(">" + node.getText()); //$NON-NLS-1$
				
				if (!nodeJTree.isLeaf())
				{
					 f.write("\r\n"); //$NON-NLS-1$
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			for (int t=0;t<nodeJTree.getChildCount();t++)
			{
				scanJTree2XML((DefaultMutableTreeNode)nodeJTree.getChildAt(t),f,ntab+1);
			}
			
			try
			{
				if (!nodeJTree.isLeaf())
				{
					f.write(Utility.repeatString("\t",ntab)); //$NON-NLS-1$
				}
				
				f.write("</" + node.getName() + ">\r\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
	}

	
	/**
	 * Scan XML file to recreate structure in JTree
	 * @param nodeXML Main node XML file
	 * @param nodeJTree Main node JTree	
	 */
	private void scanXML2JTree(Node nodeXML,DefaultMutableTreeNode nodeJTree)
	{
		HashMap attributes=new HashMap();
		DefaultMutableTreeNode childJTree;
		int start=0;
		
		if (nodeXML.getNodeType()==Node.ELEMENT_NODE)
		{
			NodeList childsXML = nodeXML.getChildNodes();

			String name=nodeXML.getNodeName();

			String text=""; //$NON-NLS-1$
			
			if (childsXML.item(0)!=null)
			{
				if (childsXML.item(0).getNodeValue()!=null)
				{
					text=childsXML.item(0).getNodeValue().trim();
					start=1;
				}
			}
			
			for (int t=0;t<nodeXML.getAttributes().getLength();t++)
			{
				attributes.put(nodeXML.getAttributes().item(t).getNodeName(),nodeXML.getAttributes().item(t).getNodeName() + "=" + nodeXML.getAttributes().item(t).getNodeValue()); //$NON-NLS-1$
			}
			
			if (nodeXML.getAttributes().getLength()>0)
			{
				childJTree = new DefaultMutableTreeNode(new NodeElement(name,text,attributes,NodeElement.TYPENODE_NODEWITHATTRIBUTES));				
			}
			else
			{
				childJTree = new DefaultMutableTreeNode(new NodeElement(name,text,attributes,NodeElement.TYPENODE_WITHOUTATTRIBUTES));				
			}
			
			nodeJTree.add(childJTree);
			
			if (childsXML!=null)
			{
				for (int t=start;t<childsXML.getLength();t++)
				{
					scanXML2JTree(childsXML.item(t),childJTree);
				}
			}
		}
	}
}
