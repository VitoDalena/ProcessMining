/*     */ package cern.colt;
/*     */ 
/*     */ import cern.colt.function.IntComparator;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.doublealgo.Sorting;
/*     */ import cern.colt.matrix.impl.DenseDoubleMatrix2D;
/*     */ import cern.jet.random.Uniform;
/*     */ import cern.jet.random.engine.MersenneTwister;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ class GenericSortingTest
/*     */ {
/*     */   public static void demo1()
/*     */   {
/*  31 */     int[] arrayOfInt = { 3, 2, 1 };
/*  32 */     double[] arrayOfDouble1 = { 3.0D, 2.0D, 1.0D };
/*  33 */     double[] arrayOfDouble2 = { 6.0D, 7.0D, 8.0D };
/*     */     
/*  35 */     Swapper local1 = new Swapper() {
/*     */       private final int[] val$x;
/*     */       
/*  38 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { int i = this.val$x[paramAnonymousInt1];this.val$x[paramAnonymousInt1] = this.val$x[paramAnonymousInt2];this.val$x[paramAnonymousInt2] = i;
/*  39 */         double d1 = this.val$y[paramAnonymousInt1];this.val$y[paramAnonymousInt1] = this.val$y[paramAnonymousInt2];this.val$y[paramAnonymousInt2] = d1;
/*  40 */         double d2 = this.val$z[paramAnonymousInt1];this.val$z[paramAnonymousInt1] = this.val$z[paramAnonymousInt2];this.val$z[paramAnonymousInt2] = d2;
/*     */       }
/*     */       
/*  43 */     };
/*  44 */     IntComparator local2 = new IntComparator() { private final double[] val$y;
/*     */       
/*  46 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { return this.val$x[paramAnonymousInt1] < this.val$x[paramAnonymousInt2] ? -1 : this.val$x[paramAnonymousInt1] == this.val$x[paramAnonymousInt2] ? 0 : 1;
/*     */       }
/*     */ 
/*  49 */     };
/*  50 */     System.out.println("before:");
/*  51 */     System.out.println("X=" + Arrays.toString(arrayOfInt));
/*  52 */     System.out.println("Y=" + Arrays.toString(arrayOfDouble1));
/*  53 */     System.out.println("Z=" + Arrays.toString(arrayOfDouble2));
/*     */     
/*     */ 
/*  56 */     int i = 0;
/*  57 */     int j = arrayOfInt.length;
/*  58 */     GenericSorting.quickSort(i, j, local2, local1);
/*     */     
/*  60 */     System.out.println("after:");
/*  61 */     System.out.println("X=" + Arrays.toString(arrayOfInt));
/*  62 */     System.out.println("Y=" + Arrays.toString(arrayOfDouble1));
/*  63 */     System.out.println("Z=" + Arrays.toString(arrayOfDouble2));
/*  64 */     System.out.println("\n\n");
/*     */   }
/*     */   
/*     */ 
/*     */   private final double[] val$z;
/*     */   
/*     */   private final int[] val$x;
/*     */   
/*     */   public static void demo2()
/*     */   {
/*  74 */     int[] arrayOfInt = { 6, 7, 8, 9 };
/*  75 */     double[] arrayOfDouble1 = { 3.0D, 2.0D, 1.0D, 3.0D };
/*  76 */     double[] arrayOfDouble2 = { 5.0D, 4.0D, 4.0D, 1.0D };
/*     */     
/*  78 */     Swapper local3 = new Swapper() {
/*     */       private final int[] val$x;
/*     */       
/*  81 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { int i = this.val$x[paramAnonymousInt1];this.val$x[paramAnonymousInt1] = this.val$x[paramAnonymousInt2];this.val$x[paramAnonymousInt2] = i;
/*  82 */         double d1 = this.val$y[paramAnonymousInt1];this.val$y[paramAnonymousInt1] = this.val$y[paramAnonymousInt2];this.val$y[paramAnonymousInt2] = d1;
/*  83 */         double d2 = this.val$z[paramAnonymousInt1];this.val$z[paramAnonymousInt1] = this.val$z[paramAnonymousInt2];this.val$z[paramAnonymousInt2] = d2;
/*     */       }
/*     */       
/*  86 */     };
/*  87 */     IntComparator local4 = new IntComparator() { private final double[] val$y;
/*     */       
/*  89 */       public int compare(int paramAnonymousInt1, int paramAnonymousInt2) { if (this.val$y[paramAnonymousInt1] == this.val$y[paramAnonymousInt2]) return this.val$z[paramAnonymousInt1] < this.val$z[paramAnonymousInt2] ? -1 : this.val$z[paramAnonymousInt1] == this.val$z[paramAnonymousInt2] ? 0 : 1;
/*  90 */         return this.val$y[paramAnonymousInt1] < this.val$y[paramAnonymousInt2] ? -1 : 1;
/*     */       }
/*     */       
/*     */ 
/*  94 */     };
/*  95 */     System.out.println("before:");
/*  96 */     System.out.println("X=" + Arrays.toString(arrayOfInt));
/*  97 */     System.out.println("Y=" + Arrays.toString(arrayOfDouble1));
/*  98 */     System.out.println("Z=" + Arrays.toString(arrayOfDouble2));
/*     */     
/*     */ 
/* 101 */     int i = 0;
/* 102 */     int j = arrayOfInt.length;
/* 103 */     GenericSorting.quickSort(i, j, local4, local3);
/*     */     
/* 105 */     System.out.println("after:");
/* 106 */     System.out.println("X=" + Arrays.toString(arrayOfInt));
/* 107 */     System.out.println("Y=" + Arrays.toString(arrayOfDouble1));
/* 108 */     System.out.println("Z=" + Arrays.toString(arrayOfDouble2));
/* 109 */     System.out.println("\n\n");
/*     */   }
/*     */   
/*     */   private final double[] val$z;
/*     */   private final double[] val$y;
/*     */   private final double[] val$z;
/* 115 */   public static void testRandomly(int paramInt) { MersenneTwister localMersenneTwister = new MersenneTwister();
/* 116 */     Uniform localUniform = new Uniform(localMersenneTwister);
/*     */     
/* 118 */     for (int i = 0; i < paramInt; i++) {
/* 119 */       int j = 50;
/* 120 */       int k = 2 * j;
/*     */       
/*     */ 
/* 123 */       int m = localUniform.nextIntFromTo(1, j);
/*     */       int n;
/* 125 */       int i1; if (m == 0) {
/* 126 */         n = 0;i1 = -1;
/*     */       }
/*     */       else {
/* 129 */         n = localUniform.nextIntFromTo(0, m - 1);
/* 130 */         i1 = localUniform.nextIntFromTo(Math.min(n, m - 1), m - 1);
/*     */       }
/*     */       
/* 133 */       DenseDoubleMatrix2D localDenseDoubleMatrix2D = new DenseDoubleMatrix2D(m, m);
/* 134 */       DoubleMatrix2D localDoubleMatrix2D1 = localDenseDoubleMatrix2D.viewPart(n, n, m - i1, m - i1);
/*     */       
/* 136 */       int i2 = localUniform.nextIntFromTo(m / 2, 2 * m);
/* 137 */       int i3 = localUniform.nextIntFromTo(i2, 2 * m);
/*     */       
/* 139 */       for (int i4 = 0; i4 < m; i4++) {
/* 140 */         for (int i5 = 0; i5 < m; i5++) {
/* 141 */           localDenseDoubleMatrix2D.set(i4, i5, localUniform.nextIntFromTo(i2, i3));
/*     */         }
/*     */       }
/*     */       
/* 145 */       DoubleMatrix2D localDoubleMatrix2D2 = localDenseDoubleMatrix2D.copy();
/* 146 */       DoubleMatrix2D localDoubleMatrix2D3 = localDoubleMatrix2D2.viewPart(n, n, m - i1, m - i1);
/*     */       
/* 148 */       int i6 = 0;
/* 149 */       DoubleMatrix2D localDoubleMatrix2D4 = Sorting.quickSort.sort(localDoubleMatrix2D1, i6);
/* 150 */       DoubleMatrix2D localDoubleMatrix2D5 = Sorting.mergeSort.sort(localDoubleMatrix2D3, i6);
/*     */       
/* 152 */       if (!localDoubleMatrix2D4.viewColumn(i6).equals(localDoubleMatrix2D5.viewColumn(i6))) { throw new InternalError();
/*     */       }
/*     */     }
/* 155 */     System.out.println("All tests passed. No bug detected.");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/GenericSortingTest.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */