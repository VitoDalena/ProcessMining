/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYShapeAnnotation
/*     */   extends AbstractXYAnnotation
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8553218317600684041L;
/*     */   private transient Shape shape;
/*     */   private transient Stroke stroke;
/*     */   private transient Paint outlinePaint;
/*     */   private transient Paint fillPaint;
/*     */   
/*     */   public XYShapeAnnotation(Shape shape)
/*     */   {
/* 111 */     this(shape, new BasicStroke(1.0F), Color.black);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYShapeAnnotation(Shape shape, Stroke stroke, Paint outlinePaint)
/*     */   {
/* 123 */     this(shape, stroke, outlinePaint, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYShapeAnnotation(Shape shape, Stroke stroke, Paint outlinePaint, Paint fillPaint)
/*     */   {
/* 137 */     if (shape == null) {
/* 138 */       throw new IllegalArgumentException("Null 'shape' argument.");
/*     */     }
/* 140 */     this.shape = shape;
/* 141 */     this.stroke = stroke;
/* 142 */     this.outlinePaint = outlinePaint;
/* 143 */     this.fillPaint = fillPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info)
/*     */   {
/* 163 */     PlotOrientation orientation = plot.getOrientation();
/* 164 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/*     */     
/* 166 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 171 */     Rectangle2D bounds = this.shape.getBounds2D();
/* 172 */     double x0 = bounds.getMinX();
/* 173 */     double x1 = bounds.getMaxX();
/* 174 */     double xx0 = domainAxis.valueToJava2D(x0, dataArea, domainEdge);
/* 175 */     double xx1 = domainAxis.valueToJava2D(x1, dataArea, domainEdge);
/* 176 */     double m00 = (xx1 - xx0) / (x1 - x0);
/* 177 */     double m02 = xx0 - x0 * m00;
/*     */     
/* 179 */     double y0 = bounds.getMaxY();
/* 180 */     double y1 = bounds.getMinY();
/* 181 */     double yy0 = rangeAxis.valueToJava2D(y0, dataArea, rangeEdge);
/* 182 */     double yy1 = rangeAxis.valueToJava2D(y1, dataArea, rangeEdge);
/* 183 */     double m11 = (yy1 - yy0) / (y1 - y0);
/* 184 */     double m12 = yy0 - m11 * y0;
/*     */     
/*     */ 
/* 187 */     Shape s = null;
/* 188 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 189 */       AffineTransform t1 = new AffineTransform(0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
/*     */       
/* 191 */       AffineTransform t2 = new AffineTransform(m11, 0.0D, 0.0D, m00, m12, m02);
/*     */       
/* 193 */       s = t1.createTransformedShape(this.shape);
/* 194 */       s = t2.createTransformedShape(s);
/*     */     }
/* 196 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 197 */       AffineTransform t = new AffineTransform(m00, 0.0D, 0.0D, m11, m02, m12);
/* 198 */       s = t.createTransformedShape(this.shape);
/*     */     }
/*     */     
/* 201 */     if (this.fillPaint != null) {
/* 202 */       g2.setPaint(this.fillPaint);
/* 203 */       g2.fill(s);
/*     */     }
/*     */     
/* 206 */     if ((this.stroke != null) && (this.outlinePaint != null)) {
/* 207 */       g2.setPaint(this.outlinePaint);
/* 208 */       g2.setStroke(this.stroke);
/* 209 */       g2.draw(s);
/*     */     }
/* 211 */     addEntity(info, s, rendererIndex, getToolTipText(), getURL());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 223 */     if (obj == this) {
/* 224 */       return true;
/*     */     }
/*     */     
/* 227 */     if (!super.equals(obj)) {
/* 228 */       return false;
/*     */     }
/* 230 */     if (!(obj instanceof XYShapeAnnotation)) {
/* 231 */       return false;
/*     */     }
/* 233 */     XYShapeAnnotation that = (XYShapeAnnotation)obj;
/* 234 */     if (!this.shape.equals(that.shape)) {
/* 235 */       return false;
/*     */     }
/* 237 */     if (!ObjectUtilities.equal(this.stroke, that.stroke)) {
/* 238 */       return false;
/*     */     }
/* 240 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/* 241 */       return false;
/*     */     }
/* 243 */     if (!PaintUtilities.equal(this.fillPaint, that.fillPaint)) {
/* 244 */       return false;
/*     */     }
/*     */     
/* 247 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 256 */     int result = 193;
/* 257 */     result = 37 * result + this.shape.hashCode();
/* 258 */     if (this.stroke != null) {
/* 259 */       result = 37 * result + this.stroke.hashCode();
/*     */     }
/* 261 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.outlinePaint);
/*     */     
/* 263 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.fillPaint);
/* 264 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 275 */     return super.clone();
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
/* 286 */     stream.defaultWriteObject();
/* 287 */     SerialUtilities.writeShape(this.shape, stream);
/* 288 */     SerialUtilities.writeStroke(this.stroke, stream);
/* 289 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 290 */     SerialUtilities.writePaint(this.fillPaint, stream);
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
/* 303 */     stream.defaultReadObject();
/* 304 */     this.shape = SerialUtilities.readShape(stream);
/* 305 */     this.stroke = SerialUtilities.readStroke(stream);
/* 306 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 307 */     this.fillPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/XYShapeAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */