/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.lang.reflect.Array;
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
/*     */ 
/*     */ 
/*     */ public class ArrayListIterator<E>
/*     */   extends ArrayIterator<E>
/*     */   implements ListIterator<E>, ResettableListIterator<E>
/*     */ {
/*  53 */   protected int lastItemIndex = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrayListIterator() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrayListIterator(Object array)
/*     */   {
/*  76 */     super(array);
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
/*     */   public ArrayListIterator(Object array, int startIndex)
/*     */   {
/*  90 */     super(array, startIndex);
/*  91 */     this.startIndex = startIndex;
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
/*     */   public ArrayListIterator(Object array, int startIndex, int endIndex)
/*     */   {
/* 107 */     super(array, startIndex, endIndex);
/* 108 */     this.startIndex = startIndex;
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
/* 119 */     return this.index > this.startIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E previous()
/*     */   {
/* 129 */     if (!hasPrevious()) {
/* 130 */       throw new NoSuchElementException();
/*     */     }
/* 132 */     this.lastItemIndex = (--this.index);
/* 133 */     return (E)Array.get(this.array, this.index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E next()
/*     */   {
/* 143 */     if (!hasNext()) {
/* 144 */       throw new NoSuchElementException();
/*     */     }
/* 146 */     this.lastItemIndex = this.index;
/* 147 */     return (E)Array.get(this.array, this.index++);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int nextIndex()
/*     */   {
/* 156 */     return this.index - this.startIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int previousIndex()
/*     */   {
/* 165 */     return this.index - this.startIndex - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(E o)
/*     */   {
/* 176 */     throw new UnsupportedOperationException("add() method is not supported");
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
/*     */   public void set(E o)
/*     */   {
/* 195 */     if (this.lastItemIndex == -1) {
/* 196 */       throw new IllegalStateException("must call next() or previous() before a call to set()");
/*     */     }
/*     */     
/* 199 */     Array.set(this.array, this.lastItemIndex, o);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 206 */     super.reset();
/* 207 */     this.lastItemIndex = -1;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/ArrayListIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */