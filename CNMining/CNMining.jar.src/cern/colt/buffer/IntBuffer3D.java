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
/*    */ public class IntBuffer3D
/*    */   extends PersistentObject
/*    */   implements IntBuffer3DConsumer
/*    */ {
/*    */   protected IntBuffer3DConsumer target;
/*    */   protected int[] xElements;
/*    */   protected int[] yElements;
/*    */   protected int[] zElements;
/*    */   protected IntArrayList xList;
/*    */   protected IntArrayList yList;
/*    */   protected IntArrayList zList;
/*    */   protected int capacity;
/*    */   protected int size;
/*    */   
/*    */   public IntBuffer3D(IntBuffer3DConsumer paramIntBuffer3DConsumer, int paramInt)
/*    */   {
/* 36 */     this.target = paramIntBuffer3DConsumer;
/* 37 */     this.capacity = paramInt;
/* 38 */     this.xElements = new int[paramInt];
/* 39 */     this.yElements = new int[paramInt];
/* 40 */     this.zElements = new int[paramInt];
/* 41 */     this.xList = new IntArrayList(this.xElements);
/* 42 */     this.yList = new IntArrayList(this.yElements);
/* 43 */     this.zList = new IntArrayList(this.zElements);
/* 44 */     this.size = 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void add(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 54 */     if (this.size == this.capacity) flush();
/* 55 */     this.xElements[this.size] = paramInt1;
/* 56 */     this.yElements[this.size] = paramInt2;
/* 57 */     this.zElements[(this.size++)] = paramInt3;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void addAllOf(IntArrayList paramIntArrayList1, IntArrayList paramIntArrayList2, IntArrayList paramIntArrayList3)
/*    */   {
/* 66 */     int i = paramIntArrayList1.size();
/* 67 */     if (this.size + i >= this.capacity) flush();
/* 68 */     this.target.addAllOf(paramIntArrayList1, paramIntArrayList2, paramIntArrayList3);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void clear()
/*    */   {
/* 75 */     this.size = 0;
/*    */   }
/*    */   
/*    */ 
/*    */   public void flush()
/*    */   {
/* 81 */     if (this.size > 0) {
/* 82 */       this.xList.setSize(this.size);
/* 83 */       this.yList.setSize(this.size);
/* 84 */       this.zList.setSize(this.size);
/* 85 */       this.target.addAllOf(this.xList, this.yList, this.zList);
/* 86 */       this.size = 0;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/buffer/IntBuffer3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */