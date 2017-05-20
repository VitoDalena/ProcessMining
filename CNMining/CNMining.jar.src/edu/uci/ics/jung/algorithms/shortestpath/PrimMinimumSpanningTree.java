/*     */ package edu.uci.ics.jung.algorithms.shortestpath;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.Transformer;
/*     */ import org.apache.commons.collections15.functors.ConstantTransformer;
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
/*     */ public class PrimMinimumSpanningTree<V, E>
/*     */   implements Transformer<Graph<V, E>, Graph<V, E>>
/*     */ {
/*     */   protected Factory<? extends Graph<V, E>> treeFactory;
/*     */   protected Transformer<E, Double> weights;
/*     */   
/*     */   public PrimMinimumSpanningTree(Factory<? extends Graph<V, E>> factory)
/*     */   {
/*  33 */     this(factory, new ConstantTransformer(Double.valueOf(1.0D)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public PrimMinimumSpanningTree(Factory<? extends Graph<V, E>> factory, Transformer<E, Double> weights)
/*     */   {
/*  41 */     this.treeFactory = factory;
/*  42 */     if (weights != null) {
/*  43 */       this.weights = weights;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Graph<V, E> transform(Graph<V, E> graph)
/*     */   {
/*  51 */     Set<E> unfinishedEdges = new HashSet(graph.getEdges());
/*  52 */     Graph<V, E> tree = (Graph)this.treeFactory.create();
/*  53 */     V root = findRoot(graph);
/*  54 */     if (graph.getVertices().contains(root)) {
/*  55 */       tree.addVertex(root);
/*  56 */     } else if (graph.getVertexCount() > 0)
/*     */     {
/*  58 */       tree.addVertex(graph.getVertices().iterator().next());
/*     */     }
/*  60 */     updateTree(tree, graph, unfinishedEdges);
/*     */     
/*  62 */     return tree;
/*     */   }
/*     */   
/*     */   protected V findRoot(Graph<V, E> graph) {
/*  66 */     for (V v : graph.getVertices()) {
/*  67 */       if (graph.getInEdges(v).size() == 0) {
/*  68 */         return v;
/*     */       }
/*     */     }
/*     */     
/*  72 */     if (graph.getVertexCount() > 0) {
/*  73 */       return (V)graph.getVertices().iterator().next();
/*     */     }
/*     */     
/*  76 */     return null;
/*     */   }
/*     */   
/*     */   protected void updateTree(Graph<V, E> tree, Graph<V, E> graph, Collection<E> unfinishedEdges) {
/*  80 */     Collection<V> tv = tree.getVertices();
/*  81 */     double minCost = Double.MAX_VALUE;
/*  82 */     E nextEdge = null;
/*  83 */     V nextVertex = null;
/*  84 */     V currentVertex = null;
/*  85 */     for (E e : unfinishedEdges)
/*     */     {
/*  87 */       if (!tree.getEdges().contains(e))
/*     */       {
/*     */ 
/*  90 */         Pair<V> endpoints = graph.getEndpoints(e);
/*  91 */         V first = endpoints.getFirst();
/*  92 */         V second = endpoints.getSecond();
/*  93 */         if ((tv.contains(first) == true) && (!tv.contains(second))) {
/*  94 */           if (((Double)this.weights.transform(e)).doubleValue() < minCost) {
/*  95 */             minCost = ((Double)this.weights.transform(e)).doubleValue();
/*  96 */             nextEdge = e;
/*  97 */             currentVertex = first;
/*  98 */             nextVertex = second;
/*     */           }
/* 100 */         } else if ((tv.contains(second) == true) && (!tv.contains(first)) && 
/* 101 */           (((Double)this.weights.transform(e)).doubleValue() < minCost)) {
/* 102 */           minCost = ((Double)this.weights.transform(e)).doubleValue();
/* 103 */           nextEdge = e;
/* 104 */           currentVertex = second;
/* 105 */           nextVertex = first;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 110 */     if ((nextVertex != null) && (nextEdge != null)) {
/* 111 */       unfinishedEdges.remove(nextEdge);
/* 112 */       tree.addEdge(nextEdge, currentVertex, nextVertex);
/* 113 */       updateTree(tree, graph, unfinishedEdges);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/shortestpath/PrimMinimumSpanningTree.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */