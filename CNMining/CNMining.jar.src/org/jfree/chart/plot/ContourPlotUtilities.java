/*    */ package org.jfree.chart.plot;
/*    */ 
/*    */ import org.jfree.data.Range;
/*    */ import org.jfree.data.contour.ContourDataset;
/*    */ import org.jfree.data.contour.DefaultContourDataset;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public abstract class ContourPlotUtilities
/*    */ {
/*    */   public static Range visibleRange(ContourDataset data, Range x, Range y)
/*    */   {
/* 71 */     Range range = null;
/* 72 */     range = ((DefaultContourDataset)data).getZValueRange(x, y);
/* 73 */     return range;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/ContourPlotUtilities.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */