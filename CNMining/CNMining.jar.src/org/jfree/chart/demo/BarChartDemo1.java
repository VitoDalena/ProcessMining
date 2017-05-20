/*     */ package org.jfree.chart.demo;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GradientPaint;
/*     */ import org.jfree.chart.ChartFactory;
/*     */ import org.jfree.chart.ChartPanel;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.CategoryLabelPositions;
/*     */ import org.jfree.chart.axis.NumberAxis;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.renderer.category.BarRenderer;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.DefaultCategoryDataset;
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
/*     */ public class BarChartDemo1
/*     */   extends ApplicationFrame
/*     */ {
/*     */   public BarChartDemo1(String title)
/*     */   {
/*  72 */     super(title);
/*  73 */     CategoryDataset dataset = createDataset();
/*  74 */     JFreeChart chart = createChart(dataset);
/*  75 */     ChartPanel chartPanel = new ChartPanel(chart);
/*  76 */     chartPanel.setFillZoomRectangle(true);
/*  77 */     chartPanel.setMouseWheelEnabled(true);
/*  78 */     chartPanel.setPreferredSize(new Dimension(500, 270));
/*  79 */     setContentPane(chartPanel);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static CategoryDataset createDataset()
/*     */   {
/*  90 */     String series1 = "First";
/*  91 */     String series2 = "Second";
/*  92 */     String series3 = "Third";
/*     */     
/*     */ 
/*  95 */     String category1 = "Category 1";
/*  96 */     String category2 = "Category 2";
/*  97 */     String category3 = "Category 3";
/*  98 */     String category4 = "Category 4";
/*  99 */     String category5 = "Category 5";
/*     */     
/*     */ 
/* 102 */     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
/*     */     
/* 104 */     dataset.addValue(1.0D, series1, category1);
/* 105 */     dataset.addValue(4.0D, series1, category2);
/* 106 */     dataset.addValue(3.0D, series1, category3);
/* 107 */     dataset.addValue(5.0D, series1, category4);
/* 108 */     dataset.addValue(5.0D, series1, category5);
/*     */     
/* 110 */     dataset.addValue(5.0D, series2, category1);
/* 111 */     dataset.addValue(7.0D, series2, category2);
/* 112 */     dataset.addValue(6.0D, series2, category3);
/* 113 */     dataset.addValue(8.0D, series2, category4);
/* 114 */     dataset.addValue(4.0D, series2, category5);
/*     */     
/* 116 */     dataset.addValue(4.0D, series3, category1);
/* 117 */     dataset.addValue(3.0D, series3, category2);
/* 118 */     dataset.addValue(2.0D, series3, category3);
/* 119 */     dataset.addValue(3.0D, series3, category4);
/* 120 */     dataset.addValue(6.0D, series3, category5);
/*     */     
/* 122 */     return dataset;
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
/*     */   private static JFreeChart createChart(CategoryDataset dataset)
/*     */   {
/* 136 */     JFreeChart chart = ChartFactory.createBarChart("Bar Chart Demo 1", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true, false);
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
/* 150 */     chart.setBackgroundPaint(Color.white);
/*     */     
/*     */ 
/* 153 */     CategoryPlot plot = (CategoryPlot)chart.getPlot();
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
/* 164 */     NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
/* 165 */     rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
/*     */     
/*     */ 
/* 168 */     BarRenderer renderer = (BarRenderer)plot.getRenderer();
/* 169 */     renderer.setDrawBarOutline(false);
/*     */     
/*     */ 
/* 172 */     GradientPaint gp0 = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
/*     */     
/* 174 */     GradientPaint gp1 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
/*     */     
/* 176 */     GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
/*     */     
/* 178 */     renderer.setSeriesPaint(0, gp0);
/* 179 */     renderer.setSeriesPaint(1, gp1);
/* 180 */     renderer.setSeriesPaint(2, gp2);
/*     */     
/* 182 */     CategoryAxis domainAxis = plot.getDomainAxis();
/* 183 */     domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.5235987755982988D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 188 */     return chart;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 198 */     BarChartDemo1 demo = new BarChartDemo1("Bar Chart Demo 1");
/* 199 */     demo.pack();
/* 200 */     RefineryUtilities.centerFrameOnScreen(demo);
/* 201 */     demo.setVisible(true);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/demo/BarChartDemo1.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */