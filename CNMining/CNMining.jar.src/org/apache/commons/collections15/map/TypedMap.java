/*    */ package org.apache.commons.collections15.map;
/*    */ 
/*    */ import java.util.Map;
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
/*    */ public class TypedMap
/*    */ {
/*    */   public static Map decorate(Map map, Class keyType, Class valueType)
/*    */   {
/* 54 */     return new PredicatedMap(map, InstanceofPredicate.getInstance(keyType), InstanceofPredicate.getInstance(valueType));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/TypedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */