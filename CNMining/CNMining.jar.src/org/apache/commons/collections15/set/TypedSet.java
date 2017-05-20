/*    */ package org.apache.commons.collections15.set;
/*    */ 
/*    */ import java.util.Set;
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
/*    */ public class TypedSet
/*    */ {
/*    */   public static Set decorate(Set set, Class type)
/*    */   {
/* 50 */     return new PredicatedSet(set, InstanceofPredicate.getInstance(type));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/TypedSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */