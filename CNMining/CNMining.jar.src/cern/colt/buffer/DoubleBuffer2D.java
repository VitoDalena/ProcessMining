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
/*    */ public class DoubleBuffer2D
/*    */   extends PersistentObject
/*    */   implements DoubleBuffer2DConsumer
/*    */ {
/*    */   protected DoubleBuffer2DConsumer target;
/*    */   protected double[] xElements;
/*    */   protected double[] yElements;
/*    */   protected DoubleArrayList xList;
/*    */   protected DoubleArrayList yList;
/*    */   protected int capacity;
/*    */   protected int size;
/*    */   
/*    */   public DoubleBuffer2D(DoubleBuffer2DConsumer paramDoubleBuffer2DConsumer, int paramInt)
/*    */   {
/* 34 */     this.target = paramDoubleBuffer2DConsumer;
/* 35 */     this.capacity = paramInt;
/* 36 */     this.xElements = new double[paramInt];
/* 37 */     this.yElements = new double[paramInt];
/* 38 */     this.xList = new DoubleArrayList(this.xElements);
/* 39 */     this.yList = new DoubleArrayList(this.yElements);
/* 40 */     this.size = 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void add(double paramDouble1, double paramDouble2)
/*    */   {
/* 49 */     if (this.size == this.capacity) flush();
/* 50 */     this.xElements[this.size] = paramDouble1;
/* 51 */     this.yElements[(this.size++)] = paramDouble2;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void addAllOf(DoubleArrayList paramDoubleArrayList1, DoubleArrayList paramDoubleArrayList2)
/*    */   {
/* 59 */     int i = paramDoubleArrayList1.size();
/* 60 */     if (this.size + i >= this.capacity) flush();
/* 61 */     this.target.addAllOf(paramDoubleArrayList1, paramDoubleArrayList2);
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/buffer/DoubleBuffer2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */