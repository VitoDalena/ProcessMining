/*     */ package org.jfree.chart.demo;
/*     */ 
/*     */ import javax.swing.JPanel;
/*     */ import org.jfree.chart.ChartFactory;
/*     */ import org.jfree.chart.ChartPanel;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.plot.PiePlot;
/*     */ import org.jfree.data.general.DefaultPieDataset;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ import org.jfree.ui.ApplicationFrame;
/*     */ import org.jfree.ui.RefineryUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PieChartDemo1
/*     */   extends ApplicationFrame
/*     */ {
/*     */   public PieChartDemo1(String title)
/*     */   {
/*  67 */     super(title);
/*  68 */     setContentPane(createDemoPanel());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static PieDataset createDataset()
/*     */   {
/*  77 */     DefaultPieDataset dataset = new DefaultPieDataset();
/*  78 */     dataset.setValue("One", new Double(43.2D));
/*  79 */     dataset.setValue("Two", new Double(10.0D));
/*  80 */     dataset.setValue("Three", new Double(27.5D));
/*  81 */     dataset.setValue("Four", new Double(17.5D));
/*  82 */     dataset.setValue("Five", new Double(11.0D));
/*  83 */     dataset.setValue("Six", new Double(19.4D));
/*  84 */     return dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static JFreeChart createChart(PieDataset dataset)
/*     */   {
/*  96 */     JFreeChart chart = ChartFactory.createPieChart("Pie Chart Demo 1", dataset, true, true, false);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 104 */     PiePlot plot = (PiePlot)chart.getPlot();
/* 105 */     plot.setSectionOutlinesVisible(false);
/* 106 */     plot.setNoDataMessage("No data available");
/*     */     
/* 108 */     return chart;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static JPanel createDemoPanel()
/*     */   {
/* 118 */     JFreeChart chart = createChart(createDataset());
/* 119 */     return new ChartPanel(chart);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 137 */     PieChartDemo1 demo = new PieChartDemo1("Pie Chart Demo 1");
/* 138 */     demo.pack();
/* 139 */     RefineryUtilities.centerFrameOnScreen(demo);
/* 140 */     demo.setVisible(true);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/demo/PieChartDemo1.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */