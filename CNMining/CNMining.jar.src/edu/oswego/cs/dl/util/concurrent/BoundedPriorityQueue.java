/*     */ package edu.oswego.cs.dl.util.concurrent;
/*     */ 
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.Comparator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BoundedPriorityQueue
/*     */   extends SemaphoreControlledChannel
/*     */ {
/*     */   protected final Heap heap_;
/*     */   
/*     */   public BoundedPriorityQueue(int paramInt, Comparator paramComparator)
/*     */     throws IllegalArgumentException
/*     */   {
/*  55 */     super(paramInt);
/*  56 */     this.heap_ = new Heap(paramInt, paramComparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BoundedPriorityQueue(Comparator paramComparator)
/*     */   {
/*  65 */     this(DefaultChannelCapacity.get(), paramComparator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BoundedPriorityQueue(int paramInt)
/*     */   {
/*  74 */     this(paramInt, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public BoundedPriorityQueue()
/*     */   {
/*  83 */     this(DefaultChannelCapacity.get(), null);
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
/*     */   public BoundedPriorityQueue(int paramInt, Comparator paramComparator, Class paramClass)
/*     */     throws IllegalArgumentException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 109 */     super(paramInt, paramClass);
/* 110 */     this.heap_ = new Heap(paramInt, paramComparator);
/*     */   }
/*     */   
/* 113 */   protected void insert(Object paramObject) { this.heap_.insert(paramObject); }
/* 114 */   protected Object extract() { return this.heap_.extract(); }
/* 115 */   public Object peek() { return this.heap_.peek(); }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/BoundedPriorityQueue.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */