/*     */ package edu.uci.ics.jung.algorithms.scoring;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.scoring.util.UniformDegreeWeight;
/*     */ import edu.uci.ics.jung.graph.Hypergraph;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
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
/*     */ public class VoltageScorer<V, E>
/*     */   extends AbstractIterativeScorer<V, E, Double>
/*     */   implements VertexScorer<V, Double>
/*     */ {
/*     */   protected Map<V, ? extends Number> source_voltages;
/*     */   protected Collection<V> sinks;
/*     */   
/*     */   public VoltageScorer(Hypergraph<V, E> g, Transformer<E, ? extends Number> edge_weights, Map<V, ? extends Number> source_voltages, Collection<V> sinks)
/*     */   {
/*  66 */     super(g, edge_weights);
/*  67 */     this.source_voltages = source_voltages;
/*  68 */     this.sinks = sinks;
/*  69 */     initialize();
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
/*     */   public VoltageScorer(Hypergraph<V, E> g, Transformer<E, ? extends Number> edge_weights, Collection<V> sources, Collection<V> sinks)
/*     */   {
/*  83 */     super(g, edge_weights);
/*     */     
/*  85 */     Map<V, Double> unit_voltages = new HashMap();
/*  86 */     for (V v : sources)
/*  87 */       unit_voltages.put(v, new Double(1.0D));
/*  88 */     this.source_voltages = unit_voltages;
/*  89 */     this.sinks = sinks;
/*  90 */     initialize();
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
/*     */   public VoltageScorer(Hypergraph<V, E> g, Collection<V> sources, Collection<V> sinks)
/*     */   {
/* 104 */     super(g);
/*     */     
/* 106 */     Map<V, Double> unit_voltages = new HashMap();
/* 107 */     for (V v : sources)
/* 108 */       unit_voltages.put(v, new Double(1.0D));
/* 109 */     this.source_voltages = unit_voltages;
/* 110 */     this.sinks = sinks;
/* 111 */     initialize();
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
/*     */   public VoltageScorer(Hypergraph<V, E> g, Map<V, ? extends Number> source_voltages, Collection<V> sinks)
/*     */   {
/* 125 */     super(g);
/* 126 */     this.source_voltages = source_voltages;
/* 127 */     this.sinks = sinks;
/* 128 */     this.edge_weights = new UniformDegreeWeight(g);
/* 129 */     initialize();
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
/*     */   public VoltageScorer(Hypergraph<V, E> g, Transformer<E, ? extends Number> edge_weights, V source, V sink)
/*     */   {
/* 143 */     this(g, edge_weights, Collections.singletonMap(source, Double.valueOf(1.0D)), Collections.singletonList(sink));
/* 144 */     initialize();
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
/*     */   public VoltageScorer(Hypergraph<V, E> g, V source, V sink)
/*     */   {
/* 158 */     this(g, Collections.singletonMap(source, Double.valueOf(1.0D)), Collections.singletonList(sink));
/* 159 */     initialize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void initialize()
/*     */   {
/* 169 */     super.initialize();
/*     */     
/*     */ 
/* 172 */     if ((this.source_voltages.isEmpty()) || (this.sinks.isEmpty())) {
/* 173 */       throw new IllegalArgumentException("Both sources and sinks (grounds) must be defined");
/*     */     }
/* 175 */     if (this.source_voltages.size() + this.sinks.size() > this.graph.getVertexCount()) {
/* 176 */       throw new IllegalArgumentException("Source/sink sets overlap, or contain vertices not in graph");
/*     */     }
/* 178 */     for (Map.Entry<V, ? extends Number> entry : this.source_voltages.entrySet())
/*     */     {
/* 180 */       V v = entry.getKey();
/* 181 */       if (this.sinks.contains(v))
/* 182 */         throw new IllegalArgumentException("Vertex " + v + " is incorrectly specified as both source and sink");
/* 183 */       double value = ((Number)entry.getValue()).doubleValue();
/* 184 */       if (value <= 0.0D) {
/* 185 */         throw new IllegalArgumentException("Source vertex " + v + " has negative voltage");
/*     */       }
/*     */     }
/*     */     
/* 189 */     for (V v : this.graph.getVertices())
/*     */     {
/* 191 */       if (this.source_voltages.containsKey(v)) {
/* 192 */         setOutputValue(v, Double.valueOf(((Number)this.source_voltages.get(v)).doubleValue()));
/*     */       } else {
/* 194 */         setOutputValue(v, Double.valueOf(0.0D));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double update(V v)
/*     */   {
/* 205 */     Number source_volts = (Number)this.source_voltages.get(v);
/* 206 */     if (source_volts != null)
/*     */     {
/* 208 */       setOutputValue(v, Double.valueOf(source_volts.doubleValue()));
/* 209 */       return 0.0D;
/*     */     }
/* 211 */     if (this.sinks.contains(v))
/*     */     {
/* 213 */       setOutputValue(v, Double.valueOf(0.0D));
/* 214 */       return 0.0D;
/*     */     }
/*     */     
/* 217 */     Collection<E> edges = this.graph.getInEdges(v);
/* 218 */     double voltage_sum = 0.0D;
/* 219 */     double weight_sum = 0.0D;
/* 220 */     for (Iterator i$ = edges.iterator(); i$.hasNext();) { e = i$.next();
/*     */       
/* 222 */       incident_count = getAdjustedIncidentCount(e);
/* 223 */       for (V w : this.graph.getIncidentVertices(e))
/*     */       {
/* 225 */         if ((!w.equals(v)) || (this.hyperedges_are_self_loops))
/*     */         {
/* 227 */           double weight = getEdgeWeight(w, e).doubleValue() / incident_count;
/* 228 */           voltage_sum += ((Double)getCurrentValue(w)).doubleValue() * weight;
/* 229 */           weight_sum += weight;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     E e;
/*     */     
/*     */     int incident_count;
/*     */     
/* 239 */     if ((voltage_sum == 0.0D) || (weight_sum == 0.0D))
/*     */     {
/* 241 */       setOutputValue(v, Double.valueOf(0.0D));
/* 242 */       return ((Double)getCurrentValue(v)).doubleValue();
/*     */     }
/*     */     
/* 245 */     setOutputValue(v, Double.valueOf(voltage_sum / weight_sum));
/* 246 */     return Math.abs(((Double)getCurrentValue(v)).doubleValue() - voltage_sum / weight_sum);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/VoltageScorer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */