/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Float;
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
/*     */ public class ArrowPanel
/*     */   extends JPanel
/*     */ {
/*     */   public static final int UP = 0;
/*     */   public static final int DOWN = 1;
/*  69 */   private int type = 0;
/*     */   
/*     */ 
/*  72 */   private Rectangle2D available = new Rectangle2D.Float();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrowPanel(int type)
/*     */   {
/*  80 */     this.type = type;
/*  81 */     setPreferredSize(new Dimension(14, 9));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paintComponent(Graphics g)
/*     */   {
/*  91 */     super.paintComponent(g);
/*  92 */     Graphics2D g2 = (Graphics2D)g;
/*     */     
/*     */ 
/*  95 */     Dimension size = getSize();
/*  96 */     Insets insets = getInsets();
/*  97 */     this.available.setRect(insets.left, insets.top, size.getWidth() - insets.left - insets.right, size.getHeight() - insets.top - insets.bottom);
/*     */     
/*     */ 
/* 100 */     g2.translate(insets.left, insets.top);
/* 101 */     g2.fill(getArrow(this.type));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Shape getArrow(int t)
/*     */   {
/* 113 */     switch (t) {
/* 114 */     case 0:  return getUpArrow();
/* 115 */     case 1:  return getDownArrow(); }
/* 116 */     return getUpArrow();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Shape getUpArrow()
/*     */   {
/* 126 */     Polygon result = new Polygon();
/* 127 */     result.addPoint(7, 2);
/* 128 */     result.addPoint(2, 7);
/* 129 */     result.addPoint(12, 7);
/* 130 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Shape getDownArrow()
/*     */   {
/* 139 */     Polygon result = new Polygon();
/* 140 */     result.addPoint(7, 7);
/* 141 */     result.addPoint(2, 2);
/* 142 */     result.addPoint(12, 2);
/* 143 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/ui/ArrowPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */