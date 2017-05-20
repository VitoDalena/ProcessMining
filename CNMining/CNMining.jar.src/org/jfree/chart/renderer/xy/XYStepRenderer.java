/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.urls.XYURLGenerator;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.RectangleEdge;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYStepRenderer
/*     */   extends XYLineAndShapeRenderer
/*     */   implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8918141928884796108L;
/* 116 */   private double stepPoint = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */   public XYStepRenderer()
/*     */   {
/* 122 */     this(null, null);
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
/*     */   public XYStepRenderer(XYToolTipGenerator toolTipGenerator, XYURLGenerator urlGenerator)
/*     */   {
/* 136 */     setBaseToolTipGenerator(toolTipGenerator);
/* 137 */     setURLGenerator(urlGenerator);
/* 138 */     setBaseShapesVisible(false);
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
/*     */   public double getStepPoint()
/*     */   {
/* 155 */     return this.stepPoint;
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
/*     */   public void setStepPoint(double stepPoint)
/*     */   {
/* 169 */     if ((stepPoint < 0.0D) || (stepPoint > 1.0D)) {
/* 170 */       throw new IllegalArgumentException("Requires stepPoint in [0.0;1.0]");
/*     */     }
/*     */     
/* 173 */     this.stepPoint = stepPoint;
/* 174 */     fireChangeEvent();
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
/*     */ 
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
/* 209 */     if (!getItemVisible(series, item)) {
/* 210 */       return;
/*     */     }
/*     */     
/* 213 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/* 215 */     Paint seriesPaint = getItemPaint(series, item);
/* 216 */     Stroke seriesStroke = getItemStroke(series, item);
/* 217 */     g2.setPaint(seriesPaint);
/* 218 */     g2.setStroke(seriesStroke);
/*     */     
/*     */ 
/* 221 */     double x1 = dataset.getXValue(series, item);
/* 222 */     double y1 = dataset.getYValue(series, item);
/*     */     
/* 224 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 225 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 226 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/* 227 */     double transY1 = Double.isNaN(y1) ? NaN.0D : rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
/*     */     
/*     */ 
/* 230 */     if ((pass == 0) && (item > 0))
/*     */     {
/* 232 */       double x0 = dataset.getXValue(series, item - 1);
/* 233 */       double y0 = dataset.getYValue(series, item - 1);
/* 234 */       double transX0 = domainAxis.valueToJava2D(x0, dataArea, xAxisLocation);
/*     */       
/* 236 */       double transY0 = Double.isNaN(y0) ? NaN.0D : rangeAxis.valueToJava2D(y0, dataArea, yAxisLocation);
/*     */       
/*     */ 
/* 239 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 240 */         if (transY0 == transY1)
/*     */         {
/*     */ 
/* 243 */           drawLine(g2, state.workingLine, transY0, transX0, transY1, transX1);
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/* 249 */           double transXs = transX0 + getStepPoint() * (transX1 - transX0);
/*     */           
/* 251 */           drawLine(g2, state.workingLine, transY0, transX0, transY0, transXs);
/*     */           
/* 253 */           drawLine(g2, state.workingLine, transY0, transXs, transY1, transXs);
/*     */           
/* 255 */           drawLine(g2, state.workingLine, transY1, transXs, transY1, transX1);
/*     */         }
/*     */         
/*     */       }
/* 259 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 260 */         if (transY0 == transY1)
/*     */         {
/* 262 */           drawLine(g2, state.workingLine, transX0, transY0, transX1, transY1);
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/* 267 */           double transXs = transX0 + getStepPoint() * (transX1 - transX0);
/*     */           
/* 269 */           drawLine(g2, state.workingLine, transX0, transY0, transXs, transY0);
/*     */           
/* 271 */           drawLine(g2, state.workingLine, transXs, transY0, transXs, transY1);
/*     */           
/* 273 */           drawLine(g2, state.workingLine, transXs, transY1, transX1, transY1);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 279 */       int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
/* 280 */       int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
/* 281 */       updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, rangeAxisIndex, transX1, transY1, orientation);
/*     */       
/*     */ 
/*     */ 
/* 285 */       EntityCollection entities = state.getEntityCollection();
/* 286 */       if (entities != null) {
/* 287 */         addEntity(entities, null, dataset, series, item, transX1, transY1);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 293 */     if (pass == 1)
/*     */     {
/* 295 */       if (isItemLabelVisible(series, item)) {
/* 296 */         double xx = transX1;
/* 297 */         double yy = transY1;
/* 298 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 299 */           xx = transY1;
/* 300 */           yy = transX1;
/*     */         }
/* 302 */         drawItemLabel(g2, orientation, dataset, series, item, xx, yy, y1 < 0.0D);
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
/*     */   private void drawLine(Graphics2D g2, Line2D line, double x0, double y0, double x1, double y1)
/*     */   {
/* 321 */     if ((Double.isNaN(x0)) || (Double.isNaN(x1)) || (Double.isNaN(y0)) || (Double.isNaN(y1)))
/*     */     {
/* 323 */       return;
/*     */     }
/* 325 */     line.setLine(x0, y0, x1, y1);
/* 326 */     g2.draw(line);
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
/* 337 */     if (obj == this) {
/* 338 */       return true;
/*     */     }
/* 340 */     if (!(obj instanceof XYLineAndShapeRenderer)) {
/* 341 */       return false;
/*     */     }
/* 343 */     XYStepRenderer that = (XYStepRenderer)obj;
/* 344 */     if (this.stepPoint != that.stepPoint) {
/* 345 */       return false;
/*     */     }
/* 347 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 356 */     return HashUtilities.hashCode(super.hashCode(), this.stepPoint);
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
/* 367 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYStepRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */