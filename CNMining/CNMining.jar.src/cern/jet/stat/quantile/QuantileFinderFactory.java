/*     */ package cern.jet.stat.quantile;
/*     */ 
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.jet.math.Arithmetic;
/*     */ import edu.cornell.lassp.houle.RngPack.RandomElement;
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
/*     */ public class QuantileFinderFactory
/*     */ {
/*     */   public static long[] known_N_compute_B_and_K(long paramLong, double paramDouble1, double paramDouble2, int paramInt, double[] paramArrayOfDouble)
/*     */   {
/* 112 */     paramArrayOfDouble[0] = 1.0D;
/* 113 */     long[] arrayOfLong; if (paramDouble1 <= 0.0D)
/*     */     {
/* 115 */       arrayOfLong = new long[2];
/* 116 */       arrayOfLong[0] = 1L;
/* 117 */       arrayOfLong[1] = paramLong;
/* 118 */       return arrayOfLong;
/*     */     }
/* 120 */     if ((paramDouble1 >= 1.0D) || (paramDouble2 >= 1.0D))
/*     */     {
/* 122 */       arrayOfLong = new long[2];
/* 123 */       arrayOfLong[0] = 2L;
/* 124 */       arrayOfLong[1] = 1L;
/* 125 */       return arrayOfLong;
/*     */     }
/*     */     
/* 128 */     if (paramDouble2 > 0.0D) {
/* 129 */       return known_N_compute_B_and_K_slow(paramLong, paramDouble1, paramDouble2, paramInt, paramArrayOfDouble);
/*     */     }
/* 131 */     return known_N_compute_B_and_K_quick(paramLong, paramDouble1);
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
/*     */   protected static long[] known_N_compute_B_and_K_quick(long paramLong, double paramDouble)
/*     */   {
/* 144 */     double d1 = paramLong;
/* 145 */     double d2 = d1 * paramDouble * 2.0D;
/* 146 */     int[] arrayOfInt = new int[49];
/*     */     
/*     */ 
/*     */ 
/* 150 */     for (int i = 2; i <= 50; i++) {
/* 151 */       int j = 3;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */       do
/*     */       {
/* 158 */         j++;
/* 157 */         if (j > 50) break; } while ((j - 2) * Arithmetic.binomial(i + j - 2, j - 1) - Arithmetic.binomial(i + j - 3, j - 3) + Arithmetic.binomial(i + j - 3, j - 2) - d2 > 0.0D);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 164 */       while ((j <= 50) && ((j - 2) * Arithmetic.binomial(i + j - 2, j - 1) - Arithmetic.binomial(i + j - 3, j - 3) + Arithmetic.binomial(i + j - 3, j - 2) - d2 <= 0.0D))
/* 165 */         j++;
/* 166 */       j--;
/*     */       
/*     */ 
/*     */ 
/* 170 */       if ((j >= 50) && ((j - 2) * Arithmetic.binomial(i + j - 2, j - 1) - Arithmetic.binomial(i + j - 3, j - 3) + Arithmetic.binomial(i + j - 3, j - 2) - d2 > 0.0D))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 175 */         k = Integer.MIN_VALUE;
/*     */       }
/*     */       else {
/* 178 */         k = j;
/*     */       }
/*     */       
/* 181 */       arrayOfInt[(i - 2)] = k;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 187 */     long[] arrayOfLong1 = new long[49];
/* 188 */     for (int k = 2; k <= 50; k++) {
/* 189 */       int m = arrayOfInt[(k - 2)];
/* 190 */       long l2 = Long.MAX_VALUE;
/* 191 */       if (m > Integer.MIN_VALUE) {
/* 192 */         double d3 = Arithmetic.binomial(k + m - 2, m - 1);
/* 193 */         long l4 = Math.ceil(d1 / d3);
/* 194 */         if (l4 <= Long.MAX_VALUE) {
/* 195 */           l2 = l4;
/*     */         }
/*     */       }
/* 198 */       arrayOfLong1[(k - 2)] = l2;
/*     */     }
/*     */     
/*     */ 
/* 202 */     long l1 = Long.MAX_VALUE;
/* 203 */     int n = -1;
/* 204 */     long l3; for (int i1 = 2; i1 <= 50; i1++) {
/* 205 */       if (arrayOfLong1[(i1 - 2)] < Long.MAX_VALUE) {
/* 206 */         l3 = i1 * arrayOfLong1[(i1 - 2)];
/* 207 */         if (l3 < l1) {
/* 208 */           l1 = l3;
/* 209 */           n = i1;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     long l5;
/* 215 */     if (n != -1) {
/* 216 */       l3 = n;
/* 217 */       l5 = arrayOfLong1[(n - 2)];
/*     */     }
/*     */     else {
/* 220 */       l3 = 1L;
/* 221 */       l5 = paramLong;
/*     */     }
/*     */     
/* 224 */     long[] arrayOfLong2 = new long[2];
/* 225 */     arrayOfLong2[0] = l3;
/* 226 */     arrayOfLong2[1] = l5;
/* 227 */     return arrayOfLong2;
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
/*     */   protected static long[] known_N_compute_B_and_K_slow(long paramLong, double paramDouble1, double paramDouble2, int paramInt, double[] paramArrayOfDouble)
/*     */   {
/* 244 */     double d1 = paramLong;
/*     */     
/*     */ 
/*     */ 
/* 248 */     long l1 = 1L;
/* 249 */     long l2 = paramLong;
/* 250 */     double d2 = 1.0D;
/* 251 */     long l3 = paramLong;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 260 */     double d3 = Math.log(2.0D * paramInt / paramDouble2);
/* 261 */     double d4 = 2.0D * paramDouble1 * d1;
/* 262 */     for (long l4 = 2L; l4 < 50L; l4 += 1L) {
/* 263 */       for (long l5 = 3L; l5 < 50L; l5 += 1L) {
/* 264 */         double d5 = Arithmetic.binomial(l4 + l5 - 2L, l5 - 1L);
/* 265 */         long l6 = Math.ceil(d1 / d5);
/* 266 */         if ((l4 * l6 < l3) && ((l5 - 2L) * d5 - Arithmetic.binomial(l4 + l5 - 3L, l5 - 3L) + Arithmetic.binomial(l4 + l5 - 3L, l5 - 2L) <= d4))
/*     */         {
/*     */ 
/* 269 */           l2 = l6;
/* 270 */           l1 = l4;
/* 271 */           l3 = l2 * l4;
/* 272 */           d2 = 1.0D;
/*     */         }
/* 274 */         if (paramDouble2 > 0.0D) {
/* 275 */           double d6 = (l5 - 2L) * Arithmetic.binomial(l4 + l5 - 2L, l5 - 1L) - Arithmetic.binomial(l4 + l5 - 3L, l5 - 3L) + Arithmetic.binomial(l4 + l5 - 3L, l5 - 2L);
/* 276 */           double d7 = d3 / paramDouble1;
/* 277 */           double d8 = Arithmetic.binomial(l4 + l5 - 2L, l5 - 1L);
/* 278 */           double d9 = d3 / (2.0D * paramDouble1 * paramDouble1);
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
/* 291 */           double d10 = 0.5D + 0.5D * Math.sqrt(1.0D + 4.0D * d6 / d7);
/* 292 */           long l7 = Math.ceil(d9 * d10 * d10 / d8);
/* 293 */           if (l4 * l7 < l3) {
/* 294 */             l2 = l7;
/* 295 */             l1 = l4;
/* 296 */             l3 = l4 * l7;
/* 297 */             d2 = d1 * 2.0D * paramDouble1 * paramDouble1 / d3;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 302 */     long[] arrayOfLong = new long[2];
/* 303 */     arrayOfLong[0] = l1;
/* 304 */     arrayOfLong[1] = l2;
/* 305 */     paramArrayOfDouble[0] = d2;
/* 306 */     return arrayOfLong;
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
/*     */   public static DoubleQuantileFinder newDoubleQuantileFinder(boolean paramBoolean, long paramLong, double paramDouble1, double paramDouble2, int paramInt, RandomElement paramRandomElement)
/*     */   {
/* 330 */     if ((paramDouble1 <= 0.0D) || (paramLong < 1000L)) return new ExactDoubleQuantileFinder();
/* 331 */     if (paramDouble1 > 1.0D) paramDouble1 = 1.0D;
/* 332 */     if (paramDouble2 < 0.0D) paramDouble2 = 0.0D;
/* 333 */     if (paramDouble2 > 1.0D) paramDouble2 = 1.0D;
/* 334 */     if (paramInt < 1) paramInt = 1;
/* 335 */     if (paramInt > paramLong) { paramLong = paramInt;
/*     */     }
/*     */     
/* 338 */     if (paramBoolean) {
/* 339 */       localObject = new double[1];
/* 340 */       long[] arrayOfLong = known_N_compute_B_and_K(paramLong, paramDouble1, paramDouble2, paramInt, (double[])localObject);
/* 341 */       long l2 = arrayOfLong[0];
/* 342 */       long l4 = arrayOfLong[1];
/* 343 */       if (l2 == 1L) return new ExactDoubleQuantileFinder();
/* 344 */       return new KnownDoubleQuantileEstimator((int)l2, (int)l4, paramLong, localObject[0], paramRandomElement);
/*     */     }
/*     */     
/* 347 */     Object localObject = unknown_N_compute_B_and_K(paramDouble1, paramDouble2, paramInt);
/* 348 */     long l1 = localObject[0];
/* 349 */     long l3 = localObject[1];
/* 350 */     long l5 = localObject[2];
/* 351 */     double d = -1.0D;
/* 352 */     if (localObject[3] == 1L) { d = paramDouble1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 358 */     if (l1 == 1L) return new ExactDoubleQuantileFinder();
/* 359 */     return new UnknownDoubleQuantileEstimator((int)l1, (int)l3, (int)l5, d, paramRandomElement);
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
/*     */   public static DoubleArrayList newEquiDepthPhis(int paramInt)
/*     */   {
/* 389 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList(paramInt - 1);
/* 390 */     for (int i = 1; i <= paramInt - 1; i++) localDoubleArrayList.add(i / paramInt);
/* 391 */     return localDoubleArrayList;
/*     */   }
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
/* 403 */     return unknown_N_compute_B_and_K_raw(paramDouble1, paramDouble2, paramInt);
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
/*     */   protected static long[] unknown_N_compute_B_and_K_raw(double paramDouble1, double paramDouble2, int paramInt)
/*     */   {
/*     */     long[] arrayOfLong1;
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
/* 440 */     if (paramDouble1 <= 0.0D) {
/* 441 */       arrayOfLong1 = new long[4];
/* 442 */       arrayOfLong1[0] = 1L;
/* 443 */       arrayOfLong1[1] = Long.MAX_VALUE;
/* 444 */       arrayOfLong1[2] = Long.MAX_VALUE;
/* 445 */       arrayOfLong1[3] = 0L;
/* 446 */       return arrayOfLong1;
/*     */     }
/* 448 */     if ((paramDouble1 >= 1.0D) || (paramDouble2 >= 1.0D))
/*     */     {
/* 450 */       arrayOfLong1 = new long[4];
/* 451 */       arrayOfLong1[0] = 2L;
/* 452 */       arrayOfLong1[1] = 1L;
/* 453 */       arrayOfLong1[2] = 3L;
/* 454 */       arrayOfLong1[3] = 0L;
/* 455 */       return arrayOfLong1;
/*     */     }
/* 457 */     if (paramDouble2 <= 0.0D)
/*     */     {
/* 459 */       arrayOfLong1 = new long[4];
/* 460 */       arrayOfLong1[0] = 1L;
/* 461 */       arrayOfLong1[1] = Long.MAX_VALUE;
/* 462 */       arrayOfLong1[2] = Long.MAX_VALUE;
/* 463 */       arrayOfLong1[3] = 0L;
/* 464 */       return arrayOfLong1;
/*     */     }
/*     */     
/* 467 */     int i = 50;
/* 468 */     int j = 50;
/* 469 */     int k = 50;
/* 470 */     int m = 2;
/*     */     
/* 472 */     long l1 = Long.MAX_VALUE;
/* 473 */     long l2 = Long.MAX_VALUE;
/* 474 */     long l3 = Long.MAX_VALUE;
/* 475 */     long l4 = Long.MAX_VALUE;
/*     */     
/* 477 */     double d1 = Math.pow(2.0D, k);
/* 478 */     double d2 = Math.log(2.0D / (paramDouble2 / paramInt)) / (2.0D * paramDouble1 * paramDouble1);
/*     */     
/*     */ 
/* 481 */     while ((l1 == Long.MAX_VALUE) && (m-- > 0))
/*     */     {
/*     */ 
/* 484 */       for (int n = 2; n <= i; n++) {
/* 485 */         for (int i1 = 2; i1 <= j; i1++) {
/* 486 */           double d3 = Arithmetic.binomial(n + i1 - 2, i1 - 1);
/* 487 */           double d4 = Arithmetic.binomial(n + i1 - 3, i1 - 1);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 492 */           double d5 = d2 / Math.min(d3, 8.0D * d4 / 3.0D);
/*     */           
/*     */ 
/*     */ 
/* 496 */           double d6 = d3 / d4;
/* 497 */           double d7 = (d6 - 2.0D) * (k - 2.0D) / (d6 + d1 - 2.0D);
/* 498 */           double d8 = (i1 + 3 + d7) / (2.0D * paramDouble1);
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 508 */           double d9 = d5 * d5 + 4.0D * d5 * d8;
/* 509 */           if (d9 >= 0.0D) {
/* 510 */             double d10 = Math.sqrt(d9);
/* 511 */             double d11 = (d5 + 2.0D * d8 + d10) / (2.0D * d8);
/* 512 */             double d12 = (d5 + 2.0D * d8 - d10) / (2.0D * d8);
/*     */             
/*     */ 
/* 515 */             int i2 = 0;
/* 516 */             int i3 = 0;
/* 517 */             if ((0.0D < d11) && (d11 < 1.0D)) i2 = 1;
/* 518 */             if ((0.0D < d12) && (d12 < 1.0D)) i3 = 1;
/* 519 */             if ((i2 != 0) || (i3 != 0)) {
/* 520 */               double d13 = d11;
/* 521 */               if ((i2 != 0) && (i3 != 0))
/*     */               {
/* 523 */                 d13 = Math.max(d11, d12);
/*     */               }
/* 525 */               else if (i3 != 0) {
/* 526 */                 d13 = d12;
/*     */               }
/*     */               
/*     */ 
/* 530 */               long l5 = Math.ceil(Math.max(d8 / d13, (i1 + 1) / (2.0D * paramDouble1)));
/* 531 */               if (l5 > 0L) {
/* 532 */                 long l6 = n * l5;
/* 533 */                 if (l6 < l4)
/*     */                 {
/* 535 */                   l2 = l5;
/* 536 */                   l1 = n;
/* 537 */                   l3 = i1;
/* 538 */                   l4 = l6;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 545 */       if (l1 == Long.MAX_VALUE) {
/* 546 */         System.out.println("Warning: Computing b and k looks like a lot of work!");
/*     */         
/* 548 */         i *= 2;
/* 549 */         j *= 2;
/* 550 */         k *= 2;
/*     */       }
/*     */     }
/*     */     
/* 554 */     long[] arrayOfLong2 = new long[4];
/* 555 */     arrayOfLong2[3] = 0L;
/* 556 */     if (l1 == Long.MAX_VALUE)
/*     */     {
/*     */ 
/* 559 */       arrayOfLong2[0] = 1L;
/* 560 */       arrayOfLong2[1] = Long.MAX_VALUE;
/* 561 */       arrayOfLong2[2] = Long.MAX_VALUE;
/*     */     }
/*     */     else {
/* 564 */       arrayOfLong2[0] = l1;
/* 565 */       arrayOfLong2[1] = l2;
/* 566 */       arrayOfLong2[2] = l3;
/*     */     }
/*     */     
/* 569 */     return arrayOfLong2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/QuantileFinderFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */