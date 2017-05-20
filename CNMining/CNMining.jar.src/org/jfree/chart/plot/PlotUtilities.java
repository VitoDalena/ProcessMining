/*    */ package org.jfree.chart.plot;
/*    */ 
/*    */ import org.jfree.data.general.DatasetUtilities;
/*    */ import org.jfree.data.xy.XYDataset;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlotUtilities
/*    */ {
/*    */   public static boolean isEmptyOrNull(XYPlot plot)
/*    */   {
/* 65 */     if (plot != null) {
/* 66 */       int i = 0; for (int n = plot.getDatasetCount(); i < n; i++) {
/* 67 */         XYDataset dataset = plot.getDataset(i);
/* 68 */         if (!DatasetUtilities.isEmptyOrNull(dataset)) {
/* 69 */           return false;
/*    */         }
/*    */       }
/*    */     }
/* 73 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/PlotUtilities.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */