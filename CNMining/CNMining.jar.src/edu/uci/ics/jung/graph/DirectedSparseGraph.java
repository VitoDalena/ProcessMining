/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.Factory;
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
/*     */ 
/*     */ 
/*     */ public class DirectedSparseGraph<V, E>
/*     */   extends AbstractTypedGraph<V, E>
/*     */   implements DirectedGraph<V, E>, Serializable
/*     */ {
/*     */   protected Map<V, Pair<Map<V, E>>> vertices;
/*     */   protected Map<E, Pair<V>> edges;
/*     */   
/*     */   public static final <V, E> Factory<DirectedGraph<V, E>> getFactory()
/*     */   {
/*  40 */     new Factory() {
/*     */       public DirectedGraph<V, E> create() {
/*  42 */         return new DirectedSparseGraph();
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DirectedSparseGraph()
/*     */   {
/*  56 */     super(EdgeType.DIRECTED);
/*  57 */     this.vertices = new HashMap();
/*  58 */     this.edges = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addEdge(E edge, Pair<? extends V> endpoints, EdgeType edgeType)
/*     */   {
/*  64 */     validateEdgeType(edgeType);
/*  65 */     Pair<V> new_endpoints = getValidatedEndpoints(edge, endpoints);
/*  66 */     if (new_endpoints == null) {
/*  67 */       return false;
/*     */     }
/*  69 */     V source = new_endpoints.getFirst();
/*  70 */     V dest = new_endpoints.getSecond();
/*     */     
/*  72 */     if (findEdge(source, dest) != null) {
/*  73 */       return false;
/*     */     }
/*  75 */     this.edges.put(edge, new_endpoints);
/*     */     
/*  77 */     if (!this.vertices.containsKey(source)) {
/*  78 */       addVertex(source);
/*     */     }
/*  80 */     if (!this.vertices.containsKey(dest)) {
/*  81 */       addVertex(dest);
/*     */     }
/*     */     
/*  84 */     ((Map)((Pair)this.vertices.get(source)).getSecond()).put(dest, edge);
/*  85 */     ((Map)((Pair)this.vertices.get(dest)).getFirst()).put(source, edge);
/*     */     
/*  87 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public E findEdge(V v1, V v2)
/*     */   {
/*  93 */     if ((!containsVertex(v1)) || (!containsVertex(v2)))
/*  94 */       return null;
/*  95 */     return (E)((Map)((Pair)this.vertices.get(v1)).getSecond()).get(v2);
/*     */   }
/*     */   
/*     */ 
/*     */   public Collection<E> findEdgeSet(V v1, V v2)
/*     */   {
/* 101 */     if ((!containsVertex(v1)) || (!containsVertex(v2)))
/* 102 */       return null;
/* 103 */     ArrayList<E> edge_collection = new ArrayList(1);
/* 104 */     E e = findEdge(v1, v2);
/* 105 */     if (e == null)
/* 106 */       return edge_collection;
/* 107 */     edge_collection.add(e);
/* 108 */     return edge_collection;
/*     */   }
/*     */   
/*     */   protected Collection<E> getIncoming_internal(V vertex)
/*     */   {
/* 113 */     return ((Map)((Pair)this.vertices.get(vertex)).getFirst()).values();
/*     */   }
/*     */   
/*     */   protected Collection<E> getOutgoing_internal(V vertex)
/*     */   {
/* 118 */     return ((Map)((Pair)this.vertices.get(vertex)).getSecond()).values();
/*     */   }
/*     */   
/*     */   protected Collection<V> getPreds_internal(V vertex)
/*     */   {
/* 123 */     return ((Map)((Pair)this.vertices.get(vertex)).getFirst()).keySet();
/*     */   }
/*     */   
/*     */   protected Collection<V> getSuccs_internal(V vertex)
/*     */   {
/* 128 */     return ((Map)((Pair)this.vertices.get(vertex)).getSecond()).keySet();
/*     */   }
/*     */   
/*     */   public Collection<E> getInEdges(V vertex)
/*     */   {
/* 133 */     if (!containsVertex(vertex))
/* 134 */       return null;
/* 135 */     return Collections.unmodifiableCollection(getIncoming_internal(vertex));
/*     */   }
/*     */   
/*     */   public Collection<E> getOutEdges(V vertex)
/*     */   {
/* 140 */     if (!containsVertex(vertex))
/* 141 */       return null;
/* 142 */     return Collections.unmodifiableCollection(getOutgoing_internal(vertex));
/*     */   }
/*     */   
/*     */   public Collection<V> getPredecessors(V vertex)
/*     */   {
/* 147 */     if (!containsVertex(vertex))
/* 148 */       return null;
/* 149 */     return Collections.unmodifiableCollection(getPreds_internal(vertex));
/*     */   }
/*     */   
/*     */   public Collection<V> getSuccessors(V vertex)
/*     */   {
/* 154 */     if (!containsVertex(vertex))
/* 155 */       return null;
/* 156 */     return Collections.unmodifiableCollection(getSuccs_internal(vertex));
/*     */   }
/*     */   
/*     */   public Pair<V> getEndpoints(E edge)
/*     */   {
/* 161 */     if (!containsEdge(edge))
/* 162 */       return null;
/* 163 */     return (Pair)this.edges.get(edge);
/*     */   }
/*     */   
/*     */   public V getSource(E directed_edge)
/*     */   {
/* 168 */     if (!containsEdge(directed_edge))
/* 169 */       return null;
/* 170 */     return (V)((Pair)this.edges.get(directed_edge)).getFirst();
/*     */   }
/*     */   
/*     */   public V getDest(E directed_edge)
/*     */   {
/* 175 */     if (!containsEdge(directed_edge))
/* 176 */       return null;
/* 177 */     return (V)((Pair)this.edges.get(directed_edge)).getSecond();
/*     */   }
/*     */   
/*     */   public boolean isSource(V vertex, E edge)
/*     */   {
/* 182 */     if ((!containsEdge(edge)) || (!containsVertex(vertex)))
/* 183 */       return false;
/* 184 */     return vertex.equals(getEndpoints(edge).getFirst());
/*     */   }
/*     */   
/*     */   public boolean isDest(V vertex, E edge)
/*     */   {
/* 189 */     if ((!containsEdge(edge)) || (!containsVertex(vertex)))
/* 190 */       return false;
/* 191 */     return vertex.equals(getEndpoints(edge).getSecond());
/*     */   }
/*     */   
/*     */   public Collection<E> getEdges()
/*     */   {
/* 196 */     return Collections.unmodifiableCollection(this.edges.keySet());
/*     */   }
/*     */   
/*     */   public Collection<V> getVertices()
/*     */   {
/* 201 */     return Collections.unmodifiableCollection(this.vertices.keySet());
/*     */   }
/*     */   
/*     */   public boolean containsVertex(V vertex)
/*     */   {
/* 206 */     return this.vertices.containsKey(vertex);
/*     */   }
/*     */   
/*     */   public boolean containsEdge(E edge)
/*     */   {
/* 211 */     return this.edges.containsKey(edge);
/*     */   }
/*     */   
/*     */   public int getEdgeCount()
/*     */   {
/* 216 */     return this.edges.size();
/*     */   }
/*     */   
/*     */   public int getVertexCount()
/*     */   {
/* 221 */     return this.vertices.size();
/*     */   }
/*     */   
/*     */   public Collection<V> getNeighbors(V vertex)
/*     */   {
/* 226 */     if (!containsVertex(vertex)) {
/* 227 */       return null;
/*     */     }
/* 229 */     Collection<V> neighbors = new HashSet();
/* 230 */     neighbors.addAll(getPreds_internal(vertex));
/* 231 */     neighbors.addAll(getSuccs_internal(vertex));
/* 232 */     return Collections.unmodifiableCollection(neighbors);
/*     */   }
/*     */   
/*     */   public Collection<E> getIncidentEdges(V vertex)
/*     */   {
/* 237 */     if (!containsVertex(vertex)) {
/* 238 */       return null;
/*     */     }
/* 240 */     Collection<E> incident_edges = new HashSet();
/* 241 */     incident_edges.addAll(getIncoming_internal(vertex));
/* 242 */     incident_edges.addAll(getOutgoing_internal(vertex));
/* 243 */     return Collections.unmodifiableCollection(incident_edges);
/*     */   }
/*     */   
/*     */   public boolean addVertex(V vertex)
/*     */   {
/* 248 */     if (vertex == null) {
/* 249 */       throw new IllegalArgumentException("vertex may not be null");
/*     */     }
/* 251 */     if (!containsVertex(vertex)) {
/* 252 */       this.vertices.put(vertex, new Pair(new HashMap(), new HashMap()));
/* 253 */       return true;
/*     */     }
/* 255 */     return false;
/*     */   }
/*     */   
/*     */   public boolean removeVertex(V vertex)
/*     */   {
/* 260 */     if (!containsVertex(vertex)) {
/* 261 */       return false;
/*     */     }
/*     */     
/* 264 */     ArrayList<E> incident = new ArrayList(getIncoming_internal(vertex));
/* 265 */     incident.addAll(getOutgoing_internal(vertex));
/*     */     
/* 267 */     for (E edge : incident) {
/* 268 */       removeEdge(edge);
/*     */     }
/* 270 */     this.vertices.remove(vertex);
/*     */     
/* 272 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removeEdge(E edge) {
/* 276 */     if (!containsEdge(edge)) {
/* 277 */       return false;
/*     */     }
/* 279 */     Pair<V> endpoints = getEndpoints(edge);
/* 280 */     V source = endpoints.getFirst();
/* 281 */     V dest = endpoints.getSecond();
/*     */     
/*     */ 
/* 284 */     ((Map)((Pair)this.vertices.get(source)).getSecond()).remove(dest);
/* 285 */     ((Map)((Pair)this.vertices.get(dest)).getFirst()).remove(source);
/*     */     
/* 287 */     this.edges.remove(edge);
/* 288 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/DirectedSparseGraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */