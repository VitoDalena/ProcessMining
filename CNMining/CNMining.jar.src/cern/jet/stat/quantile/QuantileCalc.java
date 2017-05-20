/*     */ package cern.jet.stat.quantile;
/*     */ 
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
/*     */ class QuantileCalc
/*     */ {
/*     */   public static double binomial(long paramLong1, long paramLong2)
/*     */   {
/*  22 */     if ((paramLong2 == 0L) || (paramLong2 == paramLong1)) { return 1.0D;
/*     */     }
/*     */     
/*     */ 
/*  26 */     if (paramLong2 > paramLong1 / 2.0D) { paramLong2 = paramLong1 - paramLong2;
/*     */     }
/*  28 */     double d = 1.0D;
/*  29 */     long l1 = paramLong1 - paramLong2 + 1L;
/*  30 */     for (long l2 = paramLong2; l2 > 0L;) {
/*  31 */       d *= l1++ / l2--;
/*     */     }
/*  33 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long ceiling(double paramDouble)
/*     */   {
/*  41 */     return Math.round(Math.ceil(paramDouble));
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
/*     */   public static long[] known_N_compute_B_and_K(long paramLong, double paramDouble1, double paramDouble2, int paramInt, double[] paramArrayOfDouble)
/*     */   {
/*  58 */     if (paramDouble2 > 0.0D) {
/*  59 */       return known_N_compute_B_and_K_slow(paramLong, paramDouble1, paramDouble2, paramInt, paramArrayOfDouble);
/*     */     }
/*  61 */     paramArrayOfDouble[0] = 1.0D;
/*  62 */     return known_N_compute_B_and_K_quick(paramLong, paramDouble1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static long[] known_N_compute_B_and_K_quick(long paramLong, double paramDouble)
/*     */   {
/*  73 */     if (paramDouble <= 0.0D)
/*     */     {
/*  75 */       long[] arrayOfLong1 = new long[2];
/*  76 */       arrayOfLong1[0] = 1L;
/*  77 */       arrayOfLong1[1] = paramLong;
/*  78 */       return arrayOfLong1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  83 */     double d1 = paramLong;
/*  84 */     double d2 = d1 * paramDouble * 2.0D;
/*  85 */     int[] arrayOfInt = new int[49];
/*     */     
/*     */ 
/*     */ 
/*  89 */     for (int i = 2; i <= 50; i++) {
/*  90 */       int j = 3;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       do
/*     */       {
/*  97 */         j++;
/*  96 */         if (j > 50) break; } while ((j - 2) * Math.round(binomial(i + j - 2, j - 1)) - Math.round(binomial(i + j - 3, j - 3)) + Math.round(binomial(i + j - 3, j - 2)) - d2 > 0.0D);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 103 */       while ((j <= 50) && ((j - 2) * Math.round(binomial(i + j - 2, j - 1)) - Math.round(binomial(i + j - 3, j - 3)) + Math.round(binomial(i + j - 3, j - 2)) - d2 <= 0.0D))
/* 104 */         j++;
/* 105 */       j--;
/*     */       
/*     */ 
/*     */ 
/* 109 */       if ((j >= 50) && ((j - 2) * Math.round(binomial(i + j - 2, j - 1)) - Math.round(binomial(i + j - 3, j - 3)) + Math.round(binomial(i + j - 3, j - 2)) - d2 > 0.0D))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 114 */         k = Integer.MIN_VALUE;
/*     */       }
/*     */       else {
/* 117 */         k = j;
/*     */       }
/*     */       
/* 120 */       arrayOfInt[(i - 2)] = k;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 126 */     long[] arrayOfLong2 = new long[49];
/* 127 */     for (int k = 2; k <= 50; k++) {
/* 128 */       int m = arrayOfInt[(k - 2)];
/* 129 */       long l2 = Long.MAX_VALUE;
/* 130 */       if (m > Integer.MIN_VALUE) {
/* 131 */         double d3 = Math.round(binomial(k + m - 2, m - 1));
/* 132 */         long l4 = ceiling(d1 / d3);
/* 133 */         if (l4 <= Long.MAX_VALUE) {
/* 134 */           l2 = l4;
/*     */         }
/*     */       }
/* 137 */       arrayOfLong2[(k - 2)] = l2;
/*     */     }
/*     */     
/*     */ 
/* 141 */     long l1 = Long.MAX_VALUE;
/* 142 */     int n = -1;
/* 143 */     long l3; for (int i1 = 2; i1 <= 50; i1++) {
/* 144 */       if (arrayOfLong2[(i1 - 2)] < Long.MAX_VALUE) {
/* 145 */         l3 = i1 * arrayOfLong2[(i1 - 2)];
/* 146 */         if (l3 < l1) {
/* 147 */           l1 = l3;
/* 148 */           n = i1;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     long l5;
/* 154 */     if (n != -1) {
/* 155 */       l3 = n;
/* 156 */       l5 = arrayOfLong2[(n - 2)];
/*     */     }
/*     */     else {
/* 159 */       l3 = 1L;
/* 160 */       l5 = paramLong;
/*     */     }
/*     */     
/* 163 */     long[] arrayOfLong3 = new long[2];
/* 164 */     arrayOfLong3[0] = l3;
/* 165 */     arrayOfLong3[1] = l5;
/* 166 */     return arrayOfLong3;
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
/*     */   protected static long[] known_N_compute_B_and_K_slow(long paramLong, double paramDouble1, double paramDouble2, int paramInt, double[] paramArrayOfDouble)
/*     */   {
/* 182 */     if (paramDouble1 <= 0.0D)
/*     */     {
/* 184 */       long[] arrayOfLong1 = new long[2];
/* 185 */       arrayOfLong1[0] = 1L;
/* 186 */       arrayOfLong1[1] = paramLong;
/* 187 */       paramArrayOfDouble[0] = 1.0D;
/* 188 */       return arrayOfLong1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 194 */     double d1 = paramLong;
/*     */     
/*     */ 
/*     */ 
/* 198 */     long l1 = 1L;
/* 199 */     long l2 = paramLong;
/* 200 */     double d2 = 1.0D;
/* 201 */     long l3 = paramLong;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 210 */     double d3 = Math.log(2.0D * paramInt / paramDouble2);
/* 211 */     double d4 = 2.0D * paramDouble1 * d1;
/* 212 */     for (long l4 = 2L; l4 < 50L; l4 += 1L) {
/* 213 */       for (long l5 = 3L; l5 < 50L; l5 += 1L) {
/* 214 */         double d5 = binomial(l4 + l5 - 2L, l5 - 1L);
/* 215 */         long l6 = ceiling(d1 / d5);
/* 216 */         if ((l4 * l6 < l3) && ((l5 - 2L) * d5 - binomial(l4 + l5 - 3L, l5 - 3L) + binomial(l4 + l5 - 3L, l5 - 2L) <= d4))
/*     */         {
/*     */ 
/* 219 */           l2 = l6;
/* 220 */           l1 = l4;
/* 221 */           l3 = l2 * l4;
/* 222 */           d2 = 1.0D;
/*     */         }
/* 224 */         if (paramDouble2 > 0.0D) {
/* 225 */           double d6 = (l5 - 2L) * binomial(l4 + l5 - 2L, l5 - 1L) - binomial(l4 + l5 - 3L, l5 - 3L) + binomial(l4 + l5 - 3L, l5 - 2L);
/* 226 */           double d7 = d3 / paramDouble1;
/* 227 */           double d8 = binomial(l4 + l5 - 2L, l5 - 1L);
/* 228 */           double d9 = d3 / (2.0D * paramDouble1 * paramDouble1);
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
/* 241 */           double d10 = 0.5D + 0.5D * Math.sqrt(1.0D + 4.0D * d6 / d7);
/* 242 */           long l7 = ceiling(d9 * d10 * d10 / d8);
/* 243 */           if (l4 * l7 < l3) {
/* 244 */             l2 = l7;
/* 245 */             l1 = l4;
/* 246 */             l3 = l4 * l7;
/* 247 */             d2 = d1 * 2.0D * paramDouble1 * paramDouble1 / d3;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 252 */     long[] arrayOfLong2 = new long[2];
/* 253 */     arrayOfLong2[0] = l1;
/* 254 */     arrayOfLong2[1] = l2;
/* 255 */     paramArrayOfDouble[0] = d2;
/* 256 */     return arrayOfLong2;
/*     */   }
/*     */   
/* 259 */   public static void main(String[] paramArrayOfString) { test_B_and_K_Calculation(paramArrayOfString); }
/*     */   
/*     */ 
/*     */   public static void test_B_and_K_Calculation(String[] paramArrayOfString)
/*     */   {
/*     */     boolean bool;
/*     */     
/* 266 */     if (paramArrayOfString == null) bool = false; else {
/* 267 */       bool = new Boolean(paramArrayOfString[0]).booleanValue();
/*     */     }
/* 269 */     int[] arrayOfInt = { 1, 1000 };
/*     */     
/* 271 */     long[] arrayOfLong1 = { 100000L, 1000000L, 10000000L, 1000000000L };
/*     */     
/* 273 */     double[] arrayOfDouble1 = { 0.0D, 0.001D, 1.0E-4D, 1.0E-5D };
/*     */     
/* 275 */     double[] arrayOfDouble2 = { 0.0D, 0.1D, 0.05D, 0.01D, 0.005D, 0.001D, 1.0E-7D };
/*     */     
/*     */ 
/*     */ 
/* 279 */     if (!bool) arrayOfLong1 = new long[] { 0L };
/* 280 */     System.out.println("\n\n");
/* 281 */     if (bool) {
/* 282 */       System.out.println("Computing b's and k's for KNOWN N");
/*     */     } else
/* 284 */       System.out.println("Computing b's and k's for UNKNOWN N");
/* 285 */     System.out.println("mem [elements/1024]");
/* 286 */     System.out.println("***********************************");
/*     */     
/* 288 */     for (int i = 0; i < arrayOfInt.length; i++) {
/* 289 */       int j = arrayOfInt[i];
/* 290 */       System.out.println("------------------------------");
/* 291 */       System.out.println("computing for p = " + j);
/* 292 */       for (int k = 0; k < arrayOfLong1.length; k++) {
/* 293 */         long l1 = arrayOfLong1[k];
/* 294 */         System.out.println("   ------------------------------");
/* 295 */         System.out.println("   computing for N = " + l1);
/* 296 */         for (int m = 0; m < arrayOfDouble1.length; m++) {
/* 297 */           double d1 = arrayOfDouble1[m];
/* 298 */           System.out.println("      ------------------------------");
/* 299 */           System.out.println("      computing for delta = " + d1);
/* 300 */           for (int n = 0; n < arrayOfDouble2.length; n++) {
/* 301 */             double d2 = arrayOfDouble2[n];
/*     */             
/* 303 */             double[] arrayOfDouble3 = new double[1];
/*     */             long[] arrayOfLong2;
/* 305 */             if (bool) {
/* 306 */               arrayOfLong2 = known_N_compute_B_and_K(l1, d2, d1, j, arrayOfDouble3);
/*     */             }
/*     */             else {
/* 309 */               arrayOfLong2 = unknown_N_compute_B_and_K(d2, d1, j);
/*     */             }
/*     */             
/* 312 */             long l2 = arrayOfLong2[0];
/* 313 */             long l3 = arrayOfLong2[1];
/* 314 */             System.out.print("         (e,d,N,p)=(" + d2 + "," + d1 + "," + l1 + "," + j + ") --> ");
/* 315 */             System.out.print("(b,k,mem");
/* 316 */             if (bool) System.out.print(",sampling");
/* 317 */             System.out.print(")=(" + l2 + "," + l3 + "," + l2 * l3 / 1024L);
/* 318 */             if (bool) System.out.print("," + arrayOfDouble3[0]);
/* 319 */             System.out.println(")");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
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
/*     */   public static long[] unknown_N_compute_B_and_K(double paramDouble1, double paramDouble2, int paramInt)
/*     */   {
/* 337 */     if ((paramDouble1 <= 0.0D) || (paramDouble2 <= 0.0D))
/*     */     {
/* 339 */       long[] arrayOfLong1 = new long[3];
/* 340 */       arrayOfLong1[0] = 1L;
/* 341 */       arrayOfLong1[1] = Long.MAX_VALUE;
/* 342 */       arrayOfLong1[2] = Long.MAX_VALUE;
/* 343 */       return arrayOfLong1;
/*     */     }
/*     */     
/* 346 */     int i = 50;
/* 347 */     int j = 50;
/* 348 */     int k = 50;
/* 349 */     int m = 2;
/*     */     
/* 351 */     long l1 = Long.MAX_VALUE;
/* 352 */     long l2 = Long.MAX_VALUE;
/* 353 */     long l3 = Long.MAX_VALUE;
/* 354 */     long l4 = Long.MAX_VALUE;
/*     */     
/* 356 */     double d1 = Math.pow(2.0D, k);
/* 357 */     double d2 = Math.log(2.0D / (paramDouble2 / paramInt)) / (2.0D * paramDouble1 * paramDouble1);
/*     */     
/*     */ 
/* 360 */     while ((l1 == Long.MAX_VALUE) && (m-- > 0))
/*     */     {
/*     */ 
/* 363 */       for (int n = 2; n <= i; n++) {
/* 364 */         for (int i1 = 2; i1 <= j; i1++) {
/* 365 */           double d3 = binomial(n + i1 - 2, i1 - 1);
/* 366 */           double d4 = binomial(n + i1 - 3, i1 - 1);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 371 */           double d5 = d2 / Math.min(d3, 8.0D * d4 / 3.0D);
/*     */           
/*     */ 
/*     */ 
/* 375 */           double d6 = d3 / d4;
/* 376 */           double d7 = (d6 - 2.0D) * (k - 2.0D) / (d6 + d1 - 2.0D);
/* 377 */           double d8 = (i1 + 3 + d7) / (2.0D * paramDouble1);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 387 */           double d9 = d5 * d5 + 4.0D * d5 * d8;
/* 388 */           if (d9 >= 0.0D) {
/* 389 */             double d10 = Math.sqrt(d9);
/* 390 */             double d11 = (d5 + 2.0D * d8 + d10) / (2.0D * d8);
/* 391 */             double d12 = (d5 + 2.0D * d8 - d10) / (2.0D * d8);
/*     */             
/*     */ 
/* 394 */             int i2 = 0;
/* 395 */             int i3 = 0;
/* 396 */             if ((0.0D < d11) && (d11 < 1.0D)) i2 = 1;
/* 397 */             if ((0.0D < d12) && (d12 < 1.0D)) i3 = 1;
/* 398 */             if ((i2 != 0) || (i3 != 0)) {
/* 399 */               double d13 = d11;
/* 400 */               if ((i2 != 0) && (i3 != 0))
/*     */               {
/* 402 */                 d13 = Math.max(d11, d12);
/*     */               }
/* 404 */               else if (i3 != 0) {
/* 405 */                 d13 = d12;
/*     */               }
/*     */               
/*     */ 
/* 409 */               long l5 = ceiling(Math.max(d8 / d13, (i1 + 1) / (2.0D * paramDouble1)));
/* 410 */               if (l5 > 0L) {
/* 411 */                 long l6 = n * l5;
/* 412 */                 if (l6 < l4)
/*     */                 {
/* 414 */                   l2 = l5;
/* 415 */                   l1 = n;
/* 416 */                   l3 = i1;
/* 417 */                   l4 = l6;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 424 */       if (l1 == Long.MAX_VALUE) {
/* 425 */         System.out.println("Warning: Computing b and k looks like a lot of work!");
/*     */         
/* 427 */         i *= 2;
/* 428 */         j *= 2;
/* 429 */         k *= 2;
/*     */       }
/*     */     }
/*     */     
/* 433 */     long[] arrayOfLong2 = new long[3];
/* 434 */     if (l1 == Long.MAX_VALUE)
/*     */     {
/*     */ 
/* 437 */       arrayOfLong2[0] = 1L;
/* 438 */       arrayOfLong2[1] = Long.MAX_VALUE;
/* 439 */       arrayOfLong2[2] = Long.MAX_VALUE;
/*     */     }
/*     */     else {
/* 442 */       arrayOfLong2[0] = l1;
/* 443 */       arrayOfLong2[1] = l2;
/* 444 */       arrayOfLong2[2] = l3;
/*     */     }
/*     */     
/* 447 */     return arrayOfLong2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/QuantileCalc.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */