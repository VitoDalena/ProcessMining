/*     */ package cern.jet.stat.quantile;
/*     */ 
/*     */ import cern.colt.Timer;
/*     */ import cern.colt.list.AbstractDoubleList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.jet.stat.Descriptive;
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
/*     */ class QuantileFinderTest
/*     */ {
/*     */   protected static IntArrayList binaryMultiSearch(DoubleArrayList paramDoubleArrayList, double paramDouble)
/*     */   {
/*  25 */     int i = paramDoubleArrayList.binarySearch(paramDouble);
/*  26 */     if (i < 0) { return null;
/*     */     }
/*  28 */     int j = i - 1;
/*  29 */     while ((j >= 0) && (paramDoubleArrayList.get(j) == paramDouble)) j--;
/*  30 */     j++;
/*     */     
/*  32 */     int k = i + 1;
/*  33 */     while ((k < paramDoubleArrayList.size()) && (paramDoubleArrayList.get(k) == paramDouble)) k++;
/*  34 */     k--;
/*     */     
/*  36 */     return new IntArrayList(new int[] { j, k });
/*     */   }
/*     */   
/*     */ 
/*     */   public static double epsilon(int paramInt, double paramDouble1, double paramDouble2)
/*     */   {
/*  42 */     double d = paramInt;
/*     */     
/*     */ 
/*     */ 
/*  46 */     return Math.abs(paramDouble2 / d - paramDouble1);
/*     */   }
/*     */   
/*     */ 
/*     */   public static double epsilon(DoubleArrayList paramDoubleArrayList, double paramDouble1, double paramDouble2)
/*     */   {
/*  52 */     double d = Descriptive.rankInterpolated(paramDoubleArrayList, paramDouble2);
/*  53 */     return epsilon(paramDoubleArrayList.size(), paramDouble1, d);
/*     */   }
/*     */   
/*     */ 
/*     */   public static double epsilon(DoubleArrayList paramDoubleArrayList, DoubleQuantileFinder paramDoubleQuantileFinder, double paramDouble)
/*     */   {
/*  59 */     double d = paramDoubleQuantileFinder.quantileElements(new DoubleArrayList(new double[] { paramDouble })).get(0);
/*  60 */     return epsilon(paramDoubleArrayList, paramDouble, d);
/*     */   }
/*     */   
/*  63 */   public static void main(String[] paramArrayOfString) { testBestBandKCalculation(paramArrayOfString); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double observedEpsilonAtPhi(double paramDouble, ExactDoubleQuantileFinder paramExactDoubleQuantileFinder, DoubleQuantileFinder paramDoubleQuantileFinder)
/*     */   {
/*  74 */     int i = (int)paramExactDoubleQuantileFinder.size();
/*     */     
/*  76 */     int j = (int)Utils.epsilonCeiling(paramDouble * i) - 1;
/*     */     
/*  78 */     paramExactDoubleQuantileFinder.quantileElements(new DoubleArrayList(new double[] { paramDouble })).get(0);
/*  79 */     double d1 = paramDoubleQuantileFinder.quantileElements(new DoubleArrayList(new double[] { paramDouble })).get(0);
/*     */     
/*  81 */     IntArrayList localIntArrayList = binaryMultiSearch(paramExactDoubleQuantileFinder.buffer, d1);
/*  82 */     int k = localIntArrayList.get(0);
/*  83 */     int m = localIntArrayList.get(1);
/*     */     
/*     */     int n;
/*  86 */     if ((k <= j) && (j <= m)) { n = 0;
/*     */     }
/*  88 */     else if (k > j) n = Math.abs(k - j); else {
/*  89 */       n = Math.abs(j - m);
/*     */     }
/*     */     
/*  92 */     double d2 = n / i;
/*  93 */     return d2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static DoubleArrayList observedEpsilonsAtPhis(DoubleArrayList paramDoubleArrayList, ExactDoubleQuantileFinder paramExactDoubleQuantileFinder, DoubleQuantileFinder paramDoubleQuantileFinder, double paramDouble)
/*     */   {
/* 102 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(paramDoubleArrayList.size());
/*     */     
/* 104 */     int i = paramDoubleArrayList.size();
/* 105 */     do { double d = observedEpsilonAtPhi(paramDoubleArrayList.get(i), paramExactDoubleQuantileFinder, paramDoubleQuantileFinder);
/* 106 */       localDoubleArrayList.add(d);
/* 107 */       if (d > paramDouble) System.out.println("Real epsilon = " + d + " is larger than desired by " + (d - paramDouble));
/* 104 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 109 */     return localDoubleArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void test()
/*     */   {
/* 115 */     String[] arrayOfString = new String[20];
/*     */     
/* 117 */     String str1 = "10000";
/* 118 */     arrayOfString[0] = str1;
/*     */     
/*     */ 
/* 121 */     String str2 = "12";
/* 122 */     arrayOfString[1] = str2;
/*     */     
/* 124 */     String str3 = "2290";
/* 125 */     arrayOfString[2] = str3;
/*     */     
/* 127 */     String str4 = "log";
/* 128 */     arrayOfString[3] = str4;
/*     */     
/* 130 */     String str5 = "10";
/* 131 */     arrayOfString[4] = str5;
/*     */     
/* 133 */     String str6 = "exact";
/* 134 */     arrayOfString[5] = str6;
/*     */     
/* 136 */     String str7 = "shuffle";
/* 137 */     arrayOfString[6] = str7;
/*     */     
/* 139 */     String str8 = "0.001";
/* 140 */     arrayOfString[7] = str8;
/*     */     
/* 142 */     String str9 = "0.0001";
/*     */     
/* 144 */     arrayOfString[8] = str9;
/*     */     
/* 146 */     String str10 = "1";
/* 147 */     arrayOfString[9] = str10;
/*     */     
/* 149 */     String str11 = "-1";
/* 150 */     arrayOfString[10] = str11;
/*     */     
/*     */ 
/* 153 */     testQuantileCalculation(arrayOfString);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void testBestBandKCalculation(String[] paramArrayOfString)
/*     */   {
/* 163 */     int[] arrayOfInt = { 100, 10000 };
/*     */     
/*     */ 
/* 166 */     long[] arrayOfLong = { Long.MAX_VALUE, 1000000L, 10000000L, 100000000L };
/*     */     
/* 168 */     double[] arrayOfDouble1 = { 0.0D, 0.1D, 1.0E-5D };
/*     */     
/*     */ 
/*     */ 
/* 172 */     double[] arrayOfDouble2 = { 0.0D, 0.1D, 0.01D, 0.001D, 1.0E-4D, 1.0E-5D, 1.0E-6D };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 177 */     System.out.println("\n\n");
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 182 */     System.out.println("mem [Math.round(elements/1000.0)]");
/* 183 */     System.out.println("***********************************");
/* 184 */     Timer localTimer = new Timer().start();
/*     */     
/* 186 */     for (int i = 0; i < arrayOfInt.length; i++) {
/* 187 */       int j = arrayOfInt[i];
/* 188 */       System.out.println("------------------------------");
/* 189 */       System.out.println("computing for p = " + j);
/* 190 */       for (int k = 0; k < arrayOfLong.length; k++) {
/* 191 */         long l1 = arrayOfLong[k];
/* 192 */         System.out.println("   ------------------------------");
/* 193 */         System.out.println("   computing for N = " + l1);
/* 194 */         for (int m = 0; m < arrayOfDouble2.length; m++) {
/* 195 */           double d1 = arrayOfDouble2[m];
/* 196 */           System.out.println("      ------------------------------");
/* 197 */           System.out.println("      computing for e = " + d1);
/* 198 */           for (int n = 0; n < arrayOfDouble1.length; n++) {
/* 199 */             double d2 = arrayOfDouble1[n];
/* 200 */             for (int i1 = 0; i1 < 2; i1++) {
/*     */               boolean bool;
/* 202 */               if (i1 == 0) bool = true; else {
/* 203 */                 bool = false;
/*     */               }
/* 205 */               DoubleQuantileFinder localDoubleQuantileFinder = QuantileFinderFactory.newDoubleQuantileFinder(bool, l1, d1, d2, j, null);
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
/* 233 */               String str = bool ? "  known" : "unknown";
/* 234 */               long l2 = localDoubleQuantileFinder.totalMemory();
/* 235 */               if (l2 == 0L) { l2 = l1;
/*     */               }
/*     */               
/*     */ 
/* 239 */               System.out.print("         (known, d)=(" + str + ", " + d2 + ") --> ");
/*     */               
/* 241 */               System.out.print("(MB,mem");
/*     */               
/*     */ 
/*     */ 
/* 245 */               System.out.print(")=(" + l2 * 8.0D / 1024.0D / 1024.0D + ",  " + l2 / 1000.0D + ",  " + Math.round(l2 * 8.0D / 1024.0D / 1024.0D));
/*     */               
/* 247 */               System.out.println(")");
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 254 */     localTimer.stop().display();
/*     */   }
/*     */   
/*     */ 
/*     */   public static void testLocalVarDeclarationSpeed(int paramInt)
/*     */   {
/* 260 */     System.out.println("free=" + Runtime.getRuntime().freeMemory());
/* 261 */     System.out.println("total=" + Runtime.getRuntime().totalMemory());
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
/* 274 */     Timer localTimer = new Timer().start();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 280 */     for (int k = 0; k < paramInt; k++) {
/* 281 */       for (int j = 0; j < paramInt; j++) {
/* 282 */         Object localObject = null;
/* 283 */         int i = 10;
/* 284 */         double d = 1.0D;
/*     */       }
/*     */     }
/* 287 */     System.out.println(localTimer.stop());
/*     */     
/* 289 */     System.out.println("free=" + Runtime.getRuntime().freeMemory());
/* 290 */     System.out.println("total=" + Runtime.getRuntime().totalMemory());
/*     */   }
/*     */   
/*     */   public static void testQuantileCalculation(String[] paramArrayOfString)
/*     */   {
/* 295 */     int i = Integer.parseInt(paramArrayOfString[0]);
/* 296 */     int j = Integer.parseInt(paramArrayOfString[1]);
/* 297 */     int k = Integer.parseInt(paramArrayOfString[2]);
/*     */     
/* 299 */     int m = Integer.parseInt(paramArrayOfString[4]);
/* 300 */     boolean bool1 = paramArrayOfString[5].equals("exact");
/* 301 */     boolean bool2 = paramArrayOfString[6].equals("shuffle");
/* 302 */     double d1 = new Double(paramArrayOfString[7]).doubleValue();
/* 303 */     double d2 = new Double(paramArrayOfString[8]).doubleValue();
/* 304 */     int n = Integer.parseInt(paramArrayOfString[9]);
/* 305 */     long l = Long.parseLong(paramArrayOfString[10]);
/*     */     
/*     */ 
/*     */ 
/* 309 */     System.out.println("free=" + Runtime.getRuntime().freeMemory());
/* 310 */     System.out.println("total=" + Runtime.getRuntime().totalMemory());
/*     */     
/* 312 */     double[] arrayOfDouble = { 0.001D, 0.01D, 0.1D, 0.5D, 0.9D, 0.99D, 0.999D, 1.0D };
/*     */     
/*     */ 
/* 315 */     Timer localTimer1 = new Timer();
/* 316 */     Timer localTimer2 = new Timer();
/*     */     
/*     */ 
/* 319 */     DoubleQuantileFinder localDoubleQuantileFinder1 = QuantileFinderFactory.newDoubleQuantileFinder(false, l, d1, d2, n, null);
/* 320 */     System.out.println(localDoubleQuantileFinder1);
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
/* 333 */     DoubleQuantileFinder localDoubleQuantileFinder2 = QuantileFinderFactory.newDoubleQuantileFinder(false, -1L, 0.0D, d2, n, null);
/* 334 */     System.out.println(localDoubleQuantileFinder2);
/*     */     
/* 336 */     DoubleArrayList localDoubleArrayList1 = new DoubleArrayList(i);
/*     */     Object localObject;
/* 338 */     for (int i1 = 0; i1 < m; i1++) {
/* 339 */       localDoubleArrayList1.setSize(0);
/* 340 */       int i2 = i1 * i;
/* 341 */       localTimer2.start();
/* 342 */       for (int i3 = 0; i3 < i; i3++) {
/* 343 */         localDoubleArrayList1.add(i3 + i2);
/*     */       }
/* 345 */       localTimer2.stop();
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 350 */       if (bool2) {
/* 351 */         localObject = new Timer().start();
/* 352 */         localDoubleArrayList1.shuffle();
/* 353 */         System.out.println("shuffling took ");
/* 354 */         ((Timer)localObject).stop().display();
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 360 */       localTimer1.start();
/* 361 */       localDoubleQuantileFinder1.addAllOf(localDoubleArrayList1);
/* 362 */       localTimer1.stop();
/*     */       
/* 364 */       if (bool1) {
/* 365 */         localDoubleQuantileFinder2.addAllOf(localDoubleArrayList1);
/*     */       }
/*     */     }
/*     */     
/* 369 */     System.out.println("list.add() took" + localTimer2);
/* 370 */     System.out.println("approxFinder.add() took" + localTimer1);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 375 */     localTimer1.reset().start();
/*     */     
/*     */ 
/* 378 */     DoubleArrayList localDoubleArrayList2 = localDoubleQuantileFinder1.quantileElements(new DoubleArrayList(arrayOfDouble));
/*     */     
/* 380 */     localTimer1.stop().display();
/*     */     
/* 382 */     System.out.println("Phis=" + new DoubleArrayList(arrayOfDouble));
/* 383 */     System.out.println("ApproxQuantiles=" + localDoubleArrayList2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 392 */     if (bool1) {
/* 393 */       System.out.println("Comparing with exact quantile computation...");
/*     */       
/* 395 */       localTimer1.reset().start();
/*     */       
/*     */ 
/* 398 */       DoubleArrayList localDoubleArrayList3 = localDoubleQuantileFinder2.quantileElements(new DoubleArrayList(arrayOfDouble));
/* 399 */       localTimer1.stop().display();
/*     */       
/* 401 */       System.out.println("ExactQuantiles=" + localDoubleArrayList3);
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
/* 419 */       localObject = observedEpsilonsAtPhis(new DoubleArrayList(arrayOfDouble), (ExactDoubleQuantileFinder)localDoubleQuantileFinder2, localDoubleQuantileFinder1, d1);
/* 420 */       System.out.println("observedEpsilons=" + localObject);
/*     */       
/* 422 */       double d3 = 1000.0D;
/*     */       
/*     */ 
/* 425 */       System.out.println("exact phi(" + d3 + ")=" + localDoubleQuantileFinder2.phi(d3));
/* 426 */       System.out.println("apprx phi(" + d3 + ")=" + localDoubleQuantileFinder1.phi(d3));
/*     */       
/* 428 */       System.out.println("exact elem(phi(" + d3 + "))=" + localDoubleQuantileFinder2.quantileElements(new DoubleArrayList(new double[] { localDoubleQuantileFinder2.phi(d3) })));
/* 429 */       System.out.println("apprx elem(phi(" + d3 + "))=" + localDoubleQuantileFinder1.quantileElements(new DoubleArrayList(new double[] { localDoubleQuantileFinder1.phi(d3) })));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void testRank()
/*     */   {
/* 436 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(new double[] { 1.0D, 5.0D, 5.0D, 5.0D, 7.0D, 10.0D });
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/QuantileFinderTest.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */