/*    */ package cern.jet.stat.quantile;
/*    */ 
/*    */ import cern.colt.PersistentObject;
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
/*    */ abstract class Buffer
/*    */   extends PersistentObject
/*    */ {
/*    */   protected int weight;
/*    */   protected int level;
/*    */   protected int k;
/*    */   protected boolean isAllocated;
/*    */   
/*    */   public Buffer(int paramInt)
/*    */   {
/* 24 */     this.k = paramInt;
/* 25 */     this.weight = 1;
/* 26 */     this.level = 0;
/* 27 */     this.isAllocated = false;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public abstract void clear();
/*    */   
/*    */ 
/*    */   public boolean isAllocated()
/*    */   {
/* 37 */     return this.isAllocated;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public abstract boolean isEmpty();
/*    */   
/*    */ 
/*    */ 
/*    */   public abstract boolean isFull();
/*    */   
/*    */ 
/*    */   public boolean isPartial()
/*    */   {
/* 51 */     return (!isEmpty()) && (!isFull());
/*    */   }
/*    */   
/*    */ 
/*    */   public int level()
/*    */   {
/* 57 */     return this.level;
/*    */   }
/*    */   
/*    */ 
/*    */   public void level(int paramInt)
/*    */   {
/* 63 */     this.level = paramInt;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public abstract int size();
/*    */   
/*    */ 
/*    */ 
/*    */   public abstract void sort();
/*    */   
/*    */ 
/*    */   public int weight()
/*    */   {
/* 77 */     return this.weight;
/*    */   }
/*    */   
/*    */ 
/*    */   public void weight(int paramInt)
/*    */   {
/* 83 */     this.weight = paramInt;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/jet/stat/quantile/Buffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */