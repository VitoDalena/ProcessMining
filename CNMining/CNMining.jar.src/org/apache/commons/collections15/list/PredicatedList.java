/*     */ package org.apache.commons.collections15.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections15.Predicate;
/*     */ import org.apache.commons.collections15.collection.PredicatedCollection;
/*     */ import org.apache.commons.collections15.iterators.AbstractListIteratorDecorator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PredicatedList<E>
/*     */   extends PredicatedCollection<E>
/*     */   implements List<E>
/*     */ {
/*     */   private static final long serialVersionUID = -5722039223898659102L;
/*     */   
/*     */   public static <E> List<E> decorate(List<E> list, Predicate<? super E> predicate)
/*     */   {
/*  65 */     return new PredicatedList(list, predicate);
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
/*     */   protected PredicatedList(List<E> list, Predicate<? super E> predicate)
/*     */   {
/*  81 */     super(list, predicate);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected List<E> getList()
/*     */   {
/*  90 */     return (List)getCollection();
/*     */   }
/*     */   
/*     */   public E get(int index)
/*     */   {
/*  95 */     return (E)getList().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/*  99 */     return getList().indexOf(object);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/* 103 */     return getList().lastIndexOf(object);
/*     */   }
/*     */   
/*     */   public E remove(int index) {
/* 107 */     return (E)getList().remove(index);
/*     */   }
/*     */   
/*     */   public void add(int index, E object)
/*     */   {
/* 112 */     validate(object);
/* 113 */     getList().add(index, object);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection<? extends E> coll) {
/* 117 */     for (Iterator<? extends E> it = coll.iterator(); it.hasNext();) {
/* 118 */       validate(it.next());
/*     */     }
/* 120 */     return getList().addAll(index, coll);
/*     */   }
/*     */   
/*     */   public ListIterator<E> listIterator() {
/* 124 */     return listIterator(0);
/*     */   }
/*     */   
/*     */   public ListIterator<E> listIterator(int i) {
/* 128 */     return new PredicatedListIterator(getList().listIterator(i));
/*     */   }
/*     */   
/*     */   public E set(int index, E object) {
/* 132 */     validate(object);
/* 133 */     return (E)getList().set(index, object);
/*     */   }
/*     */   
/*     */   public List<E> subList(int fromIndex, int toIndex) {
/* 137 */     List<E> sub = getList().subList(fromIndex, toIndex);
/* 138 */     return new PredicatedList(sub, this.predicate);
/*     */   }
/*     */   
/*     */ 
/*     */   protected class PredicatedListIterator
/*     */     extends AbstractListIteratorDecorator<E>
/*     */   {
/*     */     protected PredicatedListIterator()
/*     */     {
/* 147 */       super();
/*     */     }
/*     */     
/*     */     public void add(E object) {
/* 151 */       PredicatedList.this.validate(object);
/* 152 */       this.iterator.add(object);
/*     */     }
/*     */     
/*     */     public void set(E object) {
/* 156 */       PredicatedList.this.validate(object);
/* 157 */       this.iterator.set(object);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/PredicatedList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */