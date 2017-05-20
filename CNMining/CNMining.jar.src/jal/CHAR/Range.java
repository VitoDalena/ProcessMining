/*    */ package jal.CHAR;
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
/*    */   public char[] array;
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
/*    */   public Range(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*    */   {
/* 42 */     this.array = paramArrayOfChar;
/* 43 */     this.first = paramInt1;
/* 44 */     this.last = paramInt2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Range(char[] paramArrayOfChar)
/*    */   {
/* 54 */     this(paramArrayOfChar, 0, paramArrayOfChar.length);
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/CHAR/Range.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */