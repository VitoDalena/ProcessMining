/*    */ package org.apache.commons.collections15.bag;
/*    */ 
/*    */ import org.apache.commons.collections15.SortedBag;
/*    */ import org.apache.commons.collections15.functors.InstanceofPredicate;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TypedSortedBag<E>
/*    */ {
/*    */   public static <E> SortedBag<E> decorate(SortedBag<E> bag, Class<E> type)
/*    */   {
/* 50 */     return new PredicatedSortedBag(bag, InstanceofPredicate.getInstance(type));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/TypedSortedBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */