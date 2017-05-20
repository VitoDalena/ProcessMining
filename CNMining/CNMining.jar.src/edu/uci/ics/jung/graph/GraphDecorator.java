/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphDecorator<V, E>
/*     */   implements Graph<V, E>
/*     */ {
/*     */   protected Graph<V, E> delegate;
/*     */   
/*     */   public GraphDecorator(Graph<V, E> delegate)
/*     */   {
/*  23 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean addEdge(E edge, Collection<? extends V> vertices)
/*     */   {
/*  30 */     return this.delegate.addEdge(edge, vertices);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addEdge(E edge, Collection<? extends V> vertices, EdgeType edge_type)
/*     */   {
/*  39 */     return this.delegate.addEdge(edge, vertices, edge_type);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean addEdge(E e, V v1, V v2, EdgeType edgeType)
/*     */   {
/*  46 */     return this.delegate.addEdge(e, v1, v2, edgeType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean addEdge(E e, V v1, V v2)
/*     */   {
/*  53 */     return this.delegate.addEdge(e, v1, v2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean addVertex(V vertex)
/*     */   {
/*  60 */     return this.delegate.addVertex(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isIncident(V vertex, E edge)
/*     */   {
/*  67 */     return this.delegate.isIncident(vertex, edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isNeighbor(V v1, V v2)
/*     */   {
/*  74 */     return this.delegate.isNeighbor(v1, v2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int degree(V vertex)
/*     */   {
/*  81 */     return this.delegate.degree(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public E findEdge(V v1, V v2)
/*     */   {
/*  88 */     return (E)this.delegate.findEdge(v1, v2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<E> findEdgeSet(V v1, V v2)
/*     */   {
/*  95 */     return this.delegate.findEdgeSet(v1, v2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public V getDest(E directed_edge)
/*     */   {
/* 102 */     return (V)this.delegate.getDest(directed_edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getEdgeCount()
/*     */   {
/* 109 */     return this.delegate.getEdgeCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getEdgeCount(EdgeType edge_type)
/*     */   {
/* 117 */     return this.delegate.getEdgeCount(edge_type);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<E> getEdges()
/*     */   {
/* 124 */     return this.delegate.getEdges();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<E> getEdges(EdgeType edgeType)
/*     */   {
/* 131 */     return this.delegate.getEdges(edgeType);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EdgeType getEdgeType(E edge)
/*     */   {
/* 138 */     return this.delegate.getEdgeType(edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EdgeType getDefaultEdgeType()
/*     */   {
/* 146 */     return this.delegate.getDefaultEdgeType();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Pair<V> getEndpoints(E edge)
/*     */   {
/* 153 */     return this.delegate.getEndpoints(edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getIncidentCount(E edge)
/*     */   {
/* 160 */     return this.delegate.getIncidentCount(edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<E> getIncidentEdges(V vertex)
/*     */   {
/* 167 */     return this.delegate.getIncidentEdges(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<V> getIncidentVertices(E edge)
/*     */   {
/* 174 */     return this.delegate.getIncidentVertices(edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<E> getInEdges(V vertex)
/*     */   {
/* 181 */     return this.delegate.getInEdges(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getNeighborCount(V vertex)
/*     */   {
/* 188 */     return this.delegate.getNeighborCount(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<V> getNeighbors(V vertex)
/*     */   {
/* 195 */     return this.delegate.getNeighbors(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public V getOpposite(V vertex, E edge)
/*     */   {
/* 202 */     return (V)this.delegate.getOpposite(vertex, edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<E> getOutEdges(V vertex)
/*     */   {
/* 209 */     return this.delegate.getOutEdges(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getPredecessorCount(V vertex)
/*     */   {
/* 216 */     return this.delegate.getPredecessorCount(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<V> getPredecessors(V vertex)
/*     */   {
/* 223 */     return this.delegate.getPredecessors(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public V getSource(E directed_edge)
/*     */   {
/* 230 */     return (V)this.delegate.getSource(directed_edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getSuccessorCount(V vertex)
/*     */   {
/* 237 */     return this.delegate.getSuccessorCount(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<V> getSuccessors(V vertex)
/*     */   {
/* 244 */     return this.delegate.getSuccessors(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getVertexCount()
/*     */   {
/* 251 */     return this.delegate.getVertexCount();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<V> getVertices()
/*     */   {
/* 258 */     return this.delegate.getVertices();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int inDegree(V vertex)
/*     */   {
/* 265 */     return this.delegate.inDegree(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isDest(V vertex, E edge)
/*     */   {
/* 272 */     return this.delegate.isDest(vertex, edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isPredecessor(V v1, V v2)
/*     */   {
/* 279 */     return this.delegate.isPredecessor(v1, v2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isSource(V vertex, E edge)
/*     */   {
/* 286 */     return this.delegate.isSource(vertex, edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isSuccessor(V v1, V v2)
/*     */   {
/* 293 */     return this.delegate.isSuccessor(v1, v2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int outDegree(V vertex)
/*     */   {
/* 300 */     return this.delegate.outDegree(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean removeEdge(E edge)
/*     */   {
/* 307 */     return this.delegate.removeEdge(edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean removeVertex(V vertex)
/*     */   {
/* 314 */     return this.delegate.removeVertex(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean containsEdge(E edge)
/*     */   {
/* 321 */     return this.delegate.containsEdge(edge);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean containsVertex(V vertex)
/*     */   {
/* 328 */     return this.delegate.containsVertex(vertex);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/GraphDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */