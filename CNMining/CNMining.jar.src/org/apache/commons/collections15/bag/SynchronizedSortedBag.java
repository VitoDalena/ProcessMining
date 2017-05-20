/*    */ package org.apache.commons.collections15.bag;
/*    */ 
/*    */ import org.apache.commons.collections15.Bag;
/*    */ import org.apache.commons.collections15.SortedBag;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SynchronizedSortedBag<E>
/*    */   extends SynchronizedBag<E>
/*    */   implements SortedBag<E>
/*    */ {
/*    */   private static final long serialVersionUID = 722374056718497858L;
/*    */   
/*    */   public static <E> SortedBag<E> decorate(SortedBag<E> bag)
/*    */   {
/* 52 */     return new SynchronizedSortedBag(bag);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SynchronizedSortedBag(SortedBag<E> bag)
/*    */   {
/* 63 */     super(bag);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SynchronizedSortedBag(Bag<E> bag, Object lock)
/*    */   {
/* 74 */     super(bag, lock);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SortedBag<E> getSortedBag()
/*    */   {
/* 83 */     return (SortedBag)this.collection;
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public synchronized E first()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 7	org/apache/commons/collections15/bag/SynchronizedSortedBag:lock	Ljava/lang/Object;
/*    */     //   4: dup
/*    */     //   5: astore_1
/*    */     //   6: monitorenter
/*    */     //   7: aload_0
/*    */     //   8: invokevirtual 8	org/apache/commons/collections15/bag/SynchronizedSortedBag:getSortedBag	()Lorg/apache/commons/collections15/SortedBag;
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
/*    */     //   Java source line #88	-> byte code offset #0
/*    */     //   Java source line #89	-> byte code offset #7
/*    */     //   Java source line #90	-> byte code offset #19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	24	0	this	SynchronizedSortedBag<E>
/*    */     //   5	16	1	Ljava/lang/Object;	Object
/*    */     //   19	4	2	localObject1	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   7	18	19	finally
/*    */     //   19	22	19	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public synchronized E last()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 7	org/apache/commons/collections15/bag/SynchronizedSortedBag:lock	Ljava/lang/Object;
/*    */     //   4: dup
/*    */     //   5: astore_1
/*    */     //   6: monitorenter
/*    */     //   7: aload_0
/*    */     //   8: invokevirtual 8	org/apache/commons/collections15/bag/SynchronizedSortedBag:getSortedBag	()Lorg/apache/commons/collections15/SortedBag;
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
/*    */     //   Java source line #94	-> byte code offset #0
/*    */     //   Java source line #95	-> byte code offset #7
/*    */     //   Java source line #96	-> byte code offset #19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	24	0	this	SynchronizedSortedBag<E>
/*    */     //   5	16	1	Ljava/lang/Object;	Object
/*    */     //   19	4	2	localObject1	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   7	18	19	finally
/*    */     //   19	22	19	finally
/*    */   }
/*    */   
/*    */   /* Error */
/*    */   public synchronized java.util.Comparator<? super E> comparator()
/*    */   {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: getfield 7	org/apache/commons/collections15/bag/SynchronizedSortedBag:lock	Ljava/lang/Object;
/*    */     //   4: dup
/*    */     //   5: astore_1
/*    */     //   6: monitorenter
/*    */     //   7: aload_0
/*    */     //   8: invokevirtual 8	org/apache/commons/collections15/bag/SynchronizedSortedBag:getSortedBag	()Lorg/apache/commons/collections15/SortedBag;
/*    */     //   11: invokeinterface 11 1 0
/*    */     //   16: aload_1
/*    */     //   17: monitorexit
/*    */     //   18: areturn
/*    */     //   19: astore_2
/*    */     //   20: aload_1
/*    */     //   21: monitorexit
/*    */     //   22: aload_2
/*    */     //   23: athrow
/*    */     // Line number table:
/*    */     //   Java source line #100	-> byte code offset #0
/*    */     //   Java source line #101	-> byte code offset #7
/*    */     //   Java source line #102	-> byte code offset #19
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	signature
/*    */     //   0	24	0	this	SynchronizedSortedBag<E>
/*    */     //   5	16	1	Ljava/lang/Object;	Object
/*    */     //   19	4	2	localObject1	Object
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   7	18	19	finally
/*    */     //   19	22	19	finally
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/SynchronizedSortedBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */