/*      */ package org.apache.commons.collections15.list;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FastArrayList<E>
/*      */   extends ArrayList<E>
/*      */ {
/*      */   public FastArrayList()
/*      */   {
/*   71 */     this.list = new ArrayList();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public FastArrayList(int capacity)
/*      */   {
/*   84 */     this.list = new ArrayList(capacity);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public FastArrayList(Collection<E> collection)
/*      */   {
/*   99 */     this.list = new ArrayList(collection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  110 */   protected ArrayList<E> list = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  119 */   protected boolean fast = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getFast()
/*      */   {
/*  128 */     return this.fast;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFast(boolean fast)
/*      */   {
/*  137 */     this.fast = fast;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public boolean add(E element)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +42 -> 46
/*      */     //   7: aload_0
/*      */     //   8: dup
/*      */     //   9: astore_2
/*      */     //   10: monitorenter
/*      */     //   11: aload_0
/*      */     //   12: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   15: invokevirtual 7	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */     //   18: checkcast 4	java/util/ArrayList
/*      */     //   21: astore_3
/*      */     //   22: aload_3
/*      */     //   23: aload_1
/*      */     //   24: invokevirtual 8	java/util/ArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   27: istore 4
/*      */     //   29: aload_0
/*      */     //   30: aload_3
/*      */     //   31: putfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   34: iload 4
/*      */     //   36: aload_2
/*      */     //   37: monitorexit
/*      */     //   38: ireturn
/*      */     //   39: astore 5
/*      */     //   41: aload_2
/*      */     //   42: monitorexit
/*      */     //   43: aload 5
/*      */     //   45: athrow
/*      */     //   46: aload_0
/*      */     //   47: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   50: dup
/*      */     //   51: astore_2
/*      */     //   52: monitorenter
/*      */     //   53: aload_0
/*      */     //   54: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   57: aload_1
/*      */     //   58: invokevirtual 8	java/util/ArrayList:add	(Ljava/lang/Object;)Z
/*      */     //   61: aload_2
/*      */     //   62: monitorexit
/*      */     //   63: ireturn
/*      */     //   64: astore 6
/*      */     //   66: aload_2
/*      */     //   67: monitorexit
/*      */     //   68: aload 6
/*      */     //   70: athrow
/*      */     // Line number table:
/*      */     //   Java source line #151	-> byte code offset #0
/*      */     //   Java source line #152	-> byte code offset #7
/*      */     //   Java source line #153	-> byte code offset #11
/*      */     //   Java source line #154	-> byte code offset #22
/*      */     //   Java source line #155	-> byte code offset #29
/*      */     //   Java source line #156	-> byte code offset #34
/*      */     //   Java source line #157	-> byte code offset #39
/*      */     //   Java source line #159	-> byte code offset #46
/*      */     //   Java source line #160	-> byte code offset #53
/*      */     //   Java source line #161	-> byte code offset #64
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	71	0	this	FastArrayList<E>
/*      */     //   0	71	1	element	E
/*      */     //   9	33	2	Ljava/lang/Object;	Object
/*      */     //   51	16	2	Ljava/lang/Object;	Object
/*      */     //   21	10	3	temp	ArrayList<E>
/*      */     //   27	8	4	result	boolean
/*      */     //   39	5	5	localObject1	Object
/*      */     //   64	5	6	localObject2	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   11	38	39	finally
/*      */     //   39	43	39	finally
/*      */     //   53	63	64	finally
/*      */     //   64	68	64	finally
/*      */   }
/*      */   
/*      */   public void add(int index, E element)
/*      */   {
/*  177 */     if (this.fast) {
/*  178 */       synchronized (this) {
/*  179 */         ArrayList<E> temp = (ArrayList)this.list.clone();
/*  180 */         temp.add(index, element);
/*  181 */         this.list = temp;
/*      */       }
/*      */     } else {
/*  184 */       synchronized (this.list) {
/*  185 */         this.list.add(index, element);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public boolean addAll(Collection<? extends E> collection)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +42 -> 46
/*      */     //   7: aload_0
/*      */     //   8: dup
/*      */     //   9: astore_2
/*      */     //   10: monitorenter
/*      */     //   11: aload_0
/*      */     //   12: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   15: invokevirtual 7	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */     //   18: checkcast 4	java/util/ArrayList
/*      */     //   21: astore_3
/*      */     //   22: aload_3
/*      */     //   23: aload_1
/*      */     //   24: invokevirtual 10	java/util/ArrayList:addAll	(Ljava/util/Collection;)Z
/*      */     //   27: istore 4
/*      */     //   29: aload_0
/*      */     //   30: aload_3
/*      */     //   31: putfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   34: iload 4
/*      */     //   36: aload_2
/*      */     //   37: monitorexit
/*      */     //   38: ireturn
/*      */     //   39: astore 5
/*      */     //   41: aload_2
/*      */     //   42: monitorexit
/*      */     //   43: aload 5
/*      */     //   45: athrow
/*      */     //   46: aload_0
/*      */     //   47: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   50: dup
/*      */     //   51: astore_2
/*      */     //   52: monitorenter
/*      */     //   53: aload_0
/*      */     //   54: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   57: aload_1
/*      */     //   58: invokevirtual 10	java/util/ArrayList:addAll	(Ljava/util/Collection;)Z
/*      */     //   61: aload_2
/*      */     //   62: monitorexit
/*      */     //   63: ireturn
/*      */     //   64: astore 6
/*      */     //   66: aload_2
/*      */     //   67: monitorexit
/*      */     //   68: aload 6
/*      */     //   70: athrow
/*      */     // Line number table:
/*      */     //   Java source line #201	-> byte code offset #0
/*      */     //   Java source line #202	-> byte code offset #7
/*      */     //   Java source line #203	-> byte code offset #11
/*      */     //   Java source line #204	-> byte code offset #22
/*      */     //   Java source line #205	-> byte code offset #29
/*      */     //   Java source line #206	-> byte code offset #34
/*      */     //   Java source line #207	-> byte code offset #39
/*      */     //   Java source line #209	-> byte code offset #46
/*      */     //   Java source line #210	-> byte code offset #53
/*      */     //   Java source line #211	-> byte code offset #64
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	71	0	this	FastArrayList<E>
/*      */     //   0	71	1	collection	Collection<? extends E>
/*      */     //   9	33	2	Ljava/lang/Object;	Object
/*      */     //   51	16	2	Ljava/lang/Object;	Object
/*      */     //   21	10	3	temp	ArrayList<E>
/*      */     //   27	8	4	result	boolean
/*      */     //   39	5	5	localObject1	Object
/*      */     //   64	5	6	localObject2	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   11	38	39	finally
/*      */     //   39	43	39	finally
/*      */     //   53	63	64	finally
/*      */     //   64	68	64	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public boolean addAll(int index, Collection<? extends E> collection)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +46 -> 50
/*      */     //   7: aload_0
/*      */     //   8: dup
/*      */     //   9: astore_3
/*      */     //   10: monitorenter
/*      */     //   11: aload_0
/*      */     //   12: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   15: invokevirtual 7	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */     //   18: checkcast 4	java/util/ArrayList
/*      */     //   21: astore 4
/*      */     //   23: aload 4
/*      */     //   25: iload_1
/*      */     //   26: aload_2
/*      */     //   27: invokevirtual 11	java/util/ArrayList:addAll	(ILjava/util/Collection;)Z
/*      */     //   30: istore 5
/*      */     //   32: aload_0
/*      */     //   33: aload 4
/*      */     //   35: putfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   38: iload 5
/*      */     //   40: aload_3
/*      */     //   41: monitorexit
/*      */     //   42: ireturn
/*      */     //   43: astore 6
/*      */     //   45: aload_3
/*      */     //   46: monitorexit
/*      */     //   47: aload 6
/*      */     //   49: athrow
/*      */     //   50: aload_0
/*      */     //   51: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   54: dup
/*      */     //   55: astore_3
/*      */     //   56: monitorenter
/*      */     //   57: aload_0
/*      */     //   58: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   61: iload_1
/*      */     //   62: aload_2
/*      */     //   63: invokevirtual 11	java/util/ArrayList:addAll	(ILjava/util/Collection;)Z
/*      */     //   66: aload_3
/*      */     //   67: monitorexit
/*      */     //   68: ireturn
/*      */     //   69: astore 7
/*      */     //   71: aload_3
/*      */     //   72: monitorexit
/*      */     //   73: aload 7
/*      */     //   75: athrow
/*      */     // Line number table:
/*      */     //   Java source line #228	-> byte code offset #0
/*      */     //   Java source line #229	-> byte code offset #7
/*      */     //   Java source line #230	-> byte code offset #11
/*      */     //   Java source line #231	-> byte code offset #23
/*      */     //   Java source line #232	-> byte code offset #32
/*      */     //   Java source line #233	-> byte code offset #38
/*      */     //   Java source line #234	-> byte code offset #43
/*      */     //   Java source line #236	-> byte code offset #50
/*      */     //   Java source line #237	-> byte code offset #57
/*      */     //   Java source line #238	-> byte code offset #69
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	76	0	this	FastArrayList<E>
/*      */     //   0	76	1	index	int
/*      */     //   0	76	2	collection	Collection<? extends E>
/*      */     //   9	37	3	Ljava/lang/Object;	Object
/*      */     //   55	17	3	Ljava/lang/Object;	Object
/*      */     //   21	13	4	temp	ArrayList<E>
/*      */     //   30	9	5	result	boolean
/*      */     //   43	5	6	localObject1	Object
/*      */     //   69	5	7	localObject2	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   11	42	43	finally
/*      */     //   43	47	43	finally
/*      */     //   57	68	69	finally
/*      */     //   69	73	69	finally
/*      */   }
/*      */   
/*      */   public void clear()
/*      */   {
/*  253 */     if (this.fast) {
/*  254 */       synchronized (this) {
/*  255 */         ArrayList<E> temp = (ArrayList)this.list.clone();
/*  256 */         temp.clear();
/*  257 */         this.list = temp;
/*      */       }
/*      */     } else {
/*  260 */       synchronized (this.list) {
/*  261 */         this.list.clear();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */   {
/*  274 */     FastArrayList<E> results = null;
/*  275 */     if (this.fast) {
/*  276 */       results = new FastArrayList(this.list);
/*      */     } else {
/*  278 */       synchronized (this.list) {
/*  279 */         results = new FastArrayList(this.list);
/*      */       }
/*      */     }
/*  282 */     results.setFast(getFast());
/*  283 */     return results;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public boolean contains(Object element)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +12 -> 16
/*      */     //   7: aload_0
/*      */     //   8: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   11: aload_1
/*      */     //   12: invokevirtual 17	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
/*      */     //   15: ireturn
/*      */     //   16: aload_0
/*      */     //   17: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   20: dup
/*      */     //   21: astore_2
/*      */     //   22: monitorenter
/*      */     //   23: aload_0
/*      */     //   24: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   27: aload_1
/*      */     //   28: invokevirtual 17	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
/*      */     //   31: aload_2
/*      */     //   32: monitorexit
/*      */     //   33: ireturn
/*      */     //   34: astore_3
/*      */     //   35: aload_2
/*      */     //   36: monitorexit
/*      */     //   37: aload_3
/*      */     //   38: athrow
/*      */     // Line number table:
/*      */     //   Java source line #295	-> byte code offset #0
/*      */     //   Java source line #296	-> byte code offset #7
/*      */     //   Java source line #298	-> byte code offset #16
/*      */     //   Java source line #299	-> byte code offset #23
/*      */     //   Java source line #300	-> byte code offset #34
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	39	0	this	FastArrayList<E>
/*      */     //   0	39	1	element	Object
/*      */     //   21	15	2	Ljava/lang/Object;	Object
/*      */     //   34	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   23	33	34	finally
/*      */     //   34	37	34	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public boolean containsAll(Collection<?> collection)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +12 -> 16
/*      */     //   7: aload_0
/*      */     //   8: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   11: aload_1
/*      */     //   12: invokevirtual 18	java/util/ArrayList:containsAll	(Ljava/util/Collection;)Z
/*      */     //   15: ireturn
/*      */     //   16: aload_0
/*      */     //   17: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   20: dup
/*      */     //   21: astore_2
/*      */     //   22: monitorenter
/*      */     //   23: aload_0
/*      */     //   24: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   27: aload_1
/*      */     //   28: invokevirtual 18	java/util/ArrayList:containsAll	(Ljava/util/Collection;)Z
/*      */     //   31: aload_2
/*      */     //   32: monitorexit
/*      */     //   33: ireturn
/*      */     //   34: astore_3
/*      */     //   35: aload_2
/*      */     //   36: monitorexit
/*      */     //   37: aload_3
/*      */     //   38: athrow
/*      */     // Line number table:
/*      */     //   Java source line #314	-> byte code offset #0
/*      */     //   Java source line #315	-> byte code offset #7
/*      */     //   Java source line #317	-> byte code offset #16
/*      */     //   Java source line #318	-> byte code offset #23
/*      */     //   Java source line #319	-> byte code offset #34
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	39	0	this	FastArrayList<E>
/*      */     //   0	39	1	collection	Collection<?>
/*      */     //   21	15	2	Ljava/lang/Object;	Object
/*      */     //   34	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   23	33	34	finally
/*      */     //   34	37	34	finally
/*      */   }
/*      */   
/*      */   public void ensureCapacity(int capacity)
/*      */   {
/*  334 */     if (this.fast) {
/*  335 */       synchronized (this) {
/*  336 */         ArrayList<E> temp = (ArrayList)this.list.clone();
/*  337 */         temp.ensureCapacity(capacity);
/*  338 */         this.list = temp;
/*      */       }
/*      */     } else {
/*  341 */       synchronized (this.list) {
/*  342 */         this.list.ensureCapacity(capacity);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object o)
/*      */   {
/*  360 */     if (o == this)
/*  361 */       return true;
/*  362 */     if (!(o instanceof List))
/*  363 */       return false;
/*  364 */     List lo = (List)o;
/*      */     
/*      */ 
/*  367 */     if (this.fast) {
/*  368 */       ListIterator li1 = this.list.listIterator();
/*  369 */       ListIterator li2 = lo.listIterator();
/*  370 */       while ((li1.hasNext()) && (li2.hasNext())) {
/*  371 */         Object o1 = li1.next();
/*  372 */         Object o2 = li2.next();
/*  373 */         if (o1 == null ? o2 != null : !o1.equals(o2))
/*  374 */           return false;
/*      */       }
/*  376 */       return (!li1.hasNext()) && (!li2.hasNext());
/*      */     }
/*  378 */     synchronized (this.list) {
/*  379 */       ListIterator li1 = this.list.listIterator();
/*  380 */       ListIterator li2 = lo.listIterator();
/*  381 */       while ((li1.hasNext()) && (li2.hasNext())) {
/*  382 */         Object o1 = li1.next();
/*  383 */         Object o2 = li2.next();
/*  384 */         if (o1 == null ? o2 != null : !o1.equals(o2))
/*  385 */           return false;
/*      */       }
/*  387 */       return (!li1.hasNext()) && (!li2.hasNext());
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public E get(int index)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +12 -> 16
/*      */     //   7: aload_0
/*      */     //   8: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   11: iload_1
/*      */     //   12: invokevirtual 26	java/util/ArrayList:get	(I)Ljava/lang/Object;
/*      */     //   15: areturn
/*      */     //   16: aload_0
/*      */     //   17: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   20: dup
/*      */     //   21: astore_2
/*      */     //   22: monitorenter
/*      */     //   23: aload_0
/*      */     //   24: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   27: iload_1
/*      */     //   28: invokevirtual 26	java/util/ArrayList:get	(I)Ljava/lang/Object;
/*      */     //   31: aload_2
/*      */     //   32: monitorexit
/*      */     //   33: areturn
/*      */     //   34: astore_3
/*      */     //   35: aload_2
/*      */     //   36: monitorexit
/*      */     //   37: aload_3
/*      */     //   38: athrow
/*      */     // Line number table:
/*      */     //   Java source line #402	-> byte code offset #0
/*      */     //   Java source line #403	-> byte code offset #7
/*      */     //   Java source line #405	-> byte code offset #16
/*      */     //   Java source line #406	-> byte code offset #23
/*      */     //   Java source line #407	-> byte code offset #34
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	39	0	this	FastArrayList<E>
/*      */     //   0	39	1	index	int
/*      */     //   21	15	2	Ljava/lang/Object;	Object
/*      */     //   34	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   23	33	34	finally
/*      */     //   34	37	34	finally
/*      */   }
/*      */   
/*      */   public int hashCode()
/*      */   {
/*  420 */     if (this.fast) {
/*  421 */       int hashCode = 1;
/*  422 */       Iterator i = this.list.iterator();
/*  423 */       while (i.hasNext()) {
/*  424 */         Object o = i.next();
/*  425 */         hashCode = 31 * hashCode + (o == null ? 0 : o.hashCode());
/*      */       }
/*  427 */       return hashCode;
/*      */     }
/*  429 */     synchronized (this.list) {
/*  430 */       int hashCode = 1;
/*  431 */       Iterator i = this.list.iterator();
/*  432 */       while (i.hasNext()) {
/*  433 */         Object o = i.next();
/*  434 */         hashCode = 31 * hashCode + (o == null ? 0 : o.hashCode());
/*      */       }
/*  436 */       return hashCode;
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public int indexOf(Object element)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +12 -> 16
/*      */     //   7: aload_0
/*      */     //   8: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   11: aload_1
/*      */     //   12: invokevirtual 31	java/util/ArrayList:indexOf	(Ljava/lang/Object;)I
/*      */     //   15: ireturn
/*      */     //   16: aload_0
/*      */     //   17: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   20: dup
/*      */     //   21: astore_2
/*      */     //   22: monitorenter
/*      */     //   23: aload_0
/*      */     //   24: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   27: aload_1
/*      */     //   28: invokevirtual 31	java/util/ArrayList:indexOf	(Ljava/lang/Object;)I
/*      */     //   31: aload_2
/*      */     //   32: monitorexit
/*      */     //   33: ireturn
/*      */     //   34: astore_3
/*      */     //   35: aload_2
/*      */     //   36: monitorexit
/*      */     //   37: aload_3
/*      */     //   38: athrow
/*      */     // Line number table:
/*      */     //   Java source line #452	-> byte code offset #0
/*      */     //   Java source line #453	-> byte code offset #7
/*      */     //   Java source line #455	-> byte code offset #16
/*      */     //   Java source line #456	-> byte code offset #23
/*      */     //   Java source line #457	-> byte code offset #34
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	39	0	this	FastArrayList<E>
/*      */     //   0	39	1	element	Object
/*      */     //   21	15	2	Ljava/lang/Object;	Object
/*      */     //   34	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   23	33	34	finally
/*      */     //   34	37	34	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public boolean isEmpty()
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +11 -> 15
/*      */     //   7: aload_0
/*      */     //   8: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   11: invokevirtual 32	java/util/ArrayList:isEmpty	()Z
/*      */     //   14: ireturn
/*      */     //   15: aload_0
/*      */     //   16: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   19: dup
/*      */     //   20: astore_1
/*      */     //   21: monitorenter
/*      */     //   22: aload_0
/*      */     //   23: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   26: invokevirtual 32	java/util/ArrayList:isEmpty	()Z
/*      */     //   29: aload_1
/*      */     //   30: monitorexit
/*      */     //   31: ireturn
/*      */     //   32: astore_2
/*      */     //   33: aload_1
/*      */     //   34: monitorexit
/*      */     //   35: aload_2
/*      */     //   36: athrow
/*      */     // Line number table:
/*      */     //   Java source line #468	-> byte code offset #0
/*      */     //   Java source line #469	-> byte code offset #7
/*      */     //   Java source line #471	-> byte code offset #15
/*      */     //   Java source line #472	-> byte code offset #22
/*      */     //   Java source line #473	-> byte code offset #32
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	37	0	this	FastArrayList<E>
/*      */     //   20	14	1	Ljava/lang/Object;	Object
/*      */     //   32	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   22	31	32	finally
/*      */     //   32	35	32	finally
/*      */   }
/*      */   
/*      */   public Iterator<E> iterator()
/*      */   {
/*  489 */     if (this.fast) {
/*  490 */       return new ListIter(0);
/*      */     }
/*  492 */     return this.list.iterator();
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public int lastIndexOf(Object element)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +12 -> 16
/*      */     //   7: aload_0
/*      */     //   8: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   11: aload_1
/*      */     //   12: invokevirtual 35	java/util/ArrayList:lastIndexOf	(Ljava/lang/Object;)I
/*      */     //   15: ireturn
/*      */     //   16: aload_0
/*      */     //   17: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   20: dup
/*      */     //   21: astore_2
/*      */     //   22: monitorenter
/*      */     //   23: aload_0
/*      */     //   24: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   27: aload_1
/*      */     //   28: invokevirtual 35	java/util/ArrayList:lastIndexOf	(Ljava/lang/Object;)I
/*      */     //   31: aload_2
/*      */     //   32: monitorexit
/*      */     //   33: ireturn
/*      */     //   34: astore_3
/*      */     //   35: aload_2
/*      */     //   36: monitorexit
/*      */     //   37: aload_3
/*      */     //   38: athrow
/*      */     // Line number table:
/*      */     //   Java source line #506	-> byte code offset #0
/*      */     //   Java source line #507	-> byte code offset #7
/*      */     //   Java source line #509	-> byte code offset #16
/*      */     //   Java source line #510	-> byte code offset #23
/*      */     //   Java source line #511	-> byte code offset #34
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	39	0	this	FastArrayList<E>
/*      */     //   0	39	1	element	Object
/*      */     //   21	15	2	Ljava/lang/Object;	Object
/*      */     //   34	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   23	33	34	finally
/*      */     //   34	37	34	finally
/*      */   }
/*      */   
/*      */   public ListIterator<E> listIterator()
/*      */   {
/*  522 */     if (this.fast) {
/*  523 */       return new ListIter(0);
/*      */     }
/*  525 */     return this.list.listIterator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ListIterator<E> listIterator(int index)
/*      */   {
/*  539 */     if (this.fast) {
/*  540 */       return new ListIter(index);
/*      */     }
/*  542 */     return this.list.listIterator(index);
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public E remove(int index)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +42 -> 46
/*      */     //   7: aload_0
/*      */     //   8: dup
/*      */     //   9: astore_2
/*      */     //   10: monitorenter
/*      */     //   11: aload_0
/*      */     //   12: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   15: invokevirtual 7	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */     //   18: checkcast 4	java/util/ArrayList
/*      */     //   21: astore_3
/*      */     //   22: aload_3
/*      */     //   23: iload_1
/*      */     //   24: invokevirtual 37	java/util/ArrayList:remove	(I)Ljava/lang/Object;
/*      */     //   27: astore 4
/*      */     //   29: aload_0
/*      */     //   30: aload_3
/*      */     //   31: putfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   34: aload 4
/*      */     //   36: aload_2
/*      */     //   37: monitorexit
/*      */     //   38: areturn
/*      */     //   39: astore 5
/*      */     //   41: aload_2
/*      */     //   42: monitorexit
/*      */     //   43: aload 5
/*      */     //   45: athrow
/*      */     //   46: aload_0
/*      */     //   47: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   50: dup
/*      */     //   51: astore_2
/*      */     //   52: monitorenter
/*      */     //   53: aload_0
/*      */     //   54: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   57: iload_1
/*      */     //   58: invokevirtual 37	java/util/ArrayList:remove	(I)Ljava/lang/Object;
/*      */     //   61: aload_2
/*      */     //   62: monitorexit
/*      */     //   63: areturn
/*      */     //   64: astore 6
/*      */     //   66: aload_2
/*      */     //   67: monitorexit
/*      */     //   68: aload 6
/*      */     //   70: athrow
/*      */     // Line number table:
/*      */     //   Java source line #556	-> byte code offset #0
/*      */     //   Java source line #557	-> byte code offset #7
/*      */     //   Java source line #558	-> byte code offset #11
/*      */     //   Java source line #559	-> byte code offset #22
/*      */     //   Java source line #560	-> byte code offset #29
/*      */     //   Java source line #561	-> byte code offset #34
/*      */     //   Java source line #562	-> byte code offset #39
/*      */     //   Java source line #564	-> byte code offset #46
/*      */     //   Java source line #565	-> byte code offset #53
/*      */     //   Java source line #566	-> byte code offset #64
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	71	0	this	FastArrayList<E>
/*      */     //   0	71	1	index	int
/*      */     //   9	33	2	Ljava/lang/Object;	Object
/*      */     //   51	16	2	Ljava/lang/Object;	Object
/*      */     //   21	10	3	temp	ArrayList<E>
/*      */     //   27	8	4	result	E
/*      */     //   39	5	5	localObject1	Object
/*      */     //   64	5	6	localObject2	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   11	38	39	finally
/*      */     //   39	43	39	finally
/*      */     //   53	63	64	finally
/*      */     //   64	68	64	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public boolean remove(Object element)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +42 -> 46
/*      */     //   7: aload_0
/*      */     //   8: dup
/*      */     //   9: astore_2
/*      */     //   10: monitorenter
/*      */     //   11: aload_0
/*      */     //   12: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   15: invokevirtual 7	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */     //   18: checkcast 4	java/util/ArrayList
/*      */     //   21: astore_3
/*      */     //   22: aload_3
/*      */     //   23: aload_1
/*      */     //   24: invokevirtual 38	java/util/ArrayList:remove	(Ljava/lang/Object;)Z
/*      */     //   27: istore 4
/*      */     //   29: aload_0
/*      */     //   30: aload_3
/*      */     //   31: putfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   34: iload 4
/*      */     //   36: aload_2
/*      */     //   37: monitorexit
/*      */     //   38: ireturn
/*      */     //   39: astore 5
/*      */     //   41: aload_2
/*      */     //   42: monitorexit
/*      */     //   43: aload 5
/*      */     //   45: athrow
/*      */     //   46: aload_0
/*      */     //   47: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   50: dup
/*      */     //   51: astore_2
/*      */     //   52: monitorenter
/*      */     //   53: aload_0
/*      */     //   54: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   57: aload_1
/*      */     //   58: invokevirtual 38	java/util/ArrayList:remove	(Ljava/lang/Object;)Z
/*      */     //   61: aload_2
/*      */     //   62: monitorexit
/*      */     //   63: ireturn
/*      */     //   64: astore 6
/*      */     //   66: aload_2
/*      */     //   67: monitorexit
/*      */     //   68: aload 6
/*      */     //   70: athrow
/*      */     // Line number table:
/*      */     //   Java source line #580	-> byte code offset #0
/*      */     //   Java source line #581	-> byte code offset #7
/*      */     //   Java source line #582	-> byte code offset #11
/*      */     //   Java source line #583	-> byte code offset #22
/*      */     //   Java source line #584	-> byte code offset #29
/*      */     //   Java source line #585	-> byte code offset #34
/*      */     //   Java source line #586	-> byte code offset #39
/*      */     //   Java source line #588	-> byte code offset #46
/*      */     //   Java source line #589	-> byte code offset #53
/*      */     //   Java source line #590	-> byte code offset #64
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	71	0	this	FastArrayList<E>
/*      */     //   0	71	1	element	Object
/*      */     //   9	33	2	Ljava/lang/Object;	Object
/*      */     //   51	16	2	Ljava/lang/Object;	Object
/*      */     //   21	10	3	temp	ArrayList
/*      */     //   27	8	4	result	boolean
/*      */     //   39	5	5	localObject1	Object
/*      */     //   64	5	6	localObject2	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   11	38	39	finally
/*      */     //   39	43	39	finally
/*      */     //   53	63	64	finally
/*      */     //   64	68	64	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public boolean removeAll(Collection<?> collection)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +42 -> 46
/*      */     //   7: aload_0
/*      */     //   8: dup
/*      */     //   9: astore_2
/*      */     //   10: monitorenter
/*      */     //   11: aload_0
/*      */     //   12: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   15: invokevirtual 7	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */     //   18: checkcast 4	java/util/ArrayList
/*      */     //   21: astore_3
/*      */     //   22: aload_3
/*      */     //   23: aload_1
/*      */     //   24: invokevirtual 39	java/util/ArrayList:removeAll	(Ljava/util/Collection;)Z
/*      */     //   27: istore 4
/*      */     //   29: aload_0
/*      */     //   30: aload_3
/*      */     //   31: putfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   34: iload 4
/*      */     //   36: aload_2
/*      */     //   37: monitorexit
/*      */     //   38: ireturn
/*      */     //   39: astore 5
/*      */     //   41: aload_2
/*      */     //   42: monitorexit
/*      */     //   43: aload 5
/*      */     //   45: athrow
/*      */     //   46: aload_0
/*      */     //   47: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   50: dup
/*      */     //   51: astore_2
/*      */     //   52: monitorenter
/*      */     //   53: aload_0
/*      */     //   54: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   57: aload_1
/*      */     //   58: invokevirtual 39	java/util/ArrayList:removeAll	(Ljava/util/Collection;)Z
/*      */     //   61: aload_2
/*      */     //   62: monitorexit
/*      */     //   63: ireturn
/*      */     //   64: astore 6
/*      */     //   66: aload_2
/*      */     //   67: monitorexit
/*      */     //   68: aload 6
/*      */     //   70: athrow
/*      */     // Line number table:
/*      */     //   Java source line #606	-> byte code offset #0
/*      */     //   Java source line #607	-> byte code offset #7
/*      */     //   Java source line #608	-> byte code offset #11
/*      */     //   Java source line #609	-> byte code offset #22
/*      */     //   Java source line #610	-> byte code offset #29
/*      */     //   Java source line #611	-> byte code offset #34
/*      */     //   Java source line #612	-> byte code offset #39
/*      */     //   Java source line #614	-> byte code offset #46
/*      */     //   Java source line #615	-> byte code offset #53
/*      */     //   Java source line #616	-> byte code offset #64
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	71	0	this	FastArrayList<E>
/*      */     //   0	71	1	collection	Collection<?>
/*      */     //   9	33	2	Ljava/lang/Object;	Object
/*      */     //   51	16	2	Ljava/lang/Object;	Object
/*      */     //   21	10	3	temp	ArrayList
/*      */     //   27	8	4	result	boolean
/*      */     //   39	5	5	localObject1	Object
/*      */     //   64	5	6	localObject2	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   11	38	39	finally
/*      */     //   39	43	39	finally
/*      */     //   53	63	64	finally
/*      */     //   64	68	64	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public boolean retainAll(Collection<?> collection)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +42 -> 46
/*      */     //   7: aload_0
/*      */     //   8: dup
/*      */     //   9: astore_2
/*      */     //   10: monitorenter
/*      */     //   11: aload_0
/*      */     //   12: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   15: invokevirtual 7	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */     //   18: checkcast 4	java/util/ArrayList
/*      */     //   21: astore_3
/*      */     //   22: aload_3
/*      */     //   23: aload_1
/*      */     //   24: invokevirtual 40	java/util/ArrayList:retainAll	(Ljava/util/Collection;)Z
/*      */     //   27: istore 4
/*      */     //   29: aload_0
/*      */     //   30: aload_3
/*      */     //   31: putfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   34: iload 4
/*      */     //   36: aload_2
/*      */     //   37: monitorexit
/*      */     //   38: ireturn
/*      */     //   39: astore 5
/*      */     //   41: aload_2
/*      */     //   42: monitorexit
/*      */     //   43: aload 5
/*      */     //   45: athrow
/*      */     //   46: aload_0
/*      */     //   47: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   50: dup
/*      */     //   51: astore_2
/*      */     //   52: monitorenter
/*      */     //   53: aload_0
/*      */     //   54: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   57: aload_1
/*      */     //   58: invokevirtual 40	java/util/ArrayList:retainAll	(Ljava/util/Collection;)Z
/*      */     //   61: aload_2
/*      */     //   62: monitorexit
/*      */     //   63: ireturn
/*      */     //   64: astore 6
/*      */     //   66: aload_2
/*      */     //   67: monitorexit
/*      */     //   68: aload 6
/*      */     //   70: athrow
/*      */     // Line number table:
/*      */     //   Java source line #632	-> byte code offset #0
/*      */     //   Java source line #633	-> byte code offset #7
/*      */     //   Java source line #634	-> byte code offset #11
/*      */     //   Java source line #635	-> byte code offset #22
/*      */     //   Java source line #636	-> byte code offset #29
/*      */     //   Java source line #637	-> byte code offset #34
/*      */     //   Java source line #638	-> byte code offset #39
/*      */     //   Java source line #640	-> byte code offset #46
/*      */     //   Java source line #641	-> byte code offset #53
/*      */     //   Java source line #642	-> byte code offset #64
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	71	0	this	FastArrayList<E>
/*      */     //   0	71	1	collection	Collection<?>
/*      */     //   9	33	2	Ljava/lang/Object;	Object
/*      */     //   51	16	2	Ljava/lang/Object;	Object
/*      */     //   21	10	3	temp	ArrayList
/*      */     //   27	8	4	result	boolean
/*      */     //   39	5	5	localObject1	Object
/*      */     //   64	5	6	localObject2	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   11	38	39	finally
/*      */     //   39	43	39	finally
/*      */     //   53	63	64	finally
/*      */     //   64	68	64	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public E set(int index, E element)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +13 -> 17
/*      */     //   7: aload_0
/*      */     //   8: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   11: iload_1
/*      */     //   12: aload_2
/*      */     //   13: invokevirtual 41	java/util/ArrayList:set	(ILjava/lang/Object;)Ljava/lang/Object;
/*      */     //   16: areturn
/*      */     //   17: aload_0
/*      */     //   18: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   21: dup
/*      */     //   22: astore_3
/*      */     //   23: monitorenter
/*      */     //   24: aload_0
/*      */     //   25: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   28: iload_1
/*      */     //   29: aload_2
/*      */     //   30: invokevirtual 41	java/util/ArrayList:set	(ILjava/lang/Object;)Ljava/lang/Object;
/*      */     //   33: aload_3
/*      */     //   34: monitorexit
/*      */     //   35: areturn
/*      */     //   36: astore 4
/*      */     //   38: aload_3
/*      */     //   39: monitorexit
/*      */     //   40: aload 4
/*      */     //   42: athrow
/*      */     // Line number table:
/*      */     //   Java source line #662	-> byte code offset #0
/*      */     //   Java source line #663	-> byte code offset #7
/*      */     //   Java source line #665	-> byte code offset #17
/*      */     //   Java source line #666	-> byte code offset #24
/*      */     //   Java source line #667	-> byte code offset #36
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	43	0	this	FastArrayList<E>
/*      */     //   0	43	1	index	int
/*      */     //   0	43	2	element	E
/*      */     //   22	17	3	Ljava/lang/Object;	Object
/*      */     //   36	5	4	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   24	35	36	finally
/*      */     //   36	40	36	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public int size()
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +11 -> 15
/*      */     //   7: aload_0
/*      */     //   8: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   11: invokevirtual 42	java/util/ArrayList:size	()I
/*      */     //   14: ireturn
/*      */     //   15: aload_0
/*      */     //   16: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   19: dup
/*      */     //   20: astore_1
/*      */     //   21: monitorenter
/*      */     //   22: aload_0
/*      */     //   23: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   26: invokevirtual 42	java/util/ArrayList:size	()I
/*      */     //   29: aload_1
/*      */     //   30: monitorexit
/*      */     //   31: ireturn
/*      */     //   32: astore_2
/*      */     //   33: aload_1
/*      */     //   34: monitorexit
/*      */     //   35: aload_2
/*      */     //   36: athrow
/*      */     // Line number table:
/*      */     //   Java source line #678	-> byte code offset #0
/*      */     //   Java source line #679	-> byte code offset #7
/*      */     //   Java source line #681	-> byte code offset #15
/*      */     //   Java source line #682	-> byte code offset #22
/*      */     //   Java source line #683	-> byte code offset #32
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	37	0	this	FastArrayList<E>
/*      */     //   20	14	1	Ljava/lang/Object;	Object
/*      */     //   32	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   22	31	32	finally
/*      */     //   32	35	32	finally
/*      */   }
/*      */   
/*      */   public List<E> subList(int fromIndex, int toIndex)
/*      */   {
/*  701 */     if (this.fast) {
/*  702 */       return new SubList(fromIndex, toIndex);
/*      */     }
/*  704 */     return this.list.subList(fromIndex, toIndex);
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public Object[] toArray()
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +11 -> 15
/*      */     //   7: aload_0
/*      */     //   8: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   11: invokevirtual 46	java/util/ArrayList:toArray	()[Ljava/lang/Object;
/*      */     //   14: areturn
/*      */     //   15: aload_0
/*      */     //   16: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   19: dup
/*      */     //   20: astore_1
/*      */     //   21: monitorenter
/*      */     //   22: aload_0
/*      */     //   23: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   26: invokevirtual 46	java/util/ArrayList:toArray	()[Ljava/lang/Object;
/*      */     //   29: aload_1
/*      */     //   30: monitorexit
/*      */     //   31: areturn
/*      */     //   32: astore_2
/*      */     //   33: aload_1
/*      */     //   34: monitorexit
/*      */     //   35: aload_2
/*      */     //   36: athrow
/*      */     // Line number table:
/*      */     //   Java source line #715	-> byte code offset #0
/*      */     //   Java source line #716	-> byte code offset #7
/*      */     //   Java source line #718	-> byte code offset #15
/*      */     //   Java source line #719	-> byte code offset #22
/*      */     //   Java source line #720	-> byte code offset #32
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	37	0	this	FastArrayList<E>
/*      */     //   20	14	1	Ljava/lang/Object;	Object
/*      */     //   32	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   22	31	32	finally
/*      */     //   32	35	32	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public <T> T[] toArray(T[] array)
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */     //   4: ifeq +12 -> 16
/*      */     //   7: aload_0
/*      */     //   8: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   11: aload_1
/*      */     //   12: invokevirtual 47	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
/*      */     //   15: areturn
/*      */     //   16: aload_0
/*      */     //   17: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   20: dup
/*      */     //   21: astore_2
/*      */     //   22: monitorenter
/*      */     //   23: aload_0
/*      */     //   24: getfield 2	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */     //   27: aload_1
/*      */     //   28: invokevirtual 47	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
/*      */     //   31: aload_2
/*      */     //   32: monitorexit
/*      */     //   33: areturn
/*      */     //   34: astore_3
/*      */     //   35: aload_2
/*      */     //   36: monitorexit
/*      */     //   37: aload_3
/*      */     //   38: athrow
/*      */     // Line number table:
/*      */     //   Java source line #739	-> byte code offset #0
/*      */     //   Java source line #740	-> byte code offset #7
/*      */     //   Java source line #742	-> byte code offset #16
/*      */     //   Java source line #743	-> byte code offset #23
/*      */     //   Java source line #744	-> byte code offset #34
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	39	0	this	FastArrayList<E>
/*      */     //   0	39	1	array	T[]
/*      */     //   21	15	2	Ljava/lang/Object;	Object
/*      */     //   34	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   23	33	34	finally
/*      */     //   34	37	34	finally
/*      */   }
/*      */   
/*      */   public String toString()
/*      */   {
/*  755 */     StringBuffer sb = new StringBuffer("FastArrayList[");
/*  756 */     sb.append(this.list.toString());
/*  757 */     sb.append("]");
/*  758 */     return sb.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void trimToSize()
/*      */   {
/*  770 */     if (this.fast) {
/*  771 */       synchronized (this) {
/*  772 */         ArrayList temp = (ArrayList)this.list.clone();
/*  773 */         temp.trimToSize();
/*  774 */         this.list = temp;
/*      */       }
/*      */     } else {
/*  777 */       synchronized (this.list) {
/*  778 */         this.list.trimToSize();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private class SubList
/*      */     implements List<E>
/*      */   {
/*      */     private int first;
/*      */     private int last;
/*      */     private List<E> expected;
/*      */     
/*      */     public SubList(int first, int last)
/*      */     {
/*  793 */       this.first = first;
/*  794 */       this.last = last;
/*  795 */       this.expected = FastArrayList.this.list;
/*      */     }
/*      */     
/*      */     private List<E> get(List<E> l) {
/*  799 */       if (FastArrayList.this.list != this.expected) {
/*  800 */         throw new ConcurrentModificationException();
/*      */       }
/*  802 */       return l.subList(this.first, this.last);
/*      */     }
/*      */     
/*      */     public void clear() {
/*  806 */       if (FastArrayList.this.fast) {
/*  807 */         synchronized (FastArrayList.this) {
/*  808 */           ArrayList<E> temp = (ArrayList)FastArrayList.this.list.clone();
/*  809 */           get(temp).clear();
/*  810 */           this.last = this.first;
/*  811 */           FastArrayList.this.list = temp;
/*  812 */           this.expected = temp;
/*      */         }
/*      */       } else {
/*  815 */         synchronized (FastArrayList.this.list) {
/*  816 */           get(this.expected).clear();
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public boolean remove(Object o)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +77 -> 84
/*      */       //   10: aload_0
/*      */       //   11: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   14: dup
/*      */       //   15: astore_2
/*      */       //   16: monitorenter
/*      */       //   17: aload_0
/*      */       //   18: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   21: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   24: invokevirtual 12	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */       //   27: checkcast 13	java/util/ArrayList
/*      */       //   30: astore_3
/*      */       //   31: aload_0
/*      */       //   32: aload_3
/*      */       //   33: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   36: aload_1
/*      */       //   37: invokeinterface 15 2 0
/*      */       //   42: istore 4
/*      */       //   44: iload 4
/*      */       //   46: ifeq +13 -> 59
/*      */       //   49: aload_0
/*      */       //   50: dup
/*      */       //   51: getfield 1	org/apache/commons/collections15/list/FastArrayList$SubList:last	I
/*      */       //   54: iconst_1
/*      */       //   55: isub
/*      */       //   56: putfield 1	org/apache/commons/collections15/list/FastArrayList$SubList:last	I
/*      */       //   59: aload_0
/*      */       //   60: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   63: aload_3
/*      */       //   64: putfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   67: aload_0
/*      */       //   68: aload_3
/*      */       //   69: putfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   72: iload 4
/*      */       //   74: aload_2
/*      */       //   75: monitorexit
/*      */       //   76: ireturn
/*      */       //   77: astore 5
/*      */       //   79: aload_2
/*      */       //   80: monitorexit
/*      */       //   81: aload 5
/*      */       //   83: athrow
/*      */       //   84: aload_0
/*      */       //   85: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   88: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   91: dup
/*      */       //   92: astore_2
/*      */       //   93: monitorenter
/*      */       //   94: aload_0
/*      */       //   95: aload_0
/*      */       //   96: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   99: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   102: aload_1
/*      */       //   103: invokeinterface 15 2 0
/*      */       //   108: aload_2
/*      */       //   109: monitorexit
/*      */       //   110: ireturn
/*      */       //   111: astore 6
/*      */       //   113: aload_2
/*      */       //   114: monitorexit
/*      */       //   115: aload 6
/*      */       //   117: athrow
/*      */       // Line number table:
/*      */       //   Java source line #822	-> byte code offset #0
/*      */       //   Java source line #823	-> byte code offset #10
/*      */       //   Java source line #824	-> byte code offset #17
/*      */       //   Java source line #825	-> byte code offset #31
/*      */       //   Java source line #826	-> byte code offset #44
/*      */       //   Java source line #827	-> byte code offset #59
/*      */       //   Java source line #828	-> byte code offset #67
/*      */       //   Java source line #829	-> byte code offset #72
/*      */       //   Java source line #830	-> byte code offset #77
/*      */       //   Java source line #832	-> byte code offset #84
/*      */       //   Java source line #833	-> byte code offset #94
/*      */       //   Java source line #834	-> byte code offset #111
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	118	0	this	FastArrayList<E>.SubList
/*      */       //   0	118	1	o	Object
/*      */       //   15	65	2	Ljava/lang/Object;	Object
/*      */       //   92	22	2	Ljava/lang/Object;	Object
/*      */       //   30	39	3	temp	ArrayList
/*      */       //   42	31	4	r	boolean
/*      */       //   77	5	5	localObject1	Object
/*      */       //   111	5	6	localObject2	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   17	76	77	finally
/*      */       //   77	81	77	finally
/*      */       //   94	110	111	finally
/*      */       //   111	115	111	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public boolean removeAll(Collection<?> o)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +87 -> 94
/*      */       //   10: aload_0
/*      */       //   11: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   14: dup
/*      */       //   15: astore_2
/*      */       //   16: monitorenter
/*      */       //   17: aload_0
/*      */       //   18: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   21: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   24: invokevirtual 12	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */       //   27: checkcast 13	java/util/ArrayList
/*      */       //   30: astore_3
/*      */       //   31: aload_0
/*      */       //   32: aload_3
/*      */       //   33: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   36: astore 4
/*      */       //   38: aload 4
/*      */       //   40: aload_1
/*      */       //   41: invokeinterface 16 2 0
/*      */       //   46: istore 5
/*      */       //   48: iload 5
/*      */       //   50: ifeq +19 -> 69
/*      */       //   53: aload_0
/*      */       //   54: aload_0
/*      */       //   55: getfield 5	org/apache/commons/collections15/list/FastArrayList$SubList:first	I
/*      */       //   58: aload 4
/*      */       //   60: invokeinterface 17 1 0
/*      */       //   65: iadd
/*      */       //   66: putfield 1	org/apache/commons/collections15/list/FastArrayList$SubList:last	I
/*      */       //   69: aload_0
/*      */       //   70: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   73: aload_3
/*      */       //   74: putfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   77: aload_0
/*      */       //   78: aload_3
/*      */       //   79: putfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   82: iload 5
/*      */       //   84: aload_2
/*      */       //   85: monitorexit
/*      */       //   86: ireturn
/*      */       //   87: astore 6
/*      */       //   89: aload_2
/*      */       //   90: monitorexit
/*      */       //   91: aload 6
/*      */       //   93: athrow
/*      */       //   94: aload_0
/*      */       //   95: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   98: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   101: dup
/*      */       //   102: astore_2
/*      */       //   103: monitorenter
/*      */       //   104: aload_0
/*      */       //   105: aload_0
/*      */       //   106: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   109: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   112: aload_1
/*      */       //   113: invokeinterface 16 2 0
/*      */       //   118: aload_2
/*      */       //   119: monitorexit
/*      */       //   120: ireturn
/*      */       //   121: astore 7
/*      */       //   123: aload_2
/*      */       //   124: monitorexit
/*      */       //   125: aload 7
/*      */       //   127: athrow
/*      */       // Line number table:
/*      */       //   Java source line #839	-> byte code offset #0
/*      */       //   Java source line #840	-> byte code offset #10
/*      */       //   Java source line #841	-> byte code offset #17
/*      */       //   Java source line #842	-> byte code offset #31
/*      */       //   Java source line #843	-> byte code offset #38
/*      */       //   Java source line #844	-> byte code offset #48
/*      */       //   Java source line #845	-> byte code offset #69
/*      */       //   Java source line #846	-> byte code offset #77
/*      */       //   Java source line #847	-> byte code offset #82
/*      */       //   Java source line #848	-> byte code offset #87
/*      */       //   Java source line #850	-> byte code offset #94
/*      */       //   Java source line #851	-> byte code offset #104
/*      */       //   Java source line #852	-> byte code offset #121
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	128	0	this	FastArrayList<E>.SubList
/*      */       //   0	128	1	o	Collection<?>
/*      */       //   15	75	2	Ljava/lang/Object;	Object
/*      */       //   102	22	2	Ljava/lang/Object;	Object
/*      */       //   30	49	3	temp	ArrayList
/*      */       //   36	23	4	sub	List
/*      */       //   46	37	5	r	boolean
/*      */       //   87	5	6	localObject1	Object
/*      */       //   121	5	7	localObject2	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   17	86	87	finally
/*      */       //   87	91	87	finally
/*      */       //   104	120	121	finally
/*      */       //   121	125	121	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public boolean retainAll(Collection<?> o)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +87 -> 94
/*      */       //   10: aload_0
/*      */       //   11: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   14: dup
/*      */       //   15: astore_2
/*      */       //   16: monitorenter
/*      */       //   17: aload_0
/*      */       //   18: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   21: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   24: invokevirtual 12	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */       //   27: checkcast 13	java/util/ArrayList
/*      */       //   30: astore_3
/*      */       //   31: aload_0
/*      */       //   32: aload_3
/*      */       //   33: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   36: astore 4
/*      */       //   38: aload 4
/*      */       //   40: aload_1
/*      */       //   41: invokeinterface 18 2 0
/*      */       //   46: istore 5
/*      */       //   48: iload 5
/*      */       //   50: ifeq +19 -> 69
/*      */       //   53: aload_0
/*      */       //   54: aload_0
/*      */       //   55: getfield 5	org/apache/commons/collections15/list/FastArrayList$SubList:first	I
/*      */       //   58: aload 4
/*      */       //   60: invokeinterface 17 1 0
/*      */       //   65: iadd
/*      */       //   66: putfield 1	org/apache/commons/collections15/list/FastArrayList$SubList:last	I
/*      */       //   69: aload_0
/*      */       //   70: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   73: aload_3
/*      */       //   74: putfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   77: aload_0
/*      */       //   78: aload_3
/*      */       //   79: putfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   82: iload 5
/*      */       //   84: aload_2
/*      */       //   85: monitorexit
/*      */       //   86: ireturn
/*      */       //   87: astore 6
/*      */       //   89: aload_2
/*      */       //   90: monitorexit
/*      */       //   91: aload 6
/*      */       //   93: athrow
/*      */       //   94: aload_0
/*      */       //   95: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   98: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   101: dup
/*      */       //   102: astore_2
/*      */       //   103: monitorenter
/*      */       //   104: aload_0
/*      */       //   105: aload_0
/*      */       //   106: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   109: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   112: aload_1
/*      */       //   113: invokeinterface 18 2 0
/*      */       //   118: aload_2
/*      */       //   119: monitorexit
/*      */       //   120: ireturn
/*      */       //   121: astore 7
/*      */       //   123: aload_2
/*      */       //   124: monitorexit
/*      */       //   125: aload 7
/*      */       //   127: athrow
/*      */       // Line number table:
/*      */       //   Java source line #857	-> byte code offset #0
/*      */       //   Java source line #858	-> byte code offset #10
/*      */       //   Java source line #859	-> byte code offset #17
/*      */       //   Java source line #860	-> byte code offset #31
/*      */       //   Java source line #861	-> byte code offset #38
/*      */       //   Java source line #862	-> byte code offset #48
/*      */       //   Java source line #863	-> byte code offset #69
/*      */       //   Java source line #864	-> byte code offset #77
/*      */       //   Java source line #865	-> byte code offset #82
/*      */       //   Java source line #866	-> byte code offset #87
/*      */       //   Java source line #868	-> byte code offset #94
/*      */       //   Java source line #869	-> byte code offset #104
/*      */       //   Java source line #870	-> byte code offset #121
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	128	0	this	FastArrayList<E>.SubList
/*      */       //   0	128	1	o	Collection<?>
/*      */       //   15	75	2	Ljava/lang/Object;	Object
/*      */       //   102	22	2	Ljava/lang/Object;	Object
/*      */       //   30	49	3	temp	ArrayList
/*      */       //   36	23	4	sub	List
/*      */       //   46	37	5	r	boolean
/*      */       //   87	5	6	localObject1	Object
/*      */       //   121	5	7	localObject2	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   17	86	87	finally
/*      */       //   87	91	87	finally
/*      */       //   104	120	121	finally
/*      */       //   121	125	121	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public int size()
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +17 -> 24
/*      */       //   10: aload_0
/*      */       //   11: aload_0
/*      */       //   12: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   15: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   18: invokeinterface 17 1 0
/*      */       //   23: ireturn
/*      */       //   24: aload_0
/*      */       //   25: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   28: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   31: dup
/*      */       //   32: astore_1
/*      */       //   33: monitorenter
/*      */       //   34: aload_0
/*      */       //   35: aload_0
/*      */       //   36: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   39: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   42: invokeinterface 17 1 0
/*      */       //   47: aload_1
/*      */       //   48: monitorexit
/*      */       //   49: ireturn
/*      */       //   50: astore_2
/*      */       //   51: aload_1
/*      */       //   52: monitorexit
/*      */       //   53: aload_2
/*      */       //   54: athrow
/*      */       // Line number table:
/*      */       //   Java source line #875	-> byte code offset #0
/*      */       //   Java source line #876	-> byte code offset #10
/*      */       //   Java source line #878	-> byte code offset #24
/*      */       //   Java source line #879	-> byte code offset #34
/*      */       //   Java source line #880	-> byte code offset #50
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	55	0	this	FastArrayList<E>.SubList
/*      */       //   32	20	1	Ljava/lang/Object;	Object
/*      */       //   50	4	2	localObject1	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   34	49	50	finally
/*      */       //   50	53	50	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public boolean isEmpty()
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +17 -> 24
/*      */       //   10: aload_0
/*      */       //   11: aload_0
/*      */       //   12: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   15: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   18: invokeinterface 19 1 0
/*      */       //   23: ireturn
/*      */       //   24: aload_0
/*      */       //   25: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   28: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   31: dup
/*      */       //   32: astore_1
/*      */       //   33: monitorenter
/*      */       //   34: aload_0
/*      */       //   35: aload_0
/*      */       //   36: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   39: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   42: invokeinterface 19 1 0
/*      */       //   47: aload_1
/*      */       //   48: monitorexit
/*      */       //   49: ireturn
/*      */       //   50: astore_2
/*      */       //   51: aload_1
/*      */       //   52: monitorexit
/*      */       //   53: aload_2
/*      */       //   54: athrow
/*      */       // Line number table:
/*      */       //   Java source line #886	-> byte code offset #0
/*      */       //   Java source line #887	-> byte code offset #10
/*      */       //   Java source line #889	-> byte code offset #24
/*      */       //   Java source line #890	-> byte code offset #34
/*      */       //   Java source line #891	-> byte code offset #50
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	55	0	this	FastArrayList<E>.SubList
/*      */       //   32	20	1	Ljava/lang/Object;	Object
/*      */       //   50	4	2	localObject1	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   34	49	50	finally
/*      */       //   50	53	50	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public boolean contains(Object o)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +18 -> 25
/*      */       //   10: aload_0
/*      */       //   11: aload_0
/*      */       //   12: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   15: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   18: aload_1
/*      */       //   19: invokeinterface 20 2 0
/*      */       //   24: ireturn
/*      */       //   25: aload_0
/*      */       //   26: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   29: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   32: dup
/*      */       //   33: astore_2
/*      */       //   34: monitorenter
/*      */       //   35: aload_0
/*      */       //   36: aload_0
/*      */       //   37: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   40: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   43: aload_1
/*      */       //   44: invokeinterface 20 2 0
/*      */       //   49: aload_2
/*      */       //   50: monitorexit
/*      */       //   51: ireturn
/*      */       //   52: astore_3
/*      */       //   53: aload_2
/*      */       //   54: monitorexit
/*      */       //   55: aload_3
/*      */       //   56: athrow
/*      */       // Line number table:
/*      */       //   Java source line #896	-> byte code offset #0
/*      */       //   Java source line #897	-> byte code offset #10
/*      */       //   Java source line #899	-> byte code offset #25
/*      */       //   Java source line #900	-> byte code offset #35
/*      */       //   Java source line #901	-> byte code offset #52
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	57	0	this	FastArrayList<E>.SubList
/*      */       //   0	57	1	o	Object
/*      */       //   33	21	2	Ljava/lang/Object;	Object
/*      */       //   52	4	3	localObject1	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   35	51	52	finally
/*      */       //   52	55	52	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public boolean containsAll(Collection<?> o)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +18 -> 25
/*      */       //   10: aload_0
/*      */       //   11: aload_0
/*      */       //   12: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   15: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   18: aload_1
/*      */       //   19: invokeinterface 21 2 0
/*      */       //   24: ireturn
/*      */       //   25: aload_0
/*      */       //   26: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   29: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   32: dup
/*      */       //   33: astore_2
/*      */       //   34: monitorenter
/*      */       //   35: aload_0
/*      */       //   36: aload_0
/*      */       //   37: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   40: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   43: aload_1
/*      */       //   44: invokeinterface 21 2 0
/*      */       //   49: aload_2
/*      */       //   50: monitorexit
/*      */       //   51: ireturn
/*      */       //   52: astore_3
/*      */       //   53: aload_2
/*      */       //   54: monitorexit
/*      */       //   55: aload_3
/*      */       //   56: athrow
/*      */       // Line number table:
/*      */       //   Java source line #906	-> byte code offset #0
/*      */       //   Java source line #907	-> byte code offset #10
/*      */       //   Java source line #909	-> byte code offset #25
/*      */       //   Java source line #910	-> byte code offset #35
/*      */       //   Java source line #911	-> byte code offset #52
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	57	0	this	FastArrayList<E>.SubList
/*      */       //   0	57	1	o	Collection<?>
/*      */       //   33	21	2	Ljava/lang/Object;	Object
/*      */       //   52	4	3	localObject1	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   35	51	52	finally
/*      */       //   52	55	52	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public <T> T[] toArray(T[] o)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +18 -> 25
/*      */       //   10: aload_0
/*      */       //   11: aload_0
/*      */       //   12: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   15: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   18: aload_1
/*      */       //   19: invokeinterface 22 2 0
/*      */       //   24: areturn
/*      */       //   25: aload_0
/*      */       //   26: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   29: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   32: dup
/*      */       //   33: astore_2
/*      */       //   34: monitorenter
/*      */       //   35: aload_0
/*      */       //   36: aload_0
/*      */       //   37: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   40: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   43: aload_1
/*      */       //   44: invokeinterface 22 2 0
/*      */       //   49: aload_2
/*      */       //   50: monitorexit
/*      */       //   51: areturn
/*      */       //   52: astore_3
/*      */       //   53: aload_2
/*      */       //   54: monitorexit
/*      */       //   55: aload_3
/*      */       //   56: athrow
/*      */       // Line number table:
/*      */       //   Java source line #916	-> byte code offset #0
/*      */       //   Java source line #917	-> byte code offset #10
/*      */       //   Java source line #919	-> byte code offset #25
/*      */       //   Java source line #920	-> byte code offset #35
/*      */       //   Java source line #921	-> byte code offset #52
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	57	0	this	FastArrayList<E>.SubList
/*      */       //   0	57	1	o	T[]
/*      */       //   33	21	2	Ljava/lang/Object;	Object
/*      */       //   52	4	3	localObject1	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   35	51	52	finally
/*      */       //   52	55	52	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public Object[] toArray()
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +17 -> 24
/*      */       //   10: aload_0
/*      */       //   11: aload_0
/*      */       //   12: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   15: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   18: invokeinterface 23 1 0
/*      */       //   23: areturn
/*      */       //   24: aload_0
/*      */       //   25: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   28: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   31: dup
/*      */       //   32: astore_1
/*      */       //   33: monitorenter
/*      */       //   34: aload_0
/*      */       //   35: aload_0
/*      */       //   36: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   39: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   42: invokeinterface 23 1 0
/*      */       //   47: aload_1
/*      */       //   48: monitorexit
/*      */       //   49: areturn
/*      */       //   50: astore_2
/*      */       //   51: aload_1
/*      */       //   52: monitorexit
/*      */       //   53: aload_2
/*      */       //   54: athrow
/*      */       // Line number table:
/*      */       //   Java source line #926	-> byte code offset #0
/*      */       //   Java source line #927	-> byte code offset #10
/*      */       //   Java source line #929	-> byte code offset #24
/*      */       //   Java source line #930	-> byte code offset #34
/*      */       //   Java source line #931	-> byte code offset #50
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	55	0	this	FastArrayList<E>.SubList
/*      */       //   32	20	1	Ljava/lang/Object;	Object
/*      */       //   50	4	2	localObject1	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   34	49	50	finally
/*      */       //   50	53	50	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public boolean equals(Object o)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_1
/*      */       //   1: aload_0
/*      */       //   2: if_acmpne +5 -> 7
/*      */       //   5: iconst_1
/*      */       //   6: ireturn
/*      */       //   7: aload_0
/*      */       //   8: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   11: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   14: ifeq +16 -> 30
/*      */       //   17: aload_0
/*      */       //   18: aload_0
/*      */       //   19: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   22: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   25: aload_1
/*      */       //   26: invokevirtual 24	java/lang/Object:equals	(Ljava/lang/Object;)Z
/*      */       //   29: ireturn
/*      */       //   30: aload_0
/*      */       //   31: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   34: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   37: dup
/*      */       //   38: astore_2
/*      */       //   39: monitorenter
/*      */       //   40: aload_0
/*      */       //   41: aload_0
/*      */       //   42: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   45: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   48: aload_1
/*      */       //   49: invokevirtual 24	java/lang/Object:equals	(Ljava/lang/Object;)Z
/*      */       //   52: aload_2
/*      */       //   53: monitorexit
/*      */       //   54: ireturn
/*      */       //   55: astore_3
/*      */       //   56: aload_2
/*      */       //   57: monitorexit
/*      */       //   58: aload_3
/*      */       //   59: athrow
/*      */       // Line number table:
/*      */       //   Java source line #937	-> byte code offset #0
/*      */       //   Java source line #938	-> byte code offset #7
/*      */       //   Java source line #939	-> byte code offset #17
/*      */       //   Java source line #941	-> byte code offset #30
/*      */       //   Java source line #942	-> byte code offset #40
/*      */       //   Java source line #943	-> byte code offset #55
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	60	0	this	FastArrayList<E>.SubList
/*      */       //   0	60	1	o	Object
/*      */       //   38	19	2	Ljava/lang/Object;	Object
/*      */       //   55	4	3	localObject1	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   40	54	55	finally
/*      */       //   55	58	55	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public int hashCode()
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +15 -> 22
/*      */       //   10: aload_0
/*      */       //   11: aload_0
/*      */       //   12: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   15: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   18: invokevirtual 25	java/lang/Object:hashCode	()I
/*      */       //   21: ireturn
/*      */       //   22: aload_0
/*      */       //   23: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   26: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   29: dup
/*      */       //   30: astore_1
/*      */       //   31: monitorenter
/*      */       //   32: aload_0
/*      */       //   33: aload_0
/*      */       //   34: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   37: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   40: invokevirtual 25	java/lang/Object:hashCode	()I
/*      */       //   43: aload_1
/*      */       //   44: monitorexit
/*      */       //   45: ireturn
/*      */       //   46: astore_2
/*      */       //   47: aload_1
/*      */       //   48: monitorexit
/*      */       //   49: aload_2
/*      */       //   50: athrow
/*      */       // Line number table:
/*      */       //   Java source line #948	-> byte code offset #0
/*      */       //   Java source line #949	-> byte code offset #10
/*      */       //   Java source line #951	-> byte code offset #22
/*      */       //   Java source line #952	-> byte code offset #32
/*      */       //   Java source line #953	-> byte code offset #46
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	51	0	this	FastArrayList<E>.SubList
/*      */       //   30	18	1	Ljava/lang/Object;	Object
/*      */       //   46	4	2	localObject1	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   32	45	46	finally
/*      */       //   46	49	46	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public boolean add(E o)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +77 -> 84
/*      */       //   10: aload_0
/*      */       //   11: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   14: dup
/*      */       //   15: astore_2
/*      */       //   16: monitorenter
/*      */       //   17: aload_0
/*      */       //   18: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   21: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   24: invokevirtual 12	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */       //   27: checkcast 13	java/util/ArrayList
/*      */       //   30: astore_3
/*      */       //   31: aload_0
/*      */       //   32: aload_3
/*      */       //   33: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   36: aload_1
/*      */       //   37: invokeinterface 26 2 0
/*      */       //   42: istore 4
/*      */       //   44: iload 4
/*      */       //   46: ifeq +13 -> 59
/*      */       //   49: aload_0
/*      */       //   50: dup
/*      */       //   51: getfield 1	org/apache/commons/collections15/list/FastArrayList$SubList:last	I
/*      */       //   54: iconst_1
/*      */       //   55: iadd
/*      */       //   56: putfield 1	org/apache/commons/collections15/list/FastArrayList$SubList:last	I
/*      */       //   59: aload_0
/*      */       //   60: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   63: aload_3
/*      */       //   64: putfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   67: aload_0
/*      */       //   68: aload_3
/*      */       //   69: putfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   72: iload 4
/*      */       //   74: aload_2
/*      */       //   75: monitorexit
/*      */       //   76: ireturn
/*      */       //   77: astore 5
/*      */       //   79: aload_2
/*      */       //   80: monitorexit
/*      */       //   81: aload 5
/*      */       //   83: athrow
/*      */       //   84: aload_0
/*      */       //   85: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   88: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   91: dup
/*      */       //   92: astore_2
/*      */       //   93: monitorenter
/*      */       //   94: aload_0
/*      */       //   95: aload_0
/*      */       //   96: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   99: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   102: aload_1
/*      */       //   103: invokeinterface 26 2 0
/*      */       //   108: aload_2
/*      */       //   109: monitorexit
/*      */       //   110: ireturn
/*      */       //   111: astore 6
/*      */       //   113: aload_2
/*      */       //   114: monitorexit
/*      */       //   115: aload 6
/*      */       //   117: athrow
/*      */       // Line number table:
/*      */       //   Java source line #958	-> byte code offset #0
/*      */       //   Java source line #959	-> byte code offset #10
/*      */       //   Java source line #960	-> byte code offset #17
/*      */       //   Java source line #961	-> byte code offset #31
/*      */       //   Java source line #962	-> byte code offset #44
/*      */       //   Java source line #963	-> byte code offset #59
/*      */       //   Java source line #964	-> byte code offset #67
/*      */       //   Java source line #965	-> byte code offset #72
/*      */       //   Java source line #966	-> byte code offset #77
/*      */       //   Java source line #968	-> byte code offset #84
/*      */       //   Java source line #969	-> byte code offset #94
/*      */       //   Java source line #970	-> byte code offset #111
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	118	0	this	FastArrayList<E>.SubList
/*      */       //   0	118	1	o	E
/*      */       //   15	65	2	Ljava/lang/Object;	Object
/*      */       //   92	22	2	Ljava/lang/Object;	Object
/*      */       //   30	39	3	temp	ArrayList
/*      */       //   42	31	4	r	boolean
/*      */       //   77	5	5	localObject1	Object
/*      */       //   111	5	6	localObject2	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   17	76	77	finally
/*      */       //   77	81	77	finally
/*      */       //   94	110	111	finally
/*      */       //   111	115	111	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public boolean addAll(Collection<? extends E> o)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +82 -> 89
/*      */       //   10: aload_0
/*      */       //   11: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   14: dup
/*      */       //   15: astore_2
/*      */       //   16: monitorenter
/*      */       //   17: aload_0
/*      */       //   18: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   21: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   24: invokevirtual 12	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */       //   27: checkcast 13	java/util/ArrayList
/*      */       //   30: astore_3
/*      */       //   31: aload_0
/*      */       //   32: aload_3
/*      */       //   33: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   36: aload_1
/*      */       //   37: invokeinterface 27 2 0
/*      */       //   42: istore 4
/*      */       //   44: iload 4
/*      */       //   46: ifeq +18 -> 64
/*      */       //   49: aload_0
/*      */       //   50: dup
/*      */       //   51: getfield 1	org/apache/commons/collections15/list/FastArrayList$SubList:last	I
/*      */       //   54: aload_1
/*      */       //   55: invokeinterface 28 1 0
/*      */       //   60: iadd
/*      */       //   61: putfield 1	org/apache/commons/collections15/list/FastArrayList$SubList:last	I
/*      */       //   64: aload_0
/*      */       //   65: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   68: aload_3
/*      */       //   69: putfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   72: aload_0
/*      */       //   73: aload_3
/*      */       //   74: putfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   77: iload 4
/*      */       //   79: aload_2
/*      */       //   80: monitorexit
/*      */       //   81: ireturn
/*      */       //   82: astore 5
/*      */       //   84: aload_2
/*      */       //   85: monitorexit
/*      */       //   86: aload 5
/*      */       //   88: athrow
/*      */       //   89: aload_0
/*      */       //   90: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   93: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   96: dup
/*      */       //   97: astore_2
/*      */       //   98: monitorenter
/*      */       //   99: aload_0
/*      */       //   100: aload_0
/*      */       //   101: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   104: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   107: aload_1
/*      */       //   108: invokeinterface 27 2 0
/*      */       //   113: aload_2
/*      */       //   114: monitorexit
/*      */       //   115: ireturn
/*      */       //   116: astore 6
/*      */       //   118: aload_2
/*      */       //   119: monitorexit
/*      */       //   120: aload 6
/*      */       //   122: athrow
/*      */       // Line number table:
/*      */       //   Java source line #975	-> byte code offset #0
/*      */       //   Java source line #976	-> byte code offset #10
/*      */       //   Java source line #977	-> byte code offset #17
/*      */       //   Java source line #978	-> byte code offset #31
/*      */       //   Java source line #979	-> byte code offset #44
/*      */       //   Java source line #980	-> byte code offset #64
/*      */       //   Java source line #981	-> byte code offset #72
/*      */       //   Java source line #982	-> byte code offset #77
/*      */       //   Java source line #983	-> byte code offset #82
/*      */       //   Java source line #985	-> byte code offset #89
/*      */       //   Java source line #986	-> byte code offset #99
/*      */       //   Java source line #987	-> byte code offset #116
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	123	0	this	FastArrayList<E>.SubList
/*      */       //   0	123	1	o	Collection<? extends E>
/*      */       //   15	70	2	Ljava/lang/Object;	Object
/*      */       //   97	22	2	Ljava/lang/Object;	Object
/*      */       //   30	44	3	temp	ArrayList
/*      */       //   42	36	4	r	boolean
/*      */       //   82	5	5	localObject1	Object
/*      */       //   116	5	6	localObject2	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   17	81	82	finally
/*      */       //   82	86	82	finally
/*      */       //   99	115	116	finally
/*      */       //   116	120	116	finally
/*      */     }
/*      */     
/*      */     public void add(int i, E o)
/*      */     {
/*  992 */       if (FastArrayList.this.fast) {
/*  993 */         synchronized (FastArrayList.this) {
/*  994 */           ArrayList temp = (ArrayList)FastArrayList.this.list.clone();
/*  995 */           get(temp).add(i, o);
/*  996 */           this.last += 1;
/*  997 */           FastArrayList.this.list = temp;
/*  998 */           this.expected = temp;
/*      */         }
/*      */       } else {
/* 1001 */         synchronized (FastArrayList.this.list) {
/* 1002 */           get(this.expected).add(i, o);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public boolean addAll(int i, Collection<? extends E> o)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +87 -> 94
/*      */       //   10: aload_0
/*      */       //   11: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   14: dup
/*      */       //   15: astore_3
/*      */       //   16: monitorenter
/*      */       //   17: aload_0
/*      */       //   18: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   21: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   24: invokevirtual 12	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */       //   27: checkcast 13	java/util/ArrayList
/*      */       //   30: astore 4
/*      */       //   32: aload_0
/*      */       //   33: aload 4
/*      */       //   35: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   38: iload_1
/*      */       //   39: aload_2
/*      */       //   40: invokeinterface 30 3 0
/*      */       //   45: istore 5
/*      */       //   47: aload_0
/*      */       //   48: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   51: aload 4
/*      */       //   53: putfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   56: iload 5
/*      */       //   58: ifeq +18 -> 76
/*      */       //   61: aload_0
/*      */       //   62: dup
/*      */       //   63: getfield 1	org/apache/commons/collections15/list/FastArrayList$SubList:last	I
/*      */       //   66: aload_2
/*      */       //   67: invokeinterface 28 1 0
/*      */       //   72: iadd
/*      */       //   73: putfield 1	org/apache/commons/collections15/list/FastArrayList$SubList:last	I
/*      */       //   76: aload_0
/*      */       //   77: aload 4
/*      */       //   79: putfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   82: iload 5
/*      */       //   84: aload_3
/*      */       //   85: monitorexit
/*      */       //   86: ireturn
/*      */       //   87: astore 6
/*      */       //   89: aload_3
/*      */       //   90: monitorexit
/*      */       //   91: aload 6
/*      */       //   93: athrow
/*      */       //   94: aload_0
/*      */       //   95: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   98: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   101: dup
/*      */       //   102: astore_3
/*      */       //   103: monitorenter
/*      */       //   104: aload_0
/*      */       //   105: aload_0
/*      */       //   106: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   109: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   112: iload_1
/*      */       //   113: aload_2
/*      */       //   114: invokeinterface 30 3 0
/*      */       //   119: aload_3
/*      */       //   120: monitorexit
/*      */       //   121: ireturn
/*      */       //   122: astore 7
/*      */       //   124: aload_3
/*      */       //   125: monitorexit
/*      */       //   126: aload 7
/*      */       //   128: athrow
/*      */       // Line number table:
/*      */       //   Java source line #1008	-> byte code offset #0
/*      */       //   Java source line #1009	-> byte code offset #10
/*      */       //   Java source line #1010	-> byte code offset #17
/*      */       //   Java source line #1011	-> byte code offset #32
/*      */       //   Java source line #1012	-> byte code offset #47
/*      */       //   Java source line #1013	-> byte code offset #56
/*      */       //   Java source line #1014	-> byte code offset #76
/*      */       //   Java source line #1015	-> byte code offset #82
/*      */       //   Java source line #1016	-> byte code offset #87
/*      */       //   Java source line #1018	-> byte code offset #94
/*      */       //   Java source line #1019	-> byte code offset #104
/*      */       //   Java source line #1020	-> byte code offset #122
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	129	0	this	FastArrayList<E>.SubList
/*      */       //   0	129	1	i	int
/*      */       //   0	129	2	o	Collection<? extends E>
/*      */       //   15	75	3	Ljava/lang/Object;	Object
/*      */       //   102	23	3	Ljava/lang/Object;	Object
/*      */       //   30	48	4	temp	ArrayList
/*      */       //   45	38	5	r	boolean
/*      */       //   87	5	6	localObject1	Object
/*      */       //   122	5	7	localObject2	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   17	86	87	finally
/*      */       //   87	91	87	finally
/*      */       //   104	121	122	finally
/*      */       //   122	126	122	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public E remove(int i)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +72 -> 79
/*      */       //   10: aload_0
/*      */       //   11: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   14: dup
/*      */       //   15: astore_2
/*      */       //   16: monitorenter
/*      */       //   17: aload_0
/*      */       //   18: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   21: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   24: invokevirtual 12	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */       //   27: checkcast 13	java/util/ArrayList
/*      */       //   30: astore_3
/*      */       //   31: aload_0
/*      */       //   32: aload_3
/*      */       //   33: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   36: iload_1
/*      */       //   37: invokeinterface 31 2 0
/*      */       //   42: astore 4
/*      */       //   44: aload_0
/*      */       //   45: dup
/*      */       //   46: getfield 1	org/apache/commons/collections15/list/FastArrayList$SubList:last	I
/*      */       //   49: iconst_1
/*      */       //   50: isub
/*      */       //   51: putfield 1	org/apache/commons/collections15/list/FastArrayList$SubList:last	I
/*      */       //   54: aload_0
/*      */       //   55: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   58: aload_3
/*      */       //   59: putfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   62: aload_0
/*      */       //   63: aload_3
/*      */       //   64: putfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   67: aload 4
/*      */       //   69: aload_2
/*      */       //   70: monitorexit
/*      */       //   71: areturn
/*      */       //   72: astore 5
/*      */       //   74: aload_2
/*      */       //   75: monitorexit
/*      */       //   76: aload 5
/*      */       //   78: athrow
/*      */       //   79: aload_0
/*      */       //   80: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   83: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   86: dup
/*      */       //   87: astore_2
/*      */       //   88: monitorenter
/*      */       //   89: aload_0
/*      */       //   90: aload_0
/*      */       //   91: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   94: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   97: iload_1
/*      */       //   98: invokeinterface 31 2 0
/*      */       //   103: aload_2
/*      */       //   104: monitorexit
/*      */       //   105: areturn
/*      */       //   106: astore 6
/*      */       //   108: aload_2
/*      */       //   109: monitorexit
/*      */       //   110: aload 6
/*      */       //   112: athrow
/*      */       // Line number table:
/*      */       //   Java source line #1025	-> byte code offset #0
/*      */       //   Java source line #1026	-> byte code offset #10
/*      */       //   Java source line #1027	-> byte code offset #17
/*      */       //   Java source line #1028	-> byte code offset #31
/*      */       //   Java source line #1029	-> byte code offset #44
/*      */       //   Java source line #1030	-> byte code offset #54
/*      */       //   Java source line #1031	-> byte code offset #62
/*      */       //   Java source line #1032	-> byte code offset #67
/*      */       //   Java source line #1033	-> byte code offset #72
/*      */       //   Java source line #1035	-> byte code offset #79
/*      */       //   Java source line #1036	-> byte code offset #89
/*      */       //   Java source line #1037	-> byte code offset #106
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	113	0	this	FastArrayList<E>.SubList
/*      */       //   0	113	1	i	int
/*      */       //   15	60	2	Ljava/lang/Object;	Object
/*      */       //   87	22	2	Ljava/lang/Object;	Object
/*      */       //   30	34	3	temp	ArrayList
/*      */       //   42	26	4	o	E
/*      */       //   72	5	5	localObject1	Object
/*      */       //   106	5	6	localObject2	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   17	71	72	finally
/*      */       //   72	76	72	finally
/*      */       //   89	105	106	finally
/*      */       //   106	110	106	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public E set(int i, E a)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +67 -> 74
/*      */       //   10: aload_0
/*      */       //   11: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   14: dup
/*      */       //   15: astore_3
/*      */       //   16: monitorenter
/*      */       //   17: aload_0
/*      */       //   18: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   21: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   24: invokevirtual 12	java/util/ArrayList:clone	()Ljava/lang/Object;
/*      */       //   27: checkcast 13	java/util/ArrayList
/*      */       //   30: astore 4
/*      */       //   32: aload_0
/*      */       //   33: aload 4
/*      */       //   35: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   38: iload_1
/*      */       //   39: aload_2
/*      */       //   40: invokeinterface 32 3 0
/*      */       //   45: astore 5
/*      */       //   47: aload_0
/*      */       //   48: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   51: aload 4
/*      */       //   53: putfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   56: aload_0
/*      */       //   57: aload 4
/*      */       //   59: putfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   62: aload 5
/*      */       //   64: aload_3
/*      */       //   65: monitorexit
/*      */       //   66: areturn
/*      */       //   67: astore 6
/*      */       //   69: aload_3
/*      */       //   70: monitorexit
/*      */       //   71: aload 6
/*      */       //   73: athrow
/*      */       //   74: aload_0
/*      */       //   75: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   78: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   81: dup
/*      */       //   82: astore_3
/*      */       //   83: monitorenter
/*      */       //   84: aload_0
/*      */       //   85: aload_0
/*      */       //   86: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   89: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   92: iload_1
/*      */       //   93: aload_2
/*      */       //   94: invokeinterface 32 3 0
/*      */       //   99: aload_3
/*      */       //   100: monitorexit
/*      */       //   101: areturn
/*      */       //   102: astore 7
/*      */       //   104: aload_3
/*      */       //   105: monitorexit
/*      */       //   106: aload 7
/*      */       //   108: athrow
/*      */       // Line number table:
/*      */       //   Java source line #1042	-> byte code offset #0
/*      */       //   Java source line #1043	-> byte code offset #10
/*      */       //   Java source line #1044	-> byte code offset #17
/*      */       //   Java source line #1045	-> byte code offset #32
/*      */       //   Java source line #1046	-> byte code offset #47
/*      */       //   Java source line #1047	-> byte code offset #56
/*      */       //   Java source line #1048	-> byte code offset #62
/*      */       //   Java source line #1049	-> byte code offset #67
/*      */       //   Java source line #1051	-> byte code offset #74
/*      */       //   Java source line #1052	-> byte code offset #84
/*      */       //   Java source line #1053	-> byte code offset #102
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	109	0	this	FastArrayList<E>.SubList
/*      */       //   0	109	1	i	int
/*      */       //   0	109	2	a	E
/*      */       //   15	55	3	Ljava/lang/Object;	Object
/*      */       //   82	23	3	Ljava/lang/Object;	Object
/*      */       //   30	28	4	temp	ArrayList
/*      */       //   45	18	5	o	E
/*      */       //   67	5	6	localObject1	Object
/*      */       //   102	5	7	localObject2	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   17	66	67	finally
/*      */       //   67	71	67	finally
/*      */       //   84	101	102	finally
/*      */       //   102	106	102	finally
/*      */     }
/*      */     
/*      */     public Iterator<E> iterator()
/*      */     {
/* 1059 */       return new SubListIter(0);
/*      */     }
/*      */     
/*      */     public ListIterator<E> listIterator() {
/* 1063 */       return new SubListIter(0);
/*      */     }
/*      */     
/*      */     public ListIterator<E> listIterator(int i) {
/* 1067 */       return new SubListIter(i);
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public E get(int i)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +18 -> 25
/*      */       //   10: aload_0
/*      */       //   11: aload_0
/*      */       //   12: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   15: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   18: iload_1
/*      */       //   19: invokeinterface 35 2 0
/*      */       //   24: areturn
/*      */       //   25: aload_0
/*      */       //   26: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   29: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   32: dup
/*      */       //   33: astore_2
/*      */       //   34: monitorenter
/*      */       //   35: aload_0
/*      */       //   36: aload_0
/*      */       //   37: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   40: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   43: iload_1
/*      */       //   44: invokeinterface 35 2 0
/*      */       //   49: aload_2
/*      */       //   50: monitorexit
/*      */       //   51: areturn
/*      */       //   52: astore_3
/*      */       //   53: aload_2
/*      */       //   54: monitorexit
/*      */       //   55: aload_3
/*      */       //   56: athrow
/*      */       // Line number table:
/*      */       //   Java source line #1072	-> byte code offset #0
/*      */       //   Java source line #1073	-> byte code offset #10
/*      */       //   Java source line #1075	-> byte code offset #25
/*      */       //   Java source line #1076	-> byte code offset #35
/*      */       //   Java source line #1077	-> byte code offset #52
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	57	0	this	FastArrayList<E>.SubList
/*      */       //   0	57	1	i	int
/*      */       //   33	21	2	Ljava/lang/Object;	Object
/*      */       //   52	4	3	localObject1	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   35	51	52	finally
/*      */       //   52	55	52	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public int indexOf(Object o)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +18 -> 25
/*      */       //   10: aload_0
/*      */       //   11: aload_0
/*      */       //   12: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   15: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   18: aload_1
/*      */       //   19: invokeinterface 36 2 0
/*      */       //   24: ireturn
/*      */       //   25: aload_0
/*      */       //   26: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   29: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   32: dup
/*      */       //   33: astore_2
/*      */       //   34: monitorenter
/*      */       //   35: aload_0
/*      */       //   36: aload_0
/*      */       //   37: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   40: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   43: aload_1
/*      */       //   44: invokeinterface 36 2 0
/*      */       //   49: aload_2
/*      */       //   50: monitorexit
/*      */       //   51: ireturn
/*      */       //   52: astore_3
/*      */       //   53: aload_2
/*      */       //   54: monitorexit
/*      */       //   55: aload_3
/*      */       //   56: athrow
/*      */       // Line number table:
/*      */       //   Java source line #1082	-> byte code offset #0
/*      */       //   Java source line #1083	-> byte code offset #10
/*      */       //   Java source line #1085	-> byte code offset #25
/*      */       //   Java source line #1086	-> byte code offset #35
/*      */       //   Java source line #1087	-> byte code offset #52
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	57	0	this	FastArrayList<E>.SubList
/*      */       //   0	57	1	o	Object
/*      */       //   33	21	2	Ljava/lang/Object;	Object
/*      */       //   52	4	3	localObject1	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   35	51	52	finally
/*      */       //   52	55	52	finally
/*      */     }
/*      */     
/*      */     /* Error */
/*      */     public int lastIndexOf(Object o)
/*      */     {
/*      */       // Byte code:
/*      */       //   0: aload_0
/*      */       //   1: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   4: getfield 11	org/apache/commons/collections15/list/FastArrayList:fast	Z
/*      */       //   7: ifeq +18 -> 25
/*      */       //   10: aload_0
/*      */       //   11: aload_0
/*      */       //   12: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   15: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   18: aload_1
/*      */       //   19: invokeinterface 37 2 0
/*      */       //   24: ireturn
/*      */       //   25: aload_0
/*      */       //   26: getfield 3	org/apache/commons/collections15/list/FastArrayList$SubList:this$0	Lorg/apache/commons/collections15/list/FastArrayList;
/*      */       //   29: getfield 6	org/apache/commons/collections15/list/FastArrayList:list	Ljava/util/ArrayList;
/*      */       //   32: dup
/*      */       //   33: astore_2
/*      */       //   34: monitorenter
/*      */       //   35: aload_0
/*      */       //   36: aload_0
/*      */       //   37: getfield 7	org/apache/commons/collections15/list/FastArrayList$SubList:expected	Ljava/util/List;
/*      */       //   40: invokespecial 2	org/apache/commons/collections15/list/FastArrayList$SubList:get	(Ljava/util/List;)Ljava/util/List;
/*      */       //   43: aload_1
/*      */       //   44: invokeinterface 37 2 0
/*      */       //   49: aload_2
/*      */       //   50: monitorexit
/*      */       //   51: ireturn
/*      */       //   52: astore_3
/*      */       //   53: aload_2
/*      */       //   54: monitorexit
/*      */       //   55: aload_3
/*      */       //   56: athrow
/*      */       // Line number table:
/*      */       //   Java source line #1093	-> byte code offset #0
/*      */       //   Java source line #1094	-> byte code offset #10
/*      */       //   Java source line #1096	-> byte code offset #25
/*      */       //   Java source line #1097	-> byte code offset #35
/*      */       //   Java source line #1098	-> byte code offset #52
/*      */       // Local variable table:
/*      */       //   start	length	slot	name	signature
/*      */       //   0	57	0	this	FastArrayList<E>.SubList
/*      */       //   0	57	1	o	Object
/*      */       //   33	21	2	Ljava/lang/Object;	Object
/*      */       //   52	4	3	localObject1	Object
/*      */       // Exception table:
/*      */       //   from	to	target	type
/*      */       //   35	51	52	finally
/*      */       //   52	55	52	finally
/*      */     }
/*      */     
/*      */     public List<E> subList(int f, int l)
/*      */     {
/* 1104 */       if (FastArrayList.this.list != this.expected) {
/* 1105 */         throw new ConcurrentModificationException();
/*      */       }
/* 1107 */       return new SubList(FastArrayList.this, this.first + f, f + l);
/*      */     }
/*      */     
/*      */     private class SubListIter
/*      */       implements ListIterator<E>
/*      */     {
/*      */       private List<E> expected;
/*      */       private ListIterator<E> iter;
/* 1115 */       private int lastReturnedIndex = -1;
/*      */       
/*      */       public SubListIter(int i)
/*      */       {
/* 1119 */         this.expected = FastArrayList.this.list;
/* 1120 */         this.iter = FastArrayList.SubList.this.get(this.expected).listIterator(i);
/*      */       }
/*      */       
/*      */       private void checkMod() {
/* 1124 */         if (FastArrayList.this.list != this.expected) {
/* 1125 */           throw new ConcurrentModificationException();
/*      */         }
/*      */       }
/*      */       
/*      */       List<E> get() {
/* 1130 */         return FastArrayList.SubList.this.get(this.expected);
/*      */       }
/*      */       
/*      */       public boolean hasNext() {
/* 1134 */         checkMod();
/* 1135 */         return this.iter.hasNext();
/*      */       }
/*      */       
/*      */       public E next() {
/* 1139 */         checkMod();
/* 1140 */         this.lastReturnedIndex = this.iter.nextIndex();
/* 1141 */         return (E)this.iter.next();
/*      */       }
/*      */       
/*      */       public boolean hasPrevious() {
/* 1145 */         checkMod();
/* 1146 */         return this.iter.hasPrevious();
/*      */       }
/*      */       
/*      */       public E previous() {
/* 1150 */         checkMod();
/* 1151 */         this.lastReturnedIndex = this.iter.previousIndex();
/* 1152 */         return (E)this.iter.previous();
/*      */       }
/*      */       
/*      */       public int previousIndex() {
/* 1156 */         checkMod();
/* 1157 */         return this.iter.previousIndex();
/*      */       }
/*      */       
/*      */       public int nextIndex() {
/* 1161 */         checkMod();
/* 1162 */         return this.iter.nextIndex();
/*      */       }
/*      */       
/*      */       public void remove() {
/* 1166 */         checkMod();
/* 1167 */         if (this.lastReturnedIndex < 0) {
/* 1168 */           throw new IllegalStateException();
/*      */         }
/* 1170 */         get().remove(this.lastReturnedIndex);
/* 1171 */         FastArrayList.SubList.access$110(FastArrayList.SubList.this);
/* 1172 */         this.expected = FastArrayList.this.list;
/* 1173 */         this.iter = get().listIterator(previousIndex());
/* 1174 */         this.lastReturnedIndex = -1;
/*      */       }
/*      */       
/*      */       public void set(E o) {
/* 1178 */         checkMod();
/* 1179 */         if (this.lastReturnedIndex < 0) {
/* 1180 */           throw new IllegalStateException();
/*      */         }
/* 1182 */         get().set(this.lastReturnedIndex, o);
/* 1183 */         this.expected = FastArrayList.this.list;
/* 1184 */         this.iter = get().listIterator(previousIndex() + 1);
/*      */       }
/*      */       
/*      */       public void add(E o) {
/* 1188 */         checkMod();
/* 1189 */         int i = nextIndex();
/* 1190 */         get().add(i, o);
/* 1191 */         FastArrayList.SubList.access$108(FastArrayList.SubList.this);
/* 1192 */         this.iter = get().listIterator(i + 1);
/* 1193 */         this.lastReturnedIndex = 1;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private class ListIter
/*      */     implements ListIterator<E>
/*      */   {
/*      */     private List<E> expected;
/*      */     
/*      */     private ListIterator<E> iter;
/*      */     
/* 1206 */     private int lastReturnedIndex = -1;
/*      */     
/*      */     public ListIter(int i)
/*      */     {
/* 1210 */       this.expected = FastArrayList.this.list;
/* 1211 */       this.iter = get().listIterator(i);
/*      */     }
/*      */     
/*      */     private void checkMod() {
/* 1215 */       if (FastArrayList.this.list != this.expected) {
/* 1216 */         throw new ConcurrentModificationException();
/*      */       }
/*      */     }
/*      */     
/*      */     List get() {
/* 1221 */       return this.expected;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 1225 */       checkMod();
/* 1226 */       return this.iter.hasNext();
/*      */     }
/*      */     
/*      */     public E next() {
/* 1230 */       checkMod();
/* 1231 */       this.lastReturnedIndex = this.iter.nextIndex();
/* 1232 */       return (E)this.iter.next();
/*      */     }
/*      */     
/*      */     public boolean hasPrevious() {
/* 1236 */       checkMod();
/* 1237 */       return this.iter.hasPrevious();
/*      */     }
/*      */     
/*      */     public E previous() {
/* 1241 */       checkMod();
/* 1242 */       this.lastReturnedIndex = this.iter.previousIndex();
/* 1243 */       return (E)this.iter.previous();
/*      */     }
/*      */     
/*      */     public int previousIndex() {
/* 1247 */       checkMod();
/* 1248 */       return this.iter.previousIndex();
/*      */     }
/*      */     
/*      */     public int nextIndex() {
/* 1252 */       checkMod();
/* 1253 */       return this.iter.nextIndex();
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1257 */       checkMod();
/* 1258 */       if (this.lastReturnedIndex < 0) {
/* 1259 */         throw new IllegalStateException();
/*      */       }
/* 1261 */       get().remove(this.lastReturnedIndex);
/* 1262 */       this.expected = FastArrayList.this.list;
/* 1263 */       this.iter = get().listIterator(previousIndex());
/* 1264 */       this.lastReturnedIndex = -1;
/*      */     }
/*      */     
/*      */     public void set(E o) {
/* 1268 */       checkMod();
/* 1269 */       if (this.lastReturnedIndex < 0) {
/* 1270 */         throw new IllegalStateException();
/*      */       }
/* 1272 */       get().set(this.lastReturnedIndex, o);
/* 1273 */       this.expected = FastArrayList.this.list;
/* 1274 */       this.iter = get().listIterator(previousIndex() + 1);
/*      */     }
/*      */     
/*      */     public void add(E o) {
/* 1278 */       checkMod();
/* 1279 */       int i = nextIndex();
/* 1280 */       get().add(i, o);
/* 1281 */       this.iter = get().listIterator(i + 1);
/* 1282 */       this.lastReturnedIndex = -1;
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/list/FastArrayList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */