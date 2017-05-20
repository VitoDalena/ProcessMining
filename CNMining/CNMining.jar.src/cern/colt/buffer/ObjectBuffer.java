/*    */ package cern.colt.buffer;
/*    */ 
/*    */ import cern.colt.PersistentObject;
/*    */ import cern.colt.list.AbstractList;
/*    */ import cern.colt.list.ObjectArrayList;
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
/*    */ 
/*    */ public class ObjectBuffer
/*    */   extends PersistentObject
/*    */   implements ObjectBufferConsumer
/*    */ {
/*    */   protected ObjectBufferConsumer target;
/*    */   protected Object[] elements;
/*    */   protected ObjectArrayList list;
/*    */   protected int capacity;
/*    */   protected int size;
/*    */   
/*    */   public ObjectBuffer(ObjectBufferConsumer paramObjectBufferConsumer, int paramInt)
/*    */   {
/* 32 */     this.target = paramObjectBufferConsumer;
/* 33 */     this.capacity = paramInt;
/* 34 */     this.elements = new Object[paramInt];
/* 35 */     this.list = new ObjectArrayList(this.elements);
/* 36 */     this.size = 0;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void add(Object paramObject)
/*    */   {
/* 44 */     if (this.size == this.capacity) flush();
/* 45 */     this.elements[(this.size++)] = paramObject;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void addAllOf(ObjectArrayList paramObjectArrayList)
/*    */   {
/* 52 */     int i = paramObjectArrayList.size();
/* 53 */     if (this.size + i >= this.capacity) flush();
/* 54 */     this.target.addAllOf(paramObjectArrayList);
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/buffer/ObjectBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */