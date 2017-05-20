/*    */ package edu.uci.ics.jung.algorithms.importance;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import edu.uci.ics.jung.graph.UndirectedGraph;
/*    */ import org.apache.commons.collections15.BidiMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RandomWalkBetweenness<V, E>
/*    */   extends RandomWalkSTBetweenness<V, E>
/*    */ {
/*    */   public static final String CENTRALITY = "centrality.RandomWalkBetweennessCentrality";
/*    */   
/*    */   public RandomWalkBetweenness(UndirectedGraph<V, E> g)
/*    */   {
/* 40 */     super(g, null, null);
/*    */   }
/*    */   
/*    */   protected void computeBetweenness()
/*    */   {
/* 45 */     setUp();
/*    */     
/* 47 */     int numVertices = getGraph().getVertexCount();
/* 48 */     double normalizingConstant = numVertices * (numVertices - 1) / 2.0D;
/*    */     
/* 50 */     for (V ithVertex : getGraph().getVertices())
/*    */     {
/* 52 */       double ithBetweenness = 0.0D;
/* 53 */       for (int t = 0; t < numVertices; t++) {
/* 54 */         for (int s = 0; s < t; s++) {
/* 55 */           V sthVertex = getIndexer().getKey(Integer.valueOf(s));
/* 56 */           V tthVertex = getIndexer().getKey(Integer.valueOf(t));
/* 57 */           ithBetweenness += computeSTBetweenness(ithVertex, sthVertex, tthVertex);
/*    */         }
/*    */       }
/* 60 */       setVertexRankScore(ithVertex, ithBetweenness / normalizingConstant);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String getRankScoreKey()
/*    */   {
/* 72 */     return "centrality.RandomWalkBetweennessCentrality";
/*    */   }
/*    */   
/*    */   protected double evaluateIteration() {
/* 76 */     computeBetweenness();
/* 77 */     return 0.0D;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/importance/RandomWalkBetweenness.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */