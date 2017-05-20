/**
 * 
 */
package org.processmining.models.connections.flexiblemodel;

import org.processmining.framework.connections.impl.AbstractConnection;
import org.processmining.models.flexiblemodel.CancellationRegion;
import org.processmining.models.flexiblemodel.Flex;

/**
 * @author Arya Adriansyah
 * @email a.adriansyah@tue.nl
 * @version Mar 22, 2011
 */
public class FlexCancellationRegionConnection extends AbstractConnection {
	public final static String FLEX = "CausalNet";
	public final static String CANCELLATIONREGION = "CancellationRegion";

	public FlexCancellationRegionConnection(String label, Flex flex, CancellationRegion cancellationRegion) {
		super(label);
		put(FLEX, flex);
		put(CANCELLATIONREGION, cancellationRegion);
	}
}