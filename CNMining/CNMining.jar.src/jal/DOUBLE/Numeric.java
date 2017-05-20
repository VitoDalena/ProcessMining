/*     */ package jal.DOUBLE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   public static double accumulate(double[] paramArrayOfDouble, int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/*  52 */     double d = paramDouble;
/*  53 */     while (paramInt1 < paramInt2)
/*  54 */       d += paramArrayOfDouble[(paramInt1++)];
/*  55 */     return d;
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
/*     */   public static double accumulate(double[] paramArrayOfDouble, int paramInt1, int paramInt2, double paramDouble, BinaryOperator paramBinaryOperator)
/*     */   {
/*  74 */     double d = paramDouble;
/*  75 */     while (paramInt1 < paramInt2)
/*  76 */       d = paramBinaryOperator.apply(d, paramArrayOfDouble[(paramInt1++)]);
/*  77 */     return d;
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
/*     */   public static double inner_product(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2, int paramInt3, double paramDouble)
/*     */   {
/*  97 */     double d = paramDouble;
/*  98 */     while (paramInt1 < paramInt2)
/*  99 */       d += paramArrayOfDouble1[(paramInt1++)] * paramArrayOfDouble2[(paramInt3++)];
/* 100 */     return d;
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
/*     */   public static double inner_product(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2, int paramInt3, double paramDouble, BinaryOperator paramBinaryOperator1, BinaryOperator paramBinaryOperator2)
/*     */   {
/* 126 */     double d = paramDouble;
/* 127 */     while (paramInt1 < paramInt2)
/* 128 */       d = paramBinaryOperator1.apply(d, paramBinaryOperator2.apply(paramArrayOfDouble1[(paramInt1++)], paramArrayOfDouble2[(paramInt3++)]));
/* 129 */     return d;
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
/*     */   public static int partial_sum(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 152 */     if (paramInt1 < paramInt2) {
/* 153 */       paramArrayOfDouble2[paramInt3] = paramArrayOfDouble1[paramInt1];
/* 154 */       double d = paramArrayOfDouble2[paramInt3];
/*     */       do {
/* 156 */         d += paramArrayOfDouble1[paramInt1];
/* 157 */         paramArrayOfDouble2[(++paramInt3)] = d;paramInt1++;
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
/*     */   public static int partial_sum(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 190 */     if (paramInt1 < paramInt2) {
/* 191 */       paramArrayOfDouble2[paramInt3] = paramArrayOfDouble1[paramInt1];
/* 192 */       double d = paramArrayOfDouble2[paramInt3];
/*     */       do {
/* 194 */         d = paramBinaryOperator.apply(d, paramArrayOfDouble1[paramInt1]);
/* 195 */         paramArrayOfDouble2[(++paramInt3)] = d;paramInt1++;
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
/*     */   public static int adjacent_difference(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 223 */     if (paramInt1 < paramInt2) {
/* 224 */       paramArrayOfDouble2[paramInt3] = paramArrayOfDouble1[paramInt1];
/*     */       
/* 226 */       double d1 = paramArrayOfDouble1[paramInt1];
/*     */       do {
/* 228 */         double d2 = paramArrayOfDouble1[paramInt1];
/* 229 */         paramArrayOfDouble2[(++paramInt3)] = (d2 - d1);
/* 230 */         d1 = d2;paramInt1++;
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
/*     */   public static int adjacent_difference(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 261 */     if (paramInt1 < paramInt2) {
/* 262 */       paramArrayOfDouble2[paramInt3] = paramArrayOfDouble1[paramInt1];
/*     */       
/* 264 */       double d1 = paramArrayOfDouble1[paramInt1];
/*     */       do {
/* 266 */         double d2 = paramArrayOfDouble1[paramInt1];
/* 267 */         paramArrayOfDouble2[(++paramInt3)] = paramBinaryOperator.apply(d2, d1);
/* 268 */         d1 = d2;paramInt1++;
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/DOUBLE/Numeric.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */