/*     */ package edu.uci.ics.jung.algorithms.importance;
/*     */ 
/*     */ import edu.uci.ics.jung.algorithms.util.IterativeProcess;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.io.PrintStream;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.Format;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ import org.apache.commons.collections15.map.LazyMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractRanker<V, E>
/*     */   extends IterativeProcess
/*     */ {
/*     */   private Graph<V, E> mGraph;
/*     */   private List<Ranking<?>> mRankings;
/*     */   private boolean mRemoveRankScoresOnFinalize;
/*     */   private boolean mRankNodes;
/*     */   private boolean mRankEdges;
/*     */   private boolean mNormalizeRankings;
/*  51 */   protected Map<Object, Map<V, Number>> vertexRankScores = LazyMap.decorate(new HashMap(), new Factory()
/*     */   {
/*     */ 
/*     */     public Map<V, Number> create()
/*     */     {
/*  56 */       return new HashMap();
/*     */     }
/*  51 */   });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  58 */   protected Map<Object, Map<E, Number>> edgeRankScores = LazyMap.decorate(new HashMap(), new Factory()
/*     */   {
/*     */ 
/*     */     public Map<E, Number> create()
/*     */     {
/*  63 */       return new HashMap();
/*     */     }
/*  58 */   });
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  65 */   private Map<E, Number> edgeWeights = new HashMap();
/*     */   
/*     */   protected void initialize(Graph<V, E> graph, boolean isNodeRanker, boolean isEdgeRanker)
/*     */   {
/*  69 */     if ((!isNodeRanker) && (!isEdgeRanker))
/*  70 */       throw new IllegalArgumentException("Must rank edges, vertices, or both");
/*  71 */     this.mGraph = graph;
/*  72 */     this.mRemoveRankScoresOnFinalize = true;
/*  73 */     this.mNormalizeRankings = true;
/*  74 */     this.mRankNodes = isNodeRanker;
/*  75 */     this.mRankEdges = isEdgeRanker;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<Object, Map<V, Number>> getVertexRankScores()
/*     */   {
/*  82 */     return this.vertexRankScores;
/*     */   }
/*     */   
/*     */   public Map<Object, Map<E, Number>> getEdgeRankScores() {
/*  86 */     return this.edgeRankScores;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<V, Number> getVertexRankScores(Object key)
/*     */   {
/*  93 */     return (Map)this.vertexRankScores.get(key);
/*     */   }
/*     */   
/*     */   public Map<E, Number> getEdgeRankScores(Object key) {
/*  97 */     return (Map)this.edgeRankScores.get(key);
/*     */   }
/*     */   
/*     */   protected Collection<V> getVertices() {
/* 101 */     return this.mGraph.getVertices();
/*     */   }
/*     */   
/*     */   protected int getVertexCount() {
/* 105 */     return this.mGraph.getVertexCount();
/*     */   }
/*     */   
/*     */   protected Graph<V, E> getGraph() {
/* 109 */     return this.mGraph;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reset() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isRankingNodes()
/*     */   {
/* 121 */     return this.mRankNodes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isRankingEdges()
/*     */   {
/* 129 */     return this.mRankEdges;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRemoveRankScoresOnFinalize(boolean removeRankScoresOnFinalize)
/*     */   {
/* 138 */     this.mRemoveRankScoresOnFinalize = removeRankScoresOnFinalize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void onFinalize(Object e) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract Object getRankScoreKey();
/*     */   
/*     */ 
/*     */   protected void finalizeIterations()
/*     */   {
/* 152 */     List<Ranking<?>> sortedRankings = new ArrayList();
/*     */     
/* 154 */     int id = 1;
/* 155 */     if (this.mRankNodes) {
/* 156 */       for (V currentVertex : getVertices()) {
/* 157 */         Ranking<V> ranking = new Ranking(id, getVertexRankScore(currentVertex), currentVertex);
/* 158 */         sortedRankings.add(ranking);
/* 159 */         if (this.mRemoveRankScoresOnFinalize) {
/* 160 */           ((Map)this.vertexRankScores.get(getRankScoreKey())).remove(currentVertex);
/*     */         }
/* 162 */         id++;
/* 163 */         onFinalize(currentVertex);
/*     */       }
/*     */     }
/* 166 */     if (this.mRankEdges) {
/* 167 */       for (E currentEdge : this.mGraph.getEdges())
/*     */       {
/* 169 */         Ranking<E> ranking = new Ranking(id, getEdgeRankScore(currentEdge), currentEdge);
/* 170 */         sortedRankings.add(ranking);
/* 171 */         if (this.mRemoveRankScoresOnFinalize) {
/* 172 */           ((Map)this.edgeRankScores.get(getRankScoreKey())).remove(currentEdge);
/*     */         }
/* 174 */         id++;
/* 175 */         onFinalize(currentEdge);
/*     */       }
/*     */     }
/*     */     
/* 179 */     this.mRankings = sortedRankings;
/* 180 */     Collections.sort(this.mRankings);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Ranking<?>> getRankings()
/*     */   {
/* 190 */     return this.mRankings;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Double> getRankScores(int topKRankings)
/*     */   {
/* 199 */     List<Double> scores = new ArrayList();
/* 200 */     int count = 1;
/* 201 */     for (Ranking<?> currentRanking : getRankings()) {
/* 202 */       if (count > topKRankings) {
/* 203 */         return scores;
/*     */       }
/* 205 */       scores.add(Double.valueOf(currentRanking.rankScore));
/* 206 */       count++;
/*     */     }
/*     */     
/* 209 */     return scores;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getVertexRankScore(V v)
/*     */   {
/* 220 */     Number rankScore = (Number)((Map)this.vertexRankScores.get(getRankScoreKey())).get(v);
/* 221 */     if (rankScore != null) {
/* 222 */       return rankScore.doubleValue();
/*     */     }
/* 224 */     throw new RuntimeException("setRemoveRankScoresOnFinalize(false) must be called before evaluate().");
/*     */   }
/*     */   
/*     */   public double getVertexRankScore(V v, Object key)
/*     */   {
/* 229 */     return ((Number)((Map)this.vertexRankScores.get(key)).get(v)).doubleValue();
/*     */   }
/*     */   
/*     */   public double getEdgeRankScore(E e) {
/* 233 */     Number rankScore = (Number)((Map)this.edgeRankScores.get(getRankScoreKey())).get(e);
/* 234 */     if (rankScore != null) {
/* 235 */       return rankScore.doubleValue();
/*     */     }
/* 237 */     throw new RuntimeException("setRemoveRankScoresOnFinalize(false) must be called before evaluate().");
/*     */   }
/*     */   
/*     */   public double getEdgeRankScore(E e, Object key)
/*     */   {
/* 242 */     return ((Number)((Map)this.edgeRankScores.get(key)).get(e)).doubleValue();
/*     */   }
/*     */   
/*     */   protected void setVertexRankScore(V v, double rankValue, Object key) {
/* 246 */     ((Map)this.vertexRankScores.get(key)).put(v, Double.valueOf(rankValue));
/*     */   }
/*     */   
/*     */   protected void setEdgeRankScore(E e, double rankValue, Object key) {
/* 250 */     ((Map)this.edgeRankScores.get(key)).put(e, Double.valueOf(rankValue));
/*     */   }
/*     */   
/*     */   protected void setVertexRankScore(V v, double rankValue) {
/* 254 */     setVertexRankScore(v, rankValue, getRankScoreKey());
/*     */   }
/*     */   
/*     */   protected void setEdgeRankScore(E e, double rankValue) {
/* 258 */     setEdgeRankScore(e, rankValue, getRankScoreKey());
/*     */   }
/*     */   
/*     */   protected void removeVertexRankScore(V v, Object key) {
/* 262 */     ((Map)this.vertexRankScores.get(key)).remove(v);
/*     */   }
/*     */   
/*     */   protected void removeEdgeRankScore(E e, Object key) {
/* 266 */     ((Map)this.edgeRankScores.get(key)).remove(e);
/*     */   }
/*     */   
/*     */   protected void removeVertexRankScore(V v) {
/* 270 */     ((Map)this.vertexRankScores.get(getRankScoreKey())).remove(v);
/*     */   }
/*     */   
/*     */   protected void removeEdgeRankScore(E e) {
/* 274 */     ((Map)this.edgeRankScores.get(getRankScoreKey())).remove(e);
/*     */   }
/*     */   
/*     */   protected double getEdgeWeight(E e) {
/* 278 */     return ((Number)this.edgeWeights.get(e)).doubleValue();
/*     */   }
/*     */   
/*     */   protected void setEdgeWeight(E e, double weight) {
/* 282 */     this.edgeWeights.put(e, Double.valueOf(weight));
/*     */   }
/*     */   
/*     */   public void setEdgeWeights(Map<E, Number> edgeWeights) {
/* 286 */     this.edgeWeights = edgeWeights;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<E, Number> getEdgeWeights()
/*     */   {
/* 293 */     return this.edgeWeights;
/*     */   }
/*     */   
/*     */   protected void assignDefaultEdgeTransitionWeights()
/*     */   {
/* 298 */     for (V currentVertex : getVertices())
/*     */     {
/* 300 */       Collection<E> outgoingEdges = this.mGraph.getOutEdges(currentVertex);
/*     */       
/* 302 */       numOutEdges = outgoingEdges.size();
/* 303 */       for (E currentEdge : outgoingEdges) {
/* 304 */         setEdgeWeight(currentEdge, 1.0D / numOutEdges);
/*     */       }
/*     */     }
/*     */     double numOutEdges;
/*     */   }
/*     */   
/*     */   protected void normalizeEdgeTransitionWeights() {
/* 311 */     for (V currentVertex : getVertices())
/*     */     {
/* 313 */       Collection<E> outgoingEdges = this.mGraph.getOutEdges(currentVertex);
/*     */       
/* 315 */       totalEdgeWeight = 0.0D;
/* 316 */       for (E currentEdge : outgoingEdges) {
/* 317 */         totalEdgeWeight += getEdgeWeight(currentEdge);
/*     */       }
/*     */       
/* 320 */       for (E currentEdge : outgoingEdges)
/* 321 */         setEdgeWeight(currentEdge, getEdgeWeight(currentEdge) / totalEdgeWeight);
/*     */     }
/*     */     double totalEdgeWeight;
/*     */   }
/*     */   
/*     */   protected void normalizeRankings() {
/* 327 */     if (!this.mNormalizeRankings) {
/* 328 */       return;
/*     */     }
/* 330 */     double totalWeight = 0.0D;
/*     */     
/* 332 */     for (V currentVertex : getVertices()) {
/* 333 */       totalWeight += getVertexRankScore(currentVertex);
/*     */     }
/*     */     
/* 336 */     for (V currentVertex : getVertices()) {
/* 337 */       setVertexRankScore(currentVertex, getVertexRankScore(currentVertex) / totalWeight);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void printRankings(boolean verbose, boolean printScore)
/*     */   {
/* 348 */     double total = 0.0D;
/* 349 */     Format formatter = new DecimalFormat("#0.#######");
/* 350 */     int rank = 1;
/*     */     
/* 352 */     for (Ranking<?> currentRanking : getRankings()) {
/* 353 */       double rankScore = currentRanking.rankScore;
/* 354 */       if (verbose) {
/* 355 */         System.out.print("Rank " + rank + ": ");
/* 356 */         if (printScore) {
/* 357 */           System.out.print(formatter.format(Double.valueOf(rankScore)));
/*     */         }
/* 359 */         System.out.print("\tVertex Id: " + currentRanking.originalPos);
/* 360 */         System.out.print(" (" + currentRanking.getRanked() + ")");
/* 361 */         System.out.println();
/*     */       } else {
/* 363 */         System.out.print(rank + "\t");
/* 364 */         if (printScore) {
/* 365 */           System.out.print(formatter.format(Double.valueOf(rankScore)));
/*     */         }
/* 367 */         System.out.println("\t" + currentRanking.originalPos);
/*     */       }
/*     */       
/* 370 */       total += rankScore;
/* 371 */       rank++;
/*     */     }
/*     */     
/* 374 */     if (verbose) {
/* 375 */       System.out.println("Total: " + formatter.format(Double.valueOf(total)));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNormalizeRankings(boolean normalizeRankings)
/*     */   {
/* 386 */     this.mNormalizeRankings = normalizeRankings;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/importance/AbstractRanker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */