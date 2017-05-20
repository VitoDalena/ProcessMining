/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections15.ResettableListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SingletonListIterator<E>
/*     */   implements ListIterator<E>, ResettableListIterator<E>
/*     */ {
/*  35 */   private boolean beforeFirst = true;
/*  36 */   private boolean nextCalled = false;
/*  37 */   private boolean removed = false;
/*     */   
/*     */ 
/*     */ 
/*     */   private E object;
/*     */   
/*     */ 
/*     */ 
/*     */   public SingletonListIterator(E object)
/*     */   {
/*  47 */     this.object = object;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/*  58 */     return (this.beforeFirst) && (!this.removed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasPrevious()
/*     */   {
/*  69 */     return (!this.beforeFirst) && (!this.removed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int nextIndex()
/*     */   {
/*  79 */     return this.beforeFirst ? 0 : 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int previousIndex()
/*     */   {
/*  90 */     return this.beforeFirst ? -1 : 0;
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
/* 103 */     if ((!this.beforeFirst) || (this.removed)) {
/* 104 */       throw new NoSuchElementException();
/*     */     }
/* 106 */     this.beforeFirst = false;
/* 107 */     this.nextCalled = true;
/* 108 */     return (E)this.object;
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
/*     */   public E previous()
/*     */   {
/* 121 */     if ((this.beforeFirst) || (this.removed)) {
/* 122 */       throw new NoSuchElementException();
/*     */     }
/* 124 */     this.beforeFirst = true;
/* 125 */     return (E)this.object;
/*     */   }
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
/* 137 */     if ((!this.nextCalled) || (this.removed)) {
/* 138 */       throw new IllegalStateException();
/*     */     }
/* 140 */     this.object = null;
/* 141 */     this.removed = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(E obj)
/*     */   {
/* 151 */     throw new UnsupportedOperationException("add() is not supported by this iterator");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(E obj)
/*     */   {
/* 162 */     if ((!this.nextCalled) || (this.removed)) {
/* 163 */       throw new IllegalStateException();
/*     */     }
/* 165 */     this.object = obj;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 172 */     this.beforeFirst = true;
/* 173 */     this.nextCalled = false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/SingletonListIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */