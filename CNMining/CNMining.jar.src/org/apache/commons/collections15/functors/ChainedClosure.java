/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections15.Closure;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChainedClosure<T>
/*     */   implements Closure<T>, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -3520677225766901240L;
/*     */   private final Closure<? super T>[] iClosures;
/*     */   
/*     */   public static <T> Closure<T> getInstance(Closure<? super T>[] closures)
/*     */   {
/*  53 */     FunctorUtils.validate(closures);
/*  54 */     if (closures.length == 0) {
/*  55 */       return NOPClosure.INSTANCE;
/*     */     }
/*  57 */     closures = FunctorUtils.copy(closures);
/*  58 */     return new ChainedClosure(closures);
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
/*     */   public static <T> Closure<T> getInstance(Collection<? super T> closures)
/*     */   {
/*  72 */     if (closures == null) {
/*  73 */       throw new IllegalArgumentException("Closure collection must not be null");
/*     */     }
/*  75 */     if (closures.size() == 0) {
/*  76 */       return NOPClosure.INSTANCE;
/*     */     }
/*     */     
/*  79 */     Closure<? super T>[] cmds = new Closure[closures.size()];
/*  80 */     int i = 0;
/*  81 */     for (Iterator it = closures.iterator(); it.hasNext();) {
/*  82 */       cmds[(i++)] = ((Closure)it.next());
/*     */     }
/*  84 */     FunctorUtils.validate(cmds);
/*  85 */     return new ChainedClosure(cmds);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <T> Closure<T> getInstance(Closure<? super T> closure1, Closure<? super T> closure2)
/*     */   {
/*  97 */     if ((closure1 == null) || (closure2 == null)) {
/*  98 */       throw new IllegalArgumentException("Closures must not be null");
/*     */     }
/* 100 */     Closure<? super T>[] closures = { closure1, closure2 };
/* 101 */     return new ChainedClosure(closures);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ChainedClosure(Closure<? super T>[] closures)
/*     */   {
/* 112 */     this.iClosures = closures;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void execute(T input)
/*     */   {
/* 121 */     for (int i = 0; i < this.iClosures.length; i++) {
/* 122 */       this.iClosures[i].execute(input);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Closure<? super T>[] getClosures()
/*     */   {
/* 133 */     return this.iClosures;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/ChainedClosure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */