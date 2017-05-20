/*      */ package jal.Object;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class Sorting
/*      */ {
/*      */   private static final int partitionCutoff = 13;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int qsort_stacksize = 56;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int stableSortCutoff = 9;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void sort(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*   77 */     if (paramInt2 - paramInt1 >= 13)
/*   78 */       qsortLoop(paramArrayOfObject, paramInt1, paramInt2, paramBinaryPredicate);
/*   79 */     insertion_sort(paramArrayOfObject, paramInt1, paramInt2, paramBinaryPredicate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void insertion_sort(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*   98 */     int i = paramInt1;
/*   99 */     do { Object localObject1 = paramArrayOfObject[i];
/*  100 */       int j = i;
/*  101 */       for (Object localObject2 = paramArrayOfObject[(j - 1)]; 
/*  102 */           paramBinaryPredicate.apply(localObject1, localObject2); 
/*  103 */           localObject2 = paramArrayOfObject[(--j - 1)]) {
/*  104 */         paramArrayOfObject[j] = localObject2;
/*  105 */         if (paramInt1 == j - 1) {
/*  106 */           j--;
/*  107 */           break;
/*      */         }
/*      */       }
/*  110 */       paramArrayOfObject[j] = localObject1;i++;
/*   98 */     } while (i < paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static int quickPartition(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  118 */     Object localObject1 = paramArrayOfObject[paramInt1];
/*  119 */     Object localObject2 = paramArrayOfObject[(paramInt2 - 1)];
/*  120 */     Object localObject3 = paramArrayOfObject[(paramInt1 + (paramInt2 - paramInt1) / 2)];
/*      */     
/*  122 */     if (paramBinaryPredicate.apply(localObject3, localObject1)) {
/*  123 */       if (paramBinaryPredicate.apply(localObject1, localObject2)) {
/*  124 */         localObject3 = localObject1;
/*  125 */       } else if (paramBinaryPredicate.apply(localObject3, localObject2)) {
/*  126 */         localObject3 = localObject2;
/*      */       }
/*  128 */     } else if (paramBinaryPredicate.apply(localObject2, localObject1)) {
/*  129 */       localObject3 = localObject1;
/*  130 */     } else if (paramBinaryPredicate.apply(localObject2, localObject3)) {
/*  131 */       localObject3 = localObject2;
/*      */     }
/*  133 */     paramInt1--;
/*      */     for (;;) {
/*  135 */       if (!paramBinaryPredicate.apply(paramArrayOfObject[(++paramInt1)], localObject3))
/*      */       {
/*      */ 
/*  138 */         while ((goto 142) || (paramBinaryPredicate.apply(localObject3, paramArrayOfObject[(--paramInt2)]))) {}
/*      */         
/*      */ 
/*  141 */         if (paramInt1 >= paramInt2) {
/*  142 */           return paramInt1;
/*      */         }
/*  144 */         Object localObject4 = paramArrayOfObject[paramInt1];
/*  145 */         paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/*  146 */         paramArrayOfObject[paramInt2] = localObject4;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void qsortLoop(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  157 */     int[] arrayOfInt = new int[56];
/*  158 */     int i = 0;
/*      */     for (;;) {
/*  160 */       int j = quickPartition(paramArrayOfObject, paramInt1, paramInt2, paramBinaryPredicate);
/*      */       
/*  162 */       if (paramInt2 - j < 13) {
/*  163 */         if (j - paramInt1 < 13) {
/*  164 */           if (i == 0)
/*  165 */             return;
/*  166 */           paramInt2 = arrayOfInt[(--i)];
/*  167 */           paramInt1 = arrayOfInt[(--i)];
/*      */         }
/*      */         else {
/*  170 */           paramInt2 = j;
/*      */         }
/*  172 */       } else if (j - paramInt1 < 13) {
/*  173 */         paramInt1 = j;
/*      */       }
/*  175 */       else if (paramInt2 - j > j - paramInt1) {
/*  176 */         arrayOfInt[(i++)] = j;
/*  177 */         arrayOfInt[(i++)] = paramInt2;
/*  178 */         paramInt2 = j;
/*      */       }
/*      */       else {
/*  181 */         arrayOfInt[(i++)] = paramInt1;
/*  182 */         arrayOfInt[(i++)] = j;
/*  183 */         paramInt1 = j;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void stable_sort(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  204 */     if (paramInt2 - paramInt1 < 9) {
/*  205 */       insertion_sort(paramArrayOfObject, paramInt1, paramInt2, paramBinaryPredicate);
/*      */     } else {
/*  207 */       int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  208 */       stable_sort(paramArrayOfObject, paramInt1, i, paramBinaryPredicate);
/*  209 */       stable_sort(paramArrayOfObject, i, paramInt2, paramBinaryPredicate);
/*  210 */       inplace_merge(paramArrayOfObject, paramInt1, i, paramInt2, paramBinaryPredicate);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void partial_sort(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  234 */     make_heap(paramArrayOfObject, paramInt1, paramInt2, paramBinaryPredicate);
/*  235 */     int i = paramInt2;
/*  236 */     while (i < paramInt3) {
/*  237 */       if (paramBinaryPredicate.apply(paramArrayOfObject[i], paramArrayOfObject[paramInt1])) {
/*  238 */         Object localObject = paramArrayOfObject[i];
/*  239 */         paramArrayOfObject[i] = paramArrayOfObject[paramInt1];
/*  240 */         paramArrayOfObject[paramInt1] = localObject;
/*  241 */         adjust_heap(paramArrayOfObject, paramInt1, paramInt1, paramInt2, paramBinaryPredicate);
/*      */       }
/*  243 */       i++;
/*      */     }
/*  245 */     sort_heap(paramArrayOfObject, paramInt1, paramInt2, paramBinaryPredicate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int partial_sort_copy(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  271 */     if (paramInt3 == paramInt4) {
/*  272 */       return paramInt4;
/*      */     }
/*  274 */     int i = Math.min(paramInt2 - paramInt1, paramInt4 - paramInt3);
/*  275 */     Modification.copy(paramArrayOfObject1, paramArrayOfObject2, paramInt1, paramInt1 + i, paramInt3);
/*  276 */     paramInt4 = paramInt3 + i;
/*      */     
/*  278 */     make_heap(paramArrayOfObject2, paramInt3, paramInt4, paramBinaryPredicate);
/*  279 */     for (paramInt1 += i; paramInt1 < paramInt2; paramInt1++) {
/*  280 */       if (paramBinaryPredicate.apply(paramArrayOfObject1[paramInt1], paramArrayOfObject2[paramInt3])) {
/*  281 */         paramArrayOfObject2[paramInt3] = paramArrayOfObject1[paramInt1];
/*  282 */         adjust_heap(paramArrayOfObject2, paramInt3, paramInt3, paramInt4, paramBinaryPredicate);
/*      */       }
/*      */     }
/*      */     
/*  286 */     sort_heap(paramArrayOfObject2, paramInt3, paramInt4, paramBinaryPredicate);
/*      */     
/*  288 */     return paramInt4;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void nth_element(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  311 */     while (paramInt3 - paramInt1 > 3) {
/*  312 */       int i = quickPartition(paramArrayOfObject, paramInt1, paramInt3, paramBinaryPredicate);
/*  313 */       if (i <= paramInt2) {
/*  314 */         paramInt1 = i;
/*      */       } else {
/*  316 */         paramInt3 = i;
/*      */       }
/*      */     }
/*  319 */     insertion_sort(paramArrayOfObject, paramInt1, paramInt3, paramBinaryPredicate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int lower_bound(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  345 */     int i = paramInt2 - paramInt1;
/*  346 */     while (i > 0) {
/*  347 */       int j = i / 2;
/*  348 */       int k = paramInt1 + j;
/*  349 */       if (paramBinaryPredicate.apply(paramArrayOfObject[k], paramObject)) {
/*  350 */         paramInt1 = k + 1;
/*  351 */         i -= j + 1;
/*      */       } else {
/*  353 */         i = j;
/*      */       } }
/*  355 */     return paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int upper_bound(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  381 */     int i = paramInt2 - paramInt1;
/*  382 */     while (i > 0) {
/*  383 */       int j = i / 2;
/*  384 */       int k = paramInt1 + j;
/*  385 */       if (paramBinaryPredicate.apply(paramObject, paramArrayOfObject[k])) {
/*  386 */         i = j;
/*      */       } else {
/*  388 */         paramInt1 = k + 1;
/*  389 */         i -= j + 1;
/*      */       }
/*      */     }
/*  392 */     return paramInt1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Range equal_range(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  422 */     int i = paramInt2 - paramInt1;
/*  423 */     while (i > 0) {
/*  424 */       int j = i / 2;
/*  425 */       int k = paramInt1 + j;
/*  426 */       if (paramBinaryPredicate.apply(paramArrayOfObject[k], paramObject)) {
/*  427 */         paramInt1 = k + 1;
/*  428 */         i = i - j + 1;
/*      */       }
/*  430 */       else if (paramBinaryPredicate.apply(paramObject, paramArrayOfObject[k])) {
/*  431 */         i = j;
/*      */       } else {
/*  433 */         int m = lower_bound(paramArrayOfObject, paramInt1, k, paramObject, paramBinaryPredicate);
/*  434 */         int n = upper_bound(paramArrayOfObject, k + 1, paramInt1 + i, paramObject, paramBinaryPredicate);
/*  435 */         return new Range(paramArrayOfObject, m, n);
/*      */       }
/*      */     }
/*      */     
/*  439 */     return new Range(paramArrayOfObject, paramInt1, paramInt1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean binary_search(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  467 */     int i = lower_bound(paramArrayOfObject, paramInt1, paramInt2, paramObject, paramBinaryPredicate);
/*  468 */     return (i < paramInt2) && (!paramBinaryPredicate.apply(paramObject, paramArrayOfObject[i]));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int merge(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Object[] paramArrayOfObject3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  501 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  502 */       if (paramBinaryPredicate.apply(paramArrayOfObject2[paramInt3], paramArrayOfObject1[paramInt1])) {
/*  503 */         paramArrayOfObject3[(paramInt5++)] = paramArrayOfObject2[(paramInt3++)];
/*      */       } else
/*  505 */         paramArrayOfObject3[(paramInt5++)] = paramArrayOfObject1[(paramInt1++)];
/*      */     }
/*  507 */     Modification.copy(paramArrayOfObject1, paramArrayOfObject3, paramInt1, paramInt2, paramInt5);
/*  508 */     Modification.copy(paramArrayOfObject2, paramArrayOfObject3, paramInt3, paramInt4, paramInt5);
/*  509 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void inplace_merge(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  535 */     if ((paramInt1 >= paramInt2) || (paramInt2 >= paramInt3)) {
/*  536 */       return;
/*      */     }
/*  538 */     if (paramInt3 - paramInt1 == 2) {
/*  539 */       if (paramBinaryPredicate.apply(paramArrayOfObject[paramInt2], paramArrayOfObject[paramInt1])) {
/*  540 */         Object localObject = paramArrayOfObject[paramInt1];
/*  541 */         paramArrayOfObject[paramInt1] = paramArrayOfObject[paramInt2];
/*  542 */         paramArrayOfObject[paramInt2] = localObject;
/*      */       }
/*      */       
/*      */       return;
/*      */     }
/*      */     
/*      */     int i;
/*      */     int j;
/*  550 */     if (paramInt2 - paramInt1 > paramInt3 - paramInt2) {
/*  551 */       i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  552 */       j = lower_bound(paramArrayOfObject, paramInt2, paramInt3, paramArrayOfObject[i], paramBinaryPredicate);
/*      */     }
/*      */     else {
/*  555 */       j = paramInt2 + (paramInt3 - paramInt2) / 2;
/*  556 */       i = upper_bound(paramArrayOfObject, paramInt1, paramInt2, paramArrayOfObject[j], paramBinaryPredicate);
/*      */     }
/*      */     
/*  559 */     Modification.rotate(paramArrayOfObject, i, paramInt2, j);
/*  560 */     paramInt2 = i + (j - paramInt2);
/*      */     
/*  562 */     inplace_merge(paramArrayOfObject, paramInt1, i, paramInt2, paramBinaryPredicate);
/*  563 */     inplace_merge(paramArrayOfObject, paramInt2, j, paramInt3, paramBinaryPredicate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean includes(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  592 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  593 */       if (paramBinaryPredicate.apply(paramArrayOfObject2[paramInt3], paramArrayOfObject1[paramInt1]))
/*  594 */         return false;
/*  595 */       if (paramBinaryPredicate.apply(paramArrayOfObject1[paramInt1], paramArrayOfObject2[paramInt3])) {
/*  596 */         paramInt1++;
/*      */       } else {
/*  598 */         paramInt1++;
/*  599 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/*  603 */     return paramInt3 == paramInt4;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int set_union(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Object[] paramArrayOfObject3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  640 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  641 */       if (paramBinaryPredicate.apply(paramArrayOfObject1[paramInt1], paramArrayOfObject2[paramInt3])) {
/*  642 */         paramArrayOfObject3[(paramInt5++)] = paramArrayOfObject1[(paramInt1++)];
/*  643 */       } else if (paramBinaryPredicate.apply(paramArrayOfObject2[paramInt3], paramArrayOfObject1[paramInt1])) {
/*  644 */         paramArrayOfObject3[(paramInt5++)] = paramArrayOfObject2[(paramInt3++)];
/*      */       } else {
/*  646 */         paramArrayOfObject3[(paramInt5++)] = paramArrayOfObject1[(paramInt1++)];
/*  647 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/*  651 */     Modification.copy(paramArrayOfObject1, paramArrayOfObject3, paramInt1, paramInt2, paramInt5);
/*  652 */     Modification.copy(paramArrayOfObject2, paramArrayOfObject3, paramInt3, paramInt4, paramInt5);
/*  653 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int set_intersection(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Object[] paramArrayOfObject3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  690 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  691 */       if (paramBinaryPredicate.apply(paramArrayOfObject1[paramInt1], paramArrayOfObject2[paramInt3])) {
/*  692 */         paramInt1++;
/*  693 */       } else if (paramBinaryPredicate.apply(paramArrayOfObject2[paramInt3], paramArrayOfObject1[paramInt1])) {
/*  694 */         paramInt3++;
/*      */       } else {
/*  696 */         paramArrayOfObject3[(paramInt5++)] = paramArrayOfObject1[(paramInt1++)];
/*  697 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/*  701 */     return paramInt5;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int set_difference(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Object[] paramArrayOfObject3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  738 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  739 */       if (paramBinaryPredicate.apply(paramArrayOfObject1[paramInt1], paramArrayOfObject2[paramInt3])) {
/*  740 */         paramArrayOfObject3[(paramInt5++)] = paramArrayOfObject1[(paramInt1++)];
/*  741 */       } else if (paramBinaryPredicate.apply(paramArrayOfObject2[paramInt3], paramArrayOfObject1[paramInt1])) {
/*  742 */         paramInt3++;
/*      */       } else {
/*  744 */         paramInt1++;
/*  745 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/*  749 */     Modification.copy(paramArrayOfObject1, paramArrayOfObject3, paramInt1, paramInt2, paramInt5);
/*  750 */     return paramInt5 + (paramInt2 - paramInt1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int set_symmetric_difference(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, Object[] paramArrayOfObject3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  789 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  790 */       if (paramBinaryPredicate.apply(paramArrayOfObject1[paramInt1], paramArrayOfObject2[paramInt3])) {
/*  791 */         paramArrayOfObject3[(paramInt5++)] = paramArrayOfObject1[(paramInt1++)];
/*  792 */       } else if (paramBinaryPredicate.apply(paramArrayOfObject2[paramInt3], paramArrayOfObject1[paramInt1])) {
/*  793 */         paramArrayOfObject3[(paramInt5++)] = paramArrayOfObject2[(paramInt3++)];
/*      */       } else {
/*  795 */         paramInt1++;
/*  796 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/*  800 */     Modification.copy(paramArrayOfObject1, paramArrayOfObject3, paramInt1, paramInt2, paramInt5);
/*  801 */     Modification.copy(paramArrayOfObject2, paramArrayOfObject3, paramInt3, paramInt4, paramInt5);
/*  802 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void push_heap(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  823 */     if (paramInt2 - paramInt1 < 2) return;
/*  824 */     Object localObject = paramArrayOfObject[(--paramInt2)];
/*  825 */     int i = paramInt1 + (paramInt2 - paramInt1 - 1) / 2;
/*  826 */     while ((paramInt2 > paramInt1) && (paramBinaryPredicate.apply(paramArrayOfObject[i], localObject))) {
/*  827 */       paramArrayOfObject[paramInt2] = paramArrayOfObject[i];
/*  828 */       paramInt2 = i;
/*  829 */       i = paramInt1 + (paramInt2 - paramInt1 - 1) / 2;
/*      */     }
/*  831 */     paramArrayOfObject[paramInt2] = localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void adjust_heap(Object[] paramArrayOfObject, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  852 */     Object localObject = paramArrayOfObject[paramInt2];
/*  853 */     int i = paramInt3 - paramInt1;
/*  854 */     int j = paramInt2 - paramInt1;
/*  855 */     int k = 2 * j + 2;
/*  856 */     while (k < i) {
/*  857 */       if (paramBinaryPredicate.apply(paramArrayOfObject[(paramInt1 + k)], paramArrayOfObject[(paramInt1 + (k - 1))]))
/*      */       {
/*  859 */         k--; }
/*  860 */       paramArrayOfObject[(paramInt1 + j)] = paramArrayOfObject[(paramInt1 + k)];
/*  861 */       j = k++;
/*  862 */       k *= 2;
/*      */     }
/*  864 */     if (k-- == i) {
/*  865 */       paramArrayOfObject[(paramInt1 + j)] = paramArrayOfObject[(paramInt1 + k)];
/*  866 */       j = k;
/*      */     }
/*      */     
/*  869 */     int m = (j - 1) / 2;
/*  870 */     int n = paramInt2 - paramInt1;
/*      */     
/*  872 */     while ((j != n) && (paramBinaryPredicate.apply(paramArrayOfObject[(paramInt1 + m)], localObject))) {
/*  873 */       paramArrayOfObject[(paramInt1 + j)] = paramArrayOfObject[(paramInt1 + m)];
/*  874 */       j = m;
/*  875 */       m = (j - 1) / 2;
/*      */     }
/*  877 */     paramArrayOfObject[(paramInt1 + j)] = localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void pop_heap(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  897 */     if (paramInt2 - paramInt1 < 2) return;
/*  898 */     Object localObject = paramArrayOfObject[(--paramInt2)];
/*  899 */     paramArrayOfObject[paramInt2] = paramArrayOfObject[paramInt1];
/*  900 */     paramArrayOfObject[paramInt1] = localObject;
/*  901 */     adjust_heap(paramArrayOfObject, paramInt1, paramInt1, paramInt2, paramBinaryPredicate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void make_heap(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  922 */     if (paramInt2 - paramInt1 < 2) return;
/*  923 */     int i = (paramInt2 - paramInt1 - 2) / 2;
/*      */     do
/*      */     {
/*  926 */       adjust_heap(paramArrayOfObject, paramInt1, paramInt1 + i, paramInt2, paramBinaryPredicate);
/*  927 */     } while (i-- != 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void sort_heap(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  947 */     while (paramInt2 - paramInt1 > 1) {
/*  948 */       Object localObject = paramArrayOfObject[(--paramInt2)];
/*  949 */       paramArrayOfObject[paramInt2] = paramArrayOfObject[paramInt1];
/*  950 */       paramArrayOfObject[paramInt1] = localObject;
/*  951 */       adjust_heap(paramArrayOfObject, paramInt1, paramInt1, paramInt2, paramBinaryPredicate);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int max_element(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  974 */     if (paramInt1 >= paramInt2) { return paramInt2;
/*      */     }
/*  976 */     int i = paramInt1;
/*      */     do
/*      */     {
/*  979 */       if (paramBinaryPredicate.apply(paramArrayOfObject[i], paramArrayOfObject[paramInt1])) {
/*  980 */         i = paramInt1;
/*      */       }
/*  978 */       paramInt1++; } while (paramInt1 < paramInt2);
/*      */     
/*      */ 
/*      */ 
/*  982 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int min_element(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1004 */     if (paramInt1 >= paramInt2) { return paramInt2;
/*      */     }
/* 1006 */     int i = paramInt1;
/*      */     do
/*      */     {
/* 1009 */       if (paramBinaryPredicate.apply(paramArrayOfObject[paramInt1], paramArrayOfObject[i])) {
/* 1010 */         i = paramInt1;
/*      */       }
/* 1008 */       paramInt1++; } while (paramInt1 < paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 1012 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean lexicographical_compare(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1039 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1040 */       if (paramBinaryPredicate.apply(paramArrayOfObject1[paramInt1], paramArrayOfObject2[paramInt3]))
/* 1041 */         return true;
/* 1042 */       if (paramBinaryPredicate.apply(paramArrayOfObject2[(paramInt3++)], paramArrayOfObject1[(paramInt1++)]))
/* 1043 */         return false;
/*      */     }
/* 1045 */     return (paramInt1 == paramInt2) && (paramInt3 != paramInt4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean next_permutation(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1070 */     if (paramInt2 - paramInt1 < 2) {
/* 1071 */       return false;
/*      */     }
/* 1073 */     int i = paramInt2 - 1;
/*      */     do {
/* 1075 */       int j = i--;
/* 1076 */       if (paramBinaryPredicate.apply(paramArrayOfObject[i], paramArrayOfObject[j])) {
/* 1077 */         int k = paramInt2;
/* 1078 */         while (!paramBinaryPredicate.apply(paramArrayOfObject[i], paramArrayOfObject[(--k)])) {}
/*      */         
/* 1080 */         Object localObject = paramArrayOfObject[i];
/* 1081 */         paramArrayOfObject[i] = paramArrayOfObject[k];
/* 1082 */         paramArrayOfObject[k] = localObject;
/* 1083 */         Modification.reverse(paramArrayOfObject, j, paramInt2);
/* 1084 */         return true;
/*      */       }
/* 1086 */     } while (i != paramInt1);
/* 1087 */     Modification.reverse(paramArrayOfObject, paramInt1, paramInt2);
/* 1088 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean prev_permutation(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1115 */     if (paramInt2 - paramInt1 < 2) {
/* 1116 */       return false;
/*      */     }
/* 1118 */     int i = paramInt2 - 1;
/*      */     do {
/* 1120 */       int j = i--;
/* 1121 */       if (paramBinaryPredicate.apply(paramArrayOfObject[j], paramArrayOfObject[i])) {
/* 1122 */         int k = paramInt2;
/* 1123 */         while (!paramBinaryPredicate.apply(paramArrayOfObject[(--k)], paramArrayOfObject[i])) {}
/*      */         
/* 1125 */         Object localObject = paramArrayOfObject[i];
/* 1126 */         paramArrayOfObject[i] = paramArrayOfObject[k];
/* 1127 */         paramArrayOfObject[k] = localObject;
/* 1128 */         Modification.reverse(paramArrayOfObject, j, paramInt2);
/* 1129 */         return true;
/*      */       }
/* 1131 */     } while (i != paramInt1);
/* 1132 */     Modification.reverse(paramArrayOfObject, paramInt1, paramInt2);
/* 1133 */     return false;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/Object/Sorting.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */