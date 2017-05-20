/*    */ package jal.LONG;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Range
/*    */ {
/*    */   public long[] array;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int first;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int last;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Range(long[] paramArrayOfLong, int paramInt1, int paramInt2)
/*    */   {
/* 42 */     this.array = paramArrayOfLong;
/* 43 */     this.first = paramInt1;
/* 44 */     this.last = paramInt2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Range(long[] paramArrayOfLong)
/*    */   {
/* 54 */     this(paramArrayOfLong, 0, paramArrayOfLong.length);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 64 */     return "[" + this.first + ", " + this.last + ")";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/LONG/Range.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */