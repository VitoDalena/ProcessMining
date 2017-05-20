package edu.uci.ics.jung.graph;

import java.util.Collection;
import org.apache.commons.collections15.Predicate;

public abstract interface KPartiteGraph<V, E>
  extends Graph<V, E>
{
  public abstract Collection<V> getVertices(Predicate<V> paramPredicate);
  
  public abstract Collection<Predicate<V>> getPartitions();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/KPartiteGraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */