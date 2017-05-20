/*    */ package org.deckfour.uitopia.ui.test.resources;
/*    */ 
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JLabel;
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
/*    */ 
/*    */ public class TestPanel
/*    */   extends JComponent
/*    */ {
/*    */   private static final long serialVersionUID = -2531606244328017675L;
/* 56 */   private Color BG1 = new Color(255, 255, 180);
/* 57 */   private Color BG2 = new Color(255, 0, 0);
/*    */   
/*    */   public TestPanel() {
/* 60 */     setOpaque(false);
/* 61 */     setLayout(new BorderLayout());
/* 62 */     JLabel label = new JLabel("Your plugin here!");
/* 63 */     label.setOpaque(true);
/* 64 */     label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
/* 65 */     label.setFont(label.getFont().deriveFont(20.0F).deriveFont(1));
/* 66 */     label.setBackground(this.BG2);
/* 67 */     label.setForeground(this.BG1);
/* 68 */     add(ArrangementHelper.centerHorizontally(ArrangementHelper.centerVertically(label)));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void paintComponent(Graphics g)
/*    */   {
/* 76 */     int width = getWidth();
/* 77 */     int height = getHeight();
/* 78 */     Graphics2D g2d = (Graphics2D)g.create();
/* 79 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 80 */     g2d.setColor(this.BG1);
/* 81 */     g2d.fillRect(0, 0, width, height);
/* 82 */     g2d.setStroke(new BasicStroke(8.0F));
/* 83 */     g2d.setColor(this.BG2);
/* 84 */     int upper = width > height ? width : height;
/* 85 */     upper *= 2;
/* 86 */     int midX = width / 2;
/* 87 */     int midY = height / 2;
/* 88 */     for (int i = 5; i < upper; i += 16)
/*    */     {
/* 90 */       g2d.drawOval(midX - i, midY - i, 2 * i, 2 * i);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/test/resources/TestPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */