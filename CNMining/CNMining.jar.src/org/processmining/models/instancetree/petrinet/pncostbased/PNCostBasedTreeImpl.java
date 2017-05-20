/**
 * 
 */
package org.processmining.models.instancetree.petrinet.pncostbased;

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
 * @author aadrians
 *
 */
public class PNCostBasedTreeImpl extends AbstractIT<PNCostBasedTreeNode, PNCostBasedTreeEdge<PNCostBasedTreeNode,PNCostBasedTreeNode>> implements PNCostBasedTree {	
	public PNCostBasedTreeImpl(String label) {
		super();
		getAttributeMap().put(AttributeMap.LABEL, label);
		getAttributeMap().put(AttributeMap.PREF_ORIENTATION, SwingConstants.NORTH);
		
		nodes = new LinkedHashSet<PNCostBasedTreeNode>();
		arcs = new LinkedHashSet<PNCostBasedTreeEdge<PNCostBasedTreeNode,PNCostBasedTreeNode>>();
	}

	@Override
	public PNCostBasedTreeEdge<PNCostBasedTreeNode, PNCostBasedTreeNode> addArc(
			PNCostBasedTreeNode source, PNCostBasedTreeNode target) {
		PNCostBasedTreeEdge<PNCostBasedTreeNode, PNCostBasedTreeNode> edge = new PNCostBasedTreeEdge<PNCostBasedTreeNode, PNCostBasedTreeNode>(source, target);
		arcs.add(edge);
		graphElementAdded(edge);
		return edge;
	}

	@Override
	public PNCostBasedTreeNode addNode(String label) {
		PNCostBasedTreeNode node = new PNCostBasedTreeNode(this, label);
		nodes.add(node);
		graphElementAdded(node);
		return node;
	}

	@Override
	public PNCostBasedTreeEdge<PNCostBasedTreeNode, PNCostBasedTreeNode> removeArc(PNCostBasedTreeNode source,
			PNCostBasedTreeNode target) {
		return removeFromEdges(source, target, arcs);
	}

	@Override
	protected Map<? extends DirectedGraphElement, ? extends DirectedGraphElement> cloneFrom(
			DirectedGraph<PNCostBasedTreeNode, PNCostBasedTreeEdge<PNCostBasedTreeNode, PNCostBasedTreeNode>> graph) {
		HashMap<DirectedGraphElement, DirectedGraphElement> mapping = new HashMap<DirectedGraphElement, DirectedGraphElement>();

		for (PNCostBasedTreeNode a : graph.getNodes()) {
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
	protected AbstractDirectedGraph<PNCostBasedTreeNode, PNCostBasedTreeEdge<PNCostBasedTreeNode, PNCostBasedTreeNode>> getEmptyClone() {
		return new PNCostBasedTreeImpl(this.getGraph().getLabel());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeEdge(DirectedGraphEdge edge) {
		if (edge instanceof PNCostBasedTreeEdge<?,?>){
			arcs.remove(edge);
		} else {
			assert(false);
		}
		graphElementRemoved(edge);
	}

	@Override
	public void removeNode(DirectedGraphNode cell) {
		if (cell instanceof PNCostBasedTreeNode){
			removeSurroundingEdges((PNCostBasedTreeNode) cell);
			removeNodeFromCollection(nodes, (PNCostBasedTreeNode) cell);
		}
	}
}
