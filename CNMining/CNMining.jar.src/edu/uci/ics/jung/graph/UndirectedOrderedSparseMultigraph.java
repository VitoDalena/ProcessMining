/*    */ package edu.uci.ics.jung.graph;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.util.Pair;
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections15.Factory;
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
/*    */ public class UndirectedOrderedSparseMultigraph<V, E>
/*    */   extends UndirectedSparseMultigraph<V, E>
/*    */   implements UndirectedGraph<V, E>, Serializable
/*    */ {
/*    */   public static <V, E> Factory<UndirectedGraph<V, E>> getFactory()
/*    */   {
/* 41 */     new Factory() {
/*    */       public UndirectedGraph<V, E> create() {
/* 43 */         return new UndirectedOrderedSparseMultigraph();
/*    */       }
/*    */     };
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public UndirectedOrderedSparseMultigraph()
/*    */   {
/* 52 */     this.vertices = new LinkedHashMap();
/* 53 */     this.edges = new LinkedHashMap();
/*    */   }
/*    */   
/*    */   public boolean addVertex(V vertex)
/*    */   {
/* 58 */     if (vertex == null) {
/* 59 */       throw new IllegalArgumentException("vertex may not be null");
/*    */     }
/* 61 */     if (!containsVertex(vertex))
/*    */     {
/* 63 */       this.vertices.put(vertex, new LinkedHashSet());
/* 64 */       return true;
/*    */     }
/* 66 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public Collection<V> getNeighbors(V vertex)
/*    */   {
/* 72 */     if (!containsVertex(vertex)) {
/* 73 */       return null;
/*    */     }
/* 75 */     Set<V> neighbors = new LinkedHashSet();
/* 76 */     for (E edge : getIncident_internal(vertex))
/*    */     {
/* 78 */       Pair<V> endpoints = getEndpoints(edge);
/* 79 */       V e_a = endpoints.getFirst();
/* 80 */       V e_b = endpoints.getSecond();
/* 81 */       if (vertex.equals(e_a)) {
/* 82 */         neighbors.add(e_b);
/*    */       } else {
/* 84 */         neighbors.add(e_a);
/*    */       }
/*    */     }
/* 87 */     return Collections.unmodifiableCollection(neighbors);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/UndirectedOrderedSparseMultigraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */