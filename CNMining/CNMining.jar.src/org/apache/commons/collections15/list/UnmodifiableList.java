/*     */ package org.apache.commons.collections15.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections15.Unmodifiable;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableIterator;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnmodifiableList<E>
/*     */   extends AbstractSerializableListDecorator<E>
/*     */   implements Unmodifiable
/*     */ {
/*     */   private static final long serialVersionUID = 6595182819922443652L;
/*     */   
/*     */   public static <E> List<E> decorate(List<E> list)
/*     */   {
/*  51 */     if ((list instanceof Unmodifiable)) {
/*  52 */       return list;
/*     */     }
/*  54 */     return new UnmodifiableList(list);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnmodifiableList(List<E> list)
/*     */   {
/*  65 */     super(list);
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator()
/*     */   {
/*  70 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(E object) {
/*  74 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> coll) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/*  86 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/*  90 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/*  94 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public ListIterator<E> listIterator()
/*     */   {
/*  99 */     return UnmodifiableListIterator.decorate(getList().listIterator());
/*     */   }
/*     */   
/*     */   public ListIterator<E> listIterator(int index) {
/* 103 */     return UnmodifiableListIterator.decorate(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public void add(int index, E object) {
/* 107 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection<? extends E> coll) {
/* 111 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public E remove(int index) {
/* 115 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public E set(int index, E object) {
/* 119 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public List<E> subList(int fromIndex, int toIndex) {
/* 123 */     List<E> sub = getList().subList(fromIndex, toIndex);
/* 124 */     return new UnmodifiableList(sub);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/UnmodifiableList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */