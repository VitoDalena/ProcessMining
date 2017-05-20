/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.MessageFormat;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.data.xy.XYDataset;
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
/*     */ public class StandardXYSeriesLabelGenerator
/*     */   implements XYSeriesLabelGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1916017081848400024L;
/*     */   public static final String DEFAULT_LABEL_FORMAT = "{0}";
/*     */   private String formatPattern;
/*     */   
/*     */   public StandardXYSeriesLabelGenerator()
/*     */   {
/*  77 */     this("{0}");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardXYSeriesLabelGenerator(String format)
/*     */   {
/*  86 */     if (format == null) {
/*  87 */       throw new IllegalArgumentException("Null 'format' argument.");
/*     */     }
/*  89 */     this.formatPattern = format;
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
/*     */   public String generateLabel(XYDataset dataset, int series)
/*     */   {
/* 102 */     if (dataset == null) {
/* 103 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 105 */     String label = MessageFormat.format(this.formatPattern, createItemArray(dataset, series));
/*     */     
/*     */ 
/* 108 */     return label;
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
/*     */   protected Object[] createItemArray(XYDataset dataset, int series)
/*     */   {
/* 121 */     Object[] result = new Object[1];
/* 122 */     result[0] = dataset.getSeriesKey(series).toString();
/* 123 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 136 */     return super.clone();
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
/* 147 */     if (obj == this) {
/* 148 */       return true;
/*     */     }
/* 150 */     if (!(obj instanceof StandardXYSeriesLabelGenerator)) {
/* 151 */       return false;
/*     */     }
/* 153 */     StandardXYSeriesLabelGenerator that = (StandardXYSeriesLabelGenerator)obj;
/*     */     
/* 155 */     if (!this.formatPattern.equals(that.formatPattern)) {
/* 156 */       return false;
/*     */     }
/* 158 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 167 */     int result = 127;
/* 168 */     result = HashUtilities.hashCode(result, this.formatPattern);
/* 169 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/StandardXYSeriesLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */