/*    */ package cern.colt.matrix.bench;
/*    */ 
/*    */ import cern.colt.Timer;
/*    */ import cern.colt.matrix.DoubleMatrix2D;
/*    */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class Double2DProcedure
/*    */   implements TimerProcedure
/*    */ {
/*    */   public DoubleMatrix2D A;
/*    */   public DoubleMatrix2D B;
/*    */   public DoubleMatrix2D C;
/*    */   public DoubleMatrix2D D;
/*    */   
/*    */   public double operations()
/*    */   {
/* 22 */     return this.A.rows() * this.A.columns() / 1000000.0D;
/*    */   }
/*    */   
/*    */ 
/*    */   public void setParameters(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*    */   {
/* 28 */     this.A = paramDoubleMatrix2D1;
/* 29 */     this.B = paramDoubleMatrix2D2;
/* 30 */     this.C = paramDoubleMatrix2D1.copy();
/* 31 */     this.D = paramDoubleMatrix2D2.copy();
/*    */   }
/*    */   
/*    */   public abstract void init();
/*    */   
/*    */   public abstract void apply(Timer paramTimer);
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/bench/Double2DProcedure.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */