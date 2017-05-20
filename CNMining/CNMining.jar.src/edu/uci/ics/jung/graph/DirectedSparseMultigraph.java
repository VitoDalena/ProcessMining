/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.io.Serializable;
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
/*     */ public class DirectedSparseMultigraph<V, E>
/*     */   extends AbstractTypedGraph<V, E>
/*     */   implements DirectedGraph<V, E>, MultiGraph<V, E>, Serializable
/*     */ {
/*     */   protected Map<V, Pair<Set<E>>> vertices;
/*     */   protected Map<E, Pair<V>> edges;
/*     */   
/*     */   public static <V, E> Factory<DirectedGraph<V, E>> getFactory()
/*     */   {
/*  43 */     new Factory() {
/*     */       public DirectedGraph<V, E> create() {
/*  45 */         return new DirectedSparseMultigraph();
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DirectedSparseMultigraph()
/*     */   {
/*  57 */     super(EdgeType.DIRECTED);
/*  58 */     this.vertices = new HashMap();
/*  59 */     this.edges = new HashMap();
/*     */   }
/*     */   
/*     */   public Collection<E> getEdges() {
/*  63 */     return Collections.unmodifiableCollection(this.edges.keySet());
/*     */   }
/*     */   
/*     */   public Collection<V> getVertices() {
/*  67 */     return Collections.unmodifiableCollection(this.vertices.keySet());
/*     */   }
/*     */   
/*     */   public boolean containsVertex(V vertex) {
/*  71 */     return this.vertices.keySet().contains(vertex);
/*     */   }
/*     */   
/*     */   public boolean containsEdge(E edge) {
/*  75 */     return this.edges.keySet().contains(edge);
/*     */   }
/*     */   
/*     */   protected Collection<E> getIncoming_internal(V vertex)
/*     */   {
/*  80 */     return (Collection)((Pair)this.vertices.get(vertex)).getFirst();
/*     */   }
/*     */   
/*     */   protected Collection<E> getOutgoing_internal(V vertex)
/*     */   {
/*  85 */     return (Collection)((Pair)this.vertices.get(vertex)).getSecond();
/*     */   }
/*     */   
/*     */   public boolean addVertex(V vertex) {
/*  89 */     if (vertex == null) {
/*  90 */       throw new IllegalArgumentException("vertex may not be null");
/*     */     }
/*  92 */     if (!containsVertex(vertex)) {
/*  93 */       this.vertices.put(vertex, new Pair(new HashSet(), new HashSet()));
/*  94 */       return true;
/*     */     }
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public boolean removeVertex(V vertex)
/*     */   {
/* 101 */     if (!containsVertex(vertex)) {
/* 102 */       return false;
/*     */     }
/*     */     
/* 105 */     Set<E> incident = new HashSet(getIncoming_internal(vertex));
/* 106 */     incident.addAll(getOutgoing_internal(vertex));
/*     */     
/* 108 */     for (E edge : incident) {
/* 109 */       removeEdge(edge);
/*     */     }
/* 111 */     this.vertices.remove(vertex);
/*     */     
/* 113 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removeEdge(E edge) {
/* 117 */     if (!containsEdge(edge)) {
/* 118 */       return false;
/*     */     }
/* 120 */     Pair<V> endpoints = getEndpoints(edge);
/* 121 */     V source = endpoints.getFirst();
/* 122 */     V dest = endpoints.getSecond();
/*     */     
/*     */ 
/* 125 */     getOutgoing_internal(source).remove(edge);
/* 126 */     getIncoming_internal(dest).remove(edge);
/*     */     
/* 128 */     this.edges.remove(edge);
/* 129 */     return true;
/*     */   }
/*     */   
/*     */   public Collection<E> getInEdges(V vertex)
/*     */   {
/* 134 */     if (!containsVertex(vertex)) {
/* 135 */       return null;
/*     */     }
/* 137 */     return Collections.unmodifiableCollection(getIncoming_internal(vertex));
/*     */   }
/*     */   
/*     */   public Collection<E> getOutEdges(V vertex) {
/* 141 */     if (!containsVertex(vertex)) {
/* 142 */       return null;
/*     */     }
/* 144 */     return Collections.unmodifiableCollection(getOutgoing_internal(vertex));
/*     */   }
/*     */   
/*     */   public Collection<V> getPredecessors(V vertex) {
/* 148 */     if (!containsVertex(vertex)) {
/* 149 */       return null;
/*     */     }
/* 151 */     Set<V> preds = new HashSet();
/* 152 */     for (E edge : getIncoming_internal(vertex)) {
/* 153 */       preds.add(getSource(edge));
/*     */     }
/* 155 */     return Collections.unmodifiableCollection(preds);
/*     */   }
/*     */   
/*     */   public Collection<V> getSuccessors(V vertex) {
/* 159 */     if (!containsVertex(vertex)) {
/* 160 */       return null;
/*     */     }
/* 162 */     Set<V> succs = new HashSet();
/* 163 */     for (E edge : getOutgoing_internal(vertex)) {
/* 164 */       succs.add(getDest(edge));
/*     */     }
/* 166 */     return Collections.unmodifiableCollection(succs);
/*     */   }
/*     */   
/*     */   public Collection<V> getNeighbors(V vertex) {
/* 170 */     if (!containsVertex(vertex)) {
/* 171 */       return null;
/*     */     }
/* 173 */     Collection<V> neighbors = new HashSet();
/* 174 */     for (E edge : getIncoming_internal(vertex))
/* 175 */       neighbors.add(getSource(edge));
/* 176 */     for (E edge : getOutgoing_internal(vertex))
/* 177 */       neighbors.add(getDest(edge));
/* 178 */     return Collections.unmodifiableCollection(neighbors);
/*     */   }
/*     */   
/*     */   public Collection<E> getIncidentEdges(V vertex) {
/* 182 */     if (!containsVertex(vertex)) {
/* 183 */       return null;
/*     */     }
/* 185 */     Collection<E> incident = new HashSet();
/* 186 */     incident.addAll(getIncoming_internal(vertex));
/* 187 */     incident.addAll(getOutgoing_internal(vertex));
/* 188 */     return incident;
/*     */   }
/*     */   
/*     */   public E findEdge(V v1, V v2)
/*     */   {
/* 193 */     for (E edge : getOutgoing_internal(v1)) {
/* 194 */       if (getDest(edge).equals(v2))
/* 195 */         return edge;
/*     */     }
/* 197 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addEdge(E edge, Pair<? extends V> endpoints, EdgeType edgeType)
/*     */   {
/* 203 */     validateEdgeType(edgeType);
/* 204 */     Pair<V> new_endpoints = getValidatedEndpoints(edge, endpoints);
/* 205 */     if (new_endpoints == null) {
/* 206 */       return false;
/*     */     }
/* 208 */     this.edges.put(edge, new_endpoints);
/*     */     
/* 210 */     V source = new_endpoints.getFirst();
/* 211 */     V dest = new_endpoints.getSecond();
/*     */     
/* 213 */     if (!containsVertex(source)) {
/* 214 */       addVertex(source);
/*     */     }
/* 216 */     if (!containsVertex(dest)) {
/* 217 */       addVertex(dest);
/*     */     }
/* 219 */     getIncoming_internal(dest).add(edge);
/* 220 */     getOutgoing_internal(source).add(edge);
/*     */     
/* 222 */     return true;
/*     */   }
/*     */   
/*     */   public V getSource(E edge)
/*     */   {
/* 227 */     if (!containsEdge(edge))
/* 228 */       return null;
/* 229 */     return (V)getEndpoints(edge).getFirst();
/*     */   }
/*     */   
/*     */   public V getDest(E edge) {
/* 233 */     if (!containsEdge(edge))
/* 234 */       return null;
/* 235 */     return (V)getEndpoints(edge).getSecond();
/*     */   }
/*     */   
/*     */   public boolean isSource(V vertex, E edge) {
/* 239 */     if ((!containsEdge(edge)) || (!containsVertex(vertex)))
/* 240 */       return false;
/* 241 */     return vertex.equals(getEndpoints(edge).getFirst());
/*     */   }
/*     */   
/*     */   public boolean isDest(V vertex, E edge) {
/* 245 */     if ((!containsEdge(edge)) || (!containsVertex(vertex)))
/* 246 */       return false;
/* 247 */     return vertex.equals(getEndpoints(edge).getSecond());
/*     */   }
/*     */   
/*     */   public Pair<V> getEndpoints(E edge) {
/* 251 */     return (Pair)this.edges.get(edge);
/*     */   }
/*     */   
/*     */   public int getEdgeCount() {
/* 255 */     return this.edges.size();
/*     */   }
/*     */   
/*     */   public int getVertexCount() {
/* 259 */     return this.vertices.size();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/DirectedSparseMultigraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */