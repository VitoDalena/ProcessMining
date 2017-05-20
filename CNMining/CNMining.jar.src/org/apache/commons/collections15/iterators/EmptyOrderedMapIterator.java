/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import org.apache.commons.collections15.OrderedMapIterator;
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
/*    */ public class EmptyOrderedMapIterator
/*    */   extends AbstractEmptyIterator
/*    */   implements OrderedMapIterator, ResettableIterator
/*    */ {
/* 36 */   public static final OrderedMapIterator INSTANCE = new EmptyOrderedMapIterator();
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/EmptyOrderedMapIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */