/*     */ package org.jfree.ui.tabbedui;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.JDialog;
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
/*     */ 
/*     */ 
/*     */ public class TabbedDialog
/*     */   extends JDialog
/*     */ {
/*     */   private AbstractTabbedUI tabbedUI;
/*     */   public TabbedDialog() {}
/*     */   
/*     */   private class MenuBarChangeListener
/*     */     implements PropertyChangeListener
/*     */   {
/*     */     public MenuBarChangeListener() {}
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent evt)
/*     */     {
/*  84 */       if (evt.getPropertyName().equals("jMenuBar")) {
/*  85 */         TabbedDialog.this.setJMenuBar(TabbedDialog.this.getTabbedUI().getJMenuBar());
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
/*     */   public TabbedDialog(Dialog owner)
/*     */   {
/* 101 */     super(owner);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TabbedDialog(Dialog owner, boolean modal)
/*     */   {
/* 111 */     super(owner, modal);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TabbedDialog(Dialog owner, String title)
/*     */   {
/* 121 */     super(owner, title);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TabbedDialog(Dialog owner, String title, boolean modal)
/*     */   {
/* 132 */     super(owner, title, modal);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TabbedDialog(Frame owner)
/*     */   {
/* 141 */     super(owner);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TabbedDialog(Frame owner, boolean modal)
/*     */   {
/* 151 */     super(owner, modal);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TabbedDialog(Frame owner, String title)
/*     */   {
/* 161 */     super(owner, title);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TabbedDialog(Frame owner, String title, boolean modal)
/*     */   {
/* 172 */     super(owner, title, modal);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final AbstractTabbedUI getTabbedUI()
/*     */   {
/* 183 */     return this.tabbedUI;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void init(AbstractTabbedUI tabbedUI)
/*     */   {
/* 193 */     this.tabbedUI = tabbedUI;
/* 194 */     this.tabbedUI.addPropertyChangeListener("jMenuBar", new MenuBarChangeListener());
/*     */     
/*     */ 
/* 197 */     addWindowListener(new WindowAdapter() {
/*     */       public void windowClosing(WindowEvent e) {
/* 199 */         TabbedDialog.this.getTabbedUI().getCloseAction().actionPerformed(new ActionEvent(this, 1001, null, 0));
/*     */       }
/*     */       
/*     */ 
/* 203 */     });
/* 204 */     JPanel panel = new JPanel();
/* 205 */     panel.setLayout(new BorderLayout());
/* 206 */     panel.add(tabbedUI, "Center");
/* 207 */     setContentPane(panel);
/* 208 */     setJMenuBar(tabbedUI.getJMenuBar());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/tabbedui/TabbedDialog.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */