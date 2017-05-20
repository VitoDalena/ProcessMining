/*     */ package cern.colt.matrix;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.matrix.impl.DenseDoubleMatrix3D;
/*     */ import cern.colt.matrix.impl.SparseDoubleMatrix3D;
/*     */ import cern.jet.math.Functions;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DoubleFactory3D
/*     */   extends PersistentObject
/*     */ {
/*  38 */   public static final DoubleFactory3D dense = new DoubleFactory3D();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  43 */   public static final DoubleFactory3D sparse = new DoubleFactory3D();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix3D ascending(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  53 */     Functions localFunctions = Functions.functions;
/*  54 */     return descending(paramInt1, paramInt2, paramInt3).assign(Functions.chain(Functions.neg, Functions.minus(paramInt1 * paramInt2 * paramInt3)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix3D descending(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  61 */     DoubleMatrix3D localDoubleMatrix3D = make(paramInt1, paramInt2, paramInt3);
/*  62 */     int i = 0;
/*  63 */     int j = paramInt1;
/*  64 */     do { int k = paramInt2;
/*  65 */       do { int m = paramInt3;
/*  66 */         do { localDoubleMatrix3D.setQuick(j, k, m, i++);m--;
/*  65 */         } while (m >= 0);
/*  64 */         k--; } while (k >= 0);
/*  63 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  70 */     return localDoubleMatrix3D;
/*     */   }
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
/*     */   public DoubleMatrix3D make(double[][][] paramArrayOfDouble)
/*     */   {
/*  85 */     if (this == sparse) return new SparseDoubleMatrix3D(paramArrayOfDouble);
/*  86 */     return new DenseDoubleMatrix3D(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public DoubleMatrix3D make(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  92 */     if (this == sparse) return new SparseDoubleMatrix3D(paramInt1, paramInt2, paramInt3);
/*  93 */     return new DenseDoubleMatrix3D(paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */   
/*     */ 
/*     */   public DoubleMatrix3D make(int paramInt1, int paramInt2, int paramInt3, double paramDouble)
/*     */   {
/*  99 */     return make(paramInt1, paramInt2, paramInt3).assign(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   public DoubleMatrix3D random(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 105 */     return make(paramInt1, paramInt2, paramInt3).assign(Functions.random());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/DoubleFactory3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */