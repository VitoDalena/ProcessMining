/**
 * 
 */
package org.processmining.models.flexiblemodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.processmining.framework.util.Pair;

/**
 * @author Arya Adriansyah
 * @email a.adriansyah@tue.nl
 * @version Mar 22, 2011
 */
public class CancellationRegion extends TreeMap<FlexNode, Set<Pair<FlexNode, FlexNode>>>{
	private static final long serialVersionUID = 9130053378940776105L;
	
	private Map<FlexNode, Set<FlexNode>> nodeCancellationRegion = new HashMap<FlexNode, Set<FlexNode>>();

	public void addNodeCancellationFor(FlexNode node, FlexNode newNode){
		Set<FlexNode> set = nodeCancellationRegion.get(node);
		if (set != null){
			set.add(newNode);
		} else {
			set = new HashSet<FlexNode>();
			set.add(newNode);
			nodeCancellationRegion.put(node, set);
		}
	}
	
	public void removeNodeCancellationFor(FlexNode node, FlexNode removeNode){
		Set<FlexNode> set = nodeCancellationRegion.get(node);
		if (set != null){
			set.remove(removeNode);
		}
	}
	
	public void removeAllNodeCancellationFor(FlexNode node){
		nodeCancellationRegion.remove(node);
	}
	
	public Set<FlexNode> getNodeCancellationFor(FlexNode node){
		return nodeCancellationRegion.get(node);
	}
	
	public void setNodeCancellationFor(FlexNode node, Set<FlexNode> set){
		nodeCancellationRegion.put(node, set);
	}
}
