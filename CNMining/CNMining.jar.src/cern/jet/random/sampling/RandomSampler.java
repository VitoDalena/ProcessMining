/*     */ package cern.jet.random.sampling;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.Timer;
/*     */ import cern.jet.random.AbstractDistribution;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomSampler
/*     */   extends PersistentObject
/*     */ {
/*     */   long my_n;
/*     */   long my_N;
/*     */   long my_low;
/*     */   RandomElement my_RandomGenerator;
/*     */   
/*     */   public RandomSampler(long paramLong1, long paramLong2, long paramLong3, RandomElement paramRandomElement)
/*     */   {
/* 127 */     if (paramLong1 < 0L) throw new IllegalArgumentException("n must be >= 0");
/* 128 */     if (paramLong1 > paramLong2) throw new IllegalArgumentException("n must by <= N");
/* 129 */     this.my_n = paramLong1;
/* 130 */     this.my_N = paramLong2;
/* 131 */     this.my_low = paramLong3;
/*     */     
/* 133 */     if (paramRandomElement == null) paramRandomElement = AbstractDistribution.makeDefaultGenerator();
/* 134 */     this.my_RandomGenerator = paramRandomElement;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 140 */     RandomSampler localRandomSampler = (RandomSampler)super.clone();
/* 141 */     localRandomSampler.my_RandomGenerator = ((RandomElement)this.my_RandomGenerator.clone());
/* 142 */     return localRandomSampler;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 148 */     long l1 = Long.parseLong(paramArrayOfString[0]);
/* 149 */     long l2 = Long.parseLong(paramArrayOfString[1]);
/* 150 */     long l3 = Long.parseLong(paramArrayOfString[2]);
/* 151 */     int i = Integer.parseInt(paramArrayOfString[3]);
/* 152 */     int j = Integer.parseInt(paramArrayOfString[4]);
/*     */     
/* 154 */     test(l1, l2, l3, i, j);
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
/*     */   public void nextBlock(int paramInt1, long[] paramArrayOfLong, int paramInt2)
/*     */   {
/* 169 */     if (paramInt1 > this.my_n) throw new IllegalArgumentException("Random sample exhausted.");
/* 170 */     if (paramInt1 < 0) { throw new IllegalArgumentException("Negative count.");
/*     */     }
/* 172 */     if (paramInt1 == 0) { return;
/*     */     }
/* 174 */     sample(this.my_n, this.my_N, paramInt1, this.my_low, paramArrayOfLong, paramInt2, this.my_RandomGenerator);
/*     */     
/* 176 */     long l = paramArrayOfLong[(paramInt2 + paramInt1 - 1)];
/* 177 */     this.my_n -= paramInt1;
/* 178 */     this.my_N = (this.my_N - l - 1L + this.my_low);
/* 179 */     this.my_low = (l + 1L);
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
/*     */   protected static void rejectMethodD(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long[] paramArrayOfLong, int paramInt2, RandomElement paramRandomElement)
/*     */   {
/* 208 */     paramLong1 = paramLong2 - paramLong1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 214 */     long l5 = -1L + paramLong3;
/*     */     
/* 216 */     long l6 = -13L;
/*     */     
/* 218 */     double d1 = paramLong1;double d3 = 1.0D / d1;double d2 = paramLong2;
/* 219 */     double d7 = Math.exp(Math.log(paramRandomElement.raw()) * d3);
/* 220 */     long l1 = -paramLong1 + 1L + paramLong2; long l4; double d12; int i; for (double d13 = -d1 + 1.0D + d2; 
/*     */         
/*     */ 
/* 223 */         (paramLong1 > 1L) && (paramInt1 > 0); 
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 269 */         d13 = d12 + d13)
/*     */     {
/* 224 */       double d4 = 1.0D / (-1.0D + d1);
/*     */       for (;;)
/*     */       {
/* 227 */         double d6 = d2 * (-d7 + 1.0D);l4 = d6;
/* 228 */         if (l4 >= l1) {
/* 229 */           d7 = Math.exp(Math.log(paramRandomElement.raw()) * d3);
/*     */         } else {
/* 231 */           double d5 = paramRandomElement.raw();d12 = -l4;
/*     */           
/*     */ 
/* 234 */           double d8 = Math.exp(Math.log(d5 * d2 / d13) * d4);
/* 235 */           d7 = d8 * (-d6 / d2 + 1.0D) * (d13 / (d12 + d13));
/* 236 */           if (d7 <= 1.0D) {
/*     */             break;
/*     */           }
/* 239 */           double d9 = 1.0D;double d10 = -1.0D + d2;
/* 240 */           double d11; long l3; if (paramLong1 - 1L > l4) {
/* 241 */             d11 = -d1 + d2;l3 = -l4 + paramLong2;
/*     */           }
/*     */           else {
/* 244 */             d11 = -1.0D + d12 + d2;l3 = l1;
/*     */           }
/* 246 */           for (long l2 = paramLong2 - 1L; l2 >= l3; l2 -= 1L) {
/* 247 */             d9 = d9 * d10 / d11;
/* 248 */             d10 -= 1.0D;
/* 249 */             d11 -= 1.0D;
/*     */           }
/* 251 */           if (d2 / (-d6 + d2) >= d8 * Math.exp(Math.log(d9) * d4))
/*     */           {
/* 253 */             d7 = Math.exp(Math.log(paramRandomElement.raw()) * d4);
/* 254 */             break;
/*     */           }
/* 256 */           d7 = Math.exp(Math.log(paramRandomElement.raw()) * d3);
/*     */         }
/*     */       }
/*     */       
/* 260 */       i = paramInt1;
/* 261 */       if (l4 < i) { i = (int)l4;
/*     */       }
/* 263 */       paramInt1 -= i;
/* 264 */       do { paramArrayOfLong[(paramInt2++)] = (++l5);i--; } while (i >= 0);
/* 265 */       l5 += 1L;
/*     */       
/* 267 */       paramLong2 -= l4 + 1L;d2 = d12 + (-1.0D + d2);
/* 268 */       paramLong1 -= 1L;d1 -= 1.0D;d3 = d4;
/* 269 */       l1 = -l4 + l1;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 274 */     if (paramInt1 > 0)
/*     */     {
/* 276 */       l4 = (paramLong2 * d7);
/*     */       
/* 278 */       i = paramInt1;
/* 279 */       if (l4 < i) { i = (int)l4;
/*     */       }
/* 281 */       paramInt1 -= i;
/* 282 */       do { paramArrayOfLong[(paramInt2++)] = (++l5);i--; } while (i >= 0);
/*     */       
/* 284 */       l5 += 1L;
/*     */       do
/*     */       {
/* 287 */         paramArrayOfLong[(paramInt2++)] = (++l5);paramInt1--; } while (paramInt1 >= 0);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void sample(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long[] paramArrayOfLong, int paramInt2, RandomElement paramRandomElement)
/*     */   {
/* 312 */     if ((paramLong1 <= 0L) || (paramInt1 <= 0)) return;
/* 313 */     if (paramInt1 > paramLong1) throw new IllegalArgumentException("count must not be greater than n");
/* 314 */     if (paramRandomElement == null) { paramRandomElement = AbstractDistribution.makeDefaultGenerator();
/*     */     }
/* 316 */     if (paramInt1 == paramLong2) {
/* 317 */       long l = paramLong3;
/* 318 */       int i = paramInt2 + paramInt1;
/* 319 */       for (int j = paramInt2; j < i; paramArrayOfLong[(j++)] = (l++)) {}
/* 320 */       return;
/*     */     }
/*     */     
/* 323 */     if (paramLong1 < paramLong2 * 0.95D) {
/* 324 */       sampleMethodD(paramLong1, paramLong2, paramInt1, paramLong3, paramArrayOfLong, paramInt2, paramRandomElement);
/*     */     }
/*     */     else {
/* 327 */       rejectMethodD(paramLong1, paramLong2, paramInt1, paramLong3, paramArrayOfLong, paramInt2, paramRandomElement);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static void sampleMethodA(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long[] paramArrayOfLong, int paramInt2, RandomElement paramRandomElement)
/*     */   {
/* 352 */     long l2 = -1L + paramLong3;
/*     */     
/* 354 */     double d4 = paramLong2 - paramLong1;
/* 355 */     double d3 = paramLong2;
/* 356 */     long l1; while ((paramLong1 >= 2L) && (paramInt1 > 0)) {
/* 357 */       double d1 = paramRandomElement.raw();
/* 358 */       l1 = 0L;
/* 359 */       double d2 = d4 / d3;
/* 360 */       while (d2 > d1) {
/* 361 */         l1 += 1L;
/* 362 */         d4 -= 1.0D;
/* 363 */         d3 -= 1.0D;
/* 364 */         d2 = d2 * d4 / d3;
/*     */       }
/* 366 */       l2 += l1 + 1L;
/* 367 */       paramArrayOfLong[(paramInt2++)] = l2;
/* 368 */       paramInt1--;
/* 369 */       d3 -= 1.0D;
/* 370 */       paramLong1 -= 1L;
/*     */     }
/*     */     
/* 373 */     if (paramInt1 > 0)
/*     */     {
/* 375 */       l1 = (Math.round(d3) * paramRandomElement.raw());
/* 376 */       l2 += l1 + 1L;
/* 377 */       paramArrayOfLong[paramInt2] = l2;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static void sampleMethodD(long paramLong1, long paramLong2, int paramInt1, long paramLong3, long[] paramArrayOfLong, int paramInt2, RandomElement paramRandomElement)
/*     */   {
/* 401 */     long l6 = -1L + paramLong3;
/*     */     
/* 403 */     long l7 = -13L;
/*     */     
/* 405 */     double d1 = paramLong1;double d3 = 1.0D / d1;double d2 = paramLong2;
/* 406 */     double d7 = Math.exp(Math.log(paramRandomElement.raw()) * d3);
/* 407 */     long l1 = -paramLong1 + 1L + paramLong2;double d13 = -d1 + 1.0D + d2;
/* 408 */     long l2 = -l7 * paramLong1;
/*     */     long l5;
/* 410 */     while ((paramLong1 > 1L) && (paramInt1 > 0) && (l2 < paramLong2)) {
/* 411 */       double d4 = 1.0D / (-1.0D + d1);
/*     */       double d12;
/*     */       for (;;) {
/* 414 */         double d6 = d2 * (-d7 + 1.0D);l5 = d6;
/* 415 */         if (l5 >= l1) {
/* 416 */           d7 = Math.exp(Math.log(paramRandomElement.raw()) * d3);
/*     */         } else {
/* 418 */           double d5 = paramRandomElement.raw();d12 = -l5;
/*     */           
/*     */ 
/* 421 */           double d8 = Math.exp(Math.log(d5 * d2 / d13) * d4);
/* 422 */           d7 = d8 * (-d6 / d2 + 1.0D) * (d13 / (d12 + d13));
/* 423 */           if (d7 <= 1.0D) {
/*     */             break;
/*     */           }
/* 426 */           double d9 = 1.0D;double d10 = -1.0D + d2;
/* 427 */           double d11; long l4; if (paramLong1 - 1L > l5) {
/* 428 */             d11 = -d1 + d2;l4 = -l5 + paramLong2;
/*     */           }
/*     */           else {
/* 431 */             d11 = -1.0D + d12 + d2;l4 = l1;
/*     */           }
/* 433 */           for (long l3 = paramLong2 - 1L; l3 >= l4; l3 -= 1L) {
/* 434 */             d9 = d9 * d10 / d11;
/* 435 */             d10 -= 1.0D;
/* 436 */             d11 -= 1.0D;
/*     */           }
/* 438 */           if (d2 / (-d6 + d2) >= d8 * Math.exp(Math.log(d9) * d4))
/*     */           {
/* 440 */             d7 = Math.exp(Math.log(paramRandomElement.raw()) * d4);
/* 441 */             break;
/*     */           }
/* 443 */           d7 = Math.exp(Math.log(paramRandomElement.raw()) * d3);
/*     */         }
/*     */       }
/*     */       
/* 447 */       l6 += l5 + 1L;
/* 448 */       paramArrayOfLong[(paramInt2++)] = l6;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 457 */       paramInt1--;
/*     */       
/* 459 */       paramLong2 -= l5 + 1L;d2 = d12 + (-1.0D + d2);
/* 460 */       paramLong1 -= 1L;d1 -= 1.0D;d3 = d4;
/* 461 */       l1 = -l5 + l1;d13 = d12 + d13;
/* 462 */       l2 += l7;
/*     */     }
/*     */     
/*     */ 
/* 466 */     if (paramInt1 > 0) {
/* 467 */       if (paramLong1 > 1L) {
/* 468 */         sampleMethodA(paramLong1, paramLong2, paramInt1, l6 + 1L, paramArrayOfLong, paramInt2, paramRandomElement);
/*     */       }
/*     */       else
/*     */       {
/* 472 */         l5 = (paramLong2 * d7);
/* 473 */         l6 += l5 + 1L;
/* 474 */         paramArrayOfLong[(paramInt2++)] = l6;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void test(long paramLong1, long paramLong2, long paramLong3, int paramInt1, int paramInt2)
/*     */   {
/* 483 */     long[] arrayOfLong = new long[paramInt1];
/* 484 */     long l1 = paramLong1 / paramInt1;
/*     */     
/* 486 */     Timer localTimer = new Timer().start();
/* 487 */     for (long l2 = paramInt2; --l2 >= 0L;) {
/* 488 */       RandomSampler localRandomSampler = new RandomSampler(paramLong1, paramLong2, paramLong3, AbstractDistribution.makeDefaultGenerator());
/* 489 */       for (long l3 = 0L; l3 < l1; l3 += 1L) {
/* 490 */         localRandomSampler.nextBlock(paramInt1, arrayOfLong, 0);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 501 */       int i = (int)(paramLong1 - paramInt1 * l1);
/* 502 */       if (i > 0) {
/* 503 */         localRandomSampler.nextBlock(i, arrayOfLong, 0);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 515 */     localTimer.stop();
/* 516 */     System.out.println("single run took " + localTimer.elapsedTime() / paramInt2);
/* 517 */     System.out.println("Good bye.\n");
/*     */   }
/*     */   
/*     */   protected static void testNegAlphaInv(String[] paramArrayOfString) {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/random/sampling/RandomSampler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */