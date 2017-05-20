/**
 * 
 */
package org.processmining.models.instancetree.flex.costbased;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

import org.processmining.framework.util.Pair;
import org.processmining.framework.util.collection.HashMultiSet;
import org.processmining.framework.util.collection.MultiSet;
import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.AbstractDirectedGraph;
import org.processmining.models.graphbased.directed.DirectedGraphNode;
import org.processmining.models.instancetree.ITEdge;
import org.processmining.models.instancetree.ITNode;

/**
 * @author viet
 * 
 */
public class ExtendedCostBasedFITNode extends ITNode {
	// accumulated variables
	private int numberOfUnsatisfiedEvents = 0; // events that are replayed without proper predecessor
	private int numberOfReplayedEvents = 0; // number of replayed events
	private int numberOfSkippedEvts = 0; // move forward only in trace
	private int numberOfSelfExecRealTasks = 0; // move forward only in model
	private int numberOfSelfExecInviTasks = 0; // move forward only in model

	// not accumulated variables
	private int heuristicDistance = 0; // events still to be replayed

	// helping variables
	private int currIndexOnTrace = 0; // index of the next event to be replayed

	// util variables
	private MultiSet<Pair<Short, Short>> unhandledArcs = new HashMultiSet<Pair<Short, Short>>();
	private Set<Short> startingNodes;
	private int cost = 0; // cost 

	public ExtendedCostBasedFITNode(
			AbstractDirectedGraph<? extends ITNode, ? extends ITEdge<? extends ITNode, ? extends ITNode>> graph,
			String label) {
		super(graph, label);
		startingNodes = new HashSet<Short>();
		getAttributeMap().put(AttributeMap.SIZE, new Dimension(25, 25));
	}

	@Override
	public String getLabel() {
		String label = "<html><table border='0'>";
		label += "<tr><td>ID</td><td>#L</td><td>#M</td><td>#L/M</td><td>Heur</td></tr>";
		label += "<tr><td>" + sequenceID + "</td><td>" + numberOfSkippedEvts + "</td><td>"
				+ (numberOfSelfExecInviTasks + numberOfSelfExecRealTasks) + "</td><td>" + numberOfReplayedEvents
				+ "</td><td>" + heuristicDistance + "</tr>";

		//		label += "<tr><td colspan='5'>Unh.Arc:";
		//		if (unhandledArcs != null){
		//			for (Pair<Short, Short> pair : unhandledArcs){
		//				label += "(" + pair.getFirst() + "," + pair.getSecond() + ")";
		//			}
		//		} else {
		//			label += "-";
		//		}		
		//		label += "</td></tr>";

		label += "<tr><td colspan='5'>Cost:" + cost + "</td></tr>";

		label += "</table></html>";
		return label;
	}

	@Override
	public int compareTo(DirectedGraphNode node) {
		if (node instanceof ExtendedCostBasedFITNode) {

			int result = Double.compare(getCost(), ((ExtendedCostBasedFITNode) node).getCost());
			if (result == 0) {
				int compareUnhandledArcs = Double.compare(getUnhandledArcs().size(), ((ExtendedCostBasedFITNode) node)
						.getUnhandledArcs().size());
				if (compareUnhandledArcs == 0) {
					return 0;
				} else
					return compareUnhandledArcs;
			} else
				return result;
		} else {

			return super.compareTo(node);
		}
	}

	/**
	 * @return the numberOfUnsatisfiedEvents
	 */
	public int getNumberOfUnsatisfiedEvents() {
		return numberOfUnsatisfiedEvents;
	}

	/**
	 * @param numberOfUnsatisfiedEvents
	 *            the numberOfUnsatisfiedEvents to set
	 */
	public void setNumberOfUnsatisfiedEvents(int numberOfUnsatisfiedEvents) {
		this.numberOfUnsatisfiedEvents = numberOfUnsatisfiedEvents;
	}

	/**
	 * @return the numberOfReplayedEvents
	 */
	public int getNumberOfReplayedEvents() {
		return numberOfReplayedEvents;
	}

	/**
	 * @param numberOfReplayedEvents
	 *            the numberOfReplayedEvents to set
	 */
	public void setNumberOfReplayedEvents(int numberOfReplayedEvents) {
		this.numberOfReplayedEvents = numberOfReplayedEvents;
	}

	/**
	 * @return the numberOfSkippedEvts
	 */
	public int getNumberOfSkippedEvts() {
		return numberOfSkippedEvts;
	}

	/**
	 * @param numberOfSkippedEvts
	 *            the numberOfSkippedEvts to set
	 */
	public void setNumberOfSkippedEvts(int numberOfSkippedEvts) {
		this.numberOfSkippedEvts = numberOfSkippedEvts;
	}

	/**
	 * @return the numberOfSelfExecRealTasks
	 */
	public int getNumberOfSelfExecRealTasks() {
		return numberOfSelfExecRealTasks;
	}

	/**
	 * @param numberOfSelfExecRealTasks
	 *            the numberOfSelfExecRealTasks to set
	 */
	public void setNumberOfSelfExecRealTasks(int numberOfSelfExecRealTasks) {
		this.numberOfSelfExecRealTasks = numberOfSelfExecRealTasks;
	}

	/**
	 * @return the numberOfSelfExecInviTasks
	 */
	public int getNumberOfSelfExecInviTasks() {
		return numberOfSelfExecInviTasks;
	}

	/**
	 * @param numberOfSelfExecInviTasks
	 *            the numberOfSelfExecInviTasks to set
	 */
	public void setNumberOfSelfExecInviTasks(int numberOfSelfExecInviTasks) {
		this.numberOfSelfExecInviTasks = numberOfSelfExecInviTasks;
	}

	/**
	 * @return the heuristicDistance
	 */
	public int getHeuristicDistance() {
		return heuristicDistance;
	}

	/**
	 * @param heuristicDistance
	 *            the heuristicDistance to set
	 */
	public void setHeuristicDistance(int heuristicDistance) {
		this.heuristicDistance = heuristicDistance;
	}

	/**
	 * @return the unhandledArcs
	 */
	public MultiSet<Pair<Short, Short>> getUnhandledArcs() {
		return unhandledArcs;
	}

	/**
	 * @param unhandledArcs
	 *            the unhandledArcsto set
	 */
	public void setUnhandledArcs(MultiSet<Pair<Short, Short>> unhandledArcs) {
		this.unhandledArcs = unhandledArcs;
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

	public Set<Short> getStartingNodes() {
		return startingNodes;
	}

	public void setStartingNodes(Set<Short> startingNodes) {
		this.startingNodes = startingNodes;
	}

}
