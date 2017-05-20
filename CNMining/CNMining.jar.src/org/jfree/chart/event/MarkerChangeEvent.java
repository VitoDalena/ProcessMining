/*    */ package org.jfree.chart.event;
/*    */ 
/*    */ import org.jfree.chart.plot.Marker;
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
/*    */ 
/*    */ public class MarkerChangeEvent
/*    */   extends ChartChangeEvent
/*    */ {
/*    */   private Marker marker;
/*    */   
/*    */   public MarkerChangeEvent(Marker marker)
/*    */   {
/* 65 */     super(marker);
/* 66 */     this.marker = marker;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Marker getMarker()
/*    */   {
/* 77 */     return this.marker;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/event/MarkerChangeEvent.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */