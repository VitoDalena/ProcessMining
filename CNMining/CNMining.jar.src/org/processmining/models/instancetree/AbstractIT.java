/**
 * 
 */
package org.processmining.models.instancetree;

import java.util.Set;

import org.processmining.models.graphbased.directed.AbstractDirectedGraph;

/**
 * @author aadrians
 *
 */
public abstract class AbstractIT<X extends ITNode, Y extends ITEdge<? extends X, ? extends X>> 
	extends AbstractDirectedGraph<X, Y> {

	// attributes
	protected Set<X> nodes;
	protected Set<Y> arcs;

	@Override
	public Set<Y> getEdges() {
		return arcs;
	}

	@Override
	public Set<X> getNodes() {
		return nodes;
	}

}
