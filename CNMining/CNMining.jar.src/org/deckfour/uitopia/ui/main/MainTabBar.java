/*     */ package org.deckfour.uitopia.ui.main;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseMotionAdapter;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
/*     */ import org.deckfour.uitopia.ui.util.ImageLoader;
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
/*     */ public class MainTabBar
/*     */   extends JComponent
/*     */ {
/*     */   private static final long serialVersionUID = 2468367772821953968L;
/*     */   private static final int TAB_HEIGHT = 38;
/*     */   private static final int TAB_WIDTH = 110;
/*  64 */   private static Color COLOR_TAB_ACTIVE_TOP = new Color(60, 60, 60);
/*  65 */   private static Color COLOR_TAB_ACTIVE_BOTTOM = new Color(20, 20, 20);
/*  66 */   private static Color COLOR_TAB_PASSIVE_TOP = new Color(160, 160, 160);
/*  67 */   private static Color COLOR_TAB_PASSIVE_BOTTOM = new Color(100, 100, 100);
/*  68 */   private static Color COLOR_TAB_MOUSEOVER_TOP = new Color(100, 100, 100);
/*  69 */   private static Color COLOR_TAB_MOUSEOVER_BOTTOM = new Color(60, 60, 60);
/*  70 */   private static Color COLOR_TAB_BORDER = new Color(0, 0, 0, 40);
/*     */   
/*     */   private MainView mainView;
/*     */   
/*     */   private Image[] ICONS_ACTIVE;
/*     */   
/*     */   private Image[] ICONS_PASSIVE;
/*  77 */   private int indexActive = 0;
/*  78 */   private int indexMouseOver = -1;
/*     */   
/*  80 */   private boolean isEnabled = true;
/*     */   
/*     */   public MainTabBar(MainView mainView) {
/*  83 */     this.mainView = mainView;
/*     */     
/*  85 */     Dimension dim = new Dimension(331, 38);
/*  86 */     setMinimumSize(dim);
/*  87 */     setMaximumSize(dim);
/*  88 */     setPreferredSize(dim);
/*  89 */     setSize(dim);
/*     */     
/*  91 */     setOpaque(false);
/*  92 */     setBorder(BorderFactory.createEmptyBorder());
/*     */     
/*  94 */     this.ICONS_ACTIVE = new Image[] { ImageLoader.load("workspace_40x30_white.png"), ImageLoader.load("action_40x30_white.png"), ImageLoader.load("view_40x30_white.png") };
/*     */     
/*     */ 
/*     */ 
/*  98 */     this.ICONS_PASSIVE = new Image[] { ImageLoader.load("workspace_40x30_black.png"), ImageLoader.load("action_40x30_black.png"), ImageLoader.load("view_40x30_black.png") };
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 103 */     addMouseListener(new MouseAdapter() {
/*     */       public void mouseClicked(MouseEvent e) {
/* 105 */         MainTabBar.this.requestFocus();
/* 106 */         if ((e.getButton() == 1) && (MainTabBar.this.isEnabled()))
/*     */         {
/* 108 */           int x = e.getX();
/* 109 */           int active = 2;
/* 110 */           if (x < 110) {
/* 111 */             active = 0;
/* 112 */           } else if (x < 220) {
/* 113 */             active = 1;
/*     */           }
/* 115 */           MainTabBar.this.setActiveTab(active, true);
/*     */         }
/*     */       }
/*     */       
/*     */       public void mouseExited(MouseEvent e)
/*     */       {
/* 121 */         MainTabBar.this.indexMouseOver = -1;
/* 122 */         MainTabBar.this.repaint();
/*     */       }
/* 124 */     });
/* 125 */     addMouseMotionListener(new MouseMotionAdapter() {
/*     */       public void mouseMoved(MouseEvent e) {
/* 127 */         int x = e.getX();
/* 128 */         int over = 2;
/* 129 */         if (x < 110) {
/* 130 */           over = 0;
/* 131 */         } else if (x < 220) {
/* 132 */           over = 1;
/*     */         }
/* 134 */         if (over != MainTabBar.this.indexMouseOver) {
/* 135 */           MainTabBar.this.indexMouseOver = over;
/* 136 */           MainTabBar.this.repaint();
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEnabled(boolean enabled)
/*     */   {
/* 149 */     super.setEnabled(enabled);
/* 150 */     this.isEnabled = enabled;
/* 151 */     repaint();
/*     */   }
/*     */   
/*     */   public synchronized void setActiveTab(int index, boolean updateView) {
/* 155 */     if (index != this.indexActive) {
/* 156 */       this.indexActive = index;
/* 157 */       if (updateView) {
/* 158 */         if (this.indexActive == 0) {
/* 159 */           this.mainView.showWorkspaceView();
/* 160 */         } else if (this.indexActive == 1) {
/* 161 */           this.mainView.showActionsView();
/* 162 */         } else if (this.indexActive == 2) {
/* 163 */           this.mainView.showViewsView();
/*     */         }
/*     */       }
/*     */     }
/* 167 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 177 */     Graphics2D g2d = (Graphics2D)g;
/* 178 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 180 */     if (this.indexActive == 0) {
/* 181 */       paintTab(g2d, 2);
/* 182 */       paintTab(g2d, 1);
/* 183 */       paintTab(g2d, 0);
/* 184 */     } else if (this.indexActive == 1) {
/* 185 */       paintTab(g2d, 0);
/* 186 */       paintTab(g2d, 2);
/* 187 */       paintTab(g2d, 1);
/*     */     } else {
/* 189 */       paintTab(g2d, 0);
/* 190 */       paintTab(g2d, 1);
/* 191 */       paintTab(g2d, 2);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void paintTab(Graphics2D g2d, int index) {
/* 196 */     g2d = (Graphics2D)g2d.create();
/* 197 */     int x = index * 110 - 5 * index;
/* 198 */     int width = 120;
/*     */     Color bottom;
/* 200 */     Color top; Color bottom; if ((index == this.indexActive) && (this.isEnabled)) {
/* 201 */       Color top = COLOR_TAB_ACTIVE_TOP;
/* 202 */       bottom = COLOR_TAB_ACTIVE_BOTTOM; } else { Color bottom;
/* 203 */       if ((index == this.indexMouseOver) && (this.isEnabled)) {
/* 204 */         Color top = COLOR_TAB_MOUSEOVER_TOP;
/* 205 */         bottom = COLOR_TAB_MOUSEOVER_BOTTOM;
/*     */       } else {
/* 207 */         top = COLOR_TAB_PASSIVE_TOP;
/* 208 */         bottom = COLOR_TAB_PASSIVE_BOTTOM;
/*     */       } }
/* 210 */     GradientPaint gradient = new GradientPaint(0.0F, 0.0F, top, 0.0F, 26.0F, bottom, false);
/*     */     
/* 212 */     g2d.setPaint(gradient);
/* 213 */     g2d.fillRoundRect(x, 0, width, 78, 20, 20);
/* 214 */     g2d.setColor(COLOR_TAB_BORDER);
/* 215 */     g2d.setStroke(new BasicStroke(2.0F));
/* 216 */     g2d.drawRoundRect(x, 0, width, 78, 20, 20);
/* 217 */     g2d.setStroke(new BasicStroke(1.0F));
/* 218 */     g2d.setColor(new Color(0, 0, 0, 60));
/* 219 */     g2d.drawRoundRect(x, 0, width, 78, 20, 20);
/*     */     Image icon;
/* 221 */     Image icon; if ((index == this.indexActive) && (this.isEnabled)) {
/* 222 */       icon = this.ICONS_ACTIVE[index];
/*     */     } else {
/* 224 */       icon = this.ICONS_PASSIVE[index];
/* 225 */       if ((index == this.indexMouseOver) && (this.isEnabled)) {
/* 226 */         icon = this.ICONS_ACTIVE[index];
/* 227 */         g2d.setComposite(AlphaComposite.getInstance(3, 0.7F));
/*     */       }
/*     */     }
/*     */     
/* 231 */     int iconX = x + (width - icon.getWidth(null)) / 2;
/* 232 */     int iconY = (38 - icon.getHeight(null)) / 2;
/* 233 */     g2d.drawImage(icon, iconX, iconY, null);
/* 234 */     g2d.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/main/MainTabBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */