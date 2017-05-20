/*     */ package edu.uci.ics.jung.algorithms.importance;
/*     */ 
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.DenseDoubleMatrix1D;
/*     */ import cern.colt.matrix.impl.SparseDoubleMatrix1D;
/*     */ import edu.uci.ics.jung.algorithms.matrix.GraphMatrixOperations;
/*     */ import edu.uci.ics.jung.algorithms.scoring.PageRank;
/*     */ import edu.uci.ics.jung.algorithms.util.Indexer;
/*     */ import edu.uci.ics.jung.graph.DirectedGraph;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.BidiMap;
/*     */ import org.apache.commons.collections15.functors.MapTransformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MarkovCentrality<V, E>
/*     */   extends RelativeAuthorityRanker<V, E>
/*     */ {
/*     */   public static final String MEAN_FIRST_PASSAGE_TIME = "jung.algorithms.importance.mean_first_passage_time";
/*     */   private DoubleMatrix1D mRankings;
/*     */   private BidiMap<V, Integer> mIndexer;
/*     */   
/*     */   public MarkovCentrality(DirectedGraph<V, E> graph, Set<V> rootNodes)
/*     */   {
/*  38 */     this(graph, rootNodes, null);
/*     */   }
/*     */   
/*     */   public MarkovCentrality(DirectedGraph<V, E> graph, Set<V> rootNodes, Map<E, Number> edgeWeightKey) {
/*  42 */     super.initialize(graph, true, false);
/*  43 */     setPriors(rootNodes);
/*  44 */     if (edgeWeightKey == null) {
/*  45 */       assignDefaultEdgeTransitionWeights();
/*     */     } else
/*  47 */       setEdgeWeights(edgeWeightKey);
/*  48 */     normalizeEdgeTransitionWeights();
/*     */     
/*  50 */     this.mIndexer = Indexer.create(graph.getVertices());
/*  51 */     this.mRankings = new SparseDoubleMatrix1D(graph.getVertexCount());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getRankScoreKey()
/*     */   {
/*  59 */     return "jung.algorithms.importance.mean_first_passage_time";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getVertexRankScore(V vert)
/*     */   {
/*  67 */     return this.mRankings.get(((Integer)this.mIndexer.get(vert)).intValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void step()
/*     */   {
/*  75 */     DoubleMatrix2D mFPTMatrix = GraphMatrixOperations.computeMeanFirstPassageMatrix(getGraph(), getEdgeWeights(), getStationaryDistribution());
/*     */     
/*  77 */     this.mRankings.assign(0.0D);
/*     */     
/*  79 */     for (V p : getPriors()) {
/*  80 */       p_id = ((Integer)this.mIndexer.get(p)).intValue();
/*  81 */       for (V v : getVertices()) {
/*  82 */         int v_id = ((Integer)this.mIndexer.get(v)).intValue();
/*  83 */         this.mRankings.set(v_id, this.mRankings.get(v_id) + mFPTMatrix.get(p_id, v_id));
/*     */       }
/*     */     }
/*     */     int p_id;
/*  87 */     for (V v : getVertices()) {
/*  88 */       int v_id = ((Integer)this.mIndexer.get(v)).intValue();
/*  89 */       this.mRankings.set(v_id, 1.0D / (this.mRankings.get(v_id) / getPriors().size()));
/*     */     }
/*     */     
/*  92 */     double total = this.mRankings.zSum();
/*     */     
/*  94 */     for (V v : getVertices()) {
/*  95 */       int v_id = ((Integer)this.mIndexer.get(v)).intValue();
/*  96 */       this.mRankings.set(v_id, this.mRankings.get(v_id) / total);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private DoubleMatrix1D getStationaryDistribution()
/*     */   {
/* 108 */     DoubleMatrix1D piVector = new DenseDoubleMatrix1D(getVertexCount());
/* 109 */     PageRank<V, E> pageRank = new PageRank(getGraph(), MapTransformer.getInstance(getEdgeWeights()), 0.0D);
/*     */     
/* 111 */     pageRank.evaluate();
/*     */     
/* 113 */     for (V v : getGraph().getVertices())
/* 114 */       piVector.set(((Integer)this.mIndexer.get(v)).intValue(), ((Double)pageRank.getVertexScore(v)).doubleValue());
/* 115 */     return piVector;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/importance/MarkovCentrality.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */