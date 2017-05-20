/*     */ package edu.uci.ics.jung.algorithms.scoring;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.scoring.util.ScoringUtils;
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
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
/*     */ public class KStepMarkov<V, E>
/*     */   extends PageRankWithPriors<V, E>
/*     */ {
/*     */   private boolean cumulative;
/*     */   
/*     */   public KStepMarkov(Hypergraph<V, E> graph, Transformer<E, ? extends Number> edge_weights, Transformer<V, Double> vertex_priors, int steps)
/*     */   {
/*  60 */     super(graph, edge_weights, vertex_priors, 0.0D);
/*  61 */     initialize(steps);
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
/*     */   public KStepMarkov(Hypergraph<V, E> graph, Transformer<V, Double> vertex_priors, int steps)
/*     */   {
/*  75 */     super(graph, vertex_priors, 0.0D);
/*  76 */     initialize(steps);
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
/*     */   public KStepMarkov(Hypergraph<V, E> graph, int steps)
/*     */   {
/*  90 */     super(graph, ScoringUtils.getUniformRootPrior(graph.getVertices()), 0.0D);
/*  91 */     initialize(steps);
/*     */   }
/*     */   
/*     */   private void initialize(int steps)
/*     */   {
/*  96 */     acceptDisconnectedGraph(false);
/*     */     
/*  98 */     if (steps <= 0) {
/*  99 */       throw new IllegalArgumentException("Number of steps must be > 0");
/*     */     }
/* 101 */     this.max_iterations = steps;
/* 102 */     this.tolerance = -1.0D;
/*     */     
/* 104 */     this.cumulative = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCumulative(boolean cumulative)
/*     */   {
/* 114 */     this.cumulative = cumulative;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double update(V v)
/*     */   {
/* 123 */     if (!this.cumulative) {
/* 124 */       return super.update(v);
/*     */     }
/* 126 */     collectDisappearingPotential(v);
/*     */     
/* 128 */     double v_input = 0.0D;
/* 129 */     for (Iterator i$ = this.graph.getInEdges(v).iterator(); i$.hasNext();) { e = i$.next();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 136 */       incident_count = getAdjustedIncidentCount(e);
/* 137 */       for (V w : this.graph.getIncidentVertices(e))
/*     */       {
/* 139 */         if ((!w.equals(v)) || (this.hyperedges_are_self_loops)) {
/* 140 */           v_input += ((Double)getCurrentValue(w)).doubleValue() * getEdgeWeight(w, e).doubleValue() / incident_count;
/*     */         }
/*     */       }
/*     */     }
/*     */     E e;
/*     */     int incident_count;
/* 146 */     double new_value = this.alpha > 0.0D ? v_input * (1.0D - this.alpha) + ((Double)getVertexPrior(v)).doubleValue() * this.alpha : v_input;
/*     */     
/*     */ 
/* 149 */     setOutputValue(v, Double.valueOf(new_value + ((Double)getCurrentValue(v)).doubleValue()));
/*     */     
/*     */ 
/*     */ 
/* 153 */     return Math.abs(((Double)getCurrentValue(v)).doubleValue() - new_value);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/KStepMarkov.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */