/*    */ package edu.uci.ics.jung.algorithms.metrics;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Metrics
/*    */ {
/*    */   public static <V, E> Map<V, Double> clusteringCoefficients(Graph<V, E> graph)
/*    */   {
/* 42 */     Map<V, Double> coefficients = new HashMap();
/*    */     
/* 44 */     for (V v : graph.getVertices())
/*    */     {
/* 46 */       int n = graph.getNeighborCount(v);
/* 47 */       if (n < 2) {
/* 48 */         coefficients.put(v, new Double(0.0D));
/*    */       }
/*    */       else
/*    */       {
/* 52 */         ArrayList<V> neighbors = new ArrayList(graph.getNeighbors(v));
/* 53 */         double edge_count = 0.0D;
/* 54 */         for (int i = 0; i < n; i++)
/*    */         {
/* 56 */           V w = neighbors.get(i);
/* 57 */           for (int j = i + 1; j < n; j++)
/*    */           {
/* 59 */             V x = neighbors.get(j);
/* 60 */             edge_count += (graph.isNeighbor(w, x) ? 1.0D : 0.0D);
/*    */           }
/*    */         }
/* 63 */         double possible_edges = n * (n - 1) / 2.0D;
/* 64 */         coefficients.put(v, new Double(edge_count / possible_edges));
/*    */       }
/*    */     }
/*    */     
/* 68 */     return coefficients;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/metrics/Metrics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */