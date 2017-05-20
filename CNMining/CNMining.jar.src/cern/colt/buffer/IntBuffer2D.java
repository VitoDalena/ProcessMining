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
/*    */ public class IntBuffer2D
/*    */   extends PersistentObject
/*    */   implements IntBuffer2DConsumer
/*    */ {
/*    */   protected IntBuffer2DConsumer target;
/*    */   protected int[] xElements;
/*    */   protected int[] yElements;
/*    */   protected IntArrayList xList;
/*    */   protected IntArrayList yList;
/*    */   protected int capacity;
/*    */   protected int size;
/*    */   
/*    */   public IntBuffer2D(IntBuffer2DConsumer paramIntBuffer2DConsumer, int paramInt)
/*    */   {
/* 34 */     this.target = paramIntBuffer2DConsumer;
/* 35 */     this.capacity = paramInt;
/* 36 */     this.xElements = new int[paramInt];
/* 37 */     this.yElements = new int[paramInt];
/* 38 */     this.xList = new IntArrayList(this.xElements);
/* 39 */     this.yList = new IntArrayList(this.yElements);
/* 40 */     this.size = 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void add(int paramInt1, int paramInt2)
/*    */   {
/* 49 */     if (this.size == this.capacity) flush();
/* 50 */     this.xElements[this.size] = paramInt1;
/* 51 */     this.yElements[(this.size++)] = paramInt2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void addAllOf(IntArrayList paramIntArrayList1, IntArrayList paramIntArrayList2)
/*    */   {
/* 59 */     int i = paramIntArrayList1.size();
/* 60 */     if (this.size + i >= this.capacity) flush();
/* 61 */     this.target.addAllOf(paramIntArrayList1, paramIntArrayList2);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void clear()
/*    */   {
/* 68 */     this.size = 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public void flush()
/*    */   {
/* 74 */     if (this.size > 0) {
/* 75 */       this.xList.setSize(this.size);
/* 76 */       this.yList.setSize(this.size);
/* 77 */       this.target.addAllOf(this.xList, this.yList);
/* 78 */       this.size = 0;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/buffer/IntBuffer2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */