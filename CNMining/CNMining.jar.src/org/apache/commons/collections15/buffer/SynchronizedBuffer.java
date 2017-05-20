/*    */ package org.apache.commons.collections15.buffer;
/*    */ 
/*    */ import org.apache.commons.collections15.Buffer;
/*    */ import org.apache.commons.collections15.collection.SynchronizedCollection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SynchronizedBuffer<E>
/*    */   extends SynchronizedCollection<E>
/*    */   implements Buffer<E>
/*    */ {
/*    */   private static final long serialVersionUID = -6859936183953626253L;
/*    */   
/*    */   public static <E> Buffer<E> decorate(Buffer<E> buffer)
/*    */   {
/* 49 */     return new SynchronizedBuffer(buffer);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SynchronizedBuffer(Buffer<E> buffer)
/*    */   {
/* 60 */     super(buffer);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SynchronizedBuffer(Buffer<E> buffer, Object lock)
/*    */   {
/* 71 */     super(buffer, lock);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected Buffer<E> getBuffer()
/*    */   {
/* 80 */     return (Buffer)this.collection;
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public E get()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 7	org/apache/commons/collections15/buffer/SynchronizedBuffer:lock	Ljava/lang/Object;
/*    */     //   4: dup
/*    */     //   5: astore_1
/*    */     //   6: monitorenter
/*    */     //   7: aload_0
/*    */     //   8: invokevirtual 8	org/apache/commons/collections15/buffer/SynchronizedBuffer:getBuffer	()Lorg/apache/commons/collections15/Buffer;
/*    */     //   11: invokeinterface 9 1 0
/*    */     //   16: aload_1
/*    */     //   17: monitorexit
/*    */     //   18: areturn
/*    */     //   19: astore_2
/*    */     //   20: aload_1
/*    */     //   21: monitorexit
/*    */     //   22: aload_2
/*    */     //   23: athrow
/*    */     // Line number table:
/*    */     //   Java source line #85	-> byte code offset #0
/*    */     //   Java source line #86	-> byte code offset #7
/*    */     //   Java source line #87	-> byte code offset #19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	24	0	this	SynchronizedBuffer<E>
/*    */     //   5	16	1	Ljava/lang/Object;	Object
/*    */     //   19	4	2	localObject1	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   7	18	19	finally
/*    */     //   19	22	19	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public E remove()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 7	org/apache/commons/collections15/buffer/SynchronizedBuffer:lock	Ljava/lang/Object;
/*    */     //   4: dup
/*    */     //   5: astore_1
/*    */     //   6: monitorenter
/*    */     //   7: aload_0
/*    */     //   8: invokevirtual 8	org/apache/commons/collections15/buffer/SynchronizedBuffer:getBuffer	()Lorg/apache/commons/collections15/Buffer;
/*    */     //   11: invokeinterface 10 1 0
/*    */     //   16: aload_1
/*    */     //   17: monitorexit
/*    */     //   18: areturn
/*    */     //   19: astore_2
/*    */     //   20: aload_1
/*    */     //   21: monitorexit
/*    */     //   22: aload_2
/*    */     //   23: athrow
/*    */     // Line number table:
/*    */     //   Java source line #91	-> byte code offset #0
/*    */     //   Java source line #92	-> byte code offset #7
/*    */     //   Java source line #93	-> byte code offset #19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	24	0	this	SynchronizedBuffer<E>
/*    */     //   5	16	1	Ljava/lang/Object;	Object
/*    */     //   19	4	2	localObject1	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   7	18	19	finally
/*    */     //   19	22	19	finally
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/buffer/SynchronizedBuffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */