/*    */ package org.deckfour.uitopia.ui.overlay;
/*    */ 
/*    */ import com.fluxicon.slickerbox.factory.SlickerDecorator;
/*    */ import java.awt.Color;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JScrollBar;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JViewport;
/*    */ import org.deckfour.uitopia.ui.components.ImageLozengeButton;
/*    */ import org.deckfour.uitopia.ui.main.MainView;
/*    */ import org.deckfour.uitopia.ui.util.ImageLoader;
/*    */ 
/*    */ public class ActionWizardDialog extends ThreeButtonOverlayDialog
/*    */ {
/*    */   private static final long serialVersionUID = 4089577225721561365L;
/*    */   private final boolean isLast;
/*    */   
/*    */   public ActionWizardDialog(MainView mainView, String title, boolean first, boolean last, JComponent payload)
/*    */   {
/* 21 */     super(mainView, title, "Cancel", "Previous", last ? "Finish" : "Next", payload);
/*    */     
/* 23 */     this.isLast = last;
/* 24 */     if (first) {
/* 25 */       this.prevButton.setEnabled(false);
/*    */     }
/* 27 */     this.contButton.setPreferredSize(this.prevButton.getPreferredSize());
/* 28 */     if (last) {
/* 29 */       this.contButton.setIcon(ImageLoader.load("ok_white_30x30.png"));
/* 30 */       this.contButton.revalidate();
/* 31 */       this.contButton.repaint();
/*    */     }
/* 33 */     payload.setOpaque(false);
/* 34 */     JScrollPane scrollpane = new JScrollPane(payload);
/* 35 */     scrollpane.setOpaque(false);
/* 36 */     scrollpane.getViewport().setOpaque(false);
/* 37 */     scrollpane.setBorder(BorderFactory.createEmptyBorder());
/* 38 */     scrollpane.setViewportBorder(BorderFactory.createEmptyBorder());
/* 39 */     scrollpane.setHorizontalScrollBarPolicy(31);
/*    */     
/* 41 */     scrollpane.setVerticalScrollBarPolicy(20);
/*    */     
/* 43 */     SlickerDecorator.instance().decorate(scrollpane.getVerticalScrollBar(), new Color(0, 0, 0, 0), new Color(20, 20, 20), new Color(60, 60, 60));
/*    */     
/*    */ 
/* 46 */     scrollpane.getVerticalScrollBar().setOpaque(false);
/* 47 */     super.setPayload(scrollpane);
/*    */   }
/*    */   
/*    */   public ThreeButtonOverlayDialog.Result getResultBlocking()
/*    */   {
/* 52 */     while (!this.closed) {
/*    */       try {
/* 54 */         synchronized (this) {
/* 55 */           wait();
/*    */         }
/*    */       }
/*    */       catch (InterruptedException e) {
/* 59 */         e.printStackTrace();
/*    */       }
/*    */     }
/* 62 */     if ((this.isLast) || (getResult().equals(ThreeButtonOverlayDialog.Result.CANCEL))) {
/* 63 */       this.mainView.hideOverlay();
/*    */     }
/* 65 */     return getResult();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/overlay/ActionWizardDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */