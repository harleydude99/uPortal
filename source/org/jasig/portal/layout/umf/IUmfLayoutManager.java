/* Copyright 2004 The JA-SIG Collaborative.  All rights reserved.
*  See license distributed with this file and
*  available online at http://www.uportal.org/license.html
*/

package org.jasig.portal.layout.umf;

import org.jasig.portal.layout.node.INodeId;
import org.jasig.portal.layout.node.ILayoutNode;

/**
 * User Modifiable Fragment Layout Manager interface.
 * @author Michael Ivanov <a href="mailto:">mvi@immagic.com</a>
 * @version $Revision$
 */
public interface IUmfLayoutManager extends ILayoutManager {
	
	public ILayoutNode addNode ( ILayoutNodeId nodeId, IFragmentNodeId parentId, INodeId nextId );
	public ILayoutNode addNode ( IFragmentNodeId nodeId, ILayoutNodeId parentId, INodeId nextId );
	public ILayoutNode addNode ( IFragmentNodeId nodeId, IFragmentNodeId parentId, INodeId nextId );
	
	public boolean deleteNode ( IFragmentNodeId nodeId );
	
}
