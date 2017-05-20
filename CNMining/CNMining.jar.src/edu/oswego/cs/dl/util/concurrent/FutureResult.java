/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
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
/*     */ public class FutureResult
/*     */ {
/*  49 */   protected Object value_ = null;
/*     */   
/*     */ 
/*  52 */   protected boolean ready_ = false;
/*     */   
/*     */ 
/*  55 */   protected InvocationTargetException exception_ = null;
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
/*     */   public Runnable setter(Callable paramCallable)
/*     */   {
/*  72 */     new Runnable() {
/*     */       private final Callable val$function;
/*     */       
/*  75 */       public void run() { try { FutureResult.this.set(this.val$function.call());
/*     */         }
/*     */         catch (Throwable localThrowable) {
/*  78 */           FutureResult.this.setException(localThrowable);
/*     */         }
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */   protected Object doGet() throws InvocationTargetException
/*     */   {
/*  86 */     if (this.exception_ != null) {
/*  87 */       throw this.exception_;
/*     */     }
/*  89 */     return this.value_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Object get()
/*     */     throws InterruptedException, InvocationTargetException
/*     */   {
/* 101 */     while (!this.ready_) wait();
/* 102 */     return doGet();
/*     */   }
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
/*     */   public synchronized Object timedGet(long paramLong)
/*     */     throws TimeoutException, InterruptedException, InvocationTargetException
/*     */   {
/* 117 */     long l1 = paramLong <= 0L ? 0L : System.currentTimeMillis();
/* 118 */     long l2 = paramLong;
/* 119 */     if (this.ready_) return doGet();
/* 120 */     if (l2 <= 0L) throw new TimeoutException(paramLong);
/*     */     do
/*     */     {
/* 123 */       wait(l2);
/* 124 */       if (this.ready_) { return doGet();
/*     */       }
/* 126 */       l2 = paramLong - (System.currentTimeMillis() - l1);
/* 127 */     } while (l2 > 0L);
/* 128 */     throw new TimeoutException(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void set(Object paramObject)
/*     */   {
/* 141 */     this.value_ = paramObject;
/* 142 */     this.ready_ = true;
/* 143 */     notifyAll();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setException(Throwable paramThrowable)
/*     */   {
/* 152 */     this.exception_ = new InvocationTargetException(paramThrowable);
/* 153 */     this.ready_ = true;
/* 154 */     notifyAll();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized InvocationTargetException getException()
/*     */   {
/* 166 */     return this.exception_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized boolean isReady()
/*     */   {
/* 174 */     return this.ready_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Object peek()
/*     */   {
/* 182 */     return this.value_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/* 194 */     this.value_ = null;
/* 195 */     this.exception_ = null;
/* 196 */     this.ready_ = false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/FutureResult.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */