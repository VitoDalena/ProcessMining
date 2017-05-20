/*    */ package org.apache.commons.collections15.set;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.apache.commons.collections15.collection.AbstractCollectionDecorator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSetDecorator<E>
/*    */   extends AbstractCollectionDecorator<E>
/*    */   implements Set<E>
/*    */ {
/*    */   protected AbstractSetDecorator() {}
/*    */   
/*    */   protected AbstractSetDecorator(Set<E> set)
/*    */   {
/* 50 */     super(set);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected Set<E> getSet()
/*    */   {
/* 59 */     return (Set)getCollection();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/set/AbstractSetDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */