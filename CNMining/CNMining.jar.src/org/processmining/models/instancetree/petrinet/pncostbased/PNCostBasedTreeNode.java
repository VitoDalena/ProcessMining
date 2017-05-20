/**
 * 
 */
package org.processmining.models.instancetree.petrinet.pncostbased;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import org.processmining.framework.util.Pair;
import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.AbstractDirectedGraph;
import org.processmining.models.graphbased.directed.DirectedGraphNode;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.instancetree.ITEdge;
import org.processmining.models.instancetree.ITNode;
import org.processmining.models.semantics.petrinet.Marking;

/**
 * @author aadrians
 * 
 */
public class PNCostBasedTreeNode extends ITNode {
	// helping variables
	private int currIndexOnTrace = 0; // index of the next event to be replayed

	// util variables
	private Marking currMarking = new Marking();
	private List<Pair<Integer, Transition>> duplicatesOnlyStep = new LinkedList<Pair<Integer, Transition>>();
	private List<Pair<Integer, Transition>> modelOnlyStep = new LinkedList<Pair<Integer, Transition>>();
	private List<Pair<Integer, Transition>> traceModelViolatingStep = new LinkedList<Pair<Integer, Transition>>();
	private List<Integer> moveTraceOnlyStep = new LinkedList<Integer>();
	private int cost = 0; // cost 

	public PNCostBasedTreeNode(
			AbstractDirectedGraph<? extends ITNode, ? extends ITEdge<? extends ITNode, ? extends ITNode>> graph,
			String label) {
		super(graph, label);
		getAttributeMap().put(AttributeMap.SIZE, new Dimension(25, 25));
	}

	@Override
	public String getLabel() {
		String label = "<html><table border='0'>";
		label += "<tr><td>ID</td><td>#L</td><td>#M</td><td>Idx.</td></tr>";
		label += "<tr><td>" + sequenceID + "</td><td>" + moveTraceOnlyStep.size() + "</td><td>" + modelOnlyStep.size()
				+ "</td><td>" + currIndexOnTrace + "</td></tr>";
		label += "<tr><td colspan='4'>Cost:" + cost + "</td></tr>";
		label += "</table></html>";
		return label;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof PNCostBasedTreeNode) {
			PNCostBasedTreeNode nodeX = (PNCostBasedTreeNode) o;
			int nodeXCost = nodeX.getCost();
			if (getCost() != nodeXCost) {
				return false;
			} else {
				if (currIndexOnTrace != nodeX.getCurrIndexOnTrace()) {
					return false;
				} else {
					return currMarking.equals(nodeX.getCurrMarking());
				}
			}
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(DirectedGraphNode node) {
		if (node instanceof PNCostBasedTreeNode) {
			PNCostBasedTreeNode nodeX = (PNCostBasedTreeNode) node;
			int nodeXCost = nodeX.getCost();
			if (getCost() < nodeXCost) {
				return -1;
			} else if (getCost() == nodeXCost) {
				int currIndexOnTrace = getCurrIndexOnTrace();
				int nodeXcurrIndexOnTrace = nodeX.getCurrIndexOnTrace();
				if (currIndexOnTrace > nodeXcurrIndexOnTrace) {
					return -1;
				} else if (currIndexOnTrace == nodeXcurrIndexOnTrace) {
					int currMarkingSize = currMarking.size();
					int nodeXcurrMarkingSize = nodeX.getCurrMarking().size();
					if (currMarkingSize < nodeXcurrMarkingSize) { // less marking
						return -1;
					} else if (currMarkingSize > nodeXcurrMarkingSize) {
						return 1;
					} else {
						if (currMarking.equals(nodeX.getCurrMarking())) {
							return 0;
						} else {
							return -1;
						}
					}
				} else {
					return 1;
				}
			} else {
				return 1;
			}
		} else {
			return super.compareTo(node);
		}
	}

	/**
	 * @return the currIndexOnTrace
	 */
	public int getCurrIndexOnTrace() {
		return currIndexOnTrace;
	}

	/**
	 * @param currIndexOnTrace
	 *            the currIndexOnTrace to set
	 */
	public void setCurrIndexOnTrace(int currIndexOnTrace) {
		this.currIndexOnTrace = currIndexOnTrace;
	}

	/**
	 * @return the currMarking
	 */
	public Marking getCurrMarking() {
		return currMarking;
	}

	/**
	 * @param currMarking
	 *            the currMarking to set
	 */
	public void setCurrMarking(Marking currMarking) {
		this.currMarking = currMarking;
	}

	/**
	 * @return the moveTraceOnlyStep
	 */
	public List<Integer> getMoveTraceOnlyStep() {
		return moveTraceOnlyStep;
	}

	/**
	 * @param moveTraceOnlyStep
	 *            the moveTraceOnlyStep to set
	 */
	public void setMoveTraceOnlyStep(List<Integer> moveTraceOnlyStep) {
		this.moveTraceOnlyStep = moveTraceOnlyStep;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * @return the duplicatesOnlyStep
	 */
	public List<Pair<Integer, Transition>> getDuplicatesOnlyStep() {
		return duplicatesOnlyStep;
	}

	/**
	 * @param duplicatesOnlyStep
	 *            the duplicatesOnlyStep to set
	 */
	public void setDuplicatesOnlyStep(List<Pair<Integer, Transition>> duplicatesOnlyStep) {
		this.duplicatesOnlyStep = duplicatesOnlyStep;
	}

	/**
	 * @return the modelOnlyStep
	 */
	public List<Pair<Integer, Transition>> getModelOnlyStep() {
		return modelOnlyStep;
	}

	/**
	 * @param modelOnlyStep
	 *            the modelOnlyStep to set
	 */
	public void setModelOnlyStep(List<Pair<Integer, Transition>> modelOnlyStep) {
		this.modelOnlyStep = modelOnlyStep;
	}

	/**
	 * @return the traceModelViolatingStep
	 */
	public List<Pair<Integer, Transition>> getTraceModelViolatingStep() {
		return traceModelViolatingStep;
	}

	/**
	 * @param traceModelViolatingStep
	 *            the traceModelViolatingStep to set
	 */
	public void setTraceModelViolatingStep(List<Pair<Integer, Transition>> traceModelViolatingStep) {
		this.traceModelViolatingStep = traceModelViolatingStep;
	}

}
