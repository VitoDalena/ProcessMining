/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Factory;
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
/*     */ public class DirectedOrderedSparseMultigraph<V, E>
/*     */   extends DirectedSparseMultigraph<V, E>
/*     */   implements DirectedGraph<V, E>, MultiGraph<V, E>, Serializable
/*     */ {
/*     */   public static <V, E> Factory<DirectedGraph<V, E>> getFactory()
/*     */   {
/*  42 */     new Factory() {
/*     */       public DirectedGraph<V, E> create() {
/*  44 */         return new DirectedOrderedSparseMultigraph();
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DirectedOrderedSparseMultigraph()
/*     */   {
/*  53 */     this.vertices = new LinkedHashMap();
/*  54 */     this.edges = new LinkedHashMap();
/*     */   }
/*     */   
/*     */   public boolean addVertex(V vertex)
/*     */   {
/*  59 */     if (vertex == null) {
/*  60 */       throw new IllegalArgumentException("vertex may not be null");
/*     */     }
/*  62 */     if (!containsVertex(vertex)) {
/*  63 */       this.vertices.put(vertex, new Pair(new LinkedHashSet(), new LinkedHashSet()));
/*  64 */       return true;
/*     */     }
/*  66 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public Collection<V> getPredecessors(V vertex)
/*     */   {
/*  72 */     if (!containsVertex(vertex))
/*  73 */       return null;
/*  74 */     Set<V> preds = new LinkedHashSet();
/*  75 */     for (E edge : getIncoming_internal(vertex)) {
/*  76 */       preds.add(getSource(edge));
/*     */     }
/*  78 */     return Collections.unmodifiableCollection(preds);
/*     */   }
/*     */   
/*     */   public Collection<V> getSuccessors(V vertex)
/*     */   {
/*  83 */     if (!containsVertex(vertex))
/*  84 */       return null;
/*  85 */     Set<V> succs = new LinkedHashSet();
/*  86 */     for (E edge : getOutgoing_internal(vertex)) {
/*  87 */       succs.add(getDest(edge));
/*     */     }
/*  89 */     return Collections.unmodifiableCollection(succs);
/*     */   }
/*     */   
/*     */   public Collection<V> getNeighbors(V vertex)
/*     */   {
/*  94 */     if (!containsVertex(vertex))
/*  95 */       return null;
/*  96 */     Collection<V> neighbors = new LinkedHashSet();
/*  97 */     for (E edge : getIncoming_internal(vertex))
/*  98 */       neighbors.add(getSource(edge));
/*  99 */     for (E edge : getOutgoing_internal(vertex))
/* 100 */       neighbors.add(getDest(edge));
/* 101 */     return Collections.unmodifiableCollection(neighbors);
/*     */   }
/*     */   
/*     */   public Collection<E> getIncidentEdges(V vertex)
/*     */   {
/* 106 */     if (!containsVertex(vertex))
/* 107 */       return null;
/* 108 */     Collection<E> incident = new LinkedHashSet();
/* 109 */     incident.addAll(getIncoming_internal(vertex));
/* 110 */     incident.addAll(getOutgoing_internal(vertex));
/* 111 */     return incident;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/DirectedOrderedSparseMultigraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */