/*     */ package com.fluxicon.slickerbox.ui;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.AdjustmentEvent;
/*     */ import java.awt.event.AdjustmentListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.Rectangle2D.Float;
/*     */ import java.awt.geom.RoundRectangle2D.Float;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.BoundedRangeModel;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.plaf.ScrollBarUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlickerScrollBarUI
/*     */   extends ScrollBarUI
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*  69 */   protected Color colorBg = Color.BLACK;
/*  70 */   protected Color colorPassive = new Color(30, 30, 30);
/*  71 */   protected Color colorActive = new Color(80, 80, 80);
/*     */   
/*  73 */   protected Color colorFg = this.colorPassive;
/*  74 */   protected int mouseOffset = -1;
/*     */   
/*     */   protected JScrollBar scrollBar;
/*     */   
/*  78 */   protected float border = 5.0F;
/*  79 */   protected float width = 13.0F;
/*     */   
/*     */   public SlickerScrollBarUI(JScrollBar scrollBar, Color colorBg, Color colorActive, Color colorPassive, float border, float width) {
/*  82 */     this.scrollBar = scrollBar;
/*  83 */     this.colorBg = colorBg;
/*  84 */     this.colorActive = colorActive;
/*  85 */     this.colorPassive = colorPassive;
/*  86 */     this.border = border;
/*  87 */     this.width = width;
/*  88 */     this.colorFg = colorPassive;
/*     */   }
/*     */   
/*     */   public SlickerScrollBarUI(JScrollBar scrollBar) {
/*  92 */     this.scrollBar = scrollBar;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMaximumSize(JComponent c)
/*     */   {
/* 100 */     if (this.scrollBar.getOrientation() == 0) {
/* 101 */       return new Dimension(10000, (int)(this.width + this.border + this.border));
/*     */     }
/* 103 */     return new Dimension((int)(this.width + this.border + this.border), 10000);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMinimumSize(JComponent c)
/*     */   {
/* 112 */     if (this.scrollBar.getOrientation() == 0) {
/* 113 */       return new Dimension(50, (int)(this.width + this.border + this.border));
/*     */     }
/* 115 */     return new Dimension((int)(this.width + this.border + this.border), 50);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(JComponent c)
/*     */   {
/* 124 */     if (this.scrollBar.getOrientation() == 0) {
/* 125 */       return new Dimension(300, (int)(this.width + this.border + this.border));
/*     */     }
/* 127 */     return new Dimension((int)(this.width + this.border + this.border), 300);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void installUI(JComponent c)
/*     */   {
/* 136 */     c.addMouseListener(this);
/* 137 */     c.addMouseMotionListener(this);
/* 138 */     c.setOpaque(true);
/* 139 */     this.scrollBar.addAdjustmentListener(new AdjustmentListener() {
/*     */       public void adjustmentValueChanged(AdjustmentEvent e) {
/* 141 */         SlickerScrollBarUI.this.scrollBar.repaint();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paint(Graphics g, JComponent c)
/*     */   {
/* 151 */     float canvasWidth = c.getWidth();
/* 152 */     float canvasHeight = c.getHeight();
/* 153 */     Graphics2D g2d = (Graphics2D)g;
/* 154 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 155 */     if (c.isOpaque())
/*     */     {
/* 157 */       g2d.setColor(this.colorBg);
/* 158 */       g2d.fill(new Rectangle2D.Float(0.0F, 0.0F, canvasWidth, canvasHeight));
/*     */     }
/* 160 */     g2d.setColor(this.colorFg);
/* 161 */     BasicStroke stroke = new BasicStroke(2.0F, 1, 1);
/* 162 */     g2d.setStroke(stroke);
/* 163 */     if (this.scrollBar.getOrientation() == 0) {
/* 164 */       float y = (canvasHeight - this.width) / 2.0F;
/* 165 */       float trackWidth = getTrackSize();
/* 166 */       g2d.draw(new RoundRectangle2D.Float(
/* 167 */         (canvasWidth - trackWidth) / 2.0F, y, trackWidth, this.width, this.width, this.width));
/* 168 */       g2d.fill(new RoundRectangle2D.Float(getThumbPosition(), y, getThumbSize(true), this.width, this.width, this.width));
/*     */     } else {
/* 170 */       float x = (canvasWidth - this.width) / 2.0F;
/* 171 */       float trackHeight = getTrackSize();
/* 172 */       g2d.draw(new RoundRectangle2D.Float(
/* 173 */         x, (canvasHeight - trackHeight) / 2.0F, this.width, trackHeight, this.width, this.width));
/* 174 */       g2d.fill(new RoundRectangle2D.Float(x, getThumbPosition(), this.width, getThumbSize(true), this.width, this.width));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseClicked(MouseEvent e) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseEntered(MouseEvent e)
/*     */   {
/* 190 */     this.colorFg = this.colorActive;
/* 191 */     this.scrollBar.repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseExited(MouseEvent e)
/*     */   {
/* 198 */     if (this.mouseOffset < 0) {
/* 199 */       this.colorFg = this.colorPassive;
/* 200 */       this.scrollBar.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 208 */     if (e.getButton() != 1)
/*     */       return;
/*     */     int significantCoordinate;
/*     */     int significantCoordinate;
/* 212 */     if (this.scrollBar.getOrientation() == 0) {
/* 213 */       significantCoordinate = e.getX();
/*     */     } else {
/* 215 */       significantCoordinate = e.getY();
/*     */     }
/* 217 */     int minButton = (int)getThumbPosition();
/* 218 */     int maxButton = minButton + (int)getThumbSize(true);
/* 219 */     if ((significantCoordinate >= minButton) && (significantCoordinate <= maxButton))
/*     */     {
/* 221 */       this.mouseOffset = (significantCoordinate - minButton);
/*     */     }
/*     */     else {
/* 224 */       this.mouseOffset = -1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/* 232 */     if (e.getButton() != 1) {
/* 233 */       return;
/*     */     }
/*     */     
/* 236 */     this.mouseOffset = -1;
/* 237 */     if (!this.scrollBar.contains(e.getPoint())) {
/* 238 */       this.colorFg = this.colorPassive;
/* 239 */       this.scrollBar.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/* 249 */     if (this.mouseOffset >= 0) { float significantCoordinate;
/*     */       float significantCoordinate;
/* 251 */       if (this.scrollBar.getOrientation() == 0) {
/* 252 */         significantCoordinate = e.getX();
/*     */       } else {
/* 254 */         significantCoordinate = e.getY();
/*     */       }
/*     */       
/* 257 */       significantCoordinate -= this.mouseOffset;
/* 258 */       significantCoordinate -= this.border;
/* 259 */       float percentage = significantCoordinate / (getTrackSize() - getThumbSize(true));
/* 260 */       BoundedRangeModel model = this.scrollBar.getModel();
/* 261 */       float range = model.getMaximum() - model.getMinimum() - model.getExtent();
/* 262 */       int value = model.getMinimum() + (int)(percentage * range);
/* 263 */       this.scrollBar.setValue(value);
/* 264 */       this.scrollBar.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseMoved(MouseEvent e)
/*     */   {
/* 273 */     if (this.mouseOffset >= 0) { float significantCoordinate;
/*     */       float significantCoordinate;
/* 275 */       if (this.scrollBar.getOrientation() == 0) {
/* 276 */         significantCoordinate = e.getX();
/*     */       } else {
/* 278 */         significantCoordinate = e.getY();
/*     */       }
/*     */       
/* 281 */       significantCoordinate -= this.mouseOffset;
/* 282 */       significantCoordinate -= this.border;
/* 283 */       float percentage = (int)(significantCoordinate / (getTrackSize() - getThumbSize(true)));
/* 284 */       BoundedRangeModel model = this.scrollBar.getModel();
/* 285 */       float range = model.getMaximum() - model.getMinimum() - model.getExtent();
/* 286 */       int value = model.getMinimum() + (int)(percentage * range);
/* 287 */       this.scrollBar.setValue(value);
/* 288 */       this.scrollBar.repaint();
/* 289 */       System.out.println("pct: " + percentage);
/*     */     }
/*     */   }
/*     */   
/*     */   protected float getTrackSize() {
/* 294 */     if (this.scrollBar.getOrientation() == 0) {
/* 295 */       return this.scrollBar.getWidth() - this.border - this.border;
/*     */     }
/* 297 */     return this.scrollBar.getHeight() - this.border - this.border;
/*     */   }
/*     */   
/*     */   protected float getThumbSize(boolean ensureMinimumSize)
/*     */   {
/* 302 */     BoundedRangeModel model = this.scrollBar.getModel();
/* 303 */     float percentage = model.getExtent() / (model.getMaximum() - model.getMinimum());
/* 304 */     float thumbSize = getTrackSize() * percentage;
/* 305 */     float doubleWidth = this.width + this.width;
/* 306 */     if ((ensureMinimumSize) && (thumbSize < doubleWidth)) {
/* 307 */       thumbSize = doubleWidth;
/*     */     }
/* 309 */     return thumbSize;
/*     */   }
/*     */   
/*     */   protected float getThumbPosition() {
/* 313 */     BoundedRangeModel model = this.scrollBar.getModel();
/* 314 */     float percentage = (model.getValue() - model.getMinimum()) / (model.getMaximum() - model.getMinimum());
/* 315 */     float trackSize = getTrackSize();
/* 316 */     float realThumbSize = getThumbSize(false);
/* 317 */     float ensuredThumbSize = getThumbSize(true);
/* 318 */     if (ensuredThumbSize > realThumbSize) {
/* 319 */       trackSize -= ensuredThumbSize - realThumbSize;
/*     */     }
/* 321 */     return this.border + trackSize * percentage;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/ui/SlickerScrollBarUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */