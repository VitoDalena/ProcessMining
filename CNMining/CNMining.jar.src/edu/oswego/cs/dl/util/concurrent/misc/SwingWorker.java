/*     */ package edu.oswego.cs.dl.util.concurrent.misc;
/*     */ 
/*     */ import edu.oswego.cs.dl.util.concurrent.Callable;
/*     */ import edu.oswego.cs.dl.util.concurrent.FutureResult;
/*     */ import edu.oswego.cs.dl.util.concurrent.ThreadFactory;
/*     */ import edu.oswego.cs.dl.util.concurrent.ThreadFactoryUser;
/*     */ import edu.oswego.cs.dl.util.concurrent.TimedCallable;
/*     */ import edu.oswego.cs.dl.util.concurrent.TimeoutException;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SwingWorker
/*     */   extends ThreadFactoryUser
/*     */   implements Runnable
/*     */ {
/* 119 */   private static final ThreadFactory FACTORY = new ThreadFactory() {
/*     */     public Thread newThread(Runnable paramAnonymousRunnable) {
/* 121 */       Thread localThread = new Thread(paramAnonymousRunnable);
/* 122 */       localThread.setPriority(2);
/* 123 */       return localThread;
/*     */     }
/*     */   };
/*     */   
/*     */ 
/* 128 */   private final FutureResult result = new FutureResult();
/*     */   
/*     */ 
/*     */   private final long timeout;
/*     */   
/*     */   private Thread thread;
/*     */   
/*     */ 
/*     */   public SwingWorker()
/*     */   {
/* 138 */     this(FACTORY, 0L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SwingWorker(long paramLong)
/*     */   {
/* 147 */     this(FACTORY, paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SwingWorker(ThreadFactory paramThreadFactory, long paramLong)
/*     */   {
/* 157 */     setThreadFactory(paramThreadFactory);
/* 158 */     if (paramLong < 0L) {
/* 159 */       throw new IllegalArgumentException("timeout=" + paramLong);
/*     */     }
/* 161 */     this.timeout = paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract Object construct()
/*     */     throws Exception;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void finished() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getTimeout()
/*     */   {
/* 181 */     return this.timeout;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void run()
/*     */   {
/* 191 */     Object localObject = new Callable() {
/*     */       public Object call() throws Exception {
/* 193 */         return SwingWorker.this.construct();
/*     */       }
/*     */       
/* 196 */     };
/* 197 */     Runnable local3 = new Runnable() {
/*     */       public void run() {
/* 199 */         SwingWorker.this.finished();
/*     */       }
/*     */       
/*     */ 
/* 203 */     };
/* 204 */     long l = getTimeout();
/* 205 */     if (l != 0L) {
/* 206 */       TimedCallable localTimedCallable = new TimedCallable((Callable)localObject, l);
/* 207 */       localTimedCallable.setThreadFactory(getThreadFactory());
/* 208 */       localObject = localTimedCallable;
/*     */     }
/*     */     
/* 211 */     this.result.setter((Callable)localObject).run();
/* 212 */     SwingUtilities.invokeLater(local3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void start()
/*     */   {
/* 219 */     if (this.thread == null) {
/* 220 */       this.thread = getThreadFactory().newThread(this);
/*     */     }
/* 222 */     this.thread.start();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void interrupt()
/*     */   {
/* 229 */     if (this.thread != null)
/*     */     {
/*     */       try
/*     */       {
/* 233 */         this.thread.interrupt();
/*     */       } catch (Exception localException) {} }
/* 235 */     this.result.setException(new InterruptedException());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object get()
/*     */     throws InterruptedException, InvocationTargetException
/*     */   {
/* 249 */     return this.result.get();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object timedGet(long paramLong)
/*     */     throws TimeoutException, InterruptedException, InvocationTargetException
/*     */   {
/* 262 */     return this.result.timedGet(paramLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public InvocationTargetException getException()
/*     */   {
/* 273 */     return this.result.getException();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isReady()
/*     */   {
/* 283 */     return this.result.isReady();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/oswego/cs/dl/util/concurrent/misc/SwingWorker.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */