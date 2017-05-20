/*     */ package edu.oswego.cs.dl.util.concurrent;
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
/*     */ public class Rendezvous
/*     */   implements Barrier
/*     */ {
/*     */   protected final int parties_;
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
/*     */   public static class Rotator
/*     */     implements Rendezvous.RendezvousFunction
/*     */   {
/*     */     public void rendezvousFunction(Object[] paramArrayOfObject)
/*     */     {
/* 142 */       int i = paramArrayOfObject.length - 1;
/* 143 */       Object localObject = paramArrayOfObject[0];
/* 144 */       for (int j = 0; j < i; j++) paramArrayOfObject[j] = paramArrayOfObject[(j + 1)];
/* 145 */       paramArrayOfObject[i] = localObject;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 153 */   protected boolean broken_ = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 158 */   protected int entries_ = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 163 */   protected long departures_ = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final Semaphore entryGate_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final Object[] slots_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected RendezvousFunction rendezvousFunction_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Rendezvous(int paramInt)
/*     */   {
/* 188 */     this(paramInt, new Rotator());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Rendezvous(int paramInt, RendezvousFunction paramRendezvousFunction)
/*     */   {
/* 198 */     if (paramInt <= 0) throw new IllegalArgumentException();
/* 199 */     this.parties_ = paramInt;
/* 200 */     this.rendezvousFunction_ = paramRendezvousFunction;
/* 201 */     this.entryGate_ = new WaiterPreferenceSemaphore(paramInt);
/* 202 */     this.slots_ = new Object[paramInt];
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
/*     */   public synchronized RendezvousFunction setRendezvousFunction(RendezvousFunction paramRendezvousFunction)
/*     */   {
/* 216 */     RendezvousFunction localRendezvousFunction = this.rendezvousFunction_;
/* 217 */     this.rendezvousFunction_ = paramRendezvousFunction;
/* 218 */     return localRendezvousFunction;
/*     */   }
/*     */   
/* 221 */   public int parties() { return this.parties_; }
/*     */   
/* 223 */   public synchronized boolean broken() { return this.broken_; }
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
/*     */   public void restart()
/*     */   {
/*     */     for (;;)
/*     */     {
/* 238 */       synchronized (this) {
/* 239 */         if (this.entries_ != 0) {
/* 240 */           notifyAll();
/*     */         }
/*     */         else {
/* 243 */           this.broken_ = false;
/* 244 */           return;
/*     */         }
/*     */       }
/* 247 */       Thread.yield();
/*     */     }
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
/*     */   public Object rendezvous(Object paramObject)
/*     */     throws InterruptedException, BrokenBarrierException
/*     */   {
/* 279 */     return doRendezvous(paramObject, false, 0L);
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
/*     */   public Object attemptRendezvous(Object paramObject, long paramLong)
/*     */     throws InterruptedException, TimeoutException, BrokenBarrierException
/*     */   {
/* 314 */     return doRendezvous(paramObject, true, paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Object doRendezvous(Object paramObject, boolean paramBoolean, long paramLong)
/*     */     throws InterruptedException, TimeoutException, BrokenBarrierException
/*     */   {
/*     */     long l1;
/*     */     
/* 324 */     if (paramBoolean) {
/* 325 */       l1 = System.currentTimeMillis();
/* 326 */       if (!this.entryGate_.attempt(paramLong)) {
/* 327 */         throw new TimeoutException(paramLong);
/*     */       }
/*     */     }
/*     */     else {
/* 331 */       l1 = 0L;
/* 332 */       this.entryGate_.acquire();
/*     */     }
/*     */     
/* 335 */     synchronized (this)
/*     */     {
/* 337 */       Object localObject1 = null;
/*     */       
/* 339 */       int i = this.entries_++;
/* 340 */       this.slots_[i] = paramObject;
/*     */       
/*     */       try
/*     */       {
/* 344 */         if (this.entries_ == this.parties_)
/*     */         {
/* 346 */           this.departures_ = this.entries_;
/* 347 */           notifyAll();
/*     */           try
/*     */           {
/* 350 */             if ((!this.broken_) && (this.rendezvousFunction_ != null)) {
/* 351 */               this.rendezvousFunction_.rendezvousFunction(this.slots_);
/*     */             }
/*     */           } catch (RuntimeException localRuntimeException) {
/* 354 */             this.broken_ = true;
/*     */           }
/*     */           
/*     */         }
/*     */         else
/*     */         {
/*     */           do
/*     */           {
/* 362 */             long l2 = 0L;
/* 363 */             if (paramBoolean) {
/* 364 */               l2 = paramLong - (System.currentTimeMillis() - l1);
/* 365 */               if (l2 <= 0L) {
/* 366 */                 this.broken_ = true;
/* 367 */                 this.departures_ = this.entries_;
/* 368 */                 notifyAll();
/* 369 */                 throw new TimeoutException(paramLong);
/*     */               }
/*     */             }
/*     */             try
/*     */             {
/* 374 */               wait(l2);
/*     */             }
/*     */             catch (InterruptedException localInterruptedException) {
/* 377 */               if ((this.broken_) || (this.departures_ > 0L)) {
/* 378 */                 Thread.currentThread().interrupt();
/* 379 */                 break;
/*     */               }
/*     */               
/* 382 */               this.broken_ = true;
/* 383 */               this.departures_ = this.entries_;
/* 384 */               notifyAll();
/* 385 */               throw localInterruptedException;
/*     */             }
/* 361 */             if (this.broken_) break; } while (this.departures_ < 1L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         }
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
/*     */       }
/*     */       finally
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 395 */         localObject1 = this.slots_[i];
/*     */         
/*     */ 
/* 398 */         if (--this.departures_ <= 0L) {
/* 399 */           for (int j = 0; j < this.slots_.length; j++) this.slots_[j] = null;
/* 400 */           this.entryGate_.release(this.entries_);
/* 401 */           this.entries_ = 0;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 406 */       if (this.broken_) {
/* 407 */         throw new BrokenBarrierException(i);
/*     */       }
/* 409 */       Object localObject2 = localObject1;return localObject2;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract interface RendezvousFunction
/*     */   {
/*     */     public abstract void rendezvousFunction(Object[] paramArrayOfObject);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/Rendezvous.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */