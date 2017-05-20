/*      */ package jal.String;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   private static final boolean less(String paramString1, String paramString2)
/*      */   {
/*   63 */     return paramString1.compareTo(paramString2) < 0;
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
/*      */   public static void sort(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*   82 */     if (paramInt2 - paramInt1 >= 13)
/*   83 */       qsortLoop(paramArrayOfString, paramInt1, paramInt2, paramBinaryPredicate);
/*   84 */     insertion_sort(paramArrayOfString, paramInt1, paramInt2, paramBinaryPredicate);
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
/*      */   public static void insertion_sort(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  101 */     int i = paramInt1;
/*  102 */     do { String str1 = paramArrayOfString[i];
/*  103 */       int j = i;
/*  104 */       for (String str2 = paramArrayOfString[(j - 1)]; 
/*  105 */           paramBinaryPredicate.apply(str1, str2); 
/*  106 */           str2 = paramArrayOfString[(--j - 1)]) {
/*  107 */         paramArrayOfString[j] = str2;
/*  108 */         if (paramInt1 == j - 1) {
/*  109 */           j--;
/*  110 */           break;
/*      */         }
/*      */       }
/*  113 */       paramArrayOfString[j] = str1;i++;
/*  101 */     } while (i < paramInt2);
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
/*      */   private static int quickPartition(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  121 */     String str1 = paramArrayOfString[paramInt1];
/*  122 */     String str2 = paramArrayOfString[(paramInt2 - 1)];
/*  123 */     String str3 = paramArrayOfString[(paramInt1 + (paramInt2 - paramInt1) / 2)];
/*      */     
/*  125 */     if (paramBinaryPredicate.apply(str3, str1)) {
/*  126 */       if (paramBinaryPredicate.apply(str1, str2)) {
/*  127 */         str3 = str1;
/*  128 */       } else if (paramBinaryPredicate.apply(str3, str2)) {
/*  129 */         str3 = str2;
/*      */       }
/*  131 */     } else if (paramBinaryPredicate.apply(str2, str1)) {
/*  132 */       str3 = str1;
/*  133 */     } else if (paramBinaryPredicate.apply(str2, str3)) {
/*  134 */       str3 = str2;
/*      */     }
/*  136 */     paramInt1--;
/*      */     for (;;) {
/*  138 */       if (!paramBinaryPredicate.apply(paramArrayOfString[(++paramInt1)], str3))
/*      */       {
/*      */ 
/*  141 */         while ((goto 142) || (paramBinaryPredicate.apply(str3, paramArrayOfString[(--paramInt2)]))) {}
/*      */         
/*      */ 
/*  144 */         if (paramInt1 >= paramInt2) {
/*  145 */           return paramInt1;
/*      */         }
/*  147 */         String str4 = paramArrayOfString[paramInt1];
/*  148 */         paramArrayOfString[paramInt1] = paramArrayOfString[paramInt2];
/*  149 */         paramArrayOfString[paramInt2] = str4;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void qsortLoop(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  160 */     int[] arrayOfInt = new int[56];
/*  161 */     int i = 0;
/*      */     for (;;) {
/*  163 */       int j = quickPartition(paramArrayOfString, paramInt1, paramInt2, paramBinaryPredicate);
/*      */       
/*  165 */       if (paramInt2 - j < 13) {
/*  166 */         if (j - paramInt1 < 13) {
/*  167 */           if (i == 0)
/*  168 */             return;
/*  169 */           paramInt2 = arrayOfInt[(--i)];
/*  170 */           paramInt1 = arrayOfInt[(--i)];
/*      */         }
/*      */         else {
/*  173 */           paramInt2 = j;
/*      */         }
/*  175 */       } else if (j - paramInt1 < 13) {
/*  176 */         paramInt1 = j;
/*      */       }
/*  178 */       else if (paramInt2 - j > j - paramInt1) {
/*  179 */         arrayOfInt[(i++)] = j;
/*  180 */         arrayOfInt[(i++)] = paramInt2;
/*  181 */         paramInt2 = j;
/*      */       }
/*      */       else {
/*  184 */         arrayOfInt[(i++)] = paramInt1;
/*  185 */         arrayOfInt[(i++)] = j;
/*  186 */         paramInt1 = j;
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
/*      */   public static void stable_sort(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  207 */     if (paramInt2 - paramInt1 < 9) {
/*  208 */       insertion_sort(paramArrayOfString, paramInt1, paramInt2, paramBinaryPredicate);
/*      */     } else {
/*  210 */       int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  211 */       stable_sort(paramArrayOfString, paramInt1, i, paramBinaryPredicate);
/*  212 */       stable_sort(paramArrayOfString, i, paramInt2, paramBinaryPredicate);
/*  213 */       inplace_merge(paramArrayOfString, paramInt1, i, paramInt2, paramBinaryPredicate);
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
/*      */   public static void partial_sort(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  237 */     make_heap(paramArrayOfString, paramInt1, paramInt2, paramBinaryPredicate);
/*  238 */     int i = paramInt2;
/*  239 */     while (i < paramInt3) {
/*  240 */       if (paramBinaryPredicate.apply(paramArrayOfString[i], paramArrayOfString[paramInt1])) {
/*  241 */         String str = paramArrayOfString[i];
/*  242 */         paramArrayOfString[i] = paramArrayOfString[paramInt1];
/*  243 */         paramArrayOfString[paramInt1] = str;
/*  244 */         adjust_heap(paramArrayOfString, paramInt1, paramInt1, paramInt2, paramBinaryPredicate);
/*      */       }
/*  246 */       i++;
/*      */     }
/*  248 */     sort_heap(paramArrayOfString, paramInt1, paramInt2, paramBinaryPredicate);
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
/*      */   public static int partial_sort_copy(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  274 */     if (paramInt3 == paramInt4) {
/*  275 */       return paramInt4;
/*      */     }
/*  277 */     int i = Math.min(paramInt2 - paramInt1, paramInt4 - paramInt3);
/*  278 */     Modification.copy(paramArrayOfString1, paramArrayOfString2, paramInt1, paramInt1 + i, paramInt3);
/*  279 */     paramInt4 = paramInt3 + i;
/*      */     
/*  281 */     make_heap(paramArrayOfString2, paramInt3, paramInt4, paramBinaryPredicate);
/*  282 */     for (paramInt1 += i; paramInt1 < paramInt2; paramInt1++) {
/*  283 */       if (paramBinaryPredicate.apply(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3])) {
/*  284 */         paramArrayOfString2[paramInt3] = paramArrayOfString1[paramInt1];
/*  285 */         adjust_heap(paramArrayOfString2, paramInt3, paramInt3, paramInt4, paramBinaryPredicate);
/*      */       }
/*      */     }
/*      */     
/*  289 */     sort_heap(paramArrayOfString2, paramInt3, paramInt4, paramBinaryPredicate);
/*      */     
/*  291 */     return paramInt4;
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
/*      */   public static void nth_element(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  314 */     while (paramInt3 - paramInt1 > 3) {
/*  315 */       int i = quickPartition(paramArrayOfString, paramInt1, paramInt3, paramBinaryPredicate);
/*  316 */       if (i <= paramInt2) {
/*  317 */         paramInt1 = i;
/*      */       } else {
/*  319 */         paramInt3 = i;
/*      */       }
/*      */     }
/*  322 */     insertion_sort(paramArrayOfString, paramInt1, paramInt3, paramBinaryPredicate);
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
/*      */   public static int lower_bound(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  348 */     int i = paramInt2 - paramInt1;
/*  349 */     while (i > 0) {
/*  350 */       int j = i / 2;
/*  351 */       int k = paramInt1 + j;
/*  352 */       if (paramBinaryPredicate.apply(paramArrayOfString[k], paramString)) {
/*  353 */         paramInt1 = k + 1;
/*  354 */         i -= j + 1;
/*      */       } else {
/*  356 */         i = j;
/*      */       } }
/*  358 */     return paramInt1;
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
/*      */   public static int upper_bound(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  384 */     int i = paramInt2 - paramInt1;
/*  385 */     while (i > 0) {
/*  386 */       int j = i / 2;
/*  387 */       int k = paramInt1 + j;
/*  388 */       if (paramBinaryPredicate.apply(paramString, paramArrayOfString[k])) {
/*  389 */         i = j;
/*      */       } else {
/*  391 */         paramInt1 = k + 1;
/*  392 */         i -= j + 1;
/*      */       }
/*      */     }
/*  395 */     return paramInt1;
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
/*      */   public static Range equal_range(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  425 */     int i = paramInt2 - paramInt1;
/*  426 */     while (i > 0) {
/*  427 */       int j = i / 2;
/*  428 */       int k = paramInt1 + j;
/*  429 */       if (paramBinaryPredicate.apply(paramArrayOfString[k], paramString)) {
/*  430 */         paramInt1 = k + 1;
/*  431 */         i = i - j + 1;
/*      */       }
/*  433 */       else if (paramBinaryPredicate.apply(paramString, paramArrayOfString[k])) {
/*  434 */         i = j;
/*      */       } else {
/*  436 */         int m = lower_bound(paramArrayOfString, paramInt1, k, paramString, paramBinaryPredicate);
/*  437 */         int n = upper_bound(paramArrayOfString, k + 1, paramInt1 + i, paramString, paramBinaryPredicate);
/*  438 */         return new Range(paramArrayOfString, m, n);
/*      */       }
/*      */     }
/*      */     
/*  442 */     return new Range(paramArrayOfString, paramInt1, paramInt1);
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
/*      */   public static boolean binary_search(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  470 */     int i = lower_bound(paramArrayOfString, paramInt1, paramInt2, paramString, paramBinaryPredicate);
/*  471 */     return (i < paramInt2) && (!paramBinaryPredicate.apply(paramString, paramArrayOfString[i]));
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
/*      */   public static int merge(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  504 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  505 */       if (paramBinaryPredicate.apply(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1])) {
/*  506 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString2[(paramInt3++)];
/*      */       } else
/*  508 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString1[(paramInt1++)];
/*      */     }
/*  510 */     Modification.copy(paramArrayOfString1, paramArrayOfString3, paramInt1, paramInt2, paramInt5);
/*  511 */     Modification.copy(paramArrayOfString2, paramArrayOfString3, paramInt3, paramInt4, paramInt5);
/*  512 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
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
/*      */   public static void inplace_merge(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  538 */     if ((paramInt1 >= paramInt2) || (paramInt2 >= paramInt3)) {
/*  539 */       return;
/*      */     }
/*  541 */     if (paramInt3 - paramInt1 == 2) {
/*  542 */       if (paramBinaryPredicate.apply(paramArrayOfString[paramInt2], paramArrayOfString[paramInt1])) {
/*  543 */         String str = paramArrayOfString[paramInt1];
/*  544 */         paramArrayOfString[paramInt1] = paramArrayOfString[paramInt2];
/*  545 */         paramArrayOfString[paramInt2] = str;
/*      */       }
/*      */       
/*      */       return;
/*      */     }
/*      */     
/*      */     int i;
/*      */     int j;
/*  553 */     if (paramInt2 - paramInt1 > paramInt3 - paramInt2) {
/*  554 */       i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  555 */       j = lower_bound(paramArrayOfString, paramInt2, paramInt3, paramArrayOfString[i], paramBinaryPredicate);
/*      */     }
/*      */     else {
/*  558 */       j = paramInt2 + (paramInt3 - paramInt2) / 2;
/*  559 */       i = upper_bound(paramArrayOfString, paramInt1, paramInt2, paramArrayOfString[j], paramBinaryPredicate);
/*      */     }
/*      */     
/*  562 */     Modification.rotate(paramArrayOfString, i, paramInt2, j);
/*  563 */     paramInt2 = i + (j - paramInt2);
/*      */     
/*  565 */     inplace_merge(paramArrayOfString, paramInt1, i, paramInt2, paramBinaryPredicate);
/*  566 */     inplace_merge(paramArrayOfString, paramInt2, j, paramInt3, paramBinaryPredicate);
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
/*      */   public static boolean includes(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  595 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  596 */       if (paramBinaryPredicate.apply(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1]))
/*  597 */         return false;
/*  598 */       if (paramBinaryPredicate.apply(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3])) {
/*  599 */         paramInt1++;
/*      */       } else {
/*  601 */         paramInt1++;
/*  602 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/*  606 */     return paramInt3 == paramInt4;
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
/*      */   public static int set_union(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  643 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  644 */       if (paramBinaryPredicate.apply(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3])) {
/*  645 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString1[(paramInt1++)];
/*  646 */       } else if (paramBinaryPredicate.apply(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1])) {
/*  647 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString2[(paramInt3++)];
/*      */       } else {
/*  649 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString1[(paramInt1++)];
/*  650 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/*  654 */     Modification.copy(paramArrayOfString1, paramArrayOfString3, paramInt1, paramInt2, paramInt5);
/*  655 */     Modification.copy(paramArrayOfString2, paramArrayOfString3, paramInt3, paramInt4, paramInt5);
/*  656 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
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
/*      */   public static int set_intersection(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  693 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  694 */       if (paramBinaryPredicate.apply(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3])) {
/*  695 */         paramInt1++;
/*  696 */       } else if (paramBinaryPredicate.apply(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1])) {
/*  697 */         paramInt3++;
/*      */       } else {
/*  699 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString1[(paramInt1++)];
/*  700 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/*  704 */     return paramInt5;
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
/*      */   public static int set_difference(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  741 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  742 */       if (paramBinaryPredicate.apply(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3])) {
/*  743 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString1[(paramInt1++)];
/*  744 */       } else if (paramBinaryPredicate.apply(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1])) {
/*  745 */         paramInt3++;
/*      */       } else {
/*  747 */         paramInt1++;
/*  748 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/*  752 */     Modification.copy(paramArrayOfString1, paramArrayOfString3, paramInt1, paramInt2, paramInt5);
/*  753 */     return paramInt5 + (paramInt2 - paramInt1);
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
/*      */   public static int set_symmetric_difference(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  792 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  793 */       if (paramBinaryPredicate.apply(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3])) {
/*  794 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString1[(paramInt1++)];
/*  795 */       } else if (paramBinaryPredicate.apply(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1])) {
/*  796 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString2[(paramInt3++)];
/*      */       } else {
/*  798 */         paramInt1++;
/*  799 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/*  803 */     Modification.copy(paramArrayOfString1, paramArrayOfString3, paramInt1, paramInt2, paramInt5);
/*  804 */     Modification.copy(paramArrayOfString2, paramArrayOfString3, paramInt3, paramInt4, paramInt5);
/*  805 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
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
/*      */   public static void push_heap(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  826 */     if (paramInt2 - paramInt1 < 2) return;
/*  827 */     String str = paramArrayOfString[(--paramInt2)];
/*  828 */     int i = paramInt1 + (paramInt2 - paramInt1 - 1) / 2;
/*  829 */     while ((paramInt2 > paramInt1) && (paramBinaryPredicate.apply(paramArrayOfString[i], str))) {
/*  830 */       paramArrayOfString[paramInt2] = paramArrayOfString[i];
/*  831 */       paramInt2 = i;
/*  832 */       i = paramInt1 + (paramInt2 - paramInt1 - 1) / 2;
/*      */     }
/*  834 */     paramArrayOfString[paramInt2] = str;
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
/*      */   private static void adjust_heap(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  855 */     String str = paramArrayOfString[paramInt2];
/*  856 */     int i = paramInt3 - paramInt1;
/*  857 */     int j = paramInt2 - paramInt1;
/*  858 */     int k = 2 * j + 2;
/*  859 */     while (k < i) {
/*  860 */       if (paramBinaryPredicate.apply(paramArrayOfString[(paramInt1 + k)], paramArrayOfString[(paramInt1 + (k - 1))]))
/*      */       {
/*  862 */         k--; }
/*  863 */       paramArrayOfString[(paramInt1 + j)] = paramArrayOfString[(paramInt1 + k)];
/*  864 */       j = k++;
/*  865 */       k *= 2;
/*      */     }
/*  867 */     if (k-- == i) {
/*  868 */       paramArrayOfString[(paramInt1 + j)] = paramArrayOfString[(paramInt1 + k)];
/*  869 */       j = k;
/*      */     }
/*      */     
/*  872 */     int m = (j - 1) / 2;
/*  873 */     int n = paramInt2 - paramInt1;
/*      */     
/*  875 */     while ((j != n) && (paramBinaryPredicate.apply(paramArrayOfString[(paramInt1 + m)], str))) {
/*  876 */       paramArrayOfString[(paramInt1 + j)] = paramArrayOfString[(paramInt1 + m)];
/*  877 */       j = m;
/*  878 */       m = (j - 1) / 2;
/*      */     }
/*  880 */     paramArrayOfString[(paramInt1 + j)] = str;
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
/*      */   public static void pop_heap(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  900 */     if (paramInt2 - paramInt1 < 2) return;
/*  901 */     String str = paramArrayOfString[(--paramInt2)];
/*  902 */     paramArrayOfString[paramInt2] = paramArrayOfString[paramInt1];
/*  903 */     paramArrayOfString[paramInt1] = str;
/*  904 */     adjust_heap(paramArrayOfString, paramInt1, paramInt1, paramInt2, paramBinaryPredicate);
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
/*      */   public static void make_heap(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  925 */     if (paramInt2 - paramInt1 < 2) return;
/*  926 */     int i = (paramInt2 - paramInt1 - 2) / 2;
/*      */     do
/*      */     {
/*  929 */       adjust_heap(paramArrayOfString, paramInt1, paramInt1 + i, paramInt2, paramBinaryPredicate);
/*  930 */     } while (i-- != 0);
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
/*      */   public static void sort_heap(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  950 */     while (paramInt2 - paramInt1 > 1) {
/*  951 */       String str = paramArrayOfString[(--paramInt2)];
/*  952 */       paramArrayOfString[paramInt2] = paramArrayOfString[paramInt1];
/*  953 */       paramArrayOfString[paramInt1] = str;
/*  954 */       adjust_heap(paramArrayOfString, paramInt1, paramInt1, paramInt2, paramBinaryPredicate);
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
/*      */   public static int max_element(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  977 */     if (paramInt1 >= paramInt2) { return paramInt2;
/*      */     }
/*  979 */     int i = paramInt1;
/*      */     do
/*      */     {
/*  982 */       if (paramBinaryPredicate.apply(paramArrayOfString[i], paramArrayOfString[paramInt1])) {
/*  983 */         i = paramInt1;
/*      */       }
/*  981 */       paramInt1++; } while (paramInt1 < paramInt2);
/*      */     
/*      */ 
/*      */ 
/*  985 */     return i;
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
/*      */   public static int min_element(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1007 */     if (paramInt1 >= paramInt2) { return paramInt2;
/*      */     }
/* 1009 */     int i = paramInt1;
/*      */     do
/*      */     {
/* 1012 */       if (paramBinaryPredicate.apply(paramArrayOfString[paramInt1], paramArrayOfString[i])) {
/* 1013 */         i = paramInt1;
/*      */       }
/* 1011 */       paramInt1++; } while (paramInt1 < paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 1015 */     return i;
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
/*      */   public static boolean lexicographical_compare(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1042 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1043 */       if (paramBinaryPredicate.apply(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3]))
/* 1044 */         return true;
/* 1045 */       if (paramBinaryPredicate.apply(paramArrayOfString2[(paramInt3++)], paramArrayOfString1[(paramInt1++)]))
/* 1046 */         return false;
/*      */     }
/* 1048 */     return (paramInt1 == paramInt2) && (paramInt3 != paramInt4);
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
/*      */   public static boolean next_permutation(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1073 */     if (paramInt2 - paramInt1 < 2) {
/* 1074 */       return false;
/*      */     }
/* 1076 */     int i = paramInt2 - 1;
/*      */     do {
/* 1078 */       int j = i--;
/* 1079 */       if (paramBinaryPredicate.apply(paramArrayOfString[i], paramArrayOfString[j])) {
/* 1080 */         int k = paramInt2;
/* 1081 */         while (!paramBinaryPredicate.apply(paramArrayOfString[i], paramArrayOfString[(--k)])) {}
/*      */         
/* 1083 */         String str = paramArrayOfString[i];
/* 1084 */         paramArrayOfString[i] = paramArrayOfString[k];
/* 1085 */         paramArrayOfString[k] = str;
/* 1086 */         Modification.reverse(paramArrayOfString, j, paramInt2);
/* 1087 */         return true;
/*      */       }
/* 1089 */     } while (i != paramInt1);
/* 1090 */     Modification.reverse(paramArrayOfString, paramInt1, paramInt2);
/* 1091 */     return false;
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
/*      */   public static boolean prev_permutation(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1118 */     if (paramInt2 - paramInt1 < 2) {
/* 1119 */       return false;
/*      */     }
/* 1121 */     int i = paramInt2 - 1;
/*      */     do {
/* 1123 */       int j = i--;
/* 1124 */       if (paramBinaryPredicate.apply(paramArrayOfString[j], paramArrayOfString[i])) {
/* 1125 */         int k = paramInt2;
/* 1126 */         while (!paramBinaryPredicate.apply(paramArrayOfString[(--k)], paramArrayOfString[i])) {}
/*      */         
/* 1128 */         String str = paramArrayOfString[i];
/* 1129 */         paramArrayOfString[i] = paramArrayOfString[k];
/* 1130 */         paramArrayOfString[k] = str;
/* 1131 */         Modification.reverse(paramArrayOfString, j, paramInt2);
/* 1132 */         return true;
/*      */       }
/* 1134 */     } while (i != paramInt1);
/* 1135 */     Modification.reverse(paramArrayOfString, paramInt1, paramInt2);
/* 1136 */     return false;
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
/*      */   public static void sort(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*      */   {
/* 1157 */     if (paramInt2 - paramInt1 >= 13)
/* 1158 */       qsortLoop(paramArrayOfString, paramInt1, paramInt2);
/* 1159 */     insertion_sort(paramArrayOfString, paramInt1, paramInt2);
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
/*      */   public static void insertion_sort(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*      */   {
/* 1174 */     int i = paramInt1;
/* 1175 */     do { String str1 = paramArrayOfString[i];
/* 1176 */       int j = i;
/* 1177 */       for (String str2 = paramArrayOfString[(j - 1)]; 
/* 1178 */           less(str1, str2); 
/* 1179 */           str2 = paramArrayOfString[(--j - 1)]) {
/* 1180 */         paramArrayOfString[j] = str2;
/* 1181 */         if (paramInt1 == j - 1) {
/* 1182 */           j--;
/* 1183 */           break;
/*      */         }
/*      */       }
/* 1186 */       paramArrayOfString[j] = str1;i++;
/* 1174 */     } while (i < paramInt2);
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
/*      */   private static int quickPartition(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*      */   {
/* 1193 */     String str1 = paramArrayOfString[paramInt1];
/* 1194 */     String str2 = paramArrayOfString[(paramInt2 - 1)];
/* 1195 */     String str3 = paramArrayOfString[(paramInt1 + (paramInt2 - paramInt1) / 2)];
/*      */     
/* 1197 */     if (less(str3, str1)) {
/* 1198 */       if (less(str1, str2)) {
/* 1199 */         str3 = str1;
/* 1200 */       } else if (less(str3, str2)) {
/* 1201 */         str3 = str2;
/*      */       }
/* 1203 */     } else if (less(str2, str1)) {
/* 1204 */       str3 = str1;
/* 1205 */     } else if (less(str2, str3)) {
/* 1206 */       str3 = str2;
/*      */     }
/* 1208 */     paramInt1--;
/*      */     for (;;) {
/* 1210 */       if (!less(paramArrayOfString[(++paramInt1)], str3))
/*      */       {
/*      */ 
/* 1213 */         while ((goto 118) || (less(str3, paramArrayOfString[(--paramInt2)]))) {}
/*      */         
/*      */ 
/* 1216 */         if (paramInt1 >= paramInt2) {
/* 1217 */           return paramInt1;
/*      */         }
/* 1219 */         String str4 = paramArrayOfString[paramInt1];
/* 1220 */         paramArrayOfString[paramInt1] = paramArrayOfString[paramInt2];
/* 1221 */         paramArrayOfString[paramInt2] = str4;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private static void qsortLoop(String[] paramArrayOfString, int paramInt1, int paramInt2) {
/* 1227 */     int[] arrayOfInt = new int[56];
/* 1228 */     int i = 0;
/*      */     for (;;) {
/* 1230 */       int j = quickPartition(paramArrayOfString, paramInt1, paramInt2);
/*      */       
/* 1232 */       if (paramInt2 - j < 13) {
/* 1233 */         if (j - paramInt1 < 13) {
/* 1234 */           if (i == 0)
/* 1235 */             return;
/* 1236 */           paramInt2 = arrayOfInt[(--i)];
/* 1237 */           paramInt1 = arrayOfInt[(--i)];
/*      */         }
/*      */         else {
/* 1240 */           paramInt2 = j;
/*      */         }
/* 1242 */       } else if (j - paramInt1 < 13) {
/* 1243 */         paramInt1 = j;
/*      */       }
/* 1245 */       else if (paramInt2 - j > j - paramInt1) {
/* 1246 */         arrayOfInt[(i++)] = j;
/* 1247 */         arrayOfInt[(i++)] = paramInt2;
/* 1248 */         paramInt2 = j;
/*      */       }
/*      */       else {
/* 1251 */         arrayOfInt[(i++)] = paramInt1;
/* 1252 */         arrayOfInt[(i++)] = j;
/* 1253 */         paramInt1 = j;
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
/*      */   public static void stable_sort(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*      */   {
/* 1269 */     if (paramInt2 - paramInt1 < 9) {
/* 1270 */       insertion_sort(paramArrayOfString, paramInt1, paramInt2);
/*      */     } else {
/* 1272 */       int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 1273 */       stable_sort(paramArrayOfString, paramInt1, i);
/* 1274 */       stable_sort(paramArrayOfString, i, paramInt2);
/* 1275 */       inplace_merge(paramArrayOfString, paramInt1, i, paramInt2);
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
/*      */   public static void partial_sort(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 1297 */     make_heap(paramArrayOfString, paramInt1, paramInt2);
/* 1298 */     int i = paramInt2;
/* 1299 */     while (i < paramInt3) {
/* 1300 */       if (less(paramArrayOfString[i], paramArrayOfString[paramInt1])) {
/* 1301 */         String str = paramArrayOfString[i];
/* 1302 */         paramArrayOfString[i] = paramArrayOfString[paramInt1];
/* 1303 */         paramArrayOfString[paramInt1] = str;
/* 1304 */         adjust_heap(paramArrayOfString, paramInt1, paramInt1, paramInt2);
/*      */       }
/* 1306 */       i++;
/*      */     }
/* 1308 */     sort_heap(paramArrayOfString, paramInt1, paramInt2);
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
/*      */   public static int partial_sort_copy(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/* 1332 */     if (paramInt3 == paramInt4) {
/* 1333 */       return paramInt4;
/*      */     }
/* 1335 */     int i = Math.min(paramInt2 - paramInt1, paramInt4 - paramInt3);
/* 1336 */     Modification.copy(paramArrayOfString1, paramArrayOfString2, paramInt1, paramInt1 + i, paramInt3);
/* 1337 */     paramInt4 = paramInt3 + i;
/*      */     
/* 1339 */     make_heap(paramArrayOfString2, paramInt3, paramInt4);
/* 1340 */     for (paramInt1 += i; paramInt1 < paramInt2; paramInt1++) {
/* 1341 */       if (less(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3])) {
/* 1342 */         paramArrayOfString2[paramInt3] = paramArrayOfString1[paramInt1];
/* 1343 */         adjust_heap(paramArrayOfString2, paramInt3, paramInt3, paramInt4);
/*      */       }
/*      */     }
/* 1346 */     sort_heap(paramArrayOfString2, paramInt3, paramInt4);
/*      */     
/* 1348 */     return paramInt4;
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
/*      */   public static void nth_element(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 1369 */     while (paramInt3 - paramInt1 > 3) {
/* 1370 */       int i = quickPartition(paramArrayOfString, paramInt1, paramInt3);
/* 1371 */       if (i <= paramInt2) {
/* 1372 */         paramInt1 = i;
/*      */       } else {
/* 1374 */         paramInt3 = i;
/*      */       }
/*      */     }
/* 1377 */     insertion_sort(paramArrayOfString, paramInt1, paramInt3);
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
/*      */   public static int lower_bound(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString)
/*      */   {
/* 1400 */     int i = paramInt2 - paramInt1;
/* 1401 */     while (i > 0) {
/* 1402 */       int j = i / 2;
/* 1403 */       int k = paramInt1 + j;
/* 1404 */       if (less(paramArrayOfString[k], paramString)) {
/* 1405 */         paramInt1 = k + 1;
/* 1406 */         i -= j + 1;
/*      */       } else {
/* 1408 */         i = j;
/*      */       } }
/* 1410 */     return paramInt1;
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
/*      */   public static int upper_bound(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString)
/*      */   {
/* 1434 */     int i = paramInt2 - paramInt1;
/* 1435 */     while (i > 0) {
/* 1436 */       int j = i / 2;
/* 1437 */       int k = paramInt1 + j;
/* 1438 */       if (less(paramString, paramArrayOfString[k])) {
/* 1439 */         i = j;
/*      */       } else {
/* 1441 */         paramInt1 = k + 1;
/* 1442 */         i -= j + 1;
/*      */       }
/*      */     }
/* 1445 */     return paramInt1;
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
/*      */   public static Range equal_range(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString)
/*      */   {
/* 1473 */     int i = paramInt2 - paramInt1;
/* 1474 */     while (i > 0) {
/* 1475 */       int j = i / 2;
/* 1476 */       int k = paramInt1 + j;
/* 1477 */       if (less(paramArrayOfString[k], paramString)) {
/* 1478 */         paramInt1 = k + 1;
/* 1479 */         i = i - j + 1;
/*      */       }
/* 1481 */       else if (less(paramString, paramArrayOfString[k])) {
/* 1482 */         i = j;
/*      */       } else {
/* 1484 */         int m = lower_bound(paramArrayOfString, paramInt1, k, paramString);
/* 1485 */         int n = upper_bound(paramArrayOfString, k + 1, paramInt1 + i, paramString);
/* 1486 */         return new Range(paramArrayOfString, m, n);
/*      */       }
/*      */     }
/*      */     
/* 1490 */     return new Range(paramArrayOfString, paramInt1, paramInt1);
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
/*      */   public static boolean binary_search(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString)
/*      */   {
/* 1516 */     int i = lower_bound(paramArrayOfString, paramInt1, paramInt2, paramString);
/* 1517 */     return (i < paramInt2) && (!less(paramString, paramArrayOfString[i]));
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
/*      */   public static int merge(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */   {
/* 1548 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1549 */       if (less(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1])) {
/* 1550 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString2[(paramInt3++)];
/*      */       } else
/* 1552 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString1[(paramInt1++)];
/*      */     }
/* 1554 */     Modification.copy(paramArrayOfString1, paramArrayOfString3, paramInt1, paramInt2, paramInt5);
/* 1555 */     Modification.copy(paramArrayOfString2, paramArrayOfString3, paramInt3, paramInt4, paramInt5);
/* 1556 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
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
/*      */   public static void inplace_merge(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 1580 */     if ((paramInt1 >= paramInt2) || (paramInt2 >= paramInt3)) {
/* 1581 */       return;
/*      */     }
/* 1583 */     if (paramInt3 - paramInt1 == 2) {
/* 1584 */       if (less(paramArrayOfString[paramInt2], paramArrayOfString[paramInt1])) {
/* 1585 */         String str = paramArrayOfString[paramInt1];
/* 1586 */         paramArrayOfString[paramInt1] = paramArrayOfString[paramInt2];
/* 1587 */         paramArrayOfString[paramInt2] = str;
/*      */       }
/*      */       
/*      */       return;
/*      */     }
/*      */     
/*      */     int i;
/*      */     int j;
/* 1595 */     if (paramInt2 - paramInt1 > paramInt3 - paramInt2) {
/* 1596 */       i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 1597 */       j = lower_bound(paramArrayOfString, paramInt2, paramInt3, paramArrayOfString[i]);
/*      */     }
/*      */     else {
/* 1600 */       j = paramInt2 + (paramInt3 - paramInt2) / 2;
/* 1601 */       i = upper_bound(paramArrayOfString, paramInt1, paramInt2, paramArrayOfString[j]);
/*      */     }
/*      */     
/* 1604 */     Modification.rotate(paramArrayOfString, i, paramInt2, j);
/* 1605 */     paramInt2 = i + (j - paramInt2);
/*      */     
/* 1607 */     inplace_merge(paramArrayOfString, paramInt1, i, paramInt2);
/* 1608 */     inplace_merge(paramArrayOfString, paramInt2, j, paramInt3);
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
/*      */   public static boolean includes(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/* 1635 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1636 */       if (less(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1]))
/* 1637 */         return false;
/* 1638 */       if (less(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3])) {
/* 1639 */         paramInt1++;
/*      */       } else {
/* 1641 */         paramInt1++;
/* 1642 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1646 */     return paramInt3 == paramInt4;
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
/*      */   public static int set_union(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */   {
/* 1681 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1682 */       if (less(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3])) {
/* 1683 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString1[(paramInt1++)];
/* 1684 */       } else if (less(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1])) {
/* 1685 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString2[(paramInt3++)];
/*      */       } else {
/* 1687 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString1[(paramInt1++)];
/* 1688 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1692 */     Modification.copy(paramArrayOfString1, paramArrayOfString3, paramInt1, paramInt2, paramInt5);
/* 1693 */     Modification.copy(paramArrayOfString2, paramArrayOfString3, paramInt3, paramInt4, paramInt5);
/* 1694 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
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
/*      */   public static int set_intersection(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */   {
/* 1729 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1730 */       if (less(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3])) {
/* 1731 */         paramInt1++;
/* 1732 */       } else if (less(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1])) {
/* 1733 */         paramInt3++;
/*      */       } else {
/* 1735 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString1[(paramInt1++)];
/* 1736 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1740 */     return paramInt5;
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
/*      */   public static int set_difference(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */   {
/* 1775 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1776 */       if (less(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3])) {
/* 1777 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString1[(paramInt1++)];
/* 1778 */       } else if (less(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1])) {
/* 1779 */         paramInt3++;
/*      */       } else {
/* 1781 */         paramInt1++;
/* 1782 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1786 */     Modification.copy(paramArrayOfString1, paramArrayOfString3, paramInt1, paramInt2, paramInt5);
/* 1787 */     return paramInt5 + (paramInt2 - paramInt1);
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
/*      */   public static int set_symmetric_difference(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */   {
/* 1824 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1825 */       if (less(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3])) {
/* 1826 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString1[(paramInt1++)];
/* 1827 */       } else if (less(paramArrayOfString2[paramInt3], paramArrayOfString1[paramInt1])) {
/* 1828 */         paramArrayOfString3[(paramInt5++)] = paramArrayOfString2[(paramInt3++)];
/*      */       } else {
/* 1830 */         paramInt1++;
/* 1831 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1835 */     Modification.copy(paramArrayOfString1, paramArrayOfString3, paramInt1, paramInt2, paramInt5);
/* 1836 */     Modification.copy(paramArrayOfString2, paramArrayOfString3, paramInt3, paramInt4, paramInt5);
/* 1837 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
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
/*      */   public static void push_heap(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*      */   {
/* 1856 */     if (paramInt2 - paramInt1 < 2) return;
/* 1857 */     String str = paramArrayOfString[(--paramInt2)];
/* 1858 */     int i = paramInt1 + (paramInt2 - paramInt1 - 1) / 2;
/* 1859 */     while ((paramInt2 > paramInt1) && (less(paramArrayOfString[i], str))) {
/* 1860 */       paramArrayOfString[paramInt2] = paramArrayOfString[i];
/* 1861 */       paramInt2 = i;
/* 1862 */       i = paramInt2 + (paramInt2 - paramInt1 - 1) / 2;
/*      */     }
/* 1864 */     paramArrayOfString[paramInt2] = str;
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
/*      */   private static void adjust_heap(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 1883 */     String str = paramArrayOfString[paramInt2];
/* 1884 */     int i = paramInt3 - paramInt1;
/* 1885 */     int j = paramInt2 - paramInt1;
/* 1886 */     int k = 2 * j + 2;
/* 1887 */     while (k < i) {
/* 1888 */       if (less(paramArrayOfString[(paramInt1 + k)], paramArrayOfString[(paramInt1 + (k - 1))]))
/*      */       {
/* 1890 */         k--; }
/* 1891 */       paramArrayOfString[(paramInt1 + j)] = paramArrayOfString[(paramInt1 + k)];
/* 1892 */       j = k++;
/* 1893 */       k *= 2;
/*      */     }
/* 1895 */     if (k-- == i) {
/* 1896 */       paramArrayOfString[(paramInt1 + j)] = paramArrayOfString[(paramInt1 + k)];
/* 1897 */       j = k;
/*      */     }
/*      */     
/* 1900 */     int m = (j - 1) / 2;
/* 1901 */     int n = paramInt2 - paramInt1;
/*      */     
/* 1903 */     while ((j != n) && (less(paramArrayOfString[(paramInt1 + m)], str))) {
/* 1904 */       paramArrayOfString[(paramInt1 + j)] = paramArrayOfString[(paramInt1 + m)];
/* 1905 */       j = m;
/* 1906 */       m = (j - 1) / 2;
/*      */     }
/* 1908 */     paramArrayOfString[(paramInt1 + j)] = str;
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
/*      */   public static void pop_heap(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*      */   {
/* 1926 */     if (paramInt2 - paramInt1 < 2) return;
/* 1927 */     String str = paramArrayOfString[(--paramInt2)];
/* 1928 */     paramArrayOfString[paramInt2] = paramArrayOfString[paramInt1];
/* 1929 */     paramArrayOfString[paramInt1] = str;
/* 1930 */     adjust_heap(paramArrayOfString, paramInt1, paramInt1, paramInt2);
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
/*      */   public static void make_heap(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*      */   {
/* 1949 */     if (paramInt2 - paramInt1 < 2) return;
/* 1950 */     int i = (paramInt2 - paramInt1 - 2) / 2;
/*      */     do
/*      */     {
/* 1953 */       adjust_heap(paramArrayOfString, paramInt1, paramInt1 + i, paramInt2);
/* 1954 */     } while (i-- != 0);
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
/*      */   public static void sort_heap(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*      */   {
/* 1972 */     while (paramInt2 - paramInt1 > 1) {
/* 1973 */       String str = paramArrayOfString[(--paramInt2)];
/* 1974 */       paramArrayOfString[paramInt2] = paramArrayOfString[paramInt1];
/* 1975 */       paramArrayOfString[paramInt1] = str;
/* 1976 */       adjust_heap(paramArrayOfString, paramInt1, paramInt1, paramInt2);
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
/*      */   public static int max_element(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*      */   {
/* 1997 */     if (paramInt1 >= paramInt2) { return paramInt2;
/*      */     }
/* 1999 */     int i = paramInt1;
/*      */     do
/*      */     {
/* 2002 */       if (less(paramArrayOfString[i], paramArrayOfString[paramInt1])) {
/* 2003 */         i = paramInt1;
/*      */       }
/* 2001 */       paramInt1++; } while (paramInt1 < paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 2005 */     return i;
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
/*      */   public static int min_element(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*      */   {
/* 2025 */     if (paramInt1 >= paramInt2) { return paramInt2;
/*      */     }
/* 2027 */     int i = paramInt1;
/*      */     do
/*      */     {
/* 2030 */       if (less(paramArrayOfString[paramInt1], paramArrayOfString[i])) {
/* 2031 */         i = paramInt1;
/*      */       }
/* 2029 */       paramInt1++; } while (paramInt1 < paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 2033 */     return i;
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
/*      */   public static boolean lexicographical_compare(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/* 2058 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 2059 */       if (less(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3]))
/* 2060 */         return true;
/* 2061 */       if (less(paramArrayOfString2[(paramInt3++)], paramArrayOfString1[(paramInt1++)]))
/* 2062 */         return false;
/*      */     }
/* 2064 */     return (paramInt1 == paramInt2) && (paramInt3 != paramInt4);
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
/*      */   public static boolean next_permutation(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*      */   {
/* 2086 */     if (paramInt2 - paramInt1 < 2) {
/* 2087 */       return false;
/*      */     }
/* 2089 */     int i = paramInt2 - 1;
/*      */     do {
/* 2091 */       int j = i--;
/* 2092 */       if (less(paramArrayOfString[i], paramArrayOfString[j])) {
/* 2093 */         int k = paramInt2;
/* 2094 */         while (!less(paramArrayOfString[i], paramArrayOfString[(--k)])) {}
/*      */         
/* 2096 */         String str = paramArrayOfString[i];
/* 2097 */         paramArrayOfString[i] = paramArrayOfString[k];
/* 2098 */         paramArrayOfString[k] = str;
/* 2099 */         Modification.reverse(paramArrayOfString, j, paramInt2);
/* 2100 */         return true;
/*      */       }
/* 2102 */     } while (i != paramInt1);
/* 2103 */     Modification.reverse(paramArrayOfString, paramInt1, paramInt2);
/* 2104 */     return false;
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
/*      */   public static boolean prev_permutation(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*      */   {
/* 2128 */     if (paramInt2 - paramInt1 < 2) {
/* 2129 */       return false;
/*      */     }
/* 2131 */     int i = paramInt2 - 1;
/*      */     do {
/* 2133 */       int j = i--;
/* 2134 */       if (less(paramArrayOfString[j], paramArrayOfString[i])) {
/* 2135 */         int k = paramInt2;
/* 2136 */         while (!less(paramArrayOfString[(--k)], paramArrayOfString[i])) {}
/*      */         
/* 2138 */         String str = paramArrayOfString[i];
/* 2139 */         paramArrayOfString[i] = paramArrayOfString[k];
/* 2140 */         paramArrayOfString[k] = str;
/* 2141 */         Modification.reverse(paramArrayOfString, j, paramInt2);
/* 2142 */         return true;
/*      */       }
/* 2144 */     } while (i != paramInt1);
/* 2145 */     Modification.reverse(paramArrayOfString, paramInt1, paramInt2);
/* 2146 */     return false;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/String/Sorting.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */