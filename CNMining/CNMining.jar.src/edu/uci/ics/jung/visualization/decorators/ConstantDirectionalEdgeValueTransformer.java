/*    */ package edu.uci.ics.jung.visualization.decorators;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import edu.uci.ics.jung.graph.util.Context;
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
/*    */ public class ConstantDirectionalEdgeValueTransformer<V, E>
/*    */   implements Transformer<Context<Graph<V, E>, E>, Number>
/*    */ {
/*    */   protected Double undirected_value;
/*    */   protected Double directed_value;
/*    */   
/*    */   public ConstantDirectionalEdgeValueTransformer(double undirected, double directed)
/*    */   {
/* 38 */     this.undirected_value = new Double(undirected);
/* 39 */     this.directed_value = new Double(directed);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Number transform(Context<Graph<V, E>, E> context)
/*    */   {
/* 46 */     Graph<V, E> graph = (Graph)context.graph;
/* 47 */     E e = context.element;
/* 48 */     if (graph.getEdgeType(e) == EdgeType.DIRECTED) {
/* 49 */       return this.directed_value;
/*    */     }
/* 51 */     return this.undirected_value;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setUndirectedValue(double value)
/*    */   {
/* 60 */     this.undirected_value = Double.valueOf(value);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void setDirectedValue(double value)
/*    */   {
/* 69 */     this.directed_value = Double.valueOf(value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/ConstantDirectionalEdgeValueTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */