/*    */ package org.deckfour.uitopia.ui.overlay;
/*    */ 
/*    */ import com.fluxicon.slickerbox.factory.SlickerDecorator;
/*    */ import java.awt.Color;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JScrollBar;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JViewport;
/*    */ import org.deckfour.uitopia.ui.main.MainView;
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
/*    */ public class ActionFeedbackDialog
/*    */   extends TwoButtonOverlayDialog
/*    */ {
/*    */   private static final long serialVersionUID = 1714241836554172716L;
/*    */   
/*    */   public ActionFeedbackDialog(MainView mainView, String title, JComponent payload)
/*    */   {
/* 54 */     super(mainView, title, "Cancel", "Continue", null);
/* 55 */     payload.setOpaque(false);
/* 56 */     JScrollPane scrollpane = new JScrollPane(payload);
/* 57 */     scrollpane.setOpaque(false);
/* 58 */     scrollpane.getViewport().setOpaque(false);
/* 59 */     scrollpane.setBorder(BorderFactory.createEmptyBorder());
/* 60 */     scrollpane.setViewportBorder(BorderFactory.createEmptyBorder());
/* 61 */     scrollpane.setHorizontalScrollBarPolicy(31);
/* 62 */     scrollpane.setVerticalScrollBarPolicy(20);
/* 63 */     SlickerDecorator.instance().decorate(scrollpane.getVerticalScrollBar(), new Color(0, 0, 0, 0), new Color(20, 20, 20), new Color(60, 60, 60));
/*    */     
/* 65 */     scrollpane.getVerticalScrollBar().setOpaque(false);
/* 66 */     super.setPayload(scrollpane);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/overlay/ActionFeedbackDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */