/*     */ package cern.colt;
/*     */ 
/*     */ import cern.colt.function.IntComparator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GenericSorting
/*     */ {
/*     */   private static final int SMALL = 7;
/*     */   private static final int MEDIUM = 40;
/*     */   
/*     */   private static void inplace_merge(int paramInt1, int paramInt2, int paramInt3, IntComparator paramIntComparator, Swapper paramSwapper)
/*     */   {
/* 164 */     if ((paramInt1 >= paramInt2) || (paramInt2 >= paramInt3))
/* 165 */       return;
/* 166 */     if (paramInt3 - paramInt1 == 2) {
/* 167 */       if (paramIntComparator.compare(paramInt2, paramInt1) < 0) {
/* 168 */         paramSwapper.swap(paramInt1, paramInt2);
/*     */       }
/*     */       return;
/*     */     }
/*     */     int i;
/*     */     int j;
/* 174 */     if (paramInt2 - paramInt1 > paramInt3 - paramInt2) {
/* 175 */       i = paramInt1 + (paramInt2 - paramInt1) / 2;
/* 176 */       j = lower_bound(paramInt2, paramInt3, i, paramIntComparator);
/*     */     }
/*     */     else {
/* 179 */       j = paramInt2 + (paramInt3 - paramInt2) / 2;
/* 180 */       i = upper_bound(paramInt1, paramInt2, j, paramIntComparator);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 187 */     int k = i;int m = paramInt2;int n = j;
/* 188 */     if ((m != k) && (m != n)) {
/* 189 */       int i1 = k;int i2 = m;
/* 190 */       while (i1 < --i2) paramSwapper.swap(i1++, i2);
/* 191 */       i1 = m;i2 = n;
/* 192 */       while (i1 < --i2) paramSwapper.swap(i1++, i2);
/* 193 */       i1 = k;i2 = n;
/* 194 */       while (i1 < --i2) { paramSwapper.swap(i1++, i2);
/*     */       }
/*     */     }
/*     */     
/* 198 */     paramInt2 = i + (j - paramInt2);
/* 199 */     inplace_merge(paramInt1, i, paramInt2, paramIntComparator, paramSwapper);
/* 200 */     inplace_merge(paramInt2, j, paramInt3, paramIntComparator, paramSwapper);
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
/*     */   public static int lower_bound(int paramInt1, int paramInt2, int paramInt3, IntComparator paramIntComparator)
/*     */   {
/* 222 */     int i = paramInt2 - paramInt1;
/* 223 */     while (i > 0) {
/* 224 */       int j = i / 2;
/* 225 */       int k = paramInt1 + j;
/* 226 */       if (paramIntComparator.compare(k, paramInt3) < 0) {
/* 227 */         paramInt1 = k + 1;
/* 228 */         i -= j + 1;
/*     */       }
/*     */       else {
/* 231 */         i = j;
/*     */       }
/*     */     }
/* 234 */     return paramInt1;
/*     */   }
/*     */   
/*     */ 
/*     */   private static int med3(int paramInt1, int paramInt2, int paramInt3, IntComparator paramIntComparator)
/*     */   {
/* 240 */     int i = paramIntComparator.compare(paramInt1, paramInt2);
/* 241 */     int j = paramIntComparator.compare(paramInt1, paramInt3);
/* 242 */     int k = paramIntComparator.compare(paramInt2, paramInt3);
/* 243 */     return j > 0 ? paramInt3 : k > 0 ? paramInt2 : i < 0 ? paramInt1 : j < 0 ? paramInt3 : k < 0 ? paramInt2 : paramInt1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void mergeSort(int paramInt1, int paramInt2, IntComparator paramIntComparator, Swapper paramSwapper)
/*     */   {
/* 279 */     int i = paramInt2 - paramInt1;
/*     */     
/*     */ 
/* 282 */     if (i < 7) {
/* 283 */       for (j = paramInt1; j < paramInt2; j++) {
/* 284 */         for (int k = j; (k > paramInt1) && (paramIntComparator.compare(k - 1, k) > 0); k--) {
/* 285 */           paramSwapper.swap(k, k - 1);
/*     */         }
/*     */       }
/* 288 */       return;
/*     */     }
/*     */     
/*     */ 
/* 292 */     int j = (paramInt1 + paramInt2) / 2;
/* 293 */     mergeSort(paramInt1, j, paramIntComparator, paramSwapper);
/* 294 */     mergeSort(j, paramInt2, paramIntComparator, paramSwapper);
/*     */     
/*     */ 
/*     */ 
/* 298 */     if (paramIntComparator.compare(j - 1, j) <= 0) { return;
/*     */     }
/*     */     
/* 301 */     inplace_merge(paramInt1, j, paramInt2, paramIntComparator, paramSwapper);
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
/*     */   public static void quickSort(int paramInt1, int paramInt2, IntComparator paramIntComparator, Swapper paramSwapper)
/*     */   {
/* 327 */     quickSort1(paramInt1, paramInt2 - paramInt1, paramIntComparator, paramSwapper);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static void quickSort1(int paramInt1, int paramInt2, IntComparator paramIntComparator, Swapper paramSwapper)
/*     */   {
/* 334 */     if (paramInt2 < 7) {
/* 335 */       for (i = paramInt1; i < paramInt2 + paramInt1; i++) {
/* 336 */         for (j = i; (j > paramInt1) && (paramIntComparator.compare(j - 1, j) > 0); j--)
/* 337 */           paramSwapper.swap(j, j - 1);
/*     */       }
/* 339 */       return;
/*     */     }
/*     */     
/*     */ 
/* 343 */     int i = paramInt1 + paramInt2 / 2;
/* 344 */     if (paramInt2 > 7) {
/* 345 */       j = paramInt1;
/* 346 */       k = paramInt1 + paramInt2 - 1;
/* 347 */       if (paramInt2 > 40) {
/* 348 */         m = paramInt2 / 8;
/* 349 */         j = med3(j, j + m, j + 2 * m, paramIntComparator);
/* 350 */         i = med3(i - m, i, i + m, paramIntComparator);
/* 351 */         k = med3(k - 2 * m, k - m, k, paramIntComparator);
/*     */       }
/* 353 */       i = med3(j, i, k, paramIntComparator);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 358 */     int j = paramInt1;int k = j;int m = paramInt1 + paramInt2 - 1;int n = m;
/*     */     
/*     */     break label242;
/*     */     break label242;
/* 362 */     if (i1 == 0) {
/* 363 */       if (j == i) { i = k;
/* 364 */       } else if (k == i) i = j;
/* 365 */       paramSwapper.swap(j++, k);
/*     */     }
/* 367 */     k++;
/*     */     for (;;)
/*     */     {
/*     */       label242:
/* 361 */       if (k <= m) { if ((i1 = paramIntComparator.compare(k, i)) <= 0) {
/*     */           break;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 369 */       while ((m >= k) && ((i1 = paramIntComparator.compare(m, i)) >= 0)) {
/* 370 */         if (i1 == 0) {
/* 371 */           if (m == i) { i = n;
/* 372 */           } else if (n == i) i = m;
/* 373 */           paramSwapper.swap(m, n--);
/*     */         }
/* 375 */         m--;
/*     */       }
/* 377 */       if (k > m) break label395;
/* 378 */       if (k == i) { i = n;
/* 379 */       } else if (m == i) i = m;
/* 380 */       paramSwapper.swap(k++, m--);
/*     */     }
/*     */     
/*     */     label395:
/* 384 */     int i2 = paramInt1 + paramInt2;
/* 385 */     int i1 = Math.min(j - paramInt1, k - j);vecswap(paramSwapper, paramInt1, k - i1, i1);
/* 386 */     i1 = Math.min(n - m, i2 - n - 1);vecswap(paramSwapper, k, i2 - i1, i1);
/*     */     
/*     */ 
/* 389 */     if ((i1 = k - j) > 1)
/* 390 */       quickSort1(paramInt1, i1, paramIntComparator, paramSwapper);
/* 391 */     if ((i1 = n - m) > 1) {
/* 392 */       quickSort1(i2 - i1, i1, paramIntComparator, paramSwapper);
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
/*     */   private static void reverse(int paramInt1, int paramInt2, Swapper paramSwapper)
/*     */   {
/* 405 */     while (paramInt1 < --paramInt2) {
/* 406 */       paramSwapper.swap(paramInt1++, paramInt2);
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
/*     */   public static void rotate(int paramInt1, int paramInt2, int paramInt3, Swapper paramSwapper)
/*     */   {
/* 424 */     if ((paramInt2 != paramInt1) && (paramInt2 != paramInt3)) {
/* 425 */       reverse(paramInt1, paramInt2, paramSwapper);
/* 426 */       reverse(paramInt2, paramInt3, paramSwapper);
/* 427 */       reverse(paramInt1, paramInt3, paramSwapper);
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
/*     */   public static int upper_bound(int paramInt1, int paramInt2, int paramInt3, IntComparator paramIntComparator)
/*     */   {
/* 450 */     int i = paramInt2 - paramInt1;
/* 451 */     while (i > 0) {
/* 452 */       int j = i / 2;
/* 453 */       int k = paramInt1 + j;
/* 454 */       if (paramIntComparator.compare(paramInt3, k) < 0) {
/* 455 */         i = j;
/*     */       }
/*     */       else {
/* 458 */         paramInt1 = k + 1;
/* 459 */         i -= j + 1;
/*     */       }
/*     */     }
/* 462 */     return paramInt1;
/*     */   }
/*     */   
/*     */ 
/*     */   private static void vecswap(Swapper paramSwapper, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 468 */     for (int i = 0; i < paramInt3; paramInt2++) { paramSwapper.swap(paramInt1, paramInt2);i++;paramInt1++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/GenericSorting.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */