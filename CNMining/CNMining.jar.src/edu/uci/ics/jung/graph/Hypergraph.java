package edu.uci.ics.jung.graph;

import edu.uci.ics.jung.graph.util.EdgeType;
import java.util.Collection;

public abstract interface Hypergraph<V, E>
{
  public abstract Collection<E> getEdges();
  
  public abstract Collection<V> getVertices();
  
  public abstract boolean containsVertex(V paramV);
  
  public abstract boolean containsEdge(E paramE);
  
  public abstract int getEdgeCount();
  
  public abstract int getVertexCount();
  
  public abstract Collection<V> getNeighbors(V paramV);
  
  public abstract Collection<E> getIncidentEdges(V paramV);
  
  public abstract Collection<V> getIncidentVertices(E paramE);
  
  public abstract E findEdge(V paramV1, V paramV2);
  
  public abstract Collection<E> findEdgeSet(V paramV1, V paramV2);
  
  public abstract boolean addVertex(V paramV);
  
  public abstract boolean addEdge(E paramE, Collection<? extends V> paramCollection);
  
  public abstract boolean addEdge(E paramE, Collection<? extends V> paramCollection, EdgeType paramEdgeType);
  
  public abstract boolean removeVertex(V paramV);
  
  public abstract boolean removeEdge(E paramE);
  
  public abstract boolean isNeighbor(V paramV1, V paramV2);
  
  public abstract boolean isIncident(V paramV, E paramE);
  
  public abstract int degree(V paramV);
  
  public abstract int getNeighborCount(V paramV);
  
  public abstract int getIncidentCount(E paramE);
  
  public abstract EdgeType getEdgeType(E paramE);
  
  public abstract EdgeType getDefaultEdgeType();
  
  public abstract Collection<E> getEdges(EdgeType paramEdgeType);
  
  public abstract int getEdgeCount(EdgeType paramEdgeType);
  
  public abstract Collection<E> getInEdges(V paramV);
  
  public abstract Collection<E> getOutEdges(V paramV);
  
  public abstract int inDegree(V paramV);
  
  public abstract int outDegree(V paramV);
  
  public abstract V getSource(E paramE);
  
  public abstract V getDest(E paramE);
  
  public abstract Collection<V> getPredecessors(V paramV);
  
  public abstract Collection<V> getSuccessors(V paramV);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/Hypergraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */