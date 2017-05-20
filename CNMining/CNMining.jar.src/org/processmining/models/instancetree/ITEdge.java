/**
 * 
 */
package org.processmining.models.instancetree;

import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.AttributeMap.ArrowType;
import org.processmining.models.graphbased.directed.AbstractDirectedGraphEdge;

/**
 * @author aadrians
 *
 */ 
public class ITEdge<S extends ITNode, T extends ITNode> extends AbstractDirectedGraphEdge<S, T> {
	/**
	 * Default constructor
	 * 
	 * @param source
	 * @param target
	 */
	public ITEdge(S source, T target) {
		super(source, target);
		getAttributeMap().put(AttributeMap.SHOWLABEL, false);
		getAttributeMap().put(AttributeMap.EDGEEND, ArrowType.ARROWTYPE_TECHNICAL);
	}
}