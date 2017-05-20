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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Numeric
/*     */ {
/*     */   public static Object accumulate(Object[] paramArrayOfObject, int paramInt1, int paramInt2, Object paramObject, BinaryOperator paramBinaryOperator)
/*     */   {
/*  63 */     Object localObject = paramObject;
/*  64 */     while (paramInt1 < paramInt2)
/*  65 */       localObject = paramBinaryOperator.apply(localObject, paramArrayOfObject[(paramInt1++)]);
/*  66 */     return localObject;
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
/*     */   public static Object inner_product(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, Object paramObject, BinaryOperator paramBinaryOperator1, BinaryOperator paramBinaryOperator2)
/*     */   {
/*  93 */     Object localObject = paramObject;
/*  94 */     while (paramInt1 < paramInt2)
/*  95 */       localObject = paramBinaryOperator1.apply(localObject, paramBinaryOperator2.apply(paramArrayOfObject1[(paramInt1++)], paramArrayOfObject2[(paramInt3++)]));
/*  96 */     return localObject;
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
/*     */   public static int partial_sum(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 126 */     if (paramInt1 < paramInt2) {
/* 127 */       paramArrayOfObject2[paramInt3] = paramArrayOfObject1[paramInt1];
/* 128 */       Object localObject = paramArrayOfObject2[paramInt3];
/*     */       do {
/* 130 */         localObject = paramBinaryOperator.apply(localObject, paramArrayOfObject1[paramInt1]);
/* 131 */         paramArrayOfObject2[(++paramInt3)] = localObject;paramInt1++;
/* 129 */       } while (paramInt1 < paramInt2);
/*     */       
/*     */ 
/*     */ 
/* 133 */       return paramInt3 + 1;
/*     */     }
/*     */     
/* 136 */     return paramInt3;
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
/*     */   public static int adjacent_difference(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 163 */     if (paramInt1 < paramInt2) {
/* 164 */       paramArrayOfObject2[paramInt3] = paramArrayOfObject1[paramInt1];
/*     */       
/* 166 */       Object localObject1 = paramArrayOfObject1[paramInt1];
/*     */       do {
/* 168 */         Object localObject2 = paramArrayOfObject1[paramInt1];
/* 169 */         paramArrayOfObject2[(++paramInt3)] = paramBinaryOperator.apply(localObject2, localObject1);
/* 170 */         localObject1 = localObject2;paramInt1++;
/* 167 */       } while (paramInt1 < paramInt2);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 173 */       return paramInt3 + 1;
/*     */     }
/*     */     
/* 176 */     return paramInt3;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/Object/Numeric.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */