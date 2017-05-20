/*     */ package jal.INT;
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
/*     */   public static void copy(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  63 */     if (paramInt2 > paramInt1) {
/*  64 */       System.arraycopy(paramArrayOfInt1, paramInt1, paramArrayOfInt2, paramInt3, paramInt2 - paramInt1);
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
/*     */   public static void swap_ranges(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  82 */     while (paramInt1 < paramInt2) {
/*  83 */       int i = paramArrayOfInt2[paramInt3];
/*  84 */       paramArrayOfInt2[paramInt3] = paramArrayOfInt1[paramInt1];
/*  85 */       paramArrayOfInt1[paramInt1] = i;
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
/*     */   public static void transform(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3, UnaryOperator paramUnaryOperator)
/*     */   {
/* 111 */     while (paramInt1 < paramInt2) {
/* 112 */       paramArrayOfInt2[(paramInt3++)] = paramUnaryOperator.apply(paramArrayOfInt1[(paramInt1++)]);
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
/*     */   public static void transform(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryOperator paramBinaryOperator)
/*     */   {
/* 139 */     while (paramInt1 < paramInt2) {
/* 140 */       paramArrayOfInt3[(paramInt4++)] = paramBinaryOperator.apply(paramArrayOfInt1[(paramInt1++)], paramArrayOfInt2[(paramInt3++)]);
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
/*     */   public static void replace(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 155 */     while (paramInt1 < paramInt2) {
/* 156 */       if (paramArrayOfInt[paramInt1] == paramInt3)
/* 157 */         paramArrayOfInt[paramInt1] = paramInt4;
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
/*     */   public static void replace_if(int[] paramArrayOfInt, int paramInt1, int paramInt2, Predicate paramPredicate, int paramInt3)
/*     */   {
/* 175 */     while (paramInt1 < paramInt2) {
/* 176 */       if (paramPredicate.apply(paramArrayOfInt[paramInt1]))
/* 177 */         paramArrayOfInt[paramInt1] = paramInt3;
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
/*     */   public static void replace_copy(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*     */   {
/* 201 */     while (paramInt1 < paramInt2) {
/* 202 */       int i = paramArrayOfInt1[(paramInt1++)];
/* 203 */       paramArrayOfInt2[(paramInt3++)] = (i == paramInt4 ? paramInt5 : i);
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
/*     */   public static void replace_copy_if(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate, int paramInt4)
/*     */   {
/* 226 */     while (paramInt1 < paramInt2) {
/* 227 */       int i = paramArrayOfInt1[(paramInt1++)];
/* 228 */       paramArrayOfInt2[(paramInt3++)] = (paramPredicate.apply(i) ? paramInt4 : i);
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
/*     */   public static void fill(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 243 */     while (paramInt1 < paramInt2) {
/* 244 */       paramArrayOfInt[(paramInt1++)] = paramInt3;
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
/*     */   public static void generate(int[] paramArrayOfInt, int paramInt1, int paramInt2, Generator paramGenerator)
/*     */   {
/* 261 */     while (paramInt1 < paramInt2) {
/* 262 */       paramArrayOfInt[(paramInt1++)] = paramGenerator.apply();
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
/*     */   public static int remove_if(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 279 */     int i = paramInt2;
/* 280 */     paramInt1--;
/*     */     for (;;) {
/* 282 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramArrayOfInt[paramInt1] != paramInt3) {}
/* 283 */       } else { while ((paramInt1 < --paramInt2) && (paramArrayOfInt[paramInt2] == paramInt3)) {}
/* 284 */         if (paramInt1 >= paramInt2) {
/* 285 */           return paramInt1;
/*     */         }
/* 287 */         paramArrayOfInt[paramInt1] = paramArrayOfInt[paramInt2];
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
/*     */   public static int remove_if(int[] paramArrayOfInt, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 306 */     int i = paramInt2;
/* 307 */     paramInt1--;
/*     */     for (;;) {
/* 309 */       paramInt1++; if (paramInt1 < paramInt2) { if (!paramPredicate.apply(paramArrayOfInt[paramInt1])) {}
/* 310 */       } else { while ((paramInt1 < --paramInt2) && (paramPredicate.apply(paramArrayOfInt[paramInt2]))) {}
/* 311 */         if (paramInt1 >= paramInt2) {
/* 312 */           return paramInt1;
/*     */         }
/* 314 */         paramArrayOfInt[paramInt1] = paramArrayOfInt[paramInt2];
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
/*     */   public static int stable_remove(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 332 */     paramInt1 = Inspection.find(paramArrayOfInt, paramInt1, paramInt2, paramInt3);
/* 333 */     int i = Inspection.find_not(paramArrayOfInt, paramInt1, paramInt2, paramInt3);
/* 334 */     while (i < paramInt2) {
/* 335 */       paramArrayOfInt[(paramInt1++)] = paramArrayOfInt[i];
/* 336 */       i = Inspection.find_not(paramArrayOfInt, ++i, paramInt2, paramInt3);
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
/*     */   public static int stable_remove_if(int[] paramArrayOfInt, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 355 */     paramInt1 = Inspection.find_if(paramArrayOfInt, paramInt1, paramInt2, paramPredicate);
/* 356 */     int i = Inspection.find_if_not(paramArrayOfInt, paramInt1, paramInt2, paramPredicate);
/* 357 */     while (i < paramInt2) {
/* 358 */       paramArrayOfInt[(paramInt1++)] = paramArrayOfInt[i];
/* 359 */       i = Inspection.find_if_not(paramArrayOfInt, ++i, paramInt2, paramPredicate);
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
/*     */   public static int remove_copy(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 383 */     while (paramInt1 < paramInt2) {
/* 384 */       int i = paramArrayOfInt1[(paramInt1++)];
/* 385 */       if (i != paramInt4)
/* 386 */         paramArrayOfInt2[(paramInt3++)] = i;
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
/*     */   public static int remove_copy_if(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate)
/*     */   {
/* 410 */     while (paramInt1 < paramInt2) {
/* 411 */       int i = paramArrayOfInt1[(paramInt1++)];
/* 412 */       if (!paramPredicate.apply(i))
/* 413 */         paramArrayOfInt2[(paramInt3++)] = i;
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
/*     */   public static int unique(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*     */   {
/* 431 */     paramInt1 = Inspection.adjacent_find(paramArrayOfInt, paramInt1, paramInt2);
/* 432 */     return unique_copy(paramArrayOfInt, paramArrayOfInt, paramInt1, paramInt2, paramInt1);
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
/*     */   public static int unique(int[] paramArrayOfInt, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 451 */     paramInt1 = Inspection.adjacent_find(paramArrayOfInt, paramInt1, paramInt2, paramBinaryPredicate);
/* 452 */     return unique_copy(paramArrayOfInt, paramArrayOfInt, paramInt1, paramInt2, paramInt1, paramBinaryPredicate);
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
/*     */   public static int unique_copy(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 474 */     if (paramInt1 >= paramInt2) {
/* 475 */       return paramInt3;
/*     */     }
/* 477 */     paramArrayOfInt2[paramInt3] = paramArrayOfInt1[paramInt1];
/*     */     do
/*     */     {
/* 480 */       if (paramArrayOfInt2[paramInt3] != paramArrayOfInt1[paramInt1]) {
/* 481 */         paramArrayOfInt2[(++paramInt3)] = paramArrayOfInt1[paramInt1];
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
/*     */   public static int unique_copy(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 509 */     if (paramInt1 >= paramInt2) {
/* 510 */       return paramInt3;
/*     */     }
/* 512 */     paramArrayOfInt2[paramInt3] = paramArrayOfInt1[paramInt1];
/*     */     do
/*     */     {
/* 515 */       if (!paramBinaryPredicate.apply(paramArrayOfInt2[paramInt3], paramArrayOfInt1[paramInt1])) {
/* 516 */         paramArrayOfInt2[(++paramInt3)] = paramArrayOfInt1[paramInt1];
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
/*     */   public static void reverse(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*     */   {
/* 532 */     while (paramInt1 < --paramInt2) {
/* 533 */       int i = paramArrayOfInt[paramInt1];
/* 534 */       paramArrayOfInt[(paramInt1++)] = paramArrayOfInt[paramInt2];
/* 535 */       paramArrayOfInt[paramInt2] = i;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void reverse_copy(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 541 */     while (paramInt2 > paramInt1) {
/* 542 */       paramArrayOfInt[(paramInt3++)] = paramArrayOfInt[(--paramInt2)];
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
/*     */   public static void reverse_copy(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 561 */     while (paramInt2 > paramInt1) {
/* 562 */       paramArrayOfInt2[(paramInt3++)] = paramArrayOfInt1[(--paramInt2)];
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
/*     */   public static void rotate(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 580 */     if ((paramInt2 != paramInt1) && (paramInt2 != paramInt3)) {
/* 581 */       reverse(paramArrayOfInt, paramInt1, paramInt2);
/* 582 */       reverse(paramArrayOfInt, paramInt2, paramInt3);
/* 583 */       reverse(paramArrayOfInt, paramInt1, paramInt3);
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
/*     */   public static void rotate_copy(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 606 */     copy(paramArrayOfInt1, paramArrayOfInt2, paramInt2, paramInt3, paramInt4);
/* 607 */     copy(paramArrayOfInt1, paramArrayOfInt2, paramInt1, paramInt2, paramInt4 + (paramInt3 - paramInt2));
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
/*     */   public static void random_shuffle(int[] paramArrayOfInt, int paramInt1, int paramInt2, Random paramRandom)
/*     */   {
/* 621 */     for (int i = paramInt1 + 1; i < paramInt2; i++) {
/* 622 */       int j = Math.abs(paramRandom.nextInt()) % (i - paramInt1 + 1);
/*     */       
/* 624 */       int k = paramArrayOfInt[j];
/* 625 */       paramArrayOfInt[j] = paramArrayOfInt[i];
/* 626 */       paramArrayOfInt[i] = k;
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
/*     */   public static void random_shuffle(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*     */   {
/* 641 */     random_shuffle(paramArrayOfInt, paramInt1, paramInt2, default_RNG);
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
/*     */   public static int partition(int[] paramArrayOfInt, int paramInt1, int paramInt2, Predicate paramPredicate)
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
/* 664 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramPredicate.apply(paramArrayOfInt[paramInt1])) {}
/* 665 */       } else { while ((paramInt1 < --paramInt2) && (!paramPredicate.apply(paramArrayOfInt[paramInt2]))) {}
/* 666 */         if (paramInt1 >= paramInt2) return paramInt1;
/* 667 */         int i = paramArrayOfInt[paramInt1];
/* 668 */         paramArrayOfInt[paramInt1] = paramArrayOfInt[paramInt2];
/* 669 */         paramArrayOfInt[paramInt2] = i;
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
/*     */   public static int stable_partition(int[] paramArrayOfInt, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 693 */     if (paramInt1 + 1 < paramInt2) {
/* 694 */       int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 695 */       int j = stable_partition(paramArrayOfInt, paramInt1, i, paramPredicate);
/* 696 */       int k = stable_partition(paramArrayOfInt, i, paramInt2, paramPredicate);
/* 697 */       rotate(paramArrayOfInt, j, i, k);
/* 698 */       return j + (k - i);
/*     */     }
/* 700 */     if ((paramInt1 >= paramInt2) || (!paramPredicate.apply(paramArrayOfInt[paramInt1]))) {
/* 701 */       return paramInt1;
/*     */     }
/* 703 */     return paramInt2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/INT/Modification.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */