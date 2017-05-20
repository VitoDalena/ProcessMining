/*     */ package org.deckfour.uitopia.ui.main;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.deckfour.uitopia.ui.util.ArrangementHelper;
/*     */ import org.deckfour.uitopia.ui.util.ImageLoader;
/*     */ import org.deckfour.uitopia.ui.util.LinkLabel;
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
/*     */ public class MainToolbar
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private static final int HEIGHT = 45;
/*  65 */   private static final Color COLOR_TOP = new Color(200, 200, 200);
/*  66 */   private static final Color COLOR_BOTTOM = new Color(160, 160, 160);
/*     */   
/*     */   private Image appIcon;
/*     */   private Image attributionIcon;
/*     */   private MainView mainView;
/*     */   private MainTabBar tabBar;
/*     */   
/*     */   public MainToolbar(MainView mainView)
/*     */   {
/*  75 */     this.mainView = mainView;
/*  76 */     setOpaque(true);
/*  77 */     setBorder(BorderFactory.createEmptyBorder());
/*  78 */     setMinimumSize(new Dimension(45, 45));
/*  79 */     setMaximumSize(new Dimension(8000, 45));
/*  80 */     setPreferredSize(new Dimension(4000, 45));
/*  81 */     setup();
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/*  85 */     this.tabBar.setEnabled(enabled);
/*     */   }
/*     */   
/*     */   private void setup()
/*     */   {
/*  90 */     this.tabBar = new MainTabBar(this.mainView);
/*  91 */     this.appIcon = ImageLoader.load("prom_logo_130x40.png");
/*  92 */     this.attributionIcon = ImageLoader.load("fluxicon_logo_130x40.png");
/*  93 */     setLayout(new BorderLayout());
/*  94 */     JLabel logoLabel = new JLabel(new ImageIcon(this.appIcon));
/*  95 */     logoLabel.setOpaque(false);
/*  96 */     logoLabel.setBorder(BorderFactory.createEmptyBorder());
/*  97 */     LinkLabel attributionLabel = new LinkLabel(this.attributionIcon, "http://www.fluxicon.com/");
/*     */     
/*  99 */     attributionLabel.setOpaque(false);
/* 100 */     attributionLabel.setBorder(BorderFactory.createEmptyBorder());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 109 */     add(logoLabel, "West");
/* 110 */     add(ArrangementHelper.centerHorizontally(ArrangementHelper.pushDown(this.tabBar)), "Center");
/* 111 */     add(attributionLabel, "East");
/*     */   }
/*     */   
/*     */   public void activateTab(MainView.View view) {
/* 115 */     if (view.equals(MainView.View.WORKSPACE)) {
/* 116 */       this.tabBar.setActiveTab(0, false);
/* 117 */     } else if (view.equals(MainView.View.ACTIONS)) {
/* 118 */       this.tabBar.setActiveTab(1, false);
/* 119 */     } else if (view.equals(MainView.View.VIEWS)) {
/* 120 */       this.tabBar.setActiveTab(2, false);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 129 */     int width = getWidth();
/* 130 */     int height = getHeight();
/* 131 */     Graphics2D g2d = (Graphics2D)g;
/* 132 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 134 */     GradientPaint gradient = new GradientPaint(20.0F, height / 3, COLOR_TOP, 20.0F, height, COLOR_BOTTOM, false);
/* 135 */     g2d.setPaint(gradient);
/* 136 */     g2d.fillRect(0, 0, width, height);
/* 137 */     gradient = new GradientPaint(20.0F, height - 10, new Color(0, 0, 0, 0), 20.0F, height, new Color(0, 0, 0, 40), false);
/* 138 */     g2d.setPaint(gradient);
/* 139 */     g2d.fillRect(0, height - 10, width, 10);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/main/MainToolbar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */