/*    */ package org.apache.commons.collections15.buffer;
/*    */ 
/*    */ import org.apache.commons.collections15.Buffer;
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
/*    */ public class TypedBuffer<E>
/*    */ {
/*    */   public static <E> Buffer<E> decorate(Buffer<E> buffer, Class type)
/*    */   {
/* 50 */     return new PredicatedBuffer(buffer, InstanceofPredicate.getInstance(type));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/buffer/TypedBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */