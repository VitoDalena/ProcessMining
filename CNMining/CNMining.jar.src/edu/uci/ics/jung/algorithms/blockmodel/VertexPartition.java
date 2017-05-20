/*     */ package edu.uci.ics.jung.algorithms.blockmodel;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VertexPartition<V, E>
/*     */ {
/*     */   private Map<V, Set<V>> vertex_partition_map;
/*     */   private Collection<Set<V>> vertex_sets;
/*     */   private Graph<V, E> graph;
/*     */   
/*     */   public VertexPartition(Graph<V, E> g, Map<V, Set<V>> partition_map)
/*     */   {
/*  40 */     this.vertex_partition_map = Collections.unmodifiableMap(partition_map);
/*  41 */     this.graph = g;
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
/*     */   public VertexPartition(Graph<V, E> g, Map<V, Set<V>> partition_map, Collection<Set<V>> vertex_sets)
/*     */   {
/*  57 */     this.vertex_partition_map = Collections.unmodifiableMap(partition_map);
/*  58 */     this.vertex_sets = vertex_sets;
/*  59 */     this.graph = g;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public VertexPartition(Graph<V, E> g, Collection<Set<V>> vertex_sets)
/*     */   {
/*  70 */     this.vertex_sets = vertex_sets;
/*  71 */     this.graph = g;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Graph<V, E> getGraph()
/*     */   {
/*  80 */     return this.graph;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<V, Set<V>> getVertexToPartitionMap()
/*     */   {
/*     */     Iterator i$;
/*     */     
/*     */ 
/*  90 */     if (this.vertex_partition_map == null)
/*     */     {
/*  92 */       this.vertex_partition_map = new HashMap();
/*  93 */       for (i$ = this.vertex_sets.iterator(); i$.hasNext();) { set = (Set)i$.next();
/*  94 */         for (V v : set)
/*  95 */           this.vertex_partition_map.put(v, set); } }
/*     */     Set<V> set;
/*  97 */     return this.vertex_partition_map;
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
/*     */   public Collection<Set<V>> getVertexPartitions()
/*     */   {
/* 110 */     if (this.vertex_sets == null)
/*     */     {
/* 112 */       this.vertex_sets = new HashSet();
/* 113 */       this.vertex_sets.addAll(this.vertex_partition_map.values());
/*     */     }
/* 115 */     return this.vertex_sets;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int numPartitions()
/*     */   {
/* 123 */     return this.vertex_sets.size();
/*     */   }
/*     */   
/*     */ 
/*     */   public String toString()
/*     */   {
/* 129 */     return "Partitions: " + this.vertex_partition_map;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/blockmodel/VertexPartition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */