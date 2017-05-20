/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimeSeriesDataItem
/*     */   implements Cloneable, Comparable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2235346966016401302L;
/*     */   private RegularTimePeriod period;
/*     */   private Number value;
/*     */   
/*     */   public TimeSeriesDataItem(RegularTimePeriod period, Number value)
/*     */   {
/*  93 */     if (period == null) {
/*  94 */       throw new IllegalArgumentException("Null 'period' argument.");
/*     */     }
/*  96 */     this.period = period;
/*  97 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TimeSeriesDataItem(RegularTimePeriod period, double value)
/*     */   {
/* 107 */     this(period, new Double(value));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod getPeriod()
/*     */   {
/* 116 */     return this.period;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getValue()
/*     */   {
/* 125 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(Number value)
/*     */   {
/* 134 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 145 */     if (this == o) {
/* 146 */       return true;
/*     */     }
/* 148 */     if (!(o instanceof TimeSeriesDataItem)) {
/* 149 */       return false;
/*     */     }
/* 151 */     TimeSeriesDataItem timeSeriesDataItem = (TimeSeriesDataItem)o;
/* 152 */     if (this.period != null) {
/* 153 */       if (!this.period.equals(timeSeriesDataItem.period)) {
/* 154 */         return false;
/*     */       }
/*     */     }
/* 157 */     else if (timeSeriesDataItem.period != null) {
/* 158 */       return false;
/*     */     }
/*     */     
/* 161 */     if (this.value != null) {
/* 162 */       if (!this.value.equals(timeSeriesDataItem.value)) {
/* 163 */         return false;
/*     */       }
/*     */     }
/* 166 */     else if (timeSeriesDataItem.value != null) {
/* 167 */       return false;
/*     */     }
/*     */     
/* 170 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 180 */     int result = this.period != null ? this.period.hashCode() : 0;
/* 181 */     result = 29 * result + (this.value != null ? this.value.hashCode() : 0);
/* 182 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(Object o1)
/*     */   {
/*     */     int result;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     int result;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 203 */     if ((o1 instanceof TimeSeriesDataItem)) {
/* 204 */       TimeSeriesDataItem datapair = (TimeSeriesDataItem)o1;
/* 205 */       result = getPeriod().compareTo(datapair.getPeriod());
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 212 */       result = 1;
/*     */     }
/*     */     
/* 215 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 226 */     Object clone = null;
/*     */     try {
/* 228 */       clone = super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException e) {
/* 231 */       e.printStackTrace();
/*     */     }
/* 233 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/TimeSeriesDataItem.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */