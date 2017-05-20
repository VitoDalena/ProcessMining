/*     */ package org.deckfour.uitopia.ui.main;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.api.hub.ResourceManager;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.ResourceFilter;
/*     */ import org.deckfour.uitopia.api.model.ResourceType;
/*     */ import org.deckfour.uitopia.ui.UITopiaController;
/*     */ import org.deckfour.uitopia.ui.actions.ActionsView;
/*     */ import org.deckfour.uitopia.ui.conf.ConfigurationSet;
/*     */ import org.deckfour.uitopia.ui.conf.UIConfiguration;
/*     */ import org.deckfour.uitopia.ui.overlay.ActionFeedbackDialog;
/*     */ import org.deckfour.uitopia.ui.overlay.ActionWizardDialog;
/*     */ import org.deckfour.uitopia.ui.overlay.OverlayEnclosure;
/*     */ import org.deckfour.uitopia.ui.overlay.ResourcePickerDialog;
/*     */ import org.deckfour.uitopia.ui.overlay.ResourceTypePickerDialog;
/*     */ import org.deckfour.uitopia.ui.overlay.ThreeButtonOverlayDialog.Result;
/*     */ import org.deckfour.uitopia.ui.overlay.TwoButtonOverlayDialog;
/*     */ import org.deckfour.uitopia.ui.views.ViewsView;
/*     */ import org.deckfour.uitopia.ui.workspace.WorkspaceView;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MainView
/*     */   extends JPanel
/*     */   implements Overlayable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private UITopiaController controller;
/*     */   private ConfigurationSet conf;
/*     */   private MainToolbar toolbar;
/*     */   private MainViewport viewport;
/*     */   private View activeView;
/*     */   private WorkspaceView workspaceView;
/*     */   private ActionsView actionsView;
/*     */   private ViewsView viewsView;
/*     */   private OverlayEnclosure overlay;
/*     */   
/*     */   public static enum View
/*     */   {
/*  75 */     WORKSPACE,  ACTIONS,  VIEWS;
/*     */     
/*     */     private View() {}
/*     */     
/*     */     public Viewable getViewable() {
/*  80 */       return this.viewable;
/*     */     }
/*     */     
/*     */     public void setViewable(Viewable viewable) {
/*  84 */       this.viewable = viewable;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private Viewable viewable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MainView(UITopiaController controller)
/*     */   {
/* 103 */     this.controller = controller;
/* 104 */     setOpaque(false);
/* 105 */     setBorder(BorderFactory.createEmptyBorder());
/* 106 */     setLayout(new BorderLayout());
/* 107 */     this.viewport = new MainViewport();
/* 108 */     this.toolbar = new MainToolbar(this);
/* 109 */     add(this.toolbar, "North");
/* 110 */     add(this.viewport, "Center");
/* 111 */     JPanel blankView = new JPanel();
/* 112 */     blankView.setBackground(new Color(50, 50, 50));
/* 113 */     show(blankView);
/* 114 */     this.workspaceView = new WorkspaceView(controller);
/* 115 */     View.WORKSPACE.setViewable(this.workspaceView);
/* 116 */     this.actionsView = new ActionsView(controller);
/* 117 */     View.ACTIONS.setViewable(this.actionsView);
/* 118 */     this.viewsView = new ViewsView(controller);
/* 119 */     View.VIEWS.setViewable(this.viewsView);
/* 120 */     this.conf = UIConfiguration.master().getChild(getClass().getCanonicalName());
/*     */     
/* 122 */     this.activeView = View.valueOf(this.conf.get("activeView", "WORKSPACE"));
/* 123 */     showView(this.activeView);
/*     */   }
/*     */   
/*     */   public UITopiaController controller() {
/* 127 */     return this.controller;
/*     */   }
/*     */   
/*     */   public WorkspaceView getWorkspaceView() {
/* 131 */     return this.workspaceView;
/*     */   }
/*     */   
/*     */   public ActionsView getActionsView() {
/* 135 */     return this.actionsView;
/*     */   }
/*     */   
/*     */   public ViewsView getViewsView() {
/* 139 */     return this.viewsView;
/*     */   }
/*     */   
/*     */   public void showWorkspaceView() {
/* 143 */     if (!this.activeView.equals(View.WORKSPACE)) {
/* 144 */       showView(View.WORKSPACE);
/*     */     }
/*     */   }
/*     */   
/*     */   public void showWorkspaceView(Resource selected) {
/* 149 */     this.workspaceView.showResource(selected);
/* 150 */     showWorkspaceView();
/*     */   }
/*     */   
/*     */   public void showActionsView() {
/* 154 */     if (!this.activeView.equals(View.ACTIONS)) {
/* 155 */       showView(View.ACTIONS);
/*     */     }
/*     */   }
/*     */   
/*     */   public void showActionsView(Resource resource) {
/* 160 */     this.actionsView.setInputResource(resource);
/* 161 */     this.actionsView.showActionsView();
/* 162 */     showActionsView();
/*     */   }
/*     */   
/*     */   public void showViewsView() {
/* 166 */     if (!this.activeView.equals(View.VIEWS)) {
/* 167 */       showView(View.VIEWS);
/*     */     }
/*     */   }
/*     */   
/*     */   public void showViewsView(Resource toView) {
/* 172 */     showViewsView(toView, false);
/*     */   }
/*     */   
/*     */   public void showViewsView(Resource toView, boolean updateIfShown) {
/* 176 */     this.viewsView.showResource(toView, updateIfShown);
/* 177 */     showViewsView();
/*     */   }
/*     */   
/*     */   protected void showView(View view) {
/* 181 */     if (this.activeView.getViewable() != view.getViewable()) {
/* 182 */       this.activeView.getViewable().viewFocusLost();
/*     */     }
/* 184 */     if (view.equals(View.WORKSPACE)) {
/* 185 */       show(this.workspaceView);
/* 186 */     } else if (view.equals(View.ACTIONS)) {
/* 187 */       show(this.actionsView);
/* 188 */     } else if (view.equals(View.VIEWS)) {
/* 189 */       show(this.viewsView);
/*     */     }
/* 191 */     this.conf.set("activeView", view.name());
/* 192 */     if (this.activeView.getViewable() != view.getViewable()) {
/* 193 */       view.getViewable().viewFocusGained();
/*     */     }
/* 195 */     this.activeView = view;
/* 196 */     this.toolbar.activateTab(view);
/*     */   }
/*     */   
/*     */   public void show(JComponent view) {
/* 200 */     this.viewport.setView(view);
/*     */   }
/*     */   
/*     */   public void showOverlay(JComponent overlay) {
/* 204 */     final OverlayEnclosure enclosure = new OverlayEnclosure(overlay, 1024, 768);
/*     */     
/* 206 */     SwingUtilities.invokeLater(new Runnable() {
/*     */       public void run() {
/* 208 */         MainView.this.toolbar.setEnabled(false);
/* 209 */         MainView.this.viewport.showOverlay(enclosure);
/*     */       }
/* 211 */     });
/* 212 */     this.overlay = enclosure;
/*     */   }
/*     */   
/*     */   public boolean showOverlayDialog(TwoButtonOverlayDialog dialog) {
/* 216 */     dialog.setMainView(this);
/* 217 */     showOverlay(dialog);
/* 218 */     return dialog.getResultBlocking();
/*     */   }
/*     */   
/*     */   public void hideOverlay() {
/* 222 */     SwingUtilities.invokeLater(new Runnable() {
/*     */       public void run() {
/* 224 */         MainView.this.toolbar.setEnabled(true);
/* 225 */         MainView.this.viewport.hideOverlay();
/*     */       }
/* 227 */     });
/* 228 */     this.overlay = null;
/*     */   }
/*     */   
/*     */   public boolean hideOverlay(JComponent overlay) {
/* 232 */     if ((this.overlay != null) && (this.overlay.getEnclosed() == overlay)) {
/* 233 */       hideOverlay();
/* 234 */       return true;
/*     */     }
/* 236 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public Resource showResourcePickerDialog(String title, ResourceFilter filter, Comparator<Resource> comparator)
/*     */   {
/* 242 */     List<? extends Resource> resources = this.controller.getFrameworkHub().getResourceManager().getAllResources(filter);
/*     */     
/* 244 */     Collections.sort(resources, comparator);
/* 245 */     ResourcePickerDialog dialog = new ResourcePickerDialog(this, title, resources);
/*     */     
/* 247 */     showOverlay(dialog);
/* 248 */     return dialog.getResourceBlocking();
/*     */   }
/*     */   
/*     */   public ResourceType showResourceTypePickerDialog(String title) {
/* 252 */     List<? extends ResourceType> types = this.controller.getFrameworkHub().getResourceManager().getAllSupportedResourceTypes();
/*     */     
/* 254 */     Collections.sort(types, new Comparator() {
/*     */       public int compare(ResourceType o1, ResourceType o2) {
/* 256 */         return o1.getTypeName().compareTo(o2.getTypeName());
/*     */       }
/* 258 */     });
/* 259 */     ResourceTypePickerDialog dialog = new ResourceTypePickerDialog(this, title, types);
/*     */     
/* 261 */     showOverlay(dialog);
/* 262 */     return dialog.getResourceTypeBlocking();
/*     */   }
/*     */   
/*     */   public TaskListener.InteractionResult showActionFeedbackDialog(String title, JComponent payload, boolean wizard, boolean first, boolean last)
/*     */   {
/* 267 */     if (wizard) {
/* 268 */       ActionWizardDialog dialog = new ActionWizardDialog(this, title, first, last, payload);
/*     */       
/* 270 */       showOverlay(dialog);
/* 271 */       ThreeButtonOverlayDialog.Result result = dialog.getResultBlocking();
/* 272 */       if (result.equals(ThreeButtonOverlayDialog.Result.PREVIOUS))
/* 273 */         return TaskListener.InteractionResult.PREV;
/* 274 */       if (result.equals(ThreeButtonOverlayDialog.Result.CONTINUE)) {
/* 275 */         return last ? TaskListener.InteractionResult.FINISHED : TaskListener.InteractionResult.NEXT;
/*     */       }
/*     */       
/* 278 */       return TaskListener.InteractionResult.CANCEL;
/*     */     }
/*     */     
/* 281 */     ActionFeedbackDialog dialog = new ActionFeedbackDialog(this, title, payload);
/*     */     
/* 283 */     showOverlay(dialog);
/* 284 */     if (dialog.getResultBlocking()) {
/* 285 */       return TaskListener.InteractionResult.CONTINUE;
/*     */     }
/* 287 */     return TaskListener.InteractionResult.CANCEL;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/main/MainView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */