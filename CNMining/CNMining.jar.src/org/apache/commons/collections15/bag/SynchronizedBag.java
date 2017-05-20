/*     */ package org.apache.commons.collections15.bag;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.Bag;
/*     */ import org.apache.commons.collections15.collection.SynchronizedCollection;
/*     */ import org.apache.commons.collections15.set.SynchronizedSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SynchronizedBag<E>
/*     */   extends SynchronizedCollection<E>
/*     */   implements Bag<E>
/*     */ {
/*     */   private static final long serialVersionUID = 8084674570753837109L;
/*     */   
/*     */   public static <E> Bag<E> decorate(Bag<E> bag)
/*     */   {
/*  53 */     return new SynchronizedBag(bag);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SynchronizedBag(Bag<E> bag)
/*     */   {
/*  64 */     super(bag);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SynchronizedBag(Bag<E> bag, Object lock)
/*     */   {
/*  75 */     super(bag, lock);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Bag<E> getBag()
/*     */   {
/*  84 */     return (Bag)this.collection;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean add(E object, int count)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 7	org/apache/commons/collections15/bag/SynchronizedBag:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_3
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual 8	org/apache/commons/collections15/bag/SynchronizedBag:getBag	()Lorg/apache/commons/collections15/Bag;
/*     */     //   11: aload_1
/*     */     //   12: iload_2
/*     */     //   13: invokeinterface 9 3 0
/*     */     //   18: aload_3
/*     */     //   19: monitorexit
/*     */     //   20: ireturn
/*     */     //   21: astore 4
/*     */     //   23: aload_3
/*     */     //   24: monitorexit
/*     */     //   25: aload 4
/*     */     //   27: athrow
/*     */     // Line number table:
/*     */     //   Java source line #89	-> byte code offset #0
/*     */     //   Java source line #90	-> byte code offset #7
/*     */     //   Java source line #91	-> byte code offset #21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	28	0	this	SynchronizedBag<E>
/*     */     //   0	28	1	object	E
/*     */     //   0	28	2	count	int
/*     */     //   5	19	3	Ljava/lang/Object;	Object
/*     */     //   21	5	4	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	20	21	finally
/*     */     //   21	25	21	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean remove(E object, int count)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 7	org/apache/commons/collections15/bag/SynchronizedBag:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_3
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual 8	org/apache/commons/collections15/bag/SynchronizedBag:getBag	()Lorg/apache/commons/collections15/Bag;
/*     */     //   11: aload_1
/*     */     //   12: iload_2
/*     */     //   13: invokeinterface 10 3 0
/*     */     //   18: aload_3
/*     */     //   19: monitorexit
/*     */     //   20: ireturn
/*     */     //   21: astore 4
/*     */     //   23: aload_3
/*     */     //   24: monitorexit
/*     */     //   25: aload 4
/*     */     //   27: athrow
/*     */     // Line number table:
/*     */     //   Java source line #95	-> byte code offset #0
/*     */     //   Java source line #96	-> byte code offset #7
/*     */     //   Java source line #97	-> byte code offset #21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	28	0	this	SynchronizedBag<E>
/*     */     //   0	28	1	object	E
/*     */     //   0	28	2	count	int
/*     */     //   5	19	3	Ljava/lang/Object;	Object
/*     */     //   21	5	4	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	20	21	finally
/*     */     //   21	25	21	finally
/*     */   }
/*     */   
/*     */   public Set<E> uniqueSet()
/*     */   {
/* 101 */     synchronized (this.lock) {
/* 102 */       Set<E> set = getBag().uniqueSet();
/* 103 */       return new SynchronizedBagSet(set, this.lock);
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public int getCount(E object)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 7	org/apache/commons/collections15/bag/SynchronizedBag:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual 8	org/apache/commons/collections15/bag/SynchronizedBag:getBag	()Lorg/apache/commons/collections15/Bag;
/*     */     //   11: aload_1
/*     */     //   12: invokeinterface 14 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: ireturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #108	-> byte code offset #0
/*     */     //   Java source line #109	-> byte code offset #7
/*     */     //   Java source line #110	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedBag<E>
/*     */     //   0	25	1	object	E
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   class SynchronizedBagSet
/*     */     extends SynchronizedSet<E>
/*     */   {
/*     */     SynchronizedBagSet(Object set)
/*     */     {
/* 125 */       super(lock);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bag/SynchronizedBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */