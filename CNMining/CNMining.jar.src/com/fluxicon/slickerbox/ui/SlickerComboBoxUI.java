/*     */ package com.fluxicon.slickerbox.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.awt.geom.RoundRectangle2D.Float;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import javax.swing.event.PopupMenuEvent;
/*     */ import javax.swing.event.PopupMenuListener;
/*     */ import javax.swing.plaf.basic.BasicButtonUI;
/*     */ import javax.swing.plaf.basic.BasicComboBoxUI;
/*     */ import javax.swing.plaf.basic.BasicComboPopup;
/*     */ import javax.swing.plaf.basic.ComboPopup;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlickerComboBoxUI
/*     */   extends BasicComboBoxUI
/*     */ {
/*  77 */   protected Color BACKGROUND_TOP_PASSIVE = new Color(180, 180, 180);
/*  78 */   protected Color BACKGROUND_BOTTOM_PASSIVE = new Color(120, 120, 120);
/*  79 */   protected Color BACKGROUND_TOP_ACTIVE = new Color(160, 160, 160);
/*  80 */   protected Color BACKGROUND_BOTTOM_ACTIVE = new Color(50, 50, 50);
/*     */   
/*  82 */   protected Color TEXT_PASSIVE = new Color(0, 0, 0, 200);
/*  83 */   protected Color TEXT_ACTIVE = new Color(0, 0, 0, 180);
/*  84 */   protected Color BORDER_DARK = new Color(40, 40, 40, 100);
/*  85 */   protected Color ARROW = new Color(40, 40, 40, 160);
/*     */   
/*  87 */   protected Color LIST_BACKGROUND_ODD = new Color(160, 160, 160);
/*  88 */   protected Color LIST_BACKGROUND_EVEN = new Color(180, 180, 180);
/*  89 */   protected Color LIST_BACKGROUND_SELECTED = new Color(50, 50, 50);
/*  90 */   protected Color LIST_TEXT_SELECTED = new Color(180, 180, 180);
/*  91 */   protected Color LIST_TEXT_PASSIVE = new Color(30, 30, 30);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paint(Graphics g, JComponent c)
/*     */   {
/*  98 */     Graphics2D g2d = (Graphics2D)g;
/*  99 */     JComboBox box = (JComboBox)c;
/* 100 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 101 */     Insets insets = box.getInsets();
/* 102 */     int x = insets.left;
/* 103 */     int y = insets.top;
/* 104 */     int width = box.getWidth() - insets.left - insets.right;
/* 105 */     int height = box.getHeight() - insets.top - insets.bottom;
/*     */     Color text;
/* 107 */     Color bgTop; Color bgBottom; Color text; if (box.isPopupVisible()) {
/* 108 */       Color bgTop = this.BACKGROUND_TOP_ACTIVE;
/* 109 */       Color bgBottom = this.BACKGROUND_BOTTOM_ACTIVE;
/* 110 */       text = this.TEXT_ACTIVE;
/*     */     } else {
/* 112 */       bgTop = this.BACKGROUND_TOP_PASSIVE;
/* 113 */       bgBottom = this.BACKGROUND_BOTTOM_PASSIVE;
/* 114 */       text = this.TEXT_PASSIVE;
/*     */     }
/* 116 */     RoundRectangle2D shape = new RoundRectangle2D.Float(x, y, width - 1, height - 1, height, height);
/* 117 */     GradientPaint gp = new GradientPaint(0.0F, y, bgTop, 0.0F, y + height, bgBottom, false);
/* 118 */     g2d.setPaint(gp);
/*     */     
/* 120 */     g2d.fill(shape);
/* 121 */     g2d.setColor(this.BORDER_DARK);
/* 122 */     g2d.draw(shape);
/* 123 */     g2d.setFont(g2d.getFont().deriveFont(12.0F));
/* 124 */     int buttonWidth = this.arrowButton.getWidth();
/* 125 */     gp = new GradientPaint(x + width - buttonWidth - 8, 0.0F, text, x + width - buttonWidth + 5, 0.0F, new Color(0, 0, 0, 0), false);
/* 126 */     g2d.setPaint(gp);
/* 127 */     int fontHeight = g2d.getFontMetrics().getAscent();
/* 128 */     int fontY = y + height / 2 + fontHeight / 2 - 1;
/* 129 */     g2d.drawString(box.getSelectedItem().toString(), x + 8, fontY);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void installUI(JComponent c)
/*     */   {
/* 138 */     super.installUI(c);
/* 139 */     JComboBox box = (JComboBox)c;
/* 140 */     box.setOpaque(false);
/* 141 */     box.setMinimumSize(new Dimension(80, 25));
/* 142 */     box.setMaximumSize(new Dimension(400, 25));
/* 143 */     box.setPreferredSize(new Dimension(120, 25));
/* 144 */     box.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
/* 145 */     box.setRenderer(new SlickerPopupListCellRenderer());
/*     */   }
/*     */   
/*     */   protected JButton createArrowButton()
/*     */   {
/* 150 */     JButton arrowButton = new JButton();
/* 151 */     arrowButton.setOpaque(false);
/* 152 */     arrowButton.setBorder(BorderFactory.createEmptyBorder());
/* 153 */     arrowButton.setUI(new SlickerArrowButtonUI(null));
/* 154 */     return arrowButton;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ComboPopup createPopup()
/*     */   {
/* 162 */     return new SlickerComboPopup(this.comboBox);
/*     */   }
/*     */   
/*     */   private class SlickerArrowButtonUI
/*     */     extends BasicButtonUI
/*     */   {
/*     */     private SlickerArrowButtonUI() {}
/*     */     
/*     */     public void paint(Graphics g, JComponent c)
/*     */     {
/* 172 */       Graphics2D g2d = (Graphics2D)g;
/* 173 */       JButton button = (JButton)c;
/* 174 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 175 */       int x = 0;
/* 176 */       int y = 0;
/* 177 */       int width = button.getWidth();
/* 178 */       int height = button.getHeight();
/* 179 */       int xMean = x + width / 2 - 2;
/* 180 */       int yMean = y + height / 2;
/* 181 */       if (SlickerComboBoxUI.this.comboBox.isPopupVisible()) {
/* 182 */         GradientPaint gp = new GradientPaint(0.0F, yMean - 4, new Color(80, 0, 0, 160), 0.0F, yMean + 4, new Color(180, 0, 0), false);
/* 183 */         g2d.setPaint(gp);
/*     */       } else {
/* 185 */         g2d.setColor(SlickerComboBoxUI.this.ARROW);
/*     */       }
/* 187 */       int[] xCoords = { xMean - 4, xMean + 4, xMean };
/* 188 */       int[] yCoords = { yMean - 4, yMean - 4, yMean + 4 };
/* 189 */       g2d.fillPolygon(xCoords, yCoords, 3);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private class SlickerComboPopup
/*     */     extends BasicComboPopup
/*     */   {
/*     */     public SlickerComboPopup(final JComboBox combo)
/*     */     {
/* 199 */       super();
/* 200 */       setBorderPainted(true);
/* 201 */       setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 1));
/* 202 */       setBackground(new Color(180, 180, 180));
/*     */       
/* 204 */       addPopupMenuListener(new PopupMenuListener() {
/*     */         public void popupMenuCanceled(PopupMenuEvent e) {}
/*     */         
/* 207 */         public void popupMenuWillBecomeInvisible(PopupMenuEvent e) { combo.repaint(); }
/*     */         
/*     */         public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
/* 210 */           combo.repaint();
/*     */         }
/*     */       });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected void configureScroller()
/*     */     {
/* 220 */       JScrollBar vBar = this.scroller.getVerticalScrollBar();
/* 221 */       vBar.setUI(new SlickerScrollBarUI(vBar, new Color(180, 180, 180), new Color(20, 20, 20), new Color(80, 80, 80), 3.0F, 11.0F));
/* 222 */       this.scroller.setBackground(new Color(180, 180, 180));
/* 223 */       this.scroller.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected void configureList()
/*     */     {
/* 231 */       super.configureList();
/* 232 */       this.list.setCellRenderer(new SlickerComboBoxUI.SlickerPopupListCellRenderer(SlickerComboBoxUI.this));
/* 233 */       this.list.setBackground(new Color(180, 180, 180));
/* 234 */       this.list.setForeground(new Color(30, 30, 30));
/* 235 */       this.list.setSelectionBackground(new Color(50, 50, 50));
/* 236 */       this.list.setSelectionForeground(new Color(200, 200, 200));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected JList createList()
/*     */     {
/* 244 */       JList list = super.createList();
/* 245 */       list.setCellRenderer(new SlickerComboBoxUI.SlickerPopupListCellRenderer(SlickerComboBoxUI.this));
/* 246 */       return list;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected MouseMotionListener createListMouseMotionListener()
/*     */     {
/* 254 */       new MouseMotionListener()
/*     */       {
/*     */         public void mouseDragged(MouseEvent e) {}
/*     */         
/*     */ 
/*     */         public void mouseMoved(MouseEvent e)
/*     */         {
/* 261 */           int listIndex = SlickerComboBoxUI.SlickerComboPopup.this.list.locationToIndex(e.getPoint());
/* 262 */           if (listIndex >= 0) {
/* 263 */             SlickerComboBoxUI.SlickerComboPopup.this.list.setSelectedIndex(listIndex);
/*     */           }
/*     */         }
/*     */       };
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private class SlickerPopupListCellRenderer
/*     */     extends JLabel
/*     */     implements ListCellRenderer
/*     */   {
/*     */     protected SlickerPopupListCellRenderer()
/*     */     {
/* 277 */       setFont(getFont().deriveFont(12.0F));
/* 278 */       setOpaque(true);
/* 279 */       setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/*     */     {
/* 287 */       setText(value.toString());
/* 288 */       setToolTipText(value.toString());
/* 289 */       if (isSelected) {
/* 290 */         setBackground(SlickerComboBoxUI.this.LIST_BACKGROUND_SELECTED);
/* 291 */         setForeground(SlickerComboBoxUI.this.LIST_TEXT_SELECTED);
/*     */       } else {
/* 293 */         if (index % 2 == 0) {
/* 294 */           setBackground(SlickerComboBoxUI.this.LIST_BACKGROUND_EVEN);
/*     */         } else {
/* 296 */           setBackground(SlickerComboBoxUI.this.LIST_BACKGROUND_ODD);
/*     */         }
/* 298 */         setForeground(SlickerComboBoxUI.this.LIST_TEXT_PASSIVE);
/*     */       }
/* 300 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/ui/SlickerComboBoxUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */