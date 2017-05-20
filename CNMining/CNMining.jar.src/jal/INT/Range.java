/*    */ package jal.INT;
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
/*    */   public int[] array;
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
/*    */   public Range(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*    */   {
/* 42 */     this.array = paramArrayOfInt;
/* 43 */     this.first = paramInt1;
/* 44 */     this.last = paramInt2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Range(int[] paramArrayOfInt)
/*    */   {
/* 54 */     this(paramArrayOfInt, 0, paramArrayOfInt.length);
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/INT/Range.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */