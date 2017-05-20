/*    */ package org.jfree.chart.editor;
/*    */ 
/*    */ import org.jfree.chart.JFreeChart;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChartEditorManager
/*    */ {
/* 54 */   static ChartEditorFactory factory = new DefaultChartEditorFactory();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static ChartEditorFactory getChartEditorFactory()
/*    */   {
/* 69 */     return factory;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void setChartEditorFactory(ChartEditorFactory f)
/*    */   {
/* 78 */     if (f == null) {
/* 79 */       throw new IllegalArgumentException("Null 'f' argument.");
/*    */     }
/* 81 */     factory = f;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static ChartEditor getChartEditor(JFreeChart chart)
/*    */   {
/* 92 */     return factory.createEditor(chart);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/editor/ChartEditorManager.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */