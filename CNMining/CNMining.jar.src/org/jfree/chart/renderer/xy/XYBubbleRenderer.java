/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.data.xy.XYZDataset;
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
/*     */ 
/*     */ public class XYBubbleRenderer
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, PublicCloneable
/*     */ {
/*     */   public static final long serialVersionUID = -5221991598674249125L;
/*     */   public static final int SCALE_ON_BOTH_AXES = 0;
/*     */   public static final int SCALE_ON_DOMAIN_AXIS = 1;
/*     */   public static final int SCALE_ON_RANGE_AXIS = 2;
/*     */   private int scaleType;
/*     */   
/*     */   public XYBubbleRenderer()
/*     */   {
/* 126 */     this(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYBubbleRenderer(int scaleType)
/*     */   {
/* 138 */     if ((scaleType < 0) || (scaleType > 2)) {
/* 139 */       throw new IllegalArgumentException("Invalid 'scaleType'.");
/*     */     }
/* 141 */     this.scaleType = scaleType;
/* 142 */     setBaseLegendShape(new Ellipse2D.Double(-4.0D, -4.0D, 8.0D, 8.0D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getScaleType()
/*     */   {
/* 152 */     return this.scaleType;
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
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 179 */     if (!getItemVisible(series, item)) {
/* 180 */       return;
/*     */     }
/*     */     
/* 183 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/*     */ 
/* 186 */     double x = dataset.getXValue(series, item);
/* 187 */     double y = dataset.getYValue(series, item);
/* 188 */     double z = NaN.0D;
/* 189 */     if ((dataset instanceof XYZDataset)) {
/* 190 */       XYZDataset xyzData = (XYZDataset)dataset;
/* 191 */       z = xyzData.getZValue(series, item);
/*     */     }
/* 193 */     if (!Double.isNaN(z)) {
/* 194 */       RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
/* 195 */       RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/* 196 */       double transX = domainAxis.valueToJava2D(x, dataArea, domainAxisLocation);
/*     */       
/* 198 */       double transY = rangeAxis.valueToJava2D(y, dataArea, rangeAxisLocation);
/*     */       
/*     */ 
/* 201 */       double transDomain = 0.0D;
/* 202 */       double transRange = 0.0D;
/*     */       
/*     */       double zero;
/* 205 */       switch (getScaleType()) {
/*     */       case 1: 
/* 207 */         zero = domainAxis.valueToJava2D(0.0D, dataArea, domainAxisLocation);
/*     */         
/* 209 */         transDomain = domainAxis.valueToJava2D(z, dataArea, domainAxisLocation) - zero;
/*     */         
/* 211 */         transRange = transDomain;
/* 212 */         break;
/*     */       case 2: 
/* 214 */         zero = rangeAxis.valueToJava2D(0.0D, dataArea, rangeAxisLocation);
/*     */         
/* 216 */         transRange = zero - rangeAxis.valueToJava2D(z, dataArea, rangeAxisLocation);
/*     */         
/* 218 */         transDomain = transRange;
/* 219 */         break;
/*     */       default: 
/* 221 */         double zero1 = domainAxis.valueToJava2D(0.0D, dataArea, domainAxisLocation);
/*     */         
/* 223 */         double zero2 = rangeAxis.valueToJava2D(0.0D, dataArea, rangeAxisLocation);
/*     */         
/* 225 */         transDomain = domainAxis.valueToJava2D(z, dataArea, domainAxisLocation) - zero1;
/*     */         
/* 227 */         transRange = zero2 - rangeAxis.valueToJava2D(z, dataArea, rangeAxisLocation);
/*     */       }
/*     */       
/* 230 */       transDomain = Math.abs(transDomain);
/* 231 */       transRange = Math.abs(transRange);
/* 232 */       Ellipse2D circle = null;
/* 233 */       if (orientation == PlotOrientation.VERTICAL) {
/* 234 */         circle = new Ellipse2D.Double(transX - transDomain / 2.0D, transY - transRange / 2.0D, transDomain, transRange);
/*     */ 
/*     */       }
/* 237 */       else if (orientation == PlotOrientation.HORIZONTAL) {
/* 238 */         circle = new Ellipse2D.Double(transY - transRange / 2.0D, transX - transDomain / 2.0D, transRange, transDomain);
/*     */       }
/*     */       
/* 241 */       g2.setPaint(getItemPaint(series, item));
/* 242 */       g2.fill(circle);
/* 243 */       g2.setStroke(getItemOutlineStroke(series, item));
/* 244 */       g2.setPaint(getItemOutlinePaint(series, item));
/* 245 */       g2.draw(circle);
/*     */       
/* 247 */       if (isItemLabelVisible(series, item)) {
/* 248 */         if (orientation == PlotOrientation.VERTICAL) {
/* 249 */           drawItemLabel(g2, orientation, dataset, series, item, transX, transY, false);
/*     */ 
/*     */         }
/* 252 */         else if (orientation == PlotOrientation.HORIZONTAL) {
/* 253 */           drawItemLabel(g2, orientation, dataset, series, item, transY, transX, false);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 259 */       EntityCollection entities = null;
/* 260 */       if (info != null) {
/* 261 */         entities = info.getOwner().getEntityCollection();
/* 262 */         if ((entities != null) && (circle.intersects(dataArea))) {
/* 263 */           addEntity(entities, circle, dataset, series, item, circle.getCenterX(), circle.getCenterY());
/*     */         }
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
/*     */   public LegendItem getLegendItem(int datasetIndex, int series)
/*     */   {
/* 286 */     LegendItem result = null;
/* 287 */     XYPlot plot = getPlot();
/* 288 */     if (plot == null) {
/* 289 */       return null;
/*     */     }
/*     */     
/* 292 */     XYDataset dataset = plot.getDataset(datasetIndex);
/* 293 */     if ((dataset != null) && 
/* 294 */       (getItemVisible(series, 0))) {
/* 295 */       String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*     */       
/* 297 */       String description = label;
/* 298 */       String toolTipText = null;
/* 299 */       if (getLegendItemToolTipGenerator() != null) {
/* 300 */         toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*     */       }
/*     */       
/* 303 */       String urlText = null;
/* 304 */       if (getLegendItemURLGenerator() != null) {
/* 305 */         urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*     */       }
/*     */       
/* 308 */       Shape shape = lookupLegendShape(series);
/* 309 */       Paint paint = lookupSeriesPaint(series);
/* 310 */       Paint outlinePaint = lookupSeriesOutlinePaint(series);
/* 311 */       Stroke outlineStroke = lookupSeriesOutlineStroke(series);
/* 312 */       result = new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
/*     */       
/* 314 */       result.setLabelFont(lookupLegendTextFont(series));
/* 315 */       Paint labelPaint = lookupLegendTextPaint(series);
/* 316 */       if (labelPaint != null) {
/* 317 */         result.setLabelPaint(labelPaint);
/*     */       }
/* 319 */       result.setDataset(dataset);
/* 320 */       result.setDatasetIndex(datasetIndex);
/* 321 */       result.setSeriesKey(dataset.getSeriesKey(series));
/* 322 */       result.setSeriesIndex(series);
/*     */     }
/*     */     
/* 325 */     return result;
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
/* 336 */     if (obj == this) {
/* 337 */       return true;
/*     */     }
/* 339 */     if (!(obj instanceof XYBubbleRenderer)) {
/* 340 */       return false;
/*     */     }
/* 342 */     XYBubbleRenderer that = (XYBubbleRenderer)obj;
/* 343 */     if (this.scaleType != that.scaleType) {
/* 344 */       return false;
/*     */     }
/* 346 */     return super.equals(obj);
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
/* 357 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYBubbleRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */