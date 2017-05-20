/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
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
/*     */ 
/*     */ 
/*     */ public class UndirectedSparseGraph<V, E>
/*     */   extends AbstractTypedGraph<V, E>
/*     */   implements UndirectedGraph<V, E>, Serializable
/*     */ {
/*     */   protected Map<V, Map<V, E>> vertices;
/*     */   protected Map<E, Pair<V>> edges;
/*     */   
/*     */   public static <V, E> Factory<UndirectedGraph<V, E>> getFactory()
/*     */   {
/*  41 */     new Factory()
/*     */     {
/*     */       public UndirectedGraph<V, E> create() {
/*  44 */         return new UndirectedSparseGraph();
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public UndirectedSparseGraph()
/*     */   {
/*  56 */     super(EdgeType.UNDIRECTED);
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
/*  69 */     V v1 = new_endpoints.getFirst();
/*  70 */     V v2 = new_endpoints.getSecond();
/*     */     
/*  72 */     if (findEdge(v1, v2) != null) {
/*  73 */       return false;
/*     */     }
/*  75 */     this.edges.put(edge, new_endpoints);
/*     */     
/*  77 */     if (!this.vertices.containsKey(v1)) {
/*  78 */       addVertex(v1);
/*     */     }
/*  80 */     if (!this.vertices.containsKey(v2)) {
/*  81 */       addVertex(v2);
/*     */     }
/*     */     
/*  84 */     ((Map)this.vertices.get(v1)).put(v2, edge);
/*  85 */     ((Map)this.vertices.get(v2)).put(v1, edge);
/*     */     
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public Collection<E> getInEdges(V vertex)
/*     */   {
/*  92 */     return getIncidentEdges(vertex);
/*     */   }
/*     */   
/*     */   public Collection<E> getOutEdges(V vertex)
/*     */   {
/*  97 */     return getIncidentEdges(vertex);
/*     */   }
/*     */   
/*     */   public Collection<V> getPredecessors(V vertex)
/*     */   {
/* 102 */     return getNeighbors(vertex);
/*     */   }
/*     */   
/*     */   public Collection<V> getSuccessors(V vertex)
/*     */   {
/* 107 */     return getNeighbors(vertex);
/*     */   }
/*     */   
/*     */ 
/*     */   public E findEdge(V v1, V v2)
/*     */   {
/* 113 */     if ((!containsVertex(v1)) || (!containsVertex(v2)))
/* 114 */       return null;
/* 115 */     return (E)((Map)this.vertices.get(v1)).get(v2);
/*     */   }
/*     */   
/*     */ 
/*     */   public Collection<E> findEdgeSet(V v1, V v2)
/*     */   {
/* 121 */     if ((!containsVertex(v1)) || (!containsVertex(v2)))
/* 122 */       return null;
/* 123 */     ArrayList<E> edge_collection = new ArrayList(1);
/*     */     
/*     */ 
/* 126 */     E e = findEdge(v1, v2);
/* 127 */     if (e == null)
/* 128 */       return edge_collection;
/* 129 */     edge_collection.add(e);
/* 130 */     return edge_collection;
/*     */   }
/*     */   
/*     */   public Pair<V> getEndpoints(E edge)
/*     */   {
/* 135 */     return (Pair)this.edges.get(edge);
/*     */   }
/*     */   
/*     */   public V getSource(E directed_edge)
/*     */   {
/* 140 */     return null;
/*     */   }
/*     */   
/*     */   public V getDest(E directed_edge)
/*     */   {
/* 145 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isSource(V vertex, E edge)
/*     */   {
/* 150 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isDest(V vertex, E edge)
/*     */   {
/* 155 */     return false;
/*     */   }
/*     */   
/*     */   public Collection<E> getEdges()
/*     */   {
/* 160 */     return Collections.unmodifiableCollection(this.edges.keySet());
/*     */   }
/*     */   
/*     */   public Collection<V> getVertices()
/*     */   {
/* 165 */     return Collections.unmodifiableCollection(this.vertices.keySet());
/*     */   }
/*     */   
/*     */   public boolean containsVertex(V vertex)
/*     */   {
/* 170 */     return this.vertices.containsKey(vertex);
/*     */   }
/*     */   
/*     */   public boolean containsEdge(E edge)
/*     */   {
/* 175 */     return this.edges.containsKey(edge);
/*     */   }
/*     */   
/*     */   public int getEdgeCount()
/*     */   {
/* 180 */     return this.edges.size();
/*     */   }
/*     */   
/*     */   public int getVertexCount()
/*     */   {
/* 185 */     return this.vertices.size();
/*     */   }
/*     */   
/*     */   public Collection<V> getNeighbors(V vertex)
/*     */   {
/* 190 */     if (!containsVertex(vertex))
/* 191 */       return null;
/* 192 */     return Collections.unmodifiableCollection(((Map)this.vertices.get(vertex)).keySet());
/*     */   }
/*     */   
/*     */   public Collection<E> getIncidentEdges(V vertex)
/*     */   {
/* 197 */     if (!containsVertex(vertex))
/* 198 */       return null;
/* 199 */     return Collections.unmodifiableCollection(((Map)this.vertices.get(vertex)).values());
/*     */   }
/*     */   
/*     */   public boolean addVertex(V vertex)
/*     */   {
/* 204 */     if (vertex == null) {
/* 205 */       throw new IllegalArgumentException("vertex may not be null");
/*     */     }
/* 207 */     if (!containsVertex(vertex)) {
/* 208 */       this.vertices.put(vertex, new HashMap());
/* 209 */       return true;
/*     */     }
/* 211 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean removeVertex(V vertex)
/*     */   {
/* 217 */     if (!containsVertex(vertex)) {
/* 218 */       return false;
/*     */     }
/*     */     
/* 221 */     for (E edge : new ArrayList(((Map)this.vertices.get(vertex)).values())) {
/* 222 */       removeEdge(edge);
/*     */     }
/* 224 */     this.vertices.remove(vertex);
/* 225 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removeEdge(E edge)
/*     */   {
/* 230 */     if (!containsEdge(edge)) {
/* 231 */       return false;
/*     */     }
/* 233 */     Pair<V> endpoints = getEndpoints(edge);
/* 234 */     V v1 = endpoints.getFirst();
/* 235 */     V v2 = endpoints.getSecond();
/*     */     
/*     */ 
/* 238 */     ((Map)this.vertices.get(v1)).remove(v2);
/* 239 */     ((Map)this.vertices.get(v2)).remove(v1);
/*     */     
/* 241 */     this.edges.remove(edge);
/* 242 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/UndirectedSparseGraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */