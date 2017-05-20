package edu.uci.ics.jung.algorithms.layout;

import java.awt.Shape;
import java.util.Collection;

public abstract interface GraphElementAccessor<V, E>
{
  public abstract V getVertex(Layout<V, E> paramLayout, double paramDouble1, double paramDouble2);
  
  public abstract Collection<V> getVertices(Layout<V, E> paramLayout, Shape paramShape);
  
  public abstract E getEdge(Layout<V, E> paramLayout, double paramDouble1, double paramDouble2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/layout/GraphElementAccessor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */