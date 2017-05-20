/*     */ package org.apache.commons.collections15.functors;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
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
/*     */ public class SwitchClosure<T>
/*     */   implements Closure<T>, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 3518477308466486130L;
/*     */   private final Predicate<? super T>[] iPredicates;
/*     */   private final Closure<? super T>[] iClosures;
/*     */   private final Closure<? super T> iDefault;
/*     */   
/*     */   public static <T> Closure<T> getInstance(Predicate<? super T>[] predicates, Closure<? super T>[] closures, Closure<? super T> defaultClosure)
/*     */   {
/*  65 */     FunctorUtils.validate(predicates);
/*  66 */     FunctorUtils.validate(closures);
/*  67 */     if (predicates.length != closures.length) {
/*  68 */       throw new IllegalArgumentException("The predicate and closure arrays must be the same size");
/*     */     }
/*  70 */     if (predicates.length == 0) {
/*  71 */       return defaultClosure == null ? NOPClosure.INSTANCE : defaultClosure;
/*     */     }
/*  73 */     predicates = FunctorUtils.copy(predicates);
/*  74 */     closures = FunctorUtils.copy(closures);
/*  75 */     return new SwitchClosure(predicates, closures, defaultClosure);
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
/*     */   public static <T> Closure<T> getInstance(Map<Predicate<? super T>, Closure<? super T>> predicatesAndClosures)
/*     */   {
/*  96 */     Closure[] closures = null;
/*  97 */     Predicate[] preds = null;
/*  98 */     if (predicatesAndClosures == null) {
/*  99 */       throw new IllegalArgumentException("The predicate and closure map must not be null");
/*     */     }
/* 101 */     if (predicatesAndClosures.size() == 0) {
/* 102 */       return NOPClosure.INSTANCE;
/*     */     }
/*     */     
/* 105 */     Closure defaultClosure = (Closure)predicatesAndClosures.remove(null);
/* 106 */     int size = predicatesAndClosures.size();
/* 107 */     if (size == 0) {
/* 108 */       return defaultClosure == null ? NOPClosure.INSTANCE : defaultClosure;
/*     */     }
/* 110 */     closures = new Closure[size];
/* 111 */     preds = new Predicate[size];
/* 112 */     int i = 0;
/* 113 */     for (Iterator it = predicatesAndClosures.entrySet().iterator(); it.hasNext();) {
/* 114 */       Map.Entry entry = (Map.Entry)it.next();
/* 115 */       preds[i] = ((Predicate)entry.getKey());
/* 116 */       closures[i] = ((Closure)entry.getValue());
/* 117 */       i++;
/*     */     }
/* 119 */     return new SwitchClosure(preds, closures, defaultClosure);
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
/*     */   public SwitchClosure(Predicate<? super T>[] predicates, Closure<? super T>[] closures, Closure<? super T> defaultClosure)
/*     */   {
/* 132 */     this.iPredicates = predicates;
/* 133 */     this.iClosures = closures;
/* 134 */     this.iDefault = (defaultClosure == null ? NOPClosure.INSTANCE : defaultClosure);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void execute(T input)
/*     */   {
/* 143 */     for (int i = 0; i < this.iPredicates.length; i++) {
/* 144 */       if (this.iPredicates[i].evaluate(input) == true) {
/* 145 */         this.iClosures[i].execute(input);
/* 146 */         return;
/*     */       }
/*     */     }
/* 149 */     this.iDefault.execute(input);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Predicate<? super T>[] getPredicates()
/*     */   {
/* 159 */     return this.iPredicates;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Closure<? super T>[] getClosures()
/*     */   {
/* 169 */     return this.iClosures;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Closure<? super T> getDefaultClosure()
/*     */   {
/* 179 */     return this.iDefault;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/functors/SwitchClosure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */