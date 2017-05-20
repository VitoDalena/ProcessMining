/*    */ package org.apache.commons.math.stat.descriptive;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public abstract class AbstractUnivariateStatistic
/*    */   implements UnivariateStatistic, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8007759382851708045L;
/*    */   
/*    */   public double evaluate(double[] values)
/*    */   {
/* 44 */     test(values, 0, 0);
/* 45 */     return evaluate(values, 0, values.length);
/*    */   }
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
/*    */   public abstract double evaluate(double[] paramArrayOfDouble, int paramInt1, int paramInt2);
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
/*    */   protected boolean test(double[] values, int begin, int length)
/*    */   {
/* 77 */     if (values == null) {
/* 78 */       throw new IllegalArgumentException("input value array is null");
/*    */     }
/*    */     
/* 81 */     if (begin < 0) {
/* 82 */       throw new IllegalArgumentException("start position cannot be negative");
/*    */     }
/*    */     
/* 85 */     if (length < 0) {
/* 86 */       throw new IllegalArgumentException("length cannot be negative");
/*    */     }
/*    */     
/* 89 */     if (begin + length > values.length) {
/* 90 */       throw new IllegalArgumentException("begin + length > values.length");
/*    */     }
/*    */     
/*    */ 
/* 94 */     if (length == 0) {
/* 95 */       return false;
/*    */     }
/*    */     
/* 98 */     return true;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/AbstractUnivariateStatistic.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */