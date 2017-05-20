/*     */ package org.deckfour.uitopia.ui.views;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingWorker;
/*     */ import org.deckfour.uitopia.api.event.UpdateListener;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.api.hub.ViewManager;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.View;
/*     */ import org.deckfour.uitopia.api.model.ViewType;
/*     */ import org.deckfour.uitopia.ui.UITopiaController;
/*     */ import org.deckfour.uitopia.ui.main.Viewable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ViewsView
/*     */   extends JPanel
/*     */   implements Viewable
/*     */ {
/*     */   private static final long serialVersionUID = 5946573782649186241L;
/*     */   private UITopiaController controller;
/*     */   private ViewGrid grid;
/*  65 */   private View view = null;
/*     */   private ViewDetail detail;
/*     */   
/*     */   public ViewsView(UITopiaController controller)
/*     */   {
/*  70 */     this.controller = controller;
/*  71 */     setLayout(new BorderLayout());
/*  72 */     setBorder(BorderFactory.createEmptyBorder());
/*  73 */     setOpaque(true);
/*  74 */     this.grid = new ViewGrid(controller.getFrameworkHub().getViewManager(), this);
/*     */     
/*  76 */     add(this.grid, "Center");
/*     */     
/*  78 */     ViewManager vm = controller.getFrameworkHub().getViewManager();
/*  79 */     vm.addListener(new UpdateListener() {
/*     */       public void updated() {
/*  81 */         ViewsView.this.updateViews();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void showFullScreen(final View view) {
/*  87 */     this.view = view;
/*  88 */     this.detail = new ViewDetail(this, view);
/*  89 */     synchronized (view) {
/*  90 */       removeAll();
/*     */     }
/*  92 */     add(this.detail, "Center");
/*  93 */     revalidate();
/*  94 */     repaint();
/*  95 */     this.detail.addComponentListener(new ComponentListener()
/*     */     {
/*     */       public void componentHidden(ComponentEvent e) {
/*  98 */         view.captureNow();
/*     */       }
/*     */       
/*     */       public void componentMoved(ComponentEvent e) {}
/*     */       
/*     */       public void componentResized(ComponentEvent e)
/*     */       {
/* 105 */         view.captureNow();
/*     */       }
/*     */       
/*     */       public void componentShown(ComponentEvent e) {
/* 109 */         view.captureNow();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void showOverview() {
/* 115 */     removeAll();
/* 116 */     add(this.grid, "Center");
/* 117 */     revalidate();
/* 118 */     repaint();
/* 119 */     this.grid.updateItems();
/* 120 */     this.view = null;
/*     */   }
/*     */   
/*     */   public void removeView(View view) {
/* 124 */     this.controller.getFrameworkHub().getViewManager().removeView(view);
/* 125 */     updateViews();
/*     */   }
/*     */   
/*     */   public void showResource(Resource resource, boolean updateIfShown) {
/* 129 */     final ViewManager vm = this.controller.getFrameworkHub().getViewManager();
/* 130 */     for (View view : vm.getViews()) {
/* 131 */       if (view.getResource().equals(resource)) {
/* 132 */         if (updateIfShown) {
/* 133 */           view.refresh();
/*     */         }
/* 135 */         showFullScreen(view);
/* 136 */         return;
/*     */       }
/*     */     }
/* 139 */     List<ViewType> vt = vm.getViewTypes(resource);
/* 140 */     if (vt.isEmpty()) {
/* 141 */       return;
/*     */     }
/*     */     
/* 144 */     final View view = ((ViewType)vt.get(0)).createView(resource);
/*     */     
/* 146 */     SwingWorker<?, ?> worker = new SwingWorker()
/*     */     {
/*     */       protected Object doInBackground() throws Exception
/*     */       {
/* 150 */         synchronized (view) {
/* 151 */           while (!view.isReady()) {
/*     */             try {
/* 153 */               view.wait();
/*     */             }
/*     */             catch (InterruptedException e) {}
/*     */           }
/*     */         }
/*     */         
/* 159 */         return null;
/*     */       }
/*     */       
/*     */       protected void done()
/*     */       {
/* 164 */         vm.addView(view);
/* 165 */         ViewsView.this.updateViews();
/* 166 */         ViewsView.this.showFullScreen(view);
/*     */       }
/*     */       
/* 169 */     };
/* 170 */     worker.execute();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateViews()
/*     */   {
/* 195 */     this.grid.updateItems();
/*     */   }
/*     */   
/*     */   public UITopiaController getController() {
/* 199 */     return this.controller;
/*     */   }
/*     */   
/*     */   public void viewFocusGained() {}
/*     */   
/*     */   public void viewFocusLost()
/*     */   {
/* 206 */     if (this.view != null) {
/* 207 */       this.view.captureNow();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/views/ViewsView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */