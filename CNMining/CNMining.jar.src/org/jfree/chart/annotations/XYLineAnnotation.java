/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Float;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.util.LineUtilities;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYLineAnnotation
/*     */   extends AbstractXYAnnotation
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -80535465244091334L;
/*     */   private double x1;
/*     */   private double y1;
/*     */   private double x2;
/*     */   private double y2;
/*     */   private transient Stroke stroke;
/*     */   private transient Paint paint;
/*     */   
/*     */   public XYLineAnnotation(double x1, double y1, double x2, double y2)
/*     */   {
/* 116 */     this(x1, y1, x2, y2, new BasicStroke(1.0F), Color.black);
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
/*     */   public XYLineAnnotation(double x1, double y1, double x2, double y2, Stroke stroke, Paint paint)
/*     */   {
/* 134 */     if (stroke == null) {
/* 135 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 137 */     if (paint == null) {
/* 138 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 140 */     this.x1 = x1;
/* 141 */     this.y1 = y1;
/* 142 */     this.x2 = x2;
/* 143 */     this.y2 = y2;
/* 144 */     this.stroke = stroke;
/* 145 */     this.paint = paint;
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
/*     */ 
/*     */ 
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info)
/*     */   {
/* 167 */     PlotOrientation orientation = plot.getOrientation();
/* 168 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/*     */     
/* 170 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/*     */     
/* 172 */     float j2DX1 = 0.0F;
/* 173 */     float j2DX2 = 0.0F;
/* 174 */     float j2DY1 = 0.0F;
/* 175 */     float j2DY2 = 0.0F;
/* 176 */     if (orientation == PlotOrientation.VERTICAL) {
/* 177 */       j2DX1 = (float)domainAxis.valueToJava2D(this.x1, dataArea, domainEdge);
/*     */       
/* 179 */       j2DY1 = (float)rangeAxis.valueToJava2D(this.y1, dataArea, rangeEdge);
/*     */       
/* 181 */       j2DX2 = (float)domainAxis.valueToJava2D(this.x2, dataArea, domainEdge);
/*     */       
/* 183 */       j2DY2 = (float)rangeAxis.valueToJava2D(this.y2, dataArea, rangeEdge);
/*     */ 
/*     */     }
/* 186 */     else if (orientation == PlotOrientation.HORIZONTAL) {
/* 187 */       j2DY1 = (float)domainAxis.valueToJava2D(this.x1, dataArea, domainEdge);
/*     */       
/* 189 */       j2DX1 = (float)rangeAxis.valueToJava2D(this.y1, dataArea, rangeEdge);
/*     */       
/* 191 */       j2DY2 = (float)domainAxis.valueToJava2D(this.x2, dataArea, domainEdge);
/*     */       
/* 193 */       j2DX2 = (float)rangeAxis.valueToJava2D(this.y2, dataArea, rangeEdge);
/*     */     }
/*     */     
/* 196 */     g2.setPaint(this.paint);
/* 197 */     g2.setStroke(this.stroke);
/* 198 */     Line2D line = new Line2D.Float(j2DX1, j2DY1, j2DX2, j2DY2);
/*     */     
/*     */ 
/* 201 */     boolean visible = LineUtilities.clipLine(line, dataArea);
/* 202 */     if (visible) {
/* 203 */       g2.draw(line);
/*     */     }
/*     */     
/* 206 */     String toolTip = getToolTipText();
/* 207 */     String url = getURL();
/* 208 */     if ((toolTip != null) || (url != null)) {
/* 209 */       addEntity(info, ShapeUtilities.createLineRegion(line, 1.0F), rendererIndex, toolTip, url);
/*     */     }
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
/* 222 */     if (obj == this) {
/* 223 */       return true;
/*     */     }
/* 225 */     if (!super.equals(obj)) {
/* 226 */       return false;
/*     */     }
/* 228 */     if (!(obj instanceof XYLineAnnotation)) {
/* 229 */       return false;
/*     */     }
/* 231 */     XYLineAnnotation that = (XYLineAnnotation)obj;
/* 232 */     if (this.x1 != that.x1) {
/* 233 */       return false;
/*     */     }
/* 235 */     if (this.y1 != that.y1) {
/* 236 */       return false;
/*     */     }
/* 238 */     if (this.x2 != that.x2) {
/* 239 */       return false;
/*     */     }
/* 241 */     if (this.y2 != that.y2) {
/* 242 */       return false;
/*     */     }
/* 244 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 245 */       return false;
/*     */     }
/* 247 */     if (!ObjectUtilities.equal(this.stroke, that.stroke)) {
/* 248 */       return false;
/*     */     }
/*     */     
/* 251 */     return true;
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
/* 262 */     long temp = Double.doubleToLongBits(this.x1);
/* 263 */     int result = (int)(temp ^ temp >>> 32);
/* 264 */     temp = Double.doubleToLongBits(this.x2);
/* 265 */     result = 29 * result + (int)(temp ^ temp >>> 32);
/* 266 */     temp = Double.doubleToLongBits(this.y1);
/* 267 */     result = 29 * result + (int)(temp ^ temp >>> 32);
/* 268 */     temp = Double.doubleToLongBits(this.y2);
/* 269 */     result = 29 * result + (int)(temp ^ temp >>> 32);
/* 270 */     return result;
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
/* 281 */     return super.clone();
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
/* 292 */     stream.defaultWriteObject();
/* 293 */     SerialUtilities.writePaint(this.paint, stream);
/* 294 */     SerialUtilities.writeStroke(this.stroke, stream);
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
/* 307 */     stream.defaultReadObject();
/* 308 */     this.paint = SerialUtilities.readPaint(stream);
/* 309 */     this.stroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/XYLineAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */