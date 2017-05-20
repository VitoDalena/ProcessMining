/*     */ package org.deckfour.xes.util;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class XRegistry<T>
/*     */ {
/*     */   private Set<T> registry;
/*     */   private T current;
/*     */   
/*     */   public XRegistry()
/*     */   {
/*  65 */     this.registry = new HashSet();
/*  66 */     this.current = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Set<T> getAvailable()
/*     */   {
/*  73 */     return Collections.unmodifiableSet(this.registry);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public T currentDefault()
/*     */   {
/*  80 */     return (T)this.current;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void register(T instance)
/*     */   {
/*  89 */     if (!isContained(instance)) {
/*  90 */       this.registry.add(instance);
/*  91 */       if (this.current == null) {
/*  92 */         this.current = instance;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCurrentDefault(T instance)
/*     */   {
/* 105 */     this.registry.add(instance);
/* 106 */     this.current = instance;
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
/*     */   protected abstract boolean areEqual(T paramT1, T paramT2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isContained(T instance)
/*     */   {
/* 128 */     for (T ref : this.registry) {
/* 129 */       if (areEqual(instance, ref)) {
/* 130 */         return true;
/*     */       }
/*     */     }
/* 133 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/util/XRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */