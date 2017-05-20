/*    */ package edu.oswego.cs.dl.util.concurrent;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
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
/*    */ public class Slot
/*    */   extends SemaphoreControlledChannel
/*    */ {
/*    */   public Slot(Class paramClass)
/*    */     throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException
/*    */   {
/* 52 */     super(1, paramClass);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public Slot()
/*    */   {
/* 59 */     super(1);
/*    */   }
/*    */   
/*    */ 
/* 63 */   protected Object item_ = null;
/*    */   
/*    */ 
/*    */   protected synchronized void insert(Object paramObject)
/*    */   {
/* 68 */     this.item_ = paramObject;
/*    */   }
/*    */   
/*    */   protected synchronized Object extract()
/*    */   {
/* 73 */     Object localObject = this.item_;
/* 74 */     this.item_ = null;
/* 75 */     return localObject;
/*    */   }
/*    */   
/*    */   public synchronized Object peek() {
/* 79 */     return this.item_;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/Slot.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */