/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.CaretEvent;
/*     */ import javax.swing.event.CaretListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlickerSearchField
/*     */   extends JPanel
/*     */ {
/*     */   protected ArrayList<ActionListener> searchListeners;
/*     */   protected ResetButton resetButton;
/*     */   protected BufferedImage searchImage;
/*     */   protected JTextField inputField;
/*     */   protected int width;
/*     */   protected int height;
/*     */   protected Color colorBg;
/*     */   protected Color colorPassive;
/*     */   protected Color colorActive;
/*     */   protected Color colorMouseOver;
/*     */   protected String searchAdvertiser;
/*     */   
/*     */   public SlickerSearchField(int width, int height, Color background, Color passive, Color mouseover, Color active)
/*     */   {
/*  91 */     setFocusable(true);
/*  92 */     addFocusListener(new FocusListener()
/*     */     {
/*  94 */       public void focusGained(FocusEvent e) { SlickerSearchField.this.inputField.requestFocusInWindow(); }
/*     */       
/*     */       public void focusLost(FocusEvent e) {
/*  97 */         if (SlickerSearchField.this.inputField.getText().trim().length() == 0) {
/*  98 */           SlickerSearchField.this.inputField.setText(SlickerSearchField.this.searchAdvertiser);
/*     */         }
/*     */       }
/* 101 */     });
/* 102 */     this.searchListeners = new ArrayList();
/* 103 */     this.colorBg = background;
/* 104 */     this.colorPassive = passive;
/* 105 */     this.colorMouseOver = mouseover;
/* 106 */     this.colorActive = active;
/* 107 */     this.height = height;
/* 108 */     this.width = width;
/* 109 */     this.searchAdvertiser = "search...";
/* 110 */     setMinimumSize(new Dimension(height * 4, height));
/* 111 */     setMaximumSize(new Dimension(width, height));
/* 112 */     setPreferredSize(new Dimension(width, height));
/* 113 */     setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
/* 114 */     this.searchImage = createSearchImage(this.colorMouseOver, height - 4);
/* 115 */     setOpaque(false);
/* 116 */     setLayout(new BoxLayout(this, 0));
/* 117 */     this.inputField = new JTextField(this.searchAdvertiser);
/* 118 */     this.inputField.setBorder(BorderFactory.createEmptyBorder());
/* 119 */     this.inputField.setBackground(this.colorBg);
/* 120 */     this.inputField.setForeground(this.colorPassive);
/* 121 */     this.inputField.setSelectedTextColor(this.colorBg);
/* 122 */     this.inputField.setSelectionColor(this.colorMouseOver);
/* 123 */     this.inputField.setMinimumSize(new Dimension(height, height));
/* 124 */     this.inputField.setMaximumSize(new Dimension(500, height));
/* 125 */     this.inputField.setPreferredSize(new Dimension(200, height));
/* 126 */     this.inputField.addCaretListener(new CaretListener() {
/*     */       public void caretUpdate(CaretEvent arg0) {
/* 128 */         SlickerSearchField.this.searchUpdated();
/*     */       }
/* 130 */     });
/* 131 */     this.inputField.addFocusListener(new FocusListener() {
/*     */       public void focusGained(FocusEvent arg0) {
/* 133 */         if (SlickerSearchField.this.inputField.getText().equals(SlickerSearchField.this.searchAdvertiser)) {
/* 134 */           SlickerSearchField.this.inputField.setText("");
/* 135 */           SlickerSearchField.this.inputField.setForeground(SlickerSearchField.this.colorMouseOver);
/*     */         }
/*     */       }
/*     */       
/* 139 */       public void focusLost(FocusEvent arg0) { if (SlickerSearchField.this.inputField.getText().trim().length() == 0) {
/* 140 */           SlickerSearchField.this.inputField.setForeground(SlickerSearchField.this.colorPassive);
/* 141 */           SlickerSearchField.this.inputField.setText(SlickerSearchField.this.searchAdvertiser);
/*     */         }
/*     */       }
/* 144 */     });
/* 145 */     this.inputField.addKeyListener(new KeyListener() { public void keyPressed(KeyEvent e) {}
/*     */       
/*     */       public void keyReleased(KeyEvent e) {}
/*     */       
/* 149 */       public void keyTyped(KeyEvent e) { if (e.getKeyChar() == '\033') {
/* 150 */           SlickerSearchField.this.inputField.setText("");
/* 151 */           SlickerSearchField.this.repaint();
/* 152 */           SlickerSearchField.this.searchUpdated();
/*     */         }
/*     */       }
/* 155 */     });
/* 156 */     this.resetButton = new ResetButton(height - 4);
/* 157 */     this.resetButton.setActive(this.colorActive);
/* 158 */     this.resetButton.setPassive(this.colorPassive);
/* 159 */     this.resetButton.setMouseOver(this.colorMouseOver);
/* 160 */     this.resetButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/* 162 */         SlickerSearchField.this.inputField.setText(SlickerSearchField.this.searchAdvertiser);
/* 163 */         SlickerSearchField.this.repaint();
/* 164 */         SlickerSearchField.this.searchUpdated();
/*     */       }
/* 166 */     });
/* 167 */     this.resetButton.setVisible(false);
/* 168 */     add(Box.createHorizontalStrut(height));
/* 169 */     add(this.inputField);
/* 170 */     add(this.resetButton);
/*     */   }
/*     */   
/*     */   public void addSearchListener(ActionListener listener) {
/* 174 */     this.searchListeners.add(listener);
/*     */   }
/*     */   
/*     */   public void removeSearchListener(ActionListener listener) {
/* 178 */     this.searchListeners.remove(listener);
/*     */   }
/*     */   
/*     */   public void addKeyListener(KeyListener listener) {
/* 182 */     super.addKeyListener(listener);
/* 183 */     this.inputField.addKeyListener(listener);
/*     */   }
/*     */   
/*     */   public void removeKeyListener(KeyListener listener) {
/* 187 */     super.removeKeyListener(listener);
/* 188 */     this.inputField.removeKeyListener(listener);
/*     */   }
/*     */   
/*     */   protected void searchUpdated() {
/* 192 */     String search = this.inputField.getText();
/* 193 */     if ((search.equals(this.searchAdvertiser)) || (search.trim().length() == 0)) {
/* 194 */       search = "";
/* 195 */       this.resetButton.setVisible(false);
/*     */     } else {
/* 197 */       this.resetButton.setVisible(true);
/*     */     }
/* 199 */     repaint();
/* 200 */     ActionEvent event = new ActionEvent(this, 0, search);
/* 201 */     for (ActionListener listener : this.searchListeners) {
/* 202 */       listener.actionPerformed(event);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getSearchText() {
/* 207 */     String searchText = this.inputField.getText();
/* 208 */     if (searchText.equals(this.searchAdvertiser)) {
/* 209 */       searchText = "";
/*     */     }
/* 211 */     return searchText;
/*     */   }
/*     */   
/*     */   public void setSearchText(String text) {
/* 215 */     if (text.length() == 0) {
/* 216 */       text = this.searchAdvertiser;
/*     */     }
/* 218 */     this.inputField.setText(text);
/* 219 */     repaint();
/* 220 */     searchUpdated();
/*     */   }
/*     */   
/*     */   public void reset() {
/* 224 */     this.inputField.setText(this.searchAdvertiser);
/* 225 */     repaint();
/* 226 */     searchUpdated();
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/* 230 */     if (this.inputField != null) {
/* 231 */       this.inputField.setFont(font);
/*     */     } else {
/* 233 */       super.setFont(font);
/*     */     }
/*     */   }
/*     */   
/*     */   public Font getFont() {
/* 238 */     if (this.inputField != null) {
/* 239 */       return this.inputField.getFont();
/*     */     }
/* 241 */     return super.getFont();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void requestFocus()
/*     */   {
/* 250 */     this.inputField.requestFocus();
/*     */   }
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 254 */     int width = getWidth();
/* 255 */     int height = getHeight();
/* 256 */     Graphics2D g2d = (Graphics2D)g;
/* 257 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 258 */     g2d.setColor(this.colorBg);
/* 259 */     g2d.fillRoundRect(0, 0, width - 1, height - 1, height, height);
/* 260 */     g2d.drawImage(this.searchImage, 2, 2, this);
/*     */   }
/*     */   
/*     */   public BufferedImage createSearchImage(Color color, int size) {
/* 264 */     BufferedImage searchImage = new BufferedImage(this.height, this.height, 2);
/* 265 */     Graphics2D g2d = (Graphics2D)searchImage.getGraphics();
/* 266 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 267 */     g2d.setColor(color);
/* 268 */     g2d.fillOval(0, 0, size - 1, size - 1);
/* 269 */     AlphaComposite alphaComp = AlphaComposite.getInstance(8, 1.0F);
/* 270 */     g2d.setComposite(alphaComp);
/* 271 */     g2d.setColor(new Color(0, 0, 0, 255));
/* 272 */     Graphics2D g2dc = (Graphics2D)g2d.create();
/* 273 */     AffineTransform mod = g2dc.getTransform();
/* 274 */     double rotCenter = size / 2.0D;
/* 275 */     float stroke = size / 9.0F;
/* 276 */     int border = (int)stroke + 2;
/* 277 */     int ovalSize = (int)((size - border - border - 1) * 0.7D);
/* 278 */     int vBorder = (size - ovalSize) / 2;
/* 279 */     mod.rotate(Math.toRadians(45.0D), rotCenter - 0.5D, rotCenter - 0.5D);
/* 280 */     g2dc.setTransform(mod);
/* 281 */     g2dc.setStroke(new BasicStroke(stroke));
/* 282 */     g2dc.drawOval(border, vBorder, ovalSize, ovalSize);
/* 283 */     g2dc.drawLine(border + ovalSize, (int)rotCenter, size - border - 1, (int)rotCenter);
/* 284 */     g2dc.dispose();
/* 285 */     g2d.dispose();
/* 286 */     return searchImage;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/SlickerSearchField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */