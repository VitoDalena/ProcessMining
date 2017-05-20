/*    */ package cern.colt.buffer;
/*    */ 
/*    */ import cern.colt.PersistentObject;
/*    */ import cern.colt.list.AbstractDoubleList;
/*    */ import cern.colt.list.AbstractList;
/*    */ import cern.colt.list.DoubleArrayList;
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
/*    */ public class DoubleBuffer
/*    */   extends PersistentObject
/*    */   implements DoubleBufferConsumer
/*    */ {
/*    */   protected DoubleBufferConsumer target;
/*    */   protected double[] elements;
/*    */   protected DoubleArrayList list;
/*    */   protected int capacity;
/*    */   protected int size;
/*    */   
/*    */   public DoubleBuffer(DoubleBufferConsumer paramDoubleBufferConsumer, int paramInt)
/*    */   {
/* 32 */     this.target = paramDoubleBufferConsumer;
/* 33 */     this.capacity = paramInt;
/* 34 */     this.elements = new double[paramInt];
/* 35 */     this.list = new DoubleArrayList(this.elements);
/* 36 */     this.size = 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void add(double paramDouble)
/*    */   {
/* 44 */     if (this.size == this.capacity) flush();
/* 45 */     this.elements[(this.size++)] = paramDouble;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void addAllOf(DoubleArrayList paramDoubleArrayList)
/*    */   {
/* 52 */     int i = paramDoubleArrayList.size();
/* 53 */     if (this.size + i >= this.capacity) flush();
/* 54 */     this.target.addAllOf(paramDoubleArrayList);
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/buffer/DoubleBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */