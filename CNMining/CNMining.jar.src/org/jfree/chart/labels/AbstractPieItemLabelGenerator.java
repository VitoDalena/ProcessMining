/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.MessageFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.general.PieDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractPieItemLabelGenerator
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7347703325267846275L;
/*     */   private String labelFormat;
/*     */   private NumberFormat numberFormat;
/*     */   private NumberFormat percentFormat;
/*     */   
/*     */   protected AbstractPieItemLabelGenerator(String labelFormat, NumberFormat numberFormat, NumberFormat percentFormat)
/*     */   {
/*  86 */     if (labelFormat == null) {
/*  87 */       throw new IllegalArgumentException("Null 'labelFormat' argument.");
/*     */     }
/*  89 */     if (numberFormat == null) {
/*  90 */       throw new IllegalArgumentException("Null 'numberFormat' argument.");
/*     */     }
/*  92 */     if (percentFormat == null) {
/*  93 */       throw new IllegalArgumentException("Null 'percentFormat' argument.");
/*     */     }
/*     */     
/*  96 */     this.labelFormat = labelFormat;
/*  97 */     this.numberFormat = numberFormat;
/*  98 */     this.percentFormat = percentFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLabelFormat()
/*     */   {
/* 108 */     return this.labelFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getNumberFormat()
/*     */   {
/* 117 */     return this.numberFormat;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getPercentFormat()
/*     */   {
/* 126 */     return this.percentFormat;
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
/*     */ 
/*     */ 
/*     */   protected Object[] createItemArray(PieDataset dataset, Comparable key)
/*     */   {
/* 146 */     Object[] result = new Object[4];
/* 147 */     double total = DatasetUtilities.calculatePieDatasetTotal(dataset);
/* 148 */     result[0] = key.toString();
/* 149 */     Number value = dataset.getValue(key);
/* 150 */     if (value != null) {
/* 151 */       result[1] = this.numberFormat.format(value);
/*     */     }
/*     */     else {
/* 154 */       result[1] = "null";
/*     */     }
/* 156 */     double percent = 0.0D;
/* 157 */     if (value != null) {
/* 158 */       double v = value.doubleValue();
/* 159 */       if (v > 0.0D) {
/* 160 */         percent = v / total;
/*     */       }
/*     */     }
/* 163 */     result[2] = this.percentFormat.format(percent);
/* 164 */     result[3] = this.numberFormat.format(total);
/* 165 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected String generateSectionLabel(PieDataset dataset, Comparable key)
/*     */   {
/* 177 */     String result = null;
/* 178 */     if (dataset != null) {
/* 179 */       Object[] items = createItemArray(dataset, key);
/* 180 */       result = MessageFormat.format(this.labelFormat, items);
/*     */     }
/* 182 */     return result;
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
/* 193 */     if (obj == this) {
/* 194 */       return true;
/*     */     }
/* 196 */     if (!(obj instanceof AbstractPieItemLabelGenerator)) {
/* 197 */       return false;
/*     */     }
/*     */     
/* 200 */     AbstractPieItemLabelGenerator that = (AbstractPieItemLabelGenerator)obj;
/*     */     
/* 202 */     if (!this.labelFormat.equals(that.labelFormat)) {
/* 203 */       return false;
/*     */     }
/* 205 */     if (!this.numberFormat.equals(that.numberFormat)) {
/* 206 */       return false;
/*     */     }
/* 208 */     if (!this.percentFormat.equals(that.percentFormat)) {
/* 209 */       return false;
/*     */     }
/* 211 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 221 */     int result = 127;
/* 222 */     result = HashUtilities.hashCode(result, this.labelFormat);
/* 223 */     result = HashUtilities.hashCode(result, this.numberFormat);
/* 224 */     result = HashUtilities.hashCode(result, this.percentFormat);
/* 225 */     return result;
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
/* 236 */     AbstractPieItemLabelGenerator clone = (AbstractPieItemLabelGenerator)super.clone();
/*     */     
/* 238 */     if (this.numberFormat != null) {
/* 239 */       clone.numberFormat = ((NumberFormat)this.numberFormat.clone());
/*     */     }
/* 241 */     if (this.percentFormat != null) {
/* 242 */       clone.percentFormat = ((NumberFormat)this.percentFormat.clone());
/*     */     }
/* 244 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/AbstractPieItemLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */