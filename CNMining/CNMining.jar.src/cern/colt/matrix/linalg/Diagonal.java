/*    */ package cern.colt.matrix.linalg;
/*    */ 
/*    */ import cern.colt.matrix.DoubleMatrix2D;
/*    */ import cern.colt.matrix.impl.AbstractMatrix2D;
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
/*    */ class Diagonal
/*    */ {
/*    */   protected Diagonal()
/*    */   {
/* 23 */     throw new RuntimeException("Non instantiable");
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static boolean inverse(DoubleMatrix2D paramDoubleMatrix2D)
/*    */   {
/* 33 */     Property.DEFAULT.checkSquare(paramDoubleMatrix2D);
/* 34 */     boolean bool = true;
/* 35 */     int i = paramDoubleMatrix2D.rows();
/* 36 */     do { double d = paramDoubleMatrix2D.getQuick(i, i);
/* 37 */       bool &= d != 0.0D;
/* 38 */       paramDoubleMatrix2D.setQuick(i, i, 1.0D / d);i--;
/* 35 */     } while (i >= 0);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 40 */     return bool;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/linalg/Diagonal.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */