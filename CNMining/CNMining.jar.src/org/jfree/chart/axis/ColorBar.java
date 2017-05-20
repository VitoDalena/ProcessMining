/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.plot.ColorPalette;
/*     */ import org.jfree.chart.plot.ContourPlot;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.RainbowPalette;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.contour.ContourDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public class ColorBar
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2101776212647268103L;
/*     */   public static final int DEFAULT_COLORBAR_THICKNESS = 0;
/*     */   public static final double DEFAULT_COLORBAR_THICKNESS_PERCENT = 0.1D;
/*     */   public static final int DEFAULT_OUTERGAP = 2;
/*     */   private ValueAxis axis;
/*  97 */   private int colorBarThickness = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/* 102 */   private double colorBarThicknessPercent = 0.1D;
/*     */   
/*     */ 
/*     */ 
/* 106 */   private ColorPalette colorPalette = null;
/*     */   
/*     */ 
/* 109 */   private int colorBarLength = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int outerGap;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ColorBar(String label)
/*     */   {
/* 122 */     NumberAxis a = new NumberAxis(label);
/* 123 */     a.setAutoRangeIncludesZero(false);
/* 124 */     this.axis = a;
/* 125 */     this.axis.setLowerMargin(0.0D);
/* 126 */     this.axis.setUpperMargin(0.0D);
/*     */     
/* 128 */     this.colorPalette = new RainbowPalette();
/* 129 */     this.colorBarThickness = 0;
/* 130 */     this.colorBarThicknessPercent = 0.1D;
/* 131 */     this.outerGap = 2;
/* 132 */     this.colorPalette.setMinZ(this.axis.getRange().getLowerBound());
/* 133 */     this.colorPalette.setMaxZ(this.axis.getRange().getUpperBound());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void configure(ContourPlot plot)
/*     */   {
/* 143 */     double minZ = plot.getDataset().getMinZValue();
/* 144 */     double maxZ = plot.getDataset().getMaxZValue();
/* 145 */     setMinimumValue(minZ);
/* 146 */     setMaximumValue(maxZ);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ValueAxis getAxis()
/*     */   {
/* 155 */     return this.axis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAxis(ValueAxis axis)
/*     */   {
/* 164 */     this.axis = axis;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void autoAdjustRange()
/*     */   {
/* 171 */     this.axis.autoAdjustRange();
/* 172 */     this.colorPalette.setMinZ(this.axis.getLowerBound());
/* 173 */     this.colorPalette.setMaxZ(this.axis.getUpperBound());
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
/*     */   public double draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, Rectangle2D reservedArea, RectangleEdge edge)
/*     */   {
/* 195 */     Rectangle2D colorBarArea = null;
/*     */     
/* 197 */     double thickness = calculateBarThickness(dataArea, edge);
/* 198 */     if (this.colorBarThickness > 0) {
/* 199 */       thickness = this.colorBarThickness;
/*     */     }
/*     */     
/* 202 */     double length = 0.0D;
/* 203 */     if (RectangleEdge.isLeftOrRight(edge)) {
/* 204 */       length = dataArea.getHeight();
/*     */     }
/*     */     else {
/* 207 */       length = dataArea.getWidth();
/*     */     }
/*     */     
/* 210 */     if (this.colorBarLength > 0) {
/* 211 */       length = this.colorBarLength;
/*     */     }
/*     */     
/* 214 */     if (edge == RectangleEdge.BOTTOM) {
/* 215 */       colorBarArea = new Rectangle2D.Double(dataArea.getX(), plotArea.getMaxY() + this.outerGap, length, thickness);
/*     */ 
/*     */     }
/* 218 */     else if (edge == RectangleEdge.TOP) {
/* 219 */       colorBarArea = new Rectangle2D.Double(dataArea.getX(), reservedArea.getMinY() + this.outerGap, length, thickness);
/*     */ 
/*     */     }
/* 222 */     else if (edge == RectangleEdge.LEFT) {
/* 223 */       colorBarArea = new Rectangle2D.Double(plotArea.getX() - thickness - this.outerGap, dataArea.getMinY(), thickness, length);
/*     */ 
/*     */     }
/* 226 */     else if (edge == RectangleEdge.RIGHT) {
/* 227 */       colorBarArea = new Rectangle2D.Double(plotArea.getMaxX() + this.outerGap, dataArea.getMinY(), thickness, length);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 232 */     this.axis.refreshTicks(g2, new AxisState(), colorBarArea, edge);
/*     */     
/* 234 */     drawColorBar(g2, colorBarArea, edge);
/*     */     
/* 236 */     AxisState state = null;
/* 237 */     if (edge == RectangleEdge.TOP) {
/* 238 */       cursor = colorBarArea.getMinY();
/* 239 */       state = this.axis.draw(g2, cursor, reservedArea, colorBarArea, RectangleEdge.TOP, null);
/*     */ 
/*     */     }
/* 242 */     else if (edge == RectangleEdge.BOTTOM) {
/* 243 */       cursor = colorBarArea.getMaxY();
/* 244 */       state = this.axis.draw(g2, cursor, reservedArea, colorBarArea, RectangleEdge.BOTTOM, null);
/*     */ 
/*     */     }
/* 247 */     else if (edge == RectangleEdge.LEFT) {
/* 248 */       cursor = colorBarArea.getMinX();
/* 249 */       state = this.axis.draw(g2, cursor, reservedArea, colorBarArea, RectangleEdge.LEFT, null);
/*     */ 
/*     */     }
/* 252 */     else if (edge == RectangleEdge.RIGHT) {
/* 253 */       cursor = colorBarArea.getMaxX();
/* 254 */       state = this.axis.draw(g2, cursor, reservedArea, colorBarArea, RectangleEdge.RIGHT, null);
/*     */     }
/*     */     
/* 257 */     return state.getCursor();
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
/*     */   public void drawColorBar(Graphics2D g2, Rectangle2D colorBarArea, RectangleEdge edge)
/*     */   {
/* 272 */     Object antiAlias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
/* 273 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 279 */     Stroke strokeSaved = g2.getStroke();
/* 280 */     g2.setStroke(new BasicStroke(1.0F));
/*     */     
/* 282 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 283 */       double y1 = colorBarArea.getY();
/* 284 */       double y2 = colorBarArea.getMaxY();
/* 285 */       double xx = colorBarArea.getX();
/* 286 */       Line2D line = new Line2D.Double();
/* 287 */       while (xx <= colorBarArea.getMaxX()) {
/* 288 */         double value = this.axis.java2DToValue(xx, colorBarArea, edge);
/* 289 */         line.setLine(xx, y1, xx, y2);
/* 290 */         g2.setPaint(getPaint(value));
/* 291 */         g2.draw(line);
/* 292 */         xx += 1.0D;
/*     */       }
/*     */     }
/*     */     else {
/* 296 */       double y1 = colorBarArea.getX();
/* 297 */       double y2 = colorBarArea.getMaxX();
/* 298 */       double xx = colorBarArea.getY();
/* 299 */       Line2D line = new Line2D.Double();
/* 300 */       while (xx <= colorBarArea.getMaxY()) {
/* 301 */         double value = this.axis.java2DToValue(xx, colorBarArea, edge);
/* 302 */         line.setLine(y1, xx, y2, xx);
/* 303 */         g2.setPaint(getPaint(value));
/* 304 */         g2.draw(line);
/* 305 */         xx += 1.0D;
/*     */       }
/*     */     }
/*     */     
/* 309 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias);
/* 310 */     g2.setStroke(strokeSaved);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ColorPalette getColorPalette()
/*     */   {
/* 320 */     return this.colorPalette;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPaint(double value)
/*     */   {
/* 331 */     return this.colorPalette.getPaint(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setColorPalette(ColorPalette palette)
/*     */   {
/* 340 */     this.colorPalette = palette;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMaximumValue(double value)
/*     */   {
/* 349 */     this.colorPalette.setMaxZ(value);
/* 350 */     this.axis.setUpperBound(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMinimumValue(double value)
/*     */   {
/* 359 */     this.colorPalette.setMinZ(value);
/* 360 */     this.axis.setLowerBound(value);
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
/*     */   public AxisSpace reserveSpace(Graphics2D g2, Plot plot, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, AxisSpace space)
/*     */   {
/* 380 */     AxisSpace result = this.axis.reserveSpace(g2, plot, plotArea, edge, space);
/*     */     
/* 382 */     double thickness = calculateBarThickness(dataArea, edge);
/* 383 */     result.add(thickness + 2 * this.outerGap, edge);
/* 384 */     return result;
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
/*     */   private double calculateBarThickness(Rectangle2D plotArea, RectangleEdge edge)
/*     */   {
/* 398 */     double result = 0.0D;
/* 399 */     if (RectangleEdge.isLeftOrRight(edge)) {
/* 400 */       result = plotArea.getWidth() * this.colorBarThicknessPercent;
/*     */     }
/*     */     else {
/* 403 */       result = plotArea.getHeight() * this.colorBarThicknessPercent;
/*     */     }
/* 405 */     return result;
/*     */   }
/*     */   
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
/* 418 */     ColorBar clone = (ColorBar)super.clone();
/* 419 */     clone.axis = ((ValueAxis)this.axis.clone());
/* 420 */     return clone;
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 433 */     if (obj == this) {
/* 434 */       return true;
/*     */     }
/* 436 */     if (!(obj instanceof ColorBar)) {
/* 437 */       return false;
/*     */     }
/* 439 */     ColorBar that = (ColorBar)obj;
/* 440 */     if (!this.axis.equals(that.axis)) {
/* 441 */       return false;
/*     */     }
/* 443 */     if (this.colorBarThickness != that.colorBarThickness) {
/* 444 */       return false;
/*     */     }
/* 446 */     if (this.colorBarThicknessPercent != that.colorBarThicknessPercent) {
/* 447 */       return false;
/*     */     }
/* 449 */     if (!this.colorPalette.equals(that.colorPalette)) {
/* 450 */       return false;
/*     */     }
/* 452 */     if (this.colorBarLength != that.colorBarLength) {
/* 453 */       return false;
/*     */     }
/* 455 */     if (this.outerGap != that.outerGap) {
/* 456 */       return false;
/*     */     }
/* 458 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 468 */     return this.axis.hashCode();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/ColorBar.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */