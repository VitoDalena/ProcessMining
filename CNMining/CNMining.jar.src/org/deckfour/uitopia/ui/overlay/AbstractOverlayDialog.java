/*    */ package org.deckfour.uitopia.ui.overlay;
/*    */ 
/*    */ import com.fluxicon.slickerbox.util.GraphicsUtilities;
/*    */ import java.awt.AlphaComposite;
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.GradientPaint;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.RoundRectangle2D.Float;
/*    */ import java.awt.image.BufferedImage;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ public abstract class AbstractOverlayDialog
/*    */   extends JPanel
/*    */ {
/* 20 */   private static final Color TITLE_COLOR = new Color(150, 150, 150);
/* 21 */   private static final Color HEADER_COLOR = new Color(40, 40, 40);
/* 22 */   private static final Color BODY_COLOR = new Color(150, 150, 150);
/*    */   
/*    */   private String title;
/*    */   private static final long serialVersionUID = -3038622925123932745L;
/*    */   
/*    */   public AbstractOverlayDialog(String title)
/*    */   {
/* 29 */     this.title = title;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected void paintComponent(Graphics g)
/*    */   {
/* 39 */     int width = getWidth();
/* 40 */     int height = getHeight();
/* 41 */     Graphics2D g2d = (Graphics2D)g.create();
/* 42 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*    */     
/* 44 */     Shape round = new RoundRectangle2D.Float(1.0F, 1.0F, width - 2, height - 2, 60.0F, 60.0F);
/*    */     
/* 46 */     g2d.setColor(new Color(0, 0, 0, 160));
/* 47 */     g2d.fill(round);
/* 48 */     BufferedImage buffer = GraphicsUtilities.createTranslucentCompatibleImage(width, height);
/*    */     
/* 50 */     Graphics2D b2d = buffer.createGraphics();
/* 51 */     b2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*    */     
/* 53 */     b2d.setComposite(AlphaComposite.Clear);
/* 54 */     b2d.fillRect(0, 0, width, height);
/* 55 */     b2d.setComposite(AlphaComposite.Src);
/* 56 */     b2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*    */     
/* 58 */     b2d.setColor(Color.WHITE);
/* 59 */     round = new RoundRectangle2D.Float(20.0F, 20.0F, width - 40, height - 40, 30.0F, 30.0F);
/*    */     
/* 61 */     b2d.fill(round);
/* 62 */     b2d.setComposite(AlphaComposite.SrcAtop);
/* 63 */     b2d.setColor(HEADER_COLOR);
/* 64 */     b2d.fillRect(0, 0, width, 80);
/* 65 */     b2d.setColor(BODY_COLOR);
/* 66 */     b2d.fillRect(0, 80, width, height - 80);
/* 67 */     b2d.setPaint(new GradientPaint(width - 160, 0.0F, TITLE_COLOR, width - 120, 0.0F, HEADER_COLOR));
/*    */     
/* 69 */     b2d.setFont(g2d.getFont().deriveFont(22.0F).deriveFont(1));
/* 70 */     b2d.drawString(this.title, 40, 58);
/* 71 */     b2d.dispose();
/* 72 */     g2d.drawImage(buffer, 0, 0, null);
/* 73 */     g2d.dispose();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/overlay/AbstractOverlayDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */