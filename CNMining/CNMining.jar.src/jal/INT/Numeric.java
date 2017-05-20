/*     */ package jal.INT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Numeric
/*     */ {
/*     */   public static int accumulate(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  52 */     int i = paramInt3;
/*  53 */     while (paramInt1 < paramInt2)
/*  54 */       i += paramArrayOfInt[(paramInt1++)];
/*  55 */     return i;
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
/*     */   public static int accumulate(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/*  74 */     int i = paramInt3;
/*  75 */     while (paramInt1 < paramInt2)
/*  76 */       i = paramBinaryOperator.apply(i, paramArrayOfInt[(paramInt1++)]);
/*  77 */     return i;
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
/*     */   public static int inner_product(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/*  97 */     int i = paramInt4;
/*  98 */     while (paramInt1 < paramInt2)
/*  99 */       i += paramArrayOfInt1[(paramInt1++)] * paramArrayOfInt2[(paramInt3++)];
/* 100 */     return i;
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
/*     */   public static int inner_product(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BinaryOperator paramBinaryOperator1, BinaryOperator paramBinaryOperator2)
/*     */   {
/* 126 */     int i = paramInt4;
/* 127 */     while (paramInt1 < paramInt2)
/* 128 */       i = paramBinaryOperator1.apply(i, paramBinaryOperator2.apply(paramArrayOfInt1[(paramInt1++)], paramArrayOfInt2[(paramInt3++)]));
/* 129 */     return i;
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
/*     */   public static int partial_sum(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 152 */     if (paramInt1 < paramInt2) {
/* 153 */       paramArrayOfInt2[paramInt3] = paramArrayOfInt1[paramInt1];
/* 154 */       int i = paramArrayOfInt2[paramInt3];
/*     */       do {
/* 156 */         i += paramArrayOfInt1[paramInt1];
/* 157 */         paramArrayOfInt2[(++paramInt3)] = i;paramInt1++;
/* 155 */       } while (paramInt1 < paramInt2);
/*     */       
/*     */ 
/*     */ 
/* 159 */       return paramInt3 + 1;
/*     */     }
/*     */     
/* 162 */     return paramInt3;
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
/*     */   public static int partial_sum(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 190 */     if (paramInt1 < paramInt2) {
/* 191 */       paramArrayOfInt2[paramInt3] = paramArrayOfInt1[paramInt1];
/* 192 */       int i = paramArrayOfInt2[paramInt3];
/*     */       do {
/* 194 */         i = paramBinaryOperator.apply(i, paramArrayOfInt1[paramInt1]);
/* 195 */         paramArrayOfInt2[(++paramInt3)] = i;paramInt1++;
/* 193 */       } while (paramInt1 < paramInt2);
/*     */       
/*     */ 
/*     */ 
/* 197 */       return paramInt3 + 1;
/*     */     }
/*     */     
/* 200 */     return paramInt3;
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
/*     */   public static int adjacent_difference(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 223 */     if (paramInt1 < paramInt2) {
/* 224 */       paramArrayOfInt2[paramInt3] = paramArrayOfInt1[paramInt1];
/*     */       
/* 226 */       int i = paramArrayOfInt1[paramInt1];
/*     */       do {
/* 228 */         int j = paramArrayOfInt1[paramInt1];
/* 229 */         paramArrayOfInt2[(++paramInt3)] = (j - i);
/* 230 */         i = j;paramInt1++;
/* 227 */       } while (paramInt1 < paramInt2);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 233 */       return paramInt3 + 1;
/*     */     }
/*     */     
/* 236 */     return paramInt3;
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
/*     */   public static int adjacent_difference(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 261 */     if (paramInt1 < paramInt2) {
/* 262 */       paramArrayOfInt2[paramInt3] = paramArrayOfInt1[paramInt1];
/*     */       
/* 264 */       int i = paramArrayOfInt1[paramInt1];
/*     */       do {
/* 266 */         int j = paramArrayOfInt1[paramInt1];
/* 267 */         paramArrayOfInt2[(++paramInt3)] = paramBinaryOperator.apply(j, i);
/* 268 */         i = j;paramInt1++;
/* 265 */       } while (paramInt1 < paramInt2);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 271 */       return paramInt3 + 1;
/*     */     }
/*     */     
/* 274 */     return paramInt3;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/INT/Numeric.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */