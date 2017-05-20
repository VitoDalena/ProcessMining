/*      */ package jal.BYTE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public static void sort(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/*   75 */     if (paramInt2 - paramInt1 >= 13)
/*   76 */       qsortLoop(paramArrayOfByte, paramInt1, paramInt2);
/*   77 */     insertion_sort(paramArrayOfByte, paramInt1, paramInt2);
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
/*      */   public static void sort(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*   96 */     if (paramInt2 - paramInt1 >= 13)
/*   97 */       qsortLoop(paramArrayOfByte, paramInt1, paramInt2, paramBinaryPredicate);
/*   98 */     insertion_sort(paramArrayOfByte, paramInt1, paramInt2, paramBinaryPredicate);
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
/*      */   public static void insertion_sort(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/*  113 */     int i = paramInt1;
/*  114 */     do { int j = paramArrayOfByte[i];
/*  115 */       int k = i;
/*  116 */       for (int m = paramArrayOfByte[(k - 1)]; j < m; 
/*  117 */           m = paramArrayOfByte[(--k - 1)]) {
/*  118 */         paramArrayOfByte[k] = m;
/*  119 */         if (paramInt1 == k - 1) {
/*  120 */           k--;
/*  121 */           break;
/*      */         }
/*      */       }
/*  124 */       paramArrayOfByte[k] = j;i++;
/*  113 */     } while (i < paramInt2);
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
/*      */   public static void insertion_sort(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  141 */     int i = paramInt1;
/*  142 */     do { byte b1 = paramArrayOfByte[i];
/*  143 */       int j = i;
/*  144 */       for (byte b2 = paramArrayOfByte[(j - 1)]; 
/*  145 */           paramBinaryPredicate.apply(b1, b2); 
/*  146 */           b2 = paramArrayOfByte[(--j - 1)]) {
/*  147 */         paramArrayOfByte[j] = b2;
/*  148 */         if (paramInt1 == j - 1) {
/*  149 */           j--;
/*  150 */           break;
/*      */         }
/*      */       }
/*  153 */       paramArrayOfByte[j] = b1;i++;
/*  141 */     } while (i < paramInt2);
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
/*      */   private static int quickPartition(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/*  160 */     int i = paramArrayOfByte[paramInt1];
/*  161 */     int j = paramArrayOfByte[(paramInt2 - 1)];
/*  162 */     int k = paramArrayOfByte[(paramInt1 + (paramInt2 - paramInt1) / 2)];
/*      */     
/*  164 */     if (k < i) {
/*  165 */       if (i < j) {
/*  166 */         k = i;
/*  167 */       } else if (k < j) {
/*  168 */         k = j;
/*      */       }
/*  170 */     } else if (j < i) {
/*  171 */       k = i;
/*  172 */     } else if (j < k) {
/*  173 */       k = j;
/*      */     }
/*  175 */     paramInt1--;
/*      */     for (;;) {
/*  177 */       if (paramArrayOfByte[(++paramInt1)] >= k)
/*      */       {
/*      */ 
/*  180 */         while ((goto 100) || (k < paramArrayOfByte[(--paramInt2)])) {}
/*      */         
/*      */ 
/*  183 */         if (paramInt1 >= paramInt2) {
/*  184 */           return paramInt1;
/*      */         }
/*  186 */         int m = paramArrayOfByte[paramInt1];
/*  187 */         paramArrayOfByte[paramInt1] = paramArrayOfByte[paramInt2];
/*  188 */         paramArrayOfByte[paramInt2] = m;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private static int quickPartition(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  195 */     byte b1 = paramArrayOfByte[paramInt1];
/*  196 */     byte b2 = paramArrayOfByte[(paramInt2 - 1)];
/*  197 */     byte b3 = paramArrayOfByte[(paramInt1 + (paramInt2 - paramInt1) / 2)];
/*      */     
/*  199 */     if (paramBinaryPredicate.apply(b3, b1)) {
/*  200 */       if (paramBinaryPredicate.apply(b1, b2)) {
/*  201 */         b3 = b1;
/*  202 */       } else if (paramBinaryPredicate.apply(b3, b2)) {
/*  203 */         b3 = b2;
/*      */       }
/*  205 */     } else if (paramBinaryPredicate.apply(b2, b1)) {
/*  206 */       b3 = b1;
/*  207 */     } else if (paramBinaryPredicate.apply(b2, b3)) {
/*  208 */       b3 = b2;
/*      */     }
/*  210 */     paramInt1--;
/*      */     for (;;) {
/*  212 */       if (!paramBinaryPredicate.apply(paramArrayOfByte[(++paramInt1)], b3))
/*      */       {
/*      */ 
/*  215 */         while ((goto 142) || (paramBinaryPredicate.apply(b3, paramArrayOfByte[(--paramInt2)]))) {}
/*      */         
/*      */ 
/*  218 */         if (paramInt1 >= paramInt2) {
/*  219 */           return paramInt1;
/*      */         }
/*  221 */         int i = paramArrayOfByte[paramInt1];
/*  222 */         paramArrayOfByte[paramInt1] = paramArrayOfByte[paramInt2];
/*  223 */         paramArrayOfByte[paramInt2] = i;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static void qsortLoop(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/*  232 */     int[] arrayOfInt = new int[56];
/*  233 */     int i = 0;
/*      */     for (;;) {
/*  235 */       int j = quickPartition(paramArrayOfByte, paramInt1, paramInt2);
/*      */       
/*  237 */       if (paramInt2 - j < 13) {
/*  238 */         if (j - paramInt1 < 13) {
/*  239 */           if (i == 0)
/*  240 */             return;
/*  241 */           paramInt2 = arrayOfInt[(--i)];
/*  242 */           paramInt1 = arrayOfInt[(--i)];
/*      */         }
/*      */         else {
/*  245 */           paramInt2 = j;
/*      */         }
/*  247 */       } else if (j - paramInt1 < 13) {
/*  248 */         paramInt1 = j;
/*      */       }
/*  250 */       else if (paramInt2 - j > j - paramInt1) {
/*  251 */         arrayOfInt[(i++)] = j;
/*  252 */         arrayOfInt[(i++)] = paramInt2;
/*  253 */         paramInt2 = j;
/*      */       }
/*      */       else {
/*  256 */         arrayOfInt[(i++)] = paramInt1;
/*  257 */         arrayOfInt[(i++)] = j;
/*  258 */         paramInt1 = j;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private static void qsortLoop(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  266 */     int[] arrayOfInt = new int[56];
/*  267 */     int i = 0;
/*      */     for (;;) {
/*  269 */       int j = quickPartition(paramArrayOfByte, paramInt1, paramInt2, paramBinaryPredicate);
/*      */       
/*  271 */       if (paramInt2 - j < 13) {
/*  272 */         if (j - paramInt1 < 13) {
/*  273 */           if (i == 0)
/*  274 */             return;
/*  275 */           paramInt2 = arrayOfInt[(--i)];
/*  276 */           paramInt1 = arrayOfInt[(--i)];
/*      */         }
/*      */         else {
/*  279 */           paramInt2 = j;
/*      */         }
/*  281 */       } else if (j - paramInt1 < 13) {
/*  282 */         paramInt1 = j;
/*      */       }
/*  284 */       else if (paramInt2 - j > j - paramInt1) {
/*  285 */         arrayOfInt[(i++)] = j;
/*  286 */         arrayOfInt[(i++)] = paramInt2;
/*  287 */         paramInt2 = j;
/*      */       }
/*      */       else {
/*  290 */         arrayOfInt[(i++)] = paramInt1;
/*  291 */         arrayOfInt[(i++)] = j;
/*  292 */         paramInt1 = j;
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
/*      */   public static void stable_sort(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/*  311 */     if (paramInt2 - paramInt1 < 9) {
/*  312 */       insertion_sort(paramArrayOfByte, paramInt1, paramInt2);
/*      */     } else {
/*  314 */       int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  315 */       stable_sort(paramArrayOfByte, paramInt1, i);
/*  316 */       stable_sort(paramArrayOfByte, i, paramInt2);
/*  317 */       inplace_merge(paramArrayOfByte, paramInt1, i, paramInt2);
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
/*      */   public static void stable_sort(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  334 */     if (paramInt2 - paramInt1 < 9) {
/*  335 */       insertion_sort(paramArrayOfByte, paramInt1, paramInt2, paramBinaryPredicate);
/*      */     } else {
/*  337 */       int i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  338 */       stable_sort(paramArrayOfByte, paramInt1, i, paramBinaryPredicate);
/*  339 */       stable_sort(paramArrayOfByte, i, paramInt2, paramBinaryPredicate);
/*  340 */       inplace_merge(paramArrayOfByte, paramInt1, i, paramInt2, paramBinaryPredicate);
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
/*      */   public static void partial_sort(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  361 */     make_heap(paramArrayOfByte, paramInt1, paramInt2);
/*  362 */     int i = paramInt2;
/*  363 */     while (i < paramInt3) {
/*  364 */       if (paramArrayOfByte[i] < paramArrayOfByte[paramInt1]) {
/*  365 */         int j = paramArrayOfByte[i];
/*  366 */         paramArrayOfByte[i] = paramArrayOfByte[paramInt1];
/*  367 */         paramArrayOfByte[paramInt1] = j;
/*  368 */         adjust_heap(paramArrayOfByte, paramInt1, paramInt1, paramInt2);
/*      */       }
/*  370 */       i++;
/*      */     }
/*  372 */     sort_heap(paramArrayOfByte, paramInt1, paramInt2);
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
/*      */   public static void partial_sort(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  394 */     make_heap(paramArrayOfByte, paramInt1, paramInt2, paramBinaryPredicate);
/*  395 */     int i = paramInt2;
/*  396 */     while (i < paramInt3) {
/*  397 */       if (paramBinaryPredicate.apply(paramArrayOfByte[i], paramArrayOfByte[paramInt1])) {
/*  398 */         int j = paramArrayOfByte[i];
/*  399 */         paramArrayOfByte[i] = paramArrayOfByte[paramInt1];
/*  400 */         paramArrayOfByte[paramInt1] = j;
/*  401 */         adjust_heap(paramArrayOfByte, paramInt1, paramInt1, paramInt2, paramBinaryPredicate);
/*      */       }
/*  403 */       i++;
/*      */     }
/*  405 */     sort_heap(paramArrayOfByte, paramInt1, paramInt2, paramBinaryPredicate);
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
/*      */   public static int partial_sort_copy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  427 */     if (paramInt3 == paramInt4) {
/*  428 */       return paramInt4;
/*      */     }
/*  430 */     int i = Math.min(paramInt2 - paramInt1, paramInt4 - paramInt3);
/*  431 */     Modification.copy(paramArrayOfByte1, paramArrayOfByte2, paramInt1, paramInt1 + i, paramInt3);
/*  432 */     paramInt4 = paramInt3 + i;
/*      */     
/*  434 */     make_heap(paramArrayOfByte2, paramInt3, paramInt4);
/*  435 */     for (paramInt1 += i; paramInt1 < paramInt2; paramInt1++)
/*  436 */       if (paramArrayOfByte1[paramInt1] < paramArrayOfByte2[paramInt3]) {
/*  437 */         paramArrayOfByte2[paramInt3] = paramArrayOfByte1[paramInt1];
/*  438 */         adjust_heap(paramArrayOfByte2, paramInt3, paramInt3, paramInt4);
/*      */       }
/*  440 */     sort_heap(paramArrayOfByte2, paramInt3, paramInt4);
/*      */     
/*  442 */     return paramInt4;
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
/*      */   public static int partial_sort_copy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  466 */     if (paramInt3 == paramInt4) {
/*  467 */       return paramInt4;
/*      */     }
/*  469 */     int i = Math.min(paramInt2 - paramInt1, paramInt4 - paramInt3);
/*  470 */     Modification.copy(paramArrayOfByte1, paramArrayOfByte2, paramInt1, paramInt1 + i, paramInt3);
/*  471 */     paramInt4 = paramInt3 + i;
/*      */     
/*  473 */     make_heap(paramArrayOfByte2, paramInt3, paramInt4, paramBinaryPredicate);
/*  474 */     for (paramInt1 += i; paramInt1 < paramInt2; paramInt1++) {
/*  475 */       if (paramBinaryPredicate.apply(paramArrayOfByte1[paramInt1], paramArrayOfByte2[paramInt3])) {
/*  476 */         paramArrayOfByte2[paramInt3] = paramArrayOfByte1[paramInt1];
/*  477 */         adjust_heap(paramArrayOfByte2, paramInt3, paramInt3, paramInt4, paramBinaryPredicate);
/*      */       }
/*      */     }
/*      */     
/*  481 */     sort_heap(paramArrayOfByte2, paramInt3, paramInt4, paramBinaryPredicate);
/*      */     
/*  483 */     return paramInt4;
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
/*      */   public static void nth_element(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  502 */     while (paramInt3 - paramInt1 > 3) {
/*  503 */       int i = quickPartition(paramArrayOfByte, paramInt1, paramInt3);
/*  504 */       if (i <= paramInt2) {
/*  505 */         paramInt1 = i;
/*      */       } else {
/*  507 */         paramInt3 = i;
/*      */       }
/*      */     }
/*  510 */     insertion_sort(paramArrayOfByte, paramInt1, paramInt3);
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
/*      */   public static void nth_element(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  531 */     while (paramInt3 - paramInt1 > 3) {
/*  532 */       int i = quickPartition(paramArrayOfByte, paramInt1, paramInt3, paramBinaryPredicate);
/*  533 */       if (i <= paramInt2) {
/*  534 */         paramInt1 = i;
/*      */       } else {
/*  536 */         paramInt3 = i;
/*      */       }
/*      */     }
/*  539 */     insertion_sort(paramArrayOfByte, paramInt1, paramInt3, paramBinaryPredicate);
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
/*      */   public static int lower_bound(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte)
/*      */   {
/*  560 */     int i = paramInt2 - paramInt1;
/*  561 */     while (i > 0) {
/*  562 */       int j = i / 2;
/*  563 */       int k = paramInt1 + j;
/*  564 */       if (paramArrayOfByte[k] < paramByte) {
/*  565 */         paramInt1 = k + 1;
/*  566 */         i -= j + 1;
/*      */       } else {
/*  568 */         i = j;
/*      */       } }
/*  570 */     return paramInt1;
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
/*      */   public static int lower_bound(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  594 */     int i = paramInt2 - paramInt1;
/*  595 */     while (i > 0) {
/*  596 */       int j = i / 2;
/*  597 */       int k = paramInt1 + j;
/*  598 */       if (paramBinaryPredicate.apply(paramArrayOfByte[k], paramByte)) {
/*  599 */         paramInt1 = k + 1;
/*  600 */         i -= j + 1;
/*      */       } else {
/*  602 */         i = j;
/*      */       } }
/*  604 */     return paramInt1;
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
/*      */   public static int upper_bound(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte)
/*      */   {
/*  625 */     int i = paramInt2 - paramInt1;
/*  626 */     while (i > 0) {
/*  627 */       int j = i / 2;
/*  628 */       int k = paramInt1 + j;
/*  629 */       if (paramByte < paramArrayOfByte[k]) {
/*  630 */         i = j;
/*      */       } else {
/*  632 */         paramInt1 = k + 1;
/*  633 */         i -= j + 1;
/*      */       }
/*      */     }
/*  636 */     return paramInt1;
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
/*      */   public static int upper_bound(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  660 */     int i = paramInt2 - paramInt1;
/*  661 */     while (i > 0) {
/*  662 */       int j = i / 2;
/*  663 */       int k = paramInt1 + j;
/*  664 */       if (paramBinaryPredicate.apply(paramByte, paramArrayOfByte[k])) {
/*  665 */         i = j;
/*      */       } else {
/*  667 */         paramInt1 = k + 1;
/*  668 */         i -= j + 1;
/*      */       }
/*      */     }
/*  671 */     return paramInt1;
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
/*      */   public static Range equal_range(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte)
/*      */   {
/*  697 */     int i = paramInt2 - paramInt1;
/*  698 */     while (i > 0) {
/*  699 */       int j = i / 2;
/*  700 */       int k = paramInt1 + j;
/*  701 */       if (paramArrayOfByte[k] < paramByte) {
/*  702 */         paramInt1 = k + 1;
/*  703 */         i = i - j + 1;
/*      */       }
/*  705 */       else if (paramByte < paramArrayOfByte[k]) {
/*  706 */         i = j;
/*      */       } else {
/*  708 */         int m = lower_bound(paramArrayOfByte, paramInt1, k, paramByte);
/*  709 */         int n = upper_bound(paramArrayOfByte, k + 1, paramInt1 + i, paramByte);
/*  710 */         return new Range(paramArrayOfByte, m, n);
/*      */       }
/*      */     }
/*      */     
/*  714 */     return new Range(paramArrayOfByte, paramInt1, paramInt1);
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
/*      */   public static Range equal_range(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  743 */     int i = paramInt2 - paramInt1;
/*  744 */     while (i > 0) {
/*  745 */       int j = i / 2;
/*  746 */       int k = paramInt1 + j;
/*  747 */       if (paramBinaryPredicate.apply(paramArrayOfByte[k], paramByte)) {
/*  748 */         paramInt1 = k + 1;
/*  749 */         i = i - j + 1;
/*      */       }
/*  751 */       else if (paramBinaryPredicate.apply(paramByte, paramArrayOfByte[k])) {
/*  752 */         i = j;
/*      */       } else {
/*  754 */         int m = lower_bound(paramArrayOfByte, paramInt1, k, paramByte, paramBinaryPredicate);
/*  755 */         int n = upper_bound(paramArrayOfByte, k + 1, paramInt1 + i, paramByte, paramBinaryPredicate);
/*  756 */         return new Range(paramArrayOfByte, m, n);
/*      */       }
/*      */     }
/*      */     
/*  760 */     return new Range(paramArrayOfByte, paramInt1, paramInt1);
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
/*      */   public static boolean binary_search(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte)
/*      */   {
/*  784 */     int i = lower_bound(paramArrayOfByte, paramInt1, paramInt2, paramByte);
/*  785 */     return (i < paramInt2) && (paramByte >= paramArrayOfByte[i]);
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
/*      */   public static boolean binary_search(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  811 */     int i = lower_bound(paramArrayOfByte, paramInt1, paramInt2, paramByte, paramBinaryPredicate);
/*  812 */     return (i < paramInt2) && (!paramBinaryPredicate.apply(paramByte, paramArrayOfByte[i]));
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
/*      */   public static int merge(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */   {
/*  842 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  843 */       if (paramArrayOfByte2[paramInt3] < paramArrayOfByte1[paramInt1]) {
/*  844 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte2[(paramInt3++)];
/*      */       } else
/*  846 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte1[(paramInt1++)];
/*      */     }
/*  848 */     Modification.copy(paramArrayOfByte1, paramArrayOfByte3, paramInt1, paramInt2, paramInt5);
/*  849 */     Modification.copy(paramArrayOfByte2, paramArrayOfByte3, paramInt3, paramInt4, paramInt5);
/*  850 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
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
/*      */   public static int merge(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  881 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/*  882 */       if (paramBinaryPredicate.apply(paramArrayOfByte2[paramInt3], paramArrayOfByte1[paramInt1])) {
/*  883 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte2[(paramInt3++)];
/*      */       } else
/*  885 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte1[(paramInt1++)];
/*      */     }
/*  887 */     Modification.copy(paramArrayOfByte1, paramArrayOfByte3, paramInt1, paramInt2, paramInt5);
/*  888 */     Modification.copy(paramArrayOfByte2, paramArrayOfByte3, paramInt3, paramInt4, paramInt5);
/*  889 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
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
/*      */   public static void inplace_merge(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/*  912 */     if ((paramInt1 >= paramInt2) || (paramInt2 >= paramInt3))
/*      */       return;
/*      */     int i;
/*  915 */     if (paramInt3 - paramInt1 == 2) {
/*  916 */       if (paramArrayOfByte[paramInt2] < paramArrayOfByte[paramInt1]) {
/*  917 */         i = paramArrayOfByte[paramInt1];
/*  918 */         paramArrayOfByte[paramInt1] = paramArrayOfByte[paramInt2];
/*  919 */         paramArrayOfByte[paramInt2] = i;
/*      */       }
/*      */       
/*      */       return;
/*      */     }
/*      */     
/*      */     int j;
/*      */     
/*  927 */     if (paramInt2 - paramInt1 > paramInt3 - paramInt2) {
/*  928 */       i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  929 */       j = lower_bound(paramArrayOfByte, paramInt2, paramInt3, paramArrayOfByte[i]);
/*      */     }
/*      */     else {
/*  932 */       j = paramInt2 + (paramInt3 - paramInt2) / 2;
/*  933 */       i = upper_bound(paramArrayOfByte, paramInt1, paramInt2, paramArrayOfByte[j]);
/*      */     }
/*      */     
/*  936 */     Modification.rotate(paramArrayOfByte, i, paramInt2, j);
/*  937 */     paramInt2 = i + (j - paramInt2);
/*      */     
/*  939 */     inplace_merge(paramArrayOfByte, paramInt1, i, paramInt2);
/*  940 */     inplace_merge(paramArrayOfByte, paramInt2, j, paramInt3);
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
/*      */   public static void inplace_merge(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*      */   {
/*  964 */     if ((paramInt1 >= paramInt2) || (paramInt2 >= paramInt3))
/*      */       return;
/*      */     int i;
/*  967 */     if (paramInt3 - paramInt1 == 2) {
/*  968 */       if (paramBinaryPredicate.apply(paramArrayOfByte[paramInt2], paramArrayOfByte[paramInt1])) {
/*  969 */         i = paramArrayOfByte[paramInt1];
/*  970 */         paramArrayOfByte[paramInt1] = paramArrayOfByte[paramInt2];
/*  971 */         paramArrayOfByte[paramInt2] = i;
/*      */       }
/*      */       
/*      */       return;
/*      */     }
/*      */     
/*      */     int j;
/*      */     
/*  979 */     if (paramInt2 - paramInt1 > paramInt3 - paramInt2) {
/*  980 */       i = paramInt1 + (paramInt2 - paramInt1) / 2;
/*  981 */       j = lower_bound(paramArrayOfByte, paramInt2, paramInt3, paramArrayOfByte[i], paramBinaryPredicate);
/*      */     }
/*      */     else {
/*  984 */       j = paramInt2 + (paramInt3 - paramInt2) / 2;
/*  985 */       i = upper_bound(paramArrayOfByte, paramInt1, paramInt2, paramArrayOfByte[j], paramBinaryPredicate);
/*      */     }
/*      */     
/*  988 */     Modification.rotate(paramArrayOfByte, i, paramInt2, j);
/*  989 */     paramInt2 = i + (j - paramInt2);
/*      */     
/*  991 */     inplace_merge(paramArrayOfByte, paramInt1, i, paramInt2, paramBinaryPredicate);
/*  992 */     inplace_merge(paramArrayOfByte, paramInt2, j, paramInt3, paramBinaryPredicate);
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
/*      */   public static boolean includes(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/* 1018 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1019 */       if (paramArrayOfByte2[paramInt3] < paramArrayOfByte1[paramInt1])
/* 1020 */         return false;
/* 1021 */       if (paramArrayOfByte1[paramInt1] < paramArrayOfByte2[paramInt3]) {
/* 1022 */         paramInt1++;
/*      */       } else {
/* 1024 */         paramInt1++;
/* 1025 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1029 */     return paramInt3 == paramInt4;
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
/*      */   public static boolean includes(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1056 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1057 */       if (paramBinaryPredicate.apply(paramArrayOfByte2[paramInt3], paramArrayOfByte1[paramInt1]))
/* 1058 */         return false;
/* 1059 */       if (paramBinaryPredicate.apply(paramArrayOfByte1[paramInt1], paramArrayOfByte2[paramInt3])) {
/* 1060 */         paramInt1++;
/*      */       } else {
/* 1062 */         paramInt1++;
/* 1063 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1067 */     return paramInt3 == paramInt4;
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
/*      */   public static int set_union(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */   {
/* 1101 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1102 */       if (paramArrayOfByte1[paramInt1] < paramArrayOfByte2[paramInt3]) {
/* 1103 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte1[(paramInt1++)];
/* 1104 */       } else if (paramArrayOfByte2[paramInt3] < paramArrayOfByte1[paramInt1]) {
/* 1105 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte2[(paramInt3++)];
/*      */       } else {
/* 1107 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte1[(paramInt1++)];
/* 1108 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1112 */     Modification.copy(paramArrayOfByte1, paramArrayOfByte3, paramInt1, paramInt2, paramInt5);
/* 1113 */     Modification.copy(paramArrayOfByte2, paramArrayOfByte3, paramInt3, paramInt4, paramInt5);
/* 1114 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
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
/*      */   public static int set_union(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1150 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1151 */       if (paramBinaryPredicate.apply(paramArrayOfByte1[paramInt1], paramArrayOfByte2[paramInt3])) {
/* 1152 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte1[(paramInt1++)];
/* 1153 */       } else if (paramBinaryPredicate.apply(paramArrayOfByte2[paramInt3], paramArrayOfByte1[paramInt1])) {
/* 1154 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte2[(paramInt3++)];
/*      */       } else {
/* 1156 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte1[(paramInt1++)];
/* 1157 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1161 */     Modification.copy(paramArrayOfByte1, paramArrayOfByte3, paramInt1, paramInt2, paramInt5);
/* 1162 */     Modification.copy(paramArrayOfByte2, paramArrayOfByte3, paramInt3, paramInt4, paramInt5);
/* 1163 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
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
/*      */   public static int set_intersection(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */   {
/* 1197 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1198 */       if (paramArrayOfByte1[paramInt1] < paramArrayOfByte2[paramInt3]) {
/* 1199 */         paramInt1++;
/* 1200 */       } else if (paramArrayOfByte2[paramInt3] < paramArrayOfByte1[paramInt1]) {
/* 1201 */         paramInt3++;
/*      */       } else {
/* 1203 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte1[(paramInt1++)];
/* 1204 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1208 */     return paramInt5;
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
/*      */   public static int set_intersection(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1245 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1246 */       if (paramBinaryPredicate.apply(paramArrayOfByte1[paramInt1], paramArrayOfByte2[paramInt3])) {
/* 1247 */         paramInt1++;
/* 1248 */       } else if (paramBinaryPredicate.apply(paramArrayOfByte2[paramInt3], paramArrayOfByte1[paramInt1])) {
/* 1249 */         paramInt3++;
/*      */       } else {
/* 1251 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte1[(paramInt1++)];
/* 1252 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1256 */     return paramInt5;
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
/*      */   public static int set_difference(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */   {
/* 1291 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1292 */       if (paramArrayOfByte1[paramInt1] < paramArrayOfByte2[paramInt3]) {
/* 1293 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte1[(paramInt1++)];
/* 1294 */       } else if (paramArrayOfByte2[paramInt3] < paramArrayOfByte1[paramInt1]) {
/* 1295 */         paramInt3++;
/*      */       } else {
/* 1297 */         paramInt1++;
/* 1298 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1302 */     Modification.copy(paramArrayOfByte1, paramArrayOfByte3, paramInt1, paramInt2, paramInt5);
/* 1303 */     return paramInt5 + (paramInt2 - paramInt1);
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
/*      */   public static int set_difference(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1339 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1340 */       if (paramBinaryPredicate.apply(paramArrayOfByte1[paramInt1], paramArrayOfByte2[paramInt3])) {
/* 1341 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte1[(paramInt1++)];
/* 1342 */       } else if (paramBinaryPredicate.apply(paramArrayOfByte2[paramInt3], paramArrayOfByte1[paramInt1])) {
/* 1343 */         paramInt3++;
/*      */       } else {
/* 1345 */         paramInt1++;
/* 1346 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1350 */     Modification.copy(paramArrayOfByte1, paramArrayOfByte3, paramInt1, paramInt2, paramInt5);
/* 1351 */     return paramInt5 + (paramInt2 - paramInt1);
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
/*      */   public static int set_symmetric_difference(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*      */   {
/* 1387 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1388 */       if (paramArrayOfByte1[paramInt1] < paramArrayOfByte2[paramInt3]) {
/* 1389 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte1[(paramInt1++)];
/* 1390 */       } else if (paramArrayOfByte2[paramInt3] < paramArrayOfByte1[paramInt1]) {
/* 1391 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte2[(paramInt3++)];
/*      */       } else {
/* 1393 */         paramInt1++;
/* 1394 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1398 */     Modification.copy(paramArrayOfByte1, paramArrayOfByte3, paramInt1, paramInt2, paramInt5);
/* 1399 */     Modification.copy(paramArrayOfByte2, paramArrayOfByte3, paramInt3, paramInt4, paramInt5);
/* 1400 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
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
/*      */   public static int set_symmetric_difference(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1438 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1439 */       if (paramBinaryPredicate.apply(paramArrayOfByte1[paramInt1], paramArrayOfByte2[paramInt3])) {
/* 1440 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte1[(paramInt1++)];
/* 1441 */       } else if (paramBinaryPredicate.apply(paramArrayOfByte2[paramInt3], paramArrayOfByte1[paramInt1])) {
/* 1442 */         paramArrayOfByte3[(paramInt5++)] = paramArrayOfByte2[(paramInt3++)];
/*      */       } else {
/* 1444 */         paramInt1++;
/* 1445 */         paramInt3++;
/*      */       }
/*      */     }
/*      */     
/* 1449 */     Modification.copy(paramArrayOfByte1, paramArrayOfByte3, paramInt1, paramInt2, paramInt5);
/* 1450 */     Modification.copy(paramArrayOfByte2, paramArrayOfByte3, paramInt3, paramInt4, paramInt5);
/* 1451 */     return paramInt5 + (paramInt2 - paramInt1) + (paramInt4 - paramInt3);
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
/*      */   public static void push_heap(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 1469 */     if (paramInt2 - paramInt1 < 2) return;
/* 1470 */     int i = paramArrayOfByte[(--paramInt2)];
/* 1471 */     int j = paramInt1 + (paramInt2 - paramInt1 - 1) / 2;
/* 1472 */     while ((paramInt2 > paramInt1) && (paramArrayOfByte[j] < i)) {
/* 1473 */       paramArrayOfByte[paramInt2] = paramArrayOfByte[j];
/* 1474 */       paramInt2 = j;
/* 1475 */       j = paramInt1 + (paramInt2 - paramInt1 - 1) / 2;
/*      */     }
/* 1477 */     paramArrayOfByte[paramInt2] = i;
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
/*      */   public static void push_heap(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1496 */     if (paramInt2 - paramInt1 < 2) return;
/* 1497 */     byte b = paramArrayOfByte[(--paramInt2)];
/* 1498 */     int i = paramInt1 + (paramInt2 - paramInt1 - 1) / 2;
/* 1499 */     while ((paramInt2 > paramInt1) && (paramBinaryPredicate.apply(paramArrayOfByte[i], b))) {
/* 1500 */       paramArrayOfByte[paramInt2] = paramArrayOfByte[i];
/* 1501 */       paramInt2 = i;
/* 1502 */       i = paramInt1 + (paramInt2 - paramInt1 - 1) / 2;
/*      */     }
/* 1504 */     paramArrayOfByte[paramInt2] = b;
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
/*      */   private static void adjust_heap(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 1523 */     int i = paramArrayOfByte[paramInt2];
/* 1524 */     int j = paramInt3 - paramInt1;
/* 1525 */     int k = paramInt2 - paramInt1;
/* 1526 */     int m = 2 * k + 2;
/* 1527 */     while (m < j) {
/* 1528 */       if (paramArrayOfByte[(paramInt1 + m)] < paramArrayOfByte[(paramInt1 + (m - 1))])
/*      */       {
/* 1530 */         m--; }
/* 1531 */       paramArrayOfByte[(paramInt1 + k)] = paramArrayOfByte[(paramInt1 + m)];
/* 1532 */       k = m++;
/* 1533 */       m *= 2;
/*      */     }
/* 1535 */     if (m-- == j) {
/* 1536 */       paramArrayOfByte[(paramInt1 + k)] = paramArrayOfByte[(paramInt1 + m)];
/* 1537 */       k = m;
/*      */     }
/*      */     
/* 1540 */     int n = (k - 1) / 2;
/* 1541 */     int i1 = paramInt2 - paramInt1;
/*      */     
/* 1543 */     while ((k != i1) && (paramArrayOfByte[(paramInt1 + n)] < i)) {
/* 1544 */       paramArrayOfByte[(paramInt1 + k)] = paramArrayOfByte[(paramInt1 + n)];
/* 1545 */       k = n;
/* 1546 */       n = (k - 1) / 2;
/*      */     }
/* 1548 */     paramArrayOfByte[(paramInt1 + k)] = i;
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
/*      */   private static void adjust_heap(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1568 */     byte b = paramArrayOfByte[paramInt2];
/* 1569 */     int i = paramInt3 - paramInt1;
/* 1570 */     int j = paramInt2 - paramInt1;
/* 1571 */     int k = 2 * j + 2;
/* 1572 */     while (k < i) {
/* 1573 */       if (paramBinaryPredicate.apply(paramArrayOfByte[(paramInt1 + k)], paramArrayOfByte[(paramInt1 + (k - 1))]))
/*      */       {
/* 1575 */         k--; }
/* 1576 */       paramArrayOfByte[(paramInt1 + j)] = paramArrayOfByte[(paramInt1 + k)];
/* 1577 */       j = k++;
/* 1578 */       k *= 2;
/*      */     }
/* 1580 */     if (k-- == i) {
/* 1581 */       paramArrayOfByte[(paramInt1 + j)] = paramArrayOfByte[(paramInt1 + k)];
/* 1582 */       j = k;
/*      */     }
/*      */     
/* 1585 */     int m = (j - 1) / 2;
/* 1586 */     int n = paramInt2 - paramInt1;
/*      */     
/* 1588 */     while ((j != n) && (paramBinaryPredicate.apply(paramArrayOfByte[(paramInt1 + m)], b))) {
/* 1589 */       paramArrayOfByte[(paramInt1 + j)] = paramArrayOfByte[(paramInt1 + m)];
/* 1590 */       j = m;
/* 1591 */       m = (j - 1) / 2;
/*      */     }
/* 1593 */     paramArrayOfByte[(paramInt1 + j)] = b;
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
/*      */   public static void pop_heap(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 1611 */     if (paramInt2 - paramInt1 < 2) return;
/* 1612 */     int i = paramArrayOfByte[(--paramInt2)];
/* 1613 */     paramArrayOfByte[paramInt2] = paramArrayOfByte[paramInt1];
/* 1614 */     paramArrayOfByte[paramInt1] = i;
/* 1615 */     adjust_heap(paramArrayOfByte, paramInt1, paramInt1, paramInt2);
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
/*      */   public static void pop_heap(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1634 */     if (paramInt2 - paramInt1 < 2) return;
/* 1635 */     int i = paramArrayOfByte[(--paramInt2)];
/* 1636 */     paramArrayOfByte[paramInt2] = paramArrayOfByte[paramInt1];
/* 1637 */     paramArrayOfByte[paramInt1] = i;
/* 1638 */     adjust_heap(paramArrayOfByte, paramInt1, paramInt1, paramInt2, paramBinaryPredicate);
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
/*      */   public static void make_heap(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 1657 */     if (paramInt2 - paramInt1 < 2) return;
/* 1658 */     int i = (paramInt2 - paramInt1 - 2) / 2;
/*      */     do
/*      */     {
/* 1661 */       adjust_heap(paramArrayOfByte, paramInt1, paramInt1 + i, paramInt2);
/* 1662 */     } while (i-- != 0);
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
/*      */   public static void make_heap(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1682 */     if (paramInt2 - paramInt1 < 2) return;
/* 1683 */     int i = (paramInt2 - paramInt1 - 2) / 2;
/*      */     do
/*      */     {
/* 1686 */       adjust_heap(paramArrayOfByte, paramInt1, paramInt1 + i, paramInt2, paramBinaryPredicate);
/* 1687 */     } while (i-- != 0);
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
/*      */   public static void sort_heap(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 1704 */     while (paramInt2 - paramInt1 > 1) {
/* 1705 */       int i = paramArrayOfByte[(--paramInt2)];
/* 1706 */       paramArrayOfByte[paramInt2] = paramArrayOfByte[paramInt1];
/* 1707 */       paramArrayOfByte[paramInt1] = i;
/* 1708 */       adjust_heap(paramArrayOfByte, paramInt1, paramInt1, paramInt2);
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
/*      */   public static void sort_heap(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1727 */     while (paramInt2 - paramInt1 > 1) {
/* 1728 */       int i = paramArrayOfByte[(--paramInt2)];
/* 1729 */       paramArrayOfByte[paramInt2] = paramArrayOfByte[paramInt1];
/* 1730 */       paramArrayOfByte[paramInt1] = i;
/* 1731 */       adjust_heap(paramArrayOfByte, paramInt1, paramInt1, paramInt2, paramBinaryPredicate);
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
/*      */   public static int max_element(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 1750 */     if (paramInt1 >= paramInt2) { return paramInt2;
/*      */     }
/* 1752 */     int i = paramInt1;
/*      */     do
/*      */     {
/* 1755 */       if (paramArrayOfByte[i] < paramArrayOfByte[paramInt1]) {
/* 1756 */         i = paramInt1;
/*      */       }
/* 1754 */       paramInt1++; } while (paramInt1 < paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 1758 */     return i;
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
/*      */   public static int max_element(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1777 */     if (paramInt1 >= paramInt2) { return paramInt2;
/*      */     }
/* 1779 */     int i = paramInt1;
/*      */     do
/*      */     {
/* 1782 */       if (paramBinaryPredicate.apply(paramArrayOfByte[i], paramArrayOfByte[paramInt1])) {
/* 1783 */         i = paramInt1;
/*      */       }
/* 1781 */       paramInt1++; } while (paramInt1 < paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 1785 */     return i;
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
/*      */   public static int min_element(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 1803 */     if (paramInt1 >= paramInt2) { return paramInt2;
/*      */     }
/* 1805 */     int i = paramInt1;
/*      */     do
/*      */     {
/* 1808 */       if (paramArrayOfByte[paramInt1] < paramArrayOfByte[i]) {
/* 1809 */         i = paramInt1;
/*      */       }
/* 1807 */       paramInt1++; } while (paramInt1 < paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 1811 */     return i;
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
/*      */   public static int min_element(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1830 */     if (paramInt1 >= paramInt2) { return paramInt2;
/*      */     }
/* 1832 */     int i = paramInt1;
/*      */     do
/*      */     {
/* 1835 */       if (paramBinaryPredicate.apply(paramArrayOfByte[paramInt1], paramArrayOfByte[i])) {
/* 1836 */         i = paramInt1;
/*      */       }
/* 1834 */       paramInt1++; } while (paramInt1 < paramInt2);
/*      */     
/*      */ 
/*      */ 
/* 1838 */     return i;
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
/*      */   public static boolean lexicographical_compare(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/* 1861 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1862 */       if (paramArrayOfByte1[paramInt1] < paramArrayOfByte2[paramInt3])
/* 1863 */         return true;
/* 1864 */       if (paramArrayOfByte2[(paramInt3++)] < paramArrayOfByte1[(paramInt1++)])
/* 1865 */         return false;
/*      */     }
/* 1867 */     return (paramInt1 == paramInt2) && (paramInt3 != paramInt4);
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
/*      */   public static boolean lexicographical_compare(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1892 */     while ((paramInt1 < paramInt2) && (paramInt3 < paramInt4)) {
/* 1893 */       if (paramBinaryPredicate.apply(paramArrayOfByte1[paramInt1], paramArrayOfByte2[paramInt3]))
/* 1894 */         return true;
/* 1895 */       if (paramBinaryPredicate.apply(paramArrayOfByte2[(paramInt3++)], paramArrayOfByte1[(paramInt1++)]))
/* 1896 */         return false;
/*      */     }
/* 1898 */     return (paramInt1 == paramInt2) && (paramInt3 != paramInt4);
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
/*      */   public static boolean next_permutation(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 1919 */     if (paramInt2 - paramInt1 < 2) {
/* 1920 */       return false;
/*      */     }
/* 1922 */     int i = paramInt2 - 1;
/*      */     do {
/* 1924 */       int j = i--;
/* 1925 */       if (paramArrayOfByte[i] < paramArrayOfByte[j]) {
/* 1926 */         int k = paramInt2;
/* 1927 */         while (paramArrayOfByte[i] >= paramArrayOfByte[(--k)]) {}
/*      */         
/* 1929 */         int m = paramArrayOfByte[i];
/* 1930 */         paramArrayOfByte[i] = paramArrayOfByte[k];
/* 1931 */         paramArrayOfByte[k] = m;
/* 1932 */         Modification.reverse(paramArrayOfByte, j, paramInt2);
/* 1933 */         return true;
/*      */       }
/* 1935 */     } while (i != paramInt1);
/* 1936 */     Modification.reverse(paramArrayOfByte, paramInt1, paramInt2);
/* 1937 */     return false;
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
/*      */   public static boolean next_permutation(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 1963 */     if (paramInt2 - paramInt1 < 2) {
/* 1964 */       return false;
/*      */     }
/* 1966 */     int i = paramInt2 - 1;
/*      */     do {
/* 1968 */       int j = i--;
/* 1969 */       if (paramBinaryPredicate.apply(paramArrayOfByte[i], paramArrayOfByte[j])) {
/* 1970 */         int k = paramInt2;
/* 1971 */         while (!paramBinaryPredicate.apply(paramArrayOfByte[i], paramArrayOfByte[(--k)])) {}
/*      */         
/* 1973 */         int m = paramArrayOfByte[i];
/* 1974 */         paramArrayOfByte[i] = paramArrayOfByte[k];
/* 1975 */         paramArrayOfByte[k] = m;
/* 1976 */         Modification.reverse(paramArrayOfByte, j, paramInt2);
/* 1977 */         return true;
/*      */       }
/* 1979 */     } while (i != paramInt1);
/* 1980 */     Modification.reverse(paramArrayOfByte, paramInt1, paramInt2);
/* 1981 */     return false;
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
/*      */   public static boolean prev_permutation(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 2004 */     if (paramInt2 - paramInt1 < 2) {
/* 2005 */       return false;
/*      */     }
/* 2007 */     int i = paramInt2 - 1;
/*      */     do {
/* 2009 */       int j = i--;
/* 2010 */       if (paramArrayOfByte[j] < paramArrayOfByte[i]) {
/* 2011 */         int k = paramInt2;
/* 2012 */         while (paramArrayOfByte[(--k)] >= paramArrayOfByte[i]) {}
/*      */         
/* 2014 */         int m = paramArrayOfByte[i];
/* 2015 */         paramArrayOfByte[i] = paramArrayOfByte[k];
/* 2016 */         paramArrayOfByte[k] = m;
/* 2017 */         Modification.reverse(paramArrayOfByte, j, paramInt2);
/* 2018 */         return true;
/*      */       }
/* 2020 */     } while (i != paramInt1);
/* 2021 */     Modification.reverse(paramArrayOfByte, paramInt1, paramInt2);
/* 2022 */     return false;
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
/*      */   public static boolean prev_permutation(byte[] paramArrayOfByte, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*      */   {
/* 2048 */     if (paramInt2 - paramInt1 < 2) {
/* 2049 */       return false;
/*      */     }
/* 2051 */     int i = paramInt2 - 1;
/*      */     do {
/* 2053 */       int j = i--;
/* 2054 */       if (paramBinaryPredicate.apply(paramArrayOfByte[j], paramArrayOfByte[i])) {
/* 2055 */         int k = paramInt2;
/* 2056 */         while (!paramBinaryPredicate.apply(paramArrayOfByte[(--k)], paramArrayOfByte[i])) {}
/*      */         
/* 2058 */         int m = paramArrayOfByte[i];
/* 2059 */         paramArrayOfByte[i] = paramArrayOfByte[k];
/* 2060 */         paramArrayOfByte[k] = m;
/* 2061 */         Modification.reverse(paramArrayOfByte, j, paramInt2);
/* 2062 */         return true;
/*      */       }
/* 2064 */     } while (i != paramInt1);
/* 2065 */     Modification.reverse(paramArrayOfByte, paramInt1, paramInt2);
/* 2066 */     return false;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/BYTE/Sorting.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */