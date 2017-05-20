/*     */ package org.jfree.ui.tabbedui;
/*     */ 
/*     */ import javax.swing.JComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RootPanel
/*     */   extends JComponent
/*     */   implements RootEditor
/*     */ {
/*     */   private boolean active;
/*     */   
/*     */   public final boolean isActive()
/*     */   {
/*  70 */     return this.active;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void panelActivated() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void panelDeactivated() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final void setActive(boolean active)
/*     */   {
/*  93 */     if (this.active == active) {
/*  94 */       return;
/*     */     }
/*  96 */     this.active = active;
/*  97 */     if (active) {
/*  98 */       panelActivated();
/*     */     }
/*     */     else {
/* 101 */       panelDeactivated();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JComponent getMainPanel()
/*     */   {
/* 112 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JComponent getToolbar()
/*     */   {
/* 122 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/tabbedui/RootPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */