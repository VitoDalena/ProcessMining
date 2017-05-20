/*     */ package org.apache.commons.math.random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractRandomGenerator
/*     */   implements RandomGenerator
/*     */ {
/*  42 */   private double cachedNormalDeviate = NaN.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*  59 */     this.cachedNormalDeviate = NaN.0D;
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
/*     */ 
/*     */   public abstract void setSeed(long paramLong);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void nextBytes(byte[] bytes)
/*     */   {
/*  87 */     int bytesOut = 0;
/*  88 */     while (bytesOut < bytes.length) {
/*  89 */       int randInt = nextInt();
/*  90 */       for (int i = 0; i < 3; i++) {
/*  91 */         if (i > 0) {
/*  92 */           randInt >>= 8;
/*     */         }
/*  94 */         bytes[(bytesOut++)] = ((byte)randInt);
/*  95 */         if (bytesOut == bytes.length) {
/*  96 */           return;
/*     */         }
/*     */       }
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public int nextInt()
/*     */   {
/* 117 */     return (int)(nextDouble() * 2.147483647E9D);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int nextInt(int n)
/*     */   {
/* 137 */     if (n <= 0) {
/* 138 */       throw new IllegalArgumentException("upper bound must be positive");
/*     */     }
/* 140 */     int result = (int)(nextDouble() * n);
/* 141 */     return result < n ? result : n - 1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public long nextLong()
/*     */   {
/* 159 */     return (nextDouble() * 9.223372036854776E18D);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean nextBoolean()
/*     */   {
/* 177 */     return nextDouble() <= 0.5D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public float nextFloat()
/*     */   {
/* 195 */     return (float)nextDouble();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract double nextDouble();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double nextGaussian()
/*     */   {
/* 233 */     if (!Double.isNaN(this.cachedNormalDeviate)) {
/* 234 */       double dev = this.cachedNormalDeviate;
/* 235 */       this.cachedNormalDeviate = NaN.0D;
/* 236 */       return dev;
/*     */     }
/* 238 */     double v1 = 0.0D;
/* 239 */     double v2 = 0.0D;
/* 240 */     double s = 1.0D;
/* 241 */     while (s >= 1.0D) {
/* 242 */       v1 = 2.0D * nextDouble() - 1.0D;
/* 243 */       v2 = 2.0D * nextDouble() - 1.0D;
/* 244 */       s = v1 * v1 + v2 * v2;
/*     */     }
/* 246 */     if (s != 0.0D) {
/* 247 */       s = Math.sqrt(-2.0D * Math.log(s) / s);
/*     */     }
/* 249 */     this.cachedNormalDeviate = (v2 * s);
/* 250 */     return v1 * s;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/random/AbstractRandomGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */