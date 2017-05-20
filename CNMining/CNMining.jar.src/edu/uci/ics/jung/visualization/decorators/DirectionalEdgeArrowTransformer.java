/*    */ package edu.uci.ics.jung.visualization.decorators;
/*    */ 
/*    */ import edu.uci.ics.jung.graph.Graph;
/*    */ import edu.uci.ics.jung.graph.util.Context;
/*    */ import edu.uci.ics.jung.graph.util.EdgeType;
/*    */ import edu.uci.ics.jung.visualization.util.ArrowFactory;
/*    */ import java.awt.Shape;
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
/*    */ public class DirectionalEdgeArrowTransformer<V, E>
/*    */   implements Transformer<Context<Graph<V, E>, E>, Shape>
/*    */ {
/*    */   protected Shape undirected_arrow;
/*    */   protected Shape directed_arrow;
/*    */   
/*    */   public DirectionalEdgeArrowTransformer(int length, int width, int notch_depth)
/*    */   {
/* 35 */     this.directed_arrow = ArrowFactory.getNotchedArrow(width, length, notch_depth);
/* 36 */     this.undirected_arrow = ArrowFactory.getWedgeArrow(width, length);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public Shape transform(Context<Graph<V, E>, E> context)
/*    */   {
/* 44 */     if (((Graph)context.graph).getEdgeType(context.element) == EdgeType.DIRECTED) {
/* 45 */       return this.directed_arrow;
/*    */     }
/* 47 */     return this.undirected_arrow;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/DirectionalEdgeArrowTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */