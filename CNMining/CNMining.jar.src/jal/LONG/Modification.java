/*     */ package jal.LONG;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Modification
/*     */ {
/*     */   public static void copy(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  63 */     if (paramInt2 > paramInt1) {
/*  64 */       System.arraycopy(paramArrayOfLong1, paramInt1, paramArrayOfLong2, paramInt3, paramInt2 - paramInt1);
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
/*     */   public static void swap_ranges(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  82 */     while (paramInt1 < paramInt2) {
/*  83 */       long l = paramArrayOfLong2[paramInt3];
/*  84 */       paramArrayOfLong2[paramInt3] = paramArrayOfLong1[paramInt1];
/*  85 */       paramArrayOfLong1[paramInt1] = l;
/*  86 */       paramInt1++;
/*  87 */       paramInt3++;
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
/*     */   public static void transform(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3, UnaryOperator paramUnaryOperator)
/*     */   {
/* 111 */     while (paramInt1 < paramInt2) {
/* 112 */       paramArrayOfLong2[(paramInt3++)] = paramUnaryOperator.apply(paramArrayOfLong1[(paramInt1++)]);
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
/*     */ 
/*     */ 
/*     */   public static void transform(long[] paramArrayOfLong1, long[] paramArrayOfLong2, long[] paramArrayOfLong3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryOperator paramBinaryOperator)
/*     */   {
/* 139 */     while (paramInt1 < paramInt2) {
/* 140 */       paramArrayOfLong3[(paramInt4++)] = paramBinaryOperator.apply(paramArrayOfLong1[(paramInt1++)], paramArrayOfLong2[(paramInt3++)]);
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
/*     */   public static void replace(long[] paramArrayOfLong, int paramInt1, int paramInt2, long paramLong1, long paramLong2)
/*     */   {
/* 155 */     while (paramInt1 < paramInt2) {
/* 156 */       if (paramArrayOfLong[paramInt1] == paramLong1)
/* 157 */         paramArrayOfLong[paramInt1] = paramLong2;
/* 158 */       paramInt1++;
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
/*     */   public static void replace_if(long[] paramArrayOfLong, int paramInt1, int paramInt2, Predicate paramPredicate, long paramLong)
/*     */   {
/* 175 */     while (paramInt1 < paramInt2) {
/* 176 */       if (paramPredicate.apply(paramArrayOfLong[paramInt1]))
/* 177 */         paramArrayOfLong[paramInt1] = paramLong;
/* 178 */       paramInt1++;
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
/*     */   public static void replace_copy(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2)
/*     */   {
/* 201 */     while (paramInt1 < paramInt2) {
/* 202 */       long l = paramArrayOfLong1[(paramInt1++)];
/* 203 */       paramArrayOfLong2[(paramInt3++)] = (l == paramLong1 ? paramLong2 : l);
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
/*     */   public static void replace_copy_if(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate, long paramLong)
/*     */   {
/* 226 */     while (paramInt1 < paramInt2) {
/* 227 */       long l = paramArrayOfLong1[(paramInt1++)];
/* 228 */       paramArrayOfLong2[(paramInt3++)] = (paramPredicate.apply(l) ? paramLong : l);
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
/*     */   public static void fill(long[] paramArrayOfLong, int paramInt1, int paramInt2, long paramLong)
/*     */   {
/* 243 */     while (paramInt1 < paramInt2) {
/* 244 */       paramArrayOfLong[(paramInt1++)] = paramLong;
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
/*     */   public static void generate(long[] paramArrayOfLong, int paramInt1, int paramInt2, Generator paramGenerator)
/*     */   {
/* 261 */     while (paramInt1 < paramInt2) {
/* 262 */       paramArrayOfLong[(paramInt1++)] = paramGenerator.apply();
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
/*     */   public static int remove_if(long[] paramArrayOfLong, int paramInt1, int paramInt2, long paramLong)
/*     */   {
/* 279 */     int i = paramInt2;
/* 280 */     paramInt1--;
/*     */     for (;;) {
/* 282 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramArrayOfLong[paramInt1] != paramLong) {}
/* 283 */       } else { while ((paramInt1 < --paramInt2) && (paramArrayOfLong[paramInt2] == paramLong)) {}
/* 284 */         if (paramInt1 >= paramInt2) {
/* 285 */           return paramInt1;
/*     */         }
/* 287 */         paramArrayOfLong[paramInt1] = paramArrayOfLong[paramInt2];
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int remove_if(long[] paramArrayOfLong, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 306 */     int i = paramInt2;
/* 307 */     paramInt1--;
/*     */     for (;;) {
/* 309 */       paramInt1++; if (paramInt1 < paramInt2) { if (!paramPredicate.apply(paramArrayOfLong[paramInt1])) {}
/* 310 */       } else { while ((paramInt1 < --paramInt2) && (paramPredicate.apply(paramArrayOfLong[paramInt2]))) {}
/* 311 */         if (paramInt1 >= paramInt2) {
/* 312 */           return paramInt1;
/*     */         }
/* 314 */         paramArrayOfLong[paramInt1] = paramArrayOfLong[paramInt2];
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
/*     */ 
/*     */ 
/*     */   public static int stable_remove(long[] paramArrayOfLong, int paramInt1, int paramInt2, long paramLong)
/*     */   {
/* 332 */     paramInt1 = Inspection.find(paramArrayOfLong, paramInt1, paramInt2, paramLong);
/* 333 */     int i = Inspection.find_not(paramArrayOfLong, paramInt1, paramInt2, paramLong);
/* 334 */     while (i < paramInt2) {
/* 335 */       paramArrayOfLong[(paramInt1++)] = paramArrayOfLong[i];
/* 336 */       i = Inspection.find_not(paramArrayOfLong, ++i, paramInt2, paramLong);
/*     */     }
/* 338 */     return paramInt1;
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
/*     */   public static int stable_remove_if(long[] paramArrayOfLong, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 355 */     paramInt1 = Inspection.find_if(paramArrayOfLong, paramInt1, paramInt2, paramPredicate);
/* 356 */     int i = Inspection.find_if_not(paramArrayOfLong, paramInt1, paramInt2, paramPredicate);
/* 357 */     while (i < paramInt2) {
/* 358 */       paramArrayOfLong[(paramInt1++)] = paramArrayOfLong[i];
/* 359 */       i = Inspection.find_if_not(paramArrayOfLong, ++i, paramInt2, paramPredicate);
/*     */     }
/* 361 */     return paramInt1;
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
/*     */   public static int remove_copy(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3, long paramLong)
/*     */   {
/* 383 */     while (paramInt1 < paramInt2) {
/* 384 */       long l = paramArrayOfLong1[(paramInt1++)];
/* 385 */       if (l != paramLong)
/* 386 */         paramArrayOfLong2[(paramInt3++)] = l;
/*     */     }
/* 388 */     return paramInt3;
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
/*     */   public static int remove_copy_if(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate)
/*     */   {
/* 410 */     while (paramInt1 < paramInt2) {
/* 411 */       long l = paramArrayOfLong1[(paramInt1++)];
/* 412 */       if (!paramPredicate.apply(l))
/* 413 */         paramArrayOfLong2[(paramInt3++)] = l;
/*     */     }
/* 415 */     return paramInt3;
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
/*     */   public static int unique(long[] paramArrayOfLong, int paramInt1, int paramInt2)
/*     */   {
/* 431 */     paramInt1 = Inspection.adjacent_find(paramArrayOfLong, paramInt1, paramInt2);
/* 432 */     return unique_copy(paramArrayOfLong, paramArrayOfLong, paramInt1, paramInt2, paramInt1);
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
/*     */   public static int unique(long[] paramArrayOfLong, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 451 */     paramInt1 = Inspection.adjacent_find(paramArrayOfLong, paramInt1, paramInt2, paramBinaryPredicate);
/* 452 */     return unique_copy(paramArrayOfLong, paramArrayOfLong, paramInt1, paramInt2, paramInt1, paramBinaryPredicate);
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
/*     */   public static int unique_copy(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 474 */     if (paramInt1 >= paramInt2) {
/* 475 */       return paramInt3;
/*     */     }
/* 477 */     paramArrayOfLong2[paramInt3] = paramArrayOfLong1[paramInt1];
/*     */     do
/*     */     {
/* 480 */       if (paramArrayOfLong2[paramInt3] != paramArrayOfLong1[paramInt1]) {
/* 481 */         paramArrayOfLong2[(++paramInt3)] = paramArrayOfLong1[paramInt1];
/*     */       }
/* 479 */       paramInt1++; } while (paramInt1 < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 484 */     return paramInt3 + 1;
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
/*     */   public static int unique_copy(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 509 */     if (paramInt1 >= paramInt2) {
/* 510 */       return paramInt3;
/*     */     }
/* 512 */     paramArrayOfLong2[paramInt3] = paramArrayOfLong1[paramInt1];
/*     */     do
/*     */     {
/* 515 */       if (!paramBinaryPredicate.apply(paramArrayOfLong2[paramInt3], paramArrayOfLong1[paramInt1])) {
/* 516 */         paramArrayOfLong2[(++paramInt3)] = paramArrayOfLong1[paramInt1];
/*     */       }
/* 514 */       paramInt1++; } while (paramInt1 < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 519 */     return paramInt3 + 1;
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
/*     */   public static void reverse(long[] paramArrayOfLong, int paramInt1, int paramInt2)
/*     */   {
/* 532 */     while (paramInt1 < --paramInt2) {
/* 533 */       long l = paramArrayOfLong[paramInt1];
/* 534 */       paramArrayOfLong[(paramInt1++)] = paramArrayOfLong[paramInt2];
/* 535 */       paramArrayOfLong[paramInt2] = l;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void reverse_copy(long[] paramArrayOfLong, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 541 */     while (paramInt2 > paramInt1) {
/* 542 */       paramArrayOfLong[(paramInt3++)] = paramArrayOfLong[(--paramInt2)];
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
/*     */   public static void reverse_copy(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 561 */     while (paramInt2 > paramInt1) {
/* 562 */       paramArrayOfLong2[(paramInt3++)] = paramArrayOfLong1[(--paramInt2)];
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
/*     */   public static void rotate(long[] paramArrayOfLong, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 580 */     if ((paramInt2 != paramInt1) && (paramInt2 != paramInt3)) {
/* 581 */       reverse(paramArrayOfLong, paramInt1, paramInt2);
/* 582 */       reverse(paramArrayOfLong, paramInt2, paramInt3);
/* 583 */       reverse(paramArrayOfLong, paramInt1, paramInt3);
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
/*     */   public static void rotate_copy(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 606 */     copy(paramArrayOfLong1, paramArrayOfLong2, paramInt2, paramInt3, paramInt4);
/* 607 */     copy(paramArrayOfLong1, paramArrayOfLong2, paramInt1, paramInt2, paramInt4 + (paramInt3 - paramInt2));
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
/*     */   public static void random_shuffle(long[] paramArrayOfLong, int paramInt1, int paramInt2, Random paramRandom)
/*     */   {
/* 621 */     for (int i = paramInt1 + 1; i < paramInt2; i++) {
/* 622 */       int j = Math.abs(paramRandom.nextInt()) % (i - paramInt1 + 1);
/*     */       
/* 624 */       long l = paramArrayOfLong[j];
/* 625 */       paramArrayOfLong[j] = paramArrayOfLong[i];
/* 626 */       paramArrayOfLong[i] = l;
/*     */     }
/*     */   }
/*     */   
/* 630 */   private static Random default_RNG = new Random();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void random_shuffle(long[] paramArrayOfLong, int paramInt1, int paramInt2)
/*     */   {
/* 641 */     random_shuffle(paramArrayOfLong, paramInt1, paramInt2, default_RNG);
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
/*     */   public static int partition(long[] paramArrayOfLong, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/*     */     
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     for (;;)
/*     */     {
/* 664 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramPredicate.apply(paramArrayOfLong[paramInt1])) {}
/* 665 */       } else { while ((paramInt1 < --paramInt2) && (!paramPredicate.apply(paramArrayOfLong[paramInt2]))) {}
/* 666 */         if (paramInt1 >= paramInt2) return paramInt1;
/* 667 */         long l = paramArrayOfLong[paramInt1];
/* 668 */         paramArrayOfLong[paramInt1] = paramArrayOfLong[paramInt2];
/* 669 */         paramArrayOfLong[paramInt2] = l;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int stable_partition(long[] paramArrayOfLong, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 693 */     if (paramInt1 + 1 < paramInt2) {
/* 694 */       int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 695 */       int j = stable_partition(paramArrayOfLong, paramInt1, i, paramPredicate);
/* 696 */       int k = stable_partition(paramArrayOfLong, i, paramInt2, paramPredicate);
/* 697 */       rotate(paramArrayOfLong, j, i, k);
/* 698 */       return j + (k - i);
/*     */     }
/* 700 */     if ((paramInt1 >= paramInt2) || (!paramPredicate.apply(paramArrayOfLong[paramInt1]))) {
/* 701 */       return paramInt1;
/*     */     }
/* 703 */     return paramInt2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/LONG/Modification.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */