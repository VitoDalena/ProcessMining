/*     */ package edu.uci.ics.jung.graph;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.util.EdgeType;
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
/*     */ public class OrderedSparseMultigraph<V, E>
/*     */   extends SparseMultigraph<V, E>
/*     */   implements MultiGraph<V, E>, Serializable
/*     */ {
/*     */   public static <V, E> Factory<Graph<V, E>> getFactory()
/*     */   {
/*  42 */     new Factory() {
/*     */       public Graph<V, E> create() {
/*  44 */         return new OrderedSparseMultigraph();
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public OrderedSparseMultigraph()
/*     */   {
/*  54 */     this.vertices = new LinkedHashMap();
/*  55 */     this.edges = new LinkedHashMap();
/*  56 */     this.directedEdges = new LinkedHashSet();
/*     */   }
/*     */   
/*     */   public boolean addVertex(V vertex)
/*     */   {
/*  61 */     if (vertex == null) {
/*  62 */       throw new IllegalArgumentException("vertex may not be null");
/*     */     }
/*  64 */     if (!containsVertex(vertex)) {
/*  65 */       this.vertices.put(vertex, new Pair(new LinkedHashSet(), new LinkedHashSet()));
/*  66 */       return true;
/*     */     }
/*  68 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<V> getPredecessors(V vertex)
/*     */   {
/*  76 */     if (!containsVertex(vertex)) {
/*  77 */       return null;
/*     */     }
/*  79 */     Set<V> preds = new LinkedHashSet();
/*  80 */     for (E edge : getIncoming_internal(vertex)) {
/*  81 */       if (getEdgeType(edge) == EdgeType.DIRECTED) {
/*  82 */         preds.add(getSource(edge));
/*     */       } else {
/*  84 */         preds.add(getOpposite(vertex, edge));
/*     */       }
/*     */     }
/*  87 */     return Collections.unmodifiableCollection(preds);
/*     */   }
/*     */   
/*     */ 
/*     */   public Collection<V> getSuccessors(V vertex)
/*     */   {
/*  93 */     if (!containsVertex(vertex)) {
/*  94 */       return null;
/*     */     }
/*  96 */     Set<V> succs = new LinkedHashSet();
/*  97 */     for (E edge : getOutgoing_internal(vertex)) {
/*  98 */       if (getEdgeType(edge) == EdgeType.DIRECTED) {
/*  99 */         succs.add(getDest(edge));
/*     */       } else {
/* 101 */         succs.add(getOpposite(vertex, edge));
/*     */       }
/*     */     }
/* 104 */     return Collections.unmodifiableCollection(succs);
/*     */   }
/*     */   
/*     */ 
/*     */   public Collection<V> getNeighbors(V vertex)
/*     */   {
/* 110 */     if (!containsVertex(vertex)) {
/* 111 */       return null;
/*     */     }
/* 113 */     Collection<V> out = new LinkedHashSet();
/* 114 */     out.addAll(getPredecessors(vertex));
/* 115 */     out.addAll(getSuccessors(vertex));
/* 116 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */   public Collection<E> getIncidentEdges(V vertex)
/*     */   {
/* 122 */     if (!containsVertex(vertex)) {
/* 123 */       return null;
/*     */     }
/* 125 */     Collection<E> out = new LinkedHashSet();
/* 126 */     out.addAll(getInEdges(vertex));
/* 127 */     out.addAll(getOutEdges(vertex));
/* 128 */     return out;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/graph/OrderedSparseMultigraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */