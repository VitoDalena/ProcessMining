package edu.uci.ics.jung.graph;

import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;
import java.util.Collection;

public abstract interface Graph<V, E>
  extends Hypergraph<V, E>
{
  public abstract Collection<E> getInEdges(V paramV);
  
  public abstract Collection<E> getOutEdges(V paramV);
  
  public abstract Collection<V> getPredecessors(V paramV);
  
  public abstract Collection<V> getSuccessors(V paramV);
  
  public abstract int inDegree(V paramV);
  
  public abstract int outDegree(V paramV);
  
  public abstract boolean isPredecessor(V paramV1, V paramV2);
  
  public abstract boolean isSuccessor(V paramV1, V paramV2);
  
  public abstract int getPredecessorCount(V paramV);
  
  public abstract int getSuccessorCount(V paramV);
  
  public abstract V getSource(E paramE);
  
  public abstract V getDest(E paramE);
  
  public abstract boolean isSource(V paramV, E paramE);
  
  public abstract boolean isDest(V paramV, E paramE);
  
  public abstract boolean addEdge(E paramE, V paramV1, V paramV2);
  
  public abstract boolean addEdge(E paramE, V paramV1, V paramV2, EdgeType paramEdgeType);
  
  public abstract Pair<V> getEndpoints(E paramE);
  
  public abstract V getOpposite(V paramV, E paramE);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/Graph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */