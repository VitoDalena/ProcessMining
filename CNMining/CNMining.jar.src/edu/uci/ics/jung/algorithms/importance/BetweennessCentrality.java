/*     */ package edu.uci.ics.jung.algorithms.importance;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Stack;
/*     */ import org.apache.commons.collections15.Buffer;
/*     */ import org.apache.commons.collections15.buffer.UnboundedFifoBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends AbstractRanker<V, E>
/*     */ {
/*     */   public static final String CENTRALITY = "centrality.BetweennessCentrality";
/*     */   
/*     */   public BetweennessCentrality(Graph<V, E> g)
/*     */   {
/*  53 */     initialize(g, true, true);
/*     */   }
/*     */   
/*     */   public BetweennessCentrality(Graph<V, E> g, boolean rankNodes) {
/*  57 */     initialize(g, rankNodes, true);
/*     */   }
/*     */   
/*     */   public BetweennessCentrality(Graph<V, E> g, boolean rankNodes, boolean rankEdges)
/*     */   {
/*  62 */     initialize(g, rankNodes, rankEdges);
/*     */   }
/*     */   
/*     */   protected void computeBetweenness(Graph<V, E> graph)
/*     */   {
/*  67 */     Map<V, BetweennessCentrality<V, E>.BetweennessData> decorator = new HashMap();
/*  68 */     Map<V, Number> bcVertexDecorator = (Map)this.vertexRankScores.get(getRankScoreKey());
/*     */     
/*  70 */     bcVertexDecorator.clear();
/*  71 */     Map<E, Number> bcEdgeDecorator = (Map)this.edgeRankScores.get(getRankScoreKey());
/*     */     
/*  73 */     bcEdgeDecorator.clear();
/*     */     
/*  75 */     Collection<V> vertices = graph.getVertices();
/*     */     
/*  77 */     for (V s : vertices)
/*     */     {
/*  79 */       initializeData(graph, decorator);
/*     */       
/*  81 */       ((BetweennessData)decorator.get(s)).numSPs = 1.0D;
/*  82 */       ((BetweennessData)decorator.get(s)).distance = 0.0D;
/*     */       
/*  84 */       Stack<V> stack = new Stack();
/*  85 */       Buffer<V> queue = new UnboundedFifoBuffer();
/*  86 */       queue.add(s);
/*     */       V v;
/*  88 */       while (!queue.isEmpty()) {
/*  89 */         v = queue.remove();
/*  90 */         stack.push(v);
/*     */         
/*  92 */         for (V w : getGraph().getSuccessors(v))
/*     */         {
/*  94 */           if (((BetweennessData)decorator.get(w)).distance < 0.0D) {
/*  95 */             queue.add(w);
/*  96 */             ((BetweennessData)decorator.get(v)).distance += 1.0D;
/*     */           }
/*     */           
/*  99 */           if (((BetweennessData)decorator.get(w)).distance == ((BetweennessData)decorator.get(v)).distance + 1.0D) {
/* 100 */             ((BetweennessData)decorator.get(w)).numSPs += ((BetweennessData)decorator.get(v)).numSPs;
/* 101 */             ((BetweennessData)decorator.get(w)).predecessors.add(v);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 106 */       while (!stack.isEmpty()) {
/* 107 */         V w = stack.pop();
/*     */         
/* 109 */         for (V v : ((BetweennessData)decorator.get(w)).predecessors)
/*     */         {
/* 111 */           double partialDependency = ((BetweennessData)decorator.get(v)).numSPs / ((BetweennessData)decorator.get(w)).numSPs;
/* 112 */           partialDependency *= (1.0D + ((BetweennessData)decorator.get(w)).dependency);
/* 113 */           ((BetweennessData)decorator.get(v)).dependency += partialDependency;
/* 114 */           E currentEdge = getGraph().findEdge(v, w);
/* 115 */           double edgeValue = ((Number)bcEdgeDecorator.get(currentEdge)).doubleValue();
/* 116 */           edgeValue += partialDependency;
/* 117 */           bcEdgeDecorator.put(currentEdge, Double.valueOf(edgeValue));
/*     */         }
/* 119 */         if (w != s) {
/* 120 */           double bcValue = ((Number)bcVertexDecorator.get(w)).doubleValue();
/* 121 */           bcValue += ((BetweennessData)decorator.get(w)).dependency;
/* 122 */           bcVertexDecorator.put(w, Double.valueOf(bcValue));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 127 */     if ((graph instanceof UndirectedGraph)) {
/* 128 */       for (V v : vertices) {
/* 129 */         double bcValue = ((Number)bcVertexDecorator.get(v)).doubleValue();
/* 130 */         bcValue /= 2.0D;
/* 131 */         bcVertexDecorator.put(v, Double.valueOf(bcValue));
/*     */       }
/* 133 */       for (E e : graph.getEdges()) {
/* 134 */         double bcValue = ((Number)bcEdgeDecorator.get(e)).doubleValue();
/* 135 */         bcValue /= 2.0D;
/* 136 */         bcEdgeDecorator.put(e, Double.valueOf(bcValue));
/*     */       }
/*     */     }
/*     */     
/* 140 */     for (V vertex : vertices) {
/* 141 */       decorator.remove(vertex);
/*     */     }
/*     */   }
/*     */   
/*     */   private void initializeData(Graph<V, E> g, Map<V, BetweennessCentrality<V, E>.BetweennessData> decorator) {
/* 146 */     for (V vertex : g.getVertices())
/*     */     {
/* 148 */       Map<V, Number> bcVertexDecorator = (Map)this.vertexRankScores.get(getRankScoreKey());
/* 149 */       if (!bcVertexDecorator.containsKey(vertex)) {
/* 150 */         bcVertexDecorator.put(vertex, Double.valueOf(0.0D));
/*     */       }
/* 152 */       decorator.put(vertex, new BetweennessData());
/*     */     }
/* 154 */     for (E e : g.getEdges())
/*     */     {
/* 156 */       Map<E, Number> bcEdgeDecorator = (Map)this.edgeRankScores.get(getRankScoreKey());
/* 157 */       if (!bcEdgeDecorator.containsKey(e)) {
/* 158 */         bcEdgeDecorator.put(e, Double.valueOf(0.0D));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getRankScoreKey()
/*     */   {
/* 169 */     return "centrality.BetweennessCentrality";
/*     */   }
/*     */   
/*     */   public void step()
/*     */   {
/* 174 */     computeBetweenness(getGraph());
/*     */   }
/*     */   
/*     */   class BetweennessData {
/*     */     double distance;
/*     */     double numSPs;
/*     */     List<V> predecessors;
/*     */     double dependency;
/*     */     
/*     */     BetweennessData() {
/* 184 */       this.distance = -1.0D;
/* 185 */       this.numSPs = 0.0D;
/* 186 */       this.predecessors = new ArrayList();
/* 187 */       this.dependency = 0.0D;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/importance/BetweennessCentrality.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */