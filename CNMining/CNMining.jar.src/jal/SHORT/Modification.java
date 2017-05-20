/*     */ package jal.SHORT;
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
/*     */   public static void copy(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  63 */     if (paramInt2 > paramInt1) {
/*  64 */       System.arraycopy(paramArrayOfShort1, paramInt1, paramArrayOfShort2, paramInt3, paramInt2 - paramInt1);
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
/*     */   public static void swap_ranges(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  82 */     while (paramInt1 < paramInt2) {
/*  83 */       int i = paramArrayOfShort2[paramInt3];
/*  84 */       paramArrayOfShort2[paramInt3] = paramArrayOfShort1[paramInt1];
/*  85 */       paramArrayOfShort1[paramInt1] = i;
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
/*     */   public static void transform(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, UnaryOperator paramUnaryOperator)
/*     */   {
/* 111 */     while (paramInt1 < paramInt2) {
/* 112 */       paramArrayOfShort2[(paramInt3++)] = paramUnaryOperator.apply(paramArrayOfShort1[(paramInt1++)]);
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
/*     */   public static void transform(short[] paramArrayOfShort1, short[] paramArrayOfShort2, short[] paramArrayOfShort3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryOperator paramBinaryOperator)
/*     */   {
/* 139 */     while (paramInt1 < paramInt2) {
/* 140 */       paramArrayOfShort3[(paramInt4++)] = paramBinaryOperator.apply(paramArrayOfShort1[(paramInt1++)], paramArrayOfShort2[(paramInt3++)]);
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
/*     */   public static void replace(short[] paramArrayOfShort, int paramInt1, int paramInt2, short paramShort1, short paramShort2)
/*     */   {
/* 155 */     while (paramInt1 < paramInt2) {
/* 156 */       if (paramArrayOfShort[paramInt1] == paramShort1)
/* 157 */         paramArrayOfShort[paramInt1] = paramShort2;
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
/*     */   public static void replace_if(short[] paramArrayOfShort, int paramInt1, int paramInt2, Predicate paramPredicate, short paramShort)
/*     */   {
/* 175 */     while (paramInt1 < paramInt2) {
/* 176 */       if (paramPredicate.apply(paramArrayOfShort[paramInt1]))
/* 177 */         paramArrayOfShort[paramInt1] = paramShort;
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
/*     */   public static void replace_copy(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, short paramShort1, short paramShort2)
/*     */   {
/* 201 */     while (paramInt1 < paramInt2) {
/* 202 */       short s = paramArrayOfShort1[(paramInt1++)];
/* 203 */       paramArrayOfShort2[(paramInt3++)] = (s == paramShort1 ? paramShort2 : s);
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
/*     */   public static void replace_copy_if(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate, short paramShort)
/*     */   {
/* 226 */     while (paramInt1 < paramInt2) {
/* 227 */       short s = paramArrayOfShort1[(paramInt1++)];
/* 228 */       paramArrayOfShort2[(paramInt3++)] = (paramPredicate.apply(s) ? paramShort : s);
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
/*     */   public static void fill(short[] paramArrayOfShort, int paramInt1, int paramInt2, short paramShort)
/*     */   {
/* 243 */     while (paramInt1 < paramInt2) {
/* 244 */       paramArrayOfShort[(paramInt1++)] = paramShort;
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
/*     */   public static void generate(short[] paramArrayOfShort, int paramInt1, int paramInt2, Generator paramGenerator)
/*     */   {
/* 261 */     while (paramInt1 < paramInt2) {
/* 262 */       paramArrayOfShort[(paramInt1++)] = paramGenerator.apply();
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
/*     */   public static int remove_if(short[] paramArrayOfShort, int paramInt1, int paramInt2, short paramShort)
/*     */   {
/* 279 */     int i = paramInt2;
/* 280 */     paramInt1--;
/*     */     for (;;) {
/* 282 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramArrayOfShort[paramInt1] != paramShort) {}
/* 283 */       } else { while ((paramInt1 < --paramInt2) && (paramArrayOfShort[paramInt2] == paramShort)) {}
/* 284 */         if (paramInt1 >= paramInt2) {
/* 285 */           return paramInt1;
/*     */         }
/* 287 */         paramArrayOfShort[paramInt1] = paramArrayOfShort[paramInt2];
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
/*     */   public static int remove_if(short[] paramArrayOfShort, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 306 */     int i = paramInt2;
/* 307 */     paramInt1--;
/*     */     for (;;) {
/* 309 */       paramInt1++; if (paramInt1 < paramInt2) { if (!paramPredicate.apply(paramArrayOfShort[paramInt1])) {}
/* 310 */       } else { while ((paramInt1 < --paramInt2) && (paramPredicate.apply(paramArrayOfShort[paramInt2]))) {}
/* 311 */         if (paramInt1 >= paramInt2) {
/* 312 */           return paramInt1;
/*     */         }
/* 314 */         paramArrayOfShort[paramInt1] = paramArrayOfShort[paramInt2];
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
/*     */   public static int stable_remove(short[] paramArrayOfShort, int paramInt1, int paramInt2, short paramShort)
/*     */   {
/* 332 */     paramInt1 = Inspection.find(paramArrayOfShort, paramInt1, paramInt2, paramShort);
/* 333 */     int i = Inspection.find_not(paramArrayOfShort, paramInt1, paramInt2, paramShort);
/* 334 */     while (i < paramInt2) {
/* 335 */       paramArrayOfShort[(paramInt1++)] = paramArrayOfShort[i];
/* 336 */       i = Inspection.find_not(paramArrayOfShort, ++i, paramInt2, paramShort);
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
/*     */   public static int stable_remove_if(short[] paramArrayOfShort, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 355 */     paramInt1 = Inspection.find_if(paramArrayOfShort, paramInt1, paramInt2, paramPredicate);
/* 356 */     int i = Inspection.find_if_not(paramArrayOfShort, paramInt1, paramInt2, paramPredicate);
/* 357 */     while (i < paramInt2) {
/* 358 */       paramArrayOfShort[(paramInt1++)] = paramArrayOfShort[i];
/* 359 */       i = Inspection.find_if_not(paramArrayOfShort, ++i, paramInt2, paramPredicate);
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
/*     */   public static int remove_copy(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, short paramShort)
/*     */   {
/* 383 */     while (paramInt1 < paramInt2) {
/* 384 */       short s = paramArrayOfShort1[(paramInt1++)];
/* 385 */       if (s != paramShort)
/* 386 */         paramArrayOfShort2[(paramInt3++)] = s;
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
/*     */   public static int remove_copy_if(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate)
/*     */   {
/* 410 */     while (paramInt1 < paramInt2) {
/* 411 */       short s = paramArrayOfShort1[(paramInt1++)];
/* 412 */       if (!paramPredicate.apply(s))
/* 413 */         paramArrayOfShort2[(paramInt3++)] = s;
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
/*     */   public static int unique(short[] paramArrayOfShort, int paramInt1, int paramInt2)
/*     */   {
/* 431 */     paramInt1 = Inspection.adjacent_find(paramArrayOfShort, paramInt1, paramInt2);
/* 432 */     return unique_copy(paramArrayOfShort, paramArrayOfShort, paramInt1, paramInt2, paramInt1);
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
/*     */   public static int unique(short[] paramArrayOfShort, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 451 */     paramInt1 = Inspection.adjacent_find(paramArrayOfShort, paramInt1, paramInt2, paramBinaryPredicate);
/* 452 */     return unique_copy(paramArrayOfShort, paramArrayOfShort, paramInt1, paramInt2, paramInt1, paramBinaryPredicate);
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
/*     */   public static int unique_copy(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 474 */     if (paramInt1 >= paramInt2) {
/* 475 */       return paramInt3;
/*     */     }
/* 477 */     paramArrayOfShort2[paramInt3] = paramArrayOfShort1[paramInt1];
/*     */     do
/*     */     {
/* 480 */       if (paramArrayOfShort2[paramInt3] != paramArrayOfShort1[paramInt1]) {
/* 481 */         paramArrayOfShort2[(++paramInt3)] = paramArrayOfShort1[paramInt1];
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
/*     */   public static int unique_copy(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 509 */     if (paramInt1 >= paramInt2) {
/* 510 */       return paramInt3;
/*     */     }
/* 512 */     paramArrayOfShort2[paramInt3] = paramArrayOfShort1[paramInt1];
/*     */     do
/*     */     {
/* 515 */       if (!paramBinaryPredicate.apply(paramArrayOfShort2[paramInt3], paramArrayOfShort1[paramInt1])) {
/* 516 */         paramArrayOfShort2[(++paramInt3)] = paramArrayOfShort1[paramInt1];
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
/*     */   public static void reverse(short[] paramArrayOfShort, int paramInt1, int paramInt2)
/*     */   {
/* 532 */     while (paramInt1 < --paramInt2) {
/* 533 */       int i = paramArrayOfShort[paramInt1];
/* 534 */       paramArrayOfShort[(paramInt1++)] = paramArrayOfShort[paramInt2];
/* 535 */       paramArrayOfShort[paramInt2] = i;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void reverse_copy(short[] paramArrayOfShort, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 541 */     while (paramInt2 > paramInt1) {
/* 542 */       paramArrayOfShort[(paramInt3++)] = paramArrayOfShort[(--paramInt2)];
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
/*     */   public static void reverse_copy(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 561 */     while (paramInt2 > paramInt1) {
/* 562 */       paramArrayOfShort2[(paramInt3++)] = paramArrayOfShort1[(--paramInt2)];
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
/*     */   public static void rotate(short[] paramArrayOfShort, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 580 */     if ((paramInt2 != paramInt1) && (paramInt2 != paramInt3)) {
/* 581 */       reverse(paramArrayOfShort, paramInt1, paramInt2);
/* 582 */       reverse(paramArrayOfShort, paramInt2, paramInt3);
/* 583 */       reverse(paramArrayOfShort, paramInt1, paramInt3);
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
/*     */   public static void rotate_copy(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 606 */     copy(paramArrayOfShort1, paramArrayOfShort2, paramInt2, paramInt3, paramInt4);
/* 607 */     copy(paramArrayOfShort1, paramArrayOfShort2, paramInt1, paramInt2, paramInt4 + (paramInt3 - paramInt2));
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
/*     */   public static void random_shuffle(short[] paramArrayOfShort, int paramInt1, int paramInt2, Random paramRandom)
/*     */   {
/* 621 */     for (int i = paramInt1 + 1; i < paramInt2; i++) {
/* 622 */       int j = Math.abs(paramRandom.nextInt()) % (i - paramInt1 + 1);
/*     */       
/* 624 */       int k = paramArrayOfShort[j];
/* 625 */       paramArrayOfShort[j] = paramArrayOfShort[i];
/* 626 */       paramArrayOfShort[i] = k;
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
/*     */   public static void random_shuffle(short[] paramArrayOfShort, int paramInt1, int paramInt2)
/*     */   {
/* 641 */     random_shuffle(paramArrayOfShort, paramInt1, paramInt2, default_RNG);
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
/*     */   public static int partition(short[] paramArrayOfShort, int paramInt1, int paramInt2, Predicate paramPredicate)
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
/* 664 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramPredicate.apply(paramArrayOfShort[paramInt1])) {}
/* 665 */       } else { while ((paramInt1 < --paramInt2) && (!paramPredicate.apply(paramArrayOfShort[paramInt2]))) {}
/* 666 */         if (paramInt1 >= paramInt2) return paramInt1;
/* 667 */         int i = paramArrayOfShort[paramInt1];
/* 668 */         paramArrayOfShort[paramInt1] = paramArrayOfShort[paramInt2];
/* 669 */         paramArrayOfShort[paramInt2] = i;
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
/*     */   public static int stable_partition(short[] paramArrayOfShort, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 693 */     if (paramInt1 + 1 < paramInt2) {
/* 694 */       int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 695 */       int j = stable_partition(paramArrayOfShort, paramInt1, i, paramPredicate);
/* 696 */       int k = stable_partition(paramArrayOfShort, i, paramInt2, paramPredicate);
/* 697 */       rotate(paramArrayOfShort, j, i, k);
/* 698 */       return j + (k - i);
/*     */     }
/* 700 */     if ((paramInt1 >= paramInt2) || (!paramPredicate.apply(paramArrayOfShort[paramInt1]))) {
/* 701 */       return paramInt1;
/*     */     }
/* 703 */     return paramInt2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/SHORT/Modification.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */