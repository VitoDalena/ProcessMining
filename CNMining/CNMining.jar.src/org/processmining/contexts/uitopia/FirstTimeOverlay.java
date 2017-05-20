/*     */ package org.processmining.contexts.uitopia;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 110 */     super(mainView, "Starting ProM", "Cancel", "  OK  ", new JLabel("<html>All packages have been downloaded.<BR>Please wait while starting ProM<BR>for the first time.<BR><BR>If this is the first time you run ProM on this computer, please be patient.</html>"));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 115 */     getCancelButton().setEnabled(true);
/* 116 */     getOKButton().setEnabled(false);
/*     */   }
/*     */   
/*     */ 
/*     */   public void close(boolean okayed)
/*     */   {
/* 122 */     if (!okayed) {
/* 123 */       System.exit(0);
/*     */     }
/* 125 */     super.close(okayed);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/processmining/contexts/uitopia/FirstTimeOverlay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */