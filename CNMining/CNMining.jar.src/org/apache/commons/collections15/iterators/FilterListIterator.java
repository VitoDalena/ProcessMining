/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.ListIterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterListIterator<E>
/*     */   implements ListIterator<E>
/*     */ {
/*     */   private ListIterator<E> iterator;
/*     */   private Predicate<? super E> predicate;
/*     */   private E nextObject;
/*  58 */   private boolean nextObjectSet = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private E previousObject;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  70 */   private boolean previousObjectSet = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  75 */   private int nextIndex = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FilterListIterator() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FilterListIterator(ListIterator<E> iterator)
/*     */   {
/*  95 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FilterListIterator(ListIterator<E> iterator, Predicate<? super E> predicate)
/*     */   {
/* 106 */     this.iterator = iterator;
/* 107 */     this.predicate = predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FilterListIterator(Predicate<? super E> predicate)
/*     */   {
/* 117 */     this.predicate = predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(E o)
/*     */   {
/* 125 */     throw new UnsupportedOperationException("FilterListIterator.add(Object) is not supported.");
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 129 */     if (this.nextObjectSet) {
/* 130 */       return true;
/*     */     }
/* 132 */     return setNextObject();
/*     */   }
/*     */   
/*     */   public boolean hasPrevious()
/*     */   {
/* 137 */     if (this.previousObjectSet) {
/* 138 */       return true;
/*     */     }
/* 140 */     return setPreviousObject();
/*     */   }
/*     */   
/*     */   public E next()
/*     */   {
/* 145 */     if ((!this.nextObjectSet) && 
/* 146 */       (!setNextObject())) {
/* 147 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/* 150 */     this.nextIndex += 1;
/* 151 */     E temp = this.nextObject;
/* 152 */     clearNextObject();
/* 153 */     return temp;
/*     */   }
/*     */   
/*     */   public int nextIndex() {
/* 157 */     return this.nextIndex;
/*     */   }
/*     */   
/*     */   public E previous() {
/* 161 */     if ((!this.previousObjectSet) && 
/* 162 */       (!setPreviousObject())) {
/* 163 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/* 166 */     this.nextIndex -= 1;
/* 167 */     E temp = this.previousObject;
/* 168 */     clearPreviousObject();
/* 169 */     return temp;
/*     */   }
/*     */   
/*     */   public int previousIndex() {
/* 173 */     return this.nextIndex - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void remove()
/*     */   {
/* 180 */     throw new UnsupportedOperationException("FilterListIterator.remove() is not supported.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void set(E o)
/*     */   {
/* 187 */     throw new UnsupportedOperationException("FilterListIterator.set(Object) is not supported.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ListIterator<E> getListIterator()
/*     */   {
/* 197 */     return this.iterator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setListIterator(ListIterator<E> iterator)
/*     */   {
/* 207 */     this.iterator = iterator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Predicate<? super E> getPredicate()
/*     */   {
/* 217 */     return this.predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPredicate(Predicate<? super E> predicate)
/*     */   {
/* 226 */     this.predicate = predicate;
/*     */   }
/*     */   
/*     */   private void clearNextObject()
/*     */   {
/* 231 */     this.nextObject = null;
/* 232 */     this.nextObjectSet = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean setNextObject()
/*     */   {
/* 240 */     if (this.previousObjectSet) {
/* 241 */       clearPreviousObject();
/* 242 */       if (!setNextObject()) {
/* 243 */         return false;
/*     */       }
/* 245 */       clearNextObject();
/*     */     }
/*     */     
/*     */ 
/* 249 */     while (this.iterator.hasNext()) {
/* 250 */       E object = this.iterator.next();
/* 251 */       if (this.predicate.evaluate(object)) {
/* 252 */         this.nextObject = object;
/* 253 */         this.nextObjectSet = true;
/* 254 */         return true;
/*     */       }
/*     */     }
/* 257 */     return false;
/*     */   }
/*     */   
/*     */   private void clearPreviousObject() {
/* 261 */     this.previousObject = null;
/* 262 */     this.previousObjectSet = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean setPreviousObject()
/*     */   {
/* 270 */     if (this.nextObjectSet) {
/* 271 */       clearNextObject();
/* 272 */       if (!setPreviousObject()) {
/* 273 */         return false;
/*     */       }
/* 275 */       clearPreviousObject();
/*     */     }
/*     */     
/*     */ 
/* 279 */     while (this.iterator.hasPrevious()) {
/* 280 */       E object = this.iterator.previous();
/* 281 */       if (this.predicate.evaluate(object)) {
/* 282 */         this.previousObject = object;
/* 283 */         this.previousObjectSet = true;
/* 284 */         return true;
/*     */       }
/*     */     }
/* 287 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/FilterListIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */