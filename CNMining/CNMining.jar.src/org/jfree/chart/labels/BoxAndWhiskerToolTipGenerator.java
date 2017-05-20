/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BoxAndWhiskerToolTipGenerator
/*     */   extends StandardCategoryToolTipGenerator
/*     */   implements CategoryToolTipGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6076837753823076334L;
/*     */   public static final String DEFAULT_TOOL_TIP_FORMAT = "X: {1} Mean: {2} Median: {3} Min: {4} Max: {5} Q1: {6} Q3: {7} ";
/*     */   
/*     */   public BoxAndWhiskerToolTipGenerator()
/*     */   {
/*  86 */     super("X: {1} Mean: {2} Median: {3} Min: {4} Max: {5} Q1: {6} Q3: {7} ", NumberFormat.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BoxAndWhiskerToolTipGenerator(String format, NumberFormat formatter)
/*     */   {
/*  97 */     super(format, formatter);
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
/*     */   protected Object[] createItemArray(CategoryDataset dataset, int series, int item)
/*     */   {
/* 112 */     Object[] result = new Object[8];
/* 113 */     result[0] = dataset.getRowKey(series);
/* 114 */     Number y = dataset.getValue(series, item);
/* 115 */     NumberFormat formatter = getNumberFormat();
/* 116 */     result[1] = formatter.format(y);
/* 117 */     if ((dataset instanceof BoxAndWhiskerCategoryDataset)) {
/* 118 */       BoxAndWhiskerCategoryDataset d = (BoxAndWhiskerCategoryDataset)dataset;
/*     */       
/* 120 */       result[2] = formatter.format(d.getMeanValue(series, item));
/* 121 */       result[3] = formatter.format(d.getMedianValue(series, item));
/* 122 */       result[4] = formatter.format(d.getMinRegularValue(series, item));
/* 123 */       result[5] = formatter.format(d.getMaxRegularValue(series, item));
/* 124 */       result[6] = formatter.format(d.getQ1Value(series, item));
/* 125 */       result[7] = formatter.format(d.getQ3Value(series, item));
/*     */     }
/* 127 */     return result;
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
/* 138 */     if (obj == this) {
/* 139 */       return true;
/*     */     }
/* 141 */     if ((obj instanceof BoxAndWhiskerToolTipGenerator)) {
/* 142 */       return super.equals(obj);
/*     */     }
/* 144 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/BoxAndWhiskerToolTipGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */