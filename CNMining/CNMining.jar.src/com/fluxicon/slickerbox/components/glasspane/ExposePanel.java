/*     */ package com.fluxicon.slickerbox.components.glasspane;
/*     */ 
/*     */ import com.fluxicon.slickerbox.components.ResetButton;
/*     */ import com.fluxicon.slickerbox.util.RuntimeUtils;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.beans.PropertyVetoException;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDesktopPane;
/*     */ import javax.swing.JInternalFrame;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExposePanel
/*     */   extends JComponent
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*     */   protected JDesktopPane desktop;
/*     */   protected ActionListener closeListener;
/*     */   protected JInternalFrame[] frames;
/*     */   protected BufferedImage[] frameShots;
/*     */   protected BufferedImage[] frameThumbs;
/*     */   protected int[] originalX;
/*     */   protected int[] originalY;
/*     */   protected int[] originalWidth;
/*     */   protected int[] originalHeight;
/*     */   protected int[] scaledX;
/*     */   protected int[] scaledY;
/*     */   protected int[] scaledWidth;
/*     */   protected int[] scaledHeight;
/*     */   protected BufferedImage topBarBg;
/*     */   protected BufferedImage closeButton;
/*     */   protected BufferedImage parentBuffer;
/*     */   protected int cellsX;
/*     */   protected int cellsY;
/*     */   protected int cellWidth;
/*     */   protected int cellHeight;
/*     */   protected int mouseX;
/*     */   protected int mouseY;
/*     */   protected Thread alphaThread;
/*     */   protected float backgroundAlpha;
/* 109 */   protected Color bgColor = new Color(30, 30, 30, 220);
/*     */   protected GraphicsConfiguration gc;
/*     */   
/*     */   public ExposePanel(JDesktopPane desktop, ActionListener closeListener) {
/* 113 */     this.desktop = desktop;
/* 114 */     this.closeListener = closeListener;
/* 115 */     addMouseListener(this);
/* 116 */     addMouseMotionListener(this);
/* 117 */     setFocusable(true);
/* 118 */     this.frames = null;
/* 119 */     this.frameThumbs = null;
/* 120 */     this.cellsX = 1;
/* 121 */     this.cellsY = 1;
/* 122 */     this.cellWidth = 1;
/* 123 */     this.cellHeight = 1;
/* 124 */     this.mouseX = -1;
/* 125 */     this.mouseY = -1;
/* 126 */     this.backgroundAlpha = 0.0F;
/* 127 */     this.alphaThread = null;
/* 128 */     createTopBarBg();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void processKeyEvent(KeyEvent e)
/*     */   {
/* 138 */     if (e.getKeyChar() == '\033') {
/* 139 */       activateFrame(-1);
/*     */     } else {
/* 141 */       super.processKeyEvent(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void createTopBarBg()
/*     */   {
/* 148 */     this.topBarBg = createCompatibleImage(1, 70);
/* 149 */     Graphics2D g2dBuf = (Graphics2D)this.topBarBg.getGraphics();
/* 150 */     GradientPaint gradient = new GradientPaint(0.0F, 40.0F, new Color(0, 0, 0), 0.0F, 70.0F, this.bgColor, false);
/* 151 */     g2dBuf.setPaint(gradient);
/* 152 */     g2dBuf.fillRect(0, 0, 1, 70);
/* 153 */     g2dBuf.dispose();
/* 154 */     this.closeButton = ResetButton.createCloseImage(new Color(90, 90, 90), 27);
/*     */   }
/*     */   
/*     */   public void showExpose() {
/* 158 */     if (this.desktop.getAllFrames().length == 0) {
/* 159 */       return;
/*     */     }
/* 161 */     if (this.alphaThread == null) {
/* 162 */       setOpaque(true);
/* 163 */       updateFrameData();
/* 164 */       this.backgroundAlpha = 0.0F;
/* 165 */       setVisible(true);
/* 166 */       requestFocusInWindow();
/* 167 */       this.alphaThread = new Thread() {
/*     */         public void run() {
/* 169 */           while (ExposePanel.this.backgroundAlpha < 1.0F) {
/* 170 */             ExposePanel.this.backgroundAlpha += 0.06F;
/* 171 */             if (ExposePanel.this.backgroundAlpha > 1.0F) {
/* 172 */               ExposePanel.this.backgroundAlpha = 1.0F;
/* 173 */               ExposePanel.this.repaint();
/* 174 */               break;
/*     */             }
/* 176 */             ExposePanel.this.repaint();
/*     */             try {
/* 178 */               Thread.sleep(50L);
/*     */             }
/*     */             catch (InterruptedException e) {
/* 181 */               e.printStackTrace();
/*     */             }
/*     */           }
/*     */           
/* 185 */           ExposePanel.this.alphaThread = null;
/*     */         }
/* 187 */       };
/* 188 */       this.alphaThread.start();
/*     */     }
/*     */   }
/*     */   
/*     */   public void activateFrame(int frameIndex) {
/*     */     try {
/* 194 */       setOpaque(false);
/* 195 */       this.backgroundAlpha = 0.0F;
/* 196 */       if ((frameIndex < this.frames.length) && (frameIndex >= 0))
/*     */       {
/* 198 */         this.frames[frameIndex].setSelected(true);
/*     */       }
/*     */       
/* 201 */       this.parentBuffer = createCompatibleImage(getParent().getWidth(), getParent().getHeight());
/* 202 */       getParent().paint(this.parentBuffer.getGraphics());
/* 203 */       this.backgroundAlpha = 1.0F;
/* 204 */       setOpaque(true);
/*     */       
/* 206 */       while (this.alphaThread != null) {
/*     */         try {
/* 208 */           Thread.sleep(100L);
/*     */         }
/*     */         catch (InterruptedException e) {
/* 211 */           e.printStackTrace();
/*     */         }
/*     */       }
/* 214 */       this.alphaThread = new Thread() {
/*     */         public void run() {
/* 216 */           while (ExposePanel.this.backgroundAlpha > 0.0F) {
/* 217 */             ExposePanel.this.backgroundAlpha -= 0.1F;
/* 218 */             if (ExposePanel.this.backgroundAlpha <= 0.0F) {
/* 219 */               ExposePanel.this.backgroundAlpha = 0.0F;
/* 220 */               ExposePanel.this.repaint();
/* 221 */               break;
/*     */             }
/* 223 */             ExposePanel.this.repaint();
/*     */             try {
/* 225 */               Thread.sleep(50L);
/*     */             }
/*     */             catch (InterruptedException e) {
/* 228 */               e.printStackTrace();
/*     */             }
/*     */           }
/* 231 */           ExposePanel.this.setVisible(false);
/* 232 */           ExposePanel.this.alphaThread = null;
/* 233 */           ExposePanel.this.closeListener.actionPerformed(new ActionEvent(this, 0, "Expose closed"));
/*     */           
/* 235 */           ExposePanel.this.frames = null;
/* 236 */           ExposePanel.this.frameShots = null;
/* 237 */           ExposePanel.this.frameThumbs = null;
/*     */         }
/* 239 */       };
/* 240 */       this.alphaThread.start();
/*     */     }
/*     */     catch (PropertyVetoException e) {
/* 243 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void updateFrameData()
/*     */   {
/* 249 */     this.parentBuffer = createCompatibleImage(getParent().getWidth(), getParent().getHeight());
/* 250 */     getParent().paint(this.parentBuffer.getGraphics());
/*     */     
/* 252 */     this.frames = this.desktop.getAllFrames();
/*     */     
/* 254 */     this.cellsX = 1;
/* 255 */     this.cellsY = 1;
/* 256 */     int cellCount = 1;
/* 257 */     while (cellCount < this.frames.length) {
/* 258 */       this.cellsX += 1;
/* 259 */       cellCount += this.cellsY;
/* 260 */       if (cellCount < this.frames.length) {
/* 261 */         this.cellsY += 1;
/* 262 */         cellCount += this.cellsX;
/*     */       }
/*     */     }
/*     */     
/* 266 */     this.cellWidth = (getWidth() / this.cellsX);
/* 267 */     this.cellHeight = ((getHeight() - 50) / this.cellsY);
/*     */     
/* 269 */     int maxWidth = this.cellWidth - 14;
/* 270 */     int maxHeight = this.cellHeight - 14;
/* 271 */     this.frameThumbs = new BufferedImage[this.frames.length];
/* 272 */     this.frameShots = new BufferedImage[this.frames.length];
/* 273 */     this.originalX = new int[this.frames.length];
/* 274 */     this.originalY = new int[this.frames.length];
/* 275 */     this.originalWidth = new int[this.frames.length];
/* 276 */     this.originalHeight = new int[this.frames.length];
/* 277 */     this.scaledX = new int[this.frames.length];
/* 278 */     this.scaledY = new int[this.frames.length];
/* 279 */     this.scaledWidth = new int[this.frames.length];
/* 280 */     this.scaledHeight = new int[this.frames.length];
/* 281 */     int xPos = 0;
/* 282 */     int yPos = 0;
/* 283 */     for (int i = 0; i < this.frames.length; i++)
/*     */     {
/* 285 */       this.originalX[i] = this.frames[i].getX();
/* 286 */       this.originalY[i] = this.frames[i].getY();
/* 287 */       this.originalWidth[i] = this.frames[i].getWidth();
/* 288 */       this.originalHeight[i] = this.frames[i].getHeight();
/*     */       
/* 290 */       double scaleFactor = maxWidth / this.originalWidth[i];
/* 291 */       double scaleY = maxHeight / this.originalHeight[i];
/* 292 */       if (scaleY < scaleFactor) {
/* 293 */         scaleFactor = scaleY;
/*     */       }
/* 295 */       this.scaledWidth[i] = ((int)(this.originalWidth[i] * scaleFactor));
/* 296 */       this.scaledHeight[i] = ((int)(this.originalHeight[i] * scaleFactor));
/* 297 */       this.scaledX[i] = (xPos * this.cellWidth + (this.cellWidth - this.scaledWidth[i]) / 2);
/* 298 */       this.scaledY[i] = (50 + yPos * this.cellHeight + (this.cellHeight - this.scaledHeight[i]) / 2);
/*     */       
/* 300 */       BufferedImage shot = createCompatibleImage(this.frames[i].getWidth(), this.frames[i].getHeight());
/* 301 */       this.frames[i].paint(shot.getGraphics());
/* 302 */       this.frameShots[i] = shot;
/*     */       
/* 304 */       progressiveScaleThumbnail(i);
/*     */       
/* 306 */       xPos++;
/* 307 */       if (xPos == this.cellsX) {
/* 308 */         yPos++;
/* 309 */         xPos = 0;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void progressiveScaleThumbnail(int frameIndex) {
/* 315 */     int halfWidth = this.originalWidth[frameIndex] / 2;
/* 316 */     int halfHeight = this.originalHeight[frameIndex] / 2;
/* 317 */     BufferedImage template = this.frameShots[frameIndex];
/* 318 */     BufferedImage scaled = template;
/*     */     
/* 320 */     while (halfWidth > this.scaledWidth[frameIndex]) {
/* 321 */       scaled = createCompatibleImage(halfWidth, halfHeight);
/* 322 */       Graphics2D g2db = scaled.createGraphics();
/* 323 */       g2db.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 324 */       g2db.drawImage(template, 0, 0, halfWidth, halfHeight, null);
/* 325 */       template = scaled;
/* 326 */       halfWidth /= 2;
/* 327 */       halfHeight /= 2;
/*     */     }
/*     */     
/* 330 */     if (halfWidth < this.scaledWidth[frameIndex]) {
/* 331 */       scaled = createCompatibleImage(this.scaledWidth[frameIndex], this.scaledHeight[frameIndex]);
/* 332 */       Graphics2D g2db = scaled.createGraphics();
/* 333 */       g2db.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 334 */       g2db.drawImage(template, 0, 0, this.scaledWidth[frameIndex], this.scaledHeight[frameIndex], null);
/*     */     }
/* 336 */     this.frameThumbs[frameIndex] = scaled;
/*     */   }
/*     */   
/*     */   protected BufferedImage scale(BufferedImage template, int targetWidth, int targetHeight) {
/* 340 */     int halfWidth = template.getWidth() / 2;
/* 341 */     int halfHeight = template.getHeight() / 2;
/* 342 */     BufferedImage scaled = template;
/*     */     
/* 344 */     while (halfWidth > targetWidth) {
/* 345 */       scaled = createCompatibleImage(halfWidth, halfHeight);
/* 346 */       Graphics2D g2db = scaled.createGraphics();
/* 347 */       g2db.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 348 */       g2db.drawImage(template, 0, 0, halfWidth, halfHeight, null);
/* 349 */       template = scaled;
/* 350 */       halfWidth /= 2;
/* 351 */       halfHeight /= 2;
/*     */     }
/*     */     
/* 354 */     if (halfWidth < targetWidth) {
/* 355 */       scaled = createCompatibleImage(targetWidth, targetHeight);
/* 356 */       Graphics2D g2db = scaled.createGraphics();
/* 357 */       g2db.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 358 */       g2db.drawImage(template, 0, 0, targetWidth, targetHeight, null);
/*     */     }
/* 360 */     return scaled;
/*     */   }
/*     */   
/*     */   protected void paintThumbnail(int frameIndex, Graphics2D g2d) {
/* 364 */     if (this.backgroundAlpha >= 1.0F)
/*     */     {
/* 366 */       g2d.drawImage(this.frameThumbs[frameIndex], this.scaledX[frameIndex], this.scaledY[frameIndex], null);
/*     */     }
/*     */     else {
/* 369 */       int interpolatedX = (int)(this.originalX[frameIndex] * (1.0F - this.backgroundAlpha) + 
/* 370 */         this.scaledX[frameIndex] * this.backgroundAlpha);
/* 371 */       int interpolatedY = (int)(this.originalY[frameIndex] * (1.0F - this.backgroundAlpha) + 
/* 372 */         this.scaledY[frameIndex] * this.backgroundAlpha);
/* 373 */       int interpolatedWidth = (int)(this.originalWidth[frameIndex] * (1.0F - this.backgroundAlpha) + 
/* 374 */         this.scaledWidth[frameIndex] * this.backgroundAlpha);
/* 375 */       int interpolatedHeight = (int)(this.originalHeight[frameIndex] * (1.0F - this.backgroundAlpha) + 
/* 376 */         this.scaledHeight[frameIndex] * this.backgroundAlpha);
/* 377 */       g2d.drawImage(this.frameShots[frameIndex], interpolatedX, interpolatedY, interpolatedWidth, interpolatedHeight, null);
/*     */     }
/*     */   }
/*     */   
/*     */   protected Image getThumbnail(BufferedImage original, int maxWidth, int maxHeight) {
/* 382 */     int originalWidth = original.getWidth();
/* 383 */     int originalHeight = original.getHeight();
/* 384 */     double scaleFactor = maxWidth / originalWidth;
/* 385 */     double scaleY = maxHeight / originalHeight;
/* 386 */     if (scaleY < scaleFactor) {
/* 387 */       scaleFactor = scaleY;
/*     */     }
/* 389 */     int scaledWidth = (int)(originalWidth * scaleFactor);
/* 390 */     int scaledHeight = (int)(originalHeight * scaleFactor);
/* 391 */     BufferedImage scaledImage = createCompatibleImage(scaledWidth, scaledHeight);
/* 392 */     Graphics2D g2ds = scaledImage.createGraphics();
/* 393 */     g2ds.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 394 */     g2ds.drawImage(original, 0, 0, scaledWidth, scaledHeight, null);
/* 395 */     g2ds.dispose();
/* 396 */     return scaledImage;
/*     */   }
/*     */   
/*     */   protected int getMouseOverFrameIndex() {
/* 400 */     if (this.mouseY < 50) {
/* 401 */       return -1;
/*     */     }
/* 403 */     int cX = this.mouseX / this.cellWidth;
/* 404 */     int cY = (this.mouseY - 50) / this.cellHeight;
/* 405 */     int index = cY * this.cellsX + cX;
/* 406 */     if (index < this.frames.length) {
/* 407 */       return index;
/*     */     }
/* 409 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 418 */     if (this.backgroundAlpha < 0.001F) {
/* 419 */       return;
/*     */     }
/* 421 */     int width = getWidth();
/* 422 */     int height = getHeight();
/* 423 */     Graphics2D g2d = (Graphics2D)g;
/* 424 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 425 */     g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
/*     */     
/* 427 */     if (isOpaque())
/*     */     {
/* 429 */       g2d.drawImage(this.parentBuffer, 0, 0, null);
/*     */     }
/* 431 */     if (this.backgroundAlpha < 1.0F) {
/* 432 */       AlphaComposite alphaComp = AlphaComposite.getInstance(3, this.backgroundAlpha);
/* 433 */       g2d.setComposite(alphaComp);
/*     */     }
/* 435 */     g2d.setColor(this.bgColor);
/* 436 */     g2d.fillRect(0, 70, width, height);
/*     */     
/* 438 */     g2d.drawImage(this.topBarBg, 0, 0, width, 70, null);
/* 439 */     g2d.setColor(new Color(240, 240, 240));
/* 440 */     g2d.setFont(g2d.getFont().deriveFont(16.0F));
/* 441 */     if (!RuntimeUtils.isRunningMacOsX()) {
/* 442 */       g2d.setFont(g2d.getFont().deriveFont(1));
/*     */     }
/* 444 */     g2d.drawString("Exposé", 50, 30);
/* 445 */     g2d.setColor(new Color(180, 180, 180));
/* 446 */     g2d.drawString("...select the frame you want to bring forward", 120, 30);
/*     */     
/* 448 */     g2d.drawImage(this.closeButton, width - 50, 15, null);
/*     */     
/* 450 */     if (this.backgroundAlpha >= 1.0F) {
/* 451 */       int selectedIndex = getMouseOverFrameIndex();
/* 452 */       for (int i = 0; i < this.frames.length; i++) {
/* 453 */         if (i == selectedIndex) {
/* 454 */           g2d.setColor(new Color(0, 160, 0, 220));
/* 455 */           g2d.fillRoundRect(this.scaledX[i] - 8, this.scaledY[i] - 8, 
/* 456 */             this.scaledWidth[i] + 15, this.scaledHeight[i] + 15, 20, 20);
/*     */         } else {
/* 458 */           g2d.setColor(new Color(30, 30, 30, 220));
/* 459 */           g2d.fillRoundRect(this.scaledX[i] - 4, this.scaledY[i] - 4, 
/* 460 */             this.scaledWidth[i] + 7, this.scaledHeight[i] + 7, 10, 10);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 465 */     g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
/*     */     
/* 467 */     for (int i = this.frames.length - 1; i >= 0; i--) {
/* 468 */       paintThumbnail(i, g2d);
/*     */     }
/* 470 */     if (this.backgroundAlpha >= 1.0F) {
/* 471 */       int selectedFrame = getMouseOverFrameIndex();
/* 472 */       if (selectedFrame >= 0)
/*     */       {
/* 474 */         String frameTitle = this.frames[selectedFrame].getTitle();
/* 475 */         if (frameTitle.length() > 65) {
/* 476 */           frameTitle = frameTitle.substring(0, 60) + "...";
/*     */         }
/* 478 */         g2d.setFont(g2d.getFont().deriveFont(14.0F));
/* 479 */         Rectangle2D stringBounds = g2d.getFontMetrics().getStringBounds(frameTitle, g2d);
/* 480 */         int stringX = this.scaledX[selectedFrame] + 
/* 481 */           this.scaledWidth[selectedFrame] / 2 - (int)(stringBounds.getWidth() / 2.0D);
/* 482 */         int stringHeight = (int)stringBounds.getHeight();
/* 483 */         int stringCenterY = this.scaledY[selectedFrame] + this.scaledHeight[selectedFrame] / 2 - stringHeight / 2;
/* 484 */         int border = stringHeight / 2;
/* 485 */         g2d.setColor(new Color(10, 10, 10, 200));
/* 486 */         int offset = 0;
/* 487 */         if (stringX < 10) {
/* 488 */           offset = -stringX + 10;
/*     */         } else {
/* 490 */           int stringRightX = stringX + (int)stringBounds.getWidth();
/* 491 */           if (stringRightX > width - 10) {
/* 492 */             offset = -(stringRightX - (width - 10));
/*     */           }
/*     */         }
/* 495 */         g2d.fillRoundRect(stringX - border + offset, stringCenterY - stringHeight / 2 - border, 
/* 496 */           (int)stringBounds.getWidth() + stringHeight, (int)stringBounds.getHeight() + stringHeight, 
/* 497 */           stringHeight, stringHeight);
/* 498 */         g2d.setColor(new Color(240, 240, 240));
/* 499 */         g2d.drawString(frameTitle, stringX + offset, stringCenterY + border - 2);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected BufferedImage createCompatibleImage(int width, int height) {
/* 505 */     if (this.gc == null) {
/* 506 */       this.gc = getGraphicsConfiguration();
/* 507 */       if (this.gc == null) {
/* 508 */         this.gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
/*     */       }
/*     */     }
/* 511 */     return this.gc.createCompatibleImage(width, height, 3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseClicked(MouseEvent evt)
/*     */   {
/* 518 */     this.mouseX = evt.getX();
/* 519 */     this.mouseY = evt.getY();
/* 520 */     if (evt.getButton() != 1) {
/* 521 */       return;
/*     */     }
/* 523 */     int frameIndex = getMouseOverFrameIndex();
/* 524 */     activateFrame(frameIndex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseEntered(MouseEvent evt)
/*     */   {
/* 531 */     this.mouseX = evt.getX();
/* 532 */     this.mouseY = evt.getY();
/* 533 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseExited(MouseEvent evt)
/*     */   {
/* 540 */     this.mouseX = -1;
/* 541 */     this.mouseY = -1;
/* 542 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent evt) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent evt) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent evt)
/*     */   {
/* 563 */     this.mouseX = evt.getX();
/* 564 */     this.mouseY = evt.getY();
/* 565 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseMoved(MouseEvent evt)
/*     */   {
/* 572 */     this.mouseX = evt.getX();
/* 573 */     this.mouseY = evt.getY();
/* 574 */     repaint();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/glasspane/ExposePanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */