/*     */ package edu.uci.ics.jung.algorithms.importance;
/*     */ 
/*     */ import edu.uci.ics.jung.graph.DirectedGraph;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends RelativeAuthorityRanker<V, E>
/*     */ {
/*     */   public static final String RANK_SCORE = "jung.algorithms.importance.KStepMarkovExperimental.RankScore";
/*     */   private static final String CURRENT_RANK = "jung.algorithms.importance.KStepMarkovExperimental.CurrentRank";
/*     */   private int mNumSteps;
/*     */   HashMap<V, Number> mPreviousRankingsMap;
/*     */   
/*     */   public KStepMarkov(DirectedGraph<V, E> graph, Set<V> priors, int k, Map<E, Number> edgeWeights)
/*     */   {
/*  54 */     super.initialize(graph, true, false);
/*  55 */     this.mNumSteps = k;
/*  56 */     setPriors(priors);
/*  57 */     initializeRankings();
/*  58 */     if (edgeWeights == null) {
/*  59 */       assignDefaultEdgeTransitionWeights();
/*     */     } else {
/*  61 */       setEdgeWeights(edgeWeights);
/*     */     }
/*  63 */     normalizeEdgeTransitionWeights();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getRankScoreKey()
/*     */   {
/*  72 */     return "jung.algorithms.importance.KStepMarkovExperimental.RankScore";
/*     */   }
/*     */   
/*     */   protected void incrementRankScore(V v, double rankValue) {
/*  76 */     double value = getVertexRankScore(v, "jung.algorithms.importance.KStepMarkovExperimental.RankScore");
/*  77 */     value += rankValue;
/*  78 */     setVertexRankScore(v, value, "jung.algorithms.importance.KStepMarkovExperimental.RankScore");
/*     */   }
/*     */   
/*     */   protected double getCurrentRankScore(V v) {
/*  82 */     return getVertexRankScore(v, "jung.algorithms.importance.KStepMarkovExperimental.CurrentRank");
/*     */   }
/*     */   
/*     */   protected void setCurrentRankScore(V v, double rankValue) {
/*  86 */     setVertexRankScore(v, rankValue, "jung.algorithms.importance.KStepMarkovExperimental.CurrentRank");
/*     */   }
/*     */   
/*     */   protected void initializeRankings() {
/*  90 */     this.mPreviousRankingsMap = new HashMap();
/*  91 */     for (V v : getVertices()) {
/*  92 */       Set<V> priors = getPriors();
/*  93 */       double numPriors = priors.size();
/*     */       
/*  95 */       if (getPriors().contains(v)) {
/*  96 */         setVertexRankScore(v, 1.0D / numPriors);
/*  97 */         setCurrentRankScore(v, 1.0D / numPriors);
/*  98 */         this.mPreviousRankingsMap.put(v, Double.valueOf(1.0D / numPriors));
/*     */       } else {
/* 100 */         setVertexRankScore(v, 0.0D);
/* 101 */         setCurrentRankScore(v, 0.0D);
/* 102 */         this.mPreviousRankingsMap.put(v, Integer.valueOf(0));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void step()
/*     */   {
/* 109 */     for (int i = 0; i < this.mNumSteps; i++) {
/* 110 */       updateRankings();
/* 111 */       for (V v : getVertices()) {
/* 112 */         double currentRankScore = getCurrentRankScore(v);
/* 113 */         incrementRankScore(v, currentRankScore);
/* 114 */         this.mPreviousRankingsMap.put(v, Double.valueOf(currentRankScore));
/*     */       }
/*     */     }
/* 117 */     normalizeRankings();
/*     */   }
/*     */   
/*     */   protected void updateRankings()
/*     */   {
/* 122 */     for (V v : getVertices())
/*     */     {
/* 124 */       Collection<E> incomingEdges = getGraph().getInEdges(v);
/*     */       
/* 126 */       double currentPageRankSum = 0.0D;
/* 127 */       for (E e : incomingEdges) {
/* 128 */         double currentWeight = getEdgeWeight(e);
/* 129 */         currentPageRankSum += ((Number)this.mPreviousRankingsMap.get(getGraph().getOpposite(v, e))).doubleValue() * currentWeight;
/*     */       }
/*     */       
/* 132 */       setCurrentRankScore(v, currentPageRankSum);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/importance/KStepMarkov.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */