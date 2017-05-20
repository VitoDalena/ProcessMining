/*    */ package edu.oswego.cs.dl.util.concurrent;
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
/*    */ public class BrokenBarrierException
/*    */   extends RuntimeException
/*    */ {
/*    */   public final int index;
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
/*    */   public BrokenBarrierException(int paramInt)
/*    */   {
/* 32 */     this.index = paramInt;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public BrokenBarrierException(int paramInt, String paramString)
/*    */   {
/* 40 */     super(paramString);
/* 41 */     this.index = paramInt;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/BrokenBarrierException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */