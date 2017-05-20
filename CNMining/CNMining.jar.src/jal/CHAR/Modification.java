/*     */ package jal.CHAR;
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
/*     */   public static void copy(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  63 */     if (paramInt2 > paramInt1) {
/*  64 */       System.arraycopy(paramArrayOfChar1, paramInt1, paramArrayOfChar2, paramInt3, paramInt2 - paramInt1);
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
/*     */   public static void swap_ranges(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  82 */     while (paramInt1 < paramInt2) {
/*  83 */       int i = paramArrayOfChar2[paramInt3];
/*  84 */       paramArrayOfChar2[paramInt3] = paramArrayOfChar1[paramInt1];
/*  85 */       paramArrayOfChar1[paramInt1] = i;
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
/*     */   public static void transform(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3, UnaryOperator paramUnaryOperator)
/*     */   {
/* 111 */     while (paramInt1 < paramInt2) {
/* 112 */       paramArrayOfChar2[(paramInt3++)] = paramUnaryOperator.apply(paramArrayOfChar1[(paramInt1++)]);
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
/*     */   public static void transform(char[] paramArrayOfChar1, char[] paramArrayOfChar2, char[] paramArrayOfChar3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryOperator paramBinaryOperator)
/*     */   {
/* 139 */     while (paramInt1 < paramInt2) {
/* 140 */       paramArrayOfChar3[(paramInt4++)] = paramBinaryOperator.apply(paramArrayOfChar1[(paramInt1++)], paramArrayOfChar2[(paramInt3++)]);
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
/*     */   public static void replace(char[] paramArrayOfChar, int paramInt1, int paramInt2, char paramChar1, char paramChar2)
/*     */   {
/* 155 */     while (paramInt1 < paramInt2) {
/* 156 */       if (paramArrayOfChar[paramInt1] == paramChar1)
/* 157 */         paramArrayOfChar[paramInt1] = paramChar2;
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
/*     */   public static void replace_if(char[] paramArrayOfChar, int paramInt1, int paramInt2, Predicate paramPredicate, char paramChar)
/*     */   {
/* 175 */     while (paramInt1 < paramInt2) {
/* 176 */       if (paramPredicate.apply(paramArrayOfChar[paramInt1]))
/* 177 */         paramArrayOfChar[paramInt1] = paramChar;
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
/*     */   public static void replace_copy(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3, char paramChar1, char paramChar2)
/*     */   {
/* 201 */     while (paramInt1 < paramInt2) {
/* 202 */       char c = paramArrayOfChar1[(paramInt1++)];
/* 203 */       paramArrayOfChar2[(paramInt3++)] = (c == paramChar1 ? paramChar2 : c);
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
/*     */   public static void replace_copy_if(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate, char paramChar)
/*     */   {
/* 226 */     while (paramInt1 < paramInt2) {
/* 227 */       char c = paramArrayOfChar1[(paramInt1++)];
/* 228 */       paramArrayOfChar2[(paramInt3++)] = (paramPredicate.apply(c) ? paramChar : c);
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
/*     */   public static void fill(char[] paramArrayOfChar, int paramInt1, int paramInt2, char paramChar)
/*     */   {
/* 243 */     while (paramInt1 < paramInt2) {
/* 244 */       paramArrayOfChar[(paramInt1++)] = paramChar;
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
/*     */   public static void generate(char[] paramArrayOfChar, int paramInt1, int paramInt2, Generator paramGenerator)
/*     */   {
/* 261 */     while (paramInt1 < paramInt2) {
/* 262 */       paramArrayOfChar[(paramInt1++)] = paramGenerator.apply();
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
/*     */   public static int remove_if(char[] paramArrayOfChar, int paramInt1, int paramInt2, char paramChar)
/*     */   {
/* 279 */     int i = paramInt2;
/* 280 */     paramInt1--;
/*     */     for (;;) {
/* 282 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramArrayOfChar[paramInt1] != paramChar) {}
/* 283 */       } else { while ((paramInt1 < --paramInt2) && (paramArrayOfChar[paramInt2] == paramChar)) {}
/* 284 */         if (paramInt1 >= paramInt2) {
/* 285 */           return paramInt1;
/*     */         }
/* 287 */         paramArrayOfChar[paramInt1] = paramArrayOfChar[paramInt2];
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
/*     */   public static int remove_if(char[] paramArrayOfChar, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 306 */     int i = paramInt2;
/* 307 */     paramInt1--;
/*     */     for (;;) {
/* 309 */       paramInt1++; if (paramInt1 < paramInt2) { if (!paramPredicate.apply(paramArrayOfChar[paramInt1])) {}
/* 310 */       } else { while ((paramInt1 < --paramInt2) && (paramPredicate.apply(paramArrayOfChar[paramInt2]))) {}
/* 311 */         if (paramInt1 >= paramInt2) {
/* 312 */           return paramInt1;
/*     */         }
/* 314 */         paramArrayOfChar[paramInt1] = paramArrayOfChar[paramInt2];
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
/*     */   public static int stable_remove(char[] paramArrayOfChar, int paramInt1, int paramInt2, char paramChar)
/*     */   {
/* 332 */     paramInt1 = Inspection.find(paramArrayOfChar, paramInt1, paramInt2, paramChar);
/* 333 */     int i = Inspection.find_not(paramArrayOfChar, paramInt1, paramInt2, paramChar);
/* 334 */     while (i < paramInt2) {
/* 335 */       paramArrayOfChar[(paramInt1++)] = paramArrayOfChar[i];
/* 336 */       i = Inspection.find_not(paramArrayOfChar, ++i, paramInt2, paramChar);
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
/*     */   public static int stable_remove_if(char[] paramArrayOfChar, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 355 */     paramInt1 = Inspection.find_if(paramArrayOfChar, paramInt1, paramInt2, paramPredicate);
/* 356 */     int i = Inspection.find_if_not(paramArrayOfChar, paramInt1, paramInt2, paramPredicate);
/* 357 */     while (i < paramInt2) {
/* 358 */       paramArrayOfChar[(paramInt1++)] = paramArrayOfChar[i];
/* 359 */       i = Inspection.find_if_not(paramArrayOfChar, ++i, paramInt2, paramPredicate);
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
/*     */   public static int remove_copy(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3, char paramChar)
/*     */   {
/* 383 */     while (paramInt1 < paramInt2) {
/* 384 */       char c = paramArrayOfChar1[(paramInt1++)];
/* 385 */       if (c != paramChar)
/* 386 */         paramArrayOfChar2[(paramInt3++)] = c;
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
/*     */   public static int remove_copy_if(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate)
/*     */   {
/* 410 */     while (paramInt1 < paramInt2) {
/* 411 */       char c = paramArrayOfChar1[(paramInt1++)];
/* 412 */       if (!paramPredicate.apply(c))
/* 413 */         paramArrayOfChar2[(paramInt3++)] = c;
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
/*     */   public static int unique(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*     */   {
/* 431 */     paramInt1 = Inspection.adjacent_find(paramArrayOfChar, paramInt1, paramInt2);
/* 432 */     return unique_copy(paramArrayOfChar, paramArrayOfChar, paramInt1, paramInt2, paramInt1);
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
/*     */   public static int unique(char[] paramArrayOfChar, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 451 */     paramInt1 = Inspection.adjacent_find(paramArrayOfChar, paramInt1, paramInt2, paramBinaryPredicate);
/* 452 */     return unique_copy(paramArrayOfChar, paramArrayOfChar, paramInt1, paramInt2, paramInt1, paramBinaryPredicate);
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
/*     */   public static int unique_copy(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 474 */     if (paramInt1 >= paramInt2) {
/* 475 */       return paramInt3;
/*     */     }
/* 477 */     paramArrayOfChar2[paramInt3] = paramArrayOfChar1[paramInt1];
/*     */     do
/*     */     {
/* 480 */       if (paramArrayOfChar2[paramInt3] != paramArrayOfChar1[paramInt1]) {
/* 481 */         paramArrayOfChar2[(++paramInt3)] = paramArrayOfChar1[paramInt1];
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
/*     */   public static int unique_copy(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 509 */     if (paramInt1 >= paramInt2) {
/* 510 */       return paramInt3;
/*     */     }
/* 512 */     paramArrayOfChar2[paramInt3] = paramArrayOfChar1[paramInt1];
/*     */     do
/*     */     {
/* 515 */       if (!paramBinaryPredicate.apply(paramArrayOfChar2[paramInt3], paramArrayOfChar1[paramInt1])) {
/* 516 */         paramArrayOfChar2[(++paramInt3)] = paramArrayOfChar1[paramInt1];
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
/*     */   public static void reverse(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*     */   {
/* 532 */     while (paramInt1 < --paramInt2) {
/* 533 */       int i = paramArrayOfChar[paramInt1];
/* 534 */       paramArrayOfChar[(paramInt1++)] = paramArrayOfChar[paramInt2];
/* 535 */       paramArrayOfChar[paramInt2] = i;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void reverse_copy(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 541 */     while (paramInt2 > paramInt1) {
/* 542 */       paramArrayOfChar[(paramInt3++)] = paramArrayOfChar[(--paramInt2)];
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
/*     */   public static void reverse_copy(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 561 */     while (paramInt2 > paramInt1) {
/* 562 */       paramArrayOfChar2[(paramInt3++)] = paramArrayOfChar1[(--paramInt2)];
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
/*     */   public static void rotate(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 580 */     if ((paramInt2 != paramInt1) && (paramInt2 != paramInt3)) {
/* 581 */       reverse(paramArrayOfChar, paramInt1, paramInt2);
/* 582 */       reverse(paramArrayOfChar, paramInt2, paramInt3);
/* 583 */       reverse(paramArrayOfChar, paramInt1, paramInt3);
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
/*     */   public static void rotate_copy(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 606 */     copy(paramArrayOfChar1, paramArrayOfChar2, paramInt2, paramInt3, paramInt4);
/* 607 */     copy(paramArrayOfChar1, paramArrayOfChar2, paramInt1, paramInt2, paramInt4 + (paramInt3 - paramInt2));
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
/*     */   public static void random_shuffle(char[] paramArrayOfChar, int paramInt1, int paramInt2, Random paramRandom)
/*     */   {
/* 621 */     for (int i = paramInt1 + 1; i < paramInt2; i++) {
/* 622 */       int j = Math.abs(paramRandom.nextInt()) % (i - paramInt1 + 1);
/*     */       
/* 624 */       int k = paramArrayOfChar[j];
/* 625 */       paramArrayOfChar[j] = paramArrayOfChar[i];
/* 626 */       paramArrayOfChar[i] = k;
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
/*     */   public static void random_shuffle(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*     */   {
/* 641 */     random_shuffle(paramArrayOfChar, paramInt1, paramInt2, default_RNG);
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
/*     */   public static int partition(char[] paramArrayOfChar, int paramInt1, int paramInt2, Predicate paramPredicate)
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
/* 664 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramPredicate.apply(paramArrayOfChar[paramInt1])) {}
/* 665 */       } else { while ((paramInt1 < --paramInt2) && (!paramPredicate.apply(paramArrayOfChar[paramInt2]))) {}
/* 666 */         if (paramInt1 >= paramInt2) return paramInt1;
/* 667 */         int i = paramArrayOfChar[paramInt1];
/* 668 */         paramArrayOfChar[paramInt1] = paramArrayOfChar[paramInt2];
/* 669 */         paramArrayOfChar[paramInt2] = i;
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
/*     */   public static int stable_partition(char[] paramArrayOfChar, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 693 */     if (paramInt1 + 1 < paramInt2) {
/* 694 */       int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 695 */       int j = stable_partition(paramArrayOfChar, paramInt1, i, paramPredicate);
/* 696 */       int k = stable_partition(paramArrayOfChar, i, paramInt2, paramPredicate);
/* 697 */       rotate(paramArrayOfChar, j, i, k);
/* 698 */       return j + (k - i);
/*     */     }
/* 700 */     if ((paramInt1 >= paramInt2) || (!paramPredicate.apply(paramArrayOfChar[paramInt1]))) {
/* 701 */       return paramInt1;
/*     */     }
/* 703 */     return paramInt2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/CHAR/Modification.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */