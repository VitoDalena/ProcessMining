/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
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
/*     */ public class FilterIterator<E>
/*     */   implements Iterator<E>
/*     */ {
/*     */   private Iterator<E> iterator;
/*     */   private Predicate<? super E> predicate;
/*     */   private E nextObject;
/*  52 */   private boolean nextObjectSet = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FilterIterator() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FilterIterator(Iterator<E> iterator)
/*     */   {
/*  71 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FilterIterator(Iterator<E> iterator, Predicate<? super E> predicate)
/*     */   {
/*  83 */     this.iterator = iterator;
/*  84 */     this.predicate = predicate;
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
/*  95 */     if (this.nextObjectSet) {
/*  96 */       return true;
/*     */     }
/*  98 */     return setNextObject();
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
/* 110 */     if ((!this.nextObjectSet) && 
/* 111 */       (!setNextObject())) {
/* 112 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/* 115 */     this.nextObjectSet = false;
/* 116 */     return (E)this.nextObject;
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
/*     */   public void remove()
/*     */   {
/* 131 */     if (this.nextObjectSet) {
/* 132 */       throw new IllegalStateException("remove() cannot be called");
/*     */     }
/* 134 */     this.iterator.remove();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<E> getIterator()
/*     */   {
/* 144 */     return this.iterator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIterator(Iterator<E> iterator)
/*     */   {
/* 154 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Predicate<? super E> getPredicate()
/*     */   {
/* 164 */     return this.predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPredicate(Predicate<? super E> predicate)
/*     */   {
/* 173 */     this.predicate = predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean setNextObject()
/*     */   {
/* 182 */     while (this.iterator.hasNext()) {
/* 183 */       E object = this.iterator.next();
/* 184 */       if (this.predicate.evaluate(object)) {
/* 185 */         this.nextObject = object;
/* 186 */         this.nextObjectSet = true;
/* 187 */         return true;
/*     */       }
/*     */     }
/* 190 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/FilterIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */