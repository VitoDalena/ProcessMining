package edu.uci.ics.jung.graph;

import java.util.Collection;

public abstract interface Forest<V, E>
  extends DirectedGraph<V, E>
{
  public abstract Collection<Tree<V, E>> getTrees();
  
  public abstract V getParent(V paramV);
  
  public abstract E getParentEdge(V paramV);
  
  public abstract Collection<V> getChildren(V paramV);
  
  public abstract Collection<E> getChildEdges(V paramV);
  
  public abstract int getChildCount(V paramV);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/Forest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */