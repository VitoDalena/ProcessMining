/*     */ package edu.uci.ics.jung.algorithms.importance;
/*     */ 
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import edu.uci.ics.jung.algorithms.matrix.GraphMatrixOperations;
/*     */ import edu.uci.ics.jung.algorithms.util.Indexer;
/*     */ import edu.uci.ics.jung.graph.Graph;
/*     */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*     */ import org.apache.commons.collections15.BidiMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomWalkSTBetweenness<V, E>
/*     */   extends AbstractRanker<V, E>
/*     */ {
/*     */   public static final String CENTRALITY = "centrality.RandomWalkSTBetweennessCentrality";
/*     */   private DoubleMatrix2D mVoltageMatrix;
/*     */   private BidiMap<V, Integer> mIndexer;
/*     */   V mSource;
/*     */   V mTarget;
/*     */   
/*     */   public RandomWalkSTBetweenness(UndirectedGraph<V, E> g, V s, V t)
/*     */   {
/*  52 */     initialize(g, true, false);
/*  53 */     this.mSource = s;
/*  54 */     this.mTarget = t;
/*     */   }
/*     */   
/*     */   protected BidiMap<V, Integer> getIndexer() {
/*  58 */     return this.mIndexer;
/*     */   }
/*     */   
/*     */   protected DoubleMatrix2D getVoltageMatrix() {
/*  62 */     return this.mVoltageMatrix;
/*     */   }
/*     */   
/*     */   protected void setUp() {
/*  66 */     this.mVoltageMatrix = GraphMatrixOperations.computeVoltagePotentialMatrix((UndirectedGraph)getGraph());
/*  67 */     this.mIndexer = Indexer.create(getGraph().getVertices());
/*     */   }
/*     */   
/*     */   protected void computeBetweenness() {
/*  71 */     setUp();
/*     */     
/*  73 */     for (V v : getGraph().getVertices()) {
/*  74 */       setVertexRankScore(v, computeSTBetweenness(v, this.mSource, this.mTarget));
/*     */     }
/*     */   }
/*     */   
/*     */   public double computeSTBetweenness(V ithVertex, V source, V target) {
/*  79 */     if ((ithVertex == source) || (ithVertex == target)) return 1.0D;
/*  80 */     if (this.mVoltageMatrix == null) {
/*  81 */       setUp();
/*     */     }
/*  83 */     int i = ((Integer)this.mIndexer.get(ithVertex)).intValue();
/*  84 */     int s = ((Integer)this.mIndexer.get(source)).intValue();
/*  85 */     int t = ((Integer)this.mIndexer.get(target)).intValue();
/*     */     
/*  87 */     double betweenness = 0.0D;
/*  88 */     for (V jthVertex : getGraph().getSuccessors(ithVertex)) {
/*  89 */       int j = ((Integer)this.mIndexer.get(jthVertex)).intValue();
/*  90 */       double currentFlow = 0.0D;
/*  91 */       currentFlow += this.mVoltageMatrix.get(i, s);
/*  92 */       currentFlow -= this.mVoltageMatrix.get(i, t);
/*  93 */       currentFlow -= this.mVoltageMatrix.get(j, s);
/*  94 */       currentFlow += this.mVoltageMatrix.get(j, t);
/*  95 */       betweenness += Math.abs(currentFlow);
/*     */     }
/*  97 */     return betweenness / 2.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getRankScoreKey()
/*     */   {
/* 106 */     return "centrality.RandomWalkSTBetweennessCentrality";
/*     */   }
/*     */   
/*     */   public void step()
/*     */   {
/* 111 */     computeBetweenness();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/importance/RandomWalkSTBetweenness.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */