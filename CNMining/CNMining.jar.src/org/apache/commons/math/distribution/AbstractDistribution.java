/*    */ package org.apache.commons.math.distribution;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.commons.math.MathException;
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
/*    */ public abstract class AbstractDistribution
/*    */   implements Distribution, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -38038050983108802L;
/*    */   
/*    */   public double cumulativeProbability(double x0, double x1)
/*    */     throws MathException
/*    */   {
/* 60 */     if (x0 > x1) {
/* 61 */       throw new IllegalArgumentException("lower endpoint must be less than or equal to upper endpoint");
/*    */     }
/*    */     
/* 64 */     return cumulativeProbability(x1) - cumulativeProbability(x0);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/AbstractDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */