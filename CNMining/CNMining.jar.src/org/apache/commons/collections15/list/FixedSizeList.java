/*     */ package org.apache.commons.collections15.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections15.BoundedCollection;
/*     */ import org.apache.commons.collections15.iterators.AbstractListIteratorDecorator;
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
/*     */ public class FixedSizeList<E>
/*     */   extends AbstractSerializableListDecorator<E>
/*     */   implements BoundedCollection<E>
/*     */ {
/*     */   private static final long serialVersionUID = -2218010673611160319L;
/*     */   
/*     */   public static <E> List<E> decorate(List<E> list)
/*     */   {
/*  55 */     return new FixedSizeList(list);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected FixedSizeList(List<E> list)
/*     */   {
/*  66 */     super(list);
/*     */   }
/*     */   
/*     */   public boolean add(E object)
/*     */   {
/*  71 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public void add(int index, E object) {
/*  75 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection<? extends E> coll) {
/*  79 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection<? extends E> coll) {
/*  83 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public void clear() {
/*  87 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public E get(int index) {
/*  91 */     return (E)getList().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/*  95 */     return getList().indexOf(object);
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator() {
/*  99 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/* 103 */     return getList().lastIndexOf(object);
/*     */   }
/*     */   
/*     */   public ListIterator<E> listIterator() {
/* 107 */     return new FixedSizeListIterator(getList().listIterator(0));
/*     */   }
/*     */   
/*     */   public ListIterator<E> listIterator(int index) {
/* 111 */     return new FixedSizeListIterator(getList().listIterator(index));
/*     */   }
/*     */   
/*     */   public E remove(int index) {
/* 115 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 119 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection<?> coll) {
/* 123 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection<?> coll) {
/* 127 */     throw new UnsupportedOperationException("List is fixed size");
/*     */   }
/*     */   
/*     */   public E set(int index, E object) {
/* 131 */     return (E)getList().set(index, object);
/*     */   }
/*     */   
/*     */   public List<E> subList(int fromIndex, int toIndex) {
/* 135 */     List<E> sub = getList().subList(fromIndex, toIndex);
/* 136 */     return new FixedSizeList(sub);
/*     */   }
/*     */   
/*     */   static class FixedSizeListIterator<E>
/*     */     extends AbstractListIteratorDecorator<E>
/*     */   {
/*     */     protected FixedSizeListIterator(ListIterator<E> iterator)
/*     */     {
/* 144 */       super();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 148 */       throw new UnsupportedOperationException("List is fixed size");
/*     */     }
/*     */     
/*     */     public void add(E object) {
/* 152 */       throw new UnsupportedOperationException("List is fixed size");
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/* 157 */     return true;
/*     */   }
/*     */   
/*     */   public int maxSize() {
/* 161 */     return size();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/FixedSizeList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */