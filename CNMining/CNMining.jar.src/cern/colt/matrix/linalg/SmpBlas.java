/*     */ package cern.colt.matrix.linalg;
/*     */ 
/*     */ import cern.colt.function.DoubleDoubleFunction;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import edu.oswego.cs.dl.util.concurrent.FJTask;
/*     */ import edu.oswego.cs.dl.util.concurrent.FJTaskRunnerGroup;
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
/*     */ public class SmpBlas
/*     */   implements Blas
/*     */ {
/*  63 */   public static Blas smpBlas = SeqBlas.seqBlas;
/*     */   
/*     */   protected Blas seqBlas;
/*     */   
/*     */   protected Smp smp;
/*     */   
/*     */   protected int maxThreads;
/*     */   
/*  71 */   protected static int NN_THRESHOLD = 30000;
/*     */   
/*     */ 
/*     */   protected SmpBlas(int paramInt, Blas paramBlas)
/*     */   {
/*  76 */     this.seqBlas = paramBlas;
/*  77 */     this.maxThreads = paramInt;
/*  78 */     this.smp = new Smp(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void allocateBlas(int paramInt, Blas paramBlas)
/*     */   {
/*  89 */     if ((smpBlas instanceof SmpBlas)) {
/*  90 */       SmpBlas localSmpBlas = (SmpBlas)smpBlas;
/*  91 */       if ((localSmpBlas.maxThreads == paramInt) && (localSmpBlas.seqBlas == paramBlas)) { return;
/*     */       }
/*     */     }
/*  94 */     if (paramInt <= 1) {
/*  95 */       smpBlas = paramBlas;
/*     */     } else
/*  97 */       smpBlas = new SmpBlas(paramInt, paramBlas);
/*     */   }
/*     */   
/*     */   public void assign(DoubleMatrix2D paramDoubleMatrix2D, DoubleFunction paramDoubleFunction) {
/* 101 */     run(paramDoubleMatrix2D, false, new Matrix2DMatrix2DFunction() {
/*     */       private final DoubleFunction val$function;
/*     */       
/* 104 */       public double apply(DoubleMatrix2D paramAnonymousDoubleMatrix2D1, DoubleMatrix2D paramAnonymousDoubleMatrix2D2) { SmpBlas.this.seqBlas.assign(paramAnonymousDoubleMatrix2D1, this.val$function);
/* 105 */         return 0.0D;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void assign(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, DoubleDoubleFunction paramDoubleDoubleFunction) {
/* 111 */     run(paramDoubleMatrix2D1, paramDoubleMatrix2D2, false, new Matrix2DMatrix2DFunction() {
/*     */       private final DoubleDoubleFunction val$function;
/*     */       
/* 114 */       public double apply(DoubleMatrix2D paramAnonymousDoubleMatrix2D1, DoubleMatrix2D paramAnonymousDoubleMatrix2D2) { SmpBlas.this.seqBlas.assign(paramAnonymousDoubleMatrix2D1, paramAnonymousDoubleMatrix2D2, this.val$function);
/* 115 */         return 0.0D;
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public double dasum(DoubleMatrix1D paramDoubleMatrix1D) {
/* 121 */     return this.seqBlas.dasum(paramDoubleMatrix1D);
/*     */   }
/*     */   
/* 124 */   public void daxpy(double paramDouble, DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2) { this.seqBlas.daxpy(paramDouble, paramDoubleMatrix1D1, paramDoubleMatrix1D2); }
/*     */   
/*     */   public void daxpy(double paramDouble, DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2) {
/* 127 */     this.seqBlas.daxpy(paramDouble, paramDoubleMatrix2D1, paramDoubleMatrix2D2);
/*     */   }
/*     */   
/* 130 */   public void dcopy(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2) { this.seqBlas.dcopy(paramDoubleMatrix1D1, paramDoubleMatrix1D2); }
/*     */   
/*     */   public void dcopy(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2) {
/* 133 */     this.seqBlas.dcopy(paramDoubleMatrix2D1, paramDoubleMatrix2D2);
/*     */   }
/*     */   
/* 136 */   public double ddot(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2) { return this.seqBlas.ddot(paramDoubleMatrix1D1, paramDoubleMatrix1D2); }
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
/*     */ 
/*     */   public void dgemm(boolean paramBoolean1, boolean paramBoolean2, double paramDouble1, DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, double paramDouble2, DoubleMatrix2D paramDoubleMatrix2D3)
/*     */   {
/* 168 */     if (paramBoolean1) {
/* 169 */       dgemm(false, paramBoolean2, paramDouble1, paramDoubleMatrix2D1.viewDice(), paramDoubleMatrix2D2, paramDouble2, paramDoubleMatrix2D3);
/* 170 */       return;
/*     */     }
/* 172 */     if (paramBoolean2) {
/* 173 */       dgemm(paramBoolean1, false, paramDouble1, paramDoubleMatrix2D1, paramDoubleMatrix2D2.viewDice(), paramDouble2, paramDoubleMatrix2D3);
/* 174 */       return;
/*     */     }
/* 176 */     int i = paramDoubleMatrix2D1.rows();
/* 177 */     int j = paramDoubleMatrix2D1.columns();
/* 178 */     int k = paramDoubleMatrix2D2.columns();
/*     */     
/* 180 */     if (paramDoubleMatrix2D2.rows() != j)
/* 181 */       throw new IllegalArgumentException("Matrix2D inner dimensions must agree:" + paramDoubleMatrix2D1.toStringShort() + ", " + paramDoubleMatrix2D2.toStringShort());
/* 182 */     if ((paramDoubleMatrix2D3.rows() != i) || (paramDoubleMatrix2D3.columns() != k))
/* 183 */       throw new IllegalArgumentException("Incompatibel result matrix: " + paramDoubleMatrix2D1.toStringShort() + ", " + paramDoubleMatrix2D2.toStringShort() + ", " + paramDoubleMatrix2D3.toStringShort());
/* 184 */     if ((paramDoubleMatrix2D1 == paramDoubleMatrix2D3) || (paramDoubleMatrix2D2 == paramDoubleMatrix2D3)) {
/* 185 */       throw new IllegalArgumentException("Matrices must not be identical");
/*     */     }
/* 187 */     long l = 2L * i * j * k;
/* 188 */     int m = (int)Math.min(l / 30000L, this.maxThreads);
/* 189 */     int n = k >= m ? 1 : 0;
/* 190 */     int i1 = n != 0 ? k : i;
/* 191 */     m = Math.min(i1, m);
/*     */     
/* 193 */     if (m < 2) {
/* 194 */       this.seqBlas.dgemm(paramBoolean1, paramBoolean2, paramDouble1, paramDoubleMatrix2D1, paramDoubleMatrix2D2, paramDouble2, paramDoubleMatrix2D3);
/* 195 */       return;
/*     */     }
/*     */     
/*     */ 
/* 199 */     int i2 = i1 / m;
/* 200 */     FJTask[] arrayOfFJTask = new FJTask[m];
/* 201 */     for (int i3 = 0; i3 < m; i3++) {
/* 202 */       int i4 = i3 * i2;
/* 203 */       if (i3 == m - 1) i2 = i1 - i2 * i3;
/*     */       DoubleMatrix2D localDoubleMatrix2D1;
/*     */       DoubleMatrix2D localDoubleMatrix2D2;
/* 206 */       DoubleMatrix2D localDoubleMatrix2D3; if (n != 0)
/*     */       {
/* 208 */         localDoubleMatrix2D1 = paramDoubleMatrix2D1;
/* 209 */         localDoubleMatrix2D2 = paramDoubleMatrix2D2.viewPart(0, i4, j, i2);
/* 210 */         localDoubleMatrix2D3 = paramDoubleMatrix2D3.viewPart(0, i4, i, i2);
/*     */       }
/*     */       else
/*     */       {
/* 214 */         localDoubleMatrix2D1 = paramDoubleMatrix2D1.viewPart(i4, 0, i2, j);
/* 215 */         localDoubleMatrix2D2 = paramDoubleMatrix2D2;
/* 216 */         localDoubleMatrix2D3 = paramDoubleMatrix2D3.viewPart(i4, 0, i2, k);
/*     */       }
/*     */       
/* 219 */       arrayOfFJTask[i3 = new FJTask() { private final boolean val$transposeA;
/*     */         
/* 221 */         public void run() { SmpBlas.this.seqBlas.dgemm(this.val$transposeA, this.val$transposeB, this.val$alpha, this.val$AA, this.val$BB, this.val$beta, this.val$CC); }
/*     */       };
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 229 */       this.smp.taskGroup.invoke(new FJTask() { private final boolean val$transposeB;
/*     */         private final double val$alpha;
/*     */         
/* 232 */         public void run() { FJTask.coInvoke(this.val$subTasks); }
/*     */       });
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private final DoubleMatrix2D val$AA;
/*     */   
/*     */ 
/*     */   private final DoubleMatrix2D val$BB;
/*     */   
/*     */ 
/*     */   private final double val$beta;
/*     */   
/*     */ 
/*     */   private final DoubleMatrix2D val$CC;
/*     */   
/*     */   private final FJTask[] val$subTasks;
/*     */   
/*     */   public void dgemv(boolean paramBoolean, double paramDouble1, DoubleMatrix2D paramDoubleMatrix2D, DoubleMatrix1D paramDoubleMatrix1D1, double paramDouble2, DoubleMatrix1D paramDoubleMatrix1D2)
/*     */   {
/* 255 */     if (paramBoolean) {
/* 256 */       dgemv(false, paramDouble1, paramDoubleMatrix2D.viewDice(), paramDoubleMatrix1D1, paramDouble2, paramDoubleMatrix1D2);
/* 257 */       return;
/*     */     }
/* 259 */     int i = paramDoubleMatrix2D.rows();
/* 260 */     int j = paramDoubleMatrix2D.columns();
/* 261 */     long l = 2L * i * j;
/* 262 */     int k = (int)Math.min(l / 30000L, this.maxThreads);
/* 263 */     int m = paramDoubleMatrix2D.rows();
/* 264 */     k = Math.min(m, k);
/*     */     
/* 266 */     if (k < 2) {
/* 267 */       this.seqBlas.dgemv(paramBoolean, paramDouble1, paramDoubleMatrix2D, paramDoubleMatrix1D1, paramDouble2, paramDoubleMatrix1D2);
/* 268 */       return;
/*     */     }
/*     */     
/*     */ 
/* 272 */     int n = m / k;
/* 273 */     FJTask[] arrayOfFJTask = new FJTask[k];
/* 274 */     for (int i1 = 0; i1 < k; i1++) {
/* 275 */       int i2 = i1 * n;
/* 276 */       if (i1 == k - 1) { n = m - n * i1;
/*     */       }
/*     */       
/* 279 */       DoubleMatrix2D localDoubleMatrix2D = paramDoubleMatrix2D.viewPart(i2, 0, n, j);
/* 280 */       DoubleMatrix1D localDoubleMatrix1D = paramDoubleMatrix1D2.viewPart(i2, n);
/*     */       
/* 282 */       arrayOfFJTask[i1 = new FJTask() { private final boolean val$transposeA;
/*     */         
/* 284 */         public void run() { SmpBlas.this.seqBlas.dgemv(this.val$transposeA, this.val$alpha, this.val$AA, this.val$x, this.val$beta, this.val$yy); }
/*     */       };
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 292 */       this.smp.taskGroup.invoke(new FJTask() { private final double val$alpha;
/*     */         private final DoubleMatrix2D val$AA;
/*     */         
/* 295 */         public void run() { FJTask.coInvoke(this.val$subTasks); }
/*     */       });
/*     */     } catch (InterruptedException localInterruptedException) {}
/*     */   }
/*     */   
/*     */   private final DoubleMatrix1D val$x;
/*     */   private final double val$beta;
/* 302 */   public void dger(double paramDouble, DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, DoubleMatrix2D paramDoubleMatrix2D) { this.seqBlas.dger(paramDouble, paramDoubleMatrix1D1, paramDoubleMatrix1D2, paramDoubleMatrix2D); }
/*     */   
/*     */ 
/* 305 */   public double dnrm2(DoubleMatrix1D paramDoubleMatrix1D) { return this.seqBlas.dnrm2(paramDoubleMatrix1D); }
/*     */   
/*     */   public void drot(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, double paramDouble1, double paramDouble2) {
/* 308 */     this.seqBlas.drot(paramDoubleMatrix1D1, paramDoubleMatrix1D2, paramDouble1, paramDouble2);
/*     */   }
/*     */   
/* 311 */   public void drotg(double paramDouble1, double paramDouble2, double[] paramArrayOfDouble) { this.seqBlas.drotg(paramDouble1, paramDouble2, paramArrayOfDouble); }
/*     */   
/*     */   public void dscal(double paramDouble, DoubleMatrix1D paramDoubleMatrix1D) {
/* 314 */     this.seqBlas.dscal(paramDouble, paramDoubleMatrix1D);
/*     */   }
/*     */   
/* 317 */   public void dscal(double paramDouble, DoubleMatrix2D paramDoubleMatrix2D) { this.seqBlas.dscal(paramDouble, paramDoubleMatrix2D); }
/*     */   
/*     */   public void dswap(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2) {
/* 320 */     this.seqBlas.dswap(paramDoubleMatrix1D1, paramDoubleMatrix1D2);
/*     */   }
/*     */   
/* 323 */   public void dswap(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2) { this.seqBlas.dswap(paramDoubleMatrix2D1, paramDoubleMatrix2D2); }
/*     */   
/*     */   public void dsymv(boolean paramBoolean, double paramDouble1, DoubleMatrix2D paramDoubleMatrix2D, DoubleMatrix1D paramDoubleMatrix1D1, double paramDouble2, DoubleMatrix1D paramDoubleMatrix1D2) {
/* 326 */     this.seqBlas.dsymv(paramBoolean, paramDouble1, paramDoubleMatrix2D, paramDoubleMatrix1D1, paramDouble2, paramDoubleMatrix1D2);
/*     */   }
/*     */   
/* 329 */   public void dtrmv(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, DoubleMatrix2D paramDoubleMatrix2D, DoubleMatrix1D paramDoubleMatrix1D) { this.seqBlas.dtrmv(paramBoolean1, paramBoolean2, paramBoolean3, paramDoubleMatrix2D, paramDoubleMatrix1D); }
/*     */   
/*     */   public int idamax(DoubleMatrix1D paramDoubleMatrix1D) {
/* 332 */     return this.seqBlas.idamax(paramDoubleMatrix1D);
/*     */   }
/*     */   
/*     */   protected double[] run(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, boolean paramBoolean, Matrix2DMatrix2DFunction paramMatrix2DMatrix2DFunction) {
/* 336 */     DoubleMatrix2D[][] arrayOfDoubleMatrix2D = this.smp.splitBlockedNN(paramDoubleMatrix2D1, paramDoubleMatrix2D2, NN_THRESHOLD, paramDoubleMatrix2D1.rows() * paramDoubleMatrix2D1.columns());
/*     */     
/* 338 */     int i = arrayOfDoubleMatrix2D != null ? arrayOfDoubleMatrix2D[0].length : 1;
/* 339 */     double[] arrayOfDouble = paramBoolean ? new double[i] : null;
/*     */     
/* 341 */     if (arrayOfDoubleMatrix2D == null) {
/* 342 */       double d = paramMatrix2DMatrix2DFunction.apply(paramDoubleMatrix2D1, paramDoubleMatrix2D2);
/* 343 */       if (paramBoolean) arrayOfDouble[0] = d;
/* 344 */       return arrayOfDouble;
/*     */     }
/*     */     
/* 347 */     this.smp.run(arrayOfDoubleMatrix2D[0], arrayOfDoubleMatrix2D[1], arrayOfDouble, paramMatrix2DMatrix2DFunction);
/*     */     
/* 349 */     return arrayOfDouble;
/*     */   }
/*     */   
/*     */   protected double[] run(DoubleMatrix2D paramDoubleMatrix2D, boolean paramBoolean, Matrix2DMatrix2DFunction paramMatrix2DMatrix2DFunction) {
/* 353 */     DoubleMatrix2D[] arrayOfDoubleMatrix2D = this.smp.splitBlockedNN(paramDoubleMatrix2D, NN_THRESHOLD, paramDoubleMatrix2D.rows() * paramDoubleMatrix2D.columns());
/*     */     
/* 355 */     int i = arrayOfDoubleMatrix2D != null ? arrayOfDoubleMatrix2D.length : 1;
/* 356 */     double[] arrayOfDouble = paramBoolean ? new double[i] : null;
/*     */     
/* 358 */     if (arrayOfDoubleMatrix2D == null) {
/* 359 */       double d = paramMatrix2DMatrix2DFunction.apply(paramDoubleMatrix2D, null);
/* 360 */       if (paramBoolean) arrayOfDouble[0] = d;
/* 361 */       return arrayOfDouble;
/*     */     }
/*     */     
/* 364 */     this.smp.run(arrayOfDoubleMatrix2D, null, arrayOfDouble, paramMatrix2DMatrix2DFunction);
/*     */     
/* 366 */     return arrayOfDouble;
/*     */   }
/*     */   
/*     */   private final DoubleMatrix1D val$yy;
/*     */   private final FJTask[] val$subTasks;
/*     */   public void stats() {
/* 372 */     if (this.smp != null) this.smp.stats();
/*     */   }
/*     */   
/* 375 */   private double xsum(DoubleMatrix2D paramDoubleMatrix2D) { double[] arrayOfDouble = run(paramDoubleMatrix2D, true, new Matrix2DMatrix2DFunction()
/*     */     {
/*     */       public double apply(DoubleMatrix2D paramAnonymousDoubleMatrix2D1, DoubleMatrix2D paramAnonymousDoubleMatrix2D2) {
/* 378 */         return paramAnonymousDoubleMatrix2D1.zSum();
/*     */       }
/*     */       
/*     */ 
/* 382 */     });
/* 383 */     double d = 0.0D;
/* 384 */     int i = arrayOfDouble.length; do { d += arrayOfDouble[i];i--; } while (i >= 0);
/* 385 */     return d;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/linalg/SmpBlas.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */