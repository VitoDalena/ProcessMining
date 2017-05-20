/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
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
/*     */ public class XYPolygonAnnotation
/*     */   extends AbstractXYAnnotation
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6984203651995900036L;
/*     */   private double[] polygon;
/*     */   private transient Stroke stroke;
/*     */   private transient Paint outlinePaint;
/*     */   private transient Paint fillPaint;
/*     */   
/*     */   public XYPolygonAnnotation(double[] polygon)
/*     */   {
/* 100 */     this(polygon, new BasicStroke(1.0F), Color.black);
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
/*     */   public XYPolygonAnnotation(double[] polygon, Stroke stroke, Paint outlinePaint)
/*     */   {
/* 117 */     this(polygon, stroke, outlinePaint, null);
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
/*     */   public XYPolygonAnnotation(double[] polygon, Stroke stroke, Paint outlinePaint, Paint fillPaint)
/*     */   {
/* 136 */     if (polygon == null) {
/* 137 */       throw new IllegalArgumentException("Null 'polygon' argument.");
/*     */     }
/* 139 */     if (polygon.length % 2 != 0) {
/* 140 */       throw new IllegalArgumentException("The 'polygon' array must contain an even number of items.");
/*     */     }
/*     */     
/* 143 */     this.polygon = ((double[])polygon.clone());
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
/*     */   public double[] getPolygonCoordinates()
/*     */   {
/* 159 */     return (double[])this.polygon.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getFillPaint()
/*     */   {
/* 170 */     return this.fillPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getOutlineStroke()
/*     */   {
/* 181 */     return this.stroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getOutlinePaint()
/*     */   {
/* 192 */     return this.outlinePaint;
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
/* 212 */     if (this.polygon.length < 4) {
/* 213 */       return;
/*     */     }
/* 215 */     PlotOrientation orientation = plot.getOrientation();
/* 216 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/*     */     
/* 218 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/*     */     
/*     */ 
/* 221 */     GeneralPath area = new GeneralPath();
/* 222 */     double x = domainAxis.valueToJava2D(this.polygon[0], dataArea, domainEdge);
/*     */     
/* 224 */     double y = rangeAxis.valueToJava2D(this.polygon[1], dataArea, rangeEdge);
/*     */     
/* 226 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 227 */       area.moveTo((float)y, (float)x);
/* 228 */       for (int i = 2; i < this.polygon.length; i += 2) {
/* 229 */         x = domainAxis.valueToJava2D(this.polygon[i], dataArea, domainEdge);
/*     */         
/* 231 */         y = rangeAxis.valueToJava2D(this.polygon[(i + 1)], dataArea, rangeEdge);
/*     */         
/* 233 */         area.lineTo((float)y, (float)x);
/*     */       }
/* 235 */       area.closePath();
/*     */     }
/* 237 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 238 */       area.moveTo((float)x, (float)y);
/* 239 */       for (int i = 2; i < this.polygon.length; i += 2) {
/* 240 */         x = domainAxis.valueToJava2D(this.polygon[i], dataArea, domainEdge);
/*     */         
/* 242 */         y = rangeAxis.valueToJava2D(this.polygon[(i + 1)], dataArea, rangeEdge);
/*     */         
/* 244 */         area.lineTo((float)x, (float)y);
/*     */       }
/* 246 */       area.closePath();
/*     */     }
/*     */     
/*     */ 
/* 250 */     if (this.fillPaint != null) {
/* 251 */       g2.setPaint(this.fillPaint);
/* 252 */       g2.fill(area);
/*     */     }
/*     */     
/* 255 */     if ((this.stroke != null) && (this.outlinePaint != null)) {
/* 256 */       g2.setPaint(this.outlinePaint);
/* 257 */       g2.setStroke(this.stroke);
/* 258 */       g2.draw(area);
/*     */     }
/* 260 */     addEntity(info, area, rendererIndex, getToolTipText(), getURL());
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
/* 272 */     if (obj == this) {
/* 273 */       return true;
/*     */     }
/*     */     
/* 276 */     if (!super.equals(obj)) {
/* 277 */       return false;
/*     */     }
/* 279 */     if (!(obj instanceof XYPolygonAnnotation)) {
/* 280 */       return false;
/*     */     }
/* 282 */     XYPolygonAnnotation that = (XYPolygonAnnotation)obj;
/* 283 */     if (!Arrays.equals(this.polygon, that.polygon)) {
/* 284 */       return false;
/*     */     }
/* 286 */     if (!ObjectUtilities.equal(this.stroke, that.stroke)) {
/* 287 */       return false;
/*     */     }
/* 289 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/* 290 */       return false;
/*     */     }
/* 292 */     if (!PaintUtilities.equal(this.fillPaint, that.fillPaint)) {
/* 293 */       return false;
/*     */     }
/*     */     
/* 296 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 305 */     int result = 193;
/* 306 */     result = 37 * result + HashUtilities.hashCodeForDoubleArray(this.polygon);
/*     */     
/* 308 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.fillPaint);
/* 309 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.outlinePaint);
/*     */     
/* 311 */     if (this.stroke != null) {
/* 312 */       result = 37 * result + this.stroke.hashCode();
/*     */     }
/* 314 */     return result;
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
/* 326 */     return super.clone();
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
/* 337 */     stream.defaultWriteObject();
/* 338 */     SerialUtilities.writeStroke(this.stroke, stream);
/* 339 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 340 */     SerialUtilities.writePaint(this.fillPaint, stream);
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
/* 353 */     stream.defaultReadObject();
/* 354 */     this.stroke = SerialUtilities.readStroke(stream);
/* 355 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 356 */     this.fillPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/XYPolygonAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */