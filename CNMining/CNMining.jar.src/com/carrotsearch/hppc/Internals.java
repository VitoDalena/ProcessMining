/*    */ package com.carrotsearch.hppc;
/*    */ 
/*    */ import com.carrotsearch.hppc.hash.MurmurHash3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class Internals
/*    */ {
/* 10 */   static int rehash(Object o, int p) { return o == null ? 0 : MurmurHash3.hash(o.hashCode() ^ p); }
/* 11 */   static int rehash(byte v, int p) { return MurmurHash3.hash(v ^ p); }
/* 12 */   static int rehash(short v, int p) { return MurmurHash3.hash(v ^ p); }
/* 13 */   static int rehash(int v, int p) { return MurmurHash3.hash(v ^ p); }
/* 14 */   static int rehash(long v, int p) { return (int)MurmurHash3.hash(v ^ p); }
/* 15 */   static int rehash(char v, int p) { return MurmurHash3.hash(v ^ p); }
/* 16 */   static int rehash(float v, int p) { return MurmurHash3.hash(Float.floatToIntBits(v) ^ p); }
/* 17 */   static int rehash(double v, int p) { return (int)MurmurHash3.hash(Double.doubleToLongBits(v) ^ p); }
/*    */   
/* 19 */   static int rehash(Object o) { return o == null ? 0 : MurmurHash3.hash(o.hashCode()); }
/* 20 */   static int rehash(byte v) { return MurmurHash3.hash(v); }
/* 21 */   static int rehash(short v) { return MurmurHash3.hash(v); }
/* 22 */   static int rehash(int v) { return MurmurHash3.hash(v); }
/* 23 */   static int rehash(long v) { return (int)MurmurHash3.hash(v); }
/* 24 */   static int rehash(char v) { return MurmurHash3.hash(v); }
/* 25 */   static int rehash(float v) { return MurmurHash3.hash(Float.floatToIntBits(v)); }
/* 26 */   static int rehash(double v) { return (int)MurmurHash3.hash(Double.doubleToLongBits(v)); }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   static <T> T newArray(int arraySize)
/*    */   {
/* 37 */     return new Object[arraySize];
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/Internals.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */