/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
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
/*     */ public class XYBoxAnnotation
/*     */   extends AbstractXYAnnotation
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6764703772526757457L;
/*     */   private double x0;
/*     */   private double y0;
/*     */   private double x1;
/*     */   private double y1;
/*     */   private transient Stroke stroke;
/*     */   private transient Paint outlinePaint;
/*     */   private transient Paint fillPaint;
/*     */   
/*     */   public XYBoxAnnotation(double x0, double y0, double x1, double y1)
/*     */   {
/* 107 */     this(x0, y0, x1, y1, new BasicStroke(1.0F), Color.black);
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
/*     */   public XYBoxAnnotation(double x0, double y0, double x1, double y1, Stroke stroke, Paint outlinePaint)
/*     */   {
/* 123 */     this(x0, y0, x1, y1, stroke, outlinePaint, null);
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
/*     */   public XYBoxAnnotation(double x0, double y0, double x1, double y1, Stroke stroke, Paint outlinePaint, Paint fillPaint)
/*     */   {
/* 140 */     this.x0 = x0;
/* 141 */     this.y0 = y0;
/* 142 */     this.x1 = x1;
/* 143 */     this.y1 = y1;
/* 144 */     this.stroke = stroke;
/* 145 */     this.outlinePaint = outlinePaint;
/* 146 */     this.fillPaint = fillPaint;
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
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info)
/*     */   {
/* 165 */     PlotOrientation orientation = plot.getOrientation();
/* 166 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/*     */     
/* 168 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/*     */     
/*     */ 
/* 171 */     double transX0 = domainAxis.valueToJava2D(this.x0, dataArea, domainEdge);
/*     */     
/* 173 */     double transY0 = rangeAxis.valueToJava2D(this.y0, dataArea, rangeEdge);
/* 174 */     double transX1 = domainAxis.valueToJava2D(this.x1, dataArea, domainEdge);
/*     */     
/* 176 */     double transY1 = rangeAxis.valueToJava2D(this.y1, dataArea, rangeEdge);
/*     */     
/* 178 */     Rectangle2D box = null;
/* 179 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 180 */       box = new Rectangle2D.Double(transY0, transX1, transY1 - transY0, transX0 - transX1);
/*     */ 
/*     */     }
/* 183 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 184 */       box = new Rectangle2D.Double(transX0, transY1, transX1 - transX0, transY0 - transY1);
/*     */     }
/*     */     
/*     */ 
/* 188 */     if (this.fillPaint != null) {
/* 189 */       g2.setPaint(this.fillPaint);
/* 190 */       g2.fill(box);
/*     */     }
/*     */     
/* 193 */     if ((this.stroke != null) && (this.outlinePaint != null)) {
/* 194 */       g2.setPaint(this.outlinePaint);
/* 195 */       g2.setStroke(this.stroke);
/* 196 */       g2.draw(box);
/*     */     }
/* 198 */     addEntity(info, box, rendererIndex, getToolTipText(), getURL());
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
/* 210 */     if (obj == this) {
/* 211 */       return true;
/*     */     }
/*     */     
/* 214 */     if (!super.equals(obj)) {
/* 215 */       return false;
/*     */     }
/* 217 */     if (!(obj instanceof XYBoxAnnotation)) {
/* 218 */       return false;
/*     */     }
/* 220 */     XYBoxAnnotation that = (XYBoxAnnotation)obj;
/* 221 */     if (this.x0 != that.x0) {
/* 222 */       return false;
/*     */     }
/* 224 */     if (this.y0 != that.y0) {
/* 225 */       return false;
/*     */     }
/* 227 */     if (this.x1 != that.x1) {
/* 228 */       return false;
/*     */     }
/* 230 */     if (this.y1 != that.y1) {
/* 231 */       return false;
/*     */     }
/* 233 */     if (!ObjectUtilities.equal(this.stroke, that.stroke)) {
/* 234 */       return false;
/*     */     }
/* 236 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/* 237 */       return false;
/*     */     }
/* 239 */     if (!PaintUtilities.equal(this.fillPaint, that.fillPaint)) {
/* 240 */       return false;
/*     */     }
/*     */     
/* 243 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 254 */     long temp = Double.doubleToLongBits(this.x0);
/* 255 */     int result = (int)(temp ^ temp >>> 32);
/* 256 */     temp = Double.doubleToLongBits(this.x1);
/* 257 */     result = 29 * result + (int)(temp ^ temp >>> 32);
/* 258 */     temp = Double.doubleToLongBits(this.y0);
/* 259 */     result = 29 * result + (int)(temp ^ temp >>> 32);
/* 260 */     temp = Double.doubleToLongBits(this.y1);
/* 261 */     result = 29 * result + (int)(temp ^ temp >>> 32);
/* 262 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 274 */     return super.clone();
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
/* 285 */     stream.defaultWriteObject();
/* 286 */     SerialUtilities.writeStroke(this.stroke, stream);
/* 287 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 288 */     SerialUtilities.writePaint(this.fillPaint, stream);
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
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 302 */     stream.defaultReadObject();
/* 303 */     this.stroke = SerialUtilities.readStroke(stream);
/* 304 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 305 */     this.fillPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/XYBoxAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */