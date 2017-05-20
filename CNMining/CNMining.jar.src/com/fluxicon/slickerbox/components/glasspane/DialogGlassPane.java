/*    */ package com.fluxicon.slickerbox.components.glasspane;
/*    */ 
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.event.KeyEvent;
/*    */ import java.awt.event.KeyListener;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DialogGlassPane
/*    */   extends GlassCenteredPane
/*    */ {
/*    */   protected SimpleDialogInnerPanel innerPanel;
/*    */   
/*    */   public DialogGlassPane(ActionListener closeListener)
/*    */   {
/* 64 */     super(new SimpleDialogInnerPanel("title", "message"), closeListener);
/* 65 */     this.innerPanel = ((SimpleDialogInnerPanel)this.dialogPane);
/* 66 */     this.innerPanel.setParent(this);
/* 67 */     addMouseListener(new MouseListener()
/*    */     {
/* 69 */       public void mouseClicked(MouseEvent arg0) { DialogGlassPane.this.fadeOut(); }
/*    */       
/*    */       public void mouseEntered(MouseEvent arg0) {}
/*    */       
/*    */       public void mouseExited(MouseEvent arg0) {}
/*    */       
/*    */       public void mousePressed(MouseEvent arg0) {}
/* 76 */       public void mouseReleased(MouseEvent arg0) {} });
/* 77 */     addKeyListener(new KeyListener() { public void keyPressed(KeyEvent evt) {}
/*    */       
/*    */       public void keyReleased(KeyEvent evt) {}
/*    */       
/* 81 */       public void keyTyped(KeyEvent evt) { if ((evt.getKeyChar() == '\036') || 
/* 82 */           (evt.getKeyChar() == '\n') || 
/* 83 */           (evt.getKeyChar() == '\033')) {
/* 84 */           DialogGlassPane.this.fadeOut();
/*    */         }
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */   public void showDialog(String title, String message) {
/* 91 */     this.innerPanel.setTitle(title);
/* 92 */     this.innerPanel.setMessage(message);
/* 93 */     revalidate();
/* 94 */     super.fadeIn();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/glasspane/DialogGlassPane.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */