/*     */ package jal.Object;
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
/*     */ 
/*     */ public final class Modification
/*     */ {
/*     */   public static void copy(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  69 */     if (paramInt2 > paramInt1) {
/*  70 */       System.arraycopy(paramArrayOfObject1, paramInt1, paramArrayOfObject2, paramInt3, paramInt2 - paramInt1);
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
/*     */   public static void swap_ranges(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  88 */     while (paramInt1 < paramInt2) {
/*  89 */       Object localObject = paramArrayOfObject2[paramInt3];
/*  90 */       paramArrayOfObject2[paramInt3] = paramArrayOfObject1[paramInt1];
/*  91 */       paramArrayOfObject1[paramInt1] = localObject;
/*  92 */       paramInt1++;
/*  93 */       paramInt3++;
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
/*     */   public static void transform(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, UnaryOperator paramUnaryOperator)
/*     */   {
/* 117 */     while (paramInt1 < paramInt2) {
/* 118 */       paramArrayOfObject2[(paramInt3++)] = paramUnaryOperator.apply(paramArrayOfObject1[(paramInt1++)]);
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
/*     */   public static void transform(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Object[] paramArrayOfObject3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryOperator paramBinaryOperator)
/*     */   {
/* 145 */     while (paramInt1 < paramInt2) {
/* 146 */       paramArrayOfObject3[(paramInt4++)] = paramBinaryOperator.apply(paramArrayOfObject1[(paramInt1++)], paramArrayOfObject2[(paramInt3++)]);
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
/*     */   public static void replace(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject1, Object paramObject2)
/*     */   {
/* 161 */     while (paramInt1 < paramInt2) {
/* 162 */       if (paramArrayOfObject[paramInt1] == paramObject1)
/* 163 */         paramArrayOfObject[paramInt1] = paramObject2;
/* 164 */       paramInt1++;
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
/*     */   public static void replace_if(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Predicate paramPredicate, Object paramObject)
/*     */   {
/* 181 */     while (paramInt1 < paramInt2) {
/* 182 */       if (paramPredicate.apply(paramArrayOfObject[paramInt1]))
/* 183 */         paramArrayOfObject[paramInt1] = paramObject;
/* 184 */       paramInt1++;
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
/*     */   public static void replace_copy(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, Object paramObject1, Object paramObject2)
/*     */   {
/* 207 */     while (paramInt1 < paramInt2) {
/* 208 */       Object localObject = paramArrayOfObject1[(paramInt1++)];
/* 209 */       paramArrayOfObject2[(paramInt3++)] = (localObject == paramObject1 ? paramObject2 : localObject);
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
/*     */   public static void replace_copy_if(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate, Object paramObject)
/*     */   {
/* 232 */     while (paramInt1 < paramInt2) {
/* 233 */       Object localObject = paramArrayOfObject1[(paramInt1++)];
/* 234 */       paramArrayOfObject2[(paramInt3++)] = (paramPredicate.apply(localObject) ? paramObject : localObject);
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
/*     */   public static void fill(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject)
/*     */   {
/* 249 */     while (paramInt1 < paramInt2) {
/* 250 */       paramArrayOfObject[(paramInt1++)] = paramObject;
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
/*     */   public static void generate(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Generator paramGenerator)
/*     */   {
/* 267 */     while (paramInt1 < paramInt2) {
/* 268 */       paramArrayOfObject[(paramInt1++)] = paramGenerator.apply();
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
/*     */   public static int remove_if(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject)
/*     */   {
/* 285 */     int i = paramInt2;
/* 286 */     paramInt1--;
/*     */     for (;;) {
/* 288 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramArrayOfObject[paramInt1] != paramObject) {}
/* 289 */       } else { while ((paramInt1 < --paramInt2) && (paramArrayOfObject[paramInt2] == paramObject)) {}
/* 290 */         if (paramInt1 >= paramInt2) {
/* 291 */           return paramInt1;
/*     */         }
/* 293 */         paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
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
/*     */   public static int remove_if(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 312 */     int i = paramInt2;
/* 313 */     paramInt1--;
/*     */     for (;;) {
/* 315 */       paramInt1++; if (paramInt1 < paramInt2) { if (!paramPredicate.apply(paramArrayOfObject[paramInt1])) {}
/* 316 */       } else { while ((paramInt1 < --paramInt2) && (paramPredicate.apply(paramArrayOfObject[paramInt2]))) {}
/* 317 */         if (paramInt1 >= paramInt2) {
/* 318 */           return paramInt1;
/*     */         }
/* 320 */         paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
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
/*     */   public static int stable_remove(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject)
/*     */   {
/* 338 */     paramInt1 = Inspection.find(paramArrayOfObject, paramInt1, paramInt2, paramObject);
/* 339 */     int i = Inspection.find_not(paramArrayOfObject, paramInt1, paramInt2, paramObject);
/* 340 */     while (i < paramInt2) {
/* 341 */       paramArrayOfObject[(paramInt1++)] = paramArrayOfObject[i];
/* 342 */       i = Inspection.find_not(paramArrayOfObject, ++i, paramInt2, paramObject);
/*     */     }
/* 344 */     return paramInt1;
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
/*     */   public static int stable_remove_if(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 361 */     paramInt1 = Inspection.find_if(paramArrayOfObject, paramInt1, paramInt2, paramPredicate);
/* 362 */     int i = Inspection.find_if_not(paramArrayOfObject, paramInt1, paramInt2, paramPredicate);
/* 363 */     while (i < paramInt2) {
/* 364 */       paramArrayOfObject[(paramInt1++)] = paramArrayOfObject[i];
/* 365 */       i = Inspection.find_if_not(paramArrayOfObject, ++i, paramInt2, paramPredicate);
/*     */     }
/* 367 */     return paramInt1;
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
/*     */   public static int remove_copy(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, Object paramObject)
/*     */   {
/* 389 */     while (paramInt1 < paramInt2) {
/* 390 */       Object localObject = paramArrayOfObject1[(paramInt1++)];
/* 391 */       if (localObject != paramObject)
/* 392 */         paramArrayOfObject2[(paramInt3++)] = localObject;
/*     */     }
/* 394 */     return paramInt3;
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
/*     */   public static int remove_copy_if(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, Predicate paramPredicate)
/*     */   {
/* 416 */     while (paramInt1 < paramInt2) {
/* 417 */       Object localObject = paramArrayOfObject1[(paramInt1++)];
/* 418 */       if (!paramPredicate.apply(localObject))
/* 419 */         paramArrayOfObject2[(paramInt3++)] = localObject;
/*     */     }
/* 421 */     return paramInt3;
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
/*     */   public static int unique(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/* 437 */     paramInt1 = Inspection.adjacent_find(paramArrayOfObject, paramInt1, paramInt2);
/* 438 */     return unique_copy(paramArrayOfObject, paramArrayOfObject, paramInt1, paramInt2, paramInt1);
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
/*     */   public static int unique(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 457 */     paramInt1 = Inspection.adjacent_find(paramArrayOfObject, paramInt1, paramInt2, paramBinaryPredicate);
/* 458 */     return unique_copy(paramArrayOfObject, paramArrayOfObject, paramInt1, paramInt2, paramInt1, paramBinaryPredicate);
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
/*     */   public static int unique_copy(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 480 */     if (paramInt1 >= paramInt2) {
/* 481 */       return paramInt3;
/*     */     }
/* 483 */     paramArrayOfObject2[paramInt3] = paramArrayOfObject1[paramInt1];
/*     */     do
/*     */     {
/* 486 */       if (paramArrayOfObject2[paramInt3] != paramArrayOfObject1[paramInt1]) {
/* 487 */         paramArrayOfObject2[(++paramInt3)] = paramArrayOfObject1[paramInt1];
/*     */       }
/* 485 */       paramInt1++; } while (paramInt1 < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 490 */     return paramInt3 + 1;
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
/*     */   public static int unique_copy(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 515 */     if (paramInt1 >= paramInt2) {
/* 516 */       return paramInt3;
/*     */     }
/* 518 */     paramArrayOfObject2[paramInt3] = paramArrayOfObject1[paramInt1];
/*     */     do
/*     */     {
/* 521 */       if (!paramBinaryPredicate.apply(paramArrayOfObject2[paramInt3], paramArrayOfObject1[paramInt1])) {
/* 522 */         paramArrayOfObject2[(++paramInt3)] = paramArrayOfObject1[paramInt1];
/*     */       }
/* 520 */       paramInt1++; } while (paramInt1 < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 525 */     return paramInt3 + 1;
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
/*     */   public static void reverse(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/* 538 */     while (paramInt1 < --paramInt2) {
/* 539 */       Object localObject = paramArrayOfObject[paramInt1];
/* 540 */       paramArrayOfObject[(paramInt1++)] = paramArrayOfObject[paramInt2];
/* 541 */       paramArrayOfObject[paramInt2] = localObject;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void reverse_copy(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 547 */     while (paramInt2 > paramInt1) {
/* 548 */       paramArrayOfObject[(paramInt3++)] = paramArrayOfObject[(--paramInt2)];
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
/*     */   public static void reverse_copy(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 567 */     while (paramInt2 > paramInt1) {
/* 568 */       paramArrayOfObject2[(paramInt3++)] = paramArrayOfObject1[(--paramInt2)];
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
/*     */   public static void rotate(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 586 */     if ((paramInt2 != paramInt1) && (paramInt2 != paramInt3)) {
/* 587 */       reverse(paramArrayOfObject, paramInt1, paramInt2);
/* 588 */       reverse(paramArrayOfObject, paramInt2, paramInt3);
/* 589 */       reverse(paramArrayOfObject, paramInt1, paramInt3);
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
/*     */   public static void rotate_copy(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 612 */     copy(paramArrayOfObject1, paramArrayOfObject2, paramInt2, paramInt3, paramInt4);
/* 613 */     copy(paramArrayOfObject1, paramArrayOfObject2, paramInt1, paramInt2, paramInt4 + (paramInt3 - paramInt2));
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
/*     */   public static void random_shuffle(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Random paramRandom)
/*     */   {
/* 627 */     for (int i = paramInt1 + 1; i < paramInt2; i++) {
/* 628 */       int j = Math.abs(paramRandom.nextInt()) % (i - paramInt1 + 1);
/*     */       
/* 630 */       Object localObject = paramArrayOfObject[j];
/* 631 */       paramArrayOfObject[j] = paramArrayOfObject[i];
/* 632 */       paramArrayOfObject[i] = localObject;
/*     */     }
/*     */   }
/*     */   
/* 636 */   private static Random default_RNG = new Random();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void random_shuffle(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/* 647 */     random_shuffle(paramArrayOfObject, paramInt1, paramInt2, default_RNG);
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
/*     */   public static int partition(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Predicate paramPredicate)
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
/* 670 */       paramInt1++; if (paramInt1 < paramInt2) { if (paramPredicate.apply(paramArrayOfObject[paramInt1])) {}
/* 671 */       } else { while ((paramInt1 < --paramInt2) && (!paramPredicate.apply(paramArrayOfObject[paramInt2]))) {}
/* 672 */         if (paramInt1 >= paramInt2) return paramInt1;
/* 673 */         Object localObject = paramArrayOfObject[paramInt1];
/* 674 */         paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/* 675 */         paramArrayOfObject[paramInt2] = localObject;
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
/*     */   public static int stable_partition(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 699 */     if (paramInt1 + 1 < paramInt2) {
/* 700 */       int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 701 */       int j = stable_partition(paramArrayOfObject, paramInt1, i, paramPredicate);
/* 702 */       int k = stable_partition(paramArrayOfObject, i, paramInt2, paramPredicate);
/* 703 */       rotate(paramArrayOfObject, j, i, k);
/* 704 */       return j + (k - i);
/*     */     }
/* 706 */     if ((paramInt1 >= paramInt2) || (!paramPredicate.apply(paramArrayOfObject[paramInt1]))) {
/* 707 */       return paramInt1;
/*     */     }
/* 709 */     return paramInt2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/Object/Modification.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */