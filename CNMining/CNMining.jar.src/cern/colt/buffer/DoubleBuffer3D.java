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
/*    */ public class DoubleBuffer3D
/*    */   extends PersistentObject
/*    */   implements DoubleBuffer3DConsumer
/*    */ {
/*    */   protected DoubleBuffer3DConsumer target;
/*    */   protected double[] xElements;
/*    */   protected double[] yElements;
/*    */   protected double[] zElements;
/*    */   protected DoubleArrayList xList;
/*    */   protected DoubleArrayList yList;
/*    */   protected DoubleArrayList zList;
/*    */   protected int capacity;
/*    */   protected int size;
/*    */   
/*    */   public DoubleBuffer3D(DoubleBuffer3DConsumer paramDoubleBuffer3DConsumer, int paramInt)
/*    */   {
/* 36 */     this.target = paramDoubleBuffer3DConsumer;
/* 37 */     this.capacity = paramInt;
/* 38 */     this.xElements = new double[paramInt];
/* 39 */     this.yElements = new double[paramInt];
/* 40 */     this.zElements = new double[paramInt];
/* 41 */     this.xList = new DoubleArrayList(this.xElements);
/* 42 */     this.yList = new DoubleArrayList(this.yElements);
/* 43 */     this.zList = new DoubleArrayList(this.zElements);
/* 44 */     this.size = 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void add(double paramDouble1, double paramDouble2, double paramDouble3)
/*    */   {
/* 54 */     if (this.size == this.capacity) flush();
/* 55 */     this.xElements[this.size] = paramDouble1;
/* 56 */     this.yElements[this.size] = paramDouble2;
/* 57 */     this.zElements[(this.size++)] = paramDouble3;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void addAllOf(DoubleArrayList paramDoubleArrayList1, DoubleArrayList paramDoubleArrayList2, DoubleArrayList paramDoubleArrayList3)
/*    */   {
/* 66 */     int i = paramDoubleArrayList1.size();
/* 67 */     if (this.size + i >= this.capacity) flush();
/* 68 */     this.target.addAllOf(paramDoubleArrayList1, paramDoubleArrayList2, paramDoubleArrayList3);
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/buffer/DoubleBuffer3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */