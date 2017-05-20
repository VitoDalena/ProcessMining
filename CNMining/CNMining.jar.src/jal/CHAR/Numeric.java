/*     */ package jal.CHAR;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   public static char accumulate(char[] paramArrayOfChar, int paramInt1, int paramInt2, char paramChar)
/*     */   {
/*  52 */     char c = paramChar;
/*  53 */     while (paramInt1 < paramInt2)
/*  54 */       c = (char)(c + paramArrayOfChar[(paramInt1++)]);
/*  55 */     return c;
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
/*     */   public static char accumulate(char[] paramArrayOfChar, int paramInt1, int paramInt2, char paramChar, BinaryOperator paramBinaryOperator)
/*     */   {
/*  74 */     char c = paramChar;
/*  75 */     while (paramInt1 < paramInt2)
/*  76 */       c = paramBinaryOperator.apply(c, paramArrayOfChar[(paramInt1++)]);
/*  77 */     return c;
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
/*     */   public static char inner_product(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3, char paramChar)
/*     */   {
/*  97 */     int i = paramChar;
/*  98 */     while (paramInt1 < paramInt2)
/*  99 */       i = (char)(i + paramArrayOfChar1[(paramInt1++)] * paramArrayOfChar2[(paramInt3++)]);
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
/*     */   public static char inner_product(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3, char paramChar, BinaryOperator paramBinaryOperator1, BinaryOperator paramBinaryOperator2)
/*     */   {
/* 126 */     char c = paramChar;
/* 127 */     while (paramInt1 < paramInt2)
/* 128 */       c = paramBinaryOperator1.apply(c, paramBinaryOperator2.apply(paramArrayOfChar1[(paramInt1++)], paramArrayOfChar2[(paramInt3++)]));
/* 129 */     return c;
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
/*     */   public static int partial_sum(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 152 */     if (paramInt1 < paramInt2) {
/* 153 */       paramArrayOfChar2[paramInt3] = paramArrayOfChar1[paramInt1];
/* 154 */       int i = paramArrayOfChar2[paramInt3];
/*     */       do {
/* 156 */         i = (char)(i + paramArrayOfChar1[paramInt1]);
/* 157 */         paramArrayOfChar2[(++paramInt3)] = i;paramInt1++;
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
/*     */   public static int partial_sum(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 190 */     if (paramInt1 < paramInt2) {
/* 191 */       paramArrayOfChar2[paramInt3] = paramArrayOfChar1[paramInt1];
/* 192 */       char c = paramArrayOfChar2[paramInt3];
/*     */       do {
/* 194 */         c = paramBinaryOperator.apply(c, paramArrayOfChar1[paramInt1]);
/* 195 */         paramArrayOfChar2[(++paramInt3)] = c;paramInt1++;
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
/*     */   public static int adjacent_difference(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 223 */     if (paramInt1 < paramInt2) {
/* 224 */       paramArrayOfChar2[paramInt3] = paramArrayOfChar1[paramInt1];
/*     */       
/* 226 */       int i = paramArrayOfChar1[paramInt1];
/*     */       do {
/* 228 */         int j = paramArrayOfChar1[paramInt1];
/* 229 */         paramArrayOfChar2[(++paramInt3)] = ((char)(j - i));
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
/*     */   public static int adjacent_difference(char[] paramArrayOfChar1, char[] paramArrayOfChar2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 261 */     if (paramInt1 < paramInt2) {
/* 262 */       paramArrayOfChar2[paramInt3] = paramArrayOfChar1[paramInt1];
/*     */       
/* 264 */       char c1 = paramArrayOfChar1[paramInt1];
/*     */       do {
/* 266 */         char c2 = paramArrayOfChar1[paramInt1];
/* 267 */         paramArrayOfChar2[(++paramInt3)] = paramBinaryOperator.apply(c2, c1);
/* 268 */         c1 = c2;paramInt1++;
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/CHAR/Numeric.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */