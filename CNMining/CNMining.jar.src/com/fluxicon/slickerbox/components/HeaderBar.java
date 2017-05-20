/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.GraphicsUtilities;
/*     */ import com.fluxicon.slickerbox.util.RuntimeUtils;
/*     */ import java.awt.AlphaComposite;
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
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.lang.ref.SoftReference;
/*     */ import javax.swing.JComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HeaderBar
/*     */   extends JComponent
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*  73 */   protected Color COLOR_BG = new Color(40, 40, 40);
/*  74 */   protected Color COLOR_HILIGHT = new Color(55, 55, 55);
/*     */   protected int height;
/*     */   protected String title;
/*     */   protected ActionListener listener;
/*     */   protected int mouseX;
/*     */   protected int mouseY;
/*     */   protected SoftReference<BufferedImage> buffer;
/*     */   
/*  82 */   public HeaderBar(String title) { this.title = title;
/*  83 */     this.listener = null;
/*  84 */     setHeight(50);
/*  85 */     this.buffer = null;
/*     */   }
/*     */   
/*     */   public void setColors(Color colorBg, Color colorHilight) {
/*  89 */     this.COLOR_BG = colorBg;
/*  90 */     this.COLOR_HILIGHT = colorHilight;
/*  91 */     this.buffer = null;
/*     */   }
/*     */   
/*     */   public void setHeight(int aHeight) {
/*  95 */     this.height = aHeight;
/*  96 */     setMinimumSize(new Dimension(100, this.height));
/*  97 */     setMaximumSize(new Dimension(3000, this.height));
/*  98 */     setPreferredSize(new Dimension(500, this.height));
/*     */   }
/*     */   
/*     */   public void setCloseActionListener(ActionListener listener) {
/* 102 */     this.listener = listener;
/* 103 */     addMouseListener(this);
/* 104 */     addMouseMotionListener(this);
/* 105 */     if (this.height < 40) {
/* 106 */       setHeight(40);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 116 */     int width = getWidth();
/* 117 */     int height = getHeight();
/* 118 */     Graphics2D g2d = (Graphics2D)g.create();
/* 119 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 120 */     if ((this.buffer == null) || (this.buffer.get() == null) || 
/* 121 */       (width != ((BufferedImage)this.buffer.get()).getWidth()) || (height != ((BufferedImage)this.buffer.get()).getHeight()))
/*     */     {
/* 123 */       createBuffer();
/*     */     }
/* 125 */     g2d.drawImage((Image)this.buffer.get(), 0, 0, this);
/* 126 */     drawCloseButton(width, g2d);
/* 127 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   protected void createBuffer()
/*     */   {
/* 132 */     int width = getWidth();
/* 133 */     int height = getHeight();
/* 134 */     this.buffer = new SoftReference(GraphicsUtilities.createCompatibleImage(width, height));
/* 135 */     Graphics2D g2dBuf = (Graphics2D)((BufferedImage)this.buffer.get()).getGraphics();
/* 136 */     g2dBuf.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 138 */     int hilightY = height / 5;
/* 139 */     GradientPaint gradient = new GradientPaint(20.0F, 0.0F, this.COLOR_BG, 20.0F, hilightY, this.COLOR_HILIGHT, false);
/* 140 */     g2dBuf.setPaint(gradient);
/* 141 */     g2dBuf.fillRect(0, 0, width, hilightY);
/* 142 */     gradient = new GradientPaint(20.0F, hilightY, this.COLOR_HILIGHT, 20.0F, 30.0F, this.COLOR_BG, false);
/* 143 */     g2dBuf.setPaint(gradient);
/* 144 */     g2dBuf.fillRect(0, hilightY, width, height);
/*     */     
/* 146 */     int maxWidth = width - 30;
/* 147 */     if (this.listener != null) {
/* 148 */       maxWidth -= 100;
/*     */     }
/* 150 */     BufferedImage titleBuffer = new BufferedImage(width, height, 2);
/* 151 */     Graphics2D g2dt = (Graphics2D)titleBuffer.getGraphics();
/* 152 */     g2dt.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 153 */     int stringBaseY = height * 2 / 3 + 2;
/* 154 */     g2dt.setFont(g2dt.getFont().deriveFont(16.0F));
/* 155 */     if (!RuntimeUtils.isRunningMacOsX()) {
/* 156 */       g2dt.setFont(g2dt.getFont().deriveFont(1));
/*     */     }
/* 158 */     g2dt.setColor(new Color(30, 30, 30));
/* 159 */     g2dt.drawString(this.title, 22, stringBaseY);
/* 160 */     g2dt.setColor(new Color(200, 200, 200));
/* 161 */     g2dt.drawString(this.title, 20, stringBaseY - 2);
/* 162 */     int stringWidth = (int)g2dt.getFontMetrics().getStringBounds(this.title, g2dt).getWidth();
/* 163 */     if (stringWidth > maxWidth) {
/* 164 */       GradientPaint maskGradient = new GradientPaint(maxWidth - 40, 0.0F, new Color(0, 0, 0, 0), maxWidth, 0.0F, new Color(255, 255, 255, 255), false);
/* 165 */       g2dt.setPaint(maskGradient);
/* 166 */       g2dt.setComposite(AlphaComposite.DstOut);
/* 167 */       g2dt.fillRect(0, 0, width, height);
/*     */     }
/* 169 */     g2dt.dispose();
/* 170 */     g2dBuf.drawImage(titleBuffer, 0, 0, null);
/* 171 */     g2dBuf.dispose();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void drawCloseButton(int width, Graphics2D g2d)
/*     */   {
/* 177 */     if (this.listener == null) {
/* 178 */       return;
/*     */     }
/* 180 */     int rotX = width - 67;
/* 181 */     int rotY = this.height / 2;
/*     */     
/* 183 */     g2d.setColor(new Color(0, 0, 0, 150));
/* 184 */     g2d.fillRoundRect(width - 80, rotY - 13, 68, 25, 25, 25);
/* 185 */     if (mouseOverClose()) {
/* 186 */       g2d.setColor(new Color(210, 210, 210));
/*     */     } else {
/* 188 */       g2d.setColor(new Color(160, 160, 160, 200));
/*     */     }
/* 190 */     g2d.fillOval(rotX - 9, rotY - 9, 17, 17);
/* 191 */     g2d.setFont(g2d.getFont().deriveFont(12.0F));
/* 192 */     g2d.drawString("close", width - 52, rotY + 5);
/*     */     
/* 194 */     g2d.setColor(Color.BLACK);
/* 195 */     Graphics2D g2dc = (Graphics2D)g2d.create();
/* 196 */     AffineTransform mod = g2dc.getTransform();
/* 197 */     mod.rotate(Math.toRadians(45.0D), rotX, rotY);
/* 198 */     g2dc.setTransform(mod);
/* 199 */     g2dc.fillRect(rotX - 6, rotY - 2, 12, 4);
/* 200 */     g2dc.dispose();
/* 201 */     g2dc = (Graphics2D)g2d.create();
/* 202 */     mod = g2dc.getTransform();
/* 203 */     mod.rotate(Math.toRadians(135.0D), rotX, rotY);
/* 204 */     g2dc.setTransform(mod);
/* 205 */     g2dc.fillRect(rotX - 6, rotY - 2, 12, 4);
/* 206 */     g2dc.dispose();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseClicked(MouseEvent evt)
/*     */   {
/* 213 */     if ((this.listener == null) || (evt.getButton() != 1))
/* 214 */       return;
/* 215 */     if (mouseOverClose())
/*     */     {
/* 217 */       this.listener.actionPerformed(new ActionEvent(this, 1001, "CLOSE"));
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean mouseOverClose() {
/* 222 */     if ((this.mouseX >= getWidth() - 80) && 
/* 223 */       (this.mouseX <= getWidth() - 5) && 
/* 224 */       (this.mouseY >= 12) && (this.mouseY <= getHeight() - 12))
/*     */     {
/* 226 */       return true;
/*     */     }
/* 228 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseEntered(MouseEvent evt) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseExited(MouseEvent evt)
/*     */   {
/* 243 */     this.mouseX = -1;
/* 244 */     this.mouseY = -1;
/* 245 */     repaint();
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
/* 266 */     this.mouseX = evt.getX();
/* 267 */     this.mouseY = evt.getY();
/* 268 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseMoved(MouseEvent evt)
/*     */   {
/* 275 */     this.mouseX = evt.getX();
/* 276 */     this.mouseY = evt.getY();
/* 277 */     repaint();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/HeaderBar.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */