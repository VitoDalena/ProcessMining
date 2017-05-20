/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.BorderFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StackedCardsTabbedPane
/*     */   extends JPanel
/*     */ {
/*  66 */   protected static Color COLOR_ACTIVE_BG = new Color(120, 120, 120);
/*  67 */   protected static Color COLOR_PASSIVE_BG = new Color(100, 100, 100);
/*  68 */   protected static Color COLOR_ACTIVE_HILIGHT = new Color(200, 200, 200);
/*  69 */   protected static Color COLOR_PASSIVE_HILIGHT = new Color(170, 170, 170);
/*  70 */   protected static Color COLOR_ACTIVE_TXT = new Color(20, 20, 20);
/*  71 */   protected static Color COLOR_PASSIVE_TXT = new Color(40, 40, 40);
/*  72 */   protected static Color COLOR_SHADOW_TXT = new Color(140, 140, 140);
/*     */   
/*  74 */   protected static Dimension DIMENSION_HEADER_MIN = new Dimension(100, 30);
/*  75 */   protected static Dimension DIMENSION_HEADER_MAX = new Dimension(10000, 30);
/*  76 */   protected static Dimension DIMENSION_HEADER_PREF = new Dimension(250, 30);
/*  77 */   protected static Dimension DIMENSION_CONTENT_MIN = new Dimension(100, 30);
/*  78 */   protected static Dimension DIMENSION_CONTENT_MAX = new Dimension(10000, 10000);
/*  79 */   protected static Dimension DIMENSION_CONTENT_PREF = new Dimension(10000, 10000);
/*     */   protected ArrayList<SlickTab> tabs;
/*     */   protected SlickTab activeTab;
/*     */   
/*     */   public StackedCardsTabbedPane()
/*     */   {
/*  85 */     setDoubleBuffered(true);
/*  86 */     setBackground(COLOR_PASSIVE_BG);
/*  87 */     this.tabs = new ArrayList();
/*  88 */     this.activeTab = null;
/*  89 */     setBorder(BorderFactory.createEmptyBorder());
/*  90 */     setLayout(new BoxLayout(this, 1));
/*     */   }
/*     */   
/*     */   public void addTab(String title, JComponent content) {
/*  94 */     SlickTab novice = new SlickTab(this, title, content);
/*  95 */     this.tabs.add(novice);
/*  96 */     if (this.tabs.size() == 1)
/*     */     {
/*  98 */       this.activeTab = novice;
/*  99 */       novice.setActive(true);
/*     */     } else {
/* 101 */       novice.setActive(false);
/*     */     }
/* 103 */     add(novice);
/* 104 */     revalidate();
/* 105 */     if (isDisplayable()) {
/* 106 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setActive(SlickTab tab) {
/* 111 */     this.activeTab.setActive(false);
/* 112 */     this.activeTab = tab;
/* 113 */     this.activeTab.setActive(true);
/* 114 */     revalidate();
/* 115 */     if (isDisplayable()) {
/* 116 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean setActive(int index) {
/* 121 */     if ((index >= 0) && (index < this.tabs.size())) {
/* 122 */       if (this.activeTab != null) {
/* 123 */         this.activeTab.setActive(false);
/*     */       }
/* 125 */       this.activeTab = ((SlickTab)this.tabs.get(index));
/* 126 */       this.activeTab.setActive(true);
/* 127 */       revalidate();
/* 128 */       if (isDisplayable()) {
/* 129 */         repaint();
/*     */       }
/* 131 */       return true;
/*     */     }
/* 133 */     return false;
/*     */   }
/*     */   
/*     */   protected class SlickTab extends JPanel
/*     */   {
/*     */     protected StackedCardsTabbedPane parent;
/*     */     protected StackedCardsTabbedPane.SlickTabHeader header;
/*     */     protected StackedCardsTabbedPane.SlickTabFooter footer;
/*     */     protected JComponent content;
/*     */     
/*     */     public SlickTab(StackedCardsTabbedPane parentPane, String title, JComponent component)
/*     */     {
/* 145 */       setDoubleBuffered(true);
/* 146 */       this.parent = parentPane;
/* 147 */       this.content = component;
/* 148 */       this.content.setBackground(StackedCardsTabbedPane.COLOR_ACTIVE_BG);
/* 149 */       this.content.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
/* 150 */       this.content.setMinimumSize(StackedCardsTabbedPane.DIMENSION_CONTENT_MIN);
/* 151 */       this.content.setMaximumSize(StackedCardsTabbedPane.DIMENSION_CONTENT_MAX);
/* 152 */       this.content.setPreferredSize(StackedCardsTabbedPane.DIMENSION_CONTENT_PREF);
/* 153 */       this.header = new StackedCardsTabbedPane.SlickTabHeader(StackedCardsTabbedPane.this, this, title);
/* 154 */       this.footer = new StackedCardsTabbedPane.SlickTabFooter(StackedCardsTabbedPane.this);
/* 155 */       setBorder(BorderFactory.createEmptyBorder());
/* 156 */       setLayout(new BorderLayout());
/* 157 */       add(this.header, "North");
/*     */     }
/*     */     
/*     */     public void setActive(boolean isActive) {
/* 161 */       if (isActive) {
/* 162 */         add(this.content, "Center");
/* 163 */         add(this.footer, "South");
/*     */       } else {
/* 165 */         remove(this.content);
/* 166 */         remove(this.footer);
/*     */       }
/* 168 */       this.header.setActive(isActive);
/* 169 */       revalidate();
/*     */     }
/*     */     
/*     */     protected void clickedToActivate() {
/* 173 */       this.parent.setActive(this);
/*     */     }
/*     */   }
/*     */   
/*     */   protected class SlickTabHeader extends JComponent implements MouseListener
/*     */   {
/*     */     protected StackedCardsTabbedPane.SlickTab parent;
/*     */     protected String name;
/*     */     protected boolean active;
/*     */     
/*     */     public SlickTabHeader(StackedCardsTabbedPane.SlickTab parentTab, String header) {
/* 184 */       setDoubleBuffered(true);
/* 185 */       this.parent = parentTab;
/* 186 */       this.name = header;
/* 187 */       this.active = false;
/* 188 */       setMinimumSize(StackedCardsTabbedPane.DIMENSION_HEADER_MIN);
/* 189 */       setMaximumSize(StackedCardsTabbedPane.DIMENSION_HEADER_MAX);
/* 190 */       setPreferredSize(StackedCardsTabbedPane.DIMENSION_HEADER_PREF);
/* 191 */       setBorder(BorderFactory.createEmptyBorder());
/* 192 */       addMouseListener(this);
/*     */     }
/*     */     
/*     */     public void setActive(boolean isActive) {
/* 196 */       this.active = isActive;
/* 197 */       revalidate();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     protected void paintComponent(Graphics g)
/*     */     {
/* 204 */       int width = getWidth();
/* 205 */       int height = getHeight();
/* 206 */       Graphics2D g2d = (Graphics2D)g;
/* 207 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */       
/* 209 */       g2d.setColor(StackedCardsTabbedPane.COLOR_PASSIVE_BG);
/* 210 */       g2d.fillRect(0, 0, width, height);
/*     */       Color colorText;
/*     */       Color colorBase;
/* 213 */       Color colorHighlight; Color colorText; if (this.active) {
/* 214 */         Color colorBase = StackedCardsTabbedPane.COLOR_ACTIVE_BG;
/* 215 */         Color colorHighlight = StackedCardsTabbedPane.COLOR_ACTIVE_HILIGHT;
/* 216 */         colorText = StackedCardsTabbedPane.COLOR_ACTIVE_TXT;
/*     */       } else {
/* 218 */         colorBase = StackedCardsTabbedPane.COLOR_PASSIVE_BG;
/* 219 */         colorHighlight = StackedCardsTabbedPane.COLOR_PASSIVE_HILIGHT;
/* 220 */         colorText = StackedCardsTabbedPane.COLOR_PASSIVE_TXT;
/*     */       }
/* 222 */       GradientPaint gradient = new GradientPaint(20.0F, 8.0F, colorHighlight, 20.0F, height, colorBase, false);
/* 223 */       g2d.setPaint(gradient);
/* 224 */       g2d.fillRoundRect(0, 3, width, height + 10, 20, 15);
/*     */       
/* 226 */       g2d.setFont(g2d.getFont().deriveFont(13.0F));
/* 227 */       g2d.setPaint(StackedCardsTabbedPane.COLOR_SHADOW_TXT);
/* 228 */       g2d.drawString(this.name, 24, height - 7);
/* 229 */       g2d.setPaint(colorText);
/* 230 */       g2d.drawString(this.name, 25, height - 8);
/*     */       int[] yPoints;
/*     */       int[] xPoints;
/* 233 */       int[] yPoints; if (this.active) {
/* 234 */         int[] xPoints = { 10, 14, 18 };
/* 235 */         yPoints = new int[] { height - 17, height - 9, height - 17 };
/*     */       } else {
/* 237 */         xPoints = new int[] { 10, 18, 10 };
/* 238 */         yPoints = new int[] { height - 9, height - 13, height - 17 };
/*     */       }
/* 240 */       g2d.fillPolygon(xPoints, yPoints, 3);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     public void mouseClicked(MouseEvent arg0)
/*     */     {
/* 247 */       this.parent.clickedToActivate();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void mouseEntered(MouseEvent arg0) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void mouseExited(MouseEvent arg0) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void mousePressed(MouseEvent arg0) {}
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public void mouseReleased(MouseEvent arg0) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected class SlickTabFooter
/*     */     extends JComponent
/*     */   {
/*     */     public SlickTabFooter()
/*     */     {
/* 282 */       setBorder(BorderFactory.createEmptyBorder());
/* 283 */       setOpaque(false);
/* 284 */       setMinimumSize(new Dimension(100, 10));
/* 285 */       setMaximumSize(new Dimension(10000, 10));
/* 286 */       setPreferredSize(new Dimension(10000, 10));
/*     */     }
/*     */     
/*     */     protected void paintComponent(Graphics g) {
/* 290 */       int width = getWidth();
/* 291 */       int height = getHeight();
/* 292 */       Graphics2D g2d = (Graphics2D)g;
/* 293 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */       
/* 295 */       g2d.setColor(StackedCardsTabbedPane.COLOR_PASSIVE_BG);
/* 296 */       g2d.fillRect(0, 0, width, height);
/* 297 */       GradientPaint gradient = new GradientPaint(20.0F, 0.0F, StackedCardsTabbedPane.COLOR_ACTIVE_BG, 20.0F, height, StackedCardsTabbedPane.COLOR_PASSIVE_BG, false);
/* 298 */       g2d.setPaint(gradient);
/* 299 */       g2d.fillRoundRect(0, 0, width, height, 20, 15);
/* 300 */       g2d.fillRect(0, 0, width, 5);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/StackedCardsTabbedPane.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */