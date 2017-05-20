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
/*     */ public class NormalizedMatrixSeries
/*     */   extends MatrixSeries
/*     */ {
/*     */   public static final double DEFAULT_SCALE_FACTOR = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  59 */   private double m_scaleFactor = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double m_totalSum;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NormalizedMatrixSeries(String name, int rows, int columns)
/*     */   {
/*  72 */     super(name, rows, columns);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  80 */     this.m_totalSum = Double.MIN_VALUE;
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
/*     */   public Number getItem(int itemIndex)
/*     */   {
/*  93 */     int i = getItemRow(itemIndex);
/*  94 */     int j = getItemColumn(itemIndex);
/*     */     
/*  96 */     double mij = get(i, j) * this.m_scaleFactor;
/*  97 */     Number n = new Double(mij / this.m_totalSum);
/*     */     
/*  99 */     return n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setScaleFactor(double factor)
/*     */   {
/* 111 */     this.m_scaleFactor = factor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getScaleFactor()
/*     */   {
/* 123 */     return this.m_scaleFactor;
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
/*     */   public void update(int i, int j, double mij)
/*     */   {
/* 137 */     this.m_totalSum -= get(i, j);
/* 138 */     this.m_totalSum += mij;
/*     */     
/* 140 */     super.update(i, j, mij);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void zeroAll()
/*     */   {
/* 147 */     this.m_totalSum = 0.0D;
/* 148 */     super.zeroAll();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/xy/NormalizedMatrixSeries.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */