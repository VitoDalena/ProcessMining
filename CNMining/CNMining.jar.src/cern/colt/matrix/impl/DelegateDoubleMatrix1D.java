/*    */ package cern.colt.matrix.impl;
/*    */ 
/*    */ import cern.colt.matrix.DoubleMatrix1D;
/*    */ import cern.colt.matrix.DoubleMatrix2D;
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
/*    */ class DelegateDoubleMatrix1D
/*    */   extends WrapperDoubleMatrix1D
/*    */ {
/*    */   protected DoubleMatrix2D content;
/*    */   protected int row;
/*    */   
/*    */   public DelegateDoubleMatrix1D(DoubleMatrix2D paramDoubleMatrix2D, int paramInt)
/*    */   {
/* 30 */     super(null);
/* 31 */     if ((paramInt < 0) || (paramInt >= paramDoubleMatrix2D.rows())) throw new IllegalArgumentException();
/* 32 */     setUp(paramDoubleMatrix2D.columns());
/* 33 */     this.row = paramInt;
/* 34 */     this.content = paramDoubleMatrix2D;
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
/*    */   public double getQuick(int paramInt)
/*    */   {
/* 47 */     return this.content.getQuick(this.row, paramInt);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DoubleMatrix1D like(int paramInt)
/*    */   {
/* 59 */     return this.content.like1D(paramInt);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public DoubleMatrix2D like2D(int paramInt1, int paramInt2)
/*    */   {
/* 71 */     return this.content.like(paramInt1, paramInt2);
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
/*    */   public void setQuick(int paramInt, double paramDouble)
/*    */   {
/* 84 */     this.content.setQuick(this.row, paramInt, paramDouble);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/DelegateDoubleMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */