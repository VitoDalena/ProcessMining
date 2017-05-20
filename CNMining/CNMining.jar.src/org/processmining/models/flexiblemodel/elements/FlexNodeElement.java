package org.processmining.models.flexiblemodel.elements;

import org.processmining.models.flexiblemodel.FlexEdge;
import org.processmining.models.flexiblemodel.FlexNode;
import org.processmining.models.graphbased.directed.AbstractDirectedGraph;

/**
 * @author arya
 * @email arya.adriansyah@gmail.com
 * @version Nov 19, 2009
 */
public class FlexNodeElement extends FlexNode {
	public FlexNodeElement(String label,
			AbstractDirectedGraph<FlexNode, FlexEdge<? extends FlexNode, ? extends FlexNode>> net) {
		super(net, label);
	}

	public String toString() {
		return getLabel();
	}

}