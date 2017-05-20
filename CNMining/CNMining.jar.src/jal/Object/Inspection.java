/*     */ package jal.Object;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Inspection
/*     */ {
/*     */   public static void for_each(Object[] paramArrayOfObject, int paramInt1, int paramInt2, VoidFunction paramVoidFunction)
/*     */   {
/*  60 */     while (paramInt1 < paramInt2) {
/*  61 */       paramVoidFunction.apply(paramArrayOfObject[(paramInt1++)]);
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
/*     */   public static int adjacent_find(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*     */   {
/*  76 */     int i = paramInt1;
/*     */     do {
/*  78 */       if (paramArrayOfObject[paramInt1] == paramArrayOfObject[i]) {
/*  79 */         return paramInt1;
/*     */       }
/*  81 */       paramInt1 = i;i++;
/*  77 */     } while (i < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  84 */     return paramInt2;
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
/*     */   public static int adjacent_find(Object[] paramArrayOfObject, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 103 */     int i = paramInt1;
/*     */     do {
/* 105 */       if (paramBinaryPredicate.apply(paramArrayOfObject[paramInt1], paramArrayOfObject[i])) {
/* 106 */         return paramInt1;
/*     */       }
/* 108 */       paramInt1 = i;i++;
/* 104 */     } while (i < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 111 */     return paramInt2;
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
/*     */   public static int find(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject)
/*     */   {
/* 127 */     while ((paramInt1 < paramInt2) && (paramObject != paramArrayOfObject[paramInt1]))
/* 128 */       paramInt1++;
/* 129 */     return paramInt1;
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
/*     */   public static int find_not(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject)
/*     */   {
/* 144 */     while ((paramInt1 < paramInt2) && (paramObject == paramArrayOfObject[paramInt1]))
/* 145 */       paramInt1++;
/* 146 */     return paramInt1;
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
/*     */   public static int find_if(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 162 */     while ((paramInt1 < paramInt2) && (!paramPredicate.apply(paramArrayOfObject[paramInt1])))
/* 163 */       paramInt1++;
/* 164 */     return paramInt1;
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
/*     */   public static int find_if_not(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 180 */     while ((paramInt1 < paramInt2) && (paramPredicate.apply(paramArrayOfObject[paramInt1])))
/* 181 */       paramInt1++;
/* 182 */     return paramInt1;
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
/*     */   public static int count_if(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 200 */     int i = 0;
/* 201 */     while (paramInt1 < paramInt2)
/* 202 */       if (paramPredicate.apply(paramArrayOfObject[(paramInt1++)])) i++;
/* 203 */     return i;
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
/*     */   public static int count_if_not(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 219 */     int i = 0;
/* 220 */     while (paramInt1 < paramInt2)
/* 221 */       if (!paramPredicate.apply(paramArrayOfObject[(paramInt1++)])) i++;
/* 222 */     return i;
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
/*     */   public static int mismatch(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 243 */     while ((paramInt1 < paramInt2) && (paramArrayOfObject1[paramInt1] == paramArrayOfObject2[paramInt3])) {
/* 244 */       paramInt1++;
/* 245 */       paramInt3++;
/*     */     }
/* 247 */     return paramInt1;
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
/*     */   public static int mismatch(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 270 */     while ((paramInt1 < paramInt2) && (paramBinaryPredicate.apply(paramArrayOfObject1[paramInt1], paramArrayOfObject2[paramInt3]))) {
/* 271 */       paramInt1++;
/* 272 */       paramInt3++;
/*     */     }
/* 274 */     return paramInt1;
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
/*     */   public static boolean equal(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 295 */     while ((paramInt1 < paramInt2) && (paramArrayOfObject1[paramInt1] == paramArrayOfObject2[paramInt3])) {
/* 296 */       paramInt1++;
/* 297 */       paramInt3++;
/*     */     }
/*     */     
/* 300 */     return paramInt1 >= paramInt2;
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
/*     */   public static boolean equal(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 323 */     while ((paramInt1 < paramInt2) && (paramBinaryPredicate.apply(paramArrayOfObject1[paramInt1], paramArrayOfObject2[paramInt3]))) {
/* 324 */       paramInt1++;
/* 325 */       paramInt3++;
/*     */     }
/*     */     
/* 328 */     return paramInt1 >= paramInt2;
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
/*     */   public static int search(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 358 */     int i = paramInt2 - paramInt1;
/* 359 */     int j = paramInt4 - paramInt3;
/* 360 */     int k = paramInt1;
/* 361 */     int m = paramInt3;
/*     */     
/* 363 */     if (i < j) {
/* 364 */       return paramInt2;
/*     */     }
/* 366 */     while (m < paramInt4) {
/* 367 */       if (paramArrayOfObject1[(k++)] != paramArrayOfObject2[(m++)]) {
/* 368 */         if (i == j) {
/* 369 */           return paramInt2;
/*     */         }
/* 371 */         paramInt1++;k = paramInt1;
/* 372 */         m = paramInt3;
/* 373 */         i--;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 378 */     return m == paramInt4 ? paramInt1 : paramInt2;
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
/*     */   public static int search(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 411 */     int i = paramInt2 - paramInt1;
/* 412 */     int j = paramInt4 - paramInt3;
/* 413 */     int k = paramInt1;
/* 414 */     int m = paramInt3;
/*     */     
/* 416 */     if (i < j) {
/* 417 */       return paramInt2;
/*     */     }
/* 419 */     while (m < paramInt4) {
/* 420 */       if (!paramBinaryPredicate.apply(paramArrayOfObject1[(k++)], paramArrayOfObject2[(m++)])) {
/* 421 */         if (i == j) {
/* 422 */           return paramInt2;
/*     */         }
/* 424 */         paramInt1++;k = paramInt1;
/* 425 */         m = paramInt3;
/* 426 */         i--;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 431 */     return m == paramInt4 ? paramInt1 : paramInt2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/Object/Inspection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */