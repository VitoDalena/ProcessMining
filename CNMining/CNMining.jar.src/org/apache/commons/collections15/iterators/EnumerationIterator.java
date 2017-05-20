/*     */ package org.apache.commons.collections15.iterators;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnumerationIterator<E>
/*     */   implements Iterator<E>
/*     */ {
/*     */   private Collection<E> collection;
/*     */   private Enumeration<E> enumeration;
/*     */   private E last;
/*     */   
/*     */   public EnumerationIterator()
/*     */   {
/*  54 */     this(null, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EnumerationIterator(Enumeration<E> enumeration)
/*     */   {
/*  64 */     this(enumeration, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EnumerationIterator(Enumeration<E> enumeration, Collection<E> collection)
/*     */   {
/*  76 */     this.enumeration = enumeration;
/*  77 */     this.collection = collection;
/*  78 */     this.last = null;
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
/*  90 */     return this.enumeration.hasMoreElements();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public E next()
/*     */   {
/* 100 */     this.last = this.enumeration.nextElement();
/* 101 */     return (E)this.last;
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
/*     */   public void remove()
/*     */   {
/* 115 */     if (this.collection != null) {
/* 116 */       if (this.last != null) {
/* 117 */         this.collection.remove(this.last);
/*     */       } else {
/* 119 */         throw new IllegalStateException("next() must have been called for remove() to function");
/*     */       }
/*     */     } else {
/* 122 */       throw new UnsupportedOperationException("No Collection associated with this Iterator");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Enumeration<E> getEnumeration()
/*     */   {
/* 134 */     return this.enumeration;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEnumeration(Enumeration<E> enumeration)
/*     */   {
/* 143 */     this.enumeration = enumeration;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/EnumerationIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */