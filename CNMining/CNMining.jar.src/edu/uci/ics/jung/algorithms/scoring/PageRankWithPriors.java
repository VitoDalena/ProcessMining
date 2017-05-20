/*     */ package edu.uci.ics.jung.algorithms.scoring;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.scoring.util.UniformDegreeWeight;
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
/*     */ public class PageRankWithPriors<V, E>
/*     */   extends AbstractIterativeScorerWithPriors<V, E, Double>
/*     */ {
/*  35 */   protected double disappearing_potential = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PageRankWithPriors(Hypergraph<V, E> graph, Transformer<E, ? extends Number> edge_weights, Transformer<V, Double> vertex_priors, double alpha)
/*     */   {
/*  49 */     super(graph, edge_weights, vertex_priors, alpha);
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
/*     */   public PageRankWithPriors(Hypergraph<V, E> graph, Transformer<V, Double> vertex_priors, double alpha)
/*     */   {
/*  63 */     super(graph, vertex_priors, alpha);
/*  64 */     this.edge_weights = new UniformDegreeWeight(graph);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double update(V v)
/*     */   {
/*  73 */     collectDisappearingPotential(v);
/*     */     
/*  75 */     double v_input = 0.0D;
/*  76 */     for (Iterator i$ = this.graph.getInEdges(v).iterator(); i$.hasNext();) { e = i$.next();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  83 */       incident_count = getAdjustedIncidentCount(e);
/*  84 */       for (V w : this.graph.getIncidentVertices(e))
/*     */       {
/*  86 */         if ((!w.equals(v)) || (this.hyperedges_are_self_loops)) {
/*  87 */           v_input += ((Double)getCurrentValue(w)).doubleValue() * getEdgeWeight(w, e).doubleValue() / incident_count;
/*     */         }
/*     */       }
/*     */     }
/*     */     E e;
/*     */     int incident_count;
/*  93 */     double new_value = this.alpha > 0.0D ? v_input * (1.0D - this.alpha) + ((Double)getVertexPrior(v)).doubleValue() * this.alpha : v_input;
/*     */     
/*     */ 
/*  96 */     setOutputValue(v, Double.valueOf(new_value));
/*     */     
/*  98 */     return Math.abs(((Double)getCurrentValue(v)).doubleValue() - new_value);
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
/*     */   protected void afterStep()
/*     */   {
/* 111 */     if (this.disappearing_potential > 0.0D)
/*     */     {
/* 113 */       for (V v : this.graph.getVertices())
/*     */       {
/* 115 */         setOutputValue(v, Double.valueOf(((Double)getOutputValue(v)).doubleValue() + (1.0D - this.alpha) * (this.disappearing_potential * ((Double)getVertexPrior(v)).doubleValue())));
/*     */       }
/*     */       
/* 118 */       this.disappearing_potential = 0.0D;
/*     */     }
/*     */     
/* 121 */     super.afterStep();
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
/*     */   protected void collectDisappearingPotential(V v)
/*     */   {
/* 134 */     if (this.graph.outDegree(v) == 0)
/*     */     {
/* 136 */       if (isDisconnectedGraphOK()) {
/* 137 */         this.disappearing_potential += ((Double)getCurrentValue(v)).doubleValue();
/*     */       } else {
/* 139 */         throw new IllegalArgumentException("Outdegree of " + v + " must be > 0");
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/PageRankWithPriors.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */