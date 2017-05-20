/*     */ package org.processmining.plugins.core;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URL;
/*     */ import org.processmining.contexts.uitopia.packagemanager.PMFrame;
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
/*     */ class UIPackageManagerListener
/*     */   implements PackageManagerListener
/*     */ {
/*     */   private final String[] args;
/*     */   private final PMFrame frame;
/* 113 */   private boolean done = false;
/*     */   
/*     */   public UIPackageManagerListener(PMFrame frame, String[] args) {
/* 116 */     this.frame = frame;
/* 117 */     this.args = args;
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
/* 130 */     synchronized (this) {
/* 131 */       this.done = true;
/* 132 */       notifyAll();
/*     */     }
/* 134 */     PackageManager.getInstance().removeListener(this);
/* 135 */     showOverlayAfterInstall();
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
/* 148 */     return this.done;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   private void showOverlayAfterInstall()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 21	org/processmining/plugins/core/UIPackageManagerListener:frame	Lorg/processmining/contexts/uitopia/packagemanager/PMFrame;
/*     */     //   4: invokevirtual 72	org/processmining/contexts/uitopia/packagemanager/PMFrame:getController	()Lorg/processmining/contexts/uitopia/packagemanager/PMController;
/*     */     //   7: invokevirtual 78	org/processmining/contexts/uitopia/packagemanager/PMController:getMainView	()Lorg/processmining/contexts/uitopia/packagemanager/PMMainView;
/*     */     //   10: astore_1
/*     */     //   11: new 84	org/processmining/plugins/core/FirstTimeOverlay
/*     */     //   14: dup
/*     */     //   15: aload_1
/*     */     //   16: invokespecial 86	org/processmining/plugins/core/FirstTimeOverlay:<init>	(Lorg/deckfour/uitopia/ui/main/Overlayable;)V
/*     */     //   19: astore_2
/*     */     //   20: aload_1
/*     */     //   21: aload_2
/*     */     //   22: invokevirtual 89	org/processmining/contexts/uitopia/packagemanager/PMMainView:showOverlay	(Ljavax/swing/JComponent;)V
/*     */     //   25: aload_0
/*     */     //   26: getfield 21	org/processmining/plugins/core/UIPackageManagerListener:frame	Lorg/processmining/contexts/uitopia/packagemanager/PMFrame;
/*     */     //   29: invokevirtual 95	org/processmining/contexts/uitopia/packagemanager/PMFrame:saveConfig	()V
/*     */     //   32: ldc 98
/*     */     //   34: ldc 100
/*     */     //   36: aload_0
/*     */     //   37: getfield 23	org/processmining/plugins/core/UIPackageManagerListener:args	[Ljava/lang/String;
/*     */     //   40: invokestatic 102	org/processmining/framework/boot/Boot:boot	(Ljava/lang/Class;Ljava/lang/Class;[Ljava/lang/String;)V
/*     */     //   43: invokestatic 108	org/processmining/framework/boot/Boot:setLatestReleaseInstalled	()V
/*     */     //   46: goto +26 -> 72
/*     */     //   49: astore_3
/*     */     //   50: new 111	java/lang/RuntimeException
/*     */     //   53: dup
/*     */     //   54: aload_3
/*     */     //   55: invokespecial 113	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
/*     */     //   58: athrow
/*     */     //   59: astore 4
/*     */     //   61: aload_0
/*     */     //   62: getfield 21	org/processmining/plugins/core/UIPackageManagerListener:frame	Lorg/processmining/contexts/uitopia/packagemanager/PMFrame;
/*     */     //   65: iconst_0
/*     */     //   66: invokevirtual 115	org/processmining/contexts/uitopia/packagemanager/PMFrame:setVisible	(Z)V
/*     */     //   69: aload 4
/*     */     //   71: athrow
/*     */     //   72: aload_0
/*     */     //   73: getfield 21	org/processmining/plugins/core/UIPackageManagerListener:frame	Lorg/processmining/contexts/uitopia/packagemanager/PMFrame;
/*     */     //   76: iconst_0
/*     */     //   77: invokevirtual 115	org/processmining/contexts/uitopia/packagemanager/PMFrame:setVisible	(Z)V
/*     */     //   80: return
/*     */     // Line number table:
/*     */     //   Java source line #152	-> byte code offset #0
/*     */     //   Java source line #154	-> byte code offset #11
/*     */     //   Java source line #156	-> byte code offset #20
/*     */     //   Java source line #157	-> byte code offset #25
/*     */     //   Java source line #160	-> byte code offset #32
/*     */     //   Java source line #161	-> byte code offset #43
/*     */     //   Java source line #162	-> byte code offset #46
/*     */     //   Java source line #163	-> byte code offset #50
/*     */     //   Java source line #164	-> byte code offset #59
/*     */     //   Java source line #165	-> byte code offset #61
/*     */     //   Java source line #166	-> byte code offset #69
/*     */     //   Java source line #165	-> byte code offset #72
/*     */     //   Java source line #168	-> byte code offset #80
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	81	0	this	UIPackageManagerListener
/*     */     //   10	11	1	overlayable	org.processmining.contexts.uitopia.packagemanager.PMMainView
/*     */     //   19	3	2	dialog	FirstTimeOverlay
/*     */     //   49	6	3	e1	Exception
/*     */     //   59	11	4	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   32	46	49	java/lang/Exception
/*     */     //   32	59	59	finally
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/UIPackageManagerListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */