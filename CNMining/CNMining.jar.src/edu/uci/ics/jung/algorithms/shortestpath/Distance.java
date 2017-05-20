package edu.uci.ics.jung.algorithms.shortestpath;

import java.util.Map;

public abstract interface Distance<V>
{
  public abstract Number getDistance(V paramV1, V paramV2);
  
  public abstract Map<V, Number> getDistanceMap(V paramV);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/shortestpath/Distance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */