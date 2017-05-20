/*     */ package org.deckfour.uitopia.ui.test.resources;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.deckfour.uitopia.api.event.TaskListener;
/*     */ import org.deckfour.uitopia.api.hub.TaskManager;
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
/*     */ public class TestTaskManager
/*     */   implements TaskManager<TestTask, TestResource>
/*     */ {
/*     */   private List<TestTask> tasks;
/*     */   private TestFrameworkHub hub;
/*     */   
/*     */   public TestTaskManager(TestFrameworkHub hub)
/*     */   {
/*  55 */     this.hub = hub;
/*  56 */     this.tasks = new ArrayList();
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
/*     */ 
/*     */   public Task<TestResource> execute(Action action, List<Collection<? extends Resource>> parameterValues, TaskListener listener)
/*     */     throws Exception
/*     */   {
/*  71 */     List<Collection<TestResource>> parameters = new ArrayList();
/*  72 */     for (Collection<? extends Resource> col : parameterValues) {
/*  73 */       List<TestResource> l = new ArrayList();
/*  74 */       l.add((TestResource)col.iterator().next());
/*  75 */       parameters.add(l);
/*     */     }
/*  77 */     TestTask task = new TestTask(this.hub, action, parameters, listener);
/*  78 */     this.tasks.add(task);
/*  79 */     task.start();
/*  80 */     return task;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestTask> getActiveTasks()
/*     */   {
/*  89 */     List<TestTask> active = new ArrayList();
/*  90 */     for (TestTask task : this.tasks) {
/*  91 */       if (task.getProgress() < 1.0D) {
/*  92 */         active.add(task);
/*     */       }
/*     */     }
/*  95 */     return active;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<TestTask> getAllTasks()
/*     */   {
/* 104 */     return this.tasks;
/*     */   }
/*     */   
/*     */   public boolean isActionableResource(Resource r)
/*     */   {
/* 109 */     return r instanceof TestResource;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestTaskManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */