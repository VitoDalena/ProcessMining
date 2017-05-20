/*    */ package edu.uci.ics.jung.algorithms.shortestpath;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import edu.uci.ics.jung.graph.util.Pair;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
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
/*    */ public class ShortestPathUtils
/*    */ {
/*    */   public static <V, E> List<E> getPath(Graph<V, E> graph, ShortestPath<V, E> sp, V source, V target)
/*    */   {
/* 33 */     LinkedList<E> path = new LinkedList();
/*    */     
/* 35 */     Map<V, E> incomingEdges = sp.getIncomingEdgeMap(source);
/*    */     
/* 37 */     if ((incomingEdges.isEmpty()) || (incomingEdges.get(target) == null))
/* 38 */       return path;
/* 39 */     V current = target;
/* 40 */     while (!current.equals(source))
/*    */     {
/* 42 */       E incoming = incomingEdges.get(current);
/* 43 */       path.addFirst(incoming);
/* 44 */       Pair<V> endpoints = graph.getEndpoints(incoming);
/* 45 */       if (endpoints.getFirst().equals(current)) {
/* 46 */         current = endpoints.getSecond();
/*    */       } else {
/* 48 */         current = endpoints.getFirst();
/*    */       }
/*    */     }
/*    */     
/* 52 */     return path;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/shortestpath/ShortestPathUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */