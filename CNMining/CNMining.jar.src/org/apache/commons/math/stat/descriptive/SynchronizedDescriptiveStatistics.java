/*     */ package org.apache.commons.math.stat.descriptive;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SynchronizedDescriptiveStatistics
/*     */   extends DescriptiveStatistics
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedDescriptiveStatistics()
/*     */   {
/*  41 */     this(-1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedDescriptiveStatistics(int window)
/*     */   {
/*  49 */     super(window);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void addValue(double v)
/*     */   {
/*  56 */     super.addValue(v);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double apply(UnivariateStatistic stat)
/*     */   {
/*  65 */     return super.apply(stat);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/*  72 */     super.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double getElement(int index)
/*     */   {
/*  79 */     return super.getElement(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized long getN()
/*     */   {
/*  86 */     return super.getN();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double getStandardDeviation()
/*     */   {
/*  95 */     return super.getStandardDeviation();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double[] getValues()
/*     */   {
/* 102 */     return super.getValues();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int getWindowSize()
/*     */   {
/* 110 */     return super.getWindowSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setWindowSize(int windowSize)
/*     */   {
/* 117 */     super.setWindowSize(windowSize);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized String toString()
/*     */   {
/* 128 */     return super.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/SynchronizedDescriptiveStatistics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */