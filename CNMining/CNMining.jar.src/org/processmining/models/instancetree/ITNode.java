/**
 * 
 */
package org.processmining.models.instancetree;

import java.awt.Color;

import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.AbstractDirectedGraph;
import org.processmining.models.graphbased.directed.AbstractDirectedGraphNode;
import org.processmining.models.shapes.Ellipse;


/**
 * @author aadrians
 *
 */
public abstract class ITNode extends AbstractDirectedGraphNode {

	// link to graph
	private final AbstractDirectedGraph<? extends ITNode, ? extends ITEdge<? extends ITNode,? extends ITNode>> graph;
	protected String sequenceID;

	public ITNode(
			AbstractDirectedGraph<? extends ITNode, ? extends ITEdge<? extends ITNode,? extends ITNode>> graph, String label){
		super();
		this.graph = graph;
		getAttributeMap().put(AttributeMap.LABEL, label);
		getAttributeMap().put(AttributeMap.SHAPE, new Ellipse());
		getAttributeMap().put(AttributeMap.RESIZABLE, false);
		getAttributeMap().put(AttributeMap.FILLCOLOR, new Color(166, 166, 166));
	}

	@Override
	public AbstractDirectedGraph<?, ?> getGraph() {
		return graph;
	}
	
	public void setSequenceID(String sequenceID) {
		this.sequenceID = sequenceID; 
	}
	
	public String getSequenceID(){
		return this.sequenceID;
	}
}
