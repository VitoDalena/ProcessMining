package org.processmining.plugins.rttmining;

import java.util.Comparator;
import java.util.TreeSet;

public class CustomTreeSet
{
  	private TreeSet<TreeSet<String>> supercluster;
  
  	public CustomTreeSet(){
  		this.supercluster = new TreeSet(new Comparator()
  		{
  			public int compare(TreeSet<String> o1, TreeSet<String> o2)
  			{
  				if ((o1.containsAll(o2)) && (o2.containsAll(o1))) {
  					return 0;
  				}
  				if (o1.containsAll(o2)) {
  					return 1;
  				}
  				return -1;
  			}

  			// TODO: implementazione nostra
			public int compare(Object arg0, Object arg1) {
				if(arg0.getClass() != TreeSet.class || arg1.getClass() != TreeSet.class)
					return -1;
				
				TreeSet<String> o1 = (TreeSet<String>)arg0;
				TreeSet<String> o2 = (TreeSet<String>)arg1;
				
				if ((o1.containsAll(o2)) && (o2.containsAll(o1))) {
  					return 0;
  				}
  				if (o1.containsAll(o2)) {
  					return 1;
  				}
  				return -1;
			}
  		});
  	}
  
  	public void add(TreeSet<String> localcluster){
  		boolean aggiungi = true;
  		for (TreeSet<String> cluster : this.supercluster) {
  			if ((cluster.containsAll(localcluster)) && (localcluster.containsAll(cluster))) {
  				aggiungi = false;
  				break;
  			}
  		}
  		if (aggiungi) {
  			this.supercluster.add(localcluster);
  		}
  	}
  
  	public TreeSet<TreeSet<String>> getSupercluster(){
  		return this.supercluster;
  	}
}
