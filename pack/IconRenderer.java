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
	private ImageIcon iconF;
	private ImageIcon iconNWA;
	private ImageIcon iconNNA;
	
	/**
	 * Constructor
	 * @param iconFile	Icon of File node
	 * @param iconNodeWithAttribute	Icon of Node with attributes  
	 * @param iconNodeWithoutAttribute Icon of Node without attributes
	 */
	public IconRenderer(ImageIcon iconFile,ImageIcon iconNodeWithAttribute,ImageIcon iconNodeWithoutAttribute) {
		super();
		iconF=iconFile;
		iconNWA=iconNodeWithAttribute;
		iconNNA=iconNodeWithoutAttribute;
	}
	
	/**
	 * This method overrides method getTreeCellRendererComponent
	 * to personalize icons
	 * @param tree JTree object
	 * @param value 
	 * @param sel			
	 * @param expanded
	 * @param leaf
	 * @param row
	 * @param hasFocus
	 * @return Return personalized object
	 */
	public Component getTreeCellRendererComponent(JTree tree,Object value,boolean sel,boolean expanded,boolean leaf,int row,boolean hasFocus)
	{
		super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);
		
		if (value instanceof DefaultMutableTreeNode)
		{
			if (((DefaultMutableTreeNode)value).getUserObject() instanceof NodeElement)
			{
				NodeElement ele = (NodeElement)((DefaultMutableTreeNode)value).getUserObject();
	
				if (ele.getType()==NodeElement.TYPENODE_FILE)
				{
					this.setIcon(iconF);
				}
				else if (ele.getType()==NodeElement.TYPENODE_NODEWITHATTRIBUTES)
				{
					this.setIcon(iconNWA);
				}
				else if (ele.getType()==NodeElement.TYPENODE_WITHOUTATTRIBUTES)
				{
					this.setIcon(iconNNA);
				}
			}
		}
		return this;
	}
}
