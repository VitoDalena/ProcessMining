/*     */ package edu.uci.ics.jung.algorithms.cluster;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.scoring.BetweennessCentrality;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EdgeBetweennessClusterer<V, E>
/*     */   implements Transformer<Graph<V, E>, Set<Set<V>>>
/*     */ {
/*     */   private int mNumEdgesToRemove;
/*     */   private Map<E, Pair<V>> edges_removed;
/*     */   
/*     */   public EdgeBetweennessClusterer(int numEdgesToRemove)
/*     */   {
/*  55 */     this.mNumEdgesToRemove = numEdgesToRemove;
/*  56 */     this.edges_removed = new LinkedHashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<Set<V>> transform(Graph<V, E> graph)
/*     */   {
/*  66 */     if ((this.mNumEdgesToRemove < 0) || (this.mNumEdgesToRemove > graph.getEdgeCount())) {
/*  67 */       throw new IllegalArgumentException("Invalid number of edges passed in.");
/*     */     }
/*     */     
/*  70 */     this.edges_removed.clear();
/*     */     
/*  72 */     for (int k = 0; k < this.mNumEdgesToRemove; k++) {
/*  73 */       BetweennessCentrality<V, E> bc = new BetweennessCentrality(graph);
/*  74 */       E to_remove = null;
/*  75 */       double score = 0.0D;
/*  76 */       for (E e : graph.getEdges())
/*  77 */         if (bc.getEdgeScore(e).doubleValue() > score)
/*     */         {
/*  79 */           to_remove = e;
/*  80 */           score = bc.getEdgeScore(e).doubleValue();
/*     */         }
/*  82 */       this.edges_removed.put(to_remove, graph.getEndpoints(to_remove));
/*  83 */       graph.removeEdge(to_remove);
/*     */     }
/*     */     
/*  86 */     WeakComponentClusterer<V, E> wcSearch = new WeakComponentClusterer();
/*  87 */     Set<Set<V>> clusterSet = wcSearch.transform(graph);
/*     */     
/*  89 */     for (Map.Entry<E, Pair<V>> entry : this.edges_removed.entrySet())
/*     */     {
/*  91 */       Pair<V> endpoints = (Pair)entry.getValue();
/*  92 */       graph.addEdge(entry.getKey(), endpoints.getFirst(), endpoints.getSecond());
/*     */     }
/*  94 */     return clusterSet;
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
/*     */   public List<E> getEdgesRemoved()
/*     */   {
/* 107 */     return new ArrayList(this.edges_removed.keySet());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/cluster/EdgeBetweennessClusterer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */