/*    */ package cern.jet.math;
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
/*    */ public class Polynomial
/*    */   extends Constants
/*    */ {
/*    */   public static double p1evl(double paramDouble, double[] paramArrayOfDouble, int paramInt)
/*    */     throws ArithmeticException
/*    */   {
/* 46 */     double d = paramDouble + paramArrayOfDouble[0];
/*    */     
/* 48 */     for (int i = 1; i < paramInt; i++) { d = d * paramDouble + paramArrayOfDouble[i];
/*    */     }
/* 50 */     return d;
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
/*    */ 
/*    */ 
/*    */ 
/*    */   public static double polevl(double paramDouble, double[] paramArrayOfDouble, int paramInt)
/*    */     throws ArithmeticException
/*    */   {
/* 72 */     double d = paramArrayOfDouble[0];
/*    */     
/* 74 */     for (int i = 1; i <= paramInt; i++) { d = d * paramDouble + paramArrayOfDouble[i];
/*    */     }
/* 76 */     return d;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/math/Polynomial.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */