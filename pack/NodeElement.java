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

import java.util.*;

/**
 * @author Ferreri Gabriele
 *
 * Node XML into JTree
 * 
 */
public class NodeElement {
	public static int TYPENODE_FILE=0;
	public static int TYPENODE_NODEWITHATTRIBUTES=1;
	public static int TYPENODE_WITHOUTATTRIBUTES=2;

	private int typeNode;
	private String nodeName;
	private String nodeText;
	private HashMap nodeAttributes;
	
	/**
	 * Constructor
	 * @param name Name XML node
	 * @param text Text XML node
	 * @param attributes Attributes XML node
	 * @param type Type XML node 
	 */
	public NodeElement(String name,String text,HashMap attributes,int type)
	{
		nodeName=name;
		nodeText=text;
		nodeAttributes=attributes;
		typeNode=type;
	}
	
	/**
	 * Return node type
	 * @return node type
	 */
	public int getType()
	{
		return typeNode;
	}
	
	/**
	 * Set node type
	 * @param val node type
	 */
	public void setType(int val)
	{
		typeNode=val;
	}
	
	/**
	 * Return node name
	 * @return node name
	 */
	public String getName()
	{
		return nodeName;
	}
	
	/**
	 * Set node name
	 * @param val node name
	 */
	public void setName(String val)
	{
		nodeName=val;
	}
	
	/**
	 * Return node text 
	 * @return node text
	 */
	public String getText()
	{
		return nodeText;
	}
	
	/**
	 * Set node text
	 * @param val node text
	 */
	public void setText(String val)
	{
		nodeText=val;
	}
	
	/**
	 * Return node attributes 
	 * @return node attributes
	 */
	public HashMap getAttributes()
	{
		return nodeAttributes;
	}
	
	/**
	 * set node attributes
	 * @param val node attributes
	 */
	public void setAttributes(HashMap val)
	{
		nodeAttributes=val;
	}

	/**
	 * Override toString method to personalize show into JTree
	 * @return String to return
	 */
	public String toString()
	{
		return nodeName;
	}
}
