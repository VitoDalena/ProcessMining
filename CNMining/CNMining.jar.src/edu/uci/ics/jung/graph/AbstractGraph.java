/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractGraph<V, E>
/*     */   implements Graph<V, E>
/*     */ {
/*     */   public boolean addEdge(E edge, Collection<? extends V> vertices)
/*     */   {
/*  30 */     return addEdge(edge, vertices, getDefaultEdgeType());
/*     */   }
/*     */   
/*     */   public boolean addEdge(E edge, Collection<? extends V> vertices, EdgeType edgeType)
/*     */   {
/*  35 */     if (vertices == null)
/*  36 */       throw new IllegalArgumentException("'vertices' parameter must not be null");
/*  37 */     if (vertices.size() == 2) {
/*  38 */       return addEdge(edge, (vertices instanceof Pair) ? (Pair)vertices : new Pair(vertices), edgeType);
/*     */     }
/*     */     
/*  41 */     if (vertices.size() == 1)
/*     */     {
/*  43 */       V vertex = vertices.iterator().next();
/*  44 */       return addEdge(edge, new Pair(vertex, vertex), edgeType);
/*     */     }
/*     */     
/*  47 */     throw new IllegalArgumentException("Graph objects connect 1 or 2 vertices; vertices arg has " + vertices.size());
/*     */   }
/*     */   
/*     */   public boolean addEdge(E e, V v1, V v2)
/*     */   {
/*  52 */     return addEdge(e, v1, v2, getDefaultEdgeType());
/*     */   }
/*     */   
/*     */   public boolean addEdge(E e, V v1, V v2, EdgeType edge_type)
/*     */   {
/*  57 */     return addEdge(e, new Pair(v1, v2), edge_type);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addEdge(E edge, Pair<? extends V> endpoints)
/*     */   {
/*  67 */     return addEdge(edge, endpoints, getDefaultEdgeType());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean addEdge(E paramE, Pair<? extends V> paramPair, EdgeType paramEdgeType);
/*     */   
/*     */ 
/*     */ 
/*     */   protected Pair<V> getValidatedEndpoints(E edge, Pair<? extends V> endpoints)
/*     */   {
/*  79 */     if (edge == null) {
/*  80 */       throw new IllegalArgumentException("input edge may not be null");
/*     */     }
/*  82 */     if (endpoints == null) {
/*  83 */       throw new IllegalArgumentException("endpoints may not be null");
/*     */     }
/*  85 */     Pair<V> new_endpoints = new Pair(endpoints.getFirst(), endpoints.getSecond());
/*  86 */     if (containsEdge(edge))
/*     */     {
/*  88 */       Pair<V> existing_endpoints = getEndpoints(edge);
/*  89 */       if (!existing_endpoints.equals(new_endpoints)) {
/*  90 */         throw new IllegalArgumentException("edge " + edge + " already exists in this graph with endpoints " + existing_endpoints + " and cannot be added with endpoints " + endpoints);
/*     */       }
/*     */       
/*     */ 
/*  94 */       return null;
/*     */     }
/*     */     
/*  97 */     return new_endpoints;
/*     */   }
/*     */   
/*     */   public int inDegree(V vertex)
/*     */   {
/* 102 */     return getInEdges(vertex).size();
/*     */   }
/*     */   
/*     */   public int outDegree(V vertex)
/*     */   {
/* 107 */     return getOutEdges(vertex).size();
/*     */   }
/*     */   
/*     */   public boolean isPredecessor(V v1, V v2)
/*     */   {
/* 112 */     return getPredecessors(v1).contains(v2);
/*     */   }
/*     */   
/*     */   public boolean isSuccessor(V v1, V v2)
/*     */   {
/* 117 */     return getSuccessors(v1).contains(v2);
/*     */   }
/*     */   
/*     */   public int getPredecessorCount(V vertex)
/*     */   {
/* 122 */     return getPredecessors(vertex).size();
/*     */   }
/*     */   
/*     */   public int getSuccessorCount(V vertex)
/*     */   {
/* 127 */     return getSuccessors(vertex).size();
/*     */   }
/*     */   
/*     */   public boolean isNeighbor(V v1, V v2)
/*     */   {
/* 132 */     if ((!containsVertex(v1)) || (!containsVertex(v2)))
/* 133 */       throw new IllegalArgumentException("At least one of these not in this graph: " + v1 + ", " + v2);
/* 134 */     return getNeighbors(v1).contains(v2);
/*     */   }
/*     */   
/*     */   public boolean isIncident(V vertex, E edge)
/*     */   {
/* 139 */     if ((!containsVertex(vertex)) || (!containsEdge(edge)))
/* 140 */       throw new IllegalArgumentException("At least one of these not in this graph: " + vertex + ", " + edge);
/* 141 */     return getIncidentEdges(vertex).contains(edge);
/*     */   }
/*     */   
/*     */   public int getNeighborCount(V vertex)
/*     */   {
/* 146 */     if (!containsVertex(vertex))
/* 147 */       throw new IllegalArgumentException(vertex + " is not a vertex in this graph");
/* 148 */     return getNeighbors(vertex).size();
/*     */   }
/*     */   
/*     */   public int degree(V vertex)
/*     */   {
/* 153 */     if (!containsVertex(vertex))
/* 154 */       throw new IllegalArgumentException(vertex + " is not a vertex in this graph");
/* 155 */     return getIncidentEdges(vertex).size();
/*     */   }
/*     */   
/*     */   public int getIncidentCount(E edge)
/*     */   {
/* 160 */     Pair<V> incident = getEndpoints(edge);
/* 161 */     if (incident == null)
/* 162 */       return 0;
/* 163 */     if (incident.getFirst() == incident.getSecond()) {
/* 164 */       return 1;
/*     */     }
/* 166 */     return 2;
/*     */   }
/*     */   
/*     */   public V getOpposite(V vertex, E edge)
/*     */   {
/* 171 */     Pair<V> incident = getEndpoints(edge);
/* 172 */     V first = incident.getFirst();
/* 173 */     V second = incident.getSecond();
/* 174 */     if (vertex.equals(first))
/* 175 */       return second;
/* 176 */     if (vertex.equals(second)) {
/* 177 */       return first;
/*     */     }
/* 179 */     throw new IllegalArgumentException(vertex + " is not incident to " + edge + " in this graph");
/*     */   }
/*     */   
/*     */   public E findEdge(V v1, V v2)
/*     */   {
/* 184 */     for (E e : getOutEdges(v1))
/*     */     {
/* 186 */       if (getOpposite(v1, e).equals(v2))
/* 187 */         return e;
/*     */     }
/* 189 */     return null;
/*     */   }
/*     */   
/*     */   public Collection<E> findEdgeSet(V v1, V v2)
/*     */   {
/* 194 */     if (!getVertices().contains(v1)) {
/* 195 */       throw new IllegalArgumentException(v1 + " is not an element of this graph");
/*     */     }
/* 197 */     if (!getVertices().contains(v2)) {
/* 198 */       throw new IllegalArgumentException(v2 + " is not an element of this graph");
/*     */     }
/* 200 */     Collection<E> edges = new ArrayList();
/* 201 */     for (E e : getOutEdges(v1))
/*     */     {
/* 203 */       if (getOpposite(v1, e).equals(v2))
/* 204 */         edges.add(e);
/*     */     }
/* 206 */     return Collections.unmodifiableCollection(edges);
/*     */   }
/*     */   
/*     */   public Collection<V> getIncidentVertices(E edge)
/*     */   {
/* 211 */     Pair<V> endpoints = getEndpoints(edge);
/* 212 */     Collection<V> incident = new ArrayList();
/* 213 */     incident.add(endpoints.getFirst());
/* 214 */     incident.add(endpoints.getSecond());
/*     */     
/* 216 */     return Collections.unmodifiableCollection(incident);
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 221 */     StringBuffer sb = new StringBuffer("Vertices:");
/* 222 */     for (V v : getVertices()) {
/* 223 */       sb.append(v + ",");
/*     */     }
/* 225 */     sb.setLength(sb.length() - 1);
/* 226 */     sb.append("\nEdges:");
/* 227 */     for (E e : getEdges()) {
/* 228 */       Pair<V> ep = getEndpoints(e);
/* 229 */       sb.append(e + "[" + ep.getFirst() + "," + ep.getSecond() + "] ");
/*     */     }
/* 231 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/AbstractGraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */