/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractPopupGraphMousePlugin
/*    */   extends AbstractGraphMousePlugin
/*    */   implements MouseListener
/*    */ {
/*    */   public AbstractPopupGraphMousePlugin()
/*    */   {
/* 18 */     this(4);
/*    */   }
/*    */   
/* 21 */   public AbstractPopupGraphMousePlugin(int modifiers) { super(modifiers); }
/*    */   
/*    */   public void mousePressed(MouseEvent e) {
/* 24 */     if (e.isPopupTrigger()) {
/* 25 */       handlePopup(e);
/* 26 */       e.consume();
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void mouseReleased(MouseEvent e)
/*    */   {
/* 35 */     if (e.isPopupTrigger()) {
/* 36 */       handlePopup(e);
/* 37 */       e.consume();
/*    */     }
/*    */   }
/*    */   
/*    */   protected abstract void handlePopup(MouseEvent paramMouseEvent);
/*    */   
/*    */   public void mouseClicked(MouseEvent e) {}
/*    */   
/*    */   public void mouseEntered(MouseEvent e) {}
/*    */   
/*    */   public void mouseExited(MouseEvent e) {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/AbstractPopupGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */