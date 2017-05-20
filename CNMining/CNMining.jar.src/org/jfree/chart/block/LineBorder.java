/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LineBorder
/*     */   implements BlockFrame, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 4630356736707233924L;
/*     */   private transient Paint paint;
/*     */   private transient Stroke stroke;
/*     */   private RectangleInsets insets;
/*     */   
/*     */   public LineBorder()
/*     */   {
/*  85 */     this(Color.black, new BasicStroke(1.0F), new RectangleInsets(1.0D, 1.0D, 1.0D, 1.0D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LineBorder(Paint paint, Stroke stroke, RectangleInsets insets)
/*     */   {
/*  97 */     if (paint == null) {
/*  98 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 100 */     if (stroke == null) {
/* 101 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 103 */     if (insets == null) {
/* 104 */       throw new IllegalArgumentException("Null 'insets' argument.");
/*     */     }
/* 106 */     this.paint = paint;
/* 107 */     this.stroke = stroke;
/* 108 */     this.insets = insets;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPaint()
/*     */   {
/* 117 */     return this.paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleInsets getInsets()
/*     */   {
/* 126 */     return this.insets;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getStroke()
/*     */   {
/* 135 */     return this.stroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void draw(Graphics2D g2, Rectangle2D area)
/*     */   {
/* 145 */     double w = area.getWidth();
/* 146 */     double h = area.getHeight();
/*     */     
/* 148 */     if ((w <= 0.0D) || (h <= 0.0D)) {
/* 149 */       return;
/*     */     }
/* 151 */     double t = this.insets.calculateTopInset(h);
/* 152 */     double b = this.insets.calculateBottomInset(h);
/* 153 */     double l = this.insets.calculateLeftInset(w);
/* 154 */     double r = this.insets.calculateRightInset(w);
/* 155 */     double x = area.getX();
/* 156 */     double y = area.getY();
/* 157 */     double x0 = x + l / 2.0D;
/* 158 */     double x1 = x + w - r / 2.0D;
/* 159 */     double y0 = y + h - b / 2.0D;
/* 160 */     double y1 = y + t / 2.0D;
/* 161 */     g2.setPaint(getPaint());
/* 162 */     g2.setStroke(getStroke());
/* 163 */     Line2D line = new Line2D.Double();
/* 164 */     if (t > 0.0D) {
/* 165 */       line.setLine(x0, y1, x1, y1);
/* 166 */       g2.draw(line);
/*     */     }
/* 168 */     if (b > 0.0D) {
/* 169 */       line.setLine(x0, y0, x1, y0);
/* 170 */       g2.draw(line);
/*     */     }
/* 172 */     if (l > 0.0D) {
/* 173 */       line.setLine(x0, y0, x0, y1);
/* 174 */       g2.draw(line);
/*     */     }
/* 176 */     if (r > 0.0D) {
/* 177 */       line.setLine(x1, y0, x1, y1);
/* 178 */       g2.draw(line);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 190 */     if (obj == this) {
/* 191 */       return true;
/*     */     }
/* 193 */     if (!(obj instanceof LineBorder)) {
/* 194 */       return false;
/*     */     }
/* 196 */     LineBorder that = (LineBorder)obj;
/* 197 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 198 */       return false;
/*     */     }
/* 200 */     if (!ObjectUtilities.equal(this.stroke, that.stroke)) {
/* 201 */       return false;
/*     */     }
/* 203 */     if (!this.insets.equals(that.insets)) {
/* 204 */       return false;
/*     */     }
/* 206 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 217 */     stream.defaultWriteObject();
/* 218 */     SerialUtilities.writePaint(this.paint, stream);
/* 219 */     SerialUtilities.writeStroke(this.stroke, stream);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 232 */     stream.defaultReadObject();
/* 233 */     this.paint = SerialUtilities.readPaint(stream);
/* 234 */     this.stroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/LineBorder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */