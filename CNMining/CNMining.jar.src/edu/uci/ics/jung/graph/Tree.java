package edu.uci.ics.jung.graph;

public abstract interface Tree<V, E>
  extends Forest<V, E>
{
  public abstract int getDepth(V paramV);
  
  public abstract int getHeight();
  
  public abstract V getRoot();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/Tree.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */