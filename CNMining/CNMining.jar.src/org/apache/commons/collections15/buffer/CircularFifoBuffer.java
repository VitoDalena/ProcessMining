/*    */ package org.apache.commons.collections15.buffer;
/*    */ 
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CircularFifoBuffer<E>
/*    */   extends BoundedFifoBuffer<E>
/*    */ {
/*    */   private static final long serialVersionUID = -8423413834657610406L;
/*    */   
/*    */   public CircularFifoBuffer()
/*    */   {
/* 59 */     super(32);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public CircularFifoBuffer(int size)
/*    */   {
/* 69 */     super(size);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public CircularFifoBuffer(Collection<E> coll)
/*    */   {
/* 80 */     super(coll);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public boolean add(E element)
/*    */   {
/* 91 */     if (isFull()) {
/* 92 */       remove();
/*    */     }
/* 94 */     return super.add(element);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/buffer/CircularFifoBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */