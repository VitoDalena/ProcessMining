/*     */ package jal.String;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Modification
/*     */ {
/*     */   public static void copy(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  68 */     if (paramInt2 > paramInt1) {
/*  69 */       System.arraycopy(paramArrayOfString1, paramInt1, paramArrayOfString2, paramInt3, paramInt2 - paramInt1);
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
/*     */   public static void swap_ranges(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  87 */     while (paramInt1 < paramInt2) {
/*  88 */       String str = paramArrayOfString2[paramInt3];
/*  89 */       paramArrayOfString2[paramInt3] = paramArrayOfString1[paramInt1];
/*  90 */       paramArrayOfString1[paramInt1] = str;
/*  91 */       paramInt1++;
/*  92 */       paramInt3++;
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
/*     */   public static void transform(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, UnaryOperator paramUnaryOperator)
/*     */   {
/* 116 */     while (paramInt1 < paramInt2) {
/* 117 */       paramArrayOfString2[(paramInt3++)] = paramUnaryOperator.apply(paramArrayOfString1[(paramInt1++)]);
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
/*     */   public static void transform(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryOperator paramBinaryOperator)
/*     */   {
/* 144 */     while (paramInt1 < paramInt2) {
/* 145 */       paramArrayOfString3[(paramInt4++)] = paramBinaryOperator.apply(paramArrayOfString1[(paramInt1++)], paramArrayOfString2[(paramInt3++)]);
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
/*     */   public static void replace(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString1, String paramString2)
/*     */   {
/* 160 */     while (paramInt1 < paramInt2) {
/* 161 */       if (paramArrayOfString[paramInt1].equals(paramString1))
/* 162 */         paramArrayOfString[paramInt1] = paramString2;
/* 163 */       paramInt1++;
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
/*     */   public static void replace_if(String[] paramArrayOfString, int paramInt1, int paramInt2, Predicate paramPredicate, String paramString)
/*     */   {
/* 180 */     while (paramInt1 < paramInt2) {
/* 181 */       if (paramPredicate.apply(paramArrayOfString[paramInt1]))
/* 182 */         paramArrayOfString[paramInt1] = paramString;
/* 183 */       paramInt1++;
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
/*     */   public static void replace_copy(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2)
/*     */   {
/* 206 */     while (paramInt1 < paramInt2) {
/* 207 */       String str = paramArrayOfString1[(paramInt1++)];
/* 208 */       paramArrayOfString2[(paramInt3++)] = (str.equals(paramString1) ? paramString2 : str);
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
/*     */   public static void replace_copy_if(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate, String paramString)
/*     */   {
/* 231 */     while (paramInt1 < paramInt2) {
/* 232 */       String str = paramArrayOfString1[(paramInt1++)];
/* 233 */       paramArrayOfString2[(paramInt3++)] = (paramPredicate.apply(str) ? paramString : str);
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
/*     */   public static void fill(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString)
/*     */   {
/* 248 */     while (paramInt1 < paramInt2) {
/* 249 */       paramArrayOfString[(paramInt1++)] = paramString;
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
/*     */   public static void generate(String[] paramArrayOfString, int paramInt1, int paramInt2, Generator paramGenerator)
/*     */   {
/* 266 */     while (paramInt1 < paramInt2) {
/* 267 */       paramArrayOfString[(paramInt1++)] = paramGenerator.apply();
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
/*     */   public static int remove_if(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString)
/*     */   {
/* 284 */     int i = paramInt2;
/* 285 */     paramInt1--;
/*     */     for (;;) {
/* 287 */       paramInt1++; if (paramInt1 < paramInt2) { if (!paramArrayOfString[paramInt1].equals(paramString)) {}
/* 288 */       } else { while ((paramInt1 < --paramInt2) && (paramArrayOfString[paramInt2].equals(paramString))) {}
/* 289 */         if (paramInt1 >= paramInt2) {
/* 290 */           return paramInt1;
/*     */         }
/* 292 */         paramArrayOfString[paramInt1] = paramArrayOfString[paramInt2];
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
/*     */   public static int remove_if(String[] paramArrayOfString, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 311 */     int i = paramInt2;
/* 312 */     paramInt1--;
/*     */     for (;;) {
/* 314 */       paramInt1++; if (paramInt1 < paramInt2) { if (!paramPredicate.apply(paramArrayOfString[paramInt1])) {}
/* 315 */       } else { while ((paramInt1 < --paramInt2) && (paramPredicate.apply(paramArrayOfString[paramInt2]))) {}
/* 316 */         if (paramInt1 >= paramInt2) {
/* 317 */           return paramInt1;
/*     */         }
/* 319 */         paramArrayOfString[paramInt1] = paramArrayOfString[paramInt2];
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
/*     */   public static int stable_remove(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString)
/*     */   {
/* 337 */     paramInt1 = Inspection.find(paramArrayOfString, paramInt1, paramInt2, paramString);
/* 338 */     int i = Inspection.find_not(paramArrayOfString, paramInt1, paramInt2, paramString);
/* 339 */     while (i < paramInt2) {
/* 340 */       paramArrayOfString[(paramInt1++)] = paramArrayOfString[i];
/* 341 */       i = Inspection.find_not(paramArrayOfString, ++i, paramInt2, paramString);
/*     */     }
/* 343 */     return paramInt1;
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
/*     */   public static int stable_remove_if(String[] paramArrayOfString, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 360 */     paramInt1 = Inspection.find_if(paramArrayOfString, paramInt1, paramInt2, paramPredicate);
/* 361 */     int i = Inspection.find_if_not(paramArrayOfString, paramInt1, paramInt2, paramPredicate);
/* 362 */     while (i < paramInt2) {
/* 363 */       paramArrayOfString[(paramInt1++)] = paramArrayOfString[i];
/* 364 */       i = Inspection.find_if_not(paramArrayOfString, ++i, paramInt2, paramPredicate);
/*     */     }
/* 366 */     return paramInt1;
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
/*     */   public static int remove_copy(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, String paramString)
/*     */   {
/* 388 */     while (paramInt1 < paramInt2) {
/* 389 */       String str = paramArrayOfString1[(paramInt1++)];
/* 390 */       if (!str.equals(paramString))
/* 391 */         paramArrayOfString2[(paramInt3++)] = str;
/*     */     }
/* 393 */     return paramInt3;
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
/*     */   public static int remove_copy_if(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate)
/*     */   {
/* 415 */     while (paramInt1 < paramInt2) {
/* 416 */       String str = paramArrayOfString1[(paramInt1++)];
/* 417 */       if (!paramPredicate.apply(str))
/* 418 */         paramArrayOfString2[(paramInt3++)] = str;
/*     */     }
/* 420 */     return paramInt3;
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
/*     */   public static int unique(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*     */   {
/* 436 */     paramInt1 = Inspection.adjacent_find(paramArrayOfString, paramInt1, paramInt2);
/* 437 */     return unique_copy(paramArrayOfString, paramArrayOfString, paramInt1, paramInt2, paramInt1);
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
/*     */   public static int unique(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 456 */     paramInt1 = Inspection.adjacent_find(paramArrayOfString, paramInt1, paramInt2, paramBinaryPredicate);
/* 457 */     return unique_copy(paramArrayOfString, paramArrayOfString, paramInt1, paramInt2, paramInt1, paramBinaryPredicate);
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
/*     */   public static int unique_copy(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 479 */     if (paramInt1 >= paramInt2) {
/* 480 */       return paramInt3;
/*     */     }
/* 482 */     paramArrayOfString2[paramInt3] = paramArrayOfString1[paramInt1];
/*     */     do
/*     */     {
/* 485 */       if (!paramArrayOfString2[paramInt3].equals(paramArrayOfString1[paramInt1])) {
/* 486 */         paramArrayOfString2[(++paramInt3)] = paramArrayOfString1[paramInt1];
/*     */       }
/* 484 */       paramInt1++; } while (paramInt1 < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 489 */     return paramInt3 + 1;
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
/*     */   public static int unique_copy(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 514 */     if (paramInt1 >= paramInt2) {
/* 515 */       return paramInt3;
/*     */     }
/* 517 */     paramArrayOfString2[paramInt3] = paramArrayOfString1[paramInt1];
/*     */     do
/*     */     {
/* 520 */       if (!paramBinaryPredicate.apply(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1])) {
/* 521 */         paramArrayOfString2[(++paramInt3)] = paramArrayOfString1[paramInt1];
/*     */       }
/* 519 */       paramInt1++; } while (paramInt1 < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 524 */     return paramInt3 + 1;
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
/*     */   public static void reverse(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*     */   {
/* 537 */     while (paramInt1 < --paramInt2) {
/* 538 */       String str = paramArrayOfString[paramInt1];
/* 539 */       paramArrayOfString[(paramInt1++)] = paramArrayOfString[paramInt2];
/* 540 */       paramArrayOfString[paramInt2] = str;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void reverse_copy(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 546 */     while (paramInt2 > paramInt1) {
/* 547 */       paramArrayOfString[(paramInt3++)] = paramArrayOfString[(--paramInt2)];
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
/*     */   public static void reverse_copy(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 566 */     while (paramInt2 > paramInt1) {
/* 567 */       paramArrayOfString2[(paramInt3++)] = paramArrayOfString1[(--paramInt2)];
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
/*     */   public static void rotate(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 585 */     if ((paramInt2 != paramInt1) && (paramInt2 != paramInt3)) {
/* 586 */       reverse(paramArrayOfString, paramInt1, paramInt2);
/* 587 */       reverse(paramArrayOfString, paramInt2, paramInt3);
/* 588 */       reverse(paramArrayOfString, paramInt1, paramInt3);
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
/*     */   public static void rotate_copy(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 611 */     copy(paramArrayOfString1, paramArrayOfString2, paramInt2, paramInt3, paramInt4);
/* 612 */     copy(paramArrayOfString1, paramArrayOfString2, paramInt1, paramInt2, paramInt4 + (paramInt3 - paramInt2));
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
/*     */   public static void random_shuffle(String[] paramArrayOfString, int paramInt1, int paramInt2, Random paramRandom)
/*     */   {
/* 626 */     for (int i = paramInt1 + 1; i < paramInt2; i++) {
/* 627 */       int j = Math.abs(paramRandom.nextInt()) % (i - paramInt1 + 1);
/*     */       
/* 629 */       String str = paramArrayOfString[j];
/* 630 */       paramArrayOfString[j] = paramArrayOfString[i];
/* 631 */       paramArrayOfString[i] = str;
/*     */     }
/*     */   }
/*     */   
/* 635 */   private static Random default_RNG = new Random();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void random_shuffle(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*     */   {
/* 646 */     random_shuffle(paramArrayOfString, paramInt1, paramInt2, default_RNG);
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
/*     */   public static int partition(String[] paramArrayOfString, int paramInt1, int paramInt2, Predicate paramPredicate)
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
/* 669 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramPredicate.apply(paramArrayOfString[paramInt1])) {}
/* 670 */       } else { while ((paramInt1 < --paramInt2) && (!paramPredicate.apply(paramArrayOfString[paramInt2]))) {}
/* 671 */         if (paramInt1 >= paramInt2) return paramInt1;
/* 672 */         String str = paramArrayOfString[paramInt1];
/* 673 */         paramArrayOfString[paramInt1] = paramArrayOfString[paramInt2];
/* 674 */         paramArrayOfString[paramInt2] = str;
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
/*     */   public static int stable_partition(String[] paramArrayOfString, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 698 */     if (paramInt1 + 1 < paramInt2) {
/* 699 */       int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 700 */       int j = stable_partition(paramArrayOfString, paramInt1, i, paramPredicate);
/* 701 */       int k = stable_partition(paramArrayOfString, i, paramInt2, paramPredicate);
/* 702 */       rotate(paramArrayOfString, j, i, k);
/* 703 */       return j + (k - i);
/*     */     }
/* 705 */     if ((paramInt1 >= paramInt2) || (!paramPredicate.apply(paramArrayOfString[paramInt1]))) {
/* 706 */       return paramInt1;
/*     */     }
/* 708 */     return paramInt2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/String/Modification.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */