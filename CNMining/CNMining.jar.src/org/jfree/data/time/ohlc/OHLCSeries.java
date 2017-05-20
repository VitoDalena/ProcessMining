/*     */ package org.jfree.data.time.ohlc;
/*     */ 
/*     */ import org.jfree.data.ComparableObjectItem;
/*     */ import org.jfree.data.ComparableObjectSeries;
/*     */ import org.jfree.data.time.RegularTimePeriod;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OHLCSeries
/*     */   extends ComparableObjectSeries
/*     */ {
/*     */   public OHLCSeries(Comparable key)
/*     */   {
/*  64 */     super(key, true, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RegularTimePeriod getPeriod(int index)
/*     */   {
/*  75 */     OHLCItem item = (OHLCItem)getDataItem(index);
/*  76 */     return item.getPeriod();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ComparableObjectItem getDataItem(int index)
/*     */   {
/*  87 */     return super.getDataItem(index);
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
/*     */   public void add(RegularTimePeriod period, double open, double high, double low, double close)
/*     */   {
/* 101 */     if (getItemCount() > 0) {
/* 102 */       OHLCItem item0 = (OHLCItem)getDataItem(0);
/* 103 */       if (!period.getClass().equals(item0.getPeriod().getClass())) {
/* 104 */         throw new IllegalArgumentException("Can't mix RegularTimePeriod class types.");
/*     */       }
/*     */     }
/*     */     
/* 108 */     super.add(new OHLCItem(period, open, high, low, close), true);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/time/ohlc/OHLCSeries.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */