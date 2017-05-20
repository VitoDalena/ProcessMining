/*      */ package cern.colt;
/*      */ 
/*      */ import cern.colt.function.IntComparator;
/*      */ import cern.colt.list.AbstractDoubleList;
/*      */ import cern.colt.list.AbstractIntList;
/*      */ import cern.colt.list.DoubleArrayList;
/*      */ import cern.colt.list.IntArrayList;
/*      */ import java.util.Comparator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Partitioning
/*      */ {
/*      */   private static final int SMALL = 7;
/*      */   private static final int MEDIUM = 40;
/*   46 */   protected static int steps = 0;
/*   47 */   public static int swappedElements = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int binarySearchFromTo(int paramInt1, int paramInt2, int paramInt3, IntComparator paramIntComparator)
/*      */   {
/*   70 */     while (paramInt2 <= paramInt3) {
/*   71 */       int i = (paramInt2 + paramInt3) / 2;
/*   72 */       int j = paramIntComparator.compare(i, paramInt1);
/*   73 */       if (j < 0) { paramInt2 = i + 1;
/*   74 */       } else if (j > 0) paramInt3 = i - 1; else
/*   75 */         return i;
/*      */     }
/*   77 */     return -(paramInt2 + 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void dualPartition(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2, double[] paramArrayOfDouble3, int paramInt3, int paramInt4, int[] paramArrayOfInt)
/*      */   {
/*   86 */     if (paramInt3 > paramInt4) return;
/*   87 */     int i; if (paramInt1 > paramInt2) {
/*   88 */       paramInt1--;
/*   89 */       for (i = paramInt3; i <= paramInt4; paramArrayOfInt[(i++)] = paramInt1) {}
/*      */       
/*      */ 
/*      */       return;
/*      */     }
/*      */     
/*      */     int k;
/*      */     
/*   97 */     if (paramInt3 == paramInt4) {
/*   98 */       i = paramInt3;
/*      */     }
/*      */     else {
/*  101 */       j = (paramInt1 + paramInt2) / 2;
/*  102 */       k = paramInt2 - paramInt1 + 1;
/*  103 */       if (k > 7) {
/*  104 */         int m = paramInt1;
/*  105 */         int n = paramInt2;
/*  106 */         if (k > 40) {
/*  107 */           int i1 = k / 8;
/*  108 */           m = med3(paramArrayOfDouble1, m, m + i1, m + 2 * i1);
/*  109 */           j = med3(paramArrayOfDouble1, j - i1, j, j + i1);
/*  110 */           n = med3(paramArrayOfDouble1, n - 2 * i1, n - i1, n);
/*      */         }
/*  112 */         j = med3(paramArrayOfDouble1, m, j, n);
/*      */       }
/*      */       
/*      */ 
/*  116 */       i = Sorting.binarySearchFromTo(paramArrayOfDouble3, paramArrayOfDouble1[j], paramInt3, paramInt4);
/*  117 */       if (i < 0) i = -i - 1;
/*  118 */       if (i > paramInt4) { i = paramInt4;
/*      */       }
/*      */     }
/*  121 */     double d = paramArrayOfDouble3[i];
/*      */     
/*      */ 
/*      */ 
/*  125 */     int j = dualPartition(paramArrayOfDouble1, paramArrayOfDouble2, paramInt1, paramInt2, d);
/*  126 */     paramArrayOfInt[i] = j;
/*      */     
/*      */ 
/*  129 */     if (j < paramInt1)
/*      */     {
/*  131 */       k = i - 1;
/*  132 */       while ((k >= paramInt3) && (d >= paramArrayOfDouble3[k])) paramArrayOfInt[(k--)] = j;
/*  133 */       paramInt3 = i + 1;
/*      */     }
/*  135 */     else if (j >= paramInt2)
/*      */     {
/*  137 */       k = i + 1;
/*  138 */       while ((k <= paramInt4) && (d <= paramArrayOfDouble3[k])) paramArrayOfInt[(k++)] = j;
/*  139 */       paramInt4 = i - 1;
/*      */     }
/*      */     
/*      */ 
/*  143 */     if (paramInt3 <= i - 1) {
/*  144 */       dualPartition(paramArrayOfDouble1, paramArrayOfDouble2, paramInt1, j, paramArrayOfDouble3, paramInt3, i - 1, paramArrayOfInt);
/*      */     }
/*      */     
/*      */ 
/*  148 */     if (i + 1 <= paramInt4) {
/*  149 */       dualPartition(paramArrayOfDouble1, paramArrayOfDouble2, j + 1, paramInt2, paramArrayOfDouble3, i + 1, paramInt4, paramArrayOfInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int dualPartition(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2, double paramDouble)
/*      */   {
/*  158 */     int i = paramInt1 - 1;
/*  159 */     do { double d = paramArrayOfDouble1[i];
/*  160 */       if (d < paramDouble)
/*      */       {
/*  162 */         paramArrayOfDouble1[i] = paramArrayOfDouble1[paramInt1];
/*  163 */         paramArrayOfDouble1[paramInt1] = d;
/*      */         
/*  165 */         d = paramArrayOfDouble2[i];
/*  166 */         paramArrayOfDouble2[i] = paramArrayOfDouble2[paramInt1];
/*  167 */         paramArrayOfDouble2[(paramInt1++)] = d;
/*      */       }
/*  158 */       i++; } while (i <= paramInt2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  170 */     return paramInt1 - 1;
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
/*      */   public static void dualPartition(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int[] paramArrayOfInt3, int paramInt3, int paramInt4, int[] paramArrayOfInt4)
/*      */   {
/*  193 */     if (paramInt3 > paramInt4) return;
/*  194 */     int j; if (paramInt1 > paramInt2) {
/*  195 */       paramInt1--;
/*  196 */       for (j = paramInt3; j <= paramInt4; paramArrayOfInt4[(j++)] = paramInt1) {}
/*      */       
/*      */ 
/*      */       return;
/*      */     }
/*      */     
/*      */     int m;
/*      */     
/*  204 */     if (paramInt3 == paramInt4) {
/*  205 */       j = paramInt3;
/*      */     }
/*      */     else {
/*  208 */       k = (paramInt1 + paramInt2) / 2;
/*  209 */       m = paramInt2 - paramInt1 + 1;
/*  210 */       if (m > 7) {
/*  211 */         int n = paramInt1;
/*  212 */         int i1 = paramInt2;
/*  213 */         if (m > 40) {
/*  214 */           int i2 = m / 8;
/*  215 */           n = med3(paramArrayOfInt1, n, n + i2, n + 2 * i2);
/*  216 */           k = med3(paramArrayOfInt1, k - i2, k, k + i2);
/*  217 */           i1 = med3(paramArrayOfInt1, i1 - 2 * i2, i1 - i2, i1);
/*      */         }
/*  219 */         k = med3(paramArrayOfInt1, n, k, i1);
/*      */       }
/*      */       
/*      */ 
/*  223 */       j = Sorting.binarySearchFromTo(paramArrayOfInt3, paramArrayOfInt1[k], paramInt3, paramInt4);
/*  224 */       if (j < 0) j = -j - 1;
/*  225 */       if (j > paramInt4) { j = paramInt4;
/*      */       }
/*      */     }
/*  228 */     int i = paramArrayOfInt3[j];
/*      */     
/*      */ 
/*      */ 
/*  232 */     int k = dualPartition(paramArrayOfInt1, paramArrayOfInt2, paramInt1, paramInt2, i);
/*  233 */     paramArrayOfInt4[j] = k;
/*      */     
/*      */ 
/*  236 */     if (k < paramInt1)
/*      */     {
/*  238 */       m = j - 1;
/*  239 */       while ((m >= paramInt3) && (i >= paramArrayOfInt3[m])) paramArrayOfInt4[(m--)] = k;
/*  240 */       paramInt3 = j + 1;
/*      */     }
/*  242 */     else if (k >= paramInt2)
/*      */     {
/*  244 */       m = j + 1;
/*  245 */       while ((m <= paramInt4) && (i <= paramArrayOfInt3[m])) paramArrayOfInt4[(m++)] = k;
/*  246 */       paramInt4 = j - 1;
/*      */     }
/*      */     
/*      */ 
/*  250 */     if (paramInt3 <= j - 1) {
/*  251 */       dualPartition(paramArrayOfInt1, paramArrayOfInt2, paramInt1, k, paramArrayOfInt3, paramInt3, j - 1, paramArrayOfInt4);
/*      */     }
/*      */     
/*      */ 
/*  255 */     if (j + 1 <= paramInt4) {
/*  256 */       dualPartition(paramArrayOfInt1, paramArrayOfInt2, k + 1, paramInt2, paramArrayOfInt3, j + 1, paramInt4, paramArrayOfInt4);
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
/*      */   public static int dualPartition(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  270 */     int j = paramInt1 - 1;
/*  271 */     do { int i = paramArrayOfInt1[j];
/*  272 */       if (i < paramInt3)
/*      */       {
/*  274 */         paramArrayOfInt1[j] = paramArrayOfInt1[paramInt1];
/*  275 */         paramArrayOfInt1[paramInt1] = i;
/*      */         
/*  277 */         i = paramArrayOfInt2[j];
/*  278 */         paramArrayOfInt2[j] = paramArrayOfInt2[paramInt1];
/*  279 */         paramArrayOfInt2[(paramInt1++)] = i;
/*      */       }
/*  270 */       j++; } while (j <= paramInt2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  282 */     return paramInt1 - 1;
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
/*      */   public static void genericPartition(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, IntComparator paramIntComparator1, IntComparator paramIntComparator2, IntComparator paramIntComparator3, Swapper paramSwapper)
/*      */   {
/*  344 */     if (paramInt3 > paramInt4) return;
/*  345 */     int j; if (paramInt1 > paramInt2) {
/*  346 */       paramInt1--;
/*  347 */       for (j = paramInt3; j <= paramInt4; paramArrayOfInt[(j++)] = paramInt1) {}
/*      */       
/*      */ 
/*      */       return;
/*      */     }
/*      */     
/*      */     int m;
/*      */     
/*  355 */     if (paramInt3 == paramInt4) {
/*  356 */       j = paramInt3;
/*      */     }
/*      */     else {
/*  359 */       k = (paramInt1 + paramInt2) / 2;
/*  360 */       m = paramInt2 - paramInt1 + 1;
/*  361 */       if (m > 7) {
/*  362 */         int n = paramInt1;
/*  363 */         int i1 = paramInt2;
/*  364 */         if (m > 40) {
/*  365 */           int i2 = m / 8;
/*  366 */           n = med3(n, n + i2, n + 2 * i2, paramIntComparator2);
/*  367 */           k = med3(k - i2, k, k + i2, paramIntComparator2);
/*  368 */           i1 = med3(i1 - 2 * i2, i1 - i2, i1, paramIntComparator2);
/*      */         }
/*  370 */         k = med3(n, k, i1, paramIntComparator2);
/*      */       }
/*      */       
/*      */ 
/*  374 */       j = binarySearchFromTo(k, paramInt3, paramInt4, paramIntComparator1);
/*  375 */       if (j < 0) j = -j - 1;
/*  376 */       if (j > paramInt4) { j = paramInt4;
/*      */       }
/*      */     }
/*  379 */     int i = j;
/*      */     
/*      */ 
/*      */ 
/*  383 */     int k = genericPartition(paramInt1, paramInt2, i, paramIntComparator1, paramSwapper);
/*  384 */     paramArrayOfInt[j] = k;
/*      */     
/*      */ 
/*      */ 
/*  388 */     if (k < paramInt1)
/*      */     {
/*  390 */       m = j - 1;
/*  391 */       while ((m >= paramInt3) && (paramIntComparator3.compare(i, m) >= 0)) paramArrayOfInt[(m--)] = k;
/*  392 */       paramInt3 = j + 1;
/*      */     }
/*  394 */     else if (k >= paramInt2)
/*      */     {
/*  396 */       m = j + 1;
/*  397 */       while ((m <= paramInt4) && (paramIntComparator3.compare(i, m) <= 0)) paramArrayOfInt[(m++)] = k;
/*  398 */       paramInt4 = j - 1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  403 */     if (paramInt3 <= j - 1) {
/*  404 */       genericPartition(paramInt1, k, paramInt3, j - 1, paramArrayOfInt, paramIntComparator1, paramIntComparator2, paramIntComparator3, paramSwapper);
/*      */     }
/*      */     
/*      */ 
/*  408 */     if (j + 1 <= paramInt4) {
/*  409 */       genericPartition(k + 1, paramInt2, j + 1, paramInt4, paramArrayOfInt, paramIntComparator1, paramIntComparator2, paramIntComparator3, paramSwapper);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static int genericPartition(int paramInt1, int paramInt2, int paramInt3, IntComparator paramIntComparator, Swapper paramSwapper)
/*      */   {
/*  417 */     int i = paramInt1 - 1;
/*  418 */     do { if (paramIntComparator.compare(paramInt3, i) > 0)
/*      */       {
/*  420 */         paramSwapper.swap(i, paramInt1);
/*  421 */         paramInt1++;
/*      */       }
/*  417 */       i++; } while (i <= paramInt2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  424 */     return paramInt1 - 1;
/*      */   }
/*      */   
/*      */ 
/*      */   private static int med3(double[] paramArrayOfDouble, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  430 */     return paramArrayOfDouble[paramInt1] > paramArrayOfDouble[paramInt3] ? paramInt3 : paramArrayOfDouble[paramInt2] > paramArrayOfDouble[paramInt3] ? paramInt2 : paramArrayOfDouble[paramInt1] < paramArrayOfDouble[paramInt2] ? paramInt1 : paramArrayOfDouble[paramInt1] < paramArrayOfDouble[paramInt3] ? paramInt3 : paramArrayOfDouble[paramInt2] < paramArrayOfDouble[paramInt3] ? paramInt2 : paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int med3(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  438 */     return paramArrayOfInt[paramInt1] > paramArrayOfInt[paramInt3] ? paramInt3 : paramArrayOfInt[paramInt2] > paramArrayOfInt[paramInt3] ? paramInt2 : paramArrayOfInt[paramInt1] < paramArrayOfInt[paramInt2] ? paramInt1 : paramArrayOfInt[paramInt1] < paramArrayOfInt[paramInt3] ? paramInt3 : paramArrayOfInt[paramInt2] < paramArrayOfInt[paramInt3] ? paramInt2 : paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int med3(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3, Comparator paramComparator)
/*      */   {
/*  446 */     int i = paramComparator.compare(paramArrayOfObject[paramInt1], paramArrayOfObject[paramInt2]);
/*  447 */     int j = paramComparator.compare(paramArrayOfObject[paramInt1], paramArrayOfObject[paramInt3]);
/*  448 */     int k = paramComparator.compare(paramArrayOfObject[paramInt2], paramArrayOfObject[paramInt3]);
/*  449 */     return j > 0 ? paramInt3 : k > 0 ? paramInt2 : i < 0 ? paramInt1 : j < 0 ? paramInt3 : k < 0 ? paramInt2 : paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int med3(int paramInt1, int paramInt2, int paramInt3, IntComparator paramIntComparator)
/*      */   {
/*  457 */     int i = paramIntComparator.compare(paramInt1, paramInt2);
/*  458 */     int j = paramIntComparator.compare(paramInt1, paramInt3);
/*  459 */     int k = paramIntComparator.compare(paramInt2, paramInt3);
/*  460 */     return j > 0 ? paramInt3 : k > 0 ? paramInt2 : i < 0 ? paramInt1 : j < 0 ? paramInt3 : k < 0 ? paramInt2 : paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void partition(double[] paramArrayOfDouble1, int paramInt1, int paramInt2, double[] paramArrayOfDouble2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
/*      */   {
/*  471 */     if (paramInt3 > paramInt4) return;
/*  472 */     int i; if (paramInt1 > paramInt2) {
/*  473 */       paramInt1--;
/*  474 */       for (i = paramInt3; i <= paramInt4; paramArrayOfInt[(i++)] = paramInt1) {}
/*      */       
/*      */ 
/*      */       return;
/*      */     }
/*      */     
/*      */     int k;
/*      */     
/*  482 */     if (paramInt3 == paramInt4) {
/*  483 */       i = paramInt3;
/*      */     }
/*      */     else {
/*  486 */       j = (paramInt1 + paramInt2) / 2;
/*  487 */       k = paramInt2 - paramInt1 + 1;
/*  488 */       if (k > 7) {
/*  489 */         int m = paramInt1;
/*  490 */         int n = paramInt2;
/*  491 */         if (k > 40) {
/*  492 */           int i1 = k / 8;
/*  493 */           m = med3(paramArrayOfDouble1, m, m + i1, m + 2 * i1);
/*  494 */           j = med3(paramArrayOfDouble1, j - i1, j, j + i1);
/*  495 */           n = med3(paramArrayOfDouble1, n - 2 * i1, n - i1, n);
/*      */         }
/*  497 */         j = med3(paramArrayOfDouble1, m, j, n);
/*      */       }
/*      */       
/*      */ 
/*  501 */       i = Sorting.binarySearchFromTo(paramArrayOfDouble2, paramArrayOfDouble1[j], paramInt3, paramInt4);
/*  502 */       if (i < 0) i = -i - 1;
/*  503 */       if (i > paramInt4) { i = paramInt4;
/*      */       }
/*      */     }
/*  506 */     double d = paramArrayOfDouble2[i];
/*      */     
/*      */ 
/*      */ 
/*  510 */     int j = partition(paramArrayOfDouble1, paramInt1, paramInt2, d);
/*  511 */     paramArrayOfInt[i] = j;
/*      */     
/*      */ 
/*  514 */     if (j < paramInt1)
/*      */     {
/*  516 */       k = i - 1;
/*  517 */       while ((k >= paramInt3) && (d >= paramArrayOfDouble2[k])) paramArrayOfInt[(k--)] = j;
/*  518 */       paramInt3 = i + 1;
/*      */     }
/*  520 */     else if (j >= paramInt2)
/*      */     {
/*  522 */       k = i + 1;
/*  523 */       while ((k <= paramInt4) && (d <= paramArrayOfDouble2[k])) paramArrayOfInt[(k++)] = j;
/*  524 */       paramInt4 = i - 1;
/*      */     }
/*      */     
/*      */ 
/*  528 */     if (paramInt3 <= i - 1) {
/*  529 */       partition(paramArrayOfDouble1, paramInt1, j, paramArrayOfDouble2, paramInt3, i - 1, paramArrayOfInt);
/*      */     }
/*      */     
/*      */ 
/*  533 */     if (i + 1 <= paramInt4) {
/*  534 */       partition(paramArrayOfDouble1, j + 1, paramInt2, paramArrayOfDouble2, i + 1, paramInt4, paramArrayOfInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int partition(double[] paramArrayOfDouble, int paramInt1, int paramInt2, double paramDouble)
/*      */   {
/*  543 */     int i = paramInt1 - 1;
/*  544 */     do { double d = paramArrayOfDouble[i];
/*  545 */       if (d < paramDouble)
/*      */       {
/*  547 */         paramArrayOfDouble[i] = paramArrayOfDouble[paramInt1];
/*  548 */         paramArrayOfDouble[(paramInt1++)] = d;
/*      */       }
/*  543 */       i++; } while (i <= paramInt2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  551 */     return paramInt1 - 1;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void partition(int[] paramArrayOfInt1, int paramInt1, int paramInt2, int[] paramArrayOfInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt3)
/*      */   {
/*  628 */     if (paramInt3 > paramInt4) return;
/*  629 */     int j; if (paramInt1 > paramInt2) {
/*  630 */       paramInt1--;
/*  631 */       for (j = paramInt3; j <= paramInt4; paramArrayOfInt3[(j++)] = paramInt1) {}
/*      */       
/*      */ 
/*      */       return;
/*      */     }
/*      */     
/*      */     int m;
/*      */     
/*  639 */     if (paramInt3 == paramInt4) {
/*  640 */       j = paramInt3;
/*      */     }
/*      */     else {
/*  643 */       k = (paramInt1 + paramInt2) / 2;
/*  644 */       m = paramInt2 - paramInt1 + 1;
/*  645 */       if (m > 7) {
/*  646 */         int n = paramInt1;
/*  647 */         int i1 = paramInt2;
/*  648 */         if (m > 40) {
/*  649 */           int i2 = m / 8;
/*  650 */           n = med3(paramArrayOfInt1, n, n + i2, n + 2 * i2);
/*  651 */           k = med3(paramArrayOfInt1, k - i2, k, k + i2);
/*  652 */           i1 = med3(paramArrayOfInt1, i1 - 2 * i2, i1 - i2, i1);
/*      */         }
/*  654 */         k = med3(paramArrayOfInt1, n, k, i1);
/*      */       }
/*      */       
/*      */ 
/*  658 */       j = Sorting.binarySearchFromTo(paramArrayOfInt2, paramArrayOfInt1[k], paramInt3, paramInt4);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  690 */       if (j < 0) j = -j - 1;
/*  691 */       if (j > paramInt4) { j = paramInt4;
/*      */       }
/*      */     }
/*  694 */     int i = paramArrayOfInt2[j];
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  700 */     int k = partition(paramArrayOfInt1, paramInt1, paramInt2, i);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  723 */     paramArrayOfInt3[j] = k;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  728 */     if (k < paramInt1)
/*      */     {
/*  730 */       m = j - 1;
/*  731 */       while ((m >= paramInt3) && (i >= paramArrayOfInt2[m])) paramArrayOfInt3[(m--)] = k;
/*  732 */       paramInt3 = j + 1;
/*      */     }
/*  734 */     else if (k >= paramInt2)
/*      */     {
/*  736 */       m = j + 1;
/*  737 */       while ((m <= paramInt4) && (i <= paramArrayOfInt2[m])) paramArrayOfInt3[(m++)] = k;
/*  738 */       paramInt4 = j - 1;
/*      */     }
/*      */     
/*      */ 
/*  742 */     if (paramInt3 <= j - 1)
/*      */     {
/*  744 */       partition(paramArrayOfInt1, paramInt1, k, paramArrayOfInt2, paramInt3, j - 1, paramArrayOfInt3);
/*      */     }
/*      */     
/*      */ 
/*  748 */     if (j + 1 <= paramInt4)
/*      */     {
/*  750 */       partition(paramArrayOfInt1, k + 1, paramInt2, paramArrayOfInt2, j + 1, paramInt4, paramArrayOfInt3);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int partition(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  799 */     steps += paramInt2 - paramInt1 + 1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  832 */     int j = paramInt1 - 1;
/*  833 */     do { int i = paramArrayOfInt[j];
/*  834 */       if (i < paramInt3)
/*      */       {
/*  836 */         paramArrayOfInt[j] = paramArrayOfInt[paramInt1];
/*  837 */         paramArrayOfInt[(paramInt1++)] = i;
/*      */       }
/*  832 */       j++; } while (j <= paramInt2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  936 */     return paramInt1 - 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void partition(Object[] paramArrayOfObject1, int paramInt1, int paramInt2, Object[] paramArrayOfObject2, int paramInt3, int paramInt4, int[] paramArrayOfInt, Comparator paramComparator)
/*      */   {
/*  946 */     if (paramInt3 > paramInt4) return;
/*  947 */     int i; if (paramInt1 > paramInt2) {
/*  948 */       paramInt1--;
/*  949 */       for (i = paramInt3; i <= paramInt4; paramArrayOfInt[(i++)] = paramInt1) {}
/*      */       
/*      */ 
/*      */       return;
/*      */     }
/*      */     
/*      */     int k;
/*      */     
/*  957 */     if (paramInt3 == paramInt4) {
/*  958 */       i = paramInt3;
/*      */     }
/*      */     else {
/*  961 */       j = (paramInt1 + paramInt2) / 2;
/*  962 */       k = paramInt2 - paramInt1 + 1;
/*  963 */       if (k > 7) {
/*  964 */         int m = paramInt1;
/*  965 */         int n = paramInt2;
/*  966 */         if (k > 40) {
/*  967 */           int i1 = k / 8;
/*  968 */           m = med3(paramArrayOfObject1, m, m + i1, m + 2 * i1, paramComparator);
/*  969 */           j = med3(paramArrayOfObject1, j - i1, j, j + i1, paramComparator);
/*  970 */           n = med3(paramArrayOfObject1, n - 2 * i1, n - i1, n, paramComparator);
/*      */         }
/*  972 */         j = med3(paramArrayOfObject1, m, j, n, paramComparator);
/*      */       }
/*      */       
/*      */ 
/*  976 */       i = Sorting.binarySearchFromTo(paramArrayOfObject2, paramArrayOfObject1[j], paramInt3, paramInt4, paramComparator);
/*  977 */       if (i < 0) i = -i - 1;
/*  978 */       if (i > paramInt4) { i = paramInt4;
/*      */       }
/*      */     }
/*  981 */     Object localObject = paramArrayOfObject2[i];
/*      */     
/*      */ 
/*      */ 
/*  985 */     int j = partition(paramArrayOfObject1, paramInt1, paramInt2, localObject, paramComparator);
/*  986 */     paramArrayOfInt[i] = j;
/*      */     
/*      */ 
/*  989 */     if (j < paramInt1)
/*      */     {
/*  991 */       k = i - 1;
/*  992 */       while ((k >= paramInt3) && (paramComparator.compare(localObject, paramArrayOfObject2[k]) >= 0)) paramArrayOfInt[(k--)] = j;
/*  993 */       paramInt3 = i + 1;
/*      */     }
/*  995 */     else if (j >= paramInt2)
/*      */     {
/*  997 */       k = i + 1;
/*  998 */       while ((k <= paramInt4) && (paramComparator.compare(localObject, paramArrayOfObject2[k]) <= 0)) paramArrayOfInt[(k++)] = j;
/*  999 */       paramInt4 = i - 1;
/*      */     }
/*      */     
/*      */ 
/* 1003 */     if (paramInt3 <= i - 1) {
/* 1004 */       partition(paramArrayOfObject1, paramInt1, j, paramArrayOfObject2, paramInt3, i - 1, paramArrayOfInt, paramComparator);
/*      */     }
/*      */     
/*      */ 
/* 1008 */     if (i + 1 <= paramInt4) {
/* 1009 */       partition(paramArrayOfObject1, j + 1, paramInt2, paramArrayOfObject2, i + 1, paramInt4, paramArrayOfInt, paramComparator);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int partition(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject, Comparator paramComparator)
/*      */   {
/* 1018 */     int i = paramInt1 - 1;
/* 1019 */     do { Object localObject = paramArrayOfObject[i];
/* 1020 */       if (paramComparator.compare(localObject, paramObject) < 0)
/*      */       {
/* 1022 */         paramArrayOfObject[i] = paramArrayOfObject[paramInt1];
/* 1023 */         paramArrayOfObject[paramInt1] = localObject;
/* 1024 */         paramInt1++;
/*      */       }
/* 1018 */       i++; } while (i <= paramInt2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1027 */     return paramInt1 - 1;
/*      */   }
/*      */   
/*      */ 
/*      */   public static void partition(DoubleArrayList paramDoubleArrayList1, int paramInt1, int paramInt2, DoubleArrayList paramDoubleArrayList2, IntArrayList paramIntArrayList)
/*      */   {
/* 1033 */     partition(paramDoubleArrayList1.elements(), paramInt1, paramInt2, paramDoubleArrayList2.elements(), 0, paramDoubleArrayList2.size() - 1, paramIntArrayList.elements());
/*      */   }
/*      */   
/*      */ 
/*      */   public static void partition(IntArrayList paramIntArrayList1, int paramInt1, int paramInt2, IntArrayList paramIntArrayList2, IntArrayList paramIntArrayList3)
/*      */   {
/* 1039 */     partition(paramIntArrayList1.elements(), paramInt1, paramInt2, paramIntArrayList2.elements(), 0, paramIntArrayList2.size() - 1, paramIntArrayList3.elements());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void triplePartition(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt1, int paramInt2, double[] paramArrayOfDouble4, int paramInt3, int paramInt4, int[] paramArrayOfInt)
/*      */   {
/* 1048 */     if (paramInt3 > paramInt4) return;
/* 1049 */     int i; if (paramInt1 > paramInt2) {
/* 1050 */       paramInt1--;
/* 1051 */       for (i = paramInt3; i <= paramInt4; paramArrayOfInt[(i++)] = paramInt1) {}
/*      */       
/*      */ 
/*      */       return;
/*      */     }
/*      */     
/*      */     int k;
/*      */     
/* 1059 */     if (paramInt3 == paramInt4) {
/* 1060 */       i = paramInt3;
/*      */     }
/*      */     else {
/* 1063 */       j = (paramInt1 + paramInt2) / 2;
/* 1064 */       k = paramInt2 - paramInt1 + 1;
/* 1065 */       if (k > 7) {
/* 1066 */         int m = paramInt1;
/* 1067 */         int n = paramInt2;
/* 1068 */         if (k > 40) {
/* 1069 */           int i1 = k / 8;
/* 1070 */           m = med3(paramArrayOfDouble1, m, m + i1, m + 2 * i1);
/* 1071 */           j = med3(paramArrayOfDouble1, j - i1, j, j + i1);
/* 1072 */           n = med3(paramArrayOfDouble1, n - 2 * i1, n - i1, n);
/*      */         }
/* 1074 */         j = med3(paramArrayOfDouble1, m, j, n);
/*      */       }
/*      */       
/*      */ 
/* 1078 */       i = Sorting.binarySearchFromTo(paramArrayOfDouble4, paramArrayOfDouble1[j], paramInt3, paramInt4);
/* 1079 */       if (i < 0) i = -i - 1;
/* 1080 */       if (i > paramInt4) { i = paramInt4;
/*      */       }
/*      */     }
/* 1083 */     double d = paramArrayOfDouble4[i];
/*      */     
/*      */ 
/*      */ 
/* 1087 */     int j = triplePartition(paramArrayOfDouble1, paramArrayOfDouble2, paramArrayOfDouble3, paramInt1, paramInt2, d);
/* 1088 */     paramArrayOfInt[i] = j;
/*      */     
/*      */ 
/* 1091 */     if (j < paramInt1)
/*      */     {
/* 1093 */       k = i - 1;
/* 1094 */       while ((k >= paramInt3) && (d >= paramArrayOfDouble4[k])) paramArrayOfInt[(k--)] = j;
/* 1095 */       paramInt3 = i + 1;
/*      */     }
/* 1097 */     else if (j >= paramInt2)
/*      */     {
/* 1099 */       k = i + 1;
/* 1100 */       while ((k <= paramInt4) && (d <= paramArrayOfDouble4[k])) paramArrayOfInt[(k++)] = j;
/* 1101 */       paramInt4 = i - 1;
/*      */     }
/*      */     
/*      */ 
/* 1105 */     if (paramInt3 <= i - 1) {
/* 1106 */       triplePartition(paramArrayOfDouble1, paramArrayOfDouble2, paramArrayOfDouble3, paramInt1, j, paramArrayOfDouble4, paramInt3, i - 1, paramArrayOfInt);
/*      */     }
/*      */     
/*      */ 
/* 1110 */     if (i + 1 <= paramInt4) {
/* 1111 */       triplePartition(paramArrayOfDouble1, paramArrayOfDouble2, paramArrayOfDouble3, j + 1, paramInt2, paramArrayOfDouble4, i + 1, paramInt4, paramArrayOfInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int triplePartition(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt1, int paramInt2, double paramDouble)
/*      */   {
/* 1120 */     int i = paramInt1 - 1;
/* 1121 */     do { double d = paramArrayOfDouble1[i];
/* 1122 */       if (d < paramDouble)
/*      */       {
/* 1124 */         paramArrayOfDouble1[i] = paramArrayOfDouble1[paramInt1];
/* 1125 */         paramArrayOfDouble1[paramInt1] = d;
/*      */         
/* 1127 */         d = paramArrayOfDouble2[i];
/* 1128 */         paramArrayOfDouble2[i] = paramArrayOfDouble2[paramInt1];
/* 1129 */         paramArrayOfDouble2[paramInt1] = d;
/*      */         
/* 1131 */         d = paramArrayOfDouble3[i];
/* 1132 */         paramArrayOfDouble3[i] = paramArrayOfDouble3[paramInt1];
/* 1133 */         paramArrayOfDouble3[(paramInt1++)] = d;
/*      */       }
/* 1120 */       i++; } while (i <= paramInt2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1137 */     return paramInt1 - 1;
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
/*      */   public static void triplePartition(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int paramInt1, int paramInt2, int[] paramArrayOfInt4, int paramInt3, int paramInt4, int[] paramArrayOfInt5)
/*      */   {
/* 1160 */     if (paramInt3 > paramInt4) return;
/* 1161 */     int j; if (paramInt1 > paramInt2) {
/* 1162 */       paramInt1--;
/* 1163 */       for (j = paramInt3; j <= paramInt4; paramArrayOfInt5[(j++)] = paramInt1) {}
/*      */       
/*      */ 
/*      */       return;
/*      */     }
/*      */     
/*      */     int m;
/*      */     
/* 1171 */     if (paramInt3 == paramInt4) {
/* 1172 */       j = paramInt3;
/*      */     }
/*      */     else {
/* 1175 */       k = (paramInt1 + paramInt2) / 2;
/* 1176 */       m = paramInt2 - paramInt1 + 1;
/* 1177 */       if (m > 7) {
/* 1178 */         int n = paramInt1;
/* 1179 */         int i1 = paramInt2;
/* 1180 */         if (m > 40) {
/* 1181 */           int i2 = m / 8;
/* 1182 */           n = med3(paramArrayOfInt1, n, n + i2, n + 2 * i2);
/* 1183 */           k = med3(paramArrayOfInt1, k - i2, k, k + i2);
/* 1184 */           i1 = med3(paramArrayOfInt1, i1 - 2 * i2, i1 - i2, i1);
/*      */         }
/* 1186 */         k = med3(paramArrayOfInt1, n, k, i1);
/*      */       }
/*      */       
/*      */ 
/* 1190 */       j = Sorting.binarySearchFromTo(paramArrayOfInt4, paramArrayOfInt1[k], paramInt3, paramInt4);
/* 1191 */       if (j < 0) j = -j - 1;
/* 1192 */       if (j > paramInt4) { j = paramInt4;
/*      */       }
/*      */     }
/* 1195 */     int i = paramArrayOfInt4[j];
/*      */     
/*      */ 
/*      */ 
/* 1199 */     int k = triplePartition(paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3, paramInt1, paramInt2, i);
/* 1200 */     paramArrayOfInt5[j] = k;
/*      */     
/*      */ 
/* 1203 */     if (k < paramInt1)
/*      */     {
/* 1205 */       m = j - 1;
/* 1206 */       while ((m >= paramInt3) && (i >= paramArrayOfInt4[m])) paramArrayOfInt5[(m--)] = k;
/* 1207 */       paramInt3 = j + 1;
/*      */     }
/* 1209 */     else if (k >= paramInt2)
/*      */     {
/* 1211 */       m = j + 1;
/* 1212 */       while ((m <= paramInt4) && (i <= paramArrayOfInt4[m])) paramArrayOfInt5[(m++)] = k;
/* 1213 */       paramInt4 = j - 1;
/*      */     }
/*      */     
/*      */ 
/* 1217 */     if (paramInt3 <= j - 1) {
/* 1218 */       triplePartition(paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3, paramInt1, k, paramArrayOfInt4, paramInt3, j - 1, paramArrayOfInt5);
/*      */     }
/*      */     
/*      */ 
/* 1222 */     if (j + 1 <= paramInt4) {
/* 1223 */       triplePartition(paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3, k + 1, paramInt2, paramArrayOfInt4, j + 1, paramInt4, paramArrayOfInt5);
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
/*      */   public static int triplePartition(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 1237 */     int j = paramInt1 - 1;
/* 1238 */     do { int i = paramArrayOfInt1[j];
/* 1239 */       if (i < paramInt3)
/*      */       {
/* 1241 */         paramArrayOfInt1[j] = paramArrayOfInt1[paramInt1];
/* 1242 */         paramArrayOfInt1[paramInt1] = i;
/*      */         
/* 1244 */         i = paramArrayOfInt2[j];
/* 1245 */         paramArrayOfInt2[j] = paramArrayOfInt2[paramInt1];
/* 1246 */         paramArrayOfInt2[paramInt1] = i;
/*      */         
/* 1248 */         i = paramArrayOfInt3[j];
/* 1249 */         paramArrayOfInt3[j] = paramArrayOfInt3[paramInt1];
/* 1250 */         paramArrayOfInt3[(paramInt1++)] = i;
/*      */       }
/* 1237 */       j++; } while (j <= paramInt2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1254 */     return paramInt1 - 1;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/Partitioning.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */