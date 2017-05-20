/*    */ package cern.colt.matrix.impl;
/*    */ 
/*    */ import cern.colt.matrix.DoubleFactory2D;
/*    */ import cern.colt.matrix.DoubleMatrix2D;
/*    */ import cern.colt.matrix.linalg.Algebra;
/*    */ import java.io.PrintStream;
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
/*    */ public class QRTest
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 29 */     DoubleFactory2D localDoubleFactory2D = DoubleFactory2D.dense;
/* 30 */     DoubleMatrix2D localDoubleMatrix2D1 = localDoubleFactory2D.make(8, 2);
/* 31 */     DoubleMatrix2D localDoubleMatrix2D2 = localDoubleFactory2D.make(8, 1);
/*    */     
/* 33 */     localDoubleMatrix2D1.set(0, 0, 1.0D);
/* 34 */     localDoubleMatrix2D1.set(1, 0, 1.0D);
/* 35 */     localDoubleMatrix2D1.set(2, 0, 1.0D);
/* 36 */     localDoubleMatrix2D1.set(3, 0, 1.0D);
/* 37 */     localDoubleMatrix2D1.set(4, 0, 1.0D);
/* 38 */     localDoubleMatrix2D1.set(5, 0, 1.0D);
/* 39 */     localDoubleMatrix2D1.set(6, 0, 1.0D);
/* 40 */     localDoubleMatrix2D1.set(7, 0, 1.0D);
/*    */     
/* 42 */     localDoubleMatrix2D1.set(0, 1, 80.0D);
/* 43 */     localDoubleMatrix2D1.set(1, 1, 220.0D);
/* 44 */     localDoubleMatrix2D1.set(2, 1, 140.0D);
/* 45 */     localDoubleMatrix2D1.set(3, 1, 120.0D);
/* 46 */     localDoubleMatrix2D1.set(4, 1, 180.0D);
/* 47 */     localDoubleMatrix2D1.set(5, 1, 100.0D);
/* 48 */     localDoubleMatrix2D1.set(6, 1, 200.0D);
/* 49 */     localDoubleMatrix2D1.set(7, 1, 160.0D);
/*    */     
/* 51 */     localDoubleMatrix2D2.set(0, 0, 0.6D);
/* 52 */     localDoubleMatrix2D2.set(1, 0, 6.7D);
/* 53 */     localDoubleMatrix2D2.set(2, 0, 5.3D);
/* 54 */     localDoubleMatrix2D2.set(3, 0, 4.0D);
/* 55 */     localDoubleMatrix2D2.set(4, 0, 6.55D);
/* 56 */     localDoubleMatrix2D2.set(5, 0, 2.15D);
/* 57 */     localDoubleMatrix2D2.set(6, 0, 6.6D);
/* 58 */     localDoubleMatrix2D2.set(7, 0, 5.75D);
/*    */     
/* 60 */     Algebra localAlgebra = new Algebra();
/* 61 */     DoubleMatrix2D localDoubleMatrix2D3 = localAlgebra.solve(localDoubleMatrix2D1, localDoubleMatrix2D2);
/* 62 */     System.err.println(localDoubleMatrix2D1);
/* 63 */     System.err.println(localDoubleMatrix2D2);
/* 64 */     System.err.println(localDoubleMatrix2D3);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/QRTest.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */