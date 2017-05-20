/*    */ package org.apache.commons.collections15.buffer;
/*    */ 
/*    */ import org.apache.commons.collections15.Buffer;
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
/*    */ public abstract class AbstractBufferDecorator<E>
/*    */   extends AbstractCollectionDecorator<E>
/*    */   implements Buffer<E>
/*    */ {
/*    */   protected AbstractBufferDecorator() {}
/*    */   
/*    */   protected AbstractBufferDecorator(Buffer<E> buffer)
/*    */   {
/* 49 */     super(buffer);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected Buffer<E> getBuffer()
/*    */   {
/* 58 */     return (Buffer)getCollection();
/*    */   }
/*    */   
/*    */   public E get()
/*    */   {
/* 63 */     return (E)getBuffer().get();
/*    */   }
/*    */   
/*    */   public E remove() {
/* 67 */     return (E)getBuffer().remove();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/buffer/AbstractBufferDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */