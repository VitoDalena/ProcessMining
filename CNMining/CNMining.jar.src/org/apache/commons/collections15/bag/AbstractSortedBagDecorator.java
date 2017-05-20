/*    */ package org.apache.commons.collections15.bag;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.apache.commons.collections15.SortedBag;
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
/*    */ 
/*    */ public abstract class AbstractSortedBagDecorator<E>
/*    */   extends AbstractBagDecorator<E>
/*    */   implements SortedBag<E>
/*    */ {
/*    */   protected AbstractSortedBagDecorator() {}
/*    */   
/*    */   protected AbstractSortedBagDecorator(SortedBag<E> bag)
/*    */   {
/* 50 */     super(bag);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SortedBag<E> getSortedBag()
/*    */   {
/* 59 */     return (SortedBag)getCollection();
/*    */   }
/*    */   
/*    */   public E first()
/*    */   {
/* 64 */     return (E)getSortedBag().first();
/*    */   }
/*    */   
/*    */   public E last() {
/* 68 */     return (E)getSortedBag().last();
/*    */   }
/*    */   
/*    */   public Comparator<? super E> comparator() {
/* 72 */     return getSortedBag().comparator();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/AbstractSortedBagDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */