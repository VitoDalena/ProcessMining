/*     */ package org.apache.commons.collections15.list;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections15.collection.SynchronizedCollection;
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
/*     */ public class SynchronizedList<E>
/*     */   extends SynchronizedCollection<E>
/*     */   implements List<E>
/*     */ {
/*     */   private static final long serialVersionUID = -1403835447328619437L;
/*     */   
/*     */   public static <E> List<E> decorate(List<E> list)
/*     */   {
/*  51 */     return new SynchronizedList(list);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SynchronizedList(List<E> list)
/*     */   {
/*  62 */     super(list);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SynchronizedList(List<E> list, Object lock)
/*     */   {
/*  73 */     super(list, lock);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected List<E> getList()
/*     */   {
/*  82 */     return (List)this.collection;
/*     */   }
/*     */   
/*     */   public void add(int index, E object)
/*     */   {
/*  87 */     synchronized (this.lock) {
/*  88 */       getList().add(index, object);
/*     */     }
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public boolean addAll(int index, java.util.Collection<? extends E> coll)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 7	org/apache/commons/collections15/list/SynchronizedList:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_3
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual 8	org/apache/commons/collections15/list/SynchronizedList:getList	()Ljava/util/List;
/*     */     //   11: iload_1
/*     */     //   12: aload_2
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
/*     */     //   Java source line #93	-> byte code offset #0
/*     */     //   Java source line #94	-> byte code offset #7
/*     */     //   Java source line #95	-> byte code offset #21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	28	0	this	SynchronizedList<E>
/*     */     //   0	28	1	index	int
/*     */     //   0	28	2	coll	java.util.Collection<? extends E>
/*     */     //   5	19	3	Ljava/lang/Object;	Object
/*     */     //   21	5	4	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	20	21	finally
/*     */     //   21	25	21	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public E get(int index)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 7	org/apache/commons/collections15/list/SynchronizedList:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual 8	org/apache/commons/collections15/list/SynchronizedList:getList	()Ljava/util/List;
/*     */     //   11: iload_1
/*     */     //   12: invokeinterface 11 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: areturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #99	-> byte code offset #0
/*     */     //   Java source line #100	-> byte code offset #7
/*     */     //   Java source line #101	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedList<E>
/*     */     //   0	25	1	index	int
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public int indexOf(Object object)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 7	org/apache/commons/collections15/list/SynchronizedList:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual 8	org/apache/commons/collections15/list/SynchronizedList:getList	()Ljava/util/List;
/*     */     //   11: aload_1
/*     */     //   12: invokeinterface 12 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: ireturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #105	-> byte code offset #0
/*     */     //   Java source line #106	-> byte code offset #7
/*     */     //   Java source line #107	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedList<E>
/*     */     //   0	25	1	object	Object
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public int lastIndexOf(Object object)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 7	org/apache/commons/collections15/list/SynchronizedList:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual 8	org/apache/commons/collections15/list/SynchronizedList:getList	()Ljava/util/List;
/*     */     //   11: aload_1
/*     */     //   12: invokeinterface 13 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: ireturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #111	-> byte code offset #0
/*     */     //   Java source line #112	-> byte code offset #7
/*     */     //   Java source line #113	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedList<E>
/*     */     //   0	25	1	object	Object
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   public ListIterator<E> listIterator()
/*     */   {
/* 127 */     return getList().listIterator();
/*     */   }
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
/*     */   public ListIterator<E> listIterator(int index)
/*     */   {
/* 141 */     return getList().listIterator(index);
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public E remove(int index)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 7	org/apache/commons/collections15/list/SynchronizedList:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_2
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual 8	org/apache/commons/collections15/list/SynchronizedList:getList	()Ljava/util/List;
/*     */     //   11: iload_1
/*     */     //   12: invokeinterface 16 2 0
/*     */     //   17: aload_2
/*     */     //   18: monitorexit
/*     */     //   19: areturn
/*     */     //   20: astore_3
/*     */     //   21: aload_2
/*     */     //   22: monitorexit
/*     */     //   23: aload_3
/*     */     //   24: athrow
/*     */     // Line number table:
/*     */     //   Java source line #145	-> byte code offset #0
/*     */     //   Java source line #146	-> byte code offset #7
/*     */     //   Java source line #147	-> byte code offset #20
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	25	0	this	SynchronizedList<E>
/*     */     //   0	25	1	index	int
/*     */     //   5	17	2	Ljava/lang/Object;	Object
/*     */     //   20	4	3	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	19	20	finally
/*     */     //   20	23	20	finally
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public E set(int index, E object)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 7	org/apache/commons/collections15/list/SynchronizedList:lock	Ljava/lang/Object;
/*     */     //   4: dup
/*     */     //   5: astore_3
/*     */     //   6: monitorenter
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual 8	org/apache/commons/collections15/list/SynchronizedList:getList	()Ljava/util/List;
/*     */     //   11: iload_1
/*     */     //   12: aload_2
/*     */     //   13: invokeinterface 17 3 0
/*     */     //   18: aload_3
/*     */     //   19: monitorexit
/*     */     //   20: areturn
/*     */     //   21: astore 4
/*     */     //   23: aload_3
/*     */     //   24: monitorexit
/*     */     //   25: aload 4
/*     */     //   27: athrow
/*     */     // Line number table:
/*     */     //   Java source line #151	-> byte code offset #0
/*     */     //   Java source line #152	-> byte code offset #7
/*     */     //   Java source line #153	-> byte code offset #21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	28	0	this	SynchronizedList<E>
/*     */     //   0	28	1	index	int
/*     */     //   0	28	2	object	E
/*     */     //   5	19	3	Ljava/lang/Object;	Object
/*     */     //   21	5	4	localObject1	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   7	20	21	finally
/*     */     //   21	25	21	finally
/*     */   }
/*     */   
/*     */   public List<E> subList(int fromIndex, int toIndex)
/*     */   {
/* 157 */     synchronized (this.lock) {
/* 158 */       List<E> list = getList().subList(fromIndex, toIndex);
/*     */       
/*     */ 
/* 161 */       return new SynchronizedList(list, this.lock);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/SynchronizedList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */