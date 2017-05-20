/**
 * 
 */
package org.processmining.models.connections.flexiblemodel;

import org.processmining.framework.connections.impl.AbstractConnection;
import org.processmining.models.flexiblemodel.EndTaskNodesSet;
import org.processmining.models.flexiblemodel.Flex;

/**
 * @author Arya Adriansyah
 * @email a.adriansyah@tue.nl
 * @version Mar 4, 2011
 */
public class FlexEndTaskNodeConnection extends AbstractConnection {
	public final static String FLEX = "CausalNet";
	public final static String ENDTASKNODES = "EndTaskNodesSet";

	public FlexEndTaskNodeConnection(String label, Flex flex, EndTaskNodesSet endTaskNodesSet) {
		super(label);
		put(FLEX, flex);
		put(ENDTASKNODES, endTaskNodesSet);
	}
}
