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
/*     */ public class SynchronizedBoolean
/*     */   extends SynchronizedVariable
/*     */   implements Comparable, Cloneable
/*     */ {
/*     */   protected boolean value_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedBoolean(boolean paramBoolean)
/*     */   {
/*  31 */     this.value_ = paramBoolean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedBoolean(boolean paramBoolean, Object paramObject)
/*     */   {
/*  39 */     super(paramObject);
/*  40 */     this.value_ = paramBoolean;
/*     */   }
/*     */   
/*     */ 
/*     */   public final boolean get()
/*     */   {
/*  46 */     synchronized (this.lock_) { boolean bool = this.value_;return bool;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean set(boolean paramBoolean)
/*     */   {
/*  54 */     synchronized (this.lock_) {
/*  55 */       boolean bool1 = this.value_;
/*  56 */       this.value_ = paramBoolean;
/*  57 */       boolean bool2 = bool1;return bool2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean commit(boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/*  66 */     synchronized (this.lock_) {
/*  67 */       boolean bool1 = paramBoolean1 == this.value_;
/*  68 */       if (bool1) this.value_ = paramBoolean2;
/*  69 */       boolean bool2 = bool1;return bool2;
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
/*     */   public boolean swap(SynchronizedBoolean paramSynchronizedBoolean)
/*     */   {
/*  84 */     if (paramSynchronizedBoolean == this) return get();
/*  85 */     SynchronizedBoolean localSynchronizedBoolean1 = this;
/*  86 */     SynchronizedBoolean localSynchronizedBoolean2 = paramSynchronizedBoolean;
/*  87 */     if (System.identityHashCode(localSynchronizedBoolean1) > System.identityHashCode(localSynchronizedBoolean2)) {
/*  88 */       localSynchronizedBoolean1 = paramSynchronizedBoolean;
/*  89 */       localSynchronizedBoolean2 = this;
/*     */     }
/*  91 */     synchronized (localSynchronizedBoolean1.lock_) {
/*  92 */       synchronized (localSynchronizedBoolean2.lock_) {
/*  93 */         localSynchronizedBoolean1.set(localSynchronizedBoolean2.set(localSynchronizedBoolean1.get()));
/*  94 */         boolean bool = get();return bool;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean complement()
/*     */   {
/* 104 */     synchronized (this.lock_) {
/* 105 */       this.value_ = (!this.value_);
/* 106 */       boolean bool = this.value_;return bool;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean and(boolean paramBoolean)
/*     */   {
/* 115 */     synchronized (this.lock_) {
/* 116 */       this.value_ &= paramBoolean;
/* 117 */       boolean bool = this.value_;return bool;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean or(boolean paramBoolean)
/*     */   {
/* 126 */     synchronized (this.lock_) {
/* 127 */       this.value_ |= paramBoolean;
/* 128 */       boolean bool = this.value_;return bool;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean xor(boolean paramBoolean)
/*     */   {
/* 138 */     synchronized (this.lock_) {
/* 139 */       this.value_ ^= paramBoolean;
/* 140 */       boolean bool = this.value_;return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public int compareTo(boolean paramBoolean)
/*     */   {
/* 146 */     boolean bool = get();
/* 147 */     return bool ? 1 : bool == paramBoolean ? 0 : -1;
/*     */   }
/*     */   
/*     */   public int compareTo(SynchronizedBoolean paramSynchronizedBoolean) {
/* 151 */     return compareTo(paramSynchronizedBoolean.get());
/*     */   }
/*     */   
/*     */   public int compareTo(Object paramObject) {
/* 155 */     return compareTo((SynchronizedBoolean)paramObject);
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 160 */     if ((paramObject != null) && ((paramObject instanceof SynchronizedBoolean)))
/*     */     {
/* 162 */       return get() == ((SynchronizedBoolean)paramObject).get();
/*     */     }
/* 164 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 168 */     boolean bool = get();
/* 169 */     return bool ? 3412688 : 8319343;
/*     */   }
/*     */   
/* 172 */   public String toString() { return String.valueOf(get()); }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SynchronizedBoolean.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */