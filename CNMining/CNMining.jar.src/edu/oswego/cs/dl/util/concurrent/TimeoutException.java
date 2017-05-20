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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeoutException
/*    */   extends InterruptedException
/*    */ {
/*    */   public final long duration;
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
/*    */   public TimeoutException(long paramLong)
/*    */   {
/* 37 */     this.duration = paramLong;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public TimeoutException(long paramLong, String paramString)
/*    */   {
/* 45 */     super(paramString);
/* 46 */     this.duration = paramLong;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/TimeoutException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */