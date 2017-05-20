/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.IntervalCategoryDataset;
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
/*     */ public class IntervalCategoryItemLabelGenerator
/*     */   extends StandardCategoryItemLabelGenerator
/*     */   implements CategoryItemLabelGenerator, PublicCloneable, Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5056909225610630529L;
/*     */   public static final String DEFAULT_LABEL_FORMAT_STRING = "({0}, {1}) = {3} - {4}";
/*     */   
/*     */   public IntervalCategoryItemLabelGenerator()
/*     */   {
/*  71 */     super("({0}, {1}) = {3} - {4}", NumberFormat.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntervalCategoryItemLabelGenerator(String labelFormat, NumberFormat formatter)
/*     */   {
/*  83 */     super(labelFormat, formatter);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntervalCategoryItemLabelGenerator(String labelFormat, DateFormat formatter)
/*     */   {
/*  95 */     super(labelFormat, formatter);
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
/*     */   protected Object[] createItemArray(CategoryDataset dataset, int row, int column)
/*     */   {
/* 110 */     Object[] result = new Object[5];
/* 111 */     result[0] = dataset.getRowKey(row).toString();
/* 112 */     result[1] = dataset.getColumnKey(column).toString();
/* 113 */     Number value = dataset.getValue(row, column);
/* 114 */     if (getNumberFormat() != null) {
/* 115 */       result[2] = getNumberFormat().format(value);
/*     */     }
/* 117 */     else if (getDateFormat() != null) {
/* 118 */       result[2] = getDateFormat().format(value);
/*     */     }
/*     */     
/* 121 */     if ((dataset instanceof IntervalCategoryDataset)) {
/* 122 */       IntervalCategoryDataset icd = (IntervalCategoryDataset)dataset;
/* 123 */       Number start = icd.getStartValue(row, column);
/* 124 */       Number end = icd.getEndValue(row, column);
/* 125 */       if (getNumberFormat() != null) {
/* 126 */         result[3] = getNumberFormat().format(start);
/* 127 */         result[4] = getNumberFormat().format(end);
/*     */       }
/* 129 */       else if (getDateFormat() != null) {
/* 130 */         result[3] = getDateFormat().format(start);
/* 131 */         result[4] = getDateFormat().format(end);
/*     */       }
/*     */     }
/* 134 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/IntervalCategoryItemLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */