/**
 * 
 */
package org.processmining.models.instancetree.flex.costbased;



/**
 * @author aadrians
 *
 */
public class ExtendedCostBasedFITFactory {
	private ExtendedCostBasedFITFactory(){}
	
	public static ExtendedCostBasedFIT newCostBasedFIT(String label) {
		ExtendedCostBasedFITImpl res = new ExtendedCostBasedFITImpl(label);
		return res;
	}
}
