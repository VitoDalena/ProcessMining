/*     */ package jal.LONG;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   public static long accumulate(long[] paramArrayOfLong, int paramInt1, int paramInt2, long paramLong)
/*     */   {
/*  52 */     long l = paramLong;
/*  53 */     while (paramInt1 < paramInt2)
/*  54 */       l += paramArrayOfLong[(paramInt1++)];
/*  55 */     return l;
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
/*     */   public static long accumulate(long[] paramArrayOfLong, int paramInt1, int paramInt2, long paramLong, BinaryOperator paramBinaryOperator)
/*     */   {
/*  74 */     long l = paramLong;
/*  75 */     while (paramInt1 < paramInt2)
/*  76 */       l = paramBinaryOperator.apply(l, paramArrayOfLong[(paramInt1++)]);
/*  77 */     return l;
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
/*     */   public static long inner_product(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3, long paramLong)
/*     */   {
/*  97 */     long l = paramLong;
/*  98 */     while (paramInt1 < paramInt2)
/*  99 */       l += paramArrayOfLong1[(paramInt1++)] * paramArrayOfLong2[(paramInt3++)];
/* 100 */     return l;
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
/*     */   public static long inner_product(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3, long paramLong, BinaryOperator paramBinaryOperator1, BinaryOperator paramBinaryOperator2)
/*     */   {
/* 126 */     long l = paramLong;
/* 127 */     while (paramInt1 < paramInt2)
/* 128 */       l = paramBinaryOperator1.apply(l, paramBinaryOperator2.apply(paramArrayOfLong1[(paramInt1++)], paramArrayOfLong2[(paramInt3++)]));
/* 129 */     return l;
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
/*     */   public static int partial_sum(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 152 */     if (paramInt1 < paramInt2) {
/* 153 */       paramArrayOfLong2[paramInt3] = paramArrayOfLong1[paramInt1];
/* 154 */       long l = paramArrayOfLong2[paramInt3];
/*     */       do {
/* 156 */         l += paramArrayOfLong1[paramInt1];
/* 157 */         paramArrayOfLong2[(++paramInt3)] = l;paramInt1++;
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
/*     */   public static int partial_sum(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 190 */     if (paramInt1 < paramInt2) {
/* 191 */       paramArrayOfLong2[paramInt3] = paramArrayOfLong1[paramInt1];
/* 192 */       long l = paramArrayOfLong2[paramInt3];
/*     */       do {
/* 194 */         l = paramBinaryOperator.apply(l, paramArrayOfLong1[paramInt1]);
/* 195 */         paramArrayOfLong2[(++paramInt3)] = l;paramInt1++;
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
/*     */   public static int adjacent_difference(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 223 */     if (paramInt1 < paramInt2) {
/* 224 */       paramArrayOfLong2[paramInt3] = paramArrayOfLong1[paramInt1];
/*     */       
/* 226 */       long l1 = paramArrayOfLong1[paramInt1];
/*     */       do {
/* 228 */         long l2 = paramArrayOfLong1[paramInt1];
/* 229 */         paramArrayOfLong2[(++paramInt3)] = (l2 - l1);
/* 230 */         l1 = l2;paramInt1++;
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
/*     */   public static int adjacent_difference(long[] paramArrayOfLong1, long[] paramArrayOfLong2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 261 */     if (paramInt1 < paramInt2) {
/* 262 */       paramArrayOfLong2[paramInt3] = paramArrayOfLong1[paramInt1];
/*     */       
/* 264 */       long l1 = paramArrayOfLong1[paramInt1];
/*     */       do {
/* 266 */         long l2 = paramArrayOfLong1[paramInt1];
/* 267 */         paramArrayOfLong2[(++paramInt3)] = paramBinaryOperator.apply(l2, l1);
/* 268 */         l1 = l2;paramInt1++;
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/LONG/Numeric.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */