/**
 * Created on 22-nov-2002
 */
package pacchetto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class is the base for an object-model for XML. It buids up in memory the
 * child nodes instantiating, for each child, an object of type chosen from the
 * name of XML tag (String for text nodes)
 * 
 * @author Ignazio Palmisano
 */

public class StandardNode {
	private final static String startComment = "<!-- ";

	private final static String endComment = " -->";

	/** array of arguments for constructor search */
	protected String tagName = "StandardNode";

	/**
	 * sons list
	 */
	private List childNodes;

	/**
	 * attribute map
	 */
	private HashMap attributes;

	/**
	 * costruttore vuoto
	 */
	public StandardNode() {
		this.attributes = new HashMap();
		this.childNodes = new ArrayList();
	}

	public StandardNode(String s) {
		this();
		this.tagName = s;
	}

	/**
	 * costruttore invocato dalle sottoclassi, utilizza come parametro
	 * l'elemento XML che contiene i dati e il package da cui invocare le
	 * classi. L'oggetto risultante contiene una lista che rappresenta i figli
	 * di questo nodo, nello stesso ordine in cui si trovano nel file
	 * 
	 * @param myEl
	 *            the element
	 * @param textNodesRetained
	 *            tells wheter the text nodes must be reatined as Strings or
	 *            dropped
	 * @throws XMLDeserializationException
	 */
	public StandardNode(Element myEl) {
		this();
		this.setTagName(myEl.getTagName());
		NodeList children = myEl.getChildNodes();
		NamedNodeMap attrNodes = myEl.getAttributes();
		this.childNodes = new ArrayList(children.getLength());
		this.attributes = new HashMap();
		if (attrNodes != null) {
			for (int i = 0; i < attrNodes.getLength(); i++) {
				this.attributes.put(attrNodes.item(i).getNodeName(), attrNodes
						.item(i).getNodeValue());
			}
		}
		for (int i = 0; i < children.getLength(); i++) {
			// se ? un textnode, leggo il valore
			if ((children.item(i).getNodeType() == Node.TEXT_NODE)
					|| (children.item(i).getNodeType() == Node.COMMENT_NODE)) {
				if (children.item(i).getNodeType() == Node.COMMENT_NODE) {
					this.childNodes.add(startComment
							+ children.item(i).getNodeValue() + endComment);
				} else {
					// rimozione spazi, tab e ritorni carrello iniziali e finali
					String temp = children.item(i).getNodeValue().trim();
					if (temp.length() > 0) {
						this.childNodes.add(temp);
					}
				}
			} else {
				// altrimenti creo un oggetto del tipo giusto
				// invoco il costruttore XML per il nodo (nome di questo package
				// + nome del nodo)
				// nessun controllo che la struttura sia giusta
				Element el = null;
				el = (Element) children.item(i);
				//String className = el.getNodeName();
				String className = el.getNodeName();
				StandardNode newObject = new StandardNode(el);
				newObject.setTagName(className);
				this.childNodes.add(newObject);
			}
		}
	}

	/**
	 * @return
	 */
	public HashMap getAttributes() {
		return this.attributes;
	}

	/**
	 * @param a
	 */
	public void setAttributes(HashMap a) {
		this.attributes = a;
	}

	/**
	 * Returns the childNodes.
	 * 
	 * @return ArrayList
	 */
	public List getChildNodes() {
		return this.childNodes;
	}

	/**
	 * Sets the childNodes.
	 * 
	 * @param childNodesList
	 *            The childNodes to set
	 */
	public void setChildNodes(List childNodesList) {
		this.childNodes = childNodesList;
	}

	/**
	 * @param doc
	 * @see XMLSerializable#toXMLElement(org.w3c.dom.Document)
	 * @return
	 */
	public Element toXMLElement(Document doc) {
		Element el = doc.createElement(this.tagName);
		// attributes output
		Iterator it = this.attributes.keySet().iterator();
		String string;
		while (it.hasNext()) {
			string = (String) it.next();
			el.setAttribute(string, this.attributes.get(string).toString());
		}
		//childnodes output
		it = this.childNodes.iterator();
		StandardNode serializable;
		Object o;
		while (it.hasNext()) {
			o = it.next();
			if (o instanceof StandardNode) {
				serializable = (StandardNode) o;
				el.appendChild(serializable.toXMLElement(doc));
			} else {
				// then it is probably a String, maybe a comment...
				if (o instanceof String) {
					if (((String) o).startsWith(startComment)
							&& ((String) o).endsWith(endComment)) {
						el.appendChild(doc.createComment(o.toString()
								.substring(
										startComment.length(),
										o.toString().length()
												- endComment.length())));
					} else {
						el.appendChild(doc.createTextNode(o.toString()));
					}
				}
			}
		}
		return el;
	}

	/**
	 * returns the first son of the given type, or null if no son of the givcen
	 * type exists
	 * 
	 * @param type
	 *            the type of the son
	 */
	public StandardNode getSon(String type) {
		boolean found = false;
		StandardNode toReturn = null;
		Iterator it = this.getChildNodes().iterator();
		StandardNode o;
		while (it.hasNext() && !found) {
			o = (StandardNode) it.next();
			if (o.getTagName().equals(type)) {
				toReturn = o;
				found = true;
			}
		}
		return toReturn;
	}

	/**
	 * returns a List with the sons of a given type; if no son is found, returns
	 * an empty List
	 * 
	 * @param type
	 *            the type of the son
	 */
	public List listSons(String type) {
		List l = new LinkedList();
		Iterator it = this.getChildNodes().iterator();
		StandardNode o;
		while (it.hasNext()) {
			o = (StandardNode) it.next();
			if (o.getTagName().equals(type)) {
				l.add(o);
			}
		}
		return l;
	}

	public Document getDocument(String docURI, String name, String systemId) {
		Document doc = null;
		try {
			//TODO controllare che il DTD sia inizializzato bene
			DocumentType type = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().getDOMImplementation()
					.createDocumentType(name, systemId, systemId);
			//TODO controllare che il documento sia inizializzato bene
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.getDOMImplementation().createDocument(docURI, name, type);
			Element el = this.toXMLElement(doc);
			doc.appendChild(el);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
		return doc;
	}

	public Document getDocument() {
		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.newDocument();
			Element el = this.toXMLElement(doc);
			doc.appendChild(el);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 * @return Returns the tagName.
	 */
	public String getTagName() {
		return this.tagName;
	}

	/**
	 * @param aTagName
	 *            The tagName to set.
	 */
	public void setTagName(String aTagName) {
		this.tagName = aTagName;
	}

	/** this method is a shorthand for getText() */
	public String getText() {
		return (String) this.getChildNodes().get(0);
	}
}