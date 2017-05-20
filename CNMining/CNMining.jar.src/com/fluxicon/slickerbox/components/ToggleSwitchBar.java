/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import java.awt.AlphaComposite;
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
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ToggleSwitchBar
/*     */   extends JComponent
/*     */ {
/*  70 */   protected Color colorOff = new Color(30, 30, 30);
/*  71 */   protected Color colorOffHilight = new Color(80, 80, 80);
/*  72 */   protected Color colorOffText = new Color(140, 140, 140);
/*     */   
/*  74 */   protected Color colorOffOver = new Color(60, 60, 60);
/*  75 */   protected Color colorOffHilightOver = new Color(100, 100, 100);
/*  76 */   protected Color colorOffTextOver = new Color(190, 190, 190);
/*     */   
/*  78 */   protected Color colorOn = new Color(0, 70, 0);
/*  79 */   protected Color colorOnHilight = new Color(0, 130, 0);
/*  80 */   protected Color colorOnText = new Color(230, 230, 230);
/*     */   
/*  82 */   protected Color colorOnOver = new Color(0, 120, 0);
/*  83 */   protected Color colorOnHilightOver = new Color(0, 200, 0);
/*  84 */   protected Color colorOnTextOver = new Color(250, 250, 250);
/*     */   
/*     */   protected BufferedImage bgOff;
/*     */   
/*     */   protected BufferedImage bgOffOver;
/*     */   protected BufferedImage bgOn;
/*     */   protected BufferedImage bgOnOver;
/*  91 */   protected int internalBorder = 10;
/*  92 */   protected int height = 25;
/*     */   
/*     */   protected int[] boundaries;
/*  95 */   protected int mouseX = -1;
/*     */   
/*     */   protected String[] toggleNames;
/*     */   protected boolean[] toggles;
/*     */   protected ArrayList<ActionListener> listeners;
/*     */   
/*     */   public ToggleSwitchBar(String[] toggleNames, boolean initial)
/*     */   {
/* 103 */     this.listeners = new ArrayList();
/* 104 */     this.toggleNames = toggleNames;
/* 105 */     this.toggles = new boolean[toggleNames.length];
/* 106 */     Arrays.fill(this.toggles, initial);
/* 107 */     this.bgOff = createBackground(this.colorOff, this.colorOffHilight, this.height);
/* 108 */     this.bgOn = createBackground(this.colorOn, this.colorOnHilight, this.height);
/* 109 */     this.bgOffOver = createBackground(this.colorOffOver, this.colorOffHilightOver, this.height);
/* 110 */     this.bgOnOver = createBackground(this.colorOnOver, this.colorOnHilightOver, this.height);
/* 111 */     this.boundaries = new int[this.toggles.length];
/* 112 */     setFont(new JLabel("test").getFont().deriveFont(12.0F));
/* 113 */     FontMetrics fMetrics = getFontMetrics(getFont());
/* 114 */     int boundary = 0;
/* 115 */     for (int i = 0; i < this.toggles.length; i++) {
/* 116 */       String toggleName = this.toggleNames[i];
/* 117 */       int width = (int)fMetrics.getStringBounds(toggleName, getGraphics()).getWidth();
/* 118 */       boundary += width;
/* 119 */       boundary += this.internalBorder;
/* 120 */       boundary += this.internalBorder;
/* 121 */       boundary += this.internalBorder;
/* 122 */       this.boundaries[i] = boundary;
/*     */     }
/* 124 */     Dimension dim = new Dimension(boundary, this.height);
/* 125 */     setMinimumSize(dim);
/* 126 */     setMaximumSize(dim);
/* 127 */     setPreferredSize(dim);
/* 128 */     addMouseListener(new MouseListener() {
/*     */       public void mouseClicked(MouseEvent e) {
/* 130 */         if (e.getButton() == 1) {
/* 131 */           int index = ToggleSwitchBar.this.determineIndex(e.getX());
/* 132 */           if (index < 0) return;
/* 133 */           ToggleSwitchBar.this.toggles[index] = (ToggleSwitchBar.this.toggles[index] != 0 ? 0 : true);
/* 134 */           ToggleSwitchBar.this.repaint();
/* 135 */           ToggleSwitchBar.this.notifyListeners();
/*     */         }
/*     */       }
/*     */       
/* 139 */       public void mouseEntered(MouseEvent e) { ToggleSwitchBar.this.mouseX = e.getX();
/* 140 */         ToggleSwitchBar.this.repaint();
/*     */       }
/*     */       
/* 143 */       public void mouseExited(MouseEvent e) { ToggleSwitchBar.this.mouseX = -1;
/* 144 */         ToggleSwitchBar.this.repaint();
/*     */       }
/*     */       
/*     */ 
/*     */       public void mousePressed(MouseEvent e) {}
/*     */       
/*     */ 
/*     */       public void mouseReleased(MouseEvent e) {}
/* 152 */     });
/* 153 */     addMouseMotionListener(new MouseMotionListener() {
/*     */       public void mouseDragged(MouseEvent e) {
/* 155 */         ToggleSwitchBar.this.mouseX = e.getX();
/* 156 */         ToggleSwitchBar.this.repaint();
/*     */       }
/*     */       
/* 159 */       public void mouseMoved(MouseEvent e) { ToggleSwitchBar.this.mouseX = e.getX();
/* 160 */         ToggleSwitchBar.this.repaint();
/*     */       }
/* 162 */     });
/* 163 */     setOpaque(false);
/*     */   }
/*     */   
/*     */   public boolean getToggle(int index) {
/* 167 */     return this.toggles[index];
/*     */   }
/*     */   
/*     */   public void setAllToggles(boolean value) {
/* 171 */     Arrays.fill(this.toggles, value);
/* 172 */     repaint();
/*     */   }
/*     */   
/*     */   public boolean getToggle(String name) {
/* 176 */     for (int i = 0; i < this.toggles.length; i++) {
/* 177 */       if (this.toggleNames[i].equals(name)) {
/* 178 */         return this.toggles[i];
/*     */       }
/*     */     }
/* 181 */     throw new IllegalArgumentException("Unknown toggle name used for addressing! (" + name + ")");
/*     */   }
/*     */   
/*     */   public void addActionListener(ActionListener listener) {
/* 185 */     this.listeners.add(listener);
/*     */   }
/*     */   
/*     */   public void removeActionListener(ActionListener listener) {
/* 189 */     this.listeners.remove(listener);
/*     */   }
/*     */   
/*     */   protected void notifyListeners() {
/* 193 */     ActionEvent event = new ActionEvent(this, 1, "toggles modified");
/* 194 */     for (ActionListener listener : this.listeners) {
/* 195 */       listener.actionPerformed(event);
/*     */     }
/*     */   }
/*     */   
/*     */   protected int determineIndex(int mouseX) {
/* 200 */     if (mouseX < 0) return -1;
/* 201 */     for (int i = 0; i < this.boundaries.length; i++) {
/* 202 */       if (this.boundaries[i] > mouseX) {
/* 203 */         return i;
/*     */       }
/*     */     }
/* 206 */     return -1;
/*     */   }
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 210 */     int width = getWidth();
/* 211 */     int height = getHeight();
/* 212 */     BufferedImage buffer = new BufferedImage(width, height, 2);
/* 213 */     Graphics2D g2d = buffer.createGraphics();
/* 214 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 216 */     g2d.setColor(new Color(0, 0, 0, 255));
/* 217 */     g2d.fillRoundRect(0, 0, width - 1, height - 1, 15, 15);
/*     */     
/* 219 */     int mouseIndex = -1;
/* 220 */     if (this.mouseX >= 0) {
/* 221 */       mouseIndex = determineIndex(this.mouseX);
/*     */     }
/* 223 */     int x = 0;
/* 224 */     for (int i = 0; i < this.boundaries.length; i++) {
/* 225 */       int cellWidth = this.boundaries[i] - x;
/*     */       
/*     */ 
/* 228 */       g2d.setComposite(AlphaComposite.SrcIn);
/* 229 */       Color textColor; Color textColor; if (i == mouseIndex) { Color textColor;
/* 230 */         if (this.toggles[i] != 0) {
/* 231 */           g2d.drawImage(this.bgOnOver, x, 0, cellWidth, height, this);
/* 232 */           textColor = this.colorOnTextOver;
/*     */         } else {
/* 234 */           g2d.drawImage(this.bgOffOver, x, 0, cellWidth, height, this);
/* 235 */           textColor = this.colorOffTextOver;
/*     */         }
/*     */       } else { Color textColor;
/* 238 */         if (this.toggles[i] != 0) {
/* 239 */           g2d.drawImage(this.bgOn, x, 0, cellWidth, height, this);
/* 240 */           textColor = this.colorOnText;
/*     */         } else {
/* 242 */           g2d.drawImage(this.bgOff, x, 0, cellWidth, height, this);
/* 243 */           textColor = this.colorOffText;
/*     */         }
/*     */       }
/*     */       
/* 247 */       g2d.setComposite(AlphaComposite.SrcOver);
/* 248 */       g2d.setColor(new Color(0, 0, 0, 200));
/* 249 */       g2d.drawString(this.toggleNames[i], x + this.internalBorder + 1, height - 7);
/* 250 */       g2d.setColor(textColor);
/* 251 */       g2d.drawString(this.toggleNames[i], x + this.internalBorder, height - 8);
/*     */       
/* 253 */       if (i + 1 < this.boundaries.length) {
/* 254 */         g2d.setColor(new Color(0, 0, 0, 50));
/* 255 */         g2d.drawLine(this.boundaries[i] - 2, 0, this.boundaries[i] - 2, height);
/* 256 */         g2d.setColor(new Color(0, 0, 0, 70));
/* 257 */         g2d.drawLine(this.boundaries[i] - 1, 0, this.boundaries[i] - 1, height);
/*     */       }
/*     */       
/* 260 */       x = this.boundaries[i];
/*     */     }
/*     */     
/* 263 */     g2d.setColor(new Color(0, 0, 0, 90));
/* 264 */     g2d.drawRoundRect(0, 0, width - 1, height - 1, 15, 15);
/* 265 */     g2d.setColor(new Color(0, 0, 0, 50));
/* 266 */     g2d.drawRoundRect(1, 1, width - 3, height - 3, 15, 15);
/*     */     
/* 268 */     g2d.dispose();
/* 269 */     g.drawImage(buffer, 0, 0, this);
/*     */   }
/*     */   
/*     */   protected BufferedImage createBackground(Color color, Color hilight, int height) {
/* 273 */     int tip = height / 3;
/* 274 */     BufferedImage bg = new BufferedImage(1, height, 2);
/* 275 */     Graphics2D g2dBuf = (Graphics2D)bg.getGraphics();
/* 276 */     GradientPaint gradient = new GradientPaint(0.0F, 0.0F, color, 0.0F, tip, hilight, false);
/* 277 */     g2dBuf.setPaint(gradient);
/* 278 */     g2dBuf.fillRect(0, 0, 1, tip);
/* 279 */     gradient = new GradientPaint(0.0F, tip, hilight, 0.0F, height, color, false);
/* 280 */     g2dBuf.setPaint(gradient);
/* 281 */     g2dBuf.fillRect(0, tip, 1, height);
/* 282 */     g2dBuf.dispose();
/* 283 */     return bg;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/ToggleSwitchBar.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */