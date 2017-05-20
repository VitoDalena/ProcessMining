/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ import org.apache.commons.collections15.ResettableListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EmptyListIterator<E>
/*    */   extends AbstractEmptyIterator<E>
/*    */   implements ResettableListIterator<E>
/*    */ {
/* 41 */   public static final ResettableListIterator RESETTABLE_INSTANCE = new EmptyListIterator();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 47 */   public static final ListIterator INSTANCE = RESETTABLE_INSTANCE;
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/EmptyListIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */