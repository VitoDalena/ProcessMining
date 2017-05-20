/*    */ package edu.uci.ics.jung.visualization.picking;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
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
/*    */ 
/*    */ 
/*    */ public class ClassicPickSupport<V, E>
/*    */   extends RadiusPickSupport<V, E>
/*    */   implements GraphElementAccessor<V, E>
/*    */ {
/*    */   public E getEdge(double x, double y)
/*    */   {
/* 39 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/picking/ClassicPickSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */