/*    */ package org.deckfour.uitopia.ui.util;
/*    */ 
/*    */ import java.awt.AlphaComposite;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Image;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JLabel;
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
/*    */ public class LinkLabel
/*    */   extends JLabel
/*    */ {
/*    */   private static final long serialVersionUID = -2524875784323563161L;
/*    */   private boolean mouseOver;
/*    */   private Image image;
/*    */   
/*    */   public LinkLabel(Image image, final String link)
/*    */   {
/* 58 */     super(new ImageIcon(image));
/* 59 */     this.image = image;
/* 60 */     this.mouseOver = false;
/* 61 */     addMouseListener(new MouseAdapter()
/*    */     {
/* 63 */       public void mouseClicked(MouseEvent e) { BrowserLauncher.openURL(link); }
/*    */       
/*    */       public void mouseEntered(MouseEvent e) {
/* 66 */         LinkLabel.this.mouseOver = true;
/* 67 */         LinkLabel.this.repaint();
/*    */       }
/*    */       
/* 70 */       public void mouseExited(MouseEvent e) { LinkLabel.this.mouseOver = false;
/* 71 */         LinkLabel.this.repaint();
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void paintComponent(Graphics g)
/*    */   {
/* 82 */     Graphics2D g2d = (Graphics2D)g.create();
/* 83 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 84 */     int x = (getWidth() - this.image.getWidth(null)) / 2;
/* 85 */     int y = (getHeight() - this.image.getHeight(null)) / 2;
/* 86 */     if (!this.mouseOver) {
/* 87 */       g2d.setComposite(AlphaComposite.getInstance(3, 0.8F));
/*    */     }
/* 89 */     g2d.drawImage(this.image, x, y, null);
/* 90 */     g2d.dispose();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/util/LinkLabel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */