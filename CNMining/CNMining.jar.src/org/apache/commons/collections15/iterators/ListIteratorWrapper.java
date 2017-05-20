/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListIteratorWrapper<E>
/*     */   implements ListIterator<E>
/*     */ {
/*     */   private final Iterator<E> iterator;
/*  40 */   private final LinkedList<E> list = new LinkedList();
/*     */   
/*     */ 
/*  43 */   private int currentIndex = 0;
/*     */   
/*     */ 
/*  46 */   private int wrappedIteratorIndex = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final String UNSUPPORTED_OPERATION_MESSAGE = "ListIteratorWrapper does not support optional operations of ListIterator.";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ListIteratorWrapper(Iterator<E> iterator)
/*     */   {
/*  62 */     if (iterator == null) {
/*  63 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/*  65 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(E o)
/*     */     throws UnsupportedOperationException
/*     */   {
/*  78 */     throw new UnsupportedOperationException("ListIteratorWrapper does not support optional operations of ListIterator.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasNext()
/*     */   {
/*  88 */     if (this.currentIndex == this.wrappedIteratorIndex) {
/*  89 */       return this.iterator.hasNext();
/*     */     }
/*     */     
/*  92 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean hasPrevious()
/*     */   {
/* 101 */     if (this.currentIndex == 0) {
/* 102 */       return false;
/*     */     }
/*     */     
/* 105 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E next()
/*     */     throws NoSuchElementException
/*     */   {
/* 115 */     if (this.currentIndex < this.wrappedIteratorIndex) {
/* 116 */       this.currentIndex += 1;
/* 117 */       return (E)this.list.get(this.currentIndex - 1);
/*     */     }
/*     */     
/* 120 */     E retval = this.iterator.next();
/* 121 */     this.list.add(retval);
/* 122 */     this.currentIndex += 1;
/* 123 */     this.wrappedIteratorIndex += 1;
/* 124 */     return retval;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int nextIndex()
/*     */   {
/* 133 */     return this.currentIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E previous()
/*     */     throws NoSuchElementException
/*     */   {
/* 143 */     if (this.currentIndex == 0) {
/* 144 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/* 147 */     this.currentIndex -= 1;
/* 148 */     return (E)this.list.get(this.currentIndex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int previousIndex()
/*     */   {
/* 157 */     return this.currentIndex - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void remove()
/*     */     throws UnsupportedOperationException
/*     */   {
/* 166 */     throw new UnsupportedOperationException("ListIteratorWrapper does not support optional operations of ListIterator.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(E o)
/*     */     throws UnsupportedOperationException
/*     */   {
/* 176 */     throw new UnsupportedOperationException("ListIteratorWrapper does not support optional operations of ListIterator.");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/ListIteratorWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */