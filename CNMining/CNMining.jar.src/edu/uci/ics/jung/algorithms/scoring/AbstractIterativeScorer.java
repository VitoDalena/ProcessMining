/*     */ package edu.uci.ics.jung.algorithms.scoring;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.scoring.util.DelegateToEdgeTransformer;
/*     */ import edu.uci.ics.jung.algorithms.scoring.util.VEPair;
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public abstract class AbstractIterativeScorer<V, E, T>
/*     */   implements IterativeContext, VertexScorer<V, T>
/*     */ {
/*     */   protected int max_iterations;
/*     */   protected double tolerance;
/*     */   protected Hypergraph<V, E> graph;
/*     */   protected int total_iterations;
/*     */   protected Transformer<VEPair<V, E>, ? extends Number> edge_weights;
/*     */   protected boolean output_reversed;
/*     */   private Map<V, T> output;
/*     */   private Map<V, T> current_values;
/*     */   private boolean accept_disconnected_graph;
/*  84 */   protected boolean hyperedges_are_self_loops = false;
/*     */   
/*     */ 
/*     */   protected double max_delta;
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setOutputValue(V v, T value)
/*     */   {
/*  93 */     this.output.put(v, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected T getOutputValue(V v)
/*     */   {
/* 103 */     return (T)this.output.get(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected T getCurrentValue(V v)
/*     */   {
/* 113 */     return (T)this.current_values.get(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setCurrentValue(V v, T value)
/*     */   {
/* 123 */     this.current_values.put(v, value);
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
/*     */   public AbstractIterativeScorer(Hypergraph<V, E> g, Transformer<E, ? extends Number> edge_weights)
/*     */   {
/* 138 */     this.graph = g;
/* 139 */     this.max_iterations = 100;
/* 140 */     this.tolerance = 0.001D;
/* 141 */     this.accept_disconnected_graph = true;
/* 142 */     setEdgeWeights(edge_weights);
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
/*     */   public AbstractIterativeScorer(Hypergraph<V, E> g)
/*     */   {
/* 155 */     this.graph = g;
/* 156 */     this.max_iterations = 100;
/* 157 */     this.tolerance = 0.001D;
/* 158 */     this.accept_disconnected_graph = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void initialize()
/*     */   {
/* 166 */     this.total_iterations = 0;
/* 167 */     this.max_delta = Double.MIN_VALUE;
/* 168 */     this.output_reversed = true;
/* 169 */     this.current_values = new HashMap();
/* 170 */     this.output = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void evaluate()
/*     */   {
/*     */     do
/*     */     {
/* 179 */       step();
/* 180 */     } while (!done());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean done()
/*     */   {
/* 190 */     return (this.total_iterations >= this.max_iterations) || (this.max_delta < this.tolerance);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void step()
/*     */   {
/* 198 */     swapOutputForCurrent();
/*     */     
/* 200 */     for (V v : this.graph.getVertices())
/*     */     {
/* 202 */       double diff = update(v);
/* 203 */       updateMaxDelta(v, diff);
/*     */     }
/* 205 */     this.total_iterations += 1;
/* 206 */     afterStep();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void swapOutputForCurrent()
/*     */   {
/* 214 */     Map<V, T> tmp = this.output;
/* 215 */     this.output = this.current_values;
/* 216 */     this.current_values = tmp;
/* 217 */     this.output_reversed = (!this.output_reversed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract double update(V paramV);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void updateMaxDelta(V v, double diff)
/*     */   {
/* 230 */     this.max_delta = Math.max(this.max_delta, diff);
/*     */   }
/*     */   
/*     */   protected void afterStep() {}
/*     */   
/*     */   public T getVertexScore(V v)
/*     */   {
/* 237 */     if (!this.graph.containsVertex(v)) {
/* 238 */       throw new IllegalArgumentException("Vertex " + v + " not an element of this graph");
/*     */     }
/* 240 */     return (T)this.output.get(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaxIterations()
/*     */   {
/* 250 */     return this.max_iterations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIterations()
/*     */   {
/* 259 */     return this.total_iterations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMaxIterations(int max_iterations)
/*     */   {
/* 268 */     this.max_iterations = max_iterations;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getTolerance()
/*     */   {
/* 279 */     return this.tolerance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTolerance(double tolerance)
/*     */   {
/* 289 */     this.tolerance = tolerance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Transformer<VEPair<V, E>, ? extends Number> getEdgeWeights()
/*     */   {
/* 298 */     return this.edge_weights;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEdgeWeights(Transformer<E, ? extends Number> edge_weights)
/*     */   {
/* 308 */     this.edge_weights = new DelegateToEdgeTransformer(edge_weights);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Number getEdgeWeight(V v, E e)
/*     */   {
/* 319 */     return (Number)this.edge_weights.transform(new VEPair(v, e));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void collectDisappearingPotential(V v) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void acceptDisconnectedGraph(boolean accept)
/*     */   {
/* 335 */     this.accept_disconnected_graph = accept;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDisconnectedGraphOK()
/*     */   {
/* 344 */     return this.accept_disconnected_graph;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setHyperedgesAreSelfLoops(boolean arg)
/*     */   {
/* 355 */     this.hyperedges_are_self_loops = arg;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int getAdjustedIncidentCount(E e)
/*     */   {
/* 366 */     return this.graph.getIncidentCount(e) - (this.hyperedges_are_self_loops ? 0 : 1);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/AbstractIterativeScorer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */