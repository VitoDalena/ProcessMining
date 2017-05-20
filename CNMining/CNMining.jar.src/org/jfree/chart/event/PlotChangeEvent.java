/*    */ package org.jfree.chart.event;
/*    */ 
/*    */ import org.jfree.chart.plot.Plot;
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
/*    */ 
/*    */ 
/*    */ public class PlotChangeEvent
/*    */   extends ChartChangeEvent
/*    */ {
/*    */   private Plot plot;
/*    */   
/*    */   public PlotChangeEvent(Plot plot)
/*    */   {
/* 64 */     super(plot);
/* 65 */     this.plot = plot;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Plot getPlot()
/*    */   {
/* 74 */     return this.plot;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/event/PlotChangeEvent.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */