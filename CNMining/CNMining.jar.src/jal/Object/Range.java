/*    */ package jal.Object;
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
/*    */   public Object[] array;
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
/*    */   public Range(Object[] paramArrayOfObject, int paramInt1, int paramInt2)
/*    */   {
/* 42 */     this.array = paramArrayOfObject;
/* 43 */     this.first = paramInt1;
/* 44 */     this.last = paramInt2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public Range(Object[] paramArrayOfObject)
/*    */   {
/* 54 */     this(paramArrayOfObject, 0, paramArrayOfObject.length);
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/jal/Object/Range.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */