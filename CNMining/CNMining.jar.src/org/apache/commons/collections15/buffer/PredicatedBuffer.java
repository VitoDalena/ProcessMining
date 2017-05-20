/*    */ package org.apache.commons.collections15.buffer;
/*    */ 
/*    */ import org.apache.commons.collections15.Buffer;
/*    */ import org.apache.commons.collections15.Predicate;
/*    */ import org.apache.commons.collections15.collection.PredicatedCollection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PredicatedBuffer<E>
/*    */   extends PredicatedCollection<E>
/*    */   implements Buffer<E>
/*    */ {
/*    */   private static final long serialVersionUID = 2307609000539943581L;
/*    */   
/*    */   public static <E> Buffer<E> decorate(Buffer<E> buffer, Predicate<? super E> predicate)
/*    */   {
/* 61 */     return new PredicatedBuffer(buffer, predicate);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected PredicatedBuffer(Buffer<E> buffer, Predicate<? super E> predicate)
/*    */   {
/* 77 */     super(buffer, predicate);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected Buffer<E> getBuffer()
/*    */   {
/* 86 */     return (Buffer)getCollection();
/*    */   }
/*    */   
/*    */   public E get()
/*    */   {
/* 91 */     return (E)getBuffer().get();
/*    */   }
/*    */   
/*    */   public E remove() {
/* 95 */     return (E)getBuffer().remove();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/buffer/PredicatedBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */