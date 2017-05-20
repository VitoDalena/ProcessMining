/*     */ package edu.uci.ics.jung.algorithms.blockmodel;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.CollectionUtils;
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
/*     */ public class StructurallyEquivalent<V, E>
/*     */   implements Transformer<Graph<V, E>, VertexPartition<V, E>>
/*     */ {
/*     */   public VertexPartition<V, E> transform(Graph<V, E> g)
/*     */   {
/*  49 */     Set<Pair<V>> vertex_pairs = getEquivalentPairs(g);
/*     */     
/*  51 */     Set<Set<V>> rv = new HashSet();
/*  52 */     Map<V, Set<V>> intermediate = new HashMap();
/*  53 */     for (Pair<V> p : vertex_pairs)
/*     */     {
/*  55 */       Set<V> res = (Set)intermediate.get(p.getFirst());
/*  56 */       if (res == null)
/*  57 */         res = (Set)intermediate.get(p.getSecond());
/*  58 */       if (res == null)
/*  59 */         res = new HashSet();
/*  60 */       res.add(p.getFirst());
/*  61 */       res.add(p.getSecond());
/*  62 */       intermediate.put(p.getFirst(), res);
/*  63 */       intermediate.put(p.getSecond(), res);
/*     */     }
/*  65 */     rv.addAll(intermediate.values());
/*     */     
/*     */ 
/*     */ 
/*  69 */     Collection<V> singletons = CollectionUtils.subtract(g.getVertices(), intermediate.keySet());
/*     */     
/*  71 */     for (V v : singletons)
/*     */     {
/*  73 */       Set<V> v_set = Collections.singleton(v);
/*  74 */       intermediate.put(v, v_set);
/*  75 */       rv.add(v_set);
/*     */     }
/*     */     
/*  78 */     return new VertexPartition(g, intermediate, rv);
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
/*     */   protected Set<Pair<V>> getEquivalentPairs(Graph<V, ?> g)
/*     */   {
/*  93 */     Set<Pair<V>> rv = new HashSet();
/*  94 */     Set<V> alreadyEquivalent = new HashSet();
/*     */     
/*  96 */     List<V> l = new ArrayList(g.getVertices());
/*     */     
/*  98 */     for (Iterator i$ = l.iterator(); i$.hasNext();) { v1 = i$.next();
/*     */       
/* 100 */       if (!alreadyEquivalent.contains(v1))
/*     */       {
/*     */ 
/* 103 */         for (iterator = l.listIterator(l.indexOf(v1) + 1); iterator.hasNext();) {
/* 104 */           V v2 = iterator.next();
/*     */           
/* 106 */           if ((!alreadyEquivalent.contains(v2)) && 
/*     */           
/*     */ 
/* 109 */             (canPossiblyCompare(v1, v2)))
/*     */           {
/*     */ 
/* 112 */             if (isStructurallyEquivalent(g, v1, v2)) {
/* 113 */               Pair<V> p = new Pair(v1, v2);
/* 114 */               alreadyEquivalent.add(v2);
/* 115 */               rv.add(p);
/*     */             } }
/*     */         } } }
/*     */     V v1;
/*     */     Iterator<V> iterator;
/* 120 */     return rv;
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
/*     */   protected boolean isStructurallyEquivalent(Graph<V, ?> g, V v1, V v2)
/*     */   {
/* 134 */     if (g.degree(v1) != g.degree(v2)) {
/* 135 */       return false;
/*     */     }
/*     */     
/* 138 */     Set<V> n1 = new HashSet(g.getPredecessors(v1));
/* 139 */     n1.remove(v2);
/* 140 */     n1.remove(v1);
/* 141 */     Set<V> n2 = new HashSet(g.getPredecessors(v2));
/* 142 */     n2.remove(v1);
/* 143 */     n2.remove(v2);
/*     */     
/* 145 */     Set<V> o1 = new HashSet(g.getSuccessors(v1));
/* 146 */     Set<V> o2 = new HashSet(g.getSuccessors(v2));
/* 147 */     o1.remove(v1);
/* 148 */     o1.remove(v2);
/* 149 */     o2.remove(v1);
/* 150 */     o2.remove(v2);
/*     */     
/*     */ 
/* 153 */     boolean b = (n1.equals(n2)) && (o1.equals(o2));
/* 154 */     if (!b) {
/* 155 */       return b;
/*     */     }
/*     */     
/* 158 */     b &= g.isSuccessor(v1, v2) == g.isSuccessor(v2, v1);
/*     */     
/*     */ 
/* 161 */     b &= g.isSuccessor(v1, v1) == g.isSuccessor(v2, v2);
/*     */     
/* 163 */     return b;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean canPossiblyCompare(V v1, V v2)
/*     */   {
/* 175 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/blockmodel/StructurallyEquivalent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */