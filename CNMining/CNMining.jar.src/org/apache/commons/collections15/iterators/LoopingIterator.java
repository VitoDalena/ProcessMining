/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.Collection;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoopingIterator<E>
/*     */   implements ResettableIterator<E>
/*     */ {
/*     */   private Collection<E> collection;
/*     */   private Iterator<E> iterator;
/*     */   
/*     */   public LoopingIterator(Collection<E> coll)
/*     */   {
/*  61 */     if (coll == null) {
/*  62 */       throw new NullPointerException("The collection must not be null");
/*     */     }
/*  64 */     this.collection = coll;
/*  65 */     reset();
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
/*  77 */     return this.collection.size() > 0;
/*     */   }
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
/*  89 */     if (this.collection.size() == 0) {
/*  90 */       throw new NoSuchElementException("There are no elements for this iterator to loop on");
/*     */     }
/*  92 */     if (!this.iterator.hasNext()) {
/*  93 */       reset();
/*     */     }
/*  95 */     return (E)this.iterator.next();
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
/*     */   public void remove()
/*     */   {
/* 111 */     this.iterator.remove();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void reset()
/*     */   {
/* 118 */     this.iterator = this.collection.iterator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 127 */     return this.collection.size();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/LoopingIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */