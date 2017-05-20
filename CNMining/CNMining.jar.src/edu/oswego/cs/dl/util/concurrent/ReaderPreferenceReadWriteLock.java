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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReaderPreferenceReadWriteLock
/*    */   extends WriterPreferenceReadWriteLock
/*    */ {
/*    */   protected boolean allowReader()
/*    */   {
/* 27 */     return this.activeWriter_ == null;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/ReaderPreferenceReadWriteLock.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */