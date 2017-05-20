/*     */ package edu.uci.ics.jung.algorithms.metrics;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.DirectedGraph;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.CollectionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TriadicCensus
/*     */ {
/*  87 */   public static final String[] TRIAD_NAMES = { "N/A", "003", "012", "102", "021D", "021U", "021C", "111D", "111U", "030T", "030C", "201", "120D", "120U", "120C", "210", "300" };
/*     */   
/*     */ 
/*     */ 
/*  91 */   public static final int MAX_TRIADS = TRIAD_NAMES.length;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> long[] getCounts(DirectedGraph<V, E> g)
/*     */   {
/* 101 */     long[] count = new long[MAX_TRIADS];
/*     */     
/* 103 */     List<V> id = new ArrayList(g.getVertices());
/*     */     V v;
/*     */     Iterator i$;
/* 106 */     V u; for (int i_v = 0; i_v < g.getVertexCount(); i_v++) {
/* 107 */       v = id.get(i_v);
/* 108 */       for (i$ = g.getNeighbors(v).iterator(); i$.hasNext();) { u = i$.next();
/* 109 */         int triType = -1;
/* 110 */         if (id.indexOf(u) > i_v)
/*     */         {
/* 112 */           Set<V> neighbors = new HashSet(CollectionUtils.union(g.getNeighbors(u), g.getNeighbors(v)));
/* 113 */           neighbors.remove(u);
/* 114 */           neighbors.remove(v);
/* 115 */           if ((g.isSuccessor(v, u)) && (g.isSuccessor(u, v))) {
/* 116 */             triType = 3;
/*     */           } else {
/* 118 */             triType = 2;
/*     */           }
/* 120 */           count[triType] += g.getVertexCount() - neighbors.size() - 2;
/* 121 */           for (V w : neighbors) {
/* 122 */             if (shouldCount(g, id, u, v, w))
/* 123 */               count[triType(triCode(g, u, v, w))] += 1L;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 128 */     int sum = 0;
/* 129 */     for (int i = 2; i <= 16; i++) {
/* 130 */       sum = (int)(sum + count[i]);
/*     */     }
/* 132 */     int n = g.getVertexCount();
/* 133 */     count[1] = (n * (n - 1) * (n - 2) / 6 - sum);
/* 134 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <V, E> int triCode(Graph<V, E> g, V u, V v, V w)
/*     */   {
/* 143 */     int i = 0;
/* 144 */     i += (link(g, v, u) ? 1 : 0);
/* 145 */     i += (link(g, u, v) ? 2 : 0);
/* 146 */     i += (link(g, v, w) ? 4 : 0);
/* 147 */     i += (link(g, w, v) ? 8 : 0);
/* 148 */     i += (link(g, u, w) ? 16 : 0);
/* 149 */     i += (link(g, w, u) ? 32 : 0);
/* 150 */     return i;
/*     */   }
/*     */   
/*     */   protected static <V, E> boolean link(Graph<V, E> g, V a, V b) {
/* 154 */     return g.isPredecessor(b, a);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int triType(int triCode)
/*     */   {
/* 164 */     return codeToType[triCode];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 171 */   protected static final int[] codeToType = { 1, 2, 2, 3, 2, 4, 6, 8, 2, 6, 5, 7, 3, 8, 7, 11, 2, 6, 4, 8, 5, 9, 9, 13, 6, 10, 9, 14, 7, 14, 12, 15, 2, 5, 6, 7, 6, 9, 10, 14, 4, 9, 9, 12, 8, 13, 14, 15, 3, 7, 8, 11, 7, 12, 14, 15, 8, 14, 13, 15, 11, 15, 15, 16 };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static <V, E> boolean shouldCount(Graph<V, E> g, List<V> id, V u, V v, V w)
/*     */   {
/* 187 */     int i_u = id.indexOf(u);
/* 188 */     int i_w = id.indexOf(w);
/* 189 */     if (i_u < i_w)
/* 190 */       return true;
/* 191 */     int i_v = id.indexOf(v);
/* 192 */     if ((i_v < i_w) && (i_w < i_u) && (!g.isNeighbor(w, v)))
/* 193 */       return true;
/* 194 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/metrics/TriadicCensus.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */