/*    */ package org.jfree.chart;
/*    */ 
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JScrollPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChartFrame
/*    */   extends JFrame
/*    */ {
/*    */   private ChartPanel chartPanel;
/*    */   
/*    */   public ChartFrame(String title, JFreeChart chart)
/*    */   {
/* 64 */     this(title, chart, false);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ChartFrame(String title, JFreeChart chart, boolean scrollPane)
/*    */   {
/* 76 */     super(title);
/* 77 */     setDefaultCloseOperation(2);
/* 78 */     this.chartPanel = new ChartPanel(chart);
/* 79 */     if (scrollPane) {
/* 80 */       setContentPane(new JScrollPane(this.chartPanel));
/*    */     }
/*    */     else {
/* 83 */       setContentPane(this.chartPanel);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public ChartPanel getChartPanel()
/*    */   {
/* 93 */     return this.chartPanel;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/ChartFrame.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */