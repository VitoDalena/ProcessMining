/*     */ package org.processmining.plugins.core;
/*     */ 
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import org.deckfour.uitopia.ui.main.Overlayable;
/*     */ import org.deckfour.uitopia.ui.overlay.TwoButtonOverlayDialog;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class FirstTimeOverlay
/*     */   extends TwoButtonOverlayDialog
/*     */ {
/*     */   private static final long serialVersionUID = 494237962617678531L;
/*     */   
/*     */   public FirstTimeOverlay(Overlayable mainView)
/*     */   {
/*  91 */     super(mainView, "Starting ProM", "Cancel", "  OK  ", new JLabel("<html>All packages have been downloaded.<BR>Please wait while starting ProM<BR>for the first time.<BR><BR>If this is the first time you run ProM on this computer, please be patient.</html>"));
/*     */     
/*     */ 
/*     */ 
/*  95 */     getCancelButton().setEnabled(true);
/*  96 */     getOKButton().setEnabled(false);
/*     */   }
/*     */   
/*     */ 
/*     */   public void close(boolean okayed)
/*     */   {
/* 102 */     if (!okayed) {
/* 103 */       System.exit(0);
/*     */     }
/* 105 */     super.close(okayed);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/plugins/core/FirstTimeOverlay.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */