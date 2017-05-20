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
/*    */ public class LayeredSync
/*    */   implements Sync
/*    */ {
/*    */   protected final Sync outer_;
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
/*    */   protected final Sync inner_;
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
/*    */   public LayeredSync(Sync paramSync1, Sync paramSync2)
/*    */   {
/* 43 */     this.outer_ = paramSync1;
/* 44 */     this.inner_ = paramSync2;
/*    */   }
/*    */   
/*    */   public void acquire() throws InterruptedException {
/* 48 */     this.outer_.acquire();
/*    */     try {
/* 50 */       this.inner_.acquire();
/*    */     }
/*    */     catch (InterruptedException localInterruptedException) {
/* 53 */       this.outer_.release();
/* 54 */       throw localInterruptedException;
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean attempt(long paramLong) throws InterruptedException
/*    */   {
/* 60 */     long l1 = paramLong <= 0L ? 0L : System.currentTimeMillis();
/* 61 */     long l2 = paramLong;
/*    */     
/* 63 */     if (this.outer_.attempt(l2)) {
/*    */       try {
/* 65 */         if (paramLong > 0L)
/* 66 */           l2 = paramLong - (System.currentTimeMillis() - l1);
/* 67 */         if (this.inner_.attempt(l2)) {
/* 68 */           return true;
/*    */         }
/* 70 */         this.outer_.release();
/* 71 */         return false;
/*    */       }
/*    */       catch (InterruptedException localInterruptedException)
/*    */       {
/* 75 */         this.outer_.release();
/* 76 */         throw localInterruptedException;
/*    */       }
/*    */     }
/*    */     
/* 80 */     return false;
/*    */   }
/*    */   
/*    */   public void release() {
/* 84 */     this.inner_.release();
/* 85 */     this.outer_.release();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/LayeredSync.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */