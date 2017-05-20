/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
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
/*     */ public class SetHypergraph<V, H>
/*     */   implements Hypergraph<V, H>, MultiGraph<V, H>, Serializable
/*     */ {
/*     */   protected Map<V, Set<H>> vertices;
/*     */   protected Map<H, Set<V>> edges;
/*     */   
/*     */   public static <V, H> Factory<Hypergraph<V, H>> getFactory()
/*     */   {
/*  45 */     new Factory() {
/*     */       public Hypergraph<V, H> create() {
/*  47 */         return new SetHypergraph();
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SetHypergraph()
/*     */   {
/*  57 */     this.vertices = new HashMap();
/*  58 */     this.edges = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addEdge(H hyperedge, Collection<? extends V> to_attach)
/*     */   {
/*  70 */     if (hyperedge == null) {
/*  71 */       throw new IllegalArgumentException("input hyperedge may not be null");
/*     */     }
/*  73 */     if (to_attach == null) {
/*  74 */       throw new IllegalArgumentException("endpoints may not be null");
/*     */     }
/*  76 */     if (to_attach.contains(null)) {
/*  77 */       throw new IllegalArgumentException("cannot add an edge with a null endpoint");
/*     */     }
/*  79 */     Set<V> new_endpoints = new HashSet(to_attach);
/*  80 */     if (this.edges.containsKey(hyperedge))
/*     */     {
/*  82 */       Collection<V> attached = (Collection)this.edges.get(hyperedge);
/*  83 */       if (!attached.equals(new_endpoints))
/*     */       {
/*  85 */         throw new IllegalArgumentException("Edge " + hyperedge + " exists in this graph with endpoints " + attached);
/*     */       }
/*     */       
/*     */ 
/*  89 */       return false;
/*     */     }
/*  91 */     this.edges.put(hyperedge, new_endpoints);
/*  92 */     for (V v : to_attach)
/*     */     {
/*     */ 
/*  95 */       addVertex(v);
/*     */       
/*     */ 
/*  98 */       ((Set)this.vertices.get(v)).add(hyperedge);
/*     */     }
/* 100 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean addEdge(H hyperedge, Collection<? extends V> to_attach, EdgeType edge_type)
/*     */   {
/* 109 */     if (edge_type != EdgeType.UNDIRECTED) {
/* 110 */       throw new IllegalArgumentException("Edge type for this implementation must be EdgeType.HYPER, not " + edge_type);
/*     */     }
/*     */     
/* 113 */     return addEdge(hyperedge, to_attach);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public EdgeType getEdgeType(H edge)
/*     */   {
/* 121 */     if (containsEdge(edge)) {
/* 122 */       return EdgeType.UNDIRECTED;
/*     */     }
/* 124 */     return null;
/*     */   }
/*     */   
/*     */   public boolean containsVertex(V vertex) {
/* 128 */     return this.vertices.keySet().contains(vertex);
/*     */   }
/*     */   
/*     */   public boolean containsEdge(H edge) {
/* 132 */     return this.edges.keySet().contains(edge);
/*     */   }
/*     */   
/*     */   public Collection<H> getEdges()
/*     */   {
/* 137 */     return this.edges.keySet();
/*     */   }
/*     */   
/*     */   public Collection<V> getVertices()
/*     */   {
/* 142 */     return this.vertices.keySet();
/*     */   }
/*     */   
/*     */   public int getEdgeCount()
/*     */   {
/* 147 */     return this.edges.size();
/*     */   }
/*     */   
/*     */   public int getVertexCount()
/*     */   {
/* 152 */     return this.vertices.size();
/*     */   }
/*     */   
/*     */   public Collection<V> getNeighbors(V vertex)
/*     */   {
/* 157 */     if (!containsVertex(vertex)) {
/* 158 */       return null;
/*     */     }
/* 160 */     Set<V> neighbors = new HashSet();
/* 161 */     for (H hyperedge : (Set)this.vertices.get(vertex))
/*     */     {
/* 163 */       neighbors.addAll((Collection)this.edges.get(hyperedge));
/*     */     }
/* 165 */     return neighbors;
/*     */   }
/*     */   
/*     */   public Collection<H> getIncidentEdges(V vertex)
/*     */   {
/* 170 */     return (Collection)this.vertices.get(vertex);
/*     */   }
/*     */   
/*     */   public Collection<V> getIncidentVertices(H edge)
/*     */   {
/* 175 */     return (Collection)this.edges.get(edge);
/*     */   }
/*     */   
/*     */   public H findEdge(V v1, V v2)
/*     */   {
/* 180 */     if ((!containsVertex(v1)) || (!containsVertex(v2))) {
/* 181 */       return null;
/*     */     }
/* 183 */     for (H h : getIncidentEdges(v1))
/*     */     {
/* 185 */       if (isIncident(v2, h))
/* 186 */         return h;
/*     */     }
/* 188 */     return null;
/*     */   }
/*     */   
/*     */   public Collection<H> findEdgeSet(V v1, V v2)
/*     */   {
/* 193 */     if ((!containsVertex(v1)) || (!containsVertex(v2))) {
/* 194 */       return null;
/*     */     }
/* 196 */     Collection<H> edges = new ArrayList();
/* 197 */     for (H h : getIncidentEdges(v1))
/*     */     {
/* 199 */       if (isIncident(v2, h))
/* 200 */         edges.add(h);
/*     */     }
/* 202 */     return Collections.unmodifiableCollection(edges);
/*     */   }
/*     */   
/*     */   public boolean addVertex(V vertex)
/*     */   {
/* 207 */     if (vertex == null)
/* 208 */       throw new IllegalArgumentException("cannot add a null vertex");
/* 209 */     if (containsVertex(vertex))
/* 210 */       return false;
/* 211 */     this.vertices.put(vertex, new HashSet());
/* 212 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removeVertex(V vertex)
/*     */   {
/* 217 */     if (!containsVertex(vertex))
/* 218 */       return false;
/* 219 */     for (H hyperedge : (Set)this.vertices.get(vertex))
/*     */     {
/* 221 */       ((Set)this.edges.get(hyperedge)).remove(vertex);
/*     */     }
/* 223 */     this.vertices.remove(vertex);
/* 224 */     return true;
/*     */   }
/*     */   
/*     */   public boolean removeEdge(H hyperedge)
/*     */   {
/* 229 */     if (!containsEdge(hyperedge))
/* 230 */       return false;
/* 231 */     for (V vertex : (Set)this.edges.get(hyperedge))
/*     */     {
/* 233 */       ((Set)this.vertices.get(vertex)).remove(hyperedge);
/*     */     }
/* 235 */     this.edges.remove(hyperedge);
/* 236 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isNeighbor(V v1, V v2)
/*     */   {
/* 241 */     if ((!containsVertex(v1)) || (!containsVertex(v2))) {
/* 242 */       return false;
/*     */     }
/* 244 */     if (((Set)this.vertices.get(v2)).isEmpty())
/* 245 */       return false;
/* 246 */     for (H hyperedge : (Set)this.vertices.get(v1))
/*     */     {
/* 248 */       if (((Set)this.edges.get(hyperedge)).contains(v2))
/* 249 */         return true;
/*     */     }
/* 251 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isIncident(V vertex, H edge)
/*     */   {
/* 256 */     if ((!containsVertex(vertex)) || (!containsEdge(edge))) {
/* 257 */       return false;
/*     */     }
/* 259 */     return ((Set)this.vertices.get(vertex)).contains(edge);
/*     */   }
/*     */   
/*     */   public int degree(V vertex)
/*     */   {
/* 264 */     if (!containsVertex(vertex)) {
/* 265 */       return 0;
/*     */     }
/* 267 */     return ((Set)this.vertices.get(vertex)).size();
/*     */   }
/*     */   
/*     */   public int getNeighborCount(V vertex)
/*     */   {
/* 272 */     if (!containsVertex(vertex)) {
/* 273 */       return 0;
/*     */     }
/* 275 */     return getNeighbors(vertex).size();
/*     */   }
/*     */   
/*     */   public int getIncidentCount(H edge)
/*     */   {
/* 280 */     if (!containsEdge(edge)) {
/* 281 */       return 0;
/*     */     }
/* 283 */     return ((Set)this.edges.get(edge)).size();
/*     */   }
/*     */   
/*     */   public int getEdgeCount(EdgeType edge_type)
/*     */   {
/* 288 */     if (edge_type == EdgeType.UNDIRECTED)
/* 289 */       return this.edges.size();
/* 290 */     return 0;
/*     */   }
/*     */   
/*     */   public Collection<H> getEdges(EdgeType edge_type)
/*     */   {
/* 295 */     if (edge_type == EdgeType.UNDIRECTED)
/* 296 */       return this.edges.keySet();
/* 297 */     return null;
/*     */   }
/*     */   
/*     */   public EdgeType getDefaultEdgeType()
/*     */   {
/* 302 */     return EdgeType.UNDIRECTED;
/*     */   }
/*     */   
/*     */   public Collection<H> getInEdges(V vertex)
/*     */   {
/* 307 */     return getIncidentEdges(vertex);
/*     */   }
/*     */   
/*     */   public Collection<H> getOutEdges(V vertex)
/*     */   {
/* 312 */     return getIncidentEdges(vertex);
/*     */   }
/*     */   
/*     */   public int inDegree(V vertex)
/*     */   {
/* 317 */     return degree(vertex);
/*     */   }
/*     */   
/*     */   public int outDegree(V vertex)
/*     */   {
/* 322 */     return degree(vertex);
/*     */   }
/*     */   
/*     */   public V getDest(H directed_edge)
/*     */   {
/* 327 */     return null;
/*     */   }
/*     */   
/*     */   public V getSource(H directed_edge)
/*     */   {
/* 332 */     return null;
/*     */   }
/*     */   
/*     */   public Collection<V> getPredecessors(V vertex)
/*     */   {
/* 337 */     return getNeighbors(vertex);
/*     */   }
/*     */   
/*     */   public Collection<V> getSuccessors(V vertex)
/*     */   {
/* 342 */     return getNeighbors(vertex);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/SetHypergraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */