/*    */ package edu.uci.ics.jung.visualization.picking;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
/*    */ import edu.uci.ics.jung.algorithms.layout.Layout;
/*    */ import edu.uci.ics.jung.algorithms.layout.RadiusGraphElementAccessor;
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
/*    */ public class RadiusPickSupport<V, E>
/*    */   extends RadiusGraphElementAccessor<V, E>
/*    */   implements GraphElementAccessor<V, E>
/*    */ {
/*    */   public RadiusPickSupport()
/*    */   {
/* 35 */     this(Math.sqrt(Double.MAX_VALUE));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public RadiusPickSupport(double maxDistance)
/*    */   {
/* 44 */     super(maxDistance);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public V getVertex(Layout<V, E> layout, double x, double y)
/*    */   {
/* 54 */     return (V)getVertex(layout, x, y, this.maxDistance);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public V getVertex(Layout<V, E> layout, double x, double y, double maxDistance)
/*    */   {
/* 67 */     return (V)super.getVertex(layout, x, y, maxDistance);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public E getEdge(Layout<V, E> layout, double x, double y)
/*    */   {
/* 75 */     return (E)getEdge(layout, x, y, this.maxDistance);
/*    */   }
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
/*    */   public E getEdge(Layout<V, E> layout, double x, double y, double maxDistance)
/*    */   {
/* 90 */     return (E)super.getEdge(layout, x, y, maxDistance);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/picking/RadiusPickSupport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */