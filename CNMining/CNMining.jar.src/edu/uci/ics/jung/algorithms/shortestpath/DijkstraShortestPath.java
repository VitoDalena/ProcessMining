/*     */ package edu.uci.ics.jung.algorithms.shortestpath;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Transformer;
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
/*     */ public class DijkstraShortestPath<V, E>
/*     */   extends DijkstraDistance<V, E>
/*     */   implements ShortestPath<V, E>
/*     */ {
/*     */   public DijkstraShortestPath(Graph<V, E> g, Transformer<E, Number> nev, boolean cached)
/*     */   {
/*  52 */     super(g, nev, cached);
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
/*     */   public DijkstraShortestPath(Graph<V, E> g, Transformer<E, Number> nev)
/*     */   {
/*  65 */     super(g, nev);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DijkstraShortestPath(Graph<V, E> g)
/*     */   {
/*  77 */     super(g);
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
/*     */   public DijkstraShortestPath(Graph<V, E> g, boolean cached)
/*     */   {
/*  90 */     super(g, cached);
/*     */   }
/*     */   
/*     */ 
/*     */   protected DijkstraDistance<V, E>.SourceData getSourceData(V source)
/*     */   {
/*  96 */     DijkstraDistance<V, E>.SourceData sd = (DijkstraDistance.SourceData)this.sourceMap.get(source);
/*  97 */     if (sd == null)
/*  98 */       sd = new SourcePathData(source);
/*  99 */     return sd;
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
/*     */   public E getIncomingEdge(V source, V target)
/*     */   {
/* 112 */     if (!this.g.containsVertex(source)) {
/* 113 */       throw new IllegalArgumentException("Specified source vertex " + source + " is not part of graph " + this.g);
/*     */     }
/*     */     
/* 116 */     if (!this.g.containsVertex(target)) {
/* 117 */       throw new IllegalArgumentException("Specified target vertex " + target + " is not part of graph " + this.g);
/*     */     }
/*     */     
/* 120 */     Set<V> targets = new HashSet();
/* 121 */     targets.add(target);
/* 122 */     singleSourceShortestPath(source, targets, this.g.getVertexCount());
/* 123 */     Map<V, E> incomingEdgeMap = ((SourcePathData)this.sourceMap.get(source)).incomingEdges;
/*     */     
/* 125 */     E incomingEdge = incomingEdgeMap.get(target);
/*     */     
/* 127 */     if (!this.cached) {
/* 128 */       reset(source);
/*     */     }
/* 130 */     return incomingEdge;
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
/*     */ 
/*     */   public Map<V, E> getIncomingEdgeMap(V source)
/*     */   {
/* 147 */     return getIncomingEdgeMap(source, this.g.getVertexCount());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<E> getPath(V source, V target)
/*     */   {
/* 159 */     if (!this.g.containsVertex(source)) {
/* 160 */       throw new IllegalArgumentException("Specified source vertex " + source + " is not part of graph " + this.g);
/*     */     }
/*     */     
/* 163 */     if (!this.g.containsVertex(target)) {
/* 164 */       throw new IllegalArgumentException("Specified target vertex " + target + " is not part of graph " + this.g);
/*     */     }
/*     */     
/* 167 */     LinkedList<E> path = new LinkedList();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 172 */     Set<V> targets = new HashSet();
/* 173 */     targets.add(target);
/* 174 */     singleSourceShortestPath(source, targets, this.g.getVertexCount());
/* 175 */     Map<V, E> incomingEdges = ((SourcePathData)this.sourceMap.get(source)).incomingEdges;
/*     */     
/*     */ 
/* 178 */     if ((incomingEdges.isEmpty()) || (incomingEdges.get(target) == null))
/* 179 */       return path;
/* 180 */     V current = target;
/* 181 */     while (!current.equals(source))
/*     */     {
/* 183 */       E incoming = incomingEdges.get(current);
/* 184 */       path.addFirst(incoming);
/* 185 */       current = ((Graph)this.g).getOpposite(current, incoming);
/*     */     }
/* 187 */     return path;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LinkedHashMap<V, E> getIncomingEdgeMap(V source, int numDests)
/*     */   {
/* 208 */     if (!this.g.getVertices().contains(source)) {
/* 209 */       throw new IllegalArgumentException("Specified source vertex " + source + " is not part of graph " + this.g);
/*     */     }
/*     */     
/* 212 */     if ((numDests < 1) || (numDests > this.g.getVertexCount())) {
/* 213 */       throw new IllegalArgumentException("numDests must be >= 1 and <= g.numVertices()");
/*     */     }
/*     */     
/* 216 */     singleSourceShortestPath(source, null, numDests);
/*     */     
/* 218 */     LinkedHashMap<V, E> incomingEdgeMap = ((SourcePathData)this.sourceMap.get(source)).incomingEdges;
/*     */     
/*     */ 
/* 221 */     if (!this.cached) {
/* 222 */       reset(source);
/*     */     }
/* 224 */     return incomingEdgeMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected class SourcePathData
/*     */     extends DijkstraDistance.SourceData
/*     */   {
/*     */     protected Map<V, E> tentativeIncomingEdges;
/*     */     
/*     */ 
/*     */ 
/*     */     protected LinkedHashMap<V, E> incomingEdges;
/*     */     
/*     */ 
/*     */ 
/*     */     protected SourcePathData()
/*     */     {
/* 243 */       super(source);
/* 244 */       this.incomingEdges = new LinkedHashMap();
/* 245 */       this.tentativeIncomingEdges = new HashMap();
/*     */     }
/*     */     
/*     */ 
/*     */     public void update(V dest, E tentative_edge, double new_dist)
/*     */     {
/* 251 */       super.update(dest, tentative_edge, new_dist);
/* 252 */       this.tentativeIncomingEdges.put(dest, tentative_edge);
/*     */     }
/*     */     
/*     */ 
/*     */     public Map.Entry<V, Number> getNextVertex()
/*     */     {
/* 258 */       Map.Entry<V, Number> p = super.getNextVertex();
/* 259 */       V v = p.getKey();
/* 260 */       E incoming = this.tentativeIncomingEdges.remove(v);
/* 261 */       this.incomingEdges.put(v, incoming);
/* 262 */       return p;
/*     */     }
/*     */     
/*     */ 
/*     */     public void createRecord(V w, E e, double new_dist)
/*     */     {
/* 268 */       super.createRecord(w, e, new_dist);
/* 269 */       this.tentativeIncomingEdges.put(w, e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/shortestpath/DijkstraShortestPath.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */