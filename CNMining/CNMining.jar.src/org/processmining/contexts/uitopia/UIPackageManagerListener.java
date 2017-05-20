/*     */ package org.processmining.contexts.uitopia;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import org.processmining.contexts.uitopia.packagemanager.PMController;
/*     */ import org.processmining.contexts.uitopia.packagemanager.PMFrame;
/*     */ import org.processmining.contexts.uitopia.packagemanager.PMMainView;
/*     */ import org.processmining.framework.boot.Boot;
/*     */ import org.processmining.framework.packages.PackageDescriptor;
/*     */ import org.processmining.framework.packages.PackageManager;
/*     */ import org.processmining.framework.packages.events.PackageManagerListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class UIPackageManagerListener
/*     */   implements PackageManagerListener
/*     */ {
/*     */   private final String[] args;
/*     */   private final PMFrame frame;
/* 134 */   private boolean done = false;
/*     */   
/*     */   public UIPackageManagerListener(PMFrame frame, String[] args) {
/* 137 */     this.frame = frame;
/* 138 */     this.args = args;
/*     */   }
/*     */   
/*     */ 
/*     */   public void exception(Throwable t) {}
/*     */   
/*     */ 
/*     */   public void exception(String exception) {}
/*     */   
/*     */   public void finishedInstall(String packageName, File folder, PackageDescriptor pack) {}
/*     */   
/*     */   public void sessionComplete(boolean error)
/*     */   {
/* 151 */     synchronized (this) {
/* 152 */       this.done = true;
/* 153 */       notifyAll();
/*     */     }
/* 155 */     PackageManager.getInstance().removeListener(this);
/* 156 */     showOverlayAfterInstall();
/*     */   }
/*     */   
/*     */ 
/*     */   public void sessionStart() {}
/*     */   
/*     */ 
/*     */   public void startDownload(String packageName, URL url, PackageDescriptor pack) {}
/*     */   
/*     */   public void startInstall(String packageName, File folder, PackageDescriptor pack) {}
/*     */   
/*     */   public boolean isDone()
/*     */   {
/* 169 */     return this.done;
/*     */   }
/*     */   
/*     */   private void showOverlayAfterInstall() {
/* 173 */     PMMainView overlayable = this.frame.getController().getMainView();
/*     */     
/* 175 */     FirstTimeOverlay dialog = new FirstTimeOverlay(overlayable);
/*     */     
/* 177 */     overlayable.showOverlay(dialog);
/* 178 */     this.frame.saveConfig();
/*     */     try
/*     */     {
/* 181 */       Boot.boot(UI.class, UIPluginContext.class, this.args);
/* 182 */       Boot.setLatestReleaseInstalled();
/*     */     } catch (Exception e1) {
/* 184 */       throw new RuntimeException(e1);
/*     */     } finally {
/* 186 */       this.frame.setVisible(false);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/contexts/uitopia/UIPackageManagerListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */