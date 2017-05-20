/*    */ package edu.oswego.cs.dl.util.concurrent;
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
/*    */ public class DefaultChannelCapacity
/*    */ {
/*    */   public static final int INITIAL_DEFAULT_CAPACITY = 1024;
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
/* 30 */   private static final SynchronizedInt defaultCapacity_ = new SynchronizedInt(1024);
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void set(int paramInt)
/*    */   {
/* 40 */     if (paramInt <= 0) throw new IllegalArgumentException();
/* 41 */     defaultCapacity_.set(paramInt);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static int get()
/*    */   {
/* 52 */     return defaultCapacity_.get();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/DefaultChannelCapacity.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */