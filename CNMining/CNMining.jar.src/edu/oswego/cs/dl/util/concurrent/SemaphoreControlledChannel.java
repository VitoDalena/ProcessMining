/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
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
/*     */ public abstract class SemaphoreControlledChannel
/*     */   implements BoundedChannel
/*     */ {
/*     */   protected final Semaphore putGuard_;
/*     */   protected final Semaphore takeGuard_;
/*     */   protected int capacity_;
/*     */   
/*     */   public SemaphoreControlledChannel(int paramInt)
/*     */     throws IllegalArgumentException
/*     */   {
/*  37 */     if (paramInt <= 0) throw new IllegalArgumentException();
/*  38 */     this.capacity_ = paramInt;
/*  39 */     this.putGuard_ = new Semaphore(paramInt);
/*  40 */     this.takeGuard_ = new Semaphore(0L);
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
/*     */   public SemaphoreControlledChannel(int paramInt, Class paramClass)
/*     */     throws IllegalArgumentException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException
/*     */   {
/*  64 */     if (paramInt <= 0) throw new IllegalArgumentException();
/*  65 */     this.capacity_ = paramInt;
/*  66 */     Class[] arrayOfClass = { Integer.TYPE };
/*  67 */     Constructor localConstructor = paramClass.getDeclaredConstructor(arrayOfClass);
/*  68 */     Integer[] arrayOfInteger1 = { new Integer(paramInt) };
/*  69 */     this.putGuard_ = ((Semaphore)localConstructor.newInstance(arrayOfInteger1));
/*  70 */     Integer[] arrayOfInteger2 = { new Integer(0) };
/*  71 */     this.takeGuard_ = ((Semaphore)localConstructor.newInstance(arrayOfInteger2));
/*     */   }
/*     */   
/*     */   public int capacity()
/*     */   {
/*  76 */     return this.capacity_;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/*  84 */     return (int)this.takeGuard_.permits();
/*     */   }
/*     */   
/*     */ 
/*     */   protected abstract void insert(Object paramObject);
/*     */   
/*     */ 
/*     */   protected abstract Object extract();
/*     */   
/*     */ 
/*     */   public void put(Object paramObject)
/*     */     throws InterruptedException
/*     */   {
/*  97 */     if (paramObject == null) throw new IllegalArgumentException();
/*  98 */     if (Thread.interrupted()) throw new InterruptedException();
/*  99 */     this.putGuard_.acquire();
/*     */     try {
/* 101 */       insert(paramObject);
/* 102 */       this.takeGuard_.release();
/*     */     }
/*     */     catch (ClassCastException localClassCastException) {
/* 105 */       this.putGuard_.release();
/* 106 */       throw localClassCastException;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean offer(Object paramObject, long paramLong) throws InterruptedException {
/* 111 */     if (paramObject == null) throw new IllegalArgumentException();
/* 112 */     if (Thread.interrupted()) throw new InterruptedException();
/* 113 */     if (!this.putGuard_.attempt(paramLong)) {
/* 114 */       return false;
/*     */     }
/*     */     try {
/* 117 */       insert(paramObject);
/* 118 */       this.takeGuard_.release();
/* 119 */       return true;
/*     */     }
/*     */     catch (ClassCastException localClassCastException) {
/* 122 */       this.putGuard_.release();
/* 123 */       throw localClassCastException;
/*     */     }
/*     */   }
/*     */   
/*     */   public Object take() throws InterruptedException
/*     */   {
/* 129 */     if (Thread.interrupted()) throw new InterruptedException();
/* 130 */     this.takeGuard_.acquire();
/*     */     try {
/* 132 */       Object localObject = extract();
/* 133 */       this.putGuard_.release();
/* 134 */       return localObject;
/*     */     }
/*     */     catch (ClassCastException localClassCastException) {
/* 137 */       this.takeGuard_.release();
/* 138 */       throw localClassCastException;
/*     */     }
/*     */   }
/*     */   
/*     */   public Object poll(long paramLong) throws InterruptedException {
/* 143 */     if (Thread.interrupted()) throw new InterruptedException();
/* 144 */     if (!this.takeGuard_.attempt(paramLong)) {
/* 145 */       return null;
/*     */     }
/*     */     try {
/* 148 */       Object localObject = extract();
/* 149 */       this.putGuard_.release();
/* 150 */       return localObject;
/*     */     }
/*     */     catch (ClassCastException localClassCastException) {
/* 153 */       this.takeGuard_.release();
/* 154 */       throw localClassCastException;
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract Object peek();
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SemaphoreControlledChannel.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */