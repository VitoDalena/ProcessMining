/**
 * 
 */
package org.processmining.models.instancetree.petrinet.pncostbased;


/**
 * @author aadrians
 *
 */
public class PNCostBasedTreeFactory {
	private PNCostBasedTreeFactory(){}
	
	public static PNCostBasedTree newPNCostBasedTree(String label) {
		PNCostBasedTreeImpl res = new PNCostBasedTreeImpl(label);
		return res;
	}
}
