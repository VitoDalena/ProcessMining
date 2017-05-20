/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.Timer;
/*     */ import cern.colt.matrix.DoubleFactory2D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import java.io.PrintStream;
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
/*     */ class Benchmark
/*     */ {
/*     */   protected Benchmark()
/*     */   {
/*  24 */     throw new RuntimeException("Non instantiable");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void benchmark(int paramInt1, int paramInt2, String paramString, boolean paramBoolean, int paramInt3, double paramDouble1, double paramDouble2, double paramDouble3)
/*     */   {
/*  33 */     Timer localTimer1 = new Timer();
/*  34 */     Timer localTimer2 = new Timer();
/*  35 */     Timer localTimer3 = new Timer();
/*  36 */     Timer localTimer4 = new Timer();
/*  37 */     Timer localTimer5 = new Timer();
/*  38 */     Timer localTimer6 = new Timer();
/*     */     
/*  40 */     Object localObject = null;
/*  41 */     if (paramString.equals("sparse")) { localObject = new SparseDoubleMatrix2D(paramInt2, paramInt2, paramInt3, paramDouble1, paramDouble2);
/*  42 */     } else if (paramString.equals("dense")) { localObject = DoubleFactory2D.dense.make(paramInt2, paramInt2);
/*     */     } else {
/*  44 */       throw new RuntimeException("unknown kind");
/*     */     }
/*  46 */     System.out.println("\nNow initializing...");
/*     */     
/*     */ 
/*  49 */     double d1 = 2.0D;
/*  50 */     DoubleMatrix2D localDoubleMatrix2D1 = DoubleFactory2D.dense.sample(((AbstractMatrix2D)localObject).rows(), ((AbstractMatrix2D)localObject).columns(), d1, paramDouble3);
/*  51 */     ((DoubleMatrix2D)localObject).assign(localDoubleMatrix2D1);
/*  52 */     localDoubleMatrix2D1 = null;
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
/*  84 */     System.out.println("\ntesting...");
/*  85 */     if (paramBoolean) System.out.println(localObject);
/*  86 */     DoubleMatrix2D localDoubleMatrix2D2 = DoubleFactory2D.dense.make(paramInt2, paramInt2);
/*  87 */     localDoubleMatrix2D2.assign((DoubleMatrix2D)localObject);
/*  88 */     if (!localDoubleMatrix2D2.equals(localObject)) throw new InternalError();
/*  89 */     DoubleMatrix2D localDoubleMatrix2D3 = localDoubleMatrix2D2.copy();
/*  90 */     DoubleMatrix2D localDoubleMatrix2D4 = localDoubleMatrix2D2.copy();
/*  91 */     DoubleMatrix2D localDoubleMatrix2D5 = localDoubleMatrix2D2.copy();
/*  92 */     localDoubleMatrix2D3.zMult(localDoubleMatrix2D4, localDoubleMatrix2D5);
/*  93 */     System.out.println("\nNext testing...");
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
/* 106 */     DoubleMatrix2D localDoubleMatrix2D6 = ((DoubleMatrix2D)localObject).copy();
/* 107 */     DoubleMatrix2D localDoubleMatrix2D7 = ((DoubleMatrix2D)localObject).copy();
/*     */     
/* 109 */     DoubleMatrix2D localDoubleMatrix2D8 = ((DoubleMatrix2D)localObject).copy();
/* 110 */     localDoubleMatrix2D6.zMult(localDoubleMatrix2D7, localDoubleMatrix2D8);
/* 111 */     if (!localDoubleMatrix2D8.equals(localDoubleMatrix2D5)) throw new InternalError();
/* 112 */     localDoubleMatrix2D8.assign((DoubleMatrix2D)localObject);
/* 113 */     System.out.println("\nNow benchmarking...");
/*     */     
/* 115 */     localTimer3.start();
/* 116 */     for (int i = 0; i < paramInt1; i++) {
/* 117 */       localDoubleMatrix2D6.zMult(localDoubleMatrix2D7, localDoubleMatrix2D8);
/*     */     }
/* 119 */     localTimer3.stop();
/* 120 */     localTimer3.display();
/* 121 */     int j = localDoubleMatrix2D6.rows();
/* 122 */     int k = localDoubleMatrix2D6.columns();
/* 123 */     int m = localDoubleMatrix2D7.rows();
/* 124 */     int n = paramInt1;
/* 125 */     double d2 = 0.001D * (2.0D * j * k * m * n) / localTimer3.millis();
/* 126 */     System.out.println("mflops: " + d2);
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
/* 158 */     if (paramBoolean) { System.out.println(localObject);
/*     */     }
/* 160 */     System.out.println("bye bye.");
/*     */   }
/*     */   
/*     */ 
/*     */   protected static double cubicLoop(int paramInt1, int paramInt2)
/*     */   {
/* 166 */     double d1 = 1.123D;
/* 167 */     double d2 = 1.000000000012345D;
/* 168 */     for (int i = 0; i < paramInt1; i++) {
/* 169 */       int j = paramInt2;
/* 170 */       do { int k = paramInt2;
/* 171 */         do { int m = paramInt2;
/* 172 */           do { d1 *= d2;m--;
/* 171 */           } while (m >= 0);
/* 170 */           k--; } while (k >= 0);
/* 169 */         j--; } while (j >= 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 177 */     return d1;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 183 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 184 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 185 */     int k = Integer.parseInt(paramArrayOfString[2]);
/*     */     
/*     */ 
/* 188 */     String str = paramArrayOfString[3];
/* 189 */     int m = Integer.parseInt(paramArrayOfString[4]);
/* 190 */     double d1 = new Double(paramArrayOfString[5]).doubleValue();
/* 191 */     double d2 = new Double(paramArrayOfString[6]).doubleValue();
/* 192 */     boolean bool = paramArrayOfString[7].equals("print");
/* 193 */     double d3 = new Double(paramArrayOfString[8]).doubleValue();
/* 194 */     int n = j;
/*     */     
/* 196 */     benchmark(i, n, str, bool, m, d1, d2, d3);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/Benchmark.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */