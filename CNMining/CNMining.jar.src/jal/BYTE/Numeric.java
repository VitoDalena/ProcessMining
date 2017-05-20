/*     */ package jal.BYTE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   public static byte accumulate(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte)
/*     */   {
/*  52 */     byte b = paramByte;
/*  53 */     while (paramInt1 < paramInt2)
/*  54 */       b = (byte)(b + paramArrayOfByte[(paramInt1++)]);
/*  55 */     return b;
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
/*     */   public static byte accumulate(byte[] paramArrayOfByte, int paramInt1, int paramInt2, byte paramByte, BinaryOperator paramBinaryOperator)
/*     */   {
/*  74 */     byte b = paramByte;
/*  75 */     while (paramInt1 < paramInt2)
/*  76 */       b = paramBinaryOperator.apply(b, paramArrayOfByte[(paramInt1++)]);
/*  77 */     return b;
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
/*     */   public static byte inner_product(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, byte paramByte)
/*     */   {
/*  97 */     int i = paramByte;
/*  98 */     while (paramInt1 < paramInt2)
/*  99 */       i = (byte)(i + paramArrayOfByte1[(paramInt1++)] * paramArrayOfByte2[(paramInt3++)]);
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
/*     */   public static byte inner_product(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, byte paramByte, BinaryOperator paramBinaryOperator1, BinaryOperator paramBinaryOperator2)
/*     */   {
/* 126 */     byte b = paramByte;
/* 127 */     while (paramInt1 < paramInt2)
/* 128 */       b = paramBinaryOperator1.apply(b, paramBinaryOperator2.apply(paramArrayOfByte1[(paramInt1++)], paramArrayOfByte2[(paramInt3++)]));
/* 129 */     return b;
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
/*     */   public static int partial_sum(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 152 */     if (paramInt1 < paramInt2) {
/* 153 */       paramArrayOfByte2[paramInt3] = paramArrayOfByte1[paramInt1];
/* 154 */       int i = paramArrayOfByte2[paramInt3];
/*     */       do {
/* 156 */         i = (byte)(i + paramArrayOfByte1[paramInt1]);
/* 157 */         paramArrayOfByte2[(++paramInt3)] = i;paramInt1++;
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
/*     */   public static int partial_sum(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 190 */     if (paramInt1 < paramInt2) {
/* 191 */       paramArrayOfByte2[paramInt3] = paramArrayOfByte1[paramInt1];
/* 192 */       byte b = paramArrayOfByte2[paramInt3];
/*     */       do {
/* 194 */         b = paramBinaryOperator.apply(b, paramArrayOfByte1[paramInt1]);
/* 195 */         paramArrayOfByte2[(++paramInt3)] = b;paramInt1++;
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
/*     */   public static int adjacent_difference(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 223 */     if (paramInt1 < paramInt2) {
/* 224 */       paramArrayOfByte2[paramInt3] = paramArrayOfByte1[paramInt1];
/*     */       
/* 226 */       int i = paramArrayOfByte1[paramInt1];
/*     */       do {
/* 228 */         int j = paramArrayOfByte1[paramInt1];
/* 229 */         paramArrayOfByte2[(++paramInt3)] = ((byte)(j - i));
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
/*     */   public static int adjacent_difference(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, BinaryOperator paramBinaryOperator)
/*     */   {
/* 261 */     if (paramInt1 < paramInt2) {
/* 262 */       paramArrayOfByte2[paramInt3] = paramArrayOfByte1[paramInt1];
/*     */       
/* 264 */       byte b1 = paramArrayOfByte1[paramInt1];
/*     */       do {
/* 266 */         byte b2 = paramArrayOfByte1[paramInt1];
/* 267 */         paramArrayOfByte2[(++paramInt3)] = paramBinaryOperator.apply(b2, b1);
/* 268 */         b1 = b2;paramInt1++;
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/BYTE/Numeric.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */