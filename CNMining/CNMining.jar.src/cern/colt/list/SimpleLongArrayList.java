/*    */ package cern.colt.list;
/*    */ 
/*    */ import cern.colt.Arrays;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleLongArrayList
/*    */   extends AbstractLongList
/*    */ {
/*    */   protected long[] elements;
/*    */   protected int size;
/*    */   
/*    */   public SimpleLongArrayList()
/*    */   {
/* 32 */     this(10);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public SimpleLongArrayList(long[] paramArrayOfLong)
/*    */   {
/* 44 */     elements(paramArrayOfLong);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public SimpleLongArrayList(int paramInt)
/*    */   {
/* 53 */     if (paramInt < 0) {
/* 54 */       throw new IllegalArgumentException("Illegal Capacity: " + paramInt);
/*    */     }
/* 56 */     elements(new long[paramInt]);
/* 57 */     this.size = 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void ensureCapacity(int paramInt)
/*    */   {
/* 66 */     this.elements = Arrays.ensureCapacity(this.elements, paramInt);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected long getQuick(int paramInt)
/*    */   {
/* 77 */     return this.elements[paramInt];
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void setQuick(int paramInt, long paramLong)
/*    */   {
/* 89 */     this.elements[paramInt] = paramLong;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void trimToSize()
/*    */   {
/* 97 */     this.elements = Arrays.trimToCapacity(this.elements, size());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/SimpleLongArrayList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */