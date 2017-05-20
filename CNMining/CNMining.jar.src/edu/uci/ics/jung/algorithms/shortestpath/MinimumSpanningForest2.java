/*     */ package edu.uci.ics.jung.algorithms.shortestpath;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.cluster.WeakComponentClusterer;
/*     */ import edu.uci.ics.jung.algorithms.filters.FilterUtils;
/*     */ import edu.uci.ics.jung.graph.Forest;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.Tree;
/*     */ import edu.uci.ics.jung.graph.util.TreeUtils;
/*     */ import java.util.Collection;
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
/*     */ public class MinimumSpanningForest2<V, E>
/*     */ {
/*     */   protected Graph<V, E> graph;
/*     */   protected Forest<V, E> forest;
/*  31 */   protected Transformer<E, Double> weights = new ConstantTransformer(Double.valueOf(1.0D));
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
/*     */   public MinimumSpanningForest2(Graph<V, E> graph, Factory<Forest<V, E>> factory, Factory<? extends Graph<V, E>> treeFactory, Transformer<E, Double> weights)
/*     */   {
/*  51 */     this(graph, (Forest)factory.create(), treeFactory, weights);
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
/*     */   public MinimumSpanningForest2(Graph<V, E> graph, Forest<V, E> forest, Factory<? extends Graph<V, E>> treeFactory, Transformer<E, Double> weights)
/*     */   {
/*  73 */     if (forest.getVertexCount() != 0) {
/*  74 */       throw new IllegalArgumentException("Supplied Forest must be empty");
/*     */     }
/*  76 */     this.graph = graph;
/*  77 */     this.forest = forest;
/*  78 */     if (weights != null) {
/*  79 */       this.weights = weights;
/*     */     }
/*     */     
/*  82 */     WeakComponentClusterer<V, E> wcc = new WeakComponentClusterer();
/*     */     
/*  84 */     Set<Set<V>> component_vertices = wcc.transform(graph);
/*  85 */     Collection<Graph<V, E>> components = FilterUtils.createAllInducedSubgraphs(component_vertices, graph);
/*     */     
/*     */ 
/*  88 */     for (Graph<V, E> component : components) {
/*  89 */       PrimMinimumSpanningTree<V, E> mst = new PrimMinimumSpanningTree(treeFactory, weights);
/*     */       
/*  91 */       Graph<V, E> subTree = mst.transform(component);
/*  92 */       if ((subTree instanceof Tree)) {
/*  93 */         TreeUtils.addSubTree(forest, (Tree)subTree, null, null);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Forest<V, E> getForest()
/*     */   {
/* 102 */     return this.forest;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/shortestpath/MinimumSpanningForest2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */