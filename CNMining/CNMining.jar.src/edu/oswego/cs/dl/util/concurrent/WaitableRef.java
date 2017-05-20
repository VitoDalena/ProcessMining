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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WaitableRef
/*    */   extends SynchronizedRef
/*    */ {
/*    */   public WaitableRef(Object paramObject)
/*    */   {
/* 29 */     super(paramObject);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public WaitableRef(Object paramObject1, Object paramObject2)
/*    */   {
/* 37 */     super(paramObject1, paramObject2);
/*    */   }
/*    */   
/*    */   public Object set(Object paramObject) {
/* 41 */     synchronized (this.lock_) {
/* 42 */       this.lock_.notifyAll();
/* 43 */       Object localObject2 = super.set(paramObject);return localObject2;
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean commit(Object paramObject1, Object paramObject2) {
/* 48 */     synchronized (this.lock_) {
/* 49 */       boolean bool1 = super.commit(paramObject1, paramObject2);
/* 50 */       if (bool1) this.lock_.notifyAll();
/* 51 */       boolean bool2 = bool1;return bool2;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void whenNull(Runnable paramRunnable)
/*    */     throws InterruptedException
/*    */   {
/* 61 */     synchronized (this.lock_) {
/* 62 */       while (this.value_ != null) this.lock_.wait();
/* 63 */       if (paramRunnable != null) { paramRunnable.run();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void whenNotNull(Runnable paramRunnable)
/*    */     throws InterruptedException
/*    */   {
/* 72 */     synchronized (this.lock_) {
/* 73 */       while (this.value_ == null) this.lock_.wait();
/* 74 */       if (paramRunnable != null) { paramRunnable.run();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void whenEqual(Object paramObject, Runnable paramRunnable)
/*    */     throws InterruptedException
/*    */   {
/* 84 */     synchronized (this.lock_) {
/* 85 */       while (this.value_ != paramObject) this.lock_.wait();
/* 86 */       if (paramRunnable != null) { paramRunnable.run();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void whenNotEqual(Object paramObject, Runnable paramRunnable)
/*    */     throws InterruptedException
/*    */   {
/* 95 */     synchronized (this.lock_) {
/* 96 */       while (this.value_ == paramObject) this.lock_.wait();
/* 97 */       if (paramRunnable != null) paramRunnable.run();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/WaitableRef.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */