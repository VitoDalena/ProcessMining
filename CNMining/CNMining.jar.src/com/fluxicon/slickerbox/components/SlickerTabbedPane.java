/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
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
/*     */ public class SlickerTabbedPane
/*     */   extends JPanel
/*     */ {
/*  73 */   protected static Color COLOR_TRANSPARENT = new Color(0, 0, 0, 0);
/*     */   
/*  75 */   protected int tabPanelHeight = 26;
/*  76 */   protected int tabInnerBorder = 11;
/*  77 */   protected int tabOuterBorder = 6;
/*     */   protected Color colorTitle;
/*     */   protected Color colorBg;
/*     */   protected Color colorFg;
/*     */   protected JLabel title;
/*     */   protected JPanel tabPanel;
/*     */   protected ArrayList<FlatTab> tabs;
/*     */   protected RoundedPanel contentPane;
/*     */   protected JComponent view;
/*     */   
/*     */   public SlickerTabbedPane(String title, Color bgColor, Color fgColor, Color titleColor) {
/*  88 */     setOpaque(false);
/*  89 */     setDoubleBuffered(true);
/*  90 */     setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/*  91 */     setLayout(new BorderLayout());
/*  92 */     this.title = new JLabel(title);
/*  93 */     this.title.setOpaque(false);
/*  94 */     this.title.setFont(this.title.getFont().deriveFont(15.0F));
/*  95 */     this.title.setForeground(titleColor);
/*  96 */     this.colorFg = fgColor;
/*  97 */     this.colorBg = bgColor;
/*  98 */     this.tabPanel = new JPanel();
/*  99 */     this.tabPanel.setOpaque(false);
/* 100 */     this.tabPanel.setLayout(new BoxLayout(this.tabPanel, 0));
/* 101 */     this.tabPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
/* 102 */     this.tabPanel.setMinimumSize(new Dimension(200, this.tabPanelHeight));
/* 103 */     this.tabPanel.setMaximumSize(new Dimension(2000, this.tabPanelHeight));
/* 104 */     this.tabPanel.setPreferredSize(new Dimension(400, this.tabPanelHeight));
/* 105 */     add(this.tabPanel, "North");
/* 106 */     this.contentPane = new RoundedPanel(15, 0, 0);
/* 107 */     this.contentPane.setBackground(this.colorBg);
/* 108 */     this.contentPane.setLayout(new BorderLayout());
/* 109 */     add(this.contentPane, "Center");
/* 110 */     this.view = null;
/* 111 */     setFont(getFont().deriveFont(13.0F));
/* 112 */     this.tabs = new ArrayList();
/* 113 */     updateTabPanel();
/*     */   }
/*     */   
/*     */   public void addTab(String title, JComponent view) {
/* 117 */     addTab(title, view, null);
/*     */   }
/*     */   
/*     */   public void addTab(String title, JComponent view, ActionListener selectListener) {
/* 121 */     FlatTab nTab = new FlatTab(title, view, selectListener);
/* 122 */     this.tabs.add(nTab);
/* 123 */     updateTabPanel();
/* 124 */     if (this.tabs.size() == 1) {
/* 125 */       activateTab(nTab);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeTab(String title) {
/* 130 */     for (FlatTab tab : this.tabs) {
/* 131 */       if (tab.getTitle().equals(title)) {
/* 132 */         this.tabs.remove(tab);
/* 133 */         updateTabPanel();
/* 134 */         if ((!tab.isSelected()) || (this.tabs.size() <= 0)) break;
/* 135 */         activateTab((FlatTab)this.tabs.get(0));
/*     */         
/* 137 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeTab(JComponent view) {
/* 143 */     for (FlatTab tab : this.tabs) {
/* 144 */       if (tab.getView() == view) {
/* 145 */         this.tabs.remove(tab);
/* 146 */         updateTabPanel();
/* 147 */         if ((!tab.isSelected()) || (this.tabs.size() <= 0)) break;
/* 148 */         activateTab((FlatTab)this.tabs.get(0));
/*     */         
/* 150 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void selectTab(JComponent view) {
/* 156 */     for (FlatTab tab : this.tabs) {
/* 157 */       if (tab.getView() == view) {
/* 158 */         activateTab(tab);
/* 159 */         ActionListener selectListener = tab.getSelectListener();
/* 160 */         if (selectListener != null) {
/* 161 */           selectListener.actionPerformed(new ActionEvent(this, 0, "selected"));
/*     */         }
/* 163 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void selectTab(String title) {
/* 169 */     for (FlatTab tab : this.tabs) {
/* 170 */       if (tab.getTitle().equals(title)) {
/* 171 */         ActionListener selectListener = tab.getSelectListener();
/* 172 */         if (selectListener != null) {
/* 173 */           selectListener.actionPerformed(new ActionEvent(this, 0, "selected"));
/*     */         }
/* 175 */         activateTab(tab);
/* 176 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public JComponent getSelected() {
/* 182 */     for (FlatTab tab : this.tabs) {
/* 183 */       if (tab.isSelected()) {
/* 184 */         return tab.getView();
/*     */       }
/*     */     }
/* 187 */     return null;
/*     */   }
/*     */   
/*     */   public void triggerSelected() {
/* 191 */     for (FlatTab tab : this.tabs) {
/* 192 */       if (tab.isSelected()) {
/* 193 */         ActionListener selectListener = tab.getSelectListener();
/* 194 */         if (selectListener != null) {
/* 195 */           selectListener.actionPerformed(new ActionEvent(this, 0, "selected"));
/*     */         }
/* 197 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void updateTabPanel() {
/* 203 */     this.tabPanel.removeAll();
/* 204 */     if (this.title != null) {
/* 205 */       this.tabPanel.add(this.title);
/* 206 */       this.tabPanel.add(Box.createHorizontalStrut(20));
/*     */     }
/* 208 */     for (FlatTab tab : this.tabs) {
/* 209 */       this.tabPanel.add(tab);
/* 210 */       this.tabPanel.add(Box.createHorizontalStrut(this.tabOuterBorder));
/*     */     }
/* 212 */     this.tabPanel.add(Box.createHorizontalGlue());
/* 213 */     this.tabPanel.revalidate();
/* 214 */     revalidate();
/*     */   }
/*     */   
/*     */   protected void activateTab(FlatTab selectedTab) {
/* 218 */     JComponent nView = selectedTab.getView();
/* 219 */     for (FlatTab tab : this.tabs) {
/* 220 */       if (tab != selectedTab) {
/* 221 */         tab.setSelected(false);
/*     */       } else {
/* 223 */         tab.setSelected(true);
/*     */       }
/*     */     }
/* 226 */     if (this.view != null) {
/* 227 */       this.contentPane.remove(this.view);
/*     */     }
/* 229 */     this.view = nView;
/* 230 */     this.contentPane.add(nView, "Center");
/* 231 */     revalidate();
/* 232 */     repaint();
/*     */   }
/*     */   
/*     */   protected Rectangle2D getStringBounds(String string) {
/* 236 */     return getFontMetrics(getFont()).getStringBounds(string, getGraphics());
/*     */   }
/*     */   
/*     */   protected Font getTabFont() {
/* 240 */     return getFont();
/*     */   }
/*     */   
/*     */   protected class FlatTab extends JComponent
/*     */   {
/*     */     protected String title;
/*     */     protected JComponent view;
/*     */     protected ActionListener selectListener;
/*     */     protected boolean isSelected;
/*     */     protected boolean mouseOver;
/*     */     
/*     */     public FlatTab(String title, JComponent view, final ActionListener selectListener)
/*     */     {
/* 253 */       setDoubleBuffered(true);
/* 254 */       this.title = title;
/* 255 */       this.view = view;
/* 256 */       this.selectListener = selectListener;
/* 257 */       this.isSelected = false;
/* 258 */       this.mouseOver = false;
/* 259 */       Rectangle2D sBounds = SlickerTabbedPane.this.getStringBounds(title);
/* 260 */       int width = (int)sBounds.getWidth() + SlickerTabbedPane.this.tabInnerBorder + SlickerTabbedPane.this.tabInnerBorder;
/* 261 */       setMinimumSize(new Dimension(width, SlickerTabbedPane.this.tabPanelHeight));
/* 262 */       setMaximumSize(new Dimension(width, SlickerTabbedPane.this.tabPanelHeight));
/* 263 */       setPreferredSize(new Dimension(width, SlickerTabbedPane.this.tabPanelHeight));
/* 264 */       setOpaque(false);
/* 265 */       final FlatTab tab = this;
/* 266 */       addMouseListener(new MouseListener() {
/*     */         public void mouseClicked(MouseEvent arg0) {
/* 268 */           if (!SlickerTabbedPane.FlatTab.this.isSelected) {
/* 269 */             if (selectListener != null) {
/* 270 */               selectListener.actionPerformed(new ActionEvent(this, 0, "selected"));
/*     */             }
/* 272 */             SlickerTabbedPane.this.activateTab(tab);
/* 273 */             SlickerTabbedPane.FlatTab.this.mouseOver = false;
/*     */           }
/*     */         }
/*     */         
/* 277 */         public void mouseEntered(MouseEvent arg0) { SlickerTabbedPane.FlatTab.this.mouseOver = true;
/* 278 */           SlickerTabbedPane.FlatTab.this.repaint();
/*     */         }
/*     */         
/* 281 */         public void mouseExited(MouseEvent arg0) { SlickerTabbedPane.FlatTab.this.mouseOver = false;
/* 282 */           SlickerTabbedPane.FlatTab.this.repaint();
/*     */         }
/*     */         
/*     */         public void mousePressed(MouseEvent arg0) {}
/*     */         
/*     */         public void mouseReleased(MouseEvent arg0) {}
/*     */       }); }
/*     */     
/* 290 */     protected JComponent getView() { return this.view; }
/*     */     
/*     */     protected String getTitle()
/*     */     {
/* 294 */       return this.title;
/*     */     }
/*     */     
/*     */     protected ActionListener getSelectListener() {
/* 298 */       return this.selectListener;
/*     */     }
/*     */     
/*     */     protected boolean isSelected() {
/* 302 */       return this.isSelected;
/*     */     }
/*     */     
/*     */     protected void setSelected(boolean selected) {
/* 306 */       this.isSelected = selected;
/* 307 */       repaint();
/*     */     }
/*     */     
/*     */     public void paintComponent(Graphics g) {
/* 311 */       int width = getWidth();
/* 312 */       int height = getHeight();
/* 313 */       Graphics2D g2d = (Graphics2D)g.create();
/* 314 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 315 */       if (!this.isSelected) {
/* 316 */         GradientPaint gradient = new GradientPaint(0.0F, 8.0F, SlickerTabbedPane.this.colorBg, 0.0F, height * 2, SlickerTabbedPane.COLOR_TRANSPARENT, false);
/* 317 */         g2d.setPaint(gradient);
/* 318 */         if (!this.mouseOver) {
/* 319 */           g2d.setComposite(AlphaComposite.getInstance(3, 0.75F));
/*     */         } else {
/* 321 */           g2d.setComposite(AlphaComposite.getInstance(3, 0.95F));
/*     */         }
/*     */       } else {
/* 324 */         g2d.setColor(SlickerTabbedPane.this.colorBg);
/*     */       }
/* 326 */       g2d.fillRoundRect(0, 0, width, height + 30, 15, 15);
/* 327 */       g2d.setColor(SlickerTabbedPane.this.colorFg);
/* 328 */       g2d.setFont(SlickerTabbedPane.this.getTabFont());
/* 329 */       g2d.drawString(this.title, SlickerTabbedPane.this.tabInnerBorder, height - 8);
/* 330 */       g2d.dispose();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/SlickerTabbedPane.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */