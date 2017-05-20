/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.GraphicsUtilities;
/*     */ import com.fluxicon.slickerbox.util.RuntimeUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.RoundRectangle2D.Double;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.event.AncestorEvent;
/*     */ import javax.swing.event.AncestorListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlickerButton
/*     */   extends JButton
/*     */ {
/*  81 */   protected Color COLOR_BG = new Color(70, 70, 70);
/*  82 */   protected Color COLOR_HILIGHT = new Color(170, 170, 170);
/*  83 */   protected Color COLOR_TEXT = new Color(240, 240, 240, 230);
/*     */   
/*  85 */   protected Color COLOR_BG_FOCUS = new Color(20, 20, 20);
/*  86 */   protected Color COLOR_HILIGHT_FOCUS = new Color(140, 140, 140);
/*  87 */   protected Color COLOR_TEXT_FOCUS = new Color(250, 250, 250, 230);
/*     */   
/*  89 */   protected Color COLOR_BG_MOUSEOVER = new Color(100, 0, 0);
/*  90 */   protected Color COLOR_HILIGHT_MOUSEOVER = new Color(255, 20, 0);
/*  91 */   protected Color COLOR_TEXT_MOUSEOVER = new Color(255, 255, 255, 240);
/*     */   
/*  93 */   protected Color COLOR_BG_ACTIVE = new Color(220, 0, 0);
/*  94 */   protected Color COLOR_HILIGHT_ACTIVE = new Color(255, 180, 0);
/*  95 */   protected Color COLOR_TEXT_ACTIVE = new Color(255, 255, 255, 230);
/*     */   
/*  97 */   protected Color COLOR_BG_DISABLED = new Color(110, 110, 110);
/*  98 */   protected Color COLOR_HILIGHT_DISABLED = new Color(110, 110, 110);
/*  99 */   protected Color COLOR_TEXT_DISABLED = new Color(220, 220, 220, 100);
/*     */   
/*     */   protected BufferedImage passiveBgBuffer;
/*     */   
/*     */   protected BufferedImage focusBgBuffer;
/*     */   protected BufferedImage mouseOverBgBuffer;
/*     */   protected BufferedImage activeBgBuffer;
/*     */   protected BufferedImage disabledBgBuffer;
/* 107 */   protected Thread animationThread = null;
/*     */   
/*     */   protected BufferedImage shinyImage;
/*     */   protected int shinyPosition;
/*     */   protected int width;
/* 112 */   protected int height = 25;
/* 113 */   protected int hBorder = 20;
/* 114 */   protected float fontSize = 12.0F;
/*     */   
/* 116 */   protected boolean mouseOver = false;
/* 117 */   protected boolean isActive = false;
/* 118 */   protected boolean isVisible = false;
/*     */   
/*     */   public SlickerButton(String text) {
/* 121 */     super(text);
/* 122 */     initialize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SlickerButton()
/*     */   {
/* 130 */     initialize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public SlickerButton(Action a)
/*     */   {
/* 137 */     super(a);
/* 138 */     initialize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public SlickerButton(Icon icon)
/*     */   {
/* 145 */     super(icon);
/* 146 */     initialize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SlickerButton(String text, Icon icon)
/*     */   {
/* 154 */     super(text, icon);
/* 155 */     initialize();
/*     */   }
/*     */   
/*     */   protected void initialize() {
/* 159 */     String text = getText();
/* 160 */     text = RuntimeUtils.stripHtml(text);
/* 161 */     super.setText(text);
/* 162 */     setDoubleBuffered(true);
/* 163 */     setBorder(BorderFactory.createEmptyBorder());
/* 164 */     setFont(getFont().deriveFont(this.fontSize));
/* 165 */     Rectangle2D stringBounds = 
/* 166 */       getFontMetrics(getFont()).getStringBounds(text, getGraphics());
/* 167 */     this.width = ((int)stringBounds.getWidth() + this.hBorder + this.hBorder);
/* 168 */     Dimension dim = new Dimension(this.width, this.height);
/* 169 */     setMinimumSize(dim);
/* 170 */     setMaximumSize(dim);
/* 171 */     setPreferredSize(dim);
/* 172 */     setOpaque(false);
/*     */     
/* 174 */     this.passiveBgBuffer = createBackgroundBuffer(this.COLOR_BG, this.COLOR_HILIGHT);
/* 175 */     this.focusBgBuffer = createBackgroundBuffer(this.COLOR_BG_FOCUS, this.COLOR_HILIGHT_FOCUS);
/* 176 */     this.mouseOverBgBuffer = createBackgroundBuffer(this.COLOR_BG_MOUSEOVER, this.COLOR_HILIGHT_MOUSEOVER);
/* 177 */     this.activeBgBuffer = createBackgroundBuffer(this.COLOR_BG_ACTIVE, this.COLOR_HILIGHT_ACTIVE);
/* 178 */     this.disabledBgBuffer = createBackgroundBuffer(this.COLOR_BG_DISABLED, this.COLOR_HILIGHT_DISABLED);
/*     */     
/* 180 */     addAncestorListener(new AncestorListener() {
/*     */       public void ancestorAdded(AncestorEvent evt) {
/* 182 */         if (!SlickerButton.this.isVisible) {
/* 183 */           SlickerButton.this.isVisible = true;
/* 184 */           if (SlickerButton.this.hasFocus())
/* 185 */             SlickerButton.this.startAnimationThread();
/*     */         }
/*     */       }
/*     */       
/*     */       public void ancestorMoved(AncestorEvent evt) {}
/*     */       
/* 191 */       public void ancestorRemoved(AncestorEvent evt) { SlickerButton.this.isVisible = false;
/* 192 */         SlickerButton.this.repaint();
/*     */       }
/*     */       
/* 195 */     });
/* 196 */     addFocusListener(new FocusListener() {
/*     */       public void focusGained(FocusEvent evt) {
/* 198 */         SlickerButton.this.isVisible = true;
/* 199 */         SlickerButton.this.startAnimationThread();
/*     */       }
/*     */       
/* 202 */       public void focusLost(FocusEvent evt) { SlickerButton.this.isVisible = false;
/* 203 */         SlickerButton.this.repaint();
/*     */       }
/*     */       
/* 206 */     });
/* 207 */     addKeyListener(new KeyListener() {
/*     */       public void keyPressed(KeyEvent evt) {
/* 209 */         if ((evt.getKeyChar() == '\n') && 
/* 210 */           (SlickerButton.this.isEnabled())) {
/* 211 */           SlickerButton.this.doClick();
/*     */         }
/*     */       }
/*     */       
/*     */       public void keyReleased(KeyEvent evt) {}
/*     */       
/*     */       public void keyTyped(KeyEvent evt) {}
/* 218 */     });
/* 219 */     addMouseListener(new MouseListener() {
/*     */       public void mouseClicked(MouseEvent arg0) {}
/*     */       
/* 222 */       public void mouseEntered(MouseEvent arg0) { SlickerButton.this.mouseOver = true;
/* 223 */         SlickerButton.this.repaint();
/*     */       }
/*     */       
/* 226 */       public void mouseExited(MouseEvent arg0) { SlickerButton.this.mouseOver = false;
/* 227 */         SlickerButton.this.repaint();
/*     */       }
/*     */       
/* 230 */       public void mousePressed(MouseEvent arg0) { SlickerButton.this.isActive = true;
/* 231 */         SlickerButton.this.repaint();
/*     */       }
/*     */       
/* 234 */       public void mouseReleased(MouseEvent arg0) { SlickerButton.this.isActive = false;
/* 235 */         SlickerButton.this.repaint();
/*     */       }
/*     */       
/* 238 */     });
/* 239 */     createShinyOverlay();
/* 240 */     this.shinyPosition = Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   protected synchronized void startAnimationThread() {
/* 244 */     if (this.animationThread != null) {
/* 245 */       return;
/*     */     }
/* 247 */     this.animationThread = new Thread() {
/*     */       public void run() {
/* 249 */         while ((SlickerButton.this.isVisible) && (SlickerButton.this.hasFocus())) {
/* 250 */           for (int x = 0 - SlickerButton.this.shinyImage.getWidth(); x < SlickerButton.this.getWidth(); x += 7) {
/* 251 */             SlickerButton.this.shinyPosition = x;
/* 252 */             SlickerButton.this.repaint();
/* 253 */             if ((!SlickerButton.this.isVisible) || (!SlickerButton.this.hasFocus())) {
/*     */               break;
/*     */             }
/*     */             try {
/* 257 */               Thread.sleep(60L);
/*     */             }
/*     */             catch (InterruptedException e) {
/* 260 */               e.printStackTrace();
/* 261 */               break;
/*     */             }
/*     */           }
/* 264 */           SlickerButton.this.shinyPosition = SlickerButton.this.getWidth();
/* 265 */           SlickerButton.this.repaint();
/*     */           try {
/* 267 */             Thread.sleep(2600L);
/*     */           }
/*     */           catch (InterruptedException e) {
/* 270 */             e.printStackTrace();
/* 271 */             break;
/*     */           }
/*     */         }
/*     */         
/* 275 */         SlickerButton.this.shinyPosition = SlickerButton.this.getWidth();
/* 276 */         SlickerButton.this.animationThread = null;
/*     */       }
/* 278 */     };
/* 279 */     this.animationThread.start();
/*     */   }
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 283 */     int width = getWidth();
/* 284 */     int height = getHeight();
/* 285 */     Shape allClip = g.getClip();
/* 286 */     Area pillClip = new Area(new RoundRectangle2D.Double(2.0D, 2.0D, width - 4, height - 4, height - 4, height - 4));
/* 287 */     pillClip = new Area(pillClip);
/* 288 */     pillClip.intersect(new Area(allClip));
/* 289 */     Graphics2D g2d = (Graphics2D)g.create();
/* 290 */     String title = getText();
/* 291 */     g2d.setFont(getFont());
/* 292 */     Rectangle2D stringBounds = g.getFontMetrics(getFont()).getStringBounds(title, g);
/* 293 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 294 */     Color text = null;
/* 295 */     BufferedImage bgBuffer = null;
/* 296 */     if ((this.isActive) && (isEnabled())) {
/* 297 */       text = this.COLOR_TEXT_ACTIVE;
/* 298 */       bgBuffer = this.activeBgBuffer;
/* 299 */     } else if ((this.mouseOver) && (isEnabled())) {
/* 300 */       text = this.COLOR_TEXT_MOUSEOVER;
/* 301 */       bgBuffer = this.mouseOverBgBuffer;
/* 302 */     } else if (hasFocus()) {
/* 303 */       text = this.COLOR_TEXT_FOCUS;
/* 304 */       bgBuffer = this.focusBgBuffer;
/* 305 */     } else if (!isEnabled()) {
/* 306 */       text = this.COLOR_TEXT_DISABLED;
/* 307 */       bgBuffer = this.disabledBgBuffer;
/*     */     } else {
/* 309 */       text = this.COLOR_TEXT;
/* 310 */       bgBuffer = this.passiveBgBuffer;
/*     */     }
/*     */     
/* 313 */     g2d.setClip(pillClip);
/* 314 */     g2d.drawImage(bgBuffer, 0, 0, width, height, null);
/* 315 */     g2d.setClip(allClip);
/*     */     
/* 317 */     int fontX = (width - (int)stringBounds.getWidth()) / 2;
/* 318 */     int fontY = height - (height - (int)stringBounds.getHeight()) / 2 - 3;
/* 319 */     g2d.setColor(text);
/* 320 */     g2d.drawString(title, fontX, fontY);
/*     */     
/* 322 */     if (this.shinyPosition < width)
/*     */     {
/* 324 */       g2d.setClip(pillClip);
/* 325 */       g2d.drawImage(this.shinyImage, this.shinyPosition, 0, null);
/* 326 */       g2d.setClip(allClip);
/*     */     }
/*     */     
/* 329 */     g2d.setColor(new Color(0, 0, 0, 120));
/* 330 */     g2d.drawRoundRect(2, 2, width - 5, height - 5, height - 4, height - 4);
/* 331 */     g2d.setColor(new Color(0, 0, 0, 90));
/* 332 */     g2d.drawRoundRect(3, 3, width - 7, height - 7, height - 6, height - 6);
/* 333 */     g2d.setColor(new Color(0, 0, 0, 50));
/* 334 */     g2d.drawRoundRect(4, 4, width - 9, height - 9, height - 8, height - 8);
/* 335 */     g2d.setColor(new Color(0, 0, 0, 30));
/* 336 */     g2d.drawRoundRect(5, 5, width - 11, height - 11, height - 10, height - 10);
/* 337 */     g2d.setColor(new Color(0, 0, 0, 20));
/* 338 */     g2d.drawRoundRect(1, 1, width - 3, height - 3, height - 2, height - 2);
/* 339 */     g2d.setColor(new Color(0, 0, 0, 10));
/* 340 */     g2d.drawRoundRect(6, 6, width - 13, height - 13, height - 12, height - 12);
/*     */     
/* 342 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   protected void createShinyOverlay() {
/* 346 */     int gradientWidth = (int)(1.5D * this.height);
/* 347 */     Color shine = new Color(255, 255, 255, 70);
/* 348 */     Color transparent = new Color(0, 0, 0, 0);
/* 349 */     this.shinyImage = GraphicsUtilities.createTranslucentCompatibleImage(this.width * 2, this.height);
/* 350 */     Graphics2D g2d = this.shinyImage.createGraphics();
/* 351 */     AffineTransform trans = g2d.getTransform();
/* 352 */     trans.rotate(Math.toRadians(60.0D), this.width, this.height / 2);
/* 353 */     g2d.setTransform(trans);
/* 354 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 355 */     GradientPaint gradient = new GradientPaint(this.width - gradientWidth, 0.0F, transparent, this.width, 0.0F, shine, false);
/* 356 */     g2d.setPaint(gradient);
/* 357 */     g2d.fillRect(this.width - gradientWidth, -this.width, gradientWidth, this.width * 2);
/* 358 */     gradient = new GradientPaint(this.width, 0.0F, shine, this.width + gradientWidth, 0.0F, transparent, false);
/* 359 */     g2d.setPaint(gradient);
/* 360 */     g2d.fillRect(this.width, -this.width, gradientWidth, this.width * 2);
/* 361 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   protected BufferedImage createBackgroundBuffer(Color bg, Color hilight) {
/* 365 */     BufferedImage bgBuffer = GraphicsUtilities.createTranslucentCompatibleImage(1, this.height);
/* 366 */     Graphics2D g2d = bgBuffer.createGraphics();
/* 367 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 368 */     GradientPaint gradient = new GradientPaint(0.0F, 0.0F, hilight, 0.0F, this.height - this.height / 3, bg, false);
/* 369 */     g2d.setPaint(gradient);
/* 370 */     g2d.fillRect(0, 0, 1, this.height);
/* 371 */     g2d.dispose();
/* 372 */     return bgBuffer;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setText(String text)
/*     */   {
/* 380 */     Font font = getFont();
/* 381 */     if (font != null) {
/* 382 */       FontMetrics fm = getFontMetrics(getFont());
/* 383 */       if (fm != null) {
/* 384 */         Rectangle2D stringBounds = 
/* 385 */           fm.getStringBounds(text, getGraphics());
/* 386 */         this.width = ((int)stringBounds.getWidth() + this.hBorder + this.hBorder);
/* 387 */         Dimension dim = new Dimension(this.width, this.height);
/* 388 */         setMinimumSize(dim);
/* 389 */         setMaximumSize(dim);
/* 390 */         setPreferredSize(dim);
/* 391 */         revalidate();
/*     */       }
/*     */     }
/* 394 */     super.setText(text);
/* 395 */     repaint();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/SlickerButton.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */