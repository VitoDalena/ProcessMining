/*     */ package edu.uci.ics.jung.algorithms.scoring;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.shortestpath.DijkstraDistance;
/*     */ import edu.uci.ics.jung.algorithms.shortestpath.Distance;
/*     */ import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class DistanceCentralityScorer<V, E>
/*     */   implements VertexScorer<V, Double>
/*     */ {
/*     */   protected Hypergraph<V, E> graph;
/*     */   protected Distance<V> distance;
/*     */   protected Map<V, Double> output;
/*     */   protected boolean averaging;
/*     */   protected boolean ignore_missing;
/*     */   protected boolean ignore_self_distances;
/*     */   
/*     */   public DistanceCentralityScorer(Hypergraph<V, E> graph, Distance<V> distance, boolean averaging, boolean ignore_missing, boolean ignore_self_distances)
/*     */   {
/*  94 */     this.graph = graph;
/*  95 */     this.distance = distance;
/*  96 */     this.averaging = averaging;
/*  97 */     this.ignore_missing = ignore_missing;
/*  98 */     this.ignore_self_distances = ignore_self_distances;
/*  99 */     this.output = new HashMap();
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
/*     */   public DistanceCentralityScorer(Hypergraph<V, E> graph, Distance<V> distance, boolean averaging)
/*     */   {
/* 114 */     this(graph, distance, averaging, true, true);
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
/*     */   public DistanceCentralityScorer(Hypergraph<V, E> graph, Transformer<E, ? extends Number> edge_weights, boolean averaging, boolean ignore_missing, boolean ignore_self_distances)
/*     */   {
/* 137 */     this(graph, new DijkstraDistance(graph, edge_weights), averaging, ignore_missing, ignore_self_distances);
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
/*     */   public DistanceCentralityScorer(Hypergraph<V, E> graph, Transformer<E, ? extends Number> edge_weights, boolean averaging)
/*     */   {
/* 153 */     this(graph, new DijkstraDistance(graph, edge_weights), averaging, true, true);
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
/*     */   public DistanceCentralityScorer(Hypergraph<V, E> graph, boolean averaging, boolean ignore_missing, boolean ignore_self_distances)
/*     */   {
/* 173 */     this(graph, new UnweightedShortestPath(graph), averaging, ignore_missing, ignore_self_distances);
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
/*     */   public DistanceCentralityScorer(Hypergraph<V, E> graph, boolean averaging)
/*     */   {
/* 186 */     this(graph, new UnweightedShortestPath(graph), averaging, true, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Double getVertexScore(V v)
/*     */   {
/* 194 */     Double value = (Double)this.output.get(v);
/* 195 */     if (value != null)
/*     */     {
/* 197 */       if (value.doubleValue() < 0.0D)
/* 198 */         return null;
/* 199 */       return value;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 204 */     if (!this.ignore_missing)
/*     */     {
/* 206 */       int n = this.graph.getVertexCount();
/* 207 */       n -= (this.ignore_self_distances ? 1 : 0);
/* 208 */       if (this.distance.getDistanceMap(v).size() < n)
/*     */       {
/* 210 */         this.output.put(v, Double.valueOf(-1.0D));
/* 211 */         return null;
/*     */       }
/*     */     }
/*     */     
/* 215 */     Double sum = Double.valueOf(0.0D);
/* 216 */     for (V w : this.graph.getVertices())
/*     */     {
/* 218 */       if ((!w.equals(v)) || (!this.ignore_self_distances))
/*     */       {
/* 220 */         Number w_distance = this.distance.getDistance(v, w);
/* 221 */         if (w_distance == null) {
/* 222 */           if (!this.ignore_missing)
/*     */           {
/*     */ 
/*     */ 
/* 226 */             this.output.put(v, Double.valueOf(-1.0D));
/* 227 */             return null;
/*     */           }
/*     */         } else
/* 230 */           sum = Double.valueOf(sum.doubleValue() + w_distance.doubleValue());
/*     */       } }
/* 232 */     value = sum;
/* 233 */     if (this.averaging) {
/* 234 */       value = Double.valueOf(value.doubleValue() / this.distance.getDistanceMap(v).size());
/*     */     }
/* 236 */     this.output.put(v, Double.valueOf(value.doubleValue() == 0.0D ? Double.POSITIVE_INFINITY : 1.0D / value.doubleValue()));
/*     */     
/* 238 */     return value;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/DistanceCentralityScorer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */