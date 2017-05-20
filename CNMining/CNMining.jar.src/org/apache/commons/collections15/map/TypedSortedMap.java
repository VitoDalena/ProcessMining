/*    */ package org.apache.commons.collections15.map;
/*    */ 
/*    */ import java.util.SortedMap;
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
/*    */ 
/*    */ /**
/*    */  * @deprecated
/*    */  */
/*    */ public class TypedSortedMap
/*    */ {
/*    */   public static SortedMap decorate(SortedMap map, Class keyType, Class valueType)
/*    */   {
/* 54 */     return new PredicatedSortedMap(map, InstanceofPredicate.getInstance(keyType), InstanceofPredicate.getInstance(valueType));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/TypedSortedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */