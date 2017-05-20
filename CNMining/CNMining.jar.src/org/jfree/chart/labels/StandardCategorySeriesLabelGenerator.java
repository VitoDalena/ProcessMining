/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.MessageFormat;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.data.category.CategoryDataset;
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
/*     */ public class StandardCategorySeriesLabelGenerator
/*     */   implements CategorySeriesLabelGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4630760091523940820L;
/*     */   public static final String DEFAULT_LABEL_FORMAT = "{0}";
/*     */   private String formatPattern;
/*     */   
/*     */   public StandardCategorySeriesLabelGenerator()
/*     */   {
/*  74 */     this("{0}");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardCategorySeriesLabelGenerator(String format)
/*     */   {
/*  83 */     if (format == null) {
/*  84 */       throw new IllegalArgumentException("Null 'format' argument.");
/*     */     }
/*  86 */     this.formatPattern = format;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String generateLabel(CategoryDataset dataset, int series)
/*     */   {
/*  98 */     if (dataset == null) {
/*  99 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 101 */     String label = MessageFormat.format(this.formatPattern, createItemArray(dataset, series));
/*     */     
/* 103 */     return label;
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
/*     */   protected Object[] createItemArray(CategoryDataset dataset, int series)
/*     */   {
/* 116 */     Object[] result = new Object[1];
/* 117 */     result[0] = dataset.getRowKey(series).toString();
/* 118 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 129 */     return super.clone();
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
/* 140 */     if (obj == this) {
/* 141 */       return true;
/*     */     }
/* 143 */     if (!(obj instanceof StandardCategorySeriesLabelGenerator)) {
/* 144 */       return false;
/*     */     }
/* 146 */     StandardCategorySeriesLabelGenerator that = (StandardCategorySeriesLabelGenerator)obj;
/*     */     
/* 148 */     if (!this.formatPattern.equals(that.formatPattern)) {
/* 149 */       return false;
/*     */     }
/* 151 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 160 */     int result = 127;
/* 161 */     result = HashUtilities.hashCode(result, this.formatPattern);
/* 162 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/StandardCategorySeriesLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */