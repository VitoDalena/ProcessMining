/*     */ package cern.colt.matrix.linalg;
/*     */ 
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LUDecomposition
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 1020L;
/*     */   protected LUDecompositionQuick quick;
/*     */   
/*     */   public LUDecomposition(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  35 */     this.quick = new LUDecompositionQuick(0.0D);
/*  36 */     this.quick.decompose(paramDoubleMatrix2D.copy());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double det()
/*     */   {
/*  43 */     return this.quick.det();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private double[] getDoublePivot()
/*     */   {
/*  50 */     return this.quick.getDoublePivot();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getL()
/*     */   {
/*  57 */     return this.quick.getL();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int[] getPivot()
/*     */   {
/*  64 */     return (int[])this.quick.getPivot().clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getU()
/*     */   {
/*  71 */     return this.quick.getU();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isNonsingular()
/*     */   {
/*  78 */     return this.quick.isNonsingular();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D solve(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  90 */     DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix2D.copy();
/*  91 */     this.quick.solve(localDoubleMatrix2D);
/*  92 */     return localDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 104 */     return this.quick.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/linalg/LUDecomposition.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */