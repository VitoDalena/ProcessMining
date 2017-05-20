/*     */ package edu.uci.ics.jung.algorithms.scoring;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractIterativeScorerWithPriors<V, E, S>
/*     */   extends AbstractIterativeScorer<V, E, S>
/*     */   implements VertexScorer<V, S>
/*     */ {
/*     */   protected Transformer<V, ? extends S> vertex_priors;
/*     */   protected double alpha;
/*     */   
/*     */   public AbstractIterativeScorerWithPriors(Hypergraph<V, E> g, Transformer<E, ? extends Number> edge_weights, Transformer<V, ? extends S> vertex_priors, double alpha)
/*     */   {
/*  54 */     super(g, edge_weights);
/*  55 */     this.vertex_priors = vertex_priors;
/*  56 */     this.alpha = alpha;
/*  57 */     initialize();
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
/*     */   public AbstractIterativeScorerWithPriors(Hypergraph<V, E> g, Transformer<V, ? extends S> vertex_priors, double alpha)
/*     */   {
/*  70 */     super(g);
/*  71 */     this.vertex_priors = vertex_priors;
/*  72 */     this.alpha = alpha;
/*  73 */     initialize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void initialize()
/*     */   {
/*  82 */     super.initialize();
/*     */     
/*     */ 
/*     */ 
/*  86 */     for (V v : this.graph.getVertices()) {
/*  87 */       setOutputValue(v, getVertexPrior(v));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected S getVertexPrior(V v)
/*     */   {
/*  97 */     return (S)this.vertex_priors.transform(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Transformer<V, ? extends S> getVertexPriors()
/*     */   {
/* 106 */     return this.vertex_priors;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAlpha()
/*     */   {
/* 115 */     return this.alpha;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/AbstractIterativeScorerWithPriors.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */