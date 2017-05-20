package org.processmining.models.flexiblemodel;

import org.processmining.models.graphbased.directed.DirectedGraph;

/**
 * @author arya
 * @email arya.adriansyah@gmail.com
 * @version Nov 19, 2009
 */
public interface Flex extends DirectedGraph<FlexNode, FlexEdge<? extends FlexNode, ? extends FlexNode>> {
	/**
	 * Get label of a Flexible model
	 */
	public String getLabel();

	/**
	 * add a new Flex node with a certain label
	 * 
	 * @param label
	 * @return
	 */
	public FlexNode addNode(String label);

	/**
	 * Remove a FlexNode from an Flexible model
	 * 
	 * @param flexNodeElement
	 * @return
	 */
	public FlexNode removeNode(FlexNode flexNodeElement);

	// ARCS
	/**
	 * add new arc between source and target node
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public FlexEdge<FlexNode, FlexNode> addArc(FlexNode source, FlexNode target);

	/**
	 * remove an arc between source and target FlexNode
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public FlexEdge<FlexNode, FlexNode> removeArc(FlexNode source, FlexNode target);

	/**
	 * Get an arc between source and target node. Return null if there is no
	 * node
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public FlexEdge<FlexNode, FlexNode> getArc(FlexNode source, FlexNode target);

}
