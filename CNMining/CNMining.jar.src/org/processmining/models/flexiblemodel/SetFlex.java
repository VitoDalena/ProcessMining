/**
 * 
 */
package org.processmining.models.flexiblemodel;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author aadrians
 *
 */
public class SetFlex extends TreeSet<FlexNode> implements Comparable<Set<FlexNode>>{

	private static final long serialVersionUID = 3884169946523308917L;

	public int compareTo(Set<FlexNode> other) {
		if (this.size() != other.size()){
			if (this.size() > other.size()){
				return 1;
			} else {
				return -1;
			}
		} else {
			// same size, use the first label
			Iterator<FlexNode> itThis = this.iterator();
			Iterator<FlexNode> itOther = other.iterator();
			
			int temp = 0;
			while (itThis.hasNext()){
				temp = itThis.next().compareTo(itOther.next()); 
				if (temp != 0){
					return temp; 
				}
			}
			return temp;
		}
	}

}
