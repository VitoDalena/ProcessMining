package org.processmining.models.flexiblemodel;

import java.awt.Graphics2D;

import org.processmining.models.graphbased.AttributeMap;
import org.processmining.models.graphbased.AttributeMap.ArrowType;
import org.processmining.models.graphbased.directed.AbstractDirectedGraphEdge;
import org.processmining.models.shapes.Decorated;

/**
 * @author arya
 * @email arya.adriansyah@gmail.com
 * @version Nov 19, 2009
 */
public abstract class FlexEdge<S extends FlexNode, T extends FlexNode> extends AbstractDirectedGraphEdge<S, T> implements Decorated {
	private IExposeEdgeDecoration decorator = null;
	
	/**
	 * Default constructor
	 * 
	 * @param source
	 * @param target
	 */
	public FlexEdge(S source, T target) {
		super(source, target);
		getAttributeMap().put(AttributeMap.SHOWLABEL, false);
		getAttributeMap().put(AttributeMap.EDGEEND, ArrowType.ARROWTYPE_TECHNICAL);
	}
	
	/**
	 * @return the decorator
	 */
	public IExposeEdgeDecoration getDecorator() {
		return decorator;
	}

	/**
	 * @param decorator the decorator to set
	 */
	public void setDecorator(IExposeEdgeDecoration decorator) {
		this.decorator = decorator;
	}



	public void decorate(Graphics2D g2d, double x, double y, double width, double height) {
		if (decorator != null){
			decorator.decorate(g2d, x, y, width, height);
		}
	}
}
