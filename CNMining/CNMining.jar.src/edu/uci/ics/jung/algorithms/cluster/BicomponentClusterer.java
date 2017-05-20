/*     */ package edu.uci.ics.jung.algorithms.cluster;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.Stack;
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
/*     */ public class BicomponentClusterer<V, E>
/*     */   implements Transformer<UndirectedGraph<V, E>, Set<Set<V>>>
/*     */ {
/*     */   protected Map<V, Number> dfs_num;
/*     */   protected Map<V, Number> high;
/*     */   protected Map<V, V> parents;
/*     */   protected Stack<E> stack;
/*     */   protected int converse_depth;
/*     */   
/*     */   public Set<Set<V>> transform(UndirectedGraph<V, E> theGraph)
/*     */   {
/*  57 */     Set<Set<V>> bicomponents = new LinkedHashSet();
/*     */     
/*  59 */     if (theGraph.getVertices().isEmpty()) {
/*  60 */       return bicomponents;
/*     */     }
/*     */     
/*  63 */     this.dfs_num = new HashMap();
/*  64 */     for (V v : theGraph.getVertices())
/*     */     {
/*  66 */       this.dfs_num.put(v, Integer.valueOf(0));
/*     */     }
/*     */     
/*  69 */     for (V v : theGraph.getVertices())
/*     */     {
/*  71 */       if (((Number)this.dfs_num.get(v)).intValue() == 0)
/*     */       {
/*  73 */         this.high = new HashMap();
/*  74 */         this.stack = new Stack();
/*  75 */         this.parents = new HashMap();
/*  76 */         this.converse_depth = theGraph.getVertexCount();
/*     */         
/*  78 */         findBiconnectedComponents(theGraph, v, bicomponents);
/*     */         
/*     */ 
/*     */ 
/*  82 */         if (theGraph.getVertexCount() - this.converse_depth == 1)
/*     */         {
/*  84 */           Set<V> s = new HashSet();
/*  85 */           s.add(v);
/*  86 */           bicomponents.add(s);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  91 */     return bicomponents;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void findBiconnectedComponents(UndirectedGraph<V, E> g, V v, Set<Set<V>> bicomponents)
/*     */   {
/* 125 */     int v_dfs_num = this.converse_depth;
/* 126 */     this.dfs_num.put(v, Integer.valueOf(v_dfs_num));
/* 127 */     this.converse_depth -= 1;
/* 128 */     this.high.put(v, Integer.valueOf(v_dfs_num));
/*     */     
/* 130 */     for (V w : g.getNeighbors(v))
/*     */     {
/* 132 */       int w_dfs_num = ((Number)this.dfs_num.get(w)).intValue();
/* 133 */       E vw = g.findEdge(v, w);
/* 134 */       if (w_dfs_num == 0)
/*     */       {
/* 136 */         this.parents.put(w, v);
/* 137 */         this.stack.push(vw);
/* 138 */         findBiconnectedComponents(g, w, bicomponents);
/* 139 */         int w_high = ((Number)this.high.get(w)).intValue();
/* 140 */         if (w_high <= v_dfs_num)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 146 */           Set<V> bicomponent = new HashSet();
/*     */           E e;
/*     */           do
/*     */           {
/* 150 */             e = this.stack.pop();
/* 151 */             bicomponent.addAll(g.getIncidentVertices(e));
/*     */           }
/* 153 */           while (e != vw);
/* 154 */           bicomponents.add(bicomponent);
/*     */         }
/* 156 */         this.high.put(v, Integer.valueOf(Math.max(w_high, ((Number)this.high.get(v)).intValue())));
/*     */       }
/* 158 */       else if (w != this.parents.get(v)) {
/* 159 */         this.high.put(v, Integer.valueOf(Math.max(w_dfs_num, ((Number)this.high.get(v)).intValue())));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/cluster/BicomponentClusterer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */