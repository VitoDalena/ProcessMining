/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections15.ResettableIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SingletonIterator<E>
/*     */   implements Iterator<E>, ResettableIterator<E>
/*     */ {
/*     */   private final boolean removeAllowed;
/*  43 */   private boolean beforeFirst = true;
/*     */   
/*     */ 
/*     */ 
/*  47 */   private boolean removed = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private E object;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SingletonIterator(E object)
/*     */   {
/*  60 */     this(object, true);
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
/*     */   public SingletonIterator(E object, boolean removeAllowed)
/*     */   {
/*  73 */     this.object = object;
/*  74 */     this.removeAllowed = removeAllowed;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/*  86 */     return (this.beforeFirst) && (!this.removed);
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
/*     */   public E next()
/*     */   {
/*  99 */     if ((!this.beforeFirst) || (this.removed)) {
/* 100 */       throw new NoSuchElementException();
/*     */     }
/* 102 */     this.beforeFirst = false;
/* 103 */     return (E)this.object;
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
/*     */   public void remove()
/*     */   {
/* 116 */     if (this.removeAllowed) {
/* 117 */       if ((this.removed) || (this.beforeFirst)) {
/* 118 */         throw new IllegalStateException();
/*     */       }
/* 120 */       this.object = null;
/* 121 */       this.removed = true;
/*     */     }
/*     */     else {
/* 124 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 132 */     this.beforeFirst = true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/SingletonIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */