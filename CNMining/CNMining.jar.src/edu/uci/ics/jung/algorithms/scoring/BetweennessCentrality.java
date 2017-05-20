/*     */ package edu.uci.ics.jung.algorithms.scoring;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.util.MapBinaryHeap;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Queue;
/*     */ import java.util.Stack;
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
/*     */ public class BetweennessCentrality<V, E>
/*     */   implements VertexScorer<V, Double>, EdgeScorer<E, Double>
/*     */ {
/*     */   protected Graph<V, E> graph;
/*     */   protected Map<V, Double> vertex_scores;
/*     */   protected Map<E, Double> edge_scores;
/*     */   protected Map<V, BetweennessCentrality<V, E>.BetweennessData> vertex_data;
/*     */   
/*     */   public BetweennessCentrality(Graph<V, E> graph)
/*     */   {
/*  51 */     initialize(graph);
/*  52 */     computeBetweenness(new LinkedList(), new ConstantTransformer(Integer.valueOf(1)));
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
/*     */   public BetweennessCentrality(Graph<V, E> graph, Transformer<E, ? extends Number> edge_weights)
/*     */   {
/*  68 */     for (E e : graph.getEdges())
/*     */     {
/*  70 */       double e_weight = ((Number)edge_weights.transform(e)).doubleValue();
/*  71 */       if (e_weight < 0.0D) {
/*  72 */         throw new IllegalArgumentException(String.format("Weight for edge '%s' is < 0: %d", new Object[] { e, Double.valueOf(e_weight) }));
/*     */       }
/*     */     }
/*     */     
/*  76 */     initialize(graph);
/*  77 */     computeBetweenness(new MapBinaryHeap(new BetweennessComparator(null)), edge_weights);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void initialize(Graph<V, E> graph)
/*     */   {
/*  83 */     this.graph = graph;
/*  84 */     this.vertex_scores = new HashMap();
/*  85 */     this.edge_scores = new HashMap();
/*  86 */     this.vertex_data = new HashMap();
/*     */     
/*  88 */     for (V v : graph.getVertices()) {
/*  89 */       this.vertex_scores.put(v, Double.valueOf(0.0D));
/*     */     }
/*  91 */     for (E e : graph.getEdges()) {
/*  92 */       this.edge_scores.put(e, Double.valueOf(0.0D));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void computeBetweenness(Queue<V> queue, Transformer<E, ? extends Number> edge_weights)
/*     */   {
/*  98 */     for (V v : this.graph.getVertices())
/*     */     {
/*     */ 
/* 101 */       for (V s : this.graph.getVertices()) {
/* 102 */         this.vertex_data.put(s, new BetweennessData());
/*     */       }
/* 104 */       if (v.equals(new Integer(0))) {
/* 105 */         System.out.println("pause");
/*     */       }
/* 107 */       ((BetweennessData)this.vertex_data.get(v)).numSPs = 1.0D;
/* 108 */       ((BetweennessData)this.vertex_data.get(v)).distance = 0.0D;
/*     */       
/* 110 */       Stack<V> stack = new Stack();
/*     */       
/*     */ 
/* 113 */       queue.offer(v);
/*     */       V w;
/* 115 */       BetweennessCentrality<V, E>.BetweennessData w_data; while (!queue.isEmpty())
/*     */       {
/*     */ 
/* 118 */         w = queue.poll();
/* 119 */         stack.push(w);
/* 120 */         w_data = (BetweennessData)this.vertex_data.get(w);
/*     */         
/* 122 */         for (E e : this.graph.getOutEdges(w))
/*     */         {
/*     */ 
/* 125 */           V x = this.graph.getOpposite(w, e);
/* 126 */           if (!x.equals(w))
/*     */           {
/* 128 */             double wx_weight = ((Number)edge_weights.transform(e)).doubleValue();
/*     */             
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 155 */             BetweennessCentrality<V, E>.BetweennessData x_data = (BetweennessData)this.vertex_data.get(x);
/* 156 */             double x_potential_dist = w_data.distance + wx_weight;
/*     */             
/* 158 */             if (x_data.distance < 0.0D)
/*     */             {
/*     */ 
/*     */ 
/* 162 */               x_data.distance = x_potential_dist;
/* 163 */               queue.offer(x);
/*     */             }
/*     */             
/*     */ 
/*     */ 
/*     */ 
/* 169 */             if (x_data.distance > x_potential_dist)
/*     */             {
/* 171 */               x_data.distance = x_potential_dist;
/*     */               
/*     */ 
/* 174 */               x_data.incomingEdges.clear();
/*     */               
/* 176 */               ((MapBinaryHeap)queue).update(x);
/*     */             }
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 187 */         for (E e : this.graph.getOutEdges(w))
/*     */         {
/* 189 */           V x = this.graph.getOpposite(w, e);
/* 190 */           if (!x.equals(w))
/*     */           {
/* 192 */             double e_weight = ((Number)edge_weights.transform(e)).doubleValue();
/* 193 */             BetweennessCentrality<V, E>.BetweennessData x_data = (BetweennessData)this.vertex_data.get(x);
/* 194 */             double x_potential_dist = w_data.distance + e_weight;
/* 195 */             if (x_data.distance == x_potential_dist)
/*     */             {
/* 197 */               x_data.numSPs += w_data.numSPs;
/*     */               
/* 199 */               x_data.incomingEdges.add(e);
/*     */             }
/*     */           }
/*     */         } }
/* 203 */       while (!stack.isEmpty())
/*     */       {
/* 205 */         V x = stack.pop();
/*     */         
/*     */ 
/* 208 */         for (E e : ((BetweennessData)this.vertex_data.get(x)).incomingEdges)
/*     */         {
/* 210 */           V w = this.graph.getOpposite(x, e);
/* 211 */           double partialDependency = ((BetweennessData)this.vertex_data.get(w)).numSPs / ((BetweennessData)this.vertex_data.get(x)).numSPs * (1.0D + ((BetweennessData)this.vertex_data.get(x)).dependency);
/*     */           
/*     */ 
/* 214 */           ((BetweennessData)this.vertex_data.get(w)).dependency += partialDependency;
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 219 */           double e_score = ((Double)this.edge_scores.get(e)).doubleValue();
/* 220 */           this.edge_scores.put(e, Double.valueOf(e_score + partialDependency));
/*     */         }
/* 222 */         if (!x.equals(v))
/*     */         {
/* 224 */           double x_score = ((Double)this.vertex_scores.get(x)).doubleValue();
/* 225 */           x_score += ((BetweennessData)this.vertex_data.get(x)).dependency;
/* 226 */           this.vertex_scores.put(x, Double.valueOf(x_score));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 231 */     if ((this.graph instanceof UndirectedGraph))
/*     */     {
/* 233 */       for (V v : this.graph.getVertices()) {
/* 234 */         double v_score = ((Double)this.vertex_scores.get(v)).doubleValue();
/* 235 */         v_score /= 2.0D;
/* 236 */         this.vertex_scores.put(v, Double.valueOf(v_score));
/*     */       }
/* 238 */       for (E e : this.graph.getEdges()) {
/* 239 */         double e_score = ((Double)this.edge_scores.get(e)).doubleValue();
/* 240 */         e_score /= 2.0D;
/* 241 */         this.edge_scores.put(e, Double.valueOf(e_score));
/*     */       }
/*     */     }
/*     */     
/* 245 */     this.vertex_data.clear();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Double getVertexScore(V v)
/*     */   {
/* 310 */     return (Double)this.vertex_scores.get(v);
/*     */   }
/*     */   
/*     */   public Double getEdgeScore(E e)
/*     */   {
/* 315 */     return (Double)this.edge_scores.get(e);
/*     */   }
/*     */   
/*     */ 
/*     */   private class BetweennessData
/*     */   {
/*     */     double distance;
/*     */     double numSPs;
/*     */     List<E> incomingEdges;
/*     */     double dependency;
/*     */     
/*     */     BetweennessData()
/*     */     {
/* 328 */       this.distance = -1.0D;
/* 329 */       this.numSPs = 0.0D;
/*     */       
/* 331 */       this.incomingEdges = new ArrayList();
/* 332 */       this.dependency = 0.0D;
/*     */     }
/*     */     
/*     */ 
/*     */     public String toString()
/*     */     {
/* 338 */       return "[d:" + this.distance + ", sp:" + this.numSPs + ", p:" + this.incomingEdges + ", d:" + this.dependency + "]\n";
/*     */     }
/*     */   }
/*     */   
/*     */   private class BetweennessComparator implements Comparator<V>
/*     */   {
/*     */     private BetweennessComparator() {}
/*     */     
/*     */     public int compare(V v1, V v2)
/*     */     {
/* 348 */       return ((BetweennessCentrality.BetweennessData)BetweennessCentrality.this.vertex_data.get(v1)).distance > ((BetweennessCentrality.BetweennessData)BetweennessCentrality.this.vertex_data.get(v2)).distance ? 1 : -1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/BetweennessCentrality.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */