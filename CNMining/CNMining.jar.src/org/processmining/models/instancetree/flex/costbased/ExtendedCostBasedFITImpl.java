/**
 * 
 */
package org.processmining.models.instancetree.flex.costbased;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import javax.swing.SwingConstants;

import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.AbstractDirectedGraph;
import org.processmining.models.graphbased.directed.DirectedGraph;
import org.processmining.models.graphbased.directed.DirectedGraphEdge;
import org.processmining.models.graphbased.directed.DirectedGraphElement;
import org.processmining.models.graphbased.directed.DirectedGraphNode;
import org.processmining.models.instancetree.AbstractIT;


/**
 * @author viet
 * Invisible Causal Net Instance Tree Implementation
 */
public class ExtendedCostBasedFITImpl extends AbstractIT<ExtendedCostBasedFITNode, ExtendedCostBasedFITEdge<ExtendedCostBasedFITNode,ExtendedCostBasedFITNode>> implements ExtendedCostBasedFIT {	
	public ExtendedCostBasedFITImpl(String label) {
		super();
		getAttributeMap().put(AttributeMap.LABEL, label);
		getAttributeMap().put(AttributeMap.PREF_ORIENTATION, SwingConstants.NORTH);
		
		nodes = new LinkedHashSet<ExtendedCostBasedFITNode>();
		arcs = new LinkedHashSet<ExtendedCostBasedFITEdge<ExtendedCostBasedFITNode,ExtendedCostBasedFITNode>>();
	}

	@Override
	public ExtendedCostBasedFITEdge<ExtendedCostBasedFITNode, ExtendedCostBasedFITNode> addArc(
			ExtendedCostBasedFITNode source, ExtendedCostBasedFITNode target) {
		ExtendedCostBasedFITEdge<ExtendedCostBasedFITNode, ExtendedCostBasedFITNode> edge = new ExtendedCostBasedFITEdge<ExtendedCostBasedFITNode, ExtendedCostBasedFITNode>(source, target);
		arcs.add(edge);
		graphElementAdded(edge);
		return edge;
	}

	@Override
	public ExtendedCostBasedFITNode addNode(String label) {
		ExtendedCostBasedFITNode node = new ExtendedCostBasedFITNode(this, label);
		nodes.add(node);
		graphElementAdded(node);
		return node;
	}

	@Override
	public ExtendedCostBasedFITEdge<ExtendedCostBasedFITNode, ExtendedCostBasedFITNode> removeArc(ExtendedCostBasedFITNode source,
			ExtendedCostBasedFITNode target) {
		return removeFromEdges(source, target, arcs);
	}

	@Override
	protected Map<? extends DirectedGraphElement, ? extends DirectedGraphElement> cloneFrom(
			DirectedGraph<ExtendedCostBasedFITNode, ExtendedCostBasedFITEdge<ExtendedCostBasedFITNode, ExtendedCostBasedFITNode>> graph) {
		HashMap<DirectedGraphElement, DirectedGraphElement> mapping = new HashMap<DirectedGraphElement, DirectedGraphElement>();

		for (ExtendedCostBasedFITNode a : graph.getNodes()) {
			mapping.put(a, addNode(a.getLabel()));
		}

		getAttributeMap().clear();
		AttributeMap map = graph.getAttributeMap();
		for (String key : map.keySet()) {
			getAttributeMap().put(key, map.get(key));
		}
		return mapping;
	}

	@Override
	protected AbstractDirectedGraph<ExtendedCostBasedFITNode, ExtendedCostBasedFITEdge<ExtendedCostBasedFITNode, ExtendedCostBasedFITNode>> getEmptyClone() {
		return new ExtendedCostBasedFITImpl(this.getGraph().getLabel());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeEdge(DirectedGraphEdge edge) {
		if (edge instanceof ExtendedCostBasedFITEdge<?,?>){
			arcs.remove(edge);
		} else {
			assert(false);
		}
		graphElementRemoved(edge);
	}

	@Override
	public void removeNode(DirectedGraphNode cell) {
		if (cell instanceof ExtendedCostBasedFITNode){
			removeSurroundingEdges((ExtendedCostBasedFITNode) cell);
			removeNodeFromCollection(nodes, (ExtendedCostBasedFITNode) cell);
		}
	}
}
