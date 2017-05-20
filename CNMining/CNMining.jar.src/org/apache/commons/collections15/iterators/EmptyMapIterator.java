/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import org.apache.commons.collections15.MapIterator;
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
/*    */ public class EmptyMapIterator
/*    */   extends AbstractEmptyIterator
/*    */   implements MapIterator, ResettableIterator
/*    */ {
/* 36 */   public static final MapIterator INSTANCE = new EmptyMapIterator();
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/EmptyMapIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */