/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import java.util.Iterator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EmptyIterator<E>
/*    */   extends AbstractEmptyIterator<E>
/*    */   implements ResettableIterator<E>
/*    */ {
/* 41 */   public static final ResettableIterator RESETTABLE_INSTANCE = new EmptyIterator();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 47 */   public static final Iterator INSTANCE = RESETTABLE_INSTANCE;
/*    */   
/*    */   public static <T> Iterator<T> getInstance() {
/* 50 */     return INSTANCE;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/EmptyIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */