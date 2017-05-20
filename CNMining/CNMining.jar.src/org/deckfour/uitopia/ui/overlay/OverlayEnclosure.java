/*    */ package org.deckfour.uitopia.ui.overlay;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Dimension;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JPanel;
/*    */ import org.deckfour.uitopia.ui.util.ArrangementHelper;
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
/*    */ public class OverlayEnclosure
/*    */   extends JPanel
/*    */ {
/*    */   private static final long serialVersionUID = 7901317340620378569L;
/*    */   private JComponent enclosed;
/*    */   
/*    */   public OverlayEnclosure(JComponent enclosed, int maxWidth, int maxHeight)
/*    */   {
/* 54 */     this.enclosed = enclosed;
/* 55 */     setOpaque(false);
/* 56 */     setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 57 */     setLayout(new BorderLayout());
/*    */     
/* 59 */     enclosed.setMaximumSize(new Dimension(maxWidth, maxHeight));
/* 60 */     enclosed.setMinimumSize(new Dimension(600, 600));
/* 61 */     enclosed.setPreferredSize(getPreferredSize());
/*    */     
/* 63 */     add(ArrangementHelper.centerVertically(ArrangementHelper.centerHorizontally(enclosed)));
/*    */     
/*    */ 
/* 66 */     revalidate();
/*    */   }
/*    */   
/*    */ 
/*    */   public Dimension getPreferredSize()
/*    */   {
/* 72 */     Dimension d = this.enclosed.getPreferredSize();
/* 73 */     return new Dimension(Math.min(Math.max(d.width, 600), 1024), Math.min(Math.max(d.height, 600), 768));
/*    */   }
/*    */   
/*    */   public Dimension getMinimumSize()
/*    */   {
/* 78 */     return getPreferredSize();
/*    */   }
/*    */   
/*    */   public Dimension getMaximumSize() {
/* 82 */     return getPreferredSize();
/*    */   }
/*    */   
/*    */   public JComponent getEnclosed() {
/* 86 */     return this.enclosed;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/overlay/OverlayEnclosure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */