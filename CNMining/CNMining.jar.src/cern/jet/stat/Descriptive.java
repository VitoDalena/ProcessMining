/*      */ package cern.jet.stat;
/*      */ 
/*      */ import cern.colt.list.AbstractDoubleList;
/*      */ import cern.colt.list.AbstractList;
/*      */ import cern.colt.list.DoubleArrayList;
/*      */ import cern.colt.list.IntArrayList;
/*      */ import cern.jet.math.Arithmetic;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Descriptive
/*      */ {
/*      */   public static double autoCorrelation(DoubleArrayList paramDoubleArrayList, int paramInt, double paramDouble1, double paramDouble2)
/*      */   {
/*   29 */     int i = paramDoubleArrayList.size();
/*   30 */     if (paramInt >= i) { throw new IllegalArgumentException("Lag is too large");
/*      */     }
/*   32 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*   33 */     double d = 0.0D;
/*   34 */     for (int j = paramInt; j < i; j++) {
/*   35 */       d += (arrayOfDouble[j] - paramDouble1) * (arrayOfDouble[(j - paramInt)] - paramDouble1);
/*      */     }
/*   37 */     return d / (i - paramInt) / paramDouble2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected static void checkRangeFromTo(int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*   44 */     if (paramInt2 == paramInt1 - 1) return;
/*   45 */     if ((paramInt1 < 0) || (paramInt1 > paramInt2) || (paramInt2 >= paramInt3)) {
/*   46 */       throw new IndexOutOfBoundsException("from: " + paramInt1 + ", to: " + paramInt2 + ", size=" + paramInt3);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public static double correlation(DoubleArrayList paramDoubleArrayList1, double paramDouble1, DoubleArrayList paramDoubleArrayList2, double paramDouble2)
/*      */   {
/*   53 */     return covariance(paramDoubleArrayList1, paramDoubleArrayList2) / (paramDouble1 * paramDouble2);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double covariance(DoubleArrayList paramDoubleArrayList1, DoubleArrayList paramDoubleArrayList2)
/*      */   {
/*   59 */     int i = paramDoubleArrayList1.size();
/*   60 */     if ((i != paramDoubleArrayList2.size()) || (i == 0)) throw new IllegalArgumentException();
/*   61 */     double[] arrayOfDouble1 = paramDoubleArrayList1.elements();
/*   62 */     double[] arrayOfDouble2 = paramDoubleArrayList2.elements();
/*      */     
/*   64 */     double d1 = arrayOfDouble1[0];double d2 = arrayOfDouble2[0];double d3 = 0.0D;
/*   65 */     for (int j = 1; j < i; j++) {
/*   66 */       double d4 = arrayOfDouble1[j];
/*   67 */       double d5 = arrayOfDouble2[j];
/*   68 */       d1 += d4;
/*   69 */       d3 += (d4 - d1 / (j + 1)) * (d5 - d2 / j);
/*   70 */       d2 += d5;
/*      */     }
/*      */     
/*   73 */     return d3 / i;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double durbinWatson(DoubleArrayList paramDoubleArrayList)
/*      */   {
/*   79 */     int i = paramDoubleArrayList.size();
/*   80 */     if (i < 2) { throw new IllegalArgumentException("data sequence must contain at least two values.");
/*      */     }
/*   82 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*   83 */     double d1 = 0.0D;
/*   84 */     double d2 = 0.0D;
/*   85 */     d2 = arrayOfDouble[0] * arrayOfDouble[0];
/*   86 */     for (int j = 1; j < i; j++) {
/*   87 */       double d3 = arrayOfDouble[j] - arrayOfDouble[(j - 1)];
/*   88 */       d1 += d3 * d3;
/*   89 */       d2 += arrayOfDouble[j] * arrayOfDouble[j];
/*      */     }
/*      */     
/*   92 */     return d1 / d2;
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
/*      */   public static void frequencies(DoubleArrayList paramDoubleArrayList1, DoubleArrayList paramDoubleArrayList2, IntArrayList paramIntArrayList)
/*      */   {
/*  111 */     paramDoubleArrayList2.clear();
/*  112 */     if (paramIntArrayList != null) { paramIntArrayList.clear();
/*      */     }
/*  114 */     double[] arrayOfDouble = paramDoubleArrayList1.elements();
/*  115 */     int i = paramDoubleArrayList1.size();
/*  116 */     int j = 0;
/*      */     
/*  118 */     while (j < i) {
/*  119 */       double d = arrayOfDouble[j];
/*  120 */       int k = j;
/*      */       do
/*      */       {
/*  123 */         j++; } while ((j < i) && (arrayOfDouble[j] == d));
/*      */       
/*  125 */       int m = j - k;
/*  126 */       paramDoubleArrayList2.add(d);
/*  127 */       if (paramIntArrayList != null) { paramIntArrayList.add(m);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double geometricMean(int paramInt, double paramDouble)
/*      */   {
/*  138 */     return Math.exp(paramDouble / paramInt);
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
/*      */   public static double geometricMean(DoubleArrayList paramDoubleArrayList)
/*      */   {
/*  152 */     return geometricMean(paramDoubleArrayList.size(), sumOfLogarithms(paramDoubleArrayList, 0, paramDoubleArrayList.size() - 1));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double harmonicMean(int paramInt, double paramDouble)
/*      */   {
/*  161 */     return paramInt / paramDouble;
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
/*      */   public static void incrementalUpdate(DoubleArrayList paramDoubleArrayList, int paramInt1, int paramInt2, double[] paramArrayOfDouble)
/*      */   {
/*  199 */     checkRangeFromTo(paramInt1, paramInt2, paramDoubleArrayList.size());
/*      */     
/*      */ 
/*  202 */     double d1 = paramArrayOfDouble[0];
/*  203 */     double d2 = paramArrayOfDouble[1];
/*  204 */     double d3 = paramArrayOfDouble[2];
/*  205 */     double d4 = paramArrayOfDouble[3];
/*      */     
/*  207 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*  209 */     for (; 
/*  209 */         paramInt1 <= paramInt2; paramInt1++) {
/*  210 */       double d5 = arrayOfDouble[paramInt1];
/*  211 */       d3 += d5;
/*  212 */       d4 += d5 * d5;
/*  213 */       if (d5 < d1) d1 = d5;
/*  214 */       if (d5 > d2) { d2 = d5;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  233 */     paramArrayOfDouble[0] = d1;
/*  234 */     paramArrayOfDouble[1] = d2;
/*  235 */     paramArrayOfDouble[2] = d3;
/*  236 */     paramArrayOfDouble[3] = d4;
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
/*      */   public static void incrementalUpdateSumsOfPowers(DoubleArrayList paramDoubleArrayList, int paramInt1, int paramInt2, int paramInt3, int paramInt4, double[] paramArrayOfDouble)
/*      */   {
/*  279 */     int i = paramDoubleArrayList.size();
/*  280 */     double d1 = paramInt4 - paramInt3;
/*  281 */     if ((paramInt1 > i) || (d1 + 1 > paramArrayOfDouble.length)) { throw new IllegalArgumentException();
/*      */     }
/*      */     double d8;
/*  284 */     if (paramInt3 == 1) { double[] arrayOfDouble1;
/*  285 */       double d2; double d4; if (paramInt4 == 2) {
/*  286 */         arrayOfDouble1 = paramDoubleArrayList.elements();
/*  287 */         d2 = paramArrayOfDouble[0];
/*  288 */         d4 = paramArrayOfDouble[1];
/*  289 */         int m = paramInt1 - 1;
/*  290 */         do { double d7 = arrayOfDouble1[m];
/*  291 */           d2 += d7;
/*  292 */           d4 += d7 * d7;m++;
/*  289 */         } while (m <= paramInt2);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  296 */         paramArrayOfDouble[0] += d2;
/*  297 */         paramArrayOfDouble[1] += d4; return;
/*      */       }
/*      */       double d6;
/*  300 */       if (paramInt4 == 3) {
/*  301 */         arrayOfDouble1 = paramDoubleArrayList.elements();
/*  302 */         d2 = paramArrayOfDouble[0];
/*  303 */         d4 = paramArrayOfDouble[1];
/*  304 */         d6 = paramArrayOfDouble[2];
/*  305 */         int i1 = paramInt1 - 1;
/*  306 */         do { double d9 = arrayOfDouble1[i1];
/*  307 */           d2 += d9;
/*  308 */           d4 += d9 * d9;
/*  309 */           d6 += d9 * d9 * d9;i1++;
/*  305 */         } while (i1 <= paramInt2);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  313 */         paramArrayOfDouble[0] += d2;
/*  314 */         paramArrayOfDouble[1] += d4;
/*  315 */         paramArrayOfDouble[2] += d6;
/*  316 */         return;
/*      */       }
/*  318 */       if (paramInt4 == 4) {
/*  319 */         arrayOfDouble1 = paramDoubleArrayList.elements();
/*  320 */         d2 = paramArrayOfDouble[0];
/*  321 */         d4 = paramArrayOfDouble[1];
/*  322 */         d6 = paramArrayOfDouble[2];
/*  323 */         d8 = paramArrayOfDouble[3];
/*  324 */         int i2 = paramInt1 - 1;
/*  325 */         do { double d10 = arrayOfDouble1[i2];
/*  326 */           d2 += d10;
/*  327 */           d4 += d10 * d10;
/*  328 */           d6 += d10 * d10 * d10;
/*  329 */           d8 += d10 * d10 * d10 * d10;i2++;
/*  324 */         } while (i2 <= paramInt2);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  333 */         paramArrayOfDouble[0] += d2;
/*  334 */         paramArrayOfDouble[1] += d4;
/*  335 */         paramArrayOfDouble[2] += d6;
/*  336 */         paramArrayOfDouble[3] += d8;
/*  337 */         return;
/*      */       }
/*      */     }
/*      */     
/*  341 */     if ((paramInt3 == paramInt4) || ((paramInt3 >= -1) && (paramInt4 <= 5))) {
/*  342 */       for (int j = paramInt3; j <= paramInt4; j++) {
/*  343 */         paramArrayOfDouble[(j - paramInt3)] += sumOfPowerDeviations(paramDoubleArrayList, j, 0.0D, paramInt1, paramInt2);
/*      */       }
/*  345 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  351 */     double[] arrayOfDouble2 = paramDoubleArrayList.elements();
/*      */     
/*  353 */     int k = paramInt1 - 1;
/*  354 */     do { double d3 = arrayOfDouble2[k];
/*  355 */       double d5 = Math.pow(d3, paramInt3);
/*      */       
/*  357 */       int n = 0;
/*  358 */       d8 = d1;
/*  359 */       do { paramArrayOfDouble[(n++)] += d5;
/*  360 */         d5 *= d3;d8--;
/*  358 */       } while (d8 >= 0);
/*      */       
/*      */ 
/*      */ 
/*  362 */       paramArrayOfDouble[n] += d5;k++;
/*  353 */     } while (k <= paramInt2);
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
/*      */   public static void incrementalWeightedUpdate(DoubleArrayList paramDoubleArrayList1, DoubleArrayList paramDoubleArrayList2, int paramInt1, int paramInt2, double[] paramArrayOfDouble)
/*      */   {
/*  402 */     int i = paramDoubleArrayList1.size();
/*  403 */     checkRangeFromTo(paramInt1, paramInt2, i);
/*  404 */     if (i != paramDoubleArrayList2.size()) { throw new IllegalArgumentException("from=" + paramInt1 + ", to=" + paramInt2 + ", data.size()=" + i + ", weights.size()=" + paramDoubleArrayList2.size());
/*      */     }
/*      */     
/*  407 */     double d1 = paramArrayOfDouble[0];
/*  408 */     double d2 = paramArrayOfDouble[1];
/*      */     
/*  410 */     double[] arrayOfDouble1 = paramDoubleArrayList1.elements();
/*  411 */     double[] arrayOfDouble2 = paramDoubleArrayList2.elements();
/*      */     
/*  413 */     int j = paramInt1 - 1;
/*  414 */     do { double d3 = arrayOfDouble1[j];
/*  415 */       double d4 = arrayOfDouble2[j];
/*  416 */       double d5 = d3 * d4;
/*      */       
/*  418 */       d1 += d5;
/*  419 */       d2 += d3 * d5;j++;
/*  413 */     } while (j <= paramInt2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  423 */     paramArrayOfDouble[0] = d1;
/*  424 */     paramArrayOfDouble[1] = d2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double kurtosis(double paramDouble1, double paramDouble2)
/*      */   {
/*  435 */     return -3.0D + paramDouble1 / (paramDouble2 * paramDouble2 * paramDouble2 * paramDouble2);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double kurtosis(DoubleArrayList paramDoubleArrayList, double paramDouble1, double paramDouble2)
/*      */   {
/*  441 */     return kurtosis(moment(paramDoubleArrayList, 4, paramDouble1), paramDouble2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double lag1(DoubleArrayList paramDoubleArrayList, double paramDouble)
/*      */   {
/*  448 */     int i = paramDoubleArrayList.size();
/*  449 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*      */     
/*  451 */     double d2 = 0.0D;
/*  452 */     double d3 = (arrayOfDouble[0] - paramDouble) * (arrayOfDouble[0] - paramDouble);
/*      */     
/*  454 */     for (int j = 1; j < i; j++) {
/*  455 */       double d4 = arrayOfDouble[(j - 1)] - paramDouble;
/*  456 */       double d5 = arrayOfDouble[j] - paramDouble;
/*  457 */       d2 += (d4 * d5 - d2) / (j + 1);
/*  458 */       d3 += (d5 * d5 - d3) / (j + 1);
/*      */     }
/*      */     
/*  461 */     double d1 = d2 / d3;
/*  462 */     return d1;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double max(DoubleArrayList paramDoubleArrayList)
/*      */   {
/*  468 */     int i = paramDoubleArrayList.size();
/*  469 */     if (i == 0) { throw new IllegalArgumentException();
/*      */     }
/*  471 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*  472 */     double d = arrayOfDouble[(i - 1)];
/*  473 */     int j = i - 1;
/*  474 */     do { if (arrayOfDouble[j] > d) d = arrayOfDouble[j];
/*  473 */       j--; } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*  477 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double mean(DoubleArrayList paramDoubleArrayList)
/*      */   {
/*  484 */     return sum(paramDoubleArrayList) / paramDoubleArrayList.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double meanDeviation(DoubleArrayList paramDoubleArrayList, double paramDouble)
/*      */   {
/*  491 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*  492 */     int i = paramDoubleArrayList.size();
/*  493 */     double d = 0.0D;
/*  494 */     int j = i; do { d += Math.abs(arrayOfDouble[j] - paramDouble);j--; } while (j >= 0);
/*  495 */     return d / i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double median(DoubleArrayList paramDoubleArrayList)
/*      */   {
/*  503 */     return quantile(paramDoubleArrayList, 0.5D);
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
/*      */   public static double min(DoubleArrayList paramDoubleArrayList)
/*      */   {
/*  523 */     int i = paramDoubleArrayList.size();
/*  524 */     if (i == 0) { throw new IllegalArgumentException();
/*      */     }
/*  526 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*  527 */     double d = arrayOfDouble[(i - 1)];
/*  528 */     int j = i - 1;
/*  529 */     do { if (arrayOfDouble[j] < d) d = arrayOfDouble[j];
/*  528 */       j--; } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*  532 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double moment(int paramInt1, double paramDouble, int paramInt2, double[] paramArrayOfDouble)
/*      */   {
/*  543 */     double d1 = 0.0D;
/*  544 */     int i = 1;
/*  545 */     for (int j = 0; j <= paramInt1; j++) {
/*      */       double d2;
/*  547 */       if (j == 0) { d2 = 1.0D;
/*  548 */       } else if (j == 1) { d2 = paramDouble;
/*  549 */       } else if (j == 2) { d2 = paramDouble * paramDouble;
/*  550 */       } else if (j == 3) d2 = paramDouble * paramDouble * paramDouble; else {
/*  551 */         d2 = Math.pow(paramDouble, j);
/*      */       }
/*  553 */       d1 += i * Arithmetic.binomial(paramInt1, j) * d2 * paramArrayOfDouble[(paramInt1 - j)];
/*  554 */       i = -i;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  562 */     return d1 / paramInt2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double moment(DoubleArrayList paramDoubleArrayList, int paramInt, double paramDouble)
/*      */   {
/*  569 */     return sumOfPowerDeviations(paramDoubleArrayList, paramInt, paramDouble) / paramDoubleArrayList.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double pooledMean(int paramInt1, double paramDouble1, int paramInt2, double paramDouble2)
/*      */   {
/*  581 */     return (paramInt1 * paramDouble1 + paramInt2 * paramDouble2) / (paramInt1 + paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double pooledVariance(int paramInt1, double paramDouble1, int paramInt2, double paramDouble2)
/*      */   {
/*  593 */     return (paramInt1 * paramDouble1 + paramInt2 * paramDouble2) / (paramInt1 + paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double product(int paramInt, double paramDouble)
/*      */   {
/*  602 */     return Math.pow(Math.exp(paramDouble / paramInt), paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double product(DoubleArrayList paramDoubleArrayList)
/*      */   {
/*  610 */     int i = paramDoubleArrayList.size();
/*  611 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*      */     
/*  613 */     double d = 1.0D;
/*  614 */     int j = i; do { d *= arrayOfDouble[j];j--; } while (j >= 0);
/*      */     
/*  616 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double quantile(DoubleArrayList paramDoubleArrayList, double paramDouble)
/*      */   {
/*  625 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*  626 */     int i = paramDoubleArrayList.size();
/*      */     
/*  628 */     double d1 = paramDouble * (i - 1);
/*  629 */     int j = (int)d1;
/*  630 */     double d2 = d1 - j;
/*      */     
/*      */ 
/*  633 */     if (i == 0) return 0.0D;
/*      */     double d3;
/*  635 */     if (j == i - 1) {
/*  636 */       d3 = arrayOfDouble[j];
/*      */     }
/*      */     else {
/*  639 */       d3 = (1.0D - d2) * arrayOfDouble[j] + d2 * arrayOfDouble[(j + 1)];
/*      */     }
/*      */     
/*  642 */     return d3;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double quantileInverse(DoubleArrayList paramDoubleArrayList, double paramDouble)
/*      */   {
/*  653 */     return rankInterpolated(paramDoubleArrayList, paramDouble) / paramDoubleArrayList.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DoubleArrayList quantiles(DoubleArrayList paramDoubleArrayList1, DoubleArrayList paramDoubleArrayList2)
/*      */   {
/*  664 */     int i = paramDoubleArrayList2.size();
/*  665 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(i);
/*      */     
/*  667 */     for (int j = 0; j < i; j++) {
/*  668 */       localDoubleArrayList.add(quantile(paramDoubleArrayList1, paramDoubleArrayList2.get(j)));
/*      */     }
/*      */     
/*  671 */     return localDoubleArrayList;
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
/*      */   public static double rankInterpolated(DoubleArrayList paramDoubleArrayList, double paramDouble)
/*      */   {
/*  685 */     int i = paramDoubleArrayList.binarySearch(paramDouble);
/*  686 */     if (i >= 0)
/*      */     {
/*  688 */       j = i + 1;
/*  689 */       int k = paramDoubleArrayList.size();
/*  690 */       while ((j < k) && (paramDoubleArrayList.get(j) == paramDouble)) j++;
/*  691 */       return j;
/*      */     }
/*      */     
/*      */ 
/*  695 */     int j = -i - 1;
/*  696 */     if ((j == 0) || (j == paramDoubleArrayList.size())) { return j;
/*      */     }
/*  698 */     double d1 = paramDoubleArrayList.get(j - 1);
/*  699 */     double d2 = paramDoubleArrayList.get(j);
/*  700 */     double d3 = (paramDouble - d1) / (d2 - d1);
/*  701 */     return j + d3;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double rms(int paramInt, double paramDouble)
/*      */   {
/*  713 */     return Math.sqrt(paramDouble / paramInt);
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
/*      */   public static double sampleKurtosis(int paramInt, double paramDouble1, double paramDouble2)
/*      */   {
/*  727 */     int i = paramInt;
/*  728 */     double d1 = paramDouble2;
/*  729 */     double d2 = paramDouble1 * i;
/*  730 */     return d2 * i * (i + 1) / ((i - 1) * (i - 2) * (i - 3) * d1 * d1) - 3.0D * (i - 1) * (i - 1) / ((i - 2) * (i - 3));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double sampleKurtosis(DoubleArrayList paramDoubleArrayList, double paramDouble1, double paramDouble2)
/*      */   {
/*  737 */     return sampleKurtosis(paramDoubleArrayList.size(), moment(paramDoubleArrayList, 4, paramDouble1), paramDouble2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double sampleKurtosisStandardError(int paramInt)
/*      */   {
/*  749 */     int i = paramInt;
/*  750 */     return Math.sqrt(24.0D * i * (i - 1) * (i - 1) / ((i - 3) * (i - 2) * (i + 3) * (i + 5)));
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
/*      */   public static double sampleSkew(int paramInt, double paramDouble1, double paramDouble2)
/*      */   {
/*  764 */     int i = paramInt;
/*  765 */     double d1 = Math.sqrt(paramDouble2);
/*  766 */     double d2 = paramDouble1 * i;
/*  767 */     return i * d2 / ((i - 1) * (i - 2) * d1 * d1 * d1);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double sampleSkew(DoubleArrayList paramDoubleArrayList, double paramDouble1, double paramDouble2)
/*      */   {
/*  773 */     return sampleSkew(paramDoubleArrayList.size(), moment(paramDoubleArrayList, 3, paramDouble1), paramDouble2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double sampleSkewStandardError(int paramInt)
/*      */   {
/*  785 */     int i = paramInt;
/*  786 */     return Math.sqrt(6.0D * i * (i - 1) / ((i - 2) * (i + 1) * (i + 3)));
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
/*      */   public static double sampleStandardDeviation(int paramInt, double paramDouble)
/*      */   {
/*  800 */     int i = paramInt;
/*      */     
/*      */ 
/*      */ 
/*  804 */     double d1 = Math.sqrt(paramDouble);
/*      */     double d2;
/*  806 */     if (i > 30) {
/*  807 */       d2 = 1.0D + 1.0D / (4 * (i - 1));
/*      */     } else {
/*  809 */       d2 = Math.sqrt((i - 1) * 0.5D) * Gamma.gamma((i - 1) * 0.5D) / Gamma.gamma(i * 0.5D);
/*      */     }
/*  811 */     return d2 * d1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double sampleVariance(int paramInt, double paramDouble1, double paramDouble2)
/*      */   {
/*  822 */     double d = paramDouble1 / paramInt;
/*  823 */     return (paramDouble2 - d * paramDouble1) / (paramInt - 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double sampleVariance(DoubleArrayList paramDoubleArrayList, double paramDouble)
/*      */   {
/*  830 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*  831 */     int i = paramDoubleArrayList.size();
/*  832 */     double d1 = 0.0D;
/*      */     
/*  834 */     int j = i;
/*  835 */     do { double d2 = arrayOfDouble[j] - paramDouble;
/*  836 */       d1 += d2 * d2;j--;
/*  834 */     } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  839 */     return d1 / (i - 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double sampleWeightedVariance(double paramDouble1, double paramDouble2, double paramDouble3)
/*      */   {
/*  850 */     return (paramDouble3 - paramDouble2 * paramDouble2 / paramDouble1) / (paramDouble1 - 1.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double skew(double paramDouble1, double paramDouble2)
/*      */   {
/*  858 */     return paramDouble1 / (paramDouble2 * paramDouble2 * paramDouble2);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double skew(DoubleArrayList paramDoubleArrayList, double paramDouble1, double paramDouble2)
/*      */   {
/*  864 */     return skew(moment(paramDoubleArrayList, 3, paramDouble1), paramDouble2);
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
/*      */   public static DoubleArrayList[] split(DoubleArrayList paramDoubleArrayList1, DoubleArrayList paramDoubleArrayList2)
/*      */   {
/*  885 */     int i = paramDoubleArrayList2.size() + 1;
/*      */     
/*  887 */     DoubleArrayList[] arrayOfDoubleArrayList = new DoubleArrayList[i];
/*  888 */     int j = i; do { arrayOfDoubleArrayList[j] = new DoubleArrayList();j--; } while (j >= 0);
/*      */     
/*  890 */     int k = paramDoubleArrayList1.size();
/*  891 */     int m = 0;
/*  892 */     int n = 0;
/*  893 */     while ((m < k) && (n < i - 1)) {
/*  894 */       double d = paramDoubleArrayList2.get(n);
/*  895 */       int i1 = paramDoubleArrayList1.binarySearch(d);
/*  896 */       if (i1 < 0) {
/*  897 */         int i2 = -i1 - 1;
/*  898 */         arrayOfDoubleArrayList[n].addAllOfFromTo(paramDoubleArrayList1, m, i2 - 1);
/*  899 */         m = i2;
/*      */       }
/*      */       else
/*      */       {
/*      */         do
/*      */         {
/*  905 */           i1--;
/*  906 */         } while ((i1 >= 0) && (paramDoubleArrayList1.get(i1) == d));
/*      */         
/*  908 */         arrayOfDoubleArrayList[n].addAllOfFromTo(paramDoubleArrayList1, m, i1);
/*  909 */         m = i1 + 1;
/*      */       }
/*  911 */       n++;
/*      */     }
/*      */     
/*      */ 
/*  915 */     arrayOfDoubleArrayList[(i - 1)].addAllOfFromTo(paramDoubleArrayList1, m, paramDoubleArrayList1.size() - 1);
/*      */     
/*  917 */     return arrayOfDoubleArrayList;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double standardDeviation(double paramDouble)
/*      */   {
/*  923 */     return Math.sqrt(paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double standardError(int paramInt, double paramDouble)
/*      */   {
/*  933 */     return Math.sqrt(paramDouble / paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static void standardize(DoubleArrayList paramDoubleArrayList, double paramDouble1, double paramDouble2)
/*      */   {
/*  940 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*  941 */     int i = paramDoubleArrayList.size(); do { arrayOfDouble[i] = ((arrayOfDouble[i] - paramDouble1) / paramDouble2);i--; } while (i >= 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double sum(DoubleArrayList paramDoubleArrayList)
/*      */   {
/*  948 */     return sumOfPowerDeviations(paramDoubleArrayList, 1, 0.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double sumOfInversions(DoubleArrayList paramDoubleArrayList, int paramInt1, int paramInt2)
/*      */   {
/*  958 */     return sumOfPowerDeviations(paramDoubleArrayList, -1, 0.0D, paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double sumOfLogarithms(DoubleArrayList paramDoubleArrayList, int paramInt1, int paramInt2)
/*      */   {
/*  967 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*  968 */     double d = 0.0D;
/*  969 */     int i = paramInt1 - 1; do { d += Math.log(arrayOfDouble[i]);i++; } while (i <= paramInt2);
/*  970 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double sumOfPowerDeviations(DoubleArrayList paramDoubleArrayList, int paramInt, double paramDouble)
/*      */   {
/*  976 */     return sumOfPowerDeviations(paramDoubleArrayList, paramInt, paramDouble, 0, paramDoubleArrayList.size() - 1);
/*      */   }
/*      */   
/*      */ 
/*      */   public static double sumOfPowerDeviations(DoubleArrayList paramDoubleArrayList, int paramInt1, double paramDouble, int paramInt2, int paramInt3)
/*      */   {
/*  982 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*  983 */     double d1 = 0.0D;
/*      */     int i;
/*      */     double d2;
/*  986 */     switch (paramInt1) {
/*      */     case -2: 
/*  988 */       if (paramDouble == 0.0D) { i = paramInt2 - 1; do { d2 = arrayOfDouble[i];d1 += 1.0D / (d2 * d2);i++; } while (i <= paramInt3);
/*  989 */       } else { i = paramInt2 - 1; do { d2 = arrayOfDouble[i] - paramDouble;d1 += 1.0D / (d2 * d2);i++; } while (i <= paramInt3); }
/*  990 */       break;
/*      */     case -1: 
/*  992 */       if (paramDouble == 0.0D) { i = paramInt2 - 1; do { d1 += 1.0D / arrayOfDouble[i];i++; } while (i <= paramInt3);
/*  993 */       } else { i = paramInt2 - 1; do { d1 += 1.0D / (arrayOfDouble[i] - paramDouble);i++; } while (i <= paramInt3); }
/*  994 */       break;
/*      */     case 0: 
/*  996 */       d1 += paramInt3 - paramInt2 + 1;
/*  997 */       break;
/*      */     case 1: 
/*  999 */       if (paramDouble == 0.0D) { i = paramInt2 - 1; do { d1 += arrayOfDouble[i];i++; } while (i <= paramInt3);
/* 1000 */       } else { i = paramInt2 - 1; do { d1 += arrayOfDouble[i] - paramDouble;i++; } while (i <= paramInt3); }
/* 1001 */       break;
/*      */     case 2: 
/* 1003 */       if (paramDouble == 0.0D) { i = paramInt2 - 1; do { d2 = arrayOfDouble[i];d1 += d2 * d2;i++; } while (i <= paramInt3);
/* 1004 */       } else { i = paramInt2 - 1; do { d2 = arrayOfDouble[i] - paramDouble;d1 += d2 * d2;i++; } while (i <= paramInt3); }
/* 1005 */       break;
/*      */     case 3: 
/* 1007 */       if (paramDouble == 0.0D) { i = paramInt2 - 1; do { d2 = arrayOfDouble[i];d1 += d2 * d2 * d2;i++; } while (i <= paramInt3);
/* 1008 */       } else { i = paramInt2 - 1; do { d2 = arrayOfDouble[i] - paramDouble;d1 += d2 * d2 * d2;i++; } while (i <= paramInt3); }
/* 1009 */       break;
/*      */     case 4: 
/* 1011 */       if (paramDouble == 0.0D) { i = paramInt2 - 1; do { d2 = arrayOfDouble[i];d1 += d2 * d2 * d2 * d2;i++; } while (i <= paramInt3);
/* 1012 */       } else { i = paramInt2 - 1; do { d2 = arrayOfDouble[i] - paramDouble;d1 += d2 * d2 * d2 * d2;i++; } while (i <= paramInt3); }
/* 1013 */       break;
/*      */     case 5: 
/* 1015 */       if (paramDouble == 0.0D) { i = paramInt2 - 1; do { d2 = arrayOfDouble[i];d1 += d2 * d2 * d2 * d2 * d2;i++; } while (i <= paramInt3);
/* 1016 */       } else { i = paramInt2 - 1; do { d2 = arrayOfDouble[i] - paramDouble;d1 += d2 * d2 * d2 * d2 * d2;i++; } while (i <= paramInt3); }
/* 1017 */       break;
/*      */     default: 
/* 1019 */       i = paramInt2 - 1; do { d1 += Math.pow(arrayOfDouble[i] - paramDouble, paramInt1);i++; } while (i <= paramInt3);
/*      */     }
/*      */     
/* 1022 */     return d1;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double sumOfPowers(DoubleArrayList paramDoubleArrayList, int paramInt)
/*      */   {
/* 1028 */     return sumOfPowerDeviations(paramDoubleArrayList, paramInt, 0.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double sumOfSquaredDeviations(int paramInt, double paramDouble)
/*      */   {
/* 1038 */     return paramDouble * (paramInt - 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double sumOfSquares(DoubleArrayList paramDoubleArrayList)
/*      */   {
/* 1045 */     return sumOfPowerDeviations(paramDoubleArrayList, 2, 0.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double trimmedMean(DoubleArrayList paramDoubleArrayList, double paramDouble, int paramInt1, int paramInt2)
/*      */   {
/* 1056 */     int i = paramDoubleArrayList.size();
/* 1057 */     if (i == 0) throw new IllegalArgumentException("Empty data.");
/* 1058 */     if (paramInt1 + paramInt2 >= i) { throw new IllegalArgumentException("Not enough data.");
/*      */     }
/* 1060 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/* 1061 */     int j = i;
/* 1062 */     for (int k = 0; k < paramInt1; k++)
/* 1063 */       paramDouble += (paramDouble - arrayOfDouble[k]) / --i;
/* 1064 */     for (int m = 0; m < paramInt2; m++)
/* 1065 */       paramDouble += (paramDouble - arrayOfDouble[(j - 1 - m)]) / --i;
/* 1066 */     return paramDouble;
/*      */   }
/*      */   
/*      */ 
/*      */   public static double variance(double paramDouble)
/*      */   {
/* 1072 */     return paramDouble * paramDouble;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double variance(int paramInt, double paramDouble1, double paramDouble2)
/*      */   {
/* 1083 */     double d = paramDouble1 / paramInt;
/* 1084 */     return (paramDouble2 - d * paramDouble1) / paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static double weightedMean(DoubleArrayList paramDoubleArrayList1, DoubleArrayList paramDoubleArrayList2)
/*      */   {
/* 1091 */     int i = paramDoubleArrayList1.size();
/* 1092 */     if ((i != paramDoubleArrayList2.size()) || (i == 0)) { throw new IllegalArgumentException();
/*      */     }
/* 1094 */     double[] arrayOfDouble1 = paramDoubleArrayList1.elements();
/* 1095 */     double[] arrayOfDouble2 = paramDoubleArrayList2.elements();
/* 1096 */     double d1 = 0.0D;
/* 1097 */     double d2 = 0.0D;
/* 1098 */     int j = i;
/* 1099 */     do { double d3 = arrayOfDouble2[j];
/* 1100 */       d1 += arrayOfDouble1[j] * d3;
/* 1101 */       d2 += d3;j--;
/* 1098 */     } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1104 */     return d1 / d2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double weightedRMS(double paramDouble1, double paramDouble2)
/*      */   {
/* 1115 */     return paramDouble1 / paramDouble2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static double winsorizedMean(DoubleArrayList paramDoubleArrayList, double paramDouble, int paramInt1, int paramInt2)
/*      */   {
/* 1126 */     int i = paramDoubleArrayList.size();
/* 1127 */     if (i == 0) throw new IllegalArgumentException("Empty data.");
/* 1128 */     if (paramInt1 + paramInt2 >= i) { throw new IllegalArgumentException("Not enough data.");
/*      */     }
/* 1130 */     double[] arrayOfDouble = paramDoubleArrayList.elements();
/*      */     
/* 1132 */     double d1 = arrayOfDouble[paramInt1];
/* 1133 */     for (int j = 0; j < paramInt1; j++) {
/* 1134 */       paramDouble += (d1 - arrayOfDouble[j]) / i;
/*      */     }
/* 1136 */     double d2 = arrayOfDouble[(i - 1 - paramInt2)];
/* 1137 */     for (int k = 0; k < paramInt2; k++) {
/* 1138 */       paramDouble += (d2 - arrayOfDouble[(i - 1 - k)]) / i;
/*      */     }
/* 1140 */     return paramDouble;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/Descriptive.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */