/*    */ package edu.oswego.cs.dl.util.concurrent.misc;
/*    */ 
/*    */ import edu.oswego.cs.dl.util.concurrent.SemaphoreControlledChannel;
/*    */ 
/*    */ public class FIFOSlot implements edu.oswego.cs.dl.util.concurrent.BoundedChannel
/*    */ {
/*    */   private final edu.oswego.cs.dl.util.concurrent.Slot slot_;
/*    */   
/*    */   public FIFOSlot()
/*    */   {
/*    */     try {
/* 12 */       this.slot_ = new edu.oswego.cs.dl.util.concurrent.Slot(edu.oswego.cs.dl.util.concurrent.FIFOSemaphore.class);
/*    */     }
/*    */     catch (Exception localException) {
/* 15 */       localException.printStackTrace();
/* 16 */       throw new Error("Cannot make Slot?");
/*    */     }
/*    */   }
/*    */   
/*    */   public void put(Object paramObject) throws InterruptedException {
/* 21 */     this.slot_.put(paramObject);
/*    */   }
/*    */   
/*    */   public boolean offer(Object paramObject, long paramLong) throws InterruptedException {
/* 25 */     return this.slot_.offer(paramObject, paramLong);
/*    */   }
/*    */   
/*    */   public Object take() throws InterruptedException {
/* 29 */     return this.slot_.take();
/*    */   }
/*    */   
/*    */   public Object poll(long paramLong) throws InterruptedException {
/* 33 */     return this.slot_.poll(paramLong);
/*    */   }
/*    */   
/* 36 */   public int capacity() { return 1; }
/*    */   
/*    */   public Object peek() {
/* 39 */     return this.slot_.peek();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/misc/FIFOSlot.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */