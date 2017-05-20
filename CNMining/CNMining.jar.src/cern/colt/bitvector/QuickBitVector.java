/*     */ package cern.colt.bitvector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QuickBitVector
/*     */ {
/*     */   protected static final int ADDRESS_BITS_PER_UNIT = 6;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static final int BITS_PER_UNIT = 64;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static final int BIT_INDEX_MASK = 63;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  36 */   private static final long[] pows = ;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final long bitMaskWithBitsSetFromTo(int paramInt1, int paramInt2)
/*     */   {
/*  53 */     return pows[(paramInt2 - paramInt1 + 1)] << paramInt1;
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
/*     */   public static void clear(long[] paramArrayOfLong, int paramInt)
/*     */   {
/*  67 */     paramArrayOfLong[(paramInt >> 6)] &= (1L << (paramInt & 0x3F) ^ 0xFFFFFFFFFFFFFFFF);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean get(long[] paramArrayOfLong, int paramInt)
/*     */   {
/*  79 */     return (paramArrayOfLong[(paramInt >> 6)] & 1L << (paramInt & 0x3F)) != 0L;
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
/*     */   public static long getLongFromTo(long[] paramArrayOfLong, int paramInt1, int paramInt2)
/*     */   {
/*  93 */     if (paramInt1 > paramInt2) { return 0L;
/*     */     }
/*  95 */     int i = paramInt1 >> 6;
/*  96 */     int j = paramInt2 >> 6;
/*  97 */     int k = paramInt1 & 0x3F;
/*  98 */     int m = paramInt2 & 0x3F;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 107 */     if (i == j) {
/* 108 */       l1 = bitMaskWithBitsSetFromTo(k, m);
/* 109 */       return (paramArrayOfLong[i] & l1) >>> k;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 115 */     long l1 = bitMaskWithBitsSetFromTo(k, 63);
/* 116 */     long l2 = (paramArrayOfLong[i] & l1) >>> k;
/*     */     
/*     */ 
/* 119 */     l1 = bitMaskWithBitsSetFromTo(0, m);
/* 120 */     long l3 = (paramArrayOfLong[j] & l1) << 64 - k;
/*     */     
/*     */ 
/* 123 */     return l2 | l3;
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
/*     */   public static int leastSignificantBit(int paramInt)
/*     */   {
/* 137 */     int i = -1;
/* 138 */     do { i++; } while ((i < 32) && ((1 << i & paramInt) == 0));
/* 139 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static long[] makeBitVector(int paramInt1, int paramInt2)
/*     */   {
/* 149 */     int i = paramInt1 * paramInt2;
/* 150 */     int j = i - 1 >> 6;
/* 151 */     long[] arrayOfLong = new long[j + 1];
/* 152 */     return arrayOfLong;
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
/*     */   public static int mostSignificantBit(int paramInt)
/*     */   {
/* 166 */     int i = 32;
/* 167 */     do { i--; } while ((i >= 0) && ((1 << i & paramInt) == 0));
/* 168 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   protected static int offset(int paramInt)
/*     */   {
/* 174 */     return paramInt & 0x3F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static long[] precomputePows()
/*     */   {
/* 184 */     long[] arrayOfLong = new long[65];
/* 185 */     long l = -1L;
/* 186 */     int i = 65;
/* 187 */     do { arrayOfLong[i] = (l >>> 64 - i);i--;
/* 186 */     } while (i >= 1);
/*     */     
/*     */ 
/*     */ 
/* 190 */     arrayOfLong[0] = 0L;
/*     */     
/* 192 */     return arrayOfLong;
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
/*     */   public static void put(long[] paramArrayOfLong, int paramInt, boolean paramBoolean)
/*     */   {
/* 222 */     if (paramBoolean) {
/* 223 */       set(paramArrayOfLong, paramInt);
/*     */     } else {
/* 225 */       clear(paramArrayOfLong, paramInt);
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
/*     */   public static void putLongFromTo(long[] paramArrayOfLong, long paramLong, int paramInt1, int paramInt2)
/*     */   {
/* 240 */     if (paramInt1 > paramInt2) { return;
/*     */     }
/* 242 */     int i = paramInt1 >> 6;
/* 243 */     int j = paramInt2 >> 6;
/* 244 */     int k = paramInt1 & 0x3F;
/* 245 */     int m = paramInt2 & 0x3F;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 256 */     long l1 = bitMaskWithBitsSetFromTo(paramInt2 - paramInt1 + 1, 63);
/* 257 */     long l2 = paramLong & (l1 ^ 0xFFFFFFFFFFFFFFFF);
/*     */     
/*     */ 
/*     */ 
/* 261 */     if (i == j) {
/* 262 */       l3 = l2 << k;
/* 263 */       l1 = bitMaskWithBitsSetFromTo(k, m);
/* 264 */       paramArrayOfLong[i] = (paramArrayOfLong[i] & (l1 ^ 0xFFFFFFFFFFFFFFFF) | l3);
/* 265 */       return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 271 */     long l3 = l2 << k;
/* 272 */     l1 = bitMaskWithBitsSetFromTo(k, 63);
/* 273 */     paramArrayOfLong[i] = (paramArrayOfLong[i] & (l1 ^ 0xFFFFFFFFFFFFFFFF) | l3);
/*     */     
/*     */ 
/* 276 */     l3 = l2 >>> 64 - k;
/* 277 */     l1 = bitMaskWithBitsSetFromTo(0, m);
/* 278 */     paramArrayOfLong[j] = (paramArrayOfLong[j] & (l1 ^ 0xFFFFFFFFFFFFFFFF) | l3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void set(long[] paramArrayOfLong, int paramInt)
/*     */   {
/* 287 */     paramArrayOfLong[(paramInt >> 6)] |= 1L << (paramInt & 0x3F);
/*     */   }
/*     */   
/*     */ 
/*     */   protected static int unit(int paramInt)
/*     */   {
/* 293 */     return paramInt >> 6;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/bitvector/QuickBitVector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */