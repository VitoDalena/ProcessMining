/*     */ package org.deckfour.uitopia.ui.workspace;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JPanel;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.api.hub.ResourceManager;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.ui.UITopiaController;
/*     */ import org.deckfour.uitopia.ui.components.TiledPanel;
/*     */ import org.deckfour.uitopia.ui.components.ViewHeaderBar;
/*     */ import org.deckfour.uitopia.ui.components.ViewHeaderButton;
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
/*     */ public class WorkspaceView
/*     */   extends JPanel
/*     */   implements Viewable
/*     */ {
/*     */   private static final long serialVersionUID = 7445448707084672645L;
/*     */   private ViewHeaderBar header;
/*     */   private JPanel contents;
/*     */   private WorkspaceBrowserPanel browser;
/*     */   
/*     */   public WorkspaceView(final UITopiaController controller)
/*     */   {
/*  66 */     setLayout(new BorderLayout());
/*  67 */     setOpaque(true);
/*  68 */     setBorder(BorderFactory.createEmptyBorder());
/*  69 */     this.header = new ViewHeaderBar("Workspace");
/*  70 */     ViewHeaderButton importButton = new ViewHeaderButton(ImageLoader.load("import_30x30_white.png"), "import...");
/*     */     
/*  72 */     importButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  74 */         Thread t = new Thread(new Runnable()
/*     */         {
/*     */           public void run() {
/*     */             try {
/*  78 */               WorkspaceView.1.this.val$controller.getFrameworkHub().getResourceManager().importResource();
/*     */             }
/*     */             catch (IOException e1)
/*     */             {
/*  82 */               e1.printStackTrace();
/*     */             }
/*     */             
/*     */           }
/*     */           
/*  87 */         });
/*  88 */         t.start();
/*     */       }
/*  90 */     });
/*  91 */     importButton.setToolTipText("Import resource from file");
/*  92 */     this.header.addComponent(importButton);
/*  93 */     add(this.header, "North");
/*  94 */     this.contents = new TiledPanel(ImageLoader.load("tile_wooden.jpg"));
/*  95 */     this.contents.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
/*  96 */     this.contents.setLayout(new BorderLayout());
/*  97 */     this.browser = new WorkspaceBrowserPanel(controller);
/*  98 */     this.contents.add(this.browser, "Center");
/*  99 */     add(this.contents, "Center");
/*     */   }
/*     */   
/*     */   public ViewHeaderBar getHeader() {
/* 103 */     return this.header;
/*     */   }
/*     */   
/*     */   public void showResource(Resource resource) {
/* 107 */     this.browser.showResource(resource);
/*     */   }
/*     */   
/*     */   public void showParentsOf(Resource resource) {
/* 111 */     this.browser.showParentsOf(resource);
/*     */   }
/*     */   
/*     */   public void showChildrenOf(Resource resource) {
/* 115 */     this.browser.showChildrenOf(resource);
/*     */   }
/*     */   
/*     */   public void updateResources() {
/* 119 */     this.browser.updateResources();
/*     */   }
/*     */   
/*     */ 
/*     */   public void viewFocusGained() {}
/*     */   
/*     */   public void viewFocusLost() {}
/*     */   
/*     */   public void showFavorites()
/*     */   {
/* 129 */     this.browser.showFavorites();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/workspace/WorkspaceView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */