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
/*     */ public class IfClosure<T>
/*     */   implements Closure<T>, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 3518477308466486130L;
/*     */   private final Predicate<? super T> iPredicate;
/*     */   private final Closure<? super T> iTrueClosure;
/*     */   private final Closure<? super T> iFalseClosure;
/*     */   
/*     */   public static <T> Closure<T> getInstance(Predicate<? super T> predicate, Closure<? super T> trueClosure, Closure<? super T> falseClosure)
/*     */   {
/*  62 */     if (predicate == null) {
/*  63 */       throw new IllegalArgumentException("Predicate must not be null");
/*     */     }
/*  65 */     if ((trueClosure == null) || (falseClosure == null)) {
/*  66 */       throw new IllegalArgumentException("Closures must not be null");
/*     */     }
/*  68 */     return new IfClosure(predicate, trueClosure, falseClosure);
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
/*     */   public IfClosure(Predicate<? super T> predicate, Closure<? super T> trueClosure, Closure<? super T> falseClosure)
/*     */   {
/*  81 */     this.iPredicate = predicate;
/*  82 */     this.iTrueClosure = trueClosure;
/*  83 */     this.iFalseClosure = falseClosure;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void execute(T input)
/*     */   {
/*  92 */     if (this.iPredicate.evaluate(input) == true) {
/*  93 */       this.iTrueClosure.execute(input);
/*     */     } else {
/*  95 */       this.iFalseClosure.execute(input);
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
/* 106 */     return this.iPredicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Closure<? super T> getTrueClosure()
/*     */   {
/* 116 */     return this.iTrueClosure;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Closure<? super T> getFalseClosure()
/*     */   {
/* 126 */     return this.iFalseClosure;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/IfClosure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */