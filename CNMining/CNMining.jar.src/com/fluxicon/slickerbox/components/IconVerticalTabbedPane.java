/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.GraphicsUtilities;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IconVerticalTabbedPane
/*     */   extends JPanel
/*     */ {
/*  76 */   protected static Color COLOR_TRANSPARENT = new Color(0, 0, 0, 0);
/*     */   
/*  78 */   protected int tabPanelSize = 80;
/*  79 */   protected int tabOuterBorder = 4;
/*     */   protected Color colorBg;
/*     */   protected Color colorFg;
/*  82 */   protected Color colorPassive = COLOR_TRANSPARENT;
/*  83 */   protected Color colorMouseOverFade = COLOR_TRANSPARENT;
/*     */   protected JPanel tabPanel;
/*     */   protected ArrayList<TabHeader> tabs;
/*     */   protected JComponent view;
/*     */   protected RoundedPanel contentPane;
/*     */   
/*     */   public IconVerticalTabbedPane(Color fgColor, Color bgColor) {
/*  90 */     this(fgColor, bgColor, 80);
/*     */   }
/*     */   
/*     */   public IconVerticalTabbedPane(Color fgColor, Color bgColor, int tabPanelSize) {
/*  94 */     this.tabPanelSize = tabPanelSize;
/*  95 */     setOpaque(false);
/*  96 */     setDoubleBuffered(true);
/*  97 */     setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
/*  98 */     setLayout(new BoxLayout(this, 0));
/*  99 */     this.colorFg = fgColor;
/* 100 */     this.colorBg = bgColor;
/* 101 */     this.tabPanel = new JPanel();
/* 102 */     this.tabPanel.setOpaque(false);
/* 103 */     this.tabPanel.setLayout(new BoxLayout(this.tabPanel, 1));
/* 104 */     this.tabPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 105 */     this.tabPanel.setMinimumSize(new Dimension(tabPanelSize, 200));
/* 106 */     this.tabPanel.setMaximumSize(new Dimension(tabPanelSize, 2000));
/* 107 */     this.tabPanel.setPreferredSize(new Dimension(tabPanelSize, 400));
/* 108 */     add(this.tabPanel);
/* 109 */     this.contentPane = new RoundedPanel(20, 0, 0);
/* 110 */     this.contentPane.setBackground(this.colorBg);
/* 111 */     this.contentPane.setLayout(new BorderLayout());
/* 112 */     add(this.contentPane);
/* 113 */     this.view = null;
/* 114 */     setFont(getFont().deriveFont(12.0F));
/* 115 */     this.tabs = new ArrayList();
/* 116 */     updateTabPanel();
/*     */   }
/*     */   
/*     */   public void addTab(String title, Image icon, JComponent view) {
/* 120 */     addTab(title, icon, view, null);
/*     */   }
/*     */   
/*     */   public void addTab(String title, Image icon, JComponent view, ActionListener selectListener) {
/* 124 */     TabHeader nTab = new TabHeader(title, icon, view, selectListener);
/* 125 */     this.tabs.add(nTab);
/* 126 */     updateTabPanel();
/* 127 */     if (this.tabs.size() == 1) {
/* 128 */       activateTab(nTab);
/*     */     }
/*     */   }
/*     */   
/*     */   public void selectTab(JComponent view) {
/* 133 */     for (TabHeader tab : this.tabs) {
/* 134 */       if (tab.getView() == view) {
/* 135 */         ActionListener selectListener = tab.getSelectListener();
/* 136 */         if (selectListener != null) {
/* 137 */           selectListener.actionPerformed(new ActionEvent(this, 0, "selected"));
/*     */         }
/* 139 */         activateTab(tab);
/* 140 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void selectTab(String title) {
/* 146 */     for (TabHeader tab : this.tabs) {
/* 147 */       if (tab.title.equals(title)) {
/* 148 */         ActionListener selectListener = tab.getSelectListener();
/* 149 */         if (selectListener != null) {
/* 150 */           selectListener.actionPerformed(new ActionEvent(this, 0, "selected"));
/*     */         }
/* 152 */         activateTab(tab);
/* 153 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public JComponent getSelected() {
/* 159 */     for (TabHeader tab : this.tabs) {
/* 160 */       if (tab.isSelected()) {
/* 161 */         return tab.getView();
/*     */       }
/*     */     }
/* 164 */     return null;
/*     */   }
/*     */   
/*     */   public void triggerSelected() {
/* 168 */     for (TabHeader tab : this.tabs) {
/* 169 */       if (tab.isSelected()) {
/* 170 */         ActionListener selectListener = tab.getSelectListener();
/* 171 */         if (selectListener != null) {
/* 172 */           selectListener.actionPerformed(new ActionEvent(this, 0, "selected"));
/*     */         }
/* 174 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void setPassiveBackground(Color passiveBg) {
/* 180 */     this.colorPassive = passiveBg;
/*     */   }
/*     */   
/*     */   public void setMouseOverFadeColor(Color fadeColor) {
/* 184 */     this.colorMouseOverFade = fadeColor;
/*     */   }
/*     */   
/*     */   protected void updateTabPanel() {
/* 188 */     this.tabPanel.removeAll();
/* 189 */     this.tabPanel.add(Box.createVerticalStrut(10));
/* 190 */     for (TabHeader tab : this.tabs) {
/* 191 */       this.tabPanel.add(tab);
/* 192 */       this.tabPanel.add(Box.createVerticalStrut(this.tabOuterBorder));
/*     */     }
/* 194 */     this.tabPanel.add(Box.createVerticalGlue());
/*     */   }
/*     */   
/*     */   protected void activateTab(TabHeader selectedTab) {
/* 198 */     JComponent nView = selectedTab.getView();
/* 199 */     for (TabHeader tab : this.tabs) {
/* 200 */       if (tab != selectedTab) {
/* 201 */         tab.setSelected(false);
/*     */       } else {
/* 203 */         tab.setSelected(true);
/*     */       }
/*     */     }
/* 206 */     if (this.view != null) {
/* 207 */       this.contentPane.remove(this.view);
/*     */     }
/* 209 */     this.view = nView;
/* 210 */     this.contentPane.add(nView, "Center");
/* 211 */     revalidate();
/* 212 */     repaint();
/*     */   }
/*     */   
/*     */   protected Rectangle2D getStringBounds(String string) {
/* 216 */     return getFontMetrics(getFont()).getStringBounds(string, getGraphics());
/*     */   }
/*     */   
/*     */   protected Font getTabFont() {
/* 220 */     return getFont();
/*     */   }
/*     */   
/*     */   protected class TabHeader extends JComponent
/*     */   {
/*     */     protected String title;
/*     */     protected BufferedImage icon;
/*     */     protected JComponent view;
/*     */     protected ActionListener selectListener;
/*     */     protected boolean isSelected;
/*     */     protected boolean mouseOver;
/*     */     
/*     */     public TabHeader(String title, Image icon, JComponent view, final ActionListener selectListener) {
/* 233 */       setDoubleBuffered(true);
/* 234 */       this.title = title;
/* 235 */       this.icon = GraphicsUtilities.createTranslucentCompatibleImage(icon.getWidth(null), icon.getHeight(null));
/* 236 */       this.icon.getGraphics().drawImage(icon, 0, 0, null);
/* 237 */       this.view = view;
/* 238 */       this.selectListener = selectListener;
/* 239 */       this.isSelected = false;
/* 240 */       this.mouseOver = false;
/* 241 */       setMinimumSize(new Dimension(IconVerticalTabbedPane.this.tabPanelSize, IconVerticalTabbedPane.this.tabPanelSize));
/* 242 */       setMaximumSize(new Dimension(IconVerticalTabbedPane.this.tabPanelSize, IconVerticalTabbedPane.this.tabPanelSize));
/* 243 */       setPreferredSize(new Dimension(IconVerticalTabbedPane.this.tabPanelSize, IconVerticalTabbedPane.this.tabPanelSize));
/* 244 */       setOpaque(false);
/* 245 */       final TabHeader tab = this;
/* 246 */       addMouseListener(new MouseListener() {
/*     */         public void mouseClicked(MouseEvent arg0) {
/* 248 */           if (!IconVerticalTabbedPane.TabHeader.this.isSelected) {
/* 249 */             if (selectListener != null) {
/* 250 */               selectListener.actionPerformed(new ActionEvent(this, 0, "selected"));
/*     */             }
/* 252 */             IconVerticalTabbedPane.this.activateTab(tab);
/* 253 */             IconVerticalTabbedPane.TabHeader.this.mouseOver = false;
/*     */           }
/*     */         }
/*     */         
/* 257 */         public void mouseEntered(MouseEvent arg0) { IconVerticalTabbedPane.TabHeader.this.mouseOver = true;
/* 258 */           IconVerticalTabbedPane.TabHeader.this.repaint();
/*     */         }
/*     */         
/* 261 */         public void mouseExited(MouseEvent arg0) { IconVerticalTabbedPane.TabHeader.this.mouseOver = false;
/* 262 */           IconVerticalTabbedPane.TabHeader.this.repaint();
/*     */         }
/*     */         
/*     */         public void mousePressed(MouseEvent arg0) {}
/*     */         
/*     */         public void mouseReleased(MouseEvent arg0) {}
/*     */       }); }
/*     */     
/* 270 */     protected JComponent getView() { return this.view; }
/*     */     
/*     */     protected ActionListener getSelectListener()
/*     */     {
/* 274 */       return this.selectListener;
/*     */     }
/*     */     
/*     */     protected boolean isSelected() {
/* 278 */       return this.isSelected;
/*     */     }
/*     */     
/*     */     protected void setSelected(boolean selected) {
/* 282 */       this.isSelected = selected;
/* 283 */       repaint();
/*     */     }
/*     */     
/*     */     public void paintComponent(Graphics g) {
/* 287 */       int width = getWidth();
/* 288 */       int height = getHeight();
/* 289 */       Graphics2D g2d = (Graphics2D)g.create();
/* 290 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 291 */       Rectangle2D stringBounds = IconVerticalTabbedPane.this.getStringBounds(this.title);
/* 292 */       int yBorder = (height - (int)stringBounds.getHeight() - this.icon.getHeight(null) - 5) / 2;
/* 293 */       int iconX = (width - this.icon.getWidth(null)) / 2;
/* 294 */       int stringX = (width - (int)stringBounds.getWidth()) / 2;
/* 295 */       if (this.isSelected) {
/* 296 */         g2d.setColor(IconVerticalTabbedPane.this.colorBg);
/* 297 */       } else if (this.mouseOver) {
/* 298 */         GradientPaint gradient = new GradientPaint(width - width / 3, 0.0F, IconVerticalTabbedPane.this.colorBg, width, 0.0F, IconVerticalTabbedPane.this.colorMouseOverFade, false);
/* 299 */         g2d.setPaint(gradient);
/*     */       } else {
/* 301 */         GradientPaint gradient = new GradientPaint(width - width / 3, 0.0F, IconVerticalTabbedPane.this.colorPassive, width, 0.0F, IconVerticalTabbedPane.this.colorMouseOverFade, false);
/* 302 */         g2d.setPaint(gradient);
/*     */       }
/* 304 */       g2d.fillRoundRect(0, 0, width + 40, height - 1, 25, 25);
/* 305 */       if (!this.isSelected)
/*     */       {
/* 307 */         if (this.mouseOver) {
/* 308 */           g2d.setComposite(AlphaComposite.getInstance(3, 0.9F));
/*     */         } else
/* 310 */           g2d.setComposite(AlphaComposite.getInstance(3, 0.7F));
/*     */       }
/* 312 */       g2d.setColor(IconVerticalTabbedPane.this.colorFg);
/* 313 */       g2d.setFont(IconVerticalTabbedPane.this.getTabFont());
/* 314 */       g2d.drawString(this.title, stringX, yBorder + this.icon.getHeight(null) + (int)stringBounds.getHeight() + 2);
/* 315 */       g2d.drawImage(this.icon, iconX, yBorder + 3, null);
/* 316 */       g2d.dispose();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/IconVerticalTabbedPane.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */