/*     */ package org.deckfour.xes.util.progress;
/*     */ 
/*     */ import javax.swing.JProgressBar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XProgressBarListener
/*     */   implements XProgressListener
/*     */ {
/*     */   protected JProgressBar progressBar;
/*     */   protected int startValue;
/*     */   protected int stopValue;
/*     */   
/*     */   public XProgressBarListener(JProgressBar progressBar)
/*     */   {
/*  78 */     this(progressBar, progressBar.getMinimum(), progressBar.getMaximum());
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
/*     */   public XProgressBarListener(JProgressBar progressBar, int startValue, int stopValue)
/*     */   {
/*  95 */     this.progressBar = progressBar;
/*  96 */     this.startValue = startValue;
/*  97 */     this.stopValue = stopValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void updateProgress(int progress, int maxProgress)
/*     */   {
/* 104 */     int increment = (int)((this.stopValue - this.startValue) * (maxProgress / progress));
/* 105 */     this.progressBar.setValue(this.startValue + increment);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isAborted()
/*     */   {
/* 112 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/util/progress/XProgressBarListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */