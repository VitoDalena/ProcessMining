/*     */ package org.deckfour.uitopia.ui.workspace;
/*     */ 
/*     */ import com.fluxicon.slickerbox.components.IconVerticalTabbedPane;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.SwingUtilities;
/*     */ import org.deckfour.uitopia.api.event.UpdateListener;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.api.hub.ResourceManager;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.ui.UITopiaController;
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
/*     */ public class WorkspaceBrowserPanel
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = -1467890361679445697L;
/*  58 */   private static final Color BG = new Color(180, 180, 180);
/*  59 */   private static final Color FG = new Color(60, 60, 60);
/*     */   
/*     */   private IconVerticalTabbedPane tabs;
/*     */   private UITopiaController controller;
/*     */   private ResourceListBrowser browserAll;
/*     */   private ResourceListBrowser browserFavorites;
/*     */   private ResourceListBrowser browserImported;
/*     */   private ResourceListBrowser browserSelection;
/*     */   
/*     */   public WorkspaceBrowserPanel(UITopiaController controller)
/*     */   {
/*  70 */     this.controller = controller;
/*  71 */     setLayout(new BorderLayout());
/*  72 */     setOpaque(false);
/*  73 */     setupUI();
/*  74 */     controller.getFrameworkHub().getResourceManager().addListener(new UpdateListener()
/*     */     {
/*     */       public void updated() {
/*  77 */         WorkspaceBrowserPanel.this.updateResources();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void showResource(Resource resource) {
/*  83 */     if (resource.isFavorite()) {
/*  84 */       this.tabs.selectTab("Favorites");
/*  85 */       this.browserFavorites.showResource(resource);
/*     */     } else {
/*  87 */       this.tabs.selectTab("All");
/*  88 */       this.browserAll.showResource(resource);
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateResources() {
/*  93 */     SwingUtilities.invokeLater(new Runnable()
/*     */     {
/*     */       public void run() {
/*  96 */         JComponent tab = WorkspaceBrowserPanel.this.tabs.getSelected();
/*  97 */         if ((tab != null) && ((tab instanceof ResourceListBrowser))) {
/*  98 */           ((ResourceListBrowser)tab).updateData();
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public void showParentsOf(Resource resource) {
/* 105 */     this.browserSelection.setSelectionContent(resource, true);
/* 106 */     this.tabs.selectTab("Selection");
/*     */   }
/*     */   
/*     */   public void showChildrenOf(Resource resource) {
/* 110 */     this.browserSelection.setSelectionContent(resource, false);
/* 111 */     this.tabs.selectTab("Selection");
/*     */   }
/*     */   
/*     */   public void showFavorites() {
/* 115 */     this.tabs.selectTab("Favorites");
/*     */   }
/*     */   
/*     */   private void setupUI() {
/* 119 */     this.browserAll = new ResourceListBrowser(this.controller, ResourceListBrowser.Type.ALL);
/*     */     
/* 121 */     this.browserFavorites = new ResourceListBrowser(this.controller, ResourceListBrowser.Type.FAVORITES);
/*     */     
/* 123 */     this.browserImported = new ResourceListBrowser(this.controller, ResourceListBrowser.Type.IMPORTED);
/*     */     
/* 125 */     this.browserSelection = new ResourceListBrowser(this.controller, ResourceListBrowser.Type.SELECTION);
/*     */     
/*     */ 
/* 128 */     this.tabs = new IconVerticalTabbedPane(FG, BG, 100);
/* 129 */     this.tabs.setPassiveBackground(new Color(140, 140, 140));
/* 130 */     this.tabs.setMouseOverFadeColor(new Color(90, 90, 90));
/*     */     
/* 132 */     this.tabs.addTab("All", ImageLoader.load("workspace_60x60_black.png"), this.browserAll, new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 135 */         WorkspaceBrowserPanel.this.browserAll.updateData();
/*     */       }
/* 137 */     });
/* 138 */     this.tabs.addTab("Favorites", ImageLoader.load("favorites_60x60_black.png"), this.browserFavorites, new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 141 */         WorkspaceBrowserPanel.this.browserFavorites.updateData();
/*     */       }
/* 143 */     });
/* 144 */     this.tabs.addTab("Imported", ImageLoader.load("imported_60x60_black.png"), this.browserImported, new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 147 */         WorkspaceBrowserPanel.this.browserImported.updateData();
/*     */       }
/* 149 */     });
/* 150 */     this.tabs.addTab("Selection", ImageLoader.load("selection_60x60_black.png"), this.browserSelection, new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 153 */         WorkspaceBrowserPanel.this.browserSelection.updateData();
/*     */       }
/*     */       
/* 156 */     });
/* 157 */     add(this.tabs, "Center");
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/workspace/WorkspaceBrowserPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */