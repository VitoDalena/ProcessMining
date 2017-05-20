/*     */ package com.fluxicon.slickerbox.ui;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import javax.swing.plaf.basic.BasicSliderUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlickerSliderUI
/*     */   extends BasicSliderUI
/*     */ {
/*  65 */   protected Color COLOR_TRACK_BG_DARK = new Color(90, 90, 90);
/*  66 */   protected Color COLOR_TRACK_BG_BRIGHT = new Color(110, 110, 110);
/*  67 */   protected Color COLOR_THUMB_BG_DARK = new Color(0, 0, 0);
/*  68 */   protected Color COLOR_THUMB_BG_BRIGHT = new Color(160, 160, 160);
/*  69 */   protected Color COLOR_THUMB_ACTIVE_BG_DARK = new Color(0, 0, 0);
/*  70 */   protected Color COLOR_THUMB_ACTIVE_BG_BRIGHT = new Color(120, 120, 120);
/*     */   
/*  72 */   protected int TRACK_WIDTH = 8;
/*  73 */   protected int THUMB_WIDTH = 16;
/*     */   
/*     */ 
/*     */ 
/*     */   public SlickerSliderUI(JSlider slider)
/*     */   {
/*  79 */     super(slider);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public SlickerSliderUI(JSlider slider, Color trackBgDark, Color trackBgBright, Color thumbBgDark, Color thumbBgBright, Color thumbActiveBgDark, Color thumbActiveBgBright, int trackWidth, int thumbWidth)
/*     */   {
/*  86 */     super(slider);
/*  87 */     this.COLOR_TRACK_BG_DARK = trackBgDark;
/*  88 */     this.COLOR_TRACK_BG_BRIGHT = trackBgBright;
/*  89 */     this.COLOR_THUMB_BG_DARK = thumbBgDark;
/*  90 */     this.COLOR_THUMB_BG_BRIGHT = thumbBgBright;
/*  91 */     this.COLOR_THUMB_ACTIVE_BG_DARK = thumbActiveBgDark;
/*  92 */     this.COLOR_THUMB_ACTIVE_BG_BRIGHT = thumbActiveBgBright;
/*  93 */     this.TRACK_WIDTH = trackWidth;
/*  94 */     this.THUMB_WIDTH = thumbWidth;
/*     */   }
/*     */   
/*     */   protected double getSliderPercentage() {
/*  98 */     double value = this.slider.getValue() - this.slider.getMinimum();
/*  99 */     double max = this.slider.getMaximum() - this.slider.getMinimum();
/* 100 */     return value / max;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void installDefaults(JSlider slider)
/*     */   {
/* 108 */     super.installDefaults(slider);
/* 109 */     slider.setOpaque(false);
/* 110 */     slider.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
/* 111 */     final JSlider aSlider = slider;
/* 112 */     slider.addChangeListener(new ChangeListener() {
/*     */       public void stateChanged(ChangeEvent arg0) {
/* 114 */         aSlider.repaint();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void calculateThumbLocation()
/*     */   {
/* 124 */     Insets insets = this.slider.getInsets();
/* 125 */     int width = this.slider.getWidth() - insets.left - insets.right;
/* 126 */     int height = this.slider.getHeight() - insets.top - insets.bottom;
/* 127 */     double percentage = getSliderPercentage();
/* 128 */     int thumbX = 0;int thumbY = 0;
/* 129 */     if (this.slider.getOrientation() == 1) {
/* 130 */       percentage = 1.0D - percentage;
/* 131 */       thumbX = insets.left + (width - this.THUMB_WIDTH) / 2;
/* 132 */       thumbY = insets.top + 1 + (int)((height - 2 - this.THUMB_WIDTH) * percentage);
/*     */     } else {
/* 134 */       thumbY = insets.top + (height - this.THUMB_WIDTH) / 2;
/* 135 */       thumbX = insets.left + 1 + (int)((width - 2 - this.THUMB_WIDTH) * percentage);
/*     */     }
/* 137 */     this.thumbRect = new Rectangle(thumbX, thumbY, this.THUMB_WIDTH, this.THUMB_WIDTH);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void calculateThumbSize()
/*     */   {
/* 145 */     this.thumbRect = new Rectangle(this.thumbRect.x, this.thumbRect.y, this.THUMB_WIDTH, this.THUMB_WIDTH);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void calculateTrackRect()
/*     */   {
/* 153 */     Insets insets = this.slider.getInsets();
/* 154 */     int width = this.slider.getWidth() - insets.left - insets.right;
/* 155 */     int height = this.slider.getHeight() - insets.top - insets.bottom;
/* 156 */     int trackX = 0;int trackY = 0;
/* 157 */     if (this.slider.getOrientation() == 1) {
/* 158 */       trackX = insets.left + (width - this.TRACK_WIDTH) / 2;
/* 159 */       trackY = 1 + insets.top;
/* 160 */       this.trackRect = new Rectangle(trackX, trackY, this.TRACK_WIDTH, height - 2);
/*     */     } else {
/* 162 */       trackX = 1 + insets.left;
/* 163 */       trackY = insets.top + (height - this.TRACK_WIDTH) / 2;
/* 164 */       this.trackRect = new Rectangle(trackX, trackY, width - 2, this.TRACK_WIDTH);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void paintTrack(Graphics2D g2d, JSlider slider) {
/* 169 */     Insets insets = slider.getInsets();
/* 170 */     int width = slider.getWidth() - insets.left - insets.right;
/* 171 */     int height = slider.getHeight() - insets.top - insets.bottom;
/* 172 */     if (slider.getOrientation() == 1) {
/* 173 */       int trackX = insets.left + (width - this.TRACK_WIDTH) / 2;
/* 174 */       int trackY = 1 + insets.top;
/* 175 */       GradientPaint gp = new GradientPaint(trackX, 0.0F, this.COLOR_TRACK_BG_DARK, 
/* 176 */         trackX + this.TRACK_WIDTH, 0.0F, this.COLOR_TRACK_BG_BRIGHT, false);
/* 177 */       g2d.setPaint(gp);
/* 178 */       g2d.fillRoundRect(trackX, trackY, this.TRACK_WIDTH - 1, height - 3, this.TRACK_WIDTH, this.TRACK_WIDTH);
/* 179 */       gp = new GradientPaint(trackX, 0.0F, new Color(10, 10, 10, 90), 
/* 180 */         trackX + this.TRACK_WIDTH, 0.0F, new Color(0, 0, 0, 0), false);
/* 181 */       g2d.setPaint(gp);
/* 182 */       g2d.drawRoundRect(trackX, trackY, this.TRACK_WIDTH, height - 2, this.TRACK_WIDTH, this.TRACK_WIDTH);
/* 183 */       gp = new GradientPaint(trackX, 0.0F, new Color(10, 10, 10, 60), 
/* 184 */         trackX + this.TRACK_WIDTH, 0.0F, new Color(0, 0, 0, 0), false);
/* 185 */       g2d.setPaint(gp);
/* 186 */       g2d.drawRoundRect(trackX + 1, trackY + 1, this.TRACK_WIDTH - 2, height - 4, this.TRACK_WIDTH - 2, this.TRACK_WIDTH - 2);
/* 187 */       gp = new GradientPaint(trackX, 0.0F, new Color(0, 0, 0, 0), 
/* 188 */         trackX + this.TRACK_WIDTH, 0.0F, new Color(220, 220, 220, 80), false);
/* 189 */       g2d.setPaint(gp);
/* 190 */       g2d.drawRoundRect(trackX, trackY, this.TRACK_WIDTH, height - 2, this.TRACK_WIDTH, this.TRACK_WIDTH);
/* 191 */       gp = new GradientPaint(trackX, 0.0F, new Color(0, 0, 0, 0), 
/* 192 */         trackX + this.TRACK_WIDTH, 0.0F, new Color(220, 220, 220, 50), false);
/* 193 */       g2d.setPaint(gp);
/* 194 */       g2d.drawRoundRect(trackX + 1, trackY + 1, this.TRACK_WIDTH - 2, height - 4, this.TRACK_WIDTH - 2, this.TRACK_WIDTH - 2);
/*     */     } else {
/* 196 */       int trackX = 1 + insets.left;
/* 197 */       int trackY = insets.top + (height - this.TRACK_WIDTH) / 2;
/* 198 */       GradientPaint gp = new GradientPaint(0.0F, trackY, this.COLOR_TRACK_BG_DARK, 
/* 199 */         0.0F, trackY + this.TRACK_WIDTH, this.COLOR_TRACK_BG_BRIGHT, false);
/* 200 */       g2d.setPaint(gp);
/* 201 */       g2d.fillRoundRect(trackX, trackY, width - 3, this.TRACK_WIDTH - 1, this.TRACK_WIDTH, this.TRACK_WIDTH);
/* 202 */       gp = new GradientPaint(0.0F, trackY, new Color(10, 10, 10, 90), 
/* 203 */         0.0F, trackY + this.TRACK_WIDTH, new Color(0, 0, 0, 0), false);
/* 204 */       g2d.setPaint(gp);
/* 205 */       g2d.drawRoundRect(trackX, trackY, width - 2, this.TRACK_WIDTH, this.TRACK_WIDTH, this.TRACK_WIDTH);
/* 206 */       gp = new GradientPaint(0.0F, trackY, new Color(10, 10, 10, 60), 
/* 207 */         0.0F, trackY + this.TRACK_WIDTH, new Color(0, 0, 0, 0), false);
/* 208 */       g2d.setPaint(gp);
/* 209 */       g2d.drawRoundRect(trackX + 1, trackY + 1, width - 4, this.TRACK_WIDTH - 2, this.TRACK_WIDTH - 2, this.TRACK_WIDTH - 2);
/* 210 */       gp = new GradientPaint(0.0F, trackY, new Color(0, 0, 0, 0), 
/* 211 */         0.0F, trackY + this.TRACK_WIDTH, new Color(220, 220, 220, 80), false);
/* 212 */       g2d.setPaint(gp);
/* 213 */       g2d.drawRoundRect(trackX, trackY, width - 2, this.TRACK_WIDTH, this.TRACK_WIDTH, this.TRACK_WIDTH);
/* 214 */       gp = new GradientPaint(0.0F, trackY, new Color(0, 0, 0, 0), 
/* 215 */         0.0F, trackY + this.TRACK_WIDTH, new Color(220, 220, 220, 50), false);
/* 216 */       g2d.setPaint(gp);
/* 217 */       g2d.drawRoundRect(trackX + 1, trackY + 1, width - 4, this.TRACK_WIDTH - 2, this.TRACK_WIDTH - 2, this.TRACK_WIDTH - 2);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void paintThumb(Graphics2D g2d, JSlider slider) {
/* 222 */     if (!slider.isEnabled()) {
/* 223 */       g2d = (Graphics2D)g2d.create();
/* 224 */       g2d.setComposite(AlphaComposite.getInstance(3, 0.5F));
/*     */     }
/* 226 */     Insets insets = slider.getInsets();
/* 227 */     int width = slider.getWidth() - insets.left - insets.right;
/* 228 */     int height = slider.getHeight() - insets.top - insets.bottom;
/* 229 */     double percentage = getSliderPercentage();
/* 230 */     int thumbX = 0;int thumbY = 0;
/* 231 */     if (slider.getOrientation() == 1) {
/* 232 */       percentage = 1.0D - percentage;
/* 233 */       thumbX = insets.left + (width - this.THUMB_WIDTH) / 2;
/* 234 */       thumbY = insets.top + 1 + (int)((height - 2 - this.THUMB_WIDTH) * percentage);
/*     */     } else {
/* 236 */       thumbY = insets.top + (height - this.THUMB_WIDTH) / 2;
/* 237 */       thumbX = insets.left + 1 + (int)((width - 2 - this.THUMB_WIDTH) * percentage); }
/*     */     GradientPaint gp;
/*     */     GradientPaint gp;
/* 240 */     if (isDragging()) {
/* 241 */       gp = new GradientPaint(thumbX, thumbY, this.COLOR_THUMB_ACTIVE_BG_BRIGHT, 
/* 242 */         thumbX + this.THUMB_WIDTH, thumbY + this.THUMB_WIDTH, this.COLOR_THUMB_ACTIVE_BG_DARK, false);
/*     */     } else {
/* 244 */       gp = new GradientPaint(thumbX, thumbY, this.COLOR_THUMB_BG_BRIGHT, 
/* 245 */         thumbX + this.THUMB_WIDTH, thumbY + this.THUMB_WIDTH, this.COLOR_THUMB_BG_DARK, false);
/*     */     }
/* 247 */     g2d.setPaint(gp);
/* 248 */     g2d.fillOval(thumbX, thumbY, this.THUMB_WIDTH, this.THUMB_WIDTH);
/* 249 */     g2d.setColor(new Color(10, 10, 10, 160));
/* 250 */     g2d.drawOval(thumbX, thumbY, this.THUMB_WIDTH, this.THUMB_WIDTH);
/* 251 */     g2d.setColor(new Color(10, 10, 10, 80));
/* 252 */     g2d.drawOval(thumbX + 1, thumbY + 1, this.THUMB_WIDTH - 2, this.THUMB_WIDTH - 2);
/* 253 */     g2d.setColor(new Color(10, 10, 10, 40));
/* 254 */     g2d.drawOval(thumbX + 2, thumbY + 2, this.THUMB_WIDTH - 4, this.THUMB_WIDTH - 4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paintFocus(Graphics g) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paintLabels(Graphics g) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paintThumb(Graphics g)
/*     */   {
/* 278 */     Graphics2D g2d = (Graphics2D)g;
/* 279 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 280 */     paintThumb(g2d, this.slider);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paintTicks(Graphics g) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paintTrack(Graphics g)
/*     */   {
/* 296 */     Graphics2D g2d = (Graphics2D)g;
/* 297 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 298 */     paintTrack(g2d, this.slider);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/ui/SlickerSliderUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */