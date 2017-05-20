/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
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
/*     */ 
/*     */ public class XYDotRenderer
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, PublicCloneable
/*     */ {
/*     */   private static final long serialVersionUID = -2764344339073566425L;
/*     */   private int dotWidth;
/*     */   private int dotHeight;
/*     */   private transient Shape legendShape;
/*     */   
/*     */   public XYDotRenderer()
/*     */   {
/* 110 */     this.dotWidth = 1;
/* 111 */     this.dotHeight = 1;
/* 112 */     this.legendShape = new Rectangle2D.Double(-3.0D, -3.0D, 6.0D, 6.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDotWidth()
/*     */   {
/* 124 */     return this.dotWidth;
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
/*     */   public void setDotWidth(int w)
/*     */   {
/* 139 */     if (w < 1) {
/* 140 */       throw new IllegalArgumentException("Requires w > 0.");
/*     */     }
/* 142 */     this.dotWidth = w;
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
/*     */   public int getDotHeight()
/*     */   {
/* 155 */     return this.dotHeight;
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
/*     */   public void setDotHeight(int h)
/*     */   {
/* 170 */     if (h < 1) {
/* 171 */       throw new IllegalArgumentException("Requires h > 0.");
/*     */     }
/* 173 */     this.dotHeight = h;
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
/*     */   public Shape getLegendShape()
/*     */   {
/* 187 */     return this.legendShape;
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
/*     */   public void setLegendShape(Shape shape)
/*     */   {
/* 201 */     if (shape == null) {
/* 202 */       throw new IllegalArgumentException("Null 'shape' argument.");
/*     */     }
/* 204 */     this.legendShape = shape;
/* 205 */     fireChangeEvent();
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
/* 240 */     if (!getItemVisible(series, item)) {
/* 241 */       return;
/*     */     }
/*     */     
/*     */ 
/* 245 */     double x = dataset.getXValue(series, item);
/* 246 */     double y = dataset.getYValue(series, item);
/* 247 */     double adjx = (this.dotWidth - 1) / 2.0D;
/* 248 */     double adjy = (this.dotHeight - 1) / 2.0D;
/* 249 */     if (!Double.isNaN(y)) {
/* 250 */       RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 251 */       RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 252 */       double transX = domainAxis.valueToJava2D(x, dataArea, xAxisLocation) - adjx;
/*     */       
/* 254 */       double transY = rangeAxis.valueToJava2D(y, dataArea, yAxisLocation) - adjy;
/*     */       
/*     */ 
/* 257 */       g2.setPaint(getItemPaint(series, item));
/* 258 */       PlotOrientation orientation = plot.getOrientation();
/* 259 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 260 */         g2.fillRect((int)transY, (int)transX, this.dotHeight, this.dotWidth);
/*     */ 
/*     */       }
/* 263 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 264 */         g2.fillRect((int)transX, (int)transY, this.dotWidth, this.dotHeight);
/*     */       }
/*     */       
/*     */ 
/* 268 */       int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
/* 269 */       int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
/* 270 */       updateCrosshairValues(crosshairState, x, y, domainAxisIndex, rangeAxisIndex, transX, transY, orientation);
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
/*     */   public LegendItem getLegendItem(int datasetIndex, int series)
/*     */   {
/* 288 */     XYPlot plot = getPlot();
/* 289 */     if (plot == null) {
/* 290 */       return null;
/*     */     }
/*     */     
/* 293 */     XYDataset dataset = plot.getDataset(datasetIndex);
/* 294 */     if (dataset == null) {
/* 295 */       return null;
/*     */     }
/*     */     
/* 298 */     LegendItem result = null;
/* 299 */     if (getItemVisible(series, 0)) {
/* 300 */       String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*     */       
/* 302 */       String description = label;
/* 303 */       String toolTipText = null;
/* 304 */       if (getLegendItemToolTipGenerator() != null) {
/* 305 */         toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*     */       }
/*     */       
/* 308 */       String urlText = null;
/* 309 */       if (getLegendItemURLGenerator() != null) {
/* 310 */         urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*     */       }
/*     */       
/* 313 */       Paint fillPaint = lookupSeriesPaint(series);
/* 314 */       result = new LegendItem(label, description, toolTipText, urlText, getLegendShape(), fillPaint);
/*     */       
/* 316 */       result.setLabelFont(lookupLegendTextFont(series));
/* 317 */       Paint labelPaint = lookupLegendTextPaint(series);
/* 318 */       if (labelPaint != null) {
/* 319 */         result.setLabelPaint(labelPaint);
/*     */       }
/* 321 */       result.setSeriesKey(dataset.getSeriesKey(series));
/* 322 */       result.setSeriesIndex(series);
/* 323 */       result.setDataset(dataset);
/* 324 */       result.setDatasetIndex(datasetIndex);
/*     */     }
/*     */     
/* 327 */     return result;
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 346 */     if (obj == this) {
/* 347 */       return true;
/*     */     }
/* 349 */     if (!(obj instanceof XYDotRenderer)) {
/* 350 */       return false;
/*     */     }
/* 352 */     XYDotRenderer that = (XYDotRenderer)obj;
/* 353 */     if (this.dotWidth != that.dotWidth) {
/* 354 */       return false;
/*     */     }
/* 356 */     if (this.dotHeight != that.dotHeight) {
/* 357 */       return false;
/*     */     }
/* 359 */     if (!ShapeUtilities.equal(this.legendShape, that.legendShape)) {
/* 360 */       return false;
/*     */     }
/* 362 */     return super.equals(obj);
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
/* 373 */     return super.clone();
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
/* 386 */     stream.defaultReadObject();
/* 387 */     this.legendShape = SerialUtilities.readShape(stream);
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
/* 398 */     stream.defaultWriteObject();
/* 399 */     SerialUtilities.writeShape(this.legendShape, stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYDotRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */