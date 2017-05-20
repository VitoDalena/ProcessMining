package edu.uci.ics.jung.graph.util;

import edu.uci.ics.jung.graph.Graph;

public abstract interface EdgeIndexFunction<V, E>
{
  public abstract int getIndex(Graph<V, E> paramGraph, E paramE);
  
  public abstract void reset(Graph<V, E> paramGraph, E paramE);
  
  public abstract void reset();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/util/EdgeIndexFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */