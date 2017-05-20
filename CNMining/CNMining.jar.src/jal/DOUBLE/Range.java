/*    */ package jal.DOUBLE;
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
/*    */   public double[] array;
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
/*    */   public Range(double[] paramArrayOfDouble, int paramInt1, int paramInt2)
/*    */   {
/* 42 */     this.array = paramArrayOfDouble;
/* 43 */     this.first = paramInt1;
/* 44 */     this.last = paramInt2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Range(double[] paramArrayOfDouble)
/*    */   {
/* 54 */     this(paramArrayOfDouble, 0, paramArrayOfDouble.length);
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/DOUBLE/Range.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */