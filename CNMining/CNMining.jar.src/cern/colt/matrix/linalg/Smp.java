/*     */ package cern.colt.matrix.linalg;
/*     */ 
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
/*     */ class Smp
/*     */ {
/*     */   protected FJTaskRunnerGroup taskGroup;
/*     */   protected int maxThreads;
/*     */   
/*     */   protected Smp(int paramInt)
/*     */   {
/*  23 */     paramInt = Math.max(1, paramInt);
/*  24 */     this.maxThreads = paramInt;
/*  25 */     if (paramInt > 1) {
/*  26 */       this.taskGroup = new FJTaskRunnerGroup(paramInt);
/*     */     }
/*     */     else {
/*  29 */       this.taskGroup = null;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void finalize()
/*     */   {
/*  36 */     if (this.taskGroup != null) this.taskGroup.interruptAll();
/*     */   }
/*     */   
/*  39 */   protected void run(DoubleMatrix2D[] paramArrayOfDoubleMatrix2D1, DoubleMatrix2D[] paramArrayOfDoubleMatrix2D2, double[] paramArrayOfDouble, Matrix2DMatrix2DFunction paramMatrix2DMatrix2DFunction) { FJTask[] arrayOfFJTask = new FJTask[paramArrayOfDoubleMatrix2D1.length];
/*  40 */     for (int i = 0; i < paramArrayOfDoubleMatrix2D1.length; i++) {
/*  41 */       int j = i;
/*  42 */       arrayOfFJTask[i = new FJTask() { private final Matrix2DMatrix2DFunction val$function;
/*     */         
/*  44 */         public void run() { double d = this.val$function.apply(this.val$blocksA[this.val$k], this.val$blocksB != null ? this.val$blocksB[this.val$k] : null);
/*  45 */           if (this.val$results != null) { this.val$results[this.val$k] = d;
/*     */           }
/*     */         }
/*     */       };
/*     */     }
/*     */     
/*     */     try
/*     */     {
/*  53 */       this.taskGroup.invoke(new FJTask() {
/*     */         private final DoubleMatrix2D[] val$blocksA;
/*     */         
/*  56 */         public void run() { FJTask.coInvoke(this.val$subTasks); }
/*     */       });
/*     */     }
/*     */     catch (InterruptedException localInterruptedException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final int val$k;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final DoubleMatrix2D[] val$blocksB;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final double[] val$results;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private final FJTask[] val$subTasks;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix2D[] splitBlockedNN(DoubleMatrix2D paramDoubleMatrix2D, int paramInt, long paramLong)
/*     */   {
/*  93 */     int i = (int)Math.min(paramLong / paramInt, this.maxThreads);
/*  94 */     int j = paramDoubleMatrix2D.columns() < i ? 1 : 0;
/*     */     
/*  96 */     int k = j != 0 ? paramDoubleMatrix2D.rows() : paramDoubleMatrix2D.columns();
/*  97 */     i = Math.min(k, i);
/*     */     
/*  99 */     if (i < 2) {
/* 100 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 104 */     int m = k / i;
/* 105 */     DoubleMatrix2D[] arrayOfDoubleMatrix2D = new DoubleMatrix2D[i];
/* 106 */     for (int n = 0; n < i; n++) {
/* 107 */       int i1 = n * m;
/* 108 */       if (n == i - 1) { m = k - m * n;
/*     */       }
/*     */       
/* 111 */       if (j == 0) {
/* 112 */         arrayOfDoubleMatrix2D[n] = paramDoubleMatrix2D.viewPart(0, i1, paramDoubleMatrix2D.rows(), m);
/*     */       }
/*     */       else {
/* 115 */         arrayOfDoubleMatrix2D[n] = paramDoubleMatrix2D.viewPart(i1, 0, m, paramDoubleMatrix2D.columns());
/*     */       }
/*     */     }
/* 118 */     return arrayOfDoubleMatrix2D;
/*     */   }
/*     */   
/* 121 */   protected DoubleMatrix2D[][] splitBlockedNN(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, int paramInt, long paramLong) { DoubleMatrix2D[] arrayOfDoubleMatrix2D1 = splitBlockedNN(paramDoubleMatrix2D1, paramInt, paramLong);
/* 122 */     if (arrayOfDoubleMatrix2D1 == null) return null;
/* 123 */     DoubleMatrix2D[] arrayOfDoubleMatrix2D2 = splitBlockedNN(paramDoubleMatrix2D2, paramInt, paramLong);
/* 124 */     if (arrayOfDoubleMatrix2D2 == null) return null;
/* 125 */     DoubleMatrix2D[][] arrayOfDoubleMatrix2D = { arrayOfDoubleMatrix2D1, arrayOfDoubleMatrix2D2 };
/* 126 */     return arrayOfDoubleMatrix2D;
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
/*     */   protected DoubleMatrix2D[] splitStridedNN(DoubleMatrix2D paramDoubleMatrix2D, int paramInt, long paramLong)
/*     */   {
/* 159 */     int i = (int)Math.min(paramLong / paramInt, this.maxThreads);
/* 160 */     int j = paramDoubleMatrix2D.columns() < i ? 1 : 0;
/*     */     
/* 162 */     int k = j != 0 ? paramDoubleMatrix2D.rows() : paramDoubleMatrix2D.columns();
/* 163 */     i = Math.min(k, i);
/*     */     
/* 165 */     if (i < 2) {
/* 166 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 170 */     int m = k / i;
/* 171 */     DoubleMatrix2D[] arrayOfDoubleMatrix2D = new DoubleMatrix2D[i];
/* 172 */     for (int n = 0; n < i; n++) {
/* 173 */       int i1 = n * m;
/* 174 */       if (n == i - 1) { m = k - m * n;
/*     */       }
/*     */       
/* 177 */       if (j == 0)
/*     */       {
/* 179 */         arrayOfDoubleMatrix2D[n] = paramDoubleMatrix2D.viewPart(0, n, paramDoubleMatrix2D.rows(), paramDoubleMatrix2D.columns() - n).viewStrides(1, i);
/*     */       }
/*     */       else
/*     */       {
/* 183 */         arrayOfDoubleMatrix2D[n] = paramDoubleMatrix2D.viewPart(n, 0, paramDoubleMatrix2D.rows() - n, paramDoubleMatrix2D.columns()).viewStrides(i, 1);
/*     */       }
/*     */     }
/* 186 */     return arrayOfDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */   public void stats()
/*     */   {
/* 192 */     if (this.taskGroup != null) this.taskGroup.stats();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/linalg/Smp.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */