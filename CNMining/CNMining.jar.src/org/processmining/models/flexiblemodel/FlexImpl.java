package org.processmining.models.flexiblemodel;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.SwingConstants;

import org.processmining.models.flexiblemodel.elements.FlexEdgeElement;
import org.processmining.models.flexiblemodel.elements.FlexNodeElement;
import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.AbstractDirectedGraph;
import org.processmining.models.graphbased.directed.DirectedGraph;
import org.processmining.models.graphbased.directed.DirectedGraphEdge;
import org.processmining.models.graphbased.directed.DirectedGraphElement;
import org.processmining.models.graphbased.directed.DirectedGraphNode;

/**
 * @author arya
 * @email arya.adriansyah@gmail.com
 * @version Nov 19, 2009
 */
public class FlexImpl extends AbstractDirectedGraph<FlexNode, FlexEdge<? extends FlexNode, ? extends FlexNode>>
		implements Flex {

	protected final Set<FlexNode> nodes;
	protected final Set<FlexEdge<FlexNode, FlexNode>> arcs;

	public FlexImpl(String label) {
		super();
		nodes = new LinkedHashSet<FlexNode>();
		arcs = new LinkedHashSet<FlexEdge<FlexNode, FlexNode>>();
		getAttributeMap().put(AttributeMap.PREF_ORIENTATION, SwingConstants.WEST);
		getAttributeMap().put(AttributeMap.LABEL, label);
	}

	@Override
	protected FlexImpl getEmptyClone() {
		return new FlexImpl(getLabel());
	}

	protected Map<? extends DirectedGraphElement, ? extends DirectedGraphElement> cloneFrom(
			DirectedGraph<FlexNode, FlexEdge<? extends FlexNode, ? extends FlexNode>> graph) {
		HashMap<DirectedGraphElement, DirectedGraphElement> mapping = new HashMap<DirectedGraphElement, DirectedGraphElement>();

		for (FlexNode a : graph.getNodes()) {
			mapping.put(a, addNode(a.getLabel()));
		}

		getAttributeMap().clear();
		AttributeMap map = graph.getAttributeMap();
		for (String key : map.keySet()) {
			getAttributeMap().put(key, map.get(key));
		}
		return mapping;
	}

	/**
	 * ARC/EDGE
	 */
	public FlexEdge<FlexNode, FlexNode> addArc(FlexNode source, FlexNode target) {
		return addArc(source, target, null);
	}

	public synchronized FlexEdge<FlexNode, FlexNode> addArc(FlexNode source, FlexNode target, String label) {
		checkAddEdge(source, target);
		FlexEdgeElement a = new FlexEdgeElement(source, target);
		if (arcs.add(a)) {
			graphElementAdded(a);
			return a;
		} else {
			for (FlexEdge<FlexNode, FlexNode> existing : arcs) {
				if (existing.equals(a)) {
					if (label != null) {
						existing.getAttributeMap().put(AttributeMap.LABEL, label);
					}
					return existing;
				}
			}
		}
		assert (false);
		return null;
	}

	public synchronized FlexEdge<FlexNode, FlexNode> removeArc(FlexNode source, FlexNode target) {
		return removeFromEdges(source, target, arcs);
	}

	public FlexEdge<FlexNode, FlexNode> removeArc(FlexEdge<? extends FlexNode, ? extends FlexNode> edge) {
		return removeFromEdges(edge.getSource(), edge.getTarget(), arcs);
	}

	@SuppressWarnings("unchecked")
	public void removeEdge(DirectedGraphEdge edge) {
		if (edge instanceof FlexEdgeElement) {
			arcs.remove(edge);
		} else {
			assert (false);
		}
		graphElementRemoved(edge);
	}

	public synchronized FlexEdge<FlexNode, FlexNode> getArc(FlexNode source, FlexNode target) {
		Collection<FlexEdge<FlexNode, FlexNode>> set = getEdges(source, target, arcs);
		return (set.isEmpty() ? null : set.iterator().next());
	}

	public synchronized Set<FlexEdge<? extends FlexNode, ? extends FlexNode>> getEdges() {
		Set<FlexEdge<? extends FlexNode, ? extends FlexNode>> edges = new HashSet<FlexEdge<? extends FlexNode, ? extends FlexNode>>();
		edges.addAll(arcs);
		return edges;
	}

	/**
	 * NODE
	 */
	public synchronized FlexNode addNode(String label) {
		FlexNode node = new FlexNodeElement(label, this);
		nodes.add(node);
		graphElementAdded(node);
		return node;
	}

	public FlexNode removeNode(FlexNode flexNodeElement) {
		removeSurroundingEdges(flexNodeElement);
		return removeNodeFromCollection(nodes, flexNodeElement);
	}

	public synchronized Set<FlexNode> getNodes() {
		Set<FlexNode> newNodes = new HashSet<FlexNode>();
		newNodes.addAll(nodes);
		return newNodes;
	}

	public void removeNode(DirectedGraphNode cell) {
		if (cell instanceof FlexNode) {
			removeNode((FlexNode) cell);
		}
	}

}
