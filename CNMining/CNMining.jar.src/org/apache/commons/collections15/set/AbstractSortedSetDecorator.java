/*    */ package org.apache.commons.collections15.set;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.Set;
/*    */ import java.util.SortedSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSortedSetDecorator<E>
/*    */   extends AbstractSetDecorator<E>
/*    */   implements SortedSet<E>
/*    */ {
/*    */   protected AbstractSortedSetDecorator() {}
/*    */   
/*    */   protected AbstractSortedSetDecorator(Set<E> set)
/*    */   {
/* 50 */     super(set);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SortedSet<E> getSortedSet()
/*    */   {
/* 59 */     return (SortedSet)getCollection();
/*    */   }
/*    */   
/*    */   public SortedSet<E> subSet(E fromElement, E toElement)
/*    */   {
/* 64 */     return getSortedSet().subSet(fromElement, toElement);
/*    */   }
/*    */   
/*    */   public SortedSet<E> headSet(E toElement) {
/* 68 */     return getSortedSet().headSet(toElement);
/*    */   }
/*    */   
/*    */   public SortedSet<E> tailSet(E fromElement) {
/* 72 */     return getSortedSet().tailSet(fromElement);
/*    */   }
/*    */   
/*    */   public E first() {
/* 76 */     return (E)getSortedSet().first();
/*    */   }
/*    */   
/*    */   public E last() {
/* 80 */     return (E)getSortedSet().last();
/*    */   }
/*    */   
/*    */   public Comparator<? super E> comparator() {
/* 84 */     return getSortedSet().comparator();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/AbstractSortedSetDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */