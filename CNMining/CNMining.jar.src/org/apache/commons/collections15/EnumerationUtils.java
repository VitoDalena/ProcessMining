/*    */ package org.apache.commons.collections15;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.List;
/*    */ import org.apache.commons.collections15.iterators.EnumerationIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnumerationUtils
/*    */ {
/*    */   public static <E> List<E> toList(Enumeration<E> enumeration)
/*    */   {
/* 50 */     return IteratorUtils.toList(new EnumerationIterator(enumeration));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/EnumerationUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */