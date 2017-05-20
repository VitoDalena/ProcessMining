/**
 * 
 */
package org.processmining.models.connections.flexiblemodel;

import org.processmining.framework.connections.impl.AbstractConnection;
import org.processmining.models.flexiblemodel.Flex;
import org.processmining.models.flexiblemodel.StartTaskNodesSet;

/**
 * @author Arya Adriansyah
 * @email a.adriansyah@tue.nl
 * @version Apr 5, 2010
 * 
 */
public class FlexStartTaskNodeConnection extends AbstractConnection {
	public final static String FLEX = "CausalNet";
	public final static String STARTTASKNODES = "StartTaskNodesSet";

	public FlexStartTaskNodeConnection(String label, Flex flex, StartTaskNodesSet startTaskNodesSet) {
		super(label);
		put(FLEX, flex);
		put(STARTTASKNODES, startTaskNodesSet);
	}
}