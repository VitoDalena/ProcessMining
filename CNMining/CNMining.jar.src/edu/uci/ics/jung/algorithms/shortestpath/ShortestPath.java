package edu.uci.ics.jung.algorithms.shortestpath;

import java.util.Map;

public abstract interface ShortestPath<V, E>
{
  public abstract Map<V, E> getIncomingEdgeMap(V paramV);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/shortestpath/ShortestPath.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */