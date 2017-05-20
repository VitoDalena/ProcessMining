/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.collections15.Closure;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WhileClosure<T>
/*     */   implements Closure<T>, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -3110538116913760108L;
/*     */   private final Predicate<? super T> iPredicate;
/*     */   private final Closure<? super T> iClosure;
/*     */   private final boolean iDoLoop;
/*     */   
/*     */   public static <T> Closure<T> getInstance(Predicate<? super T> predicate, Closure<? super T> closure, boolean doLoop)
/*     */   {
/*  62 */     if (predicate == null) {
/*  63 */       throw new IllegalArgumentException("Predicate must not be null");
/*     */     }
/*  65 */     if (closure == null) {
/*  66 */       throw new IllegalArgumentException("Closure must not be null");
/*     */     }
/*  68 */     return new WhileClosure(predicate, closure, doLoop);
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
/*     */   public WhileClosure(Predicate<? super T> predicate, Closure<? super T> closure, boolean doLoop)
/*     */   {
/*  81 */     this.iPredicate = predicate;
/*  82 */     this.iClosure = closure;
/*  83 */     this.iDoLoop = doLoop;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void execute(T input)
/*     */   {
/*  92 */     if (this.iDoLoop) {
/*  93 */       this.iClosure.execute(input);
/*     */     }
/*  95 */     while (this.iPredicate.evaluate(input)) {
/*  96 */       this.iClosure.execute(input);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Predicate<? super T> getPredicate()
/*     */   {
/* 107 */     return this.iPredicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Closure<? super T> getClosure()
/*     */   {
/* 117 */     return this.iClosure;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isDoLoop()
/*     */   {
/* 127 */     return this.iDoLoop;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/WhileClosure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */