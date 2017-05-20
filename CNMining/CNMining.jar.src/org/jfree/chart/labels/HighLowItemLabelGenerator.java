/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Date;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.data.xy.OHLCDataset;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HighLowItemLabelGenerator
/*     */   implements XYItemLabelGenerator, XYToolTipGenerator, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5617111754832211830L;
/*     */   private DateFormat dateFormatter;
/*     */   private NumberFormat numberFormatter;
/*     */   
/*     */   public HighLowItemLabelGenerator()
/*     */   {
/*  86 */     this(DateFormat.getInstance(), NumberFormat.getInstance());
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
/*     */   public HighLowItemLabelGenerator(DateFormat dateFormatter, NumberFormat numberFormatter)
/*     */   {
/*  99 */     if (dateFormatter == null) {
/* 100 */       throw new IllegalArgumentException("Null 'dateFormatter' argument.");
/*     */     }
/*     */     
/* 103 */     if (numberFormatter == null) {
/* 104 */       throw new IllegalArgumentException("Null 'numberFormatter' argument.");
/*     */     }
/*     */     
/* 107 */     this.dateFormatter = dateFormatter;
/* 108 */     this.numberFormatter = numberFormatter;
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
/*     */   public String generateToolTip(XYDataset dataset, int series, int item)
/*     */   {
/* 122 */     String result = null;
/*     */     
/* 124 */     if ((dataset instanceof OHLCDataset)) {
/* 125 */       OHLCDataset d = (OHLCDataset)dataset;
/* 126 */       Number high = d.getHigh(series, item);
/* 127 */       Number low = d.getLow(series, item);
/* 128 */       Number open = d.getOpen(series, item);
/* 129 */       Number close = d.getClose(series, item);
/* 130 */       Number x = d.getX(series, item);
/*     */       
/* 132 */       result = d.getSeriesKey(series).toString();
/*     */       
/* 134 */       if (x != null) {
/* 135 */         Date date = new Date(x.longValue());
/* 136 */         result = result + "--> Date=" + this.dateFormatter.format(date);
/* 137 */         if (high != null) {
/* 138 */           result = result + " High=" + this.numberFormatter.format(high.doubleValue());
/*     */         }
/*     */         
/* 141 */         if (low != null) {
/* 142 */           result = result + " Low=" + this.numberFormatter.format(low.doubleValue());
/*     */         }
/*     */         
/* 145 */         if (open != null) {
/* 146 */           result = result + " Open=" + this.numberFormatter.format(open.doubleValue());
/*     */         }
/*     */         
/* 149 */         if (close != null) {
/* 150 */           result = result + " Close=" + this.numberFormatter.format(close.doubleValue());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 157 */     return result;
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
/*     */   public String generateLabel(XYDataset dataset, int series, int category)
/*     */   {
/* 172 */     return null;
/*     */   }
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
/* 184 */     HighLowItemLabelGenerator clone = (HighLowItemLabelGenerator)super.clone();
/*     */     
/*     */ 
/* 187 */     if (this.dateFormatter != null) {
/* 188 */       clone.dateFormatter = ((DateFormat)this.dateFormatter.clone());
/*     */     }
/* 190 */     if (this.numberFormatter != null) {
/* 191 */       clone.numberFormatter = ((NumberFormat)this.numberFormatter.clone());
/*     */     }
/*     */     
/* 194 */     return clone;
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
/* 206 */     if (obj == this) {
/* 207 */       return true;
/*     */     }
/* 209 */     if (!(obj instanceof HighLowItemLabelGenerator)) {
/* 210 */       return false;
/*     */     }
/* 212 */     HighLowItemLabelGenerator generator = (HighLowItemLabelGenerator)obj;
/* 213 */     if (!this.dateFormatter.equals(generator.dateFormatter)) {
/* 214 */       return false;
/*     */     }
/* 216 */     if (!this.numberFormatter.equals(generator.numberFormatter)) {
/* 217 */       return false;
/*     */     }
/* 219 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 228 */     int result = 127;
/* 229 */     result = HashUtilities.hashCode(result, this.dateFormatter);
/* 230 */     result = HashUtilities.hashCode(result, this.numberFormatter);
/* 231 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/HighLowItemLabelGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */