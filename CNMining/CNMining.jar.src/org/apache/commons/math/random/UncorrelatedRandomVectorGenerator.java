/*    */ package org.apache.commons.math.random;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UncorrelatedRandomVectorGenerator
/*    */   implements RandomVectorGenerator
/*    */ {
/*    */   private double[] mean;
/*    */   private double[] standardDeviation;
/*    */   private NormalizedRandomGenerator generator;
/*    */   
/*    */   public UncorrelatedRandomVectorGenerator(double[] mean, double[] standardDeviation, NormalizedRandomGenerator generator)
/*    */   {
/* 45 */     if (mean.length != standardDeviation.length) {
/* 46 */       throw new IllegalArgumentException("dimension mismatch");
/*    */     }
/* 48 */     this.mean = ((double[])mean.clone());
/* 49 */     this.standardDeviation = ((double[])standardDeviation.clone());
/* 50 */     this.generator = generator;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public UncorrelatedRandomVectorGenerator(int dimension, NormalizedRandomGenerator generator)
/*    */   {
/* 62 */     this.mean = new double[dimension];
/* 63 */     this.standardDeviation = new double[dimension];
/* 64 */     Arrays.fill(this.standardDeviation, 1.0D);
/* 65 */     this.generator = generator;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public double[] nextVector()
/*    */   {
/* 73 */     double[] random = new double[this.mean.length];
/* 74 */     for (int i = 0; i < random.length; i++) {
/* 75 */       random[i] = (this.mean[i] + this.standardDeviation[i] * this.generator.nextNormalizedDouble());
/*    */     }
/*    */     
/* 78 */     return random;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/random/UncorrelatedRandomVectorGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */