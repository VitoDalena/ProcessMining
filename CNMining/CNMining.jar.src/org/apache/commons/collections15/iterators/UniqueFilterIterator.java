/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import org.apache.commons.collections15.functors.UniquePredicate;
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
/*    */ public class UniqueFilterIterator<E>
/*    */   extends FilterIterator<E>
/*    */ {
/*    */   public UniqueFilterIterator(Iterator<E> iterator)
/*    */   {
/* 42 */     super(iterator, UniquePredicate.getInstance());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/UniqueFilterIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */