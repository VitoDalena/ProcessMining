/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObservableSync
/*     */   implements Sync
/*     */ {
/*  58 */   protected final CopyOnWriteArraySet observers_ = new CopyOnWriteArraySet();
/*     */   
/*     */ 
/*     */ 
/*     */   protected Object arg_;
/*     */   
/*     */ 
/*     */ 
/*     */   public ObservableSync(Object paramObject)
/*     */   {
/*  68 */     this.arg_ = paramObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized Object getNotificationArgument()
/*     */   {
/*  75 */     return this.arg_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Object setNotificationArgument(Object paramObject)
/*     */   {
/*  84 */     Object localObject = this.arg_;
/*  85 */     this.arg_ = paramObject;
/*  86 */     return localObject;
/*     */   }
/*     */   
/*     */ 
/*     */   public void acquire()
/*     */   {
/*  92 */     Object localObject = getNotificationArgument();
/*  93 */     for (Iterator localIterator = this.observers_.iterator(); localIterator.hasNext();) {
/*  94 */       ((SyncObserver)localIterator.next()).onAcquire(localObject);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean attempt(long paramLong) {
/*  99 */     acquire();
/* 100 */     return true;
/*     */   }
/*     */   
/*     */   public void release() {
/* 104 */     Object localObject = getNotificationArgument();
/* 105 */     for (Iterator localIterator = this.observers_.iterator(); localIterator.hasNext();) {
/* 106 */       ((SyncObserver)localIterator.next()).onRelease(localObject);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void attach(SyncObserver paramSyncObserver)
/*     */   {
/* 114 */     this.observers_.add(paramSyncObserver);
/*     */   }
/*     */   
/*     */   public void detach(SyncObserver paramSyncObserver)
/*     */   {
/* 119 */     this.observers_.remove(paramSyncObserver);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator observers()
/*     */   {
/* 127 */     return this.observers_.iterator();
/*     */   }
/*     */   
/*     */   public static abstract interface SyncObserver
/*     */   {
/*     */     public abstract void onAcquire(Object paramObject);
/*     */     
/*     */     public abstract void onRelease(Object paramObject);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/ObservableSync.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */