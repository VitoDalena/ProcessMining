/*    */ package org.processmining.plugins.core;
/*    */ 
/*    */ import org.deckfour.uitopia.ui.util.ImageLoader;
/*    */ import org.processmining.contexts.uitopia.UI;
/*    */ import org.processmining.contexts.uitopia.UIPluginContext;
/*    */ import org.processmining.contexts.uitopia.packagemanager.PMController;
/*    */ import org.processmining.contexts.uitopia.packagemanager.PMFrame;
/*    */ import org.processmining.contexts.uitopia.packagemanager.PMMainView;
/*    */ import org.processmining.contexts.uitopia.packagemanager.PMPackage;
/*    */ import org.processmining.contexts.uitopia.packagemanager.PMPackage.PMStatus;
/*    */ import org.processmining.framework.boot.Boot;
/*    */ import org.processmining.framework.packages.PackageManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Main
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 33 */     if (!Boot.isLatestReleaseInstalled()) {
/* 34 */       Boot.setReleaseInstalled("", "");
/* 35 */       PMFrame frame = (PMFrame)Boot.boot(PMFrame.class, new String[0]);
/* 36 */       frame.setIconImage(ImageLoader.load("prom_icon_32x32.png"));
/*    */       
/* 38 */       PMPackage releasePackage = frame.getController().selectPackage(Boot.RELEASE_PACKAGE);
/* 39 */       if (releasePackage == null) {
/* 40 */         Boot.boot(UI.class, UIPluginContext.class, args);
/* 41 */         throw new Exception("Cannot find release package defined in ProM.ini file: " + Boot.RELEASE_PACKAGE + 
/* 42 */           ". Continuing to load ProM.");
/*    */       }
/*    */       
/* 45 */       if (releasePackage.getStatus() == PMPackage.PMStatus.TOUNINSTALL)
/*    */       {
/*    */ 
/* 48 */         Boot.setLatestReleaseInstalled();
/* 49 */         Boot.boot(UI.class, UIPluginContext.class, args);
/*    */ 
/*    */       }
/*    */       else
/*    */       {
/* 54 */         UIPackageManagerListener listener = new UIPackageManagerListener(frame, args);
/* 55 */         PackageManager.getInstance().addListener(listener);
/*    */         
/*    */ 
/* 58 */         frame.setVisible(true);
/*    */         
/*    */ 
/* 61 */         frame.getController().update(releasePackage, frame.getController().getMainView().getWorkspaceView());
/*    */         
/*    */ 
/*    */ 
/* 65 */         synchronized (listener) {
/* 66 */           while (!listener.isDone()) {
/* 67 */             listener.wait();
/*    */           }
/*    */           
/*    */         }
/*    */       }
/*    */     }
/*    */     else
/*    */     {
/* 75 */       Boot.boot(UI.class, UIPluginContext.class, args);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/Main.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */