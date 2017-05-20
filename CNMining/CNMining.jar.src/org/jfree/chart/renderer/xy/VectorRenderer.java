/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.xy.VectorXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
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
/*     */ public class VectorRenderer
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*  81 */   private double baseLength = 0.1D;
/*     */   
/*     */ 
/*  84 */   private double headLength = 0.14D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range findDomainBounds(XYDataset dataset)
/*     */   {
/* 103 */     if (dataset == null) {
/* 104 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 106 */     double minimum = Double.POSITIVE_INFINITY;
/* 107 */     double maximum = Double.NEGATIVE_INFINITY;
/* 108 */     int seriesCount = dataset.getSeriesCount();
/*     */     
/*     */ 
/* 111 */     if ((dataset instanceof VectorXYDataset)) {
/* 112 */       VectorXYDataset vdataset = (VectorXYDataset)dataset;
/* 113 */       for (int series = 0; series < seriesCount; series++) {
/* 114 */         int itemCount = dataset.getItemCount(series);
/* 115 */         for (int item = 0; item < itemCount; item++) {
/* 116 */           double delta = vdataset.getVectorXValue(series, item);
/* 117 */           double lvalue; double lvalue; double uvalue; if (delta < 0.0D) {
/* 118 */             double uvalue = vdataset.getXValue(series, item);
/* 119 */             lvalue = uvalue + delta;
/*     */           }
/*     */           else {
/* 122 */             lvalue = vdataset.getXValue(series, item);
/* 123 */             uvalue = lvalue + delta;
/*     */           }
/* 125 */           minimum = Math.min(minimum, lvalue);
/* 126 */           maximum = Math.max(maximum, uvalue);
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 131 */       for (int series = 0; series < seriesCount; series++) {
/* 132 */         int itemCount = dataset.getItemCount(series);
/* 133 */         for (int item = 0; item < itemCount; item++) {
/* 134 */           double lvalue = dataset.getXValue(series, item);
/* 135 */           double uvalue = lvalue;
/* 136 */           minimum = Math.min(minimum, lvalue);
/* 137 */           maximum = Math.max(maximum, uvalue);
/*     */         }
/*     */       }
/*     */     }
/* 141 */     if (minimum > maximum) {
/* 142 */       return null;
/*     */     }
/*     */     
/* 145 */     return new Range(minimum, maximum);
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
/*     */   public Range findRangeBounds(XYDataset dataset)
/*     */   {
/* 159 */     if (dataset == null) {
/* 160 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 162 */     double minimum = Double.POSITIVE_INFINITY;
/* 163 */     double maximum = Double.NEGATIVE_INFINITY;
/* 164 */     int seriesCount = dataset.getSeriesCount();
/*     */     
/*     */ 
/* 167 */     if ((dataset instanceof VectorXYDataset)) {
/* 168 */       VectorXYDataset vdataset = (VectorXYDataset)dataset;
/* 169 */       for (int series = 0; series < seriesCount; series++) {
/* 170 */         int itemCount = dataset.getItemCount(series);
/* 171 */         for (int item = 0; item < itemCount; item++) {
/* 172 */           double delta = vdataset.getVectorYValue(series, item);
/* 173 */           double lvalue; double lvalue; double uvalue; if (delta < 0.0D) {
/* 174 */             double uvalue = vdataset.getYValue(series, item);
/* 175 */             lvalue = uvalue + delta;
/*     */           }
/*     */           else {
/* 178 */             lvalue = vdataset.getYValue(series, item);
/* 179 */             uvalue = lvalue + delta;
/*     */           }
/* 181 */           minimum = Math.min(minimum, lvalue);
/* 182 */           maximum = Math.max(maximum, uvalue);
/*     */         }
/*     */       }
/*     */     }
/*     */     else {
/* 187 */       for (int series = 0; series < seriesCount; series++) {
/* 188 */         int itemCount = dataset.getItemCount(series);
/* 189 */         for (int item = 0; item < itemCount; item++) {
/* 190 */           double lvalue = dataset.getYValue(series, item);
/* 191 */           double uvalue = lvalue;
/* 192 */           minimum = Math.min(minimum, lvalue);
/* 193 */           maximum = Math.max(maximum, uvalue);
/*     */         }
/*     */       }
/*     */     }
/* 197 */     if (minimum > maximum) {
/* 198 */       return null;
/*     */     }
/*     */     
/* 201 */     return new Range(minimum, maximum);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 226 */     double x = dataset.getXValue(series, item);
/* 227 */     double y = dataset.getYValue(series, item);
/* 228 */     double dx = 0.0D;
/* 229 */     double dy = 0.0D;
/* 230 */     if ((dataset instanceof VectorXYDataset)) {
/* 231 */       dx = ((VectorXYDataset)dataset).getVectorXValue(series, item);
/* 232 */       dy = ((VectorXYDataset)dataset).getVectorYValue(series, item);
/*     */     }
/* 234 */     double xx0 = domainAxis.valueToJava2D(x, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 236 */     double yy0 = rangeAxis.valueToJava2D(y, dataArea, plot.getRangeAxisEdge());
/*     */     
/* 238 */     double xx1 = domainAxis.valueToJava2D(x + dx, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 240 */     double yy1 = rangeAxis.valueToJava2D(y + dy, dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/* 243 */     PlotOrientation orientation = plot.getOrientation();
/* 244 */     Line2D line; Line2D line; if (orientation.equals(PlotOrientation.HORIZONTAL)) {
/* 245 */       line = new Line2D.Double(yy0, xx0, yy1, xx1);
/*     */     }
/*     */     else {
/* 248 */       line = new Line2D.Double(xx0, yy0, xx1, yy1);
/*     */     }
/* 250 */     g2.setPaint(getItemPaint(series, item));
/* 251 */     g2.setStroke(getItemStroke(series, item));
/* 252 */     g2.draw(line);
/*     */     
/*     */ 
/* 255 */     double dxx = xx1 - xx0;
/* 256 */     double dyy = yy1 - yy0;
/* 257 */     double bx = xx0 + (1.0D - this.baseLength) * dxx;
/* 258 */     double by = yy0 + (1.0D - this.baseLength) * dyy;
/*     */     
/* 260 */     double cx = xx0 + (1.0D - this.headLength) * dxx;
/* 261 */     double cy = yy0 + (1.0D - this.headLength) * dyy;
/*     */     
/* 263 */     double angle = 0.0D;
/* 264 */     if (dxx != 0.0D) {
/* 265 */       angle = 1.5707963267948966D - Math.atan(dyy / dxx);
/*     */     }
/* 267 */     double deltaX = 2.0D * Math.cos(angle);
/* 268 */     double deltaY = 2.0D * Math.sin(angle);
/*     */     
/* 270 */     double leftx = cx + deltaX;
/* 271 */     double lefty = cy - deltaY;
/* 272 */     double rightx = cx - deltaX;
/* 273 */     double righty = cy + deltaY;
/*     */     
/* 275 */     GeneralPath p = new GeneralPath();
/* 276 */     if (orientation == PlotOrientation.VERTICAL) {
/* 277 */       p.moveTo((float)xx1, (float)yy1);
/* 278 */       p.lineTo((float)rightx, (float)righty);
/* 279 */       p.lineTo((float)bx, (float)by);
/* 280 */       p.lineTo((float)leftx, (float)lefty);
/*     */     }
/*     */     else {
/* 283 */       p.moveTo((float)yy1, (float)xx1);
/* 284 */       p.lineTo((float)righty, (float)rightx);
/* 285 */       p.lineTo((float)by, (float)bx);
/* 286 */       p.lineTo((float)lefty, (float)leftx);
/*     */     }
/* 288 */     p.closePath();
/* 289 */     g2.draw(p);
/*     */     
/*     */ 
/* 292 */     EntityCollection entities = null;
/* 293 */     if (info != null) {
/* 294 */       entities = info.getOwner().getEntityCollection();
/* 295 */       if (entities != null) {
/* 296 */         addEntity(entities, line.getBounds(), dataset, series, item, 0.0D, 0.0D);
/*     */       }
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
/* 318 */     if (obj == this) {
/* 319 */       return true;
/*     */     }
/* 321 */     if (!(obj instanceof VectorRenderer)) {
/* 322 */       return false;
/*     */     }
/* 324 */     VectorRenderer that = (VectorRenderer)obj;
/* 325 */     if (this.baseLength != that.baseLength) {
/* 326 */       return false;
/*     */     }
/* 328 */     if (this.headLength != that.headLength) {
/* 329 */       return false;
/*     */     }
/* 331 */     return super.equals(obj);
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
/* 343 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/VectorRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */