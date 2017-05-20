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
/*     */ import java.util.Set;
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
/*     */ public class UndirectedSparseMultigraph<V, E>
/*     */   extends AbstractTypedGraph<V, E>
/*     */   implements UndirectedGraph<V, E>, MultiGraph<V, E>, Serializable
/*     */ {
/*     */   protected Map<V, Set<E>> vertices;
/*     */   protected Map<E, Pair<V>> edges;
/*     */   
/*     */   public static <V, E> Factory<UndirectedGraph<V, E>> getFactory()
/*     */   {
/*  54 */     new Factory()
/*     */     {
/*     */       public UndirectedGraph<V, E> create() {
/*  57 */         return new UndirectedSparseMultigraph();
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public UndirectedSparseMultigraph()
/*     */   {
/*  69 */     super(EdgeType.UNDIRECTED);
/*  70 */     this.vertices = new HashMap();
/*  71 */     this.edges = new HashMap();
/*     */   }
/*     */   
/*     */   public Collection<E> getEdges() {
/*  75 */     return Collections.unmodifiableCollection(this.edges.keySet());
/*     */   }
/*     */   
/*     */   public Collection<V> getVertices() {
/*  79 */     return Collections.unmodifiableCollection(this.vertices.keySet());
/*     */   }
/*     */   
/*     */   public boolean containsVertex(V vertex) {
/*  83 */     return this.vertices.keySet().contains(vertex);
/*     */   }
/*     */   
/*     */   public boolean containsEdge(E edge) {
/*  87 */     return this.edges.keySet().contains(edge);
/*     */   }
/*     */   
/*     */   protected Collection<E> getIncident_internal(V vertex)
/*     */   {
/*  92 */     return (Collection)this.vertices.get(vertex);
/*     */   }
/*     */   
/*     */   public boolean addVertex(V vertex) {
/*  96 */     if (vertex == null) {
/*  97 */       throw new IllegalArgumentException("vertex may not be null");
/*     */     }
/*  99 */     if (!containsVertex(vertex))
/*     */     {
/* 101 */       this.vertices.put(vertex, new HashSet());
/* 102 */       return true;
/*     */     }
/* 104 */     return false;
/*     */   }
/*     */   
/*     */   public boolean removeVertex(V vertex)
/*     */   {
/* 109 */     if (!containsVertex(vertex)) {
/* 110 */       return false;
/*     */     }
/* 112 */     for (E edge : new ArrayList(getIncident_internal(vertex))) {
/* 113 */       removeEdge(edge);
/*     */     }
/* 115 */     this.vertices.remove(vertex);
/* 116 */     return true;
/*     */   }
/*     */   
/*     */   public boolean addEdge(E edge, V v1, V v2, EdgeType edgeType)
/*     */   {
/* 121 */     return addEdge(edge, new Pair(v1, v2), edgeType);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addEdge(E edge, Pair<? extends V> endpoints, EdgeType edge_type)
/*     */   {
/* 127 */     validateEdgeType(edge_type);
/*     */     
/* 129 */     Pair<V> new_endpoints = getValidatedEndpoints(edge, endpoints);
/* 130 */     if (new_endpoints == null) {
/* 131 */       return false;
/*     */     }
/* 133 */     V v1 = endpoints.getFirst();
/* 134 */     V v2 = endpoints.getSecond();
/*     */     
/* 136 */     this.edges.put(edge, new_endpoints);
/*     */     
/* 138 */     if (!containsVertex(v1)) {
/* 139 */       addVertex(v1);
/*     */     }
/* 141 */     if (!containsVertex(v2)) {
/* 142 */       addVertex(v2);
/*     */     }
/* 144 */     ((Set)this.vertices.get(v1)).add(edge);
/* 145 */     ((Set)this.vertices.get(v2)).add(edge);
/*     */     
/* 147 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removeEdge(E edge) {
/* 151 */     if (!containsEdge(edge)) {
/* 152 */       return false;
/*     */     }
/* 154 */     Pair<V> endpoints = getEndpoints(edge);
/* 155 */     V v1 = endpoints.getFirst();
/* 156 */     V v2 = endpoints.getSecond();
/*     */     
/*     */ 
/* 159 */     ((Set)this.vertices.get(v1)).remove(edge);
/* 160 */     ((Set)this.vertices.get(v2)).remove(edge);
/*     */     
/* 162 */     this.edges.remove(edge);
/* 163 */     return true;
/*     */   }
/*     */   
/*     */   public Collection<E> getInEdges(V vertex) {
/* 167 */     return getIncidentEdges(vertex);
/*     */   }
/*     */   
/*     */   public Collection<E> getOutEdges(V vertex) {
/* 171 */     return getIncidentEdges(vertex);
/*     */   }
/*     */   
/*     */   public Collection<V> getPredecessors(V vertex) {
/* 175 */     return getNeighbors(vertex);
/*     */   }
/*     */   
/*     */   public Collection<V> getSuccessors(V vertex) {
/* 179 */     return getNeighbors(vertex);
/*     */   }
/*     */   
/*     */   public Collection<V> getNeighbors(V vertex) {
/* 183 */     if (!containsVertex(vertex)) {
/* 184 */       return null;
/*     */     }
/* 186 */     Set<V> neighbors = new HashSet();
/* 187 */     for (E edge : getIncident_internal(vertex))
/*     */     {
/* 189 */       Pair<V> endpoints = getEndpoints(edge);
/* 190 */       V e_a = endpoints.getFirst();
/* 191 */       V e_b = endpoints.getSecond();
/* 192 */       if (vertex.equals(e_a)) {
/* 193 */         neighbors.add(e_b);
/*     */       } else {
/* 195 */         neighbors.add(e_a);
/*     */       }
/*     */     }
/* 198 */     return Collections.unmodifiableCollection(neighbors);
/*     */   }
/*     */   
/*     */   public Collection<E> getIncidentEdges(V vertex) {
/* 202 */     if (!containsVertex(vertex)) {
/* 203 */       return null;
/*     */     }
/* 205 */     return Collections.unmodifiableCollection(getIncident_internal(vertex));
/*     */   }
/*     */   
/*     */   public E findEdge(V v1, V v2)
/*     */   {
/* 210 */     if ((!containsVertex(v1)) || (!containsVertex(v2)))
/* 211 */       return null;
/* 212 */     for (E edge : getIncident_internal(v1)) {
/* 213 */       Pair<V> endpoints = getEndpoints(edge);
/* 214 */       V e_a = endpoints.getFirst();
/* 215 */       V e_b = endpoints.getSecond();
/* 216 */       if (((v1.equals(e_a)) && (v2.equals(e_b))) || ((v1.equals(e_b)) && (v2.equals(e_a))))
/* 217 */         return edge;
/*     */     }
/* 219 */     return null;
/*     */   }
/*     */   
/*     */   public Pair<V> getEndpoints(E edge) {
/* 223 */     return (Pair)this.edges.get(edge);
/*     */   }
/*     */   
/*     */   public V getDest(E directed_edge) {
/* 227 */     return null;
/*     */   }
/*     */   
/*     */   public V getSource(E directed_edge) {
/* 231 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isDest(V vertex, E edge) {
/* 235 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isSource(V vertex, E edge) {
/* 239 */     return false;
/*     */   }
/*     */   
/*     */   public int getEdgeCount() {
/* 243 */     return this.edges.size();
/*     */   }
/*     */   
/*     */   public int getVertexCount() {
/* 247 */     return this.vertices.size();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/UndirectedSparseMultigraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */