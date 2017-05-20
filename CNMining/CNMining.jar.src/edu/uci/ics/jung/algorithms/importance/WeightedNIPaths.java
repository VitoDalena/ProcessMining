/*     */ package edu.uci.ics.jung.algorithms.importance;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.DirectedGraph;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.util.Pair;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WeightedNIPaths<V, E>
/*     */   extends AbstractRanker<V, E>
/*     */ {
/*     */   public static final String WEIGHTED_NIPATHS_KEY = "jung.algorithms.importance.WEIGHTED_NIPATHS_KEY";
/*     */   private double mAlpha;
/*     */   private int mMaxDepth;
/*     */   private Set<V> mPriors;
/*  50 */   private Map<E, Number> pathIndices = new HashMap();
/*  51 */   private Map<Object, V> roots = new HashMap();
/*  52 */   private Map<V, Set<Number>> pathsSeenMap = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */   private Factory<V> vertexFactory;
/*     */   
/*     */ 
/*     */   private Factory<E> edgeFactory;
/*     */   
/*     */ 
/*     */ 
/*     */   public WeightedNIPaths(DirectedGraph<V, E> graph, Factory<V> vertexFactory, Factory<E> edgeFactory, double alpha, int maxDepth, Set<V> priors)
/*     */   {
/*  65 */     super.initialize(graph, true, false);
/*  66 */     this.vertexFactory = vertexFactory;
/*  67 */     this.edgeFactory = edgeFactory;
/*  68 */     this.mAlpha = alpha;
/*  69 */     this.mMaxDepth = maxDepth;
/*  70 */     this.mPriors = priors;
/*  71 */     for (V v : graph.getVertices()) {
/*  72 */       super.setVertexRankScore(v, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void incrementRankScore(V v, double rankValue) {
/*  77 */     setVertexRankScore(v, getVertexRankScore(v) + rankValue);
/*     */   }
/*     */   
/*     */   protected void computeWeightedPathsFromSource(V root, int depth)
/*     */   {
/*  82 */     int pathIdx = 1;
/*     */     
/*  84 */     for (E e : getGraph().getOutEdges(root)) {
/*  85 */       this.pathIndices.put(e, Integer.valueOf(pathIdx));
/*  86 */       this.roots.put(e, root);
/*  87 */       newVertexEncountered(pathIdx, getGraph().getEndpoints(e).getSecond(), root);
/*  88 */       pathIdx++;
/*     */     }
/*     */     
/*  91 */     List<E> edges = new ArrayList();
/*     */     
/*  93 */     V virtualNode = this.vertexFactory.create();
/*  94 */     getGraph().addVertex(virtualNode);
/*  95 */     E virtualSinkEdge = this.edgeFactory.create();
/*     */     
/*  97 */     getGraph().addEdge(virtualSinkEdge, virtualNode, root);
/*  98 */     edges.add(virtualSinkEdge);
/*     */     
/* 100 */     int currentDepth = 0;
/* 101 */     while (currentDepth <= depth)
/*     */     {
/* 103 */       double currentWeight = Math.pow(this.mAlpha, -1.0D * currentDepth);
/* 104 */       for (E currentEdge : edges) {
/* 105 */         incrementRankScore(getGraph().getEndpoints(currentEdge).getSecond(), currentWeight);
/*     */       }
/*     */       
/*     */ 
/* 109 */       if ((currentDepth == depth) || (edges.size() == 0))
/*     */         break;
/* 111 */       List<E> newEdges = new ArrayList();
/*     */       
/* 113 */       for (Iterator i$ = edges.iterator(); i$.hasNext();) { currentSourceEdge = i$.next();
/* 114 */         sourcePathIndex = (Number)this.pathIndices.get(currentSourceEdge);
/*     */         
/*     */ 
/*     */ 
/* 118 */         V newDestVertex = getGraph().getEndpoints(currentSourceEdge).getSecond();
/* 119 */         Collection<E> outs = getGraph().getOutEdges(newDestVertex);
/* 120 */         for (E currentDestEdge : outs) {
/* 121 */           V destEdgeRoot = this.roots.get(currentDestEdge);
/* 122 */           V destEdgeDest = getGraph().getEndpoints(currentDestEdge).getSecond();
/*     */           
/* 124 */           if (currentSourceEdge == virtualSinkEdge) {
/* 125 */             newEdges.add(currentDestEdge);
/*     */ 
/*     */           }
/* 128 */           else if ((destEdgeRoot != root) && 
/*     */           
/*     */ 
/* 131 */             (destEdgeDest != getGraph().getEndpoints(currentSourceEdge).getFirst()))
/*     */           {
/*     */ 
/* 134 */             Set<Number> pathsSeen = (Set)this.pathsSeenMap.get(destEdgeDest);
/*     */             
/* 136 */             if (pathsSeen == null) {
/* 137 */               newVertexEncountered(sourcePathIndex.intValue(), destEdgeDest, root);
/* 138 */             } else if (this.roots.get(destEdgeDest) != root) {
/* 139 */               this.roots.put(destEdgeDest, root);
/* 140 */               pathsSeen.clear();
/* 141 */               pathsSeen.add(sourcePathIndex);
/* 142 */             } else { if (pathsSeen.contains(sourcePathIndex)) continue;
/* 143 */               pathsSeen.add(sourcePathIndex);
/*     */             }
/*     */             
/*     */ 
/*     */ 
/* 148 */             this.pathIndices.put(currentDestEdge, sourcePathIndex);
/* 149 */             this.roots.put(currentDestEdge, root);
/* 150 */             newEdges.add(currentDestEdge);
/*     */           } } }
/*     */       E currentSourceEdge;
/*     */       Number sourcePathIndex;
/* 154 */       edges = newEdges;
/* 155 */       currentDepth++;
/*     */     }
/*     */     
/* 158 */     getGraph().removeVertex(virtualNode);
/*     */   }
/*     */   
/*     */   private void newVertexEncountered(int sourcePathIndex, V dest, V root) {
/* 162 */     Set<Number> pathsSeen = new HashSet();
/* 163 */     pathsSeen.add(Integer.valueOf(sourcePathIndex));
/* 164 */     this.pathsSeenMap.put(dest, pathsSeen);
/* 165 */     this.roots.put(dest, root);
/*     */   }
/*     */   
/*     */   public void step()
/*     */   {
/* 170 */     for (V v : this.mPriors) {
/* 171 */       computeWeightedPathsFromSource(v, this.mMaxDepth);
/*     */     }
/*     */     
/* 174 */     normalizeRankings();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getRankScoreKey()
/*     */   {
/* 185 */     return "jung.algorithms.importance.WEIGHTED_NIPATHS_KEY";
/*     */   }
/*     */   
/*     */   protected void onFinalize(Object udc)
/*     */   {
/* 190 */     this.pathIndices.remove(udc);
/* 191 */     this.roots.remove(udc);
/* 192 */     this.pathsSeenMap.remove(udc);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/importance/WeightedNIPaths.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */