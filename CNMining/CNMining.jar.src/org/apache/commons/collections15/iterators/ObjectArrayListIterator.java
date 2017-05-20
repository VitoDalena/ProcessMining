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
/*     */ public class ObjectArrayListIterator<E>
/*     */   extends ObjectArrayIterator<E>
/*     */   implements ListIterator<E>, ResettableListIterator<E>
/*     */ {
/*  50 */   protected int lastItemIndex = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayListIterator() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayListIterator(E[] array)
/*     */   {
/*  70 */     super(array);
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
/*     */   public ObjectArrayListIterator(E[] array, int start)
/*     */   {
/*  83 */     super(array, start);
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
/*     */   public ObjectArrayListIterator(E[] array, int start, int end)
/*     */   {
/*  98 */     super(array, start, end);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasPrevious()
/*     */   {
/* 110 */     return this.index > this.startIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E previous()
/*     */   {
/* 120 */     if (!hasPrevious()) {
/* 121 */       throw new NoSuchElementException();
/*     */     }
/* 123 */     this.lastItemIndex = (--this.index);
/* 124 */     return (E)this.array[this.index];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E next()
/*     */   {
/* 134 */     if (!hasNext()) {
/* 135 */       throw new NoSuchElementException();
/*     */     }
/* 137 */     this.lastItemIndex = this.index;
/* 138 */     return (E)this.array[(this.index++)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int nextIndex()
/*     */   {
/* 147 */     return this.index - this.startIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int previousIndex()
/*     */   {
/* 156 */     return this.index - this.startIndex - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(E obj)
/*     */   {
/* 167 */     throw new UnsupportedOperationException("add() method is not supported");
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
/*     */   public void set(E obj)
/*     */   {
/* 188 */     if (this.lastItemIndex == -1) {
/* 189 */       throw new IllegalStateException("must call next() or previous() before a call to set()");
/*     */     }
/*     */     
/* 192 */     this.array[this.lastItemIndex] = obj;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 199 */     super.reset();
/* 200 */     this.lastItemIndex = -1;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/ObjectArrayListIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */