/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractIntervalXYDataset
/*     */   extends AbstractXYDataset
/*     */   implements IntervalXYDataset
/*     */ {
/*     */   public double getStartXValue(int series, int item)
/*     */   {
/*  64 */     double result = NaN.0D;
/*  65 */     Number x = getStartX(series, item);
/*  66 */     if (x != null) {
/*  67 */       result = x.doubleValue();
/*     */     }
/*  69 */     return result;
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
/*     */   public double getEndXValue(int series, int item)
/*     */   {
/*  82 */     double result = NaN.0D;
/*  83 */     Number x = getEndX(series, item);
/*  84 */     if (x != null) {
/*  85 */       result = x.doubleValue();
/*     */     }
/*  87 */     return result;
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
/*     */   public double getStartYValue(int series, int item)
/*     */   {
/* 100 */     double result = NaN.0D;
/* 101 */     Number y = getStartY(series, item);
/* 102 */     if (y != null) {
/* 103 */       result = y.doubleValue();
/*     */     }
/* 105 */     return result;
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
/*     */   public double getEndYValue(int series, int item)
/*     */   {
/* 118 */     double result = NaN.0D;
/* 119 */     Number y = getEndY(series, item);
/* 120 */     if (y != null) {
/* 121 */       result = y.doubleValue();
/*     */     }
/* 123 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/AbstractIntervalXYDataset.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */