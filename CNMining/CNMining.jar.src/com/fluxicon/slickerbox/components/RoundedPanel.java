/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.awt.geom.RoundRectangle2D.Double;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
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
/*     */ public class RoundedPanel
/*     */   extends JPanel
/*     */ {
/*     */   protected int radius;
/*     */   protected int outerBorder;
/*     */   protected int innerBorder;
/*     */   protected Color borderColor;
/*     */   protected float borderWidth;
/*     */   
/*     */   public RoundedPanel(int aRadius, int anOuterBorder, int anInnerBorder)
/*     */   {
/*  70 */     this.borderColor = null;
/*  71 */     this.borderWidth = 0.0F;
/*  72 */     this.radius = aRadius;
/*  73 */     this.outerBorder = anOuterBorder;
/*  74 */     this.innerBorder = anInnerBorder;
/*  75 */     setOpaque(false);
/*  76 */     int borderSize = this.radius / 2 + this.outerBorder + this.innerBorder;
/*  77 */     setBorder(BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
/*  78 */     setOpaque(false);
/*     */   }
/*     */   
/*     */   public RoundedPanel(int aRadius) {
/*  82 */     this(aRadius, 0, 0);
/*     */   }
/*     */   
/*     */   public RoundedPanel() {
/*  86 */     this(5, 0, 0);
/*     */   }
/*     */   
/*     */   public void setBorder(Color borderColor, float borderWidth) {
/*  90 */     this.borderColor = borderColor;
/*  91 */     this.borderWidth = borderWidth;
/*  92 */     if ((isVisible()) && (isDisplayable())) {
/*  93 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearBorder() {
/*  98 */     setBorder(null, 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 106 */     Graphics2D g2d = (Graphics2D)g;
/* 107 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 108 */     g2d.setColor(getBackground());
/* 109 */     RoundRectangle2D rrect = new RoundRectangle2D.Double(this.outerBorder - 0.5D, 
/* 110 */       this.outerBorder - 0.5D, 
/* 111 */       getWidth() - 2 * this.outerBorder, 
/* 112 */       getHeight() - 2 * this.outerBorder, 
/* 113 */       this.radius, 
/* 114 */       this.radius);
/*     */     
/* 116 */     g2d.fill(rrect);
/*     */     
/*     */ 
/*     */ 
/* 120 */     if (this.borderColor != null)
/*     */     {
/* 122 */       g2d.setColor(this.borderColor);
/* 123 */       BasicStroke stroke = new BasicStroke(this.borderWidth, 1, 1);
/* 124 */       g2d.setStroke(stroke);
/* 125 */       g2d.draw(rrect);
/*     */     }
/*     */   }
/*     */   
/*     */   public static RoundedPanel enclose(JComponent comp, int radius, int outerBorder, int innerBorder) {
/* 130 */     RoundedPanel enclosure = new RoundedPanel(radius, outerBorder, innerBorder);
/* 131 */     enclosure.setLayout(new BorderLayout());
/* 132 */     enclosure.setBackground(comp.getBackground());
/* 133 */     enclosure.add(comp, "Center");
/* 134 */     return enclosure;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/RoundedPanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */