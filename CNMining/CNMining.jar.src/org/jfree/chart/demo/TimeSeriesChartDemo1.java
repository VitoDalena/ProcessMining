/*     */ package org.jfree.chart.demo;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.text.SimpleDateFormat;
/*     */ import javax.swing.JPanel;
/*     */ import org.jfree.chart.ChartFactory;
/*     */ import org.jfree.chart.ChartPanel;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.axis.DateAxis;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.renderer.xy.XYItemRenderer;
/*     */ import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
/*     */ import org.jfree.data.time.Month;
/*     */ import org.jfree.data.time.TimeSeries;
/*     */ import org.jfree.data.time.TimeSeriesCollection;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.ApplicationFrame;
/*     */ import org.jfree.ui.RectangleInsets;
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
/*     */ 
/*     */ 
/*     */ public class TimeSeriesChartDemo1
/*     */   extends ApplicationFrame
/*     */ {
/*     */   public TimeSeriesChartDemo1(String title)
/*     */   {
/*  78 */     super(title);
/*  79 */     ChartPanel chartPanel = (ChartPanel)createDemoPanel();
/*  80 */     chartPanel.setPreferredSize(new Dimension(500, 270));
/*  81 */     setContentPane(chartPanel);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static JFreeChart createChart(XYDataset dataset)
/*     */   {
/*  93 */     JFreeChart chart = ChartFactory.createTimeSeriesChart("Legal & General Unit Trust Prices", "Date", "Price Per Unit", dataset, true, true, false);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 103 */     chart.setBackgroundPaint(Color.white);
/*     */     
/* 105 */     XYPlot plot = (XYPlot)chart.getPlot();
/* 106 */     plot.setBackgroundPaint(Color.lightGray);
/* 107 */     plot.setDomainGridlinePaint(Color.white);
/* 108 */     plot.setRangeGridlinePaint(Color.white);
/* 109 */     plot.setAxisOffset(new RectangleInsets(5.0D, 5.0D, 5.0D, 5.0D));
/* 110 */     plot.setDomainCrosshairVisible(true);
/* 111 */     plot.setRangeCrosshairVisible(true);
/*     */     
/* 113 */     XYItemRenderer r = plot.getRenderer();
/* 114 */     if ((r instanceof XYLineAndShapeRenderer)) {
/* 115 */       XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)r;
/* 116 */       renderer.setBaseShapesVisible(true);
/* 117 */       renderer.setBaseShapesFilled(true);
/* 118 */       renderer.setDrawSeriesLineAsPath(true);
/*     */     }
/*     */     
/* 121 */     DateAxis axis = (DateAxis)plot.getDomainAxis();
/* 122 */     axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
/*     */     
/* 124 */     return chart;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static XYDataset createDataset()
/*     */   {
/* 135 */     TimeSeries s1 = new TimeSeries("L&G European Index Trust");
/* 136 */     s1.add(new Month(2, 2001), 181.8D);
/* 137 */     s1.add(new Month(3, 2001), 167.3D);
/* 138 */     s1.add(new Month(4, 2001), 153.8D);
/* 139 */     s1.add(new Month(5, 2001), 167.6D);
/* 140 */     s1.add(new Month(6, 2001), 158.8D);
/* 141 */     s1.add(new Month(7, 2001), 148.3D);
/* 142 */     s1.add(new Month(8, 2001), 153.9D);
/* 143 */     s1.add(new Month(9, 2001), 142.7D);
/* 144 */     s1.add(new Month(10, 2001), 123.2D);
/* 145 */     s1.add(new Month(11, 2001), 131.8D);
/* 146 */     s1.add(new Month(12, 2001), 139.6D);
/* 147 */     s1.add(new Month(1, 2002), 142.9D);
/* 148 */     s1.add(new Month(2, 2002), 138.7D);
/* 149 */     s1.add(new Month(3, 2002), 137.3D);
/* 150 */     s1.add(new Month(4, 2002), 143.9D);
/* 151 */     s1.add(new Month(5, 2002), 139.8D);
/* 152 */     s1.add(new Month(6, 2002), 137.0D);
/* 153 */     s1.add(new Month(7, 2002), 132.8D);
/*     */     
/* 155 */     TimeSeries s2 = new TimeSeries("L&G UK Index Trust");
/* 156 */     s2.add(new Month(2, 2001), 129.6D);
/* 157 */     s2.add(new Month(3, 2001), 123.2D);
/* 158 */     s2.add(new Month(4, 2001), 117.2D);
/* 159 */     s2.add(new Month(5, 2001), 124.1D);
/* 160 */     s2.add(new Month(6, 2001), 122.6D);
/* 161 */     s2.add(new Month(7, 2001), 119.2D);
/* 162 */     s2.add(new Month(8, 2001), 116.5D);
/* 163 */     s2.add(new Month(9, 2001), 112.7D);
/* 164 */     s2.add(new Month(10, 2001), 101.5D);
/* 165 */     s2.add(new Month(11, 2001), 106.1D);
/* 166 */     s2.add(new Month(12, 2001), 110.3D);
/* 167 */     s2.add(new Month(1, 2002), 111.7D);
/* 168 */     s2.add(new Month(2, 2002), 111.0D);
/* 169 */     s2.add(new Month(3, 2002), 109.6D);
/* 170 */     s2.add(new Month(4, 2002), 113.2D);
/* 171 */     s2.add(new Month(5, 2002), 111.6D);
/* 172 */     s2.add(new Month(6, 2002), 108.8D);
/* 173 */     s2.add(new Month(7, 2002), 101.6D);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 183 */     TimeSeriesCollection dataset = new TimeSeriesCollection();
/* 184 */     dataset.addSeries(s1);
/* 185 */     dataset.addSeries(s2);
/*     */     
/* 187 */     return dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static JPanel createDemoPanel()
/*     */   {
/* 197 */     JFreeChart chart = createChart(createDataset());
/* 198 */     ChartPanel panel = new ChartPanel(chart);
/* 199 */     panel.setFillZoomRectangle(true);
/* 200 */     panel.setMouseWheelEnabled(true);
/* 201 */     return panel;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 211 */     TimeSeriesChartDemo1 demo = new TimeSeriesChartDemo1("Time Series Chart Demo 1");
/*     */     
/* 213 */     demo.pack();
/* 214 */     RefineryUtilities.centerFrameOnScreen(demo);
/* 215 */     demo.setVisible(true);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/demo/TimeSeriesChartDemo1.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */