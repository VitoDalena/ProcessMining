/*    */ package org.apache.commons.math.random;
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
/*    */ public class GaussianRandomGenerator
/*    */   implements NormalizedRandomGenerator
/*    */ {
/*    */   private RandomGenerator generator;
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
/*    */   public GaussianRandomGenerator(RandomGenerator generator)
/*    */   {
/* 34 */     this.generator = generator;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public double nextNormalizedDouble()
/*    */   {
/* 41 */     return this.generator.nextGaussian();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/random/GaussianRandomGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */