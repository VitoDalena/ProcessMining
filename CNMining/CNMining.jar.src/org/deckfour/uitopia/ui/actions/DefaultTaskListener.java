/*     */ package org.deckfour.uitopia.ui.actions;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.swing.JComponent;
/*     */ import org.deckfour.uitopia.api.event.TaskListener;
/*     */ import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.api.hub.TaskManager;
/*     */ import org.deckfour.uitopia.api.hub.ViewManager;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.Task;
/*     */ import org.deckfour.uitopia.ui.UITopiaController;
/*     */ import org.deckfour.uitopia.ui.main.MainView;
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
/*     */ class DefaultTaskListener
/*     */   implements TaskListener
/*     */ {
/*     */   private Task<?> task;
/*     */   private final ActionsView parent;
/*     */   
/*     */   public DefaultTaskListener(ActionsView parent)
/*     */   {
/* 397 */     this.parent = parent;
/*     */   }
/*     */   
/*     */   public void setTask(Task<?> task) {
/* 401 */     this.task = task;
/*     */   }
/*     */   
/*     */   public void completed(Resource... results) {
/* 405 */     if ((results.length > 0) && (results[0] != null)) {
/* 406 */       boolean shown = false;
/* 407 */       for (Resource result : results) {
/* 408 */         if (this.parent.getController().getFrameworkHub().getViewManager().getViewTypes(result).size() > 0)
/*     */         {
/*     */ 
/* 411 */           shown = true;
/* 412 */           this.parent.getController().getMainView().showViewsView(result, true);
/*     */         }
/*     */       }
/*     */       
/* 416 */       if (!shown)
/*     */       {
/* 418 */         this.parent.getController().getMainView().showWorkspaceView(results[0]);
/*     */       }
/*     */     }
/* 421 */     else if (this.parent.getController().getFrameworkHub().getTaskManager().getActiveTasks().size() == 0)
/*     */     {
/*     */ 
/* 424 */       this.parent.showActionsView();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TaskListener.InteractionResult showConfiguration(String title, JComponent configuration)
/*     */   {
/* 433 */     return this.parent.getController().getMainView().showActionFeedbackDialog(title, configuration, false, false, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TaskListener.InteractionResult showWizard(String title, boolean first, boolean last, JComponent configuration)
/*     */   {
/* 442 */     return this.parent.getController().getMainView().showActionFeedbackDialog(title, configuration, true, first, last);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateProgress(double progress)
/*     */   {
/* 450 */     this.parent.updateActivity();
/*     */   }
/*     */   
/*     */   public void debug(String message) {
/* 454 */     this.parent.updateTaskLog(this.task, message);
/*     */   }
/*     */   
/*     */   public void error(String message) {
/* 458 */     this.parent.updateTaskLog(this.task, message);
/*     */   }
/*     */   
/*     */   public void error(String message, Throwable t) {
/* 462 */     this.parent.updateTaskLog(this.task, message);
/*     */   }
/*     */   
/*     */   public void info(String message)
/*     */   {
/* 467 */     this.parent.updateTaskLog(this.task, message);
/*     */   }
/*     */   
/*     */   public void warning(String message) {
/* 471 */     this.parent.updateTaskLog(this.task, message);
/*     */   }
/*     */   
/*     */   public void warning(String message, Throwable t) {
/* 475 */     this.parent.updateTaskLog(this.task, message);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/actions/DefaultTaskListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */