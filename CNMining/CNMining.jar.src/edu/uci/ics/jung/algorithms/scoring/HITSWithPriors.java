/*     */ package edu.uci.ics.jung.algorithms.scoring;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HITSWithPriors<V, E>
/*     */   extends AbstractIterativeScorerWithPriors<V, E, HITS.Scores>
/*     */ {
/*     */   protected HITS.Scores disappearing_potential;
/*     */   
/*     */   public HITSWithPriors(Hypergraph<V, E> g, Transformer<E, ? extends Number> edge_weights, Transformer<V, HITS.Scores> vertex_priors, double alpha)
/*     */   {
/*  49 */     super(g, edge_weights, vertex_priors, alpha);
/*  50 */     this.disappearing_potential = new HITS.Scores(0.0D, 0.0D);
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
/*     */   public HITSWithPriors(Hypergraph<V, E> g, Transformer<V, HITS.Scores> vertex_priors, double alpha)
/*     */   {
/*  64 */     super(g, new ConstantTransformer(Double.valueOf(1.0D)), vertex_priors, alpha);
/*  65 */     this.disappearing_potential = new HITS.Scores(0.0D, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double update(V v)
/*     */   {
/*  74 */     collectDisappearingPotential(v);
/*     */     
/*  76 */     double v_auth = 0.0D;
/*  77 */     for (Iterator i$ = this.graph.getInEdges(v).iterator(); i$.hasNext();) { e = i$.next();
/*     */       
/*  79 */       incident_count = getAdjustedIncidentCount(e);
/*  80 */       for (V w : this.graph.getIncidentVertices(e))
/*     */       {
/*  82 */         if ((!w.equals(v)) || (this.hyperedges_are_self_loops)) {
/*  83 */           v_auth += ((HITS.Scores)getCurrentValue(w)).hub * getEdgeWeight(w, e).doubleValue() / incident_count;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     E e;
/*     */     int incident_count;
/*  90 */     double v_hub = 0.0D;
/*  91 */     for (Iterator i$ = this.graph.getOutEdges(v).iterator(); i$.hasNext();) { e = i$.next();
/*     */       
/*  93 */       incident_count = getAdjustedIncidentCount(e);
/*  94 */       for (V w : this.graph.getIncidentVertices(e))
/*     */       {
/*  96 */         if ((!w.equals(v)) || (this.hyperedges_are_self_loops)) {
/*  97 */           v_hub += ((HITS.Scores)getCurrentValue(w)).authority * getEdgeWeight(w, e).doubleValue() / incident_count;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     E e;
/*     */     
/*     */     int incident_count;
/* 105 */     if (this.alpha > 0.0D)
/*     */     {
/* 107 */       v_auth = v_auth * (1.0D - this.alpha) + ((HITS.Scores)getVertexPrior(v)).authority * this.alpha;
/* 108 */       v_hub = v_hub * (1.0D - this.alpha) + ((HITS.Scores)getVertexPrior(v)).hub * this.alpha;
/*     */     }
/* 110 */     setOutputValue(v, new HITS.Scores(v_hub, v_auth));
/*     */     
/* 112 */     return Math.max(Math.abs(((HITS.Scores)getCurrentValue(v)).hub - v_hub), Math.abs(((HITS.Scores)getCurrentValue(v)).authority - v_auth));
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
/* 125 */     if ((this.disappearing_potential.hub > 0.0D) || (this.disappearing_potential.authority > 0.0D))
/*     */     {
/* 127 */       for (V v : this.graph.getVertices())
/*     */       {
/* 129 */         double new_hub = ((HITS.Scores)getOutputValue(v)).hub + (1.0D - this.alpha) * (this.disappearing_potential.hub * ((HITS.Scores)getVertexPrior(v)).hub);
/*     */         
/* 131 */         double new_auth = ((HITS.Scores)getOutputValue(v)).authority + (1.0D - this.alpha) * (this.disappearing_potential.authority * ((HITS.Scores)getVertexPrior(v)).authority);
/*     */         
/* 133 */         setOutputValue(v, new HITS.Scores(new_hub, new_auth));
/*     */       }
/* 135 */       this.disappearing_potential.hub = 0.0D;
/* 136 */       this.disappearing_potential.authority = 0.0D;
/*     */     }
/*     */     
/* 139 */     normalizeScores();
/*     */     
/* 141 */     super.afterStep();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void normalizeScores()
/*     */   {
/* 150 */     double hub_ssum = 0.0D;
/* 151 */     double auth_ssum = 0.0D;
/* 152 */     for (V v : this.graph.getVertices())
/*     */     {
/* 154 */       double hub_val = ((HITS.Scores)getOutputValue(v)).hub;
/* 155 */       double auth_val = ((HITS.Scores)getOutputValue(v)).authority;
/* 156 */       hub_ssum += hub_val * hub_val;
/* 157 */       auth_ssum += auth_val * auth_val;
/*     */     }
/*     */     
/* 160 */     hub_ssum = Math.sqrt(hub_ssum);
/* 161 */     auth_ssum = Math.sqrt(auth_ssum);
/*     */     
/* 163 */     for (V v : this.graph.getVertices())
/*     */     {
/* 165 */       HITS.Scores values = (HITS.Scores)getOutputValue(v);
/* 166 */       setOutputValue(v, new HITS.Scores(values.hub / hub_ssum, values.authority / auth_ssum));
/*     */     }
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
/*     */   protected void collectDisappearingPotential(V v)
/*     */   {
/* 184 */     if (this.graph.outDegree(v) == 0)
/*     */     {
/* 186 */       if (isDisconnectedGraphOK()) {
/* 187 */         this.disappearing_potential.hub += ((HITS.Scores)getCurrentValue(v)).authority;
/*     */       } else
/* 189 */         throw new IllegalArgumentException("Outdegree of " + v + " must be > 0");
/*     */     }
/* 191 */     if (this.graph.inDegree(v) == 0)
/*     */     {
/* 193 */       if (isDisconnectedGraphOK()) {
/* 194 */         this.disappearing_potential.authority += ((HITS.Scores)getCurrentValue(v)).hub;
/*     */       } else {
/* 196 */         throw new IllegalArgumentException("Indegree of " + v + " must be > 0");
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/HITSWithPriors.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */