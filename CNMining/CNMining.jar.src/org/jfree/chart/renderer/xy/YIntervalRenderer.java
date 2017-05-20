/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.ItemLabelPosition;
/*     */ import org.jfree.chart.labels.XYItemLabelGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ public class YIntervalRenderer
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2951586537224143260L;
/*     */   private XYItemLabelGenerator additionalItemLabelGenerator;
/*     */   
/*     */   public YIntervalRenderer()
/*     */   {
/* 111 */     this.additionalItemLabelGenerator = null;
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
/*     */   public XYItemLabelGenerator getAdditionalItemLabelGenerator()
/*     */   {
/* 125 */     return this.additionalItemLabelGenerator;
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
/*     */   public void setAdditionalItemLabelGenerator(XYItemLabelGenerator generator)
/*     */   {
/* 142 */     this.additionalItemLabelGenerator = generator;
/* 143 */     fireChangeEvent();
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
/*     */   public Range findRangeBounds(XYDataset dataset)
/*     */   {
/* 156 */     return findRangeBounds(dataset, true);
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
/* 191 */     EntityCollection entities = null;
/* 192 */     if (info != null) {
/* 193 */       entities = info.getOwner().getEntityCollection();
/*     */     }
/*     */     
/* 196 */     IntervalXYDataset intervalDataset = (IntervalXYDataset)dataset;
/*     */     
/* 198 */     double x = intervalDataset.getXValue(series, item);
/* 199 */     double yLow = intervalDataset.getStartYValue(series, item);
/* 200 */     double yHigh = intervalDataset.getEndYValue(series, item);
/*     */     
/* 202 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 203 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/*     */     
/* 205 */     double xx = domainAxis.valueToJava2D(x, dataArea, xAxisLocation);
/* 206 */     double yyLow = rangeAxis.valueToJava2D(yLow, dataArea, yAxisLocation);
/* 207 */     double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea, yAxisLocation);
/*     */     
/* 209 */     Paint p = getItemPaint(series, item);
/* 210 */     Stroke s = getItemStroke(series, item);
/*     */     
/* 212 */     Line2D line = null;
/* 213 */     Shape shape = getItemShape(series, item);
/* 214 */     Shape top = null;
/* 215 */     Shape bottom = null;
/* 216 */     PlotOrientation orientation = plot.getOrientation();
/* 217 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 218 */       line = new Line2D.Double(yyLow, xx, yyHigh, xx);
/* 219 */       top = ShapeUtilities.createTranslatedShape(shape, yyHigh, xx);
/* 220 */       bottom = ShapeUtilities.createTranslatedShape(shape, yyLow, xx);
/*     */     }
/* 222 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 223 */       line = new Line2D.Double(xx, yyLow, xx, yyHigh);
/* 224 */       top = ShapeUtilities.createTranslatedShape(shape, xx, yyHigh);
/* 225 */       bottom = ShapeUtilities.createTranslatedShape(shape, xx, yyLow);
/*     */     }
/* 227 */     g2.setPaint(p);
/* 228 */     g2.setStroke(s);
/* 229 */     g2.draw(line);
/*     */     
/* 231 */     g2.fill(top);
/* 232 */     g2.fill(bottom);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 238 */     if (isItemLabelVisible(series, item)) {
/* 239 */       drawItemLabel(g2, orientation, dataset, series, item, xx, yyHigh, false);
/*     */       
/* 241 */       drawAdditionalItemLabel(g2, orientation, dataset, series, item, xx, yyLow);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 246 */     if (entities != null) {
/* 247 */       addEntity(entities, line.getBounds(), dataset, series, item, 0.0D, 0.0D);
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
/*     */ 
/*     */ 
/*     */   private void drawAdditionalItemLabel(Graphics2D g2, PlotOrientation orientation, XYDataset dataset, int series, int item, double x, double y)
/*     */   {
/* 270 */     if (this.additionalItemLabelGenerator == null) {
/* 271 */       return;
/*     */     }
/*     */     
/* 274 */     Font labelFont = getItemLabelFont(series, item);
/* 275 */     Paint paint = getItemLabelPaint(series, item);
/* 276 */     g2.setFont(labelFont);
/* 277 */     g2.setPaint(paint);
/* 278 */     String label = this.additionalItemLabelGenerator.generateLabel(dataset, series, item);
/*     */     
/*     */ 
/* 281 */     ItemLabelPosition position = getNegativeItemLabelPosition(series, item);
/* 282 */     Point2D anchorPoint = calculateLabelAnchorPoint(position.getItemLabelAnchor(), x, y, orientation);
/*     */     
/* 284 */     TextUtilities.drawRotatedString(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getAngle(), position.getRotationAnchor());
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 298 */     if (obj == this) {
/* 299 */       return true;
/*     */     }
/* 301 */     if (!(obj instanceof YIntervalRenderer)) {
/* 302 */       return false;
/*     */     }
/* 304 */     YIntervalRenderer that = (YIntervalRenderer)obj;
/* 305 */     if (!ObjectUtilities.equal(this.additionalItemLabelGenerator, that.additionalItemLabelGenerator))
/*     */     {
/* 307 */       return false;
/*     */     }
/* 309 */     return super.equals(obj);
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
/* 320 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/YIntervalRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */