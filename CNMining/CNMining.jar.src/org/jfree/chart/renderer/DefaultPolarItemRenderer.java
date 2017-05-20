/*     */ package org.jfree.chart.renderer;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Point;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.NumberTick;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.DrawingSupplier;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.PolarPlot;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.BooleanList;
/*     */ import org.jfree.util.BooleanUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultPolarItemRenderer
/*     */   extends AbstractRenderer
/*     */   implements PolarItemRenderer
/*     */ {
/*     */   private PolarPlot plot;
/*     */   private BooleanList seriesFilled;
/*     */   
/*     */   public DefaultPolarItemRenderer()
/*     */   {
/*  95 */     this.seriesFilled = new BooleanList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPlot(PolarPlot plot)
/*     */   {
/* 106 */     this.plot = plot;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PolarPlot getPlot()
/*     */   {
/* 117 */     return this.plot;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DrawingSupplier getDrawingSupplier()
/*     */   {
/* 126 */     DrawingSupplier result = null;
/* 127 */     PolarPlot p = getPlot();
/* 128 */     if (p != null) {
/* 129 */       result = p.getDrawingSupplier();
/*     */     }
/* 131 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isSeriesFilled(int series)
/*     */   {
/* 143 */     boolean result = false;
/* 144 */     Boolean b = this.seriesFilled.getBoolean(series);
/* 145 */     if (b != null) {
/* 146 */       result = b.booleanValue();
/*     */     }
/* 148 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeriesFilled(int series, boolean filled)
/*     */   {
/* 158 */     this.seriesFilled.setBoolean(series, BooleanUtilities.valueOf(filled));
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
/*     */   public void drawSeries(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, PolarPlot plot, XYDataset dataset, int seriesIndex)
/*     */   {
/* 178 */     Polygon poly = new Polygon();
/* 179 */     int numPoints = dataset.getItemCount(seriesIndex);
/* 180 */     for (int i = 0; i < numPoints; i++) {
/* 181 */       double theta = dataset.getXValue(seriesIndex, i);
/* 182 */       double radius = dataset.getYValue(seriesIndex, i);
/* 183 */       Point p = plot.translateValueThetaRadiusToJava2D(theta, radius, dataArea);
/*     */       
/* 185 */       poly.addPoint(p.x, p.y);
/*     */     }
/* 187 */     g2.setPaint(lookupSeriesPaint(seriesIndex));
/* 188 */     g2.setStroke(lookupSeriesStroke(seriesIndex));
/* 189 */     if (isSeriesFilled(seriesIndex)) {
/* 190 */       Composite savedComposite = g2.getComposite();
/* 191 */       g2.setComposite(AlphaComposite.getInstance(3, 0.5F));
/*     */       
/* 193 */       g2.fill(poly);
/* 194 */       g2.setComposite(savedComposite);
/*     */     }
/*     */     else {
/* 197 */       g2.draw(poly);
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
/*     */   public void drawAngularGridLines(Graphics2D g2, PolarPlot plot, List ticks, Rectangle2D dataArea)
/*     */   {
/* 214 */     g2.setFont(plot.getAngleLabelFont());
/* 215 */     g2.setStroke(plot.getAngleGridlineStroke());
/* 216 */     g2.setPaint(plot.getAngleGridlinePaint());
/*     */     
/* 218 */     double axisMin = plot.getAxis().getLowerBound();
/* 219 */     double maxRadius = plot.getMaxRadius();
/*     */     
/* 221 */     Point center = plot.translateValueThetaRadiusToJava2D(axisMin, axisMin, dataArea);
/*     */     
/* 223 */     Iterator iterator = ticks.iterator();
/* 224 */     while (iterator.hasNext()) {
/* 225 */       NumberTick tick = (NumberTick)iterator.next();
/* 226 */       Point p = plot.translateValueThetaRadiusToJava2D(tick.getNumber().doubleValue(), maxRadius, dataArea);
/*     */       
/* 228 */       g2.setPaint(plot.getAngleGridlinePaint());
/* 229 */       g2.drawLine(center.x, center.y, p.x, p.y);
/* 230 */       if (plot.isAngleLabelsVisible()) {
/* 231 */         int x = p.x;
/* 232 */         int y = p.y;
/* 233 */         g2.setPaint(plot.getAngleLabelPaint());
/* 234 */         TextUtilities.drawAlignedString(tick.getText(), g2, x, y, TextAnchor.CENTER);
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
/*     */   public void drawRadialGridLines(Graphics2D g2, PolarPlot plot, ValueAxis radialAxis, List ticks, Rectangle2D dataArea)
/*     */   {
/* 255 */     g2.setFont(radialAxis.getTickLabelFont());
/* 256 */     g2.setPaint(plot.getRadiusGridlinePaint());
/* 257 */     g2.setStroke(plot.getRadiusGridlineStroke());
/*     */     
/* 259 */     double axisMin = radialAxis.getLowerBound();
/* 260 */     Point center = plot.translateValueThetaRadiusToJava2D(axisMin, axisMin, dataArea);
/*     */     
/*     */ 
/* 263 */     Iterator iterator = ticks.iterator();
/* 264 */     while (iterator.hasNext()) {
/* 265 */       NumberTick tick = (NumberTick)iterator.next();
/* 266 */       Point p = plot.translateValueThetaRadiusToJava2D(90.0D, tick.getNumber().doubleValue(), dataArea);
/*     */       
/* 268 */       int r = p.x - center.x;
/* 269 */       int upperLeftX = center.x - r;
/* 270 */       int upperLeftY = center.y - r;
/* 271 */       int d = 2 * r;
/* 272 */       Ellipse2D ring = new Ellipse2D.Double(upperLeftX, upperLeftY, d, d);
/* 273 */       g2.setPaint(plot.getRadiusGridlinePaint());
/* 274 */       g2.draw(ring);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LegendItem getLegendItem(int series)
/*     */   {
/* 286 */     LegendItem result = null;
/* 287 */     PolarPlot polarPlot = getPlot();
/* 288 */     if (polarPlot != null) {
/* 289 */       XYDataset dataset = polarPlot.getDataset();
/* 290 */       if (dataset != null) {
/* 291 */         String label = dataset.getSeriesKey(series).toString();
/* 292 */         String description = label;
/* 293 */         Shape shape = lookupSeriesShape(series);
/* 294 */         Paint paint = lookupSeriesPaint(series);
/* 295 */         Paint outlinePaint = lookupSeriesOutlinePaint(series);
/* 296 */         Stroke outlineStroke = lookupSeriesOutlineStroke(series);
/* 297 */         result = new LegendItem(label, description, null, null, shape, paint, outlineStroke, outlinePaint);
/*     */         
/* 299 */         result.setDataset(dataset);
/*     */       }
/*     */     }
/* 302 */     return result;
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
/* 314 */     if (obj == null) {
/* 315 */       return false;
/*     */     }
/* 317 */     if (!(obj instanceof DefaultPolarItemRenderer)) {
/* 318 */       return false;
/*     */     }
/* 320 */     DefaultPolarItemRenderer that = (DefaultPolarItemRenderer)obj;
/* 321 */     if (!this.seriesFilled.equals(that.seriesFilled)) {
/* 322 */       return false;
/*     */     }
/* 324 */     return super.equals(obj);
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
/* 335 */     DefaultPolarItemRenderer clone = (DefaultPolarItemRenderer)super.clone();
/*     */     
/* 337 */     clone.seriesFilled = ((BooleanList)this.seriesFilled.clone());
/* 338 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/DefaultPolarItemRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */