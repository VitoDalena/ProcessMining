/*     */ package jal.String;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   public static void for_each(String[] paramArrayOfString, int paramInt1, int paramInt2, VoidFunction paramVoidFunction)
/*     */   {
/*  59 */     while (paramInt1 < paramInt2) {
/*  60 */       paramVoidFunction.apply(paramArrayOfString[(paramInt1++)]);
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
/*     */   public static int adjacent_find(String[] paramArrayOfString, int paramInt1, int paramInt2)
/*     */   {
/*  75 */     int i = paramInt1;
/*     */     do {
/*  77 */       if (paramArrayOfString[paramInt1].equals(paramArrayOfString[i])) {
/*  78 */         return paramInt1;
/*     */       }
/*  80 */       paramInt1 = i;i++;
/*  76 */     } while (i < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  83 */     return paramInt2;
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
/*     */   public static int adjacent_find(String[] paramArrayOfString, int paramInt1, int paramInt2, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 102 */     int i = paramInt1;
/*     */     do {
/* 104 */       if (paramBinaryPredicate.apply(paramArrayOfString[paramInt1], paramArrayOfString[i])) {
/* 105 */         return paramInt1;
/*     */       }
/* 107 */       paramInt1 = i;i++;
/* 103 */     } while (i < paramInt2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 110 */     return paramInt2;
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
/*     */   public static int find(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString)
/*     */   {
/* 126 */     while ((paramInt1 < paramInt2) && (!paramString.equals(paramArrayOfString[paramInt1])))
/* 127 */       paramInt1++;
/* 128 */     return paramInt1;
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
/*     */   public static int find_not(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString)
/*     */   {
/* 143 */     while ((paramInt1 < paramInt2) && (paramString.equals(paramArrayOfString[paramInt1])))
/* 144 */       paramInt1++;
/* 145 */     return paramInt1;
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
/*     */   public static int find_if(String[] paramArrayOfString, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 161 */     while ((paramInt1 < paramInt2) && (!paramPredicate.apply(paramArrayOfString[paramInt1])))
/* 162 */       paramInt1++;
/* 163 */     return paramInt1;
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
/*     */   public static int find_if_not(String[] paramArrayOfString, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 179 */     while ((paramInt1 < paramInt2) && (paramPredicate.apply(paramArrayOfString[paramInt1])))
/* 180 */       paramInt1++;
/* 181 */     return paramInt1;
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
/*     */   public static int count_if(String[] paramArrayOfString, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 199 */     int i = 0;
/* 200 */     while (paramInt1 < paramInt2)
/* 201 */       if (paramPredicate.apply(paramArrayOfString[(paramInt1++)])) i++;
/* 202 */     return i;
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
/*     */   public static int count_if_not(String[] paramArrayOfString, int paramInt1, int paramInt2, Predicate paramPredicate)
/*     */   {
/* 218 */     int i = 0;
/* 219 */     while (paramInt1 < paramInt2)
/* 220 */       if (!paramPredicate.apply(paramArrayOfString[(paramInt1++)])) i++;
/* 221 */     return i;
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
/*     */   public static int mismatch(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 242 */     while ((paramInt1 < paramInt2) && (paramArrayOfString1[paramInt1].equals(paramArrayOfString2[paramInt3]))) {
/* 243 */       paramInt1++;
/* 244 */       paramInt3++;
/*     */     }
/* 246 */     return paramInt1;
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
/*     */   public static int mismatch(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 269 */     while ((paramInt1 < paramInt2) && (paramBinaryPredicate.apply(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3]))) {
/* 270 */       paramInt1++;
/* 271 */       paramInt3++;
/*     */     }
/* 273 */     return paramInt1;
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
/*     */   public static boolean equal(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 294 */     while ((paramInt1 < paramInt2) && (paramArrayOfString1[paramInt1].equals(paramArrayOfString2[paramInt3]))) {
/* 295 */       paramInt1++;
/* 296 */       paramInt3++;
/*     */     }
/*     */     
/* 299 */     return paramInt1 >= paramInt2;
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
/*     */   public static boolean equal(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 322 */     while ((paramInt1 < paramInt2) && (paramBinaryPredicate.apply(paramArrayOfString1[paramInt1], paramArrayOfString2[paramInt3]))) {
/* 323 */       paramInt1++;
/* 324 */       paramInt3++;
/*     */     }
/*     */     
/* 327 */     return paramInt1 >= paramInt2;
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
/*     */   public static int search(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 357 */     int i = paramInt2 - paramInt1;
/* 358 */     int j = paramInt4 - paramInt3;
/* 359 */     int k = paramInt1;
/* 360 */     int m = paramInt3;
/*     */     
/* 362 */     if (i < j) {
/* 363 */       return paramInt2;
/*     */     }
/* 365 */     while (m < paramInt4) {
/* 366 */       if (!paramArrayOfString1[(k++)].equals(paramArrayOfString2[(m++)])) {
/* 367 */         if (i == j) {
/* 368 */           return paramInt2;
/*     */         }
/* 370 */         paramInt1++;k = paramInt1;
/* 371 */         m = paramInt3;
/* 372 */         i--;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 377 */     return m == paramInt4 ? paramInt1 : paramInt2;
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
/*     */   public static int search(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryPredicate paramBinaryPredicate)
/*     */   {
/* 410 */     int i = paramInt2 - paramInt1;
/* 411 */     int j = paramInt4 - paramInt3;
/* 412 */     int k = paramInt1;
/* 413 */     int m = paramInt3;
/*     */     
/* 415 */     if (i < j) {
/* 416 */       return paramInt2;
/*     */     }
/* 418 */     while (m < paramInt4) {
/* 419 */       if (!paramBinaryPredicate.apply(paramArrayOfString1[(k++)], paramArrayOfString2[(m++)])) {
/* 420 */         if (i == j) {
/* 421 */           return paramInt2;
/*     */         }
/* 423 */         paramInt1++;k = paramInt1;
/* 424 */         m = paramInt3;
/* 425 */         i--;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 430 */     return m == paramInt4 ? paramInt1 : paramInt2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/String/Inspection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */