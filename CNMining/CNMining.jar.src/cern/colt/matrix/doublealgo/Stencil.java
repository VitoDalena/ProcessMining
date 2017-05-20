/*    */ package cern.colt.matrix.doublealgo;
/*    */ 
/*    */ import cern.colt.function.Double27Function;
/*    */ import cern.colt.function.Double9Function;
/*    */ import cern.colt.matrix.DoubleMatrix2D;
/*    */ import cern.colt.matrix.DoubleMatrix2DProcedure;
/*    */ import cern.colt.matrix.DoubleMatrix3D;
/*    */ import cern.colt.matrix.DoubleMatrix3DProcedure;
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
/*    */ public class Stencil
/*    */ {
/*    */   public static int stencil27(DoubleMatrix3D paramDoubleMatrix3D, Double27Function paramDouble27Function, int paramInt1, DoubleMatrix3DProcedure paramDoubleMatrix3DProcedure, int paramInt2)
/*    */   {
/* 42 */     DoubleMatrix3D localDoubleMatrix3D = paramDoubleMatrix3D.copy();
/* 43 */     if (paramInt2 <= 1) paramInt2 = 2;
/* 44 */     if (paramInt2 % 2 != 0) { paramInt2++;
/*    */     }
/* 46 */     int i = 0;
/* 47 */     while (i < paramInt1) {
/* 48 */       paramDoubleMatrix3D.zAssign27Neighbors(localDoubleMatrix3D, paramDouble27Function);
/* 49 */       localDoubleMatrix3D.zAssign27Neighbors(paramDoubleMatrix3D, paramDouble27Function);
/* 50 */       i += 2;
/* 51 */       if ((i % paramInt2 == 0) && (paramDoubleMatrix3DProcedure != null) && 
/* 52 */         (paramDoubleMatrix3DProcedure.apply(paramDoubleMatrix3D))) { return i;
/*    */       }
/*    */     }
/* 55 */     return i;
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
/*    */   public static int stencil9(DoubleMatrix2D paramDoubleMatrix2D, Double9Function paramDouble9Function, int paramInt1, DoubleMatrix2DProcedure paramDoubleMatrix2DProcedure, int paramInt2)
/*    */   {
/* 71 */     DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix2D.copy();
/* 72 */     if (paramInt2 <= 1) paramInt2 = 2;
/* 73 */     if (paramInt2 % 2 != 0) { paramInt2++;
/*    */     }
/* 75 */     int i = 0;
/* 76 */     while (i < paramInt1) {
/* 77 */       paramDoubleMatrix2D.zAssign8Neighbors(localDoubleMatrix2D, paramDouble9Function);
/* 78 */       localDoubleMatrix2D.zAssign8Neighbors(paramDoubleMatrix2D, paramDouble9Function);
/* 79 */       i += 2;
/* 80 */       if ((i % paramInt2 == 0) && (paramDoubleMatrix2DProcedure != null) && 
/* 81 */         (paramDoubleMatrix2DProcedure.apply(paramDoubleMatrix2D))) { return i;
/*    */       }
/*    */     }
/* 84 */     return i;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/doublealgo/Stencil.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */