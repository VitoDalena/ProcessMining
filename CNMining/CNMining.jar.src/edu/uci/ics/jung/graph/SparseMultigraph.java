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
/*     */ public class SparseMultigraph<V, E>
/*     */   extends AbstractGraph<V, E>
/*     */   implements MultiGraph<V, E>, Serializable
/*     */ {
/*     */   protected Map<V, Pair<Set<E>>> vertices;
/*     */   protected Map<E, Pair<V>> edges;
/*     */   protected Set<E> directedEdges;
/*     */   
/*     */   public static <V, E> Factory<Graph<V, E>> getFactory()
/*     */   {
/*  42 */     new Factory() {
/*     */       public Graph<V, E> create() {
/*  44 */         return new SparseMultigraph();
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
/*     */ 
/*     */ 
/*     */   public SparseMultigraph()
/*     */   {
/*  60 */     this.vertices = new HashMap();
/*  61 */     this.edges = new HashMap();
/*  62 */     this.directedEdges = new HashSet();
/*     */   }
/*     */   
/*     */   public Collection<E> getEdges()
/*     */   {
/*  67 */     return Collections.unmodifiableCollection(this.edges.keySet());
/*     */   }
/*     */   
/*     */   public Collection<V> getVertices()
/*     */   {
/*  72 */     return Collections.unmodifiableCollection(this.vertices.keySet());
/*     */   }
/*     */   
/*     */   public boolean containsVertex(V vertex) {
/*  76 */     return this.vertices.keySet().contains(vertex);
/*     */   }
/*     */   
/*     */   public boolean containsEdge(E edge) {
/*  80 */     return this.edges.keySet().contains(edge);
/*     */   }
/*     */   
/*     */   protected Collection<E> getIncoming_internal(V vertex)
/*     */   {
/*  85 */     return (Collection)((Pair)this.vertices.get(vertex)).getFirst();
/*     */   }
/*     */   
/*     */   protected Collection<E> getOutgoing_internal(V vertex)
/*     */   {
/*  90 */     return (Collection)((Pair)this.vertices.get(vertex)).getSecond();
/*     */   }
/*     */   
/*     */   public boolean addVertex(V vertex) {
/*  94 */     if (vertex == null) {
/*  95 */       throw new IllegalArgumentException("vertex may not be null");
/*     */     }
/*  97 */     if (!this.vertices.containsKey(vertex)) {
/*  98 */       this.vertices.put(vertex, new Pair(new HashSet(), new HashSet()));
/*  99 */       return true;
/*     */     }
/* 101 */     return false;
/*     */   }
/*     */   
/*     */   public boolean removeVertex(V vertex)
/*     */   {
/* 106 */     if (!containsVertex(vertex)) {
/* 107 */       return false;
/*     */     }
/*     */     
/* 110 */     Set<E> incident = new HashSet(getIncoming_internal(vertex));
/* 111 */     incident.addAll(getOutgoing_internal(vertex));
/*     */     
/* 113 */     for (E edge : incident) {
/* 114 */       removeEdge(edge);
/*     */     }
/* 116 */     this.vertices.remove(vertex);
/*     */     
/* 118 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addEdge(E edge, Pair<? extends V> endpoints, EdgeType edgeType)
/*     */   {
/* 124 */     Pair<V> new_endpoints = getValidatedEndpoints(edge, endpoints);
/* 125 */     if (new_endpoints == null) {
/* 126 */       return false;
/*     */     }
/* 128 */     V v1 = new_endpoints.getFirst();
/* 129 */     V v2 = new_endpoints.getSecond();
/*     */     
/* 131 */     if (!this.vertices.containsKey(v1)) {
/* 132 */       addVertex(v1);
/*     */     }
/* 134 */     if (!this.vertices.containsKey(v2)) {
/* 135 */       addVertex(v2);
/*     */     }
/*     */     
/* 138 */     ((Set)((Pair)this.vertices.get(v1)).getSecond()).add(edge);
/* 139 */     ((Set)((Pair)this.vertices.get(v2)).getFirst()).add(edge);
/* 140 */     this.edges.put(edge, new_endpoints);
/* 141 */     if (edgeType == EdgeType.DIRECTED) {
/* 142 */       this.directedEdges.add(edge);
/*     */     } else {
/* 144 */       ((Set)((Pair)this.vertices.get(v1)).getFirst()).add(edge);
/* 145 */       ((Set)((Pair)this.vertices.get(v2)).getSecond()).add(edge);
/*     */     }
/* 147 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removeEdge(E edge)
/*     */   {
/* 152 */     if (!containsEdge(edge)) {
/* 153 */       return false;
/*     */     }
/*     */     
/* 156 */     Pair<V> endpoints = getEndpoints(edge);
/* 157 */     V v1 = endpoints.getFirst();
/* 158 */     V v2 = endpoints.getSecond();
/*     */     
/*     */ 
/* 161 */     ((Set)((Pair)this.vertices.get(v1)).getSecond()).remove(edge);
/* 162 */     ((Set)((Pair)this.vertices.get(v2)).getFirst()).remove(edge);
/*     */     
/* 164 */     if (!this.directedEdges.remove(edge))
/*     */     {
/*     */ 
/* 167 */       ((Set)((Pair)this.vertices.get(v2)).getSecond()).remove(edge);
/* 168 */       ((Set)((Pair)this.vertices.get(v1)).getFirst()).remove(edge);
/*     */     }
/* 170 */     this.edges.remove(edge);
/* 171 */     return true;
/*     */   }
/*     */   
/*     */   public Collection<E> getInEdges(V vertex)
/*     */   {
/* 176 */     if (!containsVertex(vertex))
/* 177 */       return null;
/* 178 */     return Collections.unmodifiableCollection((Collection)((Pair)this.vertices.get(vertex)).getFirst());
/*     */   }
/*     */   
/*     */   public Collection<E> getOutEdges(V vertex)
/*     */   {
/* 183 */     if (!containsVertex(vertex))
/* 184 */       return null;
/* 185 */     return Collections.unmodifiableCollection((Collection)((Pair)this.vertices.get(vertex)).getSecond());
/*     */   }
/*     */   
/*     */ 
/*     */   public Collection<V> getPredecessors(V vertex)
/*     */   {
/* 191 */     if (!containsVertex(vertex)) {
/* 192 */       return null;
/*     */     }
/* 194 */     Set<V> preds = new HashSet();
/* 195 */     for (E edge : getIncoming_internal(vertex)) {
/* 196 */       if (getEdgeType(edge) == EdgeType.DIRECTED) {
/* 197 */         preds.add(getSource(edge));
/*     */       } else {
/* 199 */         preds.add(getOpposite(vertex, edge));
/*     */       }
/*     */     }
/* 202 */     return Collections.unmodifiableCollection(preds);
/*     */   }
/*     */   
/*     */ 
/*     */   public Collection<V> getSuccessors(V vertex)
/*     */   {
/* 208 */     if (!containsVertex(vertex))
/* 209 */       return null;
/* 210 */     Set<V> succs = new HashSet();
/* 211 */     for (E edge : getOutgoing_internal(vertex)) {
/* 212 */       if (getEdgeType(edge) == EdgeType.DIRECTED) {
/* 213 */         succs.add(getDest(edge));
/*     */       } else {
/* 215 */         succs.add(getOpposite(vertex, edge));
/*     */       }
/*     */     }
/* 218 */     return Collections.unmodifiableCollection(succs);
/*     */   }
/*     */   
/*     */   public Collection<V> getNeighbors(V vertex)
/*     */   {
/* 223 */     if (!containsVertex(vertex))
/* 224 */       return null;
/* 225 */     Collection<V> out = new HashSet();
/* 226 */     out.addAll(getPredecessors(vertex));
/* 227 */     out.addAll(getSuccessors(vertex));
/* 228 */     return out;
/*     */   }
/*     */   
/*     */   public Collection<E> getIncidentEdges(V vertex)
/*     */   {
/* 233 */     if (!containsVertex(vertex))
/* 234 */       return null;
/* 235 */     Collection<E> out = new HashSet();
/* 236 */     out.addAll(getInEdges(vertex));
/* 237 */     out.addAll(getOutEdges(vertex));
/* 238 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */   public E findEdge(V v1, V v2)
/*     */   {
/* 244 */     if ((!containsVertex(v1)) || (!containsVertex(v2)))
/* 245 */       return null;
/* 246 */     for (E edge : getOutgoing_internal(v1)) {
/* 247 */       if (getOpposite(v1, edge).equals(v2))
/* 248 */         return edge;
/*     */     }
/* 250 */     return null;
/*     */   }
/*     */   
/*     */   public Pair<V> getEndpoints(E edge)
/*     */   {
/* 255 */     return (Pair)this.edges.get(edge);
/*     */   }
/*     */   
/*     */   public V getSource(E edge) {
/* 259 */     if (this.directedEdges.contains(edge)) {
/* 260 */       return (V)getEndpoints(edge).getFirst();
/*     */     }
/* 262 */     return null;
/*     */   }
/*     */   
/*     */   public V getDest(E edge) {
/* 266 */     if (this.directedEdges.contains(edge)) {
/* 267 */       return (V)getEndpoints(edge).getSecond();
/*     */     }
/* 269 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isSource(V vertex, E edge) {
/* 273 */     if ((!containsEdge(edge)) || (!containsVertex(vertex)))
/* 274 */       return false;
/* 275 */     return getSource(edge).equals(vertex);
/*     */   }
/*     */   
/*     */   public boolean isDest(V vertex, E edge) {
/* 279 */     if ((!containsEdge(edge)) || (!containsVertex(vertex)))
/* 280 */       return false;
/* 281 */     return getDest(edge).equals(vertex);
/*     */   }
/*     */   
/*     */   public EdgeType getEdgeType(E edge) {
/* 285 */     return this.directedEdges.contains(edge) ? EdgeType.DIRECTED : EdgeType.UNDIRECTED;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<E> getEdges(EdgeType edgeType)
/*     */   {
/* 292 */     if (edgeType == EdgeType.DIRECTED)
/* 293 */       return Collections.unmodifiableSet(this.directedEdges);
/* 294 */     if (edgeType == EdgeType.UNDIRECTED) {
/* 295 */       Collection<E> edges = new HashSet(getEdges());
/* 296 */       edges.removeAll(this.directedEdges);
/* 297 */       return edges;
/*     */     }
/* 299 */     return Collections.EMPTY_SET;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getEdgeCount()
/*     */   {
/* 305 */     return this.edges.keySet().size();
/*     */   }
/*     */   
/*     */   public int getVertexCount() {
/* 309 */     return this.vertices.keySet().size();
/*     */   }
/*     */   
/*     */   public int getEdgeCount(EdgeType edge_type)
/*     */   {
/* 314 */     return getEdges(edge_type).size();
/*     */   }
/*     */   
/*     */   public EdgeType getDefaultEdgeType()
/*     */   {
/* 319 */     return EdgeType.UNDIRECTED;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/SparseMultigraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */