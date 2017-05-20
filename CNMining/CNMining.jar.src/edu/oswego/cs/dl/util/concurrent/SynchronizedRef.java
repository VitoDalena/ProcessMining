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
/*    */ public class SynchronizedRef
/*    */   extends SynchronizedVariable
/*    */ {
/*    */   protected Object value_;
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
/*    */   public SynchronizedRef(Object paramObject)
/*    */   {
/* 34 */     this.value_ = paramObject;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public SynchronizedRef(Object paramObject1, Object paramObject2)
/*    */   {
/* 42 */     super(paramObject2);
/* 43 */     this.value_ = paramObject1;
/*    */   }
/*    */   
/*    */ 
/*    */   public final Object get()
/*    */   {
/* 49 */     synchronized (this.lock_) { Object localObject2 = this.value_;return localObject2;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Object set(Object paramObject)
/*    */   {
/* 57 */     synchronized (this.lock_) {
/* 58 */       Object localObject2 = this.value_;
/* 59 */       this.value_ = paramObject;
/* 60 */       Object localObject3 = localObject2;return localObject3;
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean commit(Object paramObject1, Object paramObject2)
/*    */   {
/* 69 */     synchronized (this.lock_) {
/* 70 */       boolean bool1 = paramObject1 == this.value_;
/* 71 */       if (bool1) this.value_ = paramObject2;
/* 72 */       boolean bool2 = bool1;return bool2;
/*    */     }
/*    */   }
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
/*    */   public Object swap(SynchronizedRef paramSynchronizedRef)
/*    */   {
/* 88 */     if (paramSynchronizedRef == this) return get();
/* 89 */     SynchronizedRef localSynchronizedRef1 = this;
/* 90 */     SynchronizedRef localSynchronizedRef2 = paramSynchronizedRef;
/* 91 */     if (System.identityHashCode(localSynchronizedRef1) > System.identityHashCode(localSynchronizedRef2)) {
/* 92 */       localSynchronizedRef1 = paramSynchronizedRef;
/* 93 */       localSynchronizedRef2 = this;
/*    */     }
/* 95 */     synchronized (localSynchronizedRef1.lock_) {
/* 96 */       synchronized (localSynchronizedRef2.lock_) {
/* 97 */         localSynchronizedRef1.set(localSynchronizedRef2.set(localSynchronizedRef1.get()));
/* 98 */         Object localObject3 = get();return localObject3;
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SynchronizedRef.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */