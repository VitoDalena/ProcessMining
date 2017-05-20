package org.processmining.models.flexiblemodel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Set;
import java.util.TreeSet;

import org.processmining.models.flexiblemodel.util.TextTransformer;
import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.directed.AbstractDirectedGraph;
import org.processmining.models.graphbased.directed.AbstractDirectedGraphNode;
import org.processmining.models.shapes.Decorated;
import org.processmining.models.shapes.RoundedRect;

/**
 * @author arya
 * @email arya.adriansyah@gmail.com
 * @version Nov 19, 2009
 */
public abstract class FlexNode extends AbstractDirectedGraphNode implements Decorated {
	/**
	 * DATA VARIABLES
	 */
	// linkage to graph
	private final AbstractDirectedGraph<FlexNode, FlexEdge<? extends FlexNode, ? extends FlexNode>> graph;

	// information inside node
	private final String elementName;
	private Set<SetFlex> inputNodes;
	private Set<SetFlex> outputNodes;
	private boolean isInvisible = false;
	
	// decoration
	private IExposeNodeDecoration decorator = null;

	/**
	 * Default constructor
	 * 
	 * @param graph
	 * @param label
	 */
	public FlexNode(AbstractDirectedGraph<FlexNode, FlexEdge<? extends FlexNode, ? extends FlexNode>> graph,
			String label) {
		super();
		this.graph = graph;

		// init I/O nodes
		inputNodes = new TreeSet<SetFlex>();
		outputNodes = new TreeSet<SetFlex>();

		// arrange attributes
		getAttributeMap().put(AttributeMap.SHAPE, new RoundedRect());
		getAttributeMap().put(AttributeMap.LABEL, label);
		getAttributeMap().put(AttributeMap.SHOWLABEL, true);
		getAttributeMap().put(AttributeMap.RESIZABLE, false);
		getAttributeMap().put(AttributeMap.SIZE, new Dimension(120, 70));
		getAttributeMap().put(AttributeMap.FILLCOLOR, Color.ORANGE);
		getAttributeMap().put(AttributeMap.SHOWLABEL, true);

		elementName = label;
	}

	public String getToolTipText() {
		return "<html><table><tr><td colspan=\"2\"><center><strong>" + elementName + "</strong></center></td></tr>"
				+ "<tr><td><strong>In:</strong></td><td>" + TextTransformer.getInNodesLabel(this) + "</td></tr>"
				+ "<tr><td><strong>Out:</strong></td><td>" + TextTransformer.getOutNodesLabel(this) + "</td></tr>";
	}

	/**
	 * set Label of the node
	 * 
	 * @param newLabel
	 */
	public void setLabel(String newLabel) {
		getAttributeMap().remove(AttributeMap.LABEL);
		getAttributeMap().put(AttributeMap.LABEL, newLabel);
	}

	public boolean addInputNodes(SetFlex inputNodes) {
		return this.inputNodes.add(inputNodes);
	}

	public boolean removeInputNodes(SetFlex inputNodes) {
		return this.inputNodes.remove(inputNodes);
	}

	public boolean addOutputNodes(SetFlex outputNodes) {
		return this.outputNodes.add(outputNodes);
	}

	public boolean removeOutputNodes(SetFlex outputNodes) {
		return this.outputNodes.remove(outputNodes);
	}

	/**
	 * @return the isInvisible
	 */
	public boolean isInvisible() {
		return isInvisible;
	}

	/**
	 * @param isInvisible
	 *            the isInvisible node to set
	 */
	public void setInvisible(boolean isInvisible) {
		if (isInvisible){
			getAttributeMap().put(AttributeMap.FILLCOLOR, Color.GRAY);
		}
		this.isInvisible = isInvisible;
	}

	public String getInfo() {
		StringBuilder builder = new StringBuilder();
		builder.append("[I:{");
		for (Set<FlexNode> setFlex : inputNodes) {
			builder.append("(");
			for (FlexNode node : setFlex) {
				builder.append(node.getLabel());
			}
			builder.append(")");
		}
		builder.append("}][O:{");
		for (Set<FlexNode> setFlex : outputNodes) {
			builder.append("(");
			for (FlexNode node : setFlex) {
				builder.append(node.getLabel());
			}
			builder.append(")");
		}
		builder.append("}]");
		return builder.toString();
	}

	/**
	 * If this node is a start task node, draw additional indicator
	 */
	public void decorate(Graphics2D g2d, double x, double y, double width, double height) {
		if (decorator != null){
			decorator.decorate(g2d, x, y, width, height);
		}
	}
	
	/**
	 * Commit all updates (including size, etc). In this case, only update
	 * tooltip first
	 */
	public void commitUpdates() {
		getAttributeMap().put(AttributeMap.TOOLTIP, getToolTipText());
	}

	/**
	 * return the graph which this node is belong to
	 */
	public AbstractDirectedGraph<FlexNode, FlexEdge<? extends FlexNode, ? extends FlexNode>> getGraph() {
		return graph;
	}

	/**
	 * @return the inputNodes
	 */
	public Set<SetFlex> getInputNodes() {
		return inputNodes;
	}

	/**
	 * @param inputNodes
	 *            the inputNodes to set
	 */
	public void setInputNodes(Set<SetFlex> inputNodes) {
		this.inputNodes = inputNodes;
	}

	/**
	 * @return the outputNodes
	 */
	public Set<SetFlex> getOutputNodes() {
		return outputNodes;
	}

	/**
	 * @param outputNodes
	 *            the outputNodes to set
	 */
	public void setOutputNodes(Set<SetFlex> outputNodes) {
		this.outputNodes = outputNodes;
	}

	/**
	 * @return the decorator
	 */
	public IExposeNodeDecoration getDecorator() {
		return decorator;
	}

	/**
	 * @param decorator the decorator to set
	 */
	public void setDecorator(IExposeNodeDecoration decorator) {
		this.decorator = decorator;
	}
}
