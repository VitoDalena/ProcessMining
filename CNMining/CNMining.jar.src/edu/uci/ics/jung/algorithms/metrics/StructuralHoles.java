/*     */ package edu.uci.ics.jung.algorithms.metrics;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
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
/*     */ public class StructuralHoles<V, E>
/*     */ {
/*     */   protected Transformer<E, ? extends Number> edge_weight;
/*     */   protected Graph<V, E> g;
/*     */   
/*     */   public StructuralHoles(Graph<V, E> graph, Transformer<E, ? extends Number> nev)
/*     */   {
/*  51 */     this.g = graph;
/*  52 */     this.edge_weight = nev;
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
/*     */   public double effectiveSize(V v)
/*     */   {
/*  73 */     double result = this.g.degree(v);
/*  74 */     for (Iterator i$ = this.g.getNeighbors(v).iterator(); i$.hasNext();) { u = i$.next();
/*     */       
/*  76 */       for (V w : this.g.getNeighbors(u))
/*     */       {
/*  78 */         if ((w != v) && (w != u))
/*  79 */           result -= normalizedMutualEdgeWeight(v, w) * maxScaledMutualEdgeWeight(u, w);
/*     */       }
/*     */     }
/*     */     V u;
/*  83 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double efficiency(V v)
/*     */   {
/*  93 */     double degree = this.g.degree(v);
/*     */     
/*  95 */     if (degree == 0.0D) {
/*  96 */       return 0.0D;
/*     */     }
/*  98 */     return effectiveSize(v) / degree;
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
/*     */   public double constraint(V v)
/*     */   {
/* 113 */     double result = 0.0D;
/* 114 */     for (V w : this.g.getSuccessors(v))
/*     */     {
/* 116 */       if ((v != w) && (this.g.isPredecessor(v, w)))
/*     */       {
/* 118 */         result += localConstraint(v, w);
/*     */       }
/*     */     }
/*     */     
/* 122 */     return result;
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
/*     */   public double hierarchy(V v)
/*     */   {
/* 143 */     double v_degree = this.g.degree(v);
/*     */     
/* 145 */     if (v_degree == 0.0D)
/* 146 */       return NaN.0D;
/* 147 */     if (v_degree == 1.0D) {
/* 148 */       return 1.0D;
/*     */     }
/* 150 */     double v_constraint = aggregateConstraint(v);
/*     */     
/* 152 */     double numerator = 0.0D;
/* 153 */     for (V w : this.g.getNeighbors(v))
/*     */     {
/* 155 */       if (v != w)
/*     */       {
/* 157 */         double sl_constraint = localConstraint(v, w) / (v_constraint / v_degree);
/* 158 */         numerator += sl_constraint * Math.log(sl_constraint);
/*     */       }
/*     */     }
/*     */     
/* 162 */     return numerator / (v_degree * Math.log(v_degree));
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
/*     */   public double localConstraint(V v1, V v2)
/*     */   {
/* 181 */     double nmew_vw = normalizedMutualEdgeWeight(v1, v2);
/* 182 */     double inner_result = 0.0D;
/* 183 */     for (V w : this.g.getNeighbors(v1))
/*     */     {
/* 185 */       inner_result += normalizedMutualEdgeWeight(v1, w) * normalizedMutualEdgeWeight(w, v2);
/*     */     }
/*     */     
/* 188 */     return (nmew_vw + inner_result) * (nmew_vw + inner_result);
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
/*     */   public double aggregateConstraint(V v)
/*     */   {
/* 205 */     double result = 0.0D;
/* 206 */     for (V w : this.g.getNeighbors(v))
/*     */     {
/* 208 */       result += localConstraint(v, w) * organizationalMeasure(this.g, w);
/*     */     }
/* 210 */     return result;
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
/*     */   protected double organizationalMeasure(Graph<V, E> g, V v)
/*     */   {
/* 224 */     return 1.0D;
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
/*     */   protected double normalizedMutualEdgeWeight(V v1, V v2)
/*     */   {
/* 239 */     if (v1 == v2) {
/* 240 */       return 0.0D;
/*     */     }
/* 242 */     double numerator = mutualWeight(v1, v2);
/*     */     
/* 244 */     if (numerator == 0.0D) {
/* 245 */       return 0.0D;
/*     */     }
/* 247 */     double denominator = 0.0D;
/* 248 */     for (V v : this.g.getNeighbors(v1)) {
/* 249 */       denominator += mutualWeight(v1, v);
/*     */     }
/* 251 */     if (denominator == 0.0D) {
/* 252 */       return 0.0D;
/*     */     }
/* 254 */     return numerator / denominator;
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
/*     */   protected double mutualWeight(V v1, V v2)
/*     */   {
/* 271 */     E e12 = this.g.findEdge(v1, v2);
/* 272 */     E e21 = this.g.findEdge(v2, v1);
/* 273 */     double w12 = e12 != null ? ((Number)this.edge_weight.transform(e12)).doubleValue() : 0.0D;
/* 274 */     double w21 = e21 != null ? ((Number)this.edge_weight.transform(e21)).doubleValue() : 0.0D;
/*     */     
/* 276 */     return w12 + w21;
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
/*     */   protected double maxScaledMutualEdgeWeight(V v1, V v2)
/*     */   {
/* 290 */     if (v1 == v2) {
/* 291 */       return 0.0D;
/*     */     }
/* 293 */     double numerator = mutualWeight(v1, v2);
/*     */     
/* 295 */     if (numerator == 0.0D) {
/* 296 */       return 0.0D;
/*     */     }
/* 298 */     double denominator = 0.0D;
/* 299 */     for (V w : this.g.getNeighbors(v1))
/*     */     {
/* 301 */       if (v2 != w) {
/* 302 */         denominator = Math.max(numerator, mutualWeight(v1, w));
/*     */       }
/*     */     }
/* 305 */     if (denominator == 0.0D) {
/* 306 */       return 0.0D;
/*     */     }
/* 308 */     return numerator / denominator;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/metrics/StructuralHoles.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */