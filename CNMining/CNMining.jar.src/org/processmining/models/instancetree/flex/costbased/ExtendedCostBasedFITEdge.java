/**
 * 
 */
package org.processmining.models.instancetree.flex.costbased;

import org.processmining.models.instancetree.ITEdge;
import org.processmining.models.instancetree.ITNode;



/**
 * @author viet
 *
 */
public class ExtendedCostBasedFITEdge<X extends ITNode, Y extends ITNode> extends ITEdge<X, Y> {
	private String info = "";
	private String selectedNodeInstance = "";
	
	public ExtendedCostBasedFITEdge(X source, Y target) {
		super(source, target);
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	
	@Override
	public String getLabel(){
		return (info + "-" + selectedNodeInstance);
	}

	/**
	 * @return the selectedNodeInstance
	 */
	public String getSelectedNodeInstance() {
		return selectedNodeInstance;
	}

	/**
	 * @param selectedNodeInstance the selectedNodeInstance to set
	 */
	public void setSelectedNodeInstance(String selectedNodeInstance) {
		this.selectedNodeInstance = selectedNodeInstance;
	}
	
	
	
}
