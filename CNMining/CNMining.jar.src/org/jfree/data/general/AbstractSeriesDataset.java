/*     */ package org.jfree.data.general;
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
/*     */ public abstract class AbstractSeriesDataset
/*     */   extends AbstractDataset
/*     */   implements SeriesDataset, SeriesChangeListener, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6074996219705033171L;
/*     */   
/*     */   public abstract int getSeriesCount();
/*     */   
/*     */   public abstract Comparable getSeriesKey(int paramInt);
/*     */   
/*     */   public int indexOf(Comparable seriesKey)
/*     */   {
/*  95 */     int seriesCount = getSeriesCount();
/*  96 */     for (int s = 0; s < seriesCount; s++) {
/*  97 */       if (getSeriesKey(s).equals(seriesKey)) {
/*  98 */         return s;
/*     */       }
/*     */     }
/* 101 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void seriesChanged(SeriesChangeEvent event)
/*     */   {
/* 110 */     fireDatasetChanged();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/general/AbstractSeriesDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */