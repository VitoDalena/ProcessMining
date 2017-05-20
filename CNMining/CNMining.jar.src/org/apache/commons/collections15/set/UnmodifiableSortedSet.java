/*     */ package org.apache.commons.collections15.set;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.SortedSet;
/*     */ import org.apache.commons.collections15.Unmodifiable;
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
/*     */ public final class UnmodifiableSortedSet<E>
/*     */   extends AbstractSortedSetDecorator<E>
/*     */   implements Unmodifiable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -725356885467962424L;
/*     */   
/*     */   public static <E> SortedSet<E> decorate(SortedSet<E> set)
/*     */   {
/*  53 */     if ((set instanceof Unmodifiable)) {
/*  54 */       return set;
/*     */     }
/*  56 */     return new UnmodifiableSortedSet(set);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/*  67 */     out.defaultWriteObject();
/*  68 */     out.writeObject(this.collection);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/*  79 */     in.defaultReadObject();
/*  80 */     this.collection = ((Collection)in.readObject());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnmodifiableSortedSet(SortedSet<E> set)
/*     */   {
/*  91 */     super(set);
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator()
/*     */   {
/*  96 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(E object) {
/* 100 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> coll) {
/* 104 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 108 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 112 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/* 116 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/* 120 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public SortedSet<E> subSet(E fromElement, E toElement)
/*     */   {
/* 125 */     SortedSet<E> sub = getSortedSet().subSet(fromElement, toElement);
/* 126 */     return new UnmodifiableSortedSet(sub);
/*     */   }
/*     */   
/*     */   public SortedSet<E> headSet(E toElement) {
/* 130 */     SortedSet sub = getSortedSet().headSet(toElement);
/* 131 */     return new UnmodifiableSortedSet(sub);
/*     */   }
/*     */   
/*     */   public SortedSet<E> tailSet(E fromElement) {
/* 135 */     SortedSet<E> sub = getSortedSet().tailSet(fromElement);
/* 136 */     return new UnmodifiableSortedSet(sub);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/UnmodifiableSortedSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */