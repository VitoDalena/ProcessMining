/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.IntervalCategoryDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntervalCategoryToolTipGenerator
/*     */   extends StandardCategoryToolTipGenerator
/*     */ {
/*     */   private static final long serialVersionUID = -3853824986520333437L;
/*     */   public static final String DEFAULT_TOOL_TIP_FORMAT_STRING = "({0}, {1}) = {3} - {4}";
/*     */   
/*     */   public IntervalCategoryToolTipGenerator()
/*     */   {
/*  68 */     super("({0}, {1}) = {3} - {4}", NumberFormat.getInstance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntervalCategoryToolTipGenerator(String labelFormat, NumberFormat formatter)
/*     */   {
/*  80 */     super(labelFormat, formatter);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntervalCategoryToolTipGenerator(String labelFormat, DateFormat formatter)
/*     */   {
/*  92 */     super(labelFormat, formatter);
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
/* 107 */     Object[] result = new Object[5];
/* 108 */     result[0] = dataset.getRowKey(row).toString();
/* 109 */     result[1] = dataset.getColumnKey(column).toString();
/* 110 */     Number value = dataset.getValue(row, column);
/* 111 */     if (getNumberFormat() != null) {
/* 112 */       result[2] = getNumberFormat().format(value);
/*     */     }
/* 114 */     else if (getDateFormat() != null) {
/* 115 */       result[2] = getDateFormat().format(value);
/*     */     }
/*     */     
/* 118 */     if ((dataset instanceof IntervalCategoryDataset)) {
/* 119 */       IntervalCategoryDataset icd = (IntervalCategoryDataset)dataset;
/* 120 */       Number start = icd.getStartValue(row, column);
/* 121 */       Number end = icd.getEndValue(row, column);
/* 122 */       if (getNumberFormat() != null) {
/* 123 */         result[3] = getNumberFormat().format(start);
/* 124 */         result[4] = getNumberFormat().format(end);
/*     */       }
/* 126 */       else if (getDateFormat() != null) {
/* 127 */         result[3] = getDateFormat().format(start);
/* 128 */         result[4] = getDateFormat().format(end);
/*     */       }
/*     */     }
/* 131 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 143 */     if (obj == this) {
/* 144 */       return true;
/*     */     }
/* 146 */     if (!(obj instanceof IntervalCategoryToolTipGenerator)) {
/* 147 */       return false;
/*     */     }
/*     */     
/* 150 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/IntervalCategoryToolTipGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */