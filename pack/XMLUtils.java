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

import java.io.ByteArrayInputStream;
//import java.io.IOException;
import java.io.InputStream;
//import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import org.apache.xml.serialize.OutputFormat;
//import org.apache.xml.serialize.XMLSerializer;
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
	/*
	public final static OutputStream document2OutputStream(Document aDocument,
			OutputStream aStream) throws IOException {
		OutputFormat format = new OutputFormat();
		format.setIndenting(false);
		XMLSerializer serial = new XMLSerializer(aStream, format);
		serial.asDOMSerializer();
		serial.serialize(aDocument);
		return aStream;
	}
	*/
	
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