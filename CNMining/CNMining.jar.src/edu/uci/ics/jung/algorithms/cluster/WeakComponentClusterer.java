/*    */ package edu.uci.ics.jung.algorithms.cluster;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections15.Buffer;
/*    */ import org.apache.commons.collections15.Transformer;
/*    */ import org.apache.commons.collections15.buffer.UnboundedFifoBuffer;
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
/*    */ public class WeakComponentClusterer<V, E>
/*    */   implements Transformer<Graph<V, E>, Set<Set<V>>>
/*    */ {
/*    */   public Set<Set<V>> transform(Graph<V, E> graph)
/*    */   {
/* 44 */     Set<Set<V>> clusterSet = new HashSet();
/*    */     
/* 46 */     HashSet<V> unvisitedVertices = new HashSet(graph.getVertices());
/*    */     
/* 48 */     while (!unvisitedVertices.isEmpty()) {
/* 49 */       Set<V> cluster = new HashSet();
/* 50 */       V root = unvisitedVertices.iterator().next();
/* 51 */       unvisitedVertices.remove(root);
/* 52 */       cluster.add(root);
/*    */       
/* 54 */       Buffer<V> queue = new UnboundedFifoBuffer();
/* 55 */       queue.add(root);
/*    */       
/* 57 */       while (!queue.isEmpty()) {
/* 58 */         V currentVertex = queue.remove();
/* 59 */         Collection<V> neighbors = graph.getNeighbors(currentVertex);
/*    */         
/* 61 */         for (V neighbor : neighbors) {
/* 62 */           if (unvisitedVertices.contains(neighbor)) {
/* 63 */             queue.add(neighbor);
/* 64 */             unvisitedVertices.remove(neighbor);
/* 65 */             cluster.add(neighbor);
/*    */           }
/*    */         }
/*    */       }
/* 69 */       clusterSet.add(cluster);
/*    */     }
/* 71 */     return clusterSet;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/cluster/WeakComponentClusterer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */