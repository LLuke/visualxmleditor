package pack;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;

/**
 * @author: Ignazio Palmisano
 */
public class XMLUtils {
	/**
	 * serializes a Document to an OutputStream; the output stream is NOT closed
	 * 
	 * @param aDocument
	 *            the document to serialize
	 * @param aStream
	 *            the OutputStream
	 */
	public final static OutputStream document2OutputStream(Document aDocument,
			OutputStream aStream) throws IOException {
		OutputFormat format = new OutputFormat();
		format.setIndenting(false);
		XMLSerializer serial = new XMLSerializer(aStream, format);
		serial.asDOMSerializer();
		serial.serialize(aDocument);
		return aStream;
	}

	/**
	 * reads a Document from an InputStream; returns null if there are errors
	 * 
	 * @param anInputStream
	 *            the input stream to read from
	 */
	public final static Document inputStream2Document(InputStream anInputStream) {
		Document aDocument = null;
		try {
			DocumentBuilder db = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			aDocument = db.parse(anInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aDocument;
	}

	public final static Document string2Document(String s) {
		Document aDocument = null;
		try {
			aDocument = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(
							new ByteArrayInputStream(s.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aDocument;
	}
}