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
/*    */ public class PickableVertexPaintTransformer<V>
/*    */   implements Transformer<V, Paint>
/*    */ {
/*    */   protected Paint fill_paint;
/*    */   protected Paint picked_paint;
/*    */   protected PickedInfo<V> pi;
/*    */   
/*    */   public PickableVertexPaintTransformer(PickedInfo<V> pi, Paint fill_paint, Paint picked_paint)
/*    */   {
/* 41 */     if (pi == null)
/* 42 */       throw new IllegalArgumentException("PickedInfo instance must be non-null");
/* 43 */     this.pi = pi;
/* 44 */     this.fill_paint = fill_paint;
/* 45 */     this.picked_paint = picked_paint;
/*    */   }
/*    */   
/*    */   public Paint transform(V v)
/*    */   {
/* 50 */     if (this.pi.isPicked(v)) {
/* 51 */       return this.picked_paint;
/*    */     }
/* 53 */     return this.fill_paint;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/PickableVertexPaintTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */