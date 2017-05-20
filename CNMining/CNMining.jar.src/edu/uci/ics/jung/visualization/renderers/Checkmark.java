/*    */ package edu.uci.ics.jung.visualization.renderers;
/*    */ 
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.Shape;
/*    */ import java.awt.Stroke;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.GeneralPath;
/*    */ import java.util.Collections;
/*    */ import javax.swing.Icon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Checkmark
/*    */   implements Icon
/*    */ {
/* 24 */   GeneralPath path = new GeneralPath();
/* 25 */   AffineTransform highlight = AffineTransform.getTranslateInstance(-1.0D, -1.0D);
/* 26 */   AffineTransform lowlight = AffineTransform.getTranslateInstance(1.0D, 1.0D);
/* 27 */   AffineTransform shadow = AffineTransform.getTranslateInstance(2.0D, 2.0D);
/*    */   Color color;
/*    */   
/* 30 */   public Checkmark() { this(Color.green); }
/*    */   
/*    */   public Checkmark(Color color) {
/* 33 */     this.color = color;
/* 34 */     this.path.moveTo(10.0F, 17.0F);
/* 35 */     this.path.lineTo(13.0F, 20.0F);
/* 36 */     this.path.lineTo(20.0F, 13.0F);
/*    */   }
/*    */   
/* 39 */   public void paintIcon(Component c, Graphics g, int x, int y) { Shape shape = AffineTransform.getTranslateInstance(x, y).createTransformedShape(this.path);
/* 40 */     Graphics2D g2d = (Graphics2D)g;
/* 41 */     g2d.addRenderingHints(Collections.singletonMap(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
/*    */     
/* 43 */     Stroke stroke = g2d.getStroke();
/* 44 */     g2d.setStroke(new BasicStroke(4.0F));
/* 45 */     g2d.setColor(Color.darkGray);
/* 46 */     g2d.draw(this.shadow.createTransformedShape(shape));
/* 47 */     g2d.setColor(Color.black);
/* 48 */     g2d.draw(this.lowlight.createTransformedShape(shape));
/* 49 */     g2d.setColor(Color.white);
/* 50 */     g2d.draw(this.highlight.createTransformedShape(shape));
/* 51 */     g2d.setColor(this.color);
/* 52 */     g2d.draw(shape);
/* 53 */     g2d.setStroke(stroke);
/*    */   }
/*    */   
/*    */   public int getIconWidth() {
/* 57 */     return 20;
/*    */   }
/*    */   
/*    */   public int getIconHeight() {
/* 61 */     return 20;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/renderers/Checkmark.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */