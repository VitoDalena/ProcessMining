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
/*     */ public class SparseGraph<V, E>
/*     */   extends AbstractGraph<V, E>
/*     */   implements Graph<V, E>, Serializable
/*     */ {
/*     */   protected static final int INCOMING = 0;
/*     */   protected static final int OUTGOING = 1;
/*     */   protected static final int INCIDENT = 2;
/*     */   protected Map<V, Map<V, E>[]> vertex_maps;
/*     */   protected Map<E, Pair<V>> directed_edges;
/*     */   protected Map<E, Pair<V>> undirected_edges;
/*     */   
/*     */   public static <V, E> Factory<Graph<V, E>> getFactory()
/*     */   {
/*  43 */     new Factory()
/*     */     {
/*     */       public Graph<V, E> create()
/*     */       {
/*  47 */         return new SparseGraph();
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
/*     */ 
/*     */ 
/*     */   public SparseGraph()
/*     */   {
/*  65 */     this.vertex_maps = new HashMap();
/*  66 */     this.directed_edges = new HashMap();
/*  67 */     this.undirected_edges = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */   public E findEdge(V v1, V v2)
/*     */   {
/*  73 */     if ((!containsVertex(v1)) || (!containsVertex(v2)))
/*  74 */       return null;
/*  75 */     E edge = ((Map[])this.vertex_maps.get(v1))[1].get(v2);
/*  76 */     if (edge == null)
/*  77 */       edge = ((Map[])this.vertex_maps.get(v1))[2].get(v2);
/*  78 */     return edge;
/*     */   }
/*     */   
/*     */ 
/*     */   public Collection<E> findEdgeSet(V v1, V v2)
/*     */   {
/*  84 */     if ((!containsVertex(v1)) || (!containsVertex(v2)))
/*  85 */       return null;
/*  86 */     Collection<E> edges = new ArrayList(2);
/*  87 */     E e1 = ((Map[])this.vertex_maps.get(v1))[1].get(v2);
/*  88 */     if (e1 != null)
/*  89 */       edges.add(e1);
/*  90 */     E e2 = ((Map[])this.vertex_maps.get(v1))[2].get(v2);
/*  91 */     if (e1 != null)
/*  92 */       edges.add(e2);
/*  93 */     return edges;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addEdge(E edge, Pair<? extends V> endpoints, EdgeType edgeType)
/*     */   {
/*  99 */     Pair<V> new_endpoints = getValidatedEndpoints(edge, endpoints);
/* 100 */     if (new_endpoints == null) {
/* 101 */       return false;
/*     */     }
/* 103 */     V v1 = new_endpoints.getFirst();
/* 104 */     V v2 = new_endpoints.getSecond();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 109 */     E connection = findEdge(v1, v2);
/* 110 */     if ((connection != null) && (getEdgeType(connection) == edgeType)) {
/* 111 */       return false;
/*     */     }
/* 113 */     if (!containsVertex(v1)) {
/* 114 */       addVertex(v1);
/*     */     }
/* 116 */     if (!containsVertex(v2)) {
/* 117 */       addVertex(v2);
/*     */     }
/*     */     
/* 120 */     if (edgeType == EdgeType.DIRECTED)
/*     */     {
/* 122 */       ((Map[])this.vertex_maps.get(v1))[1].put(v2, edge);
/* 123 */       ((Map[])this.vertex_maps.get(v2))[0].put(v1, edge);
/* 124 */       this.directed_edges.put(edge, new_endpoints);
/*     */     }
/*     */     else
/*     */     {
/* 128 */       ((Map[])this.vertex_maps.get(v1))[2].put(v2, edge);
/* 129 */       ((Map[])this.vertex_maps.get(v2))[2].put(v1, edge);
/* 130 */       this.undirected_edges.put(edge, new_endpoints);
/*     */     }
/*     */     
/* 133 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<E> getInEdges(V vertex)
/*     */   {
/* 140 */     if (!containsVertex(vertex)) {
/* 141 */       return null;
/*     */     }
/*     */     
/* 144 */     Collection<E> in = new HashSet(((Map[])this.vertex_maps.get(vertex))[0].values());
/* 145 */     in.addAll(((Map[])this.vertex_maps.get(vertex))[2].values());
/* 146 */     return Collections.unmodifiableCollection(in);
/*     */   }
/*     */   
/*     */   public Collection<E> getOutEdges(V vertex)
/*     */   {
/* 151 */     if (!containsVertex(vertex)) {
/* 152 */       return null;
/*     */     }
/*     */     
/* 155 */     Collection<E> out = new HashSet(((Map[])this.vertex_maps.get(vertex))[1].values());
/* 156 */     out.addAll(((Map[])this.vertex_maps.get(vertex))[2].values());
/* 157 */     return Collections.unmodifiableCollection(out);
/*     */   }
/*     */   
/*     */   public Collection<V> getPredecessors(V vertex)
/*     */   {
/* 162 */     if (!containsVertex(vertex)) {
/* 163 */       return null;
/*     */     }
/*     */     
/* 166 */     Collection<V> preds = new HashSet(((Map[])this.vertex_maps.get(vertex))[0].keySet());
/* 167 */     preds.addAll(((Map[])this.vertex_maps.get(vertex))[2].keySet());
/* 168 */     return Collections.unmodifiableCollection(preds);
/*     */   }
/*     */   
/*     */   public Collection<V> getSuccessors(V vertex)
/*     */   {
/* 173 */     if (!containsVertex(vertex)) {
/* 174 */       return null;
/*     */     }
/*     */     
/* 177 */     Collection<V> succs = new HashSet(((Map[])this.vertex_maps.get(vertex))[1].keySet());
/* 178 */     succs.addAll(((Map[])this.vertex_maps.get(vertex))[2].keySet());
/* 179 */     return Collections.unmodifiableCollection(succs);
/*     */   }
/*     */   
/*     */   public Collection<E> getEdges(EdgeType edgeType)
/*     */   {
/* 184 */     if (edgeType == EdgeType.DIRECTED)
/* 185 */       return Collections.unmodifiableCollection(this.directed_edges.keySet());
/* 186 */     if (edgeType == EdgeType.UNDIRECTED) {
/* 187 */       return Collections.unmodifiableCollection(this.undirected_edges.keySet());
/*     */     }
/* 189 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public Pair<V> getEndpoints(E edge)
/*     */   {
/* 195 */     Pair<V> endpoints = (Pair)this.directed_edges.get(edge);
/* 196 */     if (endpoints == null) {
/* 197 */       return (Pair)this.undirected_edges.get(edge);
/*     */     }
/* 199 */     return endpoints;
/*     */   }
/*     */   
/*     */   public EdgeType getEdgeType(E edge)
/*     */   {
/* 204 */     if (this.directed_edges.containsKey(edge))
/* 205 */       return EdgeType.DIRECTED;
/* 206 */     if (this.undirected_edges.containsKey(edge)) {
/* 207 */       return EdgeType.UNDIRECTED;
/*     */     }
/* 209 */     return null;
/*     */   }
/*     */   
/*     */   public V getSource(E directed_edge)
/*     */   {
/* 214 */     if (getEdgeType(directed_edge) == EdgeType.DIRECTED) {
/* 215 */       return (V)((Pair)this.directed_edges.get(directed_edge)).getFirst();
/*     */     }
/* 217 */     return null;
/*     */   }
/*     */   
/*     */   public V getDest(E directed_edge)
/*     */   {
/* 222 */     if (getEdgeType(directed_edge) == EdgeType.DIRECTED) {
/* 223 */       return (V)((Pair)this.directed_edges.get(directed_edge)).getSecond();
/*     */     }
/* 225 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isSource(V vertex, E edge)
/*     */   {
/* 230 */     if ((!containsVertex(vertex)) || (!containsEdge(edge))) {
/* 231 */       return false;
/*     */     }
/* 233 */     V source = getSource(edge);
/* 234 */     if (source != null) {
/* 235 */       return source.equals(vertex);
/*     */     }
/* 237 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isDest(V vertex, E edge)
/*     */   {
/* 242 */     if ((!containsVertex(vertex)) || (!containsEdge(edge))) {
/* 243 */       return false;
/*     */     }
/* 245 */     V dest = getDest(edge);
/* 246 */     if (dest != null) {
/* 247 */       return dest.equals(vertex);
/*     */     }
/* 249 */     return false;
/*     */   }
/*     */   
/*     */   public Collection<E> getEdges()
/*     */   {
/* 254 */     Collection<E> edges = new ArrayList(this.directed_edges.keySet());
/* 255 */     edges.addAll(this.undirected_edges.keySet());
/* 256 */     return Collections.unmodifiableCollection(edges);
/*     */   }
/*     */   
/*     */   public Collection<V> getVertices()
/*     */   {
/* 261 */     return Collections.unmodifiableCollection(this.vertex_maps.keySet());
/*     */   }
/*     */   
/*     */   public boolean containsVertex(V vertex)
/*     */   {
/* 266 */     return this.vertex_maps.containsKey(vertex);
/*     */   }
/*     */   
/*     */   public boolean containsEdge(E edge)
/*     */   {
/* 271 */     return (this.directed_edges.containsKey(edge)) || (this.undirected_edges.containsKey(edge));
/*     */   }
/*     */   
/*     */   public int getEdgeCount()
/*     */   {
/* 276 */     return this.directed_edges.size() + this.undirected_edges.size();
/*     */   }
/*     */   
/*     */   public int getVertexCount()
/*     */   {
/* 281 */     return this.vertex_maps.size();
/*     */   }
/*     */   
/*     */   public Collection<V> getNeighbors(V vertex)
/*     */   {
/* 286 */     if (!containsVertex(vertex)) {
/* 287 */       return null;
/*     */     }
/* 289 */     Collection<V> neighbors = new HashSet(((Map[])this.vertex_maps.get(vertex))[0].keySet());
/* 290 */     neighbors.addAll(((Map[])this.vertex_maps.get(vertex))[1].keySet());
/* 291 */     neighbors.addAll(((Map[])this.vertex_maps.get(vertex))[2].keySet());
/* 292 */     return Collections.unmodifiableCollection(neighbors);
/*     */   }
/*     */   
/*     */   public Collection<E> getIncidentEdges(V vertex)
/*     */   {
/* 297 */     if (!containsVertex(vertex))
/* 298 */       return null;
/* 299 */     Collection<E> incident = new HashSet(((Map[])this.vertex_maps.get(vertex))[0].values());
/* 300 */     incident.addAll(((Map[])this.vertex_maps.get(vertex))[1].values());
/* 301 */     incident.addAll(((Map[])this.vertex_maps.get(vertex))[2].values());
/* 302 */     return Collections.unmodifiableCollection(incident);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addVertex(V vertex)
/*     */   {
/* 308 */     if (vertex == null) {
/* 309 */       throw new IllegalArgumentException("vertex may not be null");
/*     */     }
/* 311 */     if (!containsVertex(vertex)) {
/* 312 */       this.vertex_maps.put(vertex, new HashMap[] { new HashMap(), new HashMap(), new HashMap() });
/* 313 */       return true;
/*     */     }
/* 315 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean removeVertex(V vertex)
/*     */   {
/* 321 */     if (!containsVertex(vertex)) {
/* 322 */       return false;
/*     */     }
/*     */     
/* 325 */     Collection<E> incident = new ArrayList(getIncidentEdges(vertex));
/*     */     
/* 327 */     for (E edge : incident) {
/* 328 */       removeEdge(edge);
/*     */     }
/* 330 */     this.vertex_maps.remove(vertex);
/*     */     
/* 332 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removeEdge(E edge)
/*     */   {
/* 337 */     if (!containsEdge(edge)) {
/* 338 */       return false;
/*     */     }
/* 340 */     Pair<V> endpoints = getEndpoints(edge);
/* 341 */     V v1 = endpoints.getFirst();
/* 342 */     V v2 = endpoints.getSecond();
/*     */     
/*     */ 
/* 345 */     if (getEdgeType(edge) == EdgeType.DIRECTED)
/*     */     {
/* 347 */       ((Map[])this.vertex_maps.get(v1))[1].remove(v2);
/* 348 */       ((Map[])this.vertex_maps.get(v2))[0].remove(v1);
/* 349 */       this.directed_edges.remove(edge);
/*     */     }
/*     */     else
/*     */     {
/* 353 */       ((Map[])this.vertex_maps.get(v1))[2].remove(v2);
/* 354 */       ((Map[])this.vertex_maps.get(v2))[2].remove(v1);
/* 355 */       this.undirected_edges.remove(edge);
/*     */     }
/*     */     
/* 358 */     return true;
/*     */   }
/*     */   
/*     */   public int getEdgeCount(EdgeType edge_type)
/*     */   {
/* 363 */     if (edge_type == EdgeType.DIRECTED)
/* 364 */       return this.directed_edges.size();
/* 365 */     if (edge_type == EdgeType.UNDIRECTED)
/* 366 */       return this.undirected_edges.size();
/* 367 */     return 0;
/*     */   }
/*     */   
/*     */   public EdgeType getDefaultEdgeType()
/*     */   {
/* 372 */     return EdgeType.UNDIRECTED;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/SparseGraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */