/*     */ package jal.SHORT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   public static void for_each(short[] paramArrayOfShort, int paramInt1, int paramInt2, VoidFunction paramVoidFunction)
/*     */   {
/*  51 */     while (paramInt1 < paramInt2) {
/*  52 */       paramVoidFunction.apply(paramArrayOfShort[(paramInt1++)]);
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
/*     */   public static int adjacent_find(short[] paramArrayOfShort, int paramInt1, int paramInt2)
/*     */   {
/*  67 */     int i = paramInt1;
/*     */     do {
/*  69 */       if (paramArrayOfShort[paramInt1] == paramArrayOfShort[i]) {
/*  70 */         return paramInt1;
/*     */       }
/*  72 */       paramInt1 = i;i++;
/*  68 */     } while (i < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  75 */     return paramInt2;
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
/*     */   public static int adjacent_find(short[] paramArrayOfShort, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*     */   {
/*  94 */     int i = paramInt1;
/*     */     do {
/*  96 */       if (paramBinaryPredicate.apply(paramArrayOfShort[paramInt1], paramArrayOfShort[i])) {
/*  97 */         return paramInt1;
/*     */       }
/*  99 */       paramInt1 = i;i++;
/*  95 */     } while (i < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 102 */     return paramInt2;
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
/*     */   public static int find(short[] paramArrayOfShort, int paramInt1, int paramInt2, short paramShort)
/*     */   {
/* 118 */     while ((paramInt1 < paramInt2) && (paramShort != paramArrayOfShort[paramInt1]))
/* 119 */       paramInt1++;
/* 120 */     return paramInt1;
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
/*     */   public static int find_not(short[] paramArrayOfShort, int paramInt1, int paramInt2, short paramShort)
/*     */   {
/* 135 */     while ((paramInt1 < paramInt2) && (paramShort == paramArrayOfShort[paramInt1]))
/* 136 */       paramInt1++;
/* 137 */     return paramInt1;
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
/*     */   public static int find_if(short[] paramArrayOfShort, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 153 */     while ((paramInt1 < paramInt2) && (!paramPredicate.apply(paramArrayOfShort[paramInt1])))
/* 154 */       paramInt1++;
/* 155 */     return paramInt1;
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
/*     */   public static int find_if_not(short[] paramArrayOfShort, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 171 */     while ((paramInt1 < paramInt2) && (paramPredicate.apply(paramArrayOfShort[paramInt1])))
/* 172 */       paramInt1++;
/* 173 */     return paramInt1;
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
/*     */   public static int count_if(short[] paramArrayOfShort, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 191 */     int i = 0;
/* 192 */     while (paramInt1 < paramInt2)
/* 193 */       if (paramPredicate.apply(paramArrayOfShort[(paramInt1++)])) i++;
/* 194 */     return i;
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
/*     */   public static int count_if_not(short[] paramArrayOfShort, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 209 */     int i = 0;
/* 210 */     while (paramInt1 < paramInt2)
/* 211 */       if (!paramPredicate.apply(paramArrayOfShort[(paramInt1++)])) i++;
/* 212 */     return i;
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
/*     */   public static int mismatch(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 233 */     while ((paramInt1 < paramInt2) && (paramArrayOfShort1[paramInt1] == paramArrayOfShort2[paramInt3])) {
/* 234 */       paramInt1++;
/* 235 */       paramInt3++;
/*     */     }
/* 237 */     return paramInt1;
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
/*     */   public static int mismatch(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 260 */     while ((paramInt1 < paramInt2) && (paramBinaryPredicate.apply(paramArrayOfShort1[paramInt1], paramArrayOfShort2[paramInt3]))) {
/* 261 */       paramInt1++;
/* 262 */       paramInt3++;
/*     */     }
/* 264 */     return paramInt1;
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
/*     */   public static boolean equal(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 285 */     while ((paramInt1 < paramInt2) && (paramArrayOfShort1[paramInt1] == paramArrayOfShort2[paramInt3])) {
/* 286 */       paramInt1++;
/* 287 */       paramInt3++;
/*     */     }
/*     */     
/* 290 */     return paramInt1 >= paramInt2;
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
/*     */   public static boolean equal(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 313 */     while ((paramInt1 < paramInt2) && (paramBinaryPredicate.apply(paramArrayOfShort1[paramInt1], paramArrayOfShort2[paramInt3]))) {
/* 314 */       paramInt1++;
/* 315 */       paramInt3++;
/*     */     }
/*     */     
/* 318 */     return paramInt1 >= paramInt2;
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
/*     */   public static int search(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 348 */     int i = paramInt2 - paramInt1;
/* 349 */     int j = paramInt4 - paramInt3;
/* 350 */     int k = paramInt1;
/* 351 */     int m = paramInt3;
/*     */     
/* 353 */     if (i < j) {
/* 354 */       return paramInt2;
/*     */     }
/* 356 */     while (m < paramInt4) {
/* 357 */       if (paramArrayOfShort1[(k++)] != paramArrayOfShort2[(m++)]) {
/* 358 */         if (i == j) {
/* 359 */           return paramInt2;
/*     */         }
/* 361 */         paramInt1++;k = paramInt1;
/* 362 */         m = paramInt3;
/* 363 */         i--;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 368 */     return m == paramInt4 ? paramInt1 : paramInt2;
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
/*     */   public static int search(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 401 */     int i = paramInt2 - paramInt1;
/* 402 */     int j = paramInt4 - paramInt3;
/* 403 */     int k = paramInt1;
/* 404 */     int m = paramInt3;
/*     */     
/* 406 */     if (i < j) {
/* 407 */       return paramInt2;
/*     */     }
/* 409 */     while (m < paramInt4) {
/* 410 */       if (!paramBinaryPredicate.apply(paramArrayOfShort1[(k++)], paramArrayOfShort2[(m++)])) {
/* 411 */         if (i == j) {
/* 412 */           return paramInt2;
/*     */         }
/* 414 */         paramInt1++;k = paramInt1;
/* 415 */         m = paramInt3;
/* 416 */         i--;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 421 */     return m == paramInt4 ? paramInt1 : paramInt2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/SHORT/Inspection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */