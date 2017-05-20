/*     */ package org.apache.commons.math.random;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomAdaptor
/*     */   extends Random
/*     */   implements RandomGenerator
/*     */ {
/*     */   private static final long serialVersionUID = 2570805822599485047L;
/*  34 */   private RandomGenerator randomGenerator = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private RandomAdaptor() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public RandomAdaptor(RandomGenerator randomGenerator)
/*     */   {
/*  47 */     this.randomGenerator = randomGenerator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Random createAdaptor(RandomGenerator randomGenerator)
/*     */   {
/*  58 */     return new RandomAdaptor(randomGenerator);
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
/*     */   public boolean nextBoolean()
/*     */   {
/*  71 */     return this.randomGenerator.nextBoolean();
/*     */   }
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
/*  83 */     this.randomGenerator.nextBytes(bytes);
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
/*     */   public double nextDouble()
/*     */   {
/*  96 */     return this.randomGenerator.nextDouble();
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
/*     */   public float nextFloat()
/*     */   {
/* 109 */     return this.randomGenerator.nextFloat();
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
/*     */   public double nextGaussian()
/*     */   {
/* 123 */     return this.randomGenerator.nextGaussian();
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
/*     */   public int nextInt()
/*     */   {
/* 136 */     return this.randomGenerator.nextInt();
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
/*     */   public int nextInt(int n)
/*     */   {
/* 151 */     return this.randomGenerator.nextInt(n);
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
/*     */   public long nextLong()
/*     */   {
/* 164 */     return this.randomGenerator.nextLong();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeed(long seed)
/*     */   {
/* 175 */     if (this.randomGenerator != null) {
/* 176 */       this.randomGenerator.setSeed(seed);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/random/RandomAdaptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */