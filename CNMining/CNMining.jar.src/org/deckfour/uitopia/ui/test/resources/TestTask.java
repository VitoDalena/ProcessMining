/*     */ package org.deckfour.uitopia.ui.test.resources;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.deckfour.uitopia.api.event.TaskListener;
/*     */ import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
/*     */ import org.deckfour.uitopia.api.hub.ResourceManager;
/*     */ import org.deckfour.uitopia.api.model.Action;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.Task;
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
/*     */ public class TestTask
/*     */   implements Task<TestResource>
/*     */ {
/*  50 */   private static Random rnd = new Random();
/*     */   
/*     */   private TestFrameworkHub hub;
/*     */   
/*     */   private Action action;
/*     */   private List<Collection<TestResource>> parameterValues;
/*     */   private double progress;
/*     */   private boolean enabled;
/*     */   private TaskListener listener;
/*     */   
/*     */   public TestTask(TestFrameworkHub hub, Action action, List<Collection<TestResource>> parameterValues, TaskListener listener)
/*     */   {
/*  62 */     this.hub = hub;
/*  63 */     this.action = action;
/*  64 */     this.parameterValues = parameterValues;
/*  65 */     this.progress = 0.0D;
/*  66 */     this.listener = listener;
/*  67 */     this.enabled = true;
/*     */   }
/*     */   
/*     */   public void start() {
/*  71 */     final TestTask parent = this;
/*  72 */     Thread simulationThread = new Thread() {
/*     */       public void run() {
/*  74 */         int count = TestTask.rnd.nextInt(3) + 1;
/*  75 */         for (int i = 1; i < count; i++) {
/*  76 */           if (TestTask.this.listener.showConfiguration("Dialog " + count + " from " + TestTask.this.action.getName(), new TestPanel()).equals(TaskListener.InteractionResult.CANCEL))
/*     */           {
/*     */ 
/*  79 */             parent.destroy();
/*     */           }
/*     */           try
/*     */           {
/*  83 */             Thread.sleep(1000L);
/*     */           }
/*     */           catch (InterruptedException e) {
/*  86 */             e.printStackTrace();
/*     */           }
/*     */         }
/*  89 */         while (TestTask.this.progress < 1.0D) {
/*  90 */           TestTask.access$318(TestTask.this, TestTask.rnd.nextDouble() * 0.025D);
/*  91 */           if (TestTask.this.progress > 1.0D) {
/*  92 */             TestTask.this.progress = 1.0D;
/*     */           }
/*  94 */           TestTask.this.listener.debug("TEST");
/*  95 */           TestTask.this.listener.updateProgress(TestTask.this.progress);
/*     */           try {
/*  97 */             Thread.sleep(250L);
/*     */           }
/*     */           catch (InterruptedException e) {
/* 100 */             e.printStackTrace();
/*     */           }
/*     */         }
/*     */         
/* 104 */         if (TestTask.this.enabled) {
/* 105 */           TestResource res = TestResourceFactory.generateResource();
/* 106 */           res.setName(res.getName() + "*");
/* 107 */           TestTask.this.hub.getResourceManager().getAllResources().add(res);
/* 108 */           TestTask.this.listener.completed(new Resource[] { res });
/*     */         }
/*     */       }
/* 111 */     };
/* 112 */     simulationThread.start();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void destroy()
/*     */   {
/* 121 */     this.enabled = false;
/* 122 */     this.progress = 1.0D;
/* 123 */     this.listener.updateProgress(1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Action getAction()
/*     */   {
/* 132 */     return this.action;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Collection<TestResource>> getParameterValues()
/*     */   {
/* 141 */     return this.parameterValues;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getProgress()
/*     */   {
/* 150 */     return this.progress;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */