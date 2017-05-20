/*     */ package edu.uci.ics.jung.algorithms.scoring;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.scoring.util.ScoringUtils;
/*     */ import edu.uci.ics.jung.graph.Graph;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HITS<V, E>
/*     */   extends HITSWithPriors<V, E>
/*     */ {
/*     */   public HITS(Graph<V, E> g, Transformer<E, Double> edge_weights, double alpha)
/*     */   {
/*  88 */     super(g, edge_weights, ScoringUtils.getHITSUniformRootPrior(g.getVertices()), alpha);
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
/*     */   public HITS(Graph<V, E> g, double alpha)
/*     */   {
/* 101 */     super(g, ScoringUtils.getHITSUniformRootPrior(g.getVertices()), alpha);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public HITS(Graph<V, E> g)
/*     */   {
/* 111 */     this(g, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Scores
/*     */   {
/*     */     public double hub;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public double authority;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Scores(double hub, double authority)
/*     */     {
/* 135 */       this.hub = hub;
/* 136 */       this.authority = authority;
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 142 */       return String.format("[h:%.4f,a:%.4f]", new Object[] { Double.valueOf(this.hub), Double.valueOf(this.authority) });
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/HITS.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */