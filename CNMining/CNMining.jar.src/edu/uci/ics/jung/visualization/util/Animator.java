/*    */ package edu.uci.ics.jung.visualization.util;
/*    */ 
/*    */ import edu.uci.ics.jung.algorithms.util.IterativeContext;
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
/*    */ public class Animator
/*    */   implements Runnable
/*    */ {
/*    */   protected IterativeContext process;
/*    */   protected boolean stop;
/*    */   protected Thread thread;
/* 29 */   protected long sleepTime = 10L;
/*    */   
/*    */   public Animator(IterativeContext process)
/*    */   {
/* 33 */     this(process, 10L);
/*    */   }
/*    */   
/*    */   public Animator(IterativeContext process, long sleepTime) {
/* 37 */     this.process = process;
/* 38 */     this.sleepTime = sleepTime;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public long getSleepTime()
/*    */   {
/* 45 */     return this.sleepTime;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void setSleepTime(long sleepTime)
/*    */   {
/* 52 */     this.sleepTime = sleepTime;
/*    */   }
/*    */   
/*    */   public void start()
/*    */   {
/* 57 */     stop();
/* 58 */     this.stop = false;
/* 59 */     this.thread = new Thread(this);
/* 60 */     this.thread.setPriority(1);
/* 61 */     this.thread.start();
/*    */   }
/*    */   
/*    */   public synchronized void stop() {
/* 65 */     this.stop = true;
/*    */   }
/*    */   
/*    */   public void run() {
/* 69 */     while ((!this.process.done()) && (!this.stop))
/*    */     {
/* 71 */       this.process.step();
/*    */       
/* 73 */       if (this.stop) {
/* 74 */         return;
/*    */       }
/*    */       try {
/* 77 */         Thread.sleep(this.sleepTime);
/*    */       }
/*    */       catch (InterruptedException ie) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/util/Animator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */