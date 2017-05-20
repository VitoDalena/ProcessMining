/*    */ package edu.uci.ics.jung.visualization.decorators;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.picking.PickedInfo;
/*    */ import java.awt.Paint;
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
/*    */ public class PickableEdgePaintTransformer<E>
/*    */   implements Transformer<E, Paint>
/*    */ {
/*    */   protected PickedInfo<E> pi;
/*    */   protected Paint draw_paint;
/*    */   protected Paint picked_paint;
/*    */   
/*    */   public PickableEdgePaintTransformer(PickedInfo<E> pi, Paint draw_paint, Paint picked_paint)
/*    */   {
/* 41 */     if (pi == null)
/* 42 */       throw new IllegalArgumentException("PickedInfo instance must be non-null");
/* 43 */     this.pi = pi;
/* 44 */     this.draw_paint = draw_paint;
/* 45 */     this.picked_paint = picked_paint;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Paint transform(E e)
/*    */   {
/* 52 */     if (this.pi.isPicked(e)) {
/* 53 */       return this.picked_paint;
/*    */     }
/*    */     
/* 56 */     return this.draw_paint;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/PickableEdgePaintTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */