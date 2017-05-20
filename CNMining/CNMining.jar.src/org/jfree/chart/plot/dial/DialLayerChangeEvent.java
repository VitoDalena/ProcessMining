/*    */ package org.jfree.chart.plot.dial;
/*    */ 
/*    */ import org.jfree.chart.event.ChartChangeEvent;
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
/*    */ public class DialLayerChangeEvent
/*    */   extends ChartChangeEvent
/*    */ {
/*    */   private DialLayer layer;
/*    */   
/*    */   public DialLayerChangeEvent(DialLayer layer)
/*    */   {
/* 62 */     super(layer);
/* 63 */     this.layer = layer;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DialLayer getDialLayer()
/*    */   {
/* 72 */     return this.layer;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/DialLayerChangeEvent.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */