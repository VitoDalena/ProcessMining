/**
 * 
 */
package org.processmining.models.instancetree;

import org.processmining.models.graphbased.directed.DirectedGraph;
import org.processmining.models.graphbased.directed.DirectedGraphEdge;
import org.processmining.models.graphbased.directed.DirectedGraphNode;

/**
 * @author aadrians
 * 
 */
public interface ITGraph<N extends DirectedGraphNode, E extends DirectedGraphEdge<N, N>>
		extends DirectedGraph<N, E> {

	public String getLabel();

	// NODE
	public N addNode(String label);

	// ARCS
	public E addArc(N source, N target);

	public E removeArc(N source, N target);
}
