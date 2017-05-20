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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Numeric
/*     */ {
/*     */   public static String accumulate(String[] paramArrayOfString, int paramInt1, int paramInt2, String paramString, BinaryOperator paramBinaryOperator)
/*     */   {
/*  62 */     String str = paramString;
/*  63 */     while (paramInt1 < paramInt2)
/*  64 */       str = paramBinaryOperator.apply(str, paramArrayOfString[(paramInt1++)]);
/*  65 */     return str;
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
/*     */   public static String inner_product(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, String paramString, BinaryOperator paramBinaryOperator1, BinaryOperator paramBinaryOperator2)
/*     */   {
/*  92 */     String str = paramString;
/*  93 */     while (paramInt1 < paramInt2)
/*  94 */       str = paramBinaryOperator1.apply(str, paramBinaryOperator2.apply(paramArrayOfString1[(paramInt1++)], paramArrayOfString2[(paramInt3++)]));
/*  95 */     return str;
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
/*     */   public static int partial_sum(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 125 */     if (paramInt1 < paramInt2) {
/* 126 */       paramArrayOfString2[paramInt3] = paramArrayOfString1[paramInt1];
/* 127 */       String str = paramArrayOfString2[paramInt3];
/*     */       do {
/* 129 */         str = paramBinaryOperator.apply(str, paramArrayOfString1[paramInt1]);
/* 130 */         paramArrayOfString2[(++paramInt3)] = str;paramInt1++;
/* 128 */       } while (paramInt1 < paramInt2);
/*     */       
/*     */ 
/*     */ 
/* 132 */       return paramInt3 + 1;
/*     */     }
/*     */     
/* 135 */     return paramInt3;
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
/*     */   public static int adjacent_difference(String[] paramArrayOfString1, String[] paramArrayOfString2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 162 */     if (paramInt1 < paramInt2) {
/* 163 */       paramArrayOfString2[paramInt3] = paramArrayOfString1[paramInt1];
/*     */       
/* 165 */       Object localObject = paramArrayOfString1[paramInt1];
/*     */       do {
/* 167 */         String str = paramArrayOfString1[paramInt1];
/* 168 */         paramArrayOfString2[(++paramInt3)] = paramBinaryOperator.apply(str, (String)localObject);
/* 169 */         localObject = str;paramInt1++;
/* 166 */       } while (paramInt1 < paramInt2);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 172 */       return paramInt3 + 1;
/*     */     }
/*     */     
/* 175 */     return paramInt3;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/String/Numeric.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */