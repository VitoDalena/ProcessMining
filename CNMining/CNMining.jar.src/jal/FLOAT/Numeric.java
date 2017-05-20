/*     */ package jal.FLOAT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   public static float accumulate(float[] paramArrayOfFloat, int paramInt1, int paramInt2, float paramFloat)
/*     */   {
/*  52 */     float f = paramFloat;
/*  53 */     while (paramInt1 < paramInt2)
/*  54 */       f += paramArrayOfFloat[(paramInt1++)];
/*  55 */     return f;
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
/*     */   public static float accumulate(float[] paramArrayOfFloat, int paramInt1, int paramInt2, float paramFloat, BinaryOperator paramBinaryOperator)
/*     */   {
/*  74 */     float f = paramFloat;
/*  75 */     while (paramInt1 < paramInt2)
/*  76 */       f = paramBinaryOperator.apply(f, paramArrayOfFloat[(paramInt1++)]);
/*  77 */     return f;
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
/*     */   public static float inner_product(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2, int paramInt3, float paramFloat)
/*     */   {
/*  97 */     float f = paramFloat;
/*  98 */     while (paramInt1 < paramInt2)
/*  99 */       f += paramArrayOfFloat1[(paramInt1++)] * paramArrayOfFloat2[(paramInt3++)];
/* 100 */     return f;
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
/*     */   public static float inner_product(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2, int paramInt3, float paramFloat, BinaryOperator paramBinaryOperator1, BinaryOperator paramBinaryOperator2)
/*     */   {
/* 126 */     float f = paramFloat;
/* 127 */     while (paramInt1 < paramInt2)
/* 128 */       f = paramBinaryOperator1.apply(f, paramBinaryOperator2.apply(paramArrayOfFloat1[(paramInt1++)], paramArrayOfFloat2[(paramInt3++)]));
/* 129 */     return f;
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
/*     */   public static int partial_sum(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 152 */     if (paramInt1 < paramInt2) {
/* 153 */       paramArrayOfFloat2[paramInt3] = paramArrayOfFloat1[paramInt1];
/* 154 */       float f = paramArrayOfFloat2[paramInt3];
/*     */       do {
/* 156 */         f += paramArrayOfFloat1[paramInt1];
/* 157 */         paramArrayOfFloat2[(++paramInt3)] = f;paramInt1++;
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
/*     */   public static int partial_sum(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 190 */     if (paramInt1 < paramInt2) {
/* 191 */       paramArrayOfFloat2[paramInt3] = paramArrayOfFloat1[paramInt1];
/* 192 */       float f = paramArrayOfFloat2[paramInt3];
/*     */       do {
/* 194 */         f = paramBinaryOperator.apply(f, paramArrayOfFloat1[paramInt1]);
/* 195 */         paramArrayOfFloat2[(++paramInt3)] = f;paramInt1++;
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
/*     */   public static int adjacent_difference(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 223 */     if (paramInt1 < paramInt2) {
/* 224 */       paramArrayOfFloat2[paramInt3] = paramArrayOfFloat1[paramInt1];
/*     */       
/* 226 */       float f1 = paramArrayOfFloat1[paramInt1];
/*     */       do {
/* 228 */         float f2 = paramArrayOfFloat1[paramInt1];
/* 229 */         paramArrayOfFloat2[(++paramInt3)] = (f2 - f1);
/* 230 */         f1 = f2;paramInt1++;
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
/*     */   public static int adjacent_difference(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 261 */     if (paramInt1 < paramInt2) {
/* 262 */       paramArrayOfFloat2[paramInt3] = paramArrayOfFloat1[paramInt1];
/*     */       
/* 264 */       float f1 = paramArrayOfFloat1[paramInt1];
/*     */       do {
/* 266 */         float f2 = paramArrayOfFloat1[paramInt1];
/* 267 */         paramArrayOfFloat2[(++paramInt3)] = paramBinaryOperator.apply(f2, f1);
/* 268 */         f1 = f2;paramInt1++;
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/FLOAT/Numeric.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */