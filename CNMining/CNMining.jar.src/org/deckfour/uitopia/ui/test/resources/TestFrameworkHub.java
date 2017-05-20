/*     */ package org.deckfour.uitopia.ui.test.resources;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.deckfour.uitopia.api.event.TaskListener;
/*     */ import org.deckfour.uitopia.api.hub.ActionManager;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.api.hub.ResourceManager;
/*     */ import org.deckfour.uitopia.api.hub.TaskManager;
/*     */ import org.deckfour.uitopia.api.hub.ViewManager;
/*     */ import org.deckfour.uitopia.api.model.Author;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.View;
/*     */ import org.deckfour.uitopia.api.model.ViewType;
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
/*     */ public class TestFrameworkHub
/*     */   implements FrameworkHub<TestAction, TestTask, TestResource, TestResource>
/*     */ {
/*     */   private TestResourceManager resourceManager;
/*     */   private TestActionManager actionManager;
/*     */   private TestViewManager viewManager;
/*     */   private TestTaskManager taskManager;
/*     */   
/*     */   public TestFrameworkHub()
/*     */   {
/*  64 */     Random rnd = new Random();
/*  65 */     this.resourceManager = new TestResourceManager(20 + rnd.nextInt(40));
/*  66 */     this.actionManager = new TestActionManager(this.resourceManager.getAllSupportedResourceTypes(), 10 + rnd.nextInt(30));
/*     */     
/*     */ 
/*  69 */     this.viewManager = new TestViewManager(3, this.resourceManager.getAllSupportedResourceTypes());
/*     */     
/*  71 */     this.taskManager = new TestTaskManager(this);
/*     */     
/*  73 */     List<TestResource> resources = this.resourceManager.getAllResources();
/*  74 */     for (int i = 0; i < 10; i++) {
/*  75 */       Resource r = (Resource)resources.get(rnd.nextInt(resources.size()));
/*  76 */       List<ViewType> types = this.viewManager.getViewTypes(r);
/*  77 */       ViewType t = (ViewType)types.get(rnd.nextInt(types.size()));
/*  78 */       View view = new TestView(r.getName(), t, r);
/*  79 */       this.viewManager.addView(view);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ActionManager<TestAction> getActionManager()
/*     */   {
/*  89 */     return this.actionManager;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public List<Author> getFrameworkAuthors()
/*     */   {
/*  98 */     List<Author> authors = new ArrayList();
/*  99 */     authors.add(TestAuthor.DEFAULT_AUTHOR);
/* 100 */     return authors;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getFrameworkReleaseDate()
/*     */   {
/* 109 */     return new Date();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getFrameworkVersion()
/*     */   {
/* 118 */     return "1.0";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ResourceManager<TestResource> getResourceManager()
/*     */   {
/* 127 */     return this.resourceManager;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TaskManager<TestTask, TestResource> getTaskManager()
/*     */   {
/* 136 */     return this.taskManager;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ViewManager getViewManager()
/*     */   {
/* 145 */     return this.viewManager;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shutdown(TaskListener listener)
/*     */   {
/* 156 */     System.out.println("shut down registered.");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestFrameworkHub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */