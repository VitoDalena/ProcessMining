/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.image.BufferedImage;
/*     */ import org.jfree.chart.renderer.PaintScale;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.data.xy.XYSeries;
/*     */ import org.jfree.data.xy.XYSeriesCollection;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class HeatMapUtilities
/*     */ {
/*     */   public static XYDataset extractRowFromHeatMapDataset(HeatMapDataset dataset, int row, Comparable seriesName)
/*     */   {
/*  70 */     XYSeries series = new XYSeries(seriesName);
/*  71 */     int cols = dataset.getXSampleCount();
/*  72 */     for (int c = 0; c < cols; c++) {
/*  73 */       series.add(dataset.getXValue(c), dataset.getZValue(c, row));
/*     */     }
/*  75 */     XYSeriesCollection result = new XYSeriesCollection(series);
/*  76 */     return result;
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
/*     */   public static XYDataset extractColumnFromHeatMapDataset(HeatMapDataset dataset, int column, Comparable seriesName)
/*     */   {
/*  91 */     XYSeries series = new XYSeries(seriesName);
/*  92 */     int rows = dataset.getYSampleCount();
/*  93 */     for (int r = 0; r < rows; r++) {
/*  94 */       series.add(dataset.getYValue(r), dataset.getZValue(column, r));
/*     */     }
/*  96 */     XYSeriesCollection result = new XYSeriesCollection(series);
/*  97 */     return result;
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
/*     */   public static BufferedImage createHeatMapImage(HeatMapDataset dataset, PaintScale paintScale)
/*     */   {
/* 112 */     if (dataset == null) {
/* 113 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 115 */     if (paintScale == null) {
/* 116 */       throw new IllegalArgumentException("Null 'paintScale' argument.");
/*     */     }
/* 118 */     int xCount = dataset.getXSampleCount();
/* 119 */     int yCount = dataset.getYSampleCount();
/* 120 */     BufferedImage image = new BufferedImage(xCount, yCount, 2);
/*     */     
/* 122 */     Graphics2D g2 = image.createGraphics();
/* 123 */     for (int xIndex = 0; xIndex < xCount; xIndex++) {
/* 124 */       for (int yIndex = 0; yIndex < yCount; yIndex++) {
/* 125 */         double z = dataset.getZValue(xIndex, yIndex);
/* 126 */         Paint p = paintScale.getPaint(z);
/* 127 */         g2.setPaint(p);
/* 128 */         g2.fillRect(xIndex, yCount - yIndex - 1, 1, 1);
/*     */       }
/*     */     }
/* 131 */     return image;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/HeatMapUtilities.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */