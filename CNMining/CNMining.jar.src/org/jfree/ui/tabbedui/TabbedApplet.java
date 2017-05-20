/*     */ package org.jfree.ui.tabbedui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.JApplet;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TabbedApplet
/*     */   extends JApplet
/*     */ {
/*     */   private AbstractTabbedUI tabbedUI;
/*     */   
/*     */   private class MenuBarChangeListener
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     public MenuBarChangeListener() {}
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent evt)
/*     */     {
/*  75 */       if (evt.getPropertyName().equals("jMenuBar")) {
/*  76 */         TabbedApplet.this.setJMenuBar(TabbedApplet.this.getTabbedUI().getJMenuBar());
/*     */       }
/*     */     }
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
/*     */   protected final AbstractTabbedUI getTabbedUI()
/*     */   {
/*  98 */     return this.tabbedUI;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void init(AbstractTabbedUI tabbedUI)
/*     */   {
/* 107 */     this.tabbedUI = tabbedUI;
/* 108 */     this.tabbedUI.addPropertyChangeListener("jMenuBar", new MenuBarChangeListener());
/*     */     
/*     */ 
/* 111 */     JPanel panel = new JPanel();
/* 112 */     panel.setLayout(new BorderLayout());
/* 113 */     panel.add(tabbedUI, "Center");
/* 114 */     setContentPane(panel);
/* 115 */     setJMenuBar(tabbedUI.getJMenuBar());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/tabbedui/TabbedApplet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */