/*    */ package cern.colt.buffer;
/*    */ 
/*    */ import cern.colt.PersistentObject;
/*    */ import cern.colt.list.AbstractIntList;
/*    */ import cern.colt.list.AbstractList;
/*    */ import cern.colt.list.IntArrayList;
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
/*    */ public class IntBuffer
/*    */   extends PersistentObject
/*    */   implements IntBufferConsumer
/*    */ {
/*    */   protected IntBufferConsumer target;
/*    */   protected int[] elements;
/*    */   protected IntArrayList list;
/*    */   protected int capacity;
/*    */   protected int size;
/*    */   
/*    */   public IntBuffer(IntBufferConsumer paramIntBufferConsumer, int paramInt)
/*    */   {
/* 32 */     this.target = paramIntBufferConsumer;
/* 33 */     this.capacity = paramInt;
/* 34 */     this.elements = new int[paramInt];
/* 35 */     this.list = new IntArrayList(this.elements);
/* 36 */     this.size = 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void add(int paramInt)
/*    */   {
/* 44 */     if (this.size == this.capacity) flush();
/* 45 */     this.elements[(this.size++)] = paramInt;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void addAllOf(IntArrayList paramIntArrayList)
/*    */   {
/* 52 */     int i = paramIntArrayList.size();
/* 53 */     if (this.size + i >= this.capacity) flush();
/* 54 */     this.target.addAllOf(paramIntArrayList);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void clear()
/*    */   {
/* 61 */     this.size = 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public void flush()
/*    */   {
/* 67 */     if (this.size > 0) {
/* 68 */       this.list.setSize(this.size);
/* 69 */       this.target.addAllOf(this.list);
/* 70 */       this.size = 0;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/buffer/IntBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */