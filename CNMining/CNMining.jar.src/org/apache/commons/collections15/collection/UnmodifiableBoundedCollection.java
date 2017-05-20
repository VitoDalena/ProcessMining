/*     */ package org.apache.commons.collections15.collection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections15.BoundedCollection;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnmodifiableBoundedCollection<E>
/*     */   extends AbstractSerializableCollectionDecorator<E>
/*     */   implements BoundedCollection<E>
/*     */ {
/*     */   private static final long serialVersionUID = -7112672385450340330L;
/*     */   
/*     */   public static <E> BoundedCollection<E> decorate(BoundedCollection<E> coll)
/*     */   {
/*  56 */     return new UnmodifiableBoundedCollection(coll);
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
/*     */   public static <E> BoundedCollection<E> decorateUsing(Collection<E> coll)
/*     */   {
/*  70 */     if (coll == null) {
/*  71 */       throw new IllegalArgumentException("The collection must not be null");
/*     */     }
/*     */     
/*     */ 
/*  75 */     for (int i = 0; i < 1000; i++) {
/*  76 */       if ((coll instanceof BoundedCollection))
/*     */         break;
/*  78 */       if ((coll instanceof AbstractCollectionDecorator)) {
/*  79 */         coll = ((AbstractCollectionDecorator)coll).collection;
/*  80 */       } else { if (!(coll instanceof SynchronizedCollection)) break;
/*  81 */         coll = ((SynchronizedCollection)coll).collection;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  87 */     if (!(coll instanceof BoundedCollection)) {
/*  88 */       throw new IllegalArgumentException("The collection is not a bounded collection");
/*     */     }
/*  90 */     return new UnmodifiableBoundedCollection((BoundedCollection)coll);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnmodifiableBoundedCollection(BoundedCollection<E> coll)
/*     */   {
/* 100 */     super(coll);
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator()
/*     */   {
/* 105 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(E object) {
/* 109 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> coll) {
/* 113 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 117 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 121 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/* 125 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/* 129 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean isFull()
/*     */   {
/* 134 */     return ((BoundedCollection)this.collection).isFull();
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 138 */     return ((BoundedCollection)this.collection).maxSize();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/collection/UnmodifiableBoundedCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */