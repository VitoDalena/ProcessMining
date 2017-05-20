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
/*     */ 
/*     */ public final class Numeric
/*     */ {
/*     */   public static short accumulate(short[] paramArrayOfShort, int paramInt1, int paramInt2, short paramShort)
/*     */   {
/*  52 */     short s = paramShort;
/*  53 */     while (paramInt1 < paramInt2)
/*  54 */       s = (short)(s + paramArrayOfShort[(paramInt1++)]);
/*  55 */     return s;
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
/*     */   public static short accumulate(short[] paramArrayOfShort, int paramInt1, int paramInt2, short paramShort, BinaryOperator paramBinaryOperator)
/*     */   {
/*  74 */     short s = paramShort;
/*  75 */     while (paramInt1 < paramInt2)
/*  76 */       s = paramBinaryOperator.apply(s, paramArrayOfShort[(paramInt1++)]);
/*  77 */     return s;
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
/*     */   public static short inner_product(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, short paramShort)
/*     */   {
/*  97 */     int i = paramShort;
/*  98 */     while (paramInt1 < paramInt2)
/*  99 */       i = (short)(i + paramArrayOfShort1[(paramInt1++)] * paramArrayOfShort2[(paramInt3++)]);
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
/*     */   public static short inner_product(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, short paramShort, BinaryOperator paramBinaryOperator1, BinaryOperator paramBinaryOperator2)
/*     */   {
/* 126 */     short s = paramShort;
/* 127 */     while (paramInt1 < paramInt2)
/* 128 */       s = paramBinaryOperator1.apply(s, paramBinaryOperator2.apply(paramArrayOfShort1[(paramInt1++)], paramArrayOfShort2[(paramInt3++)]));
/* 129 */     return s;
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
/*     */   public static int partial_sum(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 152 */     if (paramInt1 < paramInt2) {
/* 153 */       paramArrayOfShort2[paramInt3] = paramArrayOfShort1[paramInt1];
/* 154 */       int i = paramArrayOfShort2[paramInt3];
/*     */       do {
/* 156 */         i = (short)(i + paramArrayOfShort1[paramInt1]);
/* 157 */         paramArrayOfShort2[(++paramInt3)] = i;paramInt1++;
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
/*     */   public static int partial_sum(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 190 */     if (paramInt1 < paramInt2) {
/* 191 */       paramArrayOfShort2[paramInt3] = paramArrayOfShort1[paramInt1];
/* 192 */       short s = paramArrayOfShort2[paramInt3];
/*     */       do {
/* 194 */         s = paramBinaryOperator.apply(s, paramArrayOfShort1[paramInt1]);
/* 195 */         paramArrayOfShort2[(++paramInt3)] = s;paramInt1++;
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
/*     */   public static int adjacent_difference(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 223 */     if (paramInt1 < paramInt2) {
/* 224 */       paramArrayOfShort2[paramInt3] = paramArrayOfShort1[paramInt1];
/*     */       
/* 226 */       int i = paramArrayOfShort1[paramInt1];
/*     */       do {
/* 228 */         int j = paramArrayOfShort1[paramInt1];
/* 229 */         paramArrayOfShort2[(++paramInt3)] = ((short)(j - i));
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
/*     */   public static int adjacent_difference(short[] paramArrayOfShort1, short[] paramArrayOfShort2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 261 */     if (paramInt1 < paramInt2) {
/* 262 */       paramArrayOfShort2[paramInt3] = paramArrayOfShort1[paramInt1];
/*     */       
/* 264 */       short s1 = paramArrayOfShort1[paramInt1];
/*     */       do {
/* 266 */         short s2 = paramArrayOfShort1[paramInt1];
/* 267 */         paramArrayOfShort2[(++paramInt3)] = paramBinaryOperator.apply(s2, s1);
/* 268 */         s1 = s2;paramInt1++;
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/SHORT/Numeric.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */