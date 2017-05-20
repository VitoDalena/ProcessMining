/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
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
/*     */ public class BoxAndWhiskerXYToolTipGenerator
/*     */   extends StandardXYToolTipGenerator
/*     */   implements XYToolTipGenerator, Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2648775791161459710L;
/*     */   public static final String DEFAULT_TOOL_TIP_FORMAT = "X: {1} Mean: {2} Median: {3} Min: {4} Max: {5} Q1: {6} Q3: {7} ";
/*     */   
/*     */   public BoxAndWhiskerXYToolTipGenerator()
/*     */   {
/*  93 */     super("X: {1} Mean: {2} Median: {3} Min: {4} Max: {5} Q1: {6} Q3: {7} ", NumberFormat.getInstance(), NumberFormat.getInstance());
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
/*     */   public BoxAndWhiskerXYToolTipGenerator(String toolTipFormat, DateFormat dateFormat, NumberFormat numberFormat)
/*     */   {
/* 111 */     super(toolTipFormat, dateFormat, numberFormat);
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
/*     */   protected Object[] createItemArray(XYDataset dataset, int series, int item)
/*     */   {
/* 127 */     Object[] result = new Object[8];
/* 128 */     result[0] = dataset.getSeriesKey(series).toString();
/* 129 */     Number x = dataset.getX(series, item);
/* 130 */     if (getXDateFormat() != null) {
/* 131 */       result[1] = getXDateFormat().format(new Date(x.longValue()));
/*     */     }
/*     */     else {
/* 134 */       result[1] = getXFormat().format(x);
/*     */     }
/* 136 */     NumberFormat formatter = getYFormat();
/*     */     
/* 138 */     if ((dataset instanceof BoxAndWhiskerXYDataset)) {
/* 139 */       BoxAndWhiskerXYDataset d = (BoxAndWhiskerXYDataset)dataset;
/* 140 */       result[2] = formatter.format(d.getMeanValue(series, item));
/* 141 */       result[3] = formatter.format(d.getMedianValue(series, item));
/* 142 */       result[4] = formatter.format(d.getMinRegularValue(series, item));
/* 143 */       result[5] = formatter.format(d.getMaxRegularValue(series, item));
/* 144 */       result[6] = formatter.format(d.getQ1Value(series, item));
/* 145 */       result[7] = formatter.format(d.getQ3Value(series, item));
/*     */     }
/* 147 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 158 */     if (obj == this) {
/* 159 */       return true;
/*     */     }
/* 161 */     if (!(obj instanceof BoxAndWhiskerXYToolTipGenerator)) {
/* 162 */       return false;
/*     */     }
/* 164 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/BoxAndWhiskerXYToolTipGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */