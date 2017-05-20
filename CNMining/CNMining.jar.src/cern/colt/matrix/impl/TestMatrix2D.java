/*      */ package cern.colt.matrix.impl;
/*      */ 
/*      */ import cern.colt.GenericPermuting;
/*      */ import cern.colt.Swapper;
/*      */ import cern.colt.Timer;
/*      */ import cern.colt.list.IntArrayList;
/*      */ import cern.colt.map.AbstractIntDoubleMap;
/*      */ import cern.colt.map.OpenIntDoubleHashMap;
/*      */ import cern.colt.matrix.DoubleFactory1D;
/*      */ import cern.colt.matrix.DoubleFactory2D;
/*      */ import cern.colt.matrix.DoubleFactory3D;
/*      */ import cern.colt.matrix.DoubleMatrix1D;
/*      */ import cern.colt.matrix.DoubleMatrix2D;
/*      */ import cern.colt.matrix.DoubleMatrix3D;
/*      */ import cern.colt.matrix.doublealgo.Transform;
/*      */ import cern.colt.matrix.linalg.Algebra;
/*      */ import cern.colt.matrix.linalg.LUDecompositionQuick;
/*      */ import cern.colt.matrix.linalg.Property;
/*      */ import cern.jet.math.Functions;
/*      */ import cern.jet.random.engine.MersenneTwister;
/*      */ import hep.aida.bin.DynamicBin1D;
/*      */ import java.io.PrintStream;
/*      */ 
/*      */ public class TestMatrix2D
/*      */ {
/*   26 */   private static final Functions F = Functions.functions;
/*   27 */   private static final DoubleFactory2D Factory2D = DoubleFactory2D.dense;
/*   28 */   private static final DoubleFactory1D Factory1D = DoubleFactory1D.dense;
/*   29 */   private static final Algebra LinearAlgebra = Algebra.DEFAULT;
/*   30 */   private static final Transform Transform = Transform.transform;
/*   31 */   private static final Property Property = Property.DEFAULT;
/*      */   
/*      */ 
/*      */   protected TestMatrix2D()
/*      */   {
/*   36 */     throw new RuntimeException("Non instantiable");
/*      */   }
/*      */   
/*      */   public static void doubleTest()
/*      */   {
/*   41 */     int i = 4;
/*   42 */     int j = 5;
/*   43 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = new DenseDoubleMatrix2D(i, j);
/*   44 */     System.out.println(localDenseDoubleMatrix2D);
/*   45 */     localDenseDoubleMatrix2D.assign(1.0D);
/*   46 */     System.out.println("\n" + localDenseDoubleMatrix2D);
/*   47 */     localDenseDoubleMatrix2D.viewPart(2, 1, 2, 3).assign(2.0D);
/*   48 */     System.out.println("\n" + localDenseDoubleMatrix2D);
/*      */     
/*   50 */     DoubleMatrix2D localDoubleMatrix2D1 = localDenseDoubleMatrix2D.viewPart(2, 1, 2, 3).copy();
/*   51 */     localDoubleMatrix2D1.assign(3.0D);
/*   52 */     localDoubleMatrix2D1.set(0, 0, 4.0D);
/*   53 */     System.out.println("\n" + localDoubleMatrix2D1);
/*   54 */     System.out.println("\n" + localDenseDoubleMatrix2D);
/*      */     
/*   56 */     DoubleMatrix2D localDoubleMatrix2D2 = localDenseDoubleMatrix2D.viewPart(0, 3, 4, 2);
/*   57 */     DoubleMatrix2D localDoubleMatrix2D3 = localDoubleMatrix2D2.viewPart(0, 0, 4, 1);
/*   58 */     System.out.println("\n" + localDoubleMatrix2D2);
/*   59 */     System.out.println("\n" + localDoubleMatrix2D3);
/*      */   }
/*      */   
/*      */   public static void doubleTest(int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2)
/*      */   {
/*   64 */     SparseDoubleMatrix2D localSparseDoubleMatrix2D = new SparseDoubleMatrix2D(paramInt1, paramInt2, paramInt3, paramDouble1, paramDouble2);
/*   65 */     System.out.println(localSparseDoubleMatrix2D);
/*      */     
/*   67 */     System.out.println("adding...");
/*   68 */     int i = 0;
/*   69 */     for (int j = 0; j < paramInt2; j++) {
/*   70 */       for (k = 0; k < paramInt1; k++)
/*      */       {
/*   72 */         localSparseDoubleMatrix2D.set(k, j, i);
/*      */         
/*   74 */         i++;
/*      */       }
/*      */     }
/*   77 */     System.out.println(localSparseDoubleMatrix2D);
/*      */     
/*   79 */     System.out.println("removing...");
/*   80 */     for (int k = 0; k < paramInt2; k++) {
/*   81 */       for (int m = 0; m < paramInt1; m++)
/*      */       {
/*   83 */         localSparseDoubleMatrix2D.set(m, k, 0.0D);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*   88 */     System.out.println(localSparseDoubleMatrix2D);
/*   89 */     System.out.println("bye bye.");
/*      */   }
/*      */   
/*      */   public static void doubleTest10()
/*      */   {
/*   94 */     int i = 6;
/*   95 */     int j = 7;
/*      */     
/*   97 */     DoubleMatrix2D localDoubleMatrix2D1 = Factory2D.ascending(i, j);
/*      */     
/*      */ 
/*  100 */     Transform.mult(localDoubleMatrix2D1, Math.sin(0.3D));
/*  101 */     System.out.println("\n" + localDoubleMatrix2D1);
/*      */     
/*      */ 
/*      */ 
/*  105 */     int[] arrayOfInt1 = { 0, 1, 2, 3 };
/*  106 */     int[] arrayOfInt2 = { 0, 1, 2, 3 };
/*      */     
/*  108 */     int[] arrayOfInt3 = { 3, 0, 3 };
/*  109 */     int[] arrayOfInt4 = { 3, 0, 3 };
/*  110 */     DoubleMatrix2D localDoubleMatrix2D2 = localDoubleMatrix2D1.viewPart(1, 1, 4, 5).viewSelection(arrayOfInt1, arrayOfInt2);
/*  111 */     System.out.println("\nview1=" + localDoubleMatrix2D2);
/*  112 */     DoubleMatrix2D localDoubleMatrix2D3 = localDoubleMatrix2D2.viewStrides(2, 2).viewStrides(2, 1);
/*  113 */     System.out.println("\nview9=" + localDoubleMatrix2D3);
/*  114 */     localDoubleMatrix2D2 = localDoubleMatrix2D2.viewSelection(arrayOfInt3, arrayOfInt4);
/*  115 */     System.out.println("\nview1=" + localDoubleMatrix2D2);
/*  116 */     DoubleMatrix2D localDoubleMatrix2D4 = localDoubleMatrix2D2.viewPart(1, 1, 2, 2);
/*  117 */     System.out.println("\nview2=" + localDoubleMatrix2D4);
/*  118 */     DoubleMatrix2D localDoubleMatrix2D5 = localDoubleMatrix2D4.viewRowFlip();
/*  119 */     System.out.println("\nview3=" + localDoubleMatrix2D5);
/*  120 */     localDoubleMatrix2D5.assign(Factory2D.ascending(localDoubleMatrix2D5.rows(), localDoubleMatrix2D5.columns()));
/*      */     
/*  122 */     System.out.println("\nview3=" + localDoubleMatrix2D5);
/*      */     
/*      */ 
/*  125 */     System.out.println("\nmaster replaced" + localDoubleMatrix2D1);
/*  126 */     System.out.println("\nview1 replaced" + localDoubleMatrix2D2);
/*  127 */     System.out.println("\nview2 replaced" + localDoubleMatrix2D4);
/*  128 */     System.out.println("\nview3 replaced" + localDoubleMatrix2D5);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void doubleTest11()
/*      */   {
/*  134 */     int i = 4;
/*  135 */     int j = 5;
/*  136 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = new DenseDoubleMatrix2D(1, 1);
/*  137 */     localDenseDoubleMatrix2D.assign(2.0D);
/*  138 */     System.out.println("\n" + localDenseDoubleMatrix2D);
/*      */     
/*  140 */     int[] arrayOfInt1 = new int[i];
/*  141 */     int[] arrayOfInt2 = new int[j];
/*      */     
/*  143 */     DoubleMatrix2D localDoubleMatrix2D = localDenseDoubleMatrix2D.viewSelection(arrayOfInt1, arrayOfInt2);
/*  144 */     System.out.println(localDoubleMatrix2D);
/*      */     
/*  146 */     localDenseDoubleMatrix2D.assign(1.0D);
/*  147 */     System.out.println("\n" + localDenseDoubleMatrix2D);
/*  148 */     System.out.println(localDoubleMatrix2D);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void doubleTest12()
/*      */   {
/*  154 */     DoubleMatrix2D localDoubleMatrix2D1 = Factory2D.make(2, 3, 9.0D);
/*  155 */     DoubleMatrix2D localDoubleMatrix2D2 = Factory2D.make(4, 3, 8.0D);
/*  156 */     DoubleMatrix2D localDoubleMatrix2D3 = Factory2D.appendRows(localDoubleMatrix2D1, localDoubleMatrix2D2);
/*  157 */     System.out.println("\nA=" + localDoubleMatrix2D1);
/*  158 */     System.out.println("\nB=" + localDoubleMatrix2D2);
/*  159 */     System.out.println("\nC=" + localDoubleMatrix2D3);
/*  160 */     DoubleMatrix2D localDoubleMatrix2D4 = Factory2D.make(3, 2, 7.0D);
/*  161 */     DoubleMatrix2D localDoubleMatrix2D5 = Factory2D.make(3, 4, 6.0D);
/*  162 */     DoubleMatrix2D localDoubleMatrix2D6 = Factory2D.appendColumns(localDoubleMatrix2D4, localDoubleMatrix2D5);
/*  163 */     System.out.println("\nD=" + localDoubleMatrix2D4);
/*  164 */     System.out.println("\nE=" + localDoubleMatrix2D5);
/*  165 */     System.out.println("\nF=" + localDoubleMatrix2D6);
/*  166 */     DoubleMatrix2D localDoubleMatrix2D7 = Factory2D.appendRows(localDoubleMatrix2D3, localDoubleMatrix2D6);
/*  167 */     System.out.println("\nG=" + localDoubleMatrix2D7);
/*  168 */     DoubleMatrix2D localDoubleMatrix2D8 = Factory2D.ascending(2, 3);
/*  169 */     System.out.println("\nH=" + localDoubleMatrix2D8);
/*  170 */     DoubleMatrix2D localDoubleMatrix2D9 = Factory2D.repeat(localDoubleMatrix2D8, 2, 3);
/*  171 */     System.out.println("\nI=" + localDoubleMatrix2D9);
/*      */   }
/*      */   
/*      */   public static void doubleTest13()
/*      */   {
/*  176 */     double[] arrayOfDouble = { 0.0D, 1.0D, 2.0D, 3.0D };
/*  177 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D = new DenseDoubleMatrix1D(arrayOfDouble);
/*  178 */     System.out.println(localDenseDoubleMatrix1D);
/*      */     
/*      */ 
/*  181 */     System.out.println(localDenseDoubleMatrix1D.viewSelection(new cern.colt.function.DoubleProcedure() {
/*      */       public final boolean apply(double paramAnonymousDouble) {
/*  183 */         return paramAnonymousDouble % 2.0D == 0.0D;
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*  188 */     }));
/*  189 */     System.out.println(localDenseDoubleMatrix1D.aggregate(Functions.plus, Functions.square));
/*      */     
/*      */ 
/*      */ 
/*  193 */     System.out.println(localDenseDoubleMatrix1D.aggregate(Functions.plus, Functions.pow(3.0D)));
/*      */     
/*      */ 
/*      */ 
/*  197 */     System.out.println(localDenseDoubleMatrix1D.aggregate(Functions.plus, Functions.identity));
/*      */     
/*      */ 
/*      */ 
/*  201 */     System.out.println(localDenseDoubleMatrix1D.aggregate(Functions.min, Functions.identity));
/*      */     
/*      */ 
/*      */ 
/*  205 */     System.out.println(localDenseDoubleMatrix1D.aggregate(Functions.max, Functions.chain(Functions.div(2.0D), Functions.sqrt)));
/*      */     
/*      */ 
/*      */ 
/*  209 */     System.out.println(localDenseDoubleMatrix1D.aggregate(Functions.plus, Functions.between(0.0D, 2.0D)));
/*      */     
/*      */ 
/*      */ 
/*  213 */     System.out.println(localDenseDoubleMatrix1D.aggregate(Functions.plus, Functions.chain(Functions.between(0.8D, 1.2D), Functions.log2)));
/*      */     
/*      */ 
/*      */ 
/*  217 */     System.out.println(localDenseDoubleMatrix1D.aggregate(Functions.mult, Functions.identity));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  222 */     cern.colt.function.DoubleFunction local2 = new cern.colt.function.DoubleFunction() {
/*  223 */       public final double apply(double paramAnonymousDouble) { return paramAnonymousDouble > 1.0D ? paramAnonymousDouble : 1.0D; }
/*  224 */     };
/*  225 */     System.out.println(localDenseDoubleMatrix1D.aggregate(Functions.mult, local2));
/*      */     
/*      */ 
/*      */ 
/*  229 */     DoubleMatrix1D localDoubleMatrix1D = localDenseDoubleMatrix1D.copy();
/*  230 */     System.out.println(localDenseDoubleMatrix1D.aggregate(localDoubleMatrix1D, Functions.plus, Functions.chain(Functions.square, Functions.plus)));
/*      */     
/*      */ 
/*      */ 
/*  234 */     localDenseDoubleMatrix1D.assign(Functions.plus(1.0D));
/*  235 */     localDoubleMatrix1D = localDenseDoubleMatrix1D.copy();
/*      */     
/*  237 */     System.out.println(localDenseDoubleMatrix1D);
/*  238 */     System.out.println(localDoubleMatrix1D);
/*      */     
/*  240 */     System.out.println(localDenseDoubleMatrix1D.aggregate(localDoubleMatrix1D, Functions.plus, Functions.chain(Functions.mult(3.141592653589793D), Functions.chain(Functions.log, Functions.swapArgs(Functions.div)))));
/*      */     
/*  242 */     System.out.println(localDenseDoubleMatrix1D.aggregate(localDoubleMatrix1D, Functions.plus, new cern.colt.function.DoubleDoubleFunction() {
/*      */       public double apply(double paramAnonymousDouble1, double paramAnonymousDouble2) {
/*  244 */         return 3.141592653589793D * Math.log(paramAnonymousDouble2 / paramAnonymousDouble1);
/*      */       }
/*      */       
/*      */ 
/*  248 */     }));
/*  249 */     DoubleMatrix3D localDoubleMatrix3D1 = DoubleFactory3D.dense.ascending(2, 2, 2);
/*  250 */     System.out.println(localDoubleMatrix3D1);
/*      */     
/*      */ 
/*  253 */     System.out.println(localDoubleMatrix3D1.aggregate(Functions.plus, Functions.square));
/*      */     
/*      */ 
/*      */ 
/*  257 */     DoubleMatrix3D localDoubleMatrix3D2 = localDoubleMatrix3D1.copy();
/*      */     
/*  259 */     System.out.println(localDoubleMatrix3D1.aggregate(localDoubleMatrix3D2, Functions.plus, Functions.chain(Functions.square, Functions.plus)));
/*      */     
/*      */ 
/*  262 */     System.out.println(localDenseDoubleMatrix1D.assign(Functions.random()));
/*  263 */     System.out.println(localDenseDoubleMatrix1D.assign(new cern.jet.random.Poisson(5.0D, cern.jet.random.AbstractDistribution.makeDefaultGenerator())));
/*      */   }
/*      */   
/*      */ 
/*      */   public static void doubleTest14(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  269 */     double[] arrayOfDouble = { 0.0D, 1.0D, 2.0D, 3.0D };
/*  270 */     DoubleMatrix2D localDoubleMatrix2D1 = DoubleFactory2D.dense.ascending(paramInt1, paramInt2);
/*  271 */     DoubleMatrix2D localDoubleMatrix2D2 = Transform.mult(DoubleFactory2D.dense.ascending(paramInt2, paramInt3), -1.0D);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  277 */     localDoubleMatrix2D1.assign(0.0D);
/*  278 */     localDoubleMatrix2D2.assign(0.0D);
/*      */     
/*  280 */     Timer localTimer = new Timer().start();
/*  281 */     LinearAlgebra.mult(localDoubleMatrix2D1, localDoubleMatrix2D2);
/*  282 */     localTimer.stop().display();
/*      */   }
/*      */   
/*      */   public static void doubleTest15(int paramInt1, int paramInt2)
/*      */   {
/*  287 */     System.out.println("\n\n");
/*  288 */     double[][] arrayOfDouble = { { 0.0D, 5.0D, 9.0D }, { 2.0D, 6.0D, 10.0D }, { 3.0D, 7.0D, 11.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  296 */     DoubleMatrix2D localDoubleMatrix2D1 = Factory2D.make(paramInt1, paramInt1);
/*  297 */     double d = 5.0D;
/*  298 */     int i = paramInt1;
/*  299 */     do { localDoubleMatrix2D1.setQuick(i, i, d);i--;
/*  298 */     } while (i >= 0);
/*      */     
/*      */ 
/*  301 */     localDoubleMatrix2D1.viewRow(0).assign(d);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  307 */     Timer localTimer = new Timer().start();
/*  308 */     DoubleMatrix2D localDoubleMatrix2D2 = null;
/*  309 */     for (int j = 0; j < paramInt2; j++) {
/*  310 */       localDoubleMatrix2D2 = LinearAlgebra.inverse(localDoubleMatrix2D1);
/*      */     }
/*  312 */     localTimer.stop().display();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest17(int paramInt)
/*      */   {
/*  334 */     System.out.println("\n\n");
/*      */     
/*      */ 
/*  337 */     DoubleMatrix2D localDoubleMatrix2D1 = Factory2D.ascending(3, 4);
/*  338 */     DoubleMatrix2D localDoubleMatrix2D2 = Factory2D.ascending(2, 3);
/*  339 */     DoubleMatrix2D localDoubleMatrix2D3 = Factory2D.ascending(1, 2);
/*  340 */     localDoubleMatrix2D2.assign(Functions.plus(localDoubleMatrix2D1.zSum()));
/*  341 */     localDoubleMatrix2D3.assign(Functions.plus(localDoubleMatrix2D2.zSum()));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest18(int paramInt)
/*      */   {
/*  359 */     System.out.println("\n\n");
/*  360 */     int i = 2;
/*      */     
/*      */ 
/*      */ 
/*  364 */     DoubleMatrix2D localDoubleMatrix2D9 = Factory2D.make(0, 0);
/*      */     
/*  366 */     DoubleMatrix2D localDoubleMatrix2D1 = Factory2D.ascending(i, i);
/*      */     
/*  368 */     DoubleMatrix2D localDoubleMatrix2D2 = Factory2D.ascending(i, i).assign(Functions.plus(localDoubleMatrix2D1.getQuick(i - 1, i - 1)));
/*  369 */     DoubleMatrix2D localDoubleMatrix2D3 = Factory2D.ascending(i, i).assign(Functions.plus(localDoubleMatrix2D2.getQuick(i - 1, i - 1)));
/*  370 */     DoubleMatrix2D localDoubleMatrix2D4 = Factory2D.ascending(i, i).assign(Functions.plus(localDoubleMatrix2D3.getQuick(i - 1, i - 1)));
/*  371 */     Object localObject = null;
/*      */     
/*  373 */     DoubleMatrix2D localDoubleMatrix2D5 = Factory2D.ascending(i, i).assign(Functions.plus(localDoubleMatrix2D4.getQuick(i - 1, i - 1)));
/*      */     
/*  375 */     DoubleMatrix2D localDoubleMatrix2D6 = Factory2D.ascending(i, i).assign(Functions.plus(localDoubleMatrix2D5.getQuick(i - 1, i - 1)));
/*  376 */     DoubleMatrix2D localDoubleMatrix2D7 = localDoubleMatrix2D9;
/*      */     
/*  378 */     DoubleMatrix2D localDoubleMatrix2D8 = Factory2D.ascending(i, i).assign(Functions.plus(localDoubleMatrix2D6.getQuick(i - 1, i - 1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  388 */     System.out.println("\n" + localDoubleMatrix2D1);
/*  389 */     System.out.println("\n" + localDoubleMatrix2D2);
/*  390 */     System.out.println("\n" + localDoubleMatrix2D3);
/*  391 */     System.out.println("\n" + localDoubleMatrix2D4);
/*  392 */     System.out.println("\n" + localObject);
/*  393 */     System.out.println("\n" + localDoubleMatrix2D5);
/*  394 */     System.out.println("\n" + localDoubleMatrix2D6);
/*  395 */     System.out.println("\n" + localDoubleMatrix2D7);
/*  396 */     System.out.println("\n" + localDoubleMatrix2D8);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest19()
/*      */   {
/*  417 */     System.out.println("\n\n");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  423 */     double[][] arrayOfDouble1 = { { 0.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 0.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  430 */     DoubleMatrix2D localDoubleMatrix2D = Factory2D.make(arrayOfDouble1);
/*  431 */     int i = Property.DEFAULT.semiBandwidth(localDoubleMatrix2D);
/*  432 */     int j = Property.DEFAULT.upperBandwidth(localDoubleMatrix2D);
/*  433 */     int k = Property.DEFAULT.lowerBandwidth(localDoubleMatrix2D);
/*      */     
/*  435 */     System.out.println("\n\nupperBandwidth=" + j);
/*  436 */     System.out.println("lowerBandwidth=" + k);
/*  437 */     System.out.println("bandwidth=" + i + " " + localDoubleMatrix2D);
/*      */     
/*      */ 
/*  440 */     double[][] arrayOfDouble2 = { { 1.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 1.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  447 */     localDoubleMatrix2D = Factory2D.make(arrayOfDouble2);
/*  448 */     i = Property.DEFAULT.semiBandwidth(localDoubleMatrix2D);
/*  449 */     j = Property.DEFAULT.upperBandwidth(localDoubleMatrix2D);
/*  450 */     k = Property.DEFAULT.lowerBandwidth(localDoubleMatrix2D);
/*  451 */     System.out.println("\n\nupperBandwidth=" + j);
/*  452 */     System.out.println("lowerBandwidth=" + k);
/*  453 */     System.out.println("bandwidth=" + i + " " + localDoubleMatrix2D);
/*      */     
/*      */ 
/*  456 */     double[][] arrayOfDouble3 = { { 1.0D, 1.0D, 0.0D, 0.0D }, { 1.0D, 1.0D, 1.0D, 0.0D }, { 0.0D, 1.0D, 1.0D, 1.0D }, { 0.0D, 0.0D, 1.0D, 1.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  463 */     localDoubleMatrix2D = Factory2D.make(arrayOfDouble3);
/*  464 */     i = Property.DEFAULT.semiBandwidth(localDoubleMatrix2D);
/*  465 */     j = Property.DEFAULT.upperBandwidth(localDoubleMatrix2D);
/*  466 */     k = Property.DEFAULT.lowerBandwidth(localDoubleMatrix2D);
/*  467 */     System.out.println("\n\nupperBandwidth=" + j);
/*  468 */     System.out.println("lowerBandwidth=" + k);
/*  469 */     System.out.println("bandwidth=" + i + " " + localDoubleMatrix2D);
/*      */     
/*      */ 
/*      */ 
/*  473 */     double[][] arrayOfDouble4 = { { 0.0D, 1.0D, 1.0D, 1.0D }, { 0.0D, 1.0D, 1.0D, 1.0D }, { 0.0D, 0.0D, 0.0D, 1.0D }, { 0.0D, 0.0D, 0.0D, 1.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  480 */     localDoubleMatrix2D = Factory2D.make(arrayOfDouble4);
/*  481 */     i = Property.DEFAULT.semiBandwidth(localDoubleMatrix2D);
/*  482 */     j = Property.DEFAULT.upperBandwidth(localDoubleMatrix2D);
/*  483 */     k = Property.DEFAULT.lowerBandwidth(localDoubleMatrix2D);
/*  484 */     System.out.println("\n\nupperBandwidth=" + j);
/*  485 */     System.out.println("lowerBandwidth=" + k);
/*  486 */     System.out.println("bandwidth=" + i + " " + localDoubleMatrix2D);
/*      */     
/*      */ 
/*  489 */     double[][] arrayOfDouble5 = { { 0.0D, 0.0D, 0.0D, 0.0D }, { 1.0D, 1.0D, 0.0D, 0.0D }, { 1.0D, 1.0D, 0.0D, 0.0D }, { 1.0D, 1.0D, 1.0D, 1.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  496 */     localDoubleMatrix2D = Factory2D.make(arrayOfDouble5);
/*  497 */     i = Property.DEFAULT.semiBandwidth(localDoubleMatrix2D);
/*  498 */     j = Property.DEFAULT.upperBandwidth(localDoubleMatrix2D);
/*  499 */     k = Property.DEFAULT.lowerBandwidth(localDoubleMatrix2D);
/*  500 */     System.out.println("\n\nupperBandwidth=" + j);
/*  501 */     System.out.println("lowerBandwidth=" + k);
/*  502 */     System.out.println("bandwidth=" + i + " " + localDoubleMatrix2D);
/*      */     
/*      */ 
/*      */ 
/*  506 */     double[][] arrayOfDouble6 = { { 1.0D, 1.0D, 0.0D, 0.0D }, { 0.0D, 1.0D, 1.0D, 0.0D }, { 0.0D, 1.0D, 0.0D, 1.0D }, { 1.0D, 0.0D, 1.0D, 1.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  513 */     localDoubleMatrix2D = Factory2D.make(arrayOfDouble6);
/*  514 */     i = Property.DEFAULT.semiBandwidth(localDoubleMatrix2D);
/*  515 */     j = Property.DEFAULT.upperBandwidth(localDoubleMatrix2D);
/*  516 */     k = Property.DEFAULT.lowerBandwidth(localDoubleMatrix2D);
/*  517 */     System.out.println("\n\nupperBandwidth=" + j);
/*  518 */     System.out.println("lowerBandwidth=" + k);
/*  519 */     System.out.println("bandwidth=" + i + " " + localDoubleMatrix2D);
/*      */     
/*      */ 
/*  522 */     double[][] arrayOfDouble7 = { { 1.0D, 1.0D, 1.0D, 0.0D }, { 0.0D, 1.0D, 0.0D, 0.0D }, { 1.0D, 1.0D, 0.0D, 1.0D }, { 0.0D, 0.0D, 1.0D, 1.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  529 */     localDoubleMatrix2D = Factory2D.make(arrayOfDouble7);
/*  530 */     i = Property.DEFAULT.semiBandwidth(localDoubleMatrix2D);
/*  531 */     j = Property.DEFAULT.upperBandwidth(localDoubleMatrix2D);
/*  532 */     k = Property.DEFAULT.lowerBandwidth(localDoubleMatrix2D);
/*  533 */     System.out.println("\n\nupperBandwidth=" + j);
/*  534 */     System.out.println("lowerBandwidth=" + k);
/*  535 */     System.out.println("bandwidth=" + i + " " + localDoubleMatrix2D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest19(int paramInt)
/*      */   {
/*  546 */     System.out.println("\n\n");
/*  547 */     int i = 2;
/*      */     
/*      */ 
/*      */ 
/*  551 */     DoubleMatrix2D localDoubleMatrix2D9 = Factory2D.make(0, 0);
/*      */     
/*  553 */     DoubleMatrix2D localDoubleMatrix2D1 = Factory2D.ascending(i, i);
/*      */     
/*  555 */     DoubleMatrix2D localDoubleMatrix2D2 = Factory2D.ascending(i, i).assign(Functions.plus(localDoubleMatrix2D1.getQuick(i - 1, i - 1)));
/*  556 */     DoubleMatrix2D localDoubleMatrix2D3 = Factory2D.ascending(i, i).assign(Functions.plus(localDoubleMatrix2D2.getQuick(i - 1, i - 1)));
/*  557 */     DoubleMatrix2D localDoubleMatrix2D4 = Factory2D.ascending(i, i).assign(Functions.plus(localDoubleMatrix2D3.getQuick(i - 1, i - 1)));
/*  558 */     Object localObject = null;
/*      */     
/*  560 */     DoubleMatrix2D localDoubleMatrix2D5 = Factory2D.ascending(i, i).assign(Functions.plus(localDoubleMatrix2D4.getQuick(i - 1, i - 1)));
/*      */     
/*  562 */     DoubleMatrix2D localDoubleMatrix2D6 = Factory2D.ascending(i, i).assign(Functions.plus(localDoubleMatrix2D5.getQuick(i - 1, i - 1)));
/*  563 */     DoubleMatrix2D localDoubleMatrix2D7 = localDoubleMatrix2D9;
/*      */     
/*  565 */     DoubleMatrix2D localDoubleMatrix2D8 = Factory2D.ascending(i, i).assign(Functions.plus(localDoubleMatrix2D6.getQuick(i - 1, i - 1)));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  575 */     System.out.println("\n" + localDoubleMatrix2D1);
/*  576 */     System.out.println("\n" + localDoubleMatrix2D2);
/*  577 */     System.out.println("\n" + localDoubleMatrix2D3);
/*  578 */     System.out.println("\n" + localDoubleMatrix2D4);
/*  579 */     System.out.println("\n" + localObject);
/*  580 */     System.out.println("\n" + localDoubleMatrix2D5);
/*  581 */     System.out.println("\n" + localDoubleMatrix2D6);
/*  582 */     System.out.println("\n" + localDoubleMatrix2D7);
/*  583 */     System.out.println("\n" + localDoubleMatrix2D8);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest2()
/*      */   {
/*  605 */     int[] arrayOfInt = { 0, 3, 100000, 9 };
/*  606 */     double[] arrayOfDouble = { 100.0D, 1000.0D, 70.0D, 71.0D };
/*      */     
/*  608 */     int i = arrayOfInt.length;
/*  609 */     OpenIntDoubleHashMap localOpenIntDoubleHashMap = new OpenIntDoubleHashMap(i * 2, 0.2D, 0.5D);
/*      */     
/*  611 */     for (int j = 0; j < arrayOfInt.length; j++) {
/*  612 */       localOpenIntDoubleHashMap.put(arrayOfInt[j], (int)arrayOfDouble[j]);
/*      */     }
/*      */     
/*  615 */     System.out.println(localOpenIntDoubleHashMap.containsKey(3));
/*  616 */     System.out.println(localOpenIntDoubleHashMap.get(3));
/*      */     
/*  618 */     System.out.println(localOpenIntDoubleHashMap.containsKey(4));
/*  619 */     System.out.println(localOpenIntDoubleHashMap.get(4));
/*      */     
/*  621 */     System.out.println(localOpenIntDoubleHashMap.containsValue(71.0D));
/*  622 */     System.out.println(localOpenIntDoubleHashMap.keyOf(71.0D));
/*      */     
/*  624 */     System.out.println(localOpenIntDoubleHashMap);
/*      */   }
/*      */   
/*      */   public static void doubleTest20()
/*      */   {
/*  629 */     System.out.println("\n\n");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  635 */     double[][] arrayOfDouble1 = { { 0.0D, 1.0D, 0.0D, 0.0D }, { 3.0D, 0.0D, 2.0D, 0.0D }, { 0.0D, 2.0D, 0.0D, 3.0D }, { 0.0D, 0.0D, 1.0D, 0.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  642 */     DoubleMatrix2D localDoubleMatrix2D = Factory2D.make(arrayOfDouble1);
/*      */     
/*  644 */     System.out.println("\n\n" + LinearAlgebra.toVerboseString(localDoubleMatrix2D));
/*      */     
/*      */ 
/*      */ 
/*  648 */     double[][] arrayOfDouble2 = { { 1.0000000000000167D, -0.3623577544766736D, -0.3623577544766736D }, { 0.0D, 0.9320390859672374D, -0.3377315902755755D }, { 0.0D, 0.0D, 0.8686968577706282D }, { 0.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 0.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  657 */     localDoubleMatrix2D = Factory2D.make(arrayOfDouble2);
/*      */     
/*  659 */     System.out.println("\n\n" + LinearAlgebra.toVerboseString(localDoubleMatrix2D));
/*      */     
/*      */ 
/*  662 */     double[][] arrayOfDouble3 = { { 611.0D, 196.0D, -192.0D, 407.0D, -8.0D, -52.0D, -49.0D, 29.0D }, { 196.0D, 899.0D, 113.0D, -192.0D, -71.0D, -43.0D, -8.0D, -44.0D }, { -192.0D, 113.0D, 899.0D, 196.0D, 61.0D, 49.0D, 8.0D, 52.0D }, { 407.0D, -192.0D, 196.0D, 611.0D, 8.0D, 44.0D, 59.0D, -23.0D }, { -8.0D, -71.0D, 61.0D, 8.0D, 411.0D, -599.0D, 208.0D, 208.0D }, { -52.0D, -43.0D, 49.0D, 44.0D, -599.0D, 411.0D, 208.0D, 208.0D }, { -49.0D, -8.0D, 8.0D, 59.0D, 208.0D, 208.0D, 99.0D, -911.0D }, { 29.0D, -44.0D, 52.0D, -23.0D, 208.0D, 208.0D, -911.0D, 99.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  675 */     localDoubleMatrix2D = Factory2D.make(arrayOfDouble3);
/*      */     
/*  677 */     System.out.println("\n\n" + LinearAlgebra.toVerboseString(localDoubleMatrix2D));
/*      */     
/*      */ 
/*      */ 
/*  681 */     double d1 = Math.sqrt(10405.0D);double d2 = Math.sqrt(26.0D);
/*  682 */     double[] arrayOfDouble = { -10.0D * d1, 0.0D, 510.0D - 100.0D * d2, 1000.0D, 1000.0D, 510.0D + 100.0D * d2, 1020.0D, 10.0D * d1 };
/*  683 */     System.out.println(DoubleFactory1D.dense.make(arrayOfDouble));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static void doubleTest21()
/*      */   {
/*  690 */     System.out.println("\n\n");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  696 */     double[][] arrayOfDouble = { { 0.0D, 0.0D, 3.141592653589793D, 0.0D }, { 3.0D, 9.0D, 0.0D, 0.0D }, { 0.0D, 2.0D, 7.0D, 0.0D }, { 0.0D, 0.0D, 3.0D, 9.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  703 */     DoubleMatrix2D localDoubleMatrix2D = Factory2D.make(arrayOfDouble);
/*  704 */     System.out.println(localDoubleMatrix2D);
/*  705 */     System.out.println(new cern.colt.matrix.doublealgo.Formatter(null).toString(localDoubleMatrix2D));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest22()
/*      */   {
/*  714 */     System.out.println("\n\n");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  720 */     double[][] arrayOfDouble = { { 0.0D, 0.0D, 3.141592653589793D, 0.0D }, { 3.0D, 9.0D, 0.0D, 0.0D }, { 0.0D, 2.0D, 7.0D, 0.0D }, { 0.0D, 0.0D, 3.0D, 9.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  727 */     DoubleMatrix2D localDoubleMatrix2D = Factory2D.make(arrayOfDouble);
/*  728 */     System.out.println(localDoubleMatrix2D);
/*  729 */     System.out.println(Property.isDiagonallyDominantByRow(localDoubleMatrix2D));
/*  730 */     System.out.println(Property.isDiagonallyDominantByColumn(localDoubleMatrix2D));
/*  731 */     Property.generateNonSingular(localDoubleMatrix2D);
/*  732 */     System.out.println(localDoubleMatrix2D);
/*  733 */     System.out.println(Property.isDiagonallyDominantByRow(localDoubleMatrix2D));
/*  734 */     System.out.println(Property.isDiagonallyDominantByColumn(localDoubleMatrix2D));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest23(int paramInt1, int paramInt2, double paramDouble, boolean paramBoolean)
/*      */   {
/*  743 */     System.out.println("\n\n");
/*  744 */     System.out.println("initializing...");
/*      */     
/*      */ 
/*      */ 
/*  748 */     double d1 = 5.0D;
/*  749 */     double d2 = 3.0D;
/*  750 */     cern.jet.random.Normal localNormal = new cern.jet.random.Normal(d1, d2, new MersenneTwister());
/*      */     
/*  752 */     System.out.println("sampling...");
/*  753 */     double d3 = 2.0D;
/*  754 */     DoubleMatrix2D localDoubleMatrix2D1; if (paramBoolean) {
/*  755 */       localDoubleMatrix2D1 = DoubleFactory2D.dense.sample(paramInt2, paramInt2, d3, paramDouble);
/*      */     } else
/*  757 */       localDoubleMatrix2D1 = DoubleFactory2D.sparse.sample(paramInt2, paramInt2, d3, paramDouble);
/*  758 */     DoubleMatrix1D localDoubleMatrix1D1 = localDoubleMatrix2D1.like1D(paramInt2).assign(1.0D);
/*      */     
/*      */ 
/*      */ 
/*  762 */     System.out.println("generating invertible matrix...");
/*  763 */     Property.generateNonSingular(localDoubleMatrix2D1);
/*      */     
/*      */ 
/*      */ 
/*  767 */     DoubleMatrix2D localDoubleMatrix2D2 = localDoubleMatrix2D1.like();
/*  768 */     DoubleMatrix1D localDoubleMatrix1D2 = localDoubleMatrix1D1.like();
/*      */     
/*      */ 
/*  771 */     LUDecompositionQuick localLUDecompositionQuick = new LUDecompositionQuick();
/*      */     
/*  773 */     System.out.println("benchmarking assignment...");
/*  774 */     Timer localTimer = new Timer().start();
/*  775 */     localDoubleMatrix2D2.assign(localDoubleMatrix2D1);
/*  776 */     localDoubleMatrix1D2.assign(localDoubleMatrix1D1);
/*  777 */     localTimer.stop().display();
/*      */     
/*  779 */     localDoubleMatrix2D2.assign(localDoubleMatrix2D1);
/*  780 */     localLUDecompositionQuick.decompose(localDoubleMatrix2D2);
/*      */     
/*  782 */     System.out.println("benchmarking LU...");
/*  783 */     localTimer.reset().start();
/*  784 */     int i = paramInt1;
/*  785 */     do { localDoubleMatrix1D2.assign(localDoubleMatrix1D1);
/*      */       
/*      */ 
/*  788 */       localLUDecompositionQuick.solve(localDoubleMatrix1D2);i--;
/*  784 */     } while (i >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  791 */     localTimer.stop().display();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  797 */     System.out.println("done.");
/*      */   }
/*      */   
/*      */ 
/*      */   public static void doubleTest24(int paramInt1, int paramInt2, boolean paramBoolean)
/*      */   {
/*  803 */     System.out.println("\n\n");
/*  804 */     System.out.println("initializing...");
/*      */     
/*      */     DoubleFactory2D localDoubleFactory2D;
/*  807 */     if (paramBoolean) {
/*  808 */       localDoubleFactory2D = DoubleFactory2D.dense;
/*      */     } else {
/*  810 */       localDoubleFactory2D = DoubleFactory2D.sparse;
/*      */     }
/*  812 */     double d1 = 2.0D;
/*  813 */     double d2 = 1.25D;
/*  814 */     double d3 = d2 * 0.25D;
/*  815 */     double d4 = 1.0D - d2;
/*  816 */     DoubleMatrix2D localDoubleMatrix2D = localDoubleFactory2D.make(paramInt2, paramInt2, d1);
/*      */     
/*  818 */     cern.colt.function.Double9Function local4 = new cern.colt.function.Double9Function() { private final double val$alpha;
/*      */       
/*  820 */       public final double apply(double paramAnonymousDouble1, double paramAnonymousDouble2, double paramAnonymousDouble3, double paramAnonymousDouble4, double paramAnonymousDouble5, double paramAnonymousDouble6, double paramAnonymousDouble7, double paramAnonymousDouble8, double paramAnonymousDouble9) { return this.val$alpha * paramAnonymousDouble5 + this.val$beta * (paramAnonymousDouble2 + paramAnonymousDouble4 + paramAnonymousDouble6 + paramAnonymousDouble8);
/*      */       }
/*  822 */     };
/*  823 */     Timer localTimer = new Timer().start();
/*      */     
/*  825 */     System.out.println("benchmarking stencil...");
/*  826 */     for (int i = 0; i < paramInt1; i++) {
/*  827 */       localDoubleMatrix2D.zAssign8Neighbors(localDoubleMatrix2D, local4);
/*      */     }
/*      */     
/*  830 */     localTimer.stop().display();
/*      */     
/*  832 */     localDoubleMatrix2D = null;
/*      */     
/*  834 */     double[][] arrayOfDouble = localDoubleFactory2D.make(paramInt2, paramInt2, d1).toArray();
/*  835 */     localTimer.reset().start();
/*      */     
/*  837 */     System.out.println("benchmarking stencil scimark...");
/*  838 */     for (int j = 0; j < paramInt1; j++) {}
/*      */     
/*      */ 
/*  841 */     localTimer.stop().display();
/*      */     
/*      */ 
/*  844 */     System.out.println("done.");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest25(int paramInt)
/*      */   {
/*  852 */     System.out.println("\n\n");
/*  853 */     System.out.println("initializing...");
/*  854 */     int i = 1;
/*      */     
/*      */     DoubleFactory2D localDoubleFactory2D;
/*  857 */     if (i != 0) {
/*  858 */       localDoubleFactory2D = DoubleFactory2D.dense;
/*      */     } else {
/*  860 */       localDoubleFactory2D = DoubleFactory2D.sparse;
/*      */     }
/*  862 */     double d = 0.5D;
/*  863 */     DoubleMatrix2D localDoubleMatrix2D = localDoubleFactory2D.make(paramInt, paramInt, d);
/*  864 */     Property.generateNonSingular(localDoubleMatrix2D);
/*  865 */     Timer localTimer = new Timer().start();
/*      */     
/*  867 */     System.out.println(localDoubleMatrix2D);
/*  868 */     System.out.println(Algebra.ZERO.inverse(localDoubleMatrix2D));
/*      */     
/*  870 */     localTimer.stop().display();
/*      */     
/*  872 */     System.out.println("done.");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest26(int paramInt)
/*      */   {
/*  880 */     System.out.println("\n\n");
/*  881 */     System.out.println("initializing...");
/*  882 */     int i = 1;
/*      */     
/*      */     DoubleFactory2D localDoubleFactory2D;
/*  885 */     if (i != 0) {
/*  886 */       localDoubleFactory2D = DoubleFactory2D.dense;
/*      */     } else {
/*  888 */       localDoubleFactory2D = DoubleFactory2D.sparse;
/*      */     }
/*  890 */     double d = 0.5D;
/*  891 */     DoubleMatrix2D localDoubleMatrix2D = localDoubleFactory2D.make(paramInt, paramInt, d);
/*  892 */     Property.generateNonSingular(localDoubleMatrix2D);
/*  893 */     Timer localTimer = new Timer().start();
/*      */     
/*  895 */     cern.colt.matrix.doublealgo.DoubleMatrix2DComparator local5 = new cern.colt.matrix.doublealgo.DoubleMatrix2DComparator() {
/*      */       public int compare(DoubleMatrix2D paramAnonymousDoubleMatrix2D1, DoubleMatrix2D paramAnonymousDoubleMatrix2D2) {
/*  897 */         return paramAnonymousDoubleMatrix2D1.zSum() == paramAnonymousDoubleMatrix2D2.zSum() ? 1 : 0;
/*      */       }
/*      */       
/*  900 */     };
/*  901 */     System.out.println(localDoubleMatrix2D);
/*  902 */     System.out.println(Algebra.ZERO.inverse(localDoubleMatrix2D));
/*      */     
/*  904 */     localTimer.stop().display();
/*      */     
/*  906 */     System.out.println("done.");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest27()
/*      */   {
/*  914 */     System.out.println("\n\n");
/*  915 */     System.out.println("initializing...");
/*      */     
/*  917 */     int i = 51;
/*  918 */     int j = 10;
/*  919 */     double[][] arrayOfDouble = new double[j][i];
/*  920 */     int k = j; do { arrayOfDouble[k][k] = 2.0D;k--; } while (k >= 0);
/*      */     
/*  922 */     int m = 0;
/*  923 */     int n = 0;
/*      */     
/*  925 */     DoubleMatrix2D localDoubleMatrix2D1 = null;
/*  926 */     DoubleMatrix2D localDoubleMatrix2D2 = null;
/*  927 */     DoubleMatrix2D localDoubleMatrix2D3 = null;
/*  928 */     DoubleMatrix2D localDoubleMatrix2D4 = null;
/*  929 */     DoubleMatrix2D localDoubleMatrix2D5 = null;
/*  930 */     DoubleMatrix2D localDoubleMatrix2D6 = null;
/*      */     
/*      */ 
/*  933 */     localDoubleMatrix2D1 = DoubleFactory2D.dense.make(i, j);
/*      */     
/*      */ 
/*  936 */     for (m = 0; m < j; m++) {
/*  937 */       for (n = 0; n < i; n++) {
/*  938 */         localDoubleMatrix2D1.setQuick(n, m, arrayOfDouble[m][n]);
/*      */       }
/*      */     }
/*      */     
/*  942 */     localDoubleMatrix2D2 = Algebra.DEFAULT.transpose(localDoubleMatrix2D1);
/*  943 */     localDoubleMatrix2D3 = Algebra.DEFAULT.mult(localDoubleMatrix2D2, localDoubleMatrix2D1);
/*  944 */     localDoubleMatrix2D4 = Algebra.DEFAULT.inverse(localDoubleMatrix2D3);
/*  945 */     localDoubleMatrix2D5 = Algebra.DEFAULT.mult(localDoubleMatrix2D4, localDoubleMatrix2D2);
/*  946 */     localDoubleMatrix2D6 = Algebra.DEFAULT.mult(localDoubleMatrix2D1, localDoubleMatrix2D5);
/*  947 */     System.out.println("done.");
/*      */   }
/*      */   
/*      */ 
/*      */   public static void doubleTest28()
/*      */   {
/*  953 */     double[] arrayOfDouble = { 1.0D, 2.0D, 3.0D, 4.0D, 5.0D, 6.0D };
/*  954 */     double[][] arrayOfDouble1 = { { 1.0D, 2.0D, 3.0D, 4.0D, 5.0D, 6.0D }, { 2.0D, 3.0D, 4.0D, 5.0D, 6.0D, 7.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  959 */     DoubleFactory2D localDoubleFactory2D = DoubleFactory2D.dense;
/*  960 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D = new DenseDoubleMatrix1D(arrayOfDouble);
/*  961 */     DoubleMatrix2D localDoubleMatrix2D = localDoubleFactory2D.make(arrayOfDouble1);
/*  962 */     DoubleMatrix1D localDoubleMatrix1D = localDenseDoubleMatrix1D.like(localDoubleMatrix2D.rows());
/*      */     
/*  964 */     localDoubleMatrix2D.zMult(localDenseDoubleMatrix1D, localDoubleMatrix1D);
/*      */     
/*  966 */     System.out.println(localDoubleMatrix1D);
/*      */   }
/*      */   
/*      */   public static void doubleTest28(DoubleFactory2D paramDoubleFactory2D)
/*      */   {
/*  971 */     double[] arrayOfDouble = { 1.0D, 2.0D, 3.0D, 4.0D, 5.0D, 6.0D };
/*  972 */     double[][] arrayOfDouble1 = { { 1.0D, 2.0D, 3.0D, 4.0D, 5.0D, 6.0D }, { 2.0D, 3.0D, 4.0D, 5.0D, 6.0D, 7.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  978 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D = new DenseDoubleMatrix1D(arrayOfDouble);
/*  979 */     DoubleMatrix2D localDoubleMatrix2D = paramDoubleFactory2D.make(arrayOfDouble1);
/*  980 */     DoubleMatrix1D localDoubleMatrix1D = localDenseDoubleMatrix1D.like(localDoubleMatrix2D.rows());
/*      */     
/*  982 */     localDoubleMatrix2D.zMult(localDenseDoubleMatrix1D, localDoubleMatrix1D);
/*      */     
/*  984 */     System.out.println(localDoubleMatrix1D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest29(int paramInt) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest29(int paramInt, DoubleFactory2D paramDoubleFactory2D)
/*      */   {
/* 1062 */     DoubleMatrix2D localDoubleMatrix2D1 = new DenseDoubleMatrix2D(paramInt, paramInt).assign(0.5D);
/* 1063 */     DoubleMatrix2D localDoubleMatrix2D2 = paramDoubleFactory2D.sample(paramInt, paramInt, 0.5D, 0.001D);
/*      */     
/* 1065 */     Timer localTimer = new Timer().start();
/* 1066 */     DoubleMatrix2D localDoubleMatrix2D3 = localDoubleMatrix2D2.zMult(localDoubleMatrix2D1, null);
/* 1067 */     localTimer.stop().display();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static void doubleTest29(DoubleFactory2D paramDoubleFactory2D)
/*      */   {
/* 1074 */     double[][] arrayOfDouble1 = { { 6.0D, 5.0D, 4.0D }, { 7.0D, 6.0D, 3.0D }, { 6.0D, 5.0D, 4.0D }, { 7.0D, 6.0D, 3.0D }, { 6.0D, 5.0D, 4.0D }, { 7.0D, 6.0D, 3.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1084 */     double[][] arrayOfDouble2 = { { 1.0D, 2.0D, 3.0D, 4.0D, 5.0D, 6.0D }, { 2.0D, 3.0D, 4.0D, 5.0D, 6.0D, 7.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1090 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = new DenseDoubleMatrix2D(arrayOfDouble1);
/* 1091 */     DoubleMatrix2D localDoubleMatrix2D1 = paramDoubleFactory2D.make(arrayOfDouble2);
/*      */     
/* 1093 */     DoubleMatrix2D localDoubleMatrix2D2 = localDoubleMatrix2D1.zMult(localDenseDoubleMatrix2D, null);
/*      */     
/* 1095 */     System.out.println(localDoubleMatrix2D2);
/*      */   }
/*      */   
/*      */   public static void doubleTest3()
/*      */   {
/* 1100 */     int i = 4;
/* 1101 */     int j = 5;
/* 1102 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = new DenseDoubleMatrix2D(i, j);
/* 1103 */     System.out.println(localDenseDoubleMatrix2D);
/* 1104 */     localDenseDoubleMatrix2D.assign(1.0D);
/* 1105 */     System.out.println("\n" + localDenseDoubleMatrix2D);
/* 1106 */     localDenseDoubleMatrix2D.viewPart(2, 0, 2, 3).assign(2.0D);
/* 1107 */     System.out.println("\n" + localDenseDoubleMatrix2D);
/*      */     
/* 1109 */     DoubleMatrix2D localDoubleMatrix2D1 = localDenseDoubleMatrix2D.viewColumnFlip();
/* 1110 */     System.out.println("flip around columns=" + localDoubleMatrix2D1);
/* 1111 */     DoubleMatrix2D localDoubleMatrix2D2 = localDoubleMatrix2D1.viewRowFlip();
/* 1112 */     System.out.println("further flip around rows=" + localDoubleMatrix2D2);
/*      */     
/* 1114 */     localDoubleMatrix2D2.viewPart(0, 0, 2, 2).assign(3.0D);
/* 1115 */     System.out.println("master replaced" + localDenseDoubleMatrix2D);
/* 1116 */     System.out.println("flip1 replaced" + localDoubleMatrix2D1);
/* 1117 */     System.out.println("flip2 replaced" + localDoubleMatrix2D2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest30()
/*      */   {
/* 1136 */     double[][] arrayOfDouble = { { 6.0D, 5.0D }, { 7.0D, 6.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1142 */     double[] arrayOfDouble1 = { 1.0D, 2.0D };
/* 1143 */     double[] arrayOfDouble2 = { 3.0D, 4.0D };
/*      */     
/* 1145 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = new DenseDoubleMatrix2D(arrayOfDouble);
/* 1146 */     cern.colt.matrix.linalg.SeqBlas.seqBlas.dger(1.0D, new DenseDoubleMatrix1D(arrayOfDouble1), new DenseDoubleMatrix1D(arrayOfDouble2), localDenseDoubleMatrix2D);
/*      */     
/* 1148 */     System.out.println(localDenseDoubleMatrix2D);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void doubleTest30(int paramInt)
/*      */   {
/* 1154 */     int[] arrayOfInt = { 0, 2, 3, 5, 7 };
/* 1155 */     IntArrayList localIntArrayList = new IntArrayList(arrayOfInt);
/* 1156 */     int i = 3;
/* 1157 */     int j = 0;
/* 1158 */     Timer localTimer = new Timer().start();
/* 1159 */     int k = paramInt;
/* 1160 */     do { int m = localIntArrayList.binarySearchFromTo(i, 0, arrayOfInt.length - 1);
/* 1161 */       System.out.println(localIntArrayList + ", " + i + " --> " + m);
/* 1162 */       j += m;k--;
/* 1159 */     } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1164 */     localTimer.stop().display();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final double val$beta;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest30(int paramInt1, int paramInt2)
/*      */   {
/* 1230 */     int[] arrayOfInt = { 2 };
/* 1231 */     IntArrayList localIntArrayList = new IntArrayList(arrayOfInt);
/* 1232 */     int i = arrayOfInt.length - 1;
/* 1233 */     int j = 0;
/* 1234 */     Timer localTimer = new Timer().start();
/* 1235 */     int k = paramInt1;
/* 1236 */     do { int m = cern.colt.Sorting.binarySearchFromTo(arrayOfInt, paramInt2, 0, i);
/*      */       
/*      */ 
/* 1239 */       j += m;k--;
/* 1235 */     } while (k >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1241 */     localTimer.stop().display();
/* 1242 */     System.out.println("sum = " + j);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest31(int paramInt)
/*      */   {
/* 1306 */     System.out.println("\ninit");
/* 1307 */     DoubleMatrix1D localDoubleMatrix1D1 = DoubleFactory1D.dense.descending(paramInt);
/* 1308 */     Object localObject = new WrapperDoubleMatrix1D(localDoubleMatrix1D1);
/* 1309 */     DoubleMatrix1D localDoubleMatrix1D2 = ((DoubleMatrix1D)localObject).viewPart(2, 3);
/* 1310 */     DoubleMatrix1D localDoubleMatrix1D3 = localDoubleMatrix1D2.viewFlip();
/*      */     
/*      */ 
/* 1313 */     localDoubleMatrix1D3.set(0, 99.0D);
/* 1314 */     localObject = ((DoubleMatrix1D)localObject).viewSorted();
/* 1315 */     System.out.println("a = " + localDoubleMatrix1D1);
/* 1316 */     System.out.println("b = " + localObject);
/* 1317 */     System.out.println("c = " + localDoubleMatrix1D2);
/* 1318 */     System.out.println("d = " + localDoubleMatrix1D3);
/*      */     
/* 1320 */     System.out.println("done");
/*      */   }
/*      */   
/*      */ 
/*      */   public static void doubleTest32()
/*      */   {
/* 1326 */     double[][] arrayOfDouble = { { 1.0D, 4.0D, 0.0D }, { 6.0D, 2.0D, 5.0D }, { 0.0D, 7.0D, 3.0D }, { 0.0D, 0.0D, 8.0D }, { 0.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 0.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1336 */     TridiagonalDoubleMatrix2D localTridiagonalDoubleMatrix2D = new TridiagonalDoubleMatrix2D(arrayOfDouble);
/*      */     
/*      */ 
/* 1339 */     System.out.println("\n\n\n" + localTridiagonalDoubleMatrix2D);
/* 1340 */     System.out.println("\n" + new DenseDoubleMatrix2D(arrayOfDouble));
/*      */   }
/*      */   
/*      */   public static void doubleTest33()
/*      */   {
/* 1345 */     double d1 = NaN.0D;
/* 1346 */     double d2 = Double.POSITIVE_INFINITY;
/* 1347 */     double d3 = Double.NEGATIVE_INFINITY;
/*      */     
/* 1349 */     double[][] arrayOfDouble = { { d3, d1 } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1362 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = new DenseDoubleMatrix2D(arrayOfDouble);
/*      */     
/* 1364 */     System.out.println("\n\n\n" + localDenseDoubleMatrix2D);
/* 1365 */     System.out.println("\n" + localDenseDoubleMatrix2D.equals(d3));
/*      */   }
/*      */   
/*      */   public static void doubleTest34()
/*      */   {
/* 1370 */     double[][] arrayOfDouble = { { 3.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 4.0D, 2.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 0.0D }, { 0.0D, 0.0D, 0.0D, 0.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1378 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = new DenseDoubleMatrix2D(arrayOfDouble);
/* 1379 */     Property.DEFAULT.generateNonSingular(localDenseDoubleMatrix2D);
/* 1380 */     DoubleMatrix2D localDoubleMatrix2D1 = Algebra.DEFAULT.inverse(localDenseDoubleMatrix2D);
/*      */     
/*      */ 
/* 1383 */     System.out.println("\n\n\n" + localDenseDoubleMatrix2D);
/* 1384 */     System.out.println("\n" + localDoubleMatrix2D1);
/* 1385 */     DoubleMatrix2D localDoubleMatrix2D2 = localDenseDoubleMatrix2D.zMult(localDoubleMatrix2D1, null);
/* 1386 */     System.out.println(localDoubleMatrix2D2);
/* 1387 */     if (!localDoubleMatrix2D2.equals(DoubleFactory2D.dense.identity(localDenseDoubleMatrix2D.rows))) {
/* 1388 */       throw new InternalError();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest35() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest36()
/*      */   {
/* 1462 */     double[] arrayOfDouble = new double[5];
/* 1463 */     arrayOfDouble[0] = 5.0D;
/* 1464 */     arrayOfDouble[1] = NaN.0D;
/* 1465 */     arrayOfDouble[2] = 2.0D;
/* 1466 */     arrayOfDouble[3] = NaN.0D;
/* 1467 */     arrayOfDouble[4] = 1.0D;
/* 1468 */     Object localObject = new DenseDoubleMatrix1D(arrayOfDouble);
/* 1469 */     System.out.println("orig = " + localObject);
/* 1470 */     localObject = ((DoubleMatrix1D)localObject).viewSorted();
/* 1471 */     ((DoubleMatrix1D)localObject).toArray(arrayOfDouble);
/* 1472 */     System.out.println("sort = " + localObject);
/* 1473 */     System.out.println("done\n");
/*      */   }
/*      */   
/*      */   public static void doubleTest4()
/*      */   {
/* 1478 */     int i = 4;
/* 1479 */     int j = 5;
/* 1480 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = new DenseDoubleMatrix2D(i, j);
/* 1481 */     System.out.println(localDenseDoubleMatrix2D);
/* 1482 */     localDenseDoubleMatrix2D.assign(1.0D);
/* 1483 */     DoubleMatrix2D localDoubleMatrix2D = localDenseDoubleMatrix2D.viewPart(2, 0, 2, 3).assign(2.0D);
/* 1484 */     System.out.println("\n" + localDenseDoubleMatrix2D);
/* 1485 */     System.out.println("\n" + localDoubleMatrix2D);
/* 1486 */     Transform.mult(localDoubleMatrix2D, 3.0D);
/* 1487 */     System.out.println("\n" + localDenseDoubleMatrix2D);
/* 1488 */     System.out.println("\n" + localDoubleMatrix2D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest5() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest6()
/*      */   {
/* 1536 */     int i = 4;
/* 1537 */     int j = 5;
/* 1538 */     DoubleMatrix2D localDoubleMatrix2D = Factory2D.ascending(i, j);
/*      */     
/* 1540 */     System.out.println("\n" + localDoubleMatrix2D);
/* 1541 */     localDoubleMatrix2D.viewPart(2, 0, 2, 3).assign(2.0D);
/* 1542 */     System.out.println("\n" + localDoubleMatrix2D);
/*      */     
/* 1544 */     int[] arrayOfInt = { 0, 1, 3, 0, 1, 2 };
/* 1545 */     DoubleMatrix1D localDoubleMatrix1D1 = localDoubleMatrix2D.viewRow(0).viewSelection(arrayOfInt);
/* 1546 */     System.out.println("view1=" + localDoubleMatrix1D1);
/* 1547 */     DoubleMatrix1D localDoubleMatrix1D2 = localDoubleMatrix1D1.viewPart(0, 3);
/* 1548 */     System.out.println("view2=" + localDoubleMatrix1D2);
/*      */     
/* 1550 */     localDoubleMatrix1D2.viewPart(0, 2).assign(-1.0D);
/* 1551 */     System.out.println("master replaced" + localDoubleMatrix2D);
/* 1552 */     System.out.println("flip1 replaced" + localDoubleMatrix1D1);
/* 1553 */     System.out.println("flip2 replaced" + localDoubleMatrix1D2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void doubleTest7()
/*      */   {
/* 1572 */     int i = 4;
/* 1573 */     int j = 5;
/* 1574 */     DoubleMatrix2D localDoubleMatrix2D1 = Factory2D.ascending(i, j);
/*      */     
/* 1576 */     System.out.println("\n" + localDoubleMatrix2D1);
/*      */     
/*      */ 
/*      */ 
/* 1580 */     int[] arrayOfInt1 = { 0, 1, 3, 0 };
/* 1581 */     int[] arrayOfInt2 = { 0, 2 };
/* 1582 */     DoubleMatrix2D localDoubleMatrix2D2 = localDoubleMatrix2D1.viewSelection(arrayOfInt1, arrayOfInt2);
/* 1583 */     System.out.println("view1=" + localDoubleMatrix2D2);
/* 1584 */     DoubleMatrix2D localDoubleMatrix2D3 = localDoubleMatrix2D2.viewPart(0, 0, 2, 2);
/* 1585 */     System.out.println("view2=" + localDoubleMatrix2D3);
/*      */     
/* 1587 */     localDoubleMatrix2D3.assign(-1.0D);
/* 1588 */     System.out.println("master replaced" + localDoubleMatrix2D1);
/* 1589 */     System.out.println("flip1 replaced" + localDoubleMatrix2D2);
/* 1590 */     System.out.println("flip2 replaced" + localDoubleMatrix2D3);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void doubleTest8()
/*      */   {
/* 1596 */     int i = 2;
/* 1597 */     int j = 3;
/* 1598 */     DoubleMatrix2D localDoubleMatrix2D1 = Factory2D.ascending(i, j);
/*      */     
/* 1600 */     System.out.println("\n" + localDoubleMatrix2D1);
/*      */     
/*      */ 
/*      */ 
/* 1604 */     DoubleMatrix2D localDoubleMatrix2D2 = localDoubleMatrix2D1.viewDice();
/* 1605 */     System.out.println("view1=" + localDoubleMatrix2D2);
/* 1606 */     DoubleMatrix2D localDoubleMatrix2D3 = localDoubleMatrix2D2.viewDice();
/* 1607 */     System.out.println("view2=" + localDoubleMatrix2D3);
/*      */     
/* 1609 */     localDoubleMatrix2D3.assign(-1.0D);
/* 1610 */     System.out.println("master replaced" + localDoubleMatrix2D1);
/* 1611 */     System.out.println("flip1 replaced" + localDoubleMatrix2D2);
/* 1612 */     System.out.println("flip2 replaced" + localDoubleMatrix2D3);
/*      */   }
/*      */   
/*      */ 
/*      */   public static void doubleTest9()
/*      */   {
/* 1618 */     int i = 2;
/* 1619 */     int j = 3;
/* 1620 */     DoubleMatrix2D localDoubleMatrix2D1 = Factory2D.ascending(i, j);
/*      */     
/* 1622 */     System.out.println("\n" + localDoubleMatrix2D1);
/*      */     
/*      */ 
/*      */ 
/* 1626 */     DoubleMatrix2D localDoubleMatrix2D2 = localDoubleMatrix2D1.viewRowFlip();
/* 1627 */     System.out.println("view1=" + localDoubleMatrix2D2);
/* 1628 */     DoubleMatrix2D localDoubleMatrix2D3 = localDoubleMatrix2D2.viewRowFlip();
/* 1629 */     System.out.println("view2=" + localDoubleMatrix2D3);
/*      */     
/* 1631 */     localDoubleMatrix2D3.assign(-1.0D);
/* 1632 */     System.out.println("master replaced" + localDoubleMatrix2D1);
/* 1633 */     System.out.println("flip1 replaced" + localDoubleMatrix2D2);
/* 1634 */     System.out.println("flip2 replaced" + localDoubleMatrix2D3);
/*      */   }
/*      */   
/*      */   public static void doubleTestQR()
/*      */   {
/* 1639 */     double[] arrayOfDouble1 = { -6.221564D, -9.002113D, 2.678001D, 6.483597D, -7.934148D };
/* 1640 */     double[] arrayOfDouble2 = { -7.291898D, -7.346928D, 0.520158D, 5.012548D, -8.223725D };
/* 1641 */     double[] arrayOfDouble3 = { 1.185925D, -2.523077D, 0.13538D, 0.412556D, -2.98028D };
/* 1642 */     double[] arrayOfDouble4 = { 13.561087D, -15.20441D, 16.496829D, 16.47086D, 0.822198D };
/*      */     
/* 1644 */     solve(arrayOfDouble3.length, arrayOfDouble3, arrayOfDouble4);
/* 1645 */     solve(arrayOfDouble1.length, arrayOfDouble1, arrayOfDouble2);
/*      */   }
/*      */   
/*      */   public static void main(String[] paramArrayOfString)
/*      */   {
/* 1650 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 1651 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 1652 */     doubleTest30(i, j);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double[][] randomMatrix(int paramInt, MersenneTwister paramMersenneTwister)
/*      */   {
/* 1663 */     double[][] arrayOfDouble = new double[paramInt][paramInt];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1672 */     for (int i = 0; i < paramInt; i++) {
/* 1673 */       for (int j = 0; j < paramInt; j++) {
/* 1674 */         arrayOfDouble[i][j] = 5.0D;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1679 */     return arrayOfDouble;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void solve(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void testLU()
/*      */   {
/* 1719 */     double[][] arrayOfDouble = { { -0.074683D, 0.321248D, -0.014656D, 0.286586D, 0.0D }, { -0.344852D, -0.16278D, 0.173711D, 6.4E-4D, 0.0D }, { -0.181924D, -0.092926D, 0.184153D, 0.177966D, 1.0D }, { -0.166829D, -0.10321D, 0.582301D, 0.142583D, 0.0D }, { 0.0D, -0.112952D, -0.04932D, -0.700157D, 0.0D }, { 0.0D, 0.0D, 0.0D, 0.0D, 0.0D } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1728 */     DenseDoubleMatrix2D localDenseDoubleMatrix2D = new DenseDoubleMatrix2D(arrayOfDouble);
/* 1729 */     System.out.println("\nHplus=" + localDenseDoubleMatrix2D.viewDice().zMult(localDenseDoubleMatrix2D, null));
/*      */     
/* 1731 */     DoubleMatrix2D localDoubleMatrix2D = Algebra.DEFAULT.inverse(localDenseDoubleMatrix2D.viewDice().zMult(localDenseDoubleMatrix2D, null)).zMult(localDenseDoubleMatrix2D.viewDice(), null);
/* 1732 */     localDoubleMatrix2D.assign(Functions.round(1.0E-10D));
/* 1733 */     System.out.println("\nHplus=" + localDoubleMatrix2D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void testMax()
/*      */   {
/* 1766 */     double[] arrayOfDouble = new double[2];
/*      */     
/* 1768 */     arrayOfDouble[0] = 8.9D;
/* 1769 */     arrayOfDouble[1] = 1.0D;
/*      */     
/* 1771 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D = new DenseDoubleMatrix1D(arrayOfDouble);
/*      */     
/* 1773 */     DynamicBin1D localDynamicBin1D = cern.colt.matrix.doublealgo.Statistic.bin(localDenseDoubleMatrix1D);
/*      */     
/* 1775 */     double d = localDynamicBin1D.max();
/*      */     
/* 1777 */     System.out.println("max = " + d);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void testPermuting()
/*      */   {
/* 1786 */     int[] arrayOfInt = { 2, 3, 4, 1, 0, 5 };
/* 1787 */     double[] arrayOfDouble = { 0.0D, 1.0D, 2.0D, 3.0D, 4.0D, 5.0D };
/* 1788 */     Swapper local6 = new Swapper() { private final double[] val$v;
/*      */       
/* 1790 */       public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { double d = this.val$v[paramAnonymousInt1];this.val$v[paramAnonymousInt1] = this.val$v[paramAnonymousInt2];this.val$v[paramAnonymousInt2] = d;
/*      */       }
/*      */       
/*      */ 
/* 1794 */     };
/* 1795 */     System.out.println("\nsource=" + new cern.colt.list.DoubleArrayList(arrayOfDouble));
/* 1796 */     GenericPermuting.permute(arrayOfInt, local6, null);
/* 1797 */     System.out.println("\nres=" + new cern.colt.list.DoubleArrayList(arrayOfDouble));
/*      */   }
/*      */   
/*      */   public static void testPermuting2(int paramInt1, int paramInt2)
/*      */   {
/* 1802 */     int[] arrayOfInt1 = new int[paramInt2];
/* 1803 */     for (int i = 0; i < paramInt2; i++) arrayOfInt1[i] = i;
/* 1804 */     IntArrayList localIntArrayList1 = new IntArrayList(arrayOfInt1);
/* 1805 */     for (int j = 0; j < paramInt1; j++) {
/* 1806 */       localIntArrayList1.shuffle();
/*      */       
/* 1808 */       int[] arrayOfInt2 = new int[paramInt2];
/* 1809 */       for (int k = 0; k < paramInt2; k++) arrayOfInt2[k] = k;
/* 1810 */       IntArrayList localIntArrayList2 = new IntArrayList(arrayOfInt2).copy();
/* 1811 */       IntArrayList localIntArrayList3 = new IntArrayList(arrayOfInt2).copy();
/* 1812 */       Swapper local7 = new Swapper() { private final int[] val$v;
/*      */         
/* 1814 */         public void swap(int paramAnonymousInt1, int paramAnonymousInt2) { int i = this.val$v[paramAnonymousInt1];this.val$v[paramAnonymousInt1] = this.val$v[paramAnonymousInt2];this.val$v[paramAnonymousInt2] = i;
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 1820 */       };
/* 1821 */       GenericPermuting.permute(arrayOfInt1, local7, null);
/* 1822 */       GenericPermuting.permute(localIntArrayList3.elements(), arrayOfInt1);
/* 1823 */       IntArrayList localIntArrayList4 = new IntArrayList(arrayOfInt2);
/*      */       
/*      */ 
/*      */ 
/* 1827 */       if (!localIntArrayList4.equals(localIntArrayList3)) throw new InternalError();
/*      */     }
/* 1829 */     System.out.println("no bug detected\n\n");
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/TestMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */