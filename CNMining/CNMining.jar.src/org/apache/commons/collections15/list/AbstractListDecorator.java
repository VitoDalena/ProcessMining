/*     */ package org.apache.commons.collections15.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections15.collection.AbstractCollectionDecorator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractListDecorator<E>
/*     */   extends AbstractCollectionDecorator<E>
/*     */   implements List<E>
/*     */ {
/*     */   protected AbstractListDecorator() {}
/*     */   
/*     */   protected AbstractListDecorator(List<E> list)
/*     */   {
/*  52 */     super(list);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected List<E> getList()
/*     */   {
/*  61 */     return (List)getCollection();
/*     */   }
/*     */   
/*     */   public void add(int index, E object)
/*     */   {
/*  66 */     getList().add(index, object);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection<? extends E> coll) {
/*  70 */     return getList().addAll(index, coll);
/*     */   }
/*     */   
/*     */   public E get(int index) {
/*  74 */     return (E)getList().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/*  78 */     return getList().indexOf(object);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/*  82 */     return getList().lastIndexOf(object);
/*     */   }
/*     */   
/*     */   public ListIterator<E> listIterator() {
/*  86 */     return getList().listIterator();
/*     */   }
/*     */   
/*     */   public ListIterator<E> listIterator(int index) {
/*  90 */     return getList().listIterator(index);
/*     */   }
/*     */   
/*     */   public E remove(int index) {
/*  94 */     return (E)getList().remove(index);
/*     */   }
/*     */   
/*     */   public E set(int index, E object) {
/*  98 */     return (E)getList().set(index, object);
/*     */   }
/*     */   
/*     */   public List<E> subList(int fromIndex, int toIndex) {
/* 102 */     return getList().subList(fromIndex, toIndex);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/AbstractListDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */