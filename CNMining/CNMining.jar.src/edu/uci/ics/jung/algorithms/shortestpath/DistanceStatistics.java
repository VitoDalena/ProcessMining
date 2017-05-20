/*     */ package edu.uci.ics.jung.algorithms.shortestpath;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
/*     */ import edu.uci.ics.jung.algorithms.scoring.util.VertexScoreTransformer;
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
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
/*     */ public class DistanceStatistics
/*     */ {
/*     */   public static <V, E> Transformer<V, Double> averageDistances(Hypergraph<V, E> graph, Distance<V> d)
/*     */   {
/*  60 */     ClosenessCentrality<V, E> cc = new ClosenessCentrality(graph, d);
/*  61 */     return new VertexScoreTransformer(cc);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> Transformer<V, Double> averageDistances(Hypergraph<V, E> g)
/*     */   {
/*  73 */     ClosenessCentrality<V, E> cc = new ClosenessCentrality(g, new UnweightedShortestPath(g));
/*     */     
/*  75 */     return new VertexScoreTransformer(cc);
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
/*     */   public static <V, E> double diameter(Hypergraph<V, E> g, Distance<V> d, boolean use_max)
/*     */   {
/*  92 */     double diameter = 0.0D;
/*  93 */     Collection<V> vertices = g.getVertices();
/*  94 */     for (Iterator i$ = vertices.iterator(); i$.hasNext();) { v = i$.next();
/*  95 */       for (V w : vertices)
/*     */       {
/*  97 */         if (!v.equals(w))
/*     */         {
/*  99 */           Number dist = d.getDistance(v, w);
/* 100 */           if (dist == null)
/*     */           {
/* 102 */             if (!use_max) {
/* 103 */               return Double.POSITIVE_INFINITY;
/*     */             }
/*     */           } else
/* 106 */             diameter = Math.max(diameter, dist.doubleValue());
/*     */         } }
/*     */     }
/*     */     V v;
/* 110 */     return diameter;
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
/*     */   public static <V, E> double diameter(Hypergraph<V, E> g, Distance<V> d)
/*     */   {
/* 124 */     return diameter(g, d, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> double diameter(Hypergraph<V, E> g)
/*     */   {
/* 133 */     return diameter(g, new UnweightedShortestPath(g));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/shortestpath/DistanceStatistics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */