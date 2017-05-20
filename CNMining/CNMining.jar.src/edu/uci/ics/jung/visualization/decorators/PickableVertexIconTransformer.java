/*    */ package edu.uci.ics.jung.visualization.decorators;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.picking.PickedInfo;
/*    */ import javax.swing.Icon;
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
/*    */ public class PickableVertexIconTransformer<V>
/*    */   implements Transformer<V, Icon>
/*    */ {
/*    */   protected Icon icon;
/*    */   protected Icon picked_icon;
/*    */   protected PickedInfo<V> pi;
/*    */   
/*    */   public PickableVertexIconTransformer(PickedInfo<V> pi, Icon icon, Icon picked_icon)
/*    */   {
/* 39 */     if (pi == null)
/* 40 */       throw new IllegalArgumentException("PickedInfo instance must be non-null");
/* 41 */     this.pi = pi;
/* 42 */     this.icon = icon;
/* 43 */     this.picked_icon = picked_icon;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Icon transform(V v)
/*    */   {
/* 50 */     if (this.pi.isPicked(v)) {
/* 51 */       return this.picked_icon;
/*    */     }
/* 53 */     return this.icon;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/decorators/PickableVertexIconTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */