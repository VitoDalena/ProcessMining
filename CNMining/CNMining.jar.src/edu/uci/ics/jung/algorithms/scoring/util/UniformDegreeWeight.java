/*    */ package edu.uci.ics.jung.algorithms.scoring.util;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Hypergraph;
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
/*    */ 
/*    */ public class UniformDegreeWeight<V, E>
/*    */   implements Transformer<VEPair<V, E>, Double>
/*    */ {
/*    */   private Hypergraph<V, E> graph;
/*    */   
/*    */   public UniformDegreeWeight(Hypergraph<V, E> graph)
/*    */   {
/* 40 */     this.graph = graph;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Double transform(VEPair<V, E> ve_pair)
/*    */   {
/* 48 */     E e = ve_pair.getE();
/* 49 */     V v = ve_pair.getV();
/* 50 */     EdgeType edge_type = this.graph.getEdgeType(e);
/* 51 */     if (edge_type == EdgeType.UNDIRECTED)
/* 52 */       return Double.valueOf(1.0D / this.graph.degree(v));
/* 53 */     if (edge_type == EdgeType.DIRECTED)
/* 54 */       return Double.valueOf(1.0D / this.graph.outDegree(this.graph.getSource(e)));
/* 55 */     throw new IllegalArgumentException("can't handle edge type: " + edge_type);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/algorithms/scoring/util/UniformDegreeWeight.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */