/*    */ package org.jfree.chart.renderer;
/*    */ 
/*    */ import org.jfree.chart.ChartRenderingInfo;
/*    */ import org.jfree.chart.entity.EntityCollection;
/*    */ import org.jfree.chart.plot.PlotRenderingInfo;
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
/*    */ public class RendererState
/*    */ {
/*    */   private PlotRenderingInfo info;
/*    */   
/*    */   public RendererState(PlotRenderingInfo info)
/*    */   {
/* 63 */     this.info = info;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public PlotRenderingInfo getInfo()
/*    */   {
/* 72 */     return this.info;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public EntityCollection getEntityCollection()
/*    */   {
/* 83 */     EntityCollection result = null;
/* 84 */     if (this.info != null) {
/* 85 */       ChartRenderingInfo owner = this.info.getOwner();
/* 86 */       if (owner != null) {
/* 87 */         result = owner.getEntityCollection();
/*    */       }
/*    */     }
/* 90 */     return result;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/RendererState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */