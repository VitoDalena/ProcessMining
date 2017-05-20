/*    */ package org.apache.commons.collections15.bag;
/*    */ 
/*    */ import org.apache.commons.collections15.Bag;
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
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public class TypedBag
/*    */ {
/*    */   public static Bag decorate(Bag bag, Class type)
/*    */   {
/* 51 */     return new PredicatedBag(bag, InstanceofPredicate.getInstance(type));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/TypedBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */