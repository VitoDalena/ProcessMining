/*     */ package org.deckfour.uitopia.ui.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import javax.swing.BorderFactory;
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
/*     */ public class ShadedPanel
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 7551135573579386441L;
/*  52 */   private Color colorShadowTop = new Color(0, 0, 0, 180);
/*  53 */   private Color colorShadowBottom = new Color(0, 0, 0, 160);
/*  54 */   private Color colorTransparent = new Color(0, 0, 0, 0);
/*     */   
/*     */ 
/*     */   public ShadedPanel() {}
/*     */   
/*     */   public ShadedPanel(Color shadowTop, Color shadowBottom)
/*     */   {
/*  61 */     this.colorShadowTop = shadowTop;
/*  62 */     this.colorShadowBottom = shadowBottom;
/*  63 */     setBorder(BorderFactory.createEmptyBorder());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/*  71 */     int width = getWidth();
/*  72 */     int height = getHeight();
/*  73 */     Graphics2D g2d = (Graphics2D)g;
/*  74 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  75 */     g2d.setClip(0, 0, width, height);
/*  76 */     paintBackground(g2d);
/*  77 */     int upperLimit = Math.min(height / 8, 50);
/*  78 */     int lowerLimit = height / 2;
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
/*  96 */     GradientPaint gradient = new GradientPaint(0.0F, 0.0F, this.colorShadowTop, 0.0F, upperLimit, this.colorTransparent, false);
/*  97 */     g2d.setPaint(gradient);
/*  98 */     g2d.fillRect(0, 0, width, upperLimit);
/*     */     
/* 100 */     gradient = new GradientPaint(0.0F, lowerLimit, this.colorTransparent, 0.0F, height, this.colorShadowBottom, false);
/* 101 */     g2d.setPaint(gradient);
/* 102 */     g2d.fillRect(0, lowerLimit, width, height - lowerLimit);
/*     */   }
/*     */   
/*     */   protected void paintBackground(Graphics2D g2d) {
/* 106 */     g2d.setColor(new Color(200, 200, 200));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 112 */     g2d.fillRect(0, 0, getWidth(), getHeight());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/components/ShadedPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */