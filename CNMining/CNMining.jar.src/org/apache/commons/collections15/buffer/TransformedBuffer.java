/*    */ package org.apache.commons.collections15.buffer;
/*    */ 
/*    */ import org.apache.commons.collections15.Buffer;
/*    */ import org.apache.commons.collections15.Transformer;
/*    */ import org.apache.commons.collections15.collection.TransformedCollection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TransformedBuffer<I, O>
/*    */   extends TransformedCollection<I, O>
/*    */   implements Buffer
/*    */ {
/*    */   private static final long serialVersionUID = -7901091318986132033L;
/*    */   
/*    */   public static <I, O> Buffer<O> decorate(Buffer<I> buffer, Transformer<? super I, ? extends O> transformer)
/*    */   {
/* 58 */     return new TransformedBuffer(buffer, transformer);
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
/*    */   protected TransformedBuffer(Buffer<I> buffer, Transformer<? super I, ? extends O> transformer)
/*    */   {
/* 73 */     super(buffer, transformer);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected Buffer<O> getBuffer()
/*    */   {
/* 82 */     return (Buffer)this.collection;
/*    */   }
/*    */   
/*    */   public Object get()
/*    */   {
/* 87 */     return getBuffer().get();
/*    */   }
/*    */   
/*    */   public Object remove() {
/* 91 */     return getBuffer().remove();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/buffer/TransformedBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */