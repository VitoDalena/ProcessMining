/*     */ package org.deckfour.uitopia.ui.actions;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JPanel;
/*     */ import org.deckfour.uitopia.api.hub.ActionManager;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.api.hub.ResourceManager;
/*     */ import org.deckfour.uitopia.api.hub.TaskManager;
/*     */ import org.deckfour.uitopia.api.model.Action;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.Task;
/*     */ import org.deckfour.uitopia.ui.UITopiaController;
/*     */ import org.deckfour.uitopia.ui.components.ActivityButton;
/*     */ import org.deckfour.uitopia.ui.components.TiledPanel;
/*     */ import org.deckfour.uitopia.ui.components.ViewHeaderBar;
/*     */ import org.deckfour.uitopia.ui.main.Viewable;
/*     */ import org.deckfour.uitopia.ui.util.ImageLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActionsView
/*     */   extends JPanel
/*     */   implements Viewable
/*     */ {
/*     */   private static final long serialVersionUID = 7735337852528303426L;
/*     */   private UITopiaController controller;
/*     */   private ActionsBrowser actionsBrowser;
/*     */   private ActionsInputBrowser inputBrowser;
/*     */   private ActionsOutputBrowser outputBrowser;
/*     */   private ActivityView activityView;
/*     */   private JPanel actionsView;
/*     */   private boolean activityViewShown;
/*     */   private ActivityButton activityButton;
/*     */   private ViewHeaderBar header;
/*     */   
/*     */   public ActionsView(UITopiaController controller)
/*     */   {
/*  81 */     this.controller = controller;
/*  82 */     this.actionsBrowser = new ActionsBrowser(this);
/*  83 */     this.inputBrowser = new ActionsInputBrowser(this);
/*  84 */     this.outputBrowser = new ActionsOutputBrowser(this);
/*  85 */     this.activityButton = new ActivityButton(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  87 */         ActionsView.this.showActivityView();
/*     */       }
/*  89 */     });
/*  90 */     this.activityButton.setSpinning(false);
/*     */     
/*  92 */     setLayout(new BorderLayout());
/*  93 */     setOpaque(true);
/*  94 */     setBorder(BorderFactory.createEmptyBorder());
/*  95 */     this.actionsView = new JPanel();
/*  96 */     this.actionsView.setLayout(new BorderLayout());
/*  97 */     this.actionsView.setOpaque(true);
/*  98 */     this.actionsView.setBorder(BorderFactory.createEmptyBorder());
/*  99 */     this.header = new ViewHeaderBar("Actions");
/* 100 */     this.header.addComponent(this.activityButton);
/* 101 */     this.actionsView.add(this.header, "North");
/* 102 */     JPanel contents = new TiledPanel(ImageLoader.load("tile_metal.jpg"));
/* 103 */     contents.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
/* 104 */     contents.setLayout(new BoxLayout(contents, 0));
/* 105 */     contents.add(Box.createHorizontalGlue());
/* 106 */     contents.add(this.inputBrowser);
/* 107 */     contents.add(this.actionsBrowser);
/* 108 */     contents.add(this.outputBrowser);
/* 109 */     contents.add(Box.createHorizontalGlue());
/* 110 */     this.actionsView.add(contents, "Center");
/* 111 */     this.activityViewShown = false;
/* 112 */     add(this.actionsView, "Center");
/* 113 */     this.actionsBrowser.updateFilter();
/* 114 */     this.activityView = new ActivityView(controller.getFrameworkHub().getTaskManager(), new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 117 */         ActionsView.this.showActionsView();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public ViewHeaderBar getHeader()
/*     */   {
/* 124 */     return this.header;
/*     */   }
/*     */   
/*     */   public void showActivityView() {
/* 128 */     updateActivity();
/* 129 */     removeAll();
/* 130 */     add(this.activityView, "Center");
/* 131 */     this.activityViewShown = true;
/* 132 */     revalidate();
/* 133 */     repaint();
/*     */   }
/*     */   
/*     */   public void showActionsView() {
/* 137 */     removeAll();
/* 138 */     add(this.actionsView, "Center");
/* 139 */     this.activityViewShown = false;
/* 140 */     revalidate();
/* 141 */     repaint();
/*     */   }
/*     */   
/*     */   public void setInputResource(Resource input) {
/* 145 */     this.inputBrowser.reset();
/* 146 */     this.outputBrowser.reset();
/* 147 */     this.actionsBrowser.reset();
/* 148 */     this.inputBrowser.setConstraint(input);
/* 149 */     inputConstraintsUpdated();
/*     */   }
/*     */   
/*     */   public void reset() {
/* 153 */     this.inputBrowser.reset();
/* 154 */     this.outputBrowser.reset();
/* 155 */     this.actionsBrowser.reset();
/*     */   }
/*     */   
/*     */   public void actionSelected(Action action) {
/* 159 */     if (action != null) {
/* 160 */       this.inputBrowser.setParameters(action.getInput());
/* 161 */       this.outputBrowser.setParameters(action.getOutput());
/*     */     }
/*     */   }
/*     */   
/*     */   public void inputParametersUpdated() {
/* 166 */     this.actionsBrowser.checkExecutability();
/*     */   }
/*     */   
/*     */   public void inputConstraintsUpdated() {
/* 170 */     this.actionsBrowser.updateFilter();
/*     */   }
/*     */   
/*     */   public void outputConstraintsUpdated() {
/* 174 */     this.actionsBrowser.updateFilter();
/*     */   }
/*     */   
/*     */   public ActionsBrowser getActionsBrowser() {
/* 178 */     return this.actionsBrowser;
/*     */   }
/*     */   
/*     */   public ActionsInputBrowser getInputBrowser() {
/* 182 */     return this.inputBrowser;
/*     */   }
/*     */   
/*     */   public ActionsOutputBrowser getOutputBrowser() {
/* 186 */     return this.outputBrowser;
/*     */   }
/*     */   
/*     */   public UITopiaController getController() {
/* 190 */     return this.controller;
/*     */   }
/*     */   
/*     */   public ActionManager<? extends Action> getActionManager() {
/* 194 */     return this.controller.getFrameworkHub().getActionManager();
/*     */   }
/*     */   
/*     */   public ResourceManager<? extends Resource> getResourceManager() {
/* 198 */     return this.controller.getFrameworkHub().getResourceManager();
/*     */   }
/*     */   
/*     */   public void updateActivity() {
/* 202 */     if (this.controller.getFrameworkHub().getTaskManager().getActiveTasks().size() > 0)
/*     */     {
/* 204 */       this.activityButton.setSpinning(true);
/*     */     } else {
/* 206 */       this.activityButton.setSpinning(false);
/*     */     }
/* 208 */     this.activityView.update();
/*     */   }
/*     */   
/*     */   public void updateTaskLog(Task<?> task, String message) {
/* 212 */     this.activityView.log(task, message);
/*     */   }
/*     */   
/*     */   public void viewFocusGained() {
/* 216 */     this.inputBrowser.updateFields();
/* 217 */     this.actionsBrowser.setFocus();
/*     */   }
/*     */   
/*     */   public void viewFocusLost() {
/* 221 */     if (this.activityViewShown) {
/* 222 */       showActionsView();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/actions/ActionsView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */