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
/*     */ public class SynchronizedChar
/*     */   extends SynchronizedVariable
/*     */   implements Comparable, Cloneable
/*     */ {
/*     */   protected char value_;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedChar(char paramChar)
/*     */   {
/*  32 */     this.value_ = paramChar;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SynchronizedChar(char paramChar, Object paramObject)
/*     */   {
/*  40 */     super(paramObject);
/*  41 */     this.value_ = paramChar;
/*     */   }
/*     */   
/*     */ 
/*     */   public final char get()
/*     */   {
/*  47 */     synchronized (this.lock_) { char c = this.value_;return c;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public char set(char paramChar)
/*     */   {
/*  55 */     synchronized (this.lock_) {
/*  56 */       char c1 = this.value_;
/*  57 */       this.value_ = paramChar;
/*  58 */       char c2 = c1;return c2;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean commit(char paramChar1, char paramChar2)
/*     */   {
/*  67 */     synchronized (this.lock_) {
/*  68 */       boolean bool1 = paramChar1 == this.value_;
/*  69 */       if (bool1) this.value_ = paramChar2;
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
/*     */   public char swap(SynchronizedChar paramSynchronizedChar)
/*     */   {
/*  86 */     if (paramSynchronizedChar == this) return get();
/*  87 */     SynchronizedChar localSynchronizedChar1 = this;
/*  88 */     SynchronizedChar localSynchronizedChar2 = paramSynchronizedChar;
/*  89 */     if (System.identityHashCode(localSynchronizedChar1) > System.identityHashCode(localSynchronizedChar2)) {
/*  90 */       localSynchronizedChar1 = paramSynchronizedChar;
/*  91 */       localSynchronizedChar2 = this;
/*     */     }
/*  93 */     synchronized (localSynchronizedChar1.lock_) {
/*  94 */       synchronized (localSynchronizedChar2.lock_) {
/*  95 */         localSynchronizedChar1.set(localSynchronizedChar2.set(localSynchronizedChar1.get()));
/*  96 */         char c = get();return c;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public char add(char paramChar)
/*     */   {
/* 106 */     synchronized (this.lock_) {
/* 107 */       char c = this.value_ = (char)(this.value_ + paramChar);return c;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public char subtract(char paramChar)
/*     */   {
/* 116 */     synchronized (this.lock_) {
/* 117 */       char c = this.value_ = (char)(this.value_ - paramChar);return c;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized char multiply(char paramChar)
/*     */   {
/* 126 */     synchronized (this.lock_) {
/* 127 */       char c = this.value_ = (char)(this.value_ * paramChar);return c;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public char divide(char paramChar)
/*     */   {
/* 136 */     synchronized (this.lock_) {
/* 137 */       char c = this.value_ = (char)(this.value_ / paramChar);return c;
/*     */     }
/*     */   }
/*     */   
/*     */   public int compareTo(char paramChar) {
/* 142 */     char c = get();
/* 143 */     return c == paramChar ? 0 : c < paramChar ? -1 : 1;
/*     */   }
/*     */   
/*     */   public int compareTo(SynchronizedChar paramSynchronizedChar) {
/* 147 */     return compareTo(paramSynchronizedChar.get());
/*     */   }
/*     */   
/*     */   public int compareTo(Object paramObject) {
/* 151 */     return compareTo((SynchronizedChar)paramObject);
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 155 */     if ((paramObject != null) && ((paramObject instanceof SynchronizedChar)))
/*     */     {
/* 157 */       return get() == ((SynchronizedChar)paramObject).get();
/*     */     }
/* 159 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 163 */     return get();
/*     */   }
/*     */   
/* 166 */   public String toString() { return String.valueOf(get()); }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/SynchronizedChar.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */