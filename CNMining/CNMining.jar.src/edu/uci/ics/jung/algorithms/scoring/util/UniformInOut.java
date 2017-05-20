/*    */ package edu.uci.ics.jung.algorithms.scoring.util;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import edu.uci.ics.jung.graph.util.EdgeType;
/*    */ import org.apache.commons.collections15.Transformer;
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
/*    */ public class UniformInOut<V, E>
/*    */   implements Transformer<VEPair<V, E>, Double>
/*    */ {
/*    */   protected Graph<V, E> graph;
/*    */   
/*    */   public UniformInOut(Graph<V, E> graph)
/*    */   {
/* 39 */     this.graph = graph;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Double transform(VEPair<V, E> ve_pair)
/*    */   {
/* 48 */     V v = ve_pair.getV();
/* 49 */     E e = ve_pair.getE();
/* 50 */     if (this.graph.getEdgeType(e) != EdgeType.DIRECTED) {
/* 51 */       throw new IllegalArgumentException("This transformer only operates on directed edges");
/*    */     }
/* 53 */     return Double.valueOf(1.0D / (this.graph.isSource(v, e) ? this.graph.outDegree(v) : this.graph.inDegree(v)));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/util/UniformInOut.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */