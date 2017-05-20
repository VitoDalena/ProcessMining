/*    */ package org.apache.commons.collections15.set;
/*    */ 
/*    */ import java.util.SortedSet;
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
/*    */ public class TypedSortedSet
/*    */ {
/*    */   public static SortedSet decorate(SortedSet set, Class type)
/*    */   {
/* 50 */     return new PredicatedSortedSet(set, InstanceofPredicate.getInstance(type));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/TypedSortedSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */