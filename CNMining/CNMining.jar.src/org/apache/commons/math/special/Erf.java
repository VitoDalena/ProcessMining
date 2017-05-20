/*    */ package org.apache.commons.math.special;
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
/*    */ public class Erf
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 490960015010326571L;
/*    */   
/*    */   public static double erf(double x)
/*    */     throws MathException
/*    */   {
/* 56 */     double ret = Gamma.regularizedGammaP(0.5D, x * x, 1.0E-15D, 10000);
/* 57 */     if (x < 0.0D) {
/* 58 */       ret = -ret;
/*    */     }
/* 60 */     return ret;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/special/Erf.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */