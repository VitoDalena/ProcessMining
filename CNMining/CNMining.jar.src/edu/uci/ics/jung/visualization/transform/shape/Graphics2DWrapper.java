/*     */ package edu.uci.ics.jung.visualization.transform.shape;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.Image;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.RenderingHints.Key;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Graphics2DWrapper
/*     */ {
/*     */   protected Graphics2D delegate;
/*     */   
/*     */   public Graphics2DWrapper()
/*     */   {
/*  57 */     this(null);
/*     */   }
/*     */   
/*  60 */   public Graphics2DWrapper(Graphics2D delegate) { this.delegate = delegate; }
/*     */   
/*     */   public void setDelegate(Graphics2D delegate)
/*     */   {
/*  64 */     this.delegate = delegate;
/*     */   }
/*     */   
/*     */   public Graphics2D getDelegate() {
/*  68 */     return this.delegate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addRenderingHints(Map hints)
/*     */   {
/*  75 */     this.delegate.addRenderingHints(hints);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clearRect(int x, int y, int width, int height)
/*     */   {
/*  82 */     this.delegate.clearRect(x, y, width, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clip(Shape s)
/*     */   {
/*  89 */     this.delegate.clip(s);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clipRect(int x, int y, int width, int height)
/*     */   {
/*  96 */     this.delegate.clipRect(x, y, width, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void copyArea(int x, int y, int width, int height, int dx, int dy)
/*     */   {
/* 103 */     this.delegate.copyArea(x, y, width, height, dx, dy);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Graphics create()
/*     */   {
/* 110 */     return this.delegate.create();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Graphics create(int x, int y, int width, int height)
/*     */   {
/* 117 */     return this.delegate.create(x, y, width, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void dispose()
/*     */   {
/* 124 */     this.delegate.dispose();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void draw(Shape s)
/*     */   {
/* 131 */     this.delegate.draw(s);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void draw3DRect(int x, int y, int width, int height, boolean raised)
/*     */   {
/* 138 */     this.delegate.draw3DRect(x, y, width, height, raised);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
/*     */   {
/* 145 */     this.delegate.drawArc(x, y, width, height, startAngle, arcAngle);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawBytes(byte[] data, int offset, int length, int x, int y)
/*     */   {
/* 152 */     this.delegate.drawBytes(data, offset, length, x, y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawChars(char[] data, int offset, int length, int x, int y)
/*     */   {
/* 159 */     this.delegate.drawChars(data, offset, length, x, y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawGlyphVector(GlyphVector g, float x, float y)
/*     */   {
/* 166 */     this.delegate.drawGlyphVector(g, x, y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y)
/*     */   {
/* 173 */     this.delegate.drawImage(img, op, x, y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs)
/*     */   {
/* 180 */     return this.delegate.drawImage(img, xform, obs);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer)
/*     */   {
/* 187 */     return this.delegate.drawImage(img, x, y, bgcolor, observer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean drawImage(Image img, int x, int y, ImageObserver observer)
/*     */   {
/* 194 */     return this.delegate.drawImage(img, x, y, observer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer)
/*     */   {
/* 201 */     return this.delegate.drawImage(img, x, y, width, height, bgcolor, observer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)
/*     */   {
/* 208 */     return this.delegate.drawImage(img, x, y, width, height, observer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer)
/*     */   {
/* 215 */     return this.delegate.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
/*     */   {
/* 222 */     return this.delegate.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawLine(int x1, int y1, int x2, int y2)
/*     */   {
/* 229 */     this.delegate.drawLine(x1, y1, x2, y2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawOval(int x, int y, int width, int height)
/*     */   {
/* 236 */     this.delegate.drawOval(x, y, width, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints)
/*     */   {
/* 243 */     this.delegate.drawPolygon(xPoints, yPoints, nPoints);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawPolygon(Polygon p)
/*     */   {
/* 250 */     this.delegate.drawPolygon(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints)
/*     */   {
/* 257 */     this.delegate.drawPolyline(xPoints, yPoints, nPoints);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawRect(int x, int y, int width, int height)
/*     */   {
/* 264 */     this.delegate.drawRect(x, y, width, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawRenderableImage(RenderableImage img, AffineTransform xform)
/*     */   {
/* 271 */     this.delegate.drawRenderableImage(img, xform);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawRenderedImage(RenderedImage img, AffineTransform xform)
/*     */   {
/* 278 */     this.delegate.drawRenderedImage(img, xform);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight)
/*     */   {
/* 285 */     this.delegate.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawString(AttributedCharacterIterator iterator, float x, float y)
/*     */   {
/* 292 */     this.delegate.drawString(iterator, x, y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawString(AttributedCharacterIterator iterator, int x, int y)
/*     */   {
/* 299 */     this.delegate.drawString(iterator, x, y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawString(String s, float x, float y)
/*     */   {
/* 306 */     this.delegate.drawString(s, x, y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void drawString(String str, int x, int y)
/*     */   {
/* 313 */     this.delegate.drawString(str, x, y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 320 */     return this.delegate.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void fill(Shape s)
/*     */   {
/* 327 */     this.delegate.fill(s);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void fill3DRect(int x, int y, int width, int height, boolean raised)
/*     */   {
/* 334 */     this.delegate.fill3DRect(x, y, width, height, raised);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle)
/*     */   {
/* 341 */     this.delegate.fillArc(x, y, width, height, startAngle, arcAngle);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void fillOval(int x, int y, int width, int height)
/*     */   {
/* 348 */     this.delegate.fillOval(x, y, width, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints)
/*     */   {
/* 355 */     this.delegate.fillPolygon(xPoints, yPoints, nPoints);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void fillPolygon(Polygon p)
/*     */   {
/* 362 */     this.delegate.fillPolygon(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void fillRect(int x, int y, int width, int height)
/*     */   {
/* 369 */     this.delegate.fillRect(x, y, width, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight)
/*     */   {
/* 376 */     this.delegate.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void finalize()
/*     */   {
/* 383 */     this.delegate.finalize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Color getBackground()
/*     */   {
/* 390 */     return this.delegate.getBackground();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Shape getClip()
/*     */   {
/* 397 */     return this.delegate.getClip();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Rectangle getClipBounds()
/*     */   {
/* 404 */     return this.delegate.getClipBounds();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Rectangle getClipBounds(Rectangle r)
/*     */   {
/* 411 */     return this.delegate.getClipBounds(r);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Rectangle getClipRect()
/*     */   {
/* 419 */     return this.delegate.getClipRect();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Color getColor()
/*     */   {
/* 426 */     return this.delegate.getColor();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Composite getComposite()
/*     */   {
/* 433 */     return this.delegate.getComposite();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public GraphicsConfiguration getDeviceConfiguration()
/*     */   {
/* 440 */     return this.delegate.getDeviceConfiguration();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Font getFont()
/*     */   {
/* 447 */     return this.delegate.getFont();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FontMetrics getFontMetrics()
/*     */   {
/* 454 */     return this.delegate.getFontMetrics();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FontMetrics getFontMetrics(Font f)
/*     */   {
/* 461 */     return this.delegate.getFontMetrics(f);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public FontRenderContext getFontRenderContext()
/*     */   {
/* 468 */     return this.delegate.getFontRenderContext();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Paint getPaint()
/*     */   {
/* 475 */     return this.delegate.getPaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object getRenderingHint(RenderingHints.Key hintKey)
/*     */   {
/* 482 */     return this.delegate.getRenderingHint(hintKey);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RenderingHints getRenderingHints()
/*     */   {
/* 489 */     return this.delegate.getRenderingHints();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Stroke getStroke()
/*     */   {
/* 496 */     return this.delegate.getStroke();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AffineTransform getTransform()
/*     */   {
/* 503 */     return this.delegate.getTransform();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 510 */     return this.delegate.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean hit(Rectangle rect, Shape s, boolean onStroke)
/*     */   {
/* 517 */     return this.delegate.hit(rect, s, onStroke);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean hitClip(int x, int y, int width, int height)
/*     */   {
/* 524 */     return this.delegate.hitClip(x, y, width, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void rotate(double theta, double x, double y)
/*     */   {
/* 531 */     this.delegate.rotate(theta, x, y);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void rotate(double theta)
/*     */   {
/* 538 */     this.delegate.rotate(theta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void scale(double sx, double sy)
/*     */   {
/* 545 */     this.delegate.scale(sx, sy);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setBackground(Color color)
/*     */   {
/* 552 */     this.delegate.setBackground(color);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setClip(int x, int y, int width, int height)
/*     */   {
/* 559 */     this.delegate.setClip(x, y, width, height);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setClip(Shape clip)
/*     */   {
/* 566 */     this.delegate.setClip(clip);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setColor(Color c)
/*     */   {
/* 573 */     this.delegate.setColor(c);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setComposite(Composite comp)
/*     */   {
/* 580 */     this.delegate.setComposite(comp);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setFont(Font font)
/*     */   {
/* 587 */     this.delegate.setFont(font);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPaint(Paint paint)
/*     */   {
/* 594 */     this.delegate.setPaint(paint);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setPaintMode()
/*     */   {
/* 601 */     this.delegate.setPaintMode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue)
/*     */   {
/* 608 */     this.delegate.setRenderingHint(hintKey, hintValue);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRenderingHints(Map hints)
/*     */   {
/* 615 */     this.delegate.setRenderingHints(hints);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setStroke(Stroke s)
/*     */   {
/* 622 */     this.delegate.setStroke(s);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTransform(AffineTransform Tx)
/*     */   {
/* 629 */     this.delegate.setTransform(Tx);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setXORMode(Color c1)
/*     */   {
/* 636 */     this.delegate.setXORMode(c1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void shear(double shx, double shy)
/*     */   {
/* 643 */     this.delegate.shear(shx, shy);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 650 */     return this.delegate.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void transform(AffineTransform Tx)
/*     */   {
/* 657 */     this.delegate.transform(Tx);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void translate(double tx, double ty)
/*     */   {
/* 664 */     this.delegate.translate(tx, ty);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void translate(int x, int y)
/*     */   {
/* 671 */     this.delegate.translate(x, y);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/transform/shape/Graphics2DWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */