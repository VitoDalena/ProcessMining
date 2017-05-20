package org.processmining.models.flexiblemodel.elements;

import org.processmining.models.flexiblemodel.FlexEdge;
import org.processmining.models.flexiblemodel.FlexNode;

/**
 * @author arya
 * @email arya.adriansyah@gmail.com
 * @version Nov 19, 2009
 */
public class FlexEdgeElement extends FlexEdge<FlexNode, FlexNode> {
	public FlexEdgeElement(FlexNode source, FlexNode target) {
		super(source, target);
	}

	public String toString() {
		return getSource().getLabel() + " -> " + getTarget().getLabel();
	}
}