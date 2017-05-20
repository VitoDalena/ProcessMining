/*     */ package org.apache.commons.math.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math.DimensionMismatchException;
/*     */ import org.apache.commons.math.linear.RealMatrix;
/*     */ import org.apache.commons.math.linear.RealMatrixImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VectorialCovariance
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4118372414238930270L;
/*     */   private double[] sums;
/*     */   private double[] productsSums;
/*     */   private boolean isBiasCorrected;
/*     */   private long n;
/*     */   
/*     */   public VectorialCovariance(int dimension, boolean isBiasCorrected)
/*     */   {
/*  54 */     this.sums = new double[dimension];
/*  55 */     this.productsSums = new double[dimension * (dimension + 1) / 2];
/*  56 */     this.n = 0L;
/*  57 */     this.isBiasCorrected = isBiasCorrected;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void increment(double[] v)
/*     */     throws DimensionMismatchException
/*     */   {
/*  66 */     if (v.length != this.sums.length) {
/*  67 */       throw new DimensionMismatchException(v.length, this.sums.length);
/*     */     }
/*  69 */     int k = 0;
/*  70 */     for (int i = 0; i < v.length; i++) {
/*  71 */       this.sums[i] += v[i];
/*  72 */       for (int j = 0; j <= i; j++) {
/*  73 */         this.productsSums[(k++)] += v[i] * v[j];
/*     */       }
/*     */     }
/*  76 */     this.n += 1L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RealMatrix getResult()
/*     */   {
/*  85 */     int dimension = this.sums.length;
/*  86 */     RealMatrixImpl result = new RealMatrixImpl(dimension, dimension);
/*     */     
/*  88 */     if (this.n > 1L) {
/*  89 */       double[][] resultData = result.getDataRef();
/*  90 */       double c = 1.0D / (this.n * (this.isBiasCorrected ? this.n - 1L : this.n));
/*  91 */       int k = 0;
/*  92 */       for (int i = 0; i < dimension; i++) {
/*  93 */         for (int j = 0; j <= i; j++) {
/*  94 */           double e = c * (this.n * this.productsSums[(k++)] - this.sums[i] * this.sums[j]);
/*  95 */           resultData[i][j] = e;
/*  96 */           resultData[j][i] = e;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 101 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/* 110 */     return this.n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 117 */     this.n = 0L;
/* 118 */     Arrays.fill(this.sums, 0.0D);
/* 119 */     Arrays.fill(this.productsSums, 0.0D);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/moment/VectorialCovariance.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */