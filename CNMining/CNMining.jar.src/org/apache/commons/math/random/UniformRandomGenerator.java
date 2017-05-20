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
/*    */ 
/*    */ 
/*    */ public class UniformRandomGenerator
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
/*    */ 
/*    */ 
/*    */   public UniformRandomGenerator(RandomGenerator generator)
/*    */   {
/* 38 */     this.generator = generator;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public double nextNormalizedDouble()
/*    */   {
/* 47 */     return SQRT3 * (2.0D * this.generator.nextDouble() - 1.0D);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 54 */   private static final double SQRT3 = Math.sqrt(3.0D);
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/random/UniformRandomGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */