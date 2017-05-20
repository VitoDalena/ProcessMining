/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OHLCDataItem
/*     */   implements Comparable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7753817154401169901L;
/*     */   private Date date;
/*     */   private Number open;
/*     */   private Number high;
/*     */   private Number low;
/*     */   private Number close;
/*     */   private Number volume;
/*     */   
/*     */   public OHLCDataItem(Date date, double open, double high, double low, double close, double volume)
/*     */   {
/*  92 */     if (date == null) {
/*  93 */       throw new IllegalArgumentException("Null 'date' argument.");
/*     */     }
/*  95 */     this.date = date;
/*  96 */     this.open = new Double(open);
/*  97 */     this.high = new Double(high);
/*  98 */     this.low = new Double(low);
/*  99 */     this.close = new Double(close);
/* 100 */     this.volume = new Double(volume);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getDate()
/*     */   {
/* 109 */     return this.date;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getOpen()
/*     */   {
/* 118 */     return this.open;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getHigh()
/*     */   {
/* 127 */     return this.high;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getLow()
/*     */   {
/* 136 */     return this.low;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getClose()
/*     */   {
/* 145 */     return this.close;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getVolume()
/*     */   {
/* 154 */     return this.volume;
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
/* 165 */     if (obj == this) {
/* 166 */       return true;
/*     */     }
/* 168 */     if (!(obj instanceof OHLCDataItem)) {
/* 169 */       return false;
/*     */     }
/* 171 */     OHLCDataItem that = (OHLCDataItem)obj;
/* 172 */     if (!this.date.equals(that.date)) {
/* 173 */       return false;
/*     */     }
/* 175 */     if (!this.high.equals(that.high)) {
/* 176 */       return false;
/*     */     }
/* 178 */     if (!this.low.equals(that.low)) {
/* 179 */       return false;
/*     */     }
/* 181 */     if (!this.open.equals(that.open)) {
/* 182 */       return false;
/*     */     }
/* 184 */     if (!this.close.equals(that.close)) {
/* 185 */       return false;
/*     */     }
/* 187 */     return true;
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
/*     */   public int compareTo(Object object)
/*     */   {
/* 201 */     if ((object instanceof OHLCDataItem)) {
/* 202 */       OHLCDataItem item = (OHLCDataItem)object;
/* 203 */       return this.date.compareTo(item.date);
/*     */     }
/*     */     
/* 206 */     throw new ClassCastException("OHLCDataItem.compareTo().");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/OHLCDataItem.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */