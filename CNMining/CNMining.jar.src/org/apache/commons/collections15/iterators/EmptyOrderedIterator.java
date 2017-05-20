/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import org.apache.commons.collections15.OrderedIterator;
/*    */ import org.apache.commons.collections15.ResettableIterator;
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
/*    */ public class EmptyOrderedIterator<E>
/*    */   extends AbstractEmptyIterator<E>
/*    */   implements OrderedIterator<E>, ResettableIterator<E>
/*    */ {
/* 36 */   public static final OrderedIterator INSTANCE = new EmptyOrderedIterator();
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/EmptyOrderedIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */