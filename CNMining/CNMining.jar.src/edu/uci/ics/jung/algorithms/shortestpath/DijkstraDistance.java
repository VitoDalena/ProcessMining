/*     */ package edu.uci.ics.jung.algorithms.shortestpath;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.util.BasicMapEntry;
/*     */ import edu.uci.ics.jung.algorithms.util.MapBinaryHeap;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.functors.ConstantTransformer;
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
/*     */ public class DijkstraDistance<V, E>
/*     */   implements Distance<V>
/*     */ {
/*     */   protected Hypergraph<V, E> g;
/*     */   protected Transformer<E, ? extends Number> nev;
/*     */   protected Map<V, DijkstraDistance<V, E>.SourceData> sourceMap;
/*     */   protected boolean cached;
/*     */   protected double max_distance;
/*     */   protected int max_targets;
/*     */   
/*     */   public DijkstraDistance(Hypergraph<V, E> g, Transformer<E, ? extends Number> nev, boolean cached)
/*     */   {
/*  85 */     this.g = g;
/*  86 */     this.nev = nev;
/*  87 */     this.sourceMap = new HashMap();
/*  88 */     this.cached = cached;
/*  89 */     this.max_distance = Double.POSITIVE_INFINITY;
/*  90 */     this.max_targets = Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DijkstraDistance(Hypergraph<V, E> g, Transformer<E, ? extends Number> nev)
/*     */   {
/* 102 */     this(g, nev, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DijkstraDistance(Graph<V, E> g)
/*     */   {
/* 114 */     this(g, new ConstantTransformer(Integer.valueOf(1)), true);
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
/*     */   public DijkstraDistance(Graph<V, E> g, boolean cached)
/*     */   {
/* 127 */     this(g, new ConstantTransformer(Integer.valueOf(1)), cached);
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
/*     */ 
/*     */ 
/*     */   protected LinkedHashMap<V, Number> singleSourceShortestPath(V source, Collection<V> targets, int numDests)
/*     */   {
/* 150 */     DijkstraDistance<V, E>.SourceData sd = getSourceData(source);
/*     */     
/* 152 */     Set<V> to_get = new HashSet();
/* 153 */     Set<V> existing_dists; if (targets != null) {
/* 154 */       to_get.addAll(targets);
/* 155 */       existing_dists = sd.distances.keySet();
/* 156 */       for (V o : targets) {
/* 157 */         if (existing_dists.contains(o)) {
/* 158 */           to_get.remove(o);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 165 */     if ((sd.reached_max) || ((targets != null) && (to_get.isEmpty())) || (sd.distances.size() >= numDests))
/*     */     {
/*     */ 
/*     */ 
/* 169 */       return sd.distances; }
/*     */     double v_dist;
/*     */     Iterator i$;
/* 172 */     E e; while ((!sd.unknownVertices.isEmpty()) && ((sd.distances.size() < numDests) || (!to_get.isEmpty())))
/*     */     {
/* 174 */       Map.Entry<V, Number> p = sd.getNextVertex();
/* 175 */       V v = p.getKey();
/* 176 */       v_dist = ((Double)p.getValue()).doubleValue();
/* 177 */       sd.dist_reached = v_dist;
/* 178 */       to_get.remove(v);
/* 179 */       if ((sd.dist_reached >= this.max_distance) || (sd.distances.size() >= this.max_targets))
/*     */       {
/* 181 */         sd.reached_max = true;
/* 182 */         break;
/*     */       }
/*     */       
/* 185 */       for (i$ = getEdgesToCheck(v).iterator(); i$.hasNext();) { e = i$.next();
/*     */         
/* 187 */         for (V w : this.g.getIncidentVertices(e))
/*     */         {
/* 189 */           if (!sd.distances.containsKey(w))
/*     */           {
/* 191 */             double edge_weight = ((Number)this.nev.transform(e)).doubleValue();
/* 192 */             if (edge_weight < 0.0D)
/* 193 */               throw new IllegalArgumentException("Edges weights must be non-negative");
/* 194 */             double new_dist = v_dist + edge_weight;
/* 195 */             if (!sd.estimatedDistances.containsKey(w))
/*     */             {
/* 197 */               sd.createRecord(w, e, new_dist);
/*     */             }
/*     */             else
/*     */             {
/* 201 */               double w_dist = ((Double)sd.estimatedDistances.get(w)).doubleValue();
/* 202 */               if (new_dist < w_dist) {
/* 203 */                 sd.update(w, e, new_dist);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 213 */     return sd.distances;
/*     */   }
/*     */   
/*     */   protected DijkstraDistance<V, E>.SourceData getSourceData(V source)
/*     */   {
/* 218 */     DijkstraDistance<V, E>.SourceData sd = (SourceData)this.sourceMap.get(source);
/* 219 */     if (sd == null)
/* 220 */       sd = new SourceData(source);
/* 221 */     return sd;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Collection<E> getEdgesToCheck(V v)
/*     */   {
/* 232 */     if ((this.g instanceof Graph)) {
/* 233 */       return ((Graph)this.g).getOutEdges(v);
/*     */     }
/* 235 */     return this.g.getIncidentEdges(v);
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
/*     */   public Number getDistance(V source, V target)
/*     */   {
/* 251 */     if (!this.g.containsVertex(target)) {
/* 252 */       throw new IllegalArgumentException("Specified target vertex " + target + " is not part of graph " + this.g);
/*     */     }
/* 254 */     if (!this.g.containsVertex(source)) {
/* 255 */       throw new IllegalArgumentException("Specified source vertex " + source + " is not part of graph " + this.g);
/*     */     }
/*     */     
/* 258 */     Set<V> targets = new HashSet();
/* 259 */     targets.add(target);
/* 260 */     Map<V, Number> distanceMap = getDistanceMap(source, targets);
/* 261 */     return (Number)distanceMap.get(target);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<V, Number> getDistanceMap(V source, Collection<V> targets)
/*     */   {
/* 271 */     if (!this.g.containsVertex(source)) {
/* 272 */       throw new IllegalArgumentException("Specified source vertex " + source + " is not part of graph " + this.g);
/*     */     }
/* 274 */     if (targets.size() > this.max_targets) {
/* 275 */       throw new IllegalArgumentException("size of target set exceeds maximum number of targets allowed: " + this.max_targets);
/*     */     }
/*     */     
/* 278 */     Map<V, Number> distanceMap = singleSourceShortestPath(source, targets, Math.min(this.g.getVertexCount(), this.max_targets));
/*     */     
/*     */ 
/* 281 */     if (!this.cached) {
/* 282 */       reset(source);
/*     */     }
/* 284 */     return distanceMap;
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
/*     */   public Map<V, Number> getDistanceMap(V source)
/*     */   {
/* 303 */     return getDistanceMap(source, Math.min(this.g.getVertexCount(), this.max_targets));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LinkedHashMap<V, Number> getDistanceMap(V source, int numDests)
/*     */   {
/* 330 */     if (!this.g.getVertices().contains(source)) {
/* 331 */       throw new IllegalArgumentException("Specified source vertex " + source + " is not part of graph " + this.g);
/*     */     }
/*     */     
/*     */ 
/* 335 */     if ((numDests < 1) || (numDests > this.g.getVertexCount())) {
/* 336 */       throw new IllegalArgumentException("numDests must be >= 1 and <= g.numVertices()");
/*     */     }
/*     */     
/* 339 */     if (numDests > this.max_targets) {
/* 340 */       throw new IllegalArgumentException("numDests must be <= the maximum number of targets allowed: " + this.max_targets);
/*     */     }
/*     */     
/* 343 */     LinkedHashMap<V, Number> distanceMap = singleSourceShortestPath(source, null, numDests);
/*     */     
/*     */ 
/* 346 */     if (!this.cached) {
/* 347 */       reset(source);
/*     */     }
/* 349 */     return distanceMap;
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
/*     */   public void setMaxDistance(double max_dist)
/*     */   {
/* 369 */     this.max_distance = max_dist;
/* 370 */     for (V v : this.sourceMap.keySet())
/*     */     {
/* 372 */       DijkstraDistance<V, E>.SourceData sd = (SourceData)this.sourceMap.get(v);
/* 373 */       sd.reached_max = ((this.max_distance <= sd.dist_reached) || (sd.distances.size() >= this.max_targets));
/*     */     }
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
/*     */   public void setMaxTargets(int max_targets)
/*     */   {
/* 395 */     this.max_targets = max_targets;
/* 396 */     for (V v : this.sourceMap.keySet())
/*     */     {
/* 398 */       DijkstraDistance<V, E>.SourceData sd = (SourceData)this.sourceMap.get(v);
/* 399 */       sd.reached_max = ((this.max_distance <= sd.dist_reached) || (sd.distances.size() >= max_targets));
/*     */     }
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
/*     */   public void reset()
/*     */   {
/* 414 */     this.sourceMap = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void enableCaching(boolean enable)
/*     */   {
/* 426 */     this.cached = enable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset(V source)
/*     */   {
/* 438 */     this.sourceMap.put(source, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static class VertexComparator<V>
/*     */     implements Comparator<V>
/*     */   {
/*     */     private Map<V, Number> distances;
/*     */     
/*     */ 
/*     */     protected VertexComparator(Map<V, Number> distances)
/*     */     {
/* 451 */       this.distances = distances;
/*     */     }
/*     */     
/*     */     public int compare(V o1, V o2)
/*     */     {
/* 456 */       return ((Double)this.distances.get(o1)).compareTo((Double)this.distances.get(o2));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected class SourceData
/*     */   {
/*     */     protected LinkedHashMap<V, Number> distances;
/*     */     
/*     */ 
/*     */     protected Map<V, Number> estimatedDistances;
/*     */     
/*     */ 
/*     */     protected MapBinaryHeap<V> unknownVertices;
/*     */     
/*     */ 
/* 473 */     protected boolean reached_max = false;
/* 474 */     protected double dist_reached = 0.0D;
/*     */     
/*     */     protected SourceData()
/*     */     {
/* 478 */       this.distances = new LinkedHashMap();
/* 479 */       this.estimatedDistances = new HashMap();
/* 480 */       this.unknownVertices = new MapBinaryHeap(new DijkstraDistance.VertexComparator(this.estimatedDistances));
/*     */       
/* 482 */       DijkstraDistance.this.sourceMap.put(source, this);
/*     */       
/*     */ 
/* 485 */       this.estimatedDistances.put(source, new Double(0.0D));
/* 486 */       this.unknownVertices.add(source);
/* 487 */       this.reached_max = false;
/* 488 */       this.dist_reached = 0.0D;
/*     */     }
/*     */     
/*     */     protected Map.Entry<V, Number> getNextVertex()
/*     */     {
/* 493 */       V v = this.unknownVertices.remove();
/* 494 */       Double dist = (Double)this.estimatedDistances.remove(v);
/* 495 */       this.distances.put(v, dist);
/* 496 */       return new BasicMapEntry(v, dist);
/*     */     }
/*     */     
/*     */     protected void update(V dest, E tentative_edge, double new_dist)
/*     */     {
/* 501 */       this.estimatedDistances.put(dest, new Double(new_dist));
/* 502 */       this.unknownVertices.update(dest);
/*     */     }
/*     */     
/*     */     protected void createRecord(V w, E e, double new_dist)
/*     */     {
/* 507 */       this.estimatedDistances.put(w, new Double(new_dist));
/* 508 */       this.unknownVertices.add(w);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/shortestpath/DijkstraDistance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */