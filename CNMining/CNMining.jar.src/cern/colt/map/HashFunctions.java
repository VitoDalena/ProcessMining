/*     */ package cern.colt.map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HashFunctions
/*     */ {
/*     */   public static int hash(char paramChar)
/*     */   {
/*  28 */     return paramChar;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int hash(double paramDouble)
/*     */   {
/*  36 */     long l = Double.doubleToLongBits(paramDouble);
/*  37 */     return (int)(l ^ l >>> 32);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int hash(float paramFloat)
/*     */   {
/*  48 */     return Float.floatToIntBits(paramFloat * 6.6360896E8F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int hash(int paramInt)
/*     */   {
/*  57 */     return paramInt;
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
/*     */   public static int hash(long paramLong)
/*     */   {
/*  76 */     return (int)(paramLong ^ paramLong >> 32);
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
/*     */   public static int hash(Object paramObject)
/*     */   {
/*  92 */     return paramObject == null ? 0 : paramObject.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int hash(short paramShort)
/*     */   {
/* 100 */     return paramShort;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int hash(boolean paramBoolean)
/*     */   {
/* 108 */     return paramBoolean ? 1231 : 1237;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/map/HashFunctions.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */