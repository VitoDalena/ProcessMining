/*     */ package org.apache.commons.collections15.bag;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Bag;
/*     */ import org.apache.commons.collections15.Unmodifiable;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableIterator;
/*     */ import org.apache.commons.collections15.set.UnmodifiableSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnmodifiableBag<E>
/*     */   extends AbstractBagDecorator<E>
/*     */   implements Unmodifiable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1873799975157099624L;
/*     */   
/*     */   public static <E> Bag<E> decorate(Bag<E> bag)
/*     */   {
/*  58 */     if ((bag instanceof Unmodifiable)) {
/*  59 */       return bag;
/*     */     }
/*  61 */     return new UnmodifiableBag(bag);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnmodifiableBag(Bag<E> bag)
/*     */   {
/*  72 */     super(bag);
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
/*  83 */     out.defaultWriteObject();
/*  84 */     out.writeObject(this.collection);
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
/*  95 */     in.defaultReadObject();
/*  96 */     this.collection = ((Collection)in.readObject());
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator()
/*     */   {
/* 101 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(E object) {
/* 105 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> coll) {
/* 109 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 113 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 117 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/* 121 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/* 125 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean add(E object, int count)
/*     */   {
/* 130 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(E object, int count) {
/* 134 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set<E> uniqueSet() {
/* 138 */     Set<E> set = getBag().uniqueSet();
/* 139 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/UnmodifiableBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */