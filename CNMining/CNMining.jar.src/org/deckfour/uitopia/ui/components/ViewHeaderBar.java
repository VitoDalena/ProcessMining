/*     */ package org.deckfour.uitopia.ui.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
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
/*     */ public class ViewHeaderBar
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = -6949075106580021242L;
/*     */   private static final int HEIGHT = 48;
/*  59 */   private static final Color COLOR_TOP = new Color(20, 20, 20);
/*  60 */   private static final Color COLOR_BOTTOM = new Color(0, 0, 0);
/*  61 */   private static final Color COLOR_TITLE = new Color(220, 220, 220);
/*     */   private String title;
/*     */   private List<JComponent> buttons;
/*     */   
/*     */   public ViewHeaderBar(String title)
/*     */   {
/*  67 */     this.title = title;
/*  68 */     this.buttons = new ArrayList();
/*  69 */     setOpaque(true);
/*  70 */     setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
/*  71 */     setMinimumSize(new Dimension(100, 48));
/*  72 */     setMaximumSize(new Dimension(8000, 48));
/*  73 */     setPreferredSize(new Dimension(2000, 48));
/*  74 */     setLayout(new BoxLayout(this, 0));
/*     */   }
/*     */   
/*     */   public void addComponent(JComponent button) {
/*  78 */     this.buttons.add(button);
/*  79 */     removeAll();
/*  80 */     add(Box.createHorizontalGlue());
/*  81 */     for (JComponent b : this.buttons) {
/*  82 */       add(b);
/*  83 */       add(Box.createHorizontalStrut(10));
/*     */     }
/*  85 */     revalidate();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/*  93 */     int width = getWidth();
/*  94 */     int height = getHeight();
/*  95 */     Graphics2D g2d = (Graphics2D)g;
/*  96 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  97 */     GradientPaint gradient = new GradientPaint(0.0F, 0.0F, COLOR_TOP, 0.0F, height - height / 3, COLOR_BOTTOM, false);
/*  98 */     g2d.setPaint(gradient);
/*  99 */     g2d.fillRect(0, 0, width, height);
/* 100 */     g2d.setColor(COLOR_TITLE);
/* 101 */     g2d.setFont(g2d.getFont().deriveFont(20.0F));
/* 102 */     g2d.drawString(this.title, 20, height - 16);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/components/ViewHeaderBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */