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
/*     */ public class SynchronizedDouble
/*     */   extends SynchronizedVariable
/*     */   implements Comparable, Cloneable
/*     */ {
/*     */   protected double value_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedDouble(double paramDouble)
/*     */   {
/*  32 */     this.value_ = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedDouble(double paramDouble, Object paramObject)
/*     */   {
/*  40 */     super(paramObject);
/*  41 */     this.value_ = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */   public final double get()
/*     */   {
/*  47 */     synchronized (this.lock_) { double d = this.value_;return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double set(double paramDouble)
/*     */   {
/*  55 */     synchronized (this.lock_) {
/*  56 */       double d1 = this.value_;
/*  57 */       this.value_ = paramDouble;
/*  58 */       double d2 = d1;return d2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean commit(double paramDouble1, double paramDouble2)
/*     */   {
/*  67 */     synchronized (this.lock_) {
/*  68 */       boolean bool1 = paramDouble1 == this.value_;
/*  69 */       if (bool1) this.value_ = paramDouble2;
/*  70 */       boolean bool2 = bool1;return bool2;
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
/*     */   public double swap(SynchronizedDouble paramSynchronizedDouble)
/*     */   {
/*  86 */     if (paramSynchronizedDouble == this) return get();
/*  87 */     SynchronizedDouble localSynchronizedDouble1 = this;
/*  88 */     SynchronizedDouble localSynchronizedDouble2 = paramSynchronizedDouble;
/*  89 */     if (System.identityHashCode(localSynchronizedDouble1) > System.identityHashCode(localSynchronizedDouble2)) {
/*  90 */       localSynchronizedDouble1 = paramSynchronizedDouble;
/*  91 */       localSynchronizedDouble2 = this;
/*     */     }
/*  93 */     synchronized (localSynchronizedDouble1.lock_) {
/*  94 */       synchronized (localSynchronizedDouble2.lock_) {
/*  95 */         localSynchronizedDouble1.set(localSynchronizedDouble2.set(localSynchronizedDouble1.get()));
/*  96 */         double d = get();return d;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double add(double paramDouble)
/*     */   {
/* 107 */     synchronized (this.lock_) {
/* 108 */       double d = this.value_ += paramDouble;return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double subtract(double paramDouble)
/*     */   {
/* 117 */     synchronized (this.lock_) {
/* 118 */       double d = this.value_ -= paramDouble;return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized double multiply(double paramDouble)
/*     */   {
/* 127 */     synchronized (this.lock_) {
/* 128 */       double d = this.value_ *= paramDouble;return d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double divide(double paramDouble)
/*     */   {
/* 137 */     synchronized (this.lock_) {
/* 138 */       double d = this.value_ /= paramDouble;return d;
/*     */     }
/*     */   }
/*     */   
/*     */   public int compareTo(double paramDouble) {
/* 143 */     double d = get();
/* 144 */     return d == paramDouble ? 0 : d < paramDouble ? -1 : 1;
/*     */   }
/*     */   
/*     */   public int compareTo(SynchronizedDouble paramSynchronizedDouble) {
/* 148 */     return compareTo(paramSynchronizedDouble.get());
/*     */   }
/*     */   
/*     */   public int compareTo(Object paramObject) {
/* 152 */     return compareTo((SynchronizedDouble)paramObject);
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 156 */     if ((paramObject != null) && ((paramObject instanceof SynchronizedDouble)))
/*     */     {
/* 158 */       return get() == ((SynchronizedDouble)paramObject).get();
/*     */     }
/* 160 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 164 */     long l = Double.doubleToLongBits(get());
/* 165 */     return (int)(l ^ l >> 32);
/*     */   }
/*     */   
/* 168 */   public String toString() { return String.valueOf(get()); }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SynchronizedDouble.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */