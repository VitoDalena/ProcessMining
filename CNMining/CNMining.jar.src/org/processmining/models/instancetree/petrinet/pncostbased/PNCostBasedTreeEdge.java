/**
 * 
 */
package org.processmining.models.instancetree.petrinet.pncostbased;

import org.processmining.models.instancetree.ITEdge;
import org.processmining.models.instancetree.ITNode;

/**
 * @author aadrians
 *
 */
public class PNCostBasedTreeEdge<X extends ITNode, Y extends ITNode> extends ITEdge<X, Y> {
	private String info = "";
	private String selectedTransition = "";
	
	public PNCostBasedTreeEdge(X source, Y target) {
		super(source, target);
	}

	/**
	 * @return the info
	 */
	public String getStepInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setStepInfo(String info) {
		this.info = info;
	}
	
	@Override
	public String getLabel(){
		return (info + "-" + selectedTransition);
	}

	/**
	 * @return the selectedTransition
	 */
	public String getSelectedTransition() {
		return selectedTransition;
	}

	/**
	 * @param selectedTransition the selectedTransition to set
	 */
	public void setSelectedTransition(String selectedTransition) {
		this.selectedTransition = selectedTransition;
	}
}
